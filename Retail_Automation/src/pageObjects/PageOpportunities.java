
package pageObjects;

public class PageOpportunities {
	
	public static final String SELECT_OPPORTUNITIES  ="id=fcf";
	public static final String SELECT_OPPORTUNITIES_RECORDTYPE  ="id=p3";
	public static final String BUTTON_GO  ="name=go";
	public static final String ITEM_OPPORTUNITYNAME  ="xpath=//html[1]/body[1]/div[1]/div[2]/table[1]/tbody[1]/tr[1]/td[2]/div[3]/div[1]/div[1]/div[2]/table[1]/tbody[1]/tr[2]/th[1]/a[1]";
	public static final String LINK_OPPORTUNITY_TAB="xpath=//*[@id='Opportunity_Tab']/a";
	public static final String TABLE_OPPORTUNITY="xpath=//*[@class='x-grid3-body']/div/table/tbody";
	public static final String BUTTON_NEW="xpath=//input[@name='new']";
	public static final String BUTTON_CONTINUE="xpath=//input[@value='Continue']";
	public static final String TEXT_ACCOUNTNAME="xpath=//input[@name='opp4']";
	public static final String ELE_OPPORTUNITY_NAME= "xpath = //*[@scope='row']/a";
	public static final String BUTTON_NEWFULFILLMENTREQST  ="xpath=//input[@value='New Fulfillment Request']";
	//*[@id="bottomButtonRow"]/input[1]
	//*[@id="bottomButtonRow"]/input[1]
	//*[@id="p3"]
	public class Details{
		
		public static final String BUTTON_QUOTEENROLL  ="xpath=//input[@value='Quote/Enroll']";
		public static final String BUTTON_EDIT  ="xpath=//div[@class='pbHeader']//input[@name='edit']";
		public static final String LINK_ACCOUNTNAME="xpath=//div[contains(text(),strToreplace)]";
		public static final String Element_OpportunityName  ="id=opp3_ileinner";
		public static final String Element_LeadSource  ="id=opp6_ileinner";
		public static final String Element_OpporRecordType  ="id=RecordType_ileinner";
		public static final String Element_OpporExpiryDate  ="id=opp9_ileinner";
		public static final String Element_Probability  ="id=opp12_ileinner";
		public static final String Element_ProbabilityText = "//*[@id='opp12_ileinner']";
		public static final String Element_FulfillmentStatus = "xpath=//td[text()='Request sent to CMAPS']";
		public static final String Element_FulfillmentStatusFirst = "xpath=//*[@id='00621000004ThBx_00N1a0000084j82_body']/table/tbody/tr[3]/td[2]";
		public static final String Element_FulfillmentStatusSecond = "xpath=//*[@id='00621000004ThBx_00N1a0000084j82_body']/table/tbody/tr[2]/td[2]";
		public static final String Element_InProgressQuote = "xpath=//a[contains(text(),'In Progress')]"; //*[@id="j_id0:j_id1:j_id2:j_id3:0:j_id5"]/a
		public static final String WEBTABLE_InProgressQuote = "xpath=//*[@class='opportunityBlock']//div[@class='pbBody']/table/tbody";
		public static final String Frame_quote="id=0661a000001huxI";
		public static final String Element_Status = "xpath=//*[@id='opp11_ileinner']";
		//public static final String Element_Statusxpath = "//*[@id='opp11_ileinner']";
		//public static final String Element_Probability = "xpath=//*[text()='50%']";
		public static final String BUTTON_MEDICAREADVENROLL= "xpath=//input[@value='Medicare Advantage Quote/Enroll']";
		public static final String BUTTON_MEDICAREENROLLMENT = "xpath=.//*[@id='topButtonRow']/input[4]";
		public static final String BUTTON_MEDICARESUPENROLL= "xpath=//*[@id='topButtonRow']/input[6]";
	}
	
	public class DataFullfillment{
		public static final String ELE_FULLFILLMENT_BODYCELL = "xpath = .//*[@id='bodyCell']/div[3]";
		public static final String SELECT_FULLFILLMENT_OPTION = "xpath = .//*[@id='006210000053XGc_00N1a0000084j82_link']/span";
		public static final String TEXT_FULLFILLMENT_NUMBER = "XPATH = //*[@class='bRelatedList'][2]//div[@class='pbBody']/table/tbody/tr[2]/th";
		public static final String TEXT_FULLFILLMENT_STATUS = "XPATH = //*[@class='bRelatedList'][2]//div[@class='pbBody']/table/tbody/tr[2]/td[2]";
		public static final String TEXT_QUOTE_NUMBER = "XPATH = //div[@class='listRelatedObject quoteBlock']//div[2]/table/tbody/tr[3]/th";
		public static final String LINK_FULLFILLMENT_EDIT = "XPATH = //*[@class='bRelatedList'][2]//div[@class='pbBody']/table/tbody/tr[2]/td[1]/a";
		public static final String SELECT_MORE_OPTION = "xpath = .//*[@id='bodyCell']/div[12]/a[2]";
		public static final String LINK_FULLFILLMENT_NUMBER = "XPATH = //*[@class='bRelatedList'][2]//div[@class='pbBody']/table/tbody/tr[2]/th/a";
		public static final String ELE_FULLFILLMENTTABLE_HEADER="xpath=//*[@class='bRelatedList']//div[@class='pbBody']/table/tbody/tr[1]/th[2]";
		public static final String ELE_FULLFILLMENTTABLE_NUMBER="xpath=//*[@class='bRelatedList']//div[@class='pbBody']/table/tbody/tr[2]/th[1]";
		public static final String LINK_FULLFILLMENTTABLE_NUMBER = "XPATH = //*[@class='bRelatedList']//div[@class='pbBody']/table/tbody/tr[2]/th/a";
		public static final String ELE_FULLFILLMENTTABLE_STATUS = "XPATH =//*[@class='bRelatedList']//div[@class='pbBody']/table/tbody/tr[2]/td[2]";
	}
	
	
	public class MyInformation{
		public static final String TEXT_ZIPCODE  ="id=MinZipCode";
		public static final String SELECT_COVERAGEEFFECTIVEDATE  ="xpath=//*[contains(@id,'RequestedCoverageEffectiveDateLookupTelesales')]";
		public static final String SELECT_TOBACCO_USE  ="id=MinTobaccoUse";
		public static final String TEXT_DATEOFBIRTH  ="id=MinDOB";
		public static final String SELECT_COVERAGE  ="id=DependentCoverage";
		public static final String BUTTON_CANCEL  ="id=SaveandExitButton2";
		public static final String BUTTON_NEXT  ="xpath=//*[@id='a0s21000000U35sAAC-1']/div[2]/div[3]/button";
		public static final String ELE_FUTUREDOBERROR  ="xpath=//*[@id='a0s21000000U35sAAC-1']/div[1]/div[11]/child/div/ng-form/div/div";
		public static final String ELE_ERRORMESSAGE  ="xpath=//div[@class='vlc-validation-error ng-scope']";
		public static final String ELE_ZIP_ERRORMESSAGE  ="xpath=//div[@class='error-field ng-scope ng-hide']";
		
		

	}
	
	public class ComparePlans{
		public static final String CHECKCOMPARE  ="xpath=//input[@type='checkbox']";
		public static final String BUTTON_SELECTPLAN  ="xpath=//span[contains(text(),'Select Plan')]";
		public static final String BUTTON_COMPAREPLAN  ="xpath=//button[contains(text(),'Compare')]";
		public static final String BUTTON_CLOSE=  "xpath=//div/div/div/div/i";
		public static final String BUTTON_SELECTED=  "xpath=//span[@class='selected']";
		public static final String BUTTON_NEXT  ="xpath=//*[@id='a0s21000000U35sAAC-9']/div[2]/div[3]/button  | //*[@id='a0s21000000U35sAAC-9']/div[2]/div[4]/button | //*[@id='a0s21000000IrSgAAK-5']/div[2]/div[3]/button | //*[@id='a0s21000000KYPoAAO-5']/div[2]/div[3]/button";
		public static final String BUTTON_CANCEL  ="xpath=(//button[@id='SaveandExitButton2'])[3] | //*[@id='CancelButton'] | //*[@id='SaveandExitButton2']";
		public static final String BUTTON_PREVIOUS  ="xpath=//*[@id='a0s21000000U35sAAC-9']/div[2]/div[4]/button";
		public static final String SELECT_PRODUCTYPE  ="xpath=//*[@id='productType']";
		public static final String SELECT_METALLEVEL  ="xpath=//select[@id='levelCoverage']";
		public static final String SELECT_NETWORK  ="xpath=//select[@id='networkName']";
		public static final String SELECT_DENTAL  ="xpath=//select[@id='dental']";
		public static final String SELECT_FAVORITES  ="xpath=//select[@id='favorites']";
		public static final String Link_SOB="xpath=//a[contains(text(),'View Schedule of Benefits (SOB)')]";
		public static final String Link_SBC="xpath=//a[contains(text(),'View Summary of Benefits and Coverage (SBC)')]";
		public static final String Link_SEARCHPROVIDERDIRECTORY="xpath=//a[contains(text(),' Search Provider Directory')]";
		public static final String Element_PLANS  ="xpath=//div[@class='top-container is-collapsed']/h1";
		public static final String ELEMENT_NOPRODUCT_MSG  ="xpath=//div[@class='products-container']//p[contains(text(),'No Products Available')]";
		public static final String Link_Formulary="xpath=//a[text()='Formulary']";
		public static final String BUTTON_PREVIOUS_NEXT_PAGE="xpath=//*[@id='a0s21000000U35sAAC-10']/div[2]/div[4]/button";
		public static final String TEXT_ERROR_NOPLANSELECTED="xpath=//*[@id='a0s21000000U35sAAC-9']/div[1]/div[4]/child/div/ng-form/div/div[1]";
		public static final String TEXT_ERROR_ONEPLANSELECTED="xpath=html/body/div[3]/div/div/div/div[2]/label[text()='You need to select at least two products to compare.']";
		public static final String TEXT_ERROR_CLOSE="xpath=html/body/div[3]/div/div/div/div[1]/i";
	}
	
	public class MyFamily{
		
		public static final String TEXT_DATEOFBIRTH  ="id=DependentDOB";
		public static final String TEXT_DATEOFBIRTHID = "DependentDOB";
		public static final String SELECT_RELATION  ="id=Relationship";
		public static final String SELECT_RELATIONID  ="Relationship";
		public static final String SELECT_TOBACCO  ="id=MinTobaccoUse";
		public static final String ELE_AGE  ="id=DependentAge";
		public static final String LINK_ADD_NEWDEP  ="xpath=//*[@id='DependentList']/div/div[1]/a";
		public static final String BUTTON_PREVIOUS  ="xpath=//*[@id='a0s21000000U35sAAC-4']/div[2]/div[4]/button";
		public static final String BUTTON_CANCEL  ="xpath=(//button[@id='SaveandExitButton2'])[2]";
		public static final String BUTTON_NEXT  ="xpath=//*[@id='a0s21000000U35sAAC-4']/div[2]/div[3]/button";
		public static final String TEXT_DEPENDENT_AGE_ERROR  ="xpath=//*[@id='DependentList']/div/div[2]/div[1]/child/ng-form/div[2]/div[1]";
//		public static final String BUTTON_NEXT  ="xpath=//*[@id='a0s21000000U35sAAC-1']/div[2]/div[3]/button";
//		public static final String   ="xpath=//*[@id="DependentAge"]
//		public static final String ELE_ERRORMESSAGE  ="xpath=//div[@class='vlc-validation-error ng-scope']";
//		public static final String ELE_ZIP_ERRORMESSAGE  ="xpath=//div[@class='error-field ng-scope ng-hide']";
//		
		

	}
	public class BasicSubscriberInformation{
		
		public static final String HTMLDivElementClassvos  ="id=vos";
		public static final String SELECT_SALUTATION  ="xpath=//*[@id='qPrefix'] | //*[@id='qSalutation']";
		public static final String TEXT_FIRSTNAME  ="id=qFirstName";
		public static final String TEXT_MIDDLENAME  ="xpath=//*[@id='qMiddleName'] | //*[@id='qMI']";
		public static final String TEXT_LASTNAME  ="id=qLastName";
		public static final String TEXT_SUFFIX  ="id=qSuffix";
		public static final String TEXT_STREETADDRESS = "xpath=.//*[@id='qStreetAddress']";
		public static final String TEXT_STREETNAME = "xpath=.//*[@id='qStreetName'] | //*[@id='qStreetAddress']" ;
		public static final String TEXT_EMAILADDRESS1 = "xpath = .//*[@id='qEmail']";
		public static final String TEXT_EMAILADDRESS2 = "xpath = .//*[@id='qAddEmail']"; 
		public static final String BUTTON_NEXT  ="xpath=//*[@id='a0s21000000U35sAAC-10']/div[2]/div[3]/button | //*[@id='a0s21000000IrSgAAK-7']/div[2]/div[3]/button | //*[@id='a0s21000000IrSgAAK-5']/div[2]/div[3]/button | //*[@id='a0s21000000KYPoAAO-7']/div[2]/div[3]/button";
		public static final String BUTTON_CANCEL  ="xpath=//div[@id='PlanSelection_cancel']/*/button[@id='CancelButton']";
		public static final String BUTTON_PREVIOUS  ="xpath=//*[@id='a0s21000000U35sAAC-10']/div[2]/div[4]/button | //*[@id='a0s21000000IrSgAAK-7']/div[2]/div[4]/button | //*[@id='a0s21000000KYPoAAO-7']/div[2]/div[4]/button";
		public static final String TEXT_DOB  ="xpath=//*[@id='qDOB']";
		public static final String TEXT_FUTURE_DOB_ERROR = "xpath=.//*[@id='qName_blk']/div/div[2]/div[11]/child/div/ng-form/div/div/small[contains(text(),'Date of Birth Cannot Be In the Future')]";
	    public static final String TEXT_DOB_LOWERLIMITERROR = "xpath=.//*[@id='qName_blk']/div/div[2]/div[12]/child/div/ng-form/div/div/small[contains(text(),'Applicant must be 2 years or older to apply for Medicare Advantage plan enrollment')]";
	}
	
	public class DeliverMyQuote{
		public static final String RADION_ONE  ="id=MailEmail";
		public static final String RADION_INCLUDESOB="id=Include_SOB"; 
		public static final String RADION_INCLUDESOB_YES = "xpath = .//*[@id='Include_SOB'][@value ='Yes']";
		public static final String RADION_INCLUDESBC_YES = "xpath = .//*[@id='Include_SBC'][@value ='Yes']";
		public static final String RADION_INCLUDESBC="id=Include_SBC"; 
		public static final String BUTTON_VIEWPRINTQUOTE  ="xpath=//span[contains(text(), 'View/Print Quote')]  | //*[@id='BrokerQuoteid']/span | //*[@class='form-control btn btn-primary ng-binding'] [contains(text(), 'Print Quote')]"; 
		public static final String BUTTON_SAVEANDEXIT  ="id=SaveandExitButton";
		public static final String BUTTON_PREVIOUS  ="xpath=//*[@id='a0s21000000U35sAAC-12']/div[2]/div[4]/button";		
		public static final String BUTTON_ENROLLNOW  ="xpath=//*[contains(text(), 'Enroll Now')]";
		public static final String ELEMENT_SENDBYEMAILERROR  ="xpath=//*[@id='MailBlock']/div/div[2]/div[6]/child/div/ng-form/div/div";
		public static final String ElEMENT_SENDBYMAILERROR  ="xpath=//*[@id='MailBlock']/div/div[2]/div[5]/child/div/ng-form/div/div";
		public static final String RADIO_EMAIL="xpath=//input[@value='By Email']";
		public static final String RADIO_MAIL="xpath=//input[@value='By Mail']";
		public static final String RADIO_NONE="xpath=//input[@value='None']";
		public static final String MSG_EMAIL_ADDRESS_ERROR="xpath=//*[contains(text(), 'Email address is missing')]";
		public static final String MSG_MAIL_ADDRESS_ERROR="xpath=//*[contains(text(), 'Street address is missing')]";
	}
	
	public class ApplicationSubInfo{
		public static final String SELECT_GENDER  ="id=Gender";
		public static final String SELECT_ACCEPTNEWPATIENTS  ="XPATH=//*[@id='pag:frm:details:pbsection']/div/table/tbody/tr[5]/td[1]/select";
		public static final String RADION_MAILINGPERMAMNENTADDRESS="id=Mailing_same_as_physical";
		public static final String TEXT_STREETADDRESS  ="id=StreetAddress";
		public static final String TEXT_MAILINGADDRESS  ="id=mStreetAddress";
		public static final String TEXT_CITY = "xpath=//*[@id='CityTown']";
		public static final String TEXT_STATE ="xpath=//*[@id='StateApplication']";
		public static final String TEXT_COUNTRY ="xpath=//*[@id='Countyforphysical']";
		public static final String TEXT_ZIP ="xpath=//*[@id='Address_blk']/div/div[2]/div[2]/child/div/ng-form/label]";
		public static final String TEXT_HOMEPHONE  ="id=HomePhone";
		public static final String TEXT_WORKPHONE  ="id=WorkTelephone";
		public static final String TEXT_MOBILEPHONE  ="id=MobilePhone";
		public static final String TEXT_PCPZIP  ="id=pag:frm:details:pbsection:pCodeSectionitem:postcode";
		public static final String TEXT_PCPCITY  ="id=pag:frm:details:pbsection:pCitySectionitem:pcity";
		public static final String BUTTON_PCPLOOKUP  ="id=PCPSubscriberButton";
		public static final String BUTTON_SEARCHPCP  ="xpath=//*[@id='pag:frm:details:j_id7']/input";
		public static final String LINK_SELECTPCP ="xpath=//*[@id='pag:frm:details:providertable:INDEX:j_id82']/a";
		public static final String TABLE_PCP ="xpath=//table[@id='pag:frm:details:providertable']/tbody";
		public static final String BUTTON_NEXT  ="xpath=//*[@id='a0s21000000U35sAAC-19']/div[2]/div[3]/button | //*[@id='a0s21000000U35sAAC-19']/div[2]/div[4]/button";
		public static final String ELE_PCPID= "id=AppPCPNumber";
		public static final String ELE_PCPFNAME= "id=PCPFName";
		public static final String ELE_PCPLNAME= "id=PCPLName";
		public static final String ELE_PCPCITY= "id=PCPCity";
		public static final String ELE_PCPCURRENTPATIENT= "id=PCPCurrentPatient";
		public static final String BUTTON_BROKERLOOKUP  ="xpath=//button[@id='BrokerButton']";
		public static final String BUTTON_SEARCH_BROKER  ="xpath=//*[@id='j_id0:j_id1:j_id3:j_id4:j_id5']";
		public static final String LINK_SELECT_BROKER ="xpath=//*[@id='j_id0:j_id1:j_id3:brokertable:INDEX:j_id41']/a";
		public static final String TABLE_BROKER ="xpath=//table[@id='j_id0:j_id1:j_id3:brokertable']/tbody";
		public static final String TEXT_PREFIX = "xpath=//*[@id='Prefix']";
		public static final String TEXT_FIRSTNAME = "xpath=//*[@id='FirstName']"; 
		public static final String TEXT_MIDDLENAME = "xpath=//*[@id='MiddleName']";
		public static final String TEXT_LASTNAME = "xpath=//*[@id='LastName']";
		public static final String TEXT_SUFFIX ="xpath=//*[@id='Suffix']";
		public static final String TEXT_DOB ="xpath=//*[@id='Name_blk']/div/div[2]/div[6]/child/ng-form/label";
		public static final String TEXT_TOBACCO = "xpath=//*[@id='TobaccoUse']";
		public static final String TEXT_EMAIL = "xpath=//*[@id='Email']";
	}
	
	
	public class ApplicationDepInfo{
		public static final String SELECT_GENDER  ="id=DependentGender";
		public static final String TEXT_DEPENDENT_FNAME  ="id=DependentFirstName";
		public static final String TEXT_DEPENDENT_LNAME  ="id=DependentLastName";
		public static final String BUTTON_NEXT="xpath= //*[@id='a0s21000000U35sAAC-20']/div[2]/div[3]/button | //*[@id='a0s21000000U35sAAC-19']/div[2]/div[4]/button";
		public static final String BUTTON_ADDPCP = "id=PCPButton"; //"xpath=//*[@id='PCPSubscriberButton'][@title='PCP selection is mandatory if the selected plan is HMO']";
	}
	
	public class CommunicationPref{
		
		public static final String CHECKBOX_PHONE="id=ComFhonecheckbox";
		public static final String CHECKBOX_MAIL="id=ComMailcheckbox";
		public static final String CHECKBOX_EMAIL="id=CommEmailcheckbox";
		public static final String BUTTON_NEXT="xpath=//*[@id='a0s21000000U35sAAC-21']/div[2]/div[3]/button | //*[@id='a0s21000000U35sAAC-21']/div[2]/div[4]/button";
		public static final String BUTTON_PREVIOUS="xpath=//*[@id='a0s21000000U35sAAC-21']/div[2]/div[4]/button";
		public static final String BUTTON_SAVEANDEXIT="id=SaveandExitButton1";
		
	}
	
	public class AdditionalEMail{
		
		public static final String RADIO_CCEMAIL="id=Additionalemailoption";
		public static final String TEXT_EMAIL="id=ccemailvalue";
		public static final String BUTTON_NEXT="xpath=//*[@id='a0s21000000U35sAAC-24']/div[2]/div[3]/button | //*[@id='a0s21000000U35sAAC-24']/div[2]/div[4]/button";
		public static final String BUTTON_PREVIOUS="xpath=//*[@id='a0s21000000U35sAAC-24']/div[2]/div[4]/button";
		public static final String BUTTON_SAVEANDEXIT="id=SaveandExitButton1";
		
	}
	
	public class ReviewYourSelection{
		
		public static final String ELE_PLAN= "Xpath=//*[@id='a0s21000000U35sAAC-27']/div[1]/div[1]/child/div/div/h1/p/label";
		public static final String ELE_EFFECTIVEDATE= "xpath=//*[@id='a0s21000000U35sAAC-27']/div[1]/div[2]/child/div/div/h1/p/label";
		public static final String ELE_FIRSTNAME= "id=R_FirstName";
		public static final String ELE_DOB= "id=R_DOB";
		public static final String ELE_GENDER= "id=R_Gender";
		public static final String ELE_CITY= "id=R_CityTown";
		public static final String ELE_STATE= "id=R_State";
		public static final String ELE_ZIP= "id=R_ZipCode";
		public static final String ELE_PCPID= "id=R_PCPId";
		public static final String ELE_REVIEPAGE= "xpath=//input[@name='loopname']" ;
		public static final String ELE_ADITIONALEMAIL= "xpath=//*[@id='R_EmailPhone']/div/div[2]/div[5]/child/div/ng-form" ;
		public static final String ELE_PERMISSIONEMAIL= "//*[@id='R_CommunicationPreferences']/div/div[2]/div[1]/child/div/ng-form" ;
		public static final String ELE_PERMISSIONMAIL= "//*[@id='R_CommunicationPreferences']/div/div[2]/div[3]/child/div/ng-form" ;
		public static final String ELE_PERMISSIONCALL= "//*[@id='R_CommunicationPreferences']/div/div[2]/div[2]/child/div/ng-form" ;
		public static final String BUTTON_NEXT= "xpath=//*[@id='a0s21000000U35sAAC-27']/div[2]/div[3]/button | //*[@id='a0s21000000U35sAAC-27']/div[2]/div[4]/button";
		//public static final String ELE_CITY= 
	}
	
	public class TermsAndConditions{
		
		public static final String CHECK_IAGREE= "id=Disclosure";
		public static final String CHECK_FOCUS_IAGREE= "id=Focus Disclosure";
		public static final String BUTTON_SUBMIT= "xpath=//button[contains(text(),'Submit Application')]";
		
	}

	public class ApplicationCompleted{
		
		public static final String LINK_MAKEPAYMENT= "id=PaymentButton1";
		public static final String ELE_INFO= "xpath=//*[@id='VlocityBPView']/div[2]/div/div[2]";
		public static final String ELE_APPLICATION_NUMBR= "xpath=//*[@id='VlocityBPView']/div[2]/div/div[2]/label";
		public static final String BUTTON_CONTINUE="xpath=//*[@id='Update Application']//button[Contains(text(),'Continue')]";
		
	}
 public class Payment{
	 public static final String ELE_WELCOMEMSG= "xpath=//span[text()='Welcome to Harvard Pilgrim Online Billing!']";
	 public static final String ELE_PAYMENTMSG="xpath=//*[@id='r1:0:pgl3']/div[3]/span";
	 public static final String ELE_REVIEWACCOUNT="xpath=//span[text()='Review Account Information']";
	 public static final String TEXT_WELCOME_COMPLETENAME="xpath=//*[@id='r1:0:pgl5']/div[2]/span";
	 public static final String TEXT_APPLICATION_ID="xpath=//label[text()='Application ID :']";
	 public static final String TEXT_FIRST_NAME="xpath=//label[text()='Subscriber First Name :']";
	 public static final String TEXT_LAST_NAME="xpath=//label[text()='Subscriber Last Name :']";
	 public static final String TEXT_EFFECTIVE_DATE="xpath=//label[text()='Coverage Effective Date :']";
	 public static final String TEXT_INITIAL_PAYMENT="xpath=//label[text()='Initial Premium :']";
	 public static final String ELE_PLAN_SELECTED="xpath=//label[text()='Plan Selected :']";
	 public static final String TEXT_APPLICATION_ID_VALUE="xpath=//*[@id='r1:0:plam1']/td[2]";
	 public static final String TEXT_FIRST_NAME_VALUE="xpath=//*[@id='r1:0:plam2']/td[2]";
	 public static final String TEXT_LAST_NAME_VALUE="xpath=//*[@id='r1:0:plam3']/td[2]";
	 public static final String TEXT_EFFECTIVE_DATE_VALUE="xpath=.//*[@id='r1:0:plam4']/td[2]";
	 public static final String TEXT_INITIAL_PAYMENT_VALUE="xpath=.//*[@id='r1:0:plam5']/td[2]";
	 public static final String TEXT_PLAN_SELECTED_VALUE="xpath=.//*[@id='r1:0:plam6']/td[2]";
	 public static final String BUTTON_PREVIOUS="xpath=//*[@id='r1:0:gb1']";
	 public static final String BUTTON_NEXT="xpath=.//*[@id='r1:0:cb1']/a/span";
 }
 
 public class PaymentOptions{
	 public static final String STATUS_TABLE= "xpath=//*[@id='r1:1:pt1:pb2::content']";
     public static final String PAYMENT_OPTIONS="xpath=//span[text()='Payment Options']";
	 public static final String PAYMENT_AMOUNT="xpath=//*[@id='r1:1:pt1:it1::content']";
	 public static final String RADIO_ELECTRONIC_CHECK="xpath=//*[@id='r1:1:pt1:sbr1::content']";
	 public static final String RADIO_MAIL_CHECK="xpath=//*[@id='r1:1:pt1:sbr3::content']";
	 public static final String ELE_PAYMENT_DATE="xpath=//*[@id='r1:1:pt1:id1::content']";
	 public static final String ELE_MAIN_PAGE="xpath=//span[text()='Return to Main Page']";
	 public static final String NEXT_BUTTON="xpath=//*[@id='r1:1:pt1:pt_cb2']";
}
 
 public class AccountDetails{ 
	 public static final String SELECT_PAYMENT_DATE="xpath=//*[@id='r1:2:pt1:id1::content']";
	 public static final String TEXT_BANK_NAME="xpath=//*[@id='r1:2:pt1:it2::content']";
	 public static final String SELECT_ACCOUNT_TYPE="xpath=.//*[@id='r1:2:pt1:soc1::content']";
     public static final String TEXT_ROUTING="xpath=.//*[@id='r1:2:pt1:routingNoId::content']";
	 public static final String BUTTON_ROUTING_SEARCH="xpath=.//*[@id='r1:2:pt1:routingNoId::lovIconId']";
	 public static final String TEXT_BANK_ACCOUNT="xpath=.//*[@id='r1:2:pt1:it4::content']";
	 public static final String TEXT_REENTER_BANK_ACCOUNT="xpath=.//*[@id='r1:2:pt1:it5::content']";
	 public static final String LINK_RETURN_HOME_PAGE="xpath=//span[text()='Return to Main Page']";
	 public static final String BUTTON_PREVIOUS="xpath=//span[text()='Previous']";
	 public static final String NEXT_BUTTON="xpath=//span[text()='Next']";
	 public static final String ROUTING_NUMBER="xpath=//*[@id='r1:2:pt1:routingNoId_afrLovInternalQueryId:val00::content']";
	 public static final String ROUTING_SEARCH_BUTTON="xpath=//*[@id='r1:2:pt1:routingNoId_afrLovInternalQueryId::search']";
	 public static final String ROUTING_SELECT_ROW="xpath=.//[@id='r1:2:pt1:routingNoId_afrLovInternalTableId::db']/table/tbody/tr/td[2]/div/table/tbody/tr/td[1]/span";
	 public static final String BUTTON_OK="xpath=//*[@id='r1:2:pt1:routingNoId_afrLovDialogId::ok']";
	  }
 
 public class MedicareAdvantageQuote{ 
	 public static final String MSG_AUDITPUROSES="xpath=//*[contains(text(), 'This call is being recorded for audit purposes is that okay with you? ')][1]";
	 public static final String MSG_CONFIRMMESSAGE="xpath=//*[contains(text(),'Please confirm the state and county you reside in. Thank you.')][1]";
	 public static final String MSG_MANDATORYMESSAGE="xpath=//*[contains(text(),'All required fields are marked with an asterisk')][1]";
	 public static final String ELEMENT_ZIPCODE="xpath=//input[@id='ZipCode']";
	 public static final String ELEMENT_YEAR="xpath=//select[@id='Year']";
	public static final String BUTTON_NEXT="xpath=//div[contains(@class,'next')]/button"; //button[contains(text(),'Next')]"; ;
}

 public class ComparePlansMedicare{
	 public static final String BUTTON_CANCEL  ="xpath=//*[@id='CancelButton']";
	 public static final String BUTTON_PREVIOUS  ="xpath=//*[contains(@class,'previous')]/button";//*[@id='a0s21000000U35sAAC-9']/div[2]/div[4]/button | //*[@id='a0s21000000KYPoAAO-5']/div[2]/div[4]/button";
	 public static final String BUTTON_NEXT= "xpath=//*[contains(@class,'next')]/button"; //*[@id='a0s21000000U35sAAC-9']/div[2]/div[3]/button  | //*[@id='a0s21000000U35sAAC-9']/div[2]/div[4]/button | //*[@id='a0s21000000IrSgAAK-5']/div[2]/div[3]/button | //*[@id='a0s21000000KYPoAAO-5']/div[2]/div[3]/button";
	 public static final String LINK_FORMULARY="xpath=//a[text()='Formulary']";
	 public static final String LINK_PROVIDERDIRECTORY="xpath=//a[text()='Provider Directory']";
     public static final String LINK_SOB="xpath=//a[text()='Summary Of Benefits']";
	 public static final String CHECKCOMPARE  ="xpath=//input[@type='checkbox']";
	 public static final String BUTTON_SELECTPLAN  ="xpath=//span[contains(text(),'Select Plan')]";
     public static final String BUTTON_COMPARE="xpath=.//*[@id='ProductCartOutput']/ng-include/div/div[1]/div[2]/div/button[text()='Compare']";
     public static final String ELEMENT_NOPRODUCT_MSG  ="xpath=//div[@class='products-container']//p[contains(text(),'No Products Available')]";
     public static final String MSG_ERROR_NOPLANSELECTED= "xpath=html/body/div[3]/div/div/div/div[2]/label[text()='Please select plans to compare.']";
     public static final String MSG_ERROR_ONEPLANSELECTED= "xpath=html/body/div[3]/div/div/div/div[2]/label[text()='Please select at least two plans to compare.']";
     public static final String MSG_ERROR_CLOSE="xpath=html/body/div[3]/div/div/div/div[1]/i";
 }
 
 public class DeliverMyQuoteMedicare{
		public static final String RADION_ONE  ="xpath=//*[@id='QuoteOptions']";		 
		public static final String BUTTON_VIEWPRINTQUOTE  ="xpath=//span[contains(text(), 'View/Print Quote')]  | //*[@id='BrokerQuoteid']/span"; //*[@id="MailBlock"]/div/div[2]/div[2]/child/div/div/div/p/p/a/span
		public static final String BUTTON_SAVEANDEXIT  ="id=SaveandExitButton";
		public static final String BUTTON_PREVIOUS  ="xpath=//*[@id='a0s21000000IrSgAAK-9']/div[2]/div[4]/button | //*[@id='a0s21000000KYPoAAO-9']/div[2]/div[4]/button";
		public static final String BUTTON_ENROLLNOW  ="xpath=//*[contains(text(), 'Enroll Now')]";
		public static final String RADIO_EMAIL="xpath=//input[@value='By Email']";
		public static final String RADIO_MAIL="xpath=//input[@value='By Mail']";
		public static final String RADIO_NONE="xpath=//input[@value='None']";
	}
	
 public class EnrollmentMedicare{
			public static final String TEXT_SALUTATION = "xpath=//*[@id='EPrefix']";
	  		public static final String TEXT_FIRST_NAME= "xpath=.//*[@id='EFirstName']";
			public static final String TEXT_MIDDLE_NAME= "xpath=.//*[@id='EMI']";
			public static final String TEXT_LAST_NAME= "xpath=.//*[@id='ELastName']";
			public static final String TEXT_SUFFIX= "xpath=.//*[@id='ESuffix']";
			public static final String RADIO_SEX= "xpath=.//*[@id='EGender']";
			public static final String TEXT_SEX_FEMALE= "xpath=.//*[@id='EGender'][@value='Female']";
			public static final String TEXT_HOME_PHONE_NUMBER= "xpath=.//*[@id='EHomePhone']";
			public static final String TEXT_ALTERNATE_PHONE_NUMBER= "xpath=.//*[@id='EAltPhone']";
			public static final String TEXT_EMAIL= "xpath=.//*[@id='EEmail']";
			public static final String SELECT_CONFIRMATION_EMAIL_YES = "xpath=.//*[@id='EConfEmail'] [@value='Yes']";
			public static final String SELECT_CONFIRMATION_EMAIL_NO = "xpath=.//*[@id='EConfEmail'] [@value='No']";
			public static final String TEXT_EMERGENCY_FIRST_NAME = "xpath=.//*[@id='EEmgFirstName']";
			public static final String TEXT_EMERGENCY_LAST_NAME = "xpath=.//*[@id='EEmgLastName']";
			public static final String TEXT_EMERGENCY_MIDDLE_NAME = "xpath=.//*[@id='EEmgMI']";
			public static final String TEXT_EMERGENCY_SUFFIX = "xpath=.//*[@id='EEmgSuffix']";
			public static final String TEXT_EMERGENCY_PHONE_NUMBER = "xpath=.//*[@id='EEmgPhoneNumber']";
			public static final String TEXT_EMERGENCY_RELATIONSHIP = "xpath=.//*[@id='EEmgRelToYou']";
			public static final String BUTTON_NEXT ="xpath=//*[@id='a0s21000000IrSgAAK-12']/div[2]/div[3]/button | //*[@id='a0s21000000KYPoAAO-12']/div[2]/div[3]/button";

		}

        public class AddressInformation {
        	public static final String TEXT_STREETADDRESS = "xpath=//*[@id='EPStreetName']";
        	public static final String RADION_MAILINGPERMAMNENTADDRESS = "xpath=//*[@id='EMailingAddSameAsPerm']";
        	public static final String TEXT_CITY ="xpath=.//*[@id='EPermanentAdd']/div/div[2]/div[3]/child/div/ng-form/label";
        	public static final String TEXT_STATE ="xpath=.//*[@id='EPermanentAdd']/div/div[2]/div[4]/child/div/ng-form/label";
        	public static final String TEXT_MAILINGSTREETADDRESS = "xpath=//*[@id='EMStreetName']";
        	public static final String TEXT_MAILINGZIP = "xpath=//*[@id='EMZipCode']";
        	public static final String TEXT_COUNTRY ="xpath=//*[@id='EPermanentAdd']/div/div[2]/div[5]/child/div/ng-form/label";
        	public static final String BUTTON_NEXT ="xpath=.//*[@id='a0s21000000IrSgAAK-13']/div[2]/div[3]/button | //*[@id='a0s21000000KYPoAAO-13']/div[2]/div[3]/button";
        }
    
        public class MedicareInformationNumber {
        	public static final String TEXT_MEDICARE_CLAIM_NUMBER = "xpath=.//*[@id='MClaimNo']";
 }

        public class AutoPay {
            public static final String SELECT_AGREE_AUTOPAY_AGREEMENT = "xpath=.//*[@id='r1:3:pt1:sbc1::content']";
            public static final String BUTTON_NEXT = "xpath=.//*[@id='r1:3:pt1:pt_cb2']/a";
     }

     public class PaymentReviewPage {
            public static final String TEXT_PAYMENT_HEADER = "xpath=.//*[@id='r1:4:pt1:pgl16']/tbody/tr/td/span[text()='Review Payment']";
            public static final String TEXT_SHOW_COVERGARE_DETAILS = "xpath=.//*[@id='r1:4:pt1:sd1::_afrDscl']";
            public static final String TEXT_COVERGARE_DETAILS = "xpath=.//*[@id='r1:4:pt1:sd1::_afrDscl']/img";
            public static final String LINK_RETURN_HOME_PAGE = "xpath=.//*[@id='r1:4:pt1:cl1']";
            public static final String BUTTON_PREVIOUS = "xpath=.//*[@id='r1:4:pt1:pt_cb1']";
            public static final String BUTTON_SUBMIT = "xpath=.//*[@id='r1:4:pt1:pt_cb2']/a";
     }
     
     public class PaymentConfrimationPage {
            public static final String TEXT_PAGE_HEADER = "xpath=//*[@id='r1:5:pt1:pgl6']/div[1]/span[text()='Payment Confirmation']";
            public static final String BUTTON_NEXT = "xpath=//span[text()='Next']";
            public static final String BUTTON_PRINT = "xpath=//span[text()='Print']";
            public static final String LINK_RETURN_HOME_PAGE = "xpath=//span[text()='Return to Main Page']";
     }

    public class MedicareInsuranceInformation {
    	public static final String TEXT_NAME ="xpath=//*[@id='MName']";
    	public static final String TEXT_CLAIMNO = "xpath=//*[@id='MClaimNo']";
    	public static final String SELECT_GENDER = "xpath=.//*[@id='MSex']";
    	public static final String SELECT_GENDER_FEMALE = "xpath=.//*[@id='MSex'][@value='Female']";
    	public static final String ELE_PARTA_DATE ="xpath=//*[@id='MPartADate']";
    	public static final String ELE_PARTB_DATE = "xpath=//*[@id='MPartBDate']";
    	public static final String MSG_INVALID_CLAIM_ERROR= "xpath=//small[@class='prop-error form-control-feedback ng-binding'][contains(text(),'Invalid Health Care Number')]";//*[@id='a0s21000000IrSgAAK-15']/div[1]/div[6]/child/div/ng-form/div/div[2]/small[contains(text(),'Invalid Health Care Number')]";
    	public static final String BUTTON_NEXT = "xpath=//*[@id='a0s21000000IrSgAAK-15']/div[2]/div[3]/button[contains(text(),'Next')] | //*[@id='a0s21000000KYPoAAO-15']/div[2]/div[3]/button";
    	public static final String MSG_INVALID_EFFECTIVE_DATE= "xpath=//*[@id='a0s21000000IrSgAAK-15']/div[1]/div[12]/child/div/ng-form/div/div[1]/small[contains(text(),'Part A and Part B effective date can only be the first of any month')]";
    }
    
    public class PayingPremium {
    	public static final String MSG_PREMIUM_HEADER ="xpath=//*[@id='PaymentInfo']/div[1]/span[1]/span[contains(text(),'Paying Your Plan Premium')]";
    }
    
    
    }

