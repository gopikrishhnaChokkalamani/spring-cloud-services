package com.springboot.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.sleuth.annotation.ContinueSpan;
import org.springframework.stereotype.Service;

import com.datastax.driver.core.utils.UUIDs;
import com.springboot.entity.AddressEntity;
import com.springboot.entity.StudentEntity;
import com.springboot.entity.StudentPrimaryKey;
import com.springboot.model.Major;
import com.springboot.model.Student;
import com.springboot.model.builder.Builders;
import com.springboot.repository.StudentRepository;

@Service
public class StudentService {

	@Autowired
	private StudentRepository repository;

	@ContinueSpan
	public Student insertStudentRecord(Student student) {
		StudentEntity entity = mapModelToEntity(student);
		Student response = mapEntityToModel(repository.save(entity));
		return response;
	}

	// public boolean deleteStudentRecord(String id) {
	// try {
	// repository.delete(id);
	// return true;
	// } catch (Exception e) {
	// return false;
	// }
	// }

	@ContinueSpan
	public StudentEntity mapModelToEntity(Student student) {
		StudentPrimaryKey primaryKey = new StudentPrimaryKey();
		primaryKey.setId(student.getId());
		primaryKey.setLastName(student.getLastName());
		StudentEntity entity = new StudentEntity();
		entity.setAge(student.getAge());
		entity.setMajor(student.getMajor().value());
		entity.setFirstName(student.getFirstName());
		entity.setTimeStamp(new Date());
		entity.setUniqueId(UUIDs.timeBased());
		entity.setPrimaryKey(primaryKey);
		AddressEntity addressEntity = new AddressEntity();
		addressEntity.setCity(student.getAddress().getCity());
		addressEntity.setDoorNo(student.getAddress().getDoorNo());
		addressEntity.setState(student.getAddress().getState());
		addressEntity.setStreet(student.getAddress().getStreet());
		addressEntity.setZipCode(student.getAddress().getZipCode());
		entity.setAddress(addressEntity);
		return entity;
	}

	@ContinueSpan
	public Student mapEntityToModel(StudentEntity entity) {
		Student student = Builders.student().hasStudentId(entity.getPrimaryKey().getId())
				.hasFirstName(entity.getFirstName()).hasLastName(entity.getPrimaryKey().getLastName())
				.hasAge(entity.getAge()).withMajor(Major.valueOf(entity.getMajor().toUpperCase()))
				.fromAddress(Builders.address().withDoorNo(entity.getAddress().getDoorNo())
						.fromStreet(entity.getAddress().getStreet()).fromCity(entity.getAddress().getCity())
						.fromState(entity.getAddress().getState()).withZipCode(entity.getAddress().getZipCode())
						.build())
				.returnResponseMessage(Builders.responseMessage().withResponseCode("success")
						.andResponseMsg("record added to database").onTransactionDate(entity.getTimeStamp().toString())
						.withTransactionId(entity.getUniqueId().toString()).build())
				.build();
		return student;
	}
}