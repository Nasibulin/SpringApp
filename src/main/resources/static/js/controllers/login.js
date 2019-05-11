'use strict';

angular.module('myapp.login', [])

    .controller('loginCtrl', ['$scope', 'Session', "$state", 'util', function ($scope, Session, $state, util) {
        // Checking admin already login
        if (Session.getAccessToken() && Session.getUser()) {
            $state.go('index');
            return;
        }
        $scope.email = '';
        $scope.password = '';
        $scope.message = '';

        $scope.registerConsoleUser = function () {
            $scope.submitting = true;

            Session.consoleLogin({
                username: $scope.email,
                password: $scope.password,
                // password: util.MD5($scope.password),
                keepMeLogin: 1
            }, function (response) {
                var status = response.status;
                if (status === 200) {
                    // redirect page
                    $state.go('index');
                } else {
                    // util.showErrorToast(response.message);
                    $scope.message = response.message;
                }
            }).finally(function () {
                $scope.submitting = false;
            });
        };




    }]);