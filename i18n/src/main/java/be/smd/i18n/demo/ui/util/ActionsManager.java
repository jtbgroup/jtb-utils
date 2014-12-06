package be.smd.i18n.demo.ui.util;

import java.util.HashMap;
import java.util.Map;

import javax.swing.Action;

/**
 * Manager for the application swing {@link Action} that needs to be shared across multiple invokers
 * 
 * @author Pierre Jeanmenne
 * @version 1.0
 * @created 23-02-2009
 */
public class ActionsManager {
	
	/** The map to store application actions */
	private Map<String, Action> actions;
	
	/** The unique instance of ActionsManager */
	private static ActionsManager sharedInstance; 
	
	/**
	 * Creates an ActionsManager
	 */
	private ActionsManager(){
		actions = new HashMap<String, Action>();
	}
	
	/**
	 * Adds an Action into the actions map
	 * @param actionKey {@link String} the key to retrieve the action
	 * @param action {@link Action} the action to store
	 */
	public void addAction(String actionKey, Action action){
		actions.put(actionKey, action);
	}
	
	/**
	 * Returns an action stored in the actions map or null if not present
	 * @param actionKey {@link String} the key for the action to retrieve
	 * @return {@link Action} the request action or null if not present
	 */
	public Action getAction(String actionKey){
		return actions.get(actionKey);
	}
	
	/**
	 * Enables the actions for the specified keys
	 * @param actionKeys an ellipse of {@link String}, the keys of the actions stored into the actions Map
	 */
	public void enableActions(String...actionKeys){
		Action action;
		for (String actionKey : actionKeys) {
			if((action = getAction(actionKey)) != null)
				action.setEnabled(true);
		}
	}
	
	public void clearAll(){
		actions.clear();
	}
	
	/**
	 * Disables the actions for the specified keys
	 * @param actionKeys an ellipse of {@link String}, the keys of the actions stored into the actions Map
	 */
	public void disableActions(String...actionKeys){
		for (String actionKey : actionKeys) {
			getAction(actionKey).setEnabled(false);
		}
	}
	
	public int getActionsCount(){
		return actions.size();
	}
	
	/**
	 * Returns the unique instance of ActionsManager, and creates it as needed
	 * @return {@link ActionsManager} the unique instance of ActionsManager
	 * @post the unique instance of ActionsManager is created
	 */
	public static synchronized ActionsManager sharedInstance(){
		if(sharedInstance == null)
			return sharedInstance = new ActionsManager();
		return sharedInstance;
	}
}
