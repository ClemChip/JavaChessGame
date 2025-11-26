public class Reine extends Piece {
    public Reine(PieceCouleur couleur, Position position) {
        super(couleur, position);
    }

    @Override
    public boolean peutBouger(Position newPosition, Piece[][] board) {

        //////////////////////////////
        //// PIECE SUR LE PASSAGE ////
        //////////////////////////////
        Piece destinationPiece = board[newPosition.getLigne()][newPosition.getColonne()];
        // S'il n'y a personne
        if (destinationPiece == null) {
            return true;
        }
        // Capture la piece
        else {
            return destinationPiece.getCouleur() != this.getCouleur();
        }
    }
}
