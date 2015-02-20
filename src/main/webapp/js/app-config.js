
var CONTACTS_SERVICE_ROOT = "http://localhost:8080/phonebook/contacts";
var GET_QUERY_PARAMS ={getAllContacts: "?operation=getAllContacts",
        getContactById: "?operation=getContactById&contactId=::contactId::",
        updateContact:"operation=updateContact&contact=::contactJsonString::",
        createContact:"operation=createContact&contact=::contactJsonString::",
        deleteContacts:"operation=deleteContacts&contact=::contactJsonString::"
    }