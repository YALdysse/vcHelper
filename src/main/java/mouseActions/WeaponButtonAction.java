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

import mainPackage.DebugTools;
import mainPackage.ScreenshotViewer;
import mainPackage.main_gui;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class WeaponButtonAction extends MouseAdapter
{
    private double[][] weaponsData;
    private main_gui gui_obj;

    public WeaponButtonAction(final double[][] aWeaponsData, main_gui aGui_obj)
    {
        weaponsData = aWeaponsData;
        gui_obj = aGui_obj;
    }

    public void mouseClicked(MouseEvent e)
    {
        if (e.getButton() == MouseEvent.BUTTON3)
        {
            JButton tmpButton = (JButton) e.getSource();
            tmpButton.setVisible(false);

            //Подсчитываем кол-во отмеченных
            JButton[] weaponButtons = gui_obj.getWeaponButtons();
            int countVisible = 0;

            for (int k = 0; k < weaponButtons.length; k++)
            {
                if (weaponButtons[k].isVisible())
                {
                    countVisible++;
                }
            }

            String tmpStr = gui_obj.getWeapons_JCheckBox().getText();
            int startIndex = tmpStr.indexOf(" (");
            int endIndex = tmpStr.indexOf(")");

            if (startIndex == -1)
            {
                startIndex = tmpStr.length();
            }

            gui_obj.getWeapons_JCheckBox().setText(tmpStr.substring(0, startIndex) + " (" + countVisible + ")");

            if (countVisible == 0)
            {
                gui_obj.getWeapons_JCheckBox().setSelected(false);
                gui_obj.getShowWeapons_JMenuItem().setSelected(false);
                gui_obj.getWeapons_JCheckBox().setText(tmpStr.substring(0, startIndex));
            }
        }
        if (e.getButton() == MouseEvent.BUTTON1)
        {
            //System.out.println("нажаат левая клавиша мишы");
            JButton tmpButton = (JButton) e.getSource();

            for (int k = 0; k < weaponsData.length; k++)
            {
                if (tmpButton.getX() == weaponsData[k][0])
                {
                    if (tmpButton.getY() == weaponsData[k][1])
                    {
                        try
                        {
                            BufferedImage bufImg = ImageIO.read(main_gui.mainGui_ClassLoader.getResource("Images/Screenshots/Weapons/weapon_" + k + ".jpg"));
                            ScreenshotViewer view = new ScreenshotViewer(bufImg, "weapon_" + k);
                            view.setImageScallingPercent(100);
                            view.setVisible(true);
                            break;
                        }
                        catch (IOException IOExc)
                        {
                            DebugTools.printDebugMessage("Возникла ошибка при загрузке изображения оружия: weapon_" + k + ".jpg");
                            DebugTools.createLogFile(IOExc);
                        }
                        catch (IllegalArgumentException illegArgumExc)
                        {
                            JOptionPane.showMessageDialog(null, "Изображение не было загружено.\nИмя файла: " + "weapon_" + k + ".jpg", "Скриншот не найден", JOptionPane.ERROR_MESSAGE);
                            DebugTools.printDebugMessage("Возникла ошибка при загрузке изображения оружия.");
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
