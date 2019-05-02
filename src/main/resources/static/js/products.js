angular.module('myapp.products', [])
    .controller('productCtrl', function ($scope, $http, $routeParams, cart) {

        var productsUrl = '/categories/'+$routeParams.id+'/products';
        $scope.products = [];

        loadProducts();

        function loadProducts() {
            $http({
                method: 'GET',
                url: productsUrl
            }).then(function (response) {
                $scope.products = response.data;
            })
        };

        $scope.addProductToCart = function (product) {
            cart.addProduct(product.id, product.name, product.price);
        }
    });
