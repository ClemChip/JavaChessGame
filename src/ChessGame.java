public class ChessGame {
    private ChessBoard board;
    private boolean tourBlanc = true;

    public ChessGame() {
        this.board = new ChessBoard();
    }

    public boolean faitUnMouvement(Position debut, Position fin) {
        Piece bougerPiece = board.getPiece(debut.getLigne(), debut.getColonne());
        if (bougerPiece == null || bougerPiece.getCouleur() != (tourBlanc ? PieceCouleur.WHITE : PieceCouleur.BLACK)) {
            return false;
        }

        // Si le coup est valide, on change de tour
        if (bougerPiece.peutBouger(fin, board.getBoard())) {
            board.bougerPiece(debut, fin);
            tourBlanc = !tourBlanc;
            return true;
        }

        return false;
    }

    //////////////////////
    //// ROI EN ECHEC ////
    //////////////////////
    public boolean enEchec(PieceCouleur roiCouleur) {
        Position roiPosition = trouverPositionRoi(roiCouleur);
        for (int ligne = 0; ligne < board.getBoard().length; ligne++) {
            for (int col = 0; col < board.getBoard()[ligne].length; col++) {
                Piece piece = board.getPiece(ligne, col);
                if (piece != null && piece.getCouleur() != roiCouleur) {
                    // La pièce peut-elle aller jusqu'au Roi ?
                    if (piece.peutBouger(roiPosition, board.getBoard())){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    ////////////////////////
    //// TROUVER LE ROI ////
    ////////////////////////
    private Position trouverPositionRoi(PieceCouleur couleur) {
        for (int ligne = 0; ligne < board.getBoard().length; ligne++) {
            for (int col = 0; col < board.getBoard()[ligne].length; col++) {
                Piece piece = board.getPiece(ligne, col);
                if (piece instanceof Roi && piece.getCouleur() == couleur) {
                    return new Position(ligne, col);
                }
            }
        }
        throw new RuntimeException("Le Roi n'a pas été trouvé, ça ne devrait jamais arriver.");
    }

    //////////////////////
    //// ECHEC ET MAT ////
    //////////////////////
    public boolean estEchecMat(PieceCouleur roiCouleur){
        if (!enEchec(roiCouleur)) {
            return false;
        }
        Position roiPosition = trouverPositionRoi(roiCouleur);
        Roi roi = (Roi) board.getPiece(roiPosition.getLigne(), roiPosition.getColonne());

        // Trouver un mouvement qui ferait partir le Roi de la mise en échec
        for (int ligneDecalage = -1; ligneDecalage <= 1; ligneDecalage++){
            for (int colDecalage = -1; colDecalage <= 1; colDecalage++) {
                if (ligneDecalage == 0 && colDecalage == 0) {
                    continue; // Ignorer la position actuelle du roi
                }
                Position newPosition = new Position(roiPosition.getLigne() + ligneDecalage, roiPosition.getColonne() + colDecalage);
                // Vérifie si le mouvement du Roi vers la nouvelle position peut se faire
                // Termine en échec
                if (estPositionValide(newPosition) && roi.peutBouger(newPosition, board.getBoard())) {
                    return false; // On a trouvé un mouvement qui sort le Roi de l'échec
                }
            }
        }
        return true; // Pas de mouvement possible pour sortir de l'échec, donc c'est un échec et mat
    }

    /////////////////////////////
    //// POSITION DISPONIBLE ////
    /////////////////////////////
    private boolean estPositionValide(Position position) {
        return position.getLigne() >= 0 && position.getLigne() < board.getBoard().length && position.getColonne() >= 0 && position.getColonne() < board.getBoard()[0].length;
    }

    //////////////////////
    //// ECHEC ET MAT ////
    //////////////////////
    private boolean seraEnEchecApresMouvement(PieceCouleur roiCouleur, Position de, Position vers) {
        // Simulation le mouvement temporairement
        Piece temp = board.getPiece(vers.getLigne(), vers.getColonne());
        board.setPiece(vers.getLigne(), vers.getColonne(), board.getPiece(de.getLigne(), de.getColonne()));
        board.setPiece(de.getLigne(), de.getColonne(), null);

        boolean enEchec = enEchec(roiCouleur);

        // Retirer son coup
        board.setPiece(de.getLigne(), de.getColonne(), board.getPiece(vers.getLigne(), vers.getColonne()));
        board.setPiece(vers.getLigne(), vers.getColonne(), temp);

        return enEchec;
    }
}
