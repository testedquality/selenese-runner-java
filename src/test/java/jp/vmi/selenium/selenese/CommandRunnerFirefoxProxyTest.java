package jp.vmi.selenium.selenese;

import org.junit.AfterClass;
import org.junit.Assume;
import org.junit.Before;
import org.junit.BeforeClass;
import org.openqa.selenium.firefox.FirefoxBinary;

import com.thoughtworks.selenium.SeleniumException;

import jp.vmi.selenium.webdriver.DriverOptions;
import jp.vmi.selenium.webdriver.DriverOptions.DriverOption;
import jp.vmi.selenium.webdriver.WebDriverManager;

public class CommandRunnerFirefoxProxyTest extends CommandRunnerFirefoxTest {
    static Proxy proxy = new Proxy();

    @BeforeClass
    public static void startProxy() {
        proxy.start();
    }

    @AfterClass
    public static void stopProxy() {
        proxy.kill();
    }

    @Override
    @Before
    public void assumeInstalledFirefox() {
        try {
            new FirefoxBinary();
        } catch (SeleniumException e) {
            Assume.assumeNoException(e);
        }
    }

    @Override
    protected void setupWebDriverManager() {
        WebDriverManager manager = WebDriverManager.getInstance();
        manager.setWebDriverFactory(WebDriverManager.FIREFOX);
        manager.setDriverOptions(new DriverOptions().set(DriverOption.PROXY, proxy.getProxyString()));
    }
}
