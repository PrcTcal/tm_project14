package Control;
import javax.swing.*;
import Starter.Application;
import Model.DB;
import Model.waiting;
import Model.Music;
import View.RegisterView;
import View.WaitingView;
import View.MakeRoomView;
import View.RoomLockView;
import Control.MakeRoomControler;
import Control.IngameControler;
import Control.RoomLockControler;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
public class WaitingControler {
	private Application app;
	private waiting w;	// ���� �� ��ü
	private ButtonListener listen;
	private WaitingView view;
	private MakeRoomView mv;
	private MakeRoomControler mc;
	private IngameControler ic;
	private RoomLockView rv;
	private RoomLockControler rc;
	private DB db;
	private Mouse mouse;
	private String sendchat_s, receivechat_s;
	
	private class Mouse extends MouseAdapter{
		// �����ư���� ���콺�� �÷������� ����� ����.
		@Override
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
			} else if(e.getSource() == view.getUserexit_bt()){
				// �����ư�� �������� exitentered_img���� ����.
				//userexit_bt.setIcon(exitentered_img);
				// �����ư���� Ŀ���� ������ �ڵ�Ŀ���� ����.
				//userexit_bt.setCursor(new Cursor(Cursor.HAND_CURSOR));
				// �����ư���� Ŀ���� �÷��� �� ������ ȿ���� ����.
				Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3", false);
				// ȿ���� ���.
				buttonEnteredMusic.start();
			}
		}

		/// �����ư���� ���콺�� ���������� ����� ����.
		@Override
		public void mouseExited(MouseEvent e){
			if(e.getSource() == view.getExit_bt()) {
				// �����ư���� ���콺�� ���������� �������� exitbasic_img�� ����.
				view.getExit_bt().setIcon(view.getExitbasic_img());
				// �����ư���� ���콺�� �������� Ŀ���� �⺻Ŀ���� ����.
				view.getExit_bt().setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			} else {
				// �����ư���� ���콺�� ���������� �������� exitbasic_img�� ����.
				//userexit_bt.setIcon(exitbasic_img);
				// �����ư���� ���콺�� �������� Ŀ���� �⺻Ŀ���� ����.
				//userexit_bt.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
		}

		// �����ư�� Ŭ���������� ����� ����.
		@Override
		public void mousePressed(MouseEvent e){
			if(e.getSource() == view.getMenubar_l()) {
				// �̺�Ʈ�� �߻��������� �� ��ǥ�� ����.
				view.setMouseX(e.getX());
				view.setMouseY(e.getY());
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
				// ���α׷��� �����Ŵ.
				System.exit(0);
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
			app.setLocation(x - view.getMouseX(), y - view.getMouseY());
		}
		
	}
	
	private class ButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent event){
			// �游��� ��ư ���ý�
			if(event.getSource() == view.getMkr_bt()) {
				// ������ ���� 4�� �̸��϶�
				if(w.getRoom_num() < 4) {
					// �游��� â ����
					mv = new MakeRoomView();
					mc = new MakeRoomControler(app,mv,WaitingControler.this,ic);
					mc.buttonHandler();
				// ������ ���� 4�� �̻��϶�
				} else {
					JOptionPane.showMessageDialog(null, "�� �̻� ���� ������ �� �����ϴ�!");
				}
			// ���� ��ư ���ý�
			} else if(event.getSource() == view.getExit_bt()){
				app.offMusic();
				app.dispose();
			} else if(event.getSource() == view.getChatfield_tf()) {
				sendchat_s = view.getChatfield_tf().getText();
				view.getChatarea_ta().append(sendchat_s + "\n");
				view.getChatarea_ta().setCaretPosition(view.getChatarea_ta().getDocument().getLength());
				view.getChatfield_tf().setText("ID : ");
				view.getChatfield_tf().setCaretPosition(view.getChatfield_tf().getText().length());
			// �� ��ư ���ý�
			} else {
				// 4���� ���߿��� ���õ� ���� � ������ ã�´�.
				for(int i = 0 ; i < 4 ; i++) {
					if(event.getSource() == view.getR(i)) {
						// ���� �𵨿� ������ ���� ��������� ����
						w.setSelect(i);
						// ������ �濡 ��й�ȣ�� ���������� ������
						if(ic.getPW(i).equals("")){
							JOptionPane.showMessageDialog(null, "�濡 �����մϴ�");
							app.cards.show(app.getContentPane(), "Three");
							app.setSize(1280,720);
							app.setTitle("������ : " + ic.getTitle(i));
							// �����ص���
							//ic.setInfo(w.getSelect());
						// ������ �濡 ��й�ȣ�� ���� ��������
						} else {
							// ��й�ȣ �Է� â ����
							rv = new RoomLockView();
							rc = new RoomLockControler(app,rv,WaitingControler.this, ic);
							rc.buttonHandler();
						}
					}
				}
				
			}
		}
	}
	
	// �Ķ���ͷ� ���ڿ��� �޾� �� �������� �������ְ� �� ��ư�� Ȱ��ȭ ��Ű�� �޼ҵ�
	public void Enable_Room(String title) {
		view.getR(w.getRoom_num()).setEnabled(true);
		view.getR(w.getRoom_num()).setText(title);
		w.addRoom_Num();
	}
	
	public int getRoom_num() {
		return w.getRoom_num();
	}
	
	public int getSelect() {
		return w.getSelect();
	}
	
	// ������ ����ǥ�� �ֽ�ȭ �����ִ� �޼ҵ�
	public void update_rank() {
		try {
			db.connect();
			int i = 1;
			int j=0;
			// DBMS�� "account ���̺��� id, score�� ���� ���������� �����ض�"��� ��ɾ ������ �� ������� rs�� ����
			db.read("select id,score from account order by score desc;");

			// DB���� ������ id�� score�� ������� ����ǥ ���̺�(JTable)�� ����, ID, ���� ������ ����
			while(db.rs.next()) {
				String sqlRecipeProcess_id = db.rs.getString("id");
				String sqlRecipeProcess_score = db.rs.getString("score");
				view.getTable().setValueAt(i, j, 0);
				view.getTable().setValueAt(sqlRecipeProcess_id, j,1);
				view.getTable().setValueAt(sqlRecipeProcess_score, j, 2);
				i++;
				j++;
			}
			// ����ǥ ���̺�(JTable)�� ������ �ֽ�ȭ
			view.getTable().updateUI();
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
	}
	
	// ������ �޼ҵ�
	public WaitingControler(Application app, WaitingView wv, IngameControler ic) {
		db = new DB();
		w = new waiting();
		this.app = app;
		this.view = wv;
		this.ic = ic;
	}
	
	public void buttonHandler() {
		listen = new ButtonListener();
		mouse = new Mouse();
		
		view.getMkr_bt().addActionListener(listen);
        view.getExit_bt().addActionListener(listen);
        view.getR(0).addActionListener(listen);
        view.getR(1).addActionListener(listen);
        view.getR(2).addActionListener(listen);
        view.getR(3).addActionListener(listen);
        view.getChatfield_tf().addActionListener(listen);
        
        view.getExit_bt().addMouseListener(mouse);
        view.getMenubar_l().addMouseListener(mouse);
        view.getMenubar_l().addMouseMotionListener(mouse);
        view.getUserexit_bt().addMouseListener(mouse);
	}	
}
