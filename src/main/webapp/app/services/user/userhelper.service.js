(function() {
    'use strict';

    angular
        .module('peersApp')
        .factory('UserHelper', UserHelper);

    UserHelper.$inject = ['User'];

    function UserHelper(User) {
        var service = {
            users: [],
            getNameById: function(id) {
                if (service.users.length > 0) {
                    for (var i in service.users) {
                        var user = service.users[i];
                        if (user.id == id) {
                            return user.firstName + ' ' + user.lastName;
                        }
                    }
                } else {
                    return 'Unavailable';
                }
            }
        };

        function loadAll() {
            User.query({}, onSuccess, onError);
        }

        function onSuccess(data, headers) {
            //hide anonymous user from user management: it's a required user for Spring Security
            for (var i in data) {
                if (data[i]['login'] === 'anonymoususer') {
                    data.splice(i, 1);
                }
            }
            for (var j = 0, len = data.length; j < len; j++) {
                service.users.push(data[j]);
            }
        }

        function onError(error) {
            //AlertService.error(error.data.message);
        }

        loadAll();

        return service;
    }
})();
