import io.qameta.allure.restassured.AllureRestAssured;
import lombok.val;
import org.testng.annotations.Test;
import util.APITestRunner;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class DBtest extends APITestRunner {

//    @BeforeMethod
//    public void init() {
//        someController = new SomeController();
//        someController.createSomeData();
//    }

    @Test
    public void testDB() {

        val url = "https://api.stackexchange.com/2.2/sites?page=1&pagesize=5&filter=!2*nS3dguV(Y)xKGRu8YcH";

        given()
                .get(url)
                .then()
                .assertThat()
                .statusCode(200)
                .body("items[0].name", equalTo("Stack Overflow"));
    }
}
