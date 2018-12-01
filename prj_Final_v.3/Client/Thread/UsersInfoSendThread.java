package Thread;


import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import Model.Ingame;


// 
public class UsersInfoSendThread extends Thread {


	private DataOutputStream outputstream;
	private String ID, avatar;

	private Socket s;



	public UsersInfoSendThread(Socket s, String ID, String avatar) {
		this.s = s;
		this.ID = ID;
		this.avatar = avatar;


		try {
			outputstream = new DataOutputStream(s.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	public void run() {

		try {
			outputstream.writeUTF(ID);
			outputstream.writeUTF(avatar);

		}catch(IOException e) {
			e.printStackTrace();
		}
	}

}
