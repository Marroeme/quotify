package com.instaquotes;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class fotoApi {
    
public static void fotoApiAbfrage(Map<String, String> zitate) {
        // ToDo: Die Foto API einbinden um ein Foto zu generieren.

        try {

            String apiURL = "https://api.api-ninjas.com/v1/randomimage?category=abstract";

            URL url = new URI(apiURL).toURL();
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            // Mein API-Key wird hinterlegt, damit der Request angenommen wird
            connection.addRequestProperty("X-Api-Key", "Jpu9HpvsJOJfCZIgSA++HA==lWvaQ9QZZMAFklNR");
            connection.setRequestProperty("Accept", "image/jpg");

            
            BufferedImage foto = ImageIO.read((connection.getInputStream()));
            

            Graphics g = foto.getGraphics();
            g.setFont(g.getFont().deriveFont(30f));
            
            String zitat = zitate.get("Zitat");

            var textWidth = g.getFontMetrics().stringWidth(zitat);
            var horizontalPosition = ((foto.getWidth())/2)-(textWidth/2);
            var verticalPosition = foto.getHeight()/2;

            g.drawString(zitat, horizontalPosition, verticalPosition);
            g.dispose();

            

            JLabel fotoLabel = new JLabel(new ImageIcon(foto));

            JPanel jPanel = new JPanel();
            jPanel.add(fotoLabel);

            JFrame f = new JFrame();
            f.setSize(new Dimension(foto.getWidth(), foto.getHeight()));
            f.add(jPanel);
            f.setVisible(true);

        } catch (Exception e) {
            e.printStackTrace();

        }
    }

}
