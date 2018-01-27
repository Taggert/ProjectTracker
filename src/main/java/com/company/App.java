package com.company;

import com.company.model.Interfaces.StartMenuInt;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan("com.company")
@PropertySource("classpath:app.properties")
public class App {



    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

        context.register(App.class);
        context.refresh();

        StartMenuInt startMenu = context.getBean(StartMenuInt.class);
        startMenu.startMenu();
    }



}