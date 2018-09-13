package db.jpa.dao;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AnnotationTypeFilter;

import javax.persistence.Entity;
import java.util.Properties;

import static util.interfaces.Config.configuration;

public class JPAconfiguration {

    private static final Logger LOGGER = Logger.getLogger(JPAconfiguration.class);
    private static JPAconfiguration instance = new JPAconfiguration();

    private SessionFactory sessionFactory;

    private JPAconfiguration() {
        this.sessionFactory = buildSessionFactory();
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static JPAconfiguration getInstance() {
        if (instance == null) {
            return new JPAconfiguration();
        }
        return instance;
    }

    private synchronized SessionFactory buildSessionFactory() {
        Properties prop = new Properties();
        prop.setProperty("hibernate.connection.url", configuration.url());
        prop.setProperty("hibernate.connection.username", configuration.username());
        prop.setProperty("hibernate.connection.password", configuration.password());
        prop.setProperty("hibernate.connection.driver_class", configuration.driverClassName());
        prop.setProperty("hibernate.dialect", configuration.dialect());
        prop.setProperty("javax.persistence.lock.timeout", configuration.persistenceLockTimeout());
        prop.setProperty("javax.persistence.query.timeout", configuration.persistenceQueryTimeout());
        prop.setProperty("hibernate.show_sql", configuration.showSql());
        prop.setProperty("hibernate.format_sql", configuration.formatSql());
        //such configuration for getting current session
        //prop.setProperty("hibernate.current_session_context_class", configuration.currentSession());
        LOGGER.info("HIBERNATE init :" + prop);

        ClassPathScanningCandidateComponentProvider scanner = new ClassPathScanningCandidateComponentProvider(false);
        scanner.addIncludeFilter(new AnnotationTypeFilter(Entity.class));

        Configuration configuration = new Configuration().addProperties(prop);

        for (BeanDefinition bd : scanner.findCandidateComponents("db.jpa.entity")) {
            try {
                configuration.addAnnotatedClass(Class.forName(bd.getBeanClassName()));
            } catch (ClassNotFoundException e) {
                LOGGER.info("Class is not found :" + bd.getBeanClassName());
                e.printStackTrace();
            }
        }
        return configuration.buildSessionFactory();
    }
}
