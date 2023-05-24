import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Renderizacao extends JPanel
{

	public static final Color rosa = new Color(10573203);

	@Override
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		
		Cobrinha cobrinha = Cobrinha.cobrinha;

		g.setColor(rosa);
		
		g.fillRect(0, 0, 800, 700);

		g.setColor(Color.black);

		for (Point point : cobrinha.partesCobra)
		{
			g.fillRect(point.x * Cobrinha.escala, point.y * Cobrinha.escala, Cobrinha.escala, Cobrinha.escala);
		}
		
		g.fillRect(cobrinha.cabeca.x * Cobrinha.escala, cobrinha.cabeca.y * Cobrinha.escala, Cobrinha.escala, Cobrinha.escala);
		
		g.setColor(Color.RED);
		
		g.fillRect(cobrinha.cereja.x * Cobrinha.escala, cobrinha.cereja.y * Cobrinha.escala, Cobrinha.escala, Cobrinha.escala);
		
		String string = "Pontos: " + cobrinha.score + ", Tamanho: " + cobrinha.rabinho + ", Tempo: " + cobrinha.tempo / 20;
		
		g.setColor(Color.white);
		
		g.drawString(string, (int) (getWidth() / 2 - string.length() * 2.5f), 10);

		string = "Ih, perdeu kk";

		if (cobrinha.sobre)
		{
			g.drawString(string, (int) (getWidth() / 2 - string.length() * 2.5f), (int) cobrinha.dim.getHeight() / 4);
		}

		string = "Pausa!";

		if (cobrinha.pausa && !cobrinha.sobre)
		{
			g.drawString(string, (int) (getWidth() / 2 - string.length() * 2.5f), (int) cobrinha.dim.getHeight() / 4);
		}
	}
}
