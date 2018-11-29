package Thread;

import java.awt.Color;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import Control.IngameControler;
import View.IngameView;

public class CanVasColorReceiveThread extends Thread{

   private DataInputStream inputstream;
   private DataOutputStream outputstream;
   private Socket s;   //�׸��� ���Ϻ���
   private IngameView iv;
   private IngameControler ic;
   private String ID, color;
   private Color s_color;
   
   public CanVasColorReceiveThread(Socket s,IngameView view,String ID) {
      this.s = s;
      this.iv=view;
      this.ID=ID;

      try {
         inputstream = new DataInputStream(s.getInputStream());
         outputstream=new DataOutputStream((s.getOutputStream())); 

      }
      catch(Exception e){
         e.printStackTrace();
      }
   }






   public void run()  {
      try {
         while(inputstream!=null) {

            color=inputstream.readUTF();
            
         //   System.out.println(color);

            //            Object obj=color;
            //            System.out.println(obj);

            if(color.equals("����") ) { 
               
                  // �׷����� ������ ������ ����
               iv.getGraphics2().setColor(Color.BLACK);

               
               // ���찳��ư�� �������� �� if���� ��Ϲ����� ���� ����
            } else if(color.equals("���찳"))   {
               // �׷����� ������ ������� ����� ������ ������� ���� �׷��� �������� ��ó�� ���̰� �Ѵ�.
               iv.getGraphics2().setColor(Color.WHITE);

            }else if(color.equals("clear"))
            {
               // �г��� �ʱ�ȭ�Ͽ� �ٽ� �׷��ش�.
               iv.repaint();

            }else {
               // ���� ��������� �˷���.
               iv.setTf(true);
               // JColorChooser ����
               //JColorChooser chooser = new JColorChooser();
               // ���õ� �÷����� selectedColor�� ����
               //   iv.setSelectedColor(chooser.showDialog(null, "Color", Color.orange));

               // ���� ���� ����.
               //   System.out.println(color);
            //   java.awt.Color[r=255,g=255,b=255]
               String arr[]=color.split(",");
               
            
               s_color=new Color(Integer.parseInt(arr[0]),Integer.parseInt(arr[1]),Integer.parseInt(arr[2]));
               
               try {
                  iv.getGraphics2().setColor(s_color);
//                  iv.getGraphics2().setColor(Color.decode("#"+red+green+blue));
                  
               }catch(Exception e) {
                  e.printStackTrace();
                  System.out.println("���� Ȯ��");
               }
            }
         }
      }catch(IOException e){
         e.printStackTrace();
      }
   }
}



