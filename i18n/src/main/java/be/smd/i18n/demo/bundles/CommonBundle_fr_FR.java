package be.smd.i18n.demo.bundles;

/**
 * The english resources for the generic query tool
 * 
 * @author Pierre Jeanmenne
 * @version 03-11-08
 */
public class CommonBundle_fr_FR extends java.util.ListResourceBundle {

    private static final Object[][] contents = new String[][]{
    	{"birthdate" , "Date de naissance"},
    	{"date" , "Date"},
    	{"dutch" , "Néerlandais"},
    	{"english" , "Anglais"},
    	{"exit" , "Quitter"},
    	{"file" , "Fichier"},
    	{"french" , "Français"},
    	{"from", "De"},
    	{"languages" , "Langues"},
    	{"search" , "Rechercher"},
    	{"sex" , "Sexe"},
    	{"to", "A"},
    	{"ok", "Ok"},
    	{"cancel", "Annuler"},
    	{"select", "Sélectionner"},
    };

    @Override
    protected Object[][] getContents() {
        return contents;
    }
}