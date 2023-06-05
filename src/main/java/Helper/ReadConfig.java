package Helper;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.io.FileInputStream;
import java.io.IOException;
import Helper.ReadConfig;

public class ReadConfig {
    public static Properties prop;
	public static void initializePropertyFile() throws IOException {
	    prop = new Properties();
		FileInputStream fis = new FileInputStream("Config\\config.properties");
		prop.load(fis);
		

	}
	
	

}
