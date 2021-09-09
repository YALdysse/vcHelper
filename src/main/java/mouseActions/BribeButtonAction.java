/* Vice City Helper
 * Copyright (C) 2021 Yaroslav Lytvynov
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package mouseActions;

import mainPackage.ButtonExtended;
import mainPackage.DebugTools;
import mainPackage.ScreenshotViewer;
import mainPackage.main_gui;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class BribeButtonAction extends MouseAdapter
{
    private double[][] bribeData;
    private main_gui gui_obj;

    public BribeButtonAction(double[][] aBribeData, main_gui aGui_obj)
    {
        bribeData = aBribeData;
        gui_obj = aGui_obj;
    }

    @Override
    public void mouseClicked(MouseEvent e)
    {
        if (e.getButton() == MouseEvent.BUTTON3)
        {
            JButton tmpButton = (JButton) e.getSource();
            tmpButton.setVisible(false);

            //Подсчитываем кол-во отмеченных
            JButton[] bribeButtons = gui_obj.getBribeButtons();
            int countVisible = 0;

            for (int k = 0; k < bribeButtons.length; k++)
            {
                if (bribeButtons[k].isVisible())
                {
                    countVisible++;
                }
            }

            String tmpStr = gui_obj.getBribes_JCheckBox().getText();
            int startIndex = tmpStr.indexOf(" (");
            int endIndex = tmpStr.indexOf(")");

            if (startIndex == -1)
            {
                startIndex = tmpStr.length();
            }

            gui_obj.getBribes_JCheckBox().setText(tmpStr.substring(0, startIndex) + " (" + countVisible + ")");

            if (countVisible == 0)
            {
                gui_obj.getBribes_JCheckBox().setSelected(false);
                gui_obj.getShowBribes_JMenuItem().setSelected(false);
                gui_obj.getBribes_JCheckBox().setText(tmpStr.substring(0, startIndex));
            }
        }
        if (e.getButton() == MouseEvent.BUTTON1)
        {
            //System.out.println("нажаат левая клавиша мишы");
            JButton tmpButton = (JButton) e.getSource();

            for (int k = 0; k < bribeData.length; k++)
            {
                if (tmpButton.getX() == bribeData[k][0])
                {
                    if (tmpButton.getY() == bribeData[k][1])
                    {
                        try
                        {
                            BufferedImage bufImg = ImageIO.read(main_gui.mainGui_ClassLoader.getResource("Images/Screenshots/Bribes/bribe_" + k + ".jpg"));
                            ScreenshotViewer view = new ScreenshotViewer(bufImg, "bribe_" + k);
                            view.setImageScallingPercent(100);
                            view.setVisible(true);
                            break;
                        }
                        catch (IOException IOExc)
                        {
                            DebugTools.printDebugMessage("Возникла ошибка при загрузке изображения взятки: bribe_" + k + ".jpg");
                            DebugTools.createLogFile(IOExc);
                        }
                        catch (IllegalArgumentException illegArgumExc)
                        {
                            JOptionPane.showMessageDialog(null, "Изображение не было загружено.\nИмя файла: " + "bribe_" + k + ".jpg", "Скриншот не найден", JOptionPane.ERROR_MESSAGE);
                            DebugTools.printDebugMessage("Возникла ошибка при загрузке изображения взятки.");
                            DebugTools.createLogFile(illegArgumExc);
                            return;
                        }
                    }
                }
            }
            tmpButton.setBackground(main_gui.TRANSPARENCY);
            tmpButton.setBorder(null);
            tmpButton.setOpaque(false);
        }
    }
}
