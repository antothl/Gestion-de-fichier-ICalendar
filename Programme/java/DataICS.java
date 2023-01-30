import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Scanner;

/**
*Dans cette classe sont repr\u00e9sent\u00e9s tous les types de donn\u00e9es r\u00e9cup\u00e9r\u00e9s depuis le fichierICS
*@author Duval Antoine , Th\u00e9ologien Antoine , Noizet Jonathan , Bouchhioua Wejdane 
*@version 1.0
*/
public class DataICS {
	
//Attributs
	//Attribut contenant le fichier original
	private String fichierEntier;
	
	//Attribut mit \u00e0 jour pour remplir les autres attributs de compisition
	private String calendrierSimplifie;
	
	//Attributs de composition
	private String identifiant; //L'indentifiant et le mail seront le meme lors de la creation d'un fichier
	private String localisation;
	private String titre;
	private String description;
	private String createur;
	private String email;
	private DateICS dateDebut;
	private DateICS dateFin;
	private String prefixe;
	private String suffixe;
	
	
 /**
 *Constructeur via une chaine de caracteres
 @param nomFichier le fichier en chaine de caracteres
 */	
 public DataICS(String nomFichier ) throws MalformedURLException, IOException {
	//Ouverture du fichier ICS
	File file = new File(nomFichier);
	FileInputStream fichierICS = new FileInputStream(file);
 
	int code;
	String cal="";
	//Transformation du fichier ICS en caracteres lisibles
	while((code = fichierICS.read()) != -1) {
		char ch = (char) code;
		cal+=ch;
	}
	
	fichierICS.close();
	this.fichierEntier = cal;
 }
	
	
 /**
 *Getter du fichier
 @return le fichier entier en chaine de caracteres
 */	
 public String getFichierEntier() {
	return this.fichierEntier;
 }



 //Fonction uniquement pour tester si le fichier est bien ouvert
 public void affichageBasique() {
	System.out.println("\nAffichage basique de votre fichier ICS :\n\n"+fichierEntier);
 }
 
	
	
	
 //Fonction qui retire le prefixe et le suffixe pour ameliorer la lecture
 //Et aussi de creer le calendrierSimplifie pour ensuite remplir les attributs de composition
 public void simplification() {
	int longueurTotale = fichierEntier.length();
	
	int indexPrefixe = fichierEntier.indexOf("UID:");
	this.prefixe = fichierEntier.substring(0, indexPrefixe);
	int longueurPrefixe = this.prefixe.length();
	
	int indexSuffixe = fichierEntier.indexOf("END:VEVENT");
	this.suffixe = fichierEntier.substring(indexSuffixe, longueurTotale);
	int longueurSuffixe = this.suffixe.length();
	
	calendrierSimplifie = fichierEntier.substring(longueurPrefixe,longueurTotale-longueurSuffixe);
 }
 
 
 
 
 //Ici on va mettre chaque partie du fichier ics dans son attribut,
 //Comment dans simplification() mais plus rapidemment
 //L'attribut calendrierSimplifie sera raccourci au fur et a mesur pour pas abimer le fichierEntier de d\u00e9part
 public void remplissageAtt() {
	 
	//Verification de la pr\u00e9sence de UID dans le fichier :
	if (calendrierSimplifie.contains("UID:")) {
				
		//Avec l'identifiant : on part du UID pour aller jusqu'au retour a la ligne
		//D'ou l'interet de raccourcir le fichier calendrierSimplifie \u00e0 chaque fois
		//Sinon il y a une exception \u00e0 lever
		//J'ai rajout\u00e9 4 au premier indice car UID: \u00e0 4 caracteres et on veut pas de cela dans l'attribut.
		this.identifiant = calendrierSimplifie.substring(calendrierSimplifie.indexOf("UID:"));
		this.identifiant = identifiant.substring(identifiant.indexOf("UID:")+4,identifiant.indexOf("\r\n"));
	}
		
	else {
		this.identifiant = "projetCMI@exemple.com";
	}
	
	//LOCATION (localisation)
	if (calendrierSimplifie.contains("LOCATION:")) {
		this.localisation = calendrierSimplifie.substring(calendrierSimplifie.indexOf("LOCATION:"));
		this.localisation = localisation.substring(localisation.indexOf("LOCATION:")+9,localisation.indexOf("\r\n"));
	}
	else {
		this.localisation = " - ";
	}
	
	//SUMMARY (titre)
	if (calendrierSimplifie.contains("SUMMARY:")) {
		this.titre = calendrierSimplifie.substring(calendrierSimplifie.indexOf("SUMMARY:"));
		this.titre = titre.substring(titre.indexOf("SUMMARY:")+8,titre.indexOf("\r\n"));
	}
	else {
		this.titre = "Fichier ICS";
	}
	
	//DESCRIPTION (informations suppl\u00e9mentaires)
	if (calendrierSimplifie.contains("DESCRIPTION:")) {
		this.description  = calendrierSimplifie.substring(calendrierSimplifie.indexOf("DESCRIPTION:"));
		this.description  = description .substring(description .indexOf("DESCRIPTION:")+12,description .indexOf("\r\n"));
	}
	else {
		this.description = " - ";
	}
	
	//DTSTART (Date de d\u00e9but)
	String tempDateDebut ="";
	if (calendrierSimplifie.contains("DTSTART:")) {
		tempDateDebut  = calendrierSimplifie.substring(calendrierSimplifie.indexOf("DTSTART:"));
		tempDateDebut  = tempDateDebut .substring(tempDateDebut .indexOf("DTSTART:")+8,tempDateDebut .indexOf("\r\n"));
	}
	else {
		tempDateDebut = "20000101T010100";
	}
	dateDebut = new DateICS(tempDateDebut);
	
	//DTEND (Date de d\u00e9but)
	String tempDateFin ="";
	if (calendrierSimplifie.contains("DTEND:")) {
		tempDateFin = calendrierSimplifie.substring(calendrierSimplifie.indexOf("DTEND:"));
		tempDateFin = tempDateFin.substring(tempDateFin.indexOf("DTEND:")+6,tempDateFin.indexOf("\r\n"));
	}
	else {
		tempDateFin = "20000101T010000";
	}
	dateFin = new DateICS(tempDateFin);
	
	//ORGANIZER
	if (calendrierSimplifie.contains("ORGANIZER;CN=")) {
		this.createur = calendrierSimplifie.substring(calendrierSimplifie.indexOf("ORGANIZER;CN="));
		this.createur = createur.substring(createur.indexOf("ORGANIZER;CN=")+13,createur.indexOf(":MAILTO:"));
	}
	else {
		this.createur = " - ";
	}
	
	//MAILTO (Email)
	if (calendrierSimplifie.contains(":MAILTO:")) {
		this.email = calendrierSimplifie.substring(calendrierSimplifie.indexOf(":MAILTO:"));
		this.email = email.substring(email.indexOf(":MAILTO:")+8,email.indexOf("\r\n"));
	}
	else {
		this.email = "projetCMI@exemple2.com";
	}

 }
 
 /**
 *Appelle les fonctions correspondantes de DateICS pour modifier l'heure
 @param nbHeure le nombre d'heure \u00e0 rajouter (entre -24 et 24)
 */	
 public void modifierHeure(int nbHeure) {
	 if (nbHeure>0){
		this.dateDebut.ajouterHeure(nbHeure);
		this.dateFin.ajouterHeure(nbHeure);
	 }
	 else{
		this.dateDebut.decrementerHeure(nbHeure);
		this.dateFin.decrementerHeure(nbHeure);
	 }
 }
 
 //Affichage version utilisateur
 public void affichageComplet() {
	System.out.println("\n - "+titre+" - ");
	System.out.println("Commence le "+ dateDebut.toString()+".");
	System.out.println("Termine le "+ dateFin.toString()+".");
	System.out.println("Se d\u00e9roule \u00e0 "+localisation+"."); // é = \u00e9 ; à = \u00e0 ; ê = \u00ea
	System.out.println("Organis\u00e9 par "+createur+".");
	System.out.println("Adresse mail : "+email+".");
	System.out.println("Informations suppl\u00e9mentaires : "+description+"\n");
 }
 
 //Getters
 public String getIdentifiant() {
	return identifiant; }
 
 public String getTitre() {
	return titre;}
 
 public String getDescription() {
	return description;}
 
 public String getCreateur() {
	return createur;}
 
 public String getEmail() {
	return email;}
 
 public String getDateDebut() {
	return dateDebut.format();
 }
 
 public String getDateFin() {
	return dateFin.format();
 }
 
 public String getLocalisation() {
	return localisation;}
  
}