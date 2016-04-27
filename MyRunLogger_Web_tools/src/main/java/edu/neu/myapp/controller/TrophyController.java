package edu.neu.myapp.controller;

import java.io.BufferedReader;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.neu.myapp.dao.TrophyDAO;
import edu.neu.myapp.exceptions.MyAppException;
import edu.neu.myapp.pojo.TrophyBean;

@Controller
public class TrophyController {
	// @Autowired
	// @Qualifier("userValidator")
	// UserRunValidator validator;
	//
	// @InitBinder
	// private void initBinder(WebDataBinder binder) {
	// binder.setValidator(validator);
	// }
	public String callDefaultPage(){
		return "adminlogin";
	}
	public String createJSONObject(List<TrophyBean> trophyDetails) {
		String message;
		JSONObject json = new JSONObject();
		try {
			// json.put("name", "student");
			JSONArray array = new JSONArray();

			for (TrophyBean trophy : trophyDetails) {
				JSONObject item = new JSONObject();
				item.put("trophyId", trophy.getTrophyId());
				item.put("trophyResultName", trophy.getTrophyName());
				item.put("trophyResultDesc", trophy.getTrophyDesc());
				item.put("trophyResultCriteria", trophy.getAchievement());
				array.put(item);
			}
			json.put("results", array);

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		message = json.toString();
		return message;
	}

	public String createJSONObject(TrophyBean trophy) {
		String message;
		JSONObject json = new JSONObject();
		try {
			// json.put("name", "student");
			JSONArray array = new JSONArray();
			JSONObject item = new JSONObject();
			item.put("trophyId", trophy.getTrophyId());
			item.put("trophyResultName", trophy.getTrophyName());
			item.put("trophyResultDesc", trophy.getTrophyDesc());
			item.put("trophyResultCriteria", trophy.getAchievement());
			array.put(item);

			json.put("results", array);
			if(array.length() > 0){
				json.put("success", "Success:Trophy "+ trophy.getTrophyName()+" added to the DB ");
			}

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		message = json.toString();
		return message;
	}

	@RequestMapping(value = "/addtrophy.htm", method = RequestMethod.POST)
	protected void doSubmitAction(HttpServletRequest req, HttpServletResponse res, Model model) throws Exception {
		TrophyBean trophyBean = new TrophyBean();
		TrophyDAO trophyDAO = new TrophyDAO();
		StringBuilder sb = new StringBuilder();
		BufferedReader br = req.getReader();
		String str = null;
		try{
		while ((str = br.readLine()) != null) {
			sb.append(str);
		}
		JSONObject jObj = new JSONObject(sb.toString());
		String trophyName = jObj.getString("trophyname");
		String trophyDesc = jObj.getString("trophyDesc");
		String achievement = jObj.getString("achievement");
		try {

			trophyBean.setTrophyName(trophyName);
			trophyBean.setTrophyDesc(trophyDesc);
			trophyBean.setAchievement(Integer.parseInt(achievement));
			trophyDAO.saveToDb(trophyBean);
			model.addAttribute("msg", "Trophy Successfully Added");

		} catch (MyAppException e) {
			System.out.println(e.getMessage());
			model.addAttribute("msg", "Error Occured!!");
		}
		res.setContentType("text/plain");
		res.setCharacterEncoding("UTF-8");

		// res.getWriter().write(
		// createJSONObject(trophyDAO.getTrophyDetails())); add complete List
		res.getWriter().write(createJSONObject(trophyBean));
		}catch(Exception ex){
			callDefaultPage();
		}
	}

	@RequestMapping(value = "/updatetrophy.htm", method = RequestMethod.GET)
	protected void doSubmitActionOnUpdate(HttpServletRequest req, HttpServletResponse res, Model model)
			throws Exception {
		TrophyBean trophyBean = new TrophyBean();
		TrophyDAO trophyDAO = new TrophyDAO();
		int updateCnt =0;
		System.out.println("trophyId : " +req.getParameter("trophyId"));
		try {
			trophyBean.setTrophyId(Integer.parseInt(req.getParameter("trophyId")));
			trophyBean.setTrophyName(req.getParameter("trophyNameTxt"));
			trophyBean.setTrophyDesc(req.getParameter("trophyDescTxt"));
			trophyBean.setAchievement(Integer.parseInt(req.getParameter("achievementTxt")));
			updateCnt = trophyDAO.updateTrophyDetails(trophyBean);
			if(updateCnt>0){
				res.getWriter().write( "Trophy "+trophyBean.getTrophyName() +" Successfully Updated");
			}else{
				res.getWriter().write( "Error");
			}
			
		} catch (MyAppException e) {
			System.out.println(e.getMessage());
			model.addAttribute("msg", "Error");
			res.getWriter().write( "Error");
		}catch(Exception ex){
			res.getWriter().write( "Error");
			callDefaultPage();
			
		}
	}
	
	@RequestMapping(value = "/deletetrophy.htm", method = RequestMethod.POST)
	protected void doSubmitActionOnDelete(HttpServletRequest req, HttpServletResponse res, Model model)
			throws Exception {
		TrophyBean trophyBean = new TrophyBean();
		TrophyDAO trophyDAO = new TrophyDAO();
		int updateCnt =0;
		System.out.println("trophyId : " +req.getParameter("trophyId"));
		try {
			
			trophyBean = trophyDAO.getTrophy(Long.parseLong(req.getParameter("trophyId")));
			if(trophyBean!=null){
				trophyDAO.delete(trophyBean);
				res.getWriter().write( "Trophy "+trophyBean.getTrophyName() +" Successfully Deleted");
			}else{
				res.getWriter().write( "Sorry :Trophy "+trophyBean.getTrophyName() +" could not be updated");
			}
			
		} catch (MyAppException e) {
			System.out.println(e.getMessage());
			model.addAttribute("msg", "Error Occured!!");
			res.getWriter().write( "Sorry :Error Occured!!");
		}catch (Exception ex){
			callDefaultPage();
		}
	}
	@RequestMapping(value = "/deletetrophy.htm", method = RequestMethod.GET)
	protected String doDefaultActionOnDelete(HttpServletRequest req, HttpServletResponse res, Model model)
			throws Exception {
		return callDefaultPage();
	}

	@RequestMapping(value = "/addtrophy.htm", method = RequestMethod.GET)
	public String initializeForm(@ModelAttribute("trophy") TrophyBean trophyBean, HttpServletRequest req,
			HttpServletResponse res, Model model) {
		TrophyDAO trophyDAO = new TrophyDAO();
		try {
			model.addAttribute("trophyList", trophyDAO.getTrophyDetails());
		} catch (MyAppException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (Exception ex){
			callDefaultPage();
		}
		return "admintrophy";

	}
}