package com.example.myapplication;

public class Papeleta {

    String id_papeleta,fecha,turno;
    Check checks;
    String Boletaje;



    public String getId_papeleta() {
        return id_papeleta;
    }

    public void setId_papeleta(String id_papeleta) {
        this.id_papeleta = id_papeleta;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    public Check getChecks() {
        return checks;
    }

    public void setChecks(Check checks) {
        this.checks = checks;
    }

    public String getBoletaje() {
        return Boletaje;
    }

    public void setBoletaje(String boletaje) {
        Boletaje = boletaje;
    }

}
