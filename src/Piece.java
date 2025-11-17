// Classe permettant de definir une piece du jeu

public abstract class Piece {
    protected Position position;
    protected PieceCouleur couleur;

    public Piece(PieceCouleur couleur, Position position) {
        this.couleur = couleur;
        this.position = position;
    }

    public PieceCouleur getCouleur() {
        return couleur;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public abstract boolean peutBouger(Position newPosition, Piece[][] board);

}
