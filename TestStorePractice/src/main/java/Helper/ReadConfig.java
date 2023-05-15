package Helper;

import java.util.Properties;
import java.io.FileInputStream;
import java.io.IOException;

public class ReadConfig {
    public static Properties prop;
	public static void initializePropertyFile() throws IOException {
	    prop = new Properties();
		FileInputStream fis = new FileInputStream("Config\\config.properties");
		prop.load(fis);
		

	}
	

}
