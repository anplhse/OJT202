package com.example.demo.controller;

import java.io.IOException;
import java.time.LocalDate;

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

    @GetMapping("/ScenarioSessionModel")
    public ResponseEntity<?> getAllScenarios(
            @RequestParam(name = "startDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
            @RequestParam(name = "endDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate) {

        try {
            ScenarioPageResponse response = scenarioService.getAllScenarios(startDate, endDate);
            scenarioService.convertScenarioResponseToCsv(response);
            return new ResponseEntity<>("CSV file generated successfully", HttpStatus.OK);
        } catch (ScenarioCollectionException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (IOException e) {
            return new ResponseEntity<>("Error generating CSV file: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
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
