package utilities;

import java.io.FileInputStream;
import java.util.Properties;

public class ReadConfig {
	
	Properties properties;
	String path="config.properties";
	
	
public ReadConfig() throws Exception 
//Constructor created and while using constructor dont used void after public e.g public void.
{
	properties = new Properties();
	FileInputStream fis = new FileInputStream(path);
	properties.load(fis);
	
}

//user defined method

public String getBrowser() {
	
	String value=properties.getProperty("browser"); //value = firefox, becasue if set firefox in read config then that perticular browser will open.
	
	if (value!=null) {
		
		return value;
	} else
	{
		throw new RuntimeException("URL is not found in config file");
		
	}
	
	
}




}
