angular.module('myapp.orders', [])
    .constant('_', window._)
    .controller('ordersCtrl', ['$scope', '$state', '$http', '$stateParams', '_', function ($scope, $state, $http, $stateParams, _) {

        var ordersUrl = '/api/orders/';
        $scope.orders = [];

        loadOrders();

        function loadOrders() {
            $http({
                method: 'GET',
                url: ordersUrl
            }).then(function (response) {
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
            });
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
