package stepDefination;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Set;


public class walletHubFeature extends basicFeatures{

    @Given("^Load Driver and basic params$")
    public void load_Driver_and_basic_params() throws Throwable {
        System.out.println("Path is "+System.getProperty("user.dir"));
        //System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+"/src/drivers/geckodriver");


 System.out.println("OS NAME IS "+ System.getProperty("os.name").toLowerCase());
 String os = System.getProperty("os.name").toLowerCase();
 if(os.contains("win"))
 {
     System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+"/src/drivers/WINDOWS/geckodriver.exe");
     System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/src/drivers/WINDOWS/chromedriver.exe");
 }
 else if(os.contains("mac"))
 {
     System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+"/src/drivers/MAC/geckodriver");
     System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/src/drivers/MAC/chromedriver");
 }
 else if(os.contains("nux"))
 {
     System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+"/src/drivers/LINUX/geckodriverLinux");
     System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/src/drivers/LINUX/chromedriverLinux");
 }

        driver = new FirefoxDriver();
    }

    @Given("^User moves to url \"([^\"]*)\"$")
    public void user_moves_to_url(String arg1) throws Throwable {
        driver.get(arg1);
    }

    @When("^User clicks on review section$")
    public void user_clicks_on_review_section() throws Throwable {
        driver.findElement(By.xpath("//*[@class='nav-link semi-bold-font-weight']//child::span[text()='Reviews']")).click();
    }

    @When("^User howers over start and view change in clour on hovering over stars$")
    public void user_howers_over_start_and_view_change_in_clour_on_hovering_over_stars() throws Throwable {

        JavascriptExecutor js = (JavascriptExecutor) driver;

        for(int i=1;i<5;i++) {
            By star = By.xpath("//div[@class='rating-box-wrapper' and @style='height: 35px;']//*[@class='rvs-star-svg'][" + i + "]//*");
            List<WebElement> elementer = driver.findElements(star);
            WebElement element = new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(elementer.get(1)));
            Actions action = new Actions(driver);
            Assert.assertTrue(elementer.size()==2);
            action.moveToElement(elementer.get(1)).perform();

            elementer = driver.findElements(star);
            Assert.assertTrue(elementer.size()==3);
          Assert.assertEquals(elementer.get(2).getAttribute("stroke"),"#4ae0e1");
        }
        }


    @When("^User clicks on (\\d+) star$")
    public void user_clicks_on_star(int arg1) throws Throwable {
        By star = By.xpath("//div[@class='rating-box-wrapper' and @style='height: 35px;']//*[@class='rvs-star-svg'][" + arg1 + "]//*");
        List<WebElement> elementer = driver.findElements(star);

        Actions action = new Actions(driver);
        WebElement element = new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(elementer.get(1)));
        action.click().perform();
    }

    @Then("^user moves to policy dropdown and select \"([^\"]*)\" from dropdown menu$")
    public void user_moves_to_policy_dropdown_and_select_from_dropdown_menu(String arg1) throws Throwable {

        By dropDown = By.xpath("//span[@class='dropdown-placeholder' and contains(text(), 'Select')]");

        Set<String> windowHandles = driver.getWindowHandles();
        driver.switchTo().window(windowHandles.toArray()[windowHandles.size()-1].toString());
        WebElement dropDownElement = new WebDriverWait(driver, 20).until(ExpectedConditions.visibilityOfElementLocated(dropDown));

        dropDownElement.click();
        By xpathDropDownElements = By.xpath("//*[@class='wrev-drp']//li[@class='dropdown-item']");
        List<WebElement> dropDownList = driver.findElements(xpathDropDownElements);

        for(int i=0;i<dropDownList.size()-1;i++) {
            if (dropDownList.get(i).getText().equals("Health Insurance"))
                dropDownList.get(i).click();
        }

    }

    @Then("^User enters random value for review of (\\d+) words$")
    public void user_enters_random_value_for_review_of_words(int arg1) throws Throwable {

        By textReviewBy = By.xpath("//*[contains(@placeholder,'Write your review...')]");

        WebElement textReviewElement = driver.findElement(textReviewBy);

        String textToWrite = "";
        while(textToWrite.length()<200) {
            textToWrite+="Automation Testing!!!";
        }
        textReviewElement.sendKeys(textToWrite);
    }

    @Then("^user clicks on submit button$")
    public void user_clicks_on_submit_button() throws Throwable {
        By submitButtonBy = By.xpath("//*[text()='Submit']");
        WebElement submitButtonElement = driver.findElement(submitButtonBy);
        submitButtonElement.click();
    }

    @Then("^User logs into the webpage$")
    public void user_logs_into_the_webpage() throws Throwable {
        Thread.sleep(20000);
        By loginSectionSwitching = By.xpath("//a[text()='Login']");
        By loginTextBoxBy = By.xpath("//input[@placeholder='Email Address']");
        By passwordTextBoxBy = By.xpath("//input[@placeholder='Password']");
By loginButtonBy = By.xpath("//button[@type='button']");
        if(driver.getPageSource().contains("Login or sign up so we can post your review."))
        {
            new WebDriverWait(driver, 20).until(ExpectedConditions.visibilityOfElementLocated(loginSectionSwitching)).click();
        }
        driver.findElement(loginTextBoxBy).sendKeys("it.choudhary.rahul@gmail.com");
        driver.findElement(passwordTextBoxBy).sendKeys("Test@1234");
        driver.findElement(loginButtonBy).click();
    }

    @Then("^on successful submission user to be able to see \"([^\"]*)\"$")
    public void on_successful_submission_user_to_be_able_to_see(String arg1) throws Throwable {


    }

    @Then("^User move to profile and confirm review feed got updated$")
    public void user_move_to_profile_and_confirm_review_feed_got_updated() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Then("^User move to \"([^\"]*)\" and asset your review got updated$")
    public void user_move_to_and_asset_your_review_got_updated(String arg1) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }
}
