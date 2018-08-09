package com.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.sleuth.Span;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.cloud.sleuth.annotation.NewSpan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/student")
public class StudentController {

	@Autowired
	private Tracer tracer;

	@GetMapping
	@NewSpan("student-controller")
	public ResponseEntity<Student> getStudent() throws InterruptedException {
		Student student = new Student();
		student.setFirstName("SAM");
		student.setLastName("MICHAEL");
		student.setStudentId("ABCD1234");
		student.setSection("VII");

		Span newSpan = tracer.getCurrentSpan();
		newSpan.logEvent("going into for loop");
		newSpan.getBegin();
		int i = 0;
		while (i < 100) {
			i++;
		}
		Thread.sleep(1000);
		tracer.close(newSpan);
		return new ResponseEntity<>(student, HttpStatus.OK);
	}
}
