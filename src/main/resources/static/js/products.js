angular.module('myapp.products', [])
    .controller('productCtrl', ['$scope', '$state', '$http', '$stateParams', 'ShoppingCart', function ($scope, $state, $http, $stateParams, cart) {

        var productsUrl = '/api/categories/' + $stateParams.id + '/products';
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
            // cart.addProduct(product.id, product.partNumber, product.description, product.price);
            cart.addProduct(product.id, product.partNumber, product.description, product.price);
            // $state.reload();
        }
        $scope.removeProductFromCart = function (product) {
            cart.removeProduct(product.id);
            // $state.reload();
        }
    }]);
