package com.appoinment.errors;

public class ErrorService extends Exception {

    public ErrorService(String msj) {
        super(msj);
    }
}
