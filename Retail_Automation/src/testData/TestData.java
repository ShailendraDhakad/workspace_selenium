package testData;

import java.util.HashMap;

import com.sun.jmx.snmp.Timestamp;


public class TestData {

	public static void main(String[] args) {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		//
		String TimeStamp=String.valueOf(timestamp.getDateTime());
		
		System.out.println(timestamp.getSysUpTime());
		String temp="ABCDEFGH";
		String SuTemp=temp.substring(temp.length()-3);
		System.out.println(SuTemp);
		
		LeadData objLeadData = new LeadData();
		java.lang.reflect.Field[] fld = LeadData.class.getDeclaredFields();
		
		
		int TotalVariables=fld.length;
		
//		for (int i=0;i<TotalVariables;i++)
//		{
//			LeadDataMap.put(fld[i].getName().toString(), "");
//		}
//		
		
		objLeadData.setFirstName("Dynamic");
		objLeadData.setDateOfBirth("Dynamic", 55);
		objLeadData.getFirstName();
		
		//LeadDataMap.put("Zip", objLeadData.getZip());
		System.out.println(objLeadData.getDateOfBirth());
		System.out.println(objLeadData.getFirstName().toUpperCase());
		
	}

}
