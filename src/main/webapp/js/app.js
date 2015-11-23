/**
 * Created by udbhavgoyal on 16/11/15.
 */
var app = angular.module('MyTwitter',['ui.router','ngStorage','angularSpinkit']);
app.controller('MainCtrl',function($state,loggedUserDetails){
  if(loggedUserDetails.getName()==null){
        $state.go('LoginSignUp');
    }
    else{
        $state.go('Dashboard');
    }
})