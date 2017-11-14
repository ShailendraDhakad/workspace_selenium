package pageObjects;

public class PageSFDCHome {
	
	public static final String VERIFY_AT_ELEMENT    = "id=db_ref_btn";
	
	public class MyTask{
		public static final String SELECT_MYTASKLIST="xpath=//*[@id='tasklist_mode']";
		public static final String TABLE_MYTASK="xpath=//*[@id='actionForm']//table[@class='list']/tbody";
		public static final String LINK_MYTASK_FULFILLMENT="xpath=//*[@id='actionForm']//table[@class='list']/tbody/tr[RowNum]/td[3]/a";
	}
	
	public class TaskFulfillment{
		public static final String ELE_FULFILLMENT_SUBJECT="xpath=//*[@id='tsk5_ileinner']";
		public static final String ELE_FULFILLMENT_COMMENTS="xpath=//*[@id='tsk6_ileinner']";
	}
}
