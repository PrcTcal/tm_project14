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
			// 확인 버튼 클릭시
			if(event.getSource() == view.getOk()) {

				// 아마 여기서
				mrt = new MakeRoomSendThread(s, view, view.getTitle(), view.getPW());
				mrt.start();
				//				// 게임 방 객체의 방 제목을 지정
				//				ic.setTitle(wc.getRoom_num(), view.getTitle());
				//				// 게임 방 객체의 비밀번호를 지정
				//				ic.setPW(wc.getRoom_num(), view.getPW());
				//				
				//				// 포트 새로 열고
				//				s = new Socket(s.getInetAddress(), s.getPort()+1);

				try {
					ic.setUserInfo(s, ID, avatar);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				// 인게임에 아이디 아바타 소켓 정보 넘겨주 

				JOptionPane.showMessageDialog(null, "방에 입장합니다");
				app.changePanel();
				app.setSize(1280,720);
				app.setTitle("방제목 : " + ic.getTitle(wc.getRoom_num()));
				//ic.setInfo(wc.getRoom_num());

				// 방만들기 창 종료
				view.getN().dispose();
				// 취소 버튼 선택시
			} else {
				// 방만들기 창 종료
				view.getN().dispose();
			}
		}
	}

	// 생성자 메소드
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
