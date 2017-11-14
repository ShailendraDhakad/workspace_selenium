package testData;

import appModules.SFDCLeads;
import utility.StringUtil;

public class ApplicationSubInfoData {
	
	String Gender;
	String StreetAddress;
	String MailingAddress;
	String HomePhone;
	String WorkTelephone;
	String MobilePhone;
	String PCPCity;
	String PCPFName;
	String PCPLName;
	String PCPID;
	String PCPZIP;
	
	public String getStreetAddress() {
		return StreetAddress;
	}

	public void setStreetAddress(String strStreetAddress) {
		if (strStreetAddress.equals("Dynamic"))
			StreetAddress = StringUtil.randomNumber(3) + "," + StringUtil.generateRandomString(5) ;
		else
			StreetAddress=strStreetAddress;
	}
	
	public String getGenders() {
		return Gender;
	}

	public void setGender(String strGender) {		
		Gender=strGender;
	}
	
	public String getMailingAddress() {
		return MailingAddress;
	}

	public void setMailingAddress(String strMailingAddress) {
		if (strMailingAddress.equals("Dynamic"))
			MailingAddress = StringUtil.randomNumber(3) + "," + StringUtil.generateRandomString(5) ;
		else
			MailingAddress=strMailingAddress;
	}
	
	public String getHomePhone() {
		return HomePhone;
	}

	public void setHomePhone(String strHomePhone) {
		
		if (strHomePhone.equals("Dynamic"))
			HomePhone = StringUtil.randomNumber(10) ;
		else
			HomePhone=strHomePhone;
	}
	
	public String getPCPID() {
		return PCPID;
	}

	public void setPCPID(String strPCPID) {
		PCPID=strPCPID;
	}
	
	public String getPCPZIP() {
		return PCPZIP;
	}

	public void setPCPZIP(String strPCPZIP) {
		PCPZIP=strPCPZIP;
		if (strPCPZIP.equals("Dynamic")){
			PCPZIP=StringUtil.randomNumber(5);
		}
		else if ((strPCPZIP.equalsIgnoreCase("MA")) || (strPCPZIP.equalsIgnoreCase("ME")) || (strPCPZIP.equalsIgnoreCase("NH")))
			PCPZIP=SFDCLeads.getZipCode(strPCPZIP);
		else
			PCPZIP = strPCPZIP;
	}
	
	public String getPCPCity() {
		return PCPCity;
	}

	public void setPCPCity(String strPCPCity) {
		 
		PCPCity = strPCPCity;
	}
	
	public String getPCPFName() {
		return PCPFName;
	}

	public void setPCPFName(String strPCPFName) {
		 
		if (strPCPFName.equals("Dynamic"))
			PCPFName = StringUtil.generateRandomString(6) ;
		else
			PCPFName=strPCPFName;
	}
	
	public String getPCPLName() {
		return PCPLName;
	}

	public void setPCPLName(String strPCPLName) {
		 
		if (strPCPLName.equals("Dynamic"))
			PCPLName = StringUtil.generateRandomString(6) ;
		else
			PCPLName=strPCPLName;
	}
}
