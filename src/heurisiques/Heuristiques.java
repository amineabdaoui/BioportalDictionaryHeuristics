package heurisiques;

import java.text.Normalizer;
import java.text.Normalizer.Form;
import java.util.ArrayList;

/**
 *
 * @author Amin Abdaoui
 */
public class Heuristiques {
    
    public static String deleteSAI(String concept){
        concept = concept.replaceAll(", SANS PRéCISION", "");
        concept = concept.replaceAll(", SANS PReCISION", "");
        concept = concept.replaceAll(", SANS PRECISION", "");
        concept = concept.replaceAll(" SANS PRéCISION", "");
        concept = concept.replaceAll(" SANS PReCISION", "");
        concept = concept.replaceAll(" SANS PRECISION", "");
        concept = concept.replaceAll(", SANS AUTRE INDICATION", "");
        concept = concept.replaceAll(", SANS AUTRE EXPLICATION", "");
        concept = concept.replaceAll(", SAI", "");
        concept = concept.replaceAll(" SAI", "");
        concept = concept.replaceAll(", NON CLASSé AILLEURS", "");
        concept = concept.replaceAll(" NON CLASSé AILLEURS", "");
        concept = concept.replaceAll(", NON CLASSéE AILLEURS", "");
        concept = concept.replaceAll(" NON CLASSéE AILLEURS", "");
        concept = concept.replaceAll(", NON CLASSéS AILLEURS", "");
        concept = concept.replaceAll(" NON CLASSéS AILLEURS", "");
        concept = concept.replaceAll(", NON CLASSéES AILLEURS", "");
        concept = concept.replaceAll(" NON CLASSéES AILLEURS", "");
        concept = deleteDoubleSpaces(concept);
        concept = deleteLastSpaces(concept);
        
        return concept;
    }
    
    public static String replaceAccents(String concept){
        concept = Normalizer.normalize(concept, Form.NFD).replaceAll("\\p{InCombiningDiacriticalMarks}+", ""); 
        concept = concept.toUpperCase();
        concept = deleteDoubleSpaces(concept);
        concept = deleteLastSpaces(concept);
        
        return concept;
    }
    
    public static ArrayList<String> seperateEtDe(String concept){
        ArrayList<String> al = new ArrayList();
        al.add(" DE ");al.add(" DES ");al.add(" DU ");al.add(" D'");
        String sousPhrase, sousPhrase1, sousPhrase2, sousPhrase3;
        ArrayList<String> res = new ArrayList<String>();
        int index;
        for (String sep1:al)
            for (String sep2:al)
                if (concept.matches(".*?"+sep1+".*? ET"+sep2+".*") || (concept.matches(".*?"+sep1+".*? OU"+sep2+".*"))){
                    index = concept.indexOf(sep1);
                    sousPhrase1 = concept.substring(0,index);
                    sousPhrase = concept.substring(index+sep1.length());
                    if (sousPhrase.matches(".*? ET"+sep2+".*")){
                        sousPhrase2 = sousPhrase.split(" ET"+sep2)[0];
                        sousPhrase3 = sousPhrase.split(" ET"+sep2)[1];
                    }
                    else{
                        sousPhrase2 = sousPhrase.split(" OU"+sep2)[0];
                        sousPhrase3 = sousPhrase.split(" OU"+sep2)[1];
                    }
                    res.add(sousPhrase1+sep1+sousPhrase2);
                    res.add(sousPhrase1+sep2+sousPhrase3);
                }
        return res;
    }
    
    public static String replaceTiret(String concept){
        concept = concept.replaceAll("-", " ");
        return concept;
    }
    
    public static String deleteTiret(String concept){
        concept = concept.replaceAll("-", "");
        return concept;
    }
    
    
    public static String deleteSubContents(String concept){
        concept = concept.replaceAll("\\(.*?\\)", "" );
        concept = concept.replaceAll("\\[.*?\\]", "" );
        concept = deleteDoubleSpaces(concept);
        concept = deleteLastSpaces(concept);
        return concept;
    }
    
    public static String deleteDoubleSpaces(String concept){
        while (concept.contains("  ")) concept = concept.replaceAll("  ", " ");
        return concept;
    }
    
    public static String deleteLastSpaces(String concept){
        while (concept.endsWith(" ")) concept = concept.substring(0, concept.length()-1);
        return concept;
    }
}
