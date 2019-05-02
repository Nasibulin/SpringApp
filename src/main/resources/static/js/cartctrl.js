angular.module('myapp.cartview',[])
    .controller('cartCtrl', function ($scope, cart) {

        // $scope.totalBill = 0;
        // $scope.items = [];
        // $scope.items = cart.getProducts();
        $scope.cartData = cart.getProducts();
        console.log($scope.cartData);
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