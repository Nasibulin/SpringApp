(function (window, angular, undefined) {
  'use strict';

  angular.module('myapp').controller('loginCtrl', loginCtrl_);

  loginCtrl_.$inject = ['$scope', '$http', '$location', 'Session'];

  function loginCtrl_($scope, $http, $location, Session) {
    $scope.user={};
    $scope.newuser={};

    $scope.signinform = function() {
      var pass=$scope.user.password;
      var enc_pass= window.btoa(pass);
      $scope.user.password=pass;
      $scope.user.keepMeLogin = 1;
      $http({
          method  : 'POST',
          url     : 'http://localhost:8080/api/login',
          data    : $scope.user, //forms user object
       }).then(function(response) {
          console.log(response.status);
          console.log(response.data.data);
          // Session.put('token', '"'+results.token+'"');
          // Session.put('user_name', results.data[0].first_name + ' ' + results.data[0].last_name);

          console.log($scope.user_name);
          $location.path('/login');
        });

    }

    $scope.signupform = function() {
        var pass = $scope.newuser.password;
       var enc_pass = window.btoa(pass);
       $scope.newuser.password = enc_pass;
       $http({
            method  : 'POST',
            url     : 'http://localhost:3000/signup/',
            data    : $scope.newuser, //forms user object
         })
            .success(function(data) {
             console.log("done");
            });
    }

    $scope.check_user=function(){
      $scope.test_user={};
      var check_user=$scope.newuser.email;
      $scope.test_user.check_user=check_user;
      if($scope.test_user.check_user  != null){
        $http({
            method  : 'POST',
            url     : 'http://localhost:3000/check_user/',
            data    : $scope.test_user, //forms user object
         }).success(function(data) {
             console.log(data.results.status);
             $scope.status=data.results.status;
             if(data.results.status == "false"){
                 $scope.message_success="";
                $scope.message_error="User of Same Name Already Exists";
             }
             else if(data.results.status == "true"){
                 $scope.message_error="";
                 $scope.message_success="Valid User Name";
             }
            
          });
      } else if($scope.test_user.check_user  == null){
           $scope.message_error="";
           $scope.message_success="";
       }
    }
  }
})(window, window.angular);