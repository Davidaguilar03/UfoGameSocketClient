package co.edu.uptc.views.ufogameconnectframe;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import java.awt.Dimension;

import co.edu.uptc.views.GlobalView;
import lombok.Getter;

@Getter
public class UfoGameConnectHeader extends JPanel{
    private UfoGameConnectView ufoGameConnectView;    

        public UfoGameConnectHeader(UfoGameConnectView ufoGameConnectView){
            this.ufoGameConnectView=ufoGameConnectView;
            this.initPanel();
        }
    
        private void initPanel(){
            this.setBackground(GlobalView.HEADER_MENU_BACKGROUND);
            this.setForeground(GlobalView.HEADER_MENU_FOREGROUND);
            this.setPreferredSize(new Dimension(0,100));
            this.setBorder(BorderFactory.createMatteBorder(0,0,1,0, GlobalView.BORDER_COLOR));
            this.setLayout(null);
        }

}
