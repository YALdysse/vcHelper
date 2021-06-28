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

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseAction extends MouseAdapter
{

    @Override
    public void mouseClicked(MouseEvent e)
    {
        DebugTools.printDebugMessage("Нажата клавиша мыши: X=" + e.getX() + "; Y=" + e.getY());

        if (e.getButton() == MouseEvent.BUTTON3)//правая клавиша мыши
        {
            DebugTools.printDebugMessage("getXY: " + e.getX() + "x" + e.getY());
        }
    }
}
