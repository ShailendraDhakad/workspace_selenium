package pageObjects;

public class PageSFDC {
	
	 	public static final String VERIFY_AT_ELEMENT    = "id=username";
	    public static final String TEXT_USERNAME	= "id=username";
	    public static final String TEXT_PASSWORD	 	= "id=password";
	    public static final String BUTTON_LOGIN		= "id=Login";
	    public static final String CHECK_REMEMBERME		= "id=rememberUn";
	    public static final String LINK_FORGOTPASSWORD		=  "id=forgot_password_link";
	    public static final String LINK_USECUTOMDOMAIN		=  "id=mydomainLink";
	    public static final String LINK_ClEAR_USERNAME = "id=clear_link";
	    public static final String LINK_LOGOUT            =  "id('userNav-menuItems')/x:a[3]";
	    
	    public class Menu{
	    	public static final String MENU_HOME  ="xpath=//a[contains(text(),'Home')]";
	    	public static final String MENU_PERSONSEARCH  ="xpath=//*[@id='01r1a000000A9eL_Tab']/a[text()='Person Search']";
	    	public static final String MENU_LEADS  ="xpath=//a[contains(text(),'Leads')]";
	    	public static final String MENU_ACCOUNTS  ="xpath=//a[contains(text(),'Accounts')]";
	    	public static final String MENU_OPPORTUNITIES  ="xpath=//a[contains(text(),'Opportunities')]";
	    	public static final String MENU_APPLICATIONS  ="xpath=//a[contains(text(),'Applications')]";
	    	public static final String MENU_CAMPAIGNS  ="xpath=//a[contains(text(),'Campaigns')]";
	    	public static final String MENU_REPORTS  ="xpath=//a[contains(text(),'Reports')]";
	    	public static final String MENU_DASHBOARDS  ="xpath=//a[contains(text(),'Dashboards')]";
	    	


	    }
	    
	    public class Quote{
	    	public static final String LINK_SEARCH_QUOTE_NAME="xpath=//div[@class='listRelatedObject quoteBlock']//div[2]/table/tbody/tr[2]/th/a";
	    	public static final String BUTTON_MAILEMAILQUOTE="xpath=//*[@class='pbHeader']//*[@value='Mail/Email Quote']";
	    	public static final String BUTTON_SAVE_MAIL_QUOTE="xpath=//*[@name='saveAndMail']";
	    	public static final String BUTTON_SAVE_EMAIL_QUOTE="xpath=//*[@name='saveAndEmail']";
	    	public static final String BUTTON_CANCEL="xpath=//*[@name='cancel']";
	    	
	    	
    
	    }
	    
	    public class FulfillmentKit{
	    	public static final String BUTTON_SUBMIT="xpath=//input[@value='Submit']";
	    	public static final String BUTTON_CANCEL="xpath=//input[@value='Cancel']";
	    	public static final String BUTTON_CANCELREQUEST ="xpath =//*[@value='Cancel Request']";
	    	public static final String ELE_FULFILLMENT_MEMBERNAME="xpath=//*[@id='page:frm:pb:pblock1']//tbody/tr[1]/td[1]";
	    	public static final String TEXT_FULFILLMENT_STREET="xpath=//*[@id='page:frm:pb:pblock2']//tbody/tr[1]/td[1]//input";
	    	public static final String TEXT_FULFILLMENT_ZIP="xpath=//*[@id='page:frm:pb:pblock2']//tbody/tr[2]/td[1]//input";
	    	public static final String SELECT_KIT_TYPE_1="xpath=//*[@id='page:frm:pbwrpr']//tbody/tr[1]/td[1]//tbody/tr[1]/td[1]//Select";
	    	public static final String SELECT_KIT_NAME_1="xpath=//*[@id='page:frm:pbwrpr']//tbody/tr[1]/td[1]//tbody/tr[1]/td[2]//Select";
	    	public static final String TEXT_KIT_QUANTITY_1="xpath=//*[@id='page:frm:pbwrpr']//tbody/tr[1]/td[1]//tbody/tr[1]/td[3]//Input";
	    	public static final String SELECT_KIT_TYPE="xpath=//*[@id='page:frm:pbwrpr']//tbody/tr[1]/td[1]//tbody/tr[RowIndex]/td[1]//Select";
	    	public static final String SELECT_KIT_NAME="xpath=//*[@id='page:frm:pbwrpr']//tbody/tr[1]/td[1]//tbody/tr[RowIndex]/td[2]//Select";
	    	public static final String TEXT_KIT_QUANTITY="xpath=//*[@id='page:frm:pbwrpr']//tbody/tr[1]/td[1]//tbody/tr[RowIndex]/td[3]//Input";
	    	public static final String SELECT_EDITKIT_TYPE="xpath=//*[contains(@name,'KitType')]";
	    	public static final String SELECT_EDITKIT_NAME="xpath=//*[contains(@name,'KitName')]";
	    	public static final String TEXT_EDITKIT_QYANTITY="xpath=//*[@class='list']//tbody/tr[1]/td[3]//input";
	    	public static final String TEXT_KIT_COMMENT="xpath=//*[@class='data2Col  first  last ']/textarea";
	    	public static final String BUTTON_DELETE_1="xpath=//*[@id='page:frm:pbwrpr']//tbody/tr[1]/td[1]//tbody/tr[1]/td[4]//Input";
	    	
	    	public static final String TABLE_FULFILLMENT_DETAIL="xpath=//*[@class='pbSubsection']//tbody";
	    	public static final String ELE_FULFILLMENTKIT_FULFILLMENT_ID="xpath=//*[@class='pbSubsection']//tbody/tr[1]/td[1]";
	    	public static final String ELE_FULFILLMENTKIT_TYPE="xpath=//*[@class='pbSubsection']//tbody/tr[1]/td[2]";
	    	public static final String ELE_FULFILLMENTKIT_LEAD_NAME="xpath=//*[@class='pbSubsection']//tbody/tr[2]/td[1]";
	    	public static final String ELE_FULFILLMENTKIT_STATUS="xpath=//*[@class='pbSubsection']//tbody/tr[2]/td[2]";
	    	public static final String ELE_FULFILLMENTKIT_COMMENTS="xpath=//*[@class='pbSubsection']//tbody/tr[6]/td[2]";
	    	public static final String TABLE_FULFILLMENT_KIT_DETAIL="xpath=//*[@id='page:frm:j_id127']//*[@class='pbBody']//tbody";
	    	
	    }
	    
	    
	    public static final String BUTTON_PREVIOUS  ="xpath=//*[contains(@class,'previous')]/button";
	    public static final String BUTTON_NEXT="xpath=//div[contains(@class,'next')]/button";
	    public static final String BUTTON_CANCEL  ="xpath=//button[@id='SaveandExitButton2']";

}
