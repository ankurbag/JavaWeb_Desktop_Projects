package edu.neu.myapp.controller;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import edu.neu.myapp.pojo.*;
import edu.neu.myapp.dao.RunLogDAO;
import edu.neu.myapp.dao.UserDAO;
import edu.neu.myapp.exceptions.MyAppException;
import edu.neu.myapp.pojo.RunLog;


@Controller
@RequestMapping("/logrun.htm")
public class LogUserRunController {
//	@Autowired
//	@Qualifier("userValidator")
//	UserRunValidator validator;
//
//	@InitBinder
//	private void initBinder(WebDataBinder binder) {
//		binder.setValidator(validator);
//	}

	@RequestMapping(method = RequestMethod.POST)
	protected String doSubmitAction(@ModelAttribute("runlog") RunLog runLog,HttpServletRequest req,HttpServletResponse res,Model model) throws Exception {
		UserDAO userDao = new UserDAO();
		HttpSession userSession = req.getSession();
		User user= (User)userSession.getAttribute("user");
		
		//Insert into RunLog
		try {
            RunLogDAO runLogDAO = new RunLogDAO();

            //searhing session
            //runLog.setId(user.getPersonID());
            //insert a new runlog
            runLog.setUser(user);
    		runLogDAO.saveToDb(runLog);
    		
            user.addUserRuns(runLog);
    		userDao.updateUser(user);
    		
    		model.addAttribute("msg","Successfully Added");
            
        } catch (MyAppException e) {
            System.out.println(e.getMessage());
            model.addAttribute("msg","Error Occured!!");
        }
		model.addAttribute("name", user.getName());
		model.addAttribute("email", user.getEmail());
		model.addAttribute("profile", user.getProfilePictureURI());

		return "logrun";
	}

	@RequestMapping(method = RequestMethod.GET)
	public String initializeForm(@ModelAttribute("runlog") RunLog runLog,HttpServletRequest req,Model model) {
		HttpSession userSession = req.getSession();
		
		User user= (User)userSession.getAttribute("user");
		model.addAttribute("name", user.getName());
		model.addAttribute("email", user.getEmail());
		model.addAttribute("profile", user.getProfilePictureURI());
		
		return "logrun";
	}
}