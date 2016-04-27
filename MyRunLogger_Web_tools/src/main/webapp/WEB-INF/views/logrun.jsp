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
            url : 'logrun.htm',
            method : "POST",
            data : {
                'runDate' : $scope.runDate,
                'runTime' : $scope.runTime,
                'distance': $scope.distance,
                'feedback': $scope.feedback,
                'privacy': $scope.privacy
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
<c:set var="user" value="${session.user}" />
<jsp:scriptlet>FBConnection fbConnection = new FBConnection();</jsp:scriptlet>
	
	<div id="fb-root"></div>
	<script>
		window.fbAsyncInit = function() {
		    FB.init({
		      appId      : '1524813154490050', // App ID
		      status     : true, // check login status
		      cookie     : true, // enable cookies to allow the server to access the session
		      oauth      : true, // enable OAuth 2.0
		      xfbml      : true  // parse XFBML
		    });

		    // Additional initialization code here
		  };

		  // Load the SDK Asynchronously
		  (function(d){
		     var js, id = 'facebook-jssdk'; if (d.getElementById(id)) {return;}
		     js = d.createElement('script'); js.id = id; js.async = true;
		     js.src = "http://connect.facebook.net/en_US/all.js";
		     d.getElementsByTagName('head')[0].appendChild(js);
		   }(document));
		
	</script>
	<nav class="navbar navbar-inverse" >
		<div class="container-fluid">
			<div class="navbar-header" >
				<a class="navbar-brand" href="#">MyRunLogger</a>
			</div>
			<ul class="nav navbar-nav">
				<li ><a href="<%=fbConnection.getFBAuthUrl()%>">Home</a></li>
				<li class="active"><a href="logrun.htm">Log a run</a></li>
				<li><a href="userachievements.htm">My runs</a></li>

			</ul>
			<ul class="nav navbar-nav navbar-right">
				<li><a href="<%=fbConnection.getFBAuthUrl()%>"><img class="img-circle" src="${profile}" height="27"></a></li>
				<li><a href="<%=fbConnection.getFBAuthUrl()%>">${name}</a></li>
				<li><a href="logout.htm" onclick="FB.logout(function(response){});" >Logout</a></li>
			</ul>
		</div>
	</nav>

	<div class="container" ng-controller="myCtrl">
		<div class="panel-group">
			<div class="row ">
				<div class="col-md-12">
					<!-- Add Trophy  -->
					<div class="panel panel-info">
						<div class="panel-heading"><strong>LOG A RUN</strong></div>
						<div class="panel-body">
							<div>
								<form ng-submit="sendPost()" class="form">
									<!-- <div ng-app="myApp" ng-controller="trophyCtrl"> -->
									<div class="row">
									<!-- <div class="alert alert-success">{{ successmessage }}</div> -->
									</div>
									<div class="row">
										<div class="col-md-2"></div>
										<div class="form-group col-md-4">
											<label for="runDate">Date:</label> <input type="date"
												ng-model="runDate" required="required"
												class="form-control"
												 />
											<%-- <font
									
											color="red"><form:errors path="password" /></font> --%>
										</div>
										<div class="form-group col-md-4">
											<label for="runTime">Time:</label> <input
												type="number" ng-model="runTime" size="30"
												required class="form-control"
												min="1" max="1000"
												placeholder="Enter time you run in minutes" />
											
											<%-- <font
											color="red"><form:errors path="password" /></font> --%>
										</div>
										<div class="col-md-2"></div>
									</div>

									<div class="row">
										<div class="col-md-2"></div>
										<div class="form-group col-md-4">
											<label for="distance">Distance:</label> <input
												type="number" ng-model="distance" size="30"
												required="required" class="form-control"
												placeholder="Enter the miles"
												min="1" max="1000" />
											<%-- <font
											color="red"><form:errors path="password" /></font> --%>
										</div>
										<div class="form-group col-md-4">
											<label for="achievement">Felt:</label> <input
												type="text" ng-model="feedback" size="30"
												required="required" class="form-control"
												placeholder="Enter the feedback of the run" />
											<%-- <font
											color="red"><form:errors path="password" /></font> --%>
										</div>
										<div class="col-md-2"></div>
									</div>
									<div class="row">
										<div class="col-md-2"></div>
										<div class="form-group col-md-8">
											<label for="achievement">Privacy:</label> <input
												type="text" ng-model="privacy" size="30"
												required="required" class="form-control"
												placeholder="Enter the privacy of the run" />
											<%-- <font
											color="red"><form:errors path="password" /></font> --%>
										</div>
										<div class="col-md-2"></div>
									</div>
									
									<div class="row">
										<div class="col-md-2"></div>
										<div class="form-group col-md-8">
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
						<div class="panel-heading"><strong>MY RUNS</strong></div>
						<div class="panel-body">
							<!-- <div ng-controller="myCtrl"> -->
							<div class="alert-success alerttrophy" id="result">
                               
                            </div>
							<table class="table table-hover">
								<thead>
									<tr>
										<th>Run Number</th>
										<th>Date</th>
										<th>Distance</th>
										<th>Time</th>
										<th>Felt</th>
									</tr>
								</thead>
								<tbody>
								<c:choose>
								<c:when test="${not empty userRuns}">
										<c:forEach var="userRun" items="${userRuns}">
											<tr>
												<td class="runLogId">${userRun.id}</td>
												<td>${userRun.runDate}</td>
												<td><input name="distanceTxt" type="text" class="readonlyStyle" readonly
													value="${userRun.distance}" /></td>
												<td><input name="runTimeTxt" type="text" class="readonlyStyle" readonly
													value="${userRun.runTime}" /></td>
												<td><input name="feedbackTxt" type="text" class="readonlyStyle" readonly
													value="${userRun.feedback}" /></td>
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
										<td class="runLogId">{{ x.runLogId }}</td>
										<td>{{ x.runResultDate }}</td>
										<td><input name="distanceTxt" type="text" class="readonlyStyle" readonly
													value="{{ x.runResultDistance }}" /></td>
										<td><input name="runTimeTxt" type="text" class="readonlyStyle" readonly
													value="{{ x.runResultTime }}" /></td>
										<td><input name="feedbackTxt" type="text" class="readonlyStyle" readonly
													value="{{ x.runResultFeedback }}" /></td>
										<!-- <td><input name="runResultPrivacyTxt" type="text" class="readonlyStyle" readonly
													value="{{ x.runResultPrivacy }}" /></td> -->
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
			var runLogId = $row.children('td.runLogId').text();
            var $tds = $row.find("input:text");    
			if($(this).text() == "Update"){
				dsbutton.text("Save");
				$row.css("background-color", "none");
				 $.each($tds, function () {             
	                 $(this).removeAttr("readonly");
	                 $(this).removeClass("readonlyStyle");
	             });
			}else{
					 var query = 'updaterun.htm?runLogId='+runLogId + '&';
					 
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
	                        	
	                        	alert(result);
	                        	$(".alerttrophy").show();
                        		if(result === 'Error'){
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
	           			     alert("Update Run failed");
	           			  	 $("#result").empty().append("Update Run failed!!!");
	           			  },
	                        async: true
	                    });
				}
		});
		/**Delete Btn **/
		$(document).on("click", ".btn-danger", function() {
			var dsbutton = $(this);
			var $row = dsbutton.closest("tr");
			var runLogId = $row.children('td.runLogId').text();
			var parentTr =$(this).parent().parent();
			jQuery.ajax({
				  type: "POST",
				  dataType: "html",
				  url: "deleterun.htm",
				  data: "runLogId="+runLogId,
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
