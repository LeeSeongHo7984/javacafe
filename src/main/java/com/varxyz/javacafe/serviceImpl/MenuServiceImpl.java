package com.varxyz.javacafe.serviceImpl;

import java.util.List;

import com.varxyz.javacafe.dto.Menu;
import com.varxyz.javacafe.service.MenuService;

public class MenuServiceImpl implements MenuService {
	
	// 메뉴 추가
	@Override
	public void addMenu(Menu menu, String imgName) {
		menuDao.addMenu(menu, imgName);
	}
	
	// 메뉴 리스트
	public List<Menu> findAllMenuListByCn(String categoryName) {
		return menuDao.findAllMenuListByCn(categoryName);
	}
	
	// 메뉴만 검색
	public List<Menu> findAllMenuList() {
		return menuDao.findAllMenuList();
	}
	
	// 메뉴 선택
	@Override
	public List<Menu> selectMenuByMenu(String name) {
		return menuDao.selectMenuByMenu(name);
	}

	// 메뉴 수정
	@Override
	public void modifyMenu(String name, String afterName, String price, String imgName) {
		menuDao.modifyMenu(name, afterName, price, imgName);
		
	}

	// 메뉴 삭제
	@Override
	public void deleteMenu(String name, String imgName) {
		menuDao.deleteMenu(name, imgName);
	}
	
}
