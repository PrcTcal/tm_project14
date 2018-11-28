package Thread;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

public class MakeRoomThread extends Thread {

	private Socket s;
	private DataInputStream inputstream;
	private DataOutputStream outputstream;
	private String title, pw, roominfo;
	private Collection collection;
	private Iterator it;
	private HashMap hm;
	private String user;

	public MakeRoomThread(Socket s, HashMap hm, String user) {
		// TODO Auto-generated constructor stub
		this.s = s;
		this.hm = hm;
		this.user = user;
		try{
			inputstream = new DataInputStream(s.getInputStream());
			outputstream = new DataOutputStream(s.getOutputStream());      

			synchronized(hm) {
				hm.put(user, outputstream);
			}
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}



	public void run() {

		try{
			while(inputstream!=null) {
				title = inputstream.readUTF();
				pw = inputstream.readUTF();
				System.out.println(title+"    "+ pw);
				synchronized(hm) {
					collection = hm.values();
					it = collection.iterator();
				}
				try {
					while(it.hasNext()) {
						outputstream = (DataOutputStream)it.next();
						outputstream.writeUTF(title);
						outputstream.writeUTF(pw);
					}
				}catch(IOException e) {
					e.printStackTrace();
				}
			}

		}catch (IOException e) {
			e.printStackTrace();
		}finally {
			synchronized(hm) {
				hm.remove(user);

			}
			try {
				if(s!=null)
					s.close();
			}catch(Exception ex) {}
		}   
	}

}