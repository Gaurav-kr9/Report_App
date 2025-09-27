<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
 

 <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 
<html>
<head>
 <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Report App</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
 
</head>
<body>

<div class="container">
<h3 class="pd-3 pt-3"> Report Application</h3>



<form:form action="search" modelAttribute="search" method="POST">
<table>
<tr>
    <td> Plan Name:</td>
         <td>  
            <form:select path="planName">
             <form:option value="">-Select-</form:option>
             <form:options items="${names}"/>
              </form:select>
        </td>
        <td> Plan Status:</td>
         <td>  
            <form:select path="planStatus">
             <form:option value="">-Select-</form:option>
             <form:options items="${status}"/>
              </form:select>
        </td>
         <td> Gender: </td> 
         <td>  
            <form:select path="gender">
             <form:option value="">-Select-</form:option>
             <form:option value="Male">Male</form:option>
             <form:option value="Fe-male">Fe-Male</form:option>
              </form:select>
        </td>
</tr>   
        <td>Start Date: </td>
         <td>  
            <form:input path="startDate" type="date" data-date-format="mm/dd/yyyy"/>
        </td>
        <td>End Date: </td>
         <td>  
            <form:input type="date" path="startDate"/>
        </td>

<tr>
   
<td>
   <a href="/" class="btn btn-secondary">Re-set</a>
</td>
<td><input type="submit" value="Search" class="btn btn-primary"/>
</td>
</tr>
 
</table>
</form:form>


<hr/>

<table class="table table-striped table-hover ">
<thead>
<tr>

<th>Id</th>
<th>Holder Name</th>
<th>Gender</th>
<th>Plan Name</th>
<th>Plan Status</th>
<th>Start Date</th>
<th>End Date</th>
</tr>

</thead>
  
  <tbody>
  <c:forEach items="${plans}" var="plan" varStatus="index">
    <tr> 
    <td>${index.count}</td>
     <td>${plan.citizenName}</td>
     <td>${plan.gender}</td>
<td>${plan.planName}</td>
<td>${plan.planStatus}</td>
<td>${plan.planStartDate}</td>
<td>${plan.planEndDate}</td>

  </c:forEach>
  
<tr>
  <c:if test="${empty plans}">
 <td colspan="8" style="text-align: center"> No Records Found</td>
  </c:if>
  </tr>
</tbody>


</table>

<hr/>


Export : <a href= "excel"> Excel</a>
<<<<<<< HEAD
<a href="">pdf</a>
=======
<a href="pdf">pdf</a>
>>>>>>> 9cb9afc (Final roject 0.1)

</div>
   <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js" integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI" crossorigin="anonymous"></script>
 
</body>
</html>