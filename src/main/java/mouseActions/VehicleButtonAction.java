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

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import mainPackage.*;

public class VehicleButtonAction extends MouseAdapter
{
    private double[][] vehiclesData;
    private ClassLoader classLoad;
    private main_gui gui_obj;

    public VehicleButtonAction(final double[][] aVehiclesData, ClassLoader cl, main_gui aGui_obj)
    {
        vehiclesData = aVehiclesData;
        classLoad = cl;
        gui_obj = aGui_obj;
    }

    public void mouseClicked(MouseEvent e)
    {
        if (e.getButton() == MouseEvent.BUTTON3)
        {
            JButton tmpButton = (JButton) e.getSource();
            tmpButton.setVisible(false);

            //Подсчитываем кол-во отмеченных
            ButtonExtended[] vehBut = gui_obj.getVehicleButtons();
            int countVisible = 0;

            for (int k = 0; k < vehBut.length; k++)
            {
                if (vehBut[k].isVisible())
                {
                    countVisible++;
                }
            }

            String tmpStr = gui_obj.getVehicle_CheckBox().getText();
            int startIndex = tmpStr.indexOf(" (");
            int endIndex = tmpStr.indexOf(")");

            if (startIndex == -1)
            {
                startIndex = tmpStr.length();
            }

            gui_obj.getVehicle_CheckBox().setText(tmpStr.substring(0, startIndex) + " (" + countVisible + ")");

            if (countVisible == 0)
            {
                gui_obj.getVehicle_CheckBox().setSelected(false);
                gui_obj.getShowVehicles_JMenuItem().setSelected(false);
                gui_obj.getVehicle_CheckBox().setText(tmpStr.substring(0, startIndex));
            }
        }
        if (e.getButton() == MouseEvent.BUTTON1)
        {
            //System.out.println("нажаат левая клавиша мишы");
            JButton tmpButton = (JButton) e.getSource();

            for (int k = 0; k < vehiclesData.length; k++)
            {
                if (tmpButton.getX() == vehiclesData[k][0])
                {
                    if (tmpButton.getY() == vehiclesData[k][1])
                    {
                        try
                        {
                            BufferedImage bufImg = ImageIO.read(classLoad.getResource("Images/Screenshots/Vehicles/vehicle_" + k + ".jpg"));
                            ScreenshotViewer view = new ScreenshotViewer(bufImg, "vehicle_" + k);
                            view.setImageScallingPercent(100);
                            view.setVisible(true);
                            break;
                        }
                        catch (IOException IOExc)
                        {
                            DebugTools.printDebugMessage("Возникла ошибка при загрузке изображения автомобиля.");
                            DebugTools.createLogFile(IOExc);
                        }
                        catch (IllegalArgumentException illegArgumExc)
                        {
                            JOptionPane.showMessageDialog(null, "Изображение не было загружено.\nИмя файла: " + "vehicle_" + k + ".jpg", "Скриншот не найден", JOptionPane.ERROR_MESSAGE);
                            DebugTools.printDebugMessage("Возникла ошибка при загрузке изображения автомобиля.");
                            DebugTools.createLogFile(illegArgumExc);
                            return;
                        }
                    }
                }
            }
        }
    }

}
