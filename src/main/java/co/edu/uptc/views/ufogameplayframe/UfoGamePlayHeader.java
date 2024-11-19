package co.edu.uptc.views.ufogameplayframe;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import co.edu.uptc.utilities.RoundedButton;
import co.edu.uptc.views.GlobalView;
import lombok.Getter;

@Getter
public class UfoGamePlayHeader extends JPanel {
    private UfoGamePlayView ufoGamePlayView;
    private JLabel pointsCounter;
    private int ufoCount;
    private int crashedUfoCount;
    private int landedUfoCount;

    public UfoGamePlayHeader(UfoGamePlayView ufoGamePlayView) {
        this.ufoGamePlayView = ufoGamePlayView;
        initPanel();
        createPointsCounter();
        createExitBtn();
    }

    private void initPanel() {
        this.setBackground(GlobalView.BODY_PLAY_BACKGROUND);
        this.setForeground(GlobalView.BODY_PLAY_FOREGROUND);
        this.setPreferredSize(new Dimension(0, 70));
        this.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, GlobalView.BORDER_COLOR));
        this.setLayout(null);
    }

    private void createPointsCounter() {
        pointsCounter = new JLabel("OVNIs en Movimiento: 0 | OVNIs Estrellados: 0 | OVNIs Aterrizados: 0");
        pointsCounter.setFont(new Font("Arial", Font.PLAIN, 20));
        pointsCounter.setBounds(20, 10, 700, 50); 
        pointsCounter.setForeground(GlobalView.SECUNDARY_BTN_TEXT_BACKGROUND);
        this.add(pointsCounter);
    }

    public void updateCounters() {
        pointsCounter.setText("OVNIs en Movimiento: " + ufoCount + " | OVNIs Estrellados: " + crashedUfoCount + " | OVNIs Aterrizados: " + landedUfoCount);
    }

    public void updateUfoCount(int ufoCount) {
        this.ufoCount = ufoCount;
        updateCounters();
    }

    public void incrementCrashedUfoCount(int increment) {
        crashedUfoCount += increment;
        updateCounters();
    }

    public void incrementLandedUfoCount() { 
        landedUfoCount++;
        updateCounters();
    }

    private void createExitBtn() {
        RoundedButton exitBtn = new RoundedButton("<html><div style='text-align: center;'>X</html>", 20);
        exitBtn.setBounds(1140, 10, 50, 50);
        exitBtn.setBackground(GlobalView.BTN_BACKGROUND);
        exitBtn.setForeground(GlobalView.BTN_FOREGROUND);
        exitBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        this.add(exitBtn);
    }
}
