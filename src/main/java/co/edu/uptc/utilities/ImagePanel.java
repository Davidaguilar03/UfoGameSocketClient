package co.edu.uptc.utilities;

import javax.swing.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

@NoArgsConstructor
@Getter
@Setter
public class ImagePanel extends JPanel {
    private Image backgroundImage;
    private float transparency;

    public ImagePanel(String imagePath, float transparency) {
        try {
            backgroundImage = ImageIO.read(new File(imagePath));
            this.transparency = Math.max(0, Math.min(transparency, 1.0f));
            if (backgroundImage != null) {
                setPreferredSize(new Dimension(backgroundImage.getWidth(null), backgroundImage.getHeight(null)));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            Graphics2D g2d = (Graphics2D) g.create();
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, transparency));
            g2d.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            g2d.dispose();
        }
    }
}
