var MainCtrl = phonebook.controller('ContactListCtrl', function($scope, filterFilter, DataService, $location) {

    var ctrlModel = $scope.model = {};
    ctrlModel.editClicked = false;
    ctrlModel.pageHeader = "Phonebook - Contact list";
    ctrlModel.itemsToDelete = [];
    ctrlModel.deleteSuccessMessage = "";
    ctrlModel.orderBy = "firstName"
    ctrlModel.markedForDeletion = "";
    DataService.getAllContacts().success(function(data, status) {
        ctrlModel.contacts = data;
        ctrlModel.filteredContacts = data;
    });

    $scope.deleteContact = function(contact) {
        ctrlModel.itemsToDelete.push(contact);
        ctrlModel.markedForDeletion = "text_" + contact.id;
        ctrlModel.showCancel = true;
    }

    $scope.cancel = function() {
        ctrlModel.itemsToDelete = [];
        $(".contactname").removeClass("markedForDeletion");
        ctrlModel.markedForDeletion = "";
        ctrlModel.showCancel = false;
    }
    $scope.viewContact = function(contactId) {
        $location.path("/view/contact/" + contactId);
    }

    $scope.clickEdit = function() {
        console.log($scope.buttonText);
        if ($scope.buttonText === "Done") {
            ctrlModel.editClicked = true;
        }
        else {
            ctrlModel.editClicked = false;
            if (ctrlModel.itemsToDelete.length > 0) {
                $scope.deleteContacts($scope.model.itemsToDelete);
            }
        }
    }

    $scope.addContact = function() {
        $location.path("/add/contact/");
    }
    $scope.deleteContacts = function(contactList) {
        if (ctrlModel.itemsToDelete.length > 0) {
            DataService.deleteContacts(JSON.stringify(contactList)).success(function(data, status) {
                ctrlModel.deleteSuccessMessage = data.responseMessage;
                $(".fadeOut").show();
                $(".fadeOut").fadeOut(3000, function() {
                });
                DataService.getAllContacts().success(function(data, status) {
                    ctrlModel.contacts = data;
                    ctrlModel.filteredContacts = data;
                    ctrlModel.itemsToDelete = [];
                });
            });
        }
    };
});

var ViewContactCtrl = phonebook.controller('ViewContactCtrl', function($scope, $routeParams, DataService, $location) {
    var ctrlModel = $scope.model = {};
    ctrlModel.backUpContactObj = {};



    DataService.getContact($routeParams.contactId).success(function(data) {
        ctrlModel.contactObj = data;
        ctrlModel.backUpContactObj = angular.copy(data);
        ctrlModel.pageHeader = ctrlModel.contactObj.firstName + " " + ctrlModel.contactObj.lastName;

    });

    $scope.saveContact = function(contactObjUpdated) {

        ctrlModel.fadeIn = false;
        DataService.updateContact(JSON.stringify(contactObjUpdated)).success(function(data) {
            ctrlModel.contactObj = data;
            ctrlModel.backUpContactObj = angular.copy(data);
            ctrlModel.pageHeader = ctrlModel.contactObj.firstName + " " + ctrlModel.contactObj.lastName;
            ctrlModel.updateSuccessMessage = "Record Updated";
            ctrlModel.fadeIn = true;
        })
    };
    $scope.goBack = function() {
        $location.path("/");
    }

    $scope.reset = function() {
        ctrlModel.contactObj = angular.copy(ctrlModel.backUpContactObj);
    };

});


var AddContactCtrl = phonebook.controller('AddContactCtrl', function($scope, $routeParams, DataService, $location) {
    var ctrlModel = $scope.model = {};
    ctrlModel.backUpContactObj = {};
    ctrlModel.contactObj = {};
    $scope.addContact = function(contactObjUpdated) {
        ctrlModel.createSuccessMessage = "";
        ctrlModel.fadeIn = false;
        DataService.createContact(JSON.stringify(contactObjUpdated)).success(function(data) {
            ctrlModel.contactObj = data;
            ctrlModel.backUpContactObj = angular.copy(data);
            ctrlModel.pageHeader = ctrlModel.contactObj.firstName + " " + ctrlModel.contactObj.lastName;
            ctrlModel.createSuccessMessage = "Contact Created";
            ctrlModel.fadeIn = true;
        })
    };
    $scope.goBack = function() {
        $location.path("/");
    }

    $scope.reset = function() {
        ctrlModel.contactObj = angular.copy(ctrlModel.backUpContactObj);
    };

});

