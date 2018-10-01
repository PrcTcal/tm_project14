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
				// DB ����
				db.connect();
				// �Է��� ID�� PW�� ������ true
				boolean id_check = false;
				boolean pw_check = false;
				
				// �α��� ��ư�� ��������
				if(event.getSource() == login) {
					
					// DBMS�� "account ���̺��� id�� pw�� �����ؿͶ�"��� ��ɾ �����ϰ� ������� rs�� ����
					db.rs = db.st.executeQuery("select id,pw from account;");
					
					// DB���� ������ id�� pw�� �ϳ��� �����ͼ� �Է��� ID, PW�� ������ boolean ������ true�� ����
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
					
					// ID�� PW�� ��ġ�ϸ�
					if(id_check == true && pw_check == true) {
						JOptionPane.showMessageDialog(null, "�α��� �Ǿ����ϴ�");
						F.changePanel();
						F.setTitle("����");
						F.setSize(800,600);
					// ID�� �´µ� PW�� Ʋ����
					} else if(id_check == true && pw_check == false) {
						JOptionPane.showMessageDialog(null, "��й�ȣ�� Ʋ�Ƚ��ϴ�");
						pw.setText("");
					// ID�� Ʋ����
					} else {
						JOptionPane.showMessageDialog(null, "���� ������ �ùٸ��� �ʽ��ϴ�");
						id.setText("");
						pw.setText("");
					}
				// ȸ������ ��ư�� ��������
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
		login = new JButton("�α���");
		register = new JButton("ȸ������");
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
