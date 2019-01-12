package day1.ems.member.service;

import day1.ems.member.Student;
import day1.ems.member.dao.StudentDao;

public class StudentDeleteService {

	private StudentDao studentDao;

	public StudentDeleteService(StudentDao studentDao) {
		this.studentDao = studentDao;
	}

	public void delete(Student student) {
		if (verify(student.getsNum())) {
			studentDao.delete(student.getsNum());
		} else {
			System.out.println("Student information is not available.");
		}
	}

	public boolean verify(String sNum) {
		Student student = studentDao.select(sNum);
		return student != null ? true : false;
	}
}
