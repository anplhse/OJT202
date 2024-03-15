package com.example.demo.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.model.ScenarioSessionModel;

@Repository
public interface ScenarioRepository extends MongoRepository<ScenarioSessionModel, String> {

    List<ScenarioSessionModel> findByCreatedAtBetween(LocalDateTime startDate, LocalDateTime endDate);

    @Query("{'scenario': ?0}")
    Optional<ScenarioSessionModel> findByScenario(String scenario);
}
