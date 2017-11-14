package utility;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;
import java.io.*;
import org.w3c.dom.Document;


public class XMLUtil {

	public static String getXMLValue(String fileName, String expression)
	{
		String sXpathvalue = null;

		try{

			Log.info("Reading XML Value for Xpath: " +  expression + " From File: " + fileName);
			DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance(); 
			InputStream inputStream = new FileInputStream(new File(fileName)); 
			org.w3c.dom.Document doc = builderFactory.newDocumentBuilder().parse(inputStream); 
			StringWriter stw = new StringWriter(); 
			Transformer serializer = TransformerFactory.newInstance().newTransformer(); 
			serializer.transform(new DOMSource(doc), new StreamResult(stw)); 
			DocumentBuilder builder = null;
			builder = builderFactory.newDocumentBuilder();
			Document xmlDocument = builder.parse(new ByteArrayInputStream(stw.toString().getBytes()));
			XPath xPath =  XPathFactory.newInstance().newXPath();
			//String expression = "/Entities/Entity/Fields/Field[@Name='hierarchical-path']/Value";
			//read a string value
			sXpathvalue = xPath.compile(expression).evaluate(xmlDocument);

		}
		catch(Exception ex)
		{
			Log.error(" Exception occured while reading XML Value:" + ex.toString());

		}

		return sXpathvalue;



	}
}
