package be.vds.jtb.swing.component;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;


/**
 * This is used to reduce pictures.
 * 
 * @author vanderslyen.g
 * 
 */
public class Thumbnail extends ImageIcon {

	private static final long serialVersionUID = 6427993824215920158L;
	private ImageIcon icon;

	public Thumbnail(ImageIcon icon) {
		this.icon = icon;
	}

	/**
	 * The reduction of the icon is proportional to the scale
	 * 
	 * @param scale
	 *            eg: 0.5 is 50%
	 * @return itself
	 */
	public Thumbnail fitToScale(double scale) {
		int w = (int) (icon.getIconWidth() * scale);
		int h = (int) (icon.getIconHeight() * scale);
		BufferedImage outImage = new BufferedImage(w, h,
				BufferedImage.TYPE_INT_ARGB);
		AffineTransform trans = new AffineTransform();
		trans.scale(scale, scale);
		Graphics2D g = outImage.createGraphics();
		g.drawImage(icon.getImage(), trans, null);
		g.dispose();
		setImage(outImage);
		return this;
	}

	/**
	 * reduces the picture based on the width desired.
	 * 
	 * @param width
	 * @return
	 */
	public Thumbnail fitToWidth(double width) {
		double originalWidth = icon.getIconWidth();
		double scale = width / originalWidth;
		return fitToScale(scale);
	}

	/**
	 * reduces the picture based on the heigth desired.
	 * 
	 * @param width
	 * @return
	 */
	public Thumbnail fitToHeight(double height) {
		double originalHeight = icon.getIconHeight();
		double scale = height / originalHeight;
		return fitToScale(scale);
	}

//	public ImageIcon getIcon() {
//		return icon;
//	}

}
