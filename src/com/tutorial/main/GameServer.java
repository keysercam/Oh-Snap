package com.tutorial.main;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class GameServer extends Thread {
	private Game game;
	private DatagramSocket socket;
	private InetAddress ipAddress;
	
	public GameServer(Game game) {
	this.game = game;
		try {
			this.socket = new DatagramSocket(24601);	
		  } catch (SocketException e) {
			  e.printStackTrace();
		  } 
		
	}
	
	public void run() {
		while (true) {
			byte[] data = new byte[1024];
			DatagramPacket packet = new DatagramPacket(data, data.length);
			try {
				socket.receive(packet);
			} catch (IOException e) {
				e.printStackTrace();
			}
			String message = new String(packet.getData());
			if (message.equalsIgnoreCase("ping")) {
				System.out.println("CLIENT > " + message); 
				sendData("pong".getBytes(), packet.getAddress(), packet.getPort());
			}
			
		}
	}
	
	public void sendData(byte[] data, InetAddress ipAddress, int port) {
		DatagramPacket packet = new DatagramPacket(data, data.length, ipAddress, 24601);
		try {
			socket.send(packet);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
