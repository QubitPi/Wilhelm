// Copyright 2025 Paion Data. All rights reserved.
package com.paiondata.appointment.model;

import com.fasterxml.jackson.databind.JsonNode;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * The Arango entity that models a complete appointment.
 */
@Schema(description = "预约表单")
public class Appointment {

    /**
     * 姓名.
     */
    @Schema(name = "patientName", description = "姓名")
    public String patientName;

    /**
     * 电话号码.
     */
    @Schema(name = "phone", description = "电话号码")
    public String phone;

    /**
     * 预约门诊.
     */
    @Schema(name = "branch", description = "预约门诊")
    public String branch;

    /**
     * 诊疗项目.
     */
    @Schema(name = "service", description = "诊疗项目")
    public String service;

    /**
     * 病情描述.
     */
    @Schema(name = "details", description = "病情描述")
    public String details;

    /**
     * Creates a new {@link Appointment} instance from a Jackson object containing all the same {@link Appointment}
     * fields.
     *
     * @param appointment  The specified Jackson object
     *
     * @return a new instance
     */
    public static Appointment ofJsonNode(final JsonNode appointment) {
        final Appointment deserialized = new Appointment();

        deserialized.patientName = appointment.get("patientName").asText();
        deserialized.phone = appointment.get("phone").asText();
        deserialized.branch = appointment.get("branch").asText();
        deserialized.service = appointment.get("service").asText();
        deserialized.details = appointment.get("details").asText();

        return deserialized;
    }
}
