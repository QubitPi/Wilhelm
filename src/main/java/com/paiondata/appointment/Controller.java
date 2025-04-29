// Copyright 2025 Paion Data. All rights reserved.
package com.paiondata.appointment;

import com.paiondata.appointment.model.Appointment;
import com.paiondata.appointment.service.NotificationService;
import com.paiondata.appointment.service.PersistenceService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The {@link Controller} is responsible for all the operations related to making an appointment.
 */
@RestController
@RequestMapping("/appointment")
public class Controller {

    @Autowired
    private PersistenceService persistenceService;

    @Autowired
    private NotificationService notificationService;

    /**
     * Endpoint for creating a new appointment.
     *
     * @param newAppointment  The entity modeling the new appointment data.
     *
     * @return  The created appointment entity
     */
    @PostMapping("/createAndNotify")
    ResponseEntity<String> createAndNotify(@RequestBody final Appointment newAppointment) {
        notificationService.sendNotification(newAppointment);
        persistenceService.createAppointment(newAppointment);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
