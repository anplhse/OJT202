package com.example.demo.service.impl;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.dto.ScenarioPageResponse;
import com.example.demo.exception.ScenarioCollectionException;
import com.example.demo.model.ScenarioSessionModel;
import com.example.demo.repository.ScenarioRepository;
import com.example.demo.service.ScenarioService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.opencsv.CSVWriter;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ScenarioServiceImpl implements ScenarioService {

    private final ScenarioRepository scenarioRepo;

    @Override
    public List<ScenarioSessionModel> getAllScenarios() {
        List<ScenarioSessionModel> scenario = scenarioRepo.findAll();
        if (scenario.size() > 0) {
            return scenario;
        } else {
            return new ArrayList<ScenarioSessionModel>();
        }
    }

    @Override
    public ScenarioSessionModel getSingleScenario(String id) throws ScenarioCollectionException {
        return scenarioRepo.findById(id)
                .orElseThrow(() -> new ScenarioCollectionException(ScenarioCollectionException.NotFoundException(id)));
    }

    @Override
    public void createScenario(ScenarioSessionModel scenario) throws ScenarioCollectionException {
        Optional<ScenarioSessionModel> scenarioOptional = scenarioRepo.findByScenario(scenario.getChartId());
        if (scenarioOptional.isPresent()) {
            throw new ScenarioCollectionException(ScenarioCollectionException.ScenarioSessionAlreadyExist());
        } else if (scenario.getChartId() == null) {
            throw new ScenarioCollectionException(ScenarioCollectionException.ScenarioSessionNotFoundException());
        } else if (scenario.getContract() == null) {
            throw new ScenarioCollectionException(ScenarioCollectionException.ContractNotFoundException());
        } else if (scenario.getSessionId() == null) {
            throw new ScenarioCollectionException(ScenarioCollectionException.SessionIdNotFoundException());
        } else if (scenario.getEmail() == null) {
            throw new ScenarioCollectionException(ScenarioCollectionException.EmailNotFoundException());
        } else {

            scenario.setChartId(scenario.getChartId());
            scenario.setContract(scenario.getContract());
            scenario.setSessionId(scenario.getSessionId());
            scenario.setFinished(scenario.getFinished());
            scenario.setCurrentScenario(scenario.getCurrentScenario());
            scenario.setEmail(scenario.getEmail());
            scenario.setAnalysisBefore(scenario.getAnalysisBefore());
            scenario.setAnalysisAfter(scenario.getAnalysisAfter());
            scenario.setCreatedAt(new Date(System.currentTimeMillis()));

            scenarioRepo.save(scenario);
        }
    }

    @Override
    public void updateScenario(String id, ScenarioSessionModel scenario) throws ScenarioCollectionException {
        Optional<ScenarioSessionModel> scenarioWithId = scenarioRepo.findById(id);
        Optional<ScenarioSessionModel> scenarioWithSameChartId = scenarioRepo.findByScenario(scenario.getChartId());

        if (scenarioWithId.isPresent()) {
            if (scenarioWithSameChartId.isPresent() && !scenarioWithSameChartId.get().getId().equals(id)) {
                throw new ScenarioCollectionException(ScenarioCollectionException.ScenarioSessionAlreadyExist());
            }

            ScenarioSessionModel scenarioToUpdate = scenarioWithId.get();

            if (scenario.getChartId() != null) {
                scenarioToUpdate.setChartId(scenario.getChartId());
            }
            if (scenario.getContract() != null) {
                scenarioToUpdate.setContract(scenario.getContract());
            }
            if (scenario.getSessionId() != null) {
                scenarioToUpdate.setSessionId(scenario.getSessionId());
            }
            if (scenario.getFinished() != null) {
                scenarioToUpdate.setFinished(scenario.getFinished());
            }
            if (scenario.getCurrentScenario() != null) {
                scenarioToUpdate.setCurrentScenario(scenario.getCurrentScenario());
            }
            if (scenario.getEmail() != null) {
                scenarioToUpdate.setEmail(scenario.getEmail());
            }
            if (scenario.getAnalysisBefore() != null) {
                scenarioToUpdate.setAnalysisBefore(scenarioToUpdate.getAnalysisBefore());
            }
            if (scenario.getAnalysisAfter() != null) {
                scenarioToUpdate.setAnalysisAfter(scenarioToUpdate.getAnalysisAfter());
            }

            scenarioRepo.save(scenarioToUpdate);
        } else {
            throw new ScenarioCollectionException(ScenarioCollectionException.NotFoundException(id));
        }
    }

    public void deleteScenarioById(String id) throws ScenarioCollectionException {
        Optional<ScenarioSessionModel> scenarioOptional = scenarioRepo.findById(id);
        if (!scenarioOptional.isPresent()) {
            throw new ScenarioCollectionException(ScenarioCollectionException.NotFoundException(id));
        } else {
            scenarioRepo.deleteById(id);
        }
    }

    @Override
    public ScenarioPageResponse getAllScenarios(LocalDate startDate, LocalDate endDate)
            throws ScenarioCollectionException {
        try {
            Pageable pageable = Pageable.unpaged();
            Page<ScenarioSessionModel> scenarioPage;

            if (startDate != null && endDate != null) {
                scenarioPage = scenarioRepo.findByCreatedAtBetween(startDate.atStartOfDay(), endDate.atTime(23, 59, 59),
                        pageable);
            } else {
                scenarioPage = scenarioRepo.findAll(pageable);
            }
            return new ScenarioPageResponse(scenarioPage.getContent());
        } catch (Exception e) {
            throw new ScenarioCollectionException("Error retrieving scenarios: " + e.getMessage());
        }
    }

    @Override
    public void convertScenarioResponseToCsv(ScenarioPageResponse response, String outputPath) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        List<ScenarioSessionModel> scenarios = response.getContent();

        try (CSVWriter writer = new CSVWriter(new FileWriter(outputPath))) {

            writer.writeNext(new String[] { "ID", "Chart ID", "Contract", "Session ID", "Finished", "Current Scenario",
                    "Email", "Analysis Before", "Analysis After", "Created At" });

            for (ScenarioSessionModel scenario : scenarios) {
                String[] data = new String[] {
                        scenario.getId(),
                        scenario.getChartId(),
                        scenario.getContract(),
                        scenario.getSessionId(),
                        String.valueOf(scenario.getFinished()),
                        String.valueOf(scenario.getCurrentScenario()),
                        scenario.getEmail(),
                        objectMapper.writeValueAsString(scenario.getAnalysisBefore()),
                        objectMapper.writeValueAsString(scenario.getAnalysisAfter()),
                        String.valueOf(scenario.getCreatedAt())
                };
                writer.writeNext(data);
            }
        }
    }
}