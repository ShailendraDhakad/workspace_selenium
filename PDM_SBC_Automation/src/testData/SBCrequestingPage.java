package testData;

import utility.StringUtil;

public class SBCrequestingPage {

String MemberNumber;
String AccountNumber;
String HiosID;
String PlanDesignId; 
String MedicalID; 
String RXID;
String DentalID;
String VisionID;
String PlanStartDate;
String PlanEndDate;
String PlanEffectiveDate;

	
	public String getstringPlanDesignId() {
		return PlanDesignId;

	}

	public void setPlanDesignId(String PlanID) {

		if (PlanID.equals("Dynamic")) {
			PlanDesignId = StringUtil.generateRandomString(7);
		} else {
			PlanDesignId = PlanID;
		}
	}

	public String getPlanStartDate() {
		return PlanStartDate;
	}
	
	public void setPlanStartDate(String planstartdate) {
		PlanStartDate = planstartdate;

	}
}

