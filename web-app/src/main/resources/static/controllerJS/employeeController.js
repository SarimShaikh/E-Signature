/**
 * Created by Sarim on 5/4/2020.
 */
var app = angular.module("empApp", []);

// Controller Part
app.controller("employeeController", function ($scope, $http) {


    $scope.employees = [];
    $scope.employeePendingDecCount = '';
    $scope.employeePendingTaxCount = '';
    $scope.employeeApprovedDecCount = '';
    $scope.employeeApprovedTaxCount = '';
    $scope.empForm = {
        employeeCode: "",
        employeeName: "",
        employeeEmail: "",
        employeePassword: "",
        employeeContact: "",
        isActive: 1
    };

    if (localStorage.getItem('empObject')) {
        $scope.empObject = JSON.parse(localStorage.getItem('empObject'));
        $scope.empForm.employeeCode = $scope.empObject.employeeCode;
        console.log("EMP ID----->" + $scope.empForm.employeeCode);
    }

    _getEmpDataByCode();

    // Getting Pending Documents Count
    _getEmpPendingDecForms();
    _getEmpPendingTaxForms();

    // Getting Approved Documents Count
    _getEmpApprovedDecForms();
    _getEmpApprovedTaxForms();

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

    function _getEmpPendingDecForms() {
        $http({
            method: 'POST',
            url: 'http://localhost:8080/api/auth/declaration/getAllPendingDecdocumentsCount'
        }).then(
            function (res) { // success
                $scope.employeePendingDecCount = res.data;
            },
            function (res) { // error
                console.log("Error: " + res.status + " : " + res.data);
            }
        );
    }

    function _getEmpApprovedDecForms() {
        $http({
            method: 'POST',
            url: 'http://localhost:8080/api/auth/declaration/getAllApprovedDecdocumentsCount'
        }).then(
            function (res) { // success
                $scope.employeeApprovedDecCount = res.data;
            },
            function (res) { // error
                console.log("Error: " + res.status + " : " + res.data);
            }
        );
    }

    function _getEmpPendingTaxForms() {
        $http({
            method: 'POST',
            url: 'http://localhost:8080/api/auth/taxDocument/getAllPendingTaxdocumentsCount'
        }).then(
            function (res) { // success
                $scope.employeePendingTaxCount = res.data;
            },
            function (res) { // error
                console.log("Error: " + res.status + " : " + res.data);
            }
        );
    }

    function _getEmpApprovedTaxForms() {
        $http({
            method: 'POST',
            url: 'http://localhost:8080/api/auth/taxDocument/getAllApprovedTaxdocumentsCount'
        }).then(
            function (res) { // success
                $scope.employeeApprovedTaxCount = res.data;
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

    $scope.logout = function () {
        localStorage.clear();
    };

    $scope.resetForm = function (form) {
        angular.copy({}, $scope.userForm);
    }

});


//pending EmpDocuments Controller
angular.module("empApp").controller("pendingEmpDocumentController", function ($scope, $http) {

    $scope.declarations = [];
    $scope.taxation = [];
    $scope.empName = "";

    if (localStorage.getItem('empObject')) {
        $scope.empObject = JSON.parse(localStorage.getItem('empObject'));
        $scope.empName = $scope.empObject.employeeName;
        console.log("EMP Name----->" + $scope.empObject.employeeName);
    }

    _getAllPendingDecDocuments();
    _getAllPendingTaxDocuments();

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

    //update Declaration for Approval
    $scope.approvedDecDoc = function (item) {
        item.documentStatus="A";
        item.approvedBy = $scope.empName;
        $http({
            method: "POST",
            url: "http://localhost:8080/api/auth/declaration/update",
            data: angular.toJson(item),
            headers: {
                'Content-Type': 'application/json',
                'Access-Control-Allow-Origin': '*'
            }
        }).then(function (res) { // success
                console.log("res.data", res.data);
                if (res.status == 200) {
                    window.location.reload(true);
                }
            },
            function (res) { // error
                console.log("Error: " + res.status + " : " + res.data);
            });
    };

    //Delete Declaration for Rejection
    $scope.rejectDecDoc = function (item) {
        $http({
            method: "DELETE",
            url: "http://localhost:8080/api/auth/declaration/delete",
            data: angular.toJson(item),
            headers: {
                'Content-Type': 'application/json',
                'Access-Control-Allow-Origin': '*'
            }
        }).then(function (res) { // success
                console.log("res.data", res.data);
                if (res.status == 200) {
                    window.location.reload(true);
                }
            },
            function (res) { // error
                console.log("Error: " + res.status + " : " + res.data);
            });
    };

    //update Taxation for Approval
    $scope.approvedTaxDoc = function (item) {
        item.documentStatus="A";
        item.approvedBy = $scope.empName;
        $http({
            method: "POST",
            url: "http://localhost:8080/api/auth/taxDocument/update",
            data: angular.toJson(item),
            headers: {
                'Content-Type': 'application/json',
                'Access-Control-Allow-Origin': '*'
            }
        }).then(function (res) { // success
                console.log("res.data", res.data);
                if (res.status == 200) {
                    window.location.reload(true);
                }
            },
            function (res) { // error
                console.log("Error: " + res.status + " : " + res.data);
            });
    };

    //Delete Taxaction for Rejection
    $scope.rejectTaxDoc = function (item) {
        $http({
            method: "DELETE",
            url: "http://localhost:8080/api/auth/taxDocument/delete",
            data: angular.toJson(item),
            headers: {
                'Content-Type': 'application/json',
                'Access-Control-Allow-Origin': '*'
            }
        }).then(function (res) { // success
                console.log("res.data", res.data);
                if (res.status == 200) {
                    window.location.reload(true);
                }
            },
            function (res) { // error
                console.log("Error: " + res.status + " : " + res.data);
            });
    };

    function _getAllPendingDecDocuments() {
        $http({
            method: 'POST',
            url: 'http://localhost:8080/api/auth/declaration/getAllPendingDecdocuments'
        }).then(
            function (res) { // success
                console.log("res.data", res.data);
                var declarations = [];

                res.data.forEach(function (item, index) {
                    var tempItem = item;
                    tempItem.entity.userName = item.userName;
                    tempItem.entity.userEmail = item.userEmail;
                    declarations.push(tempItem.entity);
                });

                $scope.declarations = declarations;
            },
            function (res) { // error
                console.log("Error: " + res.status + " : " + res.data);
            }
        );
    }

    function _getAllPendingTaxDocuments() {
        $http({
            method: 'POST',
            url: 'http://localhost:8080/api/auth/taxDocument/getAllPendingTaxdocuments'
        }).then(
            function (res) { // success
                console.log("res.data", res.data);
                var taxation = [];

                res.data.forEach(function (item, index) {
                    var tempItem = item;
                    tempItem.entity.userName = item.userName;
                    tempItem.entity.userEmail = item.userEmail;
                    taxation.push(tempItem.entity);
                });

                $scope.taxation = taxation;
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

//Approved EmpDocuments Controller
angular.module("empApp").controller("approvedEmpDocumentController", function ($scope, $http) {

    $scope.declarations = [];
    $scope.taxation = [];
    $scope.empName = "";

    if (localStorage.getItem('empObject')) {
        $scope.empObject = JSON.parse(localStorage.getItem('empObject'));
        $scope.empName = $scope.empObject.employeeName;
        console.log("EMP Name----->" + $scope.empObject.employeeName);
    }

    _getAllApprovedDecDocuments();
    _getAllApprovedTaxDocuments();

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

    function _getAllApprovedDecDocuments() {
        $http({
            method: 'POST',
            url: 'http://localhost:8080/api/auth/declaration/getAllApprovedDecdocuments'
        }).then(
            function (res) { // success
                console.log("res.data", res.data);
                var declarations = [];

                res.data.forEach(function (item, index) {
                    var tempItem = item;
                    tempItem.entity.userName = item.userName;
                    tempItem.entity.userEmail = item.userEmail;
                    declarations.push(tempItem.entity);
                });

                $scope.declarations = declarations;
            },
            function (res) { // error
                console.log("Error: " + res.status + " : " + res.data);
            }
        );
    }

    function _getAllApprovedTaxDocuments() {
        $http({
            method: 'POST',
            url: 'http://localhost:8080/api/auth/taxDocument/getAllApprovedTaxdocuments'
        }).then(
            function (res) { // success
                console.log("res.data", res.data);
                var taxation = [];

                res.data.forEach(function (item, index) {
                    var tempItem = item;
                    tempItem.entity.userName = item.userName;
                    tempItem.entity.userEmail = item.userEmail;
                    taxation.push(tempItem.entity);
                });

                $scope.taxation = taxation;
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

//Rejected Documents
angular.module("empApp").controller("RejectEmpDocumentController", function ($scope, $http) {

    $scope.RejectedForms = [];

    _getAllApprovedDecDocuments();

    function _getAllApprovedDecDocuments() {
        $http({
            method: 'GET',
            url: 'http://localhost:8080/api/auth/rejectedDocuments/getAllRejectDocuments'
        }).then(
            function (res) { // success
                console.log("res.data", res.data);
                $scope.RejectedForms = res.data;
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
