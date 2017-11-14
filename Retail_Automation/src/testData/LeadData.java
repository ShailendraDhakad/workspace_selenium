package testData;


import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import appModules.SFDCLeads;
import utility.DateUtil;
import utility.StringUtil;

//@method setFirstName(String & firstName)
public class LeadData {

	String FirstName;
	String MiddleInitial;
	String LastName;
	String Suffix;
	String DateOfBirth;
	String LeadType;
	String LeadSource;
	String LeadStatus;
	String SSN;
	String Campaign;
	String SalesRep;
	String HomePhone;
	String HomePhonePreferred;
	String Mobile;
	String MobilePreferred;
	String WorkPhone;
	String WorkPhonePreferred;
	String Email;
	String EmailPreferred;
	String Country;
	String Street;
	String City;
	String State;
	String Zip;
	String County;
	String PermissionToCall;
	String PermissionToEmail;
	String PermissionToMail;
	String EligibilityInformation;
	String MedicareNo;
	String PartBEffectiveDate;
	String DecileUnder65;
	String DecileSupplement;
	String DecileAdvantage;
	String FedDNCList;
	String ListPurchaseStartDate;
	String DNCOverrideStart;
	String ListPurchaseEndDate;
	String DNCOverrideEnd;
	String LowIncomeRange;
	String HighIncomeRange;
	String LeadCreatedThroughCTI;
	String Comments;
	String ImagicExternalId;
	String Deceased;
	String CurrentlyAHPHCMember;
	String EnrolledinMedicare;
	String CurrentHealthInsurance;
	String EmployerNameProvidingHealthInsurance;
	String InterestedInHPHCMedicareOptions;
	String ViewedOnlinePresentationChoosen;
	String LeadVIP;
	
	
	public String getFirstName() {
		return FirstName;
		
	}
	
	public void setFirstName(String firstName) {
		
		if (firstName.equals("Dynamic")) {
			FirstName = StringUtil.generateRandomString(7);
		} else {
			FirstName = firstName;
		}
	}

	public String getMiddleInitial() {
		return MiddleInitial;
	}

	public void setMiddleInitial(String middleInitial) {
		if (middleInitial.equals("Dynamic")) {
			MiddleInitial = StringUtil.generateRandomString(1);
		} else {
			MiddleInitial = middleInitial;
		}

	}

	public String getLastName() {
		return LastName;
	}

	public void setLastName(String lastName) {
		if (lastName.equals("Dynamic")) {
			LastName = StringUtil.generateRandomString(4);
		} else {
			LastName = lastName;
		}

	}

	public String getSuffix() {
		return Suffix;
	}

	public void setSuffix(String suffix) {
		String sSuffix[] = { "Mr.", "Ms.", "Mrs.", "Dr.", "Prof.", "Rev", "Atty", "None/Null/Blank" };
		if (suffix.equals("Dynamic")) {
			List<String> sSuffixList = Arrays.asList(sSuffix);
			Collections.shuffle(sSuffixList);
			Suffix = sSuffixList.get(1);
		} else {
			Suffix = suffix;
		}

	}

	public String getDateOfBirth() {
		return DateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth, int age) {
		if (dateOfBirth.equals("Dynamic")) {
			DateOfBirth = DateUtil.randomDate(age);
		} else {
			DateOfBirth = dateOfBirth;
		}

	}

	public void setDateOfBirth(String dateOfBirth) {
		DateOfBirth = dateOfBirth;
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

	public String getLeadSource(){
		return LeadSource;
	}
	
	public void setLeadSource(String leadSource) {
		String sLeadSource[] = { "Other/Null/Blank","Advertisement","Broker","C65 Online Presentation"
				,"Existing Member","HPI Member","Inbound Call","Microsite","Physician"
				,"Purchase List","Referral","Seminar","Senior Agency,","Trade Show","Walk-in","Web" };
		if (leadSource.equals("Dynamic")) {
			List<String> sLeadSourceList = Arrays.asList(sLeadSource);
			Collections.shuffle(sLeadSourceList);
			LeadSource = sLeadSourceList.get(0);
		} 
		else {
			LeadSource = leadSource;
		}

	}

	
	public String getLeadStatus(){
		return LeadStatus;
	}
	
	public void setLeadStatus(String leadStatus) {
		String sLeadStatus[] = { "Open","Qualified","Ineligible","Closed for Current Year","Closed foreever"};
		if (leadStatus.equals("Dynamic")) {
			List<String> sLeadStatusList = Arrays.asList(sLeadStatus);
			Collections.shuffle(sLeadStatusList);
			LeadStatus = sLeadStatusList.get(0);
		} 
		else {
			LeadStatus = leadStatus;
		}

	}
	
	public String getSSN() {
		return SSN;
	}

	public void setSSN(String sSN) {
		if (sSN.equals("Dynamic")) {
			SSN=StringUtil.randomNumber(9);
		}
		else
		SSN = sSN;
	}

	public String getCampaign() {
		return Campaign;
	}

	public void setCampaign(String campaign) {
		Campaign = campaign;
	}

	public String getSalesRep() {
		return SalesRep;
	}

	public void setSalesRep(String salesRep) {
		SalesRep = salesRep;
	}

	public String getHomePhone() {
		return HomePhone;
	}

	public void setHomePhone(String homePhone) {
		if (homePhone.equals("Dynamic")) {
			HomePhone=StringUtil.randomNumber(10);
		}
		else
		HomePhone = homePhone;
	}

	public String getHomePhonePreferred() {
		return HomePhonePreferred;
	}

	public void setHomePhonePreferred(String homePhonePreferred) {
		String shomePhonePrefer[] = { "Yes", "No" };
		if (homePhonePreferred.equals("Dynamic")) {
			List<String> shomePhonePrefereList = Arrays.asList(shomePhonePrefer);
			Collections.shuffle(shomePhonePrefereList);
			HomePhonePreferred = shomePhonePrefereList.get(0);
		}
		else
		HomePhonePreferred = homePhonePreferred;
	}

	public String getMobile() {
		return Mobile;
	}

	public void setMobile(String mobile) {
		if (mobile.equals("Dynamic")) {
			Mobile=StringUtil.randomNumber(10);
		}
		else
		Mobile = mobile;
	}

	public String getMobilePreferred() {
		return MobilePreferred;
	}

	public void setMobilePreferred(String mobilePreferred) {
		String sMobilePrefer[] = { "Yes", "No" };
		if (mobilePreferred.equals("Dynamic")) {
			List<String> sMobilePreferredList = Arrays.asList(sMobilePrefer);
			Collections.shuffle(sMobilePreferredList);
			mobilePreferred = sMobilePreferredList.get(0);
		}
		else
		MobilePreferred = mobilePreferred;
	}

	public String getWorkPhone() {
		return WorkPhone;
	}

	public void setWorkPhone(String workPhone) {
		if (workPhone.equals("Dynamic")) {
			WorkPhone=StringUtil.randomNumber(10);
		}
		else
		WorkPhone = workPhone;
	}

	public String getWorkPhonePreferred() {
		return WorkPhonePreferred;
	}

	public void setWorkPhonePreferred(String workPhonePreferred) {
		String sWorkPhonePrefer[] = { "Yes", "No" };
		if (workPhonePreferred.equals("Dynamic")) {
			List<String> sWorkPhonePreferList = Arrays.asList(sWorkPhonePrefer);
			Collections.shuffle(sWorkPhonePreferList);
			WorkPhonePreferred = sWorkPhonePreferList.get(0);
		}
		else
		WorkPhonePreferred = workPhonePreferred;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		if (email.equals("Dynamic"))
			Email = StringUtil.generateRandomString(7) + "@test.com";
		else
			Email = email;
	}

	public String getEmailPreferred() {
		return EmailPreferred;
	}

	public void setEmailPreferred(String emailPreferred) {
		String sEmailPreferred[] = { "Yes", "No" };
		if (emailPreferred.equals("Dynamic")) {
			List<String> sEmailPreferredList = Arrays.asList(sEmailPreferred);
			Collections.shuffle(sEmailPreferredList);
			EmailPreferred = sEmailPreferredList.get(0);
		}
		else
		EmailPreferred = emailPreferred;
	}

	public String getCountry() {
		return Country;
	}

	public void setCountry(String country) {
		Country = country;
	}

	public String getStreet() {
		return Street;
	}

	public void setStreet(String street) {
		if (street.equals("Dynamic"))
			
			Street = "23, " +StringUtil.generateRandomString(4) ;
		else
		Street = street;
	}

	public String getCity() {
		return City;
	}

	public void setCity(String city) {
		City = city;
	}

	public String getState() {
		return State;
	}

	public void setState(String state) {
		State = state;
	}

	public String getZip() {
		return Zip;
	}

	public void setZip(String zip) {
		if (zip.equals("Dynamic")){
			Zip=StringUtil.randomNumber(5);
		}
		else if ((zip.equalsIgnoreCase("MA")) || (zip.equalsIgnoreCase("ME")) || (zip.equalsIgnoreCase("NH")))
			Zip=SFDCLeads.getZipCode(zip);
		else
			Zip = zip;
	}

	public String getCounty() {
		return County;
	}

	public void setCounty(String county) {
		County = county;
	}

	public String getPermissionToCall() {
		return PermissionToCall;
	}

	public void setPermissionToCall(String permissionToCall) {
		PermissionToCall = permissionToCall;
	}

	public String getPermissionToEmail() {
		return PermissionToEmail;
	}

	public void setPermissionToEmail(String permissionToEmail) {
		PermissionToEmail = permissionToEmail;
	}

	public String getPermissionToMail() {
		return PermissionToMail;
	}

	public void setPermissionToMail(String permissionToMail) {
		PermissionToMail = permissionToMail;
	}

	public String getEligibilityInformation() {
		return EligibilityInformation;
	}

	public void setEligibilityInformation(String eligibilityInformation) {
		EligibilityInformation = eligibilityInformation;
	}

	public String getMedicareNo() {
		return MedicareNo;
	}

	public void setMedicareNo(String medicareNo) {
		MedicareNo = medicareNo;
	}

	public String getPartBEffectiveDate() {
		return PartBEffectiveDate;
	}

	public void setPartBEffectiveDate(String partBEffectiveDate) {
		PartBEffectiveDate = partBEffectiveDate;
	}

	public String getDecileUnder65() {
		return DecileUnder65;
	}

	public void setDecileUnder65(String decileUnder65) {
		DecileUnder65 = decileUnder65;
	}

	public String getDecileSupplement() {
		return DecileSupplement;
	}

	public void setDecileSupplement(String decileSupplement) {
		DecileSupplement = decileSupplement;
	}

	public String getDecileAdvantage() {
		return DecileAdvantage;
	}

	public void setDecileAdvantage(String decileAdvantage) {
		DecileAdvantage = decileAdvantage;
	}

	public String getFedDNCList() {
		return FedDNCList;
	}

	public void setFedDNCList(String fedDNCList) {
		FedDNCList = fedDNCList;
	}

	public String getListPurchaseStartDate() {
		return ListPurchaseStartDate;
	}

	public void setListPurchaseStartDate(String listPurchaseStartDate) {
		ListPurchaseStartDate = listPurchaseStartDate;
	}

	public String getDNCOverrideStart() {
		return DNCOverrideStart;
	}

	public void setDNCOverrideStart(String dNCOverrideStart) {
		DNCOverrideStart = dNCOverrideStart;
	}

	public String getListPurchaseEndDate() {
		return ListPurchaseEndDate;
	}

	public void setListPurchaseEndDate(String listPurchaseEndDate) {
		ListPurchaseEndDate = listPurchaseEndDate;
	}

	public String getDNCOverrideEnd() {
		return DNCOverrideEnd;
	}

	public void setDNCOverrideEnd(String dNCOverrideEnd) {
		DNCOverrideEnd = dNCOverrideEnd;
	}

	public String getLowIncomeRange() {
		return LowIncomeRange;
	}

	public void setLowIncomeRange(String lowIncomeRange) {
		LowIncomeRange = lowIncomeRange;
	}

	public String getHighIncomeRange() {
		return HighIncomeRange;
	}

	public void setHighIncomeRange(String highIncomeRange) {
		HighIncomeRange = highIncomeRange;
	}

	public String getLeadCreatedThroughCTI() {
		return LeadCreatedThroughCTI;
	}

	public void setLeadCreatedThroughCTI(String leadCreatedThroughCTI) {
		LeadCreatedThroughCTI = leadCreatedThroughCTI;
	}

	public String getComments() {
		return Comments;
	}

	public void setComments(String comments) {
		Comments = comments;
	}

	public String getImagicExternalId() {
		return ImagicExternalId;
	}

	public void setImagicExternalId(String imagicExternalId) {
		ImagicExternalId = imagicExternalId;
	}

	public String getDeceased() {
		return Deceased;
	}

	public void setDeceased(String deceased) {
		Deceased = deceased;
	}

	public String getCurrentlyAHPHCMember() {
		return CurrentlyAHPHCMember;
	}

	public void setCurrentlyAHPHCMember(String currentlyAHPHCMember) {
		CurrentlyAHPHCMember = currentlyAHPHCMember;
	}

	public String getEnrolledinMedicare() {
		return EnrolledinMedicare;
	}

	public void setEnrolledinMedicare(String enrolledinMedicare) {
		EnrolledinMedicare = enrolledinMedicare;
	}

	public String getCurrentHealthInsurance() {
		return CurrentHealthInsurance;
	}

	public void setCurrentHealthInsurance(String currentHealthInsurance) {
		CurrentHealthInsurance = currentHealthInsurance;
	}

	public String getEmployerNameProvidingHealthInsurance() {
		return EmployerNameProvidingHealthInsurance;
	}

	public void setEmployerNameProvidingHealthInsurance(String employerNameProvidingHealthInsurance) {
		EmployerNameProvidingHealthInsurance = employerNameProvidingHealthInsurance;
	}

	public String getInterestedInHPHCMedicareOptions() {
		return InterestedInHPHCMedicareOptions;
	}

	public void setInterestedInHPHCMedicareOptions(String interestedInHPHCMedicareOptions) {
		InterestedInHPHCMedicareOptions = interestedInHPHCMedicareOptions;
	}

	public String getViewedOnlinePresentationChoosen() {
		return ViewedOnlinePresentationChoosen;
	}

	public void setViewedOnlinePresentationChoosen(String viewedOnlinePresentationChoosen) {
		ViewedOnlinePresentationChoosen = viewedOnlinePresentationChoosen;
	}
	
	public String getLeadVIP()
	{
		return LeadVIP;
	}
	public void setLeadVIP(String strVIPNumbr) {
		LeadVIP=strVIPNumbr;
		
	}
}