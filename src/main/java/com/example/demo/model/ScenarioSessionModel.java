package com.example.demo.model;

import java.util.Date;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.example.demo.dto.InfoCPEDTO;

import lombok.Data;

@Data
@Document(collection = "ScenarioSessionModel")
public class ScenarioSessionModel {
    @Id
    private String id;
    private String chartId;
    private String contract;
    private String sessionId;
    private Boolean finished = false;
    private Integer currentScenario = 1;
    private String email;
    private InfoCPEDTO analysisBefore;
    private InfoCPEDTO analysisAfter;
    @CreatedDate
    private Date createdAt;
}
