/** 
*Cette classe permet de g\u00e9rer et manipuler la date du fichierICS . 
* Elle permet d'extraire la date qui dans le fichierICS, qui se pr\u00e9sente sous la forme ("yyyy-MM-dd T HH:mm Z") 
* et manipule s\u00e9paremment l'annee ,le mois, le jour ,l'heure, les minutes et les secondes
*@author Duval Antoine , Th\u00e9ologien Antoine , Noizet Jonathan , Bouchhioua Wejdane 
*@version 1.0 
 */
public class DateICS {
	
	//Attributs
	private int annee;
	private int mois;
	private int jour;
	private int heure;
	private int minute;
	private int seconde;
	
    /**
	*Constructeur par initilisation via un String
	*@param date la chaine de caracteres de la date
	*/
	public DateICS(String date) {
		String an = date.substring(0,4);
		String moi = date.substring(4,6);
		String jo = date.substring(6,8);
		String heu = date.substring(9,11);
		String min = date.substring(11,13);
		String sec = date.substring(13,15);
		
		jour = Integer.parseInt(jo);
		mois =Integer.parseInt(moi);
		annee = Integer.parseInt(an);
		heure = Integer.parseInt (heu);
		minute = Integer.parseInt(min);
		seconde = Integer.parseInt(sec);
	}
	
	//Liste des mois
	String [] ListeMois = {"Janvier","Fevrier","Mars","Avril","Mai","Juin","Juillet","Aout","Septembre","Octobre","Novembre","Decembre"};
	
	/**
	*Methode toString
	*/
	public String toString() {
		return(jour+" "+ListeMois[mois-1]+" "+annee+" \u00e0 "+heure+"h "+minute+"m "+seconde+"s");
	}
	
	/**
	*Transforme les attributs en une chaine de caracteres du format "yyyy-MM-dd T HH:mm"
	*/
	public String format() {
		String anneeM = (""+annee);
		if (anneeM.length() == 1) {
			anneeM = ("000"+anneeM);
		} else {
			if (anneeM.length() == 2) {
				anneeM = ("00"+anneeM);
			} else {
				if(anneeM.length() == 3) {
					anneeM = ("0"+anneeM);
				}
			}
		}
		
		String moisM = (""+mois);
		if (moisM.length() == 1) {
			moisM = ("0"+moisM);
		}
		
		String jourM = (""+jour);
		if (jourM.length() == 1) {
			jourM = ("0"+jourM);
		}
		
		String heureM = (""+heure);
		if (heureM.length() == 1) {
			heureM = ("0"+heureM);
		}
		
		String minuteM = (""+minute);
		if (minuteM.length() == 1) {
			minuteM = ("0"+minuteM);
		}
		
		String secondeM = (""+seconde);
		if (secondeM.length() == 1) {
			secondeM = ("0"+secondeM);
		}
		
		return(anneeM+""+moisM+""+jourM+"T"+heureM+""+minuteM+""+secondeM);
	}
	
	//Setters
	public void setJour(int j){
        jour = j;
    }
    public void setMois(int m){
        mois = m;
    }
    public void setAnnee(int a){
        annee = a;
    }
    public void setHeure(int h){
        heure = h;
    }
    public void setMinute(int mi){
        minute = mi;
    }
    public void setSeconde(int s){
        seconde = s;
    }
	
	//Getters
	public int getJour(){
		return jour;
	}
	public int getMois(){
		return mois;
	}
	public int getAnnee(){
		return annee;
	}
	public int getHeure(){
		return heure;
	}
	public int getMinute(){
		return minute;
	}
	public int getSeconde(){
		return seconde;
	}
	
	/**
	*La fonction v\u00e9rifie que l'ann\u00e9e est bissextile
	*pour ensuite savoir si f\u00e9vrier a 28 ou 29 jours
	*/
	public boolean estBissextile() {
		boolean verif = false;
		if ((this.annee%4 == 0)&&(this.annee%100!=0)) {
			verif = true;
		}
		else {
			if (this.annee%400 == 0){
				verif = true;
			}
		}
		return verif;
	}

	/**
	*Cette fonction va ajouter un nombre d'heure et ainsi decaler le reste des attributs si besoin
	*@param h le nombre d'heure positif
	*/
	public void ajouterHeure(int h){
		heure += h;
		if (heure >= 24){
			heure -= 24;
			jour += 1;
			if ((mois==1)||(mois==3)||(mois==5)||(mois==7)||(mois==8)||(mois==10)||(mois==12)){
				if (jour > 31){
					jour -= 31;
					mois += 1;
					if (mois > 12){
						annee += 1;
						mois = 1;
					}
				}
			}
			else if (mois == 2){
				if (estBissextile() == true){
					if (jour > 29){
						jour -= 29;
						mois += 1;
					}
				}
				else {
					if (jour > 28){
						jour -= 28;
						mois += 1;
					}
				}
			}
			else{
				if (jour > 30){
					jour -= 30;
					mois += 1;
				}
			}
		}
	}

	/**
	*Cette fonction va enlever un nombre d'heure et ainsi decaler le reste des attributs si besoin
	*@param h le nombre d'heure negatif
	*/
	public void decrementerHeure(int h){
		heure += h;
		if (heure < 0){
			heure += 24;
			jour -= 1;
			if ((mois==1)||(mois==2)||(mois==4)||(mois==6)||(mois==8)||(mois==9)||(mois==11)){
				if (jour < 1){
					jour += 31;
					mois -= 1;
					if (mois < 1){
						annee -= 1;
						mois = 12;
					}
				}
			}
			else if (mois == 3){
				if (estBissextile() == true){
					if (jour < 1){
						jour += 29;
						mois -= 1;
					}
				}
				else {
					if (jour < 1){
						jour += 28;
						mois -= 1;
					}
				}
			}
			else{
				if (jour < 1){
					jour += 30;
					mois -= 1;
				}
			}
		}
	}
	
}


	