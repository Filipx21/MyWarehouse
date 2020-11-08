'use strict'

var app = angular.module('warehouse', [
    'ngRoute'
]);

app.constant("CONSTANTS", {
    REST_SERVICE: 'http://localhost:8080/api/'
});

app.config(['$routeProvider',
    function ($routeProvider) {
        $routeProvider



            .otherwise({
                redirectTo:'/'
            })
    }
]);