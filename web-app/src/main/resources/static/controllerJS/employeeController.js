/**
 * Created by Sarim on 5/4/2020.
 */
var app = angular.module("empApp", []);

// Controller Part
app.controller("employeeController", function ($scope, $http) {


    $scope.employees = [];
    $scope.empForm = {
        employeeCode: "",
        employeeName: "",
        employeeEmail: "",
        employeePassword: "",
        employeeContact: "",
        isActive: 1
    };

    if (localStorage.getItem('empObject')) {
        debugger;
        $scope.empObject = JSON.parse(localStorage.getItem('empObject'));
        $scope.empForm.employeeCode = $scope.empObject.employeeCode;
        console.log("EMP ID----->" + $scope.empForm.employeeCode);
    }

    _getEmpDataByCode();

    $scope.sendData = function () {
        if (_validateEmail($scope.empForm.employeeEmail)) {
            alert("Invalid Email Address");
        } else {
            $http({
                method: "POST",
                url: "http://localhost:8080/api/auth/employee/signup",
                data: angular.toJson($scope.empForm),
                headers: {
                    'Content-Type': 'application/json',
                    'Access-Control-Allow-Origin': '*'
                }
            }).then(_success, _error);
        }
    };

    $scope.updateData = function () {

        $http({
            method: "POST",
            url: "http://localhost:8080/api/auth/employee/update",
            data: angular.toJson($scope.employees),
            headers: {
                'Content-Type': 'application/json',
                'Access-Control-Allow-Origin': '*'
            }
        }).then(
            function (res) { // success
                $scope.employees = res.data;
                alert("Update Successfully");
            },
            function (res) { // error
                console.log("Error: " + res.status + " : " + res.data);
            }
        );
    };

    function _getEmpDataByCode() {
        debugger;
        $http({
            method: 'POST',
            url: 'http://localhost:8080/api/auth/employee/getemployee',
            params: {empCode: $scope.empForm.employeeCode}
        }).then(
            function (res) { // success
                $scope.employees = res.data;
            },
            function (res) { // error
                console.log("Error: " + res.status + " : " + res.data);
            }
        );
    }

    function _validateEmail(email) {
        var emailValidity = !/^[a-z0-9](\.?[a-z0-9]){5,}@g(oogle)?mail\.com$/i.test(email);
        return emailValidity;
    }

    function _success(res) {
        debugger;
        if (res.data.responseCode == 201) {
            alert(res.data.message);
            window.location.replace('http://localhost:8080/login');
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