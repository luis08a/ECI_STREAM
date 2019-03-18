package edu.eci.arsw.eci_stream.Streaming.Streamer;

import java.awt.AWTException;
import java.awt.HeadlessException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ConcurrentMap;

import javax.imageio.ImageIO;

public class StreamManager extends Thread {
	private ConcurrentLinkedQueue<Stream> viewers;

	public StreamManager() {
		viewers = new ConcurrentLinkedQueue<Stream>();
	}

	public void addUserStream(Stream s) {
		viewers.add(s);

	}
	
	public void addUserStream(int port) {
		/*
		 * se cambia el puerto para cada stream o el mismo puerto de server para todos los streams, si es asi, cambiar constructor
		 * de stream.
		 */
		Stream st =new Stream(port);
		viewers.add(st);
		
		
	}

	public void run() {
		try {
			BufferedImage screencap = new Robot()
					.createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
			
			for(Stream s:viewers) {
				s.setScreencap(screencap);
				
			}
		} catch (HeadlessException | AWTException e) {
		}

	}

}
