package guryme.botdetail.controller;

import guryme.botdetail.client.SeleniumClient;
import org.openqa.selenium.WebDriver;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DetailsController {

    @GetMapping()
    String getUrlByParams() {
        SeleniumClient selenium = new SeleniumClient();
        WebDriver driver = selenium.getDriver();

        driver.get("https://www.google.com/");
        System.out.println(driver.getCurrentUrl());
        driver.close();
        return "Hello world";
    }
}
