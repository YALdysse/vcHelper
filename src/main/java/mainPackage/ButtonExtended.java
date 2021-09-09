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
import java.awt.event.*;
import java.util.ArrayList;

public class ButtonExtended extends JButton
{
    private JToolTip tooltp;
    private static Font fontForToolTip;
    private static toolTipFrame toolTipFrameForButtonExtended;
    private String messageToToolTip = "";
    private ButtonExtended thisButtonObject = this;

    static private Color backgroundToolTip_Color = new Color(238, 210, 181);
    static private Color foregroundToolTip_Color = new Color(76, 40, 22);

    static
    {
        toolTipFrameForButtonExtended = new toolTipFrame("");
    }

    @Override
    public JToolTip createToolTip()
    {
        //tooltp = new ToolTipExtended();
        tooltp = new JToolTip();

        if (fontForToolTip != null)
        {
            tooltp.setFont(fontForToolTip);
        }
        tooltp.setBackground(backgroundToolTip_Color);
        //76,40,22
        tooltp.setForeground(foregroundToolTip_Color);

        //170, 126, 40
        tooltp.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(145, 30, 66), 4),
                BorderFactory.createEmptyBorder(2, 2, 2, 2)));
        //setBackground(new Color(201, 209, 200));
        ToolTipManager.sharedInstance().setDismissDelay(10000);
        DebugTools.printDebugMessage("ToolTip создан");

        return tooltp;
    }


    public ButtonExtended()
    {
        super();

        this.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseExited(MouseEvent e)
            {
                toolTipFrameForButtonExtended.setVisible(false);
            }

            @Override
            public void mouseEntered(MouseEvent e)
            {
                //Если курсор находится в пределах кнопки - отображать мою подсказку
                if (toolTipFrameForButtonExtended.isNotNullToolTip())
                {
                    toolTipFrameForButtonExtended.setToolTipFrameTextAndShow(messageToToolTip);
                    toolTipFrameForButtonExtended.setLocation((int) getLocationOnScreen().getX() + 20, (int) getLocationOnScreen().getY() + 30);
                    //toolTipFrameForButtonExtended.setVisible(true);
                }
            }
        });
    }

    public void setTextToExtendedToolTip(final String aMessage)
    {
        messageToToolTip = aMessage;
        toolTipFrameForButtonExtended.setToolTipFrameText(messageToToolTip);
    }

    public JToolTip getToolTip()
    {
        return tooltp;
    }

    public void setFontForExtendedToolTip(final Font aFont)
    {
        fontForToolTip = aFont;

        if (toolTipFrameForButtonExtended != null)
        {
            toolTipFrameForButtonExtended.messageArea.setFont(fontForToolTip);
        }
    }

    public void closeExtendedToolTip()
    {
        toolTipFrameForButtonExtended.setVisible(false);
    }

    static class toolTipFrame extends JWindow
    {
        //private String message = "";
        private JTextArea messageArea;
        private int countLines = -1;
        private final int BORDER_OFFSET = 3;
        private final int BORDER_THICKNESS = 5;

        public toolTipFrame(final String aMessage)
        {

            //this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            this.setSize((int) (((double) Toolkit.getDefaultToolkit().getScreenSize().getWidth()) * 0.5), 200);
            //this.setLocationRelativeTo(thisButtonObject);
            setLocation(0, 0);
            //setLayout(null);


            messageArea = new JTextArea(aMessage);
            messageArea.setLineWrap(true);
            messageArea.setWrapStyleWord(true);

            if (fontForToolTip != null)
            {
                messageArea.setFont(fontForToolTip);
            }
            messageArea.setBackground(new Color(238, 210, 181));
            messageArea.setForeground(new Color(76, 40, 22));

            messageArea.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(145, 30, 66), 5),
                    BorderFactory.createEmptyBorder(BORDER_OFFSET, BORDER_OFFSET, BORDER_OFFSET, BORDER_OFFSET)));

            DebugTools.printDebugMessage("Количество строк в toolTipFrame: " + countLines);


            add(messageArea);

            if (isNotNullToolTip())
                setOptimalSize();

            //this.setVisible(true);
        }

        private void setOptimalSize()
        {
            int tmpWidth = 0;
            //Если ли в сообщении явно указанные разделители строк
            int countLines = getCountSeparator(messageArea.getText());

            if (countLines == 0)
            {
                int maxWidthLine = (int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() * 0.3);
                DebugTools.printDebugMessage("Максимальна ширина одной строки: " + maxWidthLine);

                if (YALtools.getTextWidth(messageArea.getText(), messageArea.getFont(), messageArea) > maxWidthLine)
                {//разбиваем на части
                    ArrayList<String> textWithLineSeparators = YALtools.SplitToLines(messageArea.getText(), maxWidthLine, messageArea.getFont(), messageArea);
                    DebugTools.printDebugMessage("Кол-во разделенных строк: " + textWithLineSeparators.size());
                    String lineWithMaxLenght_str = YALtools.getMostWidthString(textWithLineSeparators, messageArea.getFont(), messageArea);

                    tmpWidth = (int) YALtools.getTextWidth(lineWithMaxLenght_str, messageArea.getFont(), messageArea);
                    DebugTools.printDebugMessage("Ширина наибольшей строки: " + tmpWidth);

                    countLines = textWithLineSeparators.size();
                    //tmpWidth -= 20;
                    //messageArea.setRows(countLines);
                } else
                {//Значит можно отобразить одной строкой
                    tmpWidth = (int) YALtools.getTextWidth(messageArea.getText(), messageArea.getFont(), messageArea);
                    DebugTools.printDebugMessage("Отображение одной срокой");
                }
            } else //
            {

            }

            //учитываем Border
            tmpWidth += (BORDER_OFFSET * 4) + BORDER_THICKNESS;


            int tmpHeight = 0;

            //----------- Правильная высота
            int maxWidthLine = (int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() * 0.3);
            ArrayList<String> textWithLineSeparators = YALtools.SplitToLines(messageArea.getText(), maxWidthLine, messageArea.getFont(), messageArea);

            FontMetrics fm = null;
            if (messageArea != null && messageArea.getFont() != null)
            {
                fm = messageArea.getFontMetrics(messageArea.getFont());
                for (String mtpStr : textWithLineSeparators)
                {
                    tmpHeight += fm.getStringBounds(mtpStr, messageArea.getGraphics()).getHeight();
                }
                tmpHeight += (BORDER_OFFSET * 3) + BORDER_THICKNESS;
            }
            //===========

            DebugTools.printDebugMessage("Используэтся переносов: " + getCountSeparator(messageArea.getText()));
            DebugTools.printDebugMessage("Количество строк в подсказке: " + countLines
                    + "Высота подсказки: " + (tmpHeight + 5));

            //tmpWidth+=20;
            DebugTools.printDebugMessage("tmpWidth: " + tmpWidth);
            setSize(tmpWidth, tmpHeight + 5);
            //messageArea.repaint();
        }

        public void setToolTipFrameText(final String aMessage)
        {
            messageArea.setText(aMessage);
        }

        public void setToolTipFrameTextAndShow(final String aMessage)
        {
            messageArea.setText(aMessage);
            setOptimalSize();
            setVisible(true);
        }

        private int getCountSeparator(final String aInputStr)
        {
            int result = 0;
            for (int k = 0; k < aInputStr.length(); k++)
            {
                if (aInputStr.charAt(k) == '\n')
                    result++;
            }
            return result;
        }

        public boolean isNotNullToolTip()
        {
            if (messageArea.getText().equals(""))
            {
                return false;
            }
            return true;
        }

        public String getMostLenghtLine(final String aInputStr)
        {
            String[] splittedStrings = aInputStr.split("\n");


            String maxString = "";
            for (int k = 0; k < splittedStrings.length; k++)
            {
                if (splittedStrings[k].length() > maxString.length())
                {
                    maxString = splittedStrings[k];
                }
            }
            return maxString;
        }
    }
}
