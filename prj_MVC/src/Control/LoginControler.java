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
			
			// �α��� ��ư ���ý�
			if(event.getSource() == view.getLogin()) {
				try {
					// DBMS ����
					db.connect();
					boolean id_check = false;	// id�� �´��� ����
					boolean pw_check = false;	// pw�� �´��� ����

					// DBMS�� "account ���̺��� id�� pw�� �����ؿͶ�"��� ��ɾ �����ϰ� ������� rs�� ����
					db.read("select id,pw from account;");

					// DB���� ������ id�� pw�� �ϳ��� �����ͼ� �Է��� ID, PW�� ������ boolean ������ true�� ����
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

					// ID�� PW�� ��ġ�ϸ�
					if(id_check == true && pw_check == true) {
						JOptionPane.showMessageDialog(null, "�α��� �Ǿ����ϴ�");
						app.changePanel();
						app.setSize(800,600);
						app.setTitle("����");
						app.setSize(800,600);
						// ID�� �´µ� PW�� Ʋ����
					} else if(id_check == true && pw_check == false) {
						JOptionPane.showMessageDialog(null, "��й�ȣ�� Ʋ�Ƚ��ϴ�");
						view.resetPW();
						// ID�� Ʋ����
					} else {
						JOptionPane.showMessageDialog(null, "���� ������ �ùٸ��� �ʽ��ϴ�");
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
			// ȸ������ ��ư ����	
			} else {
				// ȸ������ â ����
				rv = new RegisterView();
				// ȸ������ Controler ����
				rc = new RegisterControler(rv);
				rc.buttonHandler();
			}
		}
	}
	
	// ������ �޼ҵ�
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
