/**
 * Created by udbhavgoyal on 16/11/15.
 */
app.config(function($stateProvider){

    $stateProvider

        .state('LoginSignUp',{
            url:'/loginsignup',
            templateUrl: 'templates/loginsignup.html',
            controller: 'LoginSignUpCtrl'
        })

        .state('Dashboard',{
            url:'/dashboard',
            templateUrl: 'templates/dashboard.html',
            controller: 'DashboardCtrl'
        })

        .state('ChangePassword',{
            url:'/changepassword',
            templateUrl: 'templates/changepassword.html',
            controller: 'ChangePasswordCtrl'
        })

        .state('Logout' ,{
            url:'/loginsignup',
            templateUrl: 'templates/loginsignup.html',
            controller:'LogoutCtrl'

        })

});