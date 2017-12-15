package com.app.actions;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;
import org.springframework.beans.factory.annotation.Autowired;

import com.app.service.StudentService;
import com.bo.Student;

@ResultPath("/pages/")
public class StudentAction extends BaseAction {

	private Student student;

	private List<Student> studentList;

	@Autowired
	private StudentService studentService;

	@Action(value = "addStudentForm", results = { @Result(name = "success", location = "studentForm.jsp") })
	public String addStudentForm() {

		// On retoune la page associée à success
		return SUCCESS;

	}

	@Action(value = "addStudent", results = {
			@Result(name = "success", type = "redirectAction", location = "listStudent") })
	public String addStudent() {

		studentService.addStudent(student);

		// On retoune la page associée à success
		return SUCCESS;

	}

	@Action(value = "listStudent", results = { @Result(name = "success", location = "listStudents.jsp") })
	public String listStudent() {

		studentList = studentService.getAllStudents();

		// On retoune la page associée à success
		return SUCCESS;

	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public List<Student> getStudentList() {
		return studentList;
	}

	public void setStudentList(List<Student> studentList) {
		this.studentList = studentList;
	}

}
