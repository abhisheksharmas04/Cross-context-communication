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

@WebServlet("/indiacalurl")
public class IndianBillAmountCalcServlet extends HttpServlet {
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
		float tax = billAmounth*0.12f;
		float finalAmounth = billAmounth + tax;
		
		//display details:
		pw.println("<h1 style='color:blue; text-align:center'> India </h1>");
		pw.println("<br> <p style='text-align:center'> Name: " + name + "<br>");
		pw.println("<br> <p style='text-align:center'> Bill Amounth: " + billAmounth + "<br>");
		pw.println("<br> <p style='text-align:center'> Tax Amounth: " + tax + "<br>");
		pw.println("<br> <p style='text-align:center'> Final Amounth: " + finalAmounth + "<br>");
		
		// include the response of UsBillAmounth calculator servlet comp of USpriceCalApp
		
		// get current ServletContext of Web application
		ServletContext sc = getServletContext();
		
		// get foreign context of current web application
		ServletContext fc = sc.getContext("/UsPriceCalculatorApp");
		
		// create dispatcher pointing to destination servlet component(UsPriceCalculatorServlet)
		RequestDispatcher rd = sc.getRequestDispatcher("/uscalurl");
		
		// include the response
		rd.include(request, response);
		
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
