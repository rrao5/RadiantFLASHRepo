(function() {
    'use strict';
    angular
        .module('peersApp')
        .factory('Nomination', Nomination);

    Nomination.$inject = ['$resource', 'DateUtils', 'UserHelper'];

    function Nomination ($resource, DateUtils, UserHelper) {
        var resourceUrl =  'api/nominations/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    if (data) {
                        data = angular.fromJson(data);
                        data.nominationDt = DateUtils.convertDateTimeFromServer(data.nominationDt);
                        data.nomineeName = UserHelper.getNameById(data.nomineeId);
                        data.nominatedByName = UserHelper.getNameById(data.nominatedById)
                    }
                    return data;
                }
            },
            'update': { method:'PUT' }
        });
    }
})();
