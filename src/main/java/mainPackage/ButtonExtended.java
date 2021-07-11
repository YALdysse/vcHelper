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
    //private ToolTipExtended tooltp;
    private JToolTip tooltp;
    private Font fontForToolTip;
    private toolTipFrame toolTipFrameForButtonExtended;
    private String messageToToolTip = "";
    private boolean showToolTip = false;
    private ButtonExtended thisButtonObject = this;

    static private Color backgroundToolTip_Color = new Color(238, 210, 181);
    static private Color foregroundToolTip_Color = new Color(76, 40, 22);

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
        toolTipFrameForButtonExtended = new toolTipFrame("");

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
                showToolTip = true;
                //Если курсор находится в пределах кнопки - отображать мою подсказку
                if (showToolTip && toolTipFrameForButtonExtended.isNotNullToolTip())
                {
                    toolTipFrameForButtonExtended.setToolTipFrameText(messageToToolTip);
                    toolTipFrameForButtonExtended.setLocation((int) getLocationOnScreen().getX() + 20, (int) getLocationOnScreen().getY() + 30);

                    toolTipFrameForButtonExtended.setVisible(true);
                }
            }
        });
    }

    public void setTextToExtendedToolTip(final String aMessage)
    {
        messageToToolTip = aMessage;
        toolTipFrameForButtonExtended.setToolTipFrameText(messageToToolTip);
        //showToolTip = true;
    }

    public JToolTip getToolTip()
    {
        return tooltp;
    }

    public void setFontForToolTip(final Font aFont)
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

    class toolTipFrame extends JWindow
    {
        //private String message = "";
        private JTextArea messageArea;
        private int countLines = -1;

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

            messageArea.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(145, 30, 66), 4),
                    BorderFactory.createEmptyBorder(2, 2, 2, 2)));

            DebugTools.printDebugMessage("Количество строк в toolTipFrame: " + countLines);


            add(messageArea);

            if (isNotNullToolTip())
                setOptimalSize();

            //this.setVisible(true);
        }

        private void setOptimalSize()
        {
//            int countLines = getCountSeparator(messageArea.getText());
//            int tmpWidth = this.getWidth();
//            int tmpHeight = 28;
//
//
//            if (countLines == 0)
//            {
//                tmpWidth = (int) ((double) message.length() * 7.65);//
//
//                //если длина надписи превышает 30% ширины экрана - будем переносить
//                if (tmpWidth > Toolkit.getDefaultToolkit().getScreenSize().getWidth() * 0.3)
//                {
//                    //Опеределяем вo сколько раз длина надписи превышает предел длины одной строки в подсказке
//                    double koef = tmpWidth / (Toolkit.getDefaultToolkit().getScreenSize().getWidth() * 0.3);
//
//                    tmpWidth = (int) ((double) Toolkit.getDefaultToolkit().getScreenSize().getWidth() * 0.3);
//
//                    //Добавляем нужное кол-во строк
//                    countLines += (int) koef;
//                }
//            } else//на тот случай, когда разрыв строки указан явно, то подстаиваем под строку с наибольшей длиной
//            {
//                String maxLength_str = getMostLenghtLine(messageArea.getText());
//                FontMetrics tmp_fm = messageArea.getFontMetrics(messageArea.getFont());
//                tmpWidth = 0;
//                for (int k = 0; k < maxLength_str.length(); k++)
//                {
//                    tmpWidth += maxLength_str.charAt(k);
//                }
//                //tmpWidth = (int) ((double) getMostLenghtLine(messageArea.getText()));
//            }

            //Суть формулы: умножаем высоту одной строки на количество строк. От полученного значения отнимаем количество строк умноженное на коеффициент
            //getCountSeparator(messageArea.getText())


            int tmpWidth = 0;
            //Если ли в сообщении явно указанные разделители строк
            int countLines = getCountSeparator(messageArea.getText());

            if (countLines == 0)
            {
                int maxWidthLine = (int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() * 0.3);

                if (YALtools.getTextWidth(messageArea.getText(), messageArea.getFont(), messageArea) > maxWidthLine)
                {//разбиваем на части
                    ArrayList<String> textWithLineSeparators = YALtools.SplitToLines(messageArea.getText(), maxWidthLine, messageArea.getFont(), messageArea);
                    DebugTools.printDebugMessage("Кол-во разделенных строк: " + textWithLineSeparators.size());
                    String lineWithMaxLenght_str = YALtools.getMostLengthString(textWithLineSeparators);

                    tmpWidth = (int) YALtools.getTextWidth(lineWithMaxLenght_str, messageArea.getFont(), messageArea);
                    DebugTools.printDebugMessage("Ширина наибольшей строки: " + tmpWidth);

                    countLines = textWithLineSeparators.size();
                } else
                {//Значит можно отобразить одной строкой
                    tmpWidth = (int) YALtools.getTextWidth(messageArea.getText(), messageArea.getFont(), messageArea);

                }
            } else //
            {

            }

            int tmpHeight = 28;

            //tmpHeight = (tmpHeight * (countLines + 1)) - (int) (((countLines * 8.8)) - ((tmpHeight / (countLines + 1)) / 10));
            tmpHeight = messageArea.getFont().getSize() * (countLines + 2);

            DebugTools.printDebugMessage("Используэтся переносов: " + getCountSeparator(messageArea.getText()));
            DebugTools.printDebugMessage("Количество строк в подсказке: " + countLines
                    + "Высота подсказки: " + tmpHeight);


            setSize(tmpWidth, tmpHeight);
            //messageArea.repaint();
        }

        public void setToolTipFrameText(final String aMessage)
        {
            messageArea.setText(aMessage);
            setOptimalSize();
            //this.setVisible(true);
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
