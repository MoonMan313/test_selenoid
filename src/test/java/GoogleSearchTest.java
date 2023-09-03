import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.HashMap;
import java.util.Map;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class GoogleSearchTest {

    @BeforeAll
    public static void setUp() {
        Configuration.remote = "http://192.168.1.71:4444/wd/hub";
        Configuration.reportsFolder = "target/surefire-reports";
        Configuration.downloadsFolder = "target/downloads";

        Map<String, Boolean> options = new HashMap<>();
        options.put("enableVNC", true);
//        options.put("enableVideo", true);
        options.put("enableLog", true);

        ChromeOptions capabilities = new ChromeOptions();
        capabilities.setBrowserVersion("115.0");
        Configuration.browserCapabilities = capabilities;
        Configuration.browserCapabilities.setCapability("selenoid:options", options);
    }

    @Test
    public void testGoogleSearch() {
        // Open Google
        open("https://www.google.com");

        // Type search query in the search field
        $("textarea[name='q']").setValue("selenide");

        // Click the "Search" button
        $("input[value='Поиск в Google']").click();

        // Check if the official Selenide site link is present in the search results
        $("#rso").shouldHave(text("selenide.org"));
    }
}