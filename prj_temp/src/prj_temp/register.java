package prj_temp;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;


public class register extends JPanel{
	private JPanel p_t, p_b, l1, l2;
	private JTextField id, pw;
	private JLabel l_id, l_pw;
	private JButton reg, cancel;
	private Frame n;
	private DB db;		// DB 객체
	
	
	private class ButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent event){
			// DB연동은 try-catch문으로 예외처리를 항상 해줘야한다.
			try {
				// DB 연결
				db.connect();
				
				// 회원가입 창에서 입력한 ID와 PW를 임시적으로 저장
				String id_temp = id.getText();
				String pw_temp = pw.getText();
				// ID가 중복되었으면 true
				boolean overlap = false;
				
				// 가입 버튼을 눌렀을때
				if(event.getSource() == reg) {
					
					// DBMS에 "account 테이블에서 id 컬럼의 데이터를 추출해라"라는 명령어를 실행 후 결과값을 rs에 저장
					db.rs = db.st.executeQuery("select id from account;");
					
					// DB에서 가져온 id를 하나씩 반복문으로 넘겨서 id_temp와 비교한 후에 값이 같으면
					// DB에 이미 동일한 ID가 저장되있다는 뜻이므로 overlap = true로 한다.
					while(db.rs.next()) {
						String sqlRecipeProcess = db.rs.getString("id");
						if(id_temp.equals(sqlRecipeProcess)) {
							overlap = true;
						}
					}
					
					// ID가 중복되지 않았으면
					if(overlap == false) {
						// DB에 id_temp와 pw_temp의 값을 저장한다.
						db.st.executeUpdate("insert into account values('" + id_temp + "','" + pw_temp + "',0);");  
						JOptionPane.showMessageDialog(null, "회원가입 되었습니다");
						// 프레임 종료
						n.dispose();
					// ID가 중복됬으면
					} else {
						JOptionPane.showMessageDialog(null, "중복된 ID입니다");
						// 회원가입 창에서 ID와 PW칸을 비워준다
						id.setText("");
						pw.setText("");
					}
				
				// 취소버튼을 눌렀을때
				} else {
					// 회원가입 창 종료
					n.dispose();
				}
				// DB연동이 끝나면 rs, st, connection을 닫아주어야한다.
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
	
	public register() {
		n = new Frame("회원 가입");
		n.setSize(330,150);
		
		n.add(this);
		
		
		n.setLocation(400, 100);
		
		setLayout(new GridLayout(2,1));
		
		l1 = new JPanel();
	    l2 = new JPanel();
	    l1.setLayout(new FlowLayout(FlowLayout.CENTER));
	    l2.setLayout(new FlowLayout(FlowLayout.CENTER));
	       
		p_t = new JPanel();
		p_b = new JPanel();
		l_id = new JLabel("ID");
		l_pw = new JLabel("PW");
		id = new JTextField(20);
		pw = new JTextField(20);
		reg = new JButton("가입");
		cancel = new JButton("취소");
		p_b.setLayout(new GridLayout(1,2));
		p_t.setLayout(new GridLayout(2,2));
		l1.add(l_id);
		p_t.add(l1);
		p_t.add(id);
		l2.add(l_pw);
		p_t.add(l2);
		p_t.add(pw);
		p_b.add(reg);
		p_b.add(cancel);
		add(p_t);
		add(p_b);
		n.setVisible(true);
		
		db = new DB();
		
		
		
		ButtonListener listen = new ButtonListener();
		reg.addActionListener(listen);
		cancel.addActionListener(listen);
		
	}
}
