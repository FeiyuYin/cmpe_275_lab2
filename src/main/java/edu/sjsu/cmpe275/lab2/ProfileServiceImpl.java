package edu.sjsu.cmpe275.lab2;

import java.util.HashMap;

public class ProfileServiceImpl implements ProfileService {

	private HashMap<String, Profile> profile; 
	
	public void setProfile(HashMap<String, Profile> profile){
		
		this.profile = profile;
	}
	

	@Override
	public Profile getProfile(String userId) {
		// TODO Auto-generated method stub
		
		return profile.get(userId);
	}

	@Override
	public void storeProfile(Profile pro) {
		// TODO Auto-generated method stub

		profile.put(pro.getId(), pro);
	}

	@Override
	public HashMap<String, Profile> getHashmap() {
		// TODO Auto-generated method stub
		return profile;
	}

}
