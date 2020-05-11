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

    // HTTP POST/PUT methods for add/edit User
    $scope.sendData = function () {
       console.log("$scope.loginForm", $scope.loginForm);
       var url = "";
       if($scope.loginForm.type == 'C'){
           url = "http://localhost:8080/api/auth/login?userType=user";
       } else {
           url = "http://localhost:8080/api/auth/login?userType=emp";
       }
        $http({
            method: "POST",
            url: url,
            data: angular.toJson($scope.loginForm),
            headers: {
                'Content-Type': 'application/json',
                'Access-Control-Allow-Origin': '*'
            }
        }).then(_success, _error);
    };

    function _success(res) {
        console.log('response', res.data);
        alert(res.data.message);
        if (res.data.responseCode == 200 && res.data.entityClass.userCode) {
            var user = res.data.entityClass;
            user.oathToken = res.data.outhToken;
            console.log('user', user);
            localStorage.setItem('userObject', JSON.stringify(user));
            window.location.href = 'http://localhost:8080/user/dashboard' + '?outhToken=' + res.data.outhToken;
        } else{
            var employee = res.data.entityClass;
            employee.oathToken = res.data.outhToken;
            localStorage.setItem('empObject', JSON.stringify(employee));
            window.location.href = 'http://localhost:8080/employee/dashboard' + '?outhToken=' + res.data.outhToken;
        }
    }

    function _error(res) {
        debugger;
        var data = res.data;
        var status = res.status;
        var header = res.header;
        var config = res.config;
        alert("Error: " + status + ":" + data);
    }

});