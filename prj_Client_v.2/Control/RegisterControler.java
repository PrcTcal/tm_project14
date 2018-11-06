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
		// 종료버튼위에 마우스를 올렸을때의 사건을 구현.
		public void mouseEntered(MouseEvent e){
			if(e.getSource() == view.getSign_bt()) {
				// 회원가입버튼의 아이콘을 signupEntered_img)으로 설정.
				view.getSign_bt().setIcon(view.getSignupEntered_img());
				// 회원가입버튼위에 커서가 있을시 핸드커서로 변경.
				view.getSign_bt().setCursor(new Cursor(Cursor.HAND_CURSOR));
				// 회원가입버튼위에 커서를 올렸을 시 나오는 효과음 선언.
				Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3", false);
				// 효과음 재생.
				buttonEnteredMusic.start();
			} else if(e.getSource() == view.getCancel_bt()) {
				// 종료버튼의 아이콘을 exitentered_img으로 설정.
				view.getCancel_bt().setIcon(view.getCancelEntered_img());
				// 종료버튼위에 커서가 있을시 핸드커서로 변경.
				view.getCancel_bt().setCursor(new Cursor(Cursor.HAND_CURSOR));
				// 종료버튼위에 커서를 올렸을 시 나오는 효과음 선언.
				Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3", false);
				// 효과음 재생.
				buttonEnteredMusic.start();
			} else if(e.getSource() == view.getCharacter1()){
				// 캐릭터버튼위에 커서가 있을시 핸드커서로 변경.
				view.getCharacter1().setCursor(new Cursor(Cursor.HAND_CURSOR));
			} else if(e.getSource() == view.getCharacter2()){
				// 캐릭터버튼위에 커서가 있을시 핸드커서로 변경.
				view.getCharacter2().setCursor(new Cursor(Cursor.HAND_CURSOR));
			} else if(e.getSource() == view.getCharacter3()){
				// 캐릭터버튼위에 커서가 있을시 핸드커서로 변경.
				view.getCharacter3().setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
		}
		
		/// 종료버튼에서 마우스를 내렸을때의 사건을 구현.
		
		public void mouseExited(MouseEvent e){
			if(e.getSource() == view.getSign_bt()) {
				// 회원가입버튼에서 마우스를 내렸을때의 아이콘을 signupBasic_img로 설정.
				view.getSign_bt().setIcon(view.getSignupBasic_img());
				// 회원가입버튼에서 마우스를 내렸을때 커서를 기본커서로 설정.
				view.getSign_bt().setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			} else if(e.getSource() == view.getCancel_bt()) {
				// 종료버튼에서 마우스를 내렸을때의 아이콘을 exitbasic_img로 설정.
				view.getCancel_bt().setIcon(view.getCancelBasic_img());
				// 종료버튼에서 마우스를 내렸을때 커서를 기본커서로 설정.
				view.getCancel_bt().setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			} 
		}
		
		// 종료버튼을 클릭했을때의 사건을 구현.
		public void mousePressed(MouseEvent e){
			if(e.getSource() == view.getMenubar_l()) {
				// 이벤트가 발생했을때의 그 좌표를 얻어옴.
				view.setMouseX(e.getX());
				view.setMouseY(e.getY());
			} else if(e.getSource() == view.getCharacter1() || e.getSource() == view.getCharacter2() || e.getSource() == view.getCharacter3()){
				// 캐릭터버튼을 클릭했을시 나오는 효과음 선언.
				Music characterClicked = new Music("characterClicked.mp3", false);
				// 효과음 재생.
				characterClicked.start();
			
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
			// 가입 버튼 선택시
			if(event.getSource() == view.getSign_bt()) {
				if(!(view.getID().equals("")) && !(view.getPW().equals(""))) {
					try {
						// DBMS 연결
						db.connect();
						// DB에 저장된 id 추출
						db.read("select id from account;");
						boolean overlap = false;	// id가 중복됬는지 확인
						int avatar_code = 0;
						// DB에서 가져온 id를 하나씩 반복문으로 넘겨서 id_temp와 비교한 후에 값이 같으면
						// DB에 이미 동일한 ID가 저장되있다는 뜻이므로 overlap = true로 한다.
						while(db.rs.next()) {
							String sqlRecipeProcess = db.rs.getString("id");
							if(view.getID().equals(sqlRecipeProcess)) {
								overlap = true;
							}
						}
						
						if(view.getCharacter1().isSelected()) {
							avatar_code = 1;
						} else if(view.getCharacter2().isSelected()) {
							avatar_code = 2;
						} else {
							avatar_code = 3;
						}

						// ID가 중복되지 않았으면
						if(overlap == false && avatar_code != 0) {
							
							
							// DB에 id_temp와 pw_temp의 값을 저장한다.
							db.st.executeUpdate("insert into account values('" + view.getID() + "','" + view.getPW() + "',0 ," + avatar_code + " );");  
							JOptionPane.showMessageDialog(null, "회원가입 되었습니다");
							// 프레임 종료
							view.dispose();
							// ID가 중복됬으면
						} else {
							JOptionPane.showMessageDialog(null, "중복된 ID입니다");
							// 회원가입 창에서 ID와 PW칸을 비워준다
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
				} else {
					JOptionPane.showMessageDialog(null, "ID 혹은 PW를 입력해주세요!");
				}
			// 취소 버튼 선택시
			} else {
				// 회원가입 창 종료
				view.dispose();
			}
		}
	}
	
	// 생성자 메소드
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
