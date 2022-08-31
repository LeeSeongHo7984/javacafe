# javacafe
 + 키오스크를 바탕으로 한 기본적인 카페 주문 ☕️
 + 2022.07.16 ~ 2022.8.7

## 사용한 기술 및 환경
 + eclipse
 + Spring
 + MySQL
 + JSP
 + HTML/CSS
 + Java
 + tomcat 8.5
 + git

## 구현기능
+ 카테고리 추가 및 목록보기, 삭제
+ 메뉴 추가 및 수정, 삭제
+ 결제화면 (기능X, 화면만 구현)

## 주요 코드
 + 카테고리 추가
 
         AddCategoryController 일부
         
               @PostMapping("/success") 
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

                return "category/add/successCategory";

              }
              
![image](https://user-images.githubusercontent.com/75346102/187584052-fc2a4a0d-7405-470b-921e-c171852c010e.png)


        
  + 메뉴 추가
           ++ 각각의 카테고리에 맞게 그 안에 메뉴들을 표시해줍니다.
           ++ 메뉴를 추가 할시 파일을 추가합니다.
           ++ 가지고 온 파일의 이름은 DB에 저장한 뒤 저장되 있는 고유번호를 가지고 와 이미지를 띄워줍니다
           
           
           AddMenuController 일부
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
                
![image](https://user-images.githubusercontent.com/75346102/187584327-e5528b47-2a11-4d48-9ff7-79f0bda6f44c.png)


  + 메뉴를 선택 시 결제 화면
![image](https://user-images.githubusercontent.com/75346102/187585563-d2aa8008-67f9-42ff-b150-38b0fdf15a29.png)

         
# 참고
  + 이미지업로드 오픈소스 참고 블로그 -> https://devofroad.tistory.com/m/45
 
