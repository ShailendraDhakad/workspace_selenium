package testData;

import utility.StringUtil;

public class ApplicationAssistantData {
	
	String ApplicationAssistantOption;
	String AgentName;
	String AgentAgency;
	String AgentLastName;
	String AgentID;
	String NPN;
	String AuthRepName;
	String AuthRepPhnone;
	String AuthRepAddress;
	String AuthRepRelation;
	String ApplicationEntry;
	
	
	
	public String getApplicationAssistantOption() {
		return ApplicationAssistantOption;
	}
	public void setApplicationAssistantOption(String applicationAssistantOption) {
		ApplicationAssistantOption = applicationAssistantOption;
	}
	
	
	public String getAgentName() {
		return AgentName;
	}
	public void setAgentName(String agentName) {
		
		if (agentName.equals("Dynamic"))
			AgentName = StringUtil.generateRandomString(6) ;
		else
			AgentName = agentName;
	}
	
	
	public String getAgentAgency() {
		return AgentAgency;
	}
	public void setAgentAgency(String agentAgency) {
		if (agentAgency.equals("Dynamic"))
			AgentAgency = StringUtil.generateRandomString(6) ;
		else
			AgentAgency = agentAgency;
	}
	
	
	public String getAgentLastName() {
		return AgentLastName;
	}
	public void setAgentLastName(String agentLastName) {
		if (agentLastName.equals("Dynamic"))
			AgentLastName = StringUtil.generateRandomString(3) ;
		else
			AgentLastName = agentLastName;
	}
	
	
	public String getAgentID() {
		return AgentID;
	}
	public void setAgentID(String agentID) {
		if (agentID.equals("Dynamic"))
			AgentID = StringUtil.generateID(5) ;
		else
			AgentID = agentID;
	}
	
	
	public String getNPN() {
		return NPN;
	}
	public void setNPN(String nPN) {
		if (nPN.equals("Dynamic"))
			NPN = StringUtil.generateID(5) ;
		else
			NPN = nPN;
	}
	
	
	public String getAuthRepName() {
		return AuthRepName;
	}
	public void setAuthRepName(String authRepName) {
		if (authRepName.equals("Dynamic"))
			AuthRepName = StringUtil.generateRandomString(5) ;
		else
			AuthRepName = authRepName;
	}
	
	
	public String getAuthRepPhnone() {
		return AuthRepPhnone;
	}
	public void setAuthRepPhnone(String authRepPhnone) {
		if (authRepPhnone.equals("Dynamic"))
			AuthRepPhnone = StringUtil.randomNumber(10) ;
		else
			AuthRepPhnone = authRepPhnone;
	}
	
	
	public String getAuthRepAddress() {
		return AuthRepAddress;
	}
	public void setAuthRepAddress(String authRepAddress) {
		if (authRepAddress.equals("Dynamic"))
			AuthRepAddress = StringUtil.randomNumber(3) + "," + StringUtil.generateRandomString(5) ;
		else
			AuthRepAddress = authRepAddress;
	}
	
	
	public String getAuthRepRelation() {
		return AuthRepRelation;
	}
	public void setAuthRepRelation(String authRepRelation) {
		if (authRepRelation.equals("Dynamic"))
			AuthRepRelation = StringUtil.generateRandomString(5) ;
		else
			AuthRepRelation = authRepRelation;
	}
	
	
	public String getApplicationEntry() {
		return ApplicationEntry;
	}
	public void setApplicationEntry(String applicationEntry) {
		
		ApplicationEntry = applicationEntry;
	}
}
