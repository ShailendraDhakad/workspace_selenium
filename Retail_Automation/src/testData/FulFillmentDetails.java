package testData;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import appModules.SFDCLeads;
import utility.Log;
import utility.StringUtil;

public class FulFillmentDetails {
	String Fulfillment_Reason;
	String fulfillmentStatus;
	String fulfillmentRequestType;

	public String getFulfillment_Reason() {
		return Fulfillment_Reason;

	}

	public void setFulfillment_Reason(String Reason) {

		if (Reason.equals("Dynamic")) {
			Fulfillment_Reason = StringUtil.generateRandomString(7);
		} else {
			Fulfillment_Reason = Reason;
		}
	}

	public String getFulfillmentStatus() {
		return fulfillmentStatus;
	}

	public void setFulfillmentStatus(String status) {
		String fStatus[] = { "CMAPS Completed", "CMAPS On Hold", "CMAPS Cancelled", "Requests sent to CMAPS" };
		if (status.equals("Dynamic")) {
			List<String> fStatusTypeList = Arrays.asList(fStatus);
			Collections.shuffle(fStatusTypeList);
			fulfillmentStatus = fStatusTypeList.get(0);
		} 
		else {
			fulfillmentStatus = status;
		}

	}

	public String getFulfillmentRequestType() {
		return fulfillmentRequestType;
	}

	public void setFulfillmentRequestType(String requestType) {
		String fRequests[] = { "All", "Individual Cancelled Requests", "Individual Completed Requests",
				"Individual On Hold Requests", "Individual Pending Requests", "Medicare Cancelled Requests",
				"Medicare Completed Requests", "Medicare On Hold Requests", "Medicare Pending Requests",
				"Medicare Pending Requests for me" };
		if (requestType.equals("Dynamic")) {
			List<String> fRequestTypeList = Arrays.asList(fRequests);
			Collections.shuffle(fRequestTypeList);
			fulfillmentRequestType = fRequestTypeList.get(0);
		} else {
			fulfillmentRequestType = requestType;
		}

	}
}
