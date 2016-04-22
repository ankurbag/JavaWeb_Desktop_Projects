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
	private String code = "";
	
	@RequestMapping(value = "/admin.htm", method = RequestMethod.GET)
	public String initAdminHome(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		RunLogDAO runLogDAO = new RunLogDAO();
		// Populate Runs
		try {
			ArrayList<RunLog> userRuns = (ArrayList<RunLog>) runLogDAO.getUserRuns();
			model.addAttribute("userRuns", userRuns);
		} catch (MyAppException e) {
			model.addAttribute("nouserrun", "No User Run to Populate");
		}
		return "adminhome";
	}

}
