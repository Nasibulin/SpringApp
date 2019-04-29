var app = angular.module("myApp", []);

app.config(['$compileProvider', function ($compileProvider) {
    $compileProvider.debugInfoEnabled(false);
}]);

// Controller Part
app.controller("navCtrl", function ($scope, $http) {


    $scope.topmenu = [];
    $scope.submenu = [];

    // Now load the data from server
    _refreshTopmenuData();
    _refreshSubmenuData();

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
                        callback();
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

//jQuery loaded
            console.log('jquery loaded');

        });
        loadScript("js/bootstrap4/bootstrap.min.js", function () {

//Bootstrap loaded
            console.log('bootstrap loaded');

        });
        loadScript("js/custom.js", function () {

//Custom loaded
            console.log('custom loaded');

        });
        loadScript("js/jquery.maskedinput.js", function () {

//Custom loaded
            console.log('maskedinput loaded');

        });
        loadScript("js/custom2.js", function () {

//Custom2 loaded
            console.log('custom2 loaded');

        });
    })();


    // Private Method  
    // HTTP GET- get category collection
    // Call: http://localhost:8080/menu/{level}
    function _refreshTopmenuData() {
        $http({
            method: 'GET',
            url: '/menu/2'
        }).then(
            function (res) { // success
                $scope.topmenu = res.data;
            },
            function (res) { // error
                console.log("Error: " + res.status + " : " + res.data);
            }
        );
    }

    function _refreshSubmenuData() {
        $http({
            method: 'GET',
            url: '/menu/3'
        }).then(
            function (res) { // success
                $scope.submenu = res.data;
            },
            function (res) { // error
                console.log("Error: " + res.status + " : " + res.data);
            }
        );
    }

});