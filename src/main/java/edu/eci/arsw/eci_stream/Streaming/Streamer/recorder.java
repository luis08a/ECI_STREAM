package edu.eci.arsw.eci_stream.Streaming.Streamer;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;

import javax.swing.JFrame;

import static org.bytedeco.javacpp.opencv_imgcodecs.cvLoadImage;
import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.HeadlessException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.util.Date;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import org.bytedeco.javacpp.avcodec;
import org.bytedeco.javacv.FFmpegFrameRecorder;
import org.bytedeco.javacv.OpenCVFrameConverter;

public class recorder implements Runnable {

	private DataOutputStream out;
	private Socket sclient;
	private DataInputStream in;

	public recorder() {
		try {
			sclient = new Socket("server ip", 6667);
			OutputStream sOutToServer = sclient.getOutputStream();
			out = new DataOutputStream(sOutToServer);
			InputStream sInFromServer = sclient.getInputStream();
			in = new DataInputStream(sInFromServer);

		} catch (HeadlessException | IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void run() {
		try {
			// Takes screenshot and sends it to server as byte array
			BufferedImage screencap = new Robot()
					.createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write(screencap, "jpg", baos);

			// Gets screenshot byte size and sends it to server and flushes then closes the
			// connection
			byte[] size = ByteBuffer.allocate(4).putInt(baos.size()).array();
			out.write(size);
			out.write(baos.toByteArray());
			out.flush();
			sclient.close();
		} catch (AWTException | IOException e) {
			e.printStackTrace();
		}

	}

}
