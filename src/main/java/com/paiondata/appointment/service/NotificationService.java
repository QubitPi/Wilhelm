// Copyright 2025 Paion Data. All rights reserved.
package com.paiondata.appointment.service;

import com.paiondata.appointment.model.Appointment;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * An abstraction layer for sending notification about the new appointment.
 */
@Service
public class NotificationService extends AbstractRestService {

    @Value("${appointment-ws.notification-service.url}")
    private String url;

    /**
     * Sends a notification about a new appointment.
     *
     * @param newAppointment  An object containing all info about the appointment
     */
    public void sendNotification(final Appointment newAppointment) {
        post(
                url + (url.endsWith("/") ? "" : "/") + "dingding/createNotification",
                String.format(
                        "### 【%s】预约患者信息\n- 姓名: %s\n- 电话号码: %s\n- 预约门诊: %s\n- 诊疗项目: %s\n- 病情描述: %s\n",
                        newAppointment.branch,
                        newAppointment.patientName,
                        newAppointment.phone,
                        newAppointment.branch,
                        newAppointment.service,
                        newAppointment.details
                )
        );
    }
}
