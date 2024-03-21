package com.example.demo.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.dto.IssueRequest;
import com.example.demo.exception.IssueCollectionException;
import com.example.demo.model.IssueModel;
import com.example.demo.repository.IssueRepository;
import com.example.demo.service.IssueService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class IssueServiceImpl implements IssueService {

    private final IssueRepository issueRepo;

    @Override
    public List<IssueModel> getAllIssues() {
        List<IssueModel> issue = issueRepo.findAll();
        if (issue.size() > 0) {
            return issue;
        } else {
            return new ArrayList<IssueModel>();
        }
    }

    @Override
    public IssueModel getSingleIssueName(String id) throws IssueCollectionException {
        return issueRepo.findById(id)
                .orElseThrow(() -> new IssueCollectionException(IssueCollectionException.NotFoundException(id)));
    }

    @Override
    public void createIssue(IssueRequest request) throws IssueCollectionException {

        IssueModel issueModel = convertToIssueModel(request);

        validateIssueModel(issueModel);

        issueRepo.save(issueModel);
    }

    private IssueModel convertToIssueModel(IssueRequest request) {
        IssueModel issueModel = new IssueModel();
        issueModel.setIssueName(request.getIssueName());
        issueModel.setRecommend(request.getRecommend());
        issueModel.setListIssue(request.getListIssue());
        issueModel.setProperties(request.getProperties());
        return issueModel;
    }

    private void validateIssueModel(IssueModel issueModel) throws IssueCollectionException {
        if (issueModel.getIssueName() == null) {
            throw new IssueCollectionException(IssueCollectionException.IssueNameNotFoundException());
        }
        if (issueModel.getRecommend() == null) {
            throw new IssueCollectionException(IssueCollectionException.RecommendNotFoundException());
        }
        if (issueModel.getListIssue() == null) {
            throw new IssueCollectionException(IssueCollectionException.listIssueNotFoundException());
        }
        if (issueModel.getProperties() == null) {
            throw new IssueCollectionException("Properties are null");
        }

        Optional<IssueModel> existingIssue = issueRepo.findByIssue(issueModel.getIssueName());
        if (existingIssue.isPresent()) {
            throw new IssueCollectionException(IssueCollectionException.IssueAlreadyExists());
        }
    }

    @Override
    public void updateIssue(String id, IssueModel issue) throws IssueCollectionException {
        Optional<IssueModel> issueNameWithId = issueRepo.findById(id);
        Optional<IssueModel> issueNameWithSameName = issueRepo.findByIssue(issue.getIssueName());

        if (issueNameWithId.isPresent()) {
            if (issueNameWithSameName.isPresent() && !issueNameWithSameName.get().getId().equals(id)) {
                throw new IssueCollectionException(IssueCollectionException.IssueAlreadyExists());
            }

            IssueModel issueNameToUpdate = issueNameWithId.get();

            if (issue.getIssueName() != null) {
                issueNameToUpdate.setIssueName(issue.getIssueName());
            }
            if (issue.getRecommend() != null) {
                issueNameToUpdate.setRecommend(issue.getRecommend());
            }
            if (issue.getListIssue() != null) {
                issueNameToUpdate.setListIssue(issue.getListIssue());
            }
            if (issue.getProperties() != null) {
                issueNameToUpdate.setProperties(issue.getProperties());
            }

            issueRepo.save(issueNameToUpdate);
        } else {
            throw new IssueCollectionException(IssueCollectionException.NotFoundException(id));
        }
    }

    public void deleteIssueById(String id) throws IssueCollectionException {
        Optional<IssueModel> issueOptional = issueRepo.findById(id);
        if (!issueOptional.isPresent()) {
            throw new IssueCollectionException(IssueCollectionException.NotFoundException(id));
        } else {
            issueRepo.deleteById(id);
        }
    }
}
