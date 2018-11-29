package Control;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.Socket;

import javax.swing.JOptionPane;

import Starter.Application;
import Thread.MakeRoomSendThread;
import View.MakeRoomView;

public class MakeRoomControler {
	private MakeRoomView view;
	private ButtonListener listen;
	private Application app;
	private WaitingControler wc;
	private IngameControler ic;
	private Socket s;
	private MakeRoomSendThread mrt;
	private String ID, avatar;
	public Socket s1=null;
	public Socket s2=null;
	public Socket s3=null;
	public Socket s4=null;

	private class ButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent event){
			// Ȯ�� ��ư Ŭ����
			if(event.getSource() == view.getOk()) {

				// �Ƹ� ���⼭
				mrt = new MakeRoomSendThread(s, view, view.getTitle(), view.getPW());
				mrt.start();
				//				// ���� �� ��ü�� �� ������ ����
				//				ic.setTitle(wc.getRoom_num(), view.getTitle());
				//				// ���� �� ��ü�� ��й�ȣ�� ����
				//				ic.setPW(wc.getRoom_num(), view.getPW());
				//				
				//				// ��Ʈ ���� ����
				//				s = new Socket(s.getInetAddress(), s.getPort()+1);

				try {
					ic.setUserInfo(s, ID, avatar);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				// �ΰ��ӿ� ���̵� �ƹ�Ÿ ���� ���� �Ѱ��� 

				JOptionPane.showMessageDialog(null, "�濡 �����մϴ�");
				app.changePanel();
				app.setSize(1280,720);
				app.setTitle("������ : " + ic.getTitle(wc.getRoom_num()));
				//ic.setInfo(wc.getRoom_num());

				// �游��� â ����
				view.getN().dispose();
				// ��� ��ư ���ý�
			} else {
				// �游��� â ����
				view.getN().dispose();
			}
		}
	}

	// ������ �޼ҵ�
	public MakeRoomControler(Application app, MakeRoomView mv, WaitingControler wc, IngameControler ic,Socket s, String ID, String avatar) {
		this.app = app;
		view = mv;
		this.wc = wc;
		this.ic = ic;
		this.s = s;
		this.s1 =s;
		this.s2 =s;
		this.s3 =s;
		this.s4 =s;
		this.ID = ID;
		this.avatar = avatar;
	}

	public void buttonHandler() {
		listen = new ButtonListener();
		view.getOk().addActionListener(listen);
		view.getCancel().addActionListener(listen);
	}
}
