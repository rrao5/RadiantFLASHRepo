(function() {
    'use strict';

    angular
        .module('peersApp')
        .factory('TeamsService', TeamsService);

    TeamsService.$inject = ['$resource'];

    function TeamsService($resource) {

        var service = $resource('api/teamById/:id', {}, {
            'query': {
                url: 'api/teamNames',
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


      /*
        var service = {
            'get': function() {
                return {
                    'calls': 100,
                    'upsellcalls': 50,
                    'saleamount': 5000
                }
            }
        }
        */

        return service;
    }
})();
