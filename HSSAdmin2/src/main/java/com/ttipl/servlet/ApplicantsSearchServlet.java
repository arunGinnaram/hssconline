
package com.ttipl.servlet;

	import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ttipl.DBConfig.DBConfiguration;
import com.ttipl.bean.Applicant;
	 

	@WebServlet("/applicantsSearchServlet")
	public class ApplicantsSearchServlet extends HttpServlet {
		private static final long serialVersionUID = 1L; 
		
		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			 
			response.setContentType("text/html");
	        PrintWriter out = response.getWriter(); 
	        Connection conn = null;
	        PreparedStatement stmtF = null;
	        PreparedStatement st =  null;
	        Statement tid = null;
	        
	        try {
	        conn = 	DBConfiguration.Initialize();
			} catch (ClassNotFoundException e1) {
				e1.printStackTrace();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}  
	        try {  
	            
	            
	            List<Applicant> applicants = new ArrayList<>(); 
	             //get the recors based on transaction id 
	             String id = request.getParameter("tid");
	             System.out.println("TID: received:" + id);
	             tid = conn.createStatement();
//	             String tidRec = "SELECT * FROM hssc_groupc_online.applicants WHERE transactionid="+id;
	             
//	             ResultSet rId = tid.executeQuery(tidRec);
	             
	             String tidRec = "SELECT * FROM applicants WHERE transactionid=?";
	             PreparedStatement pstmt = conn.prepareStatement(tidRec);
	             pstmt.setString(1, id);
	             ResultSet rId = pstmt.executeQuery();
	             List<Applicant> appTid = new ArrayList<>();
	             
	             if(rId!=null && rId.next()) {
	            	 do {
	            	 Applicant applicantTId = new Applicant();
	            	 applicantTId.setTid(rId.getString(1));	            	 
	            	 applicantTId.setFullName( rId.getString(5));
	            	 applicantTId.setEmail(rId.getString(10));
	            	 applicantTId.setGender(rId.getString(12));
	            	 applicantTId.setDob(rId.getString(13));
	            	 applicantTId.setAadhar(rId.getString(40));
	            	 applicantTId.setApplicationStatus(rId.getString(56));
	            	 appTid.add(applicantTId);	
	            	 } while(rId.next());
	            	 
	            	 
	            	 {
	            		 // Set the list as an attribute of the request object
	    	             request.setAttribute("applicants", appTid);

	    	             // Forward the request to the JSP page
	    	             request.getRequestDispatcher("applicants.jsp").forward(request, response);
//	    	             dispatcher.forward(request, response);
	            	 }
	            	 
	             }else {
//	            	 out.println("No data found for Transaction Id " + id);
	            	 request.setAttribute("message", "No data found for Transaction Id:" + id);
	            	 request.getRequestDispatcher("applicants.jsp").forward(request, response);
	             }
	             
	             
	             
//	             while(rId.next()) {
//	            	 Applicant applicantTId = new Applicant();
//	            	 applicantTId.setTid(rId.getString(1));	            	 
//	            	 applicantTId.setFullName( rId.getString(5));
//	            	 applicantTId.setEmail(rId.getString(10));
//	            	 applicantTId.setGender(rId.getString(12));
//	            	 applicantTId.setDob(rId.getString(13));
//	            	 applicantTId.setAadhar(rId.getString(40));
//	            	 applicantTId.setApplicationStatus(rId.getString(56));
//	            	 appTid.add(applicantTId);	
//	            	 
//	            	  
//	             } 
	             
	             
	             
	         
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