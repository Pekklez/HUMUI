package com.uaq.HUMUI.Activities.retos;

import java.util.Date;
import java.util.UUID;

public class Reto {
    private UUID mId;
    private String mTitle;

    public String getActividad() {
        return Actividad;
    }

    public void setActividad(String actividad) {
        Actividad = actividad;
    }





    public String getContacto() {
        return Contacto;
    }

    public void setContacto(String contacto) {
        Contacto = contacto;
    }

    public String getVigencia() {
        return Vigencia;
    }

    public void setVigencia(String vigencia) {
        Vigencia = vigencia;
    }



    private String Actividad;
    private String _id;

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getCategoria() {
        return Categoria;
    }

    public void setCategoria(String categoria) {
        Categoria = categoria;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }

    public String getHistoria() {
        return Historia;
    }

    public void setHistoria(String historia) {
        Historia = historia;
    }

    public String getLogistica() {
        return Logistica;
    }

    public void setLogistica(String logistica) {
        Logistica = logistica;
    }

    public String getNotas() {
        return Notas;
    }

    public void setNotas(String notas) {
        Notas = notas;
    }

    public Integer getMPADIS() {
        return MPADIS;
    }

    public void setMPADIS(Integer MPADIS) {
        this.MPADIS = MPADIS;
    }

    public String getLink() {
        return Link;
    }

    public void setLink(String link) {
        Link = link;
    }

    public Integer getLimite() {
        return Limite;
    }

    public void setLimite(Integer limite) {
        Limite = limite;
    }

    private String Nombre;
    private String Categoria;
    private String Descripcion;
    private String Historia;
    private String Contacto;
    private String Vigencia;
    private String Logistica;
    private String Notas;
    private Integer MPADIS;
    private String Link;
    private Integer Limite;
    private Date mDate;
    private boolean solved;

    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public Reto(){
        mId = UUID.randomUUID();
        mDate = new Date();
    }

    public boolean isSolved() {
        return solved;
    }

    public void setSolved(boolean solved) {
        this.solved = solved;
    }

    public Date getmDate() {
        return mDate;
    }

    public void setmDate(Date mDate) {
        this.mDate = mDate;
    }

    public UUID getId() {
        return mId;
    }
    @Override
    public String toString(){
        return mTitle;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }
}
