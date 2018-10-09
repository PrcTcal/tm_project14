package Control;
import Starter.Application;
import View.LoginView;
import View.RegisterView;
import Model.DB;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
public class RegisterControler {
	private RegisterView view;
	private ButtonListener listen;
	private DB db;
	
	private class ButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent event){
			// 가입 버튼 선택시
			if(event.getSource() == view.getReg()) {
				try {
					// DBMS 연결
					db.connect();
					// DB에 저장된 id 추출
					db.read("select id from account;");
					boolean overlap = false;	// id가 중복됬는지 확인
					
					// DB에서 가져온 id를 하나씩 반복문으로 넘겨서 id_temp와 비교한 후에 값이 같으면
					// DB에 이미 동일한 ID가 저장되있다는 뜻이므로 overlap = true로 한다.
					while(db.rs.next()) {
						String sqlRecipeProcess = db.rs.getString("id");
						if(view.getID().equals(sqlRecipeProcess)) {
							overlap = true;
						}
					}
					
					// ID가 중복되지 않았으면
					if(overlap == false) {
						// DB에 id_temp와 pw_temp의 값을 저장한다.
						db.st.executeUpdate("insert into account values('" + view.getID() + "','" + view.getPW() + "',0);");  
						JOptionPane.showMessageDialog(null, "회원가입 되었습니다");
						// 프레임 종료
						view.getN().dispose();
					// ID가 중복됬으면
					} else {
						JOptionPane.showMessageDialog(null, "중복된 ID입니다");
						// 회원가입 창에서 ID와 PW칸을 비워준다
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
				
			// 취소 버튼 선택시
			} else {
				// 회원가입 창 종료
				view.getN().dispose();
			}
		}
	}
	
	// 생성자 메소드
	public RegisterControler(RegisterView v) {
		db = new DB();
		view = v;
	}
	
	public void buttonHandler() {
		listen = new ButtonListener();
		view.getReg().addActionListener(listen);
		view.getCancel().addActionListener(listen);
	}
}
