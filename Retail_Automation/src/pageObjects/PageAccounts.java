
package pageObjects;

public class PageAccounts {

	public static final String ELE_VERIFY_AT_ELEMENT    = "name=new";
	public static final String BUTTON_NEW  ="name=new";
	public static final String TEXT_ACCOUNT_MAILADDRESS  ="id=PersonMailingAddressstreet";
	public static final String TEXT_ACCOUNT_RESIDENTADDRESS  ="id=PersonOtherAddressstreet";

	public class SelectAccount
	{
		public static final String ELE_ACCOUNT_NAME= "xpath=//*[@id='bodyCell']/div[3]/div[1]/div/div[2]/table/tbody/tr[2]/th/a";
	}

	public class Account_NewOpportunity{
		public static final String VERIFY_PAGE = "xpath = //*[@name='invite_to_event_campaign']";
		public static final String CHECK_SELECT_OPPORTUNITY = "xpath = //span[text()='Opportunities']";
		public static final String BUTTON_NEW_OPPORTUNITY = "xpath = //*[@title='New Opportunity']";
	}
	
	public class Account_Opportunity_Recordtype{
		public static final String ELE_VERIFY_PAGE_ELEMENT = "xpath = //*[@id='ep']";
		public static final String SELECT_RECORD_TYPE = "xpath = .//*[@id='p3']";
		public static final String ELE_SELECT_RECORD_TYPE = "xpath = .//*[@id='p3']/option[text()='Individual']";
		public static final String BUTTON_CONTINUE = "xpath = .//*[@title='Continue']";
	}
	
	public class Account_Opportunity_Edit{
		public static final String BUTTON_SAVE = "xpath = .//*[@id='bottomButtonRow']/input[1]";
		public static final String BUTTON_SAVE_NEW = "xpath = .//*[@id='bottomButtonRow']/input[2]";
		public static final String BUTTON_CANCEL = "xpath = .//*[@id='bottomButtonRow']/input[3]";
	}

}

