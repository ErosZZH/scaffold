<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>scaffold管理平台</title>
    <link rel="stylesheet" href="${staticDomain}/css/bootstrap.min.css" />
  </head>
  
  <body ng-app="scaffoldApp" ng-controller="MainCtrl">
    <input type="text" class="form-control" style="width: 260px; display: inline-block; margin-left: 10px;"
                               placeholder="填写商品名" ng-model="productName"
                                typeahead="item for item in getProduct($viewValue)"
                                typeahead-loading="loadingProduct" typeahead-no-results="noResults">
                        <i ng-show="loadingProduct" class="glyphicon glyphicon-refresh"></i>
                        <div ng-show="noResults">
                            <i class="glyphicon glyphicon-remove"></i>没有该商品
                        </div>
        
        <script src="${staticDomain}/js/jquery.min.js"></script>                
    <script src="${staticDomain}/js/bootstrap.min.js"></script>
    <script src="${staticDomain}/js/angular.js"></script>
    <script src="${staticDomain}/js/ui-bootstrap.js"></script>
	<script src="${staticDomain}/js/ui-bootstrap-tpls.js"></script>
	<script type="text/javascript">
		angular
    .module('scaffoldApp', [
        'ui.bootstrap'
    ])
    .config(function ($httpProvider) {
        $httpProvider.defaults.headers.post = {
            "Content-Type" : "application/json;charset=utf-8"
        };
     }).controller('MainCtrl', function ($scope, $http) {
      $scope.productName = "";
     	 //typeAhead
    	$scope.getProduct = function(input) {
        return $http.post('api/v1/product/typeAhead', {'keyword': input})
            .then(function(response) {
                var res = response.data.data;
                return res;
            });
    	};
     });
	</script>
  </body>
</html>
