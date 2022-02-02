// Importando ferramenta Random do pacote Util 
import java.util.Random;

// Criando classe abstrata "maça" e definindo suas propriedades
public abstract class Maca {
    private int macaX;
    private int macaY;

    // Criando métodos GET e SET
    public int getMacaX() {
        return macaX;
    }

    public void setMacaX(int macaX) {
        this.macaX = macaX;
    }

    public int getMacaY() {
        return macaY;
    }

    public void setMacaY(int macaY) {
        this.macaY = macaY;
    }
    
    // Método que defini aleatoriamente a posição de spawn da "maça"
    // no painel do jogo
    public void novaMaca(Maca m){
        Random random= new Random();
        m.setMacaX(random.nextInt((int)(GamePanel.LARGURA_TELA/GamePanel.TAMANHO_UNIDADE))*GamePanel.TAMANHO_UNIDADE); 
        m.setMacaY(random.nextInt((int)(GamePanel.ALTURA_TELA/GamePanel.TAMANHO_UNIDADE))*GamePanel.TAMANHO_UNIDADE);
    }
}
