/**
 * Created by Sarim on 5/4/2020.
 */
var app = angular.module("userApp", []);

// Controller Part
app.controller("userController", function ($scope, $http) {


    $scope.users = [];
    $scope.userForm = {
        userCode: "",
        userName: "",
        userEmail: "",
        userPassword: "",
        confirmUserPassword: "",
        userContact: "",
        userCnic: "",
        userDob: "",
        userSignature: "",
        userAddress: ""
    };

    if (localStorage.getItem('userObject')) {
        $scope.userObject = JSON.parse(localStorage.getItem('userObject'));
        $scope.userForm.userCode = $scope.userObject.userCode;
        console.log("Admin ID----->" + $scope.userForm.userCode);
    }

    _getUserDataByCode();

    $scope.logout = function () {
        localStorage.clear();
    };

    $scope.sendData = function () {

        // Formatting Date Of Birth
        var dateOfBirth = new Date($scope.userForm.userDob);
        var formattedDate = dateOfBirth.getDate() + "-" + (dateOfBirth.getMonth() + 1) + "-" + dateOfBirth.getFullYear();
        var temp = $scope.userForm;

        temp.userDob = new Date(formattedDate);
        if (_validateEmail($scope.userForm.userEmail)) {
            alert("Invalid Email Address");
        }
        else {
            $http({
                method: "POST",
                url: "http://localhost:8080/api/auth/users/signup",
                data: angular.toJson(temp),
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

    function _validateEmail(email) {
        var emailValidity = !/^[a-z0-9](\.?[a-z0-9]){5,}@g(oogle)?mail\.com$/i.test(email);
        return emailValidity;
    }

    function _success(res) {
        if (res.data.responseCode == 201) {
            alert(res.data.message);
            window.location.replace('http://localhost:8080/login');
        }
    }

    function _error(res) {
        console.log('error', res);
        //alert("Error: " + res.statusText);
        $scope.resetForm();
    }

    $scope.resetForm = function (form) {
        angular.copy({}, $scope.userForm);
    }

});

// define declarationDocumentController


angular.module("userApp").controller("declarationDocumentController", function ($scope, $http) {

    $scope.declarations = [];
    $scope.userObject = {};
    $scope.signatures = [];
    $scope.signatureCode = "";
    $scope.userName= "";
    $scope.decForm = {
        userCode: "",
        documentName: "",
        abnNumber: "",
        bussinessClient: "",
        bsbNumber: "",
        agentName: "",
        documentPeriod: "",
        paperDeclareDate: "",
        bussinessAddress: "",
        documentStatus: "P",
        approvedBy: "NONE",
        signatureCode: "",
        signature: "",
        isActive: 1
    };

    if (localStorage.getItem('userObject')) {
        $scope.userObject = JSON.parse(localStorage.getItem('userObject'));
        $scope.signatureCode = $scope.userObject.userSignatureCode;
        $scope.decForm.userCode = $scope.userObject.userCode;
        $scope.userName= $scope.userObject.userName;
    }

    if ($scope.userObject.userSignature == null) {
        _generateSignature();

        function randomSignature(length, chars) {
            var result = '';
            for (var i = length; i > 0; --i) result += chars[Math.floor(Math.random() * chars.length)];
            return result;
        }

        function _generateSignature() {
            for (var i = 0; i < 4; i++) {
                $scope.signatures.push($scope.userName+randomSignature(3, '0123456789'));
            }
        }
    } else if ($scope.userObject.userSignature != null) {
        $scope.decForm.signature = $scope.userObject.userSignature;
    }


    $scope.sendDecDocData = function () {
        if ($scope.decForm.signatureCode != $scope.signatureCode) {
            alert("Invalid Signature Code \n please enter correct signature code.");
        } else {
            $http({
                method: "POST",
                url: "http://localhost:8080/api/auth/declaration/save",
                data: angular.toJson($scope.decForm),
                headers: {
                    'Content-Type': 'application/json',
                    'Access-Control-Allow-Origin': '*'
                }
            }).then(_success, _error);
        }
    };

    function _success(res) {
        if (res.status == 200) {
            $scope.userObject.userSignature = $scope.decForm.signature;
            if ($scope.userObject.isSignSelect == 'N') {
                _assignUsersignature();
            }
            else {
                alert("Form submit successfully");
                $scope.resetForm();
            }

        }
    }

    function _error(res) {
        debugger;
        console.log('error', res);
    }

    function _assignUsersignature() {
        $scope.userObject.isSignSelect = 'Y';
        $http({
            method: 'POST',
            url: 'http://localhost:8080/api/auth/users/update',
            data: angular.toJson($scope.userObject),
            headers: {
                'Content-Type': 'application/json',
                'Access-Control-Allow-Origin': '*'
            }
        }).then(
            function (res) { // success
                var user = res.data;
                const validKeys = [ 'userCode', 'userEmail', 'userSignatureCode','oathToken', 'userSignature', 'isSignSelect' ];
                Object.keys(user).forEach(function(key){
                    return validKeys.includes(key) || delete user[key];
                });
                const userObj = JSON.parse(localStorage.getItem('userObject'));
                user.oathToken = userObj.oathToken;
                localStorage.removeItem('userObject');
                localStorage.setItem('userObject', JSON.stringify(user));
                alert("Form submit successfully");
                window.location.reload(true);
            },
            function (res) { // error
                console.log("Error: " + res.status + " : " + res.data);
            }
        );
    }

    $scope.resetForm = function (form) {
        angular.copy({}, $scope.decForm);
    }

});

// Tax Document Module

angular.module("userApp").controller("taxDocumentController", function ($scope, $http) {

    $scope.userObject = {};
    $scope.signatures = [];
    $scope.signatureCode = "";
    $scope.userName= "";
    $scope.taxForm = {
        userCode: "",
        documentName: "",
        abnNumber: "",
        companyName: "",
        bussinessClient: "",
        contactNo: "",
        postCode: "",
        bankName: "",
        bsbNumber: "",
        accountNo: "",
        documentStatus: "P",
        bussinessAddress: "",
        homeAddress: "",
        approvedBy: "NONE",
        signatureCode: "",
        signature: "",
        isActive: 1
    };

    if (localStorage.getItem('userObject')) {
        $scope.userObject = JSON.parse(localStorage.getItem('userObject'));
        $scope.signatureCode = $scope.userObject.userSignatureCode;
        $scope.taxForm.userCode = $scope.userObject.userCode;
        $scope.userName = $scope.userObject.userName;
        console.log("Admin ID----->" + $scope.taxForm.userCode);
    }

    if ($scope.userObject.userSignature == null) {
        _generateSignature();

        function randomSignature(length, chars) {
            var result = '';
            for (var i = length; i > 0; --i) result += chars[Math.floor(Math.random() * chars.length)];
            return result;
        }

        function _generateSignature() {
            for (var i = 0; i < 4; i++) {
                $scope.signatures.push($scope.userName+randomSignature(3, '0123456789'));
            }
        }
    } else if ($scope.userObject.userSignature != null) {
        debugger;
        $scope.taxForm.signature = $scope.userObject.userSignature;
    }


    $scope.sendTaxDocData = function () {
        if ($scope.taxForm.signatureCode != $scope.signatureCode) {
            alert("Invalid Signature Code \n please enter correct signature code.");
        } else {
            $http({
                method: "POST",
                url: "http://localhost:8080/api/auth/taxDocument/save",
                data: angular.toJson($scope.taxForm),
                headers: {
                    'Content-Type': 'application/json',
                    'Access-Control-Allow-Origin': '*'
                }
            }).then(_success, _error);
        }
    };

    function _success(res) {
        if (res.status == 200) {
            console.log('res', res);
            $scope.userObject.userSignature = $scope.taxForm.signature;
            if ($scope.userObject.isSignSelect == 'N') {
                _assignUsersignature();
            }
            else {
                alert("Form submit successfully");
                $scope.resetForm();
            }

        }
    }

    function _error(res) {
        console.log('error', res);
    }

    function _assignUsersignature() {
        $scope.userObject.isSignSelect = 'Y';
        $http({
            method: 'POST',
            url: 'http://localhost:8080/api/auth/users/update',
            data: angular.toJson($scope.userObject),
            headers: {
                'Content-Type': 'application/json',
                'Access-Control-Allow-Origin': '*'
            }
        }).then(
            function (res) { // success
                var user = res.data;
                const validKeys = [ 'userCode', 'userEmail', 'userSignatureCode','oathToken', 'userSignature', 'isSignSelect' ];
                Object.keys(user).forEach(function(key){
                    return validKeys.includes(key) || delete user[key];
                });
                const userObj = JSON.parse(localStorage.getItem('userObject'));
                user.oathToken = userObj.oathToken;
                localStorage.removeItem('userObject');
                localStorage.setItem('userObject', JSON.stringify(user));
                alert("Form submit successfully");
                window.location.reload(true);
            },
            function (res) { // error
                console.log("Error: " + res.status + " : " + res.data);
            }
        );
    }

    $scope.resetForm = function (form) {
        angular.copy({}, $scope.taxForm);
    };

});


//pending documents Controller

angular.module("userApp").controller("pendingDocumentController", function ($scope, $http) {

    $scope.declarations = [];
    $scope.taxation = [];
    $scope.userObject = {};
    $scope.userCode = "";

    if (localStorage.getItem('userObject')) {
        $scope.userObject = JSON.parse(localStorage.getItem('userObject'));
        $scope.userCode = $scope.userObject.userCode;
    }
    _getPendingDocumentsByUserCode();

    $scope.sendDecDocData = function () {
        if ($scope.decForm.signatureCode != $scope.signatureCode) {
            alert("Invalid Signature Code \n please enter correct signature code.");
        } else {
            $http({
                method: "POST",
                url: "http://localhost:8080/api/auth/declaration/save",
                data: angular.toJson($scope.decForm),
                headers: {
                    'Content-Type': 'application/json',
                    'Access-Control-Allow-Origin': '*'
                }
            }).then(_success, _error);
        }
    };

    function _getPendingDocumentsByUserCode() {
        $http({
            method: 'POST',
            url: 'http://localhost:8080/api/auth/users/getuserPendingdocuments',
            params: {userCode: $scope.userCode}
        }).then(
            function (res) { // success
                console.log(res.data);
            },
            function (res) { // error
                console.log("Error: " + res.status + " : " + res.data);
            }
        );
    }

    function _success(res) {
        if (res.status == 200) {

        }
    }

    function _error(res) {
        debugger;
        console.log('error', res);
    }


});