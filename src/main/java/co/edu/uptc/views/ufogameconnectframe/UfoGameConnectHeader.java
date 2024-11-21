package co.edu.uptc.views.ufogameconnectframe;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import co.edu.uptc.utilities.RoundedButton;
import co.edu.uptc.views.GlobalView;
import lombok.Getter;

@Getter
public class UfoGameConnectHeader extends JPanel {
    private UfoGameConnectView ufoGameConnectView;
    private JLabel titleJLabel;

    public UfoGameConnectHeader(UfoGameConnectView ufoGameConnectView) {
        this.ufoGameConnectView = ufoGameConnectView;
        this.initPanel();
        this.createExitBtn();
        this.createTitleJLabel();
    }

    private void initPanel() {
        this.setBackground(GlobalView.HEADER_MENU_BACKGROUND);
        this.setForeground(GlobalView.HEADER_MENU_FOREGROUND);
        this.setPreferredSize(new Dimension(0, 70));
        this.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, GlobalView.BORDER_COLOR));
        this.setLayout(null);
    }

    private void createTitleJLabel() {
        titleJLabel = new JLabel("BIENVENIDO A GALACTIC ENIGNMA");
        titleJLabel.setFont(new Font("Arial", Font.PLAIN, 17));
        titleJLabel.setBounds(20, 10, 300, 50); 
        titleJLabel.setForeground(GlobalView.SECUNDARY_BTN_TEXT_BACKGROUND);
        this.add(titleJLabel);
    }
    
    private void createExitBtn(){
        RoundedButton exitBtn = new RoundedButton("<html><div style='text-align: center;'>X</html>", 20);
        exitBtn.setBounds(340, 10, 50, 50);
        exitBtn.setBackground(GlobalView.BTN_BACKGROUND);
        exitBtn.setForeground(GlobalView.BTN_FOREGROUND);
        exitBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                System.exit(0);
            }
        });
        this.add(exitBtn);
    }

}
