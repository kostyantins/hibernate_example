import org.testng.annotations.Test;
import util.MyTestRunner;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;


public class DBtest extends MyTestRunner {

//    @BeforeMethod
//    public void init() {
//        someController = new SomeController();
//        someController.createSomeData();
//    }

    @Test
    public void testDB() {
        final String url = "https://api.stackexchange.com/2.2/sites?page=1&pagesize=5&filter=!2*nS3dguV(Y)xKGRu8YcH";

        given()
                .get(url)
                .then()
                .assertThat()
                .statusCode(200)
                .body("items[0].name", equalTo("Stack Overflow"));
    }
}
