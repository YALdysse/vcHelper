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

import java.awt.*;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

/**
 * Собрание частоиспользуэмых алгоритмов
 */
public class YALtools
{

    /**
     * Импортирует шрифт из каталога с ресурсами ./Fonts/название_файла_шрифта
     *
     * @throws java.io.IOException
     * @throws java.awt.FontFormatException
     * @author YALdysse
     */
    public static Font createFont(final String fileName) throws java.io.IOException, java.awt.FontFormatException
    {
        URL fontFile_url = YALtools.class.getClassLoader().getResource("Fonts/" + fileName);
        return Font.createFont(Font.PLAIN, fontFile_url.openStream());
    }

    /**
     * Добавляет явные разделители строк в текст
     *
     * @param aMessage      Исходный текст, в который нужно добавить разделители строк.
     * @param aMaxLineWidth Ширина в пикселях одной строки текста.
     * @param aMessageFont  Шрифт, который будет применен к тексту. Нужен для подсчета ширины строки.
     * @return обект типа ArrayList<String>, содержащий строки с явно указанным разрывом
     * @author Y@Ldysse
     */
    public static ArrayList<String> SplitToLines(final String aMessage, final double aMaxLineWidth, final Font aMessageFont, final Component aComponent)
    {
        Toolkit toolkit_obj = Toolkit.getDefaultToolkit();
        Dimension screenDimension = toolkit_obj.getScreenSize();

        if (aMaxLineWidth > screenDimension.getWidth() ||
                aMaxLineWidth < 1)
        {
            throw new java.lang.NumberFormatException("Значение ширины строки не может превышать ширину по разрешению или быть меньше единицы.\n"
                    + "Текущее значение ширины строки: " + aMaxLineWidth);
        }

        double currentLineWidth = 0;
        double currentWordWidth = 0;
        StringBuilder strBuilder_obj = new StringBuilder();
        ArrayList<String> lines = new ArrayList<>();


        //Разбиваем текст на слова
        String[] words = aMessage.split(" ");

        //Добавляем пробелы - смахивает на бредятину
        for (int k = 0; k < words.length; k++)
        {
            words[k] = words[k] + " ";
        }
        //

        for (int k = 0; k < words.length; k++)
        {
            currentWordWidth = getTextWidth(words[k], aMessageFont, aComponent);

            if (currentWordWidth < aMaxLineWidth)//Если слово потенциально можно запихнуть в строку - запихиваем
            {
                //добавляем, если слово влазит
                if (currentLineWidth + currentWordWidth < aMaxLineWidth)
                {
                    currentLineWidth += currentWordWidth;
                    strBuilder_obj.append(words[k]);
                    //strBuilder_obj.append(" ");

                    if (k + 1 == words.length)
                    {
                        lines.add(strBuilder_obj.toString());
                    }
                } else //Переносим на новую строку
                {
                    currentLineWidth = 0;
                    strBuilder_obj.append("\n");
                    lines.add(strBuilder_obj.toString());
                    strBuilder_obj.delete(0, strBuilder_obj.capacity());

                    strBuilder_obj.append(words[k]);
                    currentLineWidth += getTextWidth(words[k], aMessageFont, aComponent);

                    if (k + 1 == words.length)
                    {
                        lines.add(strBuilder_obj.toString());
                    }
                }
            } else
            {
                throw new java.lang.NumberFormatException("Невозможно разбить текст на строки, если ширина слова превышает максимальный порог ширины строки.\n"
                        + "Ширина слова: " + currentWordWidth + "\tМаксимальная ширина строки: " + aMaxLineWidth);
            }
        }
        return lines;
    }

    /**
     * Возвращает самую длинную строку из коллекции строк ArrayList<String>.
     * Внимание: метод расчитан на роботу с коллекцией ArrayList<String> с соотношением: элемент коллекции - строка.
     *
     * @param aText_ArrayList Коллекция, включающая текст разбитый на строки в каждый элемент этой же коллекции
     * @return String з наибольшим количеством элементов
     * @Y@Ldysse
     */
    public static String getMostLengthString(final ArrayList<String> aText_ArrayList)
    {
        String maxLength_str = "";

        for (String currentLine_str : aText_ArrayList)
        {
            if (maxLength_str.length() < currentLine_str.length())
            {
                maxLength_str = currentLine_str;
            }
        }
        return maxLength_str;
    }

    /**
     * Возвращает самую длинную строку из коллекции строк ArrayList<String>.
     * Внимание: метод расчитан на роботу с коллекцией ArrayList<String> с соотношением: элемент коллекции - строка.
     *
     * @param aText_ArrayList Коллекция, включающая текст разбитый на строки в каждый элемент этой же коллекции
     * @return String з наибольшим количеством элементов
     * @Y@Ldysse
     */
    public static String getMostWidthString(final ArrayList<String> aText_ArrayList, final Font aFont, final Component aComponent)
    {
        String maxLength_str = "";
        double maxLengthLine = 0;

        for (String currentLine_str : aText_ArrayList)
        {
            if (maxLengthLine < getTextWidth(currentLine_str, aFont, aComponent))
            {
                maxLength_str = currentLine_str;
                maxLengthLine = getTextWidth(currentLine_str, aFont, aComponent);
            }
        }
        return maxLength_str;
    }

    /**
     * Возвращает ширину строки текста с учетом заданного шрифта
     *
     * @param aMessage     Входная строка текста
     * @param aMessageFont Шрифт, который будет применен к тексту
     * @param aComponent   Компонент, в котором будет отображен текст
     * @author Y@Ldysse
     */
    public static double getTextWidth(final String aMessage, final Font aMessageFont, final Component aComponent)
    {
        double textWidth = 0;
        FontMetrics fm_obj = aComponent.getFontMetrics(aMessageFont);

        return fm_obj.stringWidth(aMessage);
    }

    public static double getTextWidth(final String aMessage, final int countCharacters, final Font aMessageFont, final Component aComponent)
    {
        double textWidth = 0;
        FontMetrics fm_obj = aComponent.getFontMetrics(aMessageFont);

        for (int k = 0; k < countCharacters; k++)
        {
            textWidth += fm_obj.charWidth(aMessage.charAt(k));
        }

        return textWidth;
    }

    /**
     * Возвращает массив индексов вхождение определенного символа в тексте
     *
     * @param aText Входящий текст, в котором будет произведен поиск
     * @param aChar Символ, количество вхождений которого нужно подсчитать
     * @return int[] с индексами вхождения символа
     * @author Y@Ldysse
     */
    public static int[] searchCharInText(final String aText, final char aChar)
    {
        StringBuilder strBuilder_obj = new StringBuilder();

        int countElements = 0;

        for (int k = 0; k < aText.length(); k++)
        {
            if (aText.charAt(k) == aChar)
            {
                countElements++;
                strBuilder_obj.append(k);
                strBuilder_obj.append(";");
            }

        }
        String[] Indexes_str = strBuilder_obj.toString().split(";");

        int[] IndexesConcordance = new int[Indexes_str.length];

        for (int k = 0; k < Indexes_str.length; k++)
        {
            IndexesConcordance[k] = Integer.parseInt(Indexes_str[k]);
        }

        return IndexesConcordance;
    }
}
