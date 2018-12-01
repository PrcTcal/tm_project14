package Thread;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import Model.Ingame;
import View.IngameView;

// 
public class UsersInfoReceiveThread extends Thread {

	private DataInputStream inputstream;
	private DataOutputStream outputstream;
	private String ID, avatar;
	private int num, members;
	private Socket s;
	private IngameView iv;


	public UsersInfoReceiveThread(Socket s, IngameView iv) {
		this.s = s;
		this.ID = ID;
		this.avatar = avatar;
		this.iv = iv;
	
		try {
			inputstream = new DataInputStream(s.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	public void run() {
		int num;

		try {
			while(inputstream != null) {
				String msg = inputstream.readUTF();
				String[] tmp = msg.split(",");
				iv.setUserMembers(tmp[0], tmp[1]);
				 
//				ic.setTitle(w.getRoom_num(), title);
//				ic.setPW(w.getRoom_num(), pw);
//				view.getR(w.getRoom_num()).setEnabled(true);
//				view.getR(w.getRoom_num()).setText(title);				
//				w.addRoom_Num();
				
			}	
			

		} catch (IOException e) {

		}
	}
}
