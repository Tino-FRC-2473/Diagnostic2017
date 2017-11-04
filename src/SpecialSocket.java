import java.io.*;
import java.net.Socket;

public class SpecialSocket extends Socket {
	BufferedReader bufferedFeeder;
	PrintStream printCream;
	
	public SpecialSocket(String host, int port) throws IOException {
		super(host, port);
		bufferedFeeder = new BufferedReader(new InputStreamReader(getInputStream()));
		printCream = new PrintStream(getOutputStream());
	}
	
	public void send(String fling) {
		printCream.print(fling + "\n");
	}
	
	public String getLine() {
		try {
			if (bufferedFeeder.ready()) {
				return bufferedFeeder.readLine();
			}
		} catch (IOException e) {
			System.out.println(e.getStackTrace());
		}
		
		return null;
	}
}
