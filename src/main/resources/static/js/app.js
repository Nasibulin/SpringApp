'use strict';

angular.module('myapp', [
    'ui.router',
    'ngCookies',
    'myapp.products',
    'myapp.common',
    'myapp.cart',
    'myapp.nav',
    'myapp.master',
    'myapp.orders',
])// Define all route of our app
    .config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
        $urlRouterProvider.otherwise("/");

        $stateProvider
            .state('login', {
                url: '/login',
                parent: 'master',
                templateUrl: 'views/login.html',
                controller: 'loginCtrl'
            })
            .state('master', {
                templateUrl: 'fragments/master.html',
                abstract: true,
                controller: 'masterCtrl'
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
            .state('orders', {
                url: '/orders',
                parent: 'master',
                templateUrl: 'views/orders.html',
                controller: 'ordersCtrl'
            })
            // .state('orders', {
            //     url: '/checkout',
            //     parent: 'master',
            //     templateUrl: 'views/checkout.html',
            //     controller: 'checkoutCtrl'
            // })
    }])
    .config(function ($locationProvider) {
        $locationProvider.html5Mode(true);
    })
    .config(['$compileProvider', function ($compileProvider) {
        $compileProvider.debugInfoEnabled(false);
    }])
    .run(['$rootScope', '$location', '$cookies', '$http',
        function ($rootScope, $location, $cookies, $http) {
            // keep user logged in after page refresh
            $rootScope.globals = $cookies.get('globals') || {};
            if ($rootScope.globals.currentUser) {
                $http.defaults.headers.common['Authorization'] = 'Bearer ' + $rootScope.globals.token;
                $rootScope.currentUser = $rootScope.globals.currentUser;
            }

            $rootScope.isSubmitted = false;

            $rootScope.$on('$locationChangeStart', function (event, next, current) {
                console.log('received event: ' + event + ' from: ' + current + ' to go to next: ' + next);
                // redirect to login page if not logged in and trying to access a restricted page
                var restrictedPage = $.inArray($location.path(), ['/checkout', '/orders']) === -1;
                var loggedIn = $rootScope.globals.currentUser;
                $rootScope.currentUser = $rootScope.globals.currentUser;
                if (!restrictedPage && !loggedIn) {
                    if($location.path().indexOf('/checkout') > -1) {
                        $location.path('/login');
                    } else if($location.path().indexOf('orders') > -1) {
                        $location.path('/login');
                    } else {
                        $location.path('/');
                    }
                }
            });
        }
    ]);