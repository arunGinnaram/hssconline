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
  <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
   <script>
    function createChart() {
      var ctx = document.getElementById('myChart').getContext('2d');
      var myChart = new Chart(ctx, {
        type: 'bar',
        data: {
          labels: ['<9am', '9am-11am', '11am-1pm', '1pm-3pm'],
          datasets: [
            {
              label: 'Male',
              backgroundColor: 'rgba(54, 162, 235, 0.5)',
              borderColor: 'rgba(54, 162, 235, 1)',
              borderWidth: 1,
              data: [getRandomInt(10, 30), getRandomInt(20, 50), getRandomInt(30, 60), getRandomInt(40, 80)],
            },
            {
              label: 'Female',
              backgroundColor: 'rgba(255, 99, 132, 0.5)',
              borderColor: 'rgba(255, 99, 132, 1)',
              borderWidth: 1,
              data: [getRandomInt(5, 20), getRandomInt(15, 30), getRandomInt(25, 45), getRandomInt(35, 70)],
            }
          ]
        },
        options: {
          scales: {
            yAxes: [{
              ticks: {
                beginAtZero: true
              }
            }]
          }
        }
      });
    }

    // Returns a random integer between min (included) and max (included)
    function getRandomInt(min, max) {
      return Math.floor(Math.random() * (max - min + 1)) + min;
    }

    // Call createChart() function when the window has finished loading
    window.addEventListener('load', createChart);
  </script>
  
  <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
  
<!--   calling servlet to get count -->
  <script type="text/javascript"> 
  
  function getCount() {
    $.ajax({
        url: 'getCount',
        type: 'GET',
//         success: function(data) {
//             $('#count').html(data);
//         },
        error: function(jqXHR, textStatus, errorThrown) {
            console.error(textStatus, errorThrown);
        }
    });
}

  
  </script>
  
  
    
  </head>
  <body style="background-color: lightgrey;">
  
    <jsp:include page="headerDashboard.jsp" flush="true" />
    <!--wrapper div starts here-->
    <div class="mainBody" style="display: flex; flex-direction: column;justify-content: space-around;"  >
       

      <div class="mainBodyP1" style="display: flex;flex-direction: row;justify-content: space-around;margin-top: 2%;">      
	    <div style="background-color: white;width: 45%;height: 70vh;box-shadow: 0 2px 4px rgba(0, 0, 0, 0.3); ">
	    
	    <div style="height: 2vh;background-color: rgb(169, 168, 162);padding: 2%; ">
	        As of Now Overall Count Applied for the Posts
	        
	        <button type="button" onclick="return getCount()">Get Count</button>
	    </div>
	    <div id="myChartContainer">
			  <canvas id="myChart"></canvas>
			</div>
	    
	    </div>
	    <div style="background-color: white;width: 45%;height: 70vh;box-shadow: 0 2px 4px rgba(0, 0, 0, 0.3);  ">
	    </div>     
      </div>
      
      <div class="mainBodyP1" style="display: flex;flex-direction: row;justify-content: space-around;margin-top: 2%;">
      	<div style="background-color: white;width: 95%;height: 70vh;box-shadow: 0 2px 4px rgba(0, 0, 0, 0.3); ">
	    </div>
      </div>

      
    </div>

    <%-- <jsp:include page="footer.jsp" flush="true" /> --%>

    <!--Wrapper div ends here-->
  </body>
</html>
