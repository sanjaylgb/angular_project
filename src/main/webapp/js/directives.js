phonebook.directive('togglebutton', function() {
    return{
        restrict: 'E',
        templateUrl: 'templates/directive/togglebutton.html',
        link: function(scope, element, attrs) {
            scope.buttonText = attrs.initialtext;
            scope.toggleIt = function() {
                if (scope.buttonText === attrs.initialtext) {
                    scope.buttonText = attrs.toggletext;
                }
                else if (scope.buttonText === attrs.toggletext) {
                    scope.buttonText = attrs.initialtext;
                }
            }
        }
    }

});

phonebook.directive('fadeIn', function($animate){
    return function(scope, element, attrs){
        scope.$watch(attrs.fadeIn, function(newVal){
            if(newVal){
                $animate.addClass(element,"fadeIn");
            }else{
                $animate.removeClass(element, "fadeIn")
            }
        });
    };
});
phonebook.directive('markedForDeletion',function(){
    return function(scope, elem, attrs){
        scope.$watch(attrs.markedForDeletion, function(newVal){
            if(newVal!==""){
                $("#"+newVal).addClass("markedForDeletion");
            }
        })
    }
})


phonebook.animation(".fadeIn",function(){
    return {
        addClass:function(element, className){
            $(element).show();
                $(element).fadeOut(3000, function() {
                    $(element).removeClass("fadeIn");
                });
        }
    }
})