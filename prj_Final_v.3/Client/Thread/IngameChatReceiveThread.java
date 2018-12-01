package Thread;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

import Control.IngameControler;
import View.IngameView;

public class IngameChatReceiveThread extends Thread {

	private Socket s;
	private String msg="";
	private IngameView view;
	private DataInputStream inputstream;
	private String ID;
	private IngameControler ic;

	public IngameChatReceiveThread(Socket s,IngameView view, String ID, IngameControler ic){
		this.s = s;
		this.ID = ID;
		this.view = view;
		this.ic = ic;
		try {
			inputstream = new DataInputStream(s.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}	

	public void run() {
		try {
			System.out.println(msg);
			while(inputstream!=null) {
				msg = inputstream.readUTF();
				
				if(msg.contains("님이 정답을 맞추셨습니다")) {
					view.repaint();
					String[] temp2 = msg.split(">>");
					ic.checkScore(temp2[0]);
					if(msg.contains("//")) {
						String temp[] = msg.split("//");
						msg = temp[0] + "\n";
						System.out.println(msg);
						view.getChat_tx().setEditable(true);
						view.getPaint_p().setEnabled(false);
						view.getPencil_bt().setEnabled(false);
						view.getEraser_bt().setEnabled(false);
						view.getEraseall_bt().setEnabled(false);
						view.getColorselect_bt().setEnabled(false);
						view.getSetlinethickness_tx().setEnabled(false);
						view.getQuestion_tx().setVisible(false);
						ic.set_connection(false);
					} else {
						String[] temp = msg.split(">>");
						System.out.println("본인 ID= " + ID + "   들어온 ID=" + temp[0]);
						if(ID.equals(temp[0])) {
							view.getChat_tx().setEditable(false);
							view.getPaint_p().setEnabled(true);
							view.getPencil_bt().setEnabled(true);
							view.getEraser_bt().setEnabled(true);
							view.getEraseall_bt().setEnabled(true);
							view.getColorselect_bt().setEnabled(true);
							view.getSetlinethickness_tx().setEnabled(true);
							ic.set_connection(true);
							view.getQuestion_tx().setVisible(true);

						} else {
							view.getChat_tx().setEditable(true);
							view.getPaint_p().setEnabled(false);
							view.getPencil_bt().setEnabled(false);
							view.getEraser_bt().setEnabled(false);
							view.getEraseall_bt().setEnabled(false);
							view.getColorselect_bt().setEnabled(false);
							view.getSetlinethickness_tx().setEnabled(false);
							ic.set_connection(false);
							view.getQuestion_tx().setVisible(false);
						}
					}
				}
				view.setChatText(msg);
				
				
			}
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}
}
