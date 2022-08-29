package com.varxyz.javacafe.service;

import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.varxyz.javacafe.dao.CategoryDao;
import com.varxyz.javacafe.dto.Category;
import com.varxyz.javacafe.dto.Menu;
import com.varxyz.javacafe.jdbc.DataSourceConfig;

public interface CategoryService {
	AnnotationConfigApplicationContext context = 
			new AnnotationConfigApplicationContext(DataSourceConfig.class);
	
	CategoryDao categoryDao = context.getBean("categoryDao", CategoryDao.class);
	
	// 카테고리 추가
	public void addCategory(Category category);
	
	// 카테고리 목록 
	public List<Category> findAllCaList();
	
	// 메뉴 목록 선택
	List<Category> selectMenutList(String name);
	
	// 카테고리 수정
	public void modifyCategory(String afterName, String name);

	// 카테고리 삭제
	public void deleteCategory(String name);
	
}
