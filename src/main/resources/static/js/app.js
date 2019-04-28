var app = angular.module("myApp", []);

app.config(['$compileProvider', function ($compileProvider)
{
    $compileProvider.debugInfoEnabled(false);
}]);
 
// Controller Part
app.controller("navCtrl", function($scope, $http) {
 
 
    $scope.topmenu = [];
    $scope.submenu = [];
 
    // Now load the data from server
    _refreshTopmenuData();
    _refreshSubmenuData();

 

    // Private Method  
    // HTTP GET- get category collection
    // Call: http://localhost:8080/menu/{level}
    function _refreshTopmenuData() {
        $http({
            method: 'GET',
            url: '/menu/2'
        }).then(
            function(res) { // success
                $scope.topmenu = res.data;
            },
            function(res) { // error
                console.log("Error: " + res.status + " : " + res.data);
            }
        );
    }

    function _refreshSubmenuData() {
        $http({
            method: 'GET',
            url: '/menu/3'
        }).then(
            function(res) { // success
                $scope.submenu = res.data;
            },
            function(res) { // error
                console.log("Error: " + res.status + " : " + res.data);
            }
        );
    }
 
});