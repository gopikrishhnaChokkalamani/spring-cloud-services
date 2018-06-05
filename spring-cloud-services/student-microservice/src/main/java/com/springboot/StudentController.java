package com.springboot;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/student")
public class StudentController {

	@GetMapping
	public ResponseEntity<Student> getStudent() {
		Student student = new Student();
		student.setFirstName("SAM");
		student.setLastName("MICHAEL");
		student.setStudentId("ABCD1234");
		student.setSection("VII");
		return new ResponseEntity<>(student, HttpStatus.OK);
	}
}
