/**
 * Created by udbhavgoyal on 16/11/15.
 */
app.controller('DashboardCtrl',function($scope,loggedUserDetails,$http,$state) {

    if(loggedUserDetails.getName()==null){

        $state.go('LoginSignUp');
    }

    $scope.name=loggedUserDetails.getName();

     $scope.image = loggedUserDetails.getUserImage();


    $http.post("/displaytweets").then(function(response){
          console.log("inside display tweet function");

        $scope.usertweets=response.data;
    },function(error){
    });

    $scope.displaynonfollowers = {};

    $http.post("/displaynonfollowers").then( function(response){

            console.log("inside display non followers function");

        console.log("nonfollowers list displayed successfully");

        $scope.displaynonfollowers = response.data.nonfollowerslist;

    },function(response){
        console.log("failed to display nonfollowers list!");

    });

    $scope.displayfollowers = {};
    $http.post("/displayfollowers").then( function(response){
            console.log("inside displayfollowers function");
            //console.log(response);
            $scope.displayfollowers = response.data.followerslist;


        }, function (response){
            console.log("failed to display followers list!");

        }
    );

        $scope.user_tweet = {};
      $scope.postTweet = function() {

        console.log("inside post tweet function");
        $http.post("/posttweet",$scope.user_tweet).then(function(response){

            $http.post("/displaytweets").then(function(response){
                console.log(response.data);
                $scope.usertweets=response.data;

            },function(error){

            });
            $scope.user_tweet = {};
            console.log(response);
            console.log("Tweet posted successfully !");


        },function(response){
            console.log("failed to post tweet !");

        });
    };

    $scope.followUsers = function(data) {

        $scope.jsondata = {"email":data};
        console.log($scope.jsondata)
      ;
        $http.post("/followuser",$scope.jsondata).then(function (response){
            console.log(response);
             $http.post("/displayfollowers").then( function(response){
                        console.log("inside displayfollowers function");

                        $scope.displayfollowers = response.data.followerslist;


                    }, function (response){
                        console.log("failed to display followers list!");

                    }

                );
                 $http.post("/displaynonfollowers").then( function(response){

                            console.log("inside display non followers function");

                        console.log("nonfollowers list displayed successfully");

                        $scope.displaynonfollowers = response.data.nonfollowerslist;

                    },function(response){
                        console.log("failed to display nonfollowers list!");

                    });


        },function(response) {
            console.log(response);

        });

    };

    $scope.unfollowUser = function (data) {

        $scope.jsondata = {"email":data};
        $http.post("/unfollowuser",$scope.jsondata).then(function (response){
            console.log(response);
             $http.post("/displayfollowers").then( function(response){
                        console.log("inside displayfollowers function");
                     //   console.log(response);
                        $scope.displayfollowers = response.data.followerslist;


                    }, function (response){
                        console.log("failed to display followers list!");

                    }

                );
                 $http.post("/displaynonfollowers").then( function(response){

                            console.log("inside display non followers function");

                                           console.log("nonfollowers list displayed successfully");

                        $scope.displaynonfollowers = response.data.nonfollowerslist;

                    },function(response){
                        console.log("failed to display nonfollowers list!");

                    });


        },function(response) {
            console.log(response);

        });

    }

   });