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
<title> ${book.title} </title>
<link rel="stylesheet" href="/css/showOneBook_page.css">
</head>
<body>
  <div id="mainContainer">
    <div class="row1">
      <div class="column1">
        <h1>${book.title}</h1>

      </div>
      <div class="column2">
        <a href="/logout">logout</a>
        <a href="/profile">back to shelves</a>
      </div>
    </div>
    <div class="row2">
  				<c:if test='${book.user.id == loggedInUserId }'>
   					<h4>You read ${book.title} by ${book.author}</h4>
				</c:if>
  				<c:if test='${book.user.id != loggedInUserId }'>
   					<h4> ${book.user.name} read ${book.title} by ${book.author}</h4>
				</c:if>
    </div>
    <div class="border"></div>
    <div class="row3">
      <p>${book.myThoughts}</p>
    </div>
    <div class="border"></div>
      				<c:if test='${book.user.id == loggedInUserId }'>
					    <button class="btn"><a href="/book/edit/${book.id}">edit</a></button>
						</c:if>


</body>
</html>