package be.vds.jtb.swing.component.waves;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Paint;

import javax.swing.JPanel;

public class GradientPanel extends JPanel {

	private static final long serialVersionUID = 3635301590918779964L;
	private Color gradientStart = new Color(182, 219, 136);// 220, 255, 149);
	private Color gradientEnd = new Color(158, 211, 102);// 183, 234, 98);

	public void setGradientStart(Color gradientStart) {
		this.gradientStart = gradientStart;
	}

	public void setGradientEnd(Color gradientEnd) {
		this.gradientEnd = gradientEnd;
	}

	public void paintComponent(Graphics g) {
		int height = getHeight();

		Graphics2D g2 = (Graphics2D) g;
		GradientPaint painter = new GradientPaint(0, 0, gradientStart, 0,
				height, gradientEnd);
		Paint oldPainter = g2.getPaint();
		g2.setPaint(painter);
		g2.fill(g2.getClip());

		painter = new GradientPaint(0, 0, gradientEnd, 0, height / 2,
				gradientStart);
		g2.setPaint(painter);
		g2.fill(g2.getClip());

		painter = new GradientPaint(0, height / 2, gradientStart, 0, height,
				gradientEnd);
		g2.setPaint(painter);
		g2.fill(g2.getClip());

		g2.setPaint(oldPainter);
	}
}
