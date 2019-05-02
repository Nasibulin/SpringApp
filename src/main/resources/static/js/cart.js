angular.module('myapp.cart', ['ngCookies'])
    .factory('cart', ['$cookies', function ($cookies) {
        var cartData = [];

        return {

            addProduct: function (id, partnumber, name, price) {
                var addedToExistingItem = false;
                for (var i = 0; i < cartData.length; i++) {
                    if (cartData[i].id == id) {
                        cartData[i].count++;
                        addedToExistingItem = true;
                        $cookies.putObject('cartItems', cartData);
                        break;
                    }
                }
                if (!addedToExistingItem) {
                    cartData.push({
                        count: 1, id: id, partnumber: partnumber, name: name, price: price
                    });
                    $cookies.putObject('cartItems', cartData);
                }
            },

            removeProduct: function (id) {
                for (var i = 0; i < cartData.length; i++) {
                    if (cartData[i].id == id) {
                        cartData.splice(i, 1);
                        $cookies.remove('cartItems');
                        $cookies.putObject('cartItems', cartData);
                        break;
                    }
                }
            },

            getProducts: function () {
                var i = [];
                i = $cookies.getObject('cartItems');
                console.log(i[0].name);
                return cartData;
            },

            clear: function () {
                cartData = [];
                $cookies.remove('cartItems');
                $cookies.putObject('cartItems', cartData);
            }
        }
    }])
    .directive('cartSummary', function (cart) {
        return {
            restrict: "E",
            templateUrl: "views/cartSummary.html",
            controller: function ($scope) {

                var cartData = cart.getProducts();

                $scope.total = function () {
                    var total = 0;
                    for (var i = 0; i < cartData.length; i++) {
                        total += (cartData[i].price * cartData[i].count);
                    }
                    return total;
                }

                $scope.itemCount = function () {
                    var total = 0;
                    for (var i = 0; i < cartData.length; i++) {
                        total += cartData[i].count;
                    }
                    return total;
                }
            }
        };
    });