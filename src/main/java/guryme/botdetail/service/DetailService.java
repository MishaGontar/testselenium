package guryme.botdetail.service;

import guryme.botdetail.model.DetailModel;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Service;

import static org.apache.commons.lang3.StringUtils.isAnyBlank;
import static org.openqa.selenium.support.ui.ExpectedConditions.and;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

@Service
public class DetailService {
    public void fillInput(WebDriver driver, DetailModel details) {
        typeText(driver, XPATH_RECEIVER_INPUT, details.getReceiver());
        typeText(driver, XPATH_IBAN_INPUT, details.getIban());
        typeText(driver, XPATH_EDRPOU_INPUT, details.getEdrpou());
        typeText(driver, XPATH_AMOUNT_INPUT, details.getAmount());
        typeText(driver, XPATH_DESTINATION_INPUT, details.getDestination());
    }

    public String getLink(WebDriver driver) {
        return driver.findElement(By.xpath(XPATH_LINK)).getAttribute("href");
    }

    public boolean validDetail(DetailModel details) {
        return isAnyBlank(details.getAmount(), details.getIban(), details.getDestination(), details.getEdrpou(), details.getReceiver());
    }

    public void awaitPage(WebDriverWait wait) {
        wait.until(and(
                visibilityOfElementLocated(By.xpath(XPATH_RECEIVER_INPUT)),
                visibilityOfElementLocated(By.xpath(XPATH_IBAN_INPUT)),
                visibilityOfElementLocated(By.xpath(XPATH_EDRPOU_INPUT)),
                visibilityOfElementLocated(By.xpath(XPATH_AMOUNT_INPUT)),
                visibilityOfElementLocated(By.xpath(XPATH_DESTINATION_INPUT)),
                visibilityOfElementLocated(By.xpath(XPATH_LINK))
        ));
    }

    private void typeText(WebDriver driver, String xpathInput, String value) {
        WebElement input = driver.findElement(By.xpath(xpathInput));
        input.sendKeys(value);
    }

    private static final String XPATH_RECEIVER_INPUT = "//input[contains(@id,'receiver')]";
    private static final String XPATH_IBAN_INPUT = "//input[contains(@id,'IBAN')]";
    private static final String XPATH_EDRPOU_INPUT = "//input[contains(@id,'EDRPOU')]";
    private static final String XPATH_AMOUNT_INPUT = "//input[contains(@id,'amount')]";
    private static final String XPATH_DESTINATION_INPUT = "//input[contains(@id,'destination')]";
    private static final String XPATH_LINK = "//a[contains(@class,'avengers-link')]";
}
