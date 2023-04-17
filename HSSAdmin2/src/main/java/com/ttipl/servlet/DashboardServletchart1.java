package com.ttipl.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ttipl.DBConfig.DBConfiguration;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 

@WebServlet("/someservletc1")
public class DashboardServletchart1 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	//to get count of male and female - who have completed applying for the post
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 		 
		 String dateCreated = request.getParameter("dateSelected");
         System.out.println("Request Param: "+ dateCreated);
         
		System.out.println("Entered");
		response.setContentType("text/html");
        PrintWriter out = response.getWriter(); 
        Connection conn = null;
        Map<String, Integer> fCountTimWise = new HashMap<>();
        List<String> data = new ArrayList<>();
        
        try {
        conn = 	DBConfiguration.Initialize();
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		} catch (SQLException e1) {
			e1.printStackTrace();
		} 
        
      
        Statement stmtF = null; 
        Statement stmtM = null;
        PreparedStatement pF = null; 
        PreparedStatement pM = null;
        
        try {                
        	
        	   
            stmtF = conn.createStatement();            
            String femaleCountCompletedAppyingPost =  
            		
            		" select count(*) ,"
            		+ "sum(case when (TIME(date_created) <= '09:00:00' || TIME(date_created) >='21:00:00') AND DATE(date_created) = CURDATE() then 1 else 0 end ) as 'TBefore9am',"
            		+ "sum(case when (TIME(date_created) BETWEEN '09:00:00' AND '11:00:00') AND DATE(date_created) = CURDATE() then 1 else 0 end)as 'T9amTo11am',"
            		+ "sum(case when (TIME(date_created) BETWEEN '11:00:00' AND '13:00:00') AND DATE(date_created) = CURDATE() then 1 else 0 end)as 'T11amTo1pm',"
            		+ "sum(case when (TIME(date_created) BETWEEN '13:00:00' AND '15:00:00') AND DATE(date_created) = CURDATE() then 1 else 0 end)as 'T1pmTo3pm',"
            		+ "sum(case when (TIME(date_created) BETWEEN '15:00:00' AND '17:00:00') AND DATE(date_created) = CURDATE() then 1 else 0 end)as 'T3pmTo5pm',"
            		+ "sum(case when (TIME(date_created) BETWEEN '17:00:00' AND '19:00:00') AND DATE(date_created) = CURDATE() then 1 else 0 end)as 'T5pmTo7pm',"
            		+ "sum(case when (TIME(date_created) BETWEEN '19:00:00' AND '21:00:00') AND DATE(date_created) = CURDATE() then 1 else 0 end)as 'T7pmTo9pm',"

					+ "sum(case when (TIME(date_created) <= '09:00:00' OR TIME(date_created) >= '21:00:00') AND DATE(date_created) = DATE_SUB(CURDATE(), INTERVAL 1 DAY) THEN 1 ELSE 0 END) as 'TM1Before9am',"
					+ "sum(case when (TIME(date_created) BETWEEN '09:00:00' AND '11:00:00') AND DATE(date_created) = DATE_SUB(CURDATE(), INTERVAL 1 DAY)  then 1 else 0 end)as 'TM19amTo11am',"
            		+ "sum(case when (TIME(date_created) BETWEEN '11:00:00' AND '13:00:00') AND DATE(date_created) = DATE_SUB(CURDATE(), INTERVAL 1 DAY) then 1 else 0 end)as 'TM111amTo1pm',"
            		+ "sum(case when (TIME(date_created) BETWEEN '13:00:00' AND '15:00:00') AND DATE(date_created) = DATE_SUB(CURDATE(), INTERVAL 1 DAY) then 1 else 0 end)as 'TM11pmTo3pm',"
            		+ "sum(case when (TIME(date_created) BETWEEN '15:00:00' AND '17:00:00') AND DATE(date_created) = DATE_SUB(CURDATE(), INTERVAL 1 DAY) then 1 else 0 end)as 'TM13pmTo5pm',"
            		+ "sum(case when (TIME(date_created) BETWEEN '17:00:00' AND '19:00:00') AND DATE(date_created) = DATE_SUB(CURDATE(), INTERVAL 1 DAY) then 1 else 0 end)as 'TM15pmTo7pm',"
            		+ "sum(case when (TIME(date_created) BETWEEN '19:00:00' AND '21:00:00') AND DATE(date_created) = DATE_SUB(CURDATE(), INTERVAL 1 DAY) then 1 else 0 end)as 'TM17pmTo9pm',"

            		+ "sum(case when (TIME(date_created) <= '09:00:00' || TIME(date_created) >='21:00:00') AND DATE(date_created) = DATE_SUB(CURDATE(), INTERVAL 2 DAY) then 1 else 0 end ) as 'TM2Before9am',"
            		+ "sum(case when (TIME(date_created) BETWEEN '09:00:00' AND '11:00:00') AND DATE(date_created) = DATE_SUB(CURDATE(), INTERVAL 2 DAY)  then 1 else 0 end)as 'TM29amTo11am',"
            		+ "sum(case when (TIME(date_created) BETWEEN '11:00:00' AND '13:00:00') AND DATE(date_created) = DATE_SUB(CURDATE(), INTERVAL 2 DAY) then 1 else 0 end)as 'TM211amTo1pm',"
            		+ "sum(case when (TIME(date_created) BETWEEN '13:00:00' AND '15:00:00') AND DATE(date_created) = DATE_SUB(CURDATE(), INTERVAL 2 DAY) then 1 else 0 end)as 'TM21pmTo3pm',"
            		+ "sum(case when (TIME(date_created) BETWEEN '15:00:00' AND '17:00:00') AND DATE(date_created) = DATE_SUB(CURDATE(), INTERVAL 2 DAY) then 1 else 0 end)as 'TM23pmTo5pm',"
            		+ "sum(case when (TIME(date_created) BETWEEN '17:00:00' AND '19:00:00') AND DATE(date_created) = DATE_SUB(CURDATE(), INTERVAL 2 DAY) then 1 else 0 end)as 'TM25pmTo7pm',"
            		+ "sum(case when (TIME(date_created) BETWEEN '19:00:00' AND '21:00:00') AND DATE(date_created) = DATE_SUB(CURDATE(), INTERVAL 2 DAY) then 1 else 0 end)as 'TM27pmTo9pm',"

            		+ "sum(case when (TIME(date_created) <= '09:00:00' || TIME(date_created) >='21:00:00') AND DATE(date_created) = DATE_SUB(CURDATE(), INTERVAL 3 DAY) then 1 else 0 end ) as 'TM3Before9am',"
            		+ "sum(case when (TIME(date_created) BETWEEN '09:00:00' AND '11:00:00') AND DATE(date_created) = DATE_SUB(CURDATE(), INTERVAL 3 DAY)  then 1 else 0 end)as 'TM39amTo11am',"
            		+ "sum(case when (TIME(date_created) BETWEEN '11:00:00' AND '13:00:00') AND DATE(date_created) = DATE_SUB(CURDATE(), INTERVAL 3 DAY) then 1 else 0 end)as 'TM311amTo1pm',"
            		+ "sum(case when (TIME(date_created) BETWEEN '13:00:00' AND '15:00:00') AND DATE(date_created) = DATE_SUB(CURDATE(), INTERVAL 3 DAY) then 1 else 0 end)as 'TM31pmTo3pm',"
            		+ "sum(case when (TIME(date_created) BETWEEN '15:00:00' AND '17:00:00') AND DATE(date_created) = DATE_SUB(CURDATE(), INTERVAL 3 DAY) then 1 else 0 end)as 'TM33pmTo5pm',"
            		+ "sum(case when (TIME(date_created) BETWEEN '17:00:00' AND '19:00:00') AND DATE(date_created) = DATE_SUB(CURDATE(), INTERVAL 3 DAY) then 1 else 0 end)as 'TM35pmTo7pm',"
            		+ "sum(case when (TIME(date_created) BETWEEN '19:00:00' AND '21:00:00') AND DATE(date_created) = DATE_SUB(CURDATE(), INTERVAL 3 DAY) then 1 else 0 end)as 'TM37pmTo9pm',"

            		+ "sum(case when (TIME(date_created) <= '09:00:00' || TIME(date_created) >='21:00:00') AND DATE(date_created) = DATE_SUB(CURDATE(), INTERVAL 4 DAY) then 1 else 0 end ) as 'TM4Before9am',"
            		+ "sum(case when (TIME(date_created) BETWEEN '09:00:00' AND '11:00:00') AND DATE(date_created) = DATE_SUB(CURDATE(), INTERVAL 4 DAY)  then 1 else 0 end)as 'TM49amTo11am',"
            		+ "sum(case when (TIME(date_created) BETWEEN '11:00:00' AND '13:00:00') AND DATE(date_created) = DATE_SUB(CURDATE(), INTERVAL 4 DAY) then 1 else 0 end)as 'TM411amTo1pm',"
            		+ "sum(case when (TIME(date_created) BETWEEN '13:00:00' AND '15:00:00') AND DATE(date_created) = DATE_SUB(CURDATE(), INTERVAL 4 DAY) then 1 else 0 end)as 'TM41pmTo3pm',"
            		+ "sum(case when (TIME(date_created) BETWEEN '15:00:00' AND '17:00:00') AND DATE(date_created) = DATE_SUB(CURDATE(), INTERVAL 4 DAY) then 1 else 0 end)as 'TM43pmTo5pm',"
            		+ "sum(case when (TIME(date_created) BETWEEN '17:00:00' AND '19:00:00') AND DATE(date_created) = DATE_SUB(CURDATE(), INTERVAL 4 DAY) then 1 else 0 end)as 'TM45pmTo7pm',"
            		+ "sum(case when (TIME(date_created) BETWEEN '19:00:00' AND '21:00:00') AND DATE(date_created) = DATE_SUB(CURDATE(), INTERVAL 4 DAY) then 1 else 0 end)as 'TM47pmTo9pm' "
            		+ "from applicants where application_status != 'NEW';";
            
            ResultSet rsF = stmtF.executeQuery(femaleCountCompletedAppyingPost);            
            if(rsF.next()) {
            	fCountTimWise.put("fcountD1FT", rsF.getInt(1));
            	fCountTimWise.put("fcountD1B9", rsF.getInt(2));
            	fCountTimWise.put("fcountD1911", rsF.getInt(3));
            	fCountTimWise.put("fcountD11113", rsF.getInt(4));
            	fCountTimWise.put("fcountD11315", rsF.getInt(5));
            	fCountTimWise.put("fcountD11517", rsF.getInt(6));
            	fCountTimWise.put("fcountD11719", rsF.getInt(7));
            	fCountTimWise.put("fcountD11921", rsF.getInt(8));
            	
//            	fCountTimWise.put("fcountD2FT", rsF.getInt(9));
            	fCountTimWise.put("fcountD2B9", rsF.getInt(9));
            	fCountTimWise.put("fcountD2911", rsF.getInt(10));
            	fCountTimWise.put("fcountD21113", rsF.getInt(11));
            	fCountTimWise.put("fcountD21315", rsF.getInt(12));
            	fCountTimWise.put("fcountD21517", rsF.getInt(13));
            	fCountTimWise.put("fcountD21719", rsF.getInt(14));
            	fCountTimWise.put("fcountD21921", rsF.getInt(15));
            	
//            	fCountTimWise.put("fcountD3FT", rsF.getInt(17));
            	fCountTimWise.put("fcountD3B9", rsF.getInt(16));
            	fCountTimWise.put("fcountD3911", rsF.getInt(17));
            	fCountTimWise.put("fcountD31113", rsF.getInt(18));
            	fCountTimWise.put("fcountD31315", rsF.getInt(19));
            	fCountTimWise.put("fcountD31517", rsF.getInt(20));
            	fCountTimWise.put("fcountD31719", rsF.getInt(21));
            	fCountTimWise.put("fcountD31921", rsF.getInt(22));
            	
//            	fCountTimWise.put("fcountD4FT", rsF.getInt(25));
            	fCountTimWise.put("fcountD4B9", rsF.getInt(23));
            	fCountTimWise.put("fcountD4911", rsF.getInt(24));
            	fCountTimWise.put("fcountD41113", rsF.getInt(25));
            	fCountTimWise.put("fcountD41315", rsF.getInt(26));
            	fCountTimWise.put("fcountD41517", rsF.getInt(27));
            	fCountTimWise.put("fcountD41719", rsF.getInt(28));
            	fCountTimWise.put("fcountD41921", rsF.getInt(29));
            	
//            	fCountTimWise.put("fcountD5FT", rsF.getInt(33));
            	fCountTimWise.put("fcountD5B9", rsF.getInt(30));
            	fCountTimWise.put("fcountD5911", rsF.getInt(31));
            	fCountTimWise.put("fcountD51113", rsF.getInt(32));
            	fCountTimWise.put("fcountD51315", rsF.getInt(33));
            	fCountTimWise.put("fcountD51517", rsF.getInt(34));
            	fCountTimWise.put("fcountD51719", rsF.getInt(35));
            	fCountTimWise.put("fcountD51921", rsF.getInt(36));
            }  
            
                
    
 	             
 	             
 	             
 	            //converting to Json to send to html 
                 StringBuilder sb = new StringBuilder();
                 sb.append("{");
                 for (Map.Entry<String, Integer> entry : fCountTimWise.entrySet()) {
                     sb.append("\"").append(entry.getKey()).append("\":").append(entry.getValue()).append(",");
                 }
                 sb.deleteCharAt(sb.length()-1);
                 sb.append("}");
                 String jsonString = sb.toString();
                 data.add(jsonString); 
            
            out.println( data );  
            
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (stmtF != null) {
                try {
                	stmtF.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
	

}
