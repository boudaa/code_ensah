package com.contextparams;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/GetContextParamsInServlet")
public class GetContextParamsInServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// On récupère le context
		ServletContext cntx = getServletContext();

		// On récupère le paramètre du context portant le nom type_db
		String contextParam = cntx.getInitParameter("type_db");

		PrintWriter out = response.getWriter();

		out.println(contextParam);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

}
