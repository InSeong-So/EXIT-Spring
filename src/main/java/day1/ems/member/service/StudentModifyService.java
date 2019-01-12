package day1.ems.member.service;

import day1.ems.member.Student;
import day1.ems.member.dao.StudentDao;

public class StudentModifyService {

	private StudentDao studentDao;

	public StudentModifyService(StudentDao studentDao) {
		this.studentDao = studentDao;
	}

	public void modify(Student student) {
		if (verify(student.getsNum())) {
			studentDao.update(student);
		} else {
			System.out.println("Student information is not available.");
		}
	}

	public boolean verify(String sNum) {
		Student student = studentDao.select(sNum);
		return student != null ? true : false;
	}
}
