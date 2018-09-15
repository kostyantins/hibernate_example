import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.openqa.selenium.By;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import util.WebTestRunner;

import static com.codeborne.selenide.Selenide.$;

public class SelenideTest extends WebTestRunner{

    @Test
    public void testSelenide (){
        $(By.id("gs_htif0")).waitUntil(Condition.visible, 5000).sendKeys("automation");
    }
}
