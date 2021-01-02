'use strict'

angular.module('product.service', [])
	.factory("ProductService", ["$http", "$timeout", "$q", "CONSTANTS", function ($http, $timeout, $q, CONSTANTS) {

		var productServiceVar = {
			fetchAllProducts: fetchAllProducts,
			createProduct: createProduct,
			updateProduct: updateProduct,
			deleteProduct: deleteProduct,
			fetchProduct: fetchProduct
		};

		return productServiceVar;

		function fetchAllProducts() {
			var deferred = $q.defer();
			$http.get('http://localhost:8080/api/product/products')
				.then(
					function (response) {
						deferred.resolve(response.data);
					},
					function (errResponse) {
						deferred.reject(errResponse);
					}
				);
			return deferred.promise;
		}

		function createProduct(product) {
			var deferred = $q.defer();
			$http.post(CONSTANTS.REST_SERVICE + 'product/product', product)
				.then(
					function (response) {
						deferred.resolve(response.data);
					},
					function (errResponse) {
						deferred.reject(errResponse);
					}
				);
			return deferred.promise;
		}

		function updateProduct(product) {
			var deferred = $q.defer();
			$http.put(CONSTANTS.REST_SERVICE + 'product/product', product)
				.then(
					function (response) {
						deferred.resolve(response);
					},
					function (errResponse) {
						deferred.reject(errResponse);
					}
				);
			return deferred.promise;
		}

		function deleteProduct(id) {
			var deferred = $q.defer();
			$http.delete(CONSTANTS.REST_SERVICE + 'product/product/' + id)
				.then(
					function (response) {
						deferred.resolve(response);
					},
					function (errResponse) {
						deferred.reject(errResponse);
					}
				);
			return deferred.promise;
		}

		function fetchProduct(id) {
			var deferred = $q.defer();
			$http.get(CONSTANTS.REST_SERVICE + 'product/product/' + id)
				.then(
					function (response) {
						deferred.resolve(response);
					},
					function (errResponse) {
						deferred.reject(errResponse);
					}
				);
			return deferred.promise;
		}

    }]);
