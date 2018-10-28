package Control;
import java.awt.event.*;
import java.sql.SQLException;
import java.awt.*;
import javax.swing.*;
import Model.Music;
import Model.DB;
import Starter.Application;
import View.LoginView;
import View.RegisterView;
public class LoginControler {
	private Application app;
	private LoginView view;
	private ButtonListener listen;
	private RegisterView rv;
	private RegisterControler rc;
	private DB db;
	private MouseAdapter mouse;
	
	private class MouseAdapter implements MouseListener, MouseMotionListener{
		// �����ư���� ���콺�� �÷������� ����� ����.
		public void mouseEntered(MouseEvent e){
			if(e.getSource() == view.getExit_bt()) {
				// �����ư�� �������� exitentered_img���� ����.
				view.getExit_bt().setIcon(view.getExitentered_img());
				// �����ư���� Ŀ���� ������ �ڵ�Ŀ���� ����.
				view.getExit_bt().setCursor(new Cursor(Cursor.HAND_CURSOR));
				// �����ư���� Ŀ���� �÷��� �� ������ ȿ���� ����.
				Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3", false);
				// ȿ���� ���.
				buttonEnteredMusic.start();
			} else if(e.getSource() == view.getLogin_bt()) {
				// �α��ι�ư�� �������� loginEntered_img���� ����.
				view.getLogin_bt().setIcon(view.getLoginEntered_img());
				// �α��ι�ư���� Ŀ���� ������ �ڵ�Ŀ���� ����.
				view.getLogin_bt().setCursor(new Cursor(Cursor.HAND_CURSOR));
				// �α��ι�ư���� Ŀ���� �÷��� �� ������ ȿ���� ����.
				Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3", false);
				// ȿ���� ���.
				buttonEnteredMusic.start();
			} else if(e.getSource() == view.getJoin_bt()){
				// ���Թ�ư�� �������� joinEntered_img���� ����.
				view.getJoin_bt().setIcon(view.getJoinEntered_img());
				// ���Թ�ư���� Ŀ���� ������ �ڵ�Ŀ���� ����.
				view.getJoin_bt().setCursor(new Cursor(Cursor.HAND_CURSOR));
				// ���Թ�ư���� Ŀ���� �÷��� �� ������ ȿ���� ����.
				Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3", false);
				// ȿ���� ���.
				buttonEnteredMusic.start();
			}
		}
		
		/// �����ư���� ���콺�� ���������� ����� ����.
		
		public void mouseExited(MouseEvent e){
			if(e.getSource() == view.getExit_bt()) {
				// �����ư���� ���콺�� ���������� �������� exitbasic_img�� ����.
				view.getExit_bt().setIcon(view.getExitbasic_img());
				// �����ư���� ���콺�� �������� Ŀ���� �⺻Ŀ���� ����.
				view.getExit_bt().setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			} else if(e.getSource() == view.getLogin_bt()) {
				// �α��ι�ư���� ���콺�� ���������� �������� login_img�� ����.
				view.getLogin_bt().setIcon(view.getLogin_img());
				// �α��ι�ư���� ���콺�� �������� Ŀ���� �⺻Ŀ���� ����.
				view.getLogin_bt().setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			} else if(e.getSource() == view.getJoin_bt()){
				// ���Թ�ư���� ���콺�� ���������� �������� join_img�� ����.
				view.getJoin_bt().setIcon(view.getJoin_img());
				// ���Թ�ư���� ���콺�� �������� Ŀ���� �⺻Ŀ���� ����.
				view.getJoin_bt().setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
		}
		
		// �����ư�� Ŭ���������� ����� ����.
		public void mousePressed(MouseEvent e){
			if(e.getSource() == view.getMenubar_l()) {
				// �̺�Ʈ�� �߻��������� �� ��ǥ�� ����.
				view.setMouseX(e.getX());
				view.setMouseY(e.getY());
			} else if(e.getSource() == view.getExit_bt()){
				// �����ư�� Ŭ�������� ������ ȿ���� ����.
				Music buttonPressedMusic = new Music("buttonPressedMusic.mp3", false);
				// ȿ���� ���.
				buttonPressedMusic.start();

				// ���� ó��.
				try {
					Thread.sleep(1000);	// ��ư�� �����ڸ��� ������ ȿ������ �鸮�� �ʱ� ������ 1�� ������ �Ⱓ�� ��.
				}catch(InterruptedException ex)
				{
					// ������ �߻��� �޼ҵ��� ȣ�� ����� �����.
					ex.printStackTrace();
				}
				// ���α׷��� �����Ŵ.
				System.exit(0);
			} else {
				// �����ư�� Ŭ�������� ������ ȿ���� ����.
				Music buttonPressedMusic = new Music("buttonPressedMusic.mp3", false);
				// ȿ���� ���.
				buttonPressedMusic.start();

				// ���� ó��.
				try {
					Thread.sleep(1000);	// ��ư�� �����ڸ��� ������ ȿ������ �鸮�� �ʱ� ������ 1�� ������ �Ⱓ�� ��.
				}catch(InterruptedException ex)
				{
					// ������ �߻��� �޼ҵ��� ȣ�� ����� �����.
					ex.printStackTrace();
				}
			}
		}
		
		public void mouseDragged(MouseEvent e){
			// ���� ��ũ���� ��ǥ�� ����.
			int x = e.getXOnScreen();
			int y = e.getYOnScreen();
			
			// �������� ��ġ�� �巡���Ѱ����� �̵���Ŵ.
			app.setLocation(x - view.getMouseX(), y - view.getMouseY());
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseMoved(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		
	}
	
	
	private class ButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent event){
			
			// �α��� ��ư ���ý�
			if(event.getSource() == view.getLogin_bt()) {
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
		view = v;
		this.rv = rv;
		this.rc = rc;
		eventHandler();
	}
	
	
	public void eventHandler() {
		listen = new ButtonListener();
		mouse = new MouseAdapter();
		view.getLogin_bt().addActionListener(listen);
		view.getJoin_bt().addActionListener(listen);
		view.getExit_bt().addMouseListener(mouse);
		view.getLogin_bt().addMouseListener(mouse);
		view.getJoin_bt().addMouseListener(mouse);
		view.getMenubar_l().addMouseListener(mouse);
		view.getMenubar_l().addMouseMotionListener(mouse);
	}
	
	
}
