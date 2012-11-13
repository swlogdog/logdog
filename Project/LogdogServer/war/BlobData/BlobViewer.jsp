<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.google.appengine.api.blobstore.BlobKey" %>
<%@ page import="com.google.appengine.api.blobstore.BlobstoreService" %>
<%@ page import="com.google.appengine.api.blobstore.BlobstoreServiceFactory" %>

<html>
  <head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <title>LogDog</title>
  </head>

  <body>
   <%
   
   	BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();
   BlobKey blobKey = new BlobKey(request.getParameter("blob-key"));
   blobstoreService.serve(blobKey, response);
   %>
   
  </body>
</html>
