package Control;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.Socket;

import javax.swing.JOptionPane;

import Model.Music;
import Model.waiting;
import Starter.Application;
import Thread.WaitingChatRecieveThread;
import Thread.WaitingChatSendThread;
import Thread.MakeRoomRecieveThread;
import Thread.RankingRecieveThread;
import View.MakeRoomView;
import View.RoomLockView;
import View.WaitingView;
public class WaitingControler {
	private Application app;
	private waiting w;	// 대기실 모델 객체
	private ButtonListener listen;
	private TextListener text;
	private WaitingView view;
	private MakeRoomView mv;
	private MakeRoomControler mc;
	private IngameControler ic;
	private RoomLockView rv;
	private RoomLockControler rc;
	private Mouse mouse;
	private Socket s1, s2, s3;
	private String msg, ID, avatar;
	private RankingRecieveThread rrt,rrt1;
	private WaitingChatSendThread cst;
	private WaitingChatRecieveThread crt;
	private MakeRoomRecieveThread mrrt;
	public Socket s4;
	public Socket s5;

	private class Mouse extends MouseAdapter{
		// 종료버튼위에 마우스를 올렸을때의 사건을 구현.
		@Override
		public void mouseEntered(MouseEvent e){
			if(e.getSource() == view.getExit_bt()) {
				// 종료버튼의 아이콘을 exitentered_img으로 설정.
				view.getExit_bt().setIcon(view.getExitentered_img());
				// 종료버튼위에 커서가 있을시 핸드커서로 변경.
				view.getExit_bt().setCursor(new Cursor(Cursor.HAND_CURSOR));
				// 종료버튼위에 커서를 올렸을 시 나오는 효과음 선언.
				Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3", false);
				// 효과음 재생.
				buttonEnteredMusic.start();
			} else if(e.getSource() == view.getUserexit_bt()){
				// 종료버튼의 아이콘을 exitentered_img으로 설정.
				//userexit_bt.setIcon(exitentered_img);
				// 종료버튼위에 커서가 있을시 핸드커서로 변경.
				//userexit_bt.setCursor(new Cursor(Cursor.HAND_CURSOR));
				// 종료버튼위에 커서를 올렸을 시 나오는 효과음 선언.
				Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3", false);
				// 효과음 재생.
				buttonEnteredMusic.start();
			}
		}

		/// 종료버튼에서 마우스를 내렸을때의 사건을 구현.
		@Override
		public void mouseExited(MouseEvent e){
			if(e.getSource() == view.getExit_bt()) {
				// 종료버튼에서 마우스를 내렸을때의 아이콘을 exitbasic_img로 설정.
				view.getExit_bt().setIcon(view.getExitbasic_img());
				// 종료버튼에서 마우스를 내렸을때 커서를 기본커서로 설정.
				view.getExit_bt().setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			} else {
				// 종료버튼에서 마우스를 내렸을때의 아이콘을 exitbasic_img로 설정.
				//userexit_bt.setIcon(exitbasic_img);
				// 종료버튼에서 마우스를 내렸을때 커서를 기본커서로 설정.
				//userexit_bt.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
		}

		// 종료버튼을 클릭했을때의 사건을 구현.
		@Override
		public void mousePressed(MouseEvent e){
			if(e.getSource() == view.getMenubar_l()) {
				// 이벤트가 발생했을때의 그 좌표를 얻어옴.
				view.setMouseX(e.getX());
				view.setMouseY(e.getY());
			} else {
				// 종료버튼을 클릭했을시 나오는 효과음 선언.
				Music buttonPressedMusic = new Music("buttonPressedMusic.mp3", false);
				// 효과음 재생.
				buttonPressedMusic.start();

				// 예외 처리.
				try {
					Thread.sleep(1000);	// 버튼을 누르자마자 꺼지면 효과음이 들리지 않기 때문에 1초 정도의 기간을 줌.
				}catch(InterruptedException ex)
				{
					// 에러가 발생한 메소드의 호출 기록을 출력함.
					ex.printStackTrace();
				}
				// 프로그램을 종료시킴.
				System.exit(0);
			}

		}

		// 드래그가 발생했을때의 이벤트처리.
		@Override
		public void mouseDragged(MouseEvent e)
		{
			// 현재 스크린의 좌표를 얻어옴.
			int x = e.getXOnScreen();
			int y = e.getYOnScreen();

			// 프레임의 위치를 드래그한곳으로 이동시킴.
			app.setLocation(x - view.getMouseX(), y - view.getMouseY());
		}

	}

	private class TextListener implements ActionListener{
		public void actionPerformed(ActionEvent e){

			if(e.getSource() == view.getTextField()){
				String msg = ": " + view.getChatText() + "\n";
				cst = new WaitingChatSendThread(s2, msg, view);
				cst.start();
				view.getTextArea().setCaretPosition(view.getTextArea().getDocument().getLength());
			}
		}
	}



	private class ButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent event){
			if(event.getSource() == view.getMkr_bt()) {
				// 생성된 방이 4개 미만일때
				if(w.getRoom_num() < 4) {
					// 방만들기 창 생성
					mv = new MakeRoomView();
					// 거기서 정보 만들기
					mc = new MakeRoomControler(app,mv,WaitingControler.this,ic,s3,ID,avatar, w.getRoom_num());
					mc.buttonHandler();
					// 생성된 방이 4개 이상일때
				} else {
					JOptionPane.showMessageDialog(null, "더 이상 방을 생성할 수 없습니다!");
				}
				// 종료 버튼 선택시
			} else if(event.getSource() == view.getExit_bt()){
				app.offMusic();
				app.dispose();
				// 방 버튼 선택시
			} else {
				// 4가지 방중에서 선택된 방이 어떤 방인지 찾는다.
				for(int i = 0 ; i < 4 ; i++) {
					if(event.getSource() == view.getR(i)) {
						// 대기실 모델에 선택한 방이 몇번방인지 저장
						w.setSelect(i);
						//아마 여기 추가
						try {
							ic.setUserInfo(s3,ID, avatar, w.getSelect());
							
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						// 선택한 방에 비밀번호가 설정되있지 않을시
						if(ic.getPW(i).equals("")){
							JOptionPane.showMessageDialog(null, "방에 입장합니다");
							app.changePanel();
							app.setSize(1280,720);
							app.setTitle("방제목 : " + ic.getTitle(i));
							// 무시해도됨
							//ic.setInfo(w.getSelect());
							// 선택한 방에 비밀번호가 설정 되있을시
						} else {
							// 비밀번호 입력 창 생성
							rv = new RoomLockView();
							rc = new RoomLockControler(app,rv,WaitingControler.this, ic);
							rc.buttonHandler();
						}
					}
				}
			}
		}
	}

	
	//	public void setChat() {
	//		crt = new ChatRecieveThread(s, view);
	//		crt.start();
	//	}

	// 대기실의 순위표를 최신화 시켜주는 메소드



	public void setRanking() {
		rrt1 = new RankingRecieveThread(s1, view, ID);
		rrt1.start();
	}
	
	
	public void setUserInfo(Socket s1, Socket s2, Socket s3, String ID, String avatar) {
		this.s1 = s1;
		this.s2 = s2;
		this.s3 = s3;

		this.ID = ID;
		this.avatar = avatar;
		rrt = new RankingRecieveThread(s1, view, ID);
		rrt.start();
		view.getTable().updateUI();
		crt = new WaitingChatRecieveThread(s2,view);
		crt.start();
		view.getUserID(ID);
		view.setCharacter_img(avatar);
		mrrt = new MakeRoomRecieveThread(s3, view, ic, w);
		mrrt.start();
	}






	// 파라매터로 문자열을 받아 방 제목으로 지정해주고 방 버튼을 활성화 시키는 메소드
	public void Enable_Room(String title) {
		view.getR(w.getRoom_num()).setEnabled(true);
		view.getR(w.getRoom_num()).setText(title);
		w.addRoom_Num();
	}

	// 
	public int getRoom_num() {
		return w.getRoom_num();
	}

	public int getSelect() {
		return w.getSelect();
	}



	// 생성자 메소드
	public WaitingControler(Application app, WaitingView wv, IngameControler ic) {
		w = new waiting();
		this.app = app;
		this.view = wv;
		this.ic = ic;
	}

	public void buttonHandler() {
		text = new TextListener();
		listen = new ButtonListener();
		mouse = new Mouse();

		view.getTextField().addActionListener(text);


		view.getMkr_bt().addActionListener(listen);
		view.getExit_bt().addActionListener(listen);
		view.getR(0).addActionListener(listen);
		view.getR(1).addActionListener(listen);
		view.getR(2).addActionListener(listen);
		view.getR(3).addActionListener(listen);

		view.getExit_bt().addMouseListener(mouse);
		view.getMenubar_l().addMouseListener(mouse);
		view.getMenubar_l().addMouseMotionListener(mouse);
		view.getUserexit_bt().addMouseListener(mouse);
	}	
}