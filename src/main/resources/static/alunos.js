var app = angular.module('alunos', ['ngResource', 'ui.bootstrap']);

app.controller('AlunosController', function($scope, AlunosResource) {
	
	$scope.listar = function() {
		var pageable = $scope.alunosPage;
		var filter = {};
		filter.page = $scope.currentPage-1;
		filter.limit = pageable.size;
		filter.sort = pageable.sort.property;
		filter.direction = pageable.sort.direction;
		
		AlunosResource.query(filter, function(data){
			$scope.alunosPage = data;
		});
	};

	$scope.adicionar = function(aluno) {
		new AlunosResource(aluno).$save().then(function(data){
			$scope.novoAluno = {};
			$scope.listar();
		});
	};
	
	$scope.atualizar = function(aluno) {
		aluno.$update().then(function(data){
			$scope.listar();
		});
	};
	
	$scope.excluir = function(aluno) {
		aluno.$delete().then(function(data){
			$scope.listar();
		});
	};
	
	$scope.range = function(n) {
        return new Array(n);
    };

    $scope.alunosPage = {size: 10, sort: {property: 'codigo', direction: 'ASC'}};
    $scope.currentPage = 1;
	$scope.listar();
});

app.factory('AlunosResource', function($resource){
	return $resource("/api/alunos/:codigo",
			{codigo: "@codigo"}, {
			'update': {method: 'PUT'},
			'query': {
				method: 'GET',
				isArray: false,
			    transformResponse: function(response) {
			        return angular.fromJson(response);
			    }		
			 }
			});
});

app.factory('ErrorInterceptor', function($q) {
	return {
		'responseError': function(response) {
			alert(response.data[0].message);
			
			return $q.reject(response);
		}
	};
});

app.config( function($httpProvider) {
	$httpProvider.interceptors.push('ErrorInterceptor');
});