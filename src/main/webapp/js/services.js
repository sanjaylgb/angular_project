phonebook.factory('DataService', function($http) {
    return{
        getAllContacts: function() {
            var httpPromise = $http.get(CONTACTS_SERVICE_ROOT + GET_QUERY_PARAMS.getAllContacts, {
                headers: {
                    "Content-Type": "application/x-www-form-urlencoded"
                }
            });
            return httpPromise;
        },
        deleteContacts:function(contactListJsonString){
            var req={
                method:'POST',
                url: CONTACTS_SERVICE_ROOT,
                headers: {
                    "Content-Type": "application/x-www-form-urlencoded"
                },
                data:GET_QUERY_PARAMS.deleteContacts.replace("::contactJsonString::",contactListJsonString)
            };
            return $http(req);
        },
        getContact:function(contactId){
            var req={
                method:'GET',
                url: CONTACTS_SERVICE_ROOT +GET_QUERY_PARAMS.getContactById.replace("::contactId::",contactId)
            };
            return $http(req);
        },
        updateContact: function(contactObjJsonString){
            var req={
                method: 'POST',
                url: CONTACTS_SERVICE_ROOT,
                headers:{
                    "Content-Type": "application/x-www-form-urlencoded"
                },
                data:GET_QUERY_PARAMS.updateContact.replace("::contactJsonString::", contactObjJsonString)
            };
            
            return $http(req);
        },
        createContact: function(contactObjJsonString){
            var req={
                method: 'POST',
                url: CONTACTS_SERVICE_ROOT,
                headers:{
                    "Content-Type": "application/x-www-form-urlencoded"
                },
                data:GET_QUERY_PARAMS.createContact.replace("::contactJsonString::", contactObjJsonString)
            };
            
            return $http(req);
        }
    };
});