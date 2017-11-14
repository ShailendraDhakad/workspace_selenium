package testData;

import appModules.SFDCLeads;
import utility.StringUtil;

public class AddressInformation {

	String StreetAddress;
	String MailingAddress;
	String MyZip;
	public String getStreetAddress() {
		return StreetAddress;
	}

	public void setStreetAddress(String strStreetAddress) {
		if (strStreetAddress.equals("Dynamic"))
			StreetAddress = StringUtil.randomNumber(3) + "," + StringUtil.generateRandomString(5) ;
		else
			StreetAddress=strStreetAddress;
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
	
	public String getMyZip() {
		return MyZip;
	}

	public void setMyZip(String zip) {
		if (zip.equals("Dynamic")){
			MyZip=StringUtil.randomNumber(5);
		}
		else if ((zip.equalsIgnoreCase("MA")) || (zip.equalsIgnoreCase("ME")) || (zip.equalsIgnoreCase("NH")))
			MyZip=SFDCLeads.getZipCode(zip);
		else
			MyZip = zip;
	}
	
}

