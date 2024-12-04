package tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.open;

public class TestBase {
    @BeforeAll
    static void beforeAll() {
//        Configuration.pageLoadStrategy = "eager";
//        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://petstore.swagger.io/v2";
//        Configuration.holdBrowserOpen = true;
//        Configuration.timeout = 10000; // default 4000
    }

//    @Test
//    void openUrl() {
//        open("/pet");
//        open("");
//    }
}