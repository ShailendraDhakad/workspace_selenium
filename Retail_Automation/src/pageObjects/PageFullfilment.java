package pageObjects;

public class PageFullfilment {
	
	public static final String ELE_VERIFY = "xpath = .//*[@title='New Task']";
	public static final String ELE_FULLFILLMENT_TAB = "xpath = .//*[starts-with(@title,'Fulfillment Requests Tab')]"; //input[starts-with(@id, 'activation:') and contains(@id, ':voId:1')]
	public static final String TEXT_FULLFILLMENT_SERACH_INPUT = "xpath = .//*[@id='phSearchInput']";
	public static final String BUTTON_FULLFILLMENT_SEARCH = "xpath =  .//*[@id='phSearchButton']";
	
	public class FullfillmentRequests{
		public static final String ELE_VERIFY_FULLFILMENT_REQUESTS = "xpath = .//*[@class='pageDescription']"; 
		public static final String SELECT_FULLFILLMENT_VIEW = "xpath = .//*[@id='fcf']";
		
		public static final String SELECT_FULLFILLMENT_VIEW_OPTION_1 = "XPATH = .//*[@id='fcf']/option[text()='Individual Cancelled Requests']";
		public static final String SELECT_FULLFILLMENT_VIEW_OPTION_2 = "XPATH = .//*[@id='fcf']/option[text()='Individual Completed Requests']";
		public static final String SELECT_FULLFILLMENT_VIEW_OPTION_3 = "XPATH = .//*[@id='fcf']/option[text()='Individual On Hold Requests']";
		public static final String SELECT_FULLFILLMENT_VIEW_OPTION_4 = "XPATH = .//*[@id='fcf']/option[text()='Individual Pending Requests']";
		public static final String SELECT_FULLFILLMENT_VIEW_OPTION_5 = "XPATH = .//*[@id='fcf']/option[text()='Medicare Cancelled Requests']";
		public static final String SELECT_FULLFILLMENT_VIEW_OPTION_6 = "XPATH = .//*[@id='fcf']/option[text()='Medicare Completed Requests']";
		public static final String SELECT_FULLFILLMENT_VIEW_OPTION_7 = "XPATH = .//*[@id='fcf']/option[text()='Medicare On Hold Requests']";
		public static final String SELECT_FULLFILLMENT_VIEW_OPTION_8 = "XPATH = .//*[@id='fcf']/option[text()='Medicare Pending Requests']";
		public static final String ELE_FULLFILLMENT_REQUEST_CREATED = "xpath = //*[@scope='row']/a[contains(text(), 'FR')]";
		public static final String ELE_FULLFILLMENT_SERACH_CHECK = "XPATH = .//*[@id='Fulfillment_Request__c_body']/table/tbody/tr[2]/th/a";
		public static final String ELE_FULLFILLMENT_STATUS = "xpath = .//*[@id='page:frm:j_id105:j_id110:j_id116']";
		public static final String DROPDOWN_FULLFILLMENT_VIEW = "xpath = .//*[@title='View:']";
		public static final String DROPDOWN_FULLFILLMENT_VIEW_OPTION = "XPATH = .//*[@title='View:']/option[text()='All']";
		public static final String ELE_FULLFILLMENT_VIEW_OPTION_1 = "XPATH = .//*[@title='View:']/option[text()='Individual Cancelled Requests']";
		public static final String ELE_FULLFILLMENT_VIEW_OPTION_2 = "XPATH = .//*[@title='View:']/option[text()='Individual Completed Requests']";
		public static final String ELE_FULLFILLMENT_VIEW_OPTION_3 = "XPATH = .//*[@title='View:']/option[text()='Individual On Hold Requests']";
		public static final String ELE_FULLFILLMENT_VIEW_OPTION_4 = "XPATH = .//*[@title='View:']/option[text()='Individual Pending Requests']";
		public static final String ELE_FULLFILLMENT_VIEW_OPTION_5 = "XPATH = .//*[@title='View:']/option[text()='Medicare Cancelled Requests']";
		public static final String ELE_FULLFILLMENT_VIEW_OPTION_6 = "XPATH = .//*[@title='View:']/option[text()='Medicare Completed Requests']";
		public static final String ELE_FULLFILLMENT_VIEW_OPTION_7 = "XPATH = .//*[@title='View:']/option[text()='Medicare On Hold Requests']";
		public static final String ELE_FULLFILLMENT_VIEW_OPTION_8 = "XPATH = .//*[@title='View:']/option[text()='Medicare Pending Requests']";
		public static final String ELE_FULLFILLMENT_VIEW_OPTION_9 = "XPATH = .//*[@title='View:']/option[text()='Medicare Pending Requests for me']";
		
		public static final String BUTTON_GO = "xpath = //*[@name='go']";
		public static final String BUTTON_CHANGE_OWNER = "xpath = //*[@value='Change Owner']";
		public static final String BUTTON_SAVE = "xpath =//*[@id='saveButton']";
		public static final String BUTTON_REFRESH = "xpath =//*[@title='Refresh needed']";
		public static final String ELE_FULLFILLMENT_FIRST_REQUEST_STATUS = "xpath =//*[@id='ext-gen11']//div[1]/table/tbody/tr[1]/td[6]";
		public static final String ELE_MEDICARE_FULLFILLMENT_FIRST_REQUEST_STATUS = "xpath =//*[@id='ext-gen11']//div[1]/table/tbody/tr[1]/td[7]";
		public static final String ELE_FULLFILLMENT_FIRST_REQUEST_ID = "xpath =//*[@id='ext-gen11']//div[1]/table/tbody/tr[1]/td[4]";
		public static final String LINK_FULLFILLMENT_FIRST_REQUEST_ID = "xpath =//*[@id='ext-gen11']//div[1]/table/tbody/tr[1]/td[4]//a";
		public static final String SELECT_FULLFILLMENT_EDIT_STATUS = "xpath =//*[@id='massEditFieldDiv']//select";
		public static final String CHECKBOX_ALL = "xpath =//*[@id='allBox']";
		public static final String RADIO_MASS_APPLY = "xpath =//*[@name='massOrSingleEdit']";
		public static final String TABLE_FULLFILLMENT_REQUEST = "xpath =//*[@id='ext-gen11']//div/table/tbody";
		public static final String TABLE_DYNAMIC_FULLFILLMENT_REQUEST = "xpath =//*[@id='ext-gen11']//div[index]/table/tbody";
		
	}
	
	public class FullfillmentDetail{
		public static final String TABLE_FULLFILMENT_DETAIL = "xpath = //*[@class='pbSubsection']/table/tbody"; 
					
	}
	
	public class Fullfillment_Edit_options{
		public static final String BUTTON_EDIT = "xpath =.//*[contains(@value,'Edit')]"; 
		public static final String BUTTON_SUBMIT ="xpath = .//*[@value='Submit']";
		public static final String BUTTON_CANCEL ="xpath = .//*[@value='Cancel']";
		
		public static final String SELECT_FULLFILMENT_STATUS = "xpath = //*[@title='PickList1']"; 
		public static final String ELE_UPS_TRACKING_NUMBER ="xpath = .//*[@id='page:frm:j_id59:j_id65:j_id78']";
		public static final String TEXT_FULLFILMENT_REASON = "xpath = //*[@class='pbSubsection']/table/tbody/tr[8]/td[1]/input"; 
		
		public static final String ELE_CMAPS_COMMENTS = "xpath = .//*[@id='page:frm:j_id59:j_id65:j_id80']";
	}
	
	
}
