<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.google.appengine.api.users.UserService" %>
<%@ page import="com.google.appengine.api.users.UserServiceFactory" %>

<html>
  <head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <title>Hello App Engine</title>
  </head>

  <body>
   <%
     UserService userService = UserServiceFactory.getUserService();
     if (!userService.isUserLoggedIn()) {
  
	   response.sendRedirect(userService.createLoginURL("/Setting/UserSetting/LogIn"));
    } else {
		
		response.sendRedirect("/Setting/UserSetting/LogIn");
     }
   %>
   
  </body>
</html>
