angular.module('myapp.cartview', [])
    .controller('cartCtrl', function ($scope, cart) {

        $scope.items = cart.getProducts();
        $scope.total = 0;
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
            cart.getProducts().length = 0;
            cart.clear();
        };

    });