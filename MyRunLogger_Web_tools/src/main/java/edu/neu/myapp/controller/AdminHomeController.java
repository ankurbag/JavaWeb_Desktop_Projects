package edu.neu.myapp.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.neu.myapp.dao.RunLogDAO;
import edu.neu.myapp.dao.UserDAO;
import edu.neu.myapp.exceptions.MyAppException;
import edu.neu.myapp.fbsocial.FBConnection;
import edu.neu.myapp.fbsocial.FBGraph;
import edu.neu.myapp.pojo.RunLog;
import edu.neu.myapp.pojo.User;

/**
 * Handles requests for the application home page.
 */
@Controller
public class AdminHomeController {

	private static final Logger logger = LoggerFactory.getLogger(AdminHomeController.class);
	private static final String ADMIN_NAME = "admin";
	private static final String ADMIN_PSWD = "admin";
	private static final String ERR_MSG = "Username or password is invalid!!";
	private String code = "";

	
	public String initAdminHome(Model model) {
		RunLogDAO runLogDAO = new RunLogDAO();
		UserDAO userDAO = new UserDAO();
		// Populate Runs
		try {
			ArrayList<RunLog> userRuns = (ArrayList<RunLog>) runLogDAO.getUserRuns();
			ArrayList<RunLog> leadersRuns = (ArrayList<RunLog>) runLogDAO.getLeaderBoard();
			ArrayList<User> userList = (ArrayList<User>) userDAO.getUserList();
			model.addAttribute("leadersRuns", leadersRuns);
			model.addAttribute("userRuns", userRuns);
			model.addAttribute("userList", userList);
		} catch (MyAppException e) {
			model.addAttribute("nouserrun", "No User Run to Populate");
		}
		return "adminhome";
	}
	
	@RequestMapping(value = "/adminhome.htm", method = RequestMethod.GET)
	public String onSubmitAction(Locale locale, Model model, HttpServletRequest req) {
		logger.info("Welcome home! The client locale is {}.", locale);
		return initAdminHome(model);
	}
	
	@RequestMapping(value = "/adminhome.htm", method = RequestMethod.POST)
	public String validateAdmin(Locale locale, Model model, HttpServletRequest req) {
		logger.info("Welcome home! The client locale is {}.", locale);
		RunLogDAO runLogDAO = new RunLogDAO();
		// Populate Runs
		String userName = req.getParameter("adminname");
		String pswd = req.getParameter("adminpassword");
		if (userName.equals(ADMIN_NAME) && pswd.equals(ADMIN_PSWD)) {
			return initAdminHome(model);
		} else {
			model.addAttribute("errormsg", ERR_MSG);
			return "adminlogin";
		}
	}
	@RequestMapping(value = "/admin.htm", method = RequestMethod.GET)
	public String login(Locale locale, Model model,HttpServletRequest req,HttpServletResponse res) {
		return "adminlogin";
	}
	@RequestMapping(value = "/adminlogout.htm", method = RequestMethod.GET)
	public String logout(Locale locale, Model model,HttpServletRequest req,HttpServletResponse res) {
		logger.info("Welcome home! The client locale is {}.", locale);
		HttpSession session=req.getSession();
		session.invalidate();  
		return "adminlogin";
	}

}
