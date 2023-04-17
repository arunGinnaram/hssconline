
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
	 

	@WebServlet("/applicantsStatusSearchServlet")
	public class ApplicantsStatusSearchServlet extends HttpServlet {
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
	        	int pageSize=10;
	            int currentPage = request.getParameter("page")!=null ? Integer.parseInt(request.getParameter("page")) :1 ;
	            int startIndex = (currentPage-1)*pageSize ; 
	            
	            
	            List<Applicant> applicants = new ArrayList<>(); 
	             //get the recors based on transaction id 
	             String status = request.getParameter("status");
	             System.out.println("STATUS:" + status);
	             tid = conn.createStatement();  
	             String statusRec = "SELECT * FROM applicants WHERE application_status='"+status+"' LIMIT " + startIndex + ", " + pageSize; 

	             
	             ResultSet rst = tid.executeQuery(statusRec);
	             List<Applicant> appTid = new ArrayList<>();
	             while(rst.next()) {
	            	 Applicant applicantTId = new Applicant();
	            	 applicantTId.setTid(rst.getString(1));	            	 
	            	 applicantTId.setFullName( rst.getString(5));
	            	 applicantTId.setEmail(rst.getString(10));
	            	 applicantTId.setGender(rst.getString(12));
	            	 applicantTId.setDob(rst.getString(13));
	            	 applicantTId.setAadhar(rst.getString(40));
	            	 applicantTId.setApplicationStatus(rst.getString(56));
	            	 appTid.add(applicantTId);	
	            	 
	            	  
	             }
	             
	           //for the pagination
		            String pages =  "SELECT count(*) from applicants WHERE application_status='"+status+"';";
		            st = conn.prepareStatement(pages);
		            ResultSet rs = st.executeQuery();
		            int totalRecords = 0;
		            int totalPages = 0 ;
		            if(rs.next()) {
		            totalRecords = rs.getInt(1);
		            totalPages = (int) Math.ceil((double) totalRecords / pageSize);
		            
		            }
 
	             
	             
	             
	          // Set the list as an attribute of the request object
	             request.setAttribute("applicants", appTid);
	             request.setAttribute("currentPage", currentPage);
	             request.setAttribute("totalPages", totalPages);

	             // Forward the request to the JSP page
	             request.getRequestDispatcher("applicants.jsp").forward(request, response);
//	             dispatcher.forward(request, response);
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