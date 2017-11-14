package testData;


import java.util.Date;
import appModules.SFDCLeads;
import utility.StringUtil;
import utility.DateUtil;

public class MyInformationData {

	String MyZip;
	String MyInvalidZip;
	String RequestedCoverEffDate;
	String DependentCoverage;
	String MyDateOfBirth;
	String MyInvalidDateOfBirth;
	String TobaccoUse;
	
	
	
	
	public String getMyDateOfBirth() {
		return MyDateOfBirth;
	}

	public void setMyDateOfBirth(String dateOfBirth, int age) {
		if (dateOfBirth.equals("Dynamic")) {
//			int year = LocalDate.now().getYear()- age;
			MyDateOfBirth = DateUtil.randomDate(age); 
		} 
		else 
			MyDateOfBirth = dateOfBirth;
	}
	
	public void setMyDateOfBirth(String dateOfBirth) {
		
		MyDateOfBirth = dateOfBirth;
	}
	
	public String getMyInvalidDateOfBirth() {
		return MyInvalidDateOfBirth;
	}

	
	public void setMyInvalidDateOfBirth(String dateOfBirth) {
//		if (dateOfBirth.equals("Dynamic")) {
//			int year = LocalDate.now().getYear()- age;
//			MyDateOfBirth = "" + (1 + (int) (Math.random() * 12) + "/" + (1 + (int) (Math.random() * 31) + "/" + Integer.toString(year)));
//		} 
//		else 
		MyInvalidDateOfBirth = dateOfBirth;
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
	
	public String getMyInvalidZip() {
		return MyInvalidZip;
	}

	public void setMyInvalidZip(String zip) {
//		if (zip.equals("Dynamic")){
//			MyZip=StringUtil.randomNumber(5);
//		}
//		else if ((zip.equalsIgnoreCase("MA")) || (zip.equalsIgnoreCase("ME")) || (zip.equalsIgnoreCase("NH")))
//			MyZip=SFDCLeads.getZipCode(zip);
//		else
			MyZip = MyInvalidZip;
	}
	
	public String getRequestedCoverageEffDate() {
		return RequestedCoverEffDate;
	}

	public void setRequestedCoverageEffDate(Date date, String Format) {
		 
			RequestedCoverEffDate = StringUtil.dateToString(date,Format);
	}
	
	public void setRequestedCoverageEffDate(String CoverageEffDate) {
		 
		RequestedCoverEffDate = CoverageEffDate;
	}
	
	
	
	public String getDependentCoverage() {
		return DependentCoverage;
	}

	public void setDependentCoverage(String dependentCoverage) {
		
			DependentCoverage=dependentCoverage;	
	}
	
	public String getTobaccoUse() {
		return TobaccoUse;
	}

	public void setTobaccoUse(String tobaccoUse) {
		
		TobaccoUse=tobaccoUse;	
	}

}
