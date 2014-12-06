package be.smd.i18n.demo;

import java.util.Locale;

import be.smd.i18n.ErrorHandler;
import be.smd.i18n.I18nResourceManager;
import be.smd.i18n.demo.ui.DemoFrame;

public class I18nDemo {
	
	private static final Locale ENGLISH = new Locale("en", "EN");
	private static final Locale FRENCH  = new Locale("fr", "FR");
	private static final Locale DUTCH	= new Locale("nl", "NL");
	
	public static void main(String[] args) {
		
		I18nResourceManager i18nManager = I18nResourceManager.sharedInstance();
		i18nManager.setDefaultLocale(ENGLISH);
		

		i18nManager.setErrorHandler(new ErrorHandler(){

			public void resourceNotFound(String bundleKey, Locale locale) {
				System.out.println("No resource found for key '" + bundleKey + "' and for locale '" + locale + "'");
				
			}
			
			
		});
		
		Locale[] locales = new Locale[]{ENGLISH, FRENCH, DUTCH};
		i18nManager.addBundle("be.smd.i18n.demo.bundles.CommonBundle", locales);
		i18nManager.addBundle("be.smd.i18n.demo.bundles.Application", locales);
		
		new DemoFrame();
	}
}
