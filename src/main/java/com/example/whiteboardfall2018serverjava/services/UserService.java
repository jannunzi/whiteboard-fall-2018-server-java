package com.example.whiteboardfall2018serverjava.services;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.whiteboardfall2018serverjava.models.Course;
import com.example.whiteboardfall2018serverjava.models.User;

@RestController
@CrossOrigin(origins="*")
public class UserService {
	
	static List<User> users = new ArrayList<User>();
	static String[] usernames = {"alice", "bob", "charlie"};
	static String[] courseTitles = {"cs5200", "cs5610", "cs5500"};
	{
		List<Course> courses = new ArrayList<Course>();
		for(String courseTitle: courseTitles) {
			Course course = new Course(courseTitle);
			courses.add(course);
		}
		for(String username: usernames) {
			User user = new User(username);
			if(username.equals("alice")) {
				user.setCourses(courses);
			}
			users.add(user);
		}
	}
	
	@GetMapping("/api/user")
	public List<User> findAllUsers() {
		return users;
	}

	public User findUserById(int userId) {
		for(User user: users) {
			if(user.getId() == userId)
				return user;
		}
		return null;
	}

	@PostMapping("/api/user")
	public List<User> createUser(@RequestBody User user) {
		users.add(user);
		return users;
	}
	
	@PostMapping("/api/register")
	public User register(
			@RequestBody User user,
			HttpSession session) {
		session.setAttribute("currentUser", user);
		users.add(user);
		return user;
	}
	
	@GetMapping("/api/profile")
	public User profile(HttpSession session) {
		User currentUser = (User)session.getAttribute("currentUser");	
		return currentUser;
	}
	
	@PostMapping("/api/logout")
	public void logout(HttpSession session) {
		session.invalidate();
	}
	
	@PostMapping("/api/login")
	public User login(
			@RequestBody User credentials,
			HttpSession session) {
	 for (User user : users) {
	  if( user.getUsername().equals(credentials.getUsername())
	   && user.getPassword().equals(credentials.getPassword())) {
	    session.setAttribute("currentUser", user);
	    return user;
	  }
	 }
	 return null;
	}




}
