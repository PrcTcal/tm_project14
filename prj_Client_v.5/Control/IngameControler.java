package Control;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.JColorChooser;
import javax.swing.JOptionPane;
import java.io.*;
import java.sql.SQLException;

import Model.Ingame;
import Model.Music;
import Starter.Application;
import View.IngameView;
import Model.DB;

public class IngameControler {
	private Application app;
	private IngameView iv;
	//private ButtonListener listen;
	private Mouse mouse;
	private ToolActionListener tool;
	private PaintDraw paint;
	private DB db;
	
	// timer thread 실험용 변수
	private boolean check = false;
	private int round = 5;
	
	private Ingame []i;		// 게임 방 객체(Ingame 모델)
	
	private class Mouse extends MouseAdapter{
		
		@Override
		public void mousePressed(MouseEvent e) {
			if(e.getSource() == iv.getMenubar_l()) {
				// 이벤트가 발생했을때의 그 좌표를 얻어옴.
				iv.setMouseX(e.getX());
				iv.setMouseY(e.getY());
			} else if(e.getSource() == iv.getPaintdraw_p()) {
				iv.setStartX(e.getX());
				iv.setStartY(e.getY());
			
			} else if(e.getSource() == iv.getMenuexit_bt()) {
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
		
		// 드래그가 발생했을때의 이벤트처리.
		@Override
		public void mouseDragged(MouseEvent e)
		{
			// 현재 스크린의 좌표를 얻어옴.
			int x = e.getXOnScreen();
			int y = e.getYOnScreen();

			// 프레임의 위치를 드래그한곳으로 이동시킴.
			app.setLocation(x - iv.getMouseX(), y - iv.getMouseY());
		}
		
		// 종료버튼위에 마우스를 올렸을때의 사건을 구현.
		@Override
		public void mouseEntered(MouseEvent e) {
			if(e.getSource() == iv.getMenuexit_bt()) {
				// 종료버튼의 아이콘을 exitentered_img으로 설정.
				iv.getMenuexit_bt().setIcon(iv.getExitentered_img());
				// 종료버튼위에 커서가 있을시 핸드커서로 변경.
				iv.getMenuexit_bt().setCursor(new Cursor(Cursor.HAND_CURSOR));
				// 종료버튼위에 커서를 올렸을 시 나오는 효과음 선언.
				Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3", false);
				// 효과음 재생.
				buttonEnteredMusic.start();
			} else if(e.getSource() == iv.getReady_bt()) {
				// 종료버튼의 아이콘을 exitentered_img으로 설정.
        		iv.getReady_bt().setIcon(iv.getReadyEntered_img());
        		// 종료버튼위에 커서가 있을시 핸드커서로 변경.
        		iv.getReady_bt().setCursor(new Cursor(Cursor.HAND_CURSOR));
        		// 종료버튼위에 커서를 올렸을 시 나오는 효과음 선언.
        		Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3", false);
        		// 효과음 재생.
        		buttonEnteredMusic.start();
			} else if(e.getSource() == iv.getExit_bt()) {
				// 종료버튼의 아이콘을 exitentered_img으로 설정.
        		iv.getExit_bt().setIcon(iv.getExitEntered2_img());
        		// 종료버튼위에 커서가 있을시 핸드커서로 변경.
        		iv.getExit_bt().setCursor(new Cursor(Cursor.HAND_CURSOR));
        		// 종료버튼위에 커서를 올렸을 시 나오는 효과음 선언.
        		Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3", false);
        		// 효과음 재생.
        		buttonEnteredMusic.start();
			}
		}
		
		/// 종료버튼에서 마우스를 내렸을때의 사건을 구현.
		@Override
		public void mouseExited(MouseEvent e) {
			if(e.getSource() == iv.getMenuexit_bt()) {
				// 종료버튼에서 마우스를 내렸을때의 아이콘을 exitbasic_img로 설정.
				iv.getMenuexit_bt().setIcon(iv.getExitbasic_img());
				// 종료버튼에서 마우스를 내렸을때 커서를 기본커서로 설정.
				iv.getMenuexit_bt().setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			} else if(e.getSource() == iv.getReady_bt()) {
				// 종료버튼에서 마우스를 내렸을때의 아이콘을 exitbasic_img로 설정.
        		iv.getReady_bt().setIcon(iv.getReadyBasic_img());
        		// 종료버튼에서 마우스를 내렸을때 커서를 기본커서로 설정.
        		iv.getReady_bt().setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			} else if(e.getSource() == iv.getExit_bt()) {
				// 종료버튼에서 마우스를 내렸을때의 아이콘을 exitbasic_img로 설정.
        		iv.getExit_bt().setIcon(iv.getExitBasic2_img());
        		// 종료버튼에서 마우스를 내렸을때 커서를 기본커서로 설정.
        		iv.getExit_bt().setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
		}

	}
	
	private class ButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent event){
	
			// 종료 버튼 선택시
			if(event.getSource() == iv.getMenuexit_bt()) {
				app.offMusic();
				app.dispose();
			} else if(event.getSource() == iv.getChat_tx()) {
				
				if(iv.getChat_tx().getText().equals(iv.getQuestion_tx().getText())) {
					check = true;
				}
				iv.setSendchat_s(iv.getChat_tx().getText());
				iv.getChat_txa().append("ID: " + iv.getSendchat_s() + "\n");
				iv.getChat_txa().setCaretPosition(iv.getChat_txa().getDocument().getLength());
				iv.getChat_tx().setText("");
				iv.getChat_tx().setCaretPosition(iv.getChat_tx().getText().length());
			} else if(event.getSource() == iv.getExit_bt()) {
				JOptionPane.showMessageDialog(null, "대기실로 나갑니다");
				// 방을 나갈시 방입장 버튼이 비활성화 되어야 함. -> 서버 구현된 후 구현하기.
				app.cards.show(app.getContentPane(), "Two");
			} else if(event.getSource() == iv.getReady_bt()) {
				iv.getChat_txa().append("게임을 시작합니다!\n");
				timer t = new timer();
				t.start();
				
			
				
				
			}
		}
	}
	
	// 그림을 그릴 패널에 대한 마우스 모션리스너 생성
		public class PaintDraw implements MouseMotionListener
		{

			// 마우스 드래그 메소드
			@Override
			public void mouseDragged(MouseEvent e) {
				// thickness에 setlinethickness_tx에 저장된 문자열값을 정수화시켜 저장함.
				iv.setThickness(Integer.parseInt(iv.getSetlinethickness_tx().getText()));

				// 끝점 x좌표를 구해 저장.
				iv.setEndX(e.getX());

				// 끝점 y좌표를 구해 저장.
				iv.setEndY(e.getY());
				
				if(iv.getEndX() < 0 || iv.getEndY() < 0 || iv.getEndX() > 895 || iv.getEndY() > 455) {
		            return;
		        }

				iv.getGraphics2().setStroke(new BasicStroke(iv.getThickness(), BasicStroke.CAP_ROUND, 0));
				iv.getGraphics2().drawLine(iv.getStartX()+82, iv.getStartY()+100, iv.getEndX()+82, iv.getEndY()+100);

				iv.setStartX(iv.getEndX());
				iv.setStartY(iv.getEndY());

			}

			// 사용하지 않지만 implements 했기에 남겨둬야함.
			@Override
			public void mouseMoved(MouseEvent e) 
			{

			}
		}

		// 연필, 지우개, clear, 색변경에 대한 액션리스너
		public class ToolActionListener implements ActionListener {
			
			// 오버라이딩된 actionPerformed메소드 실행
			public void actionPerformed(ActionEvent e)	{
				// 연필버튼이 눌렸을떄 밑 if문장 블록범위내 문장 실행
				if(e.getSource() == iv.getPencil_bt()) { 
					if(iv.isTf() == false) {
						 // 그려지는 색상을 검은색 지정
						iv.getGraphics2().setColor(Color.BLACK);
					} else {
						// 그려지는 색상을 selectedColor변수의 값으로 지정
						iv.getGraphics2().setColor(iv.getSelectedColor());
					}
				// 지우개버튼이 눌렸을떄 밑 if문장 블록범위내 문장 실행
				} else if(e.getSource() == iv.getEraser_bt())	{
					// 그려지는 색상을 흰색으로 해줬기 때문에 흰색으로 펜이 그려져 지워지는 것처럼 보이게 한다.
					iv.getGraphics2().setColor(Color.WHITE);
				}
				else if(e.getSource() == iv.getColorselect_bt()) {
					// 색이 변경됐음을 알려줌.
					iv.setTf(true);
					// JColorChooser 생성
					JColorChooser chooser = new JColorChooser();
					// 선택된 컬러값을 selectedColor에 저장
					iv.setSelectedColor(chooser.showDialog(null, "Color", Color.orange));
					// 선의 색을 지정.
					iv.getGraphics2().setColor(iv.getSelectedColor());
				}
				else
				{
					// 패널을 초기화하여 다시 그려준다.
					iv.repaint();
				}
			}
		}
	
	// 생성자 메소드
	public IngameControler(Application app, IngameView iv) {
		this.app = app;
		this.iv = iv;
		db = new DB();
		// 게임 방 객체를 4개 생성해준다
		i = new Ingame[4];
		i[0] = new Ingame("1번방");
		i[1] = new Ingame("2번방");
		i[2] = new Ingame("3번방");
		i[3] = new Ingame("4번방");
		
	}
	
	public void buttonHandler() {
		ButtonListener listen = new ButtonListener();
		mouse = new Mouse();
		tool = new ToolActionListener();
		paint = new PaintDraw();
		
		//iv.getExit_bt().addActionListener(listen);
		iv.getMenubar_l().addMouseListener(mouse);
		iv.getMenubar_l().addMouseMotionListener(mouse);
		iv.getExit_bt().addMouseListener(mouse);
		iv.getReady_bt().addMouseListener(mouse);
		iv.getMenuexit_bt().addMouseListener(mouse);
		iv.getPaintdraw_p().addMouseListener(mouse);
		iv.getPaintdraw_p().addMouseMotionListener(paint);
		
		iv.getPencil_bt().addActionListener(tool);
		iv.getEraser_bt().addActionListener(tool);
		iv.getEraseall_bt().addActionListener(tool);
		iv.getColorselect_bt().addActionListener(tool);
		
		iv.getChat_tx().addActionListener(listen);
		iv.getExit_bt().addActionListener(listen);
		iv.getMenuexit_bt().addActionListener(listen);
		iv.getReady_bt().addActionListener(listen);
	}
	
	// 타이머 메소드 (수정 필요)
	public class timer extends Thread {
		private int min, sec, i;
		
		public timer() {
			i = 0;
			min = 2;
			sec = 59;
		}
		
		public void run() {
			String timerBuffer;
//			String sqlRecipeProcess_word;
//			try {
//				db.connect();
//				db.read("select word from theme1;");

				
				while(round != 0) {
//					db.rs.next();
//					sqlRecipeProcess_word = db.rs.getString("word");
//					iv.getQuestion_tx().setText(sqlRecipeProcess_word);
					while(i < 180){
						timerBuffer = String.format("%02d:%02d", min, sec);
						
						// 시간이 끝났을때
						if(min == -1) {
//							iv.getChat_txa().append("정답자가 없습니다\n");
							break;
						// 정답처리 신호를 받았을 때(check는 원래 정답이 맞을 때 true로 되던 변수임)
						} else if(check == true) {
//							iv.getChat_txa().append("정답입니다!\n");
							//iv.getTimer_l().setText("03:00");
							i = 0;
							min = 2;
							sec = 59;
							check = false;
							try {
								this.sleep(1000);
							}catch(Exception e) {
							}
							break;
						}
						iv.getTimer_l().setText(timerBuffer);
						if(sec == 0) {
							sec = 59;
							min--;
						} else {
							sec--;
						}
						try {
							this.sleep(1000);
						} catch(Exception e) {
						}
						i++;
					}
					iv.getTimer_l().setText("03:00");
					round--;
					iv.getRound_l().setText(Integer.toString(round) + "라운드");
					
				}

				round = 5;
				iv.getRound_l().setText("5 라운드");
				iv.getQuestion_tx().setText("");
				
//				db.rs.close();
//				db.st.close();
//				db.connection.close();
//			} catch (SQLException se1) {
//				se1.printStackTrace();
//			} catch (Exception ex) {
//				ex.printStackTrace();
//			} finally {
//				try {
//					if (db.st != null)
//						db.st.close();
//				} catch (SQLException se2) {
//				}
//				try {
//					if (db.connection != null)
//						db.connection.close();
//				} catch (SQLException se) {
//					se.printStackTrace();
//				}
//			}    
		}
		
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
	
//	// 알 필요 없음
//	public void setInfo(int num) {
//		iv.setChat(i[num].getInfo());
//	}
	
}
