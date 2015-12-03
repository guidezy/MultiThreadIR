package serverTCP.data;

import java.io.Serializable;
import java.util.ArrayList;

public class Nom implements Serializable{
	private String nom;
	private ArrayList<String> surnoms;
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public ArrayList<String> getSurnoms() {
		return surnoms;
	}
	public void setSurnoms(ArrayList<String> surnoms) {
		this.surnoms = surnoms;
	}
	public Nom(String nom, ArrayList<String> surnoms) {
		super();
		this.nom = nom;
		this.surnoms = surnoms;
	}
	
	
	
	
}
