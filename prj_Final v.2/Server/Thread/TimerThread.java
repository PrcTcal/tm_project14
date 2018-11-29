package Thread;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import DB.DB;

public class TimerThread extends Thread{
	private int min, sec, time;
	private Socket s;
	private String ID,timerBuffer, receive;
	private DataInputStream inputstream, inputstream2;
	private DataOutputStream outputstream, outputstream2;
	private Iterator it;
	private Collection collection;
	private HashMap hm, hm2;
	private DB db;
	private int round = 1;
	private AnswerRound ar;

	public TimerThread(Socket s, AnswerRound ar, HashMap hm, String ID) {
		min = 2;
		sec = 59;
		time = 0;
		this.ar = ar;
		db = new DB();
		this.ID = ID;
		this.s = s;
		this.hm = hm;
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

		String[] sqlRecipeProcess_word = new String[5];
		try {
			
			
			db.connect();
			db.read("select word from theme1;");

			for(int k = 0 ; k < 5 ; k++) {
				db.rs.next();
				sqlRecipeProcess_word[k] = db.rs.getString("word");
			}

			while(inputstream != null) {
				receive = inputstream.readUTF();
				

				if(receive.equals("true")) {
					while(round < 6) {
						ar.setRound(round);
						ar.setAnswer(sqlRecipeProcess_word[round - 1]);
						broadcast("03:00,"+sqlRecipeProcess_word[round - 1]+","+String.valueOf(round));

						while(time < 180){
							timerBuffer = String.format("%02d:%02d", min, sec);

							// �ð��� ��������
							if(min == -1) {
								//                   iv.getChat_txa().append("�����ڰ� �����ϴ�\n");
								break;
								// ����ó�� ��ȣ�� �޾��� ��(check�� ���� ������ ���� �� true�� �Ǵ� ������)
							} else if(ar.isCheck() == true) {
								//                   iv.getChat_txa().append("�����Դϴ�!\n");
								//iv.getTimer_l().setText("03:00");

								ar.setCheck(false);

								break;
							}

							broadcast(timerBuffer + "," + sqlRecipeProcess_word[round - 1] +","+ String.valueOf(round));
							//---------------------------------------------

							if(sec == 0) {
								sec = 59;
								min--;
							} else {
								sec--;
							}
							try {
								this.sleep(1000);
							} catch(Exception e) {
							}
							time++;
						}

						time = 0;
						min = 3;
						sec = 0;

						// �� ����(180��)�� ������ �ٽ� Ÿ�̸� �����ϰ� ���� �ϳ� �ٿ��ִ� �κ�
						round++;
						if(round < 6) {
							broadcast("03:00," + sqlRecipeProcess_word[round - 1] +","+ String.valueOf(round));
						} else {
							// ���� ������ ���� �ʱ�ȭ ��Ű�� �κ�
							round = 1;
							//-------------------------------------
							broadcast("03:00," + "" +","+ String.valueOf(round));

							System.out.println("���� ��");
							break;
						}
						//--------------------------------------------------------
					}
					receive = "false";
				}

			}



			db.rs.close();
			db.st.close();
			db.connection.close();
		} catch (SQLException se1) {
			se1.printStackTrace();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (db.st != null)
					db.st.close();
			} catch (SQLException se2) {
			}
			try {
				if (db.connection != null)
					db.connection.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}    
	}
	
	 public void broadcast(String time) {
	      synchronized(hm) {
	         Collection collection =hm.values();
	         Iterator iter=collection.iterator();
	         try {
	         while(iter.hasNext()) {
	             outputstream=(DataOutputStream)iter.next();
	            outputstream.writeUTF(time);
	         }
	         }catch(IOException e) {
	            e.printStackTrace();
	         }
	      }
	 }


}