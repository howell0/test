package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import model.UserBean;

public class UserDao {

	private Connection db;
	private PreparedStatement ps;
	private ResultSet rs;

	//接続共通処理
	private void connect() throws NamingException, SQLException {
		Context context = new InitialContext();
		DataSource ds = (DataSource) context.lookup("java:comp/env/jdbc/employeemanager");
		this.db = ds.getConnection();
	}
	//切断共通処理
	private void disconnect() {
		try {
			if (rs != null) {
				rs.close();
			}
			if (ps != null) {
				ps.close();
			}
			if (db != null) {
				db.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// ユーザーログイン
	public UserBean findUser(UserBean ub){
		UserBean returnUb = new UserBean();
		try {
			this.connect();
			ps = db.prepareStatement("SELECT * FROM login_user WHERE login= ? AND pwd = ?");
			ps.setString(1, ub.getLogin());
			ps.setString(2, ub.getPwd());

			rs = ps.executeQuery();


			if (rs.next()) {
				// 見つかったアカウント情報を戻り値にセット
				returnUb.setId(rs.getInt("id"));
				returnUb.setLogin(rs.getString("login"));
				returnUb.setLvl(rs.getInt("lvl"));
				returnUb.setName(rs.getString("name"));
				returnUb.setPwd(rs.getString("pwd"));
			} else {
				// アカウントがなければnullを返す
				return null;
			}
		} catch (NamingException | SQLException e) {
			e.printStackTrace();
			return null;
		}finally{
			this.disconnect();
		}
		return returnUb;
	}

	// ユーザー情報表示
	public List<UserBean> findAll() {
		List<UserBean> list = new ArrayList<>();
		try {
			this.connect();
			ps = db.prepareStatement("SELECT * FROM login_user");
			rs = ps.executeQuery();
			while(rs.next()) {
				int id = rs.getInt("id");
				String login = rs.getString("login");
				String name = rs.getString("name");
				int lvl = rs.getInt("lvl");
				String pwd = rs.getString("pwd");

				UserBean ub = new UserBean();
				ub.setId(id);
				ub.setLogin(login);
				ub.setLvl(lvl);
				ub.setName(name);
				ub.setPwd(pwd);
				list.add(ub);
			}
		}catch (NamingException | SQLException e) {
			e.printStackTrace();
			return null;
		}finally{
			this.disconnect();
		}
		return list;
	}

	// ユーザー情報新規登録
	public void insertUser(UserBean ub) {
		try {
			this.connect();
			ps = db.prepareStatement("INSERT INTO login_user(login,lvl,name,pwd) VALUES(?,?,?,?)");
			ps.setString(1, ub.getLogin());
			ps.setInt(2, ub.getLvl());
			ps.setString(3, ub.getName());
			ps.setString(4, ub.getPwd());
			ps.executeUpdate();
		} catch (NamingException | SQLException e) {  
			e.printStackTrace();
		}finally{
			this.disconnect();
		}
	}

	// ユーザー情報検索
	public UserBean findOne(int id){
		UserBean ub = null;
		try {
			this.connect();
			ps = db.prepareStatement("SELECT * FROM login_user WHERE id=?");
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if(rs.next()){
				String login = rs.getString("login");
				int lvl = rs.getInt("lvl");
				String name = rs.getString("name");
				String pwd = rs.getString("pwd");

				ub = new UserBean();
				ub.setId(id);
				ub.setLogin(login);
				ub.setLvl(lvl);
				ub.setName(name);
				ub.setPwd(pwd);
			}
		} catch (NamingException | SQLException e) {
			e.printStackTrace();
		}finally{
			this.disconnect();
		}
		return ub;
	}

	// ユーザー情報編集
	public void updateUser(UserBean ub){
		try {
			this.connect();
			ps = db.prepareStatement("UPDATE login_user SET login=?,lvl=?,name=?,pwd=? WHERE id=?");
			ps.setString(1, ub.getLogin());
			ps.setInt(2,ub.getLvl() );
			ps.setString(3, ub.getName());
			ps.setString(4, ub.getPwd());
			ps.setInt(5, ub.getId());
			ps.executeUpdate();
		} catch (NamingException | SQLException e) {
			e.printStackTrace();
		}finally{
			this.disconnect();
		}
	}

	// ユーザー情報削除
	public void deleteUser(int id){
		try {
			this.connect();
			ps = db.prepareStatement("DELETE FROM login_user WHERE id=?");
			ps.setInt(1, id);
			ps.executeUpdate();
		} catch (NamingException | SQLException e) {
			e.printStackTrace();
		}finally{
			this.disconnect();
		}
	}

}
