'use strict';

describe('UserHelper Service Test', function() {
    var UserHelper, UserMock, $httpBackend;

    beforeEach(function() {
        module('peersApp');

        UserMock = {};
        UserMock.users = [{
            id: 1,
            firstName: 'Admin',
            lastName: 'Admin'
        }, {
            id: 2,
            firstName: 'User',
            lastName: 'User'
        }, {
            id: 3,
            firstName: 'System',
            lastName: 'System'
        }];

        module(function($urlRouterProvider) {
            // stop retrieval of templates
            $urlRouterProvider.deferIntercept();
        });

        inject(function($injector, _$httpBackend_, _UserHelper_) {
            $httpBackend = $injector.get('$httpBackend');
            UserHelper = _UserHelper_;

            $httpBackend.whenGET(/^api\/users\?.*/).respond(UserMock.users);
            $httpBackend.whenGET(/^api\/account\?.*/).respond({});
        });

    });

    describe('UserHelper', function() {
        it('should hold an array of users', function() {
            expect(UserHelper.users).toBeDefined();
        });

        describe("#getNameById", function() {
            it('should return a user name given a user ID', function() {
                $httpBackend.flush();
                var name = UserHelper.getNameById(2);
                expect(name).toMatch('User User');
            })
        })
    });

});
