'use strict';

angular.module('myapp', [
    'ui.router',
    'ngCookies',
    'myapp.products',
    'myapp.cart',
    'myapp.cartview',
    'myapp.nav'
])// Define all route of our app
    .config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
        $urlRouterProvider.otherwise("/index");
        // For authentication, but for now just Mock demo.
        // Will be implement in near function
        $stateProvider
            .state('master', {
                templateUrl: 'fragments/master.html',
                abstract: true,
                // controller: ['$scope', 'Session', '$state', function ($scope, Session, $state) {
                //     Session.init().then(function () {
                //         // binding session user
                //         $scope.user = Session.getUser();
                //         $scope.isLogin = Session.isLogin();
                //         $scope.username = $scope.user.firstName + ' ' + $scope.user.lastName;
                //
                //         $scope.logout = function () {
                //             Session.logout();
                //             $state.reload();
                //         };
                //     }, function () {
                //         // error handle, show message if necessary
                //     });
                // }]
            })
            .state('index', {
                url: '/',
                // params: {
                //     search: {
                //         value: '',
                //         squash: true
                //     }
                // },
                parent: 'master',
                templateUrl: 'fragments/banner.html',
                // controller: 'HomeCtrl'
            })
            .state('category-products', {
                url: '/catalog/:id',
                parent: 'master',
                templateUrl: 'views/products.html',
                controller: 'productCtrl'
            })
            .state('cart', {
                url: '/cart',
                parent: 'master',
                templateUrl: 'views/cart.html',
                controller: 'cartCtrl'
            })
    }])
    .config(function ($locationProvider) {
        $locationProvider.html5Mode(true);
    })
    .config(['$compileProvider', function ($compileProvider) {
        $compileProvider.debugInfoEnabled(false);
    }])
