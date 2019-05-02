angular.module('myapp.cartview',[])
    .controller('cartCtrl', function ($scope, cart) {

        // $scope.totalBill = 0;
        // $scope.items = [];
        // $scope.items = cart.getProducts();
        $scope.cartData = cart.getProducts();
        console.log($scope.cartData);
        $scope.total = function () {
            var total = 0;
            for (var i = 0; i < $scope.cartData.length; i++) {
                total += ($scope.cartData[i].price * $scope.cartData[i].count);
            }
            return total;
        }

        // $scope.calculateTotal = function () {
        //     var itemList = cart.getItems();
        //     if (typeof itemList !== 'undefined') {
        //         for (var i = 0; i < itemList.length; i++) {
        //             $scope.totalBill += itemList[i].price * itemList[i].;
        //         }
        //     }
        //
        // };
        //
        // $scope.calculateTotal();
        //
        // $scope.goCheckOut = function () {
        //     $state.go('checkoutdetails');
        // };

    });