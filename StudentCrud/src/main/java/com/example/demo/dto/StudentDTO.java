
package com.example.demo.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentDTO {
    
	//private int id;
	private String name;
    private String course;
    private int age;
	
    //public StudentDTO(int id, String name, String course, int age) {
    	public StudentDTO( String name, String course, int age) {
		super();
	//	this.id = id;
		this.name = name;
		this.course = course;
		this.age = age;
	}

//	public int getId() {
//		return id;
//	}
//
//	public void setId(int id) {
//		this.id = id;
//	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
	
    
   
}
