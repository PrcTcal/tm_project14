package Thread;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class TimerSendThread extends Thread{
	private Socket s;
	private DataOutputStream outputstream;
	
	public TimerSendThread(Socket s) {
		this.s = s;
		try {
			outputstream = new DataOutputStream(s.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void run() {

		try {
			outputstream.writeUTF("true");
			
		}catch(IOException e) {
			e.printStackTrace();
		}
	}


}
