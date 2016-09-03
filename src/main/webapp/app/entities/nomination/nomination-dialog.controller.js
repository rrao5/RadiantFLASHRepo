(function() {
    'use strict';

    angular
        .module('peersApp')
        .controller('NominationDialogController', NominationDialogController);

    NominationDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', '$q', 'entity', 'Nomination', 'User'];

    function NominationDialogController ($timeout, $scope, $stateParams, $uibModalInstance, $q, entity, Nomination, User) {
        var vm = this;

        vm.nomination = entity;
        vm.clear = clear;
        vm.datePickerOpenStatus = {};
        vm.openCalendar = openCalendar;
        vm.save = save;
        vm.users = User.query();

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.nomination.id !== null) {
                Nomination.update(vm.nomination, onSaveSuccess, onSaveError);
            } else {
                Nomination.save(vm.nomination, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('peersApp:nominationUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }

        vm.datePickerOpenStatus.nominationDt = false;

        function openCalendar (date) {
            vm.datePickerOpenStatus[date] = true;
        }
    }
})();
