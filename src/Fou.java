public class Fou extends Piece {
    public Fou(PieceCouleur couleur, Position position) {
        super(couleur, position);
    }

    @Override
    public boolean peutBouger(Position newPosition, Piece[][] board) {
        ///////////////////
        //// DIAGONALE ////
        /// ///////////////
        int ligneDiff = Math.abs(position.getLigne() - newPosition.getLigne());
        int colDiff = Math.abs(position.getColonne()- newPosition.getColonne());

        if (ligneDiff != colDiff) {
            return false;
        }

        int ligneDir = newPosition.getLigne() > position.getLigne() ? 1 : -1;
        int colDir = newPosition.getColonne() > position.getColonne() ? 1 : -1;
        int pas = ligneDiff - 1; // Pareil pour colonne comme on bouge autant en ligne

        for (int i = 1; i <= pas; i++) {
            if (board[position.getLigne() + i * ligneDir][position.getColonne() + i * colDir] != null) {
                return false; // Il y a quelqu'un dans le chemin
            }
        }


        //////////////////////////////
        //// PIECE SUR LE PASSAGE ////
        //////////////////////////////
        Piece destinationPiece = board[newPosition.getLigne()][newPosition.getColonne()];

        return destinationPiece == null || destinationPiece.getCouleur() != this.getCouleur();

    }

}
