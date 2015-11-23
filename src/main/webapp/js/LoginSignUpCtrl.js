/**
 * Created by udbhavgoyal on 6/11/15.
 */

app.controller('LoginSignUpCtrl',function($scope, $http,$state,loggedUserDetails) {

    $scope.user = {};
    console.log("entry in loginsignup controller");
   // $scope.imageurl = {};
    $scope.registerUser = function() {
        console.log($scope.user);
        $scope.prograssing = true;
        $http.post("/welcome",$scope.user).then(function(response){

            console.log(response.data.firstname+" successfully registered !");

            $scope.prograssing = false;
                toastr.success('You have been successfully registered ! Check your mail');


            $scope.user = {}; //Clearing input fields
        },function(response){
             $scope.prograssing = false;
            console.log("failed to register !");

         toastr.error('You are already registered !');
         $scope.user = {};

        });
    };

    $scope.user_cred = {};

    $scope.loginUser = function(){

        $http.post("/login", $scope.user_cred).then(function (response) {


            console.log("successfully logged in");
            console.log(response);
            if(response.statusText=="OK"){
                     $state.go('Dashboard');
             loggedUserDetails.setName(response.data.name);
             loggedUserDetails.setUserImage(response.data.imageurl);

                           }
            else{
                console.log("some error");
            }



        }, function (response) {
            console.log("failed to log in");
          //  alert("Invalid username or password !");
            toastr.error('Invalid username or password !');
            $scope.user_cred = {};


        });
    };
});