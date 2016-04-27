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
	
	<nav class="navbar navbar-inverse">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand" href="adminhome.htm">MyRunLogger</a>
			</div>
			<ul class="nav navbar-nav">
				<li class="active"><a href="adminhome.htm">Home</a></li>
				<li><a href="addtrophy.htm">Trophy</a></li>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<li><a href="adminhome.htm"><img src="resources/images/adminicon.png" height="29"></a></li>
				<li><a href="adminhome.htm">Admin</a></li>
				<li><a href="adminlogout.htm">Logout</a></li>
			</ul>
		</div>
	</nav>

	<div class="container">
		<!-- <h2>Panel Group</h2>
		<p>The panel-group class clears the bottom-margin. Try to remove
			the class and see what happens.</p> -->
			
		<div class="panel-group">
			<div class="row ">
				<div class="col-md-7">
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
				<div class="col-md-5">
					<!-- Accordion -->
					<div class="panel-group" id="accordion">
					
						    <div class="panel panel-danger">
						    <div class="panel-heading">
						    	<h4 class="panel-title">
						          <a data-toggle="collapse" data-parent="#accordion" href="#collapse1">
						    <strong>RUN LEADERS</strong></a></div>
						    <div id="collapse1" class="panel-collapse collapse in">
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
											<td>${leaderRun[0]}</td>
											<td>${leaderRun[1]}</td>
										</tr>

									</c:forEach>
									
								</tbody>
							</table>
						</div>
						</div>
						      <div class="panel-heading">
						        <h4 class="panel-title">
						          <a data-toggle="collapse" data-parent="#accordion" href="#collapse2"> <strong>ACTIVE USERS</strong></a>
						        </h4>
						      </div>
						      <div id="collapse2" class="panel-collapse collapse">
						        <div class="panel-body">
						        	<c:set var="userList" value="${userList}" scope="page" />
							<table class="table">
								<thead>
									<tr>
										<th>User Id</th>
										<th>Name</th>
										<th>Email Id</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="user" items="${userList}">
										<tr>
											<td>${user.personID}</td>
											<td>${user.name}</td>
											<td>${user.email}</td>
										</tr>

									</c:forEach>
									
								</tbody>
							</table>
						        </div>
						      </div>
						    </div>
						   
						    
						  </div> 
					<!-- End -->
				</div>
			</div>
		</div>
		
	</div>

</body>
</html>

