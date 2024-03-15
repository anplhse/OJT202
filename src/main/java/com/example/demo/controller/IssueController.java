package com.example.demo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exception.IssueCollectionException;
import com.example.demo.model.IssueModel;
import com.example.demo.service.IssueService;

import lombok.RequiredArgsConstructor;


@RestController
@RequiredArgsConstructor
public class IssueController {

    private final IssueService issueService;

    @PostMapping("/issues")
    public ResponseEntity<?> createIssue(@RequestBody IssueModel issue) throws IssueCollectionException {
        issueService.createIssue(issue);
        return new ResponseEntity<IssueModel>(issue, HttpStatus.OK);
    }

    @GetMapping("/issues")
    public ResponseEntity<?> getAllIssues() throws IssueCollectionException {
        List<IssueModel> issue = issueService.getAllIssues();
        return new ResponseEntity<>(issue, issue.size() > 0 ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @GetMapping("/issues/{id}")
    public ResponseEntity<?> getIssueById(@PathVariable("id") String id) throws IssueCollectionException {
        return new ResponseEntity<>(issueService.getSingleIssueName(id), HttpStatus.OK);
    }

    @DeleteMapping("/issues/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") String id) throws IssueCollectionException {
        issueService.deleteIssueById(id);
        return new ResponseEntity<>("Successfully deleted issue with id " + id, HttpStatus.OK);
    }

    @PutMapping("/issues/{id}")
    public ResponseEntity<?> updateById(@PathVariable("id") String id, @RequestBody IssueModel issue)
            throws IssueCollectionException {
        issueService.updateIssue(id, issue);
        return new ResponseEntity<>("Updated issue with id " + id + "", HttpStatus.OK);
    }

    
}
