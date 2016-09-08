(function() {
    'use strict';

    angular
        .module('peersApp')
        .directive('radiantGrandTotals', RadiantGrandTotals);

    /**
     * <appspire-share-button type="activity" name="ACTIVITY NAME"></appspire-share-button>
     * <appspire-share-button message="Message to tweet or post."></appspire-share-button>
     */

    function RadiantGrandTotals() {
        return {
            restrict: 'E', // element only <appspire-share-button></appspire-share-button>
            scope: {}, // isolate scope
            templateUrl: 'app/teamdashboard/grandtotals.html',
            controller: 'GrandTotalsController',
            controllerAs: 'vm',
            bindToController: {}
        }
    }

})();
