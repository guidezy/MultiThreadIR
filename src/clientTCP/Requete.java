package clientTCP;

import java.io.Serializable;
import java.util.ArrayList;

import serverTCP.data.Nom;

public class Requete implements Serializable{
	private int messageID, methodID;
	private String nom;
	private ArrayList<String> donnees;
	
	
	public Requete(int messageID, int methodID, String nom,ArrayList<String> donnees){
		this.messageID = messageID;
		this.methodID = methodID;
		this.nom = nom;
		this.donnees = donnees;
	}


	public Requete() {
		 donnees = new ArrayList<String>(); 
	}


	public int getMessageID() {
		return messageID;
	}


	public void setMessageID(int messageID) {
		this.messageID = messageID;
	}


	public int getMethodID() {
		return methodID;
	}


	public void setMethodID(int methodID) {
		this.methodID = methodID;
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
		return "message ID:"+ messageID +"\n method ID: "+ methodID;
	}
	
}
