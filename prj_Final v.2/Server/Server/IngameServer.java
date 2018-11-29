package Server;


import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.LinkedHashMap;

import Thread.CanVasColorThread;
import Thread.CanVasThread;
import Thread.IngameChatThread;
import Thread.TimerThread;
import Thread.UserInfoThread;
import Thread.AnswerRound;

public class IngameServer {

	private Socket client_chatting = null;
	private Socket client_thickness = null;
	private Socket client_color = null;
	private Socket client_users = null;
	private Socket client_timer = null;
	private ServerSocket server =null;
	private CanVasThread cvt=null;
	private CanVasColorThread cvct=null;
	private DataInputStream inputstream,inputstream1;
	private String ID,avatar,receive;
	private HashMap hm_c, hm_i, hm_t, hm_ch, hm_d;
	private LinkedHashMap lhm_u;
	private UserInfoThread uit;
	private IngameChatThread ict;
	private TimerThread tt;
	private AnswerRound ar;

	public void settingInGame() {
		try {
			server = new ServerSocket(LoginRegisterServer.getPort()+2); 
			lhm_u = new LinkedHashMap();

			// ÄÃ·¯
			hm_c = new HashMap();
			// ÁÂÇ¥
			hm_t = new HashMap();

			hm_ch = new HashMap();

			hm_d = new HashMap();
			ar = new AnswerRound();
			while(true) {
				client_users = server.accept();
				inputstream = new DataInputStream(client_users.getInputStream());
				ID = inputstream.readUTF();
				avatar = inputstream.readUTF();

				client_thickness = server.accept();
				client_color = server.accept();
				client_chatting = server.accept();
				client_timer = server.accept();

				//				client_tool = server.accept();
				//				client_drawing = server.accept();


				
				uit = new UserInfoThread(client_users, lhm_u, ID,avatar);
				cvt=new CanVasThread(client_thickness,hm_t,ID);
				cvct=new CanVasColorThread(client_color,hm_c,ID);
				ict = new IngameChatThread(client_chatting, hm_ch, ID, ar);

				uit.start();
				cvt.start();
				cvct.start();
				ict.start();

				tt = new TimerThread(client_timer, ar, hm_d, ID);
				tt.start();


				//			client_tool = server.accept();
				//			client_drawing = server.accept();
				//			ct = new ChatThread(client_chatting);
				//			ct.start();
			}   
		}catch(IOException e) {
			e.printStackTrace();
		}
	}   



	public static void main(String[] args) {
		IngameServer ingameserver = new IngameServer();
		ingameserver.settingInGame();
	}


}