import java.io.IOException;

public class Driver {
	
	public static void main(String[] args) {
		try {
			SocketThread thread = new SocketThread();
			thread.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
