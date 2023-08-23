package com.student.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import java.text.*;

import com.jdbcutil.JdbcUtil;
import com.student.model.Student;

//Data base operation to student info Table
public class StudentDaoImpl implements IStudentDao {
	// Query String
	private final static String INSERT_USER = "Insert into studentInfo (full_name,email,gender,birth_date) values (?,?,?,?)";
	private final static String SELECT_ALL_USER = "select * from studentInfo";
	private final static String SELECT_USER_BY_ID = "select * from studentInfo where sid=?";
	private final static String DELETE_USER = "delete from studentInfo where sid=?";
	private final static String UPDATE_USER = "update studentInfo set full_name=?,email=?,gender=?,birth_date=? where sid = ?";

	// Insert Student Info
	public boolean insertStudnet(Student student) throws SQLException {
		boolean rowCreated;
		try (Connection connection = JdbcUtil.getJdbcConnection();
				PreparedStatement pstmt = connection.prepareStatement(INSERT_USER)) {
			pstmt.setString(1, student.getFname());
			pstmt.setString(2, student.getEmail());
			pstmt.setString(3, student.getGender());
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			java.util.Date udate = null;
			try {
				udate = sdf.parse(student.getDob());

			} catch (ParseException e) {
				e.printStackTrace();
			}
			java.sql.Date date = new java.sql.Date(udate.getTime());
			System.out.println(date);
			pstmt.setDate(4, date);
			rowCreated = pstmt.executeUpdate() > 0;
		}
		return rowCreated;
	}

	// update Student Info
	public boolean updateStudnet(Student student) throws SQLException {
		boolean rowUpdated;
		try (Connection connection = JdbcUtil.getJdbcConnection();
				PreparedStatement pstmt = connection.prepareStatement(UPDATE_USER)) {
			pstmt.setString(1, student.getFname());
			pstmt.setString(2, student.getEmail());
			pstmt.setString(3, student.getGender());
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			java.util.Date udate = null;
			try {
				udate = sdf.parse(student.getDob());

			} catch (ParseException e) {
				e.printStackTrace();
			}
			long value = udate.getTime();
			java.sql.Date date = new java.sql.Date(value);
			pstmt.setDate(4, date);
			pstmt.setInt(5, student.getId());
			rowUpdated = pstmt.executeUpdate() > 0;
		}
		return rowUpdated;
	}

	// select user by ID
	public Student selectUserById(int id) throws SQLException {
		Student student = null;
		try (Connection connection = JdbcUtil.getJdbcConnection();
				PreparedStatement pstmt = connection.prepareStatement(SELECT_USER_BY_ID)) {
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				String name = rs.getString("full_name");
				String email = rs.getString("email");
				String gender = rs.getString("gender");
				java.sql.Date date = rs.getDate("birth_Date");
				SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
				String dob = sdf.format(date);
				student = new Student(id, name, email, gender, dob);
			}
		}
		return student;
	}

	// select all users
	public List<Student> selectAllStudent() {
		List<Student> students = new ArrayList<>();
		try (Connection connection = JdbcUtil.getJdbcConnection();
				PreparedStatement pstmt = connection.prepareStatement(SELECT_ALL_USER)) {
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				int sid = rs.getInt("sid");
				String name = rs.getString("full_name");
				String email = rs.getString("email");
				String gender = rs.getString("gender");
				java.sql.Date date = rs.getDate("birth_Date");
				SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
				String dob = sdf.format(date);
				students.add(new Student(sid, name, email, gender, dob));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return students;
	}

	// delete user
	public boolean deleteUser(int id) throws SQLException {
		boolean rowDeleted;
		try (Connection connection = JdbcUtil.getJdbcConnection();
				PreparedStatement pstmt = connection.prepareStatement(DELETE_USER)) {
			pstmt.setInt(1, id);
			rowDeleted = pstmt.executeUpdate() > 0;
		}
		return rowDeleted;
	}

}
