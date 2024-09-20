package com.pwskills.harshit.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.pwskills.harshit.DataObject.CrudAppDto;
import com.pwskills.harshit.Service.ICrudAppService;
import com.pwskills.harshit.factory.CrudServiceFactory;

@WebServlet("/controller/*")
public class ServletController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	ICrudAppService service = CrudServiceFactory.getCrudServiceObj();

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	private void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		if (request.getRequestURI().endsWith("insert")) {
			insertData(request, response);
		}

		if (request.getRequestURI().endsWith("view")) {
			viewData(request, response);
		}

		if (request.getRequestURI().endsWith("delete")) {
			deleteData(request, response);
		}

		if (request.getRequestURI().endsWith("editPage")) {
			updateData(request, response);
		}

		if (request.getRequestURI().endsWith("updateRecord")) {
			updateRecordLatest(request, response);
		}

	}

	private void updateRecordLatest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String sid = request.getParameter("sid");
		String sname = request.getParameter("sname");
		String sage = request.getParameter("sage");
		String saddress = request.getParameter("saddress");

		CrudAppDto dto = new CrudAppDto();
		dto.setSid(Integer.parseInt(sid));
		dto.setSname(sname);
		dto.setSaddress(saddress);
		dto.setSage(Integer.parseInt(sage));

		String stutas = service.updateRecord(dto);
		RequestDispatcher rd = null;

		if (stutas.equals("success")) {
			System.out.println(stutas);
			rd = request.getRequestDispatcher("../../success.html");
			rd.forward(request, response);
		} else {
			rd = request.getRequestDispatcher("../../failure.html");
			rd.forward(request, response);
		}

	}

	private void updateData(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String sid = request.getParameter("id");

		CrudAppDto appDto = service.readRecord(Integer.parseInt(sid));
		RequestDispatcher rd = null;

		if (appDto != null) {

			PrintWriter out = response.getWriter();
			out.println("<html>");
			out.println(
					"<form action='./controller/updateRecord' method='post' style='width: 400px; margin: 20px auto; padding: 20px; border: 1px solid #ccc; box-shadow: 2px 2px 12px rgba(0,0,0,0.1);'>");
			out.println("<table style='width: 100%; border-collapse: collapse;'>");

			out.println("<tr>");
			out.println("<th style='text-align: left; padding: 10px;'>SID</th>");
			out.println("<td style='padding: 10px;'><input type='text' name='sid' value='" + appDto.getSid()
					+ "' readonly style='width: 100%; padding: 5px;'></td>");
			out.println("</tr>");

			out.println("<tr>");
			out.println("<th style='text-align: left; padding: 10px;'>Student Name</th>");
			out.println("<td style='padding: 10px;'><input type='text' name='sname' value='" + appDto.getSname()
					+ "' style='width: 100%; padding: 5px;'></td>");
			out.println("</tr>");

			out.println("<tr>");
			out.println("<th style='text-align: left; padding: 10px;'>Student Age</th>");
			out.println("<td style='padding: 10px;'><input type='number' name='sage' value='" + appDto.getSage()
					+ "' style='width: 100%; padding: 5px;'></td>");
			out.println("</tr>");

			out.println("<tr>");
			out.println("<th style='text-align: left; padding: 10px;'>Student Address</th>");
			out.println("<td style='padding: 10px;'><input type='text' name='saddress' value='" + appDto.getSaddress()
					+ "' style='width: 100%; padding: 5px;'></td>");
			out.println("</tr>");

			out.println("</table>");

			out.println("<div style='text-align: center; margin-top: 20px;'>");
			out.println(
					"<input type='submit' value='Update' style='padding: 10px 20px; background-color: #4CAF50; color: white; border: none; cursor: pointer;'>");
			out.println("</div>");

			out.println("</form>");
			out.println("</html>");

		} else {
			rd = request.getRequestDispatcher("../failure.html");
			rd.forward(request, response);
		}

	}

	private void deleteData(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		CrudAppDto crudDto = new CrudAppDto();

		String sid = request.getParameter("id");
		crudDto.setSid(Integer.parseInt(sid));

		String status = service.deleteRecord(Integer.parseInt(sid));
		RequestDispatcher rd = null;

		if (status.equalsIgnoreCase("success")) {
			rd = request.getRequestDispatcher("../success.html");
			rd.forward(request, response);
		} else {
			rd = request.getRequestDispatcher("../failure.html");
			rd.forward(request, response);
		}

	}

	private void insertData(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String sname = request.getParameter("sname");
		String sage = request.getParameter("sage");
		String saddress = request.getParameter("saddress");

		CrudAppDto crudDto = new CrudAppDto();

		crudDto.setSname(sname);
		crudDto.setSage(Integer.parseInt(sage));
		crudDto.setSaddress(saddress);

		String status = service.insertRecord(crudDto);
		RequestDispatcher rd = null;

		if (status.equalsIgnoreCase("success")) {
			rd = request.getRequestDispatcher("../success.html");
			rd.forward(request, response);
		} else {
			rd = request.getRequestDispatcher("../failure.html");
			rd.forward(request, response);
		}
	}

	private void viewData(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		CrudAppDto crudDto = new CrudAppDto();

		String sid = request.getParameter("id");
		crudDto.setSid(Integer.parseInt(sid));

		CrudAppDto record = service.readRecord(crudDto.getSid());
		PrintWriter out = response.getWriter();

		if (record != null) {
			out.println("<style>");
			out.println("table {");
			out.println("    width: 80%;");
			out.println("    border-collapse: collapse;");
			out.println("    margin: 20px auto;");
			out.println("    box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);");
			out.println("}");
			out.println("th, td {");
			out.println("    padding: 12px;");
			out.println("    text-align: left;");
			out.println("    border-bottom: 1px solid #ddd;");
			out.println("}");
			out.println("th {");
			out.println("    background-color: #4CAF50;");
			out.println("    color: white;");
			out.println("}");
			out.println("tr:hover {");
			out.println("    background-color: #f1f1f1;");
			out.println("}");
			out.println(".no-record {");
			out.println("    text-align: center;");
			out.println("    font-style: italic;");
			out.println("    color: #999;");
			out.println("}");
			out.println("</style>");

			out.println("<table>");
			out.println("    <tr><th>Field</th><th>Value</th></tr>");

			out.println("    <tr><td>Sid</td><td>" + record.getSid() + "</td></tr>");
			out.println("    <tr><td>Sname</td><td>" + record.getSname() + "</td></tr>");
			out.println("    <tr><td>Sage</td><td>" + record.getSage() + "</td></tr>");
			out.println("    <tr><td>Saddress</td><td>" + record.getSaddress() + "</td></tr>");
		} else {
			RequestDispatcher rd = request.getRequestDispatcher("../failure.html");
			rd.forward(request, response);
		}

		out.println("</table>");
	}
}