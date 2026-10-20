package com.rahushettyacademy.data;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;

import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

public class DataReader {
	
	public List<HashMap<String, String>> getJsonDatatoMap() throws IOException {
		
		//read json to string
		String jsoncontent=FileUtils.readFileToString(new File(System.getProperty("user.dir")+"src\\test\\java\\com\\rahushettyacademy\\data\\PurchaseOrder.json"),
				StandardCharsets.UTF_8);
		
		//convert string to hashmap- ise jackson databind utility dependency
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String,String>> data=mapper.readValue(jsoncontent, new TypeReference<List<HashMap<String,String>>>(){});
		return data;
		
	}

}
