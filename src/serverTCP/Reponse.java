package serverTCP;

import java.io.Serializable;
import java.util.ArrayList;

import serverTCP.data.Nom;

public class Reponse implements Serializable{

	private int messageID;
	private boolean rep;
	private String nom;
	private ArrayList<String> donnees;
	
	
	public Reponse(int messageID, boolean rep, String nom,ArrayList<String> donnees) {
		this.messageID = messageID;;
		this.rep = rep;
		this.nom = nom;
		this.donnees = donnees;
	}
	
	
	public Reponse() {
		 donnees = new ArrayList<String>(); 
	}


	public int getMessageID() {
		return messageID;
	}
	public void setMessageID(int messageID) {
		this.messageID = messageID;
	}
	
	public boolean isRep() {
		return rep;
	}
	public void setRep(boolean rep) {
		this.rep = rep;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public ArrayList<String> getDonnees() {
		return donnees;
	}
	public void setDonnees(ArrayList<String> donnees) {
		this.donnees = donnees;
	}
	public String toString(){
		String str = "message ID:"+ messageID +"\tReponse: "+ rep + "\tNom: "+ nom +"\tSurnoms: ";
		if (donnees == null) str = str+"null";
		else {
			str = str + "{";
			for (int i=0; i<donnees.size(); i++){
				str = str + donnees.get(i) + ", ";
			}
			str = str + "}";
		}
		return str;
	}
	
}
