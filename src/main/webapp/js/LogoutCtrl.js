/**
 * Created by udbhavgoyal on 20/11/15.
 */

app.controller('LogoutCtrl',function($scope, $http,$state,loggedUserDetails) {

    loggedUserDetails.setName(null);
    loggedUserDetails.setUserImage(null);

});
