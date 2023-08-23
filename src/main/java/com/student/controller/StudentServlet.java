package com.student.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.student.daofactory.StudentDaoFactory;
import com.student.model.Student;
import com.student.dao.IStudentDao;

@WebServlet("/")
public class StudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static IStudentDao studentDao = StudentDaoFactory.getStudentDao();

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();

		switch (action) {
		case "/new":
			showNewForm(request, response);
			break;
		case "/insert":
			insertUser(request, response);
			break;
		case "/update":
			updateUser(request, response);
			break;
		case "/edit":
			showEditForm(request, response);
			break;
		case "/delete":
			deleteUser(request, response);
			break;
		default:
			listUser(request, response);
			break;
		}
	}

	private void listUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Student> students = studentDao.selectAllStudent();
		request.setAttribute("students", students);
		RequestDispatcher dispatcher = request.getRequestDispatcher("student-list.jsp");
		dispatcher.forward(request, response);
	}

	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("student-form.jsp");
		dispatcher.forward(request, response);
	}

	private void insertUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Student student = new Student();
		student.setFname(request.getParameter("fname"));
		student.setEmail(request.getParameter("email"));
		student.setGender(request.getParameter("gender"));
		student.setDob(request.getParameter("dob"));
		System.out.println(student);
		System.out.println(request.getParameter("fname"));
		System.out.println(request.getParameter("email"));
		System.out.println(request.getParameter("gender"));
		System.out.println(request.getParameter("dob"));
		try {
			studentDao.insertStudnet(student);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		response.sendRedirect("list");
	}

	private void updateUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Student student = new Student();
		student.setId(id);
		student.setFname(request.getParameter("fname"));
		student.setEmail(request.getParameter("email"));
		student.setGender(request.getParameter("gender"));
		student.setDob(request.getParameter("dob"));
		try {
			studentDao.updateStudnet(student);
			response.sendRedirect("list");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		try {
			Student update = studentDao.selectUserById(id);

			// Date formating to Display in Jsp
			SimpleDateFormat originalFormat = new SimpleDateFormat("dd-MM-yyyy");
			SimpleDateFormat targetFormat = new SimpleDateFormat("yyyy-MM-dd");
			java.util.Date date = originalFormat.parse(update.getDob());
			String formattedDate = targetFormat.format(date);
			request.setAttribute("formattedDate", formattedDate);

			
			RequestDispatcher dispatcher = request.getRequestDispatcher("student-form.jsp");
			request.setAttribute("student", update);
			dispatcher.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void deleteUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		try {
			studentDao.deleteUser(id);
			response.sendRedirect("list");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
