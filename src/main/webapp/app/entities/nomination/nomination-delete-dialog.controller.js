(function() {
    'use strict';

    angular
        .module('peersApp')
        .controller('NominationDeleteController',NominationDeleteController);

    NominationDeleteController.$inject = ['$uibModalInstance', 'entity', 'Nomination'];

    function NominationDeleteController($uibModalInstance, entity, Nomination) {
        var vm = this;

        vm.nomination = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;
        
        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Nomination.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
