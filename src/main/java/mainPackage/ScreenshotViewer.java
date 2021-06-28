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
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;

/**
 * @author Y@L
 */
public class ScreenshotViewer extends JComponent
{
    private BufferedImage img;
    private int imageScallingPercent = 80; //Процент заполнения окна изображением
    private int resolutionPercent = 45;//процент заполнения новым окном экрана по высоте
    private String imgName = "";
    private static int framesQuantity = 0;

    int tmpWidth = 720;
    int tmpHeight = 405;

    public ScreenshotViewer(BufferedImage aImg, String aImgName)
    {
        if (framesQuantity > 6)
        {
            JOptionPane.showMessageDialog(this, "Количество окон просмотрщика изображений не должно превышать 7.", "Невозможно открыть просмотрщик", JOptionPane.INFORMATION_MESSAGE, null);
            return;
        }
        if (aImg == null)
        {
            throw new NullPointerException("Переменная типа BufferedImage ссылается на несуществующий обьект.");
        }

        img = aImg;
        imgName = aImgName;

        framesQuantity++;
        initComponents();
    }

    private void initComponents()
    {
        JFrame frm = new JFrame();

        //frm.setSize(720, 405);
        resolutionPercent = mainPackage.main_gui.getFrameObject().getScreenshotViewerSizePercent();
        setAdaptiveFrameSize(resolutionPercent);
        frm.setSize(tmpWidth, tmpHeight);

        frm.setTitle(imgName + " - VCH Screenshot Viewer");

        setLayout(new FlowLayout());

        JMenuBar main_MenuBar = new JMenuBar();
        JMenu main_Menu = new JMenu();
        JMenuItem exit_MenuItem = new JMenuItem();

        exit_MenuItem.setSize(1, 1);
        main_Menu.setSize(1, 1);
        main_MenuBar.setSize(1, 1);

        exit_MenuItem.setLocation(1, 1);
        main_Menu.setLocation(1, 1);
        main_MenuBar.setLocation(1, 1);

        exit_MenuItem.setBackground(new Color(0, 0, 0, 0));

        exit_MenuItem.addActionListener((event) ->
        {
            framesQuantity--;
            frm.dispose();
        });
        exit_MenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0));

        main_MenuBar.add(main_Menu);
        main_Menu.add(exit_MenuItem);
        //javax.swing.GroupLayout legend_JPanelLayout = new javax.swing.GroupLayout(null);

        frm.addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowClosing(WindowEvent e)
            {
                framesQuantity--;
                DebugTools.printDebugMessage("Количество окон просмотрщика: " + (framesQuantity + 1));
            }
        });

        frm.add(this);
        frm.add(main_MenuBar, BorderLayout.NORTH);
        //pack();
        frm.setVisible(true);

    }


    protected void paintComponent(Graphics g)
    {
        if(img==null)
        {DebugTools.printDebugMessage("Изображение не найдено."); return;}
        DebugTools.printDebugMessage("Процент заполнения окна: " + imageScallingPercent);
        g.drawImage(img, 0, 0, (int) ((double) getWidth() * ((double) imageScallingPercent / 100.0)), (int) ((double) getHeight() * ((double) imageScallingPercent / 100.0)), this);
    }

    public void setImageScallingPercent(final int value)
    {
        if (value < 0 || value > 100)
            throw new IllegalArgumentException("Величина масштабирования изображения в окне должна находится в пределах 0 < N < 100");
        imageScallingPercent = value;
    }

    public void setAdaptiveFrameSize(final int value)
    {
        if (value < 1 || value > 100)
            throw new IllegalArgumentException("Процент размера окна с изображением должен находится в пределах 1 < N < 100");

        resolutionPercent = value;
        int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
        int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;

        tmpWidth = (int) ((double) screenWidth * ((double) resolutionPercent / 100.0));
        tmpHeight = (int) ((double) screenHeight * ((double) resolutionPercent / 100.0));

        tmpWidth = (int) ((double) tmpHeight * 1.77777777);
        tmpHeight = (tmpHeight / 9) * 9;

        DebugTools.printDebugMessage("Размер окна: " + tmpWidth + "x" + tmpHeight);
    }

    public void setImage(BufferedImage aImg, String aimgName)
    {
        img = aImg;
        imgName = aimgName;
        repaint();
    }

}
