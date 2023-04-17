
package com.ttipl.servlet;

	import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ttipl.DBConfig.DBConfiguration;
import com.ttipl.bean.Applicant;
	 

	@WebServlet("/applicantsServlet")
	public class ApplicantsServlet extends HttpServlet {
		private static final long serialVersionUID = 1L;

		//to get count of male and female - who have completed applying for the post
//		protected void dopost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
//			 		 
//			System.out.println("Entered");
//			System.out.println("Connection: " ); 
//			response.setContentType("text/html");
//	        PrintWriter out = response.getWriter(); 
//	        Connection conn = null;
//	        PreparedStatement stmtF = null;
//	        try {
//	        conn = 	DBConfiguration.Initialize();
//			} catch (ClassNotFoundException e1) {
//				e1.printStackTrace();
//			} catch (SQLException e1) {
//				e1.printStackTrace();
//			}  
//	        try {  
//	            System.out.println("Connection: " + conn); 
//	            int pageSize=10;
//	            int currentPage = request.getParameter("page")!=null ? Integer.parseInt(request.getParameter("page")) :1 ;
//	            int startIndex = (currentPage-1)*pageSize ; 
//	            
//	            String query = "SELECT * FROM applicants LIMIT " + startIndex + ", " + pageSize;
//	            stmtF = conn.prepareStatement(query);
//	            ResultSet rsF = stmtF.executeQuery(); 
//	            
//	            //now get the number of paged based on the no of records / page size
//	            
//	            Statement st =  null;
//	            String pages =  "SELECT count(*) from applicants;";
//	            ResultSet rs = st.executeQuery(pages);
//	            int totalRecords = 0;
//	            int totalPages = 0 ;
//	            if(rs.next()) {
//	            totalRecords = rs.getInt(1);
//	            totalPages = (int) Math.ceil((double) totalRecords / pageSize);
//	            
//	            }
//	             System.out.println("TRecord: " + totalRecords);
//	             System.out.println("totalPages: " + totalPages);
//	             
//	             request.setAttribute("currentPage", currentPage);
//	             request.setAttribute("totalPages", totalPages);
//	            
//	       
//	            RequestDispatcher dispatcher = request.getRequestDispatcher("applicants.jsp");
//	            System.out.println("About to forward");
//	            dispatcher.forward(request, response);
//	            System.out.println("About to forward"); 
//	        } finally {
//	             
//				if (stmtF != null) {
//	                try {
//	                	stmtF.close();
//	                } catch (SQLException e) {
//	                    e.printStackTrace();
//	                }
//	            }
//	            if (conn != null) {
//	                try {
//	                    conn.close();
//	                } catch (SQLException e) {
//	                    e.printStackTrace();
//	                }
//	            }
//	        }
//	    }
		
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			System.out.println("Entered");
			System.out.println("Connection: " ); 
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
	            System.out.println("Connection: " + conn); 
	            int pageSize=10;
	            int currentPage = request.getParameter("page")!=null ? Integer.parseInt(request.getParameter("page")) :1 ;
	            int startIndex = (currentPage-1)*pageSize ; 
	            
	            String query = "SELECT * FROM applicants LIMIT " + startIndex + ", " + pageSize;
	            stmtF = conn.prepareStatement(query);
	            ResultSet rsF = stmtF.executeQuery();
	            
	            List<Applicant> applicants = new ArrayList<>();
//	            Applicant applicant = new Applicant();
	            while(rsF.next()) {
	            	Applicant applicant = new Applicant();
	            	applicant.setTid(rsF.getString(1));	            	 
	            	applicant.setFullName( rsF.getString(5));
	            	 applicant.setEmail(rsF.getString(10));
	            	 applicant.setGender(rsF.getString(12));
	            	 applicant.setDob(rsF.getString(13));
	            	  applicant.setAadhar(rsF.getString(40));
	            	  applicant.setApplicationStatus(rsF.getString(56));
	            	  
 
	            	  applicants.add(applicant);
	            	  }
	            
	            request.setAttribute("applicants", applicants);
	             
	            //for the pagination
	            String pages =  "SELECT count(*) from applicants;";
	            st = conn.prepareStatement(pages);
	            ResultSet rs = st.executeQuery();
	            int totalRecords = 0;
	            int totalPages = 0 ;
	            if(rs.next()) {
	            totalRecords = rs.getInt(1);
	            totalPages = (int) Math.ceil((double) totalRecords / pageSize);
	            
	            }
	             System.out.println("TRecord: " + totalRecords);
	             System.out.println("totalPages: " + totalPages);
	             
	             request.setAttribute("currentPage", currentPage);
	             request.setAttribute("totalPages", totalPages);
	             
	             
	             //get the recors based on transaction id 
	             String id = request.getParameter("tid");
	             System.out.println("TID: received:" + id);
	             tid = conn.createStatement();
	             String tidRec = "SELECT * FROM applicants WHERE transactionid="+id;
	             ResultSet rId = tid.executeQuery(tidRec);
	             List<Applicant> appTid = new ArrayList<>();
	             while(rId.next()) {
	            	 Applicant applicantTId = new Applicant();
	            	 applicantTId.setTid(rId.getString(1));	            	 
	            	 applicantTId.setFullName( rId.getString(5));
	            	 applicantTId.setEmail(rId.getString(10));
	            	 applicantTId.setGender(rId.getString(12));
	            	 applicantTId.setDob(rId.getString(13));
	            	 applicantTId.setAadhar(rId.getString(40));
	            	 applicantTId.setApplicationStatus(rId.getString(56));
	            	 appTid.add(applicantTId);	            	 
	             }
	             
	             request.setAttribute("applicantTId",appTid); 
	             
	              
	             
	            
	             request.getRequestDispatcher("applicants.jsp").forward(request, response);
 
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