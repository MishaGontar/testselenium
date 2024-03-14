package guryme.botdetail.controller;

import guryme.botdetail.client.SeleniumClient;
import guryme.botdetail.model.DetailModel;
import guryme.botdetail.service.DetailService;
import org.openqa.selenium.WebDriver;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DetailsController {

    final DetailService service;

    public DetailsController(DetailService service) {
        this.service = service;
    }

    @PostMapping("/")
    public ResponseEntity<?> getUrlByParams(@RequestBody DetailModel detail) {
        if (service.validDetail(detail)) {
            return new ResponseEntity<>("Check your data please", HttpStatus.BAD_REQUEST);
        }

        SeleniumClient selenium = new SeleniumClient();
        WebDriver driver = selenium.getDriver();

        driver.get("https://iban-qr.alximicus.com/");
        service.awaitPage(selenium.getWait());
        service.fillInput(driver, detail);
        String link = service.getLink(driver);
        driver.close();
        return ResponseEntity.ok(link);
    }
}
