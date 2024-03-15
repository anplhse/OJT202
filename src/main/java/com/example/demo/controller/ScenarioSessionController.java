package com.example.demo.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.ScenarioPageResponse;
import com.example.demo.exception.ScenarioCollectionException;
import com.example.demo.model.ScenarioSessionModel;
import com.example.demo.service.ScenarioService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ScenarioSessionController {

    private final ScenarioService scenarioService;

    @PostMapping("/ScenarioSessionModel")
    public ResponseEntity<?> createScenario(@RequestBody ScenarioSessionModel scenario)
            throws ScenarioCollectionException {
        scenarioService.createScenario(scenario);
        return new ResponseEntity<ScenarioSessionModel>(scenario, HttpStatus.OK);
    }

    @GetMapping("/ScenarioSessionModel/byDateRange")
    public ResponseEntity<?> getScenariosByDateRange(
            @RequestParam("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
            @RequestParam("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate)
            throws ScenarioCollectionException {
        List<ScenarioSessionModel> scenario = scenarioService.getScenariosByDateRange(startDate, endDate);
        return new ResponseEntity<>(scenario, scenario.size() > 0 ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @GetMapping("/ScenarioSessionModel")
    public ResponseEntity<?> getAllScenarios(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) throws Exception{
        Pageable pageable = PageRequest.of(page, size);
        ScenarioPageResponse response = scenarioService.getAllScenarios(pageable);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/ScenarioSessionModel/{id}")
    public ResponseEntity<?> getScenarioById(@PathVariable("id") String id) throws ScenarioCollectionException {
        return new ResponseEntity<>(scenarioService.getSingleScenario(id), HttpStatus.OK);
    }

    @DeleteMapping("/ScenarioSessionModel/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") String id) throws ScenarioCollectionException {
        scenarioService.deleteScenarioById(id);
        return new ResponseEntity<>("Successfully deleted issue with id " + id, HttpStatus.OK);
    }

    @PutMapping("/ScenarioSessionModel/{id}")
    public ResponseEntity<?> updateById(@PathVariable("id") String id, @RequestBody ScenarioSessionModel scenario)
            throws ScenarioCollectionException {
        scenarioService.updateScenario(id, scenario);
        return new ResponseEntity<>("Updated issue with id " + id + "", HttpStatus.OK);
    }

}
