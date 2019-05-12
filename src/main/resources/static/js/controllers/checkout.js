angular.module('myapp.checkout', ['myapp.authen'])
    .controller('checkoutCtrl', function ($scope, $http) {

        // $scope.topmenu = [];
        // $scope.submenu = [];
        //
        // loadMenu(topmenuUrl, 'topmenu');
        // loadMenu(submenuUrl, 'submenu');
        // function loadMenu(menuurl, param) {
        //     $http({
        //         method: 'GET',
        //         url: menuurl
        //     }).then(function (response) {
        //         $scope[param] = response.data;
        //     })
        // };

        (function () {

            function loadScript(url, callback) {

                var script = document.createElement('script')
                script.type = 'text/javascript';
                script.async = false;
                script.defer = true;

                if (script.readyState) { //IE
                    script.onreadystatechange = function () {
                        if (script.readyState == 'loaded' || script.readyState == 'complete') {
                            script.onreadystatechange = null;
                        }
                    };
                } else { //Others
                    script.onload = function () {
                        callback();
                    };
                }

                script.src = url;
                document.getElementsByTagName('head')[0].appendChild(script);
            }

            loadScript('https://api-maps.yandex.ru/2.1/?lang=ru_RU&amp;apikey=b9fef526-ce00-4915-bd6d-b4aca80960d3', function () {});
            loadScript('js/input_validation.js', function () {});


        })()

    });