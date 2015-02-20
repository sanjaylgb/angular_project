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
public class SuccessResponse {
    private String responseMessage;

    public SuccessResponse(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public String getResponseMessage() {
        return responseMessage;
    }
    
}
