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

/**
 * @author Y@Ldysse
 */
public class psevdoImageBox extends JComponent
{
    private BufferedImage img = null;
    private double drawImageWidth = -1;
    private double drawImageHeight = -1;
    private boolean doSaveRatio = false;
    private int currentImageFitStyle = -1;

    public static final int FIT_COMPONENT_TO_IMAGE = 1;
    public static final int FIT_IMAGE_TO_COMPONENT = 2;

    public psevdoImageBox()
    {

    }

    /**
     * По умолчанию, компонент будет подогнан под размер изображения
     */
    public psevdoImageBox(final BufferedImage aImg)
    {
        img = aImg;
        drawImageWidth = img.getWidth();
        drawImageHeight = img.getHeight();
        setSize((int) drawImageWidth, (int) drawImageHeight);
        setPreferredSize(new Dimension((int) drawImageWidth, (int) drawImageHeight));
    }

    /**
     * Конструктор с возможностью задавать, как изображение будет отобрежено (имеется ввиду размер)
     * в компоненте
     */
    public psevdoImageBox(final BufferedImage aImg, final int sizeStyle)
    {
        img = aImg;

        currentImageFitStyle = sizeStyle;
        fitImage(sizeStyle, false);

        drawImageWidth = img.getWidth();
        drawImageHeight = img.getHeight();
    }

    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        fitImage(currentImageFitStyle, doSaveRatio);

        if (img != null)
        {
            DebugTools.printDebugMessage("Размер изображения psevdoImageBox: " + drawImageWidth + "x" + drawImageHeight);
            //g.drawImage(img, 0, 0, (int) drawImageWidth, (int) drawImageHeight, null);
            //положение по центру
            int tmpX = (int) (getSize().getWidth() / 2.0) - (int) (drawImageWidth / 2);
            g.drawImage(img, tmpX, 0, (int) drawImageWidth, (int) drawImageHeight, null);

            //Уменашать высоту компонента, если разрешение изображения достигло предела
            if(getSize().getHeight() > img.getHeight())
            {
                setSize((int)getSize().getWidth(), img.getHeight());
            }
        }



        //else
        //g.drawLine(0,0,1,1);
    }

    public void setImageSize(final double aWidth, final double aHeight)
    {
        drawImageWidth = aWidth;
        drawImageHeight = aHeight;
        repaint();
    }

    /**
     * Позволяет задать, какой размер будет установлен для изображения/компонента.
     * Например, подогнать размер компонента под изображение, или наоборот.
     *
     * @param doSaveRatio Изменять ли пропорции изображения при подгонке
     */
    public void setImageFitStyle(final int aImageFitStyle, final boolean doSaveRatio)
    {
        fitImage(aImageFitStyle, doSaveRatio);
        repaint();
    }

    public void addImage(final BufferedImage aImg)
    {
        img = aImg;
        repaint();
    }

    /**
     * Осуществяет подгонку размеров изображения к компоненту либо наоборот,
     * в зависимости от параметра
     */
    private void fitImage(final int aImageFitStyle, final boolean AdoSaveRatio)
    {
        if (img == null)
            return;

        doSaveRatio = AdoSaveRatio;
        currentImageFitStyle = aImageFitStyle;

        switch (aImageFitStyle)
        {
            case 1://подограть компонент под размер изображения
                drawImageWidth = img.getWidth();
                drawImageHeight = img.getHeight();
                setSize((int) drawImageWidth, (int) drawImageHeight);
                setPreferredSize(new Dimension((int) drawImageWidth, (int) drawImageHeight));
                break;

            case 2://подогнать размер изображения под компонент
                //Убираем растягивание изображения если размер компонента больше чем длина стороны
                //Для ширины
                if (img.getWidth() < getSize().getWidth())
                {
                    drawImageWidth = img.getWidth();
                } else
                {
                    drawImageWidth = getSize().getWidth();
                }

                //Для высоты
                if (img.getHeight() < getSize().getHeight())
                {
                    drawImageHeight = getSize().getHeight();
                } else
                {
                    drawImageHeight = getSize().getHeight();
                }

                //Если нужно - сохраняем пропорции
                if (doSaveRatio)
                {
                    double koef = (double) img.getWidth() / (double) img.getHeight();
                    double minSide = Math.min(drawImageWidth, drawImageHeight);

                    if (minSide == drawImageWidth)
                    {
                        drawImageHeight = koef * drawImageWidth;
                    } else
                    {
                        drawImageWidth = drawImageHeight * koef;
                    }


                }

                //setSize((int) drawImageWidth, (int) drawImageHeight);
                //setPreferredSize(new Dimension( (int)drawImageWidth, (int) drawImageHeight));

                break;
        }
    }

    public  double getDrawImageWidth()
    {
        return drawImageWidth;
    }

    public BufferedImage getImage()
    {
        return img;
    }
}
