package com.example.demo.service;

import java.util.List;

import com.example.demo.exception.IssueCollectionException;
import com.example.demo.model.IssueModel;

public interface IssueService {

    public List<IssueModel> getAllIssues();

    public IssueModel getIssueById(String id) throws IssueCollectionException;

    public void createIssue(IssueModel issueName) throws IssueCollectionException;

    public void updateIssue(String id, IssueModel issueName) throws IssueCollectionException;

    public void deleteIssue(String id) throws IssueCollectionException;
}
