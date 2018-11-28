package Thread;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import View.MakeRoomView;

public class MakeRoomSendThread extends Thread {

	private Socket s;
	private MakeRoomView view;
	private DataOutputStream outputstream;
	private String ID, PW,num;
	
	public MakeRoomSendThread(Socket s, MakeRoomView view, String ID, String PW) {
		this.s = s;
		this.view = view;
		this.ID = ID;
		this.PW = PW;
		try {
			outputstream = new DataOutputStream(s.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		try {	
			outputstream.writeUTF(ID);
			outputstream.writeUTF(PW);
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}
	
	
	
}
