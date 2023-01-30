import java.io.File;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Scanner;
import java.util.InputMismatchException;

/**
* Cette classe est la classe du main o\u00f9 toute la magie va se manifester 
* @author Duval Antoine , Th\u00e9ologien Antoine , Noizet Jonathan , Bouchhioua Wejdane.
* @version 1.0
 */
public class AppICS {
	
 /**
 * Cette fonction est le point d'entr\u00e9e de notre programme.
 * Elle int\u00e9ragit avec l'utilisateur, et lui demande s'il veut afficher, modifier, cr\u00e9er un fichier ICS ou sortir du programme.
 * @throws
 */
 public static void main(String[] args) throws MalformedURLException, IOException {
	Scanner clavier = new Scanner(System.in); // \u00e9 = é ; \u00e0 = à ; ê = \u00ea ; http://www.eteks.com/tips/tip3.html
	System.out.println("Bienvenue dans le programme java visant \u00e0 g\u00e9rer les fichiers ICS de l'\u00e9quipe QI.");
	System.out.println("Bouchhioua Wejdene ; Duval Antoine ; Noizet Jonathan ; Th\u00e9ologien Antoine.\n");
	boolean verif=false;
	String choix;
	boolean premierTour = true;
	while(verif==false){
		if (premierTour) {
			System.out.print("Que voulez-vous faire ?"+"\n"+"1 : Afficher un fichier ICS."+"\n"+"2 : Modifier un fichier ICS."+"\n"+"3 : Cr\u00e9er un fichier ICS."+"\n"+"4 : Fermer le programme.\n=> ");
		}
		else {
			System.out.print("Que voulez-vous faire \u00e0 pr\u00e9sent ?"+"\n"+"1 : Afficher un fichier ICS."+"\n"+"2 : Modifier un fichier ICS."+"\n"+"3 : Cr\u00e9er un fichier ICS."+"\n"+"4 : Fermer le programme.\n=> ");
		}
		choix=clavier.nextLine();
		if(choix.equals("1")){
			afficherFichier();
			System.out.println("\n - - - - - - - - - - - - - - - - - - - - - - - - - - - - -");
			System.out.println("\n << Affichage termin\u00e9 >>\n");
			premierTour = false;
		} 
		else {
			if(choix.equals("2")){
				modifierFichier();
				System.out.println("\n - - - - - - - - - - - - - - - - - - - - - - - - - - - - -");
				System.out.println("\n << Modification termin\u00e9e >>\n");
					premierTour = false;
			}
			else {
				if(choix.equals("3")){
					creationFichier();
					System.out.println("\n - - - - - - - - - - - - - - - - - - - - - - - - - - - - -");
					System.out.println("\n << Cr\u00e9ation termin\u00e9e >>\n");
					premierTour = false;
				}
				else {
					if(choix.equals("4")){
						verif=true;
						if (premierTour) {
							System.out.println("\nVous partez sans rien faire... :'(\n");
						}
						else {
							System.out.println("\nAu revoir !\n");
						}
					}
					else {
						System.out.println("\nD\u00e9sol\u00e9, mais ceci n'est pas une action possible...\n");
					}
				}
			}
		}
	}
 }

	/**
	* Cette m\u00e9thode affiche le fichier ICS en demadant \u00e0 l'utilisateur son nom.
	* Elle cr\u00e9er l'objet calendrier, le simplifie, le remplit puis fait son affichage complet.
	* @exception MalformedURLException est d\u00e9clench\u00e9e si le nom du fichier est introuvable 
	* @exception IOException est d\u00e9clench\u00e9 si le fichier ne se trouve pas dans le r\u00e9pertoire 
	*/
	public static void afficherFichier() throws MalformedURLException, IOException {
		Scanner clavier=new Scanner(System.in);
		DataICS Calendrier1;
		boolean veriif;
		String nomFichier="";
		//Demande le nom du fichier de l'utilisateur
		do {
			veriif = true;
			System.out.print("\nEntrez le nom du fichier ICS \u00e0 ouvrir : ");
			nomFichier ="../../Fichiers ics/"+clavier.nextLine();
			
			//Et ajoute l'extension .ics s'il ne l'a pas \u00e9crite
			if (nomFichier.endsWith(".ics") == false) {
				nomFichier+=".ics";
			}
			
			//Cr\u00e9ation d'un objet dans la classe DataICS
			try{
				Calendrier1 = new DataICS(nomFichier);
			}
			catch(MalformedURLException e){
				System.out.println("Nom introuvable");
				veriif = false;
			}
			catch(IOException i){
				System.out.println("Ce fichier n'est pas pr\u00e9sent dans le r\u00e9pertoire, entrez en un autre : ");
				veriif = false;
			}
		} while(veriif==false);
	
	Calendrier1 = new DataICS(nomFichier);
	String fichierEntier = Calendrier1.getFichierEntier();
	
	//On retire le prefixe et le suffixe pour ameliorer la lecture (avec affichage pour tester)
	Calendrier1.simplification();
	
	//Remplissage des attributs de l'objet Calendrier1
	Calendrier1.remplissageAtt();
	
	//Test de l'affichage utilisateur
	Calendrier1.affichageComplet();
	}
	
	/** 
	* Cette m\u00e9thode permet de modifier le fichierICS et de d\u00e9caler au maximum de 24 l'heure.
	* @exception MalformedURLException Si le nom du fichier est introuvable 
	* @exception IOException Si le fichier ne se trouve pas dans le r\u00e9pertoire  
	 */
	public static void modifierFichier() throws MalformedURLException, IOException  {
		Scanner clavier=new Scanner(System.in);
		DataICS Calendrier2;
		boolean veriif;
		String nomFichier="";
		//Demande le nom du fichier de l'utilisateur
		do {
			veriif = true;
			System.out.print("\nEntrez le nom du fichier ICS \u00e0 ouvrir : ");
			nomFichier ="../../Fichiers ics/"+clavier.nextLine();
			
			//Et ajoute l'extension .ics s'il ne l'a pas \u00e9crite
			if (nomFichier.endsWith(".ics") == false) {
				nomFichier+=".ics";
			}
			
			//Cr\u00e9ation d'un objet dans la classe DataICS
			try{
				Calendrier2 = new DataICS(nomFichier);
			}
			catch(MalformedURLException e){
				System.out.println("Nom introuvable");
				veriif = false;
			}
			catch(IOException i){
				System.out.println("Ce fichier n'est pas pr\u00e9sent dans le r\u00e9pertoire, entrez en un autre : ");
				veriif = false;
			}
		} while(veriif==false);
	
	Calendrier2 = new DataICS(nomFichier);
	String fichierEntier = Calendrier2.getFichierEntier();
	Calendrier2.simplification();
	Calendrier2.remplissageAtt();
	
	boolean verifH = true;
	int nbHeure = ' ';
	
	DateICS heureActuD = new DateICS(Calendrier2.getDateDebut());
	System.out.println("\nDate de d\u00e9but : "+heureActuD+".");
	DateICS heureActuF = new DateICS(Calendrier2.getDateFin());
	System.out.println("Date de fin : "+heureActuF+".");

	do {
			System.out.print("\nNombre d'heures \u00e0 d\u00e9caler : ");
			if (clavier.hasNextInt()) {
				nbHeure = clavier.nextInt();
				clavier.nextLine();
				if ((nbHeure>-25)&&(nbHeure<25)) {
					verifH = false;
				}
				else {
					System.out.print("\nVous ne pouvez d\u00e9caler que de 24 heures.");
				}
			}
			else {
				System.out.print("\nCeci n'est pas un nombre d'heures possible.");
				clavier.nextLine();
			}		
		} while(verifH);
		Calendrier2.modifierHeure(nbHeure);
		
		String chaine = "BEGIN:VCALENDAR\r\nVERSION:2.0\r\nPRODID://Projet CMI//\r\nMETHOD:PUBLISH\r\nBEGIN:VEVENT\r\nUID:projet@cmi.com\r\nLOCATION:";
		chaine += ""+Calendrier2.getLocalisation()+"\r\nSUMMARY:"+Calendrier2.getTitre()+"\r\nDESCRIPTION:"+Calendrier2.getDescription()+"\r\nDTSTAMP:20220303T080000\r\nORGANIZER;CN=";
		chaine += ""+Calendrier2.getCreateur()+":MAILTO:"+Calendrier2.getEmail()+"\r\nDTSTART:"+Calendrier2.getDateDebut()+"\r\nDTEND:"+Calendrier2.getDateFin()+"\r\nEND:VEVENT\r\nEND:VCALENDAR";
	
		FileOutputStream fos = new FileOutputStream(nomFichier);
		fos.write(chaine.getBytes());
		fos.flush();
		fos.close();
		
		Calendrier2.affichageComplet();
	
	}
	
	
	/**
	*Cette m\u00e9thode permet de cr\u00e9er le fichierICS en demandant \u00e0 l'utilisateur toutes les informations n\u00e9cessaires ici : le nom du fichier , le titre , la localisation,
	* la date de d\u00e9but (annee, mois, jour, heure, minute), date de fin(annee, mois, jour, heure, minute)
	* v\u00e9rifie aussi le cas d'une annee bissextile, en empeche l'utilisateur de rentrer une date de fin inferieure a la date de debut.
	* et pour finir cr\u00e9er et affiche le nouveau fichier
	*@throws IOException 
 	*/
	public static void creationFichier() throws IOException {
		Scanner clavier = new Scanner(System.in);
		
		//Creation d'un objet \u00e0 remplir :
		System.out.print("\nPouvez vous me donner le nom du fichier ICS \u00e0 cr\u00e9er : ");
		String nomNouveauFichier="../../Fichiers ics/" + clavier.nextLine();
		if (nomNouveauFichier.endsWith(".ics") == false) {
			nomNouveauFichier+=".ics";
		}
		
		System.out.print("\nTitre : ");
		String titre = clavier.nextLine();
		
		System.out.print("\nLocalisation : ");
		String localisation = clavier.nextLine();
		
	//Creation de la chaine dateDebut
		//Annee du debut
		boolean verifd1 = true;
		int anneeD=' ';
		do {
			System.out.print("\nAnn\u00e9e du d\u00e9but : ");
			if (clavier.hasNextInt()) {
				anneeD = clavier.nextInt();
				clavier.nextLine();
				if ((anneeD>0)&&(anneeD<10000)) {
					verifd1 = false;
				}
				else {
					System.out.print("\nCeci n'est pas une ann\u00e9e possible.");
				}
			}
			else {
				System.out.print("\nCeci n'est pas une ann\u00e9e possible.");
				clavier.nextLine();
			}		
		} while(verifd1);
		String anneeDebut = (""+anneeD);
		if (anneeDebut.length() == 1) {
			anneeDebut = ("000"+anneeDebut);
		} else {
			if (anneeDebut.length() == 2) {
				anneeDebut = ("00"+anneeDebut);
			} else {
				if(anneeDebut.length() == 3) {
					anneeDebut = ("0"+anneeDebut);
				}
			}
		}
		
		//Mois du d\u00e9but
		boolean verifd2 = true;
		int moisD = ' ';
		do {
			System.out.print("\nMois du d\u00e9but : ");
			if (clavier.hasNextInt()) {
				moisD = clavier.nextInt();
				clavier.nextLine();
				if ((moisD>0)&&(moisD<13)) {
					verifd2 = false;
				}
				else {
					System.out.print("\nCeci n'est pas un mois possible.");
				}
			}
			else {
				System.out.print("\nCeci n'est pas un mois possible.");
				clavier.nextLine();
			}		
		} while(verifd2);
		String moisDebut = (""+moisD);
		if (moisDebut.length() == 1) {
			moisDebut = ("0"+moisDebut);
		}
				
		//Jour du d\u00e9but
		boolean verifd3 = true;
		int jourD = ' ';
		do {
			System.out.print("\nJour du d\u00e9but : ");
			if (clavier.hasNextInt()) {
				jourD = clavier.nextInt();
				clavier.nextLine();
				if ((moisD==1)||(moisD==3)||(moisD==5)||(moisD==7)||(moisD==8)||(moisD==10)||(moisD==12)){
					if ((jourD>0)&&(jourD<32)) {
						verifd3 = false;
					}
					else {
						System.out.print("\nCeci n'est pas un jour possible.");
					}
				}
				else {
					if (moisD==2) {
						if ((jourD>0)&&(jourD<30)) {
							if (estBissextile(anneeD)){
								verifd3 = false;
							}
							else {
								if (jourD<29) {
									verifd3 = false;
								}
								else {
									System.out.print("\n"+anneeD+" n'est pas bissextile.");
								}
							}
						}
						else {
							System.out.print("\nCeci n'est pas un jour possible.");
						}
					}
					else {
						if ((jourD>0)&&(jourD<31)) {
							verifd3 = false;
						}
						else {
							System.out.print("\nCeci n'est pas un jour possible.");
						}
					}
				}
			}
			else {
				System.out.print("\nCeci n'est pas un jour possible.");
				clavier.nextLine();
			}		
		} while(verifd3);
		String jourDebut = (""+jourD);
		if (jourDebut.length() == 1) {
			jourDebut = ("0"+jourDebut);
		}
		
		//Heure du d\u00e9but
		boolean verifd4 = true;
		int heureD = ' ';
		do {
			System.out.print("\nNombre d'heures du d\u00e9but : ");
			if (clavier.hasNextInt()) {
				heureD = clavier.nextInt();
				clavier.nextLine();
				if ((heureD>=0)&&(heureD<25)) {
					verifd4 = false;
				}
				else {
					System.out.print("\nCeci n'est pas un nombre d'heures possible.");
				}
			}
			else {
				System.out.print("\nCeci n'est pas un nombre d'heures possible.");
				clavier.nextLine();
			}		
		} while(verifd4);
		String heureDebut = (""+heureD);
		if (heureDebut.length() == 1) {
			heureDebut = ("0"+heureDebut);
		}
		
		//Minute du d\u00e9but
		boolean verifd5 = true;
		int minuteD = ' ';
		do {
			System.out.print("\nNombre de minutes du d\u00e9but : ");
			if (clavier.hasNextInt()) {
				minuteD = clavier.nextInt();
				clavier.nextLine();
				if ((minuteD>=0)&&(minuteD<61)) {
					verifd5 = false;
				}
				else {
					System.out.print("\nCeci n'est pas un nombre de minutes possible.");
				}
			}
			else {
				System.out.print("\nCeci n'est pas un nombre de minutes possible.");
				clavier.nextLine();
			}		
		} while(verifd5);
		String minuteDebut = (""+minuteD);
		if (minuteDebut.length() == 1) {
			minuteDebut = ("0"+minuteDebut);
		}
		
	//Assemblage de ces 5 valeurs
		String dateDebut = (anneeDebut+""+moisDebut+""+jourDebut+"T"+heureDebut+""+minuteDebut+"00");
		
		
	//Creation de la chaine dateFin
		//Annee de la fin
		boolean veriff1 = true;
		int anneeF=' ';
		do {
			System.out.print("\nAnn\u00e9e de la fin : ");
			if (clavier.hasNextInt()) {
				anneeF = clavier.nextInt();
				clavier.nextLine();
				if ((anneeF>0)&&(anneeF<10000)) {
					if(anneeF<anneeD) {
						System.out.print("\nLa date de fin ne peut pas \u00eatre inf\u00e9rieure \u00e0 la date de d\u00e9but.");
					}
					else {
						veriff1 = false;
					}
				}
				else {
					System.out.print("\nCeci n'est pas une ann\u00e9e possible.");
				}
			}
			else {
				System.out.print("\nCeci n'est pas une ann\u00e9e possible.");
				clavier.nextLine();
			}		
		} while(veriff1);
		String anneeFin = (""+anneeF);
		if (anneeFin.length() == 1) {
			anneeFin = ("000"+anneeFin);
		} else {
			if (anneeFin.length() == 2) {
				anneeFin = ("00"+anneeFin);
			} else {
				if(anneeFin.length() == 3) {
					anneeFin = ("0"+anneeFin);
				}
			}
		}
		
		//Mois de la fin
		boolean veriff2 = true;
		int moisF = ' ';
		do {
			System.out.print("\nMois de la fin : ");
			if (clavier.hasNextInt()) {
				moisF = clavier.nextInt();
				clavier.nextLine();
				if ((moisF>0)&&(moisF<13)) {
					if((anneeF==anneeD)&&(moisF<moisD)) {
						System.out.print("\nLa date de fin ne peut pas \u00eatre inf\u00e9rieure \u00e0 la date de d\u00e9but.");
					}
					else {
						veriff2 = false;
					}
				}
				else {
					System.out.print("\nCeci n'est pas un mois possible.");
				}
			}
			else {
				System.out.print("\nCeci n'est pas un mois possible.");
				clavier.nextLine();
			}		
		} while(veriff2);
		String moisFin = (""+moisF);
		if (moisFin.length() == 1) {
			moisFin = ("0"+moisFin);
		}
			
		//Jour de la fin
		boolean veriff3 = true;
		int jourF = ' ';
		do {
			System.out.print("\nJour de fin : ");
			if (clavier.hasNextInt()) {
				jourF = clavier.nextInt();
				clavier.nextLine();
				if ((moisD==1)||(moisD==3)||(moisD==5)||(moisD==7)||(moisD==8)||(moisD==10)||(moisD==12)){
					if ((jourF>0)&&(jourF<32)) {
						if((anneeF==anneeD)&&(moisF==moisD)&&(jourF<jourD)) {
							System.out.print("\nLa date de fin ne peut pas \u00eatre inf\u00e9rieure \u00e0 la date de d\u00e9but.");
						}
						else {
							veriff3 = false;
						}
					}
					else {
						System.out.print("\nCeci n'est pas un jour possible.");
					}
				}
				else {
					if (moisF==2) {
						if ((jourF>0)&&(jourF<30)) {
							if (estBissextile(anneeF)){
								if((anneeF==anneeD)&&(moisF==moisD)&&(jourF<jourD)) {
									System.out.print("\nLa date de fin ne peut pas \u00eatre inf\u00e9rieure \u00e0 la date de d\u00e9but.");
								}
								else {
									veriff3 = false;
								}
							}
							else {
								if (jourF<29) {
									if((anneeF==anneeD)&&(moisF==moisD)&&(jourF<jourD)) {
										System.out.print("\nLa date de fin ne peut pas \u00eatre inf\u00e9rieure \u00e0 la date de d\u00e9but.");
									}
									else {
										veriff3 = false;
									}
								}
								else {
									System.out.print("\n"+anneeD+" n'est pas bissextile.");
								}
							}
						}
						else {
							System.out.print("\nCeci n'est pas un jour possible.");
						}
					}
					else {
						if ((jourF>0)&&(jourF<31)) {
							if((anneeF==anneeD)&&(moisF==moisD)&&(jourF<jourD)) {
									System.out.print("\nLa date de fin ne peut pas \u00eatre inf\u00e9rieure \u00e0 la date de d\u00e9but.");
								}
								else {
									veriff3 = false;
								}
						}
						else {
							System.out.print("\nCeci n'est pas un jour possible.");
						}
					}
				}
			}
			else {
				System.out.print("\nCeci n'est pas un jour possible.");
				clavier.nextLine();
			}		
		} while(veriff3);
		String jourFin = (""+jourF);
		if (jourFin.length() == 1) {
			jourFin = ("0"+jourFin);
		}
		
		//Heure de la fin
		boolean veriff4 = true;
		int heureF = ' ';
		do {
			System.out.print("\nNombre d'heures de la fin : ");
			if (clavier.hasNextInt()) {
				heureF = clavier.nextInt();
				clavier.nextLine();
				if ((heureF>=0)&&(heureF<25)) {
					if((anneeF==anneeD)&&(moisF==moisD)&&(jourF==jourD)&&(heureF<heureD)) {
						System.out.print("\nLa date de fin ne peut pas \u00eatre inf\u00e9rieure \u00e0 la date de d\u00e9but.");
					}
					else {
						veriff4 = false;
					}
				}
				else {
					System.out.print("\nCeci n'est pas un nombre d'heures possible.");
				}
			}
			else {
				System.out.print("\nCeci n'est pas un nombre d'heures possible.");
				clavier.nextLine();
			}		
		} while(veriff4);
		String heureFin = (""+heureF);
		if (heureFin.length() == 1) {
			heureFin = ("0"+heureFin);
		}
				
		//Minute du d\u00e9but
		boolean veriff5 = true;
		int minuteF = ' ';
		do {
			System.out.print("\nNombre de minutes du d\u00e9but : ");
			if (clavier.hasNextInt()) {
				minuteF = clavier.nextInt();
				clavier.nextLine();
				if ((minuteF>=0)&&(minuteF<61)) {
					if((anneeF==anneeD)&&(moisF==moisD)&&(jourF==jourD)&&(heureF==heureD)&&(minuteF<=minuteD)) {
						System.out.print("\nLa date de fin ne peut pas \u00eatre inf\u00e9rieure \u00e0 la date de d\u00e9but.");
					}
					else {
						veriff5 = false;
					}
				}
				else {
					System.out.print("\nCeci n'est pas un nombre de minutes possible.");
				}
			}
			else {
				System.out.print("\nCeci n'est pas un nombre de minutes possible.");
				clavier.nextLine();
			}		
		} while(veriff5);
		String minuteFin = (""+minuteF);
		if (minuteFin.length() == 1) {
			minuteFin = ("0"+minuteFin);
		}
		
	//Assemblage de ces 5 valeurs
		String dateFin = (anneeFin+""+moisFin+""+jourFin+"T"+heureFin+""+minuteFin+"00");
		
		System.out.print("\nPr\u00e9nom et nom : ");
		String createur = clavier.nextLine();
		
		System.out.print("\nAdresse mail : ");
		String email = clavier.nextLine();
		
		System.out.print("\nInformations suppl\u00e9mentaires : ");
		String description = clavier.nextLine();
		
		String chaine = "BEGIN:VCALENDAR\r\nVERSION:2.0\r\nPRODID://Projet CMI//\r\nMETHOD:PUBLISH\r\nBEGIN:VEVENT\r\nUID:projet@cmi.com\r\nLOCATION:"+localisation+"\r\nSUMMARY:"+titre+"\r\nDESCRIPTION:"+description+"\r\nDTSTAMP:20220303T080000\r\nORGANIZER;CN="+createur+":MAILTO:"+email+"\r\nDTSTART:"+dateDebut+"\r\nDTEND:"+dateFin+"\r\nEND:VEVENT\r\nEND:VCALENDAR";
	
		FileOutputStream fos = new FileOutputStream(nomNouveauFichier);
		fos.write(chaine.getBytes());
		fos.flush();
		fos.close();
		
		System.out.println("\nVotre fichier \u00e0 bien \u00e9t\u00e9 cr\u00e9\u00e9 :");
		
		DataICS Calendrier3 = new DataICS(nomNouveauFichier);
		String fichierEntier = Calendrier3.getFichierEntier();
	
		//On retire le prefixe et le suffixe pour ameliorer la lecture (avec affichage pour tester)
		Calendrier3.simplification();
	
		//Remplissage des attributs de l'objet Calendrier1
		Calendrier3.remplissageAtt();
	
		//Test de l'affichage utilisateur
		Calendrier3.affichageComplet();
	}
	
	/**
	*La fonction v\u00e9rifie que l'ann\u00e9e est bissextile
	*pour ensuite savoir si f\u00e9vrier a 28 ou 29 jours
	*/
	public static boolean estBissextile(int annee) {
		boolean verif = false;
		if ((annee%4 == 0)&&(annee%100!=0)) {
			verif = true;
		}
		else {
			if (annee%400 == 0){
				verif = true;
			}
		}
		return verif;
	}
}