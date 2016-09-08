(function() {
    'use strict';

    angular
        .module('peersApp')
        .factory('GrandTotalsService', GrandTotalsService);

    GrandTotalsService.$inject = ['$resource'];

    function GrandTotalsService($resource) {
        /*
        var service = $resource('api/users/:login', {}, {
            'query': {
                method: 'GET',
                isArray: true
            },
            'get': {
                method: 'GET',
                transformResponse: function(data) {
                    data = angular.fromJson(data);
                    return data;
                }
            },
            'save': {
                method: 'POST'
            },
            'update': {
                method: 'PUT'
            },
            'delete': {
                method: 'DELETE'
            }
        });
        */

        var service = {
            'get': function() {
                return {
                    'calls': 100,
                    'upsellcalls': 50,
                    'upsellamount': 5000
                }
            }
        }

        return service;
    }
})();
