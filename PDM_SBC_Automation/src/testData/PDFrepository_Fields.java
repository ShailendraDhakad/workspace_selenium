package testData;


import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import utility.StringUtil;


public class PDFrepository_Fields {
	String filename;
	
	public String getfilename() {
		return filename;

	}

	public void setfilename(String Filename) {

		if (Filename.equals("Dynamic")) {
			filename = StringUtil.generateRandomString(7);
		} else {
			filename = Filename;
		}
	}

}
/*
		public void setAccountType(String accountType) {
			String AType[] = { "Checking", "Savings" };
			if (AType.equals("Dynamic")) {
				List<String> sSuffixList = Arrays.asList(AType);
				Collections.shuffle(sSuffixList);
				AccountType = sSuffixList.get(1);
			} else {
				AccountType = accountType;
			}

		}
		
		public String getROUTING() {
			return Routing;
			
		}
		
		public void setROUTING(String routing) {
		
		if (routing.equals("Dynamic")) {
			AccountType=StringUtil.generateRandomString(7);
	}
		else
		{
			Routing=routing;
		}
	}
	
		public String getBANK_ACCOUNT() {
			return AccountNumber;
			
		}
		
		public void setBANK_ACCOUNT(String accountNumber) {
		
		if (accountNumber.equals("Dynamic")) {
			AccountNumber=StringUtil.generateRandomString(7);
	}
		else
		{
			AccountNumber=accountNumber;
		}
	}
	
		public String getLeadType() {
			return LeadType;
		}

		public void setLeadType(String leadType) {
			String sLeadType[] = { "Individual", "Medicare" };
			if (leadType.equals("Dynamic")) {
				List<String> sLeadTypeList = Arrays.asList(sLeadType);
				Collections.shuffle(sLeadTypeList);
				LeadType = sLeadTypeList.get(0);
			} else {
				LeadType = leadType;
			}

		}
}
*/