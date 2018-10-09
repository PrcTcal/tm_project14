package Control;
import Starter.Application;
import Model.DB;
import View.LoginView;
import View.RegisterView;
import Control.RegisterControler;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
public class LoginControler {
	private Application app;
	//private Login model;
	private LoginView view;
	private ButtonListener listen;
	private RegisterView rv;
	private RegisterControler rc;
	private DB db;
	
	
	private class ButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent event){
			
			// 로그인 버튼 선택시
			if(event.getSource() == view.getLogin()) {
				try {
					// DBMS 연결
					db.connect();
					boolean id_check = false;	// id가 맞는지 여부
					boolean pw_check = false;	// pw가 맞는지 여부

					// DBMS에 "account 테이블에서 id랑 pw를 추출해와라"라는 명령어를 실행하고 결과값을 rs에 저장
					db.read("select id,pw from account;");

					// DB에서 가져온 id와 pw를 하나씩 가져와서 입력한 ID, PW와 같으면 boolean 변수를 true로 설정
					while(db.rs.next()) {
						String sqlRecipeProcess_id = db.rs.getString("id");
						String sqlRecipeProcess_pw = db.rs.getString("pw");

						if(view.getID().equals(sqlRecipeProcess_id)) {
							id_check = true;
							if(view.getPW().equals(sqlRecipeProcess_pw)) {
								pw_check = true;
							}
						}
					}

					// ID와 PW가 일치하면
					if(id_check == true && pw_check == true) {
						JOptionPane.showMessageDialog(null, "로그인 되었습니다");
						app.changePanel();
						app.setSize(800,600);
						app.setTitle("대기실");
						app.setSize(800,600);
						// ID는 맞는데 PW가 틀리면
					} else if(id_check == true && pw_check == false) {
						JOptionPane.showMessageDialog(null, "비밀번호가 틀렸습니다");
						view.resetPW();
						// ID가 틀리면
					} else {
						JOptionPane.showMessageDialog(null, "계정 정보가 올바르지 않습니다");
						view.resetText();
					}
					
					// DBMS 연결 종료
					db.rs.close();
					db.st.close();
					db.connection.close();
				} catch (SQLException se1) {
					se1.printStackTrace();
				} catch (Exception ex) {
					ex.printStackTrace();
				} finally {
					try {
						if (db.st != null)
							db.st.close();
					} catch (SQLException se2) {
					}
					try {
						if (db.connection != null)
							db.connection.close();
					} catch (SQLException se) {
						se.printStackTrace();
					}
				} 
			// 회원가입 버튼 선택	
			} else {
				// 회원가입 창 생성
				rv = new RegisterView();
				// 회원가입 Controler 연결
				rc = new RegisterControler(rv);
				rc.buttonHandler();
			}
		}
	}
	
	// 생성자 메소드
	public LoginControler(Application app, LoginView v, RegisterView rv, RegisterControler rc) {
		db = new DB();
		this.app = app;
		//model = m;
		view = v;
		this.rv = rv;
		this.rc = rc;
	}
	
	
	public void buttonHandler() {
		listen = new ButtonListener();
		view.getLogin().addActionListener(listen);
		view.getRegister().addActionListener(listen);
	}
	
	
}
