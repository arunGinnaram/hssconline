<%-- <%@ page import="java.util.Date"%>  --%>
<%-- <%@ page import="java.text.SimpleDateFormat"%>  --%>
<%-- <%@ page import="com.ttil.bean.ApplicationFormBean"%> --%>
<!DOCTYPE html5>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <%@ page pageEncoding="utf-8"%>
    <title>HSSC Online</title> 
    
<!--      chart script -->
<!--   <script src="https://cdn.jsdelivr.net/npm/chart.js@3.8.0"></script>  -->
<!--    <script src="http://code.jquery.com/jquery-latest.min.js"></script>  -->
<!--     <script src="https://cdn.jsdelivr.net/npm/chartjs-plugin-datalabels"></script>  -->

<script src="jquery.js"></script>
<script src="chart.min.js"></script>
    
  
   
   
   
   <script>
  function createChart() {  
	  
    var ctx = document.getElementById('myChart').getContext('2d');
     
    var myChart = new Chart(ctx, {
      
      data: {
        labels: ['<9am', '9am-11am', '11am-1pm', '1pm-3pm' , '3pm-5pm' , '5pm-7pm' , '7pm-9pm' ],
        datasets: [          
          
        	{
        		  type: 'bar',
        		  label: 'Date00',
        		  backgroundColor: ['#f0e0a4'],
        		  data: [],
        		},
        		{
        		  type: 'bar',
        		  label: 'Date00',
        		  backgroundColor: ['#a5a5a5'],
        		  data: [],
        		},
        		{
        		  type: 'bar',
        		  label: 'Date00',
        		  backgroundColor: ['#b94a48'],
        		  data: [],
        		},
        		{
        		  type: 'bar',
        		  label: 'Date00',
        		  backgroundColor: ['#4d7299'],
        		  data: [],
        		},
        		{
        		  type: 'bar',
        		  label: 'Date00',
        		  backgroundColor: ['#dca5e5'],
        		  data: [],
        		}

          , 
 
        ], 
        
        

      }, 
    });
    
    for (var i = 0; i < 5; i++) {
		  var date = new Date();
		  date.setDate(date.getDate() - i);
		  var formattedDate = date.toLocaleDateString('en-US', { day: 'numeric', month: 'short' }).replace(' ', '');
		  myChart.data.datasets[i].label =formattedDate;
		}
    
    
    
//     var dateSel =  document.getElementById('dateSelected').value;

    $.get("someservletc1", function(responseText) { // Execute Ajax GET request on URL of "someservlet" and execute the following function with Ajax response text...
       
    	var data = JSON.parse(responseText);
    console.log("Data: " ,data); 

myChart.data.datasets[0].data = 
	[  
		 
		 data[0].fcountD1B9,   
		 
		 data[0].fcountD1911,  
		 data[0].fcountD11113,  
		 data[0].fcountD11315,  
		 data[0].fcountD11517,
		 data[0].fcountD11719,  
		 data[0].fcountD11921, 
		 ];  
		 
		 

myChart.data.datasets[1].data = 
	[ data[0].fcountD2B9,data[0].fcountD2911, data[0].fcountD21113,  data[0].fcountD21315,  data[0].fcountD21517, data[0].fcountD21719,  data[0].fcountD21921,

		 ];  
		 
myChart.data.datasets[2].data = 
	[   data[0].fcountD3B9,data[0].fcountD3911,  data[0].fcountD31113,  data[0].fcountD31315, data[0].fcountD31517, data[0].fcountD31719,data[0].fcountD31921, 

		 ]; 
		 
myChart.data.datasets[3].data = 
	[  data[0].fcountD4B9,   data[0].fcountD4911, data[0].fcountD41113, data[0].fcountD41315,data[0].fcountD41517, data[0].fcountD41719,data[0].fcountD41921,
		 ]; 
		 
myChart.data.datasets[4].data = 
	[  data[0].fcountD5B9,data[0].fcountD5911,data[0].fcountD51113,data[0].fcountD51315,data[0].fcountD51517,data[0].fcountD51719,data[0].fcountD51921 

		 ];  
 
      // Update the chart
      myChart.update();
 
    }); 
  } 

function createSecondChart() {  
	  
    var ctx2 = document.getElementById('myChart2').getContext('2d');
     
    var myChart2 = new Chart(ctx2, {
      
      data: {
        labels: ['Day1', 'Day2', 'Day3', 'Day4' , 'Day5' , 'Day6' ],
        datasets: [          
          
        	{
        		  type: 'line',
        		  label: 'Filled Count',
        		  backgroundColor: ['#A52A2A'],
        		  data: [],
        		} 
 
        ], 
        
        

      }, 
    });
    
     

    $.get("someservlet2", function(responseText) { // Execute Ajax GET request on URL of "someservlet" and execute the following function with Ajax response text...
       


    	var parsedData = JSON.parse(responseText); 

    	// Update the chart data
    	myChart2.data.labels = parsedData.map(function(item) { return item.day; });
    	myChart2.data.datasets[0].data = parsedData.map(function(item) { return item.count; });
    	myChart2.update();
 
     
 
    }); 
  } 
  // Call createChart() function when the window has finished loading
  window.onload = function() {
  createChart();
  createSecondChart();
  getD();
  }
   
	    
</script>


<script>

function getD() {
	

// 	alert("Welcome");
	  $.get("someservlet", function(responseText) { // Execute Ajax GET request on URL of "someservlet" and execute the following function with Ajax response text...
	      
		  	var data = JSON.parse(responseText);
		  console.log("Data: " ,data);
		   document.getElementById("new1").innerHTML =data[0].new1 ;
		   document.getElementById("uploaded").innerHTML =data[0].uploaded ;
		   document.getElementById("completed").innerHTML =data[0].completed ;
		   document.getElementById("finished").innerHTML =data[0].finished ;  

		  }); 
}
</script>


<script> 

 
</script>
	 


  
 
        <script>
       
            $(document).on("click", "#somebutton", function() { // When HTML DOM "click" event is invoked on element with ID "somebutton", execute the following function...
                $.get("applicantsServlet", function(responseText) {   // Execute Ajax GET request on URL of "someservlet" and execute the following function with Ajax response text...
                   console.log(responseText);
                	$("#somediv").text(responseText);           // Locate HTML DOM element with ID "somediv" and set its text content with the response text.
                });
            });
        </script>
        
        
        
        
        
        
         
  
  
    
  </head>
  <body style="background-color: lightgrey;">
  
    <jsp:include page="headerDashboard.jsp" flush="true" />
    <!--wrapper div starts here-->
    
     
    
    <div class="mainBody" style="display: flex; flex-direction: column;justify-content: space-around;"  >
       
       
       <div class="mainBodyP0" style="display: flex;flex-direction: row;justify-content: space-around;margin-top: 2%;">
<!--         gb(18 110 96) -->
       <div id=" " style="background-color: #7878b3;width: 16%;height: 16vh;box-shadow: 0 2px 4px rgba(0, 0, 0, 0.3); ">
	      <p style="color: white;font-size: 16px;margin:2% 0 0 4%;">Filled Applicants</p>
	      <p id="new1" style="color: white;font-size: 37px;margin:6% -2% 0 10% ;"><p>
	     
	    </div>
	    
	     <div id=" " style="background-color: #78b3ae   ;width: 16%;height: 16vh;box-shadow: 0 2px 4px rgba(0, 0, 0, 0.3); ">
	      <p style="color: white;font-size: 16px;margin:2% 0 0 4%;">Uploaded Documents</p>
	      <p id="uploaded" style="color: white;font-size: 37px;margin:6% -2% 0 10% ;"><p> 
	    </div>
	    
	     <div id=" " style="background-color: #d6a84b    ;width: 16%;height: 16vh;box-shadow: 0 2px 4px rgba(0, 0, 0, 0.3); ">
	      <p style="color: white;font-size: 16px;margin:2% 0 0 4%;">Post(s) Applied</p>
	      <p id="finished" style="color: white;font-size: 37px;margin:6% -2% 0 10% ;"><p> 
	    </div>
	    
	     <div  style="background-color: #F08A76  ;width: 16%;height: 16vh;box-shadow: 0 2px 4px rgba(0, 0, 0, 0.3); ">
	     <p style="color: white;font-size: 16px;margin:2% 0 0 4%;">Completed</p>
	      <p id="completed" style="color: white;font-size: 37px;margin:6% -2% 0 10% ;"><p> 
	      
	    </div>
	    
<!-- 	    <div style="background-color: #78b3ae  ;width: 16%;height: 16vh;box-shadow: 0 2px 4px rgba(0, 0, 0, 0.3); "> -->
	     
<!-- 	    </div>  -->
       
       
       </div>
       

      <div class="mainBodyP1" style="display: flex;flex-direction: row;justify-content: flex-start;margin-top: 2%;margin-left: 2%;">      
	    
	    
	    
	    <div style="background-color: white;width: 48%;height: 60vh;box-shadow: 0 2px 4px rgba(0, 0, 0, 0.3); ">
	    
	    <div style="height: 2.7vh;padding: 2%;font-size: 21px;font-family: Inter,sans-serif;color: grey ">
	        Filled Applicants  
<!-- 	        <input type="date" name="dateSelected" id="dateSelected" onchange="updateChart()"> -->
<!-- 	         <button type="button" id="somebutton"  >S</button> -->
        <div id="somediv"></div>
	    </div>
	    <div id="myChartContainer" style="padding:2%;">
			  <canvas id="myChart"  ></canvas>
			</div>
	    
	    </div>
	    
	    
	    
	    
	    
	    <div style="background-color: white;width: 48%;margin-left:1%;height: 60vh;box-shadow: 0 2px 4px rgba(0, 0, 0, 0.3); ">
	    
	    <div style="height: 2.7vh;padding: 2%;font-size: 21px;font-family: Inter,sans-serif;color: grey ">
	        Daywise Applicants 
<!-- 	        <input type="date" name="dateSelected" id="dateSelected" onchange="updateChart()"> -->
<!-- 	         <button type="button" id="somebutton"  >S</button> -->
        <div id="somediv"></div>
	    </div>
	    <div id="myChartContainer" style="padding:2%;">
			  <canvas id="myChart2"  ></canvas>
			</div>
	    
	    </div>
	    
	    
	    
	    
	    
	    
	    
<!-- 	    <div style="background-color: white;width: 48%;margin-left:1%; height: 60vh;box-shadow: 0 2px 4px rgba(0, 0, 0, 0.3);  "> -->
<!-- 	    <div style="height: 2.7vh;padding: 2%;font-size: 21px;font-family: Inter,sans-serif;color: grey "> -->
	         
<!-- <!-- 	        <input type="date" name="dateSelected" id="dateSelected" onchange="updateChart()"> -->  
<!-- <!-- 	         <button type="button" id="somebutton"  >S</button> --> 
<!--         <div id="somediv"></div> -->
<!-- 	    </div> -->
<!-- 	     <canvas id="myChart2"></canvas> -->
	     
<!-- 	     </div>   -->
	    
      </div>
        
      
<!--       <div class="mainBodyP1" style="display: flex;flex-direction: row;justify-content: space-around;margin-top: 2%;"> -->
<!--       	<div style="background-color: white;width: 95%;height: 70vh;box-shadow: 0 2px 4px rgba(0, 0, 0, 0.3); "> -->
	   
<!-- 	    </div> -->
<!--       </div> -->

      
    </div>

    <%-- <jsp:include page="footer.jsp" flush="true" /> --%>

    <!--Wrapper div ends here-->
  </body>
</html>
