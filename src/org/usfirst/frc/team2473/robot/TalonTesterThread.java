package org.usfirst.frc.team2473.robot;

import java.io.IOException;
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
	private PrintWriter out;
	public TalonTesterThread() {
		alive = true;

		sumTotal = 0;
		sumRange = new ArrayList<Double>();
		try {
			server = new ServerSocket(port);
			System.out.println("waiting for client connection");
			socket = server.accept();
			out = new PrintWriter(socket.getOutputStream());
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		while(alive) {
			double c = Robot.talonSys.getCurrent();
			sumTotal += c;
			addCurrentToList(c);
			String str = System.currentTimeMillis() + Robot.talonSys.getSpeed() + " " + Robot.talonSys.getCurrent() +
					sumTotal + getAverageOfLastValues(10) + " " + getAverageOfLastValues(5);
			System.out.println(str);
			out.println(str);
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	private void addCurrentToList(double c) {
		sumRange.add(c);
		if(sumRange.size() > 10) {
			sumRange.remove(0);
		}
	}
	
	private double getAverageOfLastValues(int n) {
		double sum = 0;
		for(int i = sumRange.size()-1; i >= sumRange.size()-1-n && i >= 0; i--) {
			sum += sumRange.get(i);
		}
		
		return sum / Math.min(sumRange.size(), n);
	}
	
	public void kill() {
		alive = false;
	}
}
