package Thread;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

import View.WaitingView;

public class WaitingChatRecieveThread extends Thread {

	private Socket s;
	private String msg="";
	private WaitingView view;
	private DataInputStream inputstream;

	public WaitingChatRecieveThread(Socket s,WaitingView view){
		this.s = s;
		this.view = view;
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
			}
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}
}
