<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@page import="edu.neu.myapp.fbsocial.FBConnection"%>
<%@ page session="false"%>
<!DOCTYPE html>
<html lang="en" ng-app="myApp">
<head>
<title>RunLogger</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<script
	src="http://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular.min.js"></script>

<script>	
var helloAjaxApp = angular.module("myApp", []);

helloAjaxApp.controller("myCtrl", [ '$scope', '$http', function($scope, $http) {
 
    $http.defaults.headers.post["Content-Type"] = "application/x-www-form-urlencoded; charset=utf-8";
     
    $scope.sendPost = function() {
        $http({
            url : 'addtrophy.htm',
            method : "POST",
            data : {
                'trophyname' : $scope.trophyname,
                'trophyDesc' : $scope.trophyDesc,
                'achievement': $scope.achievement
            }
        }).then(function(response) {
            console.log(response.data);
            $scope.message = response.data.results;
            $scope.successmessage = response.data.success;
           	alert($scope.successmessage);
        }, function(response) {
            //fail case
            console.log(response);
            $scope.message = response;
        });
 
    };
} ]);
</script>
<style type="text/css">
.readonlyStyle {
	background: none;
	border: none;
	outline: none;
	
}
</style>
</head>
<body>

	<nav class="navbar navbar-inverse">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand" href="adminhome.htm">MyRunLogger</a>
			</div>
			<ul class="nav navbar-nav">
				<li ><a href="adminhome.htm">Home</a></li>
				<li class="active"><a href="addtrophy.htm">Trophy</a></li>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<li><a href="adminhome.htm"><img src="resources/images/adminicon.png" height="29"></a></li>
				<li><a href="adminhome.htm">Admin</a></li>
				<li><a href="adminlogout.htm">Logout</a></li>
			</ul>
		</div>
	</nav>

	<div class="container" ng-controller="myCtrl">
		<!-- <h2>Panel Group</h2>
		<p>The panel-group class clears the bottom-margin. Try to remove
			the class and see what happens.</p> -->

		<div class="panel-group">
			<div class="row ">
				<div class="col-md-10">
					<!-- Add Trophy  -->
					<div class="panel panel-info">
						<div class="panel-heading"><strong>ADD TROPHY</strong></div>
						<div class="panel-body">
							<div>
								<form ng-submit="sendPost()" class="form">
									<!-- <div ng-app="myApp" ng-controller="trophyCtrl"> -->
									<div class="row">
									<!-- <div class="alert alert-success">{{ successmessage }}</div> -->
									</div>
									<div class="row">
										<div class="col-md-2"></div>
										<div class="form-group col-md-8">
											<label for="trophyName">Trophy:</label> <input type="text"
												ng-model="trophyname" size="30" required="required"
												class="form-control"
												placeholder="Enter the name of the Trophy" />
											<%-- <font
									
											color="red"><form:errors path="password" /></font> --%>
										</div>
										<div class="col-md-2"></div>
									</div>

									<div class="row">
										<div class="col-md-2"></div>
										<div class="form-group col-md-8">
											<label for="trophyDesc">Desciption:</label> <input
												type="text" ng-model="trophyDesc" size="30"
												required="required" class="form-control"
												placeholder="Enter the description of the Trophy" />
											
											<%-- <font
											color="red"><form:errors path="password" /></font> --%>
										</div>
										<div class="col-md-2"></div>
									</div>

									<div class="row">
										<div class="col-md-2"></div>
										<div class="form-group col-md-8">
											<label for="achievement">Criteria:</label> <input
												type="number" ng-model="achievement" size="30"
												required="required" class="form-control"
												placeholder="Enter the miles" />
											<%-- <font
											color="red"><form:errors path="password" /></font> --%>
										</div>
										<div class="col-md-2"></div>
									</div>

									<div class="row">
										<div class="col-md-2"></div>
										<div class="form-group col-md-8">
											<!--  <input type="button" value="Save" id="saveBtn" ng-click="saveTrophy()"	class="btn btn-info" />
								 -->
											<button type="submit" class="btn btn-primary">Add</button>
										</div>
										<div class="col-md-2"></div>
									</div>

									<!-- </div> -->
								</form>
							</div>
						</div>
					</div>
					<!-- End : Add Trophy -->
					<!-- TROPHY LIST -->
					<div class="panel panel-info">
						<div class="panel-heading"><strong>TROPHY IN THE STORE</strong></div>
						<div class="panel-body">
							<!-- <div ng-controller="myCtrl"> -->
							<div class="alert-success alerttrophy" id="result">
                               
                            </div>
							<table class="table table-hover">
								<thead>

									<tr>
										<th>Trophy Id</th>
										<th>Trophy Name</th>
										<th>Trophy Description</th>
										<th>Achievement Criteria</th>
									</tr>
								</thead>
								<tbody>
								<c:choose>
								<c:when test="${not empty trophyList}">
										<c:forEach var="trophy" items="${trophyList}">
											<tr>
												<td class="trophyId">${trophy.trophyId}</td>
												<td><input name="trophyNameTxt" type="text" class="readonlyStyle" readonly
													value="${trophy.trophyName}" /></td>
												<td><input name="trophyDescTxt" type="text" class="readonlyStyle" readonly
													value="${trophy.trophyDesc}" /></td>
												<td><input name="achievementTxt" type="text" class="readonlyStyle" readonly
													value="${trophy.achievement}" /></td>
												<td><button type="button" class="btn btn-info btn-xs"
														onClick="">Update</button></td>
												<td><button type="button" class="btn btn-danger btn-xs"
												onClick="">Delete</button></td>
											</tr>
										</c:forEach>
									</c:when>
									<c:otherwise></c:otherwise>
									</c:choose>

									<tr ng-repeat="x in message">
										<td class="trophyId">{{ x.trophyId }}</td>
										<td><input name="trophyNameTxt" type="text" class="readonlyStyle" readonly
													value="{{ x.trophyResultName }}" /></td>
										<td><input name="trophyDescTxt" type="text" class="readonlyStyle" readonly
													value="{{ x.trophyResultDesc }}" /></td>
										<td><input name="achievementTxt" type="text" class="readonlyStyle" readonly
													value="{{ x.trophyResultCriteria }}" /></td>
										<td><button type="button" class="btn btn-info btn-xs">Update</button></td>
										<td><button type="button" class="btn btn-danger btn-xs">Delete</button></td>
									</tr>
							</table>
							<!-- </div> -->
						</div>
					</div>
					<!-- END : TROPHY LIST -->
				</div>

			</div>

		</div>
	</div>
	<!--AJAX CALL FOR UPDATION  -->
	<script>

	$(document).ready(function() {
		$(".alerttrophy").hide();
		
		/** Update **/
		$(document).on("click", ".btn-info", function() {
			var dsbutton = $(this);
			var $row = dsbutton.closest("tr");
			var trophyId = $row.children('td.trophyId').text();
            var $tds = $row.find("input:text");    
			if($(this).text() == "Update"){
				dsbutton.text("Save");
				$row.css("background-color", "none");
				 $.each($tds, function () {             
	                 $(this).removeAttr("readonly");
	                 $(this).removeClass("readonlyStyle");
	             });
			}else{
					 var query = 'updatetrophy.htm?trophyId='+trophyId + '&';
					 
					 $.each($tds, function () {  
						 $(this).attr("readonly");
		                 $(this).addClass("readonlyStyle");
		                 var name = $(this).attr("name");
		                 var val = $(this).val();
		                 if (name !== null || val !== undefined) {
		                     query = query + name + "=" + val + "&";
		                 }
					 });
					 jQuery.ajax({
	                        url: query,
	                        success: function (result) {
	                        	
	                        	if(result === 'Error'){
	                        		alert("Some error occurred");
                        			$("#result").empty().append("Some error occurred");
                        			$.each($tds, function () {             
                   	                 $(this).removeAttr("readonly");
                   	                 $(this).removeClass("readonlyStyle");
                   	             });
                        		}else{
	                        		dsbutton.text("Update");
	                        		$(".alerttrophy").show();
		                        	$("#result").empty().append(result);
		                        	$row.css("background-color", "#B3FF80");
	                        	}
	                        },
	                        error: function(XMLHttpRequest, textStatus, errorThrown) {
	           			     alert("Update Trophy failed");
	           			  	 $("#result").empty().append("Update Trophy failed!!!");
	           			  },
	                        async: true
	                    });
				}
		});
		/**Delete Btn **/
		$(document).on("click", ".btn-danger", function() {
			var dsbutton = $(this);
			var $row = dsbutton.closest("tr");
			var trophyId = $row.children('td.trophyId').text();
			var parentTr =$(this).parent().parent();
			jQuery.ajax({
				  type: "POST",
				  dataType: "html",
				  url: "deletetrophy.htm",
				  data: "trophyId="+trophyId,
				  success: function(msg){
						//if(msg === "success"){
							//var row = document.getElementById(trophyId);
	                        //row.parentNode.removeChild(row);
	                        
                            parentTr.remove();
	                        $(".alerttrophy").show();
	                        $("#result").empty().append(msg);
						//}else{
							// alert("Deleting doctor failed");
							//}
				  },
				  error: function(XMLHttpRequest, textStatus, errorThrown) {
				     alert("error deleting trophy");
				  }
				});
		});
		
	});
	</script>
	<!-- END -->
</body>
</html>

