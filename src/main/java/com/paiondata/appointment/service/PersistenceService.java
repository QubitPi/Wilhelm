// Copyright 2025 Paion Data. All rights reserved.
package com.paiondata.appointment.service;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.paiondata.appointment.model.Appointment;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * An abstraction layer for saving new appointments to database.
 */
@Service
public class PersistenceService extends AbstractRestService {

    @Value("${appointment-ws.persistence-service.url}")
    private String url;

    @Value("${appointment-ws.ersistence-service.arango-database}")
    private String database;

    @Value("${appointment-ws.ersistence-service.arango-collection}")
    private String collection;

    /**
     * Saves a new appointment to database.
     *
     * @param newAppointment  The specified appointment to save
     */
    public void createAppointment(final Appointment newAppointment) {
        final ObjectNode appointment = JSON_MAPPER.createObjectNode();
        appointment.put("patientName", newAppointment.patientName);
        appointment.put("phone", newAppointment.phone);
        appointment.put("branch", newAppointment.branch);
        appointment.put("service", newAppointment.service);
        appointment.put("details", newAppointment.details);

        post(
                url
                        + (url.endsWith("/") ? "" : "/")
                        + "arango/createDocument/"
                        + database + "/" + collection, appointment
        );
    }
}
