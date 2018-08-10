package com.springboot.model.builder;

import com.springboot.model.Address;
import com.springboot.model.Major;
import com.springboot.model.ResponseMessage;
import com.springboot.model.Student;

public class StudentBuilder {

	private Student student = new Student();

	public StudentBuilder hasStudentId(int id) {
		student.setId(id);
		return this;
	}

	public StudentBuilder hasName(String name) {
		student.setName(name);
		return this;
	}

	public StudentBuilder hasAge(String age) {
		student.setAge(age);
		return this;
	}

	public StudentBuilder fromAddress(Address address) {
		student.setAddress(address);
		return this;
	}

	public StudentBuilder withMajor(Major major) {
		student.setMajor(major);
		return this;
	}

	public StudentBuilder returnResponseMessage(ResponseMessage response) {
		student.setResponse(response);
		return this;
	}

	public Student build() {
		return student;
	}
}