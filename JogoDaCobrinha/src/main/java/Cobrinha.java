// Importando componente timer do toolkit Swing 
import javax.swing.Timer;

// Criando classe abstrata cobrinha de instância única
public abstract class Cobrinha{
    // Aqui são definidos os parâmetro iniciais da "cobrinha".
    final int x[] = new int[GamePanel.JOGO_UNIDADES];
    final int y[] = new int[GamePanel.JOGO_UNIDADES];
    private int parteCorpo = 6;
    private char direcao = 'R';
    private boolean rastejando = false;
    private int macasComidas = 0;

    // Criando métodos GET e SET
    public int getMacasComidas() {
        return macasComidas;
    }

    public void setMacasComidas(int macasComidas) {
        this.macasComidas = macasComidas;
    }

    public int getParteCorpo() {
        return parteCorpo;
    }

    public void setParteCorpo(int parteCorpo) {
        this.parteCorpo = parteCorpo;
    }

    public char getDirecao() {
        return direcao;
    }

    public void setDirecao(char direcao) {
        this.direcao = direcao;
    }

    public boolean isRastejando() {
        return rastejando;
    }

    public void setRastejando(boolean rastejando) {
        this.rastejando = rastejando;
    }
    // Método reponsável definir as direções e posições da "cobrinha"
    public void mover(Cobrinha jack){
	for(int i = jack.getParteCorpo();i>0;i--) {
            jack.x[i] =jack.x[i-1];
            jack.y[i] =jack.y[i-1];
	}
	switch(jack.getDirecao()) {
	case 'U':
            jack.y[0] = jack.y[0] - GamePanel.TAMANHO_UNIDADE;
            break;
	case 'D':
            jack.y[0] = jack.y[0] + GamePanel.TAMANHO_UNIDADE;
            break;
	case 'L':
            jack.x[0] = jack.x[0] - GamePanel.TAMANHO_UNIDADE;
            break;
        case 'R':
            jack.x[0] = jack.x[0] + GamePanel.TAMANHO_UNIDADE;
            break;
	}		
    }
    // Método que incrementa o tamanho da "cobrinha"
    public void incrementarMacas(){
        macasComidas++;
    }
    // Método que compara a posição da "cobrinha" em relação a da "maça" 
    // e incrementa o tamanho do objeto "jack"
    public void verificarMaca(Cobrinha jack, Maca maca) {
	if((jack.x[0] == maca.getMacaX()) && (jack.y[0] == maca.getMacaY())) {
            jack.setParteCorpo(jack.getParteCorpo()+ 1);
            jack.incrementarMacas();
            maca.novaMaca(maca);
	}
    }
    // Método que compara a posição da "cobrinha" com suas respectivas posições
    // de seguimento, além de comparar com o limite da janela
    public void verificarColisao(Cobrinha jack, Timer timer) {
	for(int i = jack.getParteCorpo();i>0;i--) {
            if((jack.x[0] == jack.x[i])&& (jack.y[0] == jack.y[i])) {
                // Operação que interrompe o movimento
                jack.setRastejando(false);
            }
        }
        if(jack.x[0] < 0) {
            jack.setRastejando(false);
        }
        if(jack.x[0] > GamePanel.LARGURA_TELA) {
            jack.setRastejando(false);
        }
        if(jack.y[0] < 0) {
            jack.setRastejando(false);
        }
        if(jack.y[0] > GamePanel.ALTURA_TELA) {
            jack.setRastejando(false);
        }
        if(!jack.isRastejando()) {
            timer.stop();
        }
    }
    
}
