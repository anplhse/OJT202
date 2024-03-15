package com.example.demo.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class InfoCPEDTO {
    @JsonProperty("CPUUsage")
    private String cpuUsage;
    @JsonProperty("Memory")
    private String memory;
    @JsonProperty("DeviceInfo.SoftwareVersion")
    private String softwareVersion;
    @JsonProperty("DeviceInfo.UpTime")
    private String deviceUpTime;
    @JsonProperty("LANDevice.1.WLANConfiguration.1.AutoChannelEnable")
    private String autoChannelEnable;
    @JsonProperty("LANDevice.1.WLANConfiguration.5.AutoChannelEnable")
    private String autoChannelEnable_5;
    @JsonProperty("LANDevice.1.WLANConfiguration.1.RadioEnabled")
    private String radioEnabled;
    @JsonProperty("LANDevice.1.WLANConfiguration.5.RadioEnabled")
    private String radioEnabled_5;
    @JsonProperty("LANDevice.1.WLANConfiguration.1.SSID")
    private String ssid;
    @JsonProperty("LANDevice.1.WLANConfiguration.5.SSID")
    private String ssid5;
    @JsonProperty("LANDevice.1.WLANConfiguration.1.Channel")
    private String channel;
    @JsonProperty("LANDevice.1.WLANConfiguration.5.Channel")
    private String channel5;
    @JsonProperty("DeviceInfo.ProductClass")
    private String deviceInfo;
    @JsonProperty("LANDevice.1.LANHostConfigManagement.DHCPLeaseTime")
    private String leaseTime;
    @JsonProperty("LANDevice.1.LANHostConfigManagement.DHCPServerEnable")
    private String dhcpServiceEnable;
    @JsonProperty("LANDevice.1.WLANConfiguration.1.WPAEncryptionModes")
    private String wpaEncryptionModel;
    @JsonProperty("LANDevice.1.WLANConfiguration.5.WPAEncryptionModes")
    private String wpaEncryptionModes5;
    @JsonProperty("LANDevice.1.WLANConfiguration.1.BeaconType")
    private String beaconType;
    @JsonProperty("LANDevice.1.WLANConfiguration.5.BeaconType")
    private String beaconType5;
    @JsonProperty("LANDevice.1.WLANConfiguration.1.Standard")
    private String standard;
    @JsonProperty("LANDevice.1.WLANConfiguration.5.Standard")
    private String standard5;
    @JsonProperty("LANDevice.1.WLANConfiguration.1.BandWidth")
    private String bandWidth;
    @JsonProperty("LANDevice.1.WLANConfiguration.1.X_CU_Bandwidth")
    private String xCUBandwidth;
    @JsonProperty("LANDevice.1.WLANConfiguration.1.X_FPT_Bandwidth")
    private String xFPTBandwidth;
    @JsonProperty("LANDevice.1.WLANConfiguration.1.X_TELEFONICA-ES_Bandwidth")
    private String es_Bandwidth;
    @JsonProperty("WANDevice.1.WANConnectionDevice.1.WANPPPConnection.1.DefaultGateway")
    private String defaultGateway;
    @JsonProperty("WANDevice.1.WANConnectionDevice.1.WANPPPConnection.1.ExternalIPAddress")
    private String ipWan;
    @JsonProperty("LANDevice.1.LANHostConfigManagement.DNSServers")
    private String dnsServers;
    @JsonProperty("MacAddress")
    private String macAddress;
    @JsonProperty("Output")
    private String output;
    @JsonProperty("Input")
    private String input;
    @JsonProperty("Wi-Fi2.4GhzScore")
    private String wifiScore;
    @JsonProperty("Wi-Fi5GhzScore")
    private String wifiScore5;
    @JsonProperty("Admin")
    private String admin;
    @JsonProperty("Operation")
    private String operation;
    @JsonProperty("OnStatus")
    private String onStatus;
    @JsonProperty("ONUTemp")
    private String onuTemp;
    @JsonProperty("ONUTXPower")
    private String onuTX;
    @JsonProperty("ONURXPower")
    private String onuRX;
    @JsonProperty("IP")
    private String ipOLT;
    @JsonProperty("ONUSerial")
    private String oneSerial;
    @JsonProperty("Equiment")
    private String deviceName;
    @JsonProperty("Model")
    private String Model;
    @JsonProperty("Code")
    private Integer code;
    @JsonProperty("IPAddress")
    private String ipAddress;
    @JsonProperty("useWifi")
    private String useWifi;
    @JsonProperty("useLAN")
    private String useLAN;
    @JsonProperty("useAP")
    private String useAP;
    @JsonProperty("isWiredAP")
    private String isWiredAP;
    @JsonProperty("ContractTypeName")
    private String contractTypeName;
    @JsonProperty("mac")
    private String mac;
    @JsonProperty("ObjID")
    private Integer objId;
    @JsonProperty("sender_id")
    private String sender_id;
    @JsonProperty("LANDevice.1.WLANConfiguration.1.PreSharedKey.1.KeyPassphrase")
    private String keypress;
    @JsonProperty("LANDevice.1.WLANConfiguration.5.PreSharedKey.1.KeyPassphrase")
    private String keypress5;
    @JsonProperty("ClientScore")
    private String clientScore;
    @JsonProperty("cong_suat_thu")
    private String cong_suat_thu;
    @JsonProperty("lofi")
    private String lofi;
    @JsonProperty("sf")
    private String sf;
    @JsonProperty("power_off")
    private String power_off;
    @JsonProperty("bad_wifi_rate")
    private String bad_wifi_rate;
    @JsonProperty("data_usage")
    private String data_usage;
    @JsonProperty("connection_drop")
    private String connection_drop;
    @JsonProperty("number_devices")
    private String number_devices;
    @JsonProperty("pay_tv")
    private String pay_tv;
    @JsonProperty("diem_ket_noi")
    private String diem_ket_noi;
    @JsonProperty("load_speed")
    private String load_speed;
    @JsonProperty("ping_lan")
    private String ping_lan;
    @JsonProperty("ping_Wan")
    private String ping_Wan;
    @JsonProperty("ContractStatusName")
    private String contractStatusName;

    private List<DeviceDTO> wifi24G;
    private List<DeviceDTO> wifi5G;
    private List<DeviceDTO> ethernet;

    @JsonProperty("client_use_24G")
    private Integer clientUse24G;

    @JsonProperty("client_use_lan")
    private Integer clientUseLan;

    @JsonProperty("client_use_5G")
    private Integer clientUse5G;

    @JsonProperty("list_rssi")
    private List<RssiDataDTO> listRssi;

    @JsonProperty("nat_sessions")
    private Integer natSessions;

    @JsonProperty("ip_v4_enabled")
    private String ipV4Enabled;

    @JsonProperty("ip_v6_enabled")
    private String ipV6Enabled;

    @JsonProperty("MainWare")
    private String mainWare;

    @JsonProperty("DSLAMPOP")
    private String dsLamPop;

}
