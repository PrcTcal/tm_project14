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
		this.view = view;
		this.ID = ID;
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
				view.setChatText(msg);
				if(msg.contains("님이 정답을 맞추셨습니다")) {
					String[] temp = msg.split(">>");
					System.out.println("본인 ID= " + ID + "   들어온 ID=" + temp[0]);
					if(ID.equals(temp[0])) {
						view.getChat_tx().setEditable(false);
						view.getPaint_p().setEnabled(true);
						view.getPencil_bt().setEnabled(true);
						view.getEraser_bt().setEnabled(true);
						view.getEraseall_bt().setEnabled(true);
						view.getColorselect_bt().setEnabled(true);
						view.getLinethickness_l().setEnabled(true);
						view.getSetlinethickness_tx().setEnabled(true);
//						view.getPaintdraw_p().setEnabled(true);
						ic.set_connection(true);
						
					} else {
						view.getChat_tx().setEditable(true);
						view.getPaint_p().setEnabled(false);
						view.getPencil_bt().setEnabled(false);
						view.getEraser_bt().setEnabled(false);
						view.getEraseall_bt().setEnabled(false);
						view.getColorselect_bt().setEnabled(false);
						view.getLinethickness_l().setEnabled(false);
						view.getSetlinethickness_tx().setEnabled(false);
//						view.getPaintdraw_p().setEnabled(false);
						ic.set_connection(false);
					}
				}
			}
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}
}
