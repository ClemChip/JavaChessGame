// Classe permettant de creer un echiquier de 8 par 8
public class ChessBoard {
    private Piece[][] board;

    public ChessBoard() {
        this.board = new Piece[8][8]; // 8x8 echiquier
        setupPieces(); // mettre en place les pièces dans leur premiere position
    }

    public Piece [][] getBoard(){
        return board;
    }

    public Piece getPiece(int ligne, int colonne) {
        return board[ligne][colonne];
    }

    public void setPiece(int ligne, int colonne, Piece piece) {
        board[ligne][colonne] = piece;
        if (piece != null) {
            piece.setPosition(new Position(ligne, colonne));
        }
    }

    private void setupPieces() {
        // Place Tours
        board[0][0] = new Tour(PieceCouleur.BLACK, new Position(0, 0));
        board[0][7] = new Tour(PieceCouleur.BLACK, new Position(0, 7));
        board[7][0] = new Tour(PieceCouleur.WHITE, new Position(7, 0));
        board[7][7] = new Tour(PieceCouleur.WHITE, new Position(7, 7));
        // Place Chevaliers
        board[0][1] = new Chevalier(PieceCouleur.BLACK, new Position(0, 1));
        board[0][6] = new Chevalier(PieceCouleur.BLACK, new Position(0, 6));
        board[7][1] = new Chevalier(PieceCouleur.WHITE, new Position(7, 1));
        board[7][6] = new Chevalier(PieceCouleur.WHITE, new Position(7, 6));
        // Place Fous
        board[0][2] = new Fou(PieceCouleur.BLACK, new Position(0, 2));
        board[0][5] = new Fou(PieceCouleur.BLACK, new Position(0, 5));
        board[7][2] = new Fou(PieceCouleur.WHITE, new Position(7, 2));
        board[7][5] = new Fou(PieceCouleur.WHITE, new Position(7, 5));
        // Place Reines
        board[0][3] = new Reine(PieceCouleur.BLACK, new Position(0, 3));
        board[7][3] = new Reine(PieceCouleur.WHITE, new Position(7, 3));
        // Place Rois
        board[0][4] = new Roi(PieceCouleur.BLACK, new Position(0, 4));
        board[7][4] = new Roi(PieceCouleur.WHITE, new Position(7, 4));
        // Place Pions
        for (int i = 0; i < 8; i++) {
            board[1][i] = new Pion(PieceCouleur.BLACK, new Position(1, i));
            board[6][i] = new Pion(PieceCouleur.WHITE, new Position(6, i));
        }
    }

    public void bougerPiece(Position debut, Position fin) {
        // Vérifie s'il y a une pièce en position de départ et si la piece peut bouger
        if (board[debut.getLigne()][debut.getColonne()] != null && board[debut.getLigne()][debut.getColonne()].peutBouger(fin, board)) {

            // Effectue le mouvement : place la pièce en position finale
            board[fin.getLigne()][fin.getColonne()] = board[debut.getLigne()][debut.getColonne()];

            // Met à jour la position
            board[fin.getLigne()][fin.getColonne()].setPosition(fin);

            // Efface la position de départ
            board[debut.getLigne()][debut.getColonne()] = null;
        }
    }
}