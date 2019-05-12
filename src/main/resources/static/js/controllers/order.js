angular.module('myapp.order', ['myapp.authen'])
    .controller('orderCtrl', ['$scope', 'util', '$state', '$http', 'Session', '$cookies', '$stateParams', function ($scope, util, $state, $http, Session, $cookies, $stateParams) {
        // Checking user already login
        // if (!Session.getAccessToken() && !Session.getUser()) {
        //     $state.go('login');
        //     return;
        // }

        // $scope.currentUser = Session.getUser();
        // $scope.isLogin = Session.isLogin();

        $scope.orderDetailsSet = [];
        $scope.address = '';
        $scope.ordertotal = 0;

        loadOrder();

        function loadOrder() {

            // if ($scope.isLogin) {
            //     $scope.user.userId = $scope.currentUser.userId;
            //     $scope.user.firstName = $scope.currentUser.firstName;
            //     $scope.user.lastName = $scope.currentUser.lastName;
            //     $scope.user.email = $scope.currentUser.email;
            //     $scope.user.phone = $scope.currentUser.phone;
            //     $scope.user.address = $scope.currentUser.address;
            //     $scope.user.city = $scope.currentUser.city;
            //     $scope.user.country = $scope.currentUser.country;
            // }
            //
            // params.user = angular.copy($scope.user);

            util.callRequest('order/' + $stateParams.id, "GET").then(function (data) {
                $scope.orderDetailsSet = data.data.orderDetailsSet;
                $scope.ordertotal = data.data.orderTotal;
                $scope.address = data.data.orderAddress.region+' '+data.data.orderAddress.suffix;
            });

        };

    }]);
