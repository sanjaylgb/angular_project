/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var phonebook=angular.module('phonebook',['ngRoute', 'ngAnimate']);

phonebook.config(function ($routeProvider){
   $routeProvider.when("/",{
     templateUrl:"templates/main.html",
     controller: "ContactListCtrl",
   }).when("/view/contact/:contactId",{
       templateUrl:"templates/viewcontact.html",
       controller:"ViewContactCtrl"
   }).when("/add/contact/",{
      templateUrl:"templates/addcontact.html",
      controller: "AddContactCtrl"
   });
});