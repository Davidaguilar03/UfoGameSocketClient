package co.edu.uptc.views.ufogameconnectframe;

import java.awt.Font;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import co.edu.uptc.utilities.ImagePanel;
import co.edu.uptc.utilities.PropertiesService;
import co.edu.uptc.views.GlobalView;
import lombok.Getter;

@Getter
public class UfoGameConnectBody extends JPanel{
    private UfoGameConnectView ufoGameConnectView;
    private ImagePanel connectPanel;
    private PropertiesService propertiesService;
    private JTextField txtIp;
    private JTextField txtPort;
    
    public UfoGameConnectBody(UfoGameConnectView ufoGameConnectView){
        propertiesService = new PropertiesService();
        this.ufoGameConnectView=ufoGameConnectView;
        this.initPanel();
        createLblIp();
        createLblPort();
        createAndAddTextFields(connectPanel);
    }

    private void initPanel(){
        this.setLayout(null);
        connectPanel = new ImagePanel(propertiesService.getKeyValue("ConnectBackground"), 0.8f);
        connectPanel.setForeground(GlobalView.BODY_PLAY_FOREGROUND);
        connectPanel.setBounds(0, 0, 400, 400);
        connectPanel.setLayout(null);
        this.add(connectPanel);
    }

    private void createLblIp() {
        JLabel lblIp = new JLabel("INGRESE EL IP DEL SERVIDOR");
        lblIp.setBounds(20, 15, 500, 25);
        lblIp.setFont(new Font("Semi_Bold", 1, 17));
        lblIp.setForeground(GlobalView.BODY_PLAY_BACKGROUND);
        connectPanel.add(lblIp);
    }

    private void createLblPort() {
        JLabel lblPort = new JLabel("INGRESE EL PUERTO DEL SERVIDOR");
        lblPort.setBounds(20, 90, 500, 25);
        lblPort.setFont(new Font("Semi_Bold", 1, 17));
        lblPort.setForeground(GlobalView.BODY_PLAY_BACKGROUND);
        connectPanel.add(lblPort);
    }

    private void createAndAddTextFields(JPanel connectPanel) {
        txtIp = createTextField("Ingrese el Ip del Servidor", 20, 45, 300, 30);
        txtPort = createTextField("Ingrese el Puerto del Servidor", 20, 120, 300, 30);
        connectPanel.add(txtIp);
        connectPanel.add(txtPort);
    }

    private JTextField createTextField(String placeholder, int x, int y, int width, int height) {
    JTextField textField = new JTextField(placeholder);
    textField.setBounds(x, y, width, height);
    textField.setForeground(GlobalView.PLACEHOLDER_TEXT_COLOR);
    textField.addFocusListener(createFocusListener(textField, placeholder));
    return textField;
    }
    

    private FocusListener createFocusListener(JTextField textField, String placeholder) {
        return new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (textField.getText().equals(placeholder)) {
                    textField.setText("");
                    textField.setForeground(GlobalView.TEXT_COLOR);
                }
            }
    
            @Override
            public void focusLost(FocusEvent e) {
                if (textField.getText().isEmpty()) {
                    textField.setForeground(GlobalView.PLACEHOLDER_TEXT_COLOR);
                    textField.setText(placeholder);
                }
            }
        };
    }
    
}
