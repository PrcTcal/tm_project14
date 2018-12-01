package Thread;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Set;

public class UserInfoThread extends Thread{

	private Socket s;
	private DataInputStream inputstream;
	private DataOutputStream outputstream;
	private String avatar, ID;
	private Iterator it;
	private Collection collection;
	private LinkedHashMap lhm;

	public UserInfoThread(Socket s, LinkedHashMap lhm, String ID, String avatar) {
		// TODO Auto-generated constructor stub
		this.s = s;
		this.lhm = lhm;
		this.ID = ID;
		this.avatar = avatar;
		try {
			inputstream = new DataInputStream(s.getInputStream());
			outputstream = new DataOutputStream(s.getOutputStream());

			synchronized(lhm) {
				lhm.put(ID+","+avatar,outputstream);
			}
			System.out.println(ID+" "+avatar);
		}catch(IOException e) {
			e.printStackTrace();
		}
	}

	public void run() {
		try {

				synchronized(lhm) {
					collection = lhm.values();
					it = collection.iterator();
				}
				try {
					while(it.hasNext()) {
						outputstream = (DataOutputStream)it.next();
						System.out.println(outputstream);
						if(lhm.get(ID+","+avatar).equals(outputstream)){
							Set key = lhm.keySet();
							Iterator<String> kit = key.iterator();
							System.out.println("µé¾î¿È");
							while(kit.hasNext()){
								String s = kit.next();
								System.out.println(s);
								outputstream.writeUTF(s);
							}
							break;   
						}
						
						outputstream.writeUTF(ID+","+avatar); //ÇØ½¬¸ÊÅ°°ª

					}
				}catch(IOException e) {
					e.printStackTrace();
				}

		} catch (Exception e) {
			e.printStackTrace();
//		} finally {
//			synchronized(lhm) {
//				lhm.remove(ID);
//
//			}
//			try {
//				if(s!=null)
//					s.close();
//			}catch(Exception ex) {}
		}	
	}
}