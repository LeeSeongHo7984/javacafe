package com.varxyz.javacafe.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.varxyz.javacafe.dto.Category;
import com.varxyz.javacafe.dto.Menu;
import com.varxyz.javacafe.service.CategoryService;
import com.varxyz.javacafe.service.MenuService;
import com.varxyz.javacafe.serviceImpl.CategoryServiceImpl;
import com.varxyz.javacafe.serviceImpl.MenuServiceImpl;

@Controller("controller.CafeController") // 빈에게 등록하라고 적어줌
public class CafeController {

	// MenuService는 interface라서 객체를 못만드니까
	// MenuService를 상속받은 MenuServiceImpl을 받는다
	MenuService menuService = new MenuServiceImpl();
	CategoryService categoryService = new CategoryServiceImpl();

	// 메인 페이지
	@GetMapping("/home/homePage")
	public String homePageForm() {
		return "home/homePage";
	}

//	----------- 메뉴 영역 ---------------
	
	// 메뉴 추가 및 수정화면
	@GetMapping("/auMenu")
	public String auMenuFrom() {
		
		return "auMenu/auMenu";
	}
	
	// 메뉴 추가
	@GetMapping("/addMenu")
	public String addMenuForm(Model model) {
		List<Category> categoryList = new ArrayList<Category>();
		categoryList = categoryService.findAllCaList(); // 디비에서 불러와야됨

		model.addAttribute("category", new Category());
		model.addAttribute("categoryList", categoryList);

		return "menu/addMenu/addMenu";
	}

	@PostMapping("/addMenu")
	public String addMenu(@RequestParam("file") MultipartFile file, 
								HttpServletRequest request, String imgName, Model model) {
		
		// 파일추가
		String fileRealName = file.getOriginalFilename(); //파일명을 얻어낼 수 있는 메서드!
		long size = file.getSize(); //파일 사이즈
		
		if(fileRealName == null || fileRealName.length() == 0) {
			
			model.addAttribute("msg", "프로필 사진을 등록해 주세요!!");
			
			return "alert/back";
		}
		
		System.out.println("파일명 : "  + fileRealName);
		System.out.println("용량크기(byte) : " + size);
		
		//서버에 저장할 파일이름 fileextension으로 .jsp이런식의  확장자 명을 구함
		String fileExtension = fileRealName.substring(fileRealName.lastIndexOf("."),fileRealName.length());
		String uploadFolder = "C:\\LSH\\workspace\\javacafe\\src\\main\\webapp\\resources\\img";
		
		/*
		  파일 업로드시 파일명이 동일한 파일이 이미 존재할 수도 있고 사용자가 
		  업로드 하는 파일명이 언어 이외의 언어로 되어있을 수 있습니다. 
		  타인어를 지원하지 않는 환경에서는 정산 동작이 되지 않습니다.(리눅스가 대표적인 예시)
		  고유한 랜던 문자를 통해 db와 서버에 저장할 파일명을 새롭게 만들어 준다.
		 */
		
		UUID uuid = UUID.randomUUID();
		System.out.println(uuid.toString());
		String[] uuids = uuid.toString().split("-");
		
		String uniqueName = uuids[0];
		System.out.println("생성된 고유문자열" + uniqueName);
		System.out.println("확장자명" + fileExtension);
		
		// File saveFile = new File(uploadFolder+"\\"+fileRealName); uuid 적용 전
		
		File saveFile = new File(uploadFolder+"\\"+uniqueName + fileExtension);  // 적용 후
		try {
			file.transferTo(saveFile); // 실제 파일 저장메서드(filewriter 작업을 손쉽게 한방에 처리해준다.)
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Category category = new Category();
		Menu menu = new Menu();
		
		if(request.getParameter("categoryName").equals("unknown")) {
			System.out.println(category.getName());
			
			model.addAttribute("msg", "카테고리를 선택해 주세요");
			
			return "errorMsg";
		}
		
		menu.setName(request.getParameter("name"));
		menu.setPrice(request.getParameter("price"));
		menu.setCategoryName(request.getParameter("categoryName"));
		menu.setImgName(request.getParameter("imgName"));
		
		menuService.addMenu(menu, uniqueName);
		MenuService.context.close();
		 
		return "menu/addMenu/successAddMenu";
	}
	
	// 메뉴 목록 조회
	@GetMapping("/selectMenu")
	public String selectMenuForm(HttpSession session, Model model) {

		return "menu/select/selectMenu";
	}

	@PostMapping("/selectMenu")
	public String selectMenu(Menu name, HttpSession session, Model model) {
		model.addAttribute("menuList", menuService.selectMenuByMenu(name.getName()));
		MenuService.context.close();

		return "menu/select/successSelectMenu";
	}

	// 메뉴 목록 수정
	@GetMapping("/modifyMenu") 
	public String modifyMenuFrom(MultipartFile file, String name, Model model) {
		
		List<Menu> menuList = new ArrayList<Menu>();
		
		menuList= menuService.findAllMenuList(); // 메뉴 리스트 안의 모든 값들을 불러온다
		System.out.println(menuList);	
		model.addAttribute("menuList", menuList);
		
		return "modifyMenu/modifyMenu";
	}
	
//	public String modifyMenu 이게 헷갈리면 밑에 코드 보기
//	public static void main(String[] args) {
//		MenuService.main();
//	}
	
	@PostMapping("/modifyMenu")
	public String modifyMenu(@RequestParam("file") MultipartFile file, HttpServletRequest request, Model model) {
		
		String fileRealName = file.getOriginalFilename(); // 파일명을 얻어낼 수 있는 메소드
		long size = file.getSize(); // 파일 사이즈
		
		String name = (String)request.getParameter("name");
		String afterName = (String)request.getParameter("afterName");
		String price = (String)request.getParameter("price");
		String imgName = menuService.selectMenuByMenu(name).get(0).getImgName();
		System.out.println(menuService.selectMenuByMenu(name));
		
		// 사용자가 이미지를 업로드 하지 않았을 경우 예외 처리
		if (fileRealName == null || fileRealName.length() == 0) {
			
			menuService.modifyMenu(name, afterName, price, imgName);
			
			model.addAttribute("msg", "수정이 완료되었습니다!!");
			
			return "user/modifyUser";
			
		}
		
		System.out.println("파일명 : " + fileRealName);
		System.out.println("파일크기 : " + size);
		
		String fileExtension = fileRealName.substring(fileRealName.lastIndexOf("."), fileRealName.length());
		String uploadFolder = "C:\\LSH\\workspace\\javacafe\\src\\main\\webapp\\resources\\img"; 
		
		// 고유한 랜덤 문자생성 해서 db와 서버에 저장할 파일명을 새롭게 만들어 주는 코드
		UUID uuid = UUID.randomUUID();
		System.out.println(uuid.toString());
		String[] uuids = uuid.toString().split("-");
		
		String uniqueName = uuids[0];
		System.out.println("생성된 고유문자 : " + uniqueName);
		System.out.println("확장자 : " + fileExtension);
		
		File saveFile = new File(uploadFolder + "\\" + uniqueName + fileExtension);
		
		try {
			file.transferTo(saveFile);	// 실제 파일 저장메소드
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		menuService.modifyMenu(name, afterName, price, uniqueName);
		
		return "modifyMenu/scModifyMenu";
	}
	
	// 메뉴 목록 삭제
	@PostMapping("deleteMenu")
	public String deleteMenu(String imgName, HttpServletRequest request, HttpSession session, String name) {
		
		String dbImgName = request.getParameter("imgName");
		System.out.println(dbImgName);
		
		String filePath = "C:\\LSH\\workspace\\javacafe\\src\\main\\webapp\\resources\\img" + dbImgName + ".jpg";
		
		File deleteFile = new File(filePath);
		System.out.println(deleteFile);
		// 파일 존재 여부 확인
		if(deleteFile.exists()) {
			// 있으면 삭제
			session.removeAttribute(dbImgName);
			deleteFile.delete();
			System.out.println("파일 삭제 완료");
		} else {
			System.out.println("파일이 존재하지 않습니다.");
		}
		
		menuService.deleteMenu(name, dbImgName);
		
		return "modifyMenu/scDeleteMenu";
	}
	
	// 결제화면
	@GetMapping("/successPayment")
	public String PaymentForm(HttpSession session, Model model) {

		return "payment/successPayment";
	}
	
//	----------- 카테고리 영역 ---------------
	
	// 카테고리 추가
	@GetMapping("/auCategory")
	public String auCategoryForm() {
		
		return "auCategory/auCategory";
	}

	// 카테고리 추가
	@GetMapping("/addCategory") // 여기는 내가 지정하고 싶은 url 주소
	public String addCategoryForm(Model model) {
		model.addAttribute("category", new Category());
		return "category/add/addCategory"; // jsp 경로
	}

	@PostMapping("/success") // jsp에 action값이랑 맞춰야 한다
	public String addCategory(Category category, Model model) {
		
		try {
			if(category.getName() == null || category.getName() == "") {
				model.addAttribute("msg", "카테고리를 입력해주세요");
				
				return "errorMsg";
			}
		} catch (IllegalStateException e) {
			if(categoryService.findAllCaList().size() > 0) {
			model.addAttribute("msg", "이미 존재하는 카테고리 입니다");
			
			return "errorMsg";
			}
		}
		
		model.addAttribute("category", category);
		categoryService.addCategory(category);

		return "category/add/successCategory"; // jsp 경로

}

	// 카테고리 목록 조회
	@GetMapping("/inquiryCategory")
	public String findAllCaList(Model model) {
		List<Category> categoryList = new ArrayList<Category>();
		categoryList = categoryService.findAllCaList(); // 디비에서 불러와야됨

		model.addAttribute("categoryList", categoryList);
		return "category/inquiry/inquiryCategory";
	}

	@PostMapping("/selectCategory")
	public String findAllMenuList(Category category, HttpServletRequest request, HttpSession session, Model model) {
		List<Menu> menuList = new ArrayList<Menu>();
		List<Category> categoryList = new ArrayList<Category>();
		System.out.println("카테고리 리스트 값 : " +  categoryList);
		menuList = menuService.findAllMenuListByCn(category.getName());

		try {
			if(!category.getName().equals("unknown")) {
				session.setAttribute("categoryName", category.getName());
				System.out.println(category.getName()); // 결과 : 선택한 카테고리의 이름
				
				model.addAttribute("menuList", menuList);
				System.out.println(menuList.get(0).getName()); // 결과 : 메뉴 이름
				
				CategoryService.context.close();
				
				return "menu/select/selectMenu";
			}
		} catch (Exception e) {
			if(categoryList.size() == 0) {
			model.addAttribute("msg", "죄송합니다, 준비중인 상품입니다");
			
			return "errorMsg";
			}
		}
		
		model.addAttribute("msg", "카테고리를 선택해 주세요");
		
		return "errorMsg";
	}
	
	// 카테고리 선택 및 수정
	@GetMapping("/modifyCategory")
	public String updateCategoryForm(Model model) {
		
		List<Category> categoryList = new ArrayList<Category>();
		categoryList = categoryService.findAllCaList();
		
		model.addAttribute("categoryList", categoryList);
		
		return "modifyCategory/modifyCategory";
	}
	
	@PostMapping("/modifyCategory")
	public String  modifyCategory(String afterName, String name) {
		System.out.println(afterName);	// 바뀌고 난 후 카테고리명
		System.out.println(name);	// 기존 카테고리명
		categoryService.modifyCategory(afterName,  name);	
		
		return "modifyCategory/scModifyCategory";
	}
	
	// 카테고리 삭제
	@PostMapping("/deleteCategory")
	public String deleteCategory(String name) {
		categoryService.deleteCategory(name);
		
		return "modifyCategory/scDeleteCategory";
	}

}