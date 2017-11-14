package testData;

import appModules.SFDCLeads;
import utility.StringUtil;

public class Enrollment {

	String Confirmation;
	String HomePhoneNumber;
	String Gender;
	
	public String getConfirmation() {
		return Confirmation;
	}

	public void setConfirmation(String confirmation) {
		Confirmation=confirmation;
	}
	
	public String getHomePhoneNumber() {
		return HomePhoneNumber;
	}

	public void setHomePhoneNumber(String homePhoneNumber) {
		if (homePhoneNumber.equals("Dynamic"))
			HomePhoneNumber = StringUtil.randomNumber(10) ;
		else
			HomePhoneNumber=homePhoneNumber;
	}

	public String getGender() {
		return Gender;
	}

	public void setGender(String gender) {
		Gender=gender;
	}
}

