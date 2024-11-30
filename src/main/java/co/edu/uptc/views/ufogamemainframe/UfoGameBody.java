package co.edu.uptc.views.ufogamemainframe;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import co.edu.uptc.utilities.ImagePanel;
import co.edu.uptc.utilities.PropertiesService;
import co.edu.uptc.utilities.RoundedButton;
import co.edu.uptc.views.GlobalView;
import co.edu.uptc.views.ufogameplayframe.UfoGamePlayView;

import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.HashMap;
import java.util.Map;

import lombok.Getter;

@Getter
public class UfoGameBody extends JPanel {
    private UfoGameView ufoGameView;
    private PropertiesService propertiesService;
    private JPanel menuPanel;
    private JPanel playPanel;
    private JPanel howToPlayPanel;
    private JTextField txtSpawnRate;
    private JTextField txtUfosAmount;
    private JTextField txtUfosSpeed;
    private CardLayout cardLayout;
    private JCheckBox trayectoryCheckbox;
    private UfoGamePlayView ufoGamePlayView;
    private String selectedUfoImage;
    private Map<JLabel, String> imageLabelMap = new HashMap<>();
    private boolean showTrajectory;
    private RoundedButton playBtn;

    public UfoGameBody(UfoGameView ufoGameView, CardLayout cardLayout) {
        propertiesService = new PropertiesService();
        this.ufoGameView = ufoGameView;
        this.setLayout(cardLayout);
        this.initPlayPanel();
        this.initMenuPanel();
        this.initHowToPlayPanel();
        selectedUfoImage = propertiesService.getKeyValue("UFO1-OFF");
        showTrajectory = true;
    }
    
    private void initPlayPanel() {
        playPanel = new ImagePanel(propertiesService.getKeyValue("GameLogo"), 1);
        playPanel.setForeground(GlobalView.BODY_PLAY_FOREGROUND);
        playPanel.setLayout(null);
        this.add(playPanel, "Play");
        this.createPlayBtn();
    }
    
    private void initMenuPanel() {
        menuPanel = new ImagePanel(propertiesService.getKeyValue("MenuBackGround"), 0.5f);
        menuPanel.setForeground(GlobalView.BODY_MENU_FOREGROUND);
        menuPanel.setLayout(null);
        this.add(menuPanel, "Menu");
        this.createLblSpawnRate();
        this.createLblUfosAmount();
        this.createTrayectoryCheckBox();
        this.createBackButton(menuPanel);
        this.createLblUfosSpeed();
        this.createUfoImageSelector(menuPanel);
        this.createAndAddTextFields(menuPanel);
    }

    private void initHowToPlayPanel() {
        howToPlayPanel = new ImagePanel(propertiesService.getKeyValue("HowToPlayBackground"), 0.5f);
        howToPlayPanel.setForeground(GlobalView.BODY_HOWTOPLAY_FOREGROUND);
        howToPlayPanel.setLayout(null);
        this.add(howToPlayPanel, "HowToPlay");
        addHowToPlayLabel();
        addInstructionsTextArea();
        addBackButton();
    }

    private void addHowToPlayLabel() {
        JLabel lblHowToPlay = new JLabel("<html><div style='text-align: center;'>Cómo Jugar</html>");
        lblHowToPlay.setBounds(100, 50, 300, 50);
        lblHowToPlay.setForeground(GlobalView.LABEL_FOREGROUND);
        howToPlayPanel.add(lblHowToPlay);
    }

    private void addInstructionsTextArea() {
        JTextArea txtInstructions = new JTextArea();
        txtInstructions.setText(propertiesService.getKeyValue("HowToPlayInstructions"));
        txtInstructions.setBounds(50, 100, 400, 100);
        txtInstructions.setLineWrap(true);
        txtInstructions.setWrapStyleWord(true);
        txtInstructions.setEditable(false);
        txtInstructions.setBackground(GlobalView.TEXTAREA_BACKGROUND);
        txtInstructions.setForeground(GlobalView.TEXTAREA_FOREGROUND);
        howToPlayPanel.add(txtInstructions);
    }

    private void addBackButton() {
        RoundedButton backButton = new RoundedButton("Volver", 20);
        backButton.setBounds(200, 420, 100, 50);
        backButton.setBackground(GlobalView.BTN_BACKGROUND);
        backButton.setForeground(GlobalView.BTN_FOREGROUND);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cl = (CardLayout) (getLayout());
                cl.show(UfoGameBody.this, "Play");
            }
        });
        howToPlayPanel.add(backButton);
    }

    public void setEnabledPlayBtn(boolean mode) {
        playBtn.setEnabled(mode);
    }

    public void setTextPlayBtn(String text) {
        playBtn.setText(text);
    }

    
    private void createPlayBtn() {
        playBtn = new RoundedButton("<html><div style='text-align: center;'>Jugar</html>", 20);
        playBtn.setBounds(175, 400, 150, 50);
        playBtn.setBackground(GlobalView.BTN_BACKGROUND);
        playBtn.setForeground(GlobalView.BTN_FOREGROUND);
        playBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                saveSettings();
                createUfoGamePlayView();
            }
        });
        playPanel.add(playBtn);
    }
    
    public void createUfoGamePlayView() {
        if (this.ufoGamePlayView == null) {
            this.ufoGamePlayView = new UfoGamePlayView(ufoGameView);
            this.ufoGamePlayView.begin();
            SwingUtilities.getWindowAncestor(playPanel).dispose();
        }
    }
    
    private void createLblSpawnRate() {
        JLabel lblSpawnRate = new JLabel("INGRESE EL RADIO DE APARICION");
        lblSpawnRate.setBounds(100, 15, 500, 25);
        lblSpawnRate.setFont(new Font("Semi_Bold", 1, 17));
        lblSpawnRate.setForeground(GlobalView.BODY_PLAY_BACKGROUND);
        menuPanel.add(lblSpawnRate);
    }

    private void createLblUfosAmount() {
        JLabel lblUfosAmount = new JLabel("INGRESE LA CANTIDAD DE OVNIS");
        lblUfosAmount.setBounds(100, 85, 500, 25);
        lblUfosAmount.setFont(new Font("Semi_Bold", 1, 17));
        lblUfosAmount.setForeground(GlobalView.BODY_PLAY_BACKGROUND);
        menuPanel.add(lblUfosAmount);
    }

    private void createLblUfosSpeed() {
        JLabel lblUfosSpeed = new JLabel("INGRESE LA VELOCIDAD DE OVNIS");
        lblUfosSpeed.setBounds(100, 155, 500, 25);
        lblUfosSpeed.setFont(new Font("Semi_Bold", 1, 17));
        lblUfosSpeed.setForeground(GlobalView.BODY_PLAY_BACKGROUND);
        menuPanel.add(lblUfosSpeed);
    }

    private void createTrayectoryCheckBox() {
        trayectoryCheckbox = new JCheckBox("Mostrar la Trayectoria", true);
        trayectoryCheckbox.setBackground(GlobalView.BODY_PLAY_BACKGROUND);
        trayectoryCheckbox.setForeground(GlobalView.SECUNDARY_BTN_TEXT_BACKGROUND);
        trayectoryCheckbox.setFont(new Font("Arial", Font.BOLD, 14));
        trayectoryCheckbox.setBorder(BorderFactory.createLineBorder(GlobalView.PLACEHOLDER_TEXT_COLOR, 1));
        trayectoryCheckbox.setBounds(100, 235, 300, 30);
        trayectoryCheckbox.setHorizontalAlignment(SwingConstants.LEFT);
        menuPanel.add(trayectoryCheckbox);
    }

    private void createUfoImageSelector(JPanel menuPanel) {
        JPanel ufoImageSelector = createImagePanel();
        String[] imagePaths = getImagePaths();
        selectedUfoImage = imagePaths[0];
        addImagesToSelector(ufoImageSelector, imagePaths);
        JPanel buttonPanel = createButtonPanel(ufoImageSelector);
        menuPanel.add(buttonPanel);
        menuPanel.add(ufoImageSelector);
    }

    private JPanel createImagePanel() {
        JPanel ufoImageSelector = new ImagePanel(propertiesService.getKeyValue("PlayBackground"), 0.7f);
        ufoImageSelector.setForeground(GlobalView.BODY_MENU_FOREGROUND);
        ufoImageSelector.setBounds(100, 275, 300, 110);
        cardLayout = new CardLayout();
        ufoImageSelector.setLayout(cardLayout);
        return ufoImageSelector;
    }

    private String[] getImagePaths() {
        return new String[]{
            propertiesService.getKeyValue("UFO1-OFF"),
            propertiesService.getKeyValue("UFO2-OFF"),
            propertiesService.getKeyValue("UFO3-OFF")
        };
    }

    private void addImagesToSelector(JPanel ufoImageSelector, String[] imagePaths) {
        for (String imagePath : imagePaths) {
            ImageIcon imageIcon = new ImageIcon(imagePath);
            Image scaledImage = imageIcon.getImage().getScaledInstance(125, 90, Image.SCALE_SMOOTH);
            JLabel label = new JLabel(new ImageIcon(scaledImage));
            ufoImageSelector.add(label, imagePath);
            imageLabelMap.put(label, imagePath);
        }
    }

    private JPanel createButtonPanel(JPanel ufoImageSelector) {
        RoundedButton prevButton = createButton("Anterior");
        RoundedButton nextButton = createButton("Siguiente");
        addActionListenersToButtons(prevButton, nextButton, ufoImageSelector);
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(prevButton);
        buttonPanel.add(nextButton);
        buttonPanel.setBounds(150, 390, 185, 35);
        buttonPanel.setOpaque(false);
        return buttonPanel;
    }

    private RoundedButton createButton(String text) {
        RoundedButton button = new RoundedButton(text, 20);
        button.setBackground(GlobalView.BTN_BACKGROUND);
        button.setForeground(GlobalView.BTN_FOREGROUND);
        return button;
    }

    private void addActionListenersToButtons(RoundedButton prevButton, RoundedButton nextButton, JPanel ufoImageSelector) {
        prevButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.previous(ufoImageSelector);
                updateSelectedImage(ufoImageSelector);
            }
        });
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.next(ufoImageSelector);
                updateSelectedImage(ufoImageSelector);
            }
        });
    }

    private void updateSelectedImage(JPanel ufoImageSelector) {
        for (Component component : ufoImageSelector.getComponents()) {
            if (component.isVisible()) {
                selectedUfoImage = imageLabelMap.get(component);
                break;
            }
        }
    }
    
    
    private void createBackButton(JPanel menuPanel) {
        RoundedButton backBtn = new RoundedButton("<html><div style='text-align: center;'>Volver</html>", 20);
        configureBackButton(backBtn);
        menuPanel.add(backBtn);
    }

    private void configureBackButton(RoundedButton backBtn) {
        backBtn.setBounds(100, 430, 305, 30);
        backBtn.setBackground(GlobalView.BTN_BACKGROUND);
        backBtn.setForeground(GlobalView.BTN_FOREGROUND);
        backBtn.addActionListener(createBackButtonListener());
    }

    private ActionListener createBackButtonListener() {
        return new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                saveSettings();
                ufoGameView.getBodyCardLayout().show(ufoGameView.getUfoGameBody(), "Play");
            }
        };
    }

    private void saveSettings() {
        saveSelectedUfoDesign();
        saveUfosAmount();
        saveUfosSpeed();
        saveSpawnRate();
        showTrajectory = trayectoryCheckbox.isSelected();
    }

    private void saveSelectedUfoDesign(){
        ufoGameView.getPresenter().sendSelectedUfoDesign(selectedUfoImage);
        ufoGameView.getPresenter().requestUfoDesign();
    }

    private void saveUfosAmount() {
        try {
            int ufosAmount = Integer.parseInt(txtUfosAmount.getText());
            ufoGameView.getPresenter().sendNumberofUfos(ufosAmount);
        } catch (NumberFormatException e) {
            ufoGameView.getPresenter().sendNumberofUfos(5);
        }
    }

    private void saveUfosSpeed() {
        try {
            int ufosSpeed = Integer.parseInt(txtUfosSpeed.getText());
            ufoGameView.getPresenter().sendSpeed(ufosSpeed);
        } catch (NumberFormatException e) {
            ufoGameView.getPresenter().sendSpeed(2);
        }
    }

    private void saveSpawnRate() {
        try {
            int spawnRate = Integer.parseInt(txtSpawnRate.getText());
            ufoGameView.getPresenter().sendSpawnRate(spawnRate);
        } catch (NumberFormatException e) {
            ufoGameView.getPresenter().sendSpawnRate(1000);
        }
    }

private void createAndAddTextFields(JPanel menuPanel) {
    txtSpawnRate = createTextField("Ingrese el Radio de Aparicion en Milisengundos", 100, 45, 300, 30);
    txtUfosAmount = createTextField("Ingrese la Cantidad de Ovnis", 100, 115, 300, 30);
    txtUfosSpeed = createTextField("Ingrese la Velocidad de Ovnis", 100, 185, 300, 30);
    menuPanel.add(txtSpawnRate);
    menuPanel.add(txtUfosAmount);
    menuPanel.add(txtUfosSpeed);
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