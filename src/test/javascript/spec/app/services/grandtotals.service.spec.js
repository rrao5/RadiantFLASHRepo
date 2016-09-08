'use strict';

describe('GrandTotals Service Test', function() {
    var GrandTotalsService, $httpBackend;

    beforeEach(function() {
        module('peersApp');

        module(function($urlRouterProvider) {
            // stop retrieval of templates
            $urlRouterProvider.deferIntercept();
        });

        inject(function($injector, _$httpBackend_, _GrandTotalsService_) {
            //$httpBackend = $injector.get('$httpBackend');
            GrandTotalsService = _GrandTotalsService_;

            //$httpBackend.whenGET(/^api\/account\?.*/).respond({});
        });

    });

    describe('GrandTotalsService', function() {
        it('should get a set of totals', function() {
            expect(GrandTotalsService.get).toBeDefined();
        });

        describe("#get", function() {
            it('should return a set of totals', function() {
                //$httpBackend.flush();
                var totals = GrandTotalsService.get();
                expect(totals).toBeDefined();
                expect(typeof totals.calls).toBe('number');
                expect(typeof totals.upsellcalls).toBe('number');
                expect(typeof totals.saleamount).toBe('number');
            })
        })
    });

});
