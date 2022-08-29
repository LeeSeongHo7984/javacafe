package com.varxyz.javacafe.service;

import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.varxyz.javacafe.dao.MenuDao;
import com.varxyz.javacafe.dto.Menu;
import com.varxyz.javacafe.jdbc.DataSourceConfig;

public interface MenuService {
	AnnotationConfigApplicationContext context = 
			new AnnotationConfigApplicationContext(DataSourceConfig.class);
	
	MenuDao menuDao = context.getBean("menuDao", MenuDao.class);
	
	// 메뉴 추가
	public void addMenu(Menu menu, String imgName);

	// 메뉴 리스트
	public List<Menu> findAllMenuListByCn(String categoryName);
	
	// 메뉴만 가져오기
	public List<Menu> findAllMenuList();
	
	//메뉴 선택
	public List<Menu> selectMenuByMenu(String name);
	
	// 메뉴 수정
	public void modifyMenu(String name, String afterName, String price, String imgName);

	// 메뉴 삭제
	public void deleteMenu(String name, String imgName);
	
}
