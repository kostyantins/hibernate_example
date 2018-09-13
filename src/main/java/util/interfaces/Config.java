package util.interfaces;


import com.sun.deploy.config.Platform;
import org.aeonbits.owner.ConfigFactory;
import org.aeonbits.owner.Mutable;

@Config.Sources("classpath:src/main/resources/config.properties")
public interface Config extends Mutable {

    @Key("hibernate.connection.driver.class")
//    @DefaultValue("${com.microsoft.sqlserver.jdbc.SQLServerDriver}") - to reade e.g. maven system variable
//    @DefaultValue("/drivers/%s") - method need to get parameter: String driverClassName(String path) which will be added instate of %s;
    @DefaultValue("com.microsoft.sqlserver.jdbc.SQLServerDriver")
    String driverClassName();

    @Key("hibernate.connection.url")
    @DefaultValue("jdbc:sqlserver://WS-LV-CP1549")
    String url();

    @Key("hibernate.connection.username")
    @DefaultValue("kostiantyn.sichenko")
    String username();

    @Key("hibernate.connection.password")
    @DefaultValue("1")
    String password();

    @Key("hibernate.show.sql")
    @DefaultValue("false")
    String showSql();

    @Key("hibernate.format.sql")
    @DefaultValue("false")
    String formatSql();

    @Key("hibernate.dialect")
    @DefaultValue("org.hibernate.dialect.SQLServerDialect")
    String dialect();

    @Key("javax.persistence.lock.timeout")
    @DefaultValue("8000")
    String persistenceLockTimeout();

    @Key("javax.persistence.query.timeout")
    @DefaultValue("8000")
    String persistenceQueryTimeout();

    @Key("hibernate.current_session_context_class")
    @DefaultValue("thread")
    String currentSession();

    @Key("db.dump.path")
    @DefaultValue("d:\\GIT\\kernel\\automation_test_framework\\test\\automation_test_framework\\src\\main\\resources\\db_beckups\\")
    String dumpPath();

    //    =====================================

    @Key("browser")
    @DefaultValue("firefox")
    String browser();

    @Key("browser.version")
    @DefaultValue("57")
    int version();

    @Key("browser.size")
    @DefaultValue("1366x768")
    String browserSize();

    @Key("platform")
    @DefaultValue("MAC")
    Platform platform();

    Config configuration = ConfigFactory.create(Config.class);
}
