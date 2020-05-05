/**
 * Created by Sarim on 5/4/2020.
 */
var app = angular.module("userApp", []);

// Controller Part
app.controller("userController", function ($scope, $http) {


    $scope.users = [];
    $scope.userForm = {
        userCode:"",
        userName: "",
        userEmail: "",
        userPassword: "",
        confirmUserPassword: "",
        userContact: "",
        userCnic:"",
        userDob:"",
        userSignature:"",
        userAddress:""
    };

    if (localStorage.getItem('userObject')) {
        debugger;
        $scope.userObject = JSON.parse(localStorage.getItem('userObject'));
        $scope.userForm.userCode = $scope.userObject.userCode;
        console.log("Admin ID----->" + $scope.userForm.userCode);
    }

    _getUserDataByCode();

    $scope.sendData = function () {

        $http({
            method: "POST",
            url: "http://localhost:8080/api/auth/users/signup",
            data: angular.toJson($scope.userForm),
            headers: {
                'Content-Type': 'application/json',
                'Access-Control-Allow-Origin': '*'
            }
        }).then(_success, _error);
    };

    $scope.updateData = function () {

        $http({
            method: "POST",
            url: "http://localhost:8080/api/auth/users/update",
            data: angular.toJson($scope.users),
            headers: {
                'Content-Type': 'application/json',
                'Access-Control-Allow-Origin': '*'
            }
        }).then(
            function (res) { // success
                $scope.users = res.data;
                alert("Update Successfully");
            },
            function (res) { // error
                console.log("Error: " + res.status + " : " + res.data);
            }
        );
    };

    function _getUserDataByCode() {
        debugger;
        $http({
            method: 'POST',
            url: 'http://localhost:8080/api/auth/users/getuser',
            params: {userCode: $scope.userForm.userCode}
        }).then(
            function (res) { // success
                $scope.users = res.data;
            },
            function (res) { // error
                console.log("Error: " + res.status + " : " + res.data);
            }
        );
    }

    function _success(res) {
        debugger;
        if (res.data.responseCode == 201) {
            alert(res.data.message);
            window.location.replace('http://localhost:8080/user/login');
        }
    }

    function _error(res) {
        debugger;
        console.log('error', res);
        //alert("Error: " + res.statusText);
        $scope.resetForm();
    }

    $scope.resetForm = function (form) {
        angular.copy({}, $scope.userForm);
    }

});