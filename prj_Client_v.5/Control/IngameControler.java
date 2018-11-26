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
	
	// timer thread ����� ����
	private boolean check = false;
	private int round = 5;
	
	private Ingame []i;		// ���� �� ��ü(Ingame ��)
	
	private class Mouse extends MouseAdapter{
		
		@Override
		public void mousePressed(MouseEvent e) {
			if(e.getSource() == iv.getMenubar_l()) {
				// �̺�Ʈ�� �߻��������� �� ��ǥ�� ����.
				iv.setMouseX(e.getX());
				iv.setMouseY(e.getY());
			} else if(e.getSource() == iv.getPaintdraw_p()) {
				iv.setStartX(e.getX());
				iv.setStartY(e.getY());
			
			} else if(e.getSource() == iv.getMenuexit_bt()) {
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
		
		// �巡�װ� �߻��������� �̺�Ʈó��.
		@Override
		public void mouseDragged(MouseEvent e)
		{
			// ���� ��ũ���� ��ǥ�� ����.
			int x = e.getXOnScreen();
			int y = e.getYOnScreen();

			// �������� ��ġ�� �巡���Ѱ����� �̵���Ŵ.
			app.setLocation(x - iv.getMouseX(), y - iv.getMouseY());
		}
		
		// �����ư���� ���콺�� �÷������� ����� ����.
		@Override
		public void mouseEntered(MouseEvent e) {
			if(e.getSource() == iv.getMenuexit_bt()) {
				// �����ư�� �������� exitentered_img���� ����.
				iv.getMenuexit_bt().setIcon(iv.getExitentered_img());
				// �����ư���� Ŀ���� ������ �ڵ�Ŀ���� ����.
				iv.getMenuexit_bt().setCursor(new Cursor(Cursor.HAND_CURSOR));
				// �����ư���� Ŀ���� �÷��� �� ������ ȿ���� ����.
				Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3", false);
				// ȿ���� ���.
				buttonEnteredMusic.start();
			} else if(e.getSource() == iv.getReady_bt()) {
				// �����ư�� �������� exitentered_img���� ����.
        		iv.getReady_bt().setIcon(iv.getReadyEntered_img());
        		// �����ư���� Ŀ���� ������ �ڵ�Ŀ���� ����.
        		iv.getReady_bt().setCursor(new Cursor(Cursor.HAND_CURSOR));
        		// �����ư���� Ŀ���� �÷��� �� ������ ȿ���� ����.
        		Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3", false);
        		// ȿ���� ���.
        		buttonEnteredMusic.start();
			} else if(e.getSource() == iv.getExit_bt()) {
				// �����ư�� �������� exitentered_img���� ����.
        		iv.getExit_bt().setIcon(iv.getExitEntered2_img());
        		// �����ư���� Ŀ���� ������ �ڵ�Ŀ���� ����.
        		iv.getExit_bt().setCursor(new Cursor(Cursor.HAND_CURSOR));
        		// �����ư���� Ŀ���� �÷��� �� ������ ȿ���� ����.
        		Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3", false);
        		// ȿ���� ���.
        		buttonEnteredMusic.start();
			}
		}
		
		/// �����ư���� ���콺�� ���������� ����� ����.
		@Override
		public void mouseExited(MouseEvent e) {
			if(e.getSource() == iv.getMenuexit_bt()) {
				// �����ư���� ���콺�� ���������� �������� exitbasic_img�� ����.
				iv.getMenuexit_bt().setIcon(iv.getExitbasic_img());
				// �����ư���� ���콺�� �������� Ŀ���� �⺻Ŀ���� ����.
				iv.getMenuexit_bt().setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			} else if(e.getSource() == iv.getReady_bt()) {
				// �����ư���� ���콺�� ���������� �������� exitbasic_img�� ����.
        		iv.getReady_bt().setIcon(iv.getReadyBasic_img());
        		// �����ư���� ���콺�� �������� Ŀ���� �⺻Ŀ���� ����.
        		iv.getReady_bt().setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			} else if(e.getSource() == iv.getExit_bt()) {
				// �����ư���� ���콺�� ���������� �������� exitbasic_img�� ����.
        		iv.getExit_bt().setIcon(iv.getExitBasic2_img());
        		// �����ư���� ���콺�� �������� Ŀ���� �⺻Ŀ���� ����.
        		iv.getExit_bt().setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
		}

	}
	
	private class ButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent event){
	
			// ���� ��ư ���ý�
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
				JOptionPane.showMessageDialog(null, "���Ƿ� �����ϴ�");
				// ���� ������ ������ ��ư�� ��Ȱ��ȭ �Ǿ�� ��. -> ���� ������ �� �����ϱ�.
				app.cards.show(app.getContentPane(), "Two");
			} else if(event.getSource() == iv.getReady_bt()) {
				iv.getChat_txa().append("������ �����մϴ�!\n");
				timer t = new timer();
				t.start();
				
			
				
				
			}
		}
	}
	
	// �׸��� �׸� �гο� ���� ���콺 ��Ǹ����� ����
		public class PaintDraw implements MouseMotionListener
		{

			// ���콺 �巡�� �޼ҵ�
			@Override
			public void mouseDragged(MouseEvent e) {
				// thickness�� setlinethickness_tx�� ����� ���ڿ����� ����ȭ���� ������.
				iv.setThickness(Integer.parseInt(iv.getSetlinethickness_tx().getText()));

				// ���� x��ǥ�� ���� ����.
				iv.setEndX(e.getX());

				// ���� y��ǥ�� ���� ����.
				iv.setEndY(e.getY());
				
				if(iv.getEndX() < 0 || iv.getEndY() < 0 || iv.getEndX() > 895 || iv.getEndY() > 455) {
		            return;
		        }

				iv.getGraphics2().setStroke(new BasicStroke(iv.getThickness(), BasicStroke.CAP_ROUND, 0));
				iv.getGraphics2().drawLine(iv.getStartX()+82, iv.getStartY()+100, iv.getEndX()+82, iv.getEndY()+100);

				iv.setStartX(iv.getEndX());
				iv.setStartY(iv.getEndY());

			}

			// ������� ������ implements �߱⿡ ���ܵ־���.
			@Override
			public void mouseMoved(MouseEvent e) 
			{

			}
		}

		// ����, ���찳, clear, �����濡 ���� �׼Ǹ�����
		public class ToolActionListener implements ActionListener {
			
			// �������̵��� actionPerformed�޼ҵ� ����
			public void actionPerformed(ActionEvent e)	{
				// ���ʹ�ư�� �������� �� if���� ��Ϲ����� ���� ����
				if(e.getSource() == iv.getPencil_bt()) { 
					if(iv.isTf() == false) {
						 // �׷����� ������ ������ ����
						iv.getGraphics2().setColor(Color.BLACK);
					} else {
						// �׷����� ������ selectedColor������ ������ ����
						iv.getGraphics2().setColor(iv.getSelectedColor());
					}
				// ���찳��ư�� �������� �� if���� ��Ϲ����� ���� ����
				} else if(e.getSource() == iv.getEraser_bt())	{
					// �׷����� ������ ������� ����� ������ ������� ���� �׷��� �������� ��ó�� ���̰� �Ѵ�.
					iv.getGraphics2().setColor(Color.WHITE);
				}
				else if(e.getSource() == iv.getColorselect_bt()) {
					// ���� ��������� �˷���.
					iv.setTf(true);
					// JColorChooser ����
					JColorChooser chooser = new JColorChooser();
					// ���õ� �÷����� selectedColor�� ����
					iv.setSelectedColor(chooser.showDialog(null, "Color", Color.orange));
					// ���� ���� ����.
					iv.getGraphics2().setColor(iv.getSelectedColor());
				}
				else
				{
					// �г��� �ʱ�ȭ�Ͽ� �ٽ� �׷��ش�.
					iv.repaint();
				}
			}
		}
	
	// ������ �޼ҵ�
	public IngameControler(Application app, IngameView iv) {
		this.app = app;
		this.iv = iv;
		db = new DB();
		// ���� �� ��ü�� 4�� �������ش�
		i = new Ingame[4];
		i[0] = new Ingame("1����");
		i[1] = new Ingame("2����");
		i[2] = new Ingame("3����");
		i[3] = new Ingame("4����");
		
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
	
	// Ÿ�̸� �޼ҵ� (���� �ʿ�)
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
						
						// �ð��� ��������
						if(min == -1) {
//							iv.getChat_txa().append("�����ڰ� �����ϴ�\n");
							break;
						// ����ó�� ��ȣ�� �޾��� ��(check�� ���� ������ ���� �� true�� �Ǵ� ������)
						} else if(check == true) {
//							iv.getChat_txa().append("�����Դϴ�!\n");
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
					iv.getRound_l().setText(Integer.toString(round) + "����");
					
				}

				round = 5;
				iv.getRound_l().setText("5 ����");
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
	
	// ���ӹ� ��ü�� ��й�ȣ�� �����´�
	public String getPW(int num) {
		return i[num].getPw();
	}
	
	// ���ӹ� ��ü�� ������ �����´�
	public String getTitle(int num) {
		return i[num].getTitle();
	}
	
	// ���ӹ� ��ü�� ������ �����Ѵ�
	public void setTitle(int num, String title) {
		i[num].setTitle(title);
	}
	
	// ���ӹ� ��ü�� ������ �����Ѵ�
	public void setPW(int num, String pw) {
		i[num].setPw(pw);
	}
	
//	// �� �ʿ� ����
//	public void setInfo(int num) {
//		iv.setChat(i[num].getInfo());
//	}
	
}
