package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class RssiDeviceDTO {
    private String ip;
    private String hostname;
    private String mac;
    private Integer rssiMin;
    private Integer rssiAvg;
    private Boolean lowSignal;
}
