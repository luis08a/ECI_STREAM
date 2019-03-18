package edu.eci.arsw.eci_stream.Streaming.Viewer;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Stream extends Thread {

	private final JFrame frame;

	Socket sclient;
	OutputStream sOutToServer;
	DataOutputStream out;
	InputStream sInFromServer;
	DataInputStream in;
	int port;

	public Stream(int port) {
		// Screenshot Frame
		this.port=port;
		frame = new JFrame();
		frame.setSize(500, 500);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	@Override
	public void run() {
		try {
			//toca preguntar al server por que puerto conectar? o toda la info sale del mismo port de server pero cambia el de destino?
			sclient = new Socket("server ip", port);
			OutputStream sOutToServer = sclient.getOutputStream();
			out = new DataOutputStream(sOutToServer);
			InputStream sInFromServer = sclient.getInputStream();
			in = new DataInputStream(sInFromServer);
			byte[] sizeAr = new byte[4];
			in.read(sizeAr);
			int size = ByteBuffer.wrap(sizeAr).asIntBuffer().get();

			byte[] imageAr = new byte[size];
			in.read(imageAr);

			BufferedImage image = ImageIO.read(new ByteArrayInputStream(imageAr));
			ImageIO.write(image, "jpg", new File("screen.jpg"));			
			JLabel label = new JLabel();
			label.setIcon(new ImageIcon(image));
			frame.getContentPane().add(label);
		} catch (IOException e) {
			e.printStackTrace();
		}
		frame.setVisible(true);
	}

}
