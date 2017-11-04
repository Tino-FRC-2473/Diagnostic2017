import java.io.IOException;

public class SocketThread extends Thread {
	SpecialSocket ss;
	
	public SocketThread() throws IOException {
		ss = new SpecialSocket("10.19.80.131", 50007);
	}
	
	@Override
	public void run(){
		String s = ss.getLine();
		if(s != null && s.equals("s")) {
			ss.send("");
		}
	}
}
