// Copyright 2025 Paion Data. All rights reserved.
package com.paiondata.appointment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * The main JAR for running the appointment WS.
 */
@SpringBootApplication
public class Application {

    /**
     * Entry point.
     *
     * @param args  Not used
     */
    @SuppressWarnings("UncommentedMain")
    public static void main(final String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
