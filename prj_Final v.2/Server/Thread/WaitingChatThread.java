package Thread;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

import Server.WaitingServer;

public class WaitingChatThread extends Thread{

	private Socket s;
	private DataInputStream inputstream;
	private DataOutputStream outputstream;
	private String msg, ID;
	private Iterator it;
	private Collection collection;
	private HashMap hm;

	public WaitingChatThread(Socket s, HashMap hm, String ID) {
		// TODO Auto-generated constructor stub
		this.s = s;
		this.hm = hm;
		this.ID = ID;
		try {
			inputstream = new DataInputStream(s.getInputStream());
			outputstream = new DataOutputStream(s.getOutputStream());

			synchronized(hm) {
				hm.put(ID,outputstream);
			}

		}catch(IOException e) {
			e.printStackTrace();
		}
	}

	public void run() {
		try {

			while (inputstream != null) {
				msg = inputstream.readUTF();
				synchronized(hm) {
					collection = hm.values();
					it = collection.iterator();
				}
				try {
					while(it.hasNext()) {
						outputstream = (DataOutputStream)it.next();
						outputstream.writeUTF(ID + msg);
					}
				}catch(IOException e) {
					e.printStackTrace();
				}

			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			synchronized(hm) {
				hm.remove(ID);

			}
			try {
				if(s!=null)
					s.close();
			}catch(Exception ex) {}
		}	
	}
}

