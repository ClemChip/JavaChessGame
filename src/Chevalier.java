public class Chevalier extends Piece{
    public Chevalier(PieceCouleur couleur, Position position) {
        super(couleur, position);
    }

    @Override
    public boolean peutBouger(Position newPosition, Piece[][] board) {
        //////////////////
        //// MVT EN L ////
        /// //////////////
        int ligneDiff = Math.abs(position.getLigne() - newPosition.getLigne());
        int colDiff = Math.abs(position.getColonne()- newPosition.getColonne());

        boolean peutBouger = (ligneDiff == 2 && colDiff == 1) || (ligneDiff == 1 && colDiff == 2);
        if (!peutBouger) {
            return false;
        }

        //////////////////////////////
        //// PIECE SUR LE PASSAGE ////
        //////////////////////////////
        Piece destinationPiece = board[newPosition.getLigne()][newPosition.getColonne()];

        return destinationPiece == null || destinationPiece.getCouleur() != this.getCouleur();
    }
}