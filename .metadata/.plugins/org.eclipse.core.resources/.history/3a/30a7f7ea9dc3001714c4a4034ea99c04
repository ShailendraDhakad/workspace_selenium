package testData;

import utility.StringUtil;
public class MedicareImportantQuestionInfo {
	String CoverageName;
	String ESRD;
	String OtherDrugCoverage;
	String CoverageID;
	String CoverageGroup;
	String LongCareFacilityResident;
	String InstitutionName;
	String Phone;
	String StreetAddress;
	String MedicaidPrograme;
	String MedicalNumber;
	String PCPID;
	String FirstName;
	String MiddleName;
	String LastName;
	
	public String getFirstName() {
		return FirstName;
	}

	public void setFirstName(String strFirstName) {
		if (strFirstName.equals("Dynamic"))
			FirstName = StringUtil.generateRandomString(5) ;
		else
			FirstName=strFirstName;
	}
	
	public String getMiddleName() {
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
	
	public String getMedicaidPrograme() {
		return MedicaidPrograme;
	}

	public void setMedicaidPrograme(String sMedicaidPrograme) {
		MedicaidPrograme=sMedicaidPrograme;
	}
	
	public String getOtherDrugCoverage() {
		return OtherDrugCoverage;
	}

	public void setOtherDrugCoverage(String sOtherDrugCoverage) {
		OtherDrugCoverage=sOtherDrugCoverage;
	}
	
	public String getLongCareFacilityResident() {
		return LongCareFacilityResident;
	}

	public void setLongCareFacilityResident(String sLongCareFacilityResident) {
		LongCareFacilityResident=sLongCareFacilityResident;
	}
	public String getESRD() {
		return ESRD;
	}

	public void setESRD(String sESRD) {
		ESRD=sESRD;
	}
	
	public String getPCPID() {
		return PCPID;
	}

	public void setPCPID(String strPCPID) {
		PCPID=strPCPID;
	}
	
	public String getMedicalNumber() {
		return MedicalNumber;
		
	}
	
	public void setMedicalNumber(String claimNumber) {
		
		if (claimNumber.equals("Dynamic")) {
			MedicalNumber = StringUtil.randomNumber(9) + StringUtil.generateRandomString(1);
		} else {
			MedicalNumber = claimNumber;
		}
	}
	
	
	public String getStreetAddress() {
		return StreetAddress;
	}

	public void setStreetAddress(String strStreetAddress) {
		if (strStreetAddress.equals("Dynamic"))
			StreetAddress = StringUtil.randomNumber(3) + "," + StringUtil.generateRandomString(5) ;
		else
			StreetAddress=strStreetAddress;
	}
	public String getPhone() {
		return Phone;
	}

	public void setPhone(String strPhone) {
		
		if (strPhone.equals("Dynamic"))
			Phone = StringUtil.randomNumber(10) ;
		else
			Phone=strPhone;
	}
	
	public String getInstitutionName() {
		return InstitutionName;
	}

	public void setInstitutionName(String name) {

		if (name.equals("Dynamic")) {
			InstitutionName = StringUtil.generateRandomString(7);
		} else {
			InstitutionName = name;
		}
	}
	public String getCoverageName() {
		return CoverageName;
	}

	public void setCoverageName(String name) {

		if (name.equals("Dynamic")) {
			CoverageName = StringUtil.generateRandomString(7);
		} else {
			CoverageName = name;
		}
	}

	public String getCoverageID() {
		return CoverageID;
	}

	public void setCoverageID(String number_ID) {

		if (number_ID.equals("Dynamic")) {
			CoverageID = StringUtil.generateRandomString(7);
		} else {
			CoverageID = number_ID;
		}
	}
	

	public String getCoverageGroup() {
		return CoverageGroup;
	}

	public void setCoverageGroup(String coverageGroup) {

		if (coverageGroup.equals("Dynamic")) {
			CoverageGroup = StringUtil.generateRandomString(7);
		} else {
			CoverageGroup = coverageGroup;
		}
	}
}
