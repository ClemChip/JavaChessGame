public class Chevalier extends Piece{
    public Chevalier(PieceCouleur couleur, Position position) {
        super(couleur, position);
    }

    @Override
    public boolean peutBouger(Position newPosition, Piece[][] board) {
        if (newPosition.getLigne() == position.getLigne() && newPosition.getColonne() == position.getColonne()) {
            return false;
        }

        //////////////////
        //// MVT EN L ////
        /// //////////////


        return false;
    }
}