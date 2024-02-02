package com.example.demo.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exception.IssueCollectionException;
import com.example.demo.model.IssueModel;
import com.example.demo.repository.IssueRepository;
import com.example.demo.service.IssueService;

@Service
public class IssueServiceImpl implements IssueService {

    @Autowired
    private IssueRepository issueRepo;

    @Override
    public List<IssueModel> getAllIssues() {
        List<IssueModel> issues = issueRepo.findAll();
        if (issues.size() > 0) {
            return issues;
        } else {
            return new ArrayList<IssueModel>();
        }
    }

    @Override
    public IssueModel getIssueById(String id) throws IssueCollectionException {
        Optional<IssueModel> issueOptional = issueRepo.findById(id);
        if (!issueOptional.isPresent()) {
            throw new IssueCollectionException(IssueCollectionException.NotFoundException(id));
        } else {
            return issueOptional.get();
        }
    }

    @Override
    public void createIssue(IssueModel issue) throws IssueCollectionException {
        Optional<IssueModel> issueOptional = issueRepo.findByIssue(issue.getIssueName());
        if (issueOptional.isPresent()) {
            throw new IssueCollectionException(IssueCollectionException.IssueAlreadyExists());
        } else {
            issue.setIssueName("Sai MAC");
            issue.setRecommend("Hỗ trợ KH reset MAC trên tool inside");
            List<String> issues = new ArrayList<>();
            issues.add("WiFi kết nối được nhưng không truy cập được internet");
            issues.add("Không truy cập được một số dịch vụ hoặc website");
            issue.setListIssue(issues);
            issueRepo.save(issue);
        }
    }
    

    @Override
    public void updateIssue(String id, IssueModel updatedIssue) throws IssueCollectionException {
        Optional<IssueModel> issueWithId = issueRepo.findById(id);
        if (issueWithId.isPresent()) {
            
            Optional<IssueModel> issueWithSameName = issueRepo.findByIssue(updatedIssue.getIssueName());
            if (issueWithSameName.isPresent() && !issueWithSameName.get().getId().equals(id)) {
                throw new IssueCollectionException(IssueCollectionException.IssueAlreadyExists());
            }
            
            IssueModel issueToUpdate = issueWithId.get();
            issueToUpdate.setIssueName(updatedIssue.getIssueName());
            issueToUpdate.setRecommend(updatedIssue.getRecommend());
            
            
            issueRepo.save(issueToUpdate);
        } else {
            throw new IssueCollectionException(IssueCollectionException.NotFoundException(id));
        }
    }
    

    public void deleteIssue(String id) throws IssueCollectionException{
        Optional<IssueModel> todoOptional = issueRepo.findById(id);
        if (!todoOptional.isPresent()) {
            throw new IssueCollectionException(IssueCollectionException.NotFoundException(id));
        } else {
            issueRepo.deleteById(id);
        }
    }
}
