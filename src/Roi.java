public class Roi extends Piece {
    public Roi(PieceCouleur couleur, Position position) {
        super(couleur, position);
    }

    @Override
    public boolean peutBouger(Position newPosition, Piece[][] board) {

        int ligneDiff = Math.abs(position.getLigne() - newPosition.getLigne());
        int colDiff = Math.abs(position.getColonne()- newPosition.getColonne());

        boolean estUnMouvement = ligneDiff <= 1 && colDiff <= 1 && !(ligneDiff == 0 && colDiff == 0);
        if (!estUnMouvement) {
            return false;
        }

        //////////////////////////////
        //// PIECE SUR LE PASSAGE ////
        //////////////////////////////
        Piece destinationPiece = board[newPosition.getLigne()][newPosition.getColonne()];

        return destinationPiece == null || destinationPiece.getCouleur() != this.getCouleur();

    }

}
