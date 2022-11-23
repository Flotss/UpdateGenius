package com.flotss.updateallyourprograms.Model;

import com.flotss.updateallyourprograms.Vue.Observateur;
import com.flotss.updateallyourprograms.utils.Logs;
import com.flotss.updateallyourprograms.utils.MiseAjour;
import com.flotss.updateallyourprograms.utils.SmartReturn;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Model {
    public static String PATHScript = "src\\main\\java\\com\\flotss\\updateallyourprograms\\script\\";

    private ArrayList<MiseAjour> miseAjours;
    private ArrayList<MiseAjour> miseAjoursSelectionnes;
    private final Logs logs;

    private final ArrayList<Observateur> observateurs;


    public Model() {
        this.logs = new Logs(this);
        this.observateurs = new ArrayList<>();
        rechercheMiseAjour();
        this.notifierObservateurs();
        for (MiseAjour miseAjour : miseAjours) {
            System.out.println(miseAjour);
        }
    }

    public void rechercheMiseAjour() {
        this.miseAjours = new ArrayList<>();
        this.miseAjoursSelectionnes = new ArrayList<>();

        this.logs.addLog("Recherche des mises à jour en cours...");
        try {
            // Execute script affichageFileTemp.bat qui se situe dans le dossier script de ce projet
            ProcessBuilder builder = new ProcessBuilder(PATHScript + "affichageFileTemp.bat", "/quiet");
            builder.redirectErrorStream(true);
            Process p = builder.start();

            // Destruction du processus lors que le bat est fini
            p.waitFor();
            p.destroy();
            creationMiseAjour();
        } catch (Exception e) {
            this.logs.addLog("Erreur lors de la recherche des mises à jour");
            e.printStackTrace();
        }
    }


    private void creationMiseAjour() throws IOException {
        String tmpdir = System.getProperty("java.io.tmpdir");
        BufferedReader br = new BufferedReader(new java.io.FileReader(tmpdir + "winget.tmp"));
        br.readLine();
        br.readLine();
        br.readLine();
        br.readLine();

        String line;
        // Regex for match with integer in the beginning of the line : ^[0-9]+
        while ((line = br.readLine()) != null) {
            try {
                Integer.parseInt(line.charAt(0) + "");
                throw new Error("Integer found");
            } catch (Exception ignored) {
            } catch (Error e) {
                break;
            }


            // Split par catégorie
            // Nom de l'application
            int i = 0;
            SmartReturn res = recupererString(line, i);
            String nom = res.getValue();
            i = res.getIndex();

            // ID
            i = indexProchain(line, i);
            res = recupererString(line, i);
            String id = res.getValue();
            i = res.getIndex();

            // Version
            i = indexProchain(line, i);
            res = recupererString(line, i);
            String version = res.getValue();
            i = res.getIndex();

            // Version Disponible
            i = indexProchain(line, i);
            res = recupererString(line, i);
            String disponible = res.getValue();

            this.miseAjours.add(new MiseAjour(nom, id, version, disponible, this, this.logs));
            this.notifierObservateurs();
        }
    }

    private SmartReturn recupererString(String line, int index) {
        StringBuilder res = new StringBuilder();
        while (!(line.charAt(index) == ' ' && line.charAt(index + 1) == ' ')) {

            // Lorsque l'on rencontre winget, c'est que l'on a atteint la fin de la ligne
            if (line.substring(index).startsWith("winget")) {
                break;
            }

            // Lorsque l'on rencontre Unknown, c'est qu'on peut le passer
            if (line.substring(index).startsWith("Unknown")) {
                res = new StringBuilder("Unknown");
                index += 7;
                break;
            }

            // Si le caractère est ... on le remplace par 3 points
            // Et on passe à la catégorie suivante
            if (line.substring(index).startsWith("…")) {
                res.append("...");
                index += 1;
                break;
            }

            // Passer à la catégorie suivante
            // Si c'est un nom de version (ex : Microsoft.VisualStudio.2019.BuildTools 16.11.11) 16.11.11 est une version
            // Si c'est un nom de version disponible (ex : 10.0.22000.832 10.0.22621.2) 10.0.22621.2 est une version disponible
            if (index > 79 && index < 98)
                try {
                    String regex = "^([0-9]|[a-z]|[A-Z])\\s[0-9]+\\.[0-9]+\\.[0-9]+(\\.[0-9]+)?";
                    String string = line.substring(index, index + 16);

                    Pattern pattern = Pattern.compile(regex);
                    Matcher matcher = pattern.matcher(string);

                    if (matcher.find()) {
                        index++;
                        break;
                    }
                } catch (Exception ignored) {
                }


            res.append(line.charAt(index));
            index++;
        }
        return new SmartReturn(res.toString().trim(), index);
    }

    private int indexProchain(String line, int index) {
        while ((line.charAt(index) == ' ')) {
            index++;
        }
        return index;
    }


    // PARTIE OBSERVATEUR
    public void ajouterObservateur(Observateur o) {
        observateurs.add(o);
    }

    public void supprimerObservateur(Observateur o) {
        observateurs.remove(o);
    }

    public void notifierObservateurs() {
        System.out.println("Notif");
        for (Observateur observateur : observateurs) {
            observateur.update(this);
        }
    }


    // PARTIE GETTER
    public ArrayList<MiseAjour> getMiseAjours() {
        return miseAjours;
    }

    public ArrayList<MiseAjour> miseAjoursSelectionnes() {
        return miseAjoursSelectionnes;
    }
}