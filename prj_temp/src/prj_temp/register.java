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
	private DB db;		// DB ��ü
	
	
	private class ButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent event){
			// DB������ try-catch������ ����ó���� �׻� ������Ѵ�.
			try {
				// DB ����
				db.connect();
				
				// ȸ������ â���� �Է��� ID�� PW�� �ӽ������� ����
				String id_temp = id.getText();
				String pw_temp = pw.getText();
				// ID�� �ߺ��Ǿ����� true
				boolean overlap = false;
				
				// ���� ��ư�� ��������
				if(event.getSource() == reg) {
					
					// DBMS�� "account ���̺��� id �÷��� �����͸� �����ض�"��� ��ɾ ���� �� ������� rs�� ����
					db.rs = db.st.executeQuery("select id from account;");
					
					// DB���� ������ id�� �ϳ��� �ݺ������� �Ѱܼ� id_temp�� ���� �Ŀ� ���� ������
					// DB�� �̹� ������ ID�� ������ִٴ� ���̹Ƿ� overlap = true�� �Ѵ�.
					while(db.rs.next()) {
						String sqlRecipeProcess = db.rs.getString("id");
						if(id_temp.equals(sqlRecipeProcess)) {
							overlap = true;
						}
					}
					
					// ID�� �ߺ����� �ʾ�����
					if(overlap == false) {
						// DB�� id_temp�� pw_temp�� ���� �����Ѵ�.
						db.st.executeUpdate("insert into account values('" + id_temp + "','" + pw_temp + "',0);");  
						JOptionPane.showMessageDialog(null, "ȸ������ �Ǿ����ϴ�");
						// ������ ����
						n.dispose();
					// ID�� �ߺ�������
					} else {
						JOptionPane.showMessageDialog(null, "�ߺ��� ID�Դϴ�");
						// ȸ������ â���� ID�� PWĭ�� ����ش�
						id.setText("");
						pw.setText("");
					}
				
				// ��ҹ�ư�� ��������
				} else {
					// ȸ������ â ����
					n.dispose();
				}
				// DB������ ������ rs, st, connection�� �ݾ��־���Ѵ�.
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
		n = new Frame("ȸ�� ����");
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
		reg = new JButton("����");
		cancel = new JButton("���");
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
