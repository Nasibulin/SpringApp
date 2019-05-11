'use strict';

angular.module('myapp')

    .constant('server', {

        // Local
        URL: 'http://localhost:8080/api'
        // Server
        //URL: 'http://52.196.33.166/marketplace-rest-api'
    })

    // Hold app info
    .constant('app', {
        EMAIL_REGEX: '[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}',
        COOKIE_NAME: 'authen_token', // Token name
        PAGE_LENGTH: 10 // Length per page using for list item

    });