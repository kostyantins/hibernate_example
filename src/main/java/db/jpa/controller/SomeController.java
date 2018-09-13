package db.jpa.controller;

import db.jpa.dao.GenericDAO;
import db.jpa.entity.Clusters;
import org.apache.log4j.Logger;


public class SomeController extends EntityObjectBase {

    private static final Logger LOGGER = Logger.getLogger(SomeController.class);

    public SomeController() {
        clusters = new GenericDAO<>(Clusters.class);
    }

    public void createSomeData() {
        LOGGER.info("Start createSomeData");
        //or using repo like object creation
        clusters.addEntity(new Clusters(100, "Test", "Test", 10.00, 0, ""));
        LOGGER.info("Finish createSomeData");
    }

}
