(function() {
    'use strict';

    angular
        .module('peersApp')
        .controller('TeamDashboardController', TeamDashboardController);

    TeamDashboardController.$inject = ['$scope', '$state', 'AlertService', 'TeamsService'];

    function TeamDashboardController($scope, $state, AlertService, TeamsService) {
        var vm = this;
        vm.teams = TeamsService.query();
        vm.team1 = TeamsService.get({
            id: 1
        });
    }
})();
