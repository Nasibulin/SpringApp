angular.module('myapp.cart', [])
    .controller('cartCtrl', ['$scope', 'ShoppingCart', '$state', function ($scope, cart, $state) {

        $scope.items = cart.getProducts();
        // $scope.total = 0;
        $scope.total = function () {
            var total = 0;
            for (var i = 0; i < $scope.items.length; i++) {
                total += ($scope.items[i].price * $scope.items[i].count);
            }
            return total;
        }

        $scope.goCheckOut = function () {
            $state.go('checkoutdetails');
        };

        $scope.clearCart = function () {
            $scope.items.length = 0;
            cart.clear();
        };

    }]);