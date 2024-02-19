package ru.tinkoff.edu.java.t2_seminar2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class AppStarter {
    private static final Logger logger = LoggerFactory.getLogger(AppStarter.class);
    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(AppStarter.class);
        applicationContext.addApplicationListener(event -> {
            logger.info("Application started in {} seconds", ((ApplicationStartedEvent) event).getTimeTaken().toSeconds());
        });
    }
}