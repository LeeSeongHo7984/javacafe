package com.varxyz.javacafe.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Menu {
	private String name;
	private String price;
	private String categoryName;
	private String imgName;
	private String afterName;
}
