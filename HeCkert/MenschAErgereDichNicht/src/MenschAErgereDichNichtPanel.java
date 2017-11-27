
import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class MenschAErgereDichNichtPanel extends JPanel {

        private BufferedImage background;
        private final int[][] layout = 
           {{0,1,-1,-1,24,25,26,-1,-1,8,9},
            {2,3,-1,-1,23,55,27,-1,-1,10,11},
            {-1,-1,-1,-1,22,56,28,-1,-1,-1,-1},
            {-1,-1,-1,-1,21,57,29,-1,-1,-1,-1},
            {16,17,18,19,20,58,30,31,32,33,34},
            {55,56,57,58,59,-1,63,62,61,60,35},
            {54,53,52,51,50,71,40,39,38,37,36},
            {-1,-1,-1,-1,49,70,41,-1,-1,-1,-1},
            {-1,-1,-1,-1,48,69,42,-1,-1,-1,-1},
            {12,13,-1,-1,47,68,43,-1,-1,4,5},
            {14,15,-1,-1,46,45,44,-1,-1,6,7}};
        Graphics g;

    public MenschAErgereDichNichtPanel() {
        //TODO: Change path to resources
        URL resource = getClass().getResource("386px-Menschenaergern.jpg");
        try {
            background = ImageIO.read(resource);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void drawMeepleByColor(char color, int position){
        Graphics2D Circle = background.createGraphics();
        int xpos=0,ypos=0;
        
        for(int y=0; y<11; y++){
            for(int x=0; x<11; x++){
                if(layout[y][x]==position){
                    xpos = 15+(x*34);
                    ypos = 16+(y*34);
                }
            }
        }
        Circle.setStroke(new BasicStroke(5));
        Circle.setColor(Color.BLACK);
        Circle.drawArc(xpos, ypos, 15, 15, 0, 360);
        switch(color){
            case 'r':
                Circle.setColor(Color.RED);
                break;
            case 'g':
                Circle.setColor(Color.GREEN);
                break;
            case 'b':
                Circle.setColor(Color.BLUE);
                break;
            case 'y':
                Circle.setColor(Color.YELLOW);
                break;
            default:
                Circle.setColor(Color.BLACK);
                break;                
        }
        Circle.fillArc(xpos, ypos, 15, 15, 0, 360);
        //clear(xpos, ypos);
    }
    
    private void clear(int xpos, int ypos){
        final Graphics2D a = background.createGraphics();
        a.setColor(new Color(0, 0, 0, 0));
        a.setComposite(AlphaComposite.Clear); // overpaint
        a.setComposite(AlphaComposite.SrcOver);
        a.clearRect(xpos, ypos, 16, 16);
        a.dispose();
        repaint();        
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        int w = background.getWidth(this);
        int h =  background.getHeight(this);
        this.setSize(new Dimension(w, h)); 
        g.drawImage(background, 0, 0, this);
    }
    /*private Image background;

    public MenschAErgereDichNichtPanel() {

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
    }*/
}