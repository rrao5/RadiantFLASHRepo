(function() {
    'use strict';

    angular
        .module('peersApp')
        .controller('NominationDetailController', NominationDetailController);

    NominationDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Nomination', 'User'];

    function NominationDetailController($scope, $rootScope, $stateParams, previousState, entity, Nomination, User) {
        var vm = this;

        vm.nomination = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('peersApp:nominationUpdate', function(event, result) {
            vm.nomination = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
