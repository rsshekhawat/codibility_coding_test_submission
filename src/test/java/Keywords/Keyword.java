package Keywords;

import io.netty.util.internal.StringUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class Keyword {
    WebDriver driver;
    public Keyword(WebDriver driver){
        this.driver = driver;
    }
    public void userAddsFourRandomItemsToMycart() {
        List<WebElement> items = driver.findElements(By.cssSelector(".add_to_cart_button"));

        for(int i=0;i<4;i++){
            items.get(i).click();
            hardWait(1);
        }
        System.out.println("Added four items to the cart");
    }

    public void hardWait(int time){
        try {
            Thread.sleep(time* 1000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public Integer getTotalItemsInCart() {
        List<WebElement> items_in_cart = driver.findElements(By.cssSelector("td.product-name"));
        return items_in_cart.size();
    }

    public Integer userSearchForLowestPriceItem() {
        List<WebElement> items = driver.findElements(By.cssSelector("td.product-price"));
        double mini = 1000000000.0;
        int item_no = -1;
        for(int i=0;i<items.size();i++){
            double current = Double.parseDouble(StringUtil.substringAfter(items.get(i).getText().trim(), '$'));
            if(current<mini) {
                mini = current;
                item_no = i;
            }
        }
        return item_no;
    }

    public void removeGivenItemFromCart(Integer lowest_price_item) {
        List<WebElement> items = driver.findElements(By.cssSelector(".remove"));
        items.get(lowest_price_item).click();
        hardWait(2);
        driver.navigate().refresh();
    }
}
