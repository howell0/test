package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.EmployeeDao;
import model.EmployeeBean;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/EmployeeServlet")
public class EmployeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		String forwardPath = null;
		String action = request.getParameter("action");
		String s_id = request.getParameter("id");
		String s_lvl = request.getParameter("lvl");

		// 社員管理ページに遷移し、リクエストパラメータactionによって
		// 社員新規登録ページ、社員編集ページ、社員削除完了ページのいずれかに遷移する
		if(action == null) {
			EmployeeDao ed = new EmployeeDao();
			List<EmployeeBean> list = ed.findAll();
			request.setAttribute("list", list);
			
			// リクエストパラメータs_lvl(ユーザーレベル)によって
			// システム管理者、管理職ページ、もしくは一般ユーザーページに遷移する
			if(Integer.parseInt(s_lvl) >= 1) {
				forwardPath = "/WEB-INF/employee/list_lvl1and2.jsp";
			}else {
				forwardPath = "/WEB-INF/employee/list_lvl0.jsp";
			}
		} else if(action.equals("new")) {
			forwardPath = "/WEB-INF/employee/new.jsp";
		} else if(action.equals("update")) {
			
			// リクエストパラメータs_id(社員ID)によって、データベースから社員を見つけ、
			// その社員情報をリクエストスコープに保存し、社員編集ページに遷移する
			if(s_id != null) {
				EmployeeDao ed = new EmployeeDao();
				EmployeeBean eb = ed.findOne(Integer.parseInt(s_id));
				request.setAttribute("eb", eb);
				forwardPath = "/WEB-INF/employee/edit.jsp";
			}
			
			// 社員を見つけられなかった場合は、エラーページへ遷移する
			else {
				forwardPath = "/WEB-INF/employee/findOne_faile.jsp";
			}
		} else if(action.equals("delete")) {
			
			// リクエストパラメータs_id(社員ID)によって、データベースから
			// 指定の社員を削除し、社員削除完了ページに遷移する
			if(s_id != null) {
				EmployeeDao ed = new EmployeeDao();
				ed.deleteEmployee(Integer.parseInt(s_id));
				forwardPath = "/WEB-INF/employee/delete_success.jsp";
			} 
			
			// 社員を見つけられなかった場合は、エラーページに遷移する
			else {
				forwardPath = "/WEB-INF/employee/delete_faile.jsp";
			}
		}

		RequestDispatcher rd =
				request.getRequestDispatcher(forwardPath);
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		String forwardPath = null;
		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");
		String s_id = request.getParameter("id");

		String name = request.getParameter("name");
		String zip = request.getParameter("zip");
		String address1 = request.getParameter("address1");
		String address2 = request.getParameter("address2");
		String tel = request.getParameter("tel");
		String fax = request.getParameter("fax");
		String email = request.getParameter("email");
		String birthday = request.getParameter("birthday");
		String dpt = request.getParameter("dpt");
		String post = request.getParameter("post");

		EmployeeBean eb = new EmployeeBean();
		eb.setName(name);
		eb.setZip(zip);
		eb.setAddress1(address1);
		eb.setAddress2(address2);
		eb.setTel(tel);
		eb.setFax(fax);
		eb.setEmail(email);
		eb.setBirthday(birthday);
		eb.setDpt(dpt);
		eb.setPost(post);

		EmployeeDao ed = new EmployeeDao();

		// リクエストパラメータactionによって、社員新規登録完了ページ、
		// もしくは社員編集完了ページに遷移する
		if(action == null) {
			ed.insertEmployee(eb);
			forwardPath = "/WEB-INF/employee/add_success.jsp";
		} else if(action.equals("update")) {
			
			// リクエストパラメータs_id(社員ID)によって、データベースから
			// 指定の社員を編集し、社員編集完了ページに遷移する
			if(s_id != null) {
				eb.setId(Integer.parseInt(s_id));
				ed.updateEmployee(eb);
				forwardPath = "/WEB-INF/employee/update_success.jsp";
			} 
			
			// 社員を見つけられなかった場合は、エラーページに遷移する
			else {
				forwardPath = "/WEB-INF/employee/upadate_faile.jsp";
			}
		}

		RequestDispatcher rd =
				request.getRequestDispatcher(forwardPath);
		rd.forward(request, response);
	}

}
