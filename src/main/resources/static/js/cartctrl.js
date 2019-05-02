angular.module('myapp.cartview', [])
    .controller('cartCtrl', function ($scope, cart) {

        $scope.cartData = cart.getProducts();
        $scope.total = 0;
        $scope.total = function () {
            var total = 0;
            for (var i = 0; i < $scope.cartData.length; i++) {
                total += ($scope.cartData[i].price * $scope.cartData[i].count);
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