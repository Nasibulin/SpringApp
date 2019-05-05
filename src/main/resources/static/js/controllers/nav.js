angular.module('myapp.nav', [])
    .constant('topmenuUrl', '/api/menu/2')
    .constant('submenuUrl', '/api/menu/3')
    .controller('navCtrl', function ($scope, $http, topmenuUrl, submenuUrl) {

        $scope.topmenu = [];
        $scope.submenu = [];

        loadMenu(topmenuUrl, 'topmenu');
        loadMenu(submenuUrl, 'submenu');
        function loadMenu(menuurl, param) {
            $http({
                method: 'GET',
                url: menuurl
            }).then(function (response) {
                $scope[param] = response.data;
            })
        };

    });