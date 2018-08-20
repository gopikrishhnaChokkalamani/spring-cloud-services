package com.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.model.Student;
import com.springboot.service.StudentService;
import com.springboot.validator.StudentValidator;

@RestController
@RequestMapping("/api/student")
public class StudentController {

	@Autowired
	private StudentValidator studentValidator;

	@Autowired
	private StudentService service;

	@Value("${configuration.property.value}")
	private String from_config_server;

	@InitBinder("student")
	protected void initBinderForStudent(WebDataBinder binder) {
		binder.addValidators(studentValidator);
	}

	@PostMapping
	public ResponseEntity<?> insertStudentDetails(@Validated @RequestBody Student student) {
		return new ResponseEntity<>(service.insertStudentRecord(student), HttpStatus.CREATED);
	}

//	@DeleteMapping(path = "{id}")
//	public ResponseEntity<Boolean> deleteStudentDetails(@PathVariable(name = "id") String id) {
//		return new ResponseEntity<>(service.deleteStudentRecord(id), HttpStatus.OK);
//	}

	@GetMapping(path = "/config")
	public String fromConfigServer() {
		return from_config_server;
	}

	/*
	 * @GetMapping(path = "{name}") public ResponseEntity<List<Student>>
	 * getStudentDetails(@PathVariable String name) { final List<Student> result =
	 * studentRepository.findByName(name); if (result.size() == 0) throw new
	 * StudentNotFoundException(); return new ResponseEntity<>(result,
	 * HttpStatus.OK); }
	 */
}