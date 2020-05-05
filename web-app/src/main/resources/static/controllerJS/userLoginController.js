/**
 * Created by Sarim on 5/5/2020.
 */
/**
 * Created by Sarim on 4/19/2020.
 */

var app = angular.module("userLogin", []);

// Controller Part
app.controller("userLoginController", function ($scope, $http) {

    $scope.loginForm = {
        email: "",
        password: "",
        type: ""
    };

    // HTTP POST/PUT methods for add/edit student
    // Call: http://localhost:8080/student
    $scope.sendData = function () {

        $http({
            method: "POST",
            url: "http://localhost:8080/api/auth/users/login",
            data: angular.toJson($scope.loginForm),
            headers: {
                'Content-Type': 'application/json',
                'Access-Control-Allow-Origin': '*'
            }
        }).then(_success, _error);
    };

    $scope.sendempLogin = function () {

        $http({
            method: "POST",
            url: "http://localhost:8080/api/auth/employee/login",
            data: angular.toJson($scope.loginForm),
            headers: {
                'Content-Type': 'application/json',
                'Access-Control-Allow-Origin': '*'
            }
        }).then(function (res) { // success
                alert("LogIn");
                if (res.data.responseCode == 200) {
                    localStorage.setItem('empObject', JSON.stringify(res.data.entityClass));
                    window.location.replace('http://localhost:8080/employee/dashboard');
                }
            },
            function (res) { // error
                console.log("Error: " + res.status + " : " + res.data);
            });
    };

    function _success(res) {
        console.log('response', res);
        alert(res.data.message);
        if (res.data.responseCode == 200) {
            debugger;
            localStorage.setItem('userObject', JSON.stringify(res.data.entityClass));
            window.location.replace('http://localhost:8080/user/dashboard');
        }
    }

    function _error(res) {
        var data = res.data;
        var status = res.status;
        var header = res.header;
        var config = res.config;
        alert("Error: " + status + ":" + data);
    }

});