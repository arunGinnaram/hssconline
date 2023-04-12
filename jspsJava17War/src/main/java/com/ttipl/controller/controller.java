package com.ttipl.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.security.KeyManagementException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.SSLContext;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ttipl.JspsApplication;
import com.ttipl.service.JspsApplication1;

@RequestMapping("/otp")
@Controller
public class controller {
	
	@Autowired
	private JspsApplication1 JspsApplication1;
	
	private static String username="haryanait-hssc";
	private static String password="hssc@123456";
	private static String senderID= "GOVHRY";
	private static String secureKey="cc3f6a58-cf9b-4804-985a-ee1bdc2bbb1d";
	private static String templateId ="1007659550067879922";
	
	@GetMapping("/getOTP")
	public ResponseEntity<String>  getOTP() {
//		JspsApplication1 smsServices=new JspsApplication1();
		String sendOtpSMS = JspsApplication1.sendOtpSMS(username, password, "Dear Candidate, Your OTP for login at onetimeregn.haryana.gov.in is : 123456", senderID, "8179730126", secureKey,templateId);
		System.out.println("sendOtpSMS" + sendOtpSMS);
		System.out.println("Received OTP Successfully");
		return new ResponseEntity<String>("Received OTP Successfully : " + sendOtpSMS ,HttpStatus.ACCEPTED);
		
		
	}
	
	
	

	 

}
