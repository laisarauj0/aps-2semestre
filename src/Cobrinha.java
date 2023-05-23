import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.Timer;

public class Cobrinha implements ActionListener, KeyListener {

	public static Cobrinha cobrinha;

	public JFrame jframe;

	public Renderizacao renderizacao;

	public Timer timer = new Timer(20, this);

	public ArrayList<Point> partesCobra = new ArrayList<Point>();

	public static final int cima = 0, baixo = 1, esquerda = 2, direita = 3, escala = 10;

	public int pingos = 0, direcao = baixo, score, rabinho = 10, tempo;

	public Point cabeca, cereja;

	public Random random;

	public boolean sobre = false, pausa;

	public Dimension dim;

	public Cobrinha() {
		
		dim = Toolkit.getDefaultToolkit().getScreenSize();
		jframe = new JFrame("Snake");
		jframe.setVisible(true);
		jframe.setSize(805, 700);
		jframe.setResizable(false);
		jframe.setLocation(dim.width / 2 - jframe.getWidth() / 2, dim.height / 2 - jframe.getHeight() / 2);
		jframe.add(renderizacao = new Renderizacao());
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jframe.addKeyListener(this);
		startGame();
	}

	public void startGame() {
		
		sobre = false;
		pausa = false;
		tempo = 0;
		score = 0;
		rabinho = 14;
		pingos = 0;
		direcao = baixo;
		cabeca = new Point(0, -1);
		random = new Random();
		partesCobra.clear();
		cereja = new Point(random.nextInt(79), random.nextInt(66));
		timer.start();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		renderizacao.repaint();
		pingos++;

		if (pingos % 2 == 0 && cabeca != null && !sobre && !pausa)
		{
			tempo++;

			partesCobra.add(new Point(cabeca.x, cabeca.y));

			if (direcao == cima) {
				if (cabeca.y - 1 >= 0 && noTailAt(cabeca.x, cabeca.y - 1)) {
					cabeca = new Point(cabeca.x, cabeca.y - 1);
				}
				else {
					sobre = true;
				}
			}

			if (direcao == baixo) {
				if (cabeca.y + 1 < 67 && noTailAt(cabeca.x, cabeca.y + 1)) {
					cabeca = new Point(cabeca.x, cabeca.y + 1);
				}
				else {
					sobre = true;
				}
			}

			if (direcao == esquerda) {
				if (cabeca.x - 1 >= 0 && noTailAt(cabeca.x - 1, cabeca.y)) {
					cabeca = new Point(cabeca.x - 1, cabeca.y);
				}
				else {
					sobre = true;
				}
			}

			if (direcao == direita) {
				if (cabeca.x + 1 < 80 && noTailAt(cabeca.x + 1, cabeca.y)) {
					cabeca = new Point(cabeca.x + 1, cabeca.y);
				}
				else {
					sobre = true;
				}
			}

			if (partesCobra.size() > rabinho) {
				partesCobra.remove(0);

			}

			if (cereja != null) {
				if (cabeca.equals(cereja)) {
					score += 10;
					rabinho++;
					cereja.setLocation(random.nextInt(79), random.nextInt(66));
				}
			}
		}
	}

	public boolean noTailAt(int x, int y) {
		for (Point point : partesCobra) {
			if (point.equals(new Point(x, y))) {
				return false;
			}
		}
		return true;
	}

	public static void main(String[] args) {
		cobrinha = new Cobrinha();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int i = e.getKeyCode();

		if ((i == KeyEvent.VK_A || i == KeyEvent.VK_LEFT) && direcao != direita) {
			direcao = esquerda;
		}

		if ((i == KeyEvent.VK_D || i == KeyEvent.VK_RIGHT) && direcao != esquerda) {
			direcao = direita;
		}

		if ((i == KeyEvent.VK_W || i == KeyEvent.VK_UP) && direcao != baixo) {
			direcao = cima;
		}

		if ((i == KeyEvent.VK_S || i == KeyEvent.VK_DOWN) && direcao != cima) {
			direcao = baixo;
		}

		if (i == KeyEvent.VK_SPACE) {
			if (sobre) {
				startGame();
			}
			else {
				pausa = !pausa;
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}

}