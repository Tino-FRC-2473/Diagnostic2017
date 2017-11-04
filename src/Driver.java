import java.io.IOException;

public class Driver {
	
	public static void main(String[] args) {
		try {
			SocketThread responder = new SocketThread();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
