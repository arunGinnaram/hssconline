	<div class="navbar" style="display: flex;flex-direction: row;justify-content:flex-start;flex: 1;font-family:  Inter,sans-serif; align-items: center;"> 

		 			  
	 <div style="font-weight: 700;color: #4844a3;padding: 1.2% 1% 1.2% 2%;font-size: 20px;width: 50%;">HSSC Dashboard</div>
		<div style="font-weight: 700;color: #4844a3;padding: 1.2% 1% 1.2% 2%;font-size: 20px;width: 50%; ">
		
		
		 <form method="post" action="index.jsp" style="margin-right: 4%;  ">  
	   	  <button 
	   	  id="back" 
	   	  
	   	  type="submit" 
	   	  style="border: none;
	   	  background-color:#E7A582 !important;
	   	   
	   	  padding:1vh 3vh; 
	   	  letter-spacing:0.5px;
	   	  color:white;
	   	   
	   	   cursor: pointer; 
	   	   border: none;
	   	   border-radius:2px; 
	   	   margin-left: 80%;
	   	 
	   	   ">
	   	 Back
	   	 </button>
	   	 
	    </form> 
		</div>



<!--    	 <form method="post" action="index.jsp" style="  ">  -->
<!-- <!--    	 <div style="color: grey; font-size: 13px;width: 30%;   position: absolute;right: 15px;text-align: right;margin-right: 5%;"> -->  
<!--    	 <button id="applicantsList" type="submit" style="border: none;position: absolute;right: 15px;background-color: #b8b894 !important;padding: 0.5%;letter-spacing:0.5px;color:white;font-weight:bold; cursor: pointer;margin-top: -10px;"> -->
<!--    	  Back -->
<!--    	 </button> -->
<!-- <!--    	 </div> --> 
<!--     </form>   -->
		</div> 
	 
	 
	 <style type="text/css">
      .navbar { 
		   background-color:White; 
      } 
      
/*       #applicantsList:hover{ */
/*       background-color: #7878b3 !important; */
/*       } */
    </style>
    
    <script type="text/javascript">
     function ApplicantsList() {
    	 $.get("applicantsServlet",function(){
    		 console.log("Hello Welcome");
    	 });
    	 
     }
    
     
	</script>
	
	<!-- 				<form method="post" action="applicantsServlet"> -->
<!-- 					<button type="submit" style="border: none;backgroung-color:white;">Click me</button> -->
<!-- 				</form>  -->

  