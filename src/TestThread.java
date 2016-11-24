
public class TestThread extends Thread{
	public static TestThread instance;
	static {
		instance = new TestThread();
		instance.start();
	}
	public static int i=0;
	public void run(){
		while(true) {
			try {
				Thread.sleep(500);
				++i;
			} catch(Exception ex) {
				ex.printStackTrace();
			}
			
		}
 	}
}
