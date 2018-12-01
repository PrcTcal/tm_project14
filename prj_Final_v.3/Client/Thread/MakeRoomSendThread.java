package Thread;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import View.MakeRoomView;

public class MakeRoomSendThread extends Thread {

	private Socket s;
	private MakeRoomView view;
	private DataOutputStream outputstream;
	private String ID, PW,num, theme, round;
	
	public MakeRoomSendThread(Socket s, MakeRoomView view, String ID, String PW, String round, String theme) {
		this.s = s;
		this.view = view;
		this.ID = ID;
		this.PW = PW;
		this.round = round;
		this.theme = theme;
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
			outputstream.writeUTF(String.valueOf(round));
			outputstream.writeUTF(theme);
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}
	
	
	
}
