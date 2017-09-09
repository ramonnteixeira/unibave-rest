var app = angular.module('alunos', ['ngResource']);

app.controller('AlunosController', function($scope, AlunosResource) {
	
	$scope.listar = function() {
		AlunosResource.query(function(data){
			$scope.alunos = data;
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
	
	$scope.listar();
});

app.factory('AlunosResource', function($resource){
	return $resource("/api/alunos/:codigo",
				{codigo: "@codigo"},
				{'update': {method: 'PUT'}
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