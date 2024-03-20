package com.example.demo.dto;
import java.util.List;

import com.example.demo.model.PropertyModel;

import lombok.Data;

@Data
public class IssueRequest {
    private String issueName;
    private String recommend;
    private List<String> listIssue;
    private List<PropertyModel> properties;
}
