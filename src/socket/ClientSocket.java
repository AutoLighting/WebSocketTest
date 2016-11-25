package socket;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientSocket {

	/**
	 * 이건 웹 서버가 아니고 java로 실행시켜야 함!!
	 * @param args
	 */
	public static void main(String[] args) {
        try{
            String ip = "172.31.136.241"; //클라이언트의 임시 접속 주소
        	
        	//String ip = "localhost"; //클라이언트의 임시 접속 주소
            int port = 10001; //접속할 서버 포트
            Socket socket = new Socket(ip, port); 
            
            BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
            PrintWriter pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream())); 
        
            String myMsg = null; 
         
            for(int i=0; i<30; i++) {
            	
            	pw.println(i + " " + (i*2)); 
                pw.flush(); 
            	Thread.sleep(500);
            	
            }
            /*
            while((myMsg = input.readLine()) != null){
            	
                if(myMsg.equals("/q")){
                    break; 
                }
                pw.println(myMsg); 
                pw.flush(); 
            }*/
             
            pw.close();
            input.close();
            socket.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
	
}
