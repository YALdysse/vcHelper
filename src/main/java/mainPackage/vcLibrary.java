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

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.text.*;
import javax.swing.tree.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.Buffer;
import java.sql.*;

/**
 * @author Y@Ldysse
 */
public class vcLibrary extends javax.swing.JFrame
{
    private SQLiteDBReader dbReader;
    private DefaultMutableTreeNode library_treeNode;
    private DefaultMutableTreeNode[] categories_treeNode;
    private DefaultMutableTreeNode[] characters_treeNode;
    private DefaultTreeModel library_treeModel;

    private Font titleFont = null;

    public vcLibrary()
    {
        try
        {
            dbReader = new SQLiteDBReader(DebugTools.getJarLocation().getParent() + "/vcLibrary_Russian.sqlite");
            //java.sql.
        }
        catch (SQLException sqlExc)
        {
            DebugTools.createLogFile(sqlExc);
            setVisible(false);
            dispose();
            return;
        }
        catch (FileNotFoundException fileNFExc)
        {
            JOptionPane.showMessageDialog(null, "База данных не была обнаружена. Пожалуйста, разместите файл базы данных в каталоге с программой.\nПуть: " +
                    DebugTools.getJarLocation().getParent() + "/vcLibrary_Russian.sqlite", "База данных не найдена", JOptionPane.ERROR_MESSAGE, null);
            DebugTools.createLogFile(fileNFExc);
            setVisible(false);
            dispose();
            return;
        }
//        catch(MalformedURLException malURLExc)
//        {
//            DebugTools.printDebugMessage("Некорректный URL-путь к БД.");
//            DebugTools.createLogFile(malURLExc);
//            return;
//        }

        loadCategories();
        initComponents();
    }


    private void initComponents()
    {
        main_JPanel = new javax.swing.JPanel();
        description_jScrollPane = new javax.swing.JScrollPane();
        description_jTextPane = new javax.swing.JTextPane();
        titleOfArticle_jLabel = new ControlledLabel();
        image_psevdoImageBox = new psevdoImageBox();
        library_jScrollPane = new javax.swing.JScrollPane();

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Библиотека Vice City");
        setMinimumSize(new Dimension(600, 450));


        titleOfArticle_jLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titleOfArticle_jLabel.setText("Title");
        titleOfArticle_jLabel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        try
        {
            descriptionFont = YALtools.createFont("20648.ttf");
            titleOfArticle_jLabel.setFont(YALtools.createFont("Casual Contact MF.ttf").deriveFont(Font.ITALIC, 28.0f));
            titleOfArticle_jLabel.setForeground(new Color(251, 80, 226));
        }
        catch (IOException ioExc)
        {
            DebugTools.printDebugMessage("Ошибка ввода-вывода.\n" + ioExc.toString());
            DebugTools.createLogFile(ioExc);
        }
        catch (FontFormatException fontFormExc)
        {
            DebugTools.printDebugMessage("Ошибка формата шрифта.\n" + fontFormExc.toString());
            DebugTools.createLogFile(fontFormExc);
        }

        description_jTextPane.setEditable(false);
        description_jTextPane.setFont(descriptionFont.deriveFont(Font.PLAIN, 14.0f));
        description_jTextPane.setBackground(new Color(238, 238, 238));
        description_jTextPane.setForeground(Color.BLACK);

        description_jScrollPane.setViewportView(description_jTextPane);

        //image_psevdoImageBox.setText("ImageOfArticle");

        javax.swing.GroupLayout main_JPanelLayout = new javax.swing.GroupLayout(main_JPanel);
        main_JPanel.setLayout(main_JPanelLayout);
        main_JPanelLayout.setHorizontalGroup(
                main_JPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(description_jScrollPane)
                        .addComponent(titleOfArticle_jLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(main_JPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(image_psevdoImageBox, javax.swing.GroupLayout.DEFAULT_SIZE, 496, Short.MAX_VALUE)
                                .addContainerGap())
        );
        main_JPanelLayout.setVerticalGroup(
                main_JPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, main_JPanelLayout.createSequentialGroup()
                                .addComponent(titleOfArticle_jLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(image_psevdoImageBox, javax.swing.GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(description_jScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        library_jTree = new JTree(library_treeModel);
        //library_jTree.setModel(library_treeModel);
        library_jTree.setVisible(true);
        library_jScrollPane.setViewportView(library_jTree);
        // library_jTree.setBackground(new Color(238, 238, 238));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                //.addComponent(library_jScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(library_jScrollPane, (int) ((double) getSize().getWidth() * 0.25), (int) ((double) getSize().getWidth() * 0.3), (int) ((double) getSize().getWidth() * 0.35))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(main_JPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(main_JPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(library_jScrollPane))
                                .addContainerGap())
        );


        library_jTree.addTreeSelectionListener(new TreeSelectionListener()
        {
            @Override
            public void valueChanged(TreeSelectionEvent e)
            {
                actionTreeSelection(e);
            }
        });

        addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowClosing(WindowEvent e)
            {
                try
                {
                    dbReader.closeConnection();

                }
                catch (SQLException sqlException)
                {
                    DebugTools.printDebugMessage("Ошибка при закрытии соединения с БД.\n" + sqlException.toString());
                    DebugTools.createLogFile(sqlException);
                }
            }

            @Override
            public void windowStateChanged(WindowEvent e)
            {
                super.windowStateChanged(e);
                DebugTools.printDebugMessage("Размер фрейма библиотеки: " + getSize().getWidth() + "x" + getSize().getHeight());
            }
        });

        pack();
        setVisible(true);

    }

    private void actionTreeSelection(TreeSelectionEvent e)
    {
        //library_jTree.setSize((int)(getSize().getWidth()*0.3), (int)library_jTree.getSize().getHeight());

        titleOfArticle_jLabel.setFont(titleOfArticle_jLabel.getFont().deriveFont(Font.ITALIC, 28.0f));
        titleOfArticle_jLabel.update(titleOfArticle_jLabel.getGraphics());

        image_psevdoImageBox.addImage(null);

        TreePath currentPath = e.getPath();
        TreePath parent = e.getPath();
        String currentParentNode_str = "";
        String currentNodeName_str = e.getPath().getLastPathComponent().toString();
        StringBuilder strBuilder_obj = new StringBuilder();

        //=================== Описание категорий
        parent = parent.getParentPath();

        if (parent != null)
        {
            currentParentNode_str = parent.getLastPathComponent().toString();
            DebugTools.printDebugMessage("Родитель этого узла: '" + currentParentNode_str + "'");
        } else
        {
            parent = currentPath;
            DebugTools.printDebugMessage("Это корневой уровень. Количество потомков: " + parent.getPathCount());
        }

        //ищем, к какой категории относится
        try
        {
            //Если отмечена категория
            dbReader.executeQuery("SELECT Categories.category_id, Categories.category_name, Categories.category_description " +
                    "FROM Categories WHERE category_name LIKE '%" + currentNodeName_str + "%'");

            //Категории
            DebugTools.printDebugMessage("Имя выбранного узла: " + currentNodeName_str);

            if (currentNodeName_str.equals(dbReader.getResultSet().getString("category_name")))
            {
                DebugTools.printDebugMessage("Выбрана категория: " + currentNodeName_str);
                titleOfArticle_jLabel.setText(dbReader.getResultSet().getString("category_name"));
                description_jTextPane.setText(dbReader.getResultSet().getString("category_description"));
                return;
            }
        }
        catch (SQLException sqlException)
        {
            DebugTools.printDebugMessage("Возникла ошибка: " + sqlException.toString());

            DebugTools.printDebugMessage("Совпадение по родителю текущего уровня не обнаружено. Текущий родитель: " + currentParentNode_str);
            //continue;
        }
        //====================

        parent = e.getPath();

        //2 уровня дерева
        for (int k = 0; k < 2; k++)
        {
            parent = parent.getParentPath();

            if (parent != null)
            {
                currentParentNode_str = parent.getLastPathComponent().toString();
                DebugTools.printDebugMessage("Родитель этого узла: '" + currentParentNode_str + "'");
            } else
            {
                parent = currentPath;
                DebugTools.printDebugMessage("Это корневой уровень. Количество потомков: " + parent.getPathCount());
            }
            currentNodeName_str = e.getPath().getLastPathComponent().toString();

            try
            {
                dbReader.executeQuery("SELECT Categories.category_id, Categories.category_name " +
                        "FROM Categories WHERE category_name LIKE '" + currentParentNode_str + "'");


                int category_id = dbReader.getResultSet().getInt("category_id");

                try
                {
                    //Банды
                    if (dbReader.getResultSet().getInt("category_id") == 3)
                    {
                        //Получаем название выбранной группировки
                        DebugTools.printDebugMessage("Выбранная банда: " + currentNodeName_str);
                        //получаем данные
                        dbReader.executeQuery("SELECT Bands.band_id, Bands.band_name, Bands.band_description, Bands.band_image_name, Categories.category_id, Categories.category_name " +
                                "FROM join_bands_categories JOIN Bands ON join_bands_categories.band_id=Bands.band_id " +
                                "JOIN Categories ON join_bands_categories.category_id=Categories.category_id " +
                                "WHERE Bands.band_name LIKE '" + currentNodeName_str + "'");
                        titleOfArticle_jLabel.setText(dbReader.getResultSet().getString("band_name"));

                        try
                        {
                            BufferedImage bandIcon = ImageIO.read(this.getClass().getClassLoader().getResource("Images/Bands/" + dbReader.getResultSet().getString("band_image_name") + ".jpg"));
                            image_psevdoImageBox.addImage(bandIcon);
                            image_psevdoImageBox.setImageFitStyle(psevdoImageBox.FIT_IMAGE_TO_COMPONENT, true);
                            image_psevdoImageBox.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(126, 153, 170), 6),
                                    BorderFactory.createEmptyBorder(2, 2, 2, 2)));
                            //image_psevdoImageBox.setLocation(500,500);
                        }
                        catch (IOException ioExc)
                        {
                            DebugTools.printDebugMessage("Ошибка загрузки изображения: " + titleOfArticle_jLabel.getText());
                            DebugTools.createLogFile(ioExc);
                        }
                        catch (IllegalArgumentException iArgExc)
                        {
                            JOptionPane.showMessageDialog(this, "Изображение " + titleOfArticle_jLabel.getText() + " не было загружено.", "Ошибка загрузки изображения", JOptionPane.ERROR_MESSAGE, null);
                            DebugTools.printDebugMessage("Ошибка при загрузке изображения: " + titleOfArticle_jLabel.getText() + " из ресурсов.");
                            DebugTools.createLogFile(iArgExc);
                            image_psevdoImageBox.addImage(null);
                        }

                        description_jTextPane.setText(dbReader.getResultSet().getString("band_description"));
                        description_jTextPane.setVisible(true);
                        break;
                    }

                    //Персонажи
                    if (dbReader.getResultSet().getInt("category_id") == 1)
                    {
                        //Получаем название выбранной группировки
                        DebugTools.printDebugMessage("Выбраный персонаж: " + currentNodeName_str);
                        String CharacterName = currentNodeName_str.substring(0, currentNodeName_str.indexOf(' '));
                        //получаем данные
                        dbReader.executeQuery("SELECT Characters.character_id, Characters.character_name, Characters.character_surname, Characters.character_description, Characters.character_image_name, " +
                                "Categories.category_id, Categories.category_name " +
                                "FROM join_characters_categories " +
                                "JOIN Characters ON join_characters_categories.character_id=Characters.character_id " +
                                "JOIN Categories ON join_characters_categories.category_id=Categories.category_id " +
                                "WHERE Characters.character_name LIKE '%" + CharacterName + "%'");

                        titleOfArticle_jLabel.setText(currentNodeName_str);

                        try
                        {
                            BufferedImage bandIcon = ImageIO.read(this.getClass().getClassLoader().getResource("Images/Characters/" + dbReader.getResultSet().getString("character_image_name")));
                            image_psevdoImageBox.addImage(bandIcon);
                            image_psevdoImageBox.setImageFitStyle(psevdoImageBox.FIT_IMAGE_TO_COMPONENT, true);
                            image_psevdoImageBox.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(126, 153, 170), 6),
                                    BorderFactory.createEmptyBorder(2, 2, 2, 2)));
                            //image_psevdoImageBox.setLocation(500,500);
                        }
                        catch (IOException ioExc)
                        {
                            DebugTools.printDebugMessage("Ошибка загрузки изображения: " + titleOfArticle_jLabel.getText());
                            DebugTools.createLogFile(ioExc);
                        }
                        catch (IllegalArgumentException iArgExc)
                        {
                            JOptionPane.showMessageDialog(this, "Изображение " + titleOfArticle_jLabel.getText() + " не было загружено.", "Ошибка загрузки изображения", JOptionPane.ERROR_MESSAGE, null);
                            DebugTools.printDebugMessage("Ошибка при загрузке изображения: " + titleOfArticle_jLabel.getText() + " из ресурсов.");
                            DebugTools.createLogFile(iArgExc);
                            image_psevdoImageBox.addImage(null);
                        }

                        description_jTextPane.setText(dbReader.getResultSet().getString("character_description"));
                        description_jTextPane.setVisible(true);
                        break;
                    }

                    //Транспорт
                    if (dbReader.getResultSet().getInt("category_id") == 2)
                    {
                        DebugTools.printDebugMessage("Категория : Транспорт");
                        String currentVehicleCategory_str = currentPath.getParentPath().getLastPathComponent().toString();
                        DebugTools.printDebugMessage("Родитель для узла " + e.getPath().getLastPathComponent().toString() + " - " + currentVehicleCategory_str);

                        //Ищем описание категории транспорта если она выбрана
                        strBuilder_obj.delete(0, strBuilder_obj.capacity());
                        strBuilder_obj.append("SELECT VehicleCategories.VehicleCategory_id, VehicleCategories.VehicleCategory_name ");
                        strBuilder_obj.append("FROM join_VehicleCategories_Categories ");
                        strBuilder_obj.append("JOIN Categories ON join_VehicleCategories_Categories.category_id=Categories.category_id ");
                        strBuilder_obj.append("JOIN VehicleCategories ON join_VehicleCategories_Categories.VehicleCategory_id = VehicleCategories.VehicleCategory_id ");
                        strBuilder_obj.append("WHERE VehicleCategories.VehicleCategory_name LIKE '%");
                        strBuilder_obj.append(currentNodeName_str);
                        strBuilder_obj.append("%'");

                        dbReader.executeQuery(strBuilder_obj.toString());
                        if (dbReader.getResultSet().next())
                        {
                            //Ха, а описание категории транспорта в БД нету!!!
                            titleOfArticle_jLabel.setText(dbReader.getResultSet().getString("VehicleCategory_name"));
                            break;
                        } else
                        {
                            DebugTools.printDebugMessage("Описание категории транспорта не может быть загружено, т.к. категория не выбрана.");
                        }
                        //========================================

                        //====================== легковые автомобили
                        //Получаем категорию выделенного транспортного средства
                        //TreePath tmpPParentNode = parent.getParentPath();
                        //String tmpPParentCurrentNode_str = tmpPParentNode.getLastPathComponent().toString();
                        dbReader.executeQuery("SELECT VehicleCategories.VehicleCategory_id, VehicleCategories.VehicleCategory_name " +
                                "FROM VehicleCategories " +
                                "WHERE VehicleCategories.VehicleCategory_name LIKE '%" + currentVehicleCategory_str + "%'");
                        int subCategoryID = dbReader.getResultSet().getInt("VehicleCategory_id");

                        DebugTools.printDebugMessage("Идентификатор категории: " + subCategoryID);

                        //Получаем транспортные cредство
                        dbReader.executeQuery("SELECT Cars.car_id, Cars.car_name, Cars.car_speed, Cars.car_description, Cars.car_weight, Cars.car_image_name, VehicleCategories.VehicleCategory_id, VehicleCategories.VehicleCategory_name, " +
                                "Cars.car_acceleration, Cars.car_braking, Cars.car_feature " +
                                "FROM Cars JOIN VehicleCategories ON Cars.VehicleCategory_id=VehicleCategories.VehicleCategory_id " +
                                "WHERE VehicleCategory_name LIKE '%" + currentVehicleCategory_str + "%' AND Cars.car_name LIKE '%" + currentNodeName_str + "%'");

                        ResultSet tmpRS = dbReader.getResultSet();
                        titleOfArticle_jLabel.setText(tmpRS.getString("car_name"));

                        try
                        {
                            BufferedImage weaponIcon = ImageIO.read(this.getClass().getClassLoader().getResource("Images/Vehicles/" + tmpRS.getString("car_image_name") + ".jpg"));
                            image_psevdoImageBox.addImage(weaponIcon);
                            image_psevdoImageBox.setImageFitStyle(psevdoImageBox.FIT_IMAGE_TO_COMPONENT, true);
                            //image_psevdoImageBox.setLocation(500,500);
                        }
                        catch (IOException ioExc)
                        {
                            DebugTools.printDebugMessage("Ошибка загрузки изображения: " + titleOfArticle_jLabel.getText());
                            DebugTools.createLogFile(ioExc);
                        }
                        catch (IllegalArgumentException iArgExc)
                        {
                            JOptionPane.showMessageDialog(this, "Изображение " + titleOfArticle_jLabel.getText() + " не было загружено.", "Ошибка загрузки изображения", JOptionPane.ERROR_MESSAGE, null);
                            DebugTools.printDebugMessage("Ошибка при загрузке изображения: " + titleOfArticle_jLabel.getText() + " из ресурсов.");
                            DebugTools.createLogFile(iArgExc);
                            image_psevdoImageBox.addImage(null);
                        }

                        strBuilder_obj.delete(0, strBuilder_obj.capacity());
                        strBuilder_obj.append(tmpRS.getString("car_description"));
                        strBuilder_obj.append("\n\nХарактеристики:\n\t▲ Максимальная скорость: ");
                        strBuilder_obj.append(tmpRS.getString("car_speed"));
                        strBuilder_obj.append("\n\tm Масса: ");
                        strBuilder_obj.append(tmpRS.getString("car_weight"));
                        strBuilder_obj.append(" кг");
                        strBuilder_obj.append("\n\t»»» Ускорение: ");
                        strBuilder_obj.append(tmpRS.getString("car_acceleration"));
                        strBuilder_obj.append("\n\t««« Торможение: ");
                        strBuilder_obj.append(tmpRS.getString("car_braking"));

                        boolean isUniqueVehicle = false;
                        if (!tmpRS.getString("car_feature").equals("---"))
                        {
                            strBuilder_obj.append("\n\t★ Особенности: ");
                            strBuilder_obj.append(tmpRS.getString("car_feature"));
                            isUniqueVehicle = true;
                        }

                        description_jTextPane.setText(strBuilder_obj.toString());
                        description_jTextPane.setVisible(true);
                        addStyleToVehicleDescription(isUniqueVehicle);
                        break;
                    }

                    //Вооружение
                    if (dbReader.getResultSet().getInt("category_id") == 4)
                    {
                        DebugTools.printDebugMessage("Категория: Вооружение");
                        String currentWeaponCategory_str = currentPath.getParentPath().getLastPathComponent().toString();
                        DebugTools.printDebugMessage("Родитель для узла " + e.getPath().getLastPathComponent().toString() + " - " + currentWeaponCategory_str);

                        //Если выделана категория оружия
                        try
                        {
                            dbReader.executeQuery("SELECT WeaponCategories.weaponCategory_id, WeaponCategories.weaponCategory_name, WeaponCategories.weaponCategory_description " +
                                    "FROM WeaponCategories " +
                                    "WHERE WeaponCategories.weaponCategory_name LIKE '%" + currentNodeName_str + "%'");
                            if (currentNodeName_str.equals(dbReader.getResultSet().getString("weaponCategory_name")))
                            {
                                titleOfArticle_jLabel.setText(dbReader.getResultSet().getString("weaponCategory_name"));
                                description_jTextPane.setText(dbReader.getResultSet().getString("weaponCategory_description"));
                                break;
                            }
                        }
                        catch (SQLException sqlExc)
                        {

                        }
                        //======================
                        strBuilder_obj.delete(0, strBuilder_obj.capacity());
                        strBuilder_obj.append("SELECT WeaponCategories.weaponCategory_id, WeaponCategories.weaponCategory_name ");
                        strBuilder_obj.append("FROM WeaponCategories ");
                        strBuilder_obj.append("WHERE WeaponCategories.weaponCategory_name like '%");
                        strBuilder_obj.append(currentWeaponCategory_str);
                        strBuilder_obj.append("%'");

                        //Получаем категорию выделенного транспортного средства
                        dbReader.executeQuery(strBuilder_obj.toString());
                        int subCategoryID = dbReader.getResultSet().getInt("weaponCategory_id");

                        DebugTools.printDebugMessage("Идентификатор категории: " + subCategoryID);

                        //Получаем экземпляр вооружения
                        strBuilder_obj.delete(0, strBuilder_obj.capacity());
                        strBuilder_obj.append("SELECT Weapons.weapon_id, Weapons.weapon_name, Weapons.weapon_description, Weapons.weapon_price, Weapons.weapon_range, ");
                        strBuilder_obj.append("Weapons.weapon_damage_percent,  Weapons.weapon_icon_name, Weapons.weaponCategory_id, WeaponCategories.weaponCategory_name ");
                        strBuilder_obj.append("FROM Weapons ");
                        strBuilder_obj.append("JOIN WeaponCategories ON Weapons.weaponCategory_id=WeaponCategories.weaponCategory_id ");
                        strBuilder_obj.append("WHERE WeaponCategories.weaponCategory_name LIKE '%");
                        strBuilder_obj.append(currentWeaponCategory_str);
                        strBuilder_obj.append("%' AND Weapons.weapon_name LIKE '");
                        strBuilder_obj.append(currentNodeName_str);
                        strBuilder_obj.append("'");

                        dbReader.executeQuery(strBuilder_obj.toString());

                        ResultSet tmpRS = dbReader.getResultSet();
                        titleOfArticle_jLabel.setText(tmpRS.getString("weapon_name"));

                        try
                        {
                            BufferedImage weaponIcon = ImageIO.read(this.getClass().getClassLoader().getResource("Images/WeaponIcons/" + tmpRS.getString("weapon_icon_name")));
                            image_psevdoImageBox.addImage(weaponIcon);
                            image_psevdoImageBox.setImageFitStyle(psevdoImageBox.FIT_IMAGE_TO_COMPONENT, true);
//                            image_psevdoImageBox.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(126, 153, 170), 6),
//                                    BorderFactory.createEmptyBorder(2, 2, 2, 2)));
                        }
                        catch (IOException ioExc)
                        {
                            DebugTools.printDebugMessage("Ошибка загрузки изображения: " + titleOfArticle_jLabel.getText());
                            DebugTools.createLogFile(ioExc);
                        }
                        catch (IllegalArgumentException iArgExc)
                        {
                            JOptionPane.showMessageDialog(this, "Изображение " + titleOfArticle_jLabel.getText() + " не было загружено.", "Ошибка загрузки изображения", JOptionPane.ERROR_MESSAGE, null);
                            DebugTools.printDebugMessage("Ошибка при загрузке изображения: " + titleOfArticle_jLabel.getText() + " из ресурсов.");
                            DebugTools.createLogFile(iArgExc);
                            image_psevdoImageBox.addImage(null);
                        }

                        strBuilder_obj.delete(0, strBuilder_obj.capacity());
                        strBuilder_obj.append(tmpRS.getString("weapon_description"));
                        strBuilder_obj.append("\n\nХарактеристики:\n\t◎ Повреждение: ");
                        strBuilder_obj.append(tmpRS.getString("weapon_damage_percent"));
                        strBuilder_obj.append("%\n\t↔ Дальность: ");
                        strBuilder_obj.append(tmpRS.getString("weapon_range"));
                        strBuilder_obj.append("\n\t\uD83D\uDCB5 Стоимость: ");
                        strBuilder_obj.append(tmpRS.getString("weapon_price") + "\uD83D\uDCB2");
                        description_jTextPane.setText(strBuilder_obj.toString());
                        addStyleToWeaponDescription();
                        description_jTextPane.setVisible(true);
                        break;
                    }

                    //Миссии
                    if (dbReader.getResultSet().getInt("category_id") == 5)
                    {
                        DebugTools.printDebugMessage("Категория: Миссии");
                        String currentMissionCategory_str = currentPath.getParentPath().getLastPathComponent().toString();
                        DebugTools.printDebugMessage("Родитель для узла " + e.getPath().getLastPathComponent().toString() + " - " + currentMissionCategory_str);

                        //Если выбрана категория миссии
                        dbReader.executeQuery("SELECT Missions.mission_id, Missions.mission_name, Missions.mission_description, Missions.mission_reward, Missions.mission_image_name, Missions.mission_employer_id, " +
                                "Missions.mission_category_id, MissionCategories.missionCategory_name, MissionCategories.missionCategory_description " +
                                "FROM Missions " +
                                "Join MissionCategories ON Missions.mission_category_id=MissionCategories.missionCategory_id " +
                                "WHERE MissionCategories.missionCategory_name LIKE '%" + currentNodeName_str + "%'");
                        try
                        {
                            if (dbReader.getResultSet().getString("missionCategory_name") != null)
                            {
                                titleOfArticle_jLabel.setText(dbReader.getResultSet().getString("missionCategory_name"));

                                strBuilder_obj.delete(0, strBuilder_obj.capacity());
                                strBuilder_obj.append(dbReader.getResultSet().getString("missionCategory_description"));
                                description_jTextPane.setText(strBuilder_obj.toString());
                                description_jTextPane.setVisible(true);
                                //addStyleToMissionDescription();
                                break;
                            }
                        }
                        catch (SQLException sqlExc)
                        {

                        }

                        //Для недвижимостей с одной миссией
                        dbReader.executeQuery("SELECT Missions.mission_id, Missions.mission_name, Missions.mission_description, Missions.mission_reward, Missions.mission_image_name, Missions.mission_employer_id, " +
                                "Missions.mission_category_id, MissionCategories.missionCategory_name, MissionCategories.missionCategory_description " +
                                "FROM Missions " +
                                "Join MissionCategories ON Missions.mission_category_id=MissionCategories.missionCategory_id " +
                                "WHERE Missions.mission_name LIKE '%" + currentNodeName_str + "%'");

                        description_jTextPane.setText("");
                        if (currentNodeName_str.equals(dbReader.getResultSet().getString("mission_name")))
                        {
                            titleOfArticle_jLabel.setText(dbReader.getResultSet().getString("mission_name"));
                            description_jTextPane.setText(dbReader.getResultSet().getString("mission_description") + "\n\n\tНаграда: " + dbReader.getResultSet().getString("mission_reward"));
                            description_jTextPane.setVisible(true);
                            addStyleToMissionDescription();

                            String imageName = dbReader.getResultSet().getString("mission_image_name");
                            if (imageName != null)
                            {
                                try
                                {
                                    image_psevdoImageBox.addImage(ImageIO.read(this.getClass().getClassLoader().getResource("Images/Missions/" + imageName + ".jpg")));
                                    image_psevdoImageBox.setImageFitStyle(psevdoImageBox.FIT_IMAGE_TO_COMPONENT, true);
                                }
                                catch (IOException ioExc
                                )
                                {
                                    DebugTools.printDebugMessage("Возникла ошибка при загрузке изображения миссии:" + currentNodeName_str);
                                }
                            }
                            break;
                        }
                    }

                    //Сведение
                    if (dbReader.getResultSet().getInt("category_id") == 6)
                    {
                        //Получаем название
                        DebugTools.printDebugMessage("Выбранная : " + currentNodeName_str);
                        //получаем данные
                        dbReader.executeQuery("SELECT AboutGame.aboutGame_id, AboutGame.aboutGame_name, AboutGame.aboutGame_description, Categories.category_id, Categories.category_name, Categories.category_description " +
                                "FROM join_AboutGame_Categories " +
                                "JOIN AboutGame ON join_AboutGame_Categories.aboutGame_id=AboutGame.aboutGame_id " +
                                "JOIN Categories ON join_AboutGame_Categories.category_id=Categories.category_id " +
                                "WHERE AboutGame.aboutGame_name LIKE '%" + currentNodeName_str + "%'");
                        titleOfArticle_jLabel.setText(dbReader.getResultSet().getString("aboutGame_name"));

//                        try
//                        {
//                            BufferedImage bandIcon = ImageIO.read(this.getClass().getClassLoader().getResource("Images/Bands/" + dbReader.getResultSet().getString("band_image_name") + ".jpg"));
//                            image_psevdoImageBox.addImage(bandIcon);
//                            image_psevdoImageBox.setImageFitStyle(psevdoImageBox.FIT_IMAGE_TO_COMPONENT, true);
//                            image_psevdoImageBox.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(126, 153, 170), 6),
//                                    BorderFactory.createEmptyBorder(2, 2, 2, 2)));
//                            //image_psevdoImageBox.setLocation(500,500);
//                        }
//                        catch (IOException ioExc)
//                        {
//                            DebugTools.printDebugMessage("Ошибка загрузки изображения: " + titleOfArticle_jLabel.getText());
//                            DebugTools.createLogFile(ioExc);
//                        }
//                        catch (IllegalArgumentException iArgExc)
//                        {
//                            JOptionPane.showMessageDialog(this, "Изображение " + titleOfArticle_jLabel.getText() + " не было загружено.", "Ошибка загрузки изображения", JOptionPane.ERROR_MESSAGE, null);
//                            DebugTools.printDebugMessage("Ошибка при загрузке изображения: " + titleOfArticle_jLabel.getText() + " из ресурсов.");
//                            DebugTools.createLogFile(iArgExc);
//                            image_psevdoImageBox.addImage(null);
//                        }
                        description_jTextPane.setText(dbReader.getResultSet().getString("aboutGame_description"));
                        description_jTextPane.setVisible(true);

                        if (titleOfArticle_jLabel.getText().equals("Полное прохождение"))
                        {
                            addStyleToCompletlyWalkthrough();
                        }
                        if (titleOfArticle_jLabel.getText().equals("Чит-коды для PC (Windows и MacOS)"))
                        {
                            addStyleToCheats();
                        }
                        break;
                    }


                    description_jTextPane.setVisible(true);
                    break;
                }
                catch (SQLException sqlExc)
                {
                    DebugTools.printDebugMessage("Возникла ошибка при выполнении запроса к БД.\n" + sqlExc.toString());
                    DebugTools.createLogFile(sqlExc);
                    JOptionPane.showMessageDialog(this, "Ошибка выполнения запроса к БД. Log-файл сохранен в каталог с программой ", "", JOptionPane.ERROR_MESSAGE, null);
                }
            }
            catch (SQLException sqlException)
            {
                DebugTools.printDebugMessage("Совпадение по родителю текущего уровня не обнаружено. Текущий родитель: " + currentParentNode_str);
                continue;
            }
            break;
        }
        description_jTextPane.setCaretPosition(0);
    }

    private void addStyleToVehicleDescription(final boolean isUniqueVehicle)
    {
        Color backgroud_Color = new Color(255, 255, 255);//Цвет фона
        Color foreground_Color = new Color(0, 0, 0);//Цвет основного текста
        Color parametrsName_Color = new Color(107, 8, 72);//Цвет имен параметров характеристик
        Color values_Color = new Color(41, 121, 178);//Цвет значений характеристик

        //description_jTextPane.setBackground(backgroud_Color);
        //description_jTextPane.setForeground(foreground_Color);

        SimpleAttributeSet baseline = new SimpleAttributeSet();
        StyleConstants.setForeground(baseline, new Color(255, 22, 93));
        StyleConstants.setFontSize(baseline, description_jTextPane.getFont().getSize() + 2);
        description_jTextPane.getStyledDocument().setCharacterAttributes(description_jTextPane.getText().indexOf("Характеристики"), "Характеристики:".length(), baseline, true);

        baseline = new SimpleAttributeSet();
        StyleConstants.setForeground(baseline, parametrsName_Color);
        description_jTextPane.getStyledDocument().setCharacterAttributes(description_jTextPane.getText().indexOf("Максимальная скорость:"), "Максимальная скорость:".length(), baseline, true);
        description_jTextPane.getStyledDocument().setCharacterAttributes(description_jTextPane.getText().indexOf("Масса:"), "Масса:".length(), baseline, true);
        description_jTextPane.getStyledDocument().setCharacterAttributes(description_jTextPane.getText().indexOf("Ускорение:"), "Ускорение:".length(), baseline, true);
        description_jTextPane.getStyledDocument().setCharacterAttributes(description_jTextPane.getText().indexOf("Торможение:"), "Торможение:".length(), baseline, true);

        StyleConstants.setForeground(baseline, values_Color);
        int startIndex = description_jTextPane.getText().indexOf("Максимальная скорость:") + "Максимальная скорость:".length() + 1;
        int length = description_jTextPane.getText().indexOf("m Масса: ") - 2 - startIndex;
        description_jTextPane.getStyledDocument().setCharacterAttributes(startIndex, length, baseline, true);

        startIndex = description_jTextPane.getText().indexOf("m Масса: ") + "m Масса: ".length();
        length = description_jTextPane.getText().indexOf("кг") - startIndex - 1;
        description_jTextPane.getStyledDocument().setCharacterAttributes(startIndex, length, baseline, true);

        startIndex = description_jTextPane.getText().indexOf("»»» Ускорение: ") + "»»» Ускорение: ".length();
        length = description_jTextPane.getText().indexOf("««« Торможение: ") - 2 - startIndex;
        description_jTextPane.getStyledDocument().setCharacterAttributes(startIndex, length, baseline, true);

        if (isUniqueVehicle)
        {
            //цвет для значения торможения
            startIndex = description_jTextPane.getText().indexOf("««« Торможение: ") + "««« Торможение: ".length();
            length = description_jTextPane.getText().indexOf("★ Особенности:") - 2 - startIndex;
            description_jTextPane.getStyledDocument().setCharacterAttributes(startIndex, length, baseline, true);

            baseline = new SimpleAttributeSet();
            StyleConstants.setForeground(baseline, parametrsName_Color);
            startIndex = description_jTextPane.getText().indexOf("Особенности:");
            length = "Особенности:".length();
            DebugTools.printDebugMessage("Индекс начала: " + startIndex + " [" + description_jTextPane.getText().charAt(startIndex) + "]\n"
                    + "Длина текста под стиль: " + length + ". Итоговая строка: [" + description_jTextPane.getText().substring(startIndex, startIndex + length) + "]");
            description_jTextPane.getStyledDocument().setCharacterAttributes(startIndex, length, baseline, true);
        } else
        {
            startIndex = description_jTextPane.getText().indexOf("««« Торможение: ") + "««« Торможение: ".length();
            length = description_jTextPane.getText().length() - startIndex;
            DebugTools.printDebugMessage("Индекс начала: " + startIndex + " [" + description_jTextPane.getText().charAt(startIndex) + "]\n"
                    + "Длина текста под стиль: " + length + ". Итоговая строка: [" + description_jTextPane.getText().substring(startIndex, startIndex + length) + "]");
            description_jTextPane.getStyledDocument().setCharacterAttributes(startIndex, length, baseline, true);
        }

        //====================
    }

    private void addStyleToMissionDescription()
    {
        //Color backgroud_Color = new Color(255, 255, 255);//Цвет фона
        //Color foreground_Color = new Color(0, 0, 0);//Цвет основного текста
        Color parametrsName_Color = new Color(107, 8, 72);//Цвет имен параметров характеристик
        Color values_Color = new Color(41, 121, 178);//Цвет значений характеристик

        SimpleAttributeSet baseline = new SimpleAttributeSet();
        StyleConstants.setForeground(baseline, new Color(255, 22, 93));
        StyleConstants.setFontSize(baseline, description_jTextPane.getFont().getSize() + 2);

        //================сдесь ошибка
        int startIndex = description_jTextPane.getText().indexOf("Награда:");
        int length = "Награда:".length();
        DebugTools.printDebugMessage("Индекс начала: " + startIndex + " [" + description_jTextPane.getText().charAt(startIndex) + "]\n"
                + "Длина текста под стиль: " + length + ". Итоговая строка: [" + description_jTextPane.getText().substring(startIndex, startIndex + length) + "]");
        description_jTextPane.getStyledDocument().setCharacterAttributes(startIndex, length, baseline, true);
        //======================

        baseline = new SimpleAttributeSet();
        StyleConstants.setForeground(baseline, values_Color);
        startIndex = description_jTextPane.getText().indexOf("Награда:") + "Награда:".length() + 1;
        length = description_jTextPane.getText().length() - startIndex;
        description_jTextPane.getStyledDocument().setCharacterAttributes(startIndex, length, baseline, true);
    }

    private void addStyleToWeaponDescription()
    {
        //Color backgroud_Color = new Color(255, 255, 255);//Цвет фона
        //Color foreground_Color = new Color(0, 0, 0);//Цвет основного текста
        Color parametrsName_Color = new Color(107, 8, 72);//Цвет имен параметров характеристик
        Color values_Color = new Color(41, 121, 178);//Цвет значений характеристик

        SimpleAttributeSet baseline = new SimpleAttributeSet();
        StyleConstants.setForeground(baseline, new Color(255, 22, 93));
        StyleConstants.setFontSize(baseline, description_jTextPane.getFont().getSize() + 2);
        description_jTextPane.getStyledDocument().setCharacterAttributes(description_jTextPane.getText().indexOf("Характеристики:"), "Характеристики:".length(), baseline, true);

        baseline = new SimpleAttributeSet();
        StyleConstants.setForeground(baseline, parametrsName_Color);
        description_jTextPane.getStyledDocument().setCharacterAttributes(description_jTextPane.getText().indexOf("Повреждение:"), "Повреждение:".length(), baseline, true);
        description_jTextPane.getStyledDocument().setCharacterAttributes(description_jTextPane.getText().indexOf("Дальность:"), "Дальность:".length(), baseline, true);
        description_jTextPane.getStyledDocument().setCharacterAttributes(description_jTextPane.getText().indexOf("Стоимость:"), "Стоимость:".length(), baseline, true);

        baseline = new SimpleAttributeSet();
        StyleConstants.setForeground(baseline, values_Color);
        int startIndex = description_jTextPane.getText().indexOf("Повреждение:") + "Повреждение:".length() + 1;
        int length = description_jTextPane.getText().indexOf("↔ Дальность:") - 2 - startIndex;
        description_jTextPane.getStyledDocument().setCharacterAttributes(startIndex, length, baseline, true);

        startIndex = description_jTextPane.getText().indexOf("Дальность:") + "Дальность:".length() + 1;
        length = description_jTextPane.getText().indexOf("\uD83D\uDCB5 Стоимость:") - 2 - startIndex;
        description_jTextPane.getStyledDocument().setCharacterAttributes(startIndex, length, baseline, true);

        startIndex = description_jTextPane.getText().indexOf("Стоимость:") + "Стоимость:".length() + 1;
        length = description_jTextPane.getText().length() - startIndex;
        description_jTextPane.getStyledDocument().setCharacterAttributes(startIndex, length, baseline, true);
    }

    private void addStyleToCompletlyWalkthrough()
    {
        SimpleAttributeSet sas = new SimpleAttributeSet();
        StyleConstants.setFontSize(sas, description_jTextPane.getFont().getSize() + 4);

        int[] indexes = YALtools.searchCharInText(description_jTextPane.getText(), '✔');
        int startIndex = 0;
        int length = 1;

        for (int k = 0; k < indexes.length; k++)
        {
            startIndex = indexes[k];
            description_jTextPane.getStyledDocument().setCharacterAttributes(startIndex, length, sas, true);
        }

        indexes = YALtools.searchCharInText(description_jTextPane.getText(), '★');
        startIndex = 0;


        for (int k = 0; k < indexes.length; k++)
        {
            startIndex = indexes[k];
            description_jTextPane.getStyledDocument().setCharacterAttributes(startIndex, length, sas, true);
        }

        startIndex = description_jTextPane.getText().indexOf("Награда за 100 % прохождение:");
        length = "Награда за 100 % прохождение:".length();
        StyleConstants.setForeground(sas, new Color(255, 22, 93));
        StyleConstants.setItalic(sas, true);
        StyleConstants.setBold(sas, true);
        description_jTextPane.getStyledDocument().setCharacterAttributes(startIndex, length, sas, true);
    }

    private void addStyleToCheats()
    {
        int[] indexes = YALtools.searchCharInText(description_jTextPane.getText(), '"');

        SimpleAttributeSet sas = new SimpleAttributeSet();
        StyleConstants.setForeground(sas, new Color(6, 149, 121, 225));
        StyleConstants.setItalic(sas, true);
        StyleConstants.setBold(sas, true);

        int startIndex = 0;
        int length = 0;

        for (int k = 0; k < indexes.length; k++)
        {
            startIndex = indexes[k] + 1;
            length = indexes[k + 1] - startIndex;
            description_jTextPane.getStyledDocument().setCharacterAttributes(startIndex, length, sas, true);
            k++;
        }
    }

    private void loadCategories()
    {
        library_treeNode = new DefaultMutableTreeNode("Библиотека");
        library_treeModel = new DefaultTreeModel(library_treeNode);
        TreePath tr = new TreePath(library_treeModel);
        StringBuilder strBuilder = new StringBuilder();

        try
        {
            //добавляем персонажей
            dbReader.executeQuery("SELECT Characters.character_name, Characters.character_surname, Categories.category_name " +
                    "FROM join_characters_categories JOIN Characters ON join_characters_categories.character_id=Characters.character_id " +
                    "JOIN Categories ON join_characters_categories.category_id=Categories.category_id");

            ResultSet tmpRS = dbReader.getResultSet();
            library_treeModel.insertNodeInto(new DefaultMutableTreeNode(tmpRS.getString("category_name")), library_treeNode, library_treeModel.getChildCount(library_treeNode));

            String tmpNameSurname = "";
            DefaultMutableTreeNode tmpNode = (DefaultMutableTreeNode) library_treeModel.getChild(library_treeNode, library_treeModel.getChildCount(library_treeNode) - 1);
            while (tmpRS.next())
            {
                tmpNameSurname = tmpRS.getString("character_name") + " " + tmpRS.getString("character_surname");
                library_treeModel.insertNodeInto(new DefaultMutableTreeNode(tmpNameSurname), tmpNode, tmpNode.getChildCount());
            }

            //добавляем банды

            dbReader.executeQuery("SELECT Bands.band_name, Bands.band_description, Categories.category_name " +
                    "FROM join_bands_categories JOIN Bands ON join_bands_categories.band_id=Bands.band_id " +
                    "JOIN Categories ON join_bands_categories.category_id=Categories.category_id");
            tmpRS = dbReader.getResultSet();

            library_treeModel.insertNodeInto(new DefaultMutableTreeNode(tmpRS.getString("category_name")), library_treeNode, library_treeModel.getChildCount(library_treeNode));

            //будет содержать узел с бандами
            tmpNode = (DefaultMutableTreeNode) library_treeModel.getChild(library_treeNode, library_treeModel.getChildCount(library_treeNode) - 1);

            while (tmpRS.next())
            {
                library_treeModel.insertNodeInto(new DefaultMutableTreeNode(tmpRS.getString("band_name")), tmpNode, tmpNode.getChildCount());
            }

        }
        catch (SQLException sqlExc)
        {
            DebugTools.printDebugMessage("Ошибка при выполнении запроса к БД.\n" + sqlExc.toString());
        }

        //========================= Добавляем транспорт
        //Получаем новую категорию
        DefaultMutableTreeNode newCategoryNode;

        int countVehicleCategories = 0;
        try
        {
            //Получаем кол-во категорий транспорта
            ResultSet tmpRS = null;
            dbReader.executeQuery("SELECT COUNT(VehicleCategories.VehicleCategory_id) FROM VehicleCategories");
            tmpRS = dbReader.getResultSet();
            countVehicleCategories = tmpRS.getInt("COUNT(VehicleCategories.VehicleCategory_id)");
            DebugTools.printDebugMessage("Кол-во категорий транспортных средств: " + countVehicleCategories);

            //добавляем категорию
            dbReader.executeQuery("SELECT VehicleCategories.VehicleCategory_name, Categories.category_name, VehicleCategories.VehicleCategory_id " +
                    "FROM join_VehicleCategories_Categories " +
                    "JOIN VehicleCategories ON join_VehicleCategories_Categories.VehicleCategory_id=VehicleCategories.VehicleCategory_id " +
                    "JOIN Categories ON join_VehicleCategories_Categories.category_id=Categories.category_id " +
                    "WHERE VehicleCategories.VehicleCategory_id = 1");
            tmpRS = dbReader.getResultSet();

            newCategoryNode = new DefaultMutableTreeNode(tmpRS.getString("category_name"));
            library_treeModel.insertNodeInto(newCategoryNode, library_treeNode, library_treeNode.getChildCount());

            for (int k = 0; k < countVehicleCategories; k++)
            {
                dbReader.executeQuery("SELECT VehicleCategories.VehicleCategory_name, Categories.category_name, VehicleCategories.VehicleCategory_id " +
                        "FROM join_VehicleCategories_Categories " +
                        "JOIN VehicleCategories ON join_VehicleCategories_Categories.VehicleCategory_id=VehicleCategories.VehicleCategory_id " +
                        "JOIN Categories ON join_VehicleCategories_Categories.category_id=Categories.category_id " +
                        "WHERE VehicleCategories.VehicleCategory_id = " + (k + 1));
                tmpRS = dbReader.getResultSet();

                //Добавляем подкатегорию
                DefaultMutableTreeNode subCategoryNode = new DefaultMutableTreeNode(tmpRS.getString("VehicleCategory_name"));
                String subCategoryNode_str = subCategoryNode.getUserObject().toString();
                library_treeModel.insertNodeInto(subCategoryNode, newCategoryNode, newCategoryNode.getChildCount());

                //Получаем кол-во транспорта одной категории

                //Добавляем автомобили
                dbReader.executeQuery("SELECT Cars.car_id, Cars.car_name, Cars.car_speed, VehicleCategories.VehicleCategory_name " +
                        "FROM Cars " +
                        "JOIN VehicleCategories ON Cars.VehicleCategory_id=VehicleCategories.VehicleCategory_id " +
                        "WHERE VehicleCategories.VehicleCategory_name LIKE '%" + subCategoryNode_str + "%'");

                tmpRS = dbReader.getResultSet();

                while (tmpRS.next())
                {
                    library_treeModel.insertNodeInto(new DefaultMutableTreeNode(tmpRS.getString("car_name")), subCategoryNode, subCategoryNode.getChildCount());
                }
            }
        }
        catch (SQLException sqlExc)
        {
            DebugTools.printDebugMessage("Ошибка при выполненнии запроса к БД о получении категории транпорта.\n" + sqlExc.toString());
            DebugTools.createLogFile(sqlExc);
        }

        //========================= Добавляем Вооружение
        int countWeaponCategories = 0;
        try
        {
            //Получаем кол-во категорий транспорта
            ResultSet tmpRS = null;
            dbReader.executeQuery("SELECT COUNT(WeaponCategories.weaponCategory_id) FROM WeaponCategories");
            tmpRS = dbReader.getResultSet();
            countWeaponCategories = tmpRS.getInt("COUNT(WeaponCategories.weaponCategory_id)");
            DebugTools.printDebugMessage("Кол-во категорий вооружения: " + countWeaponCategories);

            //составляем SQL запрос
            strBuilder.delete(0, strBuilder.capacity());
            strBuilder.append("SELECT WeaponCategories.weaponCategory_name, Categories.category_name, WeaponCategories.weaponCategory_id ");
            strBuilder.append("FROM join_WeaponCategories_Categories ");
            strBuilder.append("JOIN WeaponCategories ON join_WeaponCategories_Categories.weaponCategory_id=WeaponCategories.weaponCategory_id ");
            strBuilder.append("JOIN Categories ON join_WeaponCategories_Categories.category_id=Categories.category_id ");
            strBuilder.append("WHERE WeaponCategories.weaponCategory_id = 1");

            //Добавляем категорию вооружения
            dbReader.executeQuery(strBuilder.toString());
            tmpRS = dbReader.getResultSet();

            newCategoryNode = new DefaultMutableTreeNode(tmpRS.getString("category_name"));
            library_treeModel.insertNodeInto(newCategoryNode, library_treeNode, library_treeNode.getChildCount());

            for (int k = 0; k < countWeaponCategories; k++)
            {
                strBuilder.delete(0, strBuilder.capacity());
                strBuilder.append("SELECT WeaponCategories.weaponCategory_name, Categories.category_name, WeaponCategories.weaponCategory_id ");
                strBuilder.append("FROM join_WeaponCategories_Categories ");
                strBuilder.append("JOIN WeaponCategories ON join_WeaponCategories_Categories.weaponCategory_id=WeaponCategories.weaponCategory_id ");
                strBuilder.append("JOIN Categories ON join_WeaponCategories_Categories.category_id=Categories.category_id ");
                strBuilder.append("WHERE WeaponCategories.weaponCategory_id = ");
                strBuilder.append((k + 1));
                dbReader.executeQuery(strBuilder.toString());
                tmpRS = dbReader.getResultSet();

                //Добавляем подкатегорию
                DefaultMutableTreeNode subCategoryNode = new DefaultMutableTreeNode(tmpRS.getString("weaponCategory_name"));
                String subCategoryNode_str = subCategoryNode.getUserObject().toString();
                library_treeModel.insertNodeInto(subCategoryNode, newCategoryNode, newCategoryNode.getChildCount());

                //Получаем кол-во транспорта одной категории

                strBuilder.delete(0, strBuilder.capacity());
                strBuilder.append("SELECT Weapons.weapon_id, Weapons.weapon_name, Weapons.weapon_description, Weapons.weapon_damage_percent, ");
                strBuilder.append("Weapons.weapon_price, Weapons.weapon_range, Weapons.weaponCategory_id ");
                strBuilder.append("FROM Weapons ");
                strBuilder.append("JOIN WeaponCategories ON Weapons.weaponCategory_id=WeaponCategories.weaponCategory_id ");
                strBuilder.append("WHERE WeaponCategories.weaponCategory_name LIKE '%");
                strBuilder.append(subCategoryNode_str);
                strBuilder.append("%'");

                //Добавляем экземпляры оружия
                dbReader.executeQuery(strBuilder.toString());

                tmpRS = dbReader.getResultSet();

                while (tmpRS.next())
                {
                    library_treeModel.insertNodeInto(new DefaultMutableTreeNode(tmpRS.getString("weapon_name")), subCategoryNode, subCategoryNode.getChildCount());
                }
            }
        }
        catch (SQLException sqlExc)
        {
            DebugTools.printDebugMessage("Ошибка при выполненнии запроса к БД о получении категории вооружения.\n" + sqlExc.toString());
            DebugTools.createLogFile(sqlExc);
        }

        //========================= Добавляем миссии
        int countMissionCategories = 0;
        try
        {
            long timeStart = System.nanoTime();
            //Получаем кол-во категорий миссий
            ResultSet tmpRS = null;
            dbReader.executeQuery("SELECT COUNT(MissionCategories.missionCategory_id) FROM MissionCategories");
            tmpRS = dbReader.getResultSet();
            countMissionCategories = tmpRS.getInt("COUNT(MissionCategories.missionCategory_id)");
            DebugTools.printDebugMessage("Кол-во категорий миссий: " + countMissionCategories);

            strBuilder.delete(0, strBuilder.capacity());
            strBuilder.append("SELECT MissionCategories.missionCategory_id, MissionCategories.missionCategory_name, Categories.category_name ");
            strBuilder.append("FROM join_MissionCategories_Categories ");
            strBuilder.append("JOIN MissionCategories ON join_MissionCategories_Categories.missionCategory_id=MissionCategories.missionCategory_id ");
            strBuilder.append("JOIN Categories ON join_MissionCategories_Categories.category_id=Categories.category_id ");
            strBuilder.append("WHERE MissionCategories.missionCategory_id = 1");

            //добавляем категорию
            dbReader.executeQuery(strBuilder.toString());
            tmpRS = dbReader.getResultSet();

            newCategoryNode = new DefaultMutableTreeNode(tmpRS.getString("category_name"));
            library_treeModel.insertNodeInto(newCategoryNode, library_treeNode, library_treeNode.getChildCount());

            for (int k = 0; k < countMissionCategories; k++)
            {
                strBuilder.delete(0, strBuilder.capacity());
                strBuilder.append("SELECT MissionCategories.missionCategory_id, MissionCategories.missionCategory_name, Categories.category_name ");
                strBuilder.append("FROM join_MissionCategories_Categories ");
                strBuilder.append("JOIN MissionCategories ON join_MissionCategories_Categories.missionCategory_id=MissionCategories.missionCategory_id ");
                strBuilder.append("JOIN Categories ON join_MissionCategories_Categories.category_id=Categories.category_id ");
                strBuilder.append("WHERE MissionCategories.missionCategory_id = ");
                strBuilder.append((k + 1));

                dbReader.executeQuery(strBuilder.toString());
                tmpRS = dbReader.getResultSet();

                //Добавляем подкатегорию
                DefaultMutableTreeNode subCategoryNode = new DefaultMutableTreeNode(tmpRS.getString("missionCategory_name"));
                String subCategoryNode_str = subCategoryNode.getUserObject().toString();
                library_treeModel.insertNodeInto(subCategoryNode, newCategoryNode, newCategoryNode.getChildCount());

                //Получаем кол-во транспорта одной категории

                strBuilder.delete(0, strBuilder.capacity());
                strBuilder.append("SELECT Missions.mission_id, Missions.mission_name, Missions.mission_description, Missions.mission_reward, Missions.mission_category_id ");
                strBuilder.append("FROM Missions ");
                strBuilder.append("JOIN MissionCategories ON Missions.mission_category_id=MissionCategories.missionCategory_id ");
                strBuilder.append("WHERE MissionCategories.missionCategory_name LIKE '%");
                strBuilder.append(subCategoryNode_str);
                strBuilder.append("%'");

                //Добавляем собственно миссии
                dbReader.executeQuery(strBuilder.toString());

                tmpRS = dbReader.getResultSet();

                while (tmpRS.next())
                {
                    library_treeModel.insertNodeInto(new DefaultMutableTreeNode(tmpRS.getString("mission_name")), subCategoryNode, subCategoryNode.getChildCount());
                }
            }
            long currentTime = System.nanoTime();
            DebugTools.printDebugMessage("Затрачено времени на импорт миссий: " + (currentTime - timeStart));
        }
        catch (SQLException sqlExc)
        {
            DebugTools.printDebugMessage("Ошибка при выполненнии запроса к БД о получении категории миссии.\n" + sqlExc.toString());
            DebugTools.createLogFile(sqlExc);
        }

        //добавляем сведения об игре
        try
        {
            dbReader.executeQuery("SELECT AboutGame.aboutGame_id, AboutGame.aboutGame_name, AboutGame.aboutGame_description, Categories.category_id, Categories.category_name, Categories.category_description " +
                    "FROM join_AboutGame_Categories " +
                    "JOIN AboutGame ON join_AboutGame_Categories.aboutGame_id=AboutGame.aboutGame_id " +
                    "JOIN Categories ON join_AboutGame_Categories.category_id=Categories.category_id");

            ResultSet tmpRS = dbReader.getResultSet();
            library_treeModel.insertNodeInto(new DefaultMutableTreeNode(tmpRS.getString("category_name")), library_treeNode, library_treeModel.getChildCount(library_treeNode));

            String tmpNameSurname = "";
            DefaultMutableTreeNode tmpNode = (DefaultMutableTreeNode) library_treeModel.getChild(library_treeNode, library_treeModel.getChildCount(library_treeNode) - 1);
            while (tmpRS.next())
            {
                library_treeModel.insertNodeInto(new DefaultMutableTreeNode(tmpRS.getString("aboutGame_name")), tmpNode, tmpNode.getChildCount());
            }
        }
        catch (SQLException sqlExc)
        {
            DebugTools.printDebugMessage("Ошибка выполнения запроса к БД при считывании свдений об игре");
            DebugTools.createLogFile(sqlExc);
        }

    }


    private javax.swing.JTree library_jTree;
    private javax.swing.JTextPane description_jTextPane;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane library_jScrollPane;
    private javax.swing.JScrollPane description_jScrollPane;
    private ControlledLabel titleOfArticle_jLabel;

    private psevdoImageBox image_psevdoImageBox;
    private javax.swing.JPanel main_JPanel;
    private Font descriptionFont;
    private FontMetrics titleFontMetrics;


    /**
     * Если ширина строки больше чем ширина компонента, то размер шрифта будет уменьшен.
     */
    private void fixTitleSize()
    {
        if (titleOfArticle_jLabel != null && titleOfArticle_jLabel.getGraphics() != null)
        {
            titleFontMetrics = titleOfArticle_jLabel.getGraphics().getFontMetrics();

            while (true)
            {
                double widthOfTitleString = 0.0;
                for (int k = 0; k < titleOfArticle_jLabel.getText().length(); k++)
                {
                    widthOfTitleString += titleFontMetrics.charWidth(titleOfArticle_jLabel.getText().charAt(k));
                    //DebugTools.printDebugMessage("Ширина глифа '" + titleOfArticle_jLabel.getText().charAt(k) + "' равна: " + titleFontMetrics.charWidth(titleOfArticle_jLabel.getText().charAt(k)));
                }
                DebugTools.printDebugMessage("Ширина строки: " + widthOfTitleString);

                if (widthOfTitleString < titleOfArticle_jLabel.getSize().getWidth())
                {
                    break;
                }
                titleOfArticle_jLabel.setFont(titleOfArticle_jLabel.getFont().deriveFont(Font.ITALIC, titleOfArticle_jLabel.getFont().getSize() - 1));
                DebugTools.printDebugMessage("новый размер шрифта: " + titleOfArticle_jLabel.getFont().getSize());
            }
        }
    }


    /**
     * Назначение этого класса - определять, когда размер компонента с текстом
     * будет превышать ширину фрейма и, при необходимости, уменьшать размер шрифта.
     */
    class ControlledLabel extends JLabel
    {
        @Override
        public void repaint(long tm, int x, int y, int width, int height)
        {
            super.repaint(tm, x, y, width, height);
            if (library_jScrollPane != null)
            {
//                DebugTools.printDebugMessage("Размер фрейма библиотеки: " + getSize().getWidth() + "x" + getSize().getHeight());
                DebugTools.printDebugMessage("Размер дерева и заголовка: " + library_jScrollPane.getSize().getWidth() + "\t " + titleOfArticle_jLabel.getSize().getWidth()
                );
                fixTitleSize();
            }
        }
    }
}
