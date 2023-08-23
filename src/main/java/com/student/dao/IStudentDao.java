package com.student.dao;

import java.sql.SQLException;
import java.util.List;
import com.student.model.Student;

public interface IStudentDao {
	public boolean insertStudnet(Student student) throws SQLException;

	public boolean updateStudnet(Student student) throws SQLException;

	public Student selectUserById(int id) throws SQLException;

	public List<Student> selectAllStudent();

	public boolean deleteUser(int id) throws SQLException;
}
