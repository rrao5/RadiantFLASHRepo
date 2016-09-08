(function() {
    'use strict';

    angular
        .module('peersApp')
        .controller('TeamDashboardController', TeamDashboardController);

    TeamDashboardController.$inject = ['$scope', '$state', 'AlertService'];

    function TeamDashboardController($scope, $state, AlertService) {
        var vm = this;

    }
})();
