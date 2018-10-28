package Control;
import Starter.Application;
import View.LoginView;
import View.RegisterView;
import Model.DB;
import Model.Music;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
public class RegisterControler {
	private RegisterView view;
	private ButtonListener listen;
	private DB db;
	private MouseAdapter mouse;
	
	private class MouseAdapter implements MouseListener, MouseMotionListener{
		// �����ư���� ���콺�� �÷������� ����� ����.
		public void mouseEntered(MouseEvent e){
			if(e.getSource() == view.getSign_bt()) {
				// ȸ�����Թ�ư�� �������� signupEntered_img)���� ����.
				view.getSign_bt().setIcon(view.getSignupEntered_img());
				// ȸ�����Թ�ư���� Ŀ���� ������ �ڵ�Ŀ���� ����.
				view.getSign_bt().setCursor(new Cursor(Cursor.HAND_CURSOR));
				// ȸ�����Թ�ư���� Ŀ���� �÷��� �� ������ ȿ���� ����.
				Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3", false);
				// ȿ���� ���.
				buttonEnteredMusic.start();
			} else if(e.getSource() == view.getCancel_bt()) {
				// �����ư�� �������� exitentered_img���� ����.
				view.getCancel_bt().setIcon(view.getCancelEntered_img());
				// �����ư���� Ŀ���� ������ �ڵ�Ŀ���� ����.
				view.getCancel_bt().setCursor(new Cursor(Cursor.HAND_CURSOR));
				// �����ư���� Ŀ���� �÷��� �� ������ ȿ���� ����.
				Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3", false);
				// ȿ���� ���.
				buttonEnteredMusic.start();
			} else if(e.getSource() == view.getCharacter1()){
				// ĳ���͹�ư���� Ŀ���� ������ �ڵ�Ŀ���� ����.
				view.getCharacter1().setCursor(new Cursor(Cursor.HAND_CURSOR));
			} else if(e.getSource() == view.getCharacter2()){
				// ĳ���͹�ư���� Ŀ���� ������ �ڵ�Ŀ���� ����.
				view.getCharacter2().setCursor(new Cursor(Cursor.HAND_CURSOR));
			} else if(e.getSource() == view.getCharacter3()){
				// ĳ���͹�ư���� Ŀ���� ������ �ڵ�Ŀ���� ����.
				view.getCharacter3().setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
		}
		
		/// �����ư���� ���콺�� ���������� ����� ����.
		
		public void mouseExited(MouseEvent e){
			if(e.getSource() == view.getSign_bt()) {
				// ȸ�����Թ�ư���� ���콺�� ���������� �������� signupBasic_img�� ����.
				view.getSign_bt().setIcon(view.getSignupBasic_img());
				// ȸ�����Թ�ư���� ���콺�� �������� Ŀ���� �⺻Ŀ���� ����.
				view.getSign_bt().setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			} else if(e.getSource() == view.getCancel_bt()) {
				// �����ư���� ���콺�� ���������� �������� exitbasic_img�� ����.
				view.getCancel_bt().setIcon(view.getCancelBasic_img());
				// �����ư���� ���콺�� �������� Ŀ���� �⺻Ŀ���� ����.
				view.getCancel_bt().setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			} 
		}
		
		// �����ư�� Ŭ���������� ����� ����.
		public void mousePressed(MouseEvent e){
			if(e.getSource() == view.getMenubar_l()) {
				// �̺�Ʈ�� �߻��������� �� ��ǥ�� ����.
				view.setMouseX(e.getX());
				view.setMouseY(e.getY());
			} else if(e.getSource() == view.getCharacter1() || e.getSource() == view.getCharacter2() || e.getSource() == view.getCharacter3()){
				// ĳ���͹�ư�� Ŭ�������� ������ ȿ���� ����.
				Music characterClicked = new Music("characterClicked.mp3", false);
				// ȿ���� ���.
				characterClicked.start();
			
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
			view.setLocation(x - view.getMouseX(), y - view.getMouseY());
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
			// ���� ��ư ���ý�
			if(event.getSource() == view.getSign_bt()) {
				if(!(view.getID().equals("")) && !(view.getPW().equals(""))) {
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
							view.dispose();
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
				} else {
					JOptionPane.showMessageDialog(null, "ID Ȥ�� PW�� �Է����ּ���!");
				}
			// ��� ��ư ���ý�
			} else {
				// ȸ������ â ����
				view.dispose();
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
		mouse = new MouseAdapter();
		view.getSign_bt().addActionListener(listen);
		view.getCancel_bt().addActionListener(listen);
		view.getMenubar_l().addMouseListener(mouse);
		view.getMenubar_l().addMouseMotionListener(mouse);
		view.getSign_bt().addMouseListener(mouse);
		view.getCancel_bt().addMouseListener(mouse);
	}
}
