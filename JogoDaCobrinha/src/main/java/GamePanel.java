// Importando pacote de ferramentas gráficas awt
import java.awt.*;
import java.awt.event.*;
// Importando toolkit Swing
import javax.swing.*;
// Importando ferramenta Random do pacote Util
import java.util.Random;

// Criando classe do painel de jogo
public class GamePanel extends JPanel implements ActionListener{
    // Definindo proporções, valores e posições iniciais/estáticas do jogo
    static final int LARGURA_TELA = 1300;
    static final int ALTURA_TELA = 650;
    static final int TAMANHO_UNIDADE = 50;
    static final int JOGO_UNIDADES = (LARGURA_TELA*ALTURA_TELA)/(TAMANHO_UNIDADE*TAMANHO_UNIDADE);
    static final int ATRASO = 175;
    boolean restart = false;
    Timer timer;
    Random random;
    // Craindo nova instância do objeto "cobrinha" ("jack")
    Cobrinha jack = new Cobrinha() {};
    // Craindo nova instância do objeto "maça" ("maca")
    Maca maca = new Maca() {};
    // Criando onstrutor da classe do painel do jogo e definindo parâmetros/chamadas
    GamePanel(){
        random = new Random();
        this.setPreferredSize(new Dimension(LARGURA_TELA,ALTURA_TELA));
    	this.setBackground(Color.BLACK);
    	this.setFocusable(true);
	this.addKeyListener(new MyKeyAdapter());
	startGame();
    }
    // Método que inicia o jogo 
    public void startGame() {
	maca.novaMaca(maca);
	jack.setRastejando(true);
	timer = new Timer(ATRASO,this);
	timer.start();
    }
    // Método que defini o componente de desenho
    public void paintComponent(Graphics g) {
    	super.paintComponent(g);
    	desenhar(g);
    }
    // Método que desenha nas posições desejadas
    public void desenhar(Graphics g) {
        if(jack.isRastejando()) {
            g.setColor(Color.red);
            g.fillOval(maca.getMacaX(), maca.getMacaY(), TAMANHO_UNIDADE, TAMANHO_UNIDADE);
            for(int i = 0; i< jack.getParteCorpo();i++) {
		if(i == 0) {
                    g.setColor(Color.green);
                    g.fillRect(jack.x[i], jack.y[i], TAMANHO_UNIDADE, TAMANHO_UNIDADE);
		}
		else {
                    g.setColor(new Color(45,180,0));
                    g.fillRect(jack.x[i], jack.y[i], TAMANHO_UNIDADE, TAMANHO_UNIDADE);
		}			
            }
            g.setColor(Color.red);
            g.setFont( new Font("Ink Free",Font.BOLD, 40));
            FontMetrics metrics = getFontMetrics(g.getFont());
            g.drawString("Score: "+jack.getMacasComidas(), (LARGURA_TELA - metrics.stringWidth("Score: "+jack.getMacasComidas()))/2, g.getFont().getSize());
        }
        else {
            gameOver(g);            
        }	
    }
    
    // Método que termina o jogo e altera os componentes do painel de jogo
    public void gameOver(Graphics g) {
	g.setColor(Color.red);
	g.setFont( new Font("Ink Free",Font.BOLD, 40));
	FontMetrics metrics1 = getFontMetrics(g.getFont());
	g.drawString("Score: "+jack.getMacasComidas(), (LARGURA_TELA - metrics1.stringWidth("Score: "+jack.getMacasComidas()))/2, g.getFont().getSize());
	g.setColor(Color.red);
	g.setFont( new Font("Ink Free",Font.BOLD, 75));
	FontMetrics metrics2 = getFontMetrics(g.getFont());
	g.drawString("Game Over", (LARGURA_TELA - metrics2.stringWidth("Game Over"))/2, ALTURA_TELA/2);
        g.setColor(Color.green);
        g.setFont( new Font("Ink Free",Font.BOLD, 40));
        FontMetrics metrics3 = getFontMetrics(g.getFont());
        g.drawString("Para jogar novamente clique no espaço", (LARGURA_TELA - metrics3.stringWidth("Para jogar novamente clique no espaço"))/2, ALTURA_TELA-100);
    }
    // Método que reinicia o jogo
    public void reiniciar(){
        jack.setMacasComidas(0);
        jack.setParteCorpo(6); 
        jack.setDirecao('R');
        jack.x[0]=0;
        jack.y[0]=0;
        startGame();
    }
    @Override
    // Métodos que registram as entradas das direções 
    // e defini as ações realizadas por elas
    public void actionPerformed(ActionEvent e) {
        if(jack.isRastejando()) {
            jack.mover(jack);
            jack.verificarMaca(jack, maca);
            jack.verificarColisao(jack, timer);
	}
	repaint();
    }
    public class MyKeyAdapter extends KeyAdapter{
	@Override
	public void keyPressed(KeyEvent e) {
            switch(e.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                if(jack.getDirecao() != 'R') {
                    jack.setDirecao('L');
                }
                break;
            case KeyEvent.VK_RIGHT:
		if(jack.getDirecao() != 'L') {
                    jack.setDirecao('R');
		}
                break;
            case KeyEvent.VK_UP:
		if(jack.getDirecao() != 'D') {
                    jack.setDirecao('U');
		}
		break;
            case KeyEvent.VK_DOWN:
		if(jack.getDirecao() != 'U') {
                    jack.setDirecao('D');
		}
		break;
            case KeyEvent.VK_SPACE:
                if(jack.isRastejando()==false){
                    reiniciar(); 
                }
                break; 
            }
    	}
    }
}
