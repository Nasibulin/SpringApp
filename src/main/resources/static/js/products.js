angular.module("myapp.products", [])
    .controller("ProductCtrl", function ($scope, $http, $routeParams) {

        var productsUrl = "/categories/"+$routeParams.id+"/products";
        $scope.products = [];

        loadProducts();

        function loadProducts() {
            $http({
                method: 'GET',
                url: productsUrl
            }).then(function (response) {
                $scope.products = response.data;
            })
        };
    });
