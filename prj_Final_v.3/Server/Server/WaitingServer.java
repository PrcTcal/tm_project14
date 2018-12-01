package Server;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

import Thread.WaitingChatThread;
import Thread.MakeRoomThread;
import Thread.RankingThread;

public class WaitingServer extends Thread{

	private Socket client_chatting = null;
	private Socket client_making = null;
	private Socket client_ranking = null;
	private ServerSocket server =null;
	private RankingThread rt = null;
	private WaitingChatThread ct = null;
	private MakeRoomThread mrt = null;
	private DataInputStream inputstream = null;
	private String ID;	
	private HashMap hm_c;
	private HashMap hm_mr;

	public void run() {
		try {
			server = new ServerSocket(LoginRegisterServer.getPort()+1);   
			hm_c = new HashMap();
			hm_mr = new HashMap();
			while(true) {
				client_ranking = server.accept();
				inputstream = new DataInputStream(client_ranking.getInputStream());
				ID = inputstream.readUTF();
				                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                  
				client_chatting = server.accept();
				client_making = server.accept();
				
				rt = new RankingThread(client_ranking);
				ct = new WaitingChatThread(client_chatting,hm_c,ID);
				mrt = new MakeRoomThread(client_making,hm_mr,ID);
				
				rt.start();
				ct.start();
				mrt.start();
						
				
			}   
		}catch(IOException e) {
			e.printStackTrace();
		}
	}   




}