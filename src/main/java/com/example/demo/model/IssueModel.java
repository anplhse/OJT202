package com.example.demo.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "issues")
public class IssueModel {
    @Id
    private String id;

    private String issueName;
    private String recommend;
    private List<String> listIssue;
    private List<PropertyModel> properties;
}
