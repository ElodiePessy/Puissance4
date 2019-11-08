
import java.util.Random;
import java.util.Scanner;
import java.lang.NumberFormatException;
 
public class JeuPuissance4 {
    String nameJoueur1;
    String nameJoueur2;
    int joueur1 = 1;
    int joueur2 = 2;
    static int[][] plateau = new int[6][7];
    Scanner entree = new Scanner(System.in);
 
    /**
     * definit le nom des joueurs
     * 
     * @param args
     */
    public void demanderPrenomJoueurs() {
        System.out.println("Joueur 1 : Entrer votre prénom");
        nameJoueur1 = entree.nextLine();
        System.out.println("Joueur 2 : Entrer votre prénom");
        nameJoueur2 = entree.nextLine();
    }
 
    /**
     * choisit aléatoirement le joueur qui commence
     */
    public void choisirJoueurAleatoire() {
        Random r = new Random();
        int nombreAleatoire = r.nextInt(2);
        switch (nombreAleatoire) {
        case 0:
            System.out.println("Le premier joueur à commencer est " + nameJoueur1);
            break;
        case 1:
            System.out.println("Le premier joueur à commencer est " + nameJoueur2);
            break;
        default:
            System.out.println("erreur");
        }
    }
 
    /**
     * permet d'afficher la plateau du puissance 4
     */
    public void afficherPlateau() {
 
        for (int ligne = 0; ligne < plateau.length; ligne++) {
            for (int col = 0; col < plateau[0].length; col++) {
                System.out.print(plateau[ligne][col] + "\t");
            }
            System.out.println();
        }
        System.out.println();
        System.out.println("1" + "\t" + "2" + "\t" + "3" + "\t" + "4" + "\t" + "5" + "\t" + "6" + "\t" + "7");
    }
 
    /**
     * cherche 4 pions alignés :
     * renvoie true si 4 pions sont alignés avec deux boucles qui vérifie les horizontales,  les verticales
     * renvoie true si 4 pions sont alignés avec quatre boucles qui vérifie les diagonales
     */
    public static boolean cherche4alignes1() {
 
        // Vérifie les horizontales ( - )
        for (int ligne = 0; ligne < plateau.length; ligne++) {
            if (cherche4alignes(0, ligne, 1, 0)) {
                return true;
            }
        }
 
        // Vérifie les verticales ( ¦ )
        for (int col = 0; col < plateau.length; col++) {
            if (cherche4alignes(col, 0, 0, 1)) {
                return true;
            }
        }
        // Diagonales (cherche depuis la ligne du bas)
        for (int col = 0; col < plateau.length; col++) {
            // Première diagonale ( / )
            if (cherche4alignes(col, 0, 1, 1)) {
                return true;
            }
        }
 
        // Deuxième diagonale ( \ )
        for (int col = 0; col < plateau.length; col++) {
            if (cherche4alignes(col, 0, -1, 1)) {
                return true;
            }
        }
 
        // Diagonales (cherche depuis les colonnes gauches et droites)
        for (int ligne = 0; ligne < plateau.length; ligne++) {
            // Première diagonale ( / )
            if (cherche4alignes(0, ligne, 1, 1)) {
                return true;
            }
        }
 
        // Deuxième diagonale ( \ )
        for (int ligne = 0; ligne < plateau.length; ligne++) {
            if (cherche4alignes(plateau.length - 1, ligne, -1, 1)) {
                return true;
            }
        }
        return false;
    }


 
   /**
    * méthode qui vérifie si le plateau est plein
    * @return true si le plateau est plein
    */
    public static boolean plateauPlein() {
        int col = 0;
        while (plateau[0][col] != 0) {
            col++;
            if (col == 7) {
                System.out.println("la partie est terminée, il n'y a pas de gagnants!");
                return true;
            }
        }
        return false;
    }
 
    /**
     * méthode qui permet de vérifier l'allignement de quatre jetons egaux dans le plateau
     * @param oCol  colonne d'origine de la vérification
     * @param oLigne ligne d'origine de la vérification
     * @param dCol  deplacement dans la colonne de vérification
     * @param dLigne déplacement dans la ligne de vérification
     * @return true si lignes de 4 jetons egaux trouvés
     */
    public static boolean cherche4alignes(int oCol, int oLigne, int dCol, int dLigne) {
        int VIDE = 0;
        int couleur = VIDE;
        int compteur = 0;
        int curCol = oCol;
        int curRow = oLigne;
 
        while ((curCol >= 0) && (curCol < plateau[0].length) && (curRow >= 0) && (curRow < plateau.length)) {
            if (plateau[curRow][curCol] != couleur) {
                couleur = plateau[curRow][curCol];
                compteur = 1;
            } else {
                compteur++;
            }
            if ((couleur != VIDE) && (compteur == 4)) {
                return true;
            }
            curCol += dCol;
            curRow += dLigne;
        }
        return false;
    }

    /** méthode qui vérifie la colonne choisit
     * 
     * @param col renvoie la colonne choisit par le joueur
     * @return false si la colonne choisit est en dehors du plateau ou si la colonne est pleine
     */
 
    public boolean verifierColonne(int col) {
 
            if ((col < 0) || (col > plateau.length)) {
                System.out.println("veuillez choisir une colonne entre 1 à 7");
                return false;
            }
 
            else if (plateau[0][col] != 0) {
                System.out.println("colonne pleine, veuillez choisir une colonne");
                return false;
            }

        return true;
    }
 
   /** methode qui permet à chaque joueur de jouer chacun leur tour tant que la saissi est bonne
    * et qu'il n'y a pas de gagnant
    * @param estJoueur1 correspond au premier joueur
    */
    public void jouerCoup(boolean estJoueur1) {
 
        int col = 0;
        if(estJoueur1){
            System.out.println("Premier joueur veuillez choisir la colonne ou mettre votre jeton");
        }else{
            System.out.println("Deuxieme joueur veuillez choisir la colonne ou mettre votre jeton");
        }
        
 
        do {
            try{
                col = Integer.parseInt(entree.next());
                col = col - 1;
            }
            catch (NumberFormatException e) {
                System.out.println("veuillez choisir un chiffre");
                col =-1;
            }
        }
 
        while (verifierColonne(col) == false);
 
 
        for (int ligne = plateau.length-1; ligne >= 0; ligne--) {
            if (plateau[ligne][col] == 0) {
                if(estJoueur1){
                    plateau[ligne][col] = joueur1;
                }else{
                    plateau[ligne][col] = joueur2;
                }
                break;
            }
        }
        afficherPlateau();
    }
     
 
 
    public static void main(String[] args) {
        JeuPuissance4 partie = new JeuPuissance4();
        partie.demanderPrenomJoueurs();
        partie.choisirJoueurAleatoire();
        while (cherche4alignes1() == false || plateauPlein() == false) {
            if (plateauPlein() == true) {
                break;
            }
            partie.jouerCoup(true);
            if (cherche4alignes1() == true) {
                System.out.println("Le premier joueur a gagné");
                break;
            }
            partie.jouerCoup(false);
            if (cherche4alignes1() == true) {
                System.out.println("Le deuxieme joueur a gagné");
                break;
            }
        }
    }
}