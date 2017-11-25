
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class MaenschAErgereDichNichtPanel extends JPanel {

    private Image background;

    public MaenschAErgereDichNichtPanel() {

        initBoard();
    }
    
    private void initBoard() {
        
        loadImage();
        
        int w = background.getWidth(this);
        int h =  background.getHeight(this);
        setPreferredSize(new Dimension(w, h));        
    }
    
    private void loadImage() {
        
        ImageIcon ii = new ImageIcon("386px-Menschenaergern.png");
        background = ii.getImage();        
    }

    @Override
    public void paintComponent(Graphics g) {

        g.drawImage(background, 0, 0, null);
    }
}