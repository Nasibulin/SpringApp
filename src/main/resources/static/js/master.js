angular.module('myapp.master', [])
    .controller('masterCtrl', function () {

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

            loadScript('js/jquery-3.3.1.min.js', function () {
            });
            loadScript('js/bootstrap4/bootstrap.min.js', function () {
            });
            loadScript('js/custom.js', function () {
            });
            loadScript('js/jquery.maskedinput.js', function () {
            });
            loadScript('js/custom2.js', function () {
            });
        })();


    });