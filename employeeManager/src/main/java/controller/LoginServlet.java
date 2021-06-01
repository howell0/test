package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UserDao;
import model.UserBean;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		String forwardPath = null;
		String action = request.getParameter("action");
		String s_lvl = request.getParameter("lvl");

		// リクエストパラメータactionによってログインページ、
		// もしくはメニューページに遷移する
		if(action == null) {
			forwardPath = "/WEB-INF/login/login_form.jsp";
		} 
		
		// リクエストパラメータs_lvl(ユーザーレベル)によって
		// システム管理者ページ、もしくは管理職、一般ユーザーページに遷移する
		else if(action.equals("menu")) {
			if(Integer.parseInt(s_lvl) == 2) {
				forwardPath = "/WEB-INF/login/menu_lvl2.jsp";
			}else {
				forwardPath = "/WEB-INF/login/menu_lvl0and1.jsp";
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
		String login = request.getParameter("login");
		String pwd = request.getParameter("pwd");

		UserBean ub = new UserBean();
		ub.setLogin(login);
		ub.setPwd(pwd);

		// ログインページで入力されたログインIDとパスワードで
		// データベースからユーザーを見つけ、そのユーザー情報を取得する
		UserDao ud = new UserDao();
		UserBean returnUb = ud.findUser(ub);

		// 取得したユーザー情報をセッションスコープに保存し、ユーザーレベルによって
		// システム管理者ページ、もしくは管理職、一般ユーザーページに遷移する
		if(returnUb != null) {
			HttpSession session = request.getSession();
			session.setAttribute("user", returnUb);
			if(returnUb.getLvl() == 2) {
				forwardPath = "/WEB-INF/login/menu_lvl2.jsp";
			}else {
				forwardPath = "/WEB-INF/login/menu_lvl0and1.jsp";
			}
		} 
		
		// ログインページで入力されたログインIDとパスワードで、データベースから
		// ユーザーを見つけられなかった場合は、エラーページに遷移する
		else {
			forwardPath = "/WEB-INF/login/login_error.jsp";
		}

		RequestDispatcher rd =
				request.getRequestDispatcher(forwardPath);
		rd.forward(request, response);
	}

}
