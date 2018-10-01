package prj_temp;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;


public class LogIn extends JPanel{
	private JPanel p_t, p_b, mid1, mid2;
	private JButton login, register;
	private JLabel id_l, pw_l;
	private JTextField id, pw;
	private start F;
	private register rg;
	private DB db;
	
	private class ButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent event){
			try {
				// DB 연결
				db.connect();
				// 입력한 ID와 PW가 맞으면 true
				boolean id_check = false;
				boolean pw_check = false;
				
				// 로그인 버튼이 눌렸을때
				if(event.getSource() == login) {
					
					// DBMS에 "account 테이블에서 id랑 pw를 추출해와라"라는 명령어를 실행하고 결과값을 rs에 저장
					db.rs = db.st.executeQuery("select id,pw from account;");
					
					// DB에서 가져온 id와 pw를 하나씩 가져와서 입력한 ID, PW와 같으면 boolean 변수를 true로 설정
					while(db.rs.next()) {
						String sqlRecipeProcess_id = db.rs.getString("id");
						String sqlRecipeProcess_pw = db.rs.getString("pw");

						if(id.getText().equals(sqlRecipeProcess_id)) {
							id_check = true;
							if(pw.getText().equals(sqlRecipeProcess_pw)) {
								pw_check = true;
							}
						}
					}
					
					// ID와 PW가 일치하면
					if(id_check == true && pw_check == true) {
						JOptionPane.showMessageDialog(null, "로그인 되었습니다");
						F.changePanel();
						F.setTitle("대기실");
						F.setSize(800,600);
					// ID는 맞는데 PW가 틀리면
					} else if(id_check == true && pw_check == false) {
						JOptionPane.showMessageDialog(null, "비밀번호가 틀렸습니다");
						pw.setText("");
					// ID가 틀리면
					} else {
						JOptionPane.showMessageDialog(null, "계정 정보가 올바르지 않습니다");
						id.setText("");
						pw.setText("");
					}
				// 회원가입 버튼을 눌렀을때
				} else {
					rg = new register();
				}
				
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
		}
	}
	
	public LogIn(start f) {
		setSize(330, 150);
        setLayout(new GridLayout(2,1));
         
        F = f;
        mid1 = new JPanel();
        mid2 = new JPanel();
        mid1.setLayout(new FlowLayout(FlowLayout.CENTER));
        mid2.setLayout(new FlowLayout(FlowLayout.CENTER));
        
		p_t = new JPanel();
		p_b = new JPanel();
		id_l = new JLabel("ID");
		pw_l = new JLabel("PW");
		id = new JTextField(20);
		pw = new JTextField(20);
		login = new JButton("로그인");
		register = new JButton("회원가입");
		p_b.setLayout(new GridLayout(1,2));
		p_t.setLayout(new GridLayout(2,2));
		mid1.add(id_l);
		p_t.add(mid1);
		p_t.add(id);
		mid2.add(pw_l);
		p_t.add(mid2);
		p_t.add(pw);
		p_b.add(login);
		p_b.add(register);
		
		add(p_t);
		add(p_b);
		
		db = new DB();
		
		ButtonListener listen = new ButtonListener();
		login.addActionListener(listen);
		register.addActionListener(listen);

		setVisible(true);
		
		
	}

}
