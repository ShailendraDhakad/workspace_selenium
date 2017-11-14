package testData;



import utility.DateUtil;
import utility.StringUtil;

public class BasicSubscriberInfoData {
	
	String Salutation;
	String FirstName;
	String MiddleName;
	String LastName;
	String Suffix;
	String StreetAddress;
	String EmailAddress;
	String EmailAddress2;
	String DOB;
	
	public String getSalutation() {
		return Salutation;
	}

	public void setSalutation(String strSalutation) {
		 
		Salutation = strSalutation;
	}
	public String getFirstName() {
		return FirstName;
	}

	public void setFirstName(String strFirstName) {
		if (strFirstName.equals("Dynamic"))
			FirstName = StringUtil.generateRandomString(5) ;
		else
			FirstName=strFirstName;
	}
	
	public String geMiddleName() {
		return MiddleName;
	}

	public void setMiddleName(String strMiddleName) {
		
		if (strMiddleName.equals("Dynamic"))
			MiddleName = StringUtil.generateRandomString(2) ;
		else
			MiddleName=strMiddleName;
	}
	
	public String getLastName() {
		return LastName;
	}

	public void setLastName(String strLastName) {
		 
		if (strLastName.equals("Dynamic"))
			LastName = StringUtil.generateRandomString(2) ;
		else
			LastName=strLastName;
	}
	
	public String getSuffix() {
		return Suffix;
	}

	public void setSuffix(String strSuffix) {
		 
		Suffix = strSuffix;
	}
	
//	public String getSalutation() {
//		return Salutation;
//	}
//
//	public void setSalutation(String strSalutation) {
//		 
//		Salutation = strSaluation;
//	}
	
	public String getStreetAddress() {
		return StreetAddress;
	}

	public void setStreetAddress(String strStreetAddress) {
		 
		if (strStreetAddress.equals("Dynamic"))
			StreetAddress = StringUtil.randomNumber(3) + "," + StringUtil.generateRandomString(5) ;
		else
			StreetAddress=strStreetAddress;
	}
	public String getEmailAddress() {
		return EmailAddress;
	}

	public void setEmailAddress( String strEmailAddress) {
		if (strEmailAddress.equals("Dynamic"))
			EmailAddress = StringUtil.generateRandomString(5).toLowerCase() + "@test.com";
		else
			EmailAddress=strEmailAddress;
	}
	
	public String getEmailAddress2() {
		return EmailAddress2;
	}

	public void setEmailAddress2( String strEmailAddress2) {
		 
		if (strEmailAddress2.equals("Dynamic"))
			EmailAddress2 = StringUtil.generateRandomString(5).toLowerCase() + "@test.com";
		else
			EmailAddress2=strEmailAddress2;
	}
	
	public String getDOB() {
		return DOB;
	}

	public void setDOB(String dateOfBirth, int age) {
		if (dateOfBirth.equals("Dynamic")) {
			
			DOB = DateUtil.randomDate(age);
		} 
		else 
			DOB = dateOfBirth;
	}
public void setDOB(String dateOfBirth) {
		
		DOB = dateOfBirth;
	}
}
