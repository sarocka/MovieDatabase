var movieApp = angular.module("movieApp", ['ngRoute']);


//--------------------------------------------------------------------------------------------------------------------------//
movieApp.controller("moviesCtrl", function ($scope, $http, $routeParams, $location){
	
	var movieUrl="/api/movies";
	
	$scope.searchParams= {};
	$scope.searchParams.titleOrDirector="";
	
	
	$scope.pageNum=0; 
	$scope.totalPages=1;
	
	$scope.movie={};
	$scope.movie.title="";
	$scope.movie.genre="";
	$scope.movie.duration="";
	$scope.movie.directorName="";
	$scope.movie.rating="";
	$scope.movie.description="";
	
	$scope.movies=[];
	
	var promise= $http.get(movieUrl);
	
	
		var getMovies= function () {
			
			
			var config= { params: {} };
			
			if ($scope.searchParams.titleOrDirector!="") {
				config.params.titleOrDirector=$scope.searchParams.titleOrDirector;
			}
			
			
			
			
			config.params.pageNum= $scope.pageNum;
			
			var movieUrl = "/api/movies";
			
		 var promise = $http.get(movieUrl, config);
		 promise.then(function success(res){
			 $scope.movies=res.data;
			 $scope.totalPages=res.headers("totalPages");
			 
		 }, function error(){
			 alert("Something went wrong");
		 }
				 
		 
		 
		 );
}
	getMovies();
	
	
	var directorUrl="/api/directors";
	
	$scope.directors=[];
	
	 var getDirectors= function(){
		  var promise =$http.get(directorUrl);
		  promise.then(
		  function success(res){
			  $scope.directors=res.data;
		  },
		  
		  function failure (){
			  alert("Something went wrong");
		  }
		  );
		 
		 
	 }
	getDirectors();
	
	
	
	
	
	//-----------------------------------------------------
	
	
	$scope.delete=function(id){
		
		var url="/api/movies/"+id;
		$http.delete(url).then(
			function success()	{
				getMovies();
			}, function failure(){
				alert("Something went wrong");
			}
		
		);
	}
//-------------------------------------------------------------------	
	$scope.edit=function(id){
		$location.path("/movies/edit/"+id)
	}
	
	$scope.goToComment=function(id){
		$location.path("/comment/add/"+id)
	}
	$scope.goToDirectorsMovies=function(id){
		$location.path("/directors/movies/"+id)
	}
	$scope.goToMoviesActors=function(id){
		$location.path("/movies/actors/"+id)
	}
	$scope.doSearch = function(){
		$scope.pageNum=0;
		getMovies();
		getDirectors();
	}
	
	$scope.changePage= function(direction){
		$scope.pageNum= $scope.pageNum+direction;
		getMovies();
		getDirectors();
	}

	
	
	});

//___________________________________________________________________________________________________________________________________________________________________________
movieApp.controller("editMovieCtrl", function($scope, $http, $routeParams, $location){
	
	var movieUrl="/api/movies/"+$routeParams.id;


	$scope.movie={};
	$scope.movie.title="";
	$scope.movie.genre="";
	$scope.movie.duration="";
	$scope.movie.directorName="";
	$scope.movie.rating="";
	$scope.movie.description="";
	
	

		var getMovie= function (){
			$http.get(movieUrl).then(
		function success (res){
			$scope.movie=res.data;
		}, function error (){
			alert("Something went wrong");
		}
		);
		}
		getMovie();
		
	var directorUrl="/api/directors";
		
		$scope.directors=[];
		
		
		 var getDirectors= function(){
			  var promise =$http.get(directorUrl);
			  promise.then(
			  function success(res){
				  $scope.directors=res.data;
			  },
			  
			  function failure(){
				  alert("Something went wrong");
			  }
			  );
			 
			 
		 }
	getDirectors();


	$scope.doEditMovie= function(){
			$http.put(movieUrl,$scope.movie).then(
					
			function success () {
				$location.path("/");
			}, 
			function error (){
				alert("Something went worng");
			}
			
			
			);
		}
	
	});


//________________________________________________________________________________________________
movieApp.controller("directorsMoviesCtrl", function($scope, $http, $routeParams, $location){
	
	var filmographyUrl="/api/directors/"+$routeParams.id+"/movies";
	var directorUrl="/api/directors/"+$routeParams.id;


	$scope.director={};
	$scope.director.name="";
	

		var getDirector= function (){
			$http.get(directorUrl).then(
		function success (res){
			$scope.director=res.data;
		}, function error (){
			alert("Something went wrong");
		}
		);
		}
		getDirector();
		
		var getFilmography= function(){
			  var promise =$http.get(filmographyUrl);
			  promise.then(
			  function success(res){
				  $scope.movies=res.data;
			  },
			  
			  function failure(){
				  alert("Something went wrong");
			  }
			  );
			 
			 
		 }
	getFilmography();
		
	
	});
//________________________________________________________________________________________________
movieApp.controller("moviesActorsCtrl", function($scope, $http, $routeParams, $location){
	
	var actorUrl="/api/movies/"+$routeParams.id+"/actors";
	var movieUrl="/api/movies/"+$routeParams.id;


	$scope.movie={};
	$scope.movie.title="";
	

		var getMovie= function (){
			$http.get(movieUrl).then(
		function success (res){
			$scope.movie=res.data;
		}, function error (){
			alert("Something went wrong");
		}
		);
		}
		getMovie();
		
		var getActors= function(){
			  var promise =$http.get(actorUrl);
			  promise.then(
			  function success(res){
				  $scope.actors=res.data;
			  },
			  
			  function failure(){
				  alert("Something went wrong");
			  }
			  );
			 
			 
		 }
	getActors();
		
	

	
	});

//_________________________________________________________________________________________________________________________________
movieApp.controller("commentCtrl", function($scope, $http, $routeParams, $location){
	var urlComment="/api/comments";
	
	$scope.comment={};
	$scope.comment.text="";
	$scope.comment.movieId=$routeParams.id;
	
	
	var movieUrl="/api/movies/"+$routeParams.id;


	$scope.movie={};
	$scope.movie.title="";
	



		var getMovie= function (){
			$http.get(movieUrl).then(
		function success (res){
			$scope.movie=res.data;
		}, function error (){
			alert("Something went wrong");
		}
		);
		}
		getMovie();
	
	
	
	
	$scope.doAddComment= function(){
		
		$http.post(urlComment,$scope.comment).then(
				
		function success(){
			$location.path("/");
		},
		function failure(){
			alert("Comment not added");
		}
		
		
		);
	}
	
	//$location.path("/");
	
	});



movieApp.config(['$routeProvider', function($routeProvider) {
	$routeProvider
		.when('/', {
			templateUrl : '/app/html/home.html',
			
		})
		.when('/comment/add/:id', {
			templateUrl : '/app/html/comment.html'
		})
		.when('/directors/movies/:id', {
			templateUrl : '/app/html/directors.html'
		})
		.when('/movies/actors/:id', {
			templateUrl : '/app/html/actors.html'
		})
		.when('/movies/edit/:id', {
			templateUrl : '/app/html/edit-movie.html'
		})
		
		.otherwise({
			redirectTo: '/'
		});
}]);