package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDao;
import model.UserBean;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		String forwardPath = null;
		String action = request.getParameter("action");
		String s_id = request.getParameter("id");

		// ユーザー管理ページに遷移し、リクエストパラメータactionによって
		// ユーザー新規登録ページ、ユーザー編集ページ、ユーザー削除完了ページのいずれかに遷移する
		if(action == null) {
			UserDao ud = new UserDao();
			List<UserBean> list = ud.findAll();
			request.setAttribute("list", list);
			forwardPath = "/WEB-INF/user/list.jsp";
		} else if(action.equals("new")) {
			forwardPath = "/WEB-INF/user/new.jsp";
		} else if(action.equals("update")) {
			
			// リクエストパラメータs_id(ユーザーID)によって、データベースからユーザーを見つけ、
			// そのユーザー情報をリクエストスコープに保存し、ユーザー編集ページに遷移する
			if(s_id != null) {
				UserDao ud = new UserDao();
				UserBean ub = ud.findOne(Integer.parseInt(s_id));
				request.setAttribute("ub", ub);
				forwardPath = "/WEB-INF/user/edit.jsp";
			} 
			
			// ユーザーを見つけられなかった場合は、エラーページへ遷移する
			else {
				forwardPath = "/WEB-INF/user/findOne_faile.jsp";
			}
		} else if(action.equals("delete")) {
			
			// リクエストパラメータs_id(ユーザーID)によって、データベースから
			// 指定のユーザーを削除し、ユーザー削除完了ページに遷移する
			if(s_id != null) {
				UserDao ud = new UserDao();
				ud.deleteUser(Integer.parseInt(s_id));
				forwardPath = "/WEB-INF/user/delete_success.jsp";
			} 
			
			// ユーザーを見つけられなかった場合は、エラーページに遷移する
			else {
				forwardPath = "/WEB-INF/user/delete_faile.jsp";
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

		String login = request.getParameter("login");
		String lvl = request.getParameter("lvl");
		String name = request.getParameter("name");
		String pwd = request.getParameter("pwd");

		UserBean ub = new UserBean();
		ub.setLogin(login);
		ub.setLvl(Integer.parseInt(lvl));
		ub.setName(name);
		ub.setPwd(pwd);

		UserDao ud = new UserDao();

		// リクエストパラメータactionによって、ユーザー新規登録完了ページ、
		// もしくはユーザー編集完了ページに遷移する
		if(action == null) {
			ud.insertUser(ub);
			forwardPath = "/WEB-INF/user/add_success.jsp";
		} else if(action.equals("update")) {
			
			// リクエストパラメータs_id(ユーザーID)によって、データベースから
			// 指定のユーザーを編集し、ユーザー編集完了ページに遷移する
			if(s_id != null) {
				ub.setId(Integer.parseInt(s_id));
				ud.updateUser(ub);
				forwardPath = "/WEB-INF/user/update_success.jsp";
			} 
			
			// ユーザーを見つけられなかった場合は、エラーページに遷移する
			else {
				forwardPath = "/WEB-INF/user/upadate_faile.jsp";
			}
		}

		RequestDispatcher rd =
				request.getRequestDispatcher(forwardPath);
		rd.forward(request, response);
	}

}
