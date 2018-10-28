package Control;
import java.awt.event.*;
import Starter.Application;
import Model.Ingame;
import View.IngameView;
import javax.swing.*;

public class IngameControler {
	private Application app;
	private IngameView iv;
	private ButtonListener listen;
	private Ingame []i;		// 게임 방 객체(Ingame 모델)
	
	private class ButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent event){
			// 게임 시작 버튼 선택시 -> 미구현
			if(event.getSource() == iv.getStart()) {
				JOptionPane.showMessageDialog(null, "게임 시작");
			// 종료 버튼 선택시
			} else {
				JOptionPane.showMessageDialog(null, "대기실로 나갑니다");
				app.changePanel();
				app.changePanel();
				app.setTitle("대기실");
			}
		}
	}
	
	// 생성자 메소드
	public IngameControler(Application app, IngameView iv) {
		this.app = app;
		this.iv = iv;
		// 게임 방 객체를 4개 생성해준다
		i = new Ingame[4];
		i[0] = new Ingame("1번방");
		i[1] = new Ingame("2번방");
		i[2] = new Ingame("3번방");
		i[3] = new Ingame("4번방");
	}
	
	public void buttonHandler() {
		listen = new ButtonListener();
		iv.getStart().addActionListener(listen);
		iv.getExit().addActionListener(listen);
	}
	
	// 게임방 객체의 비밀번호를 가져온다
	public String getPW(int num) {
		return i[num].getPw();
	}
	
	// 게임방 객체의 제목을 가져온다
	public String getTitle(int num) {
		return i[num].getTitle();
	}
	
	// 게임방 객체의 제목을 지정한다
	public void setTitle(int num, String title) {
		i[num].setTitle(title);
	}
	
	// 게임방 객체의 제목을 지정한다
	public void setPW(int num, String pw) {
		i[num].setPw(pw);
	}
	
	// 알 필요 없음
	public void setInfo(int num) {
		iv.setChat(i[num].getInfo());
	}
	
}
