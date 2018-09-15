package util;

import db.jpa.controller.SomeController;
import org.testng.annotations.Listeners;

@Listeners(APIListener.class)
public class APITestRunner {

    protected SomeController someController;
}
