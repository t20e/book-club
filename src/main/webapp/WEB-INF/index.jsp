<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

  <!-- c:out ; c:forEach ; c:if -->
  <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <!-- Formatting (like dates) -->
    <%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
      <!-- form:form -->
      <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
        <!-- for rendering errors on PUT routes -->
        <%@ page isErrorPage="true" %>
          <!DOCTYPE html>
          <html>

          <head>
            <meta charset="UTF-8">
            <title>Insert title here</title>
            <link rel="stylesheet" href="/css/index_page.css">
          </head>

          <body>
            <div id="mainContainer">
                <div class="row1">
                  <h1>Book Club</h1>
                  <p>A place for friends to share thoughts on books</p>
                </div>
                <div class="row2">
                  <div class="column">
                    <h4>Register</h4>
                    <form:form action="/user/reg" method="post" modelAttribute="RegUser">
                      <div>
                        <form:label path="name">Name:</form:label>
                        <form:errors path="name" />
                        <form:input path="name" />
                      </div>
                      <div>
                        <form:label path="email">Email:</form:label>
                        <form:errors path="email" />
                        <form:input  path="email" />
                      </div>
                      <div>
                        <form:label path="password">Password:</form:label>
                        <form:errors path="password" />
                        <form:input type="password" path="password" />
                      </div>
                      <div>
                        <form:label path="confirmPassword">Confirm Password:</form:label>
                        <form:errors path="confirmPassword" />
                        <form:input type="password" path="confirmPassword" />
                      </div>
                      <input type="submit" value="Submit" id="btn"/>
                    </form:form>
                  </div>
                  <div class="column">
                    <h4>Login</h4>
                    <form:form action="/user/login" method="post" modelAttribute="loginUser">
                      <div>
                        <form:label path="email">Email:</form:label>
                        <form:errors path="email" />
                        <form:input path="email" />
                      </div>
                      <div>
                        <form:label path="password">Password:</form:label>
                        <form:errors path="password" />
                        <form:input type="password" path="password" />
                      </div>

                      <input type="submit" value="Submit" id="btn" />
                    </form:form>
                  </div>
                </div>
            </div>
          </body>

          </html>