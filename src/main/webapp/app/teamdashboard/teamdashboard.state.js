(function() {
    'use strict';

    angular
        .module('peersApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
            .state('teamdashboard', {
                parent: 'app',
                url: '/teamdashboard',
                data: {
                    authorities: ['ROLE_USER', 'ROLE_ADMIN'],
                    pageTitle: 'Team Dashboard'
                },
                views: {
                    'content@': {
                        templateUrl: 'app/teamdashboard/teamdashboard.html',
                        controller: 'TeamDashboardController',
                        controllerAs: 'vm'
                    }
                },
                /*
                params: {
                    page: {
                        value: '1',
                        squash: true
                    },
                    sort: {
                        value: 'id,asc',
                        squash: true
                    },
                    search: null
                },
                resolve: {
                    pagingParams: ['$stateParams', 'PaginationUtil', function ($stateParams, PaginationUtil) {
                        return {
                            page: PaginationUtil.parsePage($stateParams.page),
                            sort: $stateParams.sort,
                            predicate: PaginationUtil.parsePredicate($stateParams.sort),
                            ascending: PaginationUtil.parseAscending($stateParams.sort),
                            search: $stateParams.search
                        };
                    }],
                }*/
            })
            /*
                    .state('nomination-detail', {
                        parent: 'entity',
                        url: '/nomination/{id}',
                        data: {
                            authorities: ['ROLE_USER'],
                            pageTitle: 'Nomination'
                        },
                        views: {
                            'content@': {
                                templateUrl: 'app/entities/nomination/nomination-detail.html',
                                controller: 'NominationDetailController',
                                controllerAs: 'vm'
                            }
                        },
                        resolve: {
                            entity: ['$stateParams', 'Nomination', function($stateParams, Nomination) {
                                return Nomination.get({id : $stateParams.id}).$promise;
                            }],
                            previousState: ["$state", function ($state) {
                                var currentStateData = {
                                    name: $state.current.name || 'nomination',
                                    params: $state.params,
                                    url: $state.href($state.current.name, $state.params)
                                };
                                return currentStateData;
                            }]
                        }
                    })
                    .state('nomination-detail.edit', {
                        parent: 'nomination-detail',
                        url: '/detail/edit',
                        data: {
                            authorities: ['ROLE_USER']
                        },
                        onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                            $uibModal.open({
                                templateUrl: 'app/entities/nomination/nomination-dialog.html',
                                controller: 'NominationDialogController',
                                controllerAs: 'vm',
                                backdrop: 'static',
                                size: 'lg',
                                resolve: {
                                    entity: ['Nomination', function(Nomination) {
                                        return Nomination.get({id : $stateParams.id}).$promise;
                                    }]
                                }
                            }).result.then(function() {
                                $state.go('^', {}, { reload: false });
                            }, function() {
                                $state.go('^');
                            });
                        }]
                    })
                    .state('nomination.new', {
                        parent: 'nomination',
                        url: '/new',
                        data: {
                            authorities: ['ROLE_USER']
                        },
                        onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                            $uibModal.open({
                                templateUrl: 'app/entities/nomination/nomination-dialog.html',
                                controller: 'NominationDialogController',
                                controllerAs: 'vm',
                                backdrop: 'static',
                                size: 'lg',
                                resolve: {
                                    entity: function () {
                                        return {
                                            nominationDt: null,
                                            nominationText: null,
                                            id: null
                                        };
                                    }
                                }
                            }).result.then(function() {
                                $state.go('nomination', null, { reload: 'nomination' });
                            }, function() {
                                $state.go('nomination');
                            });
                        }]
                    })
                    .state('nomination.edit', {
                        parent: 'nomination',
                        url: '/{id}/edit',
                        data: {
                            authorities: ['ROLE_USER']
                        },
                        onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                            $uibModal.open({
                                templateUrl: 'app/entities/nomination/nomination-dialog.html',
                                controller: 'NominationDialogController',
                                controllerAs: 'vm',
                                backdrop: 'static',
                                size: 'lg',
                                resolve: {
                                    entity: ['Nomination', function(Nomination) {
                                        return Nomination.get({id : $stateParams.id}).$promise;
                                    }]
                                }
                            }).result.then(function() {
                                $state.go('nomination', null, { reload: 'nomination' });
                            }, function() {
                                $state.go('^');
                            });
                        }]
                    })
                    .state('nomination.delete', {
                        parent: 'nomination',
                        url: '/{id}/delete',
                        data: {
                            authorities: ['ROLE_USER']
                        },
                        onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                            $uibModal.open({
                                templateUrl: 'app/entities/nomination/nomination-delete-dialog.html',
                                controller: 'NominationDeleteController',
                                controllerAs: 'vm',
                                size: 'md',
                                resolve: {
                                    entity: ['Nomination', function(Nomination) {
                                        return Nomination.get({id : $stateParams.id}).$promise;
                                    }]
                                }
                            }).result.then(function() {
                                $state.go('nomination', null, { reload: 'nomination' });
                            }, function() {
                                $state.go('^');
                            });
                        }]
                    });
            */
    }

})();
