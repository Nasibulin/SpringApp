
'use strict';

angular.module('myapp.authen', ['ngCookies', 'myapp'])
        // Session
        .factory('Session', [

            '$cookies',
            'api',
            'util',
            '$state',
            '$q',
            'AppConstant',
            'toastr',

            function ($cookies, api, util, $state, $q, AppConstant, toastr) {

                // store current user
                var sessionUser;

                var service = {

                    // Get token from cookie
                    getAccessToken: function () {
                        return $cookies.get(AppConstant.SESSION_COOKIES);
                    },

                    // Set token
                    setAccessToken: function (token) {

                        if (token) {
                            $cookies.put(AppConstant.SESSION_COOKIES, token);
                        }
                    },
                    
                    // Get user
                    getUser: function () {
                        return sessionUser;
                    },
                    
                    // Set user
                    setUser: function (user) {

                        // Set user
                        if (angular.isObject(user)) {
                            sessionUser = user;
                        }
                    },
                    
                    isLogin : function(){
                       return angular.isObject(sessionUser);
                    },
                    consoleLogin: function (data, callback) {
                        var request = util.callRequest("users/login", "POST", data).then(function (response) {
                            callback && callback(response);
                            var status = response.status;
                            if (status === 200) {
                                // store user into session
                                var accessToken = response.data;
                                service.setAccessToken(accessToken);
                            }
                        });
                        return request;
                    },

                    logout: function (callback) {

                        var request = util.callRequest("users/logout", "POST", function (response) {

                            callback && callback(response);

                        }).finally(function () {
                            // clear user into session
                            $cookies.remove(AppConstant.SESSION_COOKIES);
                            sessionUser = undefined;
                            // redirect to login page
//                            $state.go('login');
                        });

                        return request;
                    },

                    detectBrowserLang: function () {
                        // Language code: http://4umi.com/web/html/languagecodes.php || http://msdn.microsoft.com/en-us/library/ie/ms533052.aspx
                        var lang = window.navigator.browserLanguage || window.navigator.language;
                        var language = lang.substr(0, 2);
                        switch (language) {
                            case 'en':
                            case 'ja':
                                return language;
                                break;
                            default:
                                return 'en';
                                break;
                        }
                    },

                    init: function () {

                        var defer = $q.defer();
                        // validate session
                        if (service.getAccessToken()) {
                            // get user info
                            util.callRequest("/auth/session/data", "GET", {}).then(function (response) {
                                var status = response.status;
                                if (status === 200) {

                                    // store user into session
                                    var userProfile = response.data;
                                    service.setUser(userProfile);
                                    defer.resolve(userProfile);
                                } else {

                                    // clear user into session
                                    $cookies.remove(AppConstant.SESSION_COOKIES);
                                    // show error
                                    var err = _.find(AppConstant, {status: status});
                                    if (err) {
                                        toastr.error($i18next(err.mgsKey));
                                    }
                                    defer.reject();
                                }

                            }, function (errResponse) {

                                // clear user into session
                                $cookies.remove(AppConstant.SESSION_COOKIES);
                                defer.reject(errResponse);

                            }, true);

                        } else {

                            // redirect to login page
//                            $state.go('login');
                            defer.reject();
                        }

                        return defer.promise;
                    }
                };

                return service;
            }
        ]);


