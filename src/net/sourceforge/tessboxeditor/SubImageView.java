package net.sourceforge.tessboxeditor;

import net.sourceforge.tessboxeditor.components.ImageIconScalable;

import javax.swing.*;
import java.awt.*;

public class SubImageView extends JLabel {

    private static final int ICON_SCALE_FACTOR = 4;

    public static final String TAG = SubImageView.class.getName();

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;

        if (g == null || g2d == null) {
            return;
        }

        g2d.setColor(Color.GREEN);

        ImageIconScalable icon = (ImageIconScalable)this.getIcon();

        if (icon == null) {
            return;
        }

        int iconActualWidth = icon.getIconWidth() / Gui.scaleFactor;
        int scaledPixelSize = icon.getIconWidth() / iconActualWidth;
        int offset = scaledPixelSize * Gui.ICON_MARGIN_PIXELS;

        // top left
        int x1 = (this.getWidth() - icon.getIconWidth()) / 2;
        int y1 = (this.getHeight() - icon.getIconHeight()) / 2;

        // bottom right
        int x2 = (this.getWidth() + this.getIcon().getIconWidth()) / 2;
        int y2 = (this.getHeight() + this.getIcon().getIconHeight()) / 2;

        // left
        g2d.drawLine(x1 + offset, y1 + offset, x1+offset, y2-offset);
        // top
        g2d.drawLine(x1 + offset, y1 + offset, x2-offset, y1+offset);
        // right
        g2d.drawLine(x2 - offset, y1 + offset, x2-offset, y2-offset);
        // bottom
        g2d.drawLine(x1 + offset, y2 - offset, x2-offset, y2-offset);

        //System.out.println("insets: left=" + this.getInsets().left
        //                + " right=" + this.getInsets().right);
        //        g.drawLine(0, 0, 100, 100);
    }

}
