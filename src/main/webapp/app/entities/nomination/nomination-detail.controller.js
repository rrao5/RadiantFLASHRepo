(function() {
    'use strict';

    angular
        .module('peersApp')
        .controller('NominationDetailController', NominationDetailController);

    NominationDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Nomination', 'User', 'Principal'];

    function NominationDetailController($scope, $rootScope, $stateParams, previousState, entity, Nomination, User, Principal) {
        var vm = this;

        vm.nomination = entity;
        vm.previousState = previousState.name;
        vm.loggedInUser = null;


        getAccount();

        function getAccount() {
            Principal.identity().then(function(account) {
                vm.loggedInUser = account.firstName + " " + account.lastName;

            });
        }

        var unsubscribe = $rootScope.$on('peersApp:nominationUpdate', function(event, result) {
            vm.nomination = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
