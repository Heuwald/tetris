package br.com.heuwald.tetris.game.engine;
import java.awt.Color;

import lombok.Data;

@Data
public class Block {
	private boolean existe = false;
	private int idTetromino = 0;
	private Color cor;
	private Color corAux;
	private int x;
	private int y;
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

}