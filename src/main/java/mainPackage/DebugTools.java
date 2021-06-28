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

import java.io.FileWriter;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * sdfjk
 * sdfjk
 * @auhor Y@L
 * */
public class DebugTools
{
    public static void createLogFile(String message)
    {
        try (FileWriter fw = new FileWriter(getJarLocation().getParent() + File.pathSeparator + "Vice_City_Helper-log.log", true))
        {
            fw.write(main_gui.getCurrentTimeString() + message);
        }
        catch (IOException ioExc)
        {
            System.out.println("Бачок потик...");
            ioExc.printStackTrace();
        }
    }

    /**
     * Возвращает путь к исполняемому файлу
     * Внимание: Работает только с обьектами своих классов.
     * Проходит тестирование!
     * author Y@L
     * */
    public static File getJarLocation()
    {
        URL locationURL;
        try
        {
            locationURL = DebugTools.class.getProtectionDomain().getCodeSource().getLocation();
            return new File(locationURL.toURI());
        }
        catch (URISyntaxException uriSynExc)
        {
            createLogFile(uriSynExc);
        }
        return null;
    }

    public static void createLogFile(Exception exc)
    {
        try (FileWriter fw = new FileWriter(new File(getJarLocation().getParent() + File.separator + "Vice_City_Helper-log.log"), true))
        {
            StackTraceElement[] ste = exc.getStackTrace();
            fw.write(exc.toString());
            for (StackTraceElement tmp : ste)
            {
                fw.write(main_gui.getCurrentTimeString() + tmp.toString() + "\n");
            }
            fw.write("\n==========================\n");
        }
        catch (IOException ioExc)
        {
            System.out.println("Бачок потик...");
            ioExc.printStackTrace();
        }
    }

    public static void printDebugMessage(String message)
    {
        System.out.println(main_gui.getCurrentTimeString() + " " + message);
    }
}
