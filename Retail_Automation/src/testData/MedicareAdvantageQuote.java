package testData;

import appModules.SFDCLeads;
import utility.StringUtil;

public class MedicareAdvantageQuote {

	String Year;
    String ZIP;
	
	public String getYear() {
		return Year;
	}

	public void setYear(String year) {
		Year=year;
	}
	
public String getZip() {
		return ZIP;
	}

public void setZip(String zip) {
	if (zip.equals("Dynamic")){
		ZIP=StringUtil.randomNumber(5);
	}
	else if ((zip.equalsIgnoreCase("MA")) || (zip.equalsIgnoreCase("ME")) || (zip.equalsIgnoreCase("NH")))
		ZIP=SFDCLeads.getZipCode(zip);
	else
		ZIP = zip;
}

	
}

