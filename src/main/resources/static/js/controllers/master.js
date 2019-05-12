angular.module('myapp.master', [])
    .controller('masterCtrl', ['$scope', 'Session', '$state', function ($scope, Session, $state) {
        Session.init().then(function () {
            // binding session user
            $scope.user = Session.getUser();
            console.log($scope.user);
            $scope.isLogin = Session.isLogin();
            $scope.username = $scope.user.firstName + ' ' + $scope.user.lastName;

            $scope.logout = function () {
                Session.logout();
                $state.reload();
            };
        }, function () {
            // error handle, show message if necessary
        });
    }]);