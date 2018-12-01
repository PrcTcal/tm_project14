package Thread;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class TimerSendThread extends Thread{
	private Socket s;
	private DataOutputStream outputstream;
	private String r,t;
	
	public TimerSendThread(Socket s, String t, String r) {
		this.s = s;
		this.t = t;
		this.r = r;
		try {
			outputstream = new DataOutputStream(s.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void run() {

		try {
			System.out.println(t+"   "+r);
			outputstream.writeUTF("true");
			outputstream.writeUTF(t);
			outputstream.writeUTF(r);
		}catch(IOException e) {
			e.printStackTrace();
		}
	}


}
