package com.example.demo.dto;

import java.util.List;

import com.example.demo.model.PropertyModel;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class IssueResponse {
    private String id;
    private String issueName;
    private String recommend;
    private List<String> listIssue;
    private List<PropertyModel> properties;
}

