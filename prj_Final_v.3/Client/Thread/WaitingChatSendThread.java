package Thread;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

import View.WaitingView;

public class WaitingChatSendThread extends Thread {
	private Socket s;
	private String ID, msg;
	private DataOutputStream outputstream;
	private WaitingView view;
	
	public WaitingChatSendThread(Socket s, String msg, WaitingView view){
		this.s = s;
		this.msg = msg;
		this.view = view;
		try {
			outputstream = new DataOutputStream(s.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}	


	public void run() {
		
		
		try {
			outputstream.writeUTF(msg);
			view.setInitChatText();
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}
}