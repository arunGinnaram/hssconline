<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<% int totalPages = (Integer)request.getAttribute("totalPages"); %>


<% int currentPage = (Integer)request.getAttribute("currentPage"); %>

<%@ taglib prefix="pag" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Applicants</title>
<!-- <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script> -->
<script src="/jquery/jquery.js"></script>

</head>

<style>
.pagination {
  display: flex;
  justify-content: right;
  align-items: center;
  list-style: none;
  margin: 0;
  padding: 0;
}

.pagination li {
  margin: 0;
  padding: 0;
}

.pagination button {
  border: none;
  
  color: black;
  font-size: 16px;
  margin: 0 5px;
  padding: 8px 12px;
  border-radius: 4px;
  cursor: pointer;
  transition: all 0.3s ease;
  background-color: rgb(18 110 96);
  color: white;
 
}

.pagination button:hover {
  background-color: #f2f2f2;
}

.pagination button:disabled {
  opacity: 0.5;
  cursor: not-allowed;
}
</style>



<style> 
.mainProjectlist  {        
    font-family: sans-serif;
 }

 #mainProjectlist_heading  {   
    font-weight: bold; 
   background-color:  rgb(18 110 96);
 } 

/*  #mainProjectlist_heading:hover  {      */
/*  background-color: brown; */
/* }  */
 

 table {
  width: 100%;
  font-family: Mukta;
  color: rgb(102, 100, 100);  
   border-collapse: collapse;
   box-shadow: 0 5px 10px #e1e5ee ;
   /* background-color: var(--background-color); */
   /* background-color: white; */
   text-align: left;
   overflow: hidden; 
   border: 0.1px solid rgb(215, 210, 210);
   letter-spacing: 0.3px;
  }
 
   thead {
     /* box-shadow: 0 5px 10px #e1e5ee ; */
     background-color: var(--background-color);
   }
 
   th {
    padding: 1% 1% 1% 0.5%;
     text-transform: capitalize;
     /* letter-spacing: 0.2px; */
     /* font-size: 0.7rem; */
     font-weight: 600;
     border: 0.1px solid rgb(215, 210, 210);
    }
 
   td {
    padding: 1% 1% 1% 0.5%;
    color: rgb(129, 127, 127); 
   border: 0.1px solid rgb(215, 210, 210);
   } 
   
   /* //Color alternating rows */
   tr:nth-child(even) {
     /* background-color: #f4f6fb; */
   }

   
 
#back:hover {
opacity:0.8;
}
 
 
</style>

<script >

// function searchById() {
	
// 	var tid = $('#transactionId').val();
	
// 	$.get('applicantsServlet', {tid: tid}, function(response) {
// 	    // Handle the response
// 	    console.log('Response:', response);
// 	  });
	 
// } 

function searchById() {
	  // Get the value of the input field
	  var tid = $('#transactionId').val();

	  // Set up the AJAX request
	  $.ajax({
	    url: 'applicantsSearchServlet',
	    type: 'POST',
	    data: { tid: tid },
	    success: function(response) {
	      // Handle the response
	      console.log('Response:', response);
	    },
	    error: function(xhr, status, error) {
	      // Handle errors
	      console.log('Error:', error);
	    }
	  });
	}




</script>

<script>
  // Hide the error message after 3 seconds
  setTimeout(function() {
    var errorMessage = document.getElementById('errorMessage');
    if (errorMessage) {
      errorMessage.style.display = 'none';
    }
  }, 3000);
</script>

<style>
button:hover {
cursor:pointer;
opacity:0.4;
color: grey;
/* background-color: rgb(18 110 96) !important; */
}
</style>








<body style="background-color: lightgrey;"> 
<jsp:include page="applicantsheader.jsp" flush="true" />
<div class='mainProjectlist' style="width:99%;"  >      
      
      <div  id="mainProjectlist_heading" style="margin-top: 1%;height:8vh; display:flex;align-items: center;justify-content: flex-start ;   ">         
        <div  style="padding: 1% 2.8% 1% 1.3%;color:white;letter-spacing:1px;width: 47%;">Applicants List</div>
      	
      	
<!-- 	    onclick="searchById()" -->
	    <div  style="display: flex;flex-direction: row;justify-content: flex-end;width: 57%;align-items: center;  ">
	    
	    <form  action="applicantsSearchServlet" style="margin-right: 4%;  "> 
	    <input required name="tid"  type="text" id="transactionId"  placeholder="Transaction id" style="padding:1.2vh; border:none;border-radius:3px; " />
	    <button id="search"  type="submit" style="padding: 1vh 2vh ;color:white; background-color: #b8b894 !important;border:none;border-radius:2px; ">Search</button>
	    </form> 
	     
	    <form id="statusSearch" action="applicantsStatusSearchServlet" style="margin-right: 4%;  " >
	    <select required name="status" style="padding:1.2vh;width:170px;  border:none;border-radius:3px;color: grey;   " >
	    <option selected="selected" value="">Select</option>
	    <option value="NEW">New</option>
    	<option value="UPLOADED">Uploaded</option>
    	<option value="FINISHED">Finished</option>
    	<option value="COMPLETED">Completed</option>
	    </select>
	    <button type="submit" style="padding: 1vh 2vh ; color:white;background-color: #b8b894 !important;border:none;border-radius:2px; " >Select</button>
	    </form>
	    
	    
      
      	<form method="post" action="index.jsp" style="margin-right: 4%;  ">  
	   	  <button 
	   	  id="back" 
	   	  
	   	  type="submit" 
	   	  style="border: none;
	   	  background-color: #b8b894 !important;
	   	  width:130%;
	   	  padding:1vh 1.2vh; 
	   	  letter-spacing:0.5px;
	   	  color:white;
	   	   
	   	   cursor: pointer; 
	   	   border: none;
	   	   border-radius:2px; 
	   	 
	   	   ">
	   	 Back
	   	 </button>
	   	 
	    </form> 
	    </div>
	    
      
      </div>
      <%-- Check if message is present and display it --%>
  <c:if test="${not empty message}">
    <div id="errorMessage" class="alert alert-danger" style="padding: 1%;background-color: #F08A76;color: white;letter-spacing: 0.5px;font-weight: bold;">${message}</div>
  </c:if>
      <table id="ProjectList"  style="background-color: white;" >
        <thead>
          <tr>
             
            
            <th style="width: 10%;">Transaction ID</th>
    <th style="width: 20%;">Full Name</th> 
    <th style="width: 25%;">Email</th>
    <th style="width: 7%;">Gender</th>
    <th style="width: 10%;">DOB</th>
    <th style="width: 15%;">Aaadhar</th>
    <th style="width: 22%;">Application Status</th>
        </tr>

        </thead>

        <tbody>
           <c:forEach var="applicants" items="${applicants}">
          <tr>
            <td>${applicants.tid}</td>
      <td>${applicants.fullName}</td> 
      <td>${applicants.email}</td>
      <td style="text-align: center;" >${applicants.gender}</td>
      <td>${applicants.dob}</td>
      <td>${applicants.aadhar}</td>
      <td>${applicants.applicationStatus}</td>
          </tr>
           </c:forEach>
        </tbody>


      </table>
      
<%-- end="${totalPages} --%>
<!-- <ul class="pagination"> -->
<%--     <pag:forEach var="page" begin="1" end="5"> --%>
<%--         <li class="${page == currentPage ? 'active' : ''}"> --%>
<!--             <form action="/HSSAdmin/applicantsServlet" method="POST"> -->
<%--                 <input type="hidden" name="page" value="${page}"> --%>
<%--                 <button type="submit">${page}</button> --%>
<!--             </form> -->
<!--         </li> -->
<%--     </pag:forEach> --%>
<!-- </ul> -->



<ul class="pagination" style=" margin-top: 1vh;">
    <li>
        <form action="/HSSAdmin/applicantsServlet" method="POST">
            <input type="hidden" name="page" value="1">
            <button type="submit">First</button>
        </form>
    </li>
    <li>
        <form action="/HSSAdmin/applicantsServlet" method="POST">
            <input type="hidden" name="page" value="${currentPage - 1}">
            <button type="submit" ${currentPage == 1 ? 'disabled' : ''}>Previous</button>
        </form>
    </li>
    <li>
        <form action="/HSSAdmin/applicantsServlet" method="POST">
            <input type="hidden" name="page" value="${currentPage + 1}">
            <button type="submit" ${currentPage == totalPages ? 'disabled' : ''}>Next</button>
        </form>
    </li>
    <li>
        <form action="/HSSAdmin/applicantsServlet" method="POST">
            <input type="hidden" name="page" value="${totalPages}">
            <button type="submit">Last</button>
        </form>
    </li>
</ul>




      
    </div>
     
    
     
</body>
</html>
