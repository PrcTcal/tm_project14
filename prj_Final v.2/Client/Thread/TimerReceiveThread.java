package Thread;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

import View.IngameView;

public class TimerReceiveThread extends Thread{

	private Socket s;
	private IngameView iv;
	private DataInputStream inputstream;
	
	public TimerReceiveThread(Socket s, IngameView iv) {
		this.s = s;
		this.iv = iv;
	
		try {
			inputstream = new DataInputStream(s.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void run() {
		
		try {
			while(inputstream != null) {
				String msg = inputstream.readUTF();
				String[] tmp = msg.split(",");
			    iv.getQuestion_tx().setText(tmp[1]);
			    iv.getTimer_l().setText(tmp[0]);
			    iv.getRound_l().setText(tmp[2] + "¶ó¿îµå");
			}	
			

		} catch (IOException e) {

		}
	}
}


	
	
	


