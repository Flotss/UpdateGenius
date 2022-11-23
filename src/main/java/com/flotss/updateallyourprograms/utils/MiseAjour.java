package com.flotss.updateallyourprograms.utils;

import com.flotss.updateallyourprograms.Model.Model;

import java.io.IOException;

public class MiseAjour {
    private final String nom;
    private final String ID;
    private final String versionActuelle;
    private final String versionDisponible;

    private final Logs logs;
    private final Model model;

    public MiseAjour(String nom, String ID, String versionActuelle, String versionDisponible, Model model, Logs logs) {
        this.nom = nom;
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
        } else {
            this.logs.addLog("Erreur lors de la mise à jour de " + this.nom);
        }

        this.model.notifierObservateurs();
    }

    public String nom() {
        return nom;
    }

    public String ID() {
        return ID;
    }

    public String versionActuelle() {
        return versionActuelle;
    }

    public String versionDisponible() {
        return versionDisponible;
    }

    public String toString() {
        return "Nom : " + nom + ", ID : " + ID + ", Version : " + versionActuelle + ", Disponible : " + versionDisponible;
    }
}
