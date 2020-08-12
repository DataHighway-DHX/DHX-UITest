import com.microsoft.appcenter.appium.Factory;
import com.microsoft.appcenter.appium.EnhancedAndroidDriver;
import org.junit.rules.TestWatcher;
import org.junit.Rule;

import io.appium.java_client.MobileElement;
import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;



public class TestAndroid {

    private static EnhancedAndroidDriver<MobileElement> driver;
    private Actions actions;

    @Rule
    public TestWatcher watcher = Factory.createWatcher();

    @Before
    public void initializeDriver() throws MalformedURLException {
        DesiredCapabilities dc = new DesiredCapabilities();
        dc.setCapability("automationName", "UiAutomator2");
        dc.setCapability("platformName", "Android");
        dc.setCapability("appPackage", "com.datahighway.mobile");
        dc.setCapability("appActivity", "com.datahighway.mobile.MainActivity");
        dc.setCapability("deviceName", "Nexus 6 API 28");
        //dc.setCapability("app", "/home/namgyeong/AndroidStudioProjects/app/build/app/outputs/apk/app.apk");

        driver = Factory.createAndroidDriver(new URL("http://0.0.0.0:4723/wd/hub"), dc);
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        actions = new Actions(driver);
    }

    @Test
    public void errorForInconsistentPassword() throws InterruptedException {
        clickCreateAccount();
        enterCredentials("test name", "123456abcde", "abcde123456");
        clickNextStep();

        Assert.assertTrue(isDisplayed(By.xpath("//android.view.View[@text='Inconsistent passwords']")));
    }

    @Test
    public void createAccount() throws InterruptedException {

        String user = "John Doe";
        String password = "Password123";

        clickCreateAccount();
        enterCredentials(user, password, password);
        clickNextStep();
        clickNextStep();
        acceptScreenshotPopup();
        List<String> mnemonics = getMnemonicList();
        clickNextStep();
        clickAsPerMnemonics(mnemonics);
        clickNextStep();

        //Assert.assertTrue(" Assets not displayed", assetsDisplayed());
        //clickImageView("Profile");
        //clickButton("Manage Account");

        System.out.println(getActivity());
    }

    @Test
    public void deleteAccount() throws InterruptedException {

        String user = "John Delete";
        String password = "Password123";

        clickCreateAccount();
        enterCredentials(user, password, password);
        clickNextStep();
        clickNextStep();
        acceptScreenshotPopup();
        List<String> mnemonics = getMnemonicList();
        clickNextStep();
        clickAsPerMnemonics(mnemonics);
        clickNextStep();
        clickTab("Profile");
        clickManageAccount();
        clickDeleteAccount();
        enterPasswordForDelete(password);
        clickOkToConfirmDelete();

        Assert.assertTrue(" Assets not displayed", homeActivityDisplayed());
    }

    @Test
    public void DHXSignal() throws InterruptedException {

        String user = "John Delete";
        String password = "Password123";

        clickCreateAccount();
        enterCredentials(user, password, password);
        clickNextStep();
        clickNextStep();
        acceptScreenshotPopup();
        List<String> mnemonics = getMnemonicList();
        clickNextStep();
        clickAsPerMnemonics(mnemonics);
        clickNextStep();

        clickTab("Profile");

    }

    public boolean homeActivityDisplayed() {
        return isDisplayed(By.xpath("//android.widget.Button[@text='Create Account']"));
    }

    public boolean assetsDisplayed() {
        return isDisplayed(By.xpath("//android.view.View[@text='Assets']"));
    }

    public void clickAsPerMnemonics(List<String> mnemonics) throws InterruptedException {
        for(String word : mnemonics) {
            click(By.xpath("//android.widget.Button[@text='" + word + "']"));
            Thread.sleep(200);
        }
    }

    public List<String> getMnemonicList() throws InterruptedException {

        By by = By.xpath("//android.view.View[@text='Backup mnemonic']/../android.view.View[3]");
        WebElement mnemonic = driver.findElement(by);

        int cnt=0;
        String text = mnemonic.getText();
        while(text.equals("") && cnt < 10) {
            Thread.sleep(500);
            cnt++;
            text = mnemonic.getText();
        }

        return Arrays.asList(text.split(" ")) ;
    }

    public void acceptScreenshotPopup() {
        click(By.xpath("//android.widget.Button[@text='OK']"));
    }

    public String getActivity() {
        return driver.currentActivity();
    }

    public void clickNextStep() {
        click(By.xpath("//android.widget.Button[@text='Next Step']"));
    }

    public void clickCreateAccount() {
        click(By.xpath("//android.widget.Button[@text='Create Account']"));
    }

    public void clickManageAccount() {
        click(By.xpath("//android.widget.Button[@text='Manage Account']"));
    }

    public void clickDeleteAccount() {
        click(By.xpath("//android.widget.Button[@text='Delete Account']"));
    }

    public void clickTab(String tabName) {
        click(By.xpath(String.format("//android.widget.ImageView[contains(@text,'%s')]", tabName)));
    }

    public void enterCredentials(String username, String password, String confirmPassword) throws InterruptedException {
        enterText(By.xpath("//android.widget.EditText[@text='Name']"), username);
        enterText(By.xpath("//android.widget.EditText[@text='Password']"), password);
        enterText(By.xpath("//android.widget.EditText[@text='Confirm Password']"), confirmPassword);
    }

    public void enterPasswordForDelete(String password) throws InterruptedException {
        enterText(By.xpath("//android.widget.EditText[@text='Current Password']"), password);
    }

    public void clickOkToConfirmDelete() {
        click(By.xpath("//android.widget.Button[@text='OK']"));
    }

    public boolean isDisplayed(By by) {
        return driver.findElement(by).isDisplayed();
    }

    public void enterText(By by, String text) throws InterruptedException {
        WebElement edit = driver.findElement(by);
        edit.click();
        Thread.sleep(200);
        actions.sendKeys(edit, text).perform();
    }

    public void click(By by) {
        driver.findElement(by).click();
    }

    @After
    public void teardown() {
        driver.quit();
    }
}
