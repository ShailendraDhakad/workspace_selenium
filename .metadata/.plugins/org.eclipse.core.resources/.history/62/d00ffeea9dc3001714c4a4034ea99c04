package testData;

import java.text.DateFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import utility.DateUtil;
import utility.StringUtil;

public class MedicareInsuranceInformation {
  String Name;
  String Gender;
  String ClaimNumber;
  String PartAEffectiveDate;
  String PartBEffectiveDate; 
  
  public String getName() {
		return Name;
		
	}
	
	public void setName(String name) {
		
		if (name.equals("Dynamic")) {
			Name = StringUtil.generateRandomString(7);
		} else {
			Name = name;
		}
	}
	
	public String getGender() {
		return Gender;
	}

	public void setGender(String strGender) {		
		Gender=strGender;
	}
	
	public String getClaimNumber() {
		return ClaimNumber;
		
	}
	
	public void setClaimNumber(String claimNumber) {
		
		if (claimNumber.equals("Dynamic")) {
			ClaimNumber = "HP" + StringUtil.randomNumber(9);
		} else {
			ClaimNumber = claimNumber;
		}
	}
	
	public String getPartAEffectiveDate() {
		return PartAEffectiveDate;
	}

	public void setPartAEffectiveDate(String partAEffectiveDate) {		
		if (partAEffectiveDate.equals("Dynamic")) {
		int year = DateUtil.getCurrentYear();
		int month;
        GregorianCalendar date = new GregorianCalendar();      
        month = date.get(Calendar.MONTH);
        month = month+1;
 
        if(month>9)
        	PartAEffectiveDate = "" + month + "/01/" + Integer.toString(year);
        else
        	PartAEffectiveDate = "0" + month + "/01/" + Integer.toString(year);

		} else {

		PartAEffectiveDate=partAEffectiveDate;
	}
}

	public String getPartBEffectiveDate() {
		return PartBEffectiveDate;
	}

	public void setPartBEffectiveDate(String partBEffectiveDate) {		
		if (partBEffectiveDate.equals("Dynamic")) {
			int year =DateUtil.getCurrentYear();
			int month;
	        GregorianCalendar date = new GregorianCalendar();      
	        month = date.get(Calendar.MONTH);
	        month = month+1;
	 
	        if(month>9)
			PartBEffectiveDate = "" + month + "/01/" + Integer.toString(year);
	        else
	        	PartBEffectiveDate = "0" + month + "/01/" + Integer.toString(year);

			} else {

			PartBEffectiveDate=partBEffectiveDate;
		}	
}}