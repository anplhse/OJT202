package com.example.demo.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.dto.PageInfo;
import com.example.demo.dto.ScenarioPageResponse;
import com.example.demo.exception.ScenarioCollectionException;
import com.example.demo.model.ScenarioSessionModel;
import com.example.demo.repository.ScenarioRepository;
import com.example.demo.service.ScenarioService;

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
    public List<ScenarioSessionModel> getScenariosByDateRange(LocalDate startDate, LocalDate endDate)
            throws ScenarioCollectionException {
        if (startDate == null || endDate == null || startDate.isAfter(endDate)) {
            throw new ScenarioCollectionException("Invalid date range");
        }

        try {
            List<ScenarioSessionModel> scenario = scenarioRepo.findByCreatedAtBetween(startDate.atStartOfDay(),
                    endDate.atTime(23, 59, 59));
            return scenario;
        } catch (Exception e) {
            throw new ScenarioCollectionException("Error retrieving scenarios by date range: " + e.getMessage());
        }
    }

    @Override
    public ScenarioPageResponse getAllScenarios(Pageable pageable) {
        Page<ScenarioSessionModel> scenarioPage = scenarioRepo.findAll(pageable);

        PageInfo pageInfo = new PageInfo(
                scenarioPage.getNumber(),
                scenarioPage.getSize(),
                scenarioPage.getTotalElements(),
                scenarioPage.getTotalPages());

        return new ScenarioPageResponse(scenarioPage.getContent(), pageInfo);
    }

}