<%@page import="edu.neu.myapp.fbsocial.FBConnection"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>MyRunLogger</title>
<meta name="description" content="">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="resources/css/style_.css">
<link rel="stylesheet" href="resources/css/style.css">
</head>
<body>
	<jsp:scriptlet>FBConnection fbConnection = new FBConnection();</jsp:scriptlet>
	<div class="fullscreen-bg">
		<video loop muted autoplay poster="img/videoframe.jpg"
			class="fullscreen-bg__video">
			<source src="resources/videos/running_bg.mp4" type="video/mp4">

		</video>
	</div>

	<!--<div class="content">-->
	<div class="login">
		<h1>
			<center><span style="font-family:'Open Sans', Arial; font-weight:bold; font-size:40px">
                    <font color="#4285F4">My</font><font color="#FCBD05">Run</font><font color="#EB584C">Logger</font></span></center>
			</h1>
		<form method='GET' action=''>
			<%--
	<input type="text" name="user" placeholder="User Name" value="Username" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = 'User Name';}" pattern="^[a-zA-Z][a-zA-Z0-9-_\.]{3,20}$" required="required"/>
	<input type="password" name="password" value="Password" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = 'User Name';}" required/>
	<div id ="msg" > </div>
	<div class="forgot">
   	  <label class="checkbox"><input type="checkbox" name="checkbox" checked><i> </i> Keep me signed in</label>
   	  <div class="forgot-para">
   	  	<a href="#"> <p>Forgot Your Password?</p></a>
   	  </div>
	   	<div class="clear"> </div>
	</div>
	 --%>

			<div style="margin-top: 10%; margin-bottom: 10%">
				<label > <!-- <input type="submit"
					class="uibutton large" value="Connect with facebook" /> -->
					<a href="<%=fbConnection.getFBAuthUrl()%>"><img src="resources/images/connect-with-facebook.png"
                alt="Log In With Facebook" title="Log In With Facebook"/></a>
				</label>
			</div>
		</form>



		<!-- <h3>Not a member ? <a href="#">Sign up now!</a>  </h3> -->
	</div>
	<!--</div>-->

</body>
</html>