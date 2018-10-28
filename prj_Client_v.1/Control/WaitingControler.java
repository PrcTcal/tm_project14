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
	
	private class ButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent event){
			// �游��� ��ư ���ý�
			if(event.getSource() == view.getMkr()) {
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
			} else if(event.getSource() == view.getExit()){
				app.offMusic();
				app.dispose();
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
							app.changePanel();
							app.setTitle("������ : " + ic.getTitle(i));
							// �����ص���
							ic.setInfo(w.getSelect());
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
		view.getMkr().addActionListener(listen);
        view.getExit().addActionListener(listen);
        view.getR(0).addActionListener(listen);
        view.getR(1).addActionListener(listen);
        view.getR(2).addActionListener(listen);
        view.getR(3).addActionListener(listen);
	}	
}
