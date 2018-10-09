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
			// ���� ��ư ���ý�
			if(event.getSource() == view.getReg()) {
				try {
					// DBMS ����
					db.connect();
					// DB�� ����� id ����
					db.read("select id from account;");
					boolean overlap = false;	// id�� �ߺ������ Ȯ��
					
					// DB���� ������ id�� �ϳ��� �ݺ������� �Ѱܼ� id_temp�� ���� �Ŀ� ���� ������
					// DB�� �̹� ������ ID�� ������ִٴ� ���̹Ƿ� overlap = true�� �Ѵ�.
					while(db.rs.next()) {
						String sqlRecipeProcess = db.rs.getString("id");
						if(view.getID().equals(sqlRecipeProcess)) {
							overlap = true;
						}
					}
					
					// ID�� �ߺ����� �ʾ�����
					if(overlap == false) {
						// DB�� id_temp�� pw_temp�� ���� �����Ѵ�.
						db.st.executeUpdate("insert into account values('" + view.getID() + "','" + view.getPW() + "',0);");  
						JOptionPane.showMessageDialog(null, "ȸ������ �Ǿ����ϴ�");
						// ������ ����
						view.getN().dispose();
					// ID�� �ߺ�������
					} else {
						JOptionPane.showMessageDialog(null, "�ߺ��� ID�Դϴ�");
						// ȸ������ â���� ID�� PWĭ�� ����ش�
						view.resetText();
					}
					
					// DBMS ���� ����
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
				
			// ��� ��ư ���ý�
			} else {
				// ȸ������ â ����
				view.getN().dispose();
			}
		}
	}
	
	// ������ �޼ҵ�
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
