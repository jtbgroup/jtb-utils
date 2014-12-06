package be.smd.i18n.demo.bundles;

/**
 * The english resources for the generic query tool
 * 
 * @author Pierre Jeanmenne
 * @version 03-11-08
 */
public class CommonBundle_nl_NL extends java.util.ListResourceBundle {

    private static final Object[][] contents = new String[][]{
    	{"birthdate" , "Geboortedatum"},
    	{"date" , "Datum"},
    	{"dutch" , "Nederlands"},
    	{"english" , "Engels"},
    	{"exit" , "Verlaten"},
    	{"file" , "Bestand"},
    	{"french" , "Frans"},
    	{"from", "Van"},
    	{"languages" , "Talen"},
    	{"search" , "Zoeken"},
    	{"sex" , "Sexe"},
    	{"to", "Tot"},
    	{"ok", "Ok"},
    	{"cancel", "Annuleren"},
    	{"select", "Selecteren"},
    };

    @Override
    protected Object[][] getContents() {
        return contents;
    }
}