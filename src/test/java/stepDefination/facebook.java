package stepDefination;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.HashSet;

public class facebook extends basicFeatures{

    @Given("^User logs in to facebook with \"([^\"]*)\" as username and \"([^\"]*)\" as password$")
    public void user_logs_in_to_facebook_with_as_username_and_as_password(String arg1, String arg2) throws Throwable {
        By xpathEmail = By.xpath("//input[@id='email']");
        By xpathPassword = By.xpath("//input[@id='pass']");

driver.get("https://www.facebook.com");
driver.findElement(xpathEmail).sendKeys(arg1);
        driver.findElement(xpathPassword).sendKeys(arg2);
    }

    @Given("^user clicks on login button to move in to profile$")
    public void user_clicks_on_login_button_to_move_in_to_profile() throws Throwable {

        By xpathSubmitButton = By.xpath("//button[@type='submit']");
By nameLeftPanelOnSuccessfullLogin = By.xpath("//span[text()='Rohit Choudhary']");

        driver.findElement(xpathSubmitButton).click();
        WebElement leftPanel = new WebDriverWait(driver, 20).until(ExpectedConditions.presenceOfElementLocated(nameLeftPanelOnSuccessfullLogin));
    }

    @Given("^user posts \"([^\"]*)\" in post senciton$")
    public void user_posts_in_post_senciton(String arg1) throws Throwable {
By whatsonYourMindPanel = By.xpath("//span[contains(text(),'on your mind')]");
By textPlacingToPost = By.xpath("//div[contains(@aria-label,'on your mind')]");
By postButton = By.xpath("//*[text()='Post']");
By createPostPopup = By.xpath("//span[text()='Create post']");
By firstElementLink = By.xpath("//div[text()='Automated Test!']");

//HashSet<String> initialWindowHandles = (HashSet<String>) driver.getWindowHandles();
driver.findElement(whatsonYourMindPanel).click();
WebElement newElem = new WebDriverWait(driver,20).until(ExpectedConditions.visibilityOfElementLocated(textPlacingToPost));

        HashSet<String> finalWindowHandles = (HashSet<String>) driver.getWindowHandles();
        System.out.println("Window handles are "+finalWindowHandles);

//finalWindowHandles.removeAll(initialWindowHandles);

driver.switchTo().window(finalWindowHandles.toArray()[finalWindowHandles.size()-1].toString());


driver.findElement(textPlacingToPost).sendKeys(arg1);
driver.findElement(postButton).click();
WebElement elemFirst = new WebDriverWait(driver,20).until(ExpectedConditions.visibilityOfElementLocated(firstElementLink));

    }

    @Then("^user logs out facebook and quit driver session$")
    public void user_logs_out_facebook_and_quit_driver_session() throws Throwable {
        By accountDropdown  = By.xpath("//div[@aria-label='Account']");
        By logOutButton = By.xpath("//span[text()='Log Out']");
        By createNewAccount = By.xpath("//a[text()='Create New Account']");

driver.findElement(accountDropdown).click();
WebElement elementLogOut = new WebDriverWait(driver,20).until(ExpectedConditions.presenceOfElementLocated(logOutButton));

        elementLogOut.click();
        WebElement elementLoggedOut = new WebDriverWait(driver,7).until(ExpectedConditions.presenceOfElementLocated(createNewAccount));
        Assert.assertTrue(driver.getPageSource().contains("Create New Account"));
        driver.quit();
    }
}
