package com.varxyz.javacafe.dao;

import java.util.List;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.varxyz.javacafe.dto.Category;

@Repository("categoryDao")
public class CategoryDao {
	private JdbcTemplate jdbcTemplate;
	
	public CategoryDao(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	// 카테고리 추가
	public void addCategory(Category category) {
		String sql = "INSERT INTO Category (name) "
					+ "VALUES (?)";
				
		jdbcTemplate.update(sql, category.getName());
	}
	
	// 카테고리 리스트
	public List<Category> findAllCaList() {
		String sql = "SELECT * FROM Category";

		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<Category>(Category.class));
	}
	
	// 카테고리 이름 목록
	public List<Category> selectMenutList(String name) {
		String sql = "SELECT * FROM Category WHERE name = ?";
		
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<Category>(Category.class), name);
	}

	// 카테고리 수정	
	public void modifyCategory(String afterName, String name) {
		String sql = "UPDATE Category SET name = ? WHERE name = ?";	//  앞에 name이 afterName, 뒤에 name이 기존 name 
		
		jdbcTemplate.update(sql, afterName, name);
	}

	// 카테고리 삭제
	public void deleteCategory(String name) {
		String sql = "DELETE FROM Category WHERE name = ?";
		
		jdbcTemplate.update(sql, name);
	}
}

