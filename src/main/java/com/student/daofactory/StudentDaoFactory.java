package com.student.daofactory;

import com.student.dao.IStudentDao;
import com.student.dao.StudentDaoImpl;

public class StudentDaoFactory {

	private StudentDaoFactory() {

	}

	// Singleton Pattern
	public static IStudentDao studentDao = null;

	public static IStudentDao getStudentDao() {
		if (studentDao == null)
			studentDao = new StudentDaoImpl();
		return studentDao;
	}
}
