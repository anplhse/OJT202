package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.example.demo.model.IssueModel;

public interface IssueRepository extends MongoRepository<IssueModel, String> {
    @Query("{'issue': ?0}")
	Optional<IssueModel> findByIssue(String issue);
}


