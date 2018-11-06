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

import Model.Ingame;
import Model.Music;
import Starter.Application;
import View.IngameView;

public class IngameControler {
	private Application app;
	private IngameView iv;
	//private ButtonListener listen;
	private Mouse mouse;
	private ToolActionListener tool;
	private PaintDraw paint;
	
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
				// ���α׷��� �����Ŵ.
				app.changePanel();
				app.changePanel();
				app.setTitle("����");
				//System.exit(0);
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
			}
		}

	}
	
	private class ButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent event){
	
			// ���� ��ư ���ý�
			if(event.getSource() == iv.getExit_bt()) {
				JOptionPane.showMessageDialog(null, "���Ƿ� �����ϴ�");
				app.changePanel();
				app.changePanel();
				app.setTitle("����");
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
		iv.getMenuexit_bt().addMouseListener(mouse);
		iv.getPaintdraw_p().addMouseListener(mouse);
		iv.getPaintdraw_p().addMouseMotionListener(paint);
		
		iv.getPencil_bt().addActionListener(tool);
		iv.getEraser_bt().addActionListener(tool);
		iv.getEraseall_bt().addActionListener(tool);
		iv.getColorselect_bt().addActionListener(tool);
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
