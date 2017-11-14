
package utility;


import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;



public class ExcelUtils 
{
	
	  public static Map<String, String> ReadExcelRowByIndexValue(String FileName, String SheetName,String IndexValue) {
		    Log.info("Reading Excel Data from file:" + FileName + " Sheet:" + SheetName + " With Index value:" + IndexValue);
		    Map<Integer, List<String>> data = new HashMap<Integer, List<String>>();
	        Map<String, String> dictionary = new HashMap<String, String>();
		    try{
	        FileInputStream inputStream = new FileInputStream(new File(FileName));
	        Workbook workbook = new XSSFWorkbook(inputStream);
	        Sheet firstSheet = workbook.getSheet(SheetName);
	        Iterator<Row> iterator = firstSheet.iterator();
	        int rowCnt = 0;
	        while (iterator.hasNext()) { 
	            Row nextRow = iterator.next();
	            Iterator<Cell> cellIterator = nextRow.cellIterator();
	            List<String> obj = new ArrayList<String>();
	            while (cellIterator.hasNext()) {
	                Cell cell = cellIterator.next();
	                String cellobj = cell.toString();
	                if (cellobj.equals(" ")) {
	                    obj.add("");
	                } else if (cellobj.equals(null)) {
	                    obj.add("");
	                } else {
	                    obj.add(cell.toString());
	                }

	            }

	            data.put(rowCnt, obj);
	            rowCnt++;

	        }
	        workbook.close();
	        String RowData = null;
	    	
	    	String Columns = data.get(0).toString();
	    	for (int i=1; i<data.size(); i++){
				if (data.get(i).toString().contains(IndexValue))
						{
					RowData = data.get(i).toString();
					break;
						}
		    }
	    	
	    	String [] ColumnsNames = Columns.replace("[", "").replace("]", "").split(",");
	    	String [] ColumnsValues = RowData.replace("[", "").replace("]", "").split(",");
	    	
	    	for (int j=0; j<ColumnsNames.length; j++){
	    		dictionary.put(ColumnsNames[j].trim(), ColumnsValues[j].trim());
	    	}
	    	
		  }
	  catch(Exception e){
		  
		  Log.error("Exception occured while reading Excel Data. Exception:" + e.getMessage());
		  
	  }
		    return dictionary;
}
}
