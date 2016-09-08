(function() {
    'use strict';

    angular
        .module('peersApp')
        .factory('TeamsService', TeamsService);

    TeamsService.$inject = ['$resource'];

    function TeamsService($resource) {
        var resourceUrl = 'teamRest/teamNames';
        //resourceUrl = 'api/nominations'

        var service = $resource(resourceUrl, {}, {
            'query': {
                //url: '/teamNames',
                method: 'GET',
                isArray: true
            },
            'get': {
                method: 'GET',
                url: 'teamRest/teamById :id',
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
