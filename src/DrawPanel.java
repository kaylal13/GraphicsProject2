import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Point;
import java.util.ArrayList;
import java.awt.Font;

class DrawPanel extends JPanel implements MouseListener {

    private Deck d;
    private Card[][] cards;
    private boolean selected;

    public DrawPanel() {

        cards = new Card[3][3];
        d = new Deck();
        for (int r = 0; r < cards.length; r++) {
            for (int c = 0; c < cards.length; c++) {
                cards[r][c] = d.getRandomCard();
            }
        }
        this.addMouseListener(this);
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int x = 50;
        int y = 10;
        for (int r = 0; r < cards.length; r++) {
            for (int c = 0; c < cards.length; c++) {
                g.drawImage(cards[r][c].getImage(), x, y, null);
                if (selected) {
                    Rectangle cardHitBox = new Rectangle(x, y, cards[r][c].getImage().getWidth(), cards[r][c].getImage().getHeight());
                    cards[r][c].setHitbox(cardHitBox);
                    if (cards[r][c].getHighlight()) {
                        g.drawRect(x, y, (int) cardHitBox.getWidth(), (int) cardHitBox.getHeight());
                    }
                }
                x += 80;
            }
            y += 100;
            x = 50;
        }

        g.drawString("Number of cards left: " + d.getDeck().size(), x, y + 100);
    }

    public void mousePressed(MouseEvent e) {

        Point p = e.getPoint();
        int button = e.getButton();
        int clickCount = e.getClickCount();
        Point p1 = e.getPoint();
        Point p2 = e.getPoint();




        for (int r = 0; r < cards.length; r++) {
            for (int c = 0; c < cards.length; c++) {
                //gets locations of each mouse click
                if (clickCount==1){
                    p1 = e.getPoint();
                } else if (clickCount ==2){
                    p2 = e.getPoint();
                }

                //if left or right mouse clicked, and the card contains that points, get hitbox
                if ((button==1) && cards[r][c].getHitbox().contains(p1)) {
                    selected = true;

                }
                if ( cards[r][c].getHitbox().contains(p2)) {
                    selected = true;

                }

//                if (d.getDeck().size() != 0 && button == 1) {
//                    if (cards[r][c].getHitbox().contains(p)) {
//                        cards[r][c] = d.getRandomCard();
//                    }
//                }
//


            }
        }



    }

    public void mouseReleased(MouseEvent e) { }
    public void mouseEntered(MouseEvent e) { }
    public void mouseExited(MouseEvent e) { }
    public void mouseClicked(MouseEvent e) { }
}