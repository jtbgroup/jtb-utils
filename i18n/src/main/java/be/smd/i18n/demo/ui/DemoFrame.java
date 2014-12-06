package be.smd.i18n.demo.ui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;

import be.smd.i18n.I18nResourceManager;

/**
 * The main frame of the I18n framework demo
 * 
 * @author Pierre Jeanmenne
 * @version 1.0
 * @created 23-02-2009
 */
public class DemoFrame extends JFrame {

	/** The Serial Version UID for {@link DemoFrame} */
	private static final long serialVersionUID = 3504397721775146361L;
	
	/** The main application panel */
	private JPanel mainPanel;
	
	/**
	 * Creates an {@link DemoFrame}
	 */
	public DemoFrame(){
		initComponents();
		initWindow();
	}
	
	/**
	 * Initializes the frame components creation
	 */
	private void initComponents(){
		setJMenuBar(new I18nDemoMenubar(this));
		mainPanel = new DemoPanel();
        getContentPane().add(mainPanel);
	}
	
	
	/** Initializes the frame properties */
	private void initWindow() {
		setSize(new Dimension(800,600));
		setVisible(true);
    }
    
    protected void processWindowEvent(WindowEvent e) {
        if (e.getID() == WindowEvent.WINDOW_CLOSING) {
            closeWindow();
        }
    }

    public void closeWindow() {
        firePropertyChange("windowClosed", this, null);
        System.exit(0);
    }

    public void paint(Graphics g) {
    	setTitle(I18nResourceManager.sharedInstance().getString("application.title"));
		super.paint(g);
	}
}
