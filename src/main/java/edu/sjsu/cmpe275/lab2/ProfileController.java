package edu.sjsu.cmpe275.lab2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/")
public class ProfileController {

	  
//	  public void setProfileService(ProfileService proSer){
//		  
//		  this.proSer = proSer;
//	  }
	   
//	   @RequestMapping(method = RequestMethod.GET)
//	   public ModelAndView student() {
//	      return new ModelAndView("profile", "command", new Profile());
//	   }
//	   
//	   @RequestMapping(value ="/results",method = RequestMethod.POST)
//	   public String addProfile(@ModelAttribute("SpringWeb")Profile profile, 
//	   ModelMap model) {
//	      model.addAttribute("id", profile.getId());
//	      model.addAttribute("firstname", profile.getFirstname());
//	      model.addAttribute("lastname", profile.getLastname());
//	      model.addAttribute("email", profile.getEmail());
//	      model.addAttribute("address", profile.getAddress());
//	      model.addAttribute("organization", profile.getOrganization());
//	      model.addAttribute("aboutMyself", profile.getAboutMyself());
//	      return "results";
//	   }
//	   @RequestMapping(value = "/home", method = RequestMethod.GET)
//	   public String getHome(Model model) {
//		   model.addAttribute("element", "fyin");
//	      return "home";
//	   }
	
	
	  private ProfileServiceImpl proSer;
	  
	  @Autowired
	  public ProfileController(ProfileServiceImpl proSer){
		  
		  this.proSer = proSer;
		  System.out.println("in ProfileController init: " + proSer.getHashmap().size());
	  }
	  
	  @RequestMapping(value = "/profile", method = RequestMethod.GET)
	  public String getBlankProfile(Model model){
		  
		   model.addAttribute("profile", new Profile());
		   return "profile";		  
	  }
	  
	   @RequestMapping(value = "/profile", method = RequestMethod.POST)
	   public String postProfile(@ModelAttribute Profile profile,Model model) {
		   
		   System.out.println("before add: " + proSer.getHashmap().size());
		   proSer.storeProfile(profile);
		   System.out.println("after added: " + proSer.getHashmap().size());
		   return "profile";
	   }	  

//	   @RequestMapping(value = "/profile/{id}",  method = RequestMethod.GET)
//	   public String getOneProfile(@PathVariable("id") String id,  Model model) {
//
//		   Profile pro = proSer.getProfile(id);
//		   if(pro != null){
//			   
//			   model.addAttribute("profile", pro);
//			   return "profile";				   
//		   }
//		   else{
//			   
//			   model.addAttribute("id", id);
//			   return "404page";
//		   }
//	   }
	   
	   @RequestMapping(value = "/profile/{id}",  method = RequestMethod.GET)
	   public String getOneProfileBrief(@PathVariable("id") String id, @RequestParam(required = false) final String brief, Model model) {
		   
		   if(brief != null && brief.equals("true")){
			   
			   Profile pro = proSer.getProfile(id);
			   if(pro != null){
				   
				   model.addAttribute("profile", pro.toString());
				   return "profile_brief";				   
			   }
			   else{
				   
				   model.addAttribute("id", id);
				   return "404page";
			   }
		   }
		   
		   else{
			   
			   Profile pro = proSer.getProfile(id);
			   if(pro != null){
				   
				   model.addAttribute("profile", pro);
				   return "profile";				   
			   }
			   else{
				   
				   model.addAttribute("id", id);
				   return "404page";
			   }
		   }
	   }
	   

}
