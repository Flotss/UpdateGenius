package com.flotss.updateallyourprograms.utils;

import com.flotss.updateallyourprograms.Model.Model;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MiseAjour implements Comparable<MiseAjour> {
    private final String nom;
    private final String ID;
    private final String versionActuelle;
    private final String versionDisponible;

    private final Logs logs;
    private final Model model;

    public MiseAjour(String nom, String ID, String versionActuelle, String versionDisponible, Model model, Logs logs) {
        this.nom = nameWithoutVersion(nom);
        this.ID = ID;
        this.versionActuelle = versionActuelle;
        this.versionDisponible = versionDisponible;
        this.model = model;
        this.logs = logs;
    }

    public void makeUpdate() throws IOException {
        this.logs.addLog("Mise à jour de " + this.nom + " en cours...");
        this.logs.addLog("Version : " + this.versionActuelle + " -> " + this.versionDisponible);
        Runtime rt = Runtime.getRuntime();
        Process exec = rt.exec(Model.PATHScript + "update.bat " + this.ID);

        // Verification du code de retour du processus
        if (exec.exitValue() == 0) {
            this.logs.addLog("Mise à jour de " + this.nom + " terminée");
        } else if (exec.exitValue() == 1) {
            this.logs.addLog("Erreur lors de la mise à jour de " + this.nom);
        }

        this.model.notifierObservateurs();
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
