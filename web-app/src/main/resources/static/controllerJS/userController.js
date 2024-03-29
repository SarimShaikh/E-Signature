/**
 * Created by Sarim on 5/4/2020.
 */
var app = angular.module("userApp", []);

// Controller Part
app.controller("userController", function ($scope, $http) {


    $scope.users = [];
    $scope.userPendingDecCount = '';
    $scope.userPendingTaxCount = '';
    $scope.userApprovedDecCount = '';
    $scope.userApprovedTaxCount = '';
    $scope.userRejectCount = '';
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

    _getUserPendingDecFormsCount();
    _getUserApprovedDecFormsCount();
    _getUserPendingTaxFormsCount();
    _getUSerApprovedTaxFormsCount();
    _getUSerRejectDocCount();

    function _getUserPendingDecFormsCount() {
        $http({
            method: 'POST',
            url: 'http://localhost:8080/api/auth/declaration/getAllPendingUserDecdocumentsCount',
            params: {userCode: $scope.userForm.userCode}
        }).then(
            function (res) { // success
                $scope.userPendingDecCount = res.data;
                console.log("----->"+$scope.userPendingDecCount);
            },
            function (res) { // error
                console.log("Error: " + res.status + " : " + res.data);
            }
        );
    }

    function _getUserApprovedDecFormsCount() {
        $http({
            method: 'POST',
            url: 'http://localhost:8080/api/auth/declaration/getAllApprovedUserDecdocumentsCount',
            params: {userCode: $scope.userForm.userCode}
        }).then(
            function (res) { // success
                $scope.userApprovedDecCount = res.data;
                console.log("------>"+$scope.userApprovedDecCount);
            },
            function (res) { // error
                console.log("Error: " + res.status + " : " + res.data);
            }
        );
    }

    function _getUserPendingTaxFormsCount() {
        $http({
            method: 'POST',
            url: 'http://localhost:8080/api/auth/taxDocument/getAllPendingUserTaxdocumentsCount',
            params: {userCode: $scope.userForm.userCode}
        }).then(
            function (res) { // success
                $scope.userPendingTaxCount = res.data;
                console.log("----->"+$scope.userPendingTaxCount);
            },
            function (res) { // error
                console.log("Error: " + res.status + " : " + res.data);
            }
        );
    }

    function _getUSerApprovedTaxFormsCount() {
        $http({
            method: 'POST',
            url: 'http://localhost:8080/api/auth/taxDocument/getAllApprovedUserTaxdocumentsCount',
            params: {userCode: $scope.userForm.userCode}
        }).then(
            function (res) { // success
                $scope.userApprovedTaxCount = res.data;
                console.log("------->"+$scope.userApprovedTaxCount);
            },
            function (res) { // error
                console.log("Error: " + res.status + " : " + res.data);
            }
        );
    }

    function _getUSerRejectDocCount() {
        $http({
            method: 'POST',
            url: 'http://localhost:8080/api/auth/rejectedDocuments/getAllRejectUserDecdocumentsCount',
            params: {userCode: $scope.userForm.userCode}
        }).then(
            function (res) { // success
                $scope.userRejectCount = res.data;
                console.log("------->"+$scope.userRejectCount);
            },
            function (res) { // error
                console.log("Error: " + res.status + " : " + res.data);
            }
        );
    }


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

    function _subStrAfterChars(str, char, pos) {
        if (pos == 'b')
            return str.substring(str.indexOf(char) + 1);
        else if (pos == 'a')
            return str.substring(0, str.indexOf(char));
        else
            return str;
    }

    function _getUserDataByCode() {
        $http({
            method: 'POST',
            url: 'http://localhost:8080/api/auth/users/getuser',
            params: {userCode: $scope.userForm.userCode}
        }).then(
            function (res) { // success
                $scope.users = res.data;
                if (res.data.isSignSelect == 'Y' && document.getElementById("signature")) {
                    var font = _subStrAfterChars(res.data.signatureFonts, '="', 'b').replace('>', '');
                    var finalSignFont = font.replace(/"/g, '');
                    document.getElementById("signature").style = finalSignFont;
                }
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
        else if (res.data.responseCode == 401) {
            alert(res.data.message);
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
    $scope.signature = "";
    $scope.signatureCode = "";
    $scope.userName = "";
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
        signatureFonts: "",
        isActive: 1
    };

    if (localStorage.getItem('userObject')) {
        $scope.userObject = JSON.parse(localStorage.getItem('userObject'));
        $scope.signatureCode = $scope.userObject.userSignatureCode;
        $scope.decForm.userCode = $scope.userObject.userCode;
        $scope.userName = $scope.userObject.userName;
    }

    if (Object.keys($scope.userObject).length > 0 && $scope.userObject.userSignature == null) {
        _generateSignature();
        function _generateSignature() {
            $scope.signature = $scope.userName;
        }

    } else if ($scope.userObject.userSignature != null) {
        $scope.decForm.signature = $scope.userObject.userSignature;
        $scope.decForm.signatureFonts = $scope.userObject.signatureFonts;

        if ($scope.userObject.isSignSelect == 'Y' && document.getElementById("decSignature")) {
            var userObj = JSON.parse(localStorage.getItem('userObject'));
            var font = _subStrAfterChars(userObj.signatureFonts, '="', 'b').replace('>', '');
            var finalSignFont = font.replace(/"/g, '');
            document.getElementById("decSignature").style = finalSignFont;
        }
    }

    $scope.getSignatureFonts = function (item) {
        switch (item) {
            case 1:
                $scope.decForm.signatureFonts = '<p style="font-family:' + ' ' + "'Rancho', cursive;" + '"' + ">";
                break;
            case 2:
                $scope.decForm.signatureFonts = '<p style="font-family:' + ' ' + "'Sacramento', cursive;" + '"' + ">";
                break;
            case 3:
                $scope.decForm.signatureFonts = '<p style="font-family:' + ' ' + "'Euphoria Script', cursive;" + '"' + ">";
                break;
            case 4:
                $scope.decForm.signatureFonts = '<p style="font-family:' + ' ' + "'Pinyon Script', cursive;" + '"' + ">";
        }
    };

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

    function _subStrAfterChars(str, char, pos) {
        if (pos == 'b')
            return str.substring(str.indexOf(char) + 1);
        else if (pos == 'a')
            return str.substring(0, str.indexOf(char));
        else
            return str;
    }

    function _success(res) {
        if (res.status == 200) {
            $scope.userObject.userSignature = $scope.decForm.signature;
            $scope.userObject.signatureFonts = $scope.decForm.signatureFonts;
            if ($scope.userObject.isSignSelect == 'N') {
                _assignUsersignature();
            }
            else {
                alert("Form submit successfully");
                if ($scope.userObject.isSignSelect == 'Y' && document.getElementById("decSignature")) {
                    var userObj = JSON.parse(localStorage.getItem('userObject'));
                    var font = _subStrAfterChars(userObj.signatureFonts, '="', 'b').replace('>', '');
                    var finalSignFont = font.replace(/"/g, '');
                    document.getElementById("decSignature").style = finalSignFont;
                }
                window.location.reload(true);
            }

        }
    }

    function _error(res) {
        debugger;
        console.log('error', res);
    }

    function _assignUsersignature() {
        $scope.userObject.isSignSelect = 'Y';
        debugger;
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
                const validKeys = ['userCode', 'userEmail', 'userSignatureCode', 'signatureFonts', 'oathToken', 'userSignature', 'isSignSelect'];
                Object.keys(user).forEach(function (key) {
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
    $scope.signature = "";
    $scope.signatureCode = "";
    $scope.userName = "";
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
        signatureFonts: "",
        isActive: 1
    };

    if (localStorage.getItem('userObject')) {
        $scope.userObject = JSON.parse(localStorage.getItem('userObject'));
        $scope.signatureCode = $scope.userObject.userSignatureCode;
        $scope.taxForm.userCode = $scope.userObject.userCode;
        $scope.userName = $scope.userObject.userName;
        console.log("Admin ID----->" + $scope.taxForm.userCode);
    }

    if (Object.keys($scope.userObject).length > 0 && $scope.userObject.userSignature == null) {
        _generateSignature();
        function _generateSignature() {
            $scope.signature = $scope.userName;
        }

    } else if ($scope.userObject.userSignature != null) {
        $scope.taxForm.signature = $scope.userObject.userSignature;
        $scope.taxForm.signatureFonts = $scope.userObject.signatureFonts;

        if ($scope.userObject.isSignSelect == 'Y' && document.getElementById("taxSignature")) {
            var userObj = JSON.parse(localStorage.getItem('userObject'));
            var font = _subStrAfterChars(userObj.signatureFonts, '="', 'b').replace('>', '');
            var finalSignFont = font.replace(/"/g, '');
            document.getElementById("taxSignature").style = finalSignFont;
        }
    }

    $scope.getSignatureFonts = function (item) {
        switch (item) {
            case 1:
                $scope.taxForm.signatureFonts = '<p style="font-family:' + ' ' + "'Rancho', cursive;" + '"' + ">";
                break;
            case 2:
                $scope.taxForm.signatureFonts = '<p style="font-family:' + ' ' + "'Sacramento', cursive;" + '"' + ">";
                break;
            case 3:
                $scope.taxForm.signatureFonts = '<p style="font-family:' + ' ' + "'Euphoria Script', cursive;" + '"' + ">";
                break;
            case 4:
                $scope.taxForm.signatureFonts = '<p style="font-family:' + ' ' + "'Pinyon Script', cursive;" + '"' + ">";
        }
    };

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

    function _subStrAfterChars(str, char, pos) {
        if (pos == 'b')
            return str.substring(str.indexOf(char) + 1);
        else if (pos == 'a')
            return str.substring(0, str.indexOf(char));
        else
            return str;
    }

    function _success(res) {
        if (res.status == 200) {
            console.log('res', res);
            $scope.userObject.userSignature = $scope.taxForm.signature;
            $scope.userObject.signatureFonts = $scope.taxForm.signatureFonts;
            if ($scope.userObject.isSignSelect == 'N') {
                _assignUsersignature();
            }
            else {
                alert("Form submit successfully");
                if ($scope.userObject.isSignSelect == 'Y' && document.getElementById("taxSignature")) {
                    var userObj = JSON.parse(localStorage.getItem('userObject'));
                    var font = _subStrAfterChars(userObj.signatureFonts, '="', 'b').replace('>', '');
                    var finalSignFont = font.replace(/"/g, '');
                    document.getElementById("taxSignature").style = finalSignFont;
                }
                window.location.reload(true);
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
                const validKeys = ['userCode', 'userEmail', 'userSignatureCode', 'signatureFonts', 'oathToken', 'userSignature', 'isSignSelect'];
                Object.keys(user).forEach(function (key) {
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

    $scope.sendDecDocData = function (item) {
        $http({
            method: "POST",
            url: "http://localhost:8080/generate-pdf",
            data: angular.toJson(item),
            params: {docType: "D"},
            headers: {
                'Content-Type': 'application/json',
                'Access-Control-Allow-Origin': '*'
            }
        }).then(_success, _error);

    };

    $scope.sendTaxDocData = function (item) {
        debugger;
        console.log("------->", item);
        $http({
            method: "POST",
            url: "http://localhost:8080/generate-pdf",
            data: angular.toJson(item),
            params: {docType: "T"},
            headers: {
                'Content-Type': 'application/json',
                'Access-Control-Allow-Origin': '*'
            }
        }).then(_success, _error);

    };

    function _getPendingDocumentsByUserCode() {
        $http({
            method: 'POST',
            url: 'http://localhost:8080/api/auth/users/getuserPendingdocuments',
            params: {userCode: $scope.userCode}
        }).then(
            function (res) { // success
                console.log("res.data", res.data);
                var declarations = res.data.declarationDocuments;
                var taxDocuments = res.data.taxDocuments;

                declarations.map(function (item, index) {
                    declarations[index].userName = res.data.userName;
                    declarations[index].userEmail = res.data.userEmail;
                    declarations[index].paperDeclareDate = new Date(declarations[index].paperDeclareDate).toISOString().slice(0, 10);
                });

                taxDocuments.map(function (item, index) {
                    taxDocuments[index].userName = res.data.userName;
                    taxDocuments[index].userEmail = res.data.userEmail;
                });
                $scope.declarations = declarations;
                $scope.taxation = taxDocuments;
            },
            function (res) { // error
                console.log("Error: " + res.status + " : " + res.data);
            }
        );
    }

    function _success(res) {
        if (res.status == 200) {
            $('#loading').hide();
            alert("PDF generated!");
        }
    }

    function _error(res) {
        debugger;
        console.log('error', res);
    }

    $(document).on('click', '#btn', function () {
        $('#loading').show();
    });

    $(window).load(function () {
        $('#loading').hide();
    });

});


//Approved documents Controller

angular.module("userApp").controller("approvedDocumentController", function ($scope, $http) {

    $scope.declarations = [];
    $scope.taxation = [];
    $scope.userObject = {};
    $scope.userCode = "";

    if (localStorage.getItem('userObject')) {
        $scope.userObject = JSON.parse(localStorage.getItem('userObject'));
        $scope.userCode = $scope.userObject.userCode;
    }
    _getPendingDocumentsByUserCode();

    $scope.sendDecDocData = function (item) {
        $http({
            method: "POST",
            url: "http://localhost:8080/generate-pdf",
            data: angular.toJson(item),
            params: {docType: "D"},
            headers: {
                'Content-Type': 'application/json',
                'Access-Control-Allow-Origin': '*'
            }
        }).then(_success, _error);

    };

    $scope.sendTaxDocData = function (item) {
        debugger;
        console.log("------->", item);
        $http({
            method: "POST",
            url: "http://localhost:8080/generate-pdf",
            data: angular.toJson(item),
            params: {docType: "T"},
            headers: {
                'Content-Type': 'application/json',
                'Access-Control-Allow-Origin': '*'
            }
        }).then(_success, _error);

    };

    function _getPendingDocumentsByUserCode() {
        $http({
            method: 'POST',
            url: 'http://localhost:8080/api/auth/users/getuserApprovedocuments',
            params: {userCode: $scope.userCode}
        }).then(
            function (res) { // success
                console.log("res.data", res.data);
                var declarations = res.data.declarationDocuments;
                var taxDocuments = res.data.taxDocuments;

                declarations.map(function (item, index) {
                    declarations[index].userName = res.data.userName;
                    declarations[index].userEmail = res.data.userEmail;
                    declarations[index].paperDeclareDate = new Date(declarations[index].paperDeclareDate).toISOString().slice(0, 10);
                });

                taxDocuments.map(function (item, index) {
                    taxDocuments[index].userName = res.data.userName;
                    taxDocuments[index].userEmail = res.data.userEmail;
                });
                $scope.declarations = declarations;
                $scope.taxation = taxDocuments;
            },
            function (res) { // error
                console.log("Error: " + res.status + " : " + res.data);
            }
        );
    }

    function _success(res) {
        if (res.status == 200) {
            $('#loading').hide();
            alert("PDF generated!");
        }
    }

    function _error(res) {
        debugger;
        console.log('error', res);
    }

    $(document).on('click', '#btn', function () {
        $('#loading').show();
    });

    $(window).load(function () {
        $('#loading').hide();
    });

});


//Rejected documents Controller

angular.module("userApp").controller("rejectedDocumentController", function ($scope, $http) {

    $scope.RejectedForms = [];
    $scope.userCode = "";

    if (localStorage.getItem('userObject')) {
        $scope.userObject = JSON.parse(localStorage.getItem('userObject'));
        $scope.userCode = $scope.userObject.userCode;
    }

    _getRejectDocuments();

    function _getRejectDocuments() {
        $http({
            method: 'GET',
            url: 'http://localhost:8080/api/auth/users/get?id=' + $scope.userCode
        }).then(
            function (res) { // success
                console.log("res.data", res.data);
                $scope.RejectedForms = res.data.rejectedDocuments;
            },
            function (res) { // error
                console.log("Error: " + res.status + " : " + res.data);
            }
        );
    }

    function _success(res) {
        if (res.status == 200) {
            $('#loading').hide();
            alert("PDF generated!");
        }
    }

    function _error(res) {
        debugger;
        console.log('error', res);
    }

    $(document).on('click', '#btn', function () {
        $('#loading').show();
    });

    $(window).load(function () {
        $('#loading').hide();
    });

});