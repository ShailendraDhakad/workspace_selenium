
package pageObjects;

public class PageMedicareOpportunities {
	
	
	public class PayPlanPremium{
		public static final String CHECK_GETBILL  ="xpath=//*[@id='GetBill']";
		public static final String CHECK_EFT  ="xpath=//*[@id='EFT']";
		public static final String CHECK_AUTO  ="xpath=//*[@id='AutoDeduction']";
		public static final String BUTTON_NEXT  ="xpath=//*[@id='a0s21000000IrSgAAK-16']/div[2]/div[3]/button | //*[@id='a0s21000000KYPoAAO-16']/div[2]/div[3]/button";
		
	}
	
	public class AttestEligibilty{
		public static final String CHECK_NEWTOMED  ="xpath=//*[@id='NewtoMed']";
		public static final String CHECK_MOVED_OUT_AREA  ="xpath=//*[@id='MovedOutArea']";
		public static final String TEXT_MOVEDOUT_AREADATE  ="xpath=//*[@id='MovedOutAreaDate']";
		public static final String TEXT_RELEASE_FROM_INCAR_DATE  ="xpath=//*[@id='RelFromIncDate']";
		public static final String TEXT_RETURN_TO_US_DATE  ="xpath=//*[@id='RetToUSDate']";
		public static final String TEXT_LAWFUL_PRESENCE_DATE  ="xpath=//*[@id='LawfulPresenceDate']";
		public static final String TEXT_NO_LONGER_DRUGS_DATE  ="xpath=//*[@id='NoLongerElgDrugsDate']";
		public static final String TEXT_MOVED_OUTOF_LONGTERM_DATE  ="xpath=//*[@id='MoveOutofLongTermDate']";
		public static final String TEXT_LEFT_PACE_DATE  ="xpath=//*[@id='LeftPaceDate']";
		public static final String TEXT_LOST_DRUG_COVRG_DATE  ="xpath=//*[@id='LostDrugCovDate']";
		public static final String TEXT_LEAVING_EMPLOYEE_DATE  ="xpath=//*[@id='LeavingUnionEmpDate']";
		public static final String TEXT_SNP_DATE  ="xpath=//*[@id='SNPDate']";		
		public static final String CHECK_RELEASE_FROM_INCAR  ="xpath=//*[@id='RelFromInc']";
		public static final String CHECK_RETURN_TO_US  ="xpath=//*[@id='RetToUS']";
		public static final String CHECK_LAWFUL_PRESENCE  ="xpath=//*[@id='LawfulPresence']";
		public static final String CHECK_MEDICARE_PREMIUM  ="xpath=//*[@id='MedPrems']";
		public static final String CHECK_MEDICARE_PRESCRIPTION  ="xpath=//*[@id='MedPresc']";
		public static final String CHECK_NO_LONGER_DRUGS  ="xpath=//*[@id='NoLongerElgDrugs']";
		public static final String CHECK_MOVED_OUTOF_LONGTERM  ="xpath=//*[@id='MoveOutofLongTerm']";
		public static final String CHECK_LEFT_PACE  ="xpath=//*[@id='LeftPace']";
		public static final String CHECK_LOST_DRUG_COVRG  ="xpath=//*[@id='LostDrugCov']";
		public static final String CHECK_LEAVING_EMPLOYEE  ="xpath=//*[@id='LeavingUnionEmp']";
		public static final String CHECK_PHAR_ASSIST  ="xpath=//*[@id='PharmAsst']";
		public static final String CHECK_PLAN_END_CONTRACT  ="xpath=//*[@id='PlanEndContract']";
		public static final String CHECK_SNP  ="xpath=//*[@id='SNP']";
		public static final String BUTTON_NEXT  ="xpath=//*[@id='a0s21000000IrSgAAK-18']/div[2]/div[3]/button";
		
		
		
	}
	
	public class ApplicationAssistance{
		public static final String CHECK_ENROLLEE  ="id=Enrollee";
		public static final String CHECK_STAFF_MEMBER  ="id=StaffMember";
		public static final String LINK_STAFF_MEMBER  ="id=AgentLookupButton";
		public static final String TEXT_AGENTNAME  ="id=pag:frm:details:AgentNameSection:agentNameId:agentNameForSearch";
		public static final String BUTTON_SEARCH  ="xpath = .//*[@id='pag:frm:details:j_id2']/input";
		public static final String TABLE_STAFF  ="id=pag:frm:details:agentDetailsTable";	//*[@id="pag:frm:details:agentDetailsTable:tb"]
		public static final String LINK_SELECT  ="xpath = .//*[@id='pag:frm:details:agentDetailsTable:INDEX:j_id32']/a";
		public static final String CHECK_AGENT_BROKER  ="id=AgentBroker";
		public static final String TEXT_AGENCY_OF_AGENT  ="id=AgencyAgent";
		public static final String TEXT_AGENT_FIRSTNAME  ="id=AgentFirstName";
		public static final String TEXT_AGENT_LASTNAME  ="id=AgentLastName";
		public static final String TEXT_AGENT_ID  ="id=AgentId";
		public static final String TEXT_AGENT_NPN  ="id=NPN";
		public static final String CHECK_AUTHORISE_REP  ="id=AuthRep";
		public static final String TEXT_REP_NAME  ="id=AuthName";
		public static final String TEXT_REP_PHONE  ="id=AuthPhoneNo";
		public static final String TEXT_REP_ADDRESS  ="id=AuthAddress";		
		public static final String TEXT_REP_RELATION  ="id=ReltoEnrollee";
		public static final String RADIO_APPLICATION_ENTRY  ="id=AppEntry";
		public static final String BUTTON_NEXT  ="//*[@id='a0s21000000IrSgAAK-20']/div[2]/div[3]/button";
		
				
	}
	
	public class ImportantInformation{
		public static final String CHECK_DISCLOSURE  ="id=Disclosure";
		public static final String BUTTON_NEXT  ="//*[@id='a0s21000000IrSgAAK-22']/div[2]/div[3]/button";
	}
	
	public class MedicareImoprotantQuestion{
    	
    	public static final String PAGE_HEADER = "xpath = .//*[@id='MedImpQues']/div/span/span";
    	public static final String RADIO_ESRD_OPTION = "xpath = .//*[@id='ESRD']";
    	public static final String ESRD_OPTION_NO = "xpath = .//*[@id='ESRD'][@value='No']";
    	public static final String RADIO_PRSCRB_DRUG = "xpath = .//*[@id='PrscDrug']";
    	public static final String PRSCRB_DRUG_NO = "xpath = .//*[@id='PrscDrug'][@value='No']";
    	public static final String OTHER_COVERAGE_NAME = "xpath = .//*[@id='NameofOtherCov']";
    	public static final String COVERAGE_ID = "xpath = .//*[@id='IDNo']";
     	public static final String GROUP_NUMBER = "xpath = .//*[@id='GroupNo']";
    	
    	
    	public static final String RADIO_LONGTERM = "xpath = .//*[@id='LongTermRes']";
    	public static final String LONGTERM_NO = "xpath = .//*[@id='LongTermRes'][@Value='No']";
    	public static final String INSTITUTION_NAME = "xpath = .//*[@id='NameofInst']";
    	public static final String PHONE_NUMBER = "xpath = .//*[@id='PhoneNo']";
    	public static final String STREET_ADDRESS = "xpath = .//*[@id='AddofInst']";
    	
    	
    	public static final String RADIO_STATE_MEDID = "xpath = .//input[@id='StateMedId']";
    	public static final String STATE_MEDID_NO = "xpath = .//*[@id='StateMedId'][@Value='No']";
    	public static final String MEDICAL_NUMBER = "xpath = .//*[@id='StateMedIdNumber']";
    	    	
    	public static final String RADIO_SPOUSE_WORK= "xpath = .//*[@id='SpouseWork']";
    	public static final String PCP_ID_NUMBER= "xpath = .//*[@id='PID']";
    	public static final String F_NAME= "xpath = .//*[@id='PFirstName']";
    	public static final String MI = "xpath = .//*[@id='PMI']";
    	public static final String L_NAME = "xpath = .//*[@id='PLastName']";

    	public static final String SPOUSE_WORK_NO = "xpath = .//*[@id='SpouseWork'][@Value='No']";
    	public static final String RADIO_EXISTING_PCP = "xpath = .//*[@id='PCurrentPatient']";
    	
    	public static final String PCP_LOOKUP_BUTTON = "xpath = .//*[@id='PCPButton']"; 
        	public static final String PCP_SEARCH_BUTTON = "xpath = .//*[@value='Search']";
        	public static final String PCP_TABLE_INFO = "xpath = .//*[@id='pag:frm:details:providertable']";
        	public static final String ACCPETING_NEW_PATIENT = "xpath = .//*[@name='pag:frm:details:pbsection:j_id44:j_id46']";
        	public static final String ACCPETING_NEW_PATIENT_YES = "xpath = .//*[@id='pag:frm:details:pbsection']/div/table/tbody/tr[4]/td[2]/select/option[2]";
        	public static final String LINK_SELECT_PCP = "xpath = .//*[@id='pag:frm:details:providertable:0:j_id82']/a";
    	public static final String NEXT_BUTTON = "xpath = .//*[@id='a0s21000000IrSgAAK-17']/div[2]/div[3]/button";
    	public static final String ATTESTATION_PAGE_HEADER = "xpath = .//*[contains(text(),'Attestation of Eligibility')]"; //*[@id="AttestofElg"]/div[1]/span[1]/span
    }
	
	 public class AddressInformation {
     	public static final String TEXT_STREETADDRESS = "xpath=//*[@id='EPStreetName']";
     	public static final String RADION_MAILINGPERMAMNENTADDRESS = "xpath=//*[@id='EMailingAddSameAsPerm']";
     	public static final String TEXT_CITY ="xpath=.//*[@id='EPermanentAdd']/div/div[2]/div[3]/child/div/ng-form/label";
     	public static final String CITY_NAME = "xpath = .//*[@id='EPCity']";
     	public static final String TEXT_STATE ="xpath=.//*[@id='EPermanentAdd']/div/div[2]/div[4]/child/div/ng-form/label";
     	public static final String STATE_NAME = "xpath = .//*[@id='EPState']";
     	public static final String TEXT_MAILINGSTREETADDRESS = "xpath=//*[@id='EMStreetName']";
     	public static final String TEXT_MAILINGZIP = "xpath=//*[@id='EMZipCode']";
     	public static final String TEXT_COUNTRY ="xpath=//*[@id='EPermanentAdd']/div/div[2]/div[5]/child/div/ng-form/label";
     	public static final String COUNTRY_NAME = "xpath = .//*[@id='EPCounty']";
     	public static final String MAILING_ADDRESS_COPY = "xpath = .//*[@id='EMailingAddSameAsPerm'][@value='Yes']";
     	public static final String BUTTON_NEXT ="xpath=.//*[@id='a0s21000000IrSgAAK-13']/div[2]/div[3]/button";
     }
		
	public class MedicareInsuranceInformation {
    	public static final String TEXT_NAME ="xpath=//*[@id='MName']";
    	public static final String TEXT_CLAIMNO = "xpath=//*[@id='MClaimNo']";
    	public static final String GENDER_MALE = "xpath=.//*[@id='MSex'][@value='Male']";
    	public static final String GENDER_FEMALE = "xpath=.//*[@id='MSex'][@value='Female']";
    	public static final String TEXT_PARTA_DATE ="xpath=//*[@id='MPartADate']";
    	public static final String TEXT_PARTB_DATE = "xpath=//*[@id='MPartBDate']";
    	public static final String ELE_INVALID_CLAIM_ERROR= "xpath=//*[@id='a0s21000000IrSgAAK-15']/div[1]/div[6]/child/div/ng-form/div/div[2]/small[contains(text(),'Invalid Health Care Number')]";
    	public static final String BUTTON_NEXT = "xpath=//*[@id='a0s21000000IrSgAAK-15']/div[2]/div[3]/button[contains(text(),'Next')]";
    	public static final String ELE_INVALID_EFFECTIVE_DATE= "xpath=//*[@id='a0s21000000IrSgAAK-15']/div[1]/div[12]/child/div/ng-form/div/div[1]/small[contains(text(),'Part A and Part B effective date can only be the first of any month')]";
    }
	
	public class ApplicationSummary {
		public static final String BUTTON_VIEW_PRINT_DOWNLOAD ="xpath=//*[@id='ReviewId']/span";
		public static final String LINK_GOBACK_TO_OPPORTUNITY ="xpath=//*[@id='vos']/a";
		public static final String CHECK_AGENT ="xpath=//*[@id='MName']";
		public static final String TEXT_AGENT_AGENCY ="xpath=//*[@id='MName']";
		public static final String TEXT_AGENT_FNAME ="xpath=//*[@id='MName']";
		public static final String TEXT_AGENT_LNAME ="xpath=//*[@id='MName']";
		public static final String TEXT_AGENT_ID ="xpath=//*[@id='MName']";
		public static final String TEXT_NPN ="xpath=//*[@id='MNRame']";
		public static final String CHECK_AUTHREP ="xpath=//*[@id='MName']";
		public static final String TEXT_AUTHREP_NAME ="xpath=//*[@id='MName']";
		public static final String TEXT_AUTHREP_PHONE ="xpath=//*[@id='MName']";
		public static final String TEXT_AUTHREP_ADDRESS ="xpath=//*[@id='MName']";
		public static final String TEXT_AUTHREP_REALTION ="xpath=//*[@id='MName']";
		public static final String RADIO_APPLICATIONENTRY ="xpath=//*[@id='MName']";
		
	}
}

