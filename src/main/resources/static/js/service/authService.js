/*global angular:true, moment:true, _:true */
(function () {
    'use strict';

    angular.module('myapp').factory('AuthService', AuthService);

    AuthService.$inject = ['$http', '$q', 'Session'];

    function AuthService($http,  $q, Session) {

        var factory = {
            isLoggedIn: isLoggedIn,
            logout: logout,
            getToken: getToken,
            getUser: getUser,
            setUser: setUser
        };

        return factory;

        function isLoggedIn(){

            if(Session.get('tokenId')){
                return true
            }
            else{
                return false
            }
        }

        function logout(){

            Session.delete('tokenId');
        }

        function getToken(){
            return Session.get('tokenId');
        }

        function setUser(data){
            console.log(data);
            Session.put('tokenId', data.token);
            Session.put('f_name', data.user.first_name);
            Session.put('l_name', data.user.last_name);
            Session.put('email', data.user.email);

        }

        function getUser(){
            return {
                'tokenId': Session.get('tokenId'),
                'name': Session.get('f_name')+' ' + Session.get('l_name'),
                'email': Session.get('email')
            };
        }

    }

})(window, window.angular);