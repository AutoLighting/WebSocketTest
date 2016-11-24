package socket;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CoordinateServerSocket extends Thread {
	
	public static CoordinateServerSocket instance;
	private ServerSocket serverSocket = null;
	
	private static int PORT = 10001;
	public static String COORD;
	
	static {
		instance = new CoordinateServerSocket();
		instance.start();
	}

	public void run(){
		try {
			
			serverSocket = new ServerSocket(PORT); 
	        System.out.println(getTime() + " : Server is ready.");
	         
	        Socket socket = serverSocket.accept(); 
	        InetAddress clientAddress = socket.getInetAddress(); 
	        System.out.println(getTime() + " : " + clientAddress + " Client connected.");
	        COORD = "Hello, " + clientAddress + ".";
	        
	        new Thread(new WorkerRunnable(socket)).start();
	       
		} catch(Exception e){
            e.printStackTrace();
        }
 	}
	
	private String getTime(){
        SimpleDateFormat f = new SimpleDateFormat("[yyyy-MM-dd hh:mm:ss]"); 
        return f.format(new Date());
    }
	
	private class WorkerRunnable implements Runnable {

	    protected Socket clientSocket = null;
	    protected String mMsgFromClient = null;

	    public WorkerRunnable(Socket clientSocket) {
	        this.clientSocket = clientSocket;
	    }

	    public void run() {
	        try {
	            BufferedReader in = new BufferedReader(new InputStreamReader(
	                    clientSocket.getInputStream()));

	            while ((mMsgFromClient = in.readLine()) != null) {
	            	COORD = getTime() + " : " + mMsgFromClient;
	            	System.out.println(COORD);
	            }
	            in.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }

	    }
	}

}