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

import model.EmployeeBean;

public class EmployeeDao {

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

	// 社員情報表示
	public List<EmployeeBean> findAll() {
		List<EmployeeBean> list = new ArrayList<>();
		try {
			this.connect();
			ps = db.prepareStatement("SELECT * FROM employee");
			rs = ps.executeQuery();
			while(rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String zip = rs.getString("zip");
				String address1 = rs.getString("address1");
				String address2 = rs.getString("address2");
				String tel = rs.getString("tel");
				String fax = rs.getString("fax");
				String email = rs.getString("email");
				String birthday = rs.getString("birthday");
				String dpt = rs.getString("dpt");
				String post = rs.getString("post");

				EmployeeBean eb = new EmployeeBean();
				eb.setId(id);
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
				list.add(eb);
			}
		}catch (NamingException | SQLException e) {
			e.printStackTrace();
			return null;
		}finally{
			this.disconnect();
		}
		return list;
	}

	// 社員情報新規登録
	public void insertEmployee(EmployeeBean eb) {
		try {
			this.connect();
			ps = db.prepareStatement("INSERT INTO employee"
					+ "(name,zip,address1,address2,tel,fax,email,birthday,dpt,post)VALUES(?,?,?,?,?,?,?,?,?,?)");
			ps.setString(1, eb.getName());
			ps.setString(2, eb.getZip());
			ps.setString(3, eb.getAddress1());
			ps.setString(4, eb.getAddress2());
			ps.setString(5, eb.getTel());
			ps.setString(6, eb.getFax());
			ps.setString(7, eb.getEmail());
			ps.setString(8, eb.getBirthday());
			ps.setString(9, eb.getDpt());
			ps.setString(10, eb.getPost());
			ps.executeUpdate();
		} catch (NamingException | SQLException e) {  
			e.printStackTrace();
		}finally{
			this.disconnect();
		}
	}

	// 社員情報検索
	public EmployeeBean findOne(int id){
		EmployeeBean eb = null;
		try {
			this.connect();
			ps = db.prepareStatement("SELECT * FROM employee WHERE id=?");
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if(rs.next()){
				String name = rs.getString("name");
				String zip = rs.getString("zip");
				String address1 = rs.getString("address1");
				String address2 = rs.getString("address2");
				String tel = rs.getString("tel");
				String fax = rs.getString("fax");
				String email = rs.getString("email");
				String birthday = rs.getString("birthday");
				String dpt = rs.getString("dpt");
				String post = rs.getString("post");

				eb = new EmployeeBean();
				eb.setId(id);
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
			}
		} catch (NamingException | SQLException e) {
			e.printStackTrace();
		}finally{
			this.disconnect();
		}
		return eb;
	}

	// 社員情報編集
	public void updateEmployee(EmployeeBean eb){
		try {
			this.connect();
			ps = db.prepareStatement("UPDATE employee SET name=?,zip=?,address1=?,address2=?,"
					+ "tel=?,fax=?,email=?,birthday=?,dpt=?,post=? WHERE id=?");
			ps.setString(1, eb.getName());
			ps.setString(2, eb.getZip());
			ps.setString(3, eb.getAddress1());
			ps.setString(4, eb.getAddress2());
			ps.setString(5, eb.getTel());
			ps.setString(6, eb.getFax());
			ps.setString(7, eb.getEmail());
			ps.setString(8, eb.getBirthday());
			ps.setString(9, eb.getDpt());
			ps.setString(10, eb.getPost());
			ps.setInt(11, eb.getId());
			ps.executeUpdate();
		} catch (NamingException | SQLException e) {
			e.printStackTrace();
		}finally{
			this.disconnect();
		}
	}

	// 社員情報削除
	public void deleteEmployee(int id){
		try {
			this.connect();
			ps = db.prepareStatement("DELETE FROM employee WHERE id=?");
			ps.setInt(1, id);
			ps.executeUpdate();
		} catch (NamingException | SQLException e) {
			e.printStackTrace();
		}finally{
			this.disconnect();
		}
	}

}
