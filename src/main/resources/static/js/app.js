'use strict';

angular.module('myapp', [
    'ui.router',
    'ngCookies',
    'ui.bootstrap',
    'myapp.products',
    'myapp.authen',
    'myapp.login',
    'myapp.cart',
    'myapp.nav',
    'myapp.master',
    'myapp.orderlist',
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
                //controller: 'masterCtrl'
                controller:  ['$scope', 'Session', '$state', function ($scope, Session, $state) {
                    Session.init().then(function () {
                        // binding session user
                        $scope.user = Session.getUser();
                        $scope.isLogin = Session.isLogin();
                        $scope.username = $scope.user.firstName + ' ' + $scope.user.lastName;

                        $scope.logout = function () {
                            Session.logout();
                            $state.reload();
                        };
                    }, function () {
                        // error handle, show message if necessary
                    });
                }]
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
                controller: 'orderlistCtrl'
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
    .config(['$httpProvider', function ($httpProvider) {

        $httpProvider.interceptors.push(function ($q, $injector, $location, $timeout, $cookies) {

            var api = $injector.get('api'),
                app = $injector.get('app'),
                error = $injector.get('error'),
                cookie = $injector.get('$cookies');

            // Init fix token


            // Lodash
            var _ = $injector.get('_');

            // Noty toast
            //var noty = $injector.get('noty');

            // Manage just one instance of noty
            //var notyInstance;

            function tokenExpiredHandler() {

                // Transition to login page
                $timeout(function () {

                    // Clear token
                    cookie.remove(app.COOKIE_NAME);
                    // Redirect
                    // $location.path( '/login' );
                });
            }

            return {

                request: function (config) {
                    config.headers['X-Access-Token'] = $cookies.get('AccessToken');
                    // Loop to find
                    angular.forEach(api, function (a) {

                        if (a.token && config.url.indexOf(a.name) > 0) {

                            // Add token to request
                            config.data.token = cookie.get(app.COOKIE_NAME);
                        }
                    });

                    return config;
                },

                response: function (response) {

                    var contentType = response.headers()['content-type'];

                    contentType = (contentType) ? contentType.toLowerCase() : null;

                    if (contentType === "application/json;charset=utf-8") {

                        var status = response.data;

                        // Get error code
                        if ('errCode' in status) {

                            var errCode = status.errCode;

                            if (errCode > 0) {

                                // Check authen
                                if (_.find(error.AUTH, {code: errCode}) !== undefined) {

                                    // Handler
                                    tokenExpiredHandler();

                                    return;
                                }

                                // Handle other errors
                                // Show error as toast
                                //var e = _.find(error.OTHERS, {code: errCode});

                                // if (e !== undefined) {
                                //
                                //     var notyOpts = {
                                //
                                //         text: "There's an erorr occur",
                                //         type: 'error', // success, information ...
                                //         theme: 'bootstrapTheme',
                                //         layout: 'top',
                                //         closeWith: ['button', 'click']
                                //         // timeout: 5000 // 5s
                                //     };
                                //
                                //     var opts = {
                                //
                                //         text: e.desc
                                //     };
                                //
                                //     // Check instance
                                //     if (notyInstance) {
                                //
                                //         // Close & clean up
                                //         notyInstance.closeCleanUp();
                                //     }
                                //
                                //     notyInstance = noty(angular.extend(notyOpts, opts));
                                // }
                            }
                        }
                    }

                    return response || $q.when(response);
                },

                responseError: function (rejection) {

                    // Handle reponse error
                    return $q.reject(rejection);
                }
            };
        });

    }]);