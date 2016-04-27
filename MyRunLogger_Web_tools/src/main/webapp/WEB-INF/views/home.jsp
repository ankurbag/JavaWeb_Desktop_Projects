<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="edu.neu.myapp.fbsocial.FBConnection"%>
<%@ page session="false"%>
<%
    response.setHeader("Cache-Control", "no-cache"); //HTTP 1.1
    response.setHeader("Pragma", "no-cache"); //HTTP 1.0
    response.setDateHeader("Expires", 0); //prevents caching at the proxy server
%>
<!DOCTYPE html>
<html lang="en">
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
</head>
<body>
	<c:set var="userSession" value="${sessionScope.user}" />
	<%-- <c:choose>
	<c:when test="${not empty userSession}"> --%>
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
				<li class="active"><a href="<%=fbConnection.getFBAuthUrl()%>">Home</a></li>
				<li><a href="logrun.htm">Log a run</a></li>
				<li><a href="userachievements.htm">My runs</a></li>

			</ul>
			<ul class="nav navbar-nav navbar-right">
				<li><a href="<%=fbConnection.getFBAuthUrl()%>"><img class="img-circle" src="${profile}" height="27"></a></li>
				<li><a href="<%=fbConnection.getFBAuthUrl()%>">${name}</a></li>
				<li><a href="logout.htm" onclick="FB.logout(function(response){});" >Logout</a></li>
			</ul>
		</div>
	</nav>

	<div class="container">
		<!-- <h2>Panel Group</h2>
		<p>The panel-group class clears the bottom-margin. Try to remove
			the class and see what happens.</p> -->
			${user}
		<div class="panel-group">
			<div class="row ">
				<div class="col-md-8">
					<div class="panel panel-info">
						<div class="panel-heading"><strong>RECENT RUNS</strong></div>
						<div class="panel-body">
							<c:set var="userRuns" value="${userRuns}" scope="page" />
							<table class="table">
								<thead>
									<tr>
										<th>Runner</th>
										<th>Date</th>
										<th>Distance(miles)</th>
										<th>Time(minutes)</th>
										<th>Pace(mi/hr)</th>
										<th>Felt</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="userRun" items="${userRuns}">
										<tr>
											<td>${userRun.user.name}</td>
											<td>${userRun.runDate}</td>
											<td>${userRun.distance}</td>
											<td>${userRun.runTime}</td>
											<td>${userRun.distance / userRun.runTime}</td>
											<td>${userRun.feedback}</td>
										</tr>

									</c:forEach>
									
								</tbody>
							</table>
						</div>
					</div>
				</div>
				<div class="col-md-4">
					<div class="panel panel-info ">
						<div class="panel-heading"><strong>RUN LEADERS</strong></div>
						<div class="panel-body">
						<c:set var="leadersRuns" value="${leadersRuns}" scope="page" />
							<table class="table">
								<thead>
									<tr>
										<th>Runner</th>
										<th>Distance</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="leaderRun" items="${leadersRuns}">
										<tr>
											<td>${leaderRun[1]}</td>
											<td>${leaderRun[0]}</td>
										</tr>

									</c:forEach>
									
								</tbody>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<%-- </c:when>
	<c:otherwise>
		<jsp:scriptlet>response.sendRedirect("http://localhost:8080/myapp/");</jsp:scriptlet>
	</c:otherwise>
	</c:choose> --%>
</body>
</html>

