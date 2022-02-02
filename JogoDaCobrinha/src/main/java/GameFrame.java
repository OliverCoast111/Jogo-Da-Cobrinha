// Importando componente JFrame do toolkit Swing
import javax.swing.JFrame;

// Criando classe que defini a janela de execução
// e suas propriedades
public class GameFrame extends JFrame {
    GameFrame(){
        this.add(new GamePanel());
	this.setTitle("Jogo da Cobrinha");
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	this.setResizable(false);
	this.pack();
	this.setVisible(true);
	this.setLocationRelativeTo(null);
    }
}
