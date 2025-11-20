public class Pion extends Piece {
    public Pion(PieceCouleur couleur, Position position) {
        super(couleur, position);
    }

    @Override
    public boolean peutBouger(Position newPosition, Piece[][] board) {
        // Vers le haut pour les blancs donc -1 et vers les bas pour les noirs
        int direction = couleur == PieceCouleur.WHITE ? -1 : 1;
        /*
         - regle : un pion ne peut reculer
         - `ligneDiff == 1` -> se déplace d'une case
         */
        // Vérifie que oui, c'est bien une case en avant
        // Ex : pion en L6 vers L5 : (6-5) * (-1) = +1
        int ligneDiff = (newPosition.getLigne() - position.getLigne()) * direction;
        /*
         - `colDiff == 0` → avance tout droit (pas de capture)
         - `colDiff == +1` → se déplace d'une colonne vers la droite (capture)
         - `colDiff == -1` → se déplace d'une colonne vers la gauche (capture)
         - `|colDiff| > 1` → mouvement invalide, le pion ne peut pas sauter plusieurs colonnes
         */
        int colDiff = newPosition.getColonne() - position.getColonne();


        // Premier mouvement : une ou deux cases en avant
        // Le pion est à la bonne position
        boolean positionDepart = (position.getLigne() == 6 && couleur == PieceCouleur.WHITE) || (position.getLigne() == 1 && couleur == PieceCouleur.BLACK);
        // Conditions : le pion avance uniquement et de 2 et personne ne se trouve sur sa possible nvlle position
        if (colDiff == 0 && ligneDiff == 2 && positionDepart && board[newPosition.getLigne()][newPosition.getColonne()] == null ) {
            // Vérifier qu'il n'y a personne sur la case d'entre deux
            int caseMilieu = position.getLigne() + direction;
            if (board[caseMilieu][newPosition.getColonne()] == null) {
                return true;
            }
        }


        // Si on avance tout droit : pas de décalage en colonne, en avant en ligne et personne sur la nvlle case
        if (colDiff == 0 && ligneDiff == 1 && board[newPosition.getLigne()][newPosition.getColonne()] == null) {
            return true; // le pion peut avancer d'une case
        }


        // Capture d'une pièce en diagonale
        if (Math.abs(colDiff) == 1 && ligneDiff == 1 && board[newPosition.getLigne()][newPosition.getColonne()] != null && board[newPosition.getLigne()][newPosition.getColonne()].couleur != this.couleur ) {
            return true;
        }

        return false;

    }

}