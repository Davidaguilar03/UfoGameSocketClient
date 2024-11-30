package co.edu.uptc.views.ufogamemainframe;

import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import co.edu.uptc.utilities.RoundedButton;
import co.edu.uptc.views.GlobalView;
import lombok.Getter;

@Getter
public class UfoGameHeader extends JPanel{
    private UfoGameView ufoGameView;
    private RoundedButton settingsBtn;
    
    public UfoGameHeader(UfoGameView ufoGameView){
        this.ufoGameView=ufoGameView;
        this.initPanel();
        this.createExitBtn();
        this.createSettingsBtn();
        this.createHowToPlayBtn();
    }

    public void setEnabledSettingsBtn(boolean mode){
        settingsBtn.setEnabled(mode);
    }

    private void initPanel(){
        this.setBackground(GlobalView.HEADER_MENU_BACKGROUND);
        this.setForeground(GlobalView.HEADER_MENU_FOREGROUND);
        this.setPreferredSize(new Dimension(0,70));
        this.setBorder(BorderFactory.createMatteBorder(0,0,1,0, GlobalView.BORDER_COLOR));
        this.setLayout(null);
    }

    private void createSettingsBtn(){
        settingsBtn = new RoundedButton("Menu", 20);
        settingsBtn.setBounds(10, 10, 80, 50);
        settingsBtn.setBackground(GlobalView.BTN_BACKGROUND);
        settingsBtn.setForeground(GlobalView.BTN_FOREGROUND);
        settingsBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
               ufoGameView.getBodyCardLayout().show(ufoGameView.getUfoGameBody(), "Menu");
            }
        });
        this.add(settingsBtn);
    }

    private void createHowToPlayBtn(){
        RoundedButton howToPlayBtn = new RoundedButton("<html><div style='text-align: center;'>How To Play</html>", 20);
        howToPlayBtn.setBounds(100, 10, 150, 50);
        howToPlayBtn.setBackground(GlobalView.BTN_BACKGROUND);
        howToPlayBtn.setForeground(GlobalView.BTN_FOREGROUND);
        howToPlayBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
               ufoGameView.getBodyCardLayout().show(ufoGameView.getUfoGameBody(), "HowToPlay");
            }
        });
        this.add(howToPlayBtn);
    }

    private void createExitBtn(){
        RoundedButton exitBtn = new RoundedButton("<html><div style='text-align: center;'>X</html>", 20);
        exitBtn.setBounds(440, 10, 50, 50);
        exitBtn.setBackground(GlobalView.BTN_BACKGROUND);
        exitBtn.setForeground(GlobalView.BTN_FOREGROUND);
        exitBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                try {
                    ufoGameView.getPresenter().stopConnection();
                    System.exit(0);
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });
        this.add(exitBtn);
    }
}
