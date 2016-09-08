(function() {
  'use strict';

  angular
    .module('peersApp')
    .controller('GrandTotalsController', GrandTotalsController);

  GrandTotalsController.$inject = ['$log', '$scope', 'GrandTotalsService'];

  function GrandTotalsController($log, $scope, GrandTotalsService) {
    $log.debug('GrandTotalsController()');
    var vm = this;

    vm.totals = GrandTotalsService.get();
  }
})();
