package ec.edu.ups.ppw64.demo64.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Bibliografia implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	//Atributos Clase Bibliografia
	
	@Id
	@Column(name="id_curso")
	private int idCurso;
	@Column(name="id_biblio_basica")
	private int idBiblioBasica;
	@Column(name="biblio_sistema_sgs")
	private String biblioSistemaSgs;
	@Column(name="tipo")
	private String tipo;
	@Column(name="titulo_url")
	private String tituloURL;
	@Column(name="autor")
	private String autor;
	@Column(name="anio")
	private int anio ;
	@Column(name="ciudad")
	private String ciudad;
	@Column(name="edicion")
	private String edicion;
	@Column(name="editorial")
	private String editorial;
	@Column(name="num_paginas")
	private int numPaginas;
	@Column(name="temas")
	private String temas;
	@Column(name="fuente")
	private String fuente;
	@Column(name="observaciones")
	private String observaciones;
	public int getIdCurso() {
		return idCurso;
	}
	public void setIdCurso(int idCurso) {
		this.idCurso = idCurso;
	}
	public int getIdBiblioBasica() {
		return idBiblioBasica;
	}
	public void setIdBiblioBasica(int idBiblioBasica) {
		this.idBiblioBasica = idBiblioBasica;
	}
	public String getBiblioSistemaSgs() {
		return biblioSistemaSgs;
	}
	public void setBiblioSistemaSgs(String biblioSistemaSgs) {
		this.biblioSistemaSgs = biblioSistemaSgs;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getTituloURL() {
		return tituloURL;
	}
	public void setTituloURL(String tituloURL) {
		this.tituloURL = tituloURL;
	}
	public String getAutor() {
		return autor;
	}
	public void setAutor(String autor) {
		this.autor = autor;
	}
	public int getAnio() {
		return anio;
	}
	public void setAnio(int anio) {
		this.anio = anio;
	}
	public String getCiudad() {
		return ciudad;
	}
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}
	public String getEdicion() {
		return edicion;
	}
	public void setEdicion(String edicion) {
		this.edicion = edicion;
	}
	public String getEditorial() {
		return editorial;
	}
	public void setEditorial(String editorial) {
		this.editorial = editorial;
	}
	public int getNumPaginas() {
		return numPaginas;
	}
	public void setNumPaginas(int numPaginas) {
		this.numPaginas = numPaginas;
	}
	public String getTemas() {
		return temas;
	}
	public void setTemas(String temas) {
		this.temas = temas;
	}
	public String getFuente() {
		return fuente;
	}
	public void setFuente(String fuente) {
		this.fuente = fuente;
	}
	public String getObservaciones() {
		return observaciones;
	}
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
	@Override
	public String toString() {
		return "Bibliografia [idCurso=" + idCurso + ", idBiblioBasica=" + idBiblioBasica + ", biblioSistemaSgs="
				+ biblioSistemaSgs + ", tipo=" + tipo + ", tituloURL=" + tituloURL + ", autor=" + autor + ", anio="
				+ anio + ", ciudad=" + ciudad + ", edicion=" + edicion + ", editorial=" + editorial + ", numPaginas="
				+ numPaginas + ", temas=" + temas + ", fuente=" + fuente + ", observaciones=" + observaciones + "]";
	}
}
