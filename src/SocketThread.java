import java.io.IOException;

public class SocketThread extends Thread {
	private UtilitySocket ss;
	private boolean alive;
	private int coordinatesStartLength = 13;
	private long lastTimeRV;
	private long delayRV = 1000;
	private long start;
	
	private static final String MV_COMP_IP = "10.19.80.131"; //fuhsd
	private static final String JETSON_IP = "10.19.48.81"; //fuhsd_guests
	
	public SocketThread() throws IOException {
		ss = new UtilitySocket(JETSON_IP, 50007);
		System.out.println("connected to server");
		alive = true;
		lastTimeRV = System.currentTimeMillis();
		start = lastTimeRV;
	}
	
	@Override
	public void run(){
		while(alive) {
			long currTime = System.currentTimeMillis();
			
			if (currTime - lastTimeRV > delayRV) {
				lastTimeRV = currTime;
				requestValues();
				System.out.println("requesting values");
			}
			
			String line = ss.getLine();
			
			if(line != null) {
				System.out.println("received " + line);
				
				if(line.equals("s")) {
					System.out.println("server ping received");
					ss.sendLine("c");
				} else {
					if (line.substring(0, coordinatesStartLength).equals("coordinates: ")) {
						System.out.println("coordinates received");
					}
				}
			}
			
			if(currTime - start > 3000) {
				ss.sendLine("done");
				end();
				alive = false;
			}
		}
		
		System.out.println("done");
	}
	
	public void requestValues() {
		ss.sendLine("function triggered");
	}
	
	public void end() {
		System.out.println("ENDING");
		try {
			ss.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
