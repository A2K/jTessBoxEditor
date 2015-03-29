/**
 * Copyright @ 20011 Quan Nguyen
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.sourceforge.tessboxeditor;

import net.sourceforge.tessboxeditor.components.ImageIconScalable;
import net.sourceforge.tessboxeditor.datamodel.TessBox;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GuiWithSpinner extends GuiWithEdit {
    private final static Logger logger = Logger.getLogger(GuiWithSpinner.class.getName());

    @Override
    void stateChanged(javax.swing.event.ChangeEvent evt) {
        if (tableSelectAction) {
            return;
        }
        List<TessBox> selected = boxes.getSelectedBoxes();
        if (selected.size() <= 0) {
            return;
        } else if (selected.size() > 1) {
//            JOptionPane.showMessageDialog(this, "Select only one box for Spinner operation.");
            return;
        }

        TessBox box = selected.get(0);
        int index = this.boxes.toList().indexOf(box);

        box.setChrs(this.jTextFieldCharacter.getText());
        tableModel.setValueAt(box.getChrs(), index, 0);
        Rectangle rect = box.getRect();
        JSpinner sp = (JSpinner) evt.getSource();
        if (sp == this.jSpinnerX) {
            rect.x = (Integer) this.jSpinnerX.getValue();
            tableModel.setValueAt(String.valueOf(rect.x), index, 1);
        } else if (sp == this.jSpinnerY) {
            rect.y = (Integer) this.jSpinnerY.getValue();
            tableModel.setValueAt(String.valueOf(rect.y), index, 2);
        } else if (sp == this.jSpinnerW) {
            rect.width = (Integer) this.jSpinnerW.getValue();
            tableModel.setValueAt(String.valueOf(rect.width), index, 3);
        } else if (sp == this.jSpinnerH) {
            rect.height = (Integer) this.jSpinnerH.getValue();
            tableModel.setValueAt(String.valueOf(rect.height), index, 4);
        }

        Icon icon = jLabelImage.getIcon();

        try {
            Gui.iconPosX = rect.x;
            Gui.iconPosY = rect.y;

            BufferedImage fullImage = ((BufferedImage) ((ImageIcon) icon).getImage());

            Gui.imageWidth = fullImage.getWidth();
            Gui.imageHeight = fullImage.getHeight();

            Gui.iconHeight = rect.height;
            Gui.iconWidth = rect.width;

            int height = Gui.iconHeight + Gui.ICON_MARGIN_PIXELS * 2;
            int width = Gui.iconWidth +  Gui.ICON_MARGIN_PIXELS * 2;

            while(width + Gui.iconPosX > fullImage.getWidth() +1) {
                width -= 1;
            }

            while(height + Gui.iconPosY > fullImage.getHeight() + 1) {
                height -= 1;
            }

            BufferedImage subImage = fullImage.getSubimage(
                    Math.max(0, Math.min(Gui.imageWidth - 1, Gui.iconPosX - Gui.ICON_MARGIN_PIXELS)),
                    Math.max(0, Math.min(Gui.imageHeight - 1, Gui.iconPosY - Gui.ICON_MARGIN_PIXELS)),
                    width,
                    height
            );

            ImageIconScalable subIcon = new ImageIconScalable(subImage);
            subIcon.setScaledFactor(Gui.scaleFactor);
            jLabelSubimage.setIcon(subIcon);
        } catch (Exception e) {
            logger.log(Level.WARNING, e.getMessage(), e);
        }
        this.jLabelImage.repaint();
        updateSave(true);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                new GuiWithSpinner().setVisible(true);
            }
        });
    }
}
