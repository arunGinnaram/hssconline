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
 

@WebServlet("/someservlet2")
public class DashboardServlet2 extends HttpServlet {
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
        	
        	   
              
            
            Statement stmtGETOUNT = null;  
            
                stmtF = conn.createStatement();            
               
                          
                 
                 List<String> days = new ArrayList<>();
	             List<Integer> counts = new ArrayList<>();
	             
 	             Statement sDW = null; 
 	             sDW = conn.createStatement();
 	             String rDW =  "SELECT DATE(date_created) AS day, COUNT(*) AS count FROM applicants  WHERE application_status!='NEW' AND DATE(date_created) >= (SELECT MIN(DATE(date_created)) FROM applicants) GROUP BY DATE(date_created) ;";
 	              
 	             ResultSet rIdES = sDW.executeQuery(rDW);
 	             Map<String, Integer> appDW = new HashMap<>();
 	             while(rIdES.next()) { 
 	            	 days.add(rIdES.getString("day"));
 	            	 counts.add( rIdES.getInt("count"));
// 	            	fCountTimWise.put(rIdES.getString("day"), rIdES.getInt("count"));
 	            	
 	             }
 	            out.println("[");
 	           for (int i = 0; i < days.size(); i++) {
 	               out.println("{");
 	               out.println("\"day\": \"" + days.get(i) + "\",");
 	               out.println("\"count\": " + counts.get(i));
 	               out.println("}" + (i < days.size() - 1 ? "," : ""));
 	           }
 	           out.println("]");

 	             
 
            
            
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
