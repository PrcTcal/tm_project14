package Control;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.Socket;

import Model.Music;
import Starter.Application;
import Thread.LoginRecieveThread;
import Thread.LoginSendThread;
import Thread.RegisterRecieveThread;
import Thread.RegisterSendThread;
import View.LoginView;
import View.RegisterView;
import View.WaitingView;

public class LoginControler {
	private Application app;
	private LoginView view;
	private ButtonListener listen;
	private RegisterView rv;
	private RegisterControler rc;
	private listen mouse;
	private WaitingView wv;
	private WaitingControler wc;
	private Socket server;
	private LoginRecieveThread lrt;
	private LoginSendThread lst;
	private RegisterRecieveThread rrt;
	private RegisterSendThread rst;
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
				lst = new LoginSendThread(server, "Login", view.getID(), view.getPW());
				lrt = new LoginRecieveThread(server,app, view, wc);
				lst.start();   
				lrt.start();
				// ȸ������ ��ư ����   
			} else {
				// ȸ������ â ����
				rv = new RegisterView();
				// ȸ������ Controler ����
				rc = new RegisterControler(rv, server);
				// ȸ������ â������ ��ư�� �����ϱ� �³� listener���� ��������ִ� �޼ҵ�
				rc.buttonHandler();
			}

		}

	}

	// ������ �޼ҵ�
	public LoginControler(Application app, LoginView v, Socket s, WaitingControler wc) throws IOException {
		this.wc = wc;
		this.server = s;
		this.app = app;
		view = v;

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
