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
		self.showDetails = showDetails;
		self.submit = submit;
		self.resetForm = resetForm;
		self.goBack = goBack;
		self.optionList = true;
		self.optionEdit = false;
		self.optionDetails = false;


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
			ProductService.fetchProduct(id)
				.then(
					function (object) {
						self.product = object.data;
						console.log(self.product);
					},
					function (errResponse) {
						console.log('Error while fetching product: ' + errResponse);
					}
				);
		}

		function showDetails(id) {
			if (id === null) {
				console.log("id null");
			}
			fetchProduct(id);
			self.optionList = false;
			self.optionDetails = true;
		}

		function createProduct(product) {
			console.log(product);
			ProductService.createProduct(product)
				.then(
					console.log('1'),
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

		}

		function resetForm() {
			reset();
			$scope.productForm.$setPristine();
		}

		function submit() {
			if (self.product.id === null) {
				createProduct(self.product);
			} else {
				updateProduct(self.product);
				reset();
				self.optionList = true;
				self.optionEdit = false;
			}
		}

		function editProduct(id) {
			self.optionList = false;
			self.optionEdit = true;
			for (var i = 0; i < self.products.length; i++) {
				if (self.products[i].id === id) {
					self.product = angular.copy(self.products[i]);
					break;
				}
			}
		}

		function removeProduct(id) {
			if (self.product.id === null) {
				reset();
			}
			deleteProduct(id);
		}

		function goBack() {
			reset();
			self.optionDetails = false;
			self.optionEdit = false;
			self.optionList = true;
		}

}]);
