angular.module('myapp', ['ngRoute', 'myapp.products'])
    .config(function ($routeProvider, $locationProvider) {
        $routeProvider.when('/',
            {
                templateUrl: 'fragments/banner.html',
            });
        $routeProvider.when('/catalog/:id',
            {
                templateUrl: '/views/products.html',
                controller: 'productCtrl'
            });
        $routeProvider.otherwise({redirectTo: '/'});
        $locationProvider.html5Mode(true);
    })
    .config(['$compileProvider', function ($compileProvider) {
        $compileProvider.debugInfoEnabled(false);
    }])
    .constant('topmenuUrl', '/menu/2')
    .constant('submenuUrl', '/menu/3')
    // Controller Part
    .controller('navCtrl', function ($scope, $http, topmenuUrl, submenuUrl) {

        $scope.topmenu = [];
        $scope.submenu = [];

        loadMenu(topmenuUrl, 'topmenu');
        loadMenu(submenuUrl, 'submenu');

        (function () {

            function loadScript(url, callback) {

                var script = document.createElement("script")
                script.type = "text/javascript";
                script.async = false;
                script.defer = true;

                if (script.readyState) { //IE
                    script.onreadystatechange = function () {
                        if (script.readyState == "loaded" || script.readyState == "complete") {
                            script.onreadystatechange = null;
                        }
                    };
                } else { //Others
                    script.onload = function () {
                        callback();
                    };
                }

                script.src = url;
                document.getElementsByTagName("head")[0].appendChild(script);
            }

            loadScript("js/jquery-3.3.1.min.js", function () {
            });
            loadScript("js/bootstrap4/bootstrap.min.js", function () {
            });
            loadScript("js/custom.js", function () {
            });
            loadScript("js/jquery.maskedinput.js", function () {
            });
            loadScript("js/custom2.js", function () {
            });
        })();


        function loadMenu(menuurl, param) {
            $http({
                method: 'GET',
                url: menuurl
            }).then(function (response) {
                $scope[param] = response.data;
            })
        };
    });