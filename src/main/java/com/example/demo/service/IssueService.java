package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.IssueRequest;
import com.example.demo.exception.IssueCollectionException;
import com.example.demo.model.IssueModel;

public interface IssueService {

    public List<IssueModel> getAllIssues();

    public IssueModel getSingleIssueName(String id) throws IssueCollectionException;

    public void createIssue(IssueRequest request) throws IssueCollectionException;

    public void updateIssue(String id, IssueModel issue) throws IssueCollectionException;

    public void deleteIssueById(String id) throws IssueCollectionException;
}
