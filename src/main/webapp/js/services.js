/**
 * Created by udbhavgoyal on 17/11/15.
 */

app.service('loggedUserDetails',function($localStorage) {

    this.setName = function (uname) {
        $localStorage.name = uname;

    }
    this.getName = function () {
        return $localStorage.name;
    }

    this.setUserImage = function(image){
            $localStorage.userimage = image;
    }
    this.getUserImage = function(){
        return $localStorage.userimage;
    }

});

