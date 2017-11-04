import java.io.*;
import java.net.Socket;

public class SpecialSocket extends Socket {
	BufferedReader r;
	PrintStream p;
	
	public SpecialSocket(String host, int port) throws IOException {
		super(host, port);
		r = new BufferedReader(new InputStreamReader(getInputStream()));
		p = new PrintStream(getOutputStream());
	}
	
	public void send(String s) {
		p.println(s + "\n");
	}
	
	public String getLine() {
		try {
			return r.readLine();
		} catch (IOException e) {
			return null;
		}
	}
}
