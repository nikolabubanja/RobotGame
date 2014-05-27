package robotgame;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.URL;

public class StartingClass extends Applet implements Runnable, KeyListener {

	private Robot robot;
	private Image image, character;
	private URL base;
	private Graphics second;

	public void init() {

		setSize(800, 600);
		setBackground(Color.BLACK);
		setFocusable(true);
		addKeyListener(this);
		Frame frame = (Frame) this.getParent().getParent();
		frame.setTitle("QTbot Alpha");
		try {
			base = getDocumentBase();
		} catch (Exception e) {
			// TODO handle exception
		}

		character = getImage(base, "data/character.png");

	}

	public void start() {
		robot = new Robot();
		Thread thread = new Thread(this);
		thread.start();
	}

	public void stop() {

		super.stop();
	}

	public void destroy() {

		super.destroy();
	}

	public void run() {

		while (true) {
			robot.update();
			repaint();

			try {
				Thread.sleep(17);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
	}

	public void update(Graphics g) {
		if (image == null) {
			image = createImage(this.getWidth(), this.getHeight());
			second = image.getGraphics();
		}

		second.setColor(getBackground());
		second.fillRect(0, 0, getWidth(), getHeight());
		second.setColor(getForeground());
		paint(second);

		g.drawImage(image, 0, 0, this);

	}

	public void paint(Graphics g) {

		g.drawImage(character, robot.getCenterX() - 61,
				robot.getCenterY() - 63, this);

	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_W:
			System.out.println("Move up!");
			break;

		case KeyEvent.VK_S:
			System.out.println("Move down!");
			break;

		case KeyEvent.VK_A:
			robot.moveLeft();
			break;

		case KeyEvent.VK_D:
			robot.moveRight();
			break;

		case KeyEvent.VK_SPACE:
			robot.jump();
			break;

		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_W:
			System.out.println("Stop Move up!");
			break;

		case KeyEvent.VK_S:
			System.out.println("Stop Move down!");
			break;

		case KeyEvent.VK_A:
			robot.stop();
			break;

		case KeyEvent.VK_D:
			robot.stop();
			break;

		case KeyEvent.VK_SPACE:
			System.out.println("Stop Jump!");
			break;

		}

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

}
