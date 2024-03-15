package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class DeviceDTO {
    @JsonProperty("IPAddress")
    private String ipAddress;
    @JsonProperty("AddressSource")
    private String addressSource;
    @JsonProperty("MACAddress")
    private String macAddress;
    @JsonProperty("HostName")
    private String hostName;
    @JsonProperty("InterfaceType")
    private String interfaceType;
    @JsonProperty("LeaseTimeRemaining")
    private String leaseTimeRemaining;
    @JsonProperty("Layer2Interface")
    private String layer2Interface;
    @JsonProperty("SignalLvl")
    private String signalLvl;
}
