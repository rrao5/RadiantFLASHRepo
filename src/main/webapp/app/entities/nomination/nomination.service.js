(function() {
    'use strict';
    angular
        .module('peersApp')
        .factory('Nomination', Nomination);

    Nomination.$inject = ['$resource', 'DateUtils'];

    function Nomination ($resource, DateUtils) {
        var resourceUrl =  'api/nominations/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    if (data) {
                        data = angular.fromJson(data);
                        data.nominationDt = DateUtils.convertDateTimeFromServer(data.nominationDt);
                    }
                    return data;
                }
            },
            'update': { method:'PUT' }
        });
    }
})();
