package pageObjects;

public class PageAdminTool {
	
	    public static final String TEXT_USERNAME	=  "id=P101_USERNAME";
	    public static final String TEXT_PASSWORD	= "id=P101_PASSWORD";
	    public static final String BUTTON_LOGIN		= "xpath=//*[@id=\"apex_layout_328137400289283815\"]/tbody/tr[2]/td[3]/button/span";
	    public static final String TEXT_MESSAGE 	= "xpath=//*[@id=\"R328209613792957559\"]/div[2]/div/div[2]/center/font";
	 
public class PageAdminHome	{ 
	
		public static final String Admin_Link  		="xpath=//*[@id=\"uHeader\"]/nav/ul/li[2]/a";
		public static final String Bulk_Load_SBC_XML_Repository = "xpath=//*[@id=\"R7132517742901982\"]/div[2]/ul/li[17]/a";
		public static final String SBC_PDF_Repository = "xpath =//*[@id=\"R7132517742901982\"]/div[2]/ul/li[14]/a";
}

public class PagePDFRepository {
	
	public static final String AlwaysUseThisSBC_dropDown = "id=P71_NEW_USAGE";
	public static final String Search_Box ="id=apexir_SEARCH";
	public static final String Go_button= "xpath=//*[@id=\"apexir_btn_SEARCH\"]";
	public static final String NO_DATA_FOUND = "id=apexir_NO_DATA_FOUND_MSG";
	public static final String FILELINK= "xpath=//*[@id=\"183904407096210439\"]/tbody/tr[2]/td[24]/a";
	
}
}	    
	