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
import View.WaitingView;
public class LoginControler {
	private Application app;
	private LoginView view;
	private ButtonListener listen;
	private RegisterView rv;
	private RegisterControler rc;
	private DB db;
	private listen mouse;
	private WaitingView wv;
	
	private class listen extends MouseAdapter{
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
					String avatar_selected = "";
					
					// DBMS�� "account ���̺��� id�� pw�� �����ؿͶ�"��� ��ɾ �����ϰ� ������� rs�� ����
					db.read("select id,pw,avatar from account;");

					// DB���� ������ id�� pw�� �ϳ��� �����ͼ� �Է��� ID, PW�� ������ boolean ������ true�� ����
					while(db.rs.next()) {
						String sqlRecipeProcess_id = db.rs.getString("id");
						String sqlRecipeProcess_pw = db.rs.getString("pw");
						int sqlRecipeProcess_avatar = db.rs.getInt("avatar");

						if(view.getID().equals(sqlRecipeProcess_id)) {
							id_check = true;
							if(view.getPW().equals(sqlRecipeProcess_pw)) {
								pw_check = true;
								if(sqlRecipeProcess_avatar == 1) {
									avatar_selected = "smallcharacter1.png";
								} else if(sqlRecipeProcess_avatar == 2) {
									avatar_selected = "smallcharacter2.png";
								} else {
									avatar_selected = "smallcharacter3.png";
								}
							}
						}
					}

					// ID�� PW�� ��ġ�ϸ�
					if(id_check == true && pw_check == true) {
						JOptionPane.showMessageDialog(null, "�α��� �Ǿ����ϴ�");
						wv.setCharacter_img(avatar_selected);
						wv.repaint();
						app.cards.show(app.getContentPane(), "Two");
						app.setTitle("����");
						
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
	public LoginControler(Application app, LoginView v, RegisterView rv, RegisterControler rc, WaitingView wv) {
		db = new DB();
		this.app = app;
		view = v;
		this.rv = rv;
		this.rc = rc;
		this.wv = wv;
		eventHandler();
	}
	
	
	public void eventHandler() {
		listen = new ButtonListener();
		mouse = new listen();
		view.getLogin_bt().addActionListener(listen);
		view.getJoin_bt().addActionListener(listen);
		view.getExit_bt().addMouseListener(mouse);
		view.getLogin_bt().addMouseListener(mouse);
		view.getJoin_bt().addMouseListener(mouse);
		view.getMenubar_l().addMouseListener(mouse);
		view.getMenubar_l().addMouseMotionListener(mouse);
	}
	
	
}
