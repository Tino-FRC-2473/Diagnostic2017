package org.usfirst.frc.team2473.robot;

import java.io.*;
import java.net.Socket;

public class SocketThread extends Thread {
	private SpecialSocket ss;
	private boolean alive;
	private int coordinatesStartLength = 13;
	private long lastTimeRV;
	private long start;
	
	public SocketThread() throws IOException {
		ss = new SpecialSocket("10.19.80.131", 50007);
		System.out.println("connected to server");
		alive = true;
		lastTimeRV = System.currentTimeMillis();
		start = lastTimeRV;
	}
	
	@Override
	public void run(){
		while(alive) {
			long currTime = System.currentTimeMillis();
			
			if (Robot.oi.button.get()) {
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
			
		}
		
		System.out.println("done");
	}
	
	public void requestValues() {
		ss.sendLine("function triggered");
	}
	
	public void end() {
		ss.sendLine("done");
		alive = false;
		System.out.println("ENDING");
		try {
			ss.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

class SpecialSocket extends Socket {
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
