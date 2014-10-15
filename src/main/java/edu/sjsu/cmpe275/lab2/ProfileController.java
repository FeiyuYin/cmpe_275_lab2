package edu.sjsu.cmpe275.lab2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * ProfileController class is the controller of our MVP desigh 
 */


@Controller
@RequestMapping("/")
public class ProfileController {
	
	  private ProfileServiceImpl proSer;
	  
	  @Autowired
	  public ProfileController(ProfileServiceImpl proSer){
		  
		  this.proSer = proSer;
		  System.out.println("in ProfileController init: " + proSer.getHashmap().size());
	  }
	  
	  /**
	   * getCreateProfilePage creates a new profile
	   * /profile mapping 
	   * @param model, add attributes of profile to this model
	   * @return create profile page
	   */
	  
	  @RequestMapping(value = "/profile", method = RequestMethod.GET)
	  public String getCreateProfilePage(Model model){
		  
		   model.addAttribute("profile", new Profile());

		   return "createprofile";		  
	  }
	  
	  /**
	   * createProfile stores new profile in the Hashmap
	   * @param profile with new userId
	   * @returns to a page with url /profile/id
	   */
	  
	   @RequestMapping(value = "/profile", method = RequestMethod.POST)
	   public String createProfile(@ModelAttribute Profile profile,Model model) {
		   
		   System.out.println("before add: " + proSer.getHashmap().size());
		   proSer.storeProfile(profile);
		   System.out.println("after added: " + proSer.getHashmap().size());

		   return "redirect:/profile/"+profile.getId();
	   }	
	   
	   
	   /**
	    * updateProfile updates the already existing profile
	    * @param profile, id gives the complete profile with given ID
	    * @return to a page with updated profile and url /profile/id
	    */
	   
	   @RequestMapping(value = "/profile/{id}", method = RequestMethod.POST)
	   public String updateProfile(@ModelAttribute Profile profile, @PathVariable("id") String id, Model model) {
		   
		   System.out.println("Update "+ id + " is triggered: before post " + proSer.getHashmap().size());
//		   System.out.println("Profile being updated: " + profile.toString());
		   proSer.storeProfile(profile);
		   System.out.println("Update "+ id + " is triggered: after post " + proSer.getHashmap().size());
		   model.addAttribute("profile", profile);

		   return "redirect:/profile/" + id;
	   }
	   
	   /**
	    * getOneProfile retrieves profile with given usedId
	    * If profile exists with that userId then returns the profile page
	    * If profile does not exists with param id then returns 404 error message page
	    */


	   
	   @RequestMapping(value = "/profile/{id}",  method = RequestMethod.GET)
	   public String getOneProfile(@PathVariable("id") String id, @RequestParam(required = false) final String brief, Model model) {
		   
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
	   
	   
	   
	   /**
	    * deleteOneprofile deletes profile with the given id
	    * @param id whose profile is to be deleted
	    * @return if profile exists remove it from the hashmap
	    * @return if profile doesnot exists return it to the 404 error message page
	    */
	   
	   @RequestMapping(value = "/profile/{id}",  method = RequestMethod.DELETE)
	   public String deleteOneProfile(@PathVariable("id") String id, Model model){
		   
		   System.out.println("DELETE /profile/"+ id + " is triggered: before delete " + proSer.getHashmap().size());
		   if(proSer.getProfile(id) == null){
			   
			   System.out.println("404");
			   model.addAttribute("id", id);
			   return  "404page";
		   }
		   else{
			   
			   proSer.getHashmap().remove(id);
			   System.out.println("DELETE /profile/"+ id + " is triggered: after delete " + proSer.getHashmap().size());
			   model.addAttribute("profile", new Profile());

			   return "redirect:/profile";
		   }
		   
	   }
	   
}
