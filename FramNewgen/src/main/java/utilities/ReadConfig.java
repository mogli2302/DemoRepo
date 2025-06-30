
package utilities;

import java.io.FileInputStream;
import java.util.Properties;

public class ReadConfig {
    Properties prop;

    public ReadConfig() {
        try {
            FileInputStream fis = new FileInputStream("src/main/java/resources/config.properties");
            prop = new Properties();
            prop.load(fis);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getURL() { return prop.getProperty("url"); }
    public String getUsername() { return prop.getProperty("username"); }
    public String getPassword() { return prop.getProperty("password"); }
    public String getBrowser() { return prop.getProperty("browser"); }
}
