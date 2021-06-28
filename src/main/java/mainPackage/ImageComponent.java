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

package mainPackage;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * Расширенная реализация класса JPanel, перерисовывает картинку
 * , которая находится в JPanel. Поскольку, нужно было, чтобы при
 * изменении размеров окна, панели с изображениями и легендой
 * перерисовывались, реализация смены позиции легенды описана сдесь.
 * <p>
 * 24.04.2021 была изменено на Component...Весь код отрисовки изображений был перенесен
 * сюда - графические артефакти грозились уити.
 *
 * @author Y@L
 */
public class ImageComponent extends JComponent
{
    private BufferedImage img;
    private main_gui guiObj;
    private int gui_width;
    private int gui_height;
    private int scaled_image_width;
    private int scaled_image_height;
    private double koefWidhtHeigh = -1.0;
    //private JPanel legend_JPanel;
    private int legendOffsetToMap = 0; //persent 0 < N < 100
    private ArrayList<BufferedImage> allImage = new ArrayList<>();
    private double[][] armorData;
    private double[][] medkitData;
    private double[][] bribesData;
    private double[][] drugsData;
    private double[][] spraiesData;
    private double[][] propertiesData;
    private double[][] rampagesData;
    private double[][] clothesData;
    private double[][] weaponsData;
    private double[][] storesData;
    private BufferedImage armorIcon;
    private BufferedImage medkitIcon;
    private BufferedImage bribeIcon;
    private BufferedImage drugIcon;
    private BufferedImage sprayIcon;
    private BufferedImage propertiesIcon;
    private BufferedImage rampageIcon;
    private BufferedImage clothesIcon;
    private BufferedImage weaponIcon;
    private BufferedImage storeIcon;

    private JButton[] medkitButtons;

    public ImageComponent(main_gui aGUIobj, BufferedImage aImg)
    {
        super();
        guiObj = aGUIobj;
        img = aImg;
        scaled_image_height = img.getHeight();
        scaled_image_width = img.getWidth();
    }

    public void setImage(BufferedImage aImg)
    {
        img = aImg;
        this.repaint();
    }

    public void addImage(BufferedImage aImg)
    {
        allImage.add(aImg);
    }

    public void setArmorData(double aArmorData[][])
    {
        armorData = aArmorData;
    }

    public void setArmorIcon(BufferedImage aArmorIcon)
    {
        armorIcon = aArmorIcon;
    }

    public void setMedkitData(double[][] aMedkitData)
    {
        medkitData = aMedkitData;
    }

    public void setMedkitIcon(BufferedImage aMedkitIcon)
    {
        medkitIcon = aMedkitIcon;
    }

    public void setBribeIcon(BufferedImage aBribeIcon)
    {
        bribeIcon = aBribeIcon;
    }

    public void setBribesData(double[][] aBribesData)
    {
        bribesData = aBribesData;
    }

    public void setDrugIcon(BufferedImage aDrugIcon)
    {
        drugIcon = aDrugIcon;
    }

    public void setDrusData(final double[][] aDrugsData)
    {
        drugsData = aDrugsData;
    }

    public void setSpraiesIcon(BufferedImage aSprayIcon)
    {
        sprayIcon = aSprayIcon;
    }

    public void setSpraiesData(final double[][] aSpraiesData)
    {
        spraiesData = aSpraiesData;
    }

    public void setPropertiesData(final double[][] aPropertiesData)
    {
        propertiesData = aPropertiesData;
    }

    public void setPropertiesIcon(BufferedImage aPropertiesIcon)
    {
        propertiesIcon = aPropertiesIcon;
    }

    public void setRampageData(final double[][] aRampageData)
    {
        rampagesData = aRampageData;
    }

    public void setRampageIcon(BufferedImage aRampageIcon)
    {
        rampageIcon = aRampageIcon;
    }

    public void setClothesData(final double[][] aClothesData)
    {
        clothesData = aClothesData;
    }

    public void setClothesIcon(BufferedImage aClothesIcon)
    {
        clothesIcon = aClothesIcon;
    }

    public void setWeaponsData(final double[][] aWeaponsData)
    {
        weaponsData = aWeaponsData;
    }

    public void setWeaponIcon(BufferedImage aWeaponIcon)
    {
        weaponIcon = aWeaponIcon;
    }

    public void setStoresData(final double[][] aStoresData)
    {
        storesData = aStoresData;
    }

    public void setStoreIcon(BufferedImage aStoreIcon)
    {
        storeIcon = aStoreIcon;
    }

    public void setMedkitButtons(JButton[] aMedkitButtons)
    {
        medkitButtons = aMedkitButtons;
    }

    /**
     * author Y@L
     */
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        //Что-то нехорошее
        /*
        if(medkitButtons!=null)
        {
            for (int k = 0; k < medkitButtons.length; k++)
            {
                medkitButtons[k].setVisible(false);
                medkitButtons[k].setLocation((int) ((double) scaled_image_width * medkitData[k][2]), (int) ((double) scaled_image_height * medkitData[k][3]));
                medkitButtons[k].setVisible(true);
                //DebugTools.printDebugMessage("Перемещение меток аптечек");
                //medkitButtons[k].revalidate();
            }
        }
         */

        if (img == null)
        {
            scaled_image_width = 0;
            scaled_image_height = 0;
            DebugTools.printDebugMessage("При отрисовке изображения на панели возникла ошибка. Изображение не задано.");
            changeLegendLocation();
            return;
        }
        int minSidePanel = Math.min(this.getWidth(), this.getHeight());

        koefWidhtHeigh = (double) img.getWidth() / (double) img.getHeight();
        int minSide = img.getWidth() < img.getHeight() ? img.getWidth() : img.getHeight();

        if (minSidePanel == this.getWidth())
        {
            scaled_image_width = minSidePanel;
            scaled_image_height = (int) ((double) minSidePanel * koefWidhtHeigh);
        } else
        {
            scaled_image_height = minSidePanel;
            scaled_image_width = (int) ((double) minSidePanel * koefWidhtHeigh);
        }

        //Внедрено 23.05.2021 в целях исправления различности положения меток
        //на разных разрешениях
        int tmpX = 0;
        int tmpY = 0;
        if (guiObj.isSouthEastMap())
        {
            tmpX = 521;
            tmpY = 486;
        }
        if (guiObj.isNorthEastMap())
        {
            tmpX = 421;
            tmpY = 486;
        }
        if (guiObj.isSouthWestMap())
        {
            tmpX = 454;
            tmpY = 486;
        }
        if (guiObj.isNorthWestMap())
        {
            tmpX = 417;
            tmpY = 486;
        }
        //================================
        //g.drawImage(img, 0, 0, scaled_image_width, scaled_image_height, null);

        g.drawImage(img, 0, 0, tmpX, tmpY, guiObj);
        //changeLegendLocation();
    }

    public int getScaled_image_width()
    {
        return scaled_image_width;
    }

    public int getScaled_image_height()
    {
        return scaled_image_height;
    }

//    public void setLegend(JPanel aLegend_JPanel)
//    {
//        legend_JPanel = aLegend_JPanel;
//    }

    public void setLegendOffsetToMap(final int aLegendOffsetTomap)
    {
        if (aLegendOffsetTomap < 0 || aLegendOffsetTomap > 100)
        {
            throw new IllegalArgumentException("Значение смещения в сторону карты должно находится в пределах 1 < N < 100.");
        }
        legendOffsetToMap = aLegendOffsetTomap;
    }

    private void changeLegendLocation()
    {
        //legend_JPanel.setLocation(scaled_image_width, 0);
        //legend_JPanel.setLocation(scaled_image_width - (int) (scaled_image_width * ((double) legendOffsetToMap / 100)), 0);
        System.out.println(main_gui.getCurrentTimeString() + " Размеры фрейма: " + guiObj.getWidth() + "x" + guiObj.getHeight());
        System.out.println(main_gui.getCurrentTimeString() + " Размеры изображения: " + scaled_image_width + "x" + scaled_image_height);
        //this.repaint();
        System.out.println(main_gui.getCurrentTimeString() + " Положение легенды: " + scaled_image_width + "x0");
    }

    private void setCorrectFrameSize()
    {
        //guiObj.setSize(legend_JPanel.getX()+legend_JPanel.getWidth(), guiObj.getHeight());
        //guiObj.setSize(100,200);
        //guiObj.pack();
        //guiObj.repaint();
    }
}
