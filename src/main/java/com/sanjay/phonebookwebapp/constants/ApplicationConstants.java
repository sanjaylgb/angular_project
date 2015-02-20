/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sanjay.phonebookwebapp.constants;

/**
 *
 * @author sanjay
 */
public class ApplicationConstants {

    //Operation key
    public static final String OPERATION = "operation";
    
    //Operation values
    public static final String CONTACTS_GETALL = "getAllContacts";
    public static final String CONTACTS_GETONE = "getContactById";
    public static final String CREATE_CONTACT = "createContact";
    public static final String UPDATE_CONTACT="updateContact";
    public static final String DELETE_CONTACTS="deleteContacts";
    
    //Request params
    public static final String CONTACTS_CONTACTID = "contactId";
    public static final String CONTACT_REQ_PARAM="contact";
    //Errors
    public static final String INVALID_SERVICE_INPUT = "INVALID_SERVICE_INPUT";
    public static final String NOT_FOUND = "DATA_NOT_FOUND";

}
