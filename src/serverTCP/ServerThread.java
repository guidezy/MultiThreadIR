package serverTCP;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;

import serverTCP.data.Nom;

import clientTCP.Requete;

public class ServerThread extends Thread{
	Socket client;
	private Requete req;
	private Reponse rep;
	private ArrayList<Nom> noms;
	
	public ServerThread(Socket client){
		super("ServerThread");
		this.client = client;
		rep = new Reponse();
		noms = new ArrayList<Nom>();
	}
	
	private boolean enreg(String nom, ArrayList<String> surnoms ){
		System.out.println("J'ai recu une demainde pour enregister un nom et ses surnoms...");
		Nom novo = new Nom(nom, surnoms);
		return noms.add(novo);
	}
	private boolean modifierSurnom (String nom, String oldSurnom, String newSurnom){
		System.out.println("J'ai recu une demainde pour modifier un surnom d'un Nom...");
		ArrayList<String> surnoms = null;
		for (int i=0; i<noms.size(); i++){
			if (noms.get(i).getNom().equals(nom)) surnoms = noms.get(i).getSurnoms();
		}
		if (surnoms == null) return false; 
		else {
			for (int i=0; i<surnoms.size(); i++){
				if (surnoms.get(i).equals(oldSurnom)){
					surnoms.set(i, newSurnom);
					return true;
				}
			}
			return false;
		}
	}
	
	private boolean ajouterSurno( String nom, String newSurnom){
		System.out.println("J'ai recu une demainde pour ajouter un surnom à une personne...");
		for (int i=0; i<noms.size(); i++){
			if (noms.get(i).getNom().equals(nom)){
				noms.get(i).getSurnoms().add(newSurnom);
				return true;
			}
		}
		return false;
	}
	
	private boolean effacerEnrg(String nom){
		System.out.println("J'ai recu une demainde pour effacer un enregistrement...");
		for (int i=0; i<noms.size(); i++){
			if (noms.get(i).getNom().equals(nom)){
				noms.remove(i);
				return true;
			}
		}
		return false;
	}
	private boolean effacerSurnom (String nom, String surnom){
		System.out.println("J'ai recu une demainde effacer un surnom...");
		for (int i=0; i<noms.size(); i++){
			if (noms.get(i).getNom().equals(nom)){
				noms.get(i).getSurnoms().remove(surnom);
				return true;
			}
		}
		return false;
	}
	private ArrayList<String> afficherEnreg (String nom){
		for (int i=0; i<noms.size(); i++){
			if (noms.get(i).getNom().equals(nom)){
				return noms.get(i).getSurnoms();
			}
		}
		return null;
	}
	private ArrayList<Nom> listerEnreg(){
		return noms;
	}
	private void deconnect(){
		try{
			client.close();
		}
		catch(IOException e){
			System.err.println(e);
		}
	}
	
	public void run(){
		System.out.println("Accept");
		
		try{
			ObjectOutputStream oos = new ObjectOutputStream(client.getOutputStream());
			ObjectInputStream ois = new ObjectInputStream(client.getInputStream());
			while( ois!=null && (req = (Requete) ois.readObject()) != null){
				
				rep.setMessageID(req.getMessageID());
				boolean re;
				switch (req.getMethodID()){
					case 0:
						re = enreg(req.getNom(), req.getDonnees());
						rep.setRep(re);
						rep.setNom(null);
						rep.setDonnees(null);
						break;
					case 1:
						re = modifierSurnom(req.getNom(), req.getDonnees().get(0), req.getDonnees().get(1)); 
						rep.setRep(re);
						rep.setNom(null);
						rep.setDonnees(null);
						break;
					case 2:
						re = ajouterSurno(req.getNom(), req.getDonnees().get(0));
						rep.setRep(re);
						rep.setNom(null);
						rep.setDonnees(null);
						break;
					case 3:
						re = effacerEnrg(req.getNom());
						rep.setRep(re);
						rep.setNom(null);
						rep.setDonnees(null);
						break;
					case 4:
						re = effacerSurnom (req.getNom(), req.getDonnees().get(0));
						rep.setRep(re);
						rep.setNom(null);
						rep.setDonnees(null);
						break;
					case 5:
						ArrayList<String> liste = afficherEnreg (req.getNom());
						re = liste==null?false:true;
						rep.setRep(re);
						rep.setNom(req.getNom());
						rep.setDonnees(liste);
						break;
					case 6:
						break;
					default:
						deconnect();
						rep.setRep(true);
						rep.setNom(null);
						rep.setDonnees(null);
						break;
						
				}
				oos.writeObject(rep);
				oos.reset();
				
			}
		} catch (IOException | ClassNotFoundException e) {
			try {
				client.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
}
