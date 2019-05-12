angular.module('myapp.master', [])
    .controller('masterCtrl', ['$scope', 'Session', '$state', function ($scope, Session, $state) {
        Session.init().then(function () {
            // binding session user
            $scope.user = Session.getUser();
            $scope.isLogin = Session.isLogin();
            $scope.username = $scope.user.firstName + ' ' + $scope.user.lastName;

            $scope.logout = function () {
                Session.logout();
                // $state.go('index');
                //$state.reload();
            };
        }, function () {
            // error handle, show message if necessary
        });
    }]);