/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sanjay.phonebookwebapp.dao;

/**
 *
 * @author sanjay
 */
public class PhoneBookCustomError {
    private String errorMessage;
    private String errorType;

    public PhoneBookCustomError() {
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public void setErrorType(String errorType) {
        this.errorType = errorType;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public String getErrorType() {
        return errorType;
    }

    public PhoneBookCustomError(String errorMessage, String errorType) {
        this.errorMessage = errorMessage;
        this.errorType = errorType;
    }
}
