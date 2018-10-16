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
		// 종료버튼위에 마우스를 올렸을때의 사건을 구현.
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
			} else if(e.getSource() == view.getLogin_bt()) {
				// 로그인버튼의 아이콘을 loginEntered_img으로 설정.
				view.getLogin_bt().setIcon(view.getLoginEntered_img());
				// 로그인버튼위에 커서가 있을시 핸드커서로 변경.
				view.getLogin_bt().setCursor(new Cursor(Cursor.HAND_CURSOR));
				// 로그인버튼위에 커서를 올렸을 시 나오는 효과음 선언.
				Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3", false);
				// 효과음 재생.
				buttonEnteredMusic.start();
			} else if(e.getSource() == view.getJoin_bt()){
				// 가입버튼의 아이콘을 joinEntered_img으로 설정.
				view.getJoin_bt().setIcon(view.getJoinEntered_img());
				// 가입버튼위에 커서가 있을시 핸드커서로 변경.
				view.getJoin_bt().setCursor(new Cursor(Cursor.HAND_CURSOR));
				// 가입버튼위에 커서를 올렸을 시 나오는 효과음 선언.
				Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3", false);
				// 효과음 재생.
				buttonEnteredMusic.start();
			}
		}
		
		/// 종료버튼에서 마우스를 내렸을때의 사건을 구현.
		
		public void mouseExited(MouseEvent e){
			if(e.getSource() == view.getExit_bt()) {
				// 종료버튼에서 마우스를 내렸을때의 아이콘을 exitbasic_img로 설정.
				view.getExit_bt().setIcon(view.getExitbasic_img());
				// 종료버튼에서 마우스를 내렸을때 커서를 기본커서로 설정.
				view.getExit_bt().setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			} else if(e.getSource() == view.getLogin_bt()) {
				// 로그인버튼에서 마우스를 내렸을때의 아이콘을 login_img로 설정.
				view.getLogin_bt().setIcon(view.getLogin_img());
				// 로그인버튼에서 마우스를 내렸을때 커서를 기본커서로 설정.
				view.getLogin_bt().setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			} else if(e.getSource() == view.getJoin_bt()){
				// 가입버튼에서 마우스를 내렸을때의 아이콘을 join_img로 설정.
				view.getJoin_bt().setIcon(view.getJoin_img());
				// 가입버튼에서 마우스를 내렸을때 커서를 기본커서로 설정.
				view.getJoin_bt().setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
		}
		
		// 종료버튼을 클릭했을때의 사건을 구현.
		public void mousePressed(MouseEvent e){
			if(e.getSource() == view.getMenubar_l()) {
				// 이벤트가 발생했을때의 그 좌표를 얻어옴.
				view.setMouseX(e.getX());
				view.setMouseY(e.getY());
			} else if(e.getSource() == view.getExit_bt()){
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
			}
		}
		
		public void mouseDragged(MouseEvent e){
			// 현재 스크린의 좌표를 얻어옴.
			int x = e.getXOnScreen();
			int y = e.getYOnScreen();
			
			// 프레임의 위치를 드래그한곳으로 이동시킴.
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
			
			// 로그인 버튼 선택시
			if(event.getSource() == view.getLogin_bt()) {
				try {
					// DBMS 연결
					db.connect();
					boolean id_check = false;	// id가 맞는지 여부
					boolean pw_check = false;	// pw가 맞는지 여부

					// DBMS에 "account 테이블에서 id랑 pw를 추출해와라"라는 명령어를 실행하고 결과값을 rs에 저장
					db.read("select id,pw from account;");

					// DB에서 가져온 id와 pw를 하나씩 가져와서 입력한 ID, PW와 같으면 boolean 변수를 true로 설정
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

					// ID와 PW가 일치하면
					if(id_check == true && pw_check == true) {
						JOptionPane.showMessageDialog(null, "로그인 되었습니다");
						app.changePanel();
						app.setSize(800,600);
						app.setTitle("대기실");
						app.setSize(800,600);
						// ID는 맞는데 PW가 틀리면
					} else if(id_check == true && pw_check == false) {
						JOptionPane.showMessageDialog(null, "비밀번호가 틀렸습니다");
						view.resetPW();
						// ID가 틀리면
					} else {
						JOptionPane.showMessageDialog(null, "계정 정보가 올바르지 않습니다");
						view.resetText();
					}
					
					// DBMS 연결 종료
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
			// 회원가입 버튼 선택	
			} else {
				// 회원가입 창 생성
				rv = new RegisterView();
				// 회원가입 Controler 연결
				rc = new RegisterControler(rv);
				rc.buttonHandler();
			}
		}
	}
	
	// 생성자 메소드
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
