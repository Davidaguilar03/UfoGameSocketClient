package co.edu.uptc.views.ufogameplayframe;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import co.edu.uptc.pojos.Ufo;
import co.edu.uptc.utilities.AudioPlayer;
import co.edu.uptc.utilities.ImagePanel;
import co.edu.uptc.utilities.PropertiesService;
import co.edu.uptc.views.GlobalView;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UfoGamePlayBody extends JPanel implements KeyListener {
    private UfoGamePlayView ufoGamePlayView;
    private PropertiesService propertiesService;
    private JPanel playBodyPanel;
    private List<Ufo> ufos;
    private String ufoImage;
    private String ufoImageOn; 
    private boolean showTrajectory;
    private Ufo selectedUfo;
    private ArrayList<Point> trajectoryPoints;
    private Image landingStripImage;
    private Image scaledLandingStripImage;
    private AudioPlayer backgroundMusicPlayer;
    private AudioPlayer crashSoundPlayer;
    private AudioPlayer landingSoundPlayer;

    public UfoGamePlayBody(UfoGamePlayView ufoGamePlayView) {
        this.propertiesService = new PropertiesService();
        this.ufoGamePlayView = ufoGamePlayView;
        this.trajectoryPoints = new ArrayList<>();
        this.ufos = ufoGamePlayView.getUfoGameView().getPresenter().getUfos();
        this.ufoImage = ufoGamePlayView.getUfoGameView().getUfoGameBody().getSelectedUfoImage();
        this.ufoImageOn = ufoImage.replace("-OFF", "-ON");
        this.showTrajectory = ufoGamePlayView.getUfoGameView().getUfoGameBody().isShowTrajectory();
        this.setLayout(null);
        initComponents();
        addMouseListeners();
    }

    private void initComponents() {
        initPlayPanel();
        loadLandingStripImage();
        initAudioPlayers();
        playBackgroundMusic();
    }

    private void addMouseListeners() {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                selectUfo(e.getPoint());
                playBodyPanel.requestFocusInWindow();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (selectedUfo != null) {
                    selectedUfo.setTrajectory(new ArrayList<>(trajectoryPoints));
                    trajectoryPoints.clear();
                }
            }
        });

        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (selectedUfo != null) {
                    trajectoryPoints.add(e.getPoint());
                    repaint();
                }
            }
        });
    }

    private void initAudioPlayers() {
        backgroundMusicPlayer = new AudioPlayer();
        crashSoundPlayer = new AudioPlayer();
        landingSoundPlayer = new AudioPlayer();
    }

    private void playBackgroundMusic() {
        backgroundMusicPlayer.playSound(propertiesService.getKeyValue("GameSound"), true);
    }

    public void playCrashSound() {
        crashSoundPlayer.playSound(propertiesService.getKeyValue("UfoCrashSound"), false);
    }

    public void playLandingSound() {
        landingSoundPlayer.playSound(propertiesService.getKeyValue("UfoLandingSound"), false);
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (selectedUfo != null) {
            if (e.getKeyCode() == KeyEvent.VK_UP) {
                increaseSpeed(selectedUfo);
            } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                decreaseSpeed(selectedUfo);
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    private void loadLandingStripImage() {
        try {
            landingStripImage = new ImageIcon(propertiesService.getKeyValue("UfoLanding")).getImage();
            scaledLandingStripImage = landingStripImage.getScaledInstance(80, 60, Image.SCALE_SMOOTH);
        } catch (Exception e) {
            landingStripImage = null;
            scaledLandingStripImage = null;
        }
    }

    private void initPlayPanel() {
        playBodyPanel = new ImagePanel(propertiesService.getKeyValue("PlayBackground"), 0.85f) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                drawLandingStrip(g);
                drawUfos(g);
                if (showTrajectory) {
                    drawTrajectory(g);
                }
            }
        };
        playBodyPanel.setFocusable(true);
        playBodyPanel.addKeyListener(this);
        playBodyPanel.setBounds(0, 0, 1200, 686);
        playBodyPanel.setLayout(null);
        this.add(playBodyPanel);
    }

    private void drawLandingStrip(Graphics g) {
        if (scaledLandingStripImage != null) {
            g.drawImage(scaledLandingStripImage, 580, 145, this);
        } 
    }

    private void drawUfos(Graphics g) {
        for (Ufo ufo : ufos) {
            String imagePath = (ufo == selectedUfo) ? ufoImageOn : ufoImage;
            ImageIcon ufoIcon = new ImageIcon(imagePath);
            Image ufoImage = ufoIcon.getImage();
            g.drawImage(ufoImage, ufo.getPosition().x, ufo.getPosition().y, 75, 54, this);
        }
    }

    private void drawTrajectory(Graphics g) {
        if (!trajectoryPoints.isEmpty()) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.setColor(GlobalView.HEADER_MENU_BACKGROUND);
            g2d.setStroke(new BasicStroke(3));
            for (int i = 0; i < trajectoryPoints.size() - 1; i++) {
                Point start = trajectoryPoints.get(i);
                Point end = trajectoryPoints.get(i + 1);
                g2d.drawLine(start.x, start.y, end.x, end.y);
            }
        }
    }

    private void selectUfo(Point point) {
        for (Ufo ufo : ufos) {
            if (ufo.getBounds().contains(point)) {
                selectedUfo = ufo;
                trajectoryPoints.clear();
                break;
            }
        }
        updateUFOs(); 
    }

    public void updateUFOs() {
        playBodyPanel.repaint();
    }

    private void increaseSpeed(Ufo ufo) {
        ufo.setSpeed(Math.max(ufo.getSpeed() + 1, 2)); 
        updateUFOs();
    }

    private void decreaseSpeed(Ufo ufo) {
        int newSpeed = ufo.getSpeed() - 1;
        if (newSpeed < 2) {
            newSpeed = 2; 
        }
        ufo.setSpeed(newSpeed);
        updateUFOs();
    }
}
