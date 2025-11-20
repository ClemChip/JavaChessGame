public class Tour extends Piece{
    public Tour(PieceCouleur couleur, Position position) {
        super(couleur, position);
    }

    @Override
    public boolean peutBouger(Position newPosition, Piece[][] board) {
        /*
         - regle : une tour avance horizontalement ou verticalement tant qu'elle ne recontre pas d'obstacle
         */

        ////////////////////
        /////// LIGNE //////
        ////////////////////
        if (position.getLigne() == newPosition.getLigne()) {
            int distance = Math.abs(newPosition.getColonne() - position.getColonne());
            if (distance > 1) {
                // Pour retrouver la direction du mouvement
                int direction = (newPosition.getColonne() - position.getColonne()) > 0 ? 1 : -1;
                for (int i = 1; i < distance; i++) {
                    int colonneAVerifier = position.getColonne() + (i * direction);
                    if (board[position.getLigne()][colonneAVerifier] != null) {
                        return false; // Il y a quelqu'un dans le chemin
                    }
                }
            }
        }

        ////////////////////
        ////// COLONNE /////
        ////////////////////
        else if (position.getColonne() == newPosition.getColonne()) {
            int distance = Math.abs(newPosition.getLigne() - position.getLigne());
            if (distance > 1) {
                // Pour retrouver la direction du mouvement
                int direction = (newPosition.getLigne() - position.getLigne()) > 0 ? 1 : -1;
                for (int i = 1; i < distance; i++) {
                    int ligneAVerifier = position.getLigne() + (i * direction);
                    if (board[ligneAVerifier][position.getColonne()] != null) {
                        return false; // Il y a quelqu'un dans le chemin
                    }
                }
            }
        }

        else {
            return false; // Ni horizontal ni vertical
        }

        //////////////////////////////
        //// PIECE SUR LE PASSAGE ////
        //////////////////////////////
        Piece destinationPiece = board[newPosition.getLigne()][newPosition.getColonne()];
        // S'il n'y a personne
        if (destinationPiece == null) {
            return true;
        }
        // Capture la piece
        else if (destinationPiece.getCouleur() != this.getCouleur()) {
            return true;
        }

        return false; // Piece de son équipe

    }
}
