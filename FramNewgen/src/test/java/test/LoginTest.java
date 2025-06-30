
package test;

import org.testng.Assert;
import org.testng.annotations.*;

import base.BaseClass;
import pageobjects.LoginPage;
import utilities.ReadConfig;

@Listeners(listeners.TestListener.class)
public class LoginTest extends BaseClass {

    @Test(dataProvider = "loginData")
    public void validLoginTest(String user, String pass) {
        LoginPage lp = new LoginPage(driver);
        test.info("Entering username");
        lp.enterUsername(user);
        test.info("Entering password");
        lp.enterPassword(pass);
        test.info("Clicking login");
        lp.clickLogin();
        
       
    }

    @DataProvider(name = "loginData")
    public Object[][] loginData() {
        ReadConfig config = new ReadConfig();
        return new Object[][] {
            { config.getUsername(), config.getPassword() }
        };
    }
}
