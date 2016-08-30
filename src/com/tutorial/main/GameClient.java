package com.tutorial.main;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class GameClient extends Thread {
	private static Game game;
	private static DatagramSocket socket;
	private static InetAddress ipAddress;
	
	public GameClient(Game game, String ipAddress) {
	this.game = game;
		try {
			this.socket = new DatagramSocket();
			this.ipAddress = InetAddress.getByName(ipAddress);		
		  } catch (SocketException e) {
			  e.printStackTrace();
		  } catch (java.net.UnknownHostException e) {
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
			System.out.println("SERVER > " + new String(packet.getData()));
		}
	}
	public static void test() {
		System.out.println("Success!");
	}
	public static void sendData(byte[] data) {
		DatagramPacket packet = new DatagramPacket(data, data.length, ipAddress, 24601);
		try {
			socket.send(packet);
		} catch (IOException e) {
			e.printStackTrace();

		}
	}
}
