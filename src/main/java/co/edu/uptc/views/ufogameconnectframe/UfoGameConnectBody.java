package co.edu.uptc.views.ufogameconnectframe;

import java.awt.Font;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import co.edu.uptc.utilities.ImagePanel;
import co.edu.uptc.utilities.PropertiesService;
import co.edu.uptc.utilities.RoundedButton;
import co.edu.uptc.views.GlobalView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import lombok.Getter;

@Getter
public class UfoGameConnectBody extends JPanel {
    private UfoGameConnectView ufoGameConnectView;
    private ImagePanel connectPanel;
    private PropertiesService propertiesService;
    private JTextField txtIp;
    private JTextField txtPort;
    private JTextField txtGameTag;

    public UfoGameConnectBody(UfoGameConnectView ufoGameConnectView) {
        propertiesService = new PropertiesService();
        this.ufoGameConnectView = ufoGameConnectView;
        this.initPanel();
        this.createLblIp();
        this.createLblPort();
        this.createAndAddTextFields(connectPanel);
        this.addConnectButton();
        this.createLblGameTag();
    }

    private void initPanel() {
        this.setLayout(null);
        connectPanel = new ImagePanel(propertiesService.getKeyValue("ConnectBackground"), 0.6f);
        connectPanel.setForeground(GlobalView.BODY_PLAY_FOREGROUND);
        connectPanel.setBounds(0, 0, 400, 330);
        connectPanel.setLayout(null);
        this.add(connectPanel);
    }

    private void createLblIp() {
        JLabel lblIp = new JLabel("INGRESE EL IP DEL SERVIDOR");
        lblIp.setBounds(50, 25, 500, 25);
        lblIp.setFont(new Font("Semi_Bold", 1, 17));
        lblIp.setForeground(GlobalView.BODY_PLAY_BACKGROUND);
        connectPanel.add(lblIp);
    }

    private void createLblPort() {
        JLabel lblPort = new JLabel("INGRESE EL PUERTO DEL SERVIDOR");
        lblPort.setBounds(50, 100, 500, 25);
        lblPort.setFont(new Font("Semi_Bold", 1, 17));
        lblPort.setForeground(GlobalView.BODY_PLAY_BACKGROUND);
        connectPanel.add(lblPort);
    }

    private void createLblGameTag() {
        JLabel lblPort = new JLabel("INGRESE EL NOMBRE DE USUARIO");
        lblPort.setBounds(50, 180, 500, 25);
        lblPort.setFont(new Font("Semi_Bold", 1, 17));
        lblPort.setForeground(GlobalView.BODY_PLAY_BACKGROUND);
        connectPanel.add(lblPort);
    }

    private void createAndAddTextFields(JPanel connectPanel) {
        txtIp = createTextField("Ingrese el IP del Servidor", 50, 55, 300, 30);
        txtPort = createTextField("Ingrese el Puerto del Servidor", 50, 130, 300, 30);
        txtGameTag = createTextField("Ingrese el Nombre de Usuario", 50, 210, 300, 30);
        connectPanel.add(txtIp);
        connectPanel.add(txtPort);
        connectPanel.add(txtGameTag);
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
                handleFocusGained(e, textField, placeholder);
            }

            @Override
            public void focusLost(FocusEvent e) {
                handleFocusLost(e, textField, placeholder);
            }
        };
    }

    private void handleFocusGained(FocusEvent e, JTextField textField, String placeholder) {
        if (textField.getText().equals(placeholder)) {
            textField.setText("");
            textField.setForeground(GlobalView.TEXT_COLOR);
        }
    }

    private void handleFocusLost(FocusEvent e, JTextField textField, String placeholder) {
        if (textField.getText().isEmpty()) {
            textField.setForeground(GlobalView.PLACEHOLDER_TEXT_COLOR);
            textField.setText(placeholder);
        }
    }

    private void showErrorDialog(String errorMessage) {
        JOptionPane.showMessageDialog(null, errorMessage, "Error", JOptionPane.ERROR_MESSAGE);
    }

    private void addConnectButton() {
        RoundedButton connectButton = new RoundedButton("Conectar", 20);
        configureConnectButton(connectButton);
        addConnectButtonActionListener(connectButton);
        connectPanel.add(connectButton);
    }

    private void configureConnectButton(RoundedButton connectButton) {
        connectButton.setBounds(20, 270, 360, 30);
        connectButton.setBackground(GlobalView.BTN_BACKGROUND);
        connectButton.setForeground(GlobalView.BTN_FOREGROUND);
    }

    private void addConnectButtonActionListener(RoundedButton connectButton) {
        connectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleConnectButtonAction();
            }
        });
    }

    private void handleConnectButtonAction() {
        try {
            ufoGameConnectView.getUfoGameView().getPresenter().startConnection(
                    txtIp.getText(),
                    Integer.parseInt(txtPort.getText()),
                    txtGameTag.getText());
            ufoGameConnectView.getUfoGameView().getPresenter().checkClientMode();
            ufoGameConnectView.getUfoGameView().setVisible(true);
            ufoGameConnectView.dispose();
        } catch (Exception e1) {
            showErrorDialog("Error al conectar: " + e1.getMessage());
        }
    }
}
