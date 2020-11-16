'use strict'

var module = angular.module('product.control', []);
module.controller("productCtrl", ["$scope", '$http', '$filter', '$location', "ProductService",
    function ($scope, $http, $filter, $location, ProductService) {

		var self = this;

		self.product = {
			id: null,
			name: null,
			quantity: null,
			price: null,
			producer: null,
			category: null,
			photoUri: null,
			opinion: null
		};


		self.products = [];

		self.editProduct = editProduct;
		self.removeProduct = removeProduct;
		self.submit = submit;
		self.reset = reset;
		self.option = true;

		fetchAllProducts();

		function fetchAllProducts() {
			ProductService.fetchAllProducts()
				.then(
					function (list) {
						self.products = list;
					},
					function (errResponse) {
						console.log('Error while fetching products: ' + errResponse);
					}
				);
		}

		function fetchProduct(id) {
			console.log('dziala');
			self.option = false;
			ProductService.fetchProduct(id)
				.then(
					function (object) {
						self.product = object;
					},
					function (errResponse) {
						console.log('Error while fetching product: ' + errResponse);
					}
				);
		}

		function createProduct(product) {
			ProductService.createProduct(product)
				.then(
					fetchAllProducts,
					function (errResponse) {
						console.log('Error while creating product: ' + errResponse);
					}
				);
		}

		function updateProduct(product) {
			ProductService.updateProduct(product)
				.then(
					fetchAllProducts,
					function (errResponse) {
						console.log('Error while updating product: ' + errResponse);
					}
				);
		}

		function deleteProduct(id) {
			ProductService.deleteProduct(id)
				.then(
					fetchAllProducts,
					function (errResponse) {
						console.log('Error while deleting product: ' + errResponse);
					}
				);
		}

		function reset() {
			self.product = {
				id: null,
				name: null,
				quantity: null,
				price: null,
				producer: null,
				category: null,
				photoUri: null,
				opinion: null
			};
			$scope.productForm.$setPristine();
		}

		function submit() {
			if (self.product.id === null) {
				createProduct(self.product);
			} else {
				updateProduct(self.product);
				reset();
			}
		}

		function editProduct(id) {
			self.option = false;
			for (var i = 0; i < self.products.length; i++) {
				if (self.products[i].id === id) {
					self.product = angular.copy(self.products[i]);
					break;
				}
			}
		}

		function removeProduct() {
			if (self.product.id === null) {
				reset();
			}
			deleteProduct(id);
		}

}]);
