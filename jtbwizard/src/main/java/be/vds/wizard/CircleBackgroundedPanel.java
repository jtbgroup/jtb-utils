package be.vds.wizard;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;

/**
 * This panel is a Basic panel with a gradient paint already set and 2 arcs
 * drawn in java2d as background. This can be used for common things like Login,
 * Lock, Splashscreen,...
 * 
 * @author Gautier Vanderslyen
 * 
 */

public class CircleBackgroundedPanel extends JPanel {
	private static final long serialVersionUID = -6358040553175659638L;
	private static final Color MEDIUM_BLUE = new Color(57, 84, 134);
	private static final Color LIGHT_BLUE = new Color(154, 174, 211);

	@Override
	public void paintComponent(Graphics g) {
		Color colorDarkArc = new Color(MEDIUM_BLUE.getRed(),
				MEDIUM_BLUE.getGreen(), MEDIUM_BLUE.getBlue(), 100);
		Color colorDark = new Color(MEDIUM_BLUE.getRed(),
				MEDIUM_BLUE.getGreen(), MEDIUM_BLUE.getBlue());
		Color colorLigthGrey = new Color(LIGHT_BLUE.getRed(),
				LIGHT_BLUE.getGreen(), LIGHT_BLUE.getBlue(), 3);
		int height = getHeight();
		int width = getWidth();

		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setPaint(Color.WHITE);
		g2d.fillRect(0, 0, getWidth(), getHeight());
		g2d.setPaint(new GradientPaint(0, 0, colorDark, 0, getHeight(),
				colorLigthGrey));
		g2d.fillRect(0, 0, getWidth(), getHeight());

		g2d.setPaint(new GradientPaint(0, height / 2, colorDarkArc, 0, height,
				colorLigthGrey));
		g2d.fillArc(0, height / 2, width, height, 0, 180);

		g2d.setPaint(new GradientPaint(width / 2, height / 2, colorDarkArc,
				width, height / 2, colorLigthGrey));
		g2d.fillArc(width / 2, 0, width, height, 90, 180);
	}

}
