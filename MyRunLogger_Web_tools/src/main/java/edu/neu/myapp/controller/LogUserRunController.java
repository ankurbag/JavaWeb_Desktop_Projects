package edu.neu.myapp.controller;

import java.io.BufferedReader;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.neu.myapp.dao.RunLogDAO;
import edu.neu.myapp.dao.TrophyDAO;
import edu.neu.myapp.dao.UserDAO;
import edu.neu.myapp.exceptions.MyAppException;
import edu.neu.myapp.pojo.RunLog;
import edu.neu.myapp.pojo.TrophyBean;
import edu.neu.myapp.pojo.User;


@Controller

public class LogUserRunController {
//	@Autowired
//	@Qualifier("userValidator")
//	UserRunValidator validator;
//
//	@InitBinder
//	private void initBinder(WebDataBinder binder) {
//		binder.setValidator(validator);
//	}

	public String createJSONObject(RunLog runLog) {
		String message;
		JSONObject json = new JSONObject();
		try {
			// json.put("name", "student");
			JSONArray array = new JSONArray();
			JSONObject item = new JSONObject();
			item.put("runLogId", runLog.getId());
			item.put("runResultDate", runLog.getRunDate());
			item.put("runResultTime", runLog.getRunTime());
			item.put("runResultDistance", runLog.getDistance());
			item.put("runResultFeedback", runLog.getFeedback());
			item.put("runResultPrivacy", runLog.getPrivacy());
			array.put(item);

			json.put("results", array);
			if(array.length() > 0){
				json.put("success", "Success:Run for "+ runLog.getDistance()+" added to the DB ");
			}

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		message = json.toString();
		return message;
	}
	
	@RequestMapping(value = "/updaterun.htm", method = RequestMethod.GET)
	protected void doSubmitActionOnUpdate(HttpServletRequest req, HttpServletResponse res, Model model)
			throws Exception {
		HttpSession userSession = req.getSession();
		User user= (User)userSession.getAttribute("user");
		RunLog runLog = new RunLog();
		UserDAO userDao = new UserDAO();
		RunLogDAO runLogDAO = new RunLogDAO();
		
		TrophyDAO trophyDAO = new TrophyDAO();
		int updateCnt =0;
		System.out.println("runLogId : " +req.getParameter("runLogId"));
		try {
			runLog.setId(Long.parseLong(req.getParameter("runLogId")));
			runLog.setRunDate(req.getParameter("runDateTxt"));
            runLog.setDistance(Long.parseLong(req.getParameter("distanceTxt")));
            runLog.setRunTime(Long.parseLong(req.getParameter("runTimeTxt")));
            runLog.setFeedback(req.getParameter("feedbackTxt"));
            updateCnt =runLogDAO.updateRunDetails(runLog) ;
			if(updateCnt>0){
				res.getWriter().write( "Run for "+runLog.getRunDate() +" Successfully Updated");
			}else{
				res.getWriter().write( "Sorry :Run for "+runLog.getRunDate() +" could not be updated");
			}
			
		} catch (MyAppException e) {
			System.out.println(e.getMessage());
			model.addAttribute("msg", "Error Occured!!");
			res.getWriter().write( "Sorry :Error Occured!!");
		}
	}
	
	@RequestMapping(value = "/deleterun.htm", method = RequestMethod.POST)
	protected void doSubmitActionOnDelete(HttpServletRequest req, HttpServletResponse res, Model model)
			throws Exception {
		HttpSession userSession = req.getSession();
		User user= (User)userSession.getAttribute("user");
		RunLog runLog=null;
		UserDAO userDao = new UserDAO();
		RunLogDAO runLogDAO = new RunLogDAO();
		
		TrophyBean trophyBean = new TrophyBean();
		TrophyDAO trophyDAO = new TrophyDAO();
		int updateCnt =0;
		System.out.println("runLogId : " +req.getParameter("runLogId"));
		try {
			runLog = runLogDAO.getRunLog(Long.parseLong(req.getParameter("runLogId")));
			if(runLog!=null){
				updateCnt = runLogDAO.delete(runLog);
				if(updateCnt>0){
				res.getWriter().write( "Run for "+runLog.getRunDate() +" Successfully Deleted");
				}else{
					res.getWriter().write( "Sorry :Run for "+runLog.getRunDate()  +" could not be deleted");
				}
				
			}else{
				res.getWriter().write( "Sorry :Run for "+runLog.getRunDate()  +" could not be found");
			}
			
		} catch (MyAppException e) {
			System.out.println(e.getMessage());
			model.addAttribute("msg", "Error Occured!!");
			res.getWriter().write( "Sorry :Error Occured!!");
		}
	}
	
	@RequestMapping(value="/logrun.htm",method = RequestMethod.POST)
	protected String doSubmitAction(HttpServletRequest req,HttpServletResponse res,Model model) throws Exception {
		UserDAO userDao = new UserDAO();
		HttpSession userSession = req.getSession();
		User user= (User)userSession.getAttribute("user");
		RunLog runLog = new RunLog();
		////JSON PARSING
		StringBuilder sb = new StringBuilder();
		BufferedReader br = req.getReader();
		String str = null;
		while ((str = br.readLine()) != null) {
			sb.append(str);
		}
		JSONObject jObj = new JSONObject(sb.toString());
		String runDate = jObj.getString("runDate");
		String distance = jObj.getString("distance");
		String runTime = jObj.getString("runTime");
		String feedback = jObj.getString("feedback");
		String privacy = jObj.getString("privacy");
		
		//Insert into RunLog
		try {
            RunLogDAO runLogDAO = new RunLogDAO();
            runLog.setRunDate(runDate.substring(1, runDate.indexOf('T')));
            runLog.setDistance(Long.parseLong(distance));
            runLog.setRunTime(Long.parseLong(runTime));
            runLog.setFeedback(feedback);
            runLog.setPrivacy(privacy);
            //searching session
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

		res.setContentType("text/plain");
		res.setCharacterEncoding("UTF-8");

		// res.getWriter().write(
		// createJSONObject(trophyDAO.getTrophyDetails())); add complete List
		res.getWriter().write(createJSONObject(runLog));
		
		
		return null;
	}

	@RequestMapping(value="/logrun.htm",method = RequestMethod.GET)
	public String initializeForm(@ModelAttribute("runlog") RunLog runLog,HttpServletRequest req,Model model) {
		HttpSession userSession = req.getSession();
		RunLogDAO runLogDAO = new RunLogDAO();
		User user= (User)userSession.getAttribute("user");
		model.addAttribute("name", user.getName());
		model.addAttribute("email", user.getEmail());
		model.addAttribute("profile", user.getProfilePictureURI());
		try {
			ArrayList<RunLog> userRuns = (ArrayList<RunLog>) runLogDAO.getUserRuns();
			model.addAttribute("userRuns", userRuns);
		} catch (MyAppException e) {
			model.addAttribute("nouserrun", "No User Run to Populate");
		}
		return "logrun";
	}
}