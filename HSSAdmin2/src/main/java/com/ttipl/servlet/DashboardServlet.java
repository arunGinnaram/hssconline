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
 

@WebServlet("/someservlet")
public class DashboardServlet extends HttpServlet {
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
                String getc =  "SELECT COUNT(*) as Count,\r\n"
                		+ "sum(case when (application_status!='NEW') then 1 else 0 end ) as 'new',\r\n"
                		+ "sum(case when (application_status='UPLOADED') then 1 else 0 end ) as 'uploaded',\r\n"
                		+ "sum(case when (application_status='FINISHED') then 1 else 0 end ) as 'finished',\r\n"
                		+ "sum(case when (application_status='COMPLETED') then 1 else 0 end ) as 'completed'\r\n"
                		+ "FROM applicants;";
                
                ResultSet rsF1 = stmtF.executeQuery(getc);            
                if(rsF1.next()) {
                	fCountTimWise.put("TC", rsF1.getInt(1));
                	fCountTimWise.put("new1", rsF1.getInt(2));
                	fCountTimWise.put("uploaded", rsF1.getInt(3));
                	fCountTimWise.put("finished", rsF1.getInt(4));
                	fCountTimWise.put("completed", rsF1.getInt(5));                	 
                }  
                
 
 	             
 	             Statement sDW = null;
// 	             String id = request.getParameter("tid");
// 	             System.out.println("TID: received:" + id);
 	             sDW = conn.createStatement();
 	              
 	             
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
