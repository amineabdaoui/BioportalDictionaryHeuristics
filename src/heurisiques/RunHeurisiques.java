package heurisiques;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 *
 * @author Amin Abdaoui
 */
public class RunHeurisiques {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {
        String dictPath=args[0];
        String line;
        ArrayList<String> al = new ArrayList<String>();
        BufferedReader r = new BufferedReader(new InputStreamReader(new FileInputStream(dictPath)));
        while ((line=r.readLine())!=null){
            al.add(line);
        }
        r.close();
        String concept,key, newConcept="";
        PrintWriter w = new PrintWriter(new BufferedWriter(new FileWriter(dictPath)));
        for (String l:al){
            w.println(l);
            key=l.split("\t")[0];
            concept=l.split("\t")[1];
            if (args[1].equals("1")){
                newConcept = Heuristiques.deleteSAI(concept);
                if (newConcept!=concept && newConcept.length()>1) w.println(key+"\t"+newConcept);
            }
            if (args[2].equals("1")){
                newConcept = Heuristiques.replaceAccents(concept);
                if (newConcept!=concept && newConcept.length()>1) w.println(key+"\t"+newConcept);
            }
            if (args[3].equals("1")){
                for (String s:Heuristiques.seperateEtDe(concept))
                    if (s.length()>1) w.println(key+"\t"+s);
            }
            if (args[5].equals("1")){
                newConcept = Heuristiques.replaceTiret(concept);
                if (newConcept!=concept && newConcept.length()>1) w.println(key+"\t"+newConcept);
            }
            if (args[6].equals("1")){
                newConcept = Heuristiques.deleteSubContents(concept);
                if (newConcept!=concept && newConcept.length()>1) w.println(key+"\t"+newConcept);
            }
            if (args[7].equals("1")){
                newConcept = Heuristiques.deleteDoubleSpaces(concept);
                if (newConcept!=concept && newConcept.length()>1) w.println(key+"\t"+newConcept);
            }
            if (concept.endsWith(" ")) if (Heuristiques.deleteLastSpaces(concept).length()>1) w.println(key+"\t"+Heuristiques.deleteLastSpaces(concept));
            
            w.flush();
        }
        w.close();
    }
    
}
