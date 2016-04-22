<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
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
${sessionScope.user}
	<c:set var="user" value="${session.user}" />
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
				<li><a href="<%=fbConnection.getFBAuthUrl()%>">Home</a></li>
				<li class="active"><a href="logrun.htm">Log a run</a></li>
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
		<div class="panel-group">
			<div class="row ">
				<div class="col-md-12">
					<div class="panel panel-default">
						<div class="panel-heading">LOG A RUN</div>
						<div class="panel-body">
							<form:form action="logrun.htm" commandName="runlog" method="post"
								class="form">
								<c:if test="${not empty msg}">
								<div class="alert alert-success">${msg}</div>
								</c:if>
								<table>
									<tr>
										<div class="form-group">
											<td>Date</td>
											<td><form:input path="runDate" size="30" type="date"
													class="form-control" required="required" /> <%-- <font
											color="red"><form:errors path="firstName" /></font> --%></td>
											<td>Time</td>
											<td><form:input path="runTime" size="30" type="number"
													class="form-control" required="required" /> <%-- <font
											color="red"><form:errors path="firstName" /></font> --%></td>
										</div>

									</tr>

									<tr>
										<div class="form-group">
										<td>Distance</td>
										<td><form:input type="number" min="1" max="100"
												path="distance" size="30" required="required"
												class="form-control" /> <%--  <font
											color="red"><form:errors path="lastName" /></font> --%></td>
										</div>
									</tr>
									<tr>
										<div class="form-group">
										<td>Felt</td>
										<td><form:input path="feedback" size="30"
												required="required" class="form-control" /> <%-- <font
											color="red"><form:errors path="password" /></font> --%></td>
										</div>
									</tr>

									<tr>
										<div class="form-group">
										<td>Privacy</td>
										<td><form:input path="privacy" size="30"
												required="required" class="form-control" /> <%--  <font
											color="red"><form:errors path="password" /></font> --%></td>
										</div>
									</tr>
									<tr>
										<td colspan="2"><input type="submit" value="Save"
											class="btn btn-info" /></td>
									</tr>
								</table>

							</form:form>

						</div>
					</div>
					<div class="panel panel-default ">
						<div class="panel-heading">MY RUNS</div>
						<div class="panel-body">Panel Content</div>
					</div>
				</div>
			</div>
		</div>
	</div>

</body>
</html>

