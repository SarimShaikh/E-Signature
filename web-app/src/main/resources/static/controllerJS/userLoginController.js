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
        if ($scope.loginForm.type == 'C') {
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
            _redirectToDashboard(res.data.outhToken, "U");
        } else {
            var employee = res.data.entityClass;
            employee.oathToken = res.data.outhToken;
            localStorage.setItem('empObject', JSON.stringify(employee));
            _redirectToDashboard(res.data.outhToken, "E");
        }
    }

    function _redirectToDashboard(token, userType) {
        var url = "";
        if(userType === "U"){
            url = "http://localhost:8080/api/auth/user/validate";
        } else{
            url = "http://localhost:8080/api/auth/employee/validate";
        }
        $http({
            method: 'POST',
            url: url,
            params: {outhToken: token}
        }).then(_loginSuccess, _loginError);
    }

    function _loginSuccess(res) {
        console.log("response", res.data.path);
        window.location.href = res.data.path;
    }

    function _loginError(res) {
        console.log("res", res);
        alert("Error: " + status + ":");
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