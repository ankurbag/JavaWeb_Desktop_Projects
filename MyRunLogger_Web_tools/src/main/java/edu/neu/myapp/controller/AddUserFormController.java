package edu.neu.myapp.controller;

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
import edu.neu.myapp.dao.UserDAO;

import edu.neu.myapp.exceptions.MyAppException;


@Controller
@RequestMapping("/adduser.htm")
public class AddUserFormController {
	@Autowired
	@Qualifier("userValidator")
	UserRunValidator validator;

	@InitBinder
	private void initBinder(WebDataBinder binder) {
		binder.setValidator(validator);
	}

	@RequestMapping(method = RequestMethod.POST)
	protected String doSubmitAction(@ModelAttribute("user") edu.neu.myapp.pojo.User user, BindingResult result) throws Exception {
		validator.validate(user, result);
		if (result.hasErrors()) {
			return "addUserForm";
		}

//		try {
//			System.out.print("test");
//			UserDAO userDao = new UserDAO();
//			System.out.print("test1");
//			
//			userDao.create(user.getName(), user.getPassword(),"", user.getFirstName(), user.getLastName());
//			
//			// DAO.close();
//		} catch (MyAppException e) {
//			System.out.println("Exception: " + e.getMessage());
//		}

		return "addedUser";
	}

	@RequestMapping(method = RequestMethod.GET)
	public String initializeForm(@ModelAttribute("user") edu.neu.myapp.pojo.User user, BindingResult result) {

		return "addUserForm";
	}
}