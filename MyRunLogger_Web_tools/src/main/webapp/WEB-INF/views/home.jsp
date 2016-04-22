<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="edu.neu.myapp.fbsocial.FBConnection"%>
<%@ page session="false"%>
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
	<jsp:scriptlet>FBConnection fbConnection = new FBConnection();</jsp:scriptlet>
	<script>
		window.fbAsyncInit = function() {
			FB.init({
				appId : '1524813154490050',
				xfbml : true,
				version : 'v2.5'
			});
		};

		(function(d, s, id) {
			var js, fjs = d.getElementsByTagName(s)[0];
			if (d.getElementById(id)) {
				return;
			}
			js = d.createElement(s);
			js.id = id;
			js.src = "//connect.facebook.net/en_US/sdk.js";
			fjs.parentNode.insertBefore(js, fjs);
		}(document, 'script', 'facebook-jssdk'));

		function logout() {
			alert("HI" + FB.getAccessToken());
			FB.logout(function(response) {
				// user is now logged out
			});
		}
	</script>
	<nav class="navbar navbar-inverse">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand" href="#">MyRunLogger</a>
			</div>
			<ul class="nav navbar-nav">
				<li class="active"><a href="<%=fbConnection.getFBAuthUrl()%>">Home</a></li>
				<li><a href="logrun.htm">Log a run</a></li>
				<li><a>My runs</a></li>

			</ul>
			<ul class="nav navbar-nav navbar-right">
				<li><a href="#"><img src="${profile}" height="50"></a></li>
				<li><a href="#">${name}</a></li>
				<li><a onclick="logout()">Logout</a></li>
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
					<div class="panel panel-default">
						<div class="panel-heading">RECENT RUNS</div>
						<div class="panel-body">
							<c:set var="userRuns" value="${userRuns}" scope="page" />
							<table class="table">
								<thead>
									<tr>
										<th>Runner</th>
										<th>Date</th>
										<th>Distance</th>
										<th>Time</th>
										<th>Pace</th>
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
											<td>NA</td>
											<td>NA</td>
										</tr>

									</c:forEach>
									
								</tbody>
							</table>
						</div>
					</div>
				</div>
				<div class="col-md-4">
					<div class="panel panel-default ">
						<div class="panel-heading">RUN LEADERS</div>
						<div class="panel-body">Panel Content</div>
					</div>
				</div>
			</div>
		</div>
	</div>

</body>
</html>

