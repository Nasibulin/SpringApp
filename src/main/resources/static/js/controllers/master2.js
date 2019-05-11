angular.module('myapp.master', [])
    .controller('masterCtrl', ['$scope', 'Session', '$state', function ($scope, Session, $state) {
        Session.init().then(function () {
            // binding session user
            $scope.user = Session.getUser();
            $scope.isLogin = Session.isLogin();
            $scope.username = $scope.user.firstName + ' ' + $scope.user.lastName;

            $scope.logout = function () {
                Session.logout();
                $state.reload();
            };
        }, (function () {

            function loadScript(url, callback) {

                var script = document.createElement('script')
                script.type = 'text/javascript';
                script.async = false;
                script.defer = false;

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

            loadScript('js/lib/jquery-3.3.1.min.js', function () {
            });
            loadScript('js/lib/bootstrap4/bootstrap.min.js', function () {
            });
            loadScript('js/custom.js', function () {
            });
            loadScript('js/lib/jquery.maskedinput.js', function () {
            });
            loadScript('js/custom2.js', function () {
            });
            // loadScript('js/lodash.js', function () {
            // });
        })())
    }]);