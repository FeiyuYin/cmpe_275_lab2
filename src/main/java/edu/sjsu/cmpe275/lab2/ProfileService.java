package edu.sjsu.cmpe275.lab2;

import java.util.HashMap;

/**
 * ProfileService is an interface which is implemented in ProfileServiceImpl class
 */

public interface ProfileService {
	
	

	public HashMap<String, Profile> getHashmap(); //stores objects of profile
	
	/**
	 *  Retrieve profile
	 *  @param userId the ID of user whose profile we want to 
	 * @reurn Profile of the user with given ID
	 */
	public Profile getProfile(String userId); 
	
	/**
	 * Store complete Profile 
	 * @param Profile object whose Profile is to be stored
	 * @retun void
	 */
	public void storeProfile(Profile pro);        // Stores complete profile @param Profile object
	
}
