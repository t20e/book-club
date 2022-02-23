<%@page import="javax.swing.text.View"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
   <!-- c:out ; c:forEach ; c:if -->
 <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
   <!-- Formatting (like dates) -->
 <%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
   <!-- form:form -->
 <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>  
   <!-- for rendering errors on PUT routes -->
 <%@ page isErrorPage="true" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add Book</title>
<link rel="stylesheet" href="/css/addBook_page.css">
</head>
<body>
  <div id="mainContainer">
    <div class="row1">
      <div class="col1">
        <h1>Add a book to your shelf! </h1>
      </div>
      <div class="col2">
        <a href="/logout">logout</a>
        <a href="/profile">back to shelves</a>
      </div>
    </div>
    <div class="row2">
                    <form:form action="/book/adding" method="post" modelAttribute="addBookObj">
					<div>
                        <form:errors path="user" />
                        <form:input type="hidden" path="user" value=" ${sessionId} "/>
                      </div>
                    <div>
                        <form:label path="title">Title:</form:label>
                        <form:errors path="title" />
                        <form:input path="title" />
                      </div>
                      <div>
                        <form:label path="author">Author:</form:label>
                        <form:errors path="author" />
                        <form:input  path="author" />
                      </div>
                      <div>
                        <form:label path="myThoughts">My Thoughts:</form:label>
                        <form:errors path="myThoughts" />
                        <form:input  path="myThoughts" />
                      </div>
                      <input type="submit" value="Submit" id="btn"/>
                    </form:form>
    </div>


  </div>
</body>
</html>