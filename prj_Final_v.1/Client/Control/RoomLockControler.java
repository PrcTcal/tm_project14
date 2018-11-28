package Control;
import View.RoomLockView;
import Control.WaitingControler;
import Control.IngameControler;
import java.awt.event.*;
import javax.swing.*;
import Starter.Application;
public class RoomLockControler {
	private Application app;
	private ButtonListener listen;
	private RoomLockView view;
	private WaitingControler wc;
	private IngameControler ic;
	
	private class ButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent event){
			// Ȯ�� ��ư ���ý�
			if(event.getSource() == view.getOk()) {
				// �Է��� ��й�ȣ�� �����Ϸ��� ���� ��й�ȣ�� ������
				if(ic.getPW(wc.getSelect()).equals(view.getPW())) {
					JOptionPane.showMessageDialog(null, "�濡 �����մϴ�");
					app.changePanel();
					app.setTitle("������ : " + ic.getTitle(wc.getSelect()));
					//ic.setInfo(wc.getSelect());
					view.getN().dispose();
				// �Է��� ��й�ȣ�� �����Ϸ��� ���� ��й�ȣ�� Ʋ����
				} else {
					JOptionPane.showMessageDialog(null, "��й�ȣ�� Ʋ���ϴ�");
					view.resetPW();
				}
			// ��� ��ư ���ý�
			} else {
				view.getN().dispose();
			}
		}
		
	}
	
	// ������ �޼ҵ�
	public RoomLockControler(Application app, RoomLockView rv, WaitingControler wc, IngameControler ic) {
		this.app = app;
		view = rv;
		this.wc = wc;
		this.ic = ic;
	}
	
	public void buttonHandler() {
		listen = new ButtonListener();
		view.getOk().addActionListener(listen);
		view.getCancel().addActionListener(listen);
	}
}
