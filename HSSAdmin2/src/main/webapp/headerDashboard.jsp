	<div class="navbar" style="display: flex;flex-direction: row;justify-content:flex-start;flex: 1;font-family:  Inter,sans-serif; align-items: center;"> 

		 			  
	 <div style="font-weight: 700;color: #4844a3;padding: 1.2% 1% 1.2% 2%;font-size: 20px;width: 50%;">HSSC Dashboard</div>
   	 <form method="post" action="applicantsServlet">  
   	 <div style="color: grey; font-size: 13px;width: 30%;   position: absolute;right: 15px;text-align: right;margin-right: 5%;">
   	 <button id="applicantsList" type="submit" style="border: none;background-color: #b8b894 !important;padding: 2%;letter-spacing:0.5px;color:white;font-weight:bold; cursor: pointer;margin-top: -2%;">
   	 Applicants List
   	 </button>
   	 </div>
    </form>  
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

  