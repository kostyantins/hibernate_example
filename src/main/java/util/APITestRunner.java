package util;

import db.jpa.controller.SomeController;
import org.testng.annotations.Listeners;

@Listeners(LogListener.class)
public class APITestRunner {

    protected SomeController someController;
}
