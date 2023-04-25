package com.flotss.updateallyourprograms.utils;

import com.flotss.updateallyourprograms.Model.Model;
import javafx.application.Platform;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MiseAjour implements Comparable<MiseAjour> {
    private final String nom;
    private final String ID;
    private final String versionActuelle;
    private final String versionDisponible;

    private final Logs logs;

    public MiseAjour(String nom, String ID, String versionActuelle, String versionDisponible, Logs logs) {
        this.nom = nameWithoutVersion(nom);
        this.ID = ID;
        this.versionActuelle = versionActuelle;
        this.versionDisponible = versionDisponible;
        this.logs = logs;
    }

    public synchronized void makeUpdate() {
        Platform.runLater(() -> {

        this.logs.addLog("Mise à jour de " + this.nom + " en cours...");
        this.logs.addLog("Version : " + this.versionActuelle + " -> " + this.versionDisponible);

            Runtime rt = Runtime.getRuntime();
            Process exec;
            try {
                exec = rt.exec(Model.PATHScript + "update.bat " + this.ID);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }


            while (exec.isAlive()) {
                try {
                    Thread.sleep(1000);
                    System.out.println("En cours");

                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

            // Verification du code de retour du processus
            String tmpdir = System.getProperty("java.io.tmpdir");
            try {
                BufferedReader br = new BufferedReader(new FileReader(tmpdir + "winget.tmp"));
                String line;
                while ((line = br.readLine()) != null) {
                    if (line.contains(this.ID)) {
                        this.logs.addLog("Erreur lors de la mise à jour de " + this.nom);
                        return;
                    }
                }

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

    }

    public String getNom() {
        return nom;
    }

    public String getID() {
        return ID;
    }

    public String getVersionActuelle() {
        return versionActuelle;
    }

    public String getVersionDisponible() {
        return versionDisponible;
    }

    public String toString() {
        return "Nom : " + nom + ", ID : " + ID + ", Version : " + versionActuelle + ", Disponible : " + versionDisponible;
    }

    @Override
    public int compareTo(MiseAjour o) {
        return this.nom.compareTo(o.nom);
    }


    private String nameWithoutVersion(String name) {
        Pattern pattern = Pattern.compile("(.*)\\s\\d+\\.\\d+\\.\\d+");
        Matcher matcher = pattern.matcher(name);
        if (matcher.find()) {
            return matcher.group(1);
        }
        return name;
    }
}
