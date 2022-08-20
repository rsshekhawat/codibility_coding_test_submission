package Step_Defs;

import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import Keywords.Keyword;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import java.util.List;

public class step_def {
    WebDriver driver;
    Keyword keyword;
    static Integer lowest_price_item;

    @Before(order=0, value="@Init")
    public void user_launches_the_ecommerce_website() {
        System.setProperty("webdriver.chrome.driver", "C:\\Automation\\Test_Framework_Setup\\drivers\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://cms.demo.katalon.com/");
        keyword = new Keyword(driver);
    }

    @Given("I add four random items to my cart")
    public void iAddFourRandomItemsToMyCart() {
        keyword.userAddsFourRandomItemsToMycart();
    }

    @When("I view my cart")
    public void iViewMyCart() {
        driver.findElement(By.xpath("//a[text()=\"Cart\"]")).click();
        System.out.println("User navigates to my cart");
    }
    
    @Then("I find total four items listed in my cart")
    public void iFindTotalFourItemsListedInMyCart() {
        Assert.assertEquals(keyword.getTotalItemsInCart(), 4, "Assertion Failed: Total items in cart are not 4");
    }

    @When("I search for lowest price item")
    public void iSearchForLowestPriceItem() {
        lowest_price_item = keyword.userSearchForLowestPriceItem();
    }

    @And("I am able to remove the lowest price item from my cart")
    public void iAmAbleToRemoveTheLowestPriceItemFromMyCart() {
        keyword.removeGivenItemFromCart(lowest_price_item);
    }

    @Then("I am able to verify three items in my cart")
    public void iAmAbleToVerifyThreeItemsInMyCart() {
        Assert.assertEquals(keyword.getTotalItemsInCart(), 3, "Assertion Failed: Total items in cart are not 3");
    }
}
