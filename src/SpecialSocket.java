import java.io.*;
import java.net.Socket;

public class SpecialSocket extends Socket {
	BufferedReader reader;
	PrintStream stream;
	
	public SpecialSocket(String host, int port) throws IOException {
		super(host, port);
		reader = new BufferedReader(new InputStreamReader(getInputStream()));
		stream = new PrintStream(getOutputStream());
	}
	
	public void sendLine(String s) {
		stream.print(s + "\n");
	}
	
	public String getLine() {
		try {
			if(reader.ready()) {
				return reader.readLine();
			}
		} catch (IOException e) {
			System.out.println(e.getStackTrace());
		}
		return null;
	}
}
