package be.smd.i18n.swing;

import java.beans.PropertyChangeListener;

import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

/**
 * An localized {@link JPopupMenu}
 * 
 * @author Pierre Jeanmenne
 * @version 1.0
 * @created 07-04-08
 */
public class I18nPopupMenu extends JPopupMenu{

    /** Serial version uid for I18nPopupMenu */
    private static final long serialVersionUID = -3710488431556056907L;
    
    /**
     * Creates an I18nPopupMenu
     */
    public I18nPopupMenu(){}
    
    /**
     * Overridden method from JPopupMenu.
     * @param a - the action from which to create a menu item
     */
    @Override
    protected JMenuItem createActionComponent(Action a) {
        
        
    	Icon itemIcon = (Icon)a.getValue(Action.SMALL_ICON);  
    	I18nMenuItem item = new I18nMenuItem((String)a.getValue(Action.NAME), itemIcon){
                
    		private static final long serialVersionUID = -7916978868441781887L;

    			protected PropertyChangeListener createActionPropertyChangeListener(Action a) {
                    PropertyChangeListener pcl = createActionChangeListener(this);
                    if (pcl == null)
                        pcl = super.createActionPropertyChangeListener(a);
                    return pcl;
                }
            };
            
		item.setHorizontalTextPosition(JButton.TRAILING);
		item.setVerticalTextPosition(JButton.CENTER);
		item.setEnabled(a.isEnabled());
           
		return item;
    }
}