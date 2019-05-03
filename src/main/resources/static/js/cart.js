angular.module('myapp')
    .factory('ShoppingCart', ['$cookies', function ($cookies) {

        var items = [];
        return {
            addProduct: function (id, partnumber, name, price) {
                // check product already exists in cart
                if (id) {
                    items = $cookies.getObject('cartItems');
                    if (items && angular.isArray(items)) {
                        for (var i = 0; i < items.length; i++) {
                            if (items[i].id === id) {
                                // if there are quantity parameter then update quantity for product
                                items[i].count++;
                                // update cookie
                                $cookies.putObject('cartItems', items);
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
                    console.log(items);
                    // save into cookie
                    $cookies.putObject('cartItems', items);
                }
            },
            // addProduct: function (id, partnumber, name, price) {
            //     var addedToExistingItem = false;
            //     for (var i = 0; i < items.length; i++) {
            //         if (items[i].id == id) {
            //             items[i].count++;
            //             addedToExistingItem = true;
            //             // $cookies.putObject('cartItems', items);
            //             break;
            //         }
            //     }
            //     if (!addedToExistingItem) {
            //         items.push({
            //             count: 1, id: id, partnumber: partnumber, name: name, price: price
            //         });
            //         // $cookies.putObject('cartItems', items);
            //     }
            // },

            removeProduct: function (id) {
                for (var i = 0; i < items.length; i++) {
                    if (items[i].id == id) {
                        items.splice(i, 1);
                        $cookies.remove('cartItems');
                        $cookies.putObject('cartItems', items);
                        break;
                    }
                }
            },

            getProducts: function () {
                items = $cookies.getObject('cartItems');
                // items = [{count: 1, id: 5960, partnumber: "7600-SIP-600", name: "Cisco 7600 Series SPA Interface Processor-600", price: 112500},
                // {count: 1, id: 5961, partnumber: "7600-SIP-600=", name: "Cisco 7600 Series SPA Interface Processor-600", price: 112500},
                // {count: 1, id: 5964, partnumber: "SPA-OC192POS-XFP=", name: "1-port OC192/STM64 POS/RPR XFP Optics", price: 125000}]
                return items;
                // return cartData;
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
                console.log(items);
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
                // $state.reload();
            }]
        };
    }]);