angular.module('myapp')
    .factory('ShoppingCart', ['$cookies', function ($cookies) {

        var items = $cookies.getObject('cartItems');

        return {
            addProduct: function (id, partnumber, name, price) {
                // check product already exists in cart
                if (id) {
                    // items = $cookies.getObject('cartItems');
                    if (items && angular.isArray(items)) {
                        for (var i = 0; i < items.length; i++) {
                            if (items[i].id === id) {
                                items[i].count++;
                                return;
                            }
                        }
                    } else {
                        // empty cart, create new item list
                        items = [];
                    }

                    // add product with quantity into item list
                    var item = {
                        count: 1,
                        id: id,
                        partnumber: partnumber,
                        name: name,
                        price: price
                    };
                    items.push(item);
                }
            },

            removeProduct: function (id) {
                for (var i = 0; i < items.length; i++) {
                    if (items[i].id == id) {
                        items.splice(i, 1);
                        // $cookies.remove('cartItems');
                        // $cookies.putObject('cartItems', items);
                        break;
                    }
                }
            },

            getProducts: function () {
                if (items && angular.isArray(items)) {
                    $cookies.putObject('cartItems', items);
                    return items;
                }
                else {
                    items = [];
                    $cookies.putObject('cartItems', items);
                    return items;
                }
            },

            clear: function () {
                var items = [];
                $cookies.remove('cartItems');
                $cookies.putObject('cartItems', items);
            }
        }
    }])
    .directive('cartSummary', [function () {
        return {
            restrict: "E",
            templateUrl: "views/cartSummary.html",
            controller: ['$scope', '$state', 'ShoppingCart', function ($scope, $state, cart) {

                var items = cart.getProducts();
                $scope.total = function () {
                    var total = 0;
                    for (var i = 0; i < items.length; i++) {
                        total += (items[i].price * items[i].count);
                    }
                    return total;
                }

                $scope.itemCount = function () {
                    var total = 0;
                    for (var i = 0; i < items.length; i++) {
                        total += items[i].count;
                    }
                    return total;
                }
            }]
        };
    }]);