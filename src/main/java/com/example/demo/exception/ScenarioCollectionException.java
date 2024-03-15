package com.example.demo.exception;

public class ScenarioCollectionException extends Exception {

    private static final long serialVersionUID = 1L;

    public ScenarioCollectionException(String message) {
        super(message);
    }

    public static String NotFoundException(String id) {
        return "ScenarioSession with id " + id + "not found";
    }

    public static String ScenarioSessionAlreadyExist() {
        return "ScenarioSession with given chartId already exists";
    }

    public static String ScenarioSessionNotFoundException(){
        return "ScenarioSession with given chartId not found";
    }

    public static String ContractNotFoundException() {
        return "contract not found";
    }

    public static String SessionIdNotFoundException() {
        return "session id not found";
    }

    public static String EmailNotFoundException() {
        return "email not found";
    }

}