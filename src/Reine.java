/**
 * La Reine va se déplacer comme la Tour et le Fou
 */

public class Reine extends Piece {
    public Reine(PieceCouleur couleur, Position position) {
        super(couleur, position);
    }

    @Override
    public boolean peutBouger(Position newPosition, Piece[][] board) {

        if (newPosition.equals(this.position)) {
            return false;
        }

        boolean ligneDroite = position.getLigne() == newPosition.getLigne() || position.getColonne() == newPosition.getColonne();

        int ligneDiff = Math.abs(position.getLigne() - newPosition.getLigne());
        int colDiff = Math.abs(position.getColonne() - newPosition.getColonne());

        boolean diagonale = ligneDiff == colDiff;

        // On n'a pas de diagonale
        if (!ligneDroite && !diagonale) {
            return false;
        }

        /*
         * Récupérer la direction en ligne et en colonne
         * Integer.compare(a, b) retourne :
         * -1 si a < b, 0 si a == b, +1 si a > b
         */
        int ligneDir = Integer.compare(newPosition.getLigne(), position.getLigne());
        int colDir = Integer.compare(newPosition.getColonne(), position.getColonne());

        ////////////////////////
        //// CAPTURER PIECE ////
        ////////////////////////
        int ligneActuelle = position.getLigne() + ligneDir;
        int colActuelle = position.getColonne() + colDir;

        while (ligneActuelle != newPosition.getLigne() || colActuelle != newPosition.getColonne()) {
            if (board[ligneActuelle][colActuelle] != null) {
                return false;
            }
            ligneActuelle += ligneDir;
            colActuelle += colDir;
        }


        Piece destinationPiece = board[newPosition.getLigne()][newPosition.getColonne()];

        return destinationPiece == null || destinationPiece.getCouleur() != this.getCouleur();

    }
}
