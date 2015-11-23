/**
 * Created by udbhavgoyal on 17/11/15.
 */
app.controller('ChangePasswordCtrl', function ($scope, $http,loggedUserDetails,$state) {

        if(loggedUserDetails.getName().length == 0){

             $state.go('LoginSignUp');
             }

    $scope.pwd_details = {};

    $scope.changePassword = function() {

        $http.post("/changepassword",$scope.pwd_details).then(function(response){

            toastr.success('Your password is changed');
            $scope.pwd_details = {};

        },function(response){
            toastr.error('Your current password is incorrect');
            $scope.pwd_details = {};
        });
    };
});