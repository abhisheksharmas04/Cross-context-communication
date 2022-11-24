package com.ab.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/uscalurl")
public class UsBillAmountCalcServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter pw = response.getWriter();
		response.setContentType("text/html");
		
		// Read form data:
		String name = request.getParameter("iname");
		float price = Float.parseFloat(request.getParameter("iprice"));
		float qty = Float.parseFloat(request.getParameter("iqty"));
		
		// calcucate bill amounth:
		float billAmounth = price * qty;
		float tax = billAmounth*0.18f;
		float tax2 = billAmounth*0.1f;
		float finalAmounth = billAmounth + tax + tax2;
		
		//display details:
		pw.println("<h1 style='color:blue; text-align:center'> USA </h1>");
		pw.println("<br> <p style='text-align:center'> Name: " + name + "<br>");
		pw.println("<br> <p style='text-align:center'> Bill Amounth: " + billAmounth + "<br>");
		pw.println("<br> <p style='text-align:center'> Tax1 Amounth: " + tax + "<br>");
		pw.println("<br> <p style='text-align:center'> Tax2 Amounth: " + tax2 + "<br>");
		pw.println("<br> <p style='text-align:center'> Final Amounth: " + finalAmounth + "<br>");
		
		// do not close stream
		// pw.close();
		
		/*
		 By default tomcat server do not support cross context communication
		 We have to enable settings :
		 	GOTO: context.xml of tomcat server
		 		add: <Context crossContext="true">
		*/
		
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	/*
	 	Limitation of RequestDispatcher object based ServletCommunication:
	 		-> Dest web comp must be there in known technology like servlet, jsp, html, i.e 
	 		we cannot take asp.net, php, js and etc components.
	 		
	 		-> Source and dest web component can be there either in same web application or in two
	 		different web applications same server (Cross context) but can not be placed in two 
	 		different web application of two different servers of same or different machines.
	 		
	 		-> In some server like tomcat we need enable extra settings for cross context communication.
	 		
	 		To overcome all these problmes go for send redirection
	 */

}
