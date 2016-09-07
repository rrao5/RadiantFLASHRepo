(function() {
    'use strict';

    angular
        .module('peersApp')
        .controller('NominationController', NominationController);

    NominationController.$inject = ['$scope', '$state', 'Nomination', 'ParseLinks', 'AlertService', 'pagingParams', 'paginationConstants', 'UserHelper'];

    function NominationController($scope, $state, Nomination, ParseLinks, AlertService, pagingParams, paginationConstants, UserHelper) {
        var vm = this;

        vm.loadPage = loadPage;
        vm.predicate = pagingParams.predicate;
        vm.reverse = pagingParams.ascending;
        vm.transition = transition;
        vm.itemsPerPage = paginationConstants.itemsPerPage;

        loadAll();

        function loadAll() {
            Nomination.query({
                page: pagingParams.page - 1,
                size: vm.itemsPerPage,
                sort: sort()
            }, onSuccess, onError);

            function sort() {
                var result = [vm.predicate + ',' + (vm.reverse ? 'asc' : 'desc')];
                if (vm.predicate !== 'id') {
                    result.push('id');
                }
                return result;
            }

            function onSuccess(data, headers) {
                vm.links = ParseLinks.parse(headers('link'));
                vm.totalItems = headers('X-Total-Count');
                vm.queryCount = vm.totalItems;
                vm.nominations = data;
                vm.page = pagingParams.page;

                for (var i = 0, len = vm.nominations.length; i < len; i++) {
                    var nomination = vm.nominations[i];
                    nomination.nomineeName = UserHelper.getNameById(nomination.nomineeId);
                    nomination.nominatedByName = UserHelper.getNameById(nomination.nominatedById);
                }
            }

            function onError(error) {
                AlertService.error(error.data.message);
            }
        }

        function loadPage(page) {
            vm.page = page;
            vm.transition();
        }

        function transition() {
            $state.transitionTo($state.$current, {
                page: vm.page,
                sort: vm.predicate + ',' + (vm.reverse ? 'asc' : 'desc'),
                search: vm.currentSearch
            });
        }
    }
})();
