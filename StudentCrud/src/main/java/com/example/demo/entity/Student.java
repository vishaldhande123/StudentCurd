package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE) 
    @Column(name = "SID") 
    private Long id;

    private String name;
    private String course;
    private int age;
	
    public Student(Long id, String name, String course, int age) {
		super();
		this.id = id;
		this.name = name;
		this.course = course;
		this.age = age;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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
