package com.symbol.lyj.test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jasig.cas.client.authentication.AttributePrincipal;
import org.jose4j.json.internal.json_simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;




@Controller
public class Test4ClientRestful {
	
	private static final String TGC = "TGC";
	
	@ResponseBody
	@RequestMapping(value="/api/rest")
	public void testAPI(HttpServletRequest request,HttpServletResponse response){
		
		Map<String, String> hashMap = new HashMap<String,String>();
		hashMap.put("data", "symbol");
		hashMap.put("status", "200");
		hashMap.put("reaction", "OK");
/*		final AttributePrincipal principal = assertion.getPrincipal();
		final String id = principal.getName();
		final Map<String, Object> attributes = principal.getAttributes();*/
		
		 AttributePrincipal userPrincipal = (AttributePrincipal) request.getUserPrincipal();
		//username
		String userName = userPrincipal.getName();
		Map<String, Object> attributes = userPrincipal.getAttributes();
		hashMap.put("attributes", attributes.toString());
		

		String mobilePhone=(String) attributes .get("mobilePhone");
		String mobile_phone=(String) attributes .get("mobile_phone");

		hashMap.put("mobilePhone", mobilePhone);
		hashMap.put("mobile_phone", mobile_phone);
		
		/*for (Entry<String, Object> entrySet : attributes.entrySet()) {
			hashMap.put(entrySet.getKey(), (String) entrySet.getValue());
		}*/			
		
		String parameter = request.getParameter("jsessionid");
		
		hashMap.put("userName", userName);
		hashMap.put("parameter", parameter);
		
		
		
		JSONObject jsonObject = new JSONObject();
		
		jsonObject.putAll(hashMap);
		
		try {
			response.getWriter().write(jsonObject.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	
	
	@RequestMapping(value="/api/test")
	public String test(HttpServletRequest request,HttpServletResponse response){
		
		System.out.println(request.getPathInfo());
		
		return "back_page/test";
		
	}

	@RequestMapping(value="/api/testPath")
	public String testPath(HttpServletRequest request,HttpServletResponse response){
		
		System.out.println(request.getPathInfo());
		
		return "back_page/test";
		
	}

}
