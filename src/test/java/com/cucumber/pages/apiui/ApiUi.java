package com.cucumber.pages.apiui;

import java.util.List;

public class ApiUi {
	
	private List<String> apiHeaders;
    private List<String> uiHeaders;
    
	public List<String> getApiHeaders() {
		return apiHeaders;
	}
	public void setApiHeaders(List<String> apiHeaders) {
		this.apiHeaders = apiHeaders;
	}
	public List<String> getUiHeaders() {
		return uiHeaders;
	}
	public void setUiHeaders(List<String> uiHeaders) {
		this.uiHeaders = uiHeaders;
	}
     
}
