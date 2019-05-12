'use strict';
angular.module('myapp.orderlist', ['myapp.authen'])
    .constant('_', window._)
    .controller('orderlistCtrl', ['$scope', 'util','$timeout', '$stateParams', '_', 'Session', '$cookies', '$state', function ($scope, util, $timeout, $stateParams, _, Session, $cookies, $state) {

        // Checking user already login
        if (!Session.getAccessToken() && !Session.getUser()) {
            $state.go('login');
            return;
        }

        $scope.currentUser = Session.getUser();
        $scope.isLogin = Session.isLogin();

        $scope.orders = [];

        loadOrders();

        function loadOrders() {
            $scope.submitting = true;
            var params = {};

            if ($scope.isLogin) {
                $scope.user.userId = $scope.currentUser.userId;
                $scope.user.firstName = $scope.currentUser.firstName;
                $scope.user.lastName = $scope.currentUser.lastName;
                $scope.user.email = $scope.currentUser.email;
                $scope.user.phone = $scope.currentUser.phone;
                $scope.user.address = $scope.currentUser.address;
                $scope.user.city = $scope.currentUser.city;
                $scope.user.country = $scope.currentUser.country;
            }

            params.user = angular.copy($scope.user);

            util.callRequest('users/orders', "POST", params).then(function (response) {
                var status = response.status;
                if (status === 200) {
                    // console.log(response);
                    $scope.orders = response.data;
                    _.map($scope.orders, function (item) {
                        switch (item.status) {
                            case 0:
                                item.status = "Pending";
                                break;
                            case 1:
                                item.status = "Shipping";
                                break;
                            case 2:
                                item.status = "Complete";
                                break;
                            default:
                                item.status = "Pending";
                                break;
                        }
                    });
                    // toastr.success("Create Order Success");
                    // cart.emptyCart();
                    // $state.go('index');
                } else {
                    //toastr.error("Error when create orders");
                }
            });
            $scope.submitting = false;
        };

        $scope.getTotal = function () {
            var total = 0;
            for (var i = 0; i < $scope.orders.length; i++) {
                var order = $scope.orders[i];
                total += order.orderTotal;
            }
            return total;
        }


    }]);



