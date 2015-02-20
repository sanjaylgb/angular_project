/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sanjay.phonebookwebapp.controller;

import com.sanjay.phonebookwebapp.constants.ApplicationConstants;
import com.sanjay.phonebookwebapp.dao.ContactsDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author sanjay
 */
@WebServlet(name = "ContactsController", urlPatterns = {"/contacts"})
public class ContactsController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String operation = (String) request.getParameter(ApplicationConstants.OPERATION);

        if (operation.equals(ApplicationConstants.CONTACTS_GETALL)) {
            //URL= "/phonebook/contacts?operation=getAllContacts"
            response.getOutputStream().println(ContactsDAO.getContactListJson());
            response.getOutputStream().flush();

        }
        if (operation.equals(ApplicationConstants.CONTACTS_GETONE)) {
            //URL="/phonebook/contacts?operation=getContactById&contactId=1
            response.getOutputStream().println(ContactsDAO.getContactById(request.getParameter(ApplicationConstants.CONTACTS_CONTACTID)));
            response.getOutputStream().flush();

        }

    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String operation=request.getParameter(ApplicationConstants.OPERATION);
        
        if(operation.equals(ApplicationConstants.CREATE_CONTACT)){
            String contactJson=request.getParameter(ApplicationConstants.CONTACT_REQ_PARAM);
            String responseString=ContactsDAO.createContact(contactJson);
            response.getOutputStream().println(responseString);
            response.getOutputStream().flush();
        }if(operation.equals(ApplicationConstants.UPDATE_CONTACT)){
            String contactJson=request.getParameter(ApplicationConstants.CONTACT_REQ_PARAM);
            String responseString=ContactsDAO.updateContact(contactJson);
            response.getOutputStream().println(responseString);
            response.getOutputStream().flush();
        }if(operation.equals(ApplicationConstants.DELETE_CONTACTS)){
            String contactJson=request.getParameter(ApplicationConstants.CONTACT_REQ_PARAM);
            String responseString=ContactsDAO.deleteContacts(contactJson);
            response.getOutputStream().println(responseString);
            response.getOutputStream().flush();
        }
        
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "ContactsController";
    }// </editor-fold>

}
