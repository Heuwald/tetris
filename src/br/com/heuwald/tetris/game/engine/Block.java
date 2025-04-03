package br.com.heuwald.tetris.game.engine;
import java.awt.Color;

public class Block {
	private boolean existe = false;
	private int idTetromino = 0;
	private Color cor;
	private Color corAux;
	//    private int controlaExplosao = 0;

	//    public boolean eventoExplodir(){
	//	if(controlaExplosao < 2){
	//	    cor = (cor.equals(corAux)?Color.BLACK:corAux);
	//	    controlaExplosao++;
	//	    return false;
	//	}else{
	//	    setExiste(false);
	//	    return true;
	//	}
	//    }

	public boolean isExiste() {
		return existe;
	}

	public void setExiste(boolean existe) {
		this.existe = existe;
	}

	public int getIdTetromino() {
		return idTetromino;
	}

	public void setIdTetromino(int idTetromino) {
		this.idTetromino = idTetromino;
	}

	public Color getCor() {
		return cor;
	}

	public void setCor(Color cor) {
		this.cor = cor;
		this.corAux = cor;
	}



}