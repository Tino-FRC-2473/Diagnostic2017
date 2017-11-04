import java.io.IOException;

public class SocketThread extends Thread {
	SpecialSocket specialRocket;
	private boolean alive;
	private int coordinatesStartLength = 13;
	private long lastTimeRV;
	private long delayRV = 1000;
	
	public SocketThread() throws IOException {
		specialRocket = new SpecialSocket("10.19.80.131", 50007);
		System.out.println("connected to server");
		alive = true;
		lastTimeRV = System.currentTimeMillis();
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
			
			String fling = specialRocket.getLine();
			
			if(fling != null) {
				System.out.println("received " + fling);
				
				if(fling.equals("s")) {
					System.out.println("server ping received");
					specialRocket.send("c");
				} else {
					if (fling.substring(0, coordinatesStartLength).equals("coordinates: ")) {
						System.out.println("coordinates received");
					}
				}
			}
		}
		
		System.out.println("dead");
	}
	
	public void requestValues() {
		specialRocket.send("function triggered");
	}
}
