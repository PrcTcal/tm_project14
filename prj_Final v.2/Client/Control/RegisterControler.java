package Control;
import Starter.Application;
import Thread.RegisterRecieveThread;
import Thread.RegisterSendThread;
import View.LoginView;
import View.RegisterView;
import Model.Music;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.Socket;
import java.sql.*;
public class RegisterControler {
	private RegisterView view;
	private ButtonListener listen;
	private MouseAdapter mouse;
	private Socket server;
	private RegisterSendThread rst;
	private RegisterRecieveThread rrt;
	private String msg,avatar;
	
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
				// �̺�Ʈ�� ����� �� �԰� ���� ��������
				if(view.getCharacter1().isSelected()) {
					avatar = "1";
				} else if(view.getCharacter2().isSelected()) {
					avatar = "2";
				} else {
					avatar = "3";
				}
				rst = new RegisterSendThread(server, "Join", view.getID(), view.getPW(), avatar);
				rrt = new RegisterRecieveThread(view, server);
				rst.start();
				rrt.start();
			} else {
				// ȸ������ â ����
//				rst.setinfo("cancel", null, null);
//				rst.start();
//				rst.interrupt();
//				rrt.interrupt();
				view.dispose();
			}
		}
	}
	
	// ������ �޼ҵ�
	public RegisterControler(RegisterView v, Socket s){
		server = s;
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
