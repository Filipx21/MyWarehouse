'use strict'

var app = angular.module('warehouseApp', [
    'ngRoute',
    'product.service',
    'product.control'
]);

app.constant("CONSTANTS", {
	REST_SERVICE: 'http://localhost:8080/api/'
});


app.config(['$routeProvider',
    function ($routeProvider) {
		$routeProvider
			.when('/product', {
				templateUrl: 'template/products.html',
				controller: 'productCtrl as productCtrl'

			})
			.when('/product/{id}', {
				templateUrl: 'template/product.html',
				controller: 'productCtrl as productCtrl'

			})
			.when('/dodaj', {
				templateUrl: 'template/newProduct.html',
				controller: 'productCtrl as productCtrl'

			})
			.otherwise({
				redirectTo: '/'
			})
}]);
