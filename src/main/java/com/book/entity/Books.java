package com.book.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Books {
	
	private Integer id;
	private String name;
	private String author;
	private double price;
	private String photo;
	
		
	
	

}
