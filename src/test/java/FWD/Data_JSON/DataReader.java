package FWD.Data_JSON;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DataReader {
	
	public List<HashMap<String,Object>> jsonDataToMap() throws IOException {
	//Convert json to String
	String jsonContent=	FileUtils.readFileToString(new File(System.getProperty("user.dir")+"\\src\\test\\java\\FWD\\Data_JSON\\PurchaseOrder.json"), StandardCharsets.UTF_8);
	//String to HashMap
	ObjectMapper mapper = new ObjectMapper();
	List<HashMap<String,Object>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, Object>>>(){
	});
	//Return List of Data
	return data;
		
	}

}
