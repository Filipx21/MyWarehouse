'use strict'

angular.module('product.service', [])
    .factory("ProductService", ["$http", "$timeout", "$q", "CONSTATNTS", function($http, $timeout, $q, CONSTATNTS) {

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
            $http.get(CONSTATNTS.REST_SERVICE + 'product/products')
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
            $http.post(CONSTATNTS.REST_SERVICE + 'product/product', product)
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
            $http.put(CONSTATNTS.REST_SERVICE + 'product/product', product)
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
            $http.delete(CONSTATNTS.REST_SERVICE + 'product/product/' + id)
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

        function fetchProduct() {
            var deferred = $q.defer();
            $http.get(CONSTATNTS.REST_SERVICE + 'product/product/' + id)
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