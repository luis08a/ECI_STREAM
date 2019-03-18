package edu.eci.arsw.eci_stream.Streaming.Streamer;

import java.awt.AWTException;
import java.awt.HeadlessException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.util.List;

import javax.imageio.ImageIO;

public class Stream extends Thread{

	private DataOutputStream out;	
	private DataInputStream in;
	private ServerSocket serverSocket;
	private Socket server;
	public BufferedImage screencap;
	
	public Stream(int port) {
		try {			
			serverSocket = new ServerSocket(port);
			server = serverSocket.accept();
			System.out.println("Enviando imagenes a puerto " + serverSocket.getLocalPort());
			in = new DataInputStream(server.getInputStream());
			out = new DataOutputStream(server.getOutputStream());

		} catch (HeadlessException | IOException e) {
			e.printStackTrace();
		}
		

	}

	@Override
	public void run() {
		try {
			//get actual screenshot
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write(screencap, "jpg", baos);
			// Gets screenshot byte size and sends it to server and flushes then closes the
			// connection
			byte[] size = ByteBuffer.allocate(4).putInt(baos.size()).array();
			out.write(size);
			out.write(baos.toByteArray());
			out.flush();			
			//frecuencia de stream ?
			Thread.sleep(10000);
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
		
		
		

	}
	
	public void setScreencap(BufferedImage image) {
		screencap  =image;
		
	}
	
	
	

	

}
