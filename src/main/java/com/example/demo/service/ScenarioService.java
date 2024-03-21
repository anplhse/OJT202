package com.example.demo.service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import com.example.demo.dto.ScenarioPageResponse;
import com.example.demo.exception.ScenarioCollectionException;
import com.example.demo.model.ScenarioSessionModel;

public interface ScenarioService {

    public List<ScenarioSessionModel> getAllScenarios();

    public ScenarioSessionModel getSingleScenario(String id) throws ScenarioCollectionException;

    public void createScenario(ScenarioSessionModel scenario) throws ScenarioCollectionException;

    public void updateScenario(String id, ScenarioSessionModel scenario) throws ScenarioCollectionException;

    public void deleteScenarioById(String id) throws ScenarioCollectionException;

    public ScenarioPageResponse getAllScenarios(LocalDate startDate, LocalDate endDate)
            throws ScenarioCollectionException;

    public void convertScenarioResponseToCsv(ScenarioPageResponse response, String outputPath) throws IOException;
}
