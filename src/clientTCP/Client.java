package clientTCP;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

import serverTCP.Reponse;


public class Client {
	private Socket socketClient;
	static private int PORT = 12346;
	private ObjectInputStream ois;
	private ObjectOutputStream oos;
	private Requete req;
	private Reponse rep;
	private static int idMessage;
	
	public void connect(){
		try{
			socketClient = new Socket("10.0.0.8", PORT);
			idMessage = 0;
			System.out.println("Connecté - Client");
			req = new Requete();
			rep = new Reponse();
			ois = new ObjectInputStream(socketClient.getInputStream());
			oos = new ObjectOutputStream(socketClient.getOutputStream());
		}catch(IOException e){
			System.err.println(e);
		}
	}	

	private void deconnect(){
		try{
			socketClient.close();
		}
		catch(IOException e){
			System.err.println(e);
		}
	}
	private void enreg(String nom, ArrayList<String> surnoms ) throws IOException, ClassNotFoundException{
		req.setMessageID(idMessage++);
		req.setMethodID(0);
		req.setNom(nom);
		req.setDonnees(surnoms);
		System.out.println("## Demander enrigestrer un nom et ses surnoms");
		oos.writeObject(req);
		oos.reset();
		while (ois==null || (rep = (Reponse) ois.readObject()) == null);
		System.out.println("§§ Reponse qui est arrivée:");
		System.out.println(rep.toString());

		
		
		
	}
	private void modifierSurnom (String nom, String oldSurnom, String newSurnom) throws IOException, ClassNotFoundException{
		req.setMessageID(idMessage++);
		req.setMethodID(1);
		req.setNom(nom);
		ArrayList<String> surnoms = new ArrayList<String>();
		surnoms.add(oldSurnom);
		surnoms.add(newSurnom);
		req.setDonnees(surnoms);
		System.out.println("## Demander modifier un surnom d'un Nom ");
		oos.writeObject(req);
		oos.reset();
		while (ois==null || (rep = (Reponse) ois.readObject()) == null);
		System.out.println("§§ Reponse qui est arrivée:");
		System.out.println(rep.toString());
		
	}
	
	private void ajouterSurno( String nom, String newSurnom) throws IOException, ClassNotFoundException{
		req.setMessageID(idMessage++);
		req.setMethodID(2);
		req.setNom(nom);
		ArrayList<String> surnoms = new ArrayList<String>();
		surnoms.add(newSurnom);
		req.setDonnees(surnoms);
		System.out.println("## Demander ajouter un surnom à une personne");
		oos.writeObject(req);
		oos.reset();
		while (ois==null || (rep = (Reponse) ois.readObject()) == null);
		System.out.println("§§ Reponse qui est arrivée:");
		System.out.println(rep.toString());
	}
	
	private void effacerEnrg(String nom) throws IOException, ClassNotFoundException{
		req.setMessageID(idMessage++);
		req.setMethodID(3);
		req.setNom(nom);
		req.setDonnees(null);
		System.out.println("## Demander effacer un enregistrement");
		oos.writeObject(req);
		oos.reset();
		while (ois==null || (rep = (Reponse) ois.readObject()) == null);
		System.out.println("§§ Reponse qui est arrivée:");
		System.out.println(rep.toString());
	}
	private void effacerSurnom (String nom, String surnom) throws IOException, ClassNotFoundException{
		req.setMessageID(idMessage++);
		req.setMethodID(4);
		req.setNom(nom);
		ArrayList<String> surnoms = new ArrayList<String>();
		surnoms.add(surnom);
		req.setDonnees(surnoms);
		System.out.println("## Demander effacer un surnom");
		oos.writeObject(req);
		oos.reset();
		while (ois==null || (rep = (Reponse) ois.readObject()) == null);
		System.out.println("§§ Reponse qui est arrivée:");
		System.out.println(rep.toString());
	}
	private void afficherEnreg (String nom) throws IOException, ClassNotFoundException{
		req.setMessageID(idMessage++);
		req.setMethodID(5);
		req.setNom(nom);
		req.setDonnees(null);
		System.out.println("## Demander afficher un enregistrement");
		oos.writeObject(req);
		oos.reset();
		while (ois==null || (rep = (Reponse) ois.readObject()) == null);
		System.out.println("§§ Reponse qui est arrivée:");
		System.out.println(rep.toString());
	}
	/*private ArrayList<Nom> listerEnreg(){
		return noms;
	}
	*/

	public static void main(String[]args) throws ClassNotFoundException, IOException{
		Client c = new Client();
		c.connect();
		ArrayList<String> surnoms = new ArrayList<String>();
		surnoms.add("Surnom11");
		surnoms.add("Surnom12");
		surnoms.add("Surnom13");
		surnoms.add("Surnom14");
		surnoms.add("Surnom15");
		surnoms.add("Surnom16");
		surnoms.add("Surnom17");
		ArrayList<String> surnoms2 = new ArrayList<String>();
		surnoms2.add("Surnom21");
		surnoms2.add("Surnom22");
		surnoms2.add("Surnom23");
		surnoms2.add("Surnom24");
		surnoms2.add("Surnom25");
		surnoms2.add("Surnom26");
		//surnoms2.add("Surnom27");
		c.enreg("Nom1", surnoms);
		c.enreg("Nom2", surnoms2);
		c.modifierSurnom("Nom1", "Surnom15", "Surnom155555");
		c.modifierSurnom("Nom1", "Surnom19", "Surnom199999");
		c.modifierSurnom("Nom2", "Surnom27", "Surnom277777");
		c.ajouterSurno("Nom2", "Surnom27");
		c.modifierSurnom("Nom2", "Surnom27", "Surnom277777");
		c.effacerEnrg("Nom3");
		c.effacerSurnom("Nom3", "Surnom277777");
		c.afficherEnreg("Nom2");
		c.effacerSurnom("Nom2", "Surnom27");
		c.afficherEnreg("Nom2");
		c.effacerSurnom("Nom2", "Surnom277777");
		c.afficherEnreg("Nom2");
		c.effacerEnrg("Nom2");
		
		
	//	c.deconnect();
	}
}

