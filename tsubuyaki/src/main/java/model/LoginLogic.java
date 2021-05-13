package model;

public class LoginLogic {

	public boolean execute(User user) {
		// デフォルト文では名前を入力しなくても「ようこそさん」で
		// ログインできてしまう為、修正
		/*
		if(user.getPass() .equals("1234")) {
			return true;
		}
		return false;
		*/
		
		String n = user.getName();
		String p = user.getPass();
		
		if(n == null || n.length() == 0) {
			return false;
		}
		if(p == null || p.length() == 0) {
			return false;
		}
		if(p .equals("1")) {
			return true;
		}
		return false;
		
	}
}
