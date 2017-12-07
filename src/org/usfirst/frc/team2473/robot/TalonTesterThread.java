package org.usfirst.frc.team2473.robot;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class TalonTesterThread extends Thread {
	private boolean alive;
	
	private double sumTotal;
	private ArrayList<Double> sumRange;
	private int port = 50007;
	private String host = "";
	private ServerSocket server;
	private Socket socket;
	private PrintStream ps;
	public TalonTesterThread() {
		alive = true;

		sumTotal = 0;
		sumRange = new ArrayList<Double>();
		try {
			server = new ServerSocket(port);
			System.out.println("waiting for client connection");
			socket = server.accept();
			System.out.println("connected");
			ps = new PrintStream(socket.getOutputStream());
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		while(alive) {
			String str = System.currentTimeMillis() + " " + (-Robot.train.getSpeed()) + " " + Robot.train.getCurrent();
			System.out.println(str);
			ps.print(str + "\n");
			
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void kill() {
		alive = false;
	}
}
