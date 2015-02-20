/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sanjay.phonebookwebapp.dao;

import com.sanjay.phonebookwebapp.constants.ApplicationConstants;
import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author sanjay
 */
public class ContactsDAO {

    public static Map<Integer, Contact> contactMap = new HashMap<Integer, Contact>();

    //Populate contacts
    static {
        initContactList();
    }

    private static void initContactList() {
        Contact contact;

        //1
        contact = new Contact("Sanjay", "Bhavnani", "sanj@abcd.com", "123-456-7890", "789-045-6123", contactMap.size() + 1);
        contactMap.put(contactMap.size() + 1, contact);

        //2
        contact = new Contact("Kiran", "Tarachandani", "kt@abcd.com", "612-442-1234", "001-789-0987", contactMap.size() + 1);
        contactMap.put(contactMap.size() + 1, contact);

        //3
        contact = new Contact("Akshath", "Kumar", "ak@abcd.com", "123-098-7789", "410-123-4567", contactMap.size() + 1);
        contactMap.put(contactMap.size() + 1, contact);
    }

    public static String getContactListJson() {
        JSONSerializer jsonSerializer = new JSONSerializer();
        return jsonSerializer.exclude("*.class", "email", "homePhone", "mobile").serialize(new ArrayList<Contact>(contactMap.values()));
    }

    public static String getContactById(String parameter) {
        JSONSerializer jsonSerializer = new JSONSerializer();
        Integer contactId;
        try {
            contactId = Integer.parseInt(parameter);
        } catch (NumberFormatException exc) {
            return jsonSerializer.exclude("*.class").serialize(new PhoneBookCustomError("Non numeric contact id was passed", ApplicationConstants.INVALID_SERVICE_INPUT));
        }
        Contact contact = contactMap.get(contactId);
        if (contact != null) {
            return jsonSerializer.exclude("*.class").serialize(contact);

        } else {
            return jsonSerializer.exclude("*.class").serialize(new PhoneBookCustomError("Contact with Id " + parameter + " was not found", ApplicationConstants.NOT_FOUND));
        }

    }

    public static String createContact(String contactJson) {
        JSONDeserializer<Contact> deserializer = new JSONDeserializer<Contact>();
        Contact contactObj = deserializer.deserialize(contactJson, Contact.class);

        System.out.println(contactMap.size() + 1);
        contactObj.setId(contactMap.size() + 1);
        contactMap.put(contactObj.getId(), contactObj);

        JSONSerializer serializer = new JSONSerializer();
        return serializer.exclude("*.class").serialize(contactObj);

    }

    public static String updateContact(String contactJson) {
        JSONDeserializer<Contact> deserializer = new JSONDeserializer<Contact>();
        Contact contactObj = deserializer.deserialize(contactJson, Contact.class);

        contactMap.put(contactObj.getId(), contactObj);

        JSONSerializer serializer = new JSONSerializer();
        return serializer.exclude("*.class").serialize(contactObj);

    }

    public static String deleteContact(String contactJson) {
        JSONDeserializer<Contact> deserializer = new JSONDeserializer<Contact>();
        JSONSerializer jsonSerializer = new JSONSerializer();

        Contact contactObj = deserializer.deserialize(contactJson, Contact.class);
        if (contactMap.get(contactObj.getId()) != null) {
            contactMap.remove(contactObj.getId());
            String responseString = jsonSerializer.exclude("*.class").serialize(new SuccessResponse("Record deleted"));
            return responseString;
        } else {
            return jsonSerializer.exclude("*.class").serialize(new PhoneBookCustomError("Contact with Id " + contactObj.getId() + " was not found", ApplicationConstants.NOT_FOUND));

        }
    }

    public static String deleteContacts(String contactsJson) {
        JSONDeserializer<List<Map>> deserializer = new JSONDeserializer<List<Map>>();
        JSONSerializer jsonSerializer = new JSONSerializer();
        List<Map> contactIdList = deserializer.deserialize(contactsJson);
        Map<Integer, Contact> mapBackUp = new HashMap<Integer, Contact>(contactMap);
        for (int i=0;i<contactIdList.size();i++) {
            Integer contactId=(Integer)contactIdList.get(i).get("id");
            
            if (contactMap.get(contactId) != null) {
                contactMap.remove(contactId);
            } else {
                contactMap = mapBackUp;
                return jsonSerializer.exclude("*.class").serialize(new PhoneBookCustomError("Contact with Id " + contactId + " was not found. No Records deleted", ApplicationConstants.NOT_FOUND));
            }
        }
        return jsonSerializer.exclude("*.class").serialize(new SuccessResponse("Record(s) deleted"));
    }

}
