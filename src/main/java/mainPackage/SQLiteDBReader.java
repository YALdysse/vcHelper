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
import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.sql.*;


public class SQLiteDBReader
{
    private java.sql.Connection db_Connection = null;
    private java.sql.Statement db_Statement = null;
    private java.sql.ResultSet resultData = null;


    public SQLiteDBReader(final String pathToDB) throws SQLException, FileNotFoundException
    {
        File db_file = new File(pathToDB);
        if(!db_file.isFile())
        {
            throw new FileNotFoundException("Файл базы данных не был обнаружен.\nПуть: " + db_file.getAbsolutePath());
        }

        DebugTools.printDebugMessage("Путь к БД: " + pathToDB.toString());
        DriverManager.registerDriver(new org.sqlite.JDBC());
        db_Connection = DriverManager.getConnection("jdbc:sqlite:" + pathToDB);

        db_Statement = db_Connection.createStatement();
        DebugTools.printDebugMessage("Соединение с БД установлено.");
    }

    public ResultSet executeQuery(final String query) throws SQLException
    {
        resultData = db_Statement.executeQuery(query);
        return resultData;
    }

    public Statement getStatement()
    {
        return db_Statement;
    }

    public ResultSet getResultSet()
    {
        return resultData;
    }

    public void closeConnection() throws SQLException
    {
        db_Connection.close();
    }


}
