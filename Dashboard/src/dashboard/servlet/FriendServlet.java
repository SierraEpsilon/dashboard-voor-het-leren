package dashboard.servlet;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dashboard.error.AlreadyRequestedException;
import dashboard.error.NotFriendException;
import dashboard.model.Student;
import dashboard.registry.StudentRegistry;

public class FriendServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3831682825249278636L;

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		HttpSession session = req.getSession();
		Student student = (Student)session.getAttribute("student");
		if(student != null)
			resp.sendRedirect("/jsp/friends/list.jsp");
		else
			resp.sendRedirect("/login");
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		HttpSession session = req.getSession();
		Student student = (Student)session.getAttribute("student");
		String action = req.getParameter("submit");
		if(action.contains("add_"))
			addFriend(action,student,req,resp);
		else if(action.contains("deny_"))
			denyFriend(action,student,req,resp);
		else if(action.contains("req_"))
			requestFriend(action,student,req,resp);
		else if(action.contains("rem_"))
			removeFriend(action,student,req,resp);
	}

	private void requestFriend(String action, Student student,
			HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String interestingPerson = action.replace("req_", "");
		try {
			StudentRegistry.sendFriendRequest(student, interestingPerson);
			req.getSession().setAttribute("student", student);
			resp.sendRedirect("/jsp/friends/list.jsp");
		} catch (AlreadyRequestedException e) {
			resp.sendRedirect("/jsp/error.jsp?msg=already requested");
		}
	}
	
	private void removeFriend(String action, Student student,
			HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String enemy = action.replace("rem_", "");
		try {
			StudentRegistry.unFriend(student, enemy);
			req.getSession().setAttribute("student", student);
			resp.sendRedirect("/jsp/friends/list.jsp");
		} catch (NotFriendException e) {
			resp.sendRedirect("/jsp/error.jsp?msg=not a friend");
		}
	}

	private void denyFriend(String action, Student student,
			HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String stranger = action.replace("deny_", "");
		student.removeRequest(stranger);
		req.getSession().setAttribute("student", student);
		resp.sendRedirect("/jsp/friends/list.jsp");
	}

	private void addFriend(String action, Student student,
			HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String friend = action.replace("add_", "");
		StudentRegistry.createFriends(student, friend);
		req.getSession().setAttribute("student", student);
		resp.sendRedirect("/jsp/friends/list.jsp");
	}	
}
