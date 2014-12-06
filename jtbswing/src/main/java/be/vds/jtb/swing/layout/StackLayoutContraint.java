package be.vds.jtb.swing.layout;

public class StackLayoutContraint {
    public static final String BOTTOM = "bottom";
    public static final String TOP = "top";
    
	private boolean keepComponentBounds;
	private String zOrder = TOP;
	
	public boolean isKeepComponentBounds() {
		return keepComponentBounds;
	}
	public void setKeepComponentBounds(boolean keepComponentBounds) {
		this.keepComponentBounds = keepComponentBounds;
	}
	public String getzOrder() {
		return zOrder;
	}
	public void setzOrder(String zOrder) {
		this.zOrder = zOrder;
	}

}
