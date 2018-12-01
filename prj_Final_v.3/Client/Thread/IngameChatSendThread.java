package Thread;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import View.IngameView;

public class IngameChatSendThread extends Thread {
	private Socket s;
	private String ID, msg;
	private DataOutputStream outputstream;
	private IngameView view;
	
	public IngameChatSendThread(Socket s, String msg, IngameView view){
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