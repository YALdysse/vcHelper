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

import mouseActions.*;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.prefs.BackingStoreException;
import java.util.prefs.InvalidPreferencesFormatException;
import java.util.prefs.Preferences;
import javax.imageio.ImageIO;
import javax.swing.*;

/*Как преобразовывать из URL в File:
 *   new File(URL.toURI)
 * В этом случае путь будет записан правильно.
 * */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Главный фрейм программы
 * Разработка: 21.04.2021 - 08.05.2021, 17.05.2021
 *
 * @author Y@Ldysse
 */
//public class main_gui extends javax.swing.JFrame
public class main_gui extends JFrame
{
    private static main_gui thisObject;
    private Icon viceCityLogo_Icon;
    private int frameWidth = 345;
    private int frameHeight = 430;
    static public ClassLoader mainGui_ClassLoader;
    private int screenshotViewerSizePercent = 40;
    private Preferences pref;
    private File rageItalic_File;
    private Font rageItalic_Font;
    private Font casualContactMF = null;
    private Font toolTipDescription = null;
    private final Color viceCityFontColor = new Color(251, 80, 226);
    private final Color TRANSPARENCY = new Color(0, 0, 0, 0);
    private final Color LEGEND_Color = new Color(225, 223, 197);

    private final float sizeRageItalic = 36f;
    private MouseAction mAction;
    private boolean isNorthEastMap = false;
    private boolean isNorthWestMap = false;
    private boolean isSouthEastMap = false;
    private boolean isSouthWestMap = false;
    private int firstTimeOnMap = 1;
    private int countCharInLine = 30;
    private final String FrameX = "Frame.X";
    private final String FrameY = "Frame.Y";
    private String startScreenFontName = "Rage_Italic_LET_Plain_1.0.ttf";

    private String medkit_str = "Аптечки";
    private String bribes_str = "Взятки";
    private String rampages_str = "Rampages";
    private String sprays_str = "Pay 'n' Spray";
    private String drugs_str = "Адреналин";
    private String armor_str = "Броня";
    private String properties_str = "Апартаменты";
    private String clothes_str = "Одежда";
    private String weapons_str = "Оружие";
    private String stores_str = "Магазины";
    private String vehicles_str = "Транспорт";
    private String uniqueJumps_str = "Уникальные прижки";
    private String hiddenPackages_str = "Скрытые пакеты";
    private String secrets_str = "Секреты";
    private String currentLanguage_str = "English";
    private String molotov_weapon_str = "Molotov cocktail";
    private String knuckle_weapon_str = "Brass knuckle";
    private String meatCleaver_weapon_str = "Meat cleaver";
    private String nightStick_weapon_str = "Night stick";
    private String golfClub_weapon_str = "Golf club";
    private String knife_weapon_str = "Knife";
    private String katana_weapon_str = "Katana";
    private String chainsaw_weapon_str = "Сhainsaw";
    private String grenades_weapon_str = "Grenades";
    private String remoteGrenades_weapon_str = "Remote grenades";
    private String baseballBat_weapon_str = "Baseball bat";
    private String pistol_weapon_str = "Colt .45";
    private String uzi_weapon_str = "Uzi";
    private String shotgun_weapon_set = "Shotgun";
    private String machete_weapon_str = "Machete";
    private String tec_weapon_str = "Tec 9";
    private String m4_weapon_str = "M4";
    private String ruger_weapon_str = "Ruger";
    private String stubbyShotgun_weapon_str = "Stubby shotgun";
    private String m60_weapon_str = "M60";
    private String minigun_weapon_str = "Minigun";
    private String rocketLauncher_weapon_str = "Rocket launcher";
    private String sniperRiffle_weapon_str = "Sniper rifle";
    private String mac_weapon_str = "Mac";
    private String flamethrower_weapon_str = "Flamethrower";
    private String coltPython_weapon_str = ".357 (Colt Python)";
    private String sniper308_weapon_str = "308 Sniper (PSG-1)";
    private String spas12_weapon_str = "S.P.A.S. 12";

    private String oceanic_vehicle_str = "Oceanic";
    private String pcj600_vehicle_str = "PCJ 600";
    private String patrion_vehicle_str = "Patriot";
    private String voodoo_vehicle_str = "Voodoo";
    private String trashmaster_str = "Trashmaster";
    private String topFun_vehicle_str = "Top Fun";
    private String pizzaBoy_vehicle_str = "Pizza Boy";
    private String infernus_vehicle_str = "Infernus";
    private String hermes_vehicle_str = "Hermes";
    private String gangBurrito_vehicle_str = "Gang Burrito";
    private String freeway_vehicle_str = "Freeway";
    private String firetruck_vehicle_str = "Firetruck";
    private String faggio_vehicle_str = "Faggio";
    private String comet_vehicle_str = "Comet";
    private String cheetah_vehicle_str = "Cheetah";
    private String bfInjection = "BF Injection";
    private String banshee_vehicle_str = "Banshee";
    private String admiral_vehicle_str = "Admiral";
    private String angel_vehicle_str = "Angel";
    private String ambulance_vehicle_str = "Ambulance";
    private String police_vehicle_str = "Police";
    private String caddy_vehicle_str = "Caddy";
    private String rumpo_vehicle_str = "Rumpo";
    private String washington_vehicle_str = "Washington";
    private String sanchez_vehicle_str = "Sanchez";
    private String landstalker_vehicle_str = "Landstalker";
    private String maverick_vehicle_str = "Maverick";
    private String pony_vehicle_str = "Pony";
    private String securicar_vehicle_str = "Securicar";
    private String linerunner_vehicle_str = "Linerunner";
    private String sentinelXS_vehicle_str = "Sentinel XS";
    private String baggageHandler_vehicle_str = "Baggage Handler";
    private String bus_vehicle_str = "Bus";
    private String stinger_vehicle_str = "Stinger";
    private String phoenix_vehicle_str = "Phoenix";
    private String policeMaverick_vehicle_str = "Police Maverick";
    private String coastGuard_vehicle_str = "Coast Guard";
    private String barracksOL_vehicle_str = "Barracks OL";

    private String secret_0_sunkenReefer = "The sunken Reefer.";
    private String secret_1_fatManUnderWater = "The Fat man wearing cement shoes can be seen underwater.";
    private String secret_2_vehicleUnderWater = "";
    private String secret_3_easterEgg = "There is a window where the player will find the 'Easter Egg Room' which is basically an actual brown easter egg on top of a pedestal.";
    private String secret_4_humansOrgans = "There are organs of human (arm and brain) into the store in Little Haiti.";
    private String secret_6 = "There is a billboard saying 'Welcome to Hell' instead of 'Welcome to Haiti'.";
    private String secret_5_lifeIsBitch = "There is a billboard saying 'Life's a bitch', instead of 'Life's a beach'.";
    private String secret_7_rockstarLogoPool = "The Shape of the pool is likeness Rockstar logo.";
    private String secret_8 = "The pool can be seen in the shape of a female body.";
    private String secret_9 = "You can play with the ball in this pool.";
    private String secret_10_manUnderWater = "The Fat man wearing cement shoes can be seen underwater.";
    private String secret_11_submarine = "There is a submarine.";
    private String secret_12_sunkenContainerShip = "There is sunken 'Chartered Libertine Lines' container ships.";
    private String secret_13_dick = "A pattern of lights on the Vice Point Langer building resembles a penis.";
    private String secret_14_apartment3c = "Apartment 3c";
    private String secret_15_sunkenContainerShip = "Another sunken 'Chartered Libertine Lines' container ships.";

    private String store_0_bunchOfTools = "Bunch of Tools";
    private String store_1_jewerlyStore = "Jewelry Store";
    private String store_2_dispansary = "Dispensary";
    private String store_3_cornerStore = "Corner Store";
    private String store_5_vinil = "Vinyl Countdown";
    private String store_6_tooledUp = "Tooled Up";
    private String store_7_gash = "Gash";
    private String store_9_pharmacy = "Pharmacy";
    private String store_10_robinas = "Robina's cafe";
    private String store_11_screwThis = "Screw This";
    private String store_12_doughnutShop = "Doughnut Shop";
    private String store_13_laundromat = "Laundromat";
    private String store_14_rytonAide = "Ryton Aide Pharmacy";

//    private String property_0_1102Washington = "1102 Washington Street\nPrice: 3,000";
//    private String property_1_oceanHeights = "Ocean Heights\nPrice: 7,000$";
//    private String property_2_linksView = "Links View Apartments\nPrice: 6,000$";
//    private String property_3_elSwanko = "El Swanko Casa\nPrice: 8,000$";
//    private String property_4_3321VicePoint = "3321 Vice Point\nPrice: 2,500$";
//    private String property_5_oceanView = "Ocean View";
//    private String property_6_skumoleShack = "Skumole Shack\nPrice: 1,000$";
//    private String property_7_hymanCondo = "Hyman Condo\nPrice: 14,000$";
//    private String property_8_polePosition = "Pole Position Club\nPrice: 30,000$";
//    private String property_9_malibu = "The Malibu\nPrice: 120,000$";
//    private String property_10_filmStudio = "Film Studio\nPrice: 60,000$";
//    private String property_11_carShowroom = "Car Showroom\nPrice: 50,000$";
//    private String property_12_printWorkd = "Print Works\nPrice: 70,000$";
//    private String property_13_boatyard = "Boatyard\nPrice: 10,000$";
//    private String property_14_iceCream = "Ice Cream Factory\nPrice: 20,000$";
//    private String property_15_taxi = "Taxi Company\nPrice: 40,000$";

    private String property_0_1102Washington = "1102 Washington Street";
    private String property_1_oceanHeights = "Ocean Heights";
    private String property_2_linksView = "Links View Apartments";
    private String property_3_elSwanko = "El Swanko Casa";
    private String property_4_3321VicePoint = "3321 Vice Point";
    private String property_5_oceanView = "Ocean View";
    private String property_6_skumoleShack = "Skumole Shack";
    private String property_7_hymanCondo = "Hyman Condo";
    private String property_8_polePosition = "Pole Position Club";
    private String property_9_malibu = "The Malibu";
    private String property_10_filmStudio = "Film Studio";
    private String property_11_carShowroom = "Car Showroom";
    private String property_12_printWorkd = "Print Works";
    private String property_13_boatyard = "Boatyard";
    private String property_14_iceCream = "Ice Cream Factory";
    private String property_15_taxi = "Taxi Company";

    private String rampage_0 = "Kill 25 gang members in 2 minutes using a M4.";
    private String rampage_1 = "Destroy 10 vehicles in 2 minutes using a Rocket Launcher.";
    private String rampage_2 = "Kill 30 gang members in 2 minutes using Molotov Cocktails.";
    private String rampage_3 = "Run over and kill 30 gang members in 2 minutes.";
    private String rampage_4 = "Kill 10 gang members in 2 minutes using a Katana.";
    private String rampage_5 = "Kill 10 gang members in 2 minutes using a Chainsaw.";
    private String rampage_6 = "Kill 25 gang members in 2 minutes using a Shotgun.";
    private String rampage_7 = "Kill 25 gang members in 2 minutes using a .308 Sniper.";
    private String rampage_8 = "Drive-by and waste 35 gang members in 2 minutes using a Uzi.";
    private String rampage_9 = "Kill 25 gang members in 2 minutes using a MP5.";
    private String rampage_10 = "Kill 30 gang members in 2 minutes using a S.P.A.S. 12.";
    private String rampage_11 = "Kill 10 gang members in 2 minutes using a .308 Sniper.";
    private String rampage_12 = "Kill 25 gang members in 2 minutes using a Colt Python.";
    private String rampage_13 = "Kill 35 gang members in 2 minutes using a M4.";
    private String rampage_14 = "Drive-by and waste 30 gang members in 2 minutes using a Uzi.";
    private String rampage_15 = "Kill 30 gang members in 2 minutes using a Rocket Launcher.";
    private String rampage_16 = "Kill 20 gang members in 2 minutes using a Chainsaw.";
    private String rampage_17 = "Kill 30 gang members in 2 minutes using a Minigun.";
    private String rampage_18 = "Kill 40 gang members in 2 minutes using Molotov Cocktails.";
    private String rampage_19 = "Kill 25 gang members in 2 minutes using a Colt Python.";
    private String rampage_20 = "Kill 30 gang members in 2 minutes using a Flamethrower.";
    private String rampage_21 = "Kill 30 gang members in 2 minutes using a M60.";
    private String rampage_22 = "Drive-by and waste 35 gang members in 2 minutes using a Uzi.";
    private String rampage_23 = "Kill 35 gang members in 2 minutes using a S.P.A.S. 12.";
    private String rampage_24 = "Kill 30 gang members in 2 minutes using a Tec 9.";
    private String rampage_25 = "Run over and kill 35 gang members in 2 minutes.";
    private String rampage_26 = "Kill 20 gang members in 2 minutes using a Sniper Rifle.";
    private String rampage_27 = "Kill 10 gang members in 2 minutes using a Katana.";
    private String rampage_28 = "Kill 20 gang members in 2 minutes using a Ruger.";
    private String rampage_29 = "Kill 35 gang members in 2 minutes using Grenades.";
    private String rampage_30 = "Destroy 15 vehicles in 2 minutes using a Rocket Launcher.";
    private String rampage_31 = "Destroy 15 vehicles in 2 minutes using a Rocket Launcher.";
    private String rampage_32 = "Kill 25 gang members in 2 minutes using a Shotgun.";
    private String rampage_33 = "Destroy 15 vehicles in 2 minutes using a Rocket Launcher.";
    private String rampage_34 = "Kill 25 gang members in 2 minutes using a S.P.A.S. 12.";

    private String uniqueJump_0 = "";
    private String uniqueJump_1 = "";
    private String uniqueJump_2 = "";
    private String uniqueJump_3 = "";
    private String uniqueJump_4 = "";
    private String uniqueJump_5 = "";
    private String uniqueJump_6 = "";
    private String uniqueJump_7 = "";
    private String uniqueJump_8 = "";
    private String uniqueJump_9 = "";
    private String uniqueJump_10 = "";
    private String uniqueJump_11 = "";
    private String uniqueJump_12 = "";
    private String uniqueJump_13 = "";
    private String uniqueJump_14 = "";
    private String uniqueJump_15 = "";
    private String uniqueJump_16 = "";
    private String uniqueJump_17 = "";
    private String uniqueJump_18 = "";
    private String uniqueJump_19 = "";
    private String uniqueJump_20 = "";
    private String uniqueJump_21 = "";
    private String uniqueJump_22 = "";
    private String uniqueJump_23 = "";
    private String uniqueJump_24 = "";
    private String uniqueJump_25 = "";
    private String uniqueJump_26 = "";
    private String uniqueJump_27 = "";
    private String uniqueJump_28 = "";
    private String uniqueJump_29 = "";
    private String uniqueJump_30 = "";
    private String uniqueJump_31 = "";
    private String uniqueJump_32 = "";
    private String uniqueJump_33 = "";
    private String uniqueJump_34 = "";
    private String uniqueJump_35 = "";

    private String clothes_0_Street = "";
    private String clothes_1_Soiree = "";
    private String clothes_2_Coveralls = "";
    private String clothes_3_CountryClub = "";
    private String clothes_4_Cop = "";
    private String clothes_5_BankJob = "";
    private String clothes_6_Casual = "";
    private String clothes_7_Vercetti = "";
    private String clothes_8_Tracksuit1 = "";
    private String clothes_9_Tracksuit2 = "";
    private String clothes_10_Havana = "";
    private String clothes_11_Frankie = "";

    private String saveMap_str = "Save the Map";

    private String howToWorkWithMap_str = "Press LMB to delete pickup or RMB to show screenshot.";
    private String howToWorkWithMap_title = "How to work with map";


    /**
     * Creates new form main_gui
     */
    public main_gui()
    {
        thisObject = this;
        mainGui_ClassLoader = this.getClass().getClassLoader();
        DebugTools.printDebugMessage("Получение пути: " + DebugTools.getJarLocation().getAbsolutePath());
        startScreenInitComponents();
    }

    private void startScreenInitComponents()
    {
        PanelWithMapParts = new javax.swing.JPanel();
        selectMapPart_JLabel = new javax.swing.JLabel();
        northWestPart_JButton = new javax.swing.JButton();
        northEastPart_JButton = new javax.swing.JButton();
        southWestPart_JButton = new javax.swing.JButton();
        southEastPart_JButton = new javax.swing.JButton();
        main_MenuBar = new javax.swing.JMenuBar();
        file_Menu = new javax.swing.JMenu("General");
        show_Menu = new javax.swing.JMenu("Show");
        goTo_Menu = new javax.swing.JMenu("Go to...");

        changeLanguage_MenuItem = new javax.swing.JMenuItem("Change Language");
        aboutProgramm_JMenuItem = new javax.swing.JMenuItem("About");
        showHowToWorkMessage_JMenuItem = new javax.swing.JMenuItem("How to work with Map?");
        showMedkit_JMenuItem = new javax.swing.JCheckBoxMenuItem(medkit_str);
        showArmor_JMenuItem = new javax.swing.JCheckBoxMenuItem(armor_str);
        showBribes_JMenuItem = new javax.swing.JCheckBoxMenuItem(bribes_str);
        showDrugs_JMenuItem = new javax.swing.JCheckBoxMenuItem(drugs_str);
        showSpraies_JMenuItem = new javax.swing.JCheckBoxMenuItem(sprays_str);
        showProperties_JMenuItem = new javax.swing.JCheckBoxMenuItem(properties_str);
        showRampages_JMenuItem = new javax.swing.JCheckBoxMenuItem(rampages_str);
        showClothes_JMenuItem = new javax.swing.JCheckBoxMenuItem(clothes_str);
        showWeapons_JMenuItem = new javax.swing.JCheckBoxMenuItem(weapons_str);
        showStores_JMenuItem = new javax.swing.JCheckBoxMenuItem(stores_str);
        showVehicles_JMenuItem = new javax.swing.JCheckBoxMenuItem(vehicles_str);
        showUniqueJumps_JMenuItem = new javax.swing.JCheckBoxMenuItem(uniqueJumps_str);
        showHiddenPackages_JMenuItem = new javax.swing.JCheckBoxMenuItem(hiddenPackages_str);
        showSecrets_JMenuItem = new javax.swing.JCheckBoxMenuItem(secrets_str);
        saveMap_JMenuItem = new javax.swing.JMenuItem("Save the Map");
        setUpScreenshotViewerSizePercent_JMenuItem = new javax.swing.JMenuItem();

        goToNorthWestMap_JMenuItem = new javax.swing.JMenuItem("North-West part of the Map");
        goToSouthWestMap_JMenuItem = new javax.swing.JMenuItem("South-West part of the Map");
        goToNorthEastMap_JMenuItem = new javax.swing.JMenuItem("North-East part of the Map");
        goToSouthEastMap_JMenuItem = new javax.swing.JMenuItem("South-East part of the Map");

        exit_MenuItem = new javax.swing.JMenuItem("Exit");
        goToStartScreen_MenuItem = new javax.swing.JMenuItem("Start screen");
        goToLibrary_JMenuItem = new javax.swing.JMenuItem("Library");
        openPageWithGitHubRepository = new javax.swing.JMenuItem("Repository on GitHub");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(frameWidth, frameHeight));
        setSize(frameWidth, frameHeight);
        setResizable(false);
        setTitle("Vice City Helper [build 95 Beta]");
        try
        {
            BufferedImage frameIcon = ImageIO.read(mainGui_ClassLoader.getResource("Images/icon.png"));
            setIconImage(frameIcon);
        }
        catch (IOException ioExc)
        {
            DebugTools.printDebugMessage("Иконка программы не была найдена.");
            DebugTools.createLogFile(ioExc);
            return;
        }

        //============
        importSettingFile();

        try
        {
            InputStream rageItalicFile_is = mainGui_ClassLoader.getResource("Fonts/" + startScreenFontName).openStream();
            //rageItalic_File = new File(DebugTools.getJarLocation().getParent() + File.separator + "Fonts" + File.separator + pref.get("startScreenFontFileName", "Rage_Italic_LET_Plain_1.0.ttf"));
            //DebugTools.printDebugMessage("Поиск шрифта: " + rageItalic_File.getAbsolutePath());
            //DebugTools.printDebugMessage("Поиск шрифта: " + );

//            if (rageItalicFile_is==null)
//            {
//                DebugTools.printDebugMessage("Шрифт не найден.");
//
//                JOptionPane.showMessageDialog(this, "Шрифт не был найден. Проверьте, чтобы каталог с ресурсами находился рядом с исполняемым файлом.\n"
//                                + "Путь к шрифту: " + rageItalic_File.getAbsolutePath()
//                        , "Ошибка загрузки шрифта", JOptionPane.ERROR_MESSAGE);
//                //DebugTools.createLogFile(new FileNotFoundException("Шрифт не был найден. \n" + rageItalic_File.getAbsolutePath()));
//                System.exit(1);
//            }
            //rageItalic_Font = Font.createFont(Font.TRUETYPE_FONT, rageItalic_File);
            rageItalic_Font = Font.createFont(Font.TRUETYPE_FONT, rageItalicFile_is);
            //pref.removeNode();
        }
        catch (NullPointerException ioExc)
        {
            DebugTools.printDebugMessage("Пустая ссылка при импорте шрифта RageItalic.");
            DebugTools.createLogFile(ioExc);
        }
        catch (IOException ioExc)
        {
            DebugTools.printDebugMessage("Ошибка ввода-вывода при импорте файла настроек.");
            DebugTools.createLogFile(ioExc);
        }
        catch (FontFormatException fontFormExc)
        {
            System.out.println(getCurrentTimeString() + " Неправильный формат шрифта.\n" + fontFormExc.toString());
            DebugTools.createLogFile(fontFormExc);
            System.exit(1);
        }

        //============

        //PanelWithMapParts.setBackground(new java.awt.Color(24, 184, 24));
        PanelWithMapParts.setPreferredSize(new java.awt.Dimension(246, 234));

        northWestPart_JButton.setSize(300, 300);

        BufferedImage northWestMapPartMini = null;
        BufferedImage southWestMapPartMini = null;
        BufferedImage northEastMapPartMini = null;
        BufferedImage southEastMapPartMini = null;
        try
        {
            northWestMapPartMini = ImageIO.read(mainGui_ClassLoader.getResource("Images/part1_razm_mini.png"));
            southWestMapPartMini = ImageIO.read(mainGui_ClassLoader.getResource("Images/part2_razm_mini.png"));
            northEastMapPartMini = ImageIO.read(mainGui_ClassLoader.getResource("Images/part3_razm_mini.png"));
            southEastMapPartMini = ImageIO.read(mainGui_ClassLoader.getResource("Images/part4_razm_mini.png"));
        }
        catch (IllegalArgumentException illegArgExc)
        {
            System.out.println(getCurrentTimeString() + " Некорректный аргумент.\n" + illegArgExc.toString());
            DebugTools.createLogFile(illegArgExc);
        }
        catch (IOException ioExc)
        {
            System.out.println(getCurrentTimeString() + " Ошибка ввода вывода при загрузке миниатюр карт.\n" + ioExc.toString());
            DebugTools.createLogFile(ioExc);
        }

        northWestPart_JButton.setBackground(new Color(148, 199, 246));
        southWestPart_JButton.setBackground(new Color(148, 199, 246));
        northEastPart_JButton.setBackground(new Color(148, 199, 246));
        southEastPart_JButton.setBackground(new Color(148, 199, 246));

        northWestPart_JButton.setIcon(new ImageIcon(northWestMapPartMini));
        southWestPart_JButton.setIcon(new ImageIcon(southWestMapPartMini));
        northEastPart_JButton.setIcon(new ImageIcon(northEastMapPartMini));
        southEastPart_JButton.setIcon(new ImageIcon(southEastMapPartMini));

        selectMapPart_JLabel.setText("Выберите часть карты");
        //selectMapPart_JLabel.setText("Choose part of a Map");
        //selectMapPart_JLabel.setHorizontalAlignment(SwingConstants.CENTER);
        selectMapPart_JLabel.setFont(rageItalic_Font.deriveFont(sizeRageItalic));
        selectMapPart_JLabel.setForeground(viceCityFontColor);

        northWestPart_JButton.setToolTipText("North-West part of Vice City");
        northWestPart_JButton.addActionListener((event)
                ->
        {
            NorthWestPart_JButton_Action();
        });

        northEastPart_JButton.setToolTipText("North-East part of Vice City");
        northEastPart_JButton.addActionListener((event) ->
        {
            northEastPart_JButton_Action();
        });

        southWestPart_JButton.setToolTipText("South-West part of Vice City");
        southWestPart_JButton.addActionListener((event) ->
        {
            southWestPart_JButton_Action();
        });

        southEastPart_JButton.setToolTipText("South-East part of Vice City");
        southEastPart_JButton.addActionListener((event) ->
        {
            southEastPart_JButton_Action();
        });

        exit_MenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, KeyEvent.CTRL_DOWN_MASK));
        exit_MenuItem.addActionListener((event) ->
        {
            saveSetting();
            System.exit(0);
        });

        goToStartScreen_MenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0));
        goToStartScreen_MenuItem.addActionListener((event) -> goToStartScreen_Action());

        showArmor_JMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, KeyEvent.SHIFT_DOWN_MASK));
        showArmor_JMenuItem.addActionListener((event) ->
        {
            Armor_JCheckBox.setSelected(showArmor_JMenuItem.isSelected());
            armor_CheckBoxAction();
        });

        showBribes_JMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_B, KeyEvent.SHIFT_DOWN_MASK));
        showBribes_JMenuItem.addActionListener((event) ->
        {
            if (showBribes_JMenuItem.isSelected())
                Bribes_JCheckBox.setSelected(true);
            else
                Bribes_JCheckBox.setSelected(false);
            bribes_CheckBoxAction();
        });

        showDrugs_JMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, KeyEvent.SHIFT_DOWN_MASK));
        showDrugs_JMenuItem.addActionListener((event) ->
        {
            if (showDrugs_JMenuItem.isSelected())
                Drugs_JCheckBox.setSelected(true);
            else
                Drugs_JCheckBox.setSelected(false);
            drugs_CheckBoxAction();
        });

        showMedkit_JMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_M, KeyEvent.SHIFT_DOWN_MASK));
        showMedkit_JMenuItem.addActionListener((event) ->
        {
            if (showMedkit_JMenuItem.isSelected())
                Medkit_jCheckBox.setSelected(true);
            else
                Medkit_jCheckBox.setSelected(false);
            medkit_CheckBoxAction();
        });

        showProperties_JMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, KeyEvent.SHIFT_DOWN_MASK));
        showProperties_JMenuItem.addActionListener((event) ->
        {
            if (showProperties_JMenuItem.isSelected())
                Properties_JCheckBox.setSelected(true);
            else
                Properties_JCheckBox.setSelected(false);
            properties_CheckBoxAction();
        });

        showSpraies_JMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.SHIFT_DOWN_MASK));
        showSpraies_JMenuItem.addActionListener((event) ->
        {
            if (showSpraies_JMenuItem.isSelected())
                Spraies_JCheckBox.setSelected(true);
            else
                Spraies_JCheckBox.setSelected(false);
            spraies_CheckBoxAction();
        });

        showRampages_JMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, KeyEvent.SHIFT_DOWN_MASK));
        showRampages_JMenuItem.addActionListener((event) ->
        {
            if (showRampages_JMenuItem.isSelected())
                Rampages_JCheckBox.setSelected(true);
            else
                Rampages_JCheckBox.setSelected(false);
            rampages_CheckBoxAction();
        });

        showClothes_JMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, KeyEvent.SHIFT_DOWN_MASK));
        showClothes_JMenuItem.addActionListener((event) ->
        {
            if (showClothes_JMenuItem.isSelected())
                Clothes_JCheckBox.setSelected(true);
            else
                Clothes_JCheckBox.setSelected(false);
            clothes_CheckBoxAction();
        });

        showWeapons_JMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W, KeyEvent.SHIFT_DOWN_MASK));
        showWeapons_JMenuItem.addActionListener((event) ->
        {
            if (showWeapons_JMenuItem.isSelected())
                Weapons_JCheckBox.setSelected(true);
            else
                Weapons_JCheckBox.setSelected(false);
            weapons_CheckBoxAction();
        });

        showStores_JMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_T, KeyEvent.SHIFT_DOWN_MASK));
        showStores_JMenuItem.addActionListener((event) ->
        {
            if (showStores_JMenuItem.isSelected())
                Stores_JCheckBox.setSelected(true);
            else
                Stores_JCheckBox.setSelected(false);
            stores_CheckBoxAction();
        });

        showVehicles_JMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, KeyEvent.SHIFT_DOWN_MASK));
        showVehicles_JMenuItem.addActionListener((event) ->
        {
            if (showVehicles_JMenuItem.isSelected())
                Vehicles_JCheckBox.setSelected(true);
            else
                Vehicles_JCheckBox.setSelected(false);
            vehicles_CheckBoxAction();
        });

        showUniqueJumps_JMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_U, KeyEvent.SHIFT_DOWN_MASK));
        showUniqueJumps_JMenuItem.addActionListener((event) ->
        {
            if (showUniqueJumps_JMenuItem.isSelected())
                UniqueJumps_JCheckBox.setSelected(true);
            else
                UniqueJumps_JCheckBox.setSelected(false);
            uniqueJumps_CheckBoxAction();
        });

        showHiddenPackages_JMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, KeyEvent.SHIFT_DOWN_MASK));
        showHiddenPackages_JMenuItem.addActionListener((event) ->
        {
            if (showHiddenPackages_JMenuItem.isSelected())
                HiddenPackages_JCheckBox.setSelected(true);
            else
                HiddenPackages_JCheckBox.setSelected(false);
            hiddenPackages_CheckBoxAction();
        });

        showSecrets_JMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, KeyEvent.SHIFT_DOWN_MASK));
        showSecrets_JMenuItem.addActionListener((event) ->
        {
            if (showSecrets_JMenuItem.isSelected())
                Secrets_JCheckBox.setSelected(true);
            else
                Secrets_JCheckBox.setSelected(false);
            secrets_CheckBoxAction();
        });

        goToNorthWestMap_JMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, KeyEvent.CTRL_DOWN_MASK));
        goToNorthWestMap_JMenuItem.addActionListener((event) ->
        {
            NorthWestPart_JButton_Action();
        });

        goToSouthWestMap_JMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_2, KeyEvent.CTRL_DOWN_MASK));
        goToSouthWestMap_JMenuItem.addActionListener((event) ->
        {
            southWestPart_JButton_Action();
        });

        goToNorthEastMap_JMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_3, KeyEvent.CTRL_DOWN_MASK));
        goToNorthEastMap_JMenuItem.addActionListener((event) ->
        {
            northEastPart_JButton_Action();
        });

        goToSouthEastMap_JMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_4, KeyEvent.CTRL_DOWN_MASK));
        goToSouthEastMap_JMenuItem.addActionListener((event) ->
        {
            southEastPart_JButton_Action();
        });

        goToLibrary_JMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, KeyEvent.CTRL_DOWN_MASK));
        goToLibrary_JMenuItem.addActionListener((event) ->
        {
            viceCityButton_Action(event);
        });

        showHowToWorkMessage_JMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0));
        showHowToWorkMessage_JMenuItem.addActionListener((event) ->
        {
            showHowToWorkMessage();
        });

        changeLanguage_MenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F3, 0));
        changeLanguage_MenuItem.addActionListener((event) ->
        {
            changeLanguage_MenuItemAction();
        });

        saveMap_JMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK));
        saveMap_JMenuItem.addActionListener((event) ->
        {
            saveMap_actionPerformed();
        });

        setUpScreenshotViewerSizePercent_JMenuItem.addActionListener((event) ->
        {
            setUpScreenshotViewerSizePercent();
        });

        aboutProgramm_JMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1, KeyEvent.CTRL_DOWN_MASK));
        aboutProgramm_JMenuItem.addActionListener((event) ->
        {
            AboutProgramm about_frame = new AboutProgramm();
            about_frame.setVisible(true);
        });

        openPageWithGitHubRepository.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_G, KeyEvent.CTRL_DOWN_MASK));
        openPageWithGitHubRepository.addActionListener((event) ->
        {
            try
            {
                Desktop.getDesktop().browse(new URI("https://github.com/YALdysse/vcHelper/tree/master"));
            }
            catch (URISyntaxException uriSynExc)
            {
                DebugTools.printDebugMessage("Некорретний формат ссылки на репозиторий github.");
                DebugTools.createLogFile(uriSynExc);
            }
            catch (IOException ioExc)
            {
                DebugTools.printDebugMessage("Возникла ошибка ввода-вывода при откритии ссылки на репозиторий gitHub.");
                DebugTools.createLogFile(ioExc);
            }
        });

        main_MenuBar.setSize(getWidth(), 15);
        main_MenuBar.add(file_Menu);
        main_MenuBar.add(show_Menu);
        main_MenuBar.add(goTo_Menu);
        main_MenuBar.setVisible(false);

        file_Menu.add(aboutProgramm_JMenuItem);
        file_Menu.add(openPageWithGitHubRepository);
        file_Menu.add(showHowToWorkMessage_JMenuItem);
        file_Menu.add(changeLanguage_MenuItem);
        file_Menu.add(setUpScreenshotViewerSizePercent_JMenuItem);
        file_Menu.add(saveMap_JMenuItem);
        file_Menu.add(exit_MenuItem);

        show_Menu.add(showDrugs_JMenuItem);
        show_Menu.add(showProperties_JMenuItem);
        show_Menu.add(showMedkit_JMenuItem);
        show_Menu.add(showArmor_JMenuItem);
        show_Menu.add(showBribes_JMenuItem);
        show_Menu.add(showStores_JMenuItem);
        show_Menu.add(showClothes_JMenuItem);
        show_Menu.add(showWeapons_JMenuItem);
        show_Menu.add(showSecrets_JMenuItem);
        show_Menu.add(showHiddenPackages_JMenuItem);
        show_Menu.add(showVehicles_JMenuItem);
        show_Menu.add(showUniqueJumps_JMenuItem);
        show_Menu.add(showSpraies_JMenuItem);
        show_Menu.add(showRampages_JMenuItem);

        goTo_Menu.add(goToStartScreen_MenuItem);
        goTo_Menu.add(goToNorthWestMap_JMenuItem);
        goTo_Menu.add(goToSouthWestMap_JMenuItem);
        goTo_Menu.add(goToNorthEastMap_JMenuItem);
        goTo_Menu.add(goToSouthEastMap_JMenuItem);
        goTo_Menu.add(goToLibrary_JMenuItem);

        add(main_MenuBar);

        initLocalization();

        addWindowListener(new WindowAdapter()
        {
            public void windowClosing(WindowEvent e)
            {
                try
                {
                    pref.removeNode();
                }
                catch (BackingStoreException backStoreExc)
                {
                    DebugTools.printDebugMessage("Возникла ошибка при удалении узла файла настроек.");
                    DebugTools.createLogFile(backStoreExc);
                }
                saveSetting();
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(PanelWithMapParts);
        PanelWithMapParts.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(selectMapPart_JLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 428, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(southWestPart_JButton, javax.swing.GroupLayout.Alignment.LEADING, 110, 150, Short.MAX_VALUE)
                                        .addComponent(northWestPart_JButton, javax.swing.GroupLayout.Alignment.LEADING, 110, 150, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(northEastPart_JButton, 110, 150, Short.MAX_VALUE)
                                        .addComponent(southEastPart_JButton, 110, 150, Short.MAX_VALUE))
                                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(selectMapPart_JLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(northWestPart_JButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(northEastPart_JButton, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(southWestPart_JButton, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                                        .addComponent(southEastPart_JButton, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE))
                                .addGap(90, 95, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(PanelWithMapParts, javax.swing.GroupLayout.PREFERRED_SIZE, 345, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(686, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(PanelWithMapParts, javax.swing.GroupLayout.PREFERRED_SIZE, 365, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(230, Short.MAX_VALUE))
        );
        pack();
    }

    private boolean saveMap_actionPerformed()
    {
        String[] mapListforSave = {"HQ original map by @joyo2", "HQ detailed map (original style) by @Huggito Baggio"};
        String result_str = (String) JOptionPane.showInputDialog(this, "Choose a map", saveMap_str, JOptionPane.QUESTION_MESSAGE, null, mapListforSave, mapListforSave[0]);
        DebugTools.printDebugMessage("При сохранении карты выбран вариант: " + result_str);

        int result_int = -1;

        DebugTools.printDebugMessage("Сохраняем " + mapListforSave[0]);
        String currentMap_str = null;

        try
        {
            File mapToSave_file = null;
            InputStream sourceFile_is = null;

            if (result_str.equals(mapListforSave[0]))
            {
                sourceFile_is = this.getClass().getClassLoader().getResource("Images/Vice_City_Map_original.png").openStream();
            }

            if (result_str.equals(mapListforSave[1]))
            {
                sourceFile_is = this.getClass().getClassLoader().getResource("Images/vice_city_map_fixed.png").openStream();
            }

            File outputFile = chooseSaveFolder();

            if (result_str.equals(mapListforSave[0]))
            {
                currentMap_str = "Vice_City_HQ_Map_by_joyo.png";
            }
            if (result_str.equals(mapListforSave[1]))
            {
                currentMap_str = "Vice_City_HQ_Detailed_Map_by_Huggito-Baggio.png";
            }

            Files.copy(sourceFile_is, new File(outputFile.getParent() + "/" + currentMap_str).toPath(), StandardCopyOption.REPLACE_EXISTING);

            DebugTools.printDebugMessage("Путь сохранения изображения:" + outputFile.getAbsolutePath());
            JOptionPane.showMessageDialog(this, "Image location: " + outputFile.getParent() + currentMap_str, "Image saved successfully", JOptionPane.INFORMATION_MESSAGE, null);
            return true;
        }
        catch (IOException ioExc)
        {
            DebugTools.printDebugMessage("Возникла ошибка ввода-вывода при сохранении файла изображения.");
            DebugTools.createLogFile(ioExc);
            return false;
        }
//        catch (URISyntaxException uriSynExc)
//        {
//            DebugTools.printDebugMessage("Возникла ошибка ");
//            DebugTools.createLogFile(uriSynExc);
//            return false;
//        }
    }

    public File chooseSaveFolder()
    {
        File output_file = null;

        JFileChooser jfchose = new JFileChooser(DebugTools.getJarLocation());
        int returnOfChooser = jfchose.showSaveDialog(this);
        output_file = jfchose.getSelectedFile();

        DebugTools.printDebugMessage("Каталог для сохранения файлов: " + output_file.getAbsolutePath());

        return output_file;
    }

    private void initNorthEastComponents(String imageName)
    {
        BufferedImage northWestPartMap_img = null;
        try
        {
            northWestPartMap_img = ImageIO.read(mainGui_ClassLoader.getResource("Images/" + imageName));
        }
        catch (IOException ioExc)
        {
            DebugTools.printDebugMessage("Ошибка ввода вывода при загрузке северо-западной части карты.\n" + ioExc);
            DebugTools.createLogFile(ioExc);
        }

        Map = new ImageComponent(this, northWestPartMap_img);
        legend_JPanel = new javax.swing.JPanel();
        Medkit_jCheckBox = new javax.swing.JCheckBox();
        Armor_JCheckBox = new javax.swing.JCheckBox();
        HiddenPackages_JCheckBox = new javax.swing.JCheckBox();
        UniqueJumps_JCheckBox = new javax.swing.JCheckBox();
        ViceCity_JButton = new javax.swing.JButton();
        Bribes_JCheckBox = new javax.swing.JCheckBox();
        Drugs_JCheckBox = new javax.swing.JCheckBox();
        Spraies_JCheckBox = new javax.swing.JCheckBox();
        Properties_JCheckBox = new javax.swing.JCheckBox();
        Rampages_JCheckBox = new javax.swing.JCheckBox();
        Clothes_JCheckBox = new javax.swing.JCheckBox();
        Weapons_JCheckBox = new javax.swing.JCheckBox();
        Stores_JCheckBox = new javax.swing.JCheckBox();
        Vehicles_JCheckBox = new javax.swing.JCheckBox();
        Secrets_JCheckBox = new javax.swing.JCheckBox();

        goToStartScreen_JButton = new javax.swing.JButton(pref.get("GoToStartScreen_JMenuItem", "Choose other part"));
        goToStartScreen_JButton.addActionListener((event) ->
        {
            goToStartScreen_Action();
        });

        int width = 600;
        int height = 540;

        //int tmpWidth = 555;
        //int tmpHeight = 540;

        if (isSouthEastMap)
        {
            width = 600;
            height = 540;
            //Map.setLegendOffsetToMap(32);
        }
        if (isNorthWestMap)
        {
            width = 635;
            height = 540;
            //Map.setLegendOffsetToMap(0);
        }
        if (isSouthWestMap)
        {
            width = 690;
            height = 540;
            //Map.setLegendOffsetToMap(0);
        }
        if (isNorthEastMap)
        {
            width = 575;
            height = 540;
            //Map.setLegendOffsetToMap(20);
        }

        //setPreferredSize(new java.awt.Dimension(frameWidth, frameHeight));
        setMinimumSize(new Dimension(width, height));
        setSize(new java.awt.Dimension(width, height));

        //Map.setBackground(new java.awt.Color(255, 153, 153));
        //Map.setLegend(legend_JPanel);
        mAction = new MouseAction();
        Map.addMouseListener(mAction);
        Map.setVisible(true);
        Map.setImage(northWestPartMap_img);


        legend_JPanel.setBackground(new Color(250, 231, 181, 193));
        legend_JPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(126, 153, 170), 5),
                BorderFactory.createEmptyBorder(2, 2, 2, 2)));
        //Map_JPanel.setLegendOffsetToMap(20);

        Medkit_jCheckBox.setText(medkit_str);
        Medkit_jCheckBox.setBackground(LEGEND_Color);
        //Medkit_jCheckBox.setForeground(viceCityFontColor);
        Medkit_jCheckBox.setPreferredSize(new java.awt.Dimension(130, 20));
        Medkit_jCheckBox.addActionListener((event) ->
        {
            medkit_CheckBoxAction();
        });


        Armor_JCheckBox.setText(armor_str);
        Armor_JCheckBox.setBackground(LEGEND_Color);
        //Armor_JCheckBox.setForeground(viceCityFontColor);
        Armor_JCheckBox.setPreferredSize(new java.awt.Dimension(130, 20));
        Armor_JCheckBox.addActionListener((event) ->
        {
            armor_CheckBoxAction();
        });

        HiddenPackages_JCheckBox.setText(hiddenPackages_str);
        HiddenPackages_JCheckBox.setBackground(LEGEND_Color);
        //HiddenPackages_JCheckBox.setForeground(viceCityFontColor);
        HiddenPackages_JCheckBox.setPreferredSize(new java.awt.Dimension(139, 20));
        HiddenPackages_JCheckBox.addActionListener((event) ->
        {
            hiddenPackages_CheckBoxAction();
        });

        UniqueJumps_JCheckBox.setText(uniqueJumps_str);
        UniqueJumps_JCheckBox.setBackground(LEGEND_Color);
        UniqueJumps_JCheckBox.setPreferredSize(new Dimension(130, 20));
        UniqueJumps_JCheckBox.addActionListener((event) ->
        {
            uniqueJumps_CheckBoxAction();
        });

        Bribes_JCheckBox.setText(bribes_str);
        Bribes_JCheckBox.setBackground(LEGEND_Color);
        //Bribes_JCheckBox.setForeground(viceCityFontColor);
        Bribes_JCheckBox.setPreferredSize(new Dimension(130, 20));
        Bribes_JCheckBox.addActionListener((event) ->
        {
            bribes_CheckBoxAction();
        });

        Drugs_JCheckBox.setText(drugs_str);
        Drugs_JCheckBox.setBackground(LEGEND_Color);
        //Drugs_JCheckBox.setForeground(viceCityFontColor);
        Drugs_JCheckBox.setPreferredSize(new Dimension(130, 20));
        Drugs_JCheckBox.addActionListener((event) ->
        {
            drugs_CheckBoxAction();
        });

        Spraies_JCheckBox.setText(sprays_str);
        Spraies_JCheckBox.setBackground(LEGEND_Color);
        Spraies_JCheckBox.setPreferredSize(new Dimension(130, 20));
        Spraies_JCheckBox.addActionListener((event) ->
        {
            spraies_CheckBoxAction();
        });

        Properties_JCheckBox.setText(properties_str);
        Properties_JCheckBox.setBackground(LEGEND_Color);
        Properties_JCheckBox.setPreferredSize(new Dimension(130, 20));
        Properties_JCheckBox.addActionListener((event) ->
        {
            properties_CheckBoxAction();
        });

        Rampages_JCheckBox.setText(rampages_str);
        Rampages_JCheckBox.setBackground(LEGEND_Color);
        Rampages_JCheckBox.setPreferredSize(new Dimension(130, 20));
        Rampages_JCheckBox.addActionListener((event) ->
        {
            rampages_CheckBoxAction();
        });

        Clothes_JCheckBox.setText(clothes_str);
        Clothes_JCheckBox.setBackground(LEGEND_Color);
        Clothes_JCheckBox.setPreferredSize(new Dimension(130, 20));
        Clothes_JCheckBox.addActionListener((event) ->
        {
            clothes_CheckBoxAction();
        });

        Weapons_JCheckBox.setText(weapons_str);
        Weapons_JCheckBox.setBackground(LEGEND_Color);
        Weapons_JCheckBox.setPreferredSize(new Dimension(130, 20));
        Weapons_JCheckBox.addActionListener((event) ->
        {
            weapons_CheckBoxAction();
        });

        Stores_JCheckBox.setText(stores_str);
        Stores_JCheckBox.setBackground(LEGEND_Color);
        Stores_JCheckBox.setPreferredSize(new Dimension(130, 20));
        Stores_JCheckBox.addActionListener((event) ->
        {
            stores_CheckBoxAction();
        });

        Vehicles_JCheckBox.setText(vehicles_str);
        Vehicles_JCheckBox.setBackground(LEGEND_Color);
        Vehicles_JCheckBox.setPreferredSize(new Dimension(130, 20));
        Vehicles_JCheckBox.addActionListener((event) ->
        {
            vehicles_CheckBoxAction();
        });

        Secrets_JCheckBox.setText(secrets_str);
        Secrets_JCheckBox.setBackground(LEGEND_Color);
        Secrets_JCheckBox.setPreferredSize(new Dimension(130, 20));
        Secrets_JCheckBox.addActionListener((event) ->
        {
            secrets_CheckBoxAction();
        });

        ViceCity_JButton.setText("Vice City");
        Font montana = null;
        File montanaFontFile = null;
        try
        {
            InputStream montanaFile_is = mainGui_ClassLoader.getResource("Fonts/" + "[Roman]MontanaRoughTypeface.ttf").openStream();
            //montanaFontFile = new File(DebugTools.getJarLocation().getParent() + "/Fonts/[Roman]MontanaRoughTypeface.ttf");
            montana = Font.createFont(Font.TRUETYPE_FONT, montanaFile_is);
            montana = montana.deriveFont(22.0f);

            //=================
            String fontName = "Casual Contact MF.ttf";
            String fontNameToolTipDescription = "20648.ttf";
            InputStream casualFontFile_is = mainGui_ClassLoader.getResource("Fonts/" + fontName).openStream();
            //File fontFile = new File(DebugTools.getJarLocation().getParent() + "/Fonts/" + fontName);
            casualContactMF = null;
            toolTipDescription = null;
            try
            {
                casualContactMF = Font.createFont(Font.PLAIN, casualFontFile_is);
                toolTipDescription = Font.createFont(Font.PLAIN, mainGui_ClassLoader.getResource("Fonts/" + fontNameToolTipDescription).openStream());
            }
            catch (IOException IOExc)
            {

            }
            catch (FontFormatException fontFormExc)
            {

            }
            //Font tmpFont = Font.decode(Font.MONOSPACED);
            casualContactMF = casualContactMF.deriveFont(Font.PLAIN, 14.0f);
            toolTipDescription = toolTipDescription.deriveFont(Font.PLAIN, 14.0f);
        }
        catch (IOException ioExc)
        {
            DebugTools.printDebugMessage("Ошибка при загрузке шрифта: " + montanaFontFile.getAbsolutePath());
            DebugTools.createLogFile(ioExc);
        }
        catch (FontFormatException fontFormExc)
        {
            DebugTools.printDebugMessage("Ошибка формата шрифта.");
            DebugTools.createLogFile(fontFormExc);
        }

        ViceCity_JButton.setFont(montana);
        ViceCity_JButton.setBackground(LEGEND_Color);
        ViceCity_JButton.setForeground(viceCityFontColor);


        ViceCity_JButton.addActionListener((event) ->
        {
            viceCityButton_Action(event);
        });

        //add(Map);

        javax.swing.GroupLayout legend_JPanelLayout = new javax.swing.GroupLayout(legend_JPanel);
        legend_JPanel.setLayout(legend_JPanelLayout);
        legend_JPanelLayout.setHorizontalGroup(
                legend_JPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(ViceCity_JButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(legend_JPanelLayout.createSequentialGroup()
                                .addGroup(legend_JPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(Armor_JCheckBox)
                                        .addComponent(HiddenPackages_JCheckBox)
                                        .addComponent(UniqueJumps_JCheckBox)
                                        .addComponent(Bribes_JCheckBox)
                                        .addComponent(Drugs_JCheckBox)
                                        .addComponent(Spraies_JCheckBox)
                                        .addComponent(Properties_JCheckBox)
                                        .addComponent(Rampages_JCheckBox)
                                        .addComponent(Clothes_JCheckBox)
                                        .addComponent(Weapons_JCheckBox)
                                        .addComponent(Stores_JCheckBox)
                                        .addComponent(Vehicles_JCheckBox)
                                        .addComponent(UniqueJumps_JCheckBox)
                                        .addComponent(HiddenPackages_JCheckBox)
                                        .addComponent(Secrets_JCheckBox)
                                        .addComponent(Medkit_jCheckBox)
                                        .addComponent(goToStartScreen_JButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(0, 0, Short.MAX_VALUE))
        );
        legend_JPanelLayout.setVerticalGroup(
                legend_JPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, legend_JPanelLayout.createSequentialGroup()
                                .addComponent(ViceCity_JButton)
                                .addComponent(Drugs_JCheckBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Properties_JCheckBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Medkit_jCheckBox, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Armor_JCheckBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Bribes_JCheckBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Stores_JCheckBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Clothes_JCheckBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Weapons_JCheckBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Secrets_JCheckBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(HiddenPackages_JCheckBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Vehicles_JCheckBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(UniqueJumps_JCheckBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Spraies_JCheckBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Rampages_JCheckBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(HiddenPackages_JCheckBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(UniqueJumps_JCheckBox)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 318, Short.MAX_VALUE)
                                .addComponent(goToStartScreen_JButton)));

        javax.swing.GroupLayout Map_JPanelLayout = new javax.swing.GroupLayout(Map);
        Map.setLayout(Map_JPanelLayout);
        Map_JPanelLayout.setHorizontalGroup(
                Map_JPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Map_JPanelLayout.createSequentialGroup()
                                .addGap(0, 586, Short.MAX_VALUE)
                                .addComponent(legend_JPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        Map_JPanelLayout.setVerticalGroup(
                Map_JPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(legend_JPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());

        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(Map, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(Map, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())
        );
        DebugTools.printDebugMessage("Размер фрейма: " + this.getSize().getWidth() + "x" + this.getSize().getHeight());

        pack();
    }

//    private void initNorthEastComponents(String imageName)
//    {
//        BufferedImage northWestPartMap_img = null;
//        try
//        {
//            northWestPartMap_img = ImageIO.read(mainGui_ClassLoader.getResource("Images/" + imageName));
//        }
//        catch (IOException ioExc)
//        {
//            DebugTools.printDebugMessage("Ошибка ввода вывода при загрузке северо-западной части карты.\n" + ioExc);
//            DebugTools.createLogFile(ioExc);
//        }
//
//        //Map = new ImageComponent(this, northWestPartMap_img);
//        legend_JPanel = new javax.swing.JPanel();
//        Medkit_jCheckBox = new javax.swing.JCheckBox();
//        Armor_JCheckBox = new javax.swing.JCheckBox();
//        HiddenPackages_JCheckBox = new javax.swing.JCheckBox();
//        UniqueJumps_JCheckBox = new javax.swing.JCheckBox();
//        ViceCity_JButton = new javax.swing.JButton();
//        Bribes_JCheckBox = new javax.swing.JCheckBox();
//        Drugs_JCheckBox = new javax.swing.JCheckBox();
//        Spraies_JCheckBox = new javax.swing.JCheckBox();
//        Properties_JCheckBox = new javax.swing.JCheckBox();
//        Rampages_JCheckBox = new javax.swing.JCheckBox();
//        Clothes_JCheckBox = new javax.swing.JCheckBox();
//        Weapons_JCheckBox = new javax.swing.JCheckBox();
//        Stores_JCheckBox = new javax.swing.JCheckBox();
//        Vehicles_JCheckBox = new javax.swing.JCheckBox();
//        Secrets_JCheckBox = new javax.swing.JCheckBox();
//
//        goToStartScreen_JButton = new javax.swing.JButton(pref.get("GoToStartScreen_JMenuItem", "Choose other part"));
//        goToStartScreen_JButton.addActionListener((event) ->
//        {
//            goToStartScreen_Action();
//        });
//
//        int width = 600;
//        int height = 540;
//
//        //int tmpWidth = 555;
//        //int tmpHeight = 540;
//
//        if (isSouthEastMap)
//        {
//            width = 600;
//            height = 540;
//            //Map.setLegendOffsetToMap(32);
//        }
//        if (isNorthWestMap)
//        {
//            width = 635;
//            height = 540;
//            //Map.setLegendOffsetToMap(0);
//        }
//        if (isSouthWestMap)
//        {
//            width = 690;
//            height = 540;
//            //Map.setLegendOffsetToMap(0);
//        }
//        if (isNorthEastMap)
//        {
//            width = 575;
//            height = 540;
//            //Map.setLegendOffsetToMap(20);
//        }
//
//        //setPreferredSize(new java.awt.Dimension(frameWidth, frameHeight));
//        setMinimumSize(new Dimension(width, height));
//        setSize(new java.awt.Dimension(width, height));
//
//        //Map.setBackground(new java.awt.Color(255, 153, 153));
//        //Map.setLegend(legend_JPanel);
////        mAction = new MouseAction();
////        Map.addMouseListener(mAction);
////        Map.setVisible(true);
////        Map.setImage(northWestPartMap_img);
//
//
//        legend_JPanel.setBackground(new Color(250, 231, 181, 193));
//        legend_JPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(126, 153, 170), 5),
//                BorderFactory.createEmptyBorder(2, 2, 2, 2)));
//        //Map_JPanel.setLegendOffsetToMap(20);
//
//        Medkit_jCheckBox.setText(medkit_str);
//        //Medkit_jCheckBox.setBackground(new Color(0, 0, 0, 0));
//        //Medkit_jCheckBox.setForeground(viceCityFontColor);
//        Medkit_jCheckBox.setPreferredSize(new java.awt.Dimension(130, 20));
//        Medkit_jCheckBox.addActionListener((event) ->
//        {
//            medkit_CheckBoxAction();
//        });
//
//
//        Armor_JCheckBox.setText(armor_str);
//        //Armor_JCheckBox.setBackground(TRANSPARENCY);
//        //Armor_JCheckBox.setForeground(viceCityFontColor);
//        Armor_JCheckBox.setPreferredSize(new java.awt.Dimension(130, 20));
//        Armor_JCheckBox.addActionListener((event) ->
//        {
//            armor_CheckBoxAction();
//        });
//
//        HiddenPackages_JCheckBox.setText(hiddenPackages_str);
//        //HiddenPackages_JCheckBox.setBackground(TRANSPARENCY);
//        //HiddenPackages_JCheckBox.setForeground(viceCityFontColor);
//        HiddenPackages_JCheckBox.setPreferredSize(new java.awt.Dimension(139, 20));
//        HiddenPackages_JCheckBox.addActionListener((event) ->
//        {
//            hiddenPackages_CheckBoxAction();
//        });
//
//        UniqueJumps_JCheckBox.setText(uniqueJumps_str);
//        //UniqueJumps_JCheckBox.setBackground(TRANSPARENCY);
//        UniqueJumps_JCheckBox.setPreferredSize(new Dimension(130, 20));
//        UniqueJumps_JCheckBox.addActionListener((event) ->
//        {
//            uniqueJumps_CheckBoxAction();
//        });
//
//        Bribes_JCheckBox.setText(bribes_str);
//        //Bribes_JCheckBox.setBackground(TRANSPARENCY);
//        //Bribes_JCheckBox.setForeground(viceCityFontColor);
//        Bribes_JCheckBox.setPreferredSize(new Dimension(130, 20));
//        Bribes_JCheckBox.addActionListener((event) ->
//        {
//            bribes_CheckBoxAction();
//        });
//
//        Drugs_JCheckBox.setText(drugs_str);
//        //Drugs_JCheckBox.setBackground(TRANSPARENCY);
//        //Drugs_JCheckBox.setForeground(viceCityFontColor);
//        Drugs_JCheckBox.setPreferredSize(new Dimension(130, 20));
//        Drugs_JCheckBox.addActionListener((event) ->
//        {
//            drugs_CheckBoxAction();
//        });
//
//        Spraies_JCheckBox.setText(sprays_str);
//        //Spraies_JCheckBox.setBackground(TRANSPARENCY);
//        Spraies_JCheckBox.setPreferredSize(new Dimension(130, 20));
//        Spraies_JCheckBox.addActionListener((event) ->
//        {
//            spraies_CheckBoxAction();
//        });
//
//        Properties_JCheckBox.setText(properties_str);
//        //Properties_JCheckBox.setBackground(TRANSPARENCY);
//        Properties_JCheckBox.setPreferredSize(new Dimension(130, 20));
//        Properties_JCheckBox.addActionListener((event) ->
//        {
//            properties_CheckBoxAction();
//        });
//
//        Rampages_JCheckBox.setText(rampages_str);
//        //Rampages_JCheckBox.setBackground(TRANSPARENCY);
//        Rampages_JCheckBox.setPreferredSize(new Dimension(130, 20));
//        Rampages_JCheckBox.addActionListener((event) ->
//        {
//            rampages_CheckBoxAction();
//        });
//
//        Clothes_JCheckBox.setText(clothes_str);
//        //Clothes_JCheckBox.setBackground(TRANSPARENCY);
//        Clothes_JCheckBox.setPreferredSize(new Dimension(130, 20));
//        Clothes_JCheckBox.addActionListener((event) ->
//        {
//            clothes_CheckBoxAction();
//        });
//
//        Weapons_JCheckBox.setText(weapons_str);
//        //Weapons_JCheckBox.setBackground(TRANSPARENCY);
//        Weapons_JCheckBox.setPreferredSize(new Dimension(130, 20));
//        Weapons_JCheckBox.addActionListener((event) ->
//        {
//            weapons_CheckBoxAction();
//        });
//
//        Stores_JCheckBox.setText(stores_str);
//        //Stores_JCheckBox.setBackground(TRANSPARENCY);
//        Stores_JCheckBox.setPreferredSize(new Dimension(130, 20));
//        Stores_JCheckBox.addActionListener((event) ->
//        {
//            stores_CheckBoxAction();
//        });
//
//        Vehicles_JCheckBox.setText(vehicles_str);
//        //Vehicles_JCheckBox.setBackground(TRANSPARENCY);
//        Vehicles_JCheckBox.setPreferredSize(new Dimension(130, 20));
//        Vehicles_JCheckBox.addActionListener((event) ->
//        {
//            vehicles_CheckBoxAction();
//        });
//
//        Secrets_JCheckBox.setText(secrets_str);
//        //Secrets_JCheckBox.setBackground(TRANSPARENCY);
//        Secrets_JCheckBox.setPreferredSize(new Dimension(130, 20));
//        Secrets_JCheckBox.addActionListener((event) ->
//        {
//            secrets_CheckBoxAction();
//        });
//
//        ViceCity_JButton.setText("Vice City");
//        Font montana = null;
//        File montanaFontFile = null;
//        try
//        {
//            InputStream montanaFile_is = mainGui_ClassLoader.getResource("Fonts/" + "[Roman]MontanaRoughTypeface.ttf").openStream();
//            //montanaFontFile = new File(DebugTools.getJarLocation().getParent() + "/Fonts/[Roman]MontanaRoughTypeface.ttf");
//            montana = Font.createFont(Font.TRUETYPE_FONT, montanaFile_is);
//            montana = montana.deriveFont(22.0f);
//
//            //=================
//            String fontName = "Casual Contact MF.ttf";
//            String fontNameToolTipDescription = "20648.ttf";
//            InputStream casualFontFile_is = mainGui_ClassLoader.getResource("Fonts/" + fontName).openStream();
//            //File fontFile = new File(DebugTools.getJarLocation().getParent() + "/Fonts/" + fontName);
//            casualContactMF = null;
//            toolTipDescription = null;
//            try
//            {
//                casualContactMF = Font.createFont(Font.PLAIN, casualFontFile_is);
//                toolTipDescription = Font.createFont(Font.PLAIN, mainGui_ClassLoader.getResource("Fonts/" + fontNameToolTipDescription).openStream());
//            }
//            catch (IOException IOExc)
//            {
//
//            }
//            catch (FontFormatException fontFormExc)
//            {
//
//            }
//            //Font tmpFont = Font.decode(Font.MONOSPACED);
//            casualContactMF = casualContactMF.deriveFont(Font.PLAIN, 14.0f);
//            toolTipDescription = toolTipDescription.deriveFont(Font.PLAIN, 14.0f);
//        }
//        catch (IOException ioExc)
//        {
//            DebugTools.printDebugMessage("Ошибка при загрузке шрифта: " + montanaFontFile.getAbsolutePath());
//            DebugTools.createLogFile(ioExc);
//        }
//        catch (FontFormatException fontFormExc)
//        {
//            DebugTools.printDebugMessage("Ошибка формата шрифта.");
//            DebugTools.createLogFile(fontFormExc);
//        }
//
//        ViceCity_JButton.setFont(montana);
//        //ViceCity_JButton.setBackground(new Color(1, 1, 1, 0));
//        ViceCity_JButton.setForeground(viceCityFontColor);
//
//
//        ViceCity_JButton.addActionListener((event) ->
//        {
//
//        });
//
//        //add(Map);
//
//        //================================
//        if (System.getProperty("os.name").
//
//                contains("Windows"))
//
//        {
//            fixGraphicsArtifactsOnWindows = 1;
//            DebugTools.printDebugMessage("Исправление графических артефактов под Windows включено.");
//        }
//        if (fixGraphicsArtifactsOnWindows == 11)
//
//        {
//            DebugTools.printDebugMessage("Исправление графических артефактов под Windows включено.");
//            Vehicles_JCheckBox.addMouseMotionListener(new MouseMotionAdapter()
//            {
//                @Override
//                public void mouseMoved(MouseEvent e)
//                {
//                    DebugTools.printDebugMessage("Vehicle");
//                    Map.repaint();
//                }
//            });
//
//            Medkit_jCheckBox.addMouseMotionListener(new MouseMotionAdapter()
//            {
//                @Override
//                public void mouseMoved(MouseEvent e)
//                {
//
//                    DebugTools.printDebugMessage("Medkit");
//                    Map.repaint();
//                }
//            });
//
//            Armor_JCheckBox.addMouseMotionListener(new MouseMotionAdapter()
//            {
//                @Override
//                public void mouseMoved(MouseEvent e)
//                {
//                    DebugTools.printDebugMessage("Armor");
//                    Map.repaint();
//                }
//            });
//
//            Bribes_JCheckBox.addMouseMotionListener(new MouseMotionAdapter()
//            {
//                @Override
//                public void mouseMoved(MouseEvent e)
//                {
//                    DebugTools.printDebugMessage("Bribes");
//                    Map.repaint();
//                }
//            });
//
//            Drugs_JCheckBox.addMouseMotionListener(new MouseMotionAdapter()
//            {
//                @Override
//                public void mouseMoved(MouseEvent e)
//                {
//                    DebugTools.printDebugMessage("Drugs");
//                    Map.repaint();
//                }
//            });
//
//            HiddenPackages_JCheckBox.addMouseMotionListener(new MouseMotionAdapter()
//            {
//                @Override
//                public void mouseMoved(MouseEvent e)
//                {
//                    DebugTools.printDebugMessage("Hidden Packages");
//                    Map.repaint();
//                }
//            });
//
//            Rampages_JCheckBox.addMouseMotionListener(new MouseMotionAdapter()
//            {
//                @Override
//                public void mouseMoved(MouseEvent e)
//                {
//                    DebugTools.printDebugMessage("Rampages");
//                    Map.repaint();
//                }
//            });
//
//            UniqueJumps_JCheckBox.addMouseMotionListener(new MouseMotionAdapter()
//            {
//                @Override
//                public void mouseMoved(MouseEvent e)
//                {
//                    Map.repaint();
//                }
//            });
//
//            Stores_JCheckBox.addMouseMotionListener(new MouseMotionAdapter()
//            {
//                @Override
//                public void mouseMoved(MouseEvent e)
//                {
//                    Map.repaint();
//                }
//            });
//
//            Clothes_JCheckBox.addMouseMotionListener(new MouseMotionAdapter()
//            {
//                @Override
//                public void mouseMoved(MouseEvent e)
//                {
//                    DebugTools.printDebugMessage("Clothes");
//                    Map.repaint();
//                }
//            });
//
//            Properties_JCheckBox.addMouseMotionListener(new MouseMotionAdapter()
//            {
//                @Override
//                public void mouseMoved(MouseEvent e)
//                {
//                    DebugTools.printDebugMessage("Properties");
//                    Map.repaint();
//                }
//            });
//
//            Weapons_JCheckBox.addMouseMotionListener(new MouseMotionAdapter()
//            {
//                @Override
//                public void mouseMoved(MouseEvent e)
//                {
//                    DebugTools.printDebugMessage("Weapons");
//                    Map.repaint();
//                }
//            });
//
//            Spraies_JCheckBox.addMouseMotionListener(new MouseMotionAdapter()
//            {
//                @Override
//                public void mouseMoved(MouseEvent e)
//                {
//                    DebugTools.printDebugMessage("Spray");
//                    Map.repaint();
//                }
//            });
//            Secrets_JCheckBox.addMouseMotionListener(new MouseMotionAdapter()
//            {
//                @Override
//                public void mouseMoved(MouseEvent e)
//                {
//                    DebugTools.printDebugMessage("Secrets");
//                    Map.repaint();
//                }
//            });
//            ViceCity_JButton.addMouseMotionListener(new MouseMotionAdapter()
//            {
//                @Override
//                public void mouseMoved(MouseEvent e)
//                {
//                    DebugTools.printDebugMessage("Перерисовка ViceCity Button");
//                    Map.repaint();
//                }
//            });
//        }
//        //================================
//
//        legend_JPanel.setSize(300,600);
//        legend_JPanel.setLayout(new FlowLayout());
//        legend_JPanel.add(Medkit_jCheckBox);
//        legend_JPanel.add(Armor_JCheckBox);
//        legend_JPanel.add(Properties_JCheckBox);
//        legend_JPanel.add(Vehicles_JCheckBox);
//        legend_JPanel.add(Weapons_JCheckBox);
//        legend_JPanel.add(Stores_JCheckBox);
//        legend_JPanel.add(HiddenPackages_JCheckBox);
//        legend_JPanel.add(UniqueJumps_JCheckBox);
//        legend_JPanel.add(Rampages_JCheckBox);
//        legend_JPanel.add(Secrets_JCheckBox);
//
//        add(legend_JPanel);
//    }

    private void changeLanguage_MenuItemAction()
    {
        String[] languages_str = {"English", "Русский"};

        String obj = (String) JOptionPane.showInputDialog(this, "Select language: ", "Change Language", JOptionPane.QUESTION_MESSAGE, null, languages_str, languages_str);

        if (obj == null)
        {
            return;
        }
        DebugTools.printDebugMessage(obj);

        if (obj.equals("Русский"))
        {
            currentLanguage_str = "Russian";

        } else
            currentLanguage_str = obj;
        initLocalization();
        //JOptionPane.showInputDialog(this, "Select language:", "fjd", JOptionPane.QUESTION_MESSAGE, null, languages_str)''
    }

    private void setUpScreenshotViewerSizePercent()
    {
        while (true)
        {
            String hren = JOptionPane.showInputDialog(this, "Текущее значение: " + screenshotViewerSizePercent + "% экрана.", "Значение от 10 до 90");

            try
            {

                int tmp = Integer.parseInt(hren);
                if (tmp >= 10 && tmp <= 90)
                {
                    screenshotViewerSizePercent = tmp;
                    DebugTools.printDebugMessage("Указан новый размер просмотрщика изображений: " + screenshotViewerSizePercent + "%");
                    break;
                } else
                    throw new NumberFormatException();

            }
            catch (NumberFormatException NumFormExc)
            {
                DebugTools.printDebugMessage("Указано неверное значение для размера просмотрщика изображений.");
                JOptionPane.showMessageDialog(this, "Значение размеров окна просмотрщика задается в процентах от разрешения монитора.\nОно должно находится в пределах 10 <= N <= 90", "", JOptionPane.WARNING_MESSAGE, null);
            }
        }
    }

    private void importSettingFile()
    {
        try
        {
            pref = Preferences.userRoot().node("Vice_City_Helper");
            pref.importPreferences(new FileInputStream(DebugTools.getJarLocation().getParent() + "/preference.xml"));
            DebugTools.printDebugMessage("Путь к файлу настроек: " + pref.absolutePath());
            int x = pref.getInt(FrameX, 0);
            int y = pref.getInt(FrameY, 0);
            firstTimeOnMap = pref.getInt("showTutorialOnMap", 1);
            currentLanguage_str = pref.get("Language", "English");
            screenshotViewerSizePercent = pref.getInt("ScreenshotViewerSizePercent", 45);
            startScreenFontName = pref.get("startScreenFontFileName", "Rage_Italic_LET_Plain_1.0.ttf");
            countCharInLine = pref.getInt("CountCharInLine", 30);
            setLocation(x, y);
        }
        catch (IOException ioExc)
        {
            DebugTools.printDebugMessage("Ошибка ввода-вывода при импорте файла настроек.");
            //DebugTools.createLogFile(ioExc);
        }
        catch (InvalidPreferencesFormatException invalidPrefFormExc)
        {

        }
    }

    private void saveSetting()
    {
        Preferences prefSave = Preferences.userRoot().node("Vice_City_Helper");
        DebugTools.printDebugMessage("Путь к файлу настроек: " + pref.absolutePath());
        prefSave.put("Warning", "Куда полез ? - А ну закрывай!");
        prefSave.putInt(FrameX, getX());
        prefSave.putInt(FrameY, getY());
        prefSave.putInt("showTutorialOnMap", 0);
        prefSave.put("startScreenFontFileName", startScreenFontName);
        prefSave.put("Language", currentLanguage_str);
        prefSave.putInt("ScreenshotViewerSizePercent", screenshotViewerSizePercent);
        prefSave.putInt("CountCharInLine", countCharInLine);
        try
        {
            prefSave.exportNode(new FileOutputStream(new File(DebugTools.getJarLocation().getParent() + "/preference.xml")));
            prefSave.removeNode();
        }
        catch (FileNotFoundException fnfExc)
        {
            DebugTools.printDebugMessage("Ошибка при экспорте файла настроек по пути.");
            DebugTools.createLogFile(fnfExc);
        }
        catch (IOException fnfExc)
        {
            DebugTools.printDebugMessage("Ошибка ввода-вывода при экспорте файла настроек.");
            DebugTools.createLogFile(fnfExc);
        }
        catch (BackingStoreException backStoreExc)
        {

        }
    }

    private void initLocalization()
    {
        Preferences localPref = Preferences.userRoot().node("ViceCityHelper.localization");
        File langFile = null;
        try
        {
            URL langFile_url = mainGui_ClassLoader.getResource("languages/" + currentLanguage_str + ".lang");
            InputStream lang_inputStream = langFile_url.openStream();
            //FileInputStream fis = new Fi

            //langFile = new File(DebugTools.getJarLocation().getParent() + "/languages/" + currentLanguage_str + ".lang");
            //URL langFileURL = mainGui_ClassLoader.getResource("languages/" + currentLanguage_str + ".lang");

//            try
//            {
//                //langFile = new File(langFileURL.toURI());
//            }
//            catch (URISyntaxException uir)
//            {
//                DebugTools.printDebugMessage("Возникла ошибка при загрузко файла локализации.");
//                DebugTools.createLogFile(uir);
//            }
            //DebugTools.printDebugMessage("Поиск файла локализкации: " + langFile.getAbsolutePath());
            DebugTools.printDebugMessage("Поиск файла локализкации: " + langFile_url.toExternalForm());
            //localPref.importPreferences(new FileInputStream(langFile));
            localPref.importPreferences(lang_inputStream);


            file_Menu.setText(localPref.get("File_Menu", "File"));
            exit_MenuItem.setText(localPref.get("Exit_MenuItem", "Exit"));
            showHowToWorkMessage_JMenuItem.setText(localPref.get("HowToWork_MenuItem", "How To Work with Map"));
            howToWorkWithMap_str = localPref.get("HowToWorkWithMap_str", "Press LMB to delete pickup or RMB to show screenshot.");
            howToWorkWithMap_title = localPref.get("HowToWorkWithMap_title", "How to work with Map");
            aboutProgramm_JMenuItem.setText(localPref.get("About_MenuItem", "About"));
            saveMap_JMenuItem.setText(localPref.get("SaveMap_JMenuItem", "Save The Map"));
            stores_str = localPref.get("Stores_JCheckBox", "Stores");
            weapons_str = localPref.get("Weapons_JCheckBox", "Weapons");
            rampages_str = localPref.get("Rampages_JCheckBox", "Rampages");
            armor_str = localPref.get("Armors_JCheckBox", "Armors");
            medkit_str = localPref.get("Health_JCheckBox", "Health");
            bribes_str = localPref.get("Bribes_JCheckBox", "Bribes");
            drugs_str = localPref.get("Drugs_JCheckBox", "Drugs");
            properties_str = localPref.get("Properties_JCheckBox", "Properties");
            vehicles_str = localPref.get("Vehicles_JCheckBox", "Vehicles");
            uniqueJumps_str = localPref.get("UniqueJumps_JCheckBox", "Unique Jumps");
            clothes_str = localPref.get("Clothes_JCheckBox", "Clothes");
            hiddenPackages_str = localPref.get("HiddenPackages_JCheckBox", "Hidden Packages");
            secrets_str = localPref.get("Secrets_JCheckBox", "Secrets");


            molotov_weapon_str = localPref.get("Molotov_weapon", "Molotov cocktail");
            knuckle_weapon_str = localPref.get("Knuckle_weapon", "Knuckle");
            meatCleaver_weapon_str = localPref.get("MeatCleaver_weapon", "Meat cleaver");
            nightStick_weapon_str = "Night stick";
            golfClub_weapon_str = localPref.get("GolfClub_weapon", "Golf club");
            knife_weapon_str = localPref.get("Knife_weapon", "Knife");
            katana_weapon_str = localPref.get("Katana_weapon", "Katana");
            chainsaw_weapon_str = localPref.get("Chainsaw_weapon", "Сhainsaw");
            grenades_weapon_str = localPref.get("Grenades_weapon", "Grenades");
            remoteGrenades_weapon_str = localPref.get("RemoteGrenades_weapon", "Remote grenades");
            baseballBat_weapon_str = localPref.get("BaseballBat_weapon", "Baseball bat");
            pistol_weapon_str = localPref.get("Colt.45_weapon", "Colt .45");
            uzi_weapon_str = localPref.get("Uzi_weapon", "Uzi");
            shotgun_weapon_set = localPref.get("Shotgun_weapon", "Shotgun");
            machete_weapon_str = localPref.get("Machete_weapon", "Machete");
            ruger_weapon_str = localPref.get("Ruger_weapon", "Ruger");
            stubbyShotgun_weapon_str = localPref.get("StubbyShotgun_weapon", "Stubby shotgun");
            minigun_weapon_str = "Minigun";
            rocketLauncher_weapon_str = localPref.get("RocketLauncher_weapon", "Rocket launcher");
            sniperRiffle_weapon_str = localPref.get("SniperRiffle_weapon", "Sniper rifle");
            mac_weapon_str = localPref.get("Mac_weapon", "Mac");
            flamethrower_weapon_str = localPref.get("Flamethrower_weapon", "Flamethrower");
            coltPython_weapon_str = localPref.get("ColtPython_weapon", ".357 (Colt Python)");

            secret_0_sunkenReefer = localPref.get("Secret_0_sunkenReefer", "The sunken Reefer.");
            secret_1_fatManUnderWater = localPref.get("Secret_1_fatManUnderWater", "The Fat man wearing cement shoes can be seen underwater.");
            secret_2_vehicleUnderWater = localPref.get("Secret_2_vehicleUnderWater", secret_2_vehicleUnderWater);
            secret_3_easterEgg = localPref.get("Secret_3_easterEgg", "There is a window where the player will find the 'Easter Egg Room' which is basically an actual brown easter egg on top of a pedestal.");
            secret_4_humansOrgans = localPref.get("Secret_4_humansOrgans", "There are organs of human (arm and brain) into the store in Little Haiti.");
            secret_5_lifeIsBitch = localPref.get("Secret_5_lifeIsBitch", "There is a billboard saying 'Life's a bitch', instead of 'Life's a beach'.");
            secret_6 = localPref.get("Secret_6", "There is a billboard saying 'Welcome to Hell' instead of 'Welcome to Haiti'.");
            secret_7_rockstarLogoPool = localPref.get("Secret_7_rockstarLogoPool", "The Shape of the pool is likeness Rockstar logo.");
            secret_8 = localPref.get("Secret_8", "The pool can be seen in the shape of a female body.");
            secret_9 = localPref.get("Secret_9", "You can play with the ball in this pool.");
            secret_10_manUnderWater = localPref.get("Secret_10_manUnderWater", "The Fat man wearing cement shoes can be seen underwater.");
            secret_11_submarine = localPref.get("Secret_11_submarine", "There is a submarine.");
            secret_12_sunkenContainerShip = localPref.get("Secret_12_sunkenContainerShip", "There is sunken 'Chartered Libertine Lines' container ships.");
            secret_13_dick = localPref.get("Secret_13_dick", "A pattern of lights on the Vice Point Langer building resembles a penis.");
            secret_14_apartment3c = localPref.get("Secret_14_apartment3c", "Apartment 3c");
            secret_15_sunkenContainerShip = localPref.get("Secret_15_sunkenContainerShip", "Another sunken 'Chartered Libertine Lines' container ships.");

            rampage_0 = localPref.get("Rampage_0", rampage_0);
            rampage_1 = localPref.get("Rampage_1", rampage_1);
            rampage_2 = localPref.get("Rampage_2", rampage_2);
            rampage_3 = localPref.get("Rampage_3", rampage_3);
            rampage_4 = localPref.get("Rampage_4", rampage_4);
            rampage_5 = localPref.get("Rampage_5", rampage_5);
            rampage_6 = localPref.get("Rampage_6", rampage_6);
            rampage_7 = localPref.get("Rampage_7", rampage_7);
            rampage_8 = localPref.get("Rampage_8", rampage_8);
            rampage_9 = localPref.get("Rampage_9", rampage_9);
            rampage_10 = localPref.get("Rampage_10", rampage_10);
            rampage_11 = localPref.get("Rampage_11", rampage_11);
            rampage_12 = localPref.get("Rampage_12", rampage_12);
            rampage_13 = localPref.get("Rampage_13", rampage_13);
            rampage_14 = localPref.get("Rampage_14", rampage_14);
            rampage_15 = localPref.get("Rampage_15", rampage_15);
            rampage_16 = localPref.get("Rampage_16", rampage_16);
            rampage_17 = localPref.get("Rampage_17", rampage_17);
            rampage_18 = localPref.get("Rampage_18", rampage_18);
            rampage_19 = localPref.get("Rampage_19", rampage_19);
            rampage_20 = localPref.get("Rampage_20", rampage_20);
            rampage_21 = localPref.get("Rampage_21", rampage_21);
            rampage_22 = localPref.get("Rampage_22", rampage_22);
            rampage_23 = localPref.get("Rampage_23", rampage_23);
            rampage_24 = localPref.get("Rampage_24", rampage_24);
            rampage_25 = localPref.get("Rampage_25", rampage_25);
            rampage_26 = localPref.get("Rampage_26", rampage_26);
            rampage_27 = localPref.get("Rampage_27", rampage_27);
            rampage_28 = localPref.get("Rampage_28", rampage_28);
            rampage_29 = localPref.get("Rampage_29", rampage_29);
            rampage_30 = localPref.get("Rampage_30", rampage_30);
            rampage_31 = localPref.get("Rampage_31", rampage_31);
            rampage_32 = localPref.get("Rampage_32", rampage_32);
            rampage_33 = localPref.get("Rampage_33", rampage_33);
            rampage_34 = localPref.get("Rampage_34", rampage_34);

            uniqueJump_0 = localPref.get("UniqueJump_0", "");
            uniqueJump_1 = localPref.get("UniqueJump_1", "");
            uniqueJump_2 = localPref.get("UniqueJump_2", "");
            uniqueJump_3 = localPref.get("UniqueJump_3", "");
            uniqueJump_4 = localPref.get("UniqueJump_4", "");
            uniqueJump_5 = localPref.get("UniqueJump_5", "");
            uniqueJump_6 = localPref.get("UniqueJump_6", "");
            uniqueJump_7 = localPref.get("UniqueJump_7", "");
            uniqueJump_8 = localPref.get("UniqueJump_8", "");
            uniqueJump_9 = localPref.get("UniqueJump_9", "");
            uniqueJump_10 = localPref.get("UniqueJump_10", "");
            uniqueJump_11 = localPref.get("UniqueJump_11", "");
            uniqueJump_12 = localPref.get("UniqueJump_12", "");
            uniqueJump_13 = localPref.get("UniqueJump_13", "");
            uniqueJump_14 = localPref.get("UniqueJump_14", "");
            uniqueJump_15 = localPref.get("UniqueJump_15", "");
            uniqueJump_16 = localPref.get("UniqueJump_16", "");
            uniqueJump_17 = localPref.get("UniqueJump_17", "");
            uniqueJump_18 = localPref.get("UniqueJump_18", "");
            uniqueJump_19 = localPref.get("UniqueJump_19", "");
            uniqueJump_20 = localPref.get("UniqueJump_20", "");
            uniqueJump_21 = localPref.get("UniqueJump_21", "");
            uniqueJump_22 = localPref.get("UniqueJump_22", "");
            uniqueJump_23 = localPref.get("UniqueJump_23", "");
            uniqueJump_24 = localPref.get("UniqueJump_24", "");
            uniqueJump_25 = localPref.get("UniqueJump_25", "");
            uniqueJump_26 = localPref.get("UniqueJump_26", "");
            uniqueJump_27 = localPref.get("UniqueJump_27", "");
            uniqueJump_28 = localPref.get("UniqueJump_28", "");
            uniqueJump_29 = localPref.get("UniqueJump_29", "");
            uniqueJump_30 = localPref.get("UniqueJump_30", "");
            uniqueJump_31 = localPref.get("UniqueJump_31", "");
            uniqueJump_32 = localPref.get("UniqueJump_32", "");
            uniqueJump_33 = localPref.get("UniqueJump_33", "");
            uniqueJump_34 = localPref.get("UniqueJump_34", "");
            uniqueJump_35 = localPref.get("UniqueJump_35", "");

            clothes_0_Street = localPref.get("Clothes_0_Street", "Street");
            clothes_1_Soiree = localPref.get("Clothes_1_Soiree", "Soiree");
            clothes_2_Coveralls = localPref.get("Clothes_2_Coveralls", "Coveralls");
            clothes_3_CountryClub = localPref.get("Clothes_3_CountryClub", "Country Club");
            clothes_4_Cop = localPref.get("Clothes_4_Cop", "Cop");
            clothes_5_BankJob = localPref.get("Clothes_5_Bank Job", "Bank Job");
            clothes_6_Casual = localPref.get("Clothes_6_Casual", "Casual");
            clothes_7_Vercetti = localPref.get("Clothes_7_Vercetti", "Mr. Vercetti");
            clothes_8_Tracksuit1 = localPref.get("Clothes_8_Tracksuit1", "Tracksuit #1");
            clothes_9_Tracksuit2 = localPref.get("Clothes_9_Tracksuit2", "Tracksuit #2");
            clothes_10_Havana = localPref.get("Clothes_10_Havana", "Havana");


            //===========================================u
            //updating text
            goTo_Menu.setText(localPref.get("GoTo_JMenu", "Go to ..."));
            goToSouthEastMap_JMenuItem.setText(localPref.get("SouthEast_JMenuItem", "South-East part"));
            goToSouthWestMap_JMenuItem.setText(localPref.get("SouthWest_JMenuItem", "South-West part"));
            goToNorthEastMap_JMenuItem.setText(localPref.get("NorthEast_JMenuItem", "North-East part"));
            goToNorthWestMap_JMenuItem.setText(localPref.get("NorthWest_JMenuItem", "North-West part"));
            goToStartScreen_MenuItem.setText(localPref.get("GoToStartScren_JMenuItem", "Start Screen"));
            setUpScreenshotViewerSizePercent_JMenuItem.setText(localPref.get("setUpScreenshotViewerSizePercent_JMenuItem", "Set up size of ScreenshotViewer"));
            goToLibrary_JMenuItem.setText(localPref.get("GoToLibrary_JMenuItem", "Library"));
            openPageWithGitHubRepository.setText(localPref.get("OpenPageWithGitHubRepository_JMenuItem", "Repository on GitHub"));
            northEastPart_JButton.setToolTipText(localPref.get("North_East_part_toolTip", "North-East part of Vice City"));
            northWestPart_JButton.setToolTipText(localPref.get("North_West_part_toolTip", "North-West part of Vice City"));
            southEastPart_JButton.setToolTipText(localPref.get("South_East_part_toolTip", "South-East part of Vice City"));
            southWestPart_JButton.setToolTipText(localPref.get("South_West_part_toolTip", "South-West part of Vice City"));

            showStores_JMenuItem.setText(stores_str);
            showWeapons_JMenuItem.setText(weapons_str);
            showRampages_JMenuItem.setText(rampages_str);
            showVehicles_JMenuItem.setText(vehicles_str);
            showUniqueJumps_JMenuItem.setText(uniqueJumps_str);
            showArmor_JMenuItem.setText(armor_str);
            showMedkit_JMenuItem.setText(medkit_str);
            showDrugs_JMenuItem.setText(drugs_str);
            showBribes_JMenuItem.setText(bribes_str);
            showClothes_JMenuItem.setText(clothes_str);
            showProperties_JMenuItem.setText(properties_str);
            showHiddenPackages_JMenuItem.setText(hiddenPackages_str);
            showSecrets_JMenuItem.setText(secrets_str);
            show_Menu.setText(localPref.get("Show_JMenu", "Show"));
            selectMapPart_JLabel.setText(localPref.get("ChooseMap_JLabel", "Choose part of the Map"));
            changeLanguage_MenuItem.setText(localPref.get("Language_JMenuItem", "Change Language"));

            if (Stores_JCheckBox != null)
            {
                Stores_JCheckBox.setText(stores_str);
                Weapons_JCheckBox.setText(weapons_str);
                Rampages_JCheckBox.setText(rampages_str);
                Vehicles_JCheckBox.setText(vehicles_str);
                UniqueJumps_JCheckBox.setText(uniqueJumps_str);
                Armor_JCheckBox.setText(armor_str);
                Medkit_jCheckBox.setText(medkit_str);
                Drugs_JCheckBox.setText(drugs_str);
                Bribes_JCheckBox.setText(bribes_str);
                Properties_JCheckBox.setText(properties_str);
                Clothes_JCheckBox.setText(clothes_str);
                HiddenPackages_JCheckBox.setText(hiddenPackages_str);
                Secrets_JCheckBox.setText(secrets_str);

                goToStartScreen_JButton.setText(localPref.get("GoToStartScren_JMenuItem", "Start Screen"));
            }

            //Stores_JCheckBox.setText(stores_str);
            //Stores_JCheckBox.setText(stores_str);
            //Stores_JCheckBox.setText(stores_str);
            //goTo_Menu.setText(localPref.get("GoTo_JMenu", "Go to ..."));
            //goTo_Menu.setText(localPref.get("GoTo_JMenu", "Go to ..."));
            //===========================================


            //exit_MenuItem.setText(localPref.get("Exit_MenuItem", "[error]"));
            //exit_MenuItem.setText(localPref.get("Exit_MenuItem", "[error]"));

            localPref.removeNode();
        }
        catch (IOException IOExc)
        {
            DebugTools.printDebugMessage("Ошибка при загрузке файла локализации.");
            JOptionPane.showMessageDialog(this, "Localization file not found.\nPath: " + langFile.getAbsolutePath(), "Error", JOptionPane.ERROR_MESSAGE);
            DebugTools.createLogFile(IOExc);
            System.exit(1);
        }
        catch (InvalidPreferencesFormatException InvalidPrefFormExc)
        {
            DebugTools.printDebugMessage("Неверный формат файла локализкации.");
            DebugTools.createLogFile(InvalidPrefFormExc);
        }
        catch (BackingStoreException BackStoreExc)
        {
            DebugTools.printDebugMessage("Ошибка при удалении узла локализации.");
            DebugTools.createLogFile(BackStoreExc);
        }

        if (Rampages_JCheckBox != null)
        {
            Rampages_JCheckBox.setSelected(false);
            Properties_JCheckBox.setSelected(false);
            Vehicles_JCheckBox.setSelected(false);
            Stores_JCheckBox.setSelected(false);
            Weapons_JCheckBox.setSelected(false);
            Secrets_JCheckBox.setSelected(false);

            if (rampageButton != null)
            {
                for (int k = 0; k < rampageButton.length; k++)
                {
                    rampageButton[k].setVisible(false);
                }
            }
            if (propertiesButton != null)
            {
                for (int k = 0; k < propertiesButton.length; k++)
                {
                    propertiesButton[k].setVisible(false);
                }
            }
            if (vehicleButton != null)
            {

                for (int k = 0; k < vehicleButton.length; k++)
                {
                    vehicleButton[k].setVisible(false);
                }
            }
            if (storeButton != null)
            {
                for (int k = 0; k < storeButton.length; k++)
                {
                    storeButton[k].setVisible(false);
                }
            }
            if (weaponButton != null)
            {
                for (int k = 0; k < weaponButton.length; k++)
                {
                    weaponButton[k].setVisible(false);
                }
            }
            if (secretsButton != null)
            {
                for (int k = 0; k < secretsButton.length; k++)
                {
                    secretsButton[k].setVisible(false);
                }
            }
        }

        if (showRampages_JMenuItem != null)
        {
            showRampages_JMenuItem.setSelected(false);
            showProperties_JMenuItem.setSelected(false);
            showVehicles_JMenuItem.setSelected(false);
            showStores_JMenuItem.setSelected(false);
            showWeapons_JMenuItem.setSelected(false);
            showSecrets_JMenuItem.setSelected(false);
        }

        if (firstTimeOnMap == 1)
        {
            showHowToWorkMessage();
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        java.awt.EventQueue.invokeLater(() ->
        {
            new main_gui().setVisible(true);
        });
    }

    private void NorthWestPart_JButton_Action()
    {
        getContentPane().setBackground(new java.awt.Color(148, 199, 246));
        //if (Map != null)

        // Map.setLegendOffsetToMap(0);

        isNorthWestMap = true;
        isNorthEastMap = false;
        isSouthWestMap = false;
        isSouthEastMap = false;

        goToSouthWestMap_JMenuItem.setEnabled(true);
        //goToSouthWestMap_JMenuItem.setBackground(viceCityFontColor);
        goToNorthEastMap_JMenuItem.setEnabled(true);
        goToSouthEastMap_JMenuItem.setEnabled(true);
        goToNorthWestMap_JMenuItem.setEnabled(false);

        int tmpWidth = 635;
        int tmpHeight = 540;

        hideNresetComponents();
        showSpraies_JMenuItem.setEnabled(true);

        //this.setResizable(true);

        if (Map == null)
            initNorthEastComponents("part1_razm.png");
        else
        {
            Map.setVisible(true);
            legend_JPanel.setVisible(true);
            ViceCity_JButton.setVisible(true);
            Armor_JCheckBox.setVisible(true);
            HiddenPackages_JCheckBox.setVisible(true);
            UniqueJumps_JCheckBox.setVisible(true);
            Medkit_jCheckBox.setVisible(true);
            Rampages_JCheckBox.setVisible(true);
            goToStartScreen_JButton.setVisible(true);

            setMinimumSize(new Dimension(tmpWidth, tmpHeight));
            setPreferredSize(new java.awt.Dimension(tmpWidth, tmpHeight));
            setSize(tmpWidth, tmpHeight);

            try
            {
                BufferedImage tmpImg = ImageIO.read(mainGui_ClassLoader.getResource("Images/part1_razm.png"));
                Map.setImage(tmpImg);
                //pack();
            }
            catch (IOException ioExc)
            {
                DebugTools.printDebugMessage("Ошибка ввода-вывода при загрузке части карты.\n" + ioExc.toString());
                DebugTools.createLogFile(ioExc);
            }
        }

        if (firstTimeOnMap == 1)
        {
            showHowToWorkMessage();
        }
        DebugTools.printDebugMessage("Размер фрейма: " + this.getSize().getWidth() + "x" + this.getSize().getHeight());
    }

    private void northEastPart_JButton_Action()
    {
        getContentPane().setBackground(new java.awt.Color(148, 199, 246));
        //if (Map != null)
        //Map.setLegendOffsetToMap(0);

        isNorthWestMap = false;
        isNorthEastMap = true;
        isSouthWestMap = false;
        isSouthEastMap = false;

        goToSouthWestMap_JMenuItem.setEnabled(true);
        //goToSouthWestMap_JMenuItem.setBackground(viceCityFontColor);
        goToNorthEastMap_JMenuItem.setEnabled(true);
        goToSouthEastMap_JMenuItem.setEnabled(false);
        goToNorthWestMap_JMenuItem.setEnabled(true);

        int tmpWidth = 575;
        int tmpHeight = 540;

        hideNresetComponents();
        showSpraies_JMenuItem.setEnabled(true);

        //this.setResizable(true);

        if (Map == null)
            initNorthEastComponents("part3_razm.png");
        else
        {
            Map.setVisible(true);
            legend_JPanel.setVisible(true);
            ViceCity_JButton.setVisible(true);
            Armor_JCheckBox.setVisible(true);
            HiddenPackages_JCheckBox.setVisible(true);
            UniqueJumps_JCheckBox.setVisible(true);
            Medkit_jCheckBox.setVisible(true);
            Rampages_JCheckBox.setVisible(true);
            goToStartScreen_JButton.setVisible(true);

            setMinimumSize(new Dimension(tmpWidth, tmpHeight));
            setPreferredSize(new java.awt.Dimension(tmpWidth, tmpHeight));
            setSize(tmpWidth, tmpHeight);

            try
            {
                BufferedImage tmpImg = ImageIO.read(mainGui_ClassLoader.getResource("Images/part3_razm.png"));
                Map.setImage(tmpImg);
                //pack();
            }
            catch (IOException ioExc)
            {
                DebugTools.printDebugMessage("Ошибка ввода-вывода при загрузке части карты.\n" + ioExc.toString());
                DebugTools.createLogFile(ioExc);
            }
        }

        if (firstTimeOnMap == 1)
        {
            showHowToWorkMessage();
        }
    }

    private void southWestPart_JButton_Action()
    {
        getContentPane().setBackground(new java.awt.Color(148, 199, 246));
        //if (Map != null)
        //Map.setLegendOffsetToMap(0);

        isNorthWestMap = false;
        isNorthEastMap = false;
        isSouthWestMap = true;
        isSouthEastMap = false;

        goToSouthWestMap_JMenuItem.setEnabled(false);
        //goToSouthWestMap_JMenuItem.setBackground(viceCityFontColor);
        goToNorthEastMap_JMenuItem.setEnabled(true);
        goToSouthEastMap_JMenuItem.setEnabled(true);
        goToNorthWestMap_JMenuItem.setEnabled(true);

        int tmpWidth = 690;
        int tmpHeight = 540;

        hideNresetComponents();
        showSpraies_JMenuItem.setEnabled(true);

        //this.setResizable(true);

        if (Map == null)
            initNorthEastComponents("part2_razm.png");
        else
        {
            Map.setVisible(true);
            legend_JPanel.setVisible(true);
            ViceCity_JButton.setVisible(true);
            Armor_JCheckBox.setVisible(true);
            HiddenPackages_JCheckBox.setVisible(true);
            UniqueJumps_JCheckBox.setVisible(true);
            Medkit_jCheckBox.setVisible(true);
            Rampages_JCheckBox.setVisible(true);
            goToStartScreen_JButton.setVisible(true);

            setMinimumSize(new Dimension(tmpWidth, tmpHeight));
            setPreferredSize(new java.awt.Dimension(tmpWidth, tmpHeight));
            setSize(tmpWidth, tmpHeight);

            try
            {
                BufferedImage tmpImg = ImageIO.read(mainGui_ClassLoader.getResource("Images/part2_razm.png"));
                Map.setImage(tmpImg);
                //pack();
            }
            catch (IOException ioExc)
            {
                DebugTools.printDebugMessage("Ошибка ввода-вывода при загрузке части карты.\n" + ioExc.toString());
                DebugTools.createLogFile(ioExc);
            }
        }

        if (firstTimeOnMap == 1)
        {
            showHowToWorkMessage();
        }
    }

    private void southEastPart_JButton_Action()
    {
        getContentPane().setBackground(new java.awt.Color(148, 199, 246));
        //if (Map != null)
        //Map.setLegendOffsetToMap(0);

        isNorthWestMap = false;
        isNorthEastMap = false;
        isSouthWestMap = false;
        isSouthEastMap = true;

        goToSouthWestMap_JMenuItem.setEnabled(true);
        //goToSouthWestMap_JMenuItem.setBackground(viceCityFontColor);
        goToNorthEastMap_JMenuItem.setEnabled(true);
        goToSouthEastMap_JMenuItem.setEnabled(false);
        goToNorthWestMap_JMenuItem.setEnabled(true);

        int tmpWidth = 600;
        int tmpHeight = 540;

        hideNresetComponents();
        showSpraies_JMenuItem.setEnabled(true);

        //this.setResizable(true);

        if (Map == null)
            initNorthEastComponents("part4_razm.png");
        else
        {
            Map.setVisible(true);
            legend_JPanel.setVisible(true);
            ViceCity_JButton.setVisible(true);
            Armor_JCheckBox.setVisible(true);
            HiddenPackages_JCheckBox.setVisible(true);
            UniqueJumps_JCheckBox.setVisible(true);
            Medkit_jCheckBox.setVisible(true);
            Rampages_JCheckBox.setVisible(true);
            goToStartScreen_JButton.setVisible(true);

            setMinimumSize(new Dimension(tmpWidth, tmpHeight));
            setPreferredSize(new java.awt.Dimension(tmpWidth, tmpHeight));
            setSize(tmpWidth, tmpHeight);

            try
            {
                BufferedImage tmpImg = ImageIO.read(mainGui_ClassLoader.getResource("Images/part4_razm.png"));
                Map.setImage(tmpImg);
                //pack();
            }
            catch (IOException ioExc)
            {
                DebugTools.printDebugMessage("Ошибка ввода-вывода при загрузке части карты.\n" + ioExc.toString());
                DebugTools.createLogFile(ioExc);
            }
        }

        if (firstTimeOnMap == 1)
        {
            showHowToWorkMessage();
        }
    }

    private void showHowToWorkMessage()
    {
//        JOptionPane.showMessageDialog(this, "" +
//                "Если Вы хотите подробнее узнать о местонахождении\nпикапа - просто "
//                + "нажмите на него левой кнопкой мыши.\n"
//                + "Для удаления отметки воспользуйтесь правой кнопкой мыши.", "Основы работы с картой", JOptionPane.INFORMATION_MESSAGE);


        //
        howToWorkWithMap_str = addLineSeparatorInString(howToWorkWithMap_str, 30);
        JOptionPane.showMessageDialog(this, howToWorkWithMap_str
                , howToWorkWithMap_title, JOptionPane.INFORMATION_MESSAGE);
        firstTimeOnMap = 0;
    }

    /**
     * Возвращает строку с добавленными переносами через заданные количество символов
     *
     * @author Y@L_ka
     */
    public String addLineSeparatorInString(final String input, final int countCharInLine)
    {
        //lineWrap

        String tmp = "";
        if (input.length() > countCharInLine)
        {
            for (int k = 0, n = 0; k < input.length(); )
            {
                if (k + countCharInLine > input.length())
                {
                    tmp += input.substring(k, input.length());
                    break;
                }
                //ищем где заканчивается последнее слово
                int lastWordInLineIndex = -1;
                for (int b = k + countCharInLine; b > k; b--)
                {
                    if (input.charAt(b) == ' ')
                    {
                        lastWordInLineIndex = b;
                        break;
                    }
                }
                //==============================

                tmp += input.substring(k, lastWordInLineIndex);
                tmp += System.lineSeparator();
                n++;
                k = lastWordInLineIndex;
            }

        }
        return tmp;
    }

    private void goToStartScreen_Action()
    {
        getContentPane().setBackground(TRANSPARENCY);
        isSouthEastMap = false;
        isSouthWestMap = false;
        isNorthEastMap = false;
        isNorthWestMap = false;

        goToNorthWestMap_JMenuItem.setEnabled(true);
        //goToNorthWestMap_JMenuItem.setBackground(viceCityFontColor);
        goToNorthEastMap_JMenuItem.setEnabled(true);
        goToSouthEastMap_JMenuItem.setEnabled(true);
        goToSouthWestMap_JMenuItem.setEnabled(true);

        Map.setVisible(false);
        legend_JPanel.setVisible(false);
        main_MenuBar.setSize(0, 0);//Для того, чтобы скрыть, но горячие клавиши работали

        setMinimumSize(new Dimension(frameWidth, frameHeight));
        setSize(frameWidth, frameHeight);
        //setResizable(false);//Баг - не изменяется размер окна при переходе на общий вид

        PanelWithMapParts.setVisible(true);
        selectMapPart_JLabel.setVisible(true);
        northWestPart_JButton.setVisible(true);
        northEastPart_JButton.setVisible(true);
        southWestPart_JButton.setVisible(true);
        southEastPart_JButton.setVisible(true);
    }

    private void hideNresetComponents()
    {
        ToolTipManager.sharedInstance().setInitialDelay(10);

        northWestPart_JButton.setVisible(false);
        southWestPart_JButton.setVisible(false);
        northEastPart_JButton.setVisible(false);
        southEastPart_JButton.setVisible(false);
        selectMapPart_JLabel.setVisible(false);
        PanelWithMapParts.setVisible(false);

        if (Medkit_jCheckBox != null && Armor_JCheckBox != null
                && HiddenPackages_JCheckBox != null)
        {
            Medkit_jCheckBox.setText(medkit_str);
            Armor_JCheckBox.setText(armor_str);
            HiddenPackages_JCheckBox.setText(hiddenPackages_str);
            UniqueJumps_JCheckBox.setText(uniqueJumps_str);
            Bribes_JCheckBox.setText(bribes_str);
            Drugs_JCheckBox.setText(drugs_str);
            Spraies_JCheckBox.setText(sprays_str);
            Rampages_JCheckBox.setText(rampages_str);
            Properties_JCheckBox.setText(properties_str);
            Clothes_JCheckBox.setText(clothes_str);
            Weapons_JCheckBox.setText(weapons_str);
            Stores_JCheckBox.setText(stores_str);
            Vehicles_JCheckBox.setText(vehicles_str);
            Secrets_JCheckBox.setText(secrets_str);

            Armor_JCheckBox.setSelected(false);
            Medkit_jCheckBox.setSelected(false);
            UniqueJumps_JCheckBox.setSelected(false);
            HiddenPackages_JCheckBox.setSelected(false);
            Bribes_JCheckBox.setSelected(false);
            Drugs_JCheckBox.setSelected(false);
            Spraies_JCheckBox.setSelected(false);
            Spraies_JCheckBox.setEnabled(false);
            Properties_JCheckBox.setSelected(false);
            Rampages_JCheckBox.setSelected(false);
            Clothes_JCheckBox.setSelected(false);
            Weapons_JCheckBox.setSelected(false);
            Stores_JCheckBox.setSelected(false);
            Vehicles_JCheckBox.setSelected(false);
            Secrets_JCheckBox.setSelected(false);

            showArmor_JMenuItem.setSelected(false);
            showMedkit_JMenuItem.setSelected(false);
            showUniqueJumps_JMenuItem.setSelected(false);
            showHiddenPackages_JMenuItem.setSelected(false);
            showBribes_JMenuItem.setSelected(false);
            showDrugs_JMenuItem.setSelected(false);
            showSpraies_JMenuItem.setSelected(false);
            showProperties_JMenuItem.setSelected(false);
            showRampages_JMenuItem.setSelected(false);
            showClothes_JMenuItem.setSelected(false);
            showStores_JMenuItem.setSelected(false);
            showVehicles_JMenuItem.setSelected(false);
            showWeapons_JMenuItem.setSelected(false);
            showSecrets_JMenuItem.setSelected(false);

            Spraies_JCheckBox.setEnabled(true);
            showSpraies_JMenuItem.setEnabled(true);
        }

        if (vehicleButton != null)
        {
            for (int k = 0; k < vehicleButton.length; k++)
            {
                vehicleButton[k].setVisible(false);
            }
        }
        if (medkitButton != null)
        {
            for (int k = 0; k < medkitButton.length; k++)
            {
                medkitButton[k].setVisible(false);
            }
        }
        if (bribeButton != null)
        {
            for (int k = 0; k < bribeButton.length; k++)
            {
                bribeButton[k].setVisible(false);
            }
        }
        if (drugButton != null)
        {
            for (int k = 0; k < drugButton.length; k++)
            {
                drugButton[k].setVisible(false);
            }
        }
        if (clothesButton != null)
        {
            for (int k = 0; k < clothesButton.length; k++)
            {
                clothesButton[k].setVisible(false);
            }
        }
        if (weaponButton != null)
        {
            for (int k = 0; k < weaponButton.length; k++)
            {
                weaponButton[k].setVisible(false);
            }
        }
        if (sprayButton != null)
        {
            for (int k = 0; k < sprayButton.length; k++)
            {
                sprayButton[k].setVisible(false);
            }
        }
        if (storeButton != null)
        {
            for (int k = 0; k < storeButton.length; k++)
            {
                storeButton[k].setVisible(false);
            }
        }
        if (armorButton != null)
        {
            for (int k = 0; k < armorButton.length; k++)
            {
                armorButton[k].setVisible(false);
            }
        }
        if (rampageButton != null)
        {
            for (int k = 0; k < rampageButton.length; k++)
            {
                rampageButton[k].setVisible(false);
            }
        }
        if (uniqueJumpButton != null)
        {
            for (int k = 0; k < uniqueJumpButton.length; k++)
            {
                uniqueJumpButton[k].setVisible(false);
            }
        }
        if (propertiesButton != null)
        {
            for (int k = 0; k < propertiesButton.length; k++)
            {
                propertiesButton[k].setVisible(false);
            }
        }
        if (hiddenPackagesButton != null)
        {
            for (int k = 0; k < hiddenPackagesButton.length; k++)
            {
                hiddenPackagesButton[k].setVisible(false);
            }
        }
        if (secretsButton != null)
        {
            for (int k = 0; k < secretsButton.length; k++)
            {
                secretsButton[k].setVisible(false);
            }
        }

        main_MenuBar.setVisible(true);
        main_MenuBar.setSize(getWidth(), 15);
    }

    private void viceCityButton_Action(ActionEvent event)
    {
        if (!currentLanguage_str.equals("Russian"))
        {
            JOptionPane.showMessageDialog(this, "Sorry, but this function is not available with English language.", "", JOptionPane.WARNING_MESSAGE);
            return;
        }
        vcLibrary libraryFrame = new vcLibrary();
    }

    private javax.swing.JCheckBox Armor_JCheckBox;
    private javax.swing.JCheckBox HiddenPackages_JCheckBox;
    private ImageComponent Map;
    private JTextArea pickUpDescription;
    private javax.swing.JCheckBox Medkit_jCheckBox;
    private javax.swing.JCheckBox UniqueJumps_JCheckBox;
    private javax.swing.JCheckBox Bribes_JCheckBox;
    private javax.swing.JCheckBox Drugs_JCheckBox;
    private javax.swing.JCheckBox Spraies_JCheckBox;
    private javax.swing.JCheckBox Properties_JCheckBox;
    private javax.swing.JCheckBox Rampages_JCheckBox;
    private javax.swing.JCheckBox Clothes_JCheckBox;
    private javax.swing.JCheckBox Weapons_JCheckBox;
    private javax.swing.JCheckBox Stores_JCheckBox;
    private javax.swing.JCheckBox Vehicles_JCheckBox;
    private javax.swing.JCheckBox Secrets_JCheckBox;
    private javax.swing.JButton ViceCity_JButton;
    private javax.swing.JPanel legend_JPanel;

    private javax.swing.JButton northEastPart_JButton;
    private javax.swing.JButton northWestPart_JButton;
    private javax.swing.JButton southEastPart_JButton;
    private javax.swing.JButton southWestPart_JButton;
    private javax.swing.JPanel PanelWithMapParts;
    private javax.swing.JLabel selectMapPart_JLabel;
    private javax.swing.JButton goToStartScreen_JButton;
    private javax.swing.JMenuBar main_MenuBar;
    private javax.swing.JMenu file_Menu;
    private javax.swing.JMenuItem changeLanguage_MenuItem;
    private javax.swing.JMenuItem exit_MenuItem;
    private javax.swing.JMenu show_Menu;
    private javax.swing.JMenu goTo_Menu;
    private javax.swing.JMenuItem showHowToWorkMessage_JMenuItem;
    private javax.swing.JMenuItem saveMap_JMenuItem;
    private javax.swing.JMenuItem aboutProgramm_JMenuItem;
    private javax.swing.JCheckBoxMenuItem showMedkit_JMenuItem;
    private javax.swing.JCheckBoxMenuItem showArmor_JMenuItem;
    private javax.swing.JCheckBoxMenuItem showBribes_JMenuItem;
    private javax.swing.JCheckBoxMenuItem showDrugs_JMenuItem;
    private javax.swing.JCheckBoxMenuItem showSpraies_JMenuItem;
    private javax.swing.JCheckBoxMenuItem showProperties_JMenuItem;
    private javax.swing.JCheckBoxMenuItem showRampages_JMenuItem;
    private javax.swing.JCheckBoxMenuItem showClothes_JMenuItem;
    private javax.swing.JCheckBoxMenuItem showWeapons_JMenuItem;
    private javax.swing.JCheckBoxMenuItem showStores_JMenuItem;
    private javax.swing.JCheckBoxMenuItem showVehicles_JMenuItem;
    private javax.swing.JCheckBoxMenuItem showUniqueJumps_JMenuItem;
    private javax.swing.JCheckBoxMenuItem showHiddenPackages_JMenuItem;
    private javax.swing.JCheckBoxMenuItem showSecrets_JMenuItem;
    private javax.swing.JMenuItem goToNorthWestMap_JMenuItem;
    private javax.swing.JMenuItem goToSouthWestMap_JMenuItem;
    private javax.swing.JMenuItem goToNorthEastMap_JMenuItem;
    private javax.swing.JMenuItem goToSouthEastMap_JMenuItem;
    private javax.swing.JMenuItem goToLibrary_JMenuItem;
    private javax.swing.JMenuItem setUpScreenshotViewerSizePercent_JMenuItem;
    private javax.swing.JMenuItem openPageWithGitHubRepository;

    private ButtonExtended[] rampageButton;
    private ButtonExtended[] clothesButton;
    private ButtonExtended[] storeButton;
    private ButtonExtended[] weaponButton;
    private JButton[] sprayButton;
    private JButton[] drugButton;
    private JButton[] bribeButton;
    private JButton[] armorButton;
    private JButton[] medkitButton;
    private ButtonExtended[] vehicleButton;
    private ButtonExtended[] uniqueJumpButton;
    private ButtonExtended[] propertiesButton;
    private JButton[] hiddenPackagesButton;
    private ButtonExtended[] secretsButton;

    private javax.swing.JMenuItem goToStartScreen_MenuItem;

    static public String getCurrentTimeString()
    {
        LocalDateTime time = LocalDateTime.now();
        //long milli = System.nanoTime();
        StringBuilder strBuilder = new StringBuilder();
        strBuilder.append("[");
        strBuilder.append(time.getHour());
        strBuilder.append(":");
        strBuilder.append(time.getMinute());
        strBuilder.append(":");
        strBuilder.append(time.getSecond());
        strBuilder.append("]");
        //System.out.println("Времени на StringBuilder: " + (System.nanoTime()-milli));
        return strBuilder.toString();
    }

    private double[][] medkitData;
    private double[][] armorData;
    private double[][] bribesData;
    private double[][] drugsData;
    private double[][] spraiesData;
    private double[][] propertiesData;
    private double[][] rampagesData;
    private double[][] clothesData;
    private double[][] weaponsData;
    private double[][] storesData;
    private double[][] vehiclesData;
    private double[][] uniqueJumpsData;
    private double[][] hiddenPackagesData;
    private double[][] secretsData;
    /*Заполнение массива
     *0 - положение по X
     *1 - положение по Y
     *2 - отношение с изображением по X
     *3 - отношение с изображенип по Y
     *4 - код части карты (1 - северо-запад,
     *  2 - юго-запад, 3- севоро-восток, 4 - юго-восток)
     * */

    private String[] weaponsNamesForWeaponsData;

    private void medkit_CheckBoxAction()
    {
        if (Medkit_jCheckBox.isSelected())
        {
            showMedkit_JMenuItem.setSelected(true);
            DebugTools.printDebugMessage("Активирован CheckBox Medkit.");

            initMedkitData();

            double[][] currentPartMedkitData = null;
            if (isSouthEastMap)
            {
                currentPartMedkitData = getNewArrayWithCorrespondingData(medkitData, 0.4);
            }
            if (isNorthEastMap)
            {
                currentPartMedkitData = getNewArrayWithCorrespondingData(medkitData, 0.3);
            }
            if (isSouthWestMap)
            {
                currentPartMedkitData = getNewArrayWithCorrespondingData(medkitData, 0.2);
            }
            if (isNorthWestMap)
            {
                currentPartMedkitData = getNewArrayWithCorrespondingData(medkitData, 0.1);
            }

            Medkit_jCheckBox.setText("[" + currentPartMedkitData.length + "] " + medkit_str);


            medkitButton = new JButton[currentPartMedkitData.length];
            MedkitButtonAction medkitButAction = new MedkitButtonAction(medkitData, this);

            //=======
            Map.setMedkitData(currentPartMedkitData);
            Map.setMedkitButtons(medkitButton);
            //=======
            for (int k = 0; k < medkitButton.length; k++)
            {
                medkitButton[k] = new JButton("");
                medkitButton[k].setSize(18, 18);
                medkitButton[k].setForeground(TRANSPARENCY);
                medkitButton[k].setBackground(TRANSPARENCY);
                medkitButton[k].setBorder(null);
                //tmpBut.setText("");
                medkitButton[k].setIcon(new ImageIcon(mainGui_ClassLoader.getResource("Images/medical-kit_1.png")));
                medkitButton[k].setLocation((int) currentPartMedkitData[k][0], (int) currentPartMedkitData[k][1]);
                medkitButton[k].addMouseListener(medkitButAction);
                Map.add(medkitButton[k]);
            }
            Map.repaint();

        } else
        {
            showMedkit_JMenuItem.setSelected(false);
            Medkit_jCheckBox.setSelected(false);
            for (int k = 0; k < medkitButton.length; k++)
            {
                medkitButton[k].setVisible(false);
            }
            DebugTools.printDebugMessage("Деактивирован CheckBox Medkit.");
            Map.repaint();
        }
    }

    private void armor_CheckBoxAction()
    {
        if (Armor_JCheckBox.isSelected())
        {
            showArmor_JMenuItem.setSelected(true);
            DebugTools.printDebugMessage("Активирован CheckBox Armor.");

            initArmorData();

            double[][] currentPartArmorData = null;
            if (isSouthEastMap)
            {
                currentPartArmorData = getNewArrayWithCorrespondingData(armorData, 0.4);
            }
            if (isNorthEastMap)
            {
                currentPartArmorData = getNewArrayWithCorrespondingData(armorData, 0.3);
            }
            if (isSouthWestMap)
            {
                currentPartArmorData = getNewArrayWithCorrespondingData(armorData, 0.2);
            }
            if (isNorthWestMap)
            {
                currentPartArmorData = getNewArrayWithCorrespondingData(armorData, 0.1);
            }

            Armor_JCheckBox.setText("[" + currentPartArmorData.length + "] " + armor_str);
            armorButton = new JButton[currentPartArmorData.length];
            mouseActions.ArmorButtonAction armorButtonAction = new mouseActions.ArmorButtonAction(armorData, this);
            for (int k = 0; k < armorButton.length; k++)
            {
                armorButton[k] = new JButton("");
                armorButton[k].setSize(18, 18);
                armorButton[k].setForeground(TRANSPARENCY);
                armorButton[k].setBackground(TRANSPARENCY);
                armorButton[k].setBorder(null);
                //tmpBut.setText("");
                armorButton[k].setIcon(new ImageIcon(mainGui_ClassLoader.getResource("Images/armor_1.png")));
                armorButton[k].setLocation((int) currentPartArmorData[k][0], (int) currentPartArmorData[k][1]);
                armorButton[k].addMouseListener(armorButtonAction);
                Map.add(armorButton[k]);
            }
            Map.repaint();
        } else
        {
            showArmor_JMenuItem.setSelected(false);
            for (int k = 0; k < armorButton.length; k++)
            {
                armorButton[k].setVisible(false);
            }
            DebugTools.printDebugMessage("Деактивирован CheckBox Armor.");
            Map.repaint();
        }

    }

    private void bribes_CheckBoxAction()
    {
        if (Bribes_JCheckBox.isSelected())
        {
            showBribes_JMenuItem.setSelected(true);
            DebugTools.printDebugMessage("Активирован CheckBox Bribes.");

            initBribesData();

            double[][] currentPartBribesData = null;
            if (isSouthEastMap)
            {
                currentPartBribesData = getNewArrayWithCorrespondingData(bribesData, 0.4);
            }
            if (isNorthEastMap)
            {
                currentPartBribesData = getNewArrayWithCorrespondingData(bribesData, 0.3);
            }
            if (isSouthWestMap)
            {
                currentPartBribesData = getNewArrayWithCorrespondingData(bribesData, 0.2);
            }
            if (isNorthWestMap)
            {
                currentPartBribesData = getNewArrayWithCorrespondingData(bribesData, 0.1);
            }
            Bribes_JCheckBox.setText("[" + currentPartBribesData.length + "] " + bribes_str);
            bribeButton = new JButton[currentPartBribesData.length];
            mouseActions.BribeButtonAction bribeButtonAction = new mouseActions.BribeButtonAction(bribesData, this);
            for (int k = 0; k < bribeButton.length; k++)
            {
                bribeButton[k] = new JButton("");
                bribeButton[k].setSize(18, 18);
                bribeButton[k].setForeground(TRANSPARENCY);
                bribeButton[k].setBackground(new Color(0, 0, 0, 0));
                bribeButton[k].setBorder(null);
                //tmpBut.setText("");
                bribeButton[k].setIcon(new ImageIcon(mainGui_ClassLoader.getResource("Images/bribe_2.png")));
                bribeButton[k].setLocation((int) currentPartBribesData[k][0], (int) currentPartBribesData[k][1]);
                bribeButton[k].addMouseListener(bribeButtonAction);
                Map.add(bribeButton[k]);
            }
            Map.repaint();

        } else
        {
            showBribes_JMenuItem.setSelected(false);
            for (int k = 0; k < bribeButton.length; k++)
            {
                bribeButton[k].setVisible(false);
            }
            DebugTools.printDebugMessage("Деактивирован CheckBox Bribes.");
            Map.repaint();
        }
    }

    private void drugs_CheckBoxAction()
    {
        if (Drugs_JCheckBox.isSelected())
        {
            showDrugs_JMenuItem.setSelected(true);
            DebugTools.printDebugMessage("Активирован CheckBox Drugs.");

            initDrugsData();

            double[][] currentPartDrugsData = null;
            if (isSouthEastMap)
            {
                currentPartDrugsData = getNewArrayWithCorrespondingData(drugsData, 0.4);
            }
            if (isNorthEastMap)
            {
                currentPartDrugsData = getNewArrayWithCorrespondingData(drugsData, 0.3);
            }
            if (isSouthWestMap)
            {
                currentPartDrugsData = getNewArrayWithCorrespondingData(drugsData, 0.2);
            }
            if (isNorthWestMap)
            {
                currentPartDrugsData = getNewArrayWithCorrespondingData(drugsData, 0.1);
            }

            Drugs_JCheckBox.setText("[" + currentPartDrugsData.length + "] " + drugs_str);
            drugButton = new JButton[currentPartDrugsData.length];
            mouseActions.DrugButtonAction drugButAction = new mouseActions.DrugButtonAction(drugsData, this);
            for (int k = 0; k < drugButton.length; k++)
            {
                drugButton[k] = new JButton("");
                drugButton[k].setSize(18, 18);
                drugButton[k].setForeground(TRANSPARENCY);
                drugButton[k].setBackground(TRANSPARENCY);
                drugButton[k].setBorder(null);
                //tmpBut.setText("");
                drugButton[k].setIcon(new ImageIcon(mainGui_ClassLoader.getResource("Images/drug_2.png")));
                drugButton[k].setLocation((int) currentPartDrugsData[k][0], (int) currentPartDrugsData[k][1]);
                drugButton[k].addMouseListener(drugButAction);
                Map.add(drugButton[k]);
            }
            Map.repaint();

        } else
        {
            showDrugs_JMenuItem.setSelected(false);
            for (int k = 0; k < drugButton.length; k++)
            {
                drugButton[k].setVisible(false);
            }
            DebugTools.printDebugMessage("Деактивирован CheckBox Drugs.");
            Map.repaint();
        }
    }

    private void spraies_CheckBoxAction()
    {
        if (Spraies_JCheckBox.isSelected())
        {
            showSpraies_JMenuItem.setSelected(true);
            DebugTools.printDebugMessage("Активирован CheckBox Spraies.");

            initSpraiesData();

            double[][] currentPartSpraiesData = null;
            if (isSouthEastMap)
            {
                currentPartSpraiesData = getNewArrayWithCorrespondingData(spraiesData, 0.4);
            }
            if (isNorthEastMap)
            {
                currentPartSpraiesData = getNewArrayWithCorrespondingData(spraiesData, 0.3);
            }
            if (isSouthWestMap)
            {
                currentPartSpraiesData = getNewArrayWithCorrespondingData(spraiesData, 0.2);
            }
            if (isNorthWestMap)
            {
                currentPartSpraiesData = getNewArrayWithCorrespondingData(spraiesData, 0.1);
            }
            Spraies_JCheckBox.setText("[" + currentPartSpraiesData.length + "] " + sprays_str);
            sprayButton = new JButton[currentPartSpraiesData.length];
            mouseActions.SprayButtonAction sprayButAction = new mouseActions.SprayButtonAction(spraiesData, this);
            for (int k = 0; k < sprayButton.length; k++)
            {
                sprayButton[k] = new JButton("");
                sprayButton[k].setSize(18, 18);
                sprayButton[k].setForeground(TRANSPARENCY);
                sprayButton[k].setBackground(TRANSPARENCY);
                sprayButton[k].setBorder(null);
                //tmpBut.setText("");
                sprayButton[k].setIcon(new ImageIcon(mainGui_ClassLoader.getResource("Images/spray.png")));
                sprayButton[k].setLocation((int) currentPartSpraiesData[k][0], (int) currentPartSpraiesData[k][1]);
                sprayButton[k].addMouseListener(sprayButAction);
                Map.add(sprayButton[k]);
            }
            Map.repaint();
        } else
        {
            showSpraies_JMenuItem.setSelected(false);
            for (int k = 0; k < sprayButton.length; k++)
            {
                sprayButton[k].setVisible(false);
            }
            DebugTools.printDebugMessage("Деактивирован CheckBox Spraies.");
            Map.repaint();
        }
    }

    private void properties_CheckBoxAction()
    {
        if (Properties_JCheckBox.isSelected())
        {
            showProperties_JMenuItem.setSelected(true);
            DebugTools.printDebugMessage("Активирован CheckBox Properties.");
            try
            {
                BufferedImage propertiesIcon = ImageIO.read(mainGui_ClassLoader.getResource("Images/properties_3.png"));

                initPropertiesData();

                double[][] currentPartPropertiesData = null;
                if (isSouthEastMap)
                {
                    currentPartPropertiesData = getNewArrayWithCorrespondingData(propertiesData, 0.4);
                }
                if (isNorthEastMap)
                {
                    currentPartPropertiesData = getNewArrayWithCorrespondingData(propertiesData, 0.3);
                }
                if (isSouthWestMap)
                {
                    currentPartPropertiesData = getNewArrayWithCorrespondingData(propertiesData, 0.2);
                }
                if (isNorthWestMap)
                {
                    currentPartPropertiesData = getNewArrayWithCorrespondingData(propertiesData, 0.1);
                }
                Properties_JCheckBox.setText("[" + currentPartPropertiesData.length + "] " + properties_str);
                propertiesButton = new ButtonExtended[currentPartPropertiesData.length];
                mouseActions.PropertyButtonAction propertyButAction = new mouseActions.PropertyButtonAction(propertiesData, this);
                for (int k = 0; k < propertiesButton.length; k++)
                {
                    propertiesButton[k] = new ButtonExtended();
                    propertiesButton[k].setSize(18, 18);
                    propertiesButton[k].setForeground(TRANSPARENCY);
                    propertiesButton[k].setBackground(TRANSPARENCY);
                    propertiesButton[k].setBorder(null);
                    //tmpBut.setText("");

                    propertiesButton[k].setIcon(new ImageIcon(mainGui_ClassLoader.getResource("Images/properties_3.png")));
                    propertiesButton[k].setLocation((int) currentPartPropertiesData[k][0], (int) currentPartPropertiesData[k][1]);
                    propertiesButton[k].addMouseListener(propertyButAction);

                    //Уникальные иконки для бизнеса
                    if (propertiesButton[k].getX() == propertiesData[15][0] &&
                            propertiesButton[k].getY() == propertiesData[15][1])//caufmanCabs
                    {
                        propertiesButton[k].setIcon(new ImageIcon(ImageIO.read(mainGui_ClassLoader.getResource("Images/kcabs.png"))));
                        propertiesButton[k].setToolTipText(property_15_taxi);
                    }
                    if (propertiesButton[k].getX() == propertiesData[14][0] &&
                            propertiesButton[k].getY() == propertiesData[14][1])//iceCream
                    {
                        propertiesButton[k].setIcon(new ImageIcon(ImageIO.read(mainGui_ClassLoader.getResource("Images/icecream.png"))));
                        propertiesButton[k].setToolTipText(property_14_iceCream);
                    }
                    if (propertiesButton[k].getX() == propertiesData[13][0] &&
                            propertiesButton[k].getY() == propertiesData[13][1])//
                    {
                        propertiesButton[k].setIcon(new ImageIcon(ImageIO.read(mainGui_ClassLoader.getResource("Images/boatyard.png"))));
                        propertiesButton[k].setToolTipText(property_13_boatyard);
                    }
                    if (propertiesButton[k].getX() == propertiesData[12][0] &&
                            propertiesButton[k].getY() == propertiesData[12][1])//
                    {
                        propertiesButton[k].setIcon(new ImageIcon(ImageIO.read(mainGui_ClassLoader.getResource("Images/printworks.png"))));
                        propertiesButton[k].setToolTipText(property_12_printWorkd);
                    }
                    if (propertiesButton[k].getX() == propertiesData[11][0] &&
                            propertiesButton[k].getY() == propertiesData[11][1])//
                    {
                        propertiesButton[k].setIcon(new ImageIcon(ImageIO.read(mainGui_ClassLoader.getResource("Images/SunYard.png"))));
                        propertiesButton[k].setToolTipText(property_11_carShowroom);
                    }
                    if (propertiesButton[k].getX() == propertiesData[10][0] &&
                            propertiesButton[k].getY() == propertiesData[10][1])//
                    {
                        propertiesButton[k].setIcon(new ImageIcon(ImageIO.read(mainGui_ClassLoader.getResource("Images/filmstudio.png"))));
                        propertiesButton[k].setToolTipText(property_10_filmStudio);
                    }
                    if (propertiesButton[k].getX() == propertiesData[9][0] &&
                            propertiesButton[k].getY() == propertiesData[9][1])//
                    {
                        propertiesButton[k].setIcon(new ImageIcon(ImageIO.read(mainGui_ClassLoader.getResource("Images/club.png"))));
                        propertiesButton[k].setToolTipText(property_9_malibu);
                    }
                    if (propertiesButton[k].getX() == propertiesData[8][0] &&
                            propertiesButton[k].getY() == propertiesData[8][1])//
                    {
                        propertiesButton[k].setIcon(new ImageIcon(ImageIO.read(mainGui_ClassLoader.getResource("Images/radar_strip.png"))));
                        propertiesButton[k].setToolTipText(property_8_polePosition);
                    }
                    if (propertiesButton[k].getX() == propertiesData[7][0] &&
                            propertiesButton[k].getY() == propertiesData[7][1])//
                    {
                        propertiesButton[k].setToolTipText(property_7_hymanCondo);
                    }
                    if (propertiesButton[k].getX() == propertiesData[6][0] &&
                            propertiesButton[k].getY() == propertiesData[6][1])//
                    {
                        propertiesButton[k].setToolTipText(property_6_skumoleShack);
                    }
                    if (propertiesButton[k].getX() == propertiesData[5][0] &&
                            propertiesButton[k].getY() == propertiesData[5][1])//
                    {
                        propertiesButton[k].setToolTipText(property_5_oceanView);
                    }
                    if (propertiesButton[k].getX() == propertiesData[4][0] &&
                            propertiesButton[k].getY() == propertiesData[4][1])//
                    {
                        propertiesButton[k].setToolTipText(property_4_3321VicePoint);
                    }
                    if (propertiesButton[k].getX() == propertiesData[3][0] &&
                            propertiesButton[k].getY() == propertiesData[3][1])//
                    {
                        propertiesButton[k].setToolTipText(property_3_elSwanko);
                    }
                    if (propertiesButton[k].getX() == propertiesData[2][0] &&
                            propertiesButton[k].getY() == propertiesData[2][1])//
                    {
                        propertiesButton[k].setToolTipText(property_2_linksView);
                    }
                    if (propertiesButton[k].getX() == propertiesData[1][0] &&
                            propertiesButton[k].getY() == propertiesData[1][1])//
                    {
                        propertiesButton[k].setToolTipText(property_1_oceanHeights);
                    }
                    if (propertiesButton[k].getX() == propertiesData[0][0] &&
                            propertiesButton[k].getY() == propertiesData[0][1])//
                    {
                        propertiesButton[k].setToolTipText(property_0_1102Washington);
                    }

                    //=============================
                    propertiesButton[k].setFontForToolTip(casualContactMF);

                    Map.add(propertiesButton[k]);
                }
                Map.repaint();
            }
            catch (IOException ioExc)
            {
                DebugTools.printDebugMessage("Ошибка ввода вывода при загрузке иконки недвижимости.\n" + ioExc.toString());
                DebugTools.createLogFile(ioExc);
            }
        } else
        {
            showProperties_JMenuItem.setSelected(false);
            for (int k = 0; k < propertiesButton.length; k++)
            {
                propertiesButton[k].setVisible(false);
            }
            DebugTools.printDebugMessage("Деактивирован CheckBox недвижимость.");
            Map.repaint();
        }
    }

    private void rampages_CheckBoxAction()
    {
        if (Rampages_JCheckBox.isSelected())
        {
            showRampages_JMenuItem.setSelected(true);
            DebugTools.printDebugMessage("Активирован CheckBox Rampages.");
            //BufferedImage RampageIcon = ImageIO.read(mainGui_ClassLoader.getResource("Images/rampage_4.png"));

            initRampagesData();

            double[][] currentPartRampagesData = null;
            if (isSouthEastMap)
            {
                currentPartRampagesData = getNewArrayWithCorrespondingData(rampagesData, 0.4);
            }
            if (isNorthEastMap)
            {
                currentPartRampagesData = getNewArrayWithCorrespondingData(rampagesData, 0.3);
            }
            if (isSouthWestMap)
            {
                currentPartRampagesData = getNewArrayWithCorrespondingData(rampagesData, 0.2);
            }
            if (isNorthWestMap)
            {
                currentPartRampagesData = getNewArrayWithCorrespondingData(rampagesData, 0.1);
            }

            Rampages_JCheckBox.setText("[" + currentPartRampagesData.length + "] " + rampages_str);
            rampageButton = new ButtonExtended[currentPartRampagesData.length];
            mouseActions.RampageButtonAction rampageButAction = new mouseActions.RampageButtonAction(rampagesData, this);
            for (int k = 0; k < rampageButton.length; k++)
            {
                rampageButton[k] = new ButtonExtended();
                rampageButton[k].setSize(18, 18);
                rampageButton[k].setForeground(TRANSPARENCY);
                rampageButton[k].setBackground(TRANSPARENCY);
                rampageButton[k].setBorder(null);
                //tmpBut.setText("");
                rampageButton[k].setIcon(new ImageIcon(mainGui_ClassLoader.getResource("Images/rampage_4.png")));
                rampageButton[k].setLocation((int) currentPartRampagesData[k][0], (int) currentPartRampagesData[k][1]);
                rampageButton[k].addMouseListener(rampageButAction);


                if (rampageButton[k].getX() == rampagesData[0][0]
                        && rampageButton[k].getY() == rampagesData[0][1])
                {
                    //psevdoToolTip ptt = psevdoToolTip.createPsevdoToolTip("JTextArea в роли всплывающей подсказки.\nУбить 25 членов с M4.");
                    rampageButton[k].setToolTipText(rampage_0);
                }
                if (rampageButton[k].getX() == rampagesData[1][0]
                        && rampageButton[k].getY() == rampagesData[1][1])
                {
                    rampageButton[k].setToolTipText(rampage_1);
                }
                if (rampageButton[k].getX() == rampagesData[2][0]
                        && rampageButton[k].getY() == rampagesData[2][1])
                {
                    rampageButton[k].setToolTipText(rampage_2);
                }
                if (rampageButton[k].getX() == rampagesData[3][0]
                        && rampageButton[k].getY() == rampagesData[3][1])
                {
                    rampageButton[k].setToolTipText(rampage_3);
                }
                if (rampageButton[k].getX() == rampagesData[4][0]
                        && rampageButton[k].getY() == rampagesData[4][1])
                {
                    rampageButton[k].setToolTipText(rampage_4);
                }
                if (rampageButton[k].getX() == rampagesData[5][0]
                        && rampageButton[k].getY() == rampagesData[5][1])
                {
                    rampageButton[k].setToolTipText(rampage_5);
                }
                if (rampageButton[k].getX() == rampagesData[6][0]
                        && rampageButton[k].getY() == rampagesData[6][1])
                {
                    rampageButton[k].setToolTipText(rampage_6);
                }
                if (rampageButton[k].getX() == rampagesData[7][0]
                        && rampageButton[k].getY() == rampagesData[7][1])
                {
                    rampageButton[k].setToolTipText(rampage_7);
                }
                if (rampageButton[k].getX() == rampagesData[8][0]
                        && rampageButton[k].getY() == rampagesData[8][1])
                {
                    rampageButton[k].setToolTipText(rampage_8);
                }
                if (rampageButton[k].getX() == rampagesData[9][0]
                        && rampageButton[k].getY() == rampagesData[9][1])
                {
                    rampageButton[k].setToolTipText(rampage_9);
                }
                if (rampageButton[k].getX() == rampagesData[10][0]
                        && rampageButton[k].getY() == rampagesData[10][1])
                {
                    rampageButton[k].setToolTipText(rampage_10);
                }
                if (rampageButton[k].getX() == rampagesData[11][0]
                        && rampageButton[k].getY() == rampagesData[11][1])
                {
                    rampageButton[k].setToolTipText(rampage_11);
                }
                if (rampageButton[k].getX() == rampagesData[12][0]
                        && rampageButton[k].getY() == rampagesData[12][1])
                {
                    rampageButton[k].setToolTipText(rampage_12);
                }
                if (rampageButton[k].getX() == rampagesData[13][0]
                        && rampageButton[k].getY() == rampagesData[13][1])
                {
                    rampageButton[k].setToolTipText(rampage_13);
                }
                if (rampageButton[k].getX() == rampagesData[14][0]
                        && rampageButton[k].getY() == rampagesData[14][1])
                {
                    rampageButton[k].setToolTipText(rampage_14);
                }
                if (rampageButton[k].getX() == rampagesData[15][0]
                        && rampageButton[k].getY() == rampagesData[15][1])
                {
                    rampageButton[k].setToolTipText(rampage_15);
                }
                if (rampageButton[k].getX() == rampagesData[16][0]
                        && rampageButton[k].getY() == rampagesData[16][1])
                {
                    rampageButton[k].setToolTipText(rampage_16);
                }
                if (rampageButton[k].getX() == rampagesData[17][0]
                        && rampageButton[k].getY() == rampagesData[17][1])
                {
                    rampageButton[k].setToolTipText(rampage_17);
                }
                if (rampageButton[k].getX() == rampagesData[18][0]
                        && rampageButton[k].getY() == rampagesData[18][1])
                {
                    rampageButton[k].setToolTipText(rampage_18);
                }
                if (rampageButton[k].getX() == rampagesData[19][0]
                        && rampageButton[k].getY() == rampagesData[19][1])
                {
                    rampageButton[k].setToolTipText(rampage_19);
                }
                if (rampageButton[k].getX() == rampagesData[20][0]
                        && rampageButton[k].getY() == rampagesData[20][1])
                {
                    rampageButton[k].setToolTipText(rampage_20);
                }
                if (rampageButton[k].getX() == rampagesData[21][0]
                        && rampageButton[k].getY() == rampagesData[21][1])
                {
                    rampageButton[k].setToolTipText(rampage_21);
                }
                if (rampageButton[k].getX() == rampagesData[22][0]
                        && rampageButton[k].getY() == rampagesData[22][1])
                {
                    rampageButton[k].setToolTipText(rampage_22);
                }
                if (rampageButton[k].getX() == rampagesData[23][0]
                        && rampageButton[k].getY() == rampagesData[23][1])
                {
                    rampageButton[k].setToolTipText(rampage_23);
                }
                if (rampageButton[k].getX() == rampagesData[24][0]
                        && rampageButton[k].getY() == rampagesData[24][1])
                {
                    rampageButton[k].setToolTipText(rampage_24);
                }
                if (rampageButton[k].getX() == rampagesData[25][0]
                        && rampageButton[k].getY() == rampagesData[25][1])
                {
                    rampageButton[k].setToolTipText(rampage_25);
                }
                if (rampageButton[k].getX() == rampagesData[26][0]
                        && rampageButton[k].getY() == rampagesData[26][1])
                {
                    rampageButton[k].setToolTipText(rampage_26);
                }
                if (rampageButton[k].getX() == rampagesData[27][0]
                        && rampageButton[k].getY() == rampagesData[27][1])
                {
                    rampageButton[k].setToolTipText(rampage_27);
                }
                if (rampageButton[k].getX() == rampagesData[28][0]
                        && rampageButton[k].getY() == rampagesData[28][1])
                {
                    rampageButton[k].setToolTipText(rampage_28);
                }
                if (rampageButton[k].getX() == rampagesData[29][0]
                        && rampageButton[k].getY() == rampagesData[29][1])
                {
                    rampageButton[k].setToolTipText(rampage_29);
                }
                if (rampageButton[k].getX() == rampagesData[30][0]
                        && rampageButton[k].getY() == rampagesData[30][1])
                {
                    rampageButton[k].setToolTipText(rampage_30);
                }
                if (rampageButton[k].getX() == rampagesData[31][0]
                        && rampageButton[k].getY() == rampagesData[31][1])
                {
                    rampageButton[k].setToolTipText(rampage_31);
                }
                if (rampageButton[k].getX() == rampagesData[32][0]
                        && rampageButton[k].getY() == rampagesData[32][1])
                {
                    rampageButton[k].setToolTipText(rampage_32);
                }
                if (rampageButton[k].getX() == rampagesData[33][0]
                        && rampageButton[k].getY() == rampagesData[33][1])
                {
                    rampageButton[k].setToolTipText(rampage_33);
                }
                if (rampageButton[k].getX() == rampagesData[34][0]
                        && rampageButton[k].getY() == rampagesData[34][1])
                {
                    rampageButton[k].setToolTipText(rampage_34);
                }

                //toolTipDescription = toolTipDescription.deriveFont(Font.BOLD, 14.0f);
                rampageButton[k].setFontForToolTip(toolTipDescription);
                //setFont(tmpFont);
                //==================================

                Map.add(rampageButton[k]);
            }
            //Map.repaint();
            Map.repaint();
            //Map.paintComponents(Map.getGraphics());
        } else
        {
            showRampages_JMenuItem.setSelected(false);
            for (int k = 0; k < rampageButton.length; k++)
            {
                rampageButton[k].setVisible(false);
            }
            DebugTools.printDebugMessage("Деактивирован CheckBox Rampage.");
            Map.repaint();
        }
    }

    private void clothes_CheckBoxAction()
    {
        if (Clothes_JCheckBox.isSelected())
        {
            showClothes_JMenuItem.setSelected(true);
            DebugTools.printDebugMessage("Активирован CheckBox Clothes.");

            initClothesData();

            double[][] currentPartClothesData = null;
            if (isSouthEastMap)
            {
                currentPartClothesData = getNewArrayWithCorrespondingData(clothesData, 0.4);
            }
            if (isNorthEastMap)
            {
                currentPartClothesData = getNewArrayWithCorrespondingData(clothesData, 0.3);
            }
            if (isSouthWestMap)
            {
                currentPartClothesData = getNewArrayWithCorrespondingData(clothesData, 0.2);
            }
            if (isNorthWestMap)
            {
                currentPartClothesData = getNewArrayWithCorrespondingData(clothesData, 0.1);
            }

            Clothes_JCheckBox.setText("[" + currentPartClothesData.length + "] " + clothes_str);
            clothesButton = new ButtonExtended[currentPartClothesData.length];
            ClothesButtonAction clothesButAction = new ClothesButtonAction(clothesData, this);
            for (int k = 0; k < clothesButton.length; k++)
            {
                clothesButton[k] = new ButtonExtended();
                clothesButton[k].setSize(18, 18);
                clothesButton[k].setForeground(TRANSPARENCY);
                clothesButton[k].setBackground(TRANSPARENCY);
                clothesButton[k].setBorder(null);
                //tmpBut.setText("");
                clothesButton[k].setIcon(new ImageIcon(mainGui_ClassLoader.getResource("Images/clothes.png")));
                clothesButton[k].setLocation((int) currentPartClothesData[k][0], (int) currentPartClothesData[k][1]);
                clothesButton[k].addMouseListener(clothesButAction);
                //clothesButton[k].setFontForToolTip(toolTipDescription);
                Map.add(clothesButton[k]);


                clothesButton[k].setFontForToolTip(casualContactMF);

                if (clothesButton[k].getX() == clothesData[0][0]
                        && clothesButton[k].getY() == clothesData[0][1])
                {
                    clothesButton[k].setToolTipText(clothes_0_Street);
                    continue;
                }
                if (clothesButton[k].getX() == clothesData[1][0]
                        && clothesButton[k].getY() == clothesData[1][1])
                {
                    clothesButton[k].setToolTipText(clothes_1_Soiree);
                    continue;
                }
                if (clothesButton[k].getX() == clothesData[2][0]
                        && clothesButton[k].getY() == clothesData[2][1])
                {
                    clothesButton[k].setToolTipText(clothes_7_Vercetti);
                    continue;
                }
                if (clothesButton[k].getX() == clothesData[3][0]
                        && clothesButton[k].getY() == clothesData[3][1])
                {
                    clothesButton[k].setToolTipText(clothes_4_Cop);
                    continue;
                }

                if (clothesButton[k].getX() == clothesData[4][0]
                        && clothesButton[k].getY() == clothesData[4][1])
                {
                    clothesButton[k].setToolTipText(clothes_5_BankJob);
                    continue;
                }
                if (clothesButton[k].getX() == clothesData[5][0]
                        && clothesButton[k].getY() == clothesData[5][1])
                {
                    clothesButton[k].setToolTipText(clothes_3_CountryClub);
                    continue;
                }
                if (clothesButton[k].getX() == clothesData[6][0]
                        && clothesButton[k].getY() == clothesData[6][1])
                {
                    clothesButton[k].setToolTipText(clothes_6_Casual);
                    continue;
                }
                if (clothesButton[k].getX() == clothesData[7][0]
                        && clothesButton[k].getY() == clothesData[7][1])
                {
                    clothesButton[k].setToolTipText(clothes_2_Coveralls);
                    continue;
                }
                if (clothesButton[k].getX() == clothesData[8][0]
                        && clothesButton[k].getY() == clothesData[8][1])
                {
                    clothesButton[k].setToolTipText(clothes_0_Street);
                    continue;
                }
                if (clothesButton[k].getX() == clothesData[9][0]
                        && clothesButton[k].getY() == clothesData[9][1])
                {
                    clothesButton[k].setToolTipText(clothes_8_Tracksuit1);
                    continue;
                }
                if (clothesButton[k].getX() == clothesData[10][0]
                        && clothesButton[k].getY() == clothesData[10][1])
                {
                    clothesButton[k].setToolTipText(clothes_9_Tracksuit2);
                    continue;
                }

                if (clothesButton[k].getX() == clothesData[11][0]
                        && clothesButton[k].getY() == clothesData[11][1])
                {
                    clothesButton[k].setToolTipText(clothes_10_Havana);
                    continue;
                }

                if (clothesButton[k].getX() == clothesData[12][0]
                        && clothesButton[k].getY() == clothesData[12][1])
                {
                    clothesButton[k].setToolTipText(clothes_0_Street + " & " + clothes_11_Frankie);
                    continue;
                }
            }

            Map.repaint();
        } else
        {
            showClothes_JMenuItem.setSelected(false);
            for (int k = 0; k < clothesButton.length; k++)
            {
                clothesButton[k].setVisible(false);
            }
            DebugTools.printDebugMessage("Деактивирован CheckBox Clothes.");
            Map.repaint();
        }
    }

    private void weapons_CheckBoxAction()
    {
        if (Weapons_JCheckBox.isSelected())
        {
            showWeapons_JMenuItem.setSelected(true);
            DebugTools.printDebugMessage("Активирован CheckBox Weapons.");

            initWeaponsData();

            double[][] currentPartWeaponsData = null;
            if (isSouthEastMap)
            {
                currentPartWeaponsData = getNewArrayWithCorrespondingData(weaponsData, 0.4);
            }
            if (isNorthEastMap)
            {
                currentPartWeaponsData = getNewArrayWithCorrespondingData(weaponsData, 0.3);
            }
            if (isSouthWestMap)
            {
                currentPartWeaponsData = getNewArrayWithCorrespondingData(weaponsData, 0.2);
            }
            if (isNorthWestMap)
            {
                currentPartWeaponsData = getNewArrayWithCorrespondingData(weaponsData, 0.1);
            }

            Weapons_JCheckBox.setText("[" + currentPartWeaponsData.length + "] " + weapons_str);
            //weaponButton = new JButton[currentPartWeaponsData.length];
            weaponButton = new ButtonExtended[currentPartWeaponsData.length];

            WeaponButtonAction weaponButAction = new WeaponButtonAction(weaponsData, this);
            for (int k = 0; k < weaponButton.length; k++)
            {
                weaponButton[k] = new ButtonExtended();
                weaponButton[k].setSize(18, 18);
                weaponButton[k].setForeground(TRANSPARENCY);
                weaponButton[k].setBackground(TRANSPARENCY);
                weaponButton[k].setBorder(null);
                //tmpBut.setText("");
                weaponButton[k].setIcon(new ImageIcon(mainGui_ClassLoader.getResource("Images/weapon_2.png")));
                weaponButton[k].setLocation((int) currentPartWeaponsData[k][0], (int) currentPartWeaponsData[k][1]);
                weaponButton[k].addMouseListener(weaponButAction);

                weaponButton[k].setFontForToolTip(casualContactMF);
                Map.add(weaponButton[k]);

                //================= Устанавливаем описание-подсказку
                if (weaponButton[k].getX() == weaponsData[0][0]
                        && weaponButton[k].getY() == weaponsData[0][1])
                {
                    weaponButton[k].setToolTipText(baseballBat_weapon_str);
                    continue;
                }
                if (weaponButton[k].getX() == weaponsData[1][0]
                        && weaponButton[k].getY() == weaponsData[1][1])
                {
                    weaponButton[k].setToolTipText(knuckle_weapon_str);
                    continue;
                }
                if (weaponButton[k].getX() == weaponsData[2][0]
                        && weaponButton[k].getY() == weaponsData[2][1])
                {
                    weaponButton[k].setToolTipText(knife_weapon_str);
                    continue;
                }
                if (weaponButton[k].getX() == weaponsData[3][0]
                        && weaponButton[k].getY() == weaponsData[3][1])
                {
                    weaponButton[k].setToolTipText(chainsaw_weapon_str);
                    continue;
                }
                if (weaponButton[k].getX() == weaponsData[4][0]
                        && weaponButton[k].getY() == weaponsData[4][1])
                {
                    weaponButton[k].setToolTipText(uzi_weapon_str);
                    continue;
                }
                if (weaponButton[k].getX() == weaponsData[5][0]
                        && weaponButton[k].getY() == weaponsData[5][1])
                {
                    weaponButton[k].setToolTipText(pistol_weapon_str);
                    continue;
                }
                if (weaponButton[k].getX() == weaponsData[6][0]
                        && weaponButton[k].getY() == weaponsData[6][1])
                {
                    weaponButton[k].setToolTipText(shotgun_weapon_set);
                    continue;
                }
                if (weaponButton[k].getX() == weaponsData[7][0]
                        && weaponButton[k].getY() == weaponsData[7][1])
                {
                    weaponButton[k].setToolTipText(shotgun_weapon_set);
                    continue;
                }
                if (weaponButton[k].getX() == weaponsData[8][0]
                        && weaponButton[k].getY() == weaponsData[8][1])
                {
                    weaponButton[k].setToolTipText(nightStick_weapon_str);
                    continue;
                }
                if (weaponButton[k].getX() == weaponsData[9][0]
                        && weaponButton[k].getY() == weaponsData[9][1])
                {
                    weaponButton[k].setToolTipText(grenades_weapon_str);
                    continue;
                }
                if (weaponButton[k].getX() == weaponsData[10][0]
                        && weaponButton[k].getY() == weaponsData[10][1])
                {
                    weaponButton[k].setToolTipText(pistol_weapon_str);
                    continue;
                }
                if (weaponButton[k].getX() == weaponsData[11][0]
                        && weaponButton[k].getY() == weaponsData[11][1])
                {
                    weaponButton[k].setToolTipText(machete_weapon_str);
                    continue;
                }
                if (weaponButton[k].getX() == weaponsData[12][0]
                        && weaponButton[k].getY() == weaponsData[12][1])
                {
                    weaponButton[k].setToolTipText(tec_weapon_str);
                    continue;
                }
                if (weaponButton[k].getX() == weaponsData[13][0]
                        && weaponButton[k].getY() == weaponsData[13][1])
                {
                    weaponButton[k].setToolTipText(knife_weapon_str);
                    continue;
                }
                if (weaponButton[k].getX() == weaponsData[14][0]
                        && weaponButton[k].getY() == weaponsData[14][1])
                {
                    weaponButton[k].setToolTipText(m4_weapon_str);
                    continue;
                }
                if (weaponButton[k].getX() == weaponsData[15][0]
                        && weaponButton[k].getY() == weaponsData[15][1])
                {
                    weaponButton[k].setToolTipText(uzi_weapon_str);
                    continue;
                }
                if (weaponButton[k].getX() == weaponsData[16][0]
                        && weaponButton[k].getY() == weaponsData[16][1])
                {
                    weaponButton[k].setToolTipText(katana_weapon_str);
                    continue;
                }
                if (weaponButton[k].getX() == weaponsData[17][0]
                        && weaponButton[k].getY() == weaponsData[17][1])
                {
                    weaponButton[k].setToolTipText(ruger_weapon_str);
                    continue;
                }
                if (weaponButton[k].getX() == weaponsData[18][0]
                        && weaponButton[k].getY() == weaponsData[18][1])
                {
                    weaponButton[k].setToolTipText(remoteGrenades_weapon_str);
                    continue;
                }
                if (weaponButton[k].getX() == weaponsData[19][0]
                        && weaponButton[k].getY() == weaponsData[19][1])
                {
                    weaponButton[k].setToolTipText(golfClub_weapon_str);
                    continue;
                }
                if (weaponButton[k].getX() == weaponsData[20][0]
                        && weaponButton[k].getY() == weaponsData[20][1])
                {
                    weaponButton[k].setToolTipText(meatCleaver_weapon_str);
                    continue;
                }
                if (weaponButton[k].getX() == weaponsData[21][0]
                        && weaponButton[k].getY() == weaponsData[21][1])
                {
                    weaponButton[k].setToolTipText(uzi_weapon_str);
                    continue;
                }
                if (weaponButton[k].getX() == weaponsData[22][0]
                        && weaponButton[k].getY() == weaponsData[22][1])
                {
                    weaponButton[k].setToolTipText(molotov_weapon_str);
                    continue;
                }
                if (weaponButton[k].getX() == weaponsData[23][0]
                        && weaponButton[k].getY() == weaponsData[23][1])
                {
                    weaponButton[k].setToolTipText(stubbyShotgun_weapon_str);
                    continue;
                }
                if (weaponButton[k].getX() == weaponsData[24][0]
                        && weaponButton[k].getY() == weaponsData[24][1])
                {
                    weaponButton[k].setToolTipText(m4_weapon_str);
                    continue;
                }
                if (weaponButton[k].getX() == weaponsData[25][0]
                        && weaponButton[k].getY() == weaponsData[25][1])
                {
                    weaponButton[k].setToolTipText(minigun_weapon_str);
                    continue;
                }
                if (weaponButton[k].getX() == weaponsData[26][0]
                        && weaponButton[k].getY() == weaponsData[26][1])
                {
                    weaponButton[k].setToolTipText(sniperRiffle_weapon_str);
                    continue;
                }
                if (weaponButton[k].getX() == weaponsData[27][0]
                        && weaponButton[k].getY() == weaponsData[27][1])
                {
                    weaponButton[k].setToolTipText(rocketLauncher_weapon_str);
                    continue;
                }
                if (weaponButton[k].getX() == weaponsData[28][0]
                        && weaponButton[k].getY() == weaponsData[28][1])
                {
                    weaponButton[k].setToolTipText(mac_weapon_str);
                    continue;
                }
                if (weaponButton[k].getX() == weaponsData[29][0]
                        && weaponButton[k].getY() == weaponsData[29][1])
                {
                    weaponButton[k].setToolTipText(flamethrower_weapon_str);
                    continue;
                }
                if (weaponButton[k].getX() == weaponsData[30][0]
                        && weaponButton[k].getY() == weaponsData[30][1])
                {
                    weaponButton[k].setToolTipText(spas12_weapon_str);
                    continue;
                }
                if (weaponButton[k].getX() == weaponsData[31][0]
                        && weaponButton[k].getY() == weaponsData[31][1])
                {
                    weaponButton[k].setToolTipText(m60_weapon_str);
                    continue;
                }
                if (weaponButton[k].getX() == weaponsData[32][0]
                        && weaponButton[k].getY() == weaponsData[32][1])
                {
                    weaponButton[k].setToolTipText(m4_weapon_str);
                    continue;
                }
                if (weaponButton[k].getX() == weaponsData[33][0]
                        && weaponButton[k].getY() == weaponsData[33][1])
                {
                    weaponButton[k].setToolTipText(grenades_weapon_str);
                    continue;
                }
                if (weaponButton[k].getX() == weaponsData[34][0]
                        && weaponButton[k].getY() == weaponsData[34][1])
                {
                    weaponButton[k].setToolTipText(flamethrower_weapon_str);
                    continue;
                }
                if (weaponButton[k].getX() == weaponsData[35][0]
                        && weaponButton[k].getY() == weaponsData[35][1])
                {
                    weaponButton[k].setToolTipText(sniperRiffle_weapon_str);
                    continue;
                }
                if (weaponButton[k].getX() == weaponsData[36][0]
                        && weaponButton[k].getY() == weaponsData[36][1])
                {
                    weaponButton[k].setToolTipText(katana_weapon_str);
                    continue;
                }
                if (weaponButton[k].getX() == weaponsData[37][0]
                        && weaponButton[k].getY() == weaponsData[37][1])
                {
                    weaponButton[k].setToolTipText(coltPython_weapon_str);
                    continue;
                }

                if (weaponButton[k].getX() == weaponsData[38][0]
                        && weaponButton[k].getY() == weaponsData[38][1])
                {
                    weaponButton[k].setToolTipText(coltPython_weapon_str);
                    continue;
                }

                if (weaponButton[k].getX() == weaponsData[39][0]
                        && weaponButton[k].getY() == weaponsData[39][1])
                {
                    weaponButton[k].setToolTipText(shotgun_weapon_set + " & " + m4_weapon_str);
                    continue;
                }

                if (weaponButton[k].getX() == weaponsData[40][0]
                        && weaponButton[k].getY() == weaponsData[40][1])
                {
                    weaponButton[k].setToolTipText(sniper308_weapon_str);
                    continue;
                }
            }
            Map.repaint();

        } else

        {
            showWeapons_JMenuItem.setSelected(false);
            for (int k = 0; k < weaponButton.length; k++)
            {
                weaponButton[k].setVisible(false);
            }
            DebugTools.printDebugMessage("Деактивирован CheckBox Weapons.");
            Map.repaint();
        }

    }

    private void stores_CheckBoxAction()
    {
        if (Stores_JCheckBox.isSelected())
        {
            showStores_JMenuItem.setSelected(true);
            DebugTools.printDebugMessage("Активирован CheckBox Stores.");

            initStoresData();

            double[][] currentPartStoresData = null;
            if (isSouthEastMap)
            {
                currentPartStoresData = getNewArrayWithCorrespondingData(storesData, 0.4);
            }
            if (isNorthEastMap)
            {
                currentPartStoresData = getNewArrayWithCorrespondingData(storesData, 0.3);
            }
            if (isSouthWestMap)
            {
                currentPartStoresData = getNewArrayWithCorrespondingData(storesData, 0.2);
            }
            if (isNorthWestMap)
            {
                currentPartStoresData = getNewArrayWithCorrespondingData(storesData, 0.1);
            }

            Stores_JCheckBox.setText("[" + currentPartStoresData.length + "] " + stores_str);
            storeButton = new ButtonExtended[currentPartStoresData.length];
            StoreButtonAction storeButAction = new StoreButtonAction(storesData, this);
            for (int k = 0; k < storeButton.length; k++)
            {
                storeButton[k] = new ButtonExtended();
                storeButton[k].setSize(18, 18);
                storeButton[k].setForeground(TRANSPARENCY);
                storeButton[k].setBackground(TRANSPARENCY);
                storeButton[k].setBorder(null);
                //tmpBut.setText("");
                storeButton[k].setIcon(new ImageIcon(mainGui_ClassLoader.getResource("Images/store_7.png")));
                storeButton[k].setLocation((int) currentPartStoresData[k][0], (int) currentPartStoresData[k][1]);
                storeButton[k].addMouseListener(storeButAction);

                if (storeButton[k].getX() == storesData[0][0]
                        && storeButton[k].getY() == storesData[0][1])
                {
                    storeButton[k].setToolTipText(store_0_bunchOfTools);
                }
                if (storeButton[k].getX() == storesData[1][0]
                        && storeButton[k].getY() == storesData[1][1])
                {
                    storeButton[k].setToolTipText(store_1_jewerlyStore);
                }
                if (storeButton[k].getX() == storesData[2][0]
                        && storeButton[k].getY() == storesData[2][1])
                {
                    storeButton[k].setToolTipText(store_2_dispansary);
                }
                if (storeButton[k].getX() == storesData[3][0]
                        && storeButton[k].getY() == storesData[3][1])
                {
                    storeButton[k].setToolTipText(store_3_cornerStore);
                }
                if (storeButton[k].getX() == storesData[4][0]
                        && storeButton[k].getY() == storesData[4][1])
                {
                    storeButton[k].setToolTipText(store_1_jewerlyStore);
                }
                if (storeButton[k].getX() == storesData[5][0]
                        && storeButton[k].getY() == storesData[5][1])
                {
                    storeButton[k].setToolTipText(store_5_vinil);
                }
                if (storeButton[k].getX() == storesData[6][0]
                        && storeButton[k].getY() == storesData[6][1])
                {
                    storeButton[k].setToolTipText(store_6_tooledUp);
                }
                if (storeButton[k].getX() == storesData[7][0]
                        && storeButton[k].getY() == storesData[7][1])
                {
                    storeButton[k].setToolTipText(store_7_gash);
                }
                if (storeButton[k].getX() == storesData[8][0]
                        && storeButton[k].getY() == storesData[8][1])
                {
                    storeButton[k].setToolTipText(store_1_jewerlyStore);
                }
                if (storeButton[k].getX() == storesData[10][0]
                        && storeButton[k].getY() == storesData[10][1])
                {
                    storeButton[k].setToolTipText(store_10_robinas);
                }
                if (storeButton[k].getX() == storesData[9][0]
                        && storeButton[k].getY() == storesData[9][1])
                {
                    storeButton[k].setToolTipText(store_9_pharmacy);
                }
                if (storeButton[k].getX() == storesData[11][0]
                        && storeButton[k].getY() == storesData[11][1])
                {
                    storeButton[k].setToolTipText(store_11_screwThis);
                }
                if (storeButton[k].getX() == storesData[12][0]
                        && storeButton[k].getY() == storesData[12][1])
                {
                    storeButton[k].setToolTipText(store_12_doughnutShop);
                }
                if (storeButton[k].getX() == storesData[13][0]
                        && storeButton[k].getY() == storesData[13][1])
                {
                    storeButton[k].setToolTipText(store_13_laundromat);
                }
                if (storeButton[k].getX() == storesData[14][0]
                        && storeButton[k].getY() == storesData[14][1])
                {
                    storeButton[k].setToolTipText(store_14_rytonAide);
                }

                storeButton[k].setFontForToolTip(casualContactMF);
                Map.add(storeButton[k]);
            }
            Map.repaint();

        } else
        {
            showStores_JMenuItem.setSelected(false);
            for (int k = 0; k < storeButton.length; k++)
            {
                storeButton[k].setVisible(false);
            }
            DebugTools.printDebugMessage("Деактивирован CheckBox Stores.");
            Map.repaint();
        }
    }

    private void vehicles_CheckBoxAction()
    {
        //JOptionPane.showMessageDialog(this, "Внимание: Это раздел требует доработки", "В разработке", JOptionPane.INFORMATION_MESSAGE, null);
        if (Vehicles_JCheckBox.isSelected())
        {
            showVehicles_JMenuItem.setSelected(true);
            DebugTools.printDebugMessage("Активирован CheckBox Vehicles.");

            initVehiclesData();

            double[][] currentPartVehiclesData = null;
            if (isSouthEastMap)
            {
                currentPartVehiclesData = getNewArrayWithCorrespondingData(vehiclesData, 0.4);
            }
            if (isNorthEastMap)
            {
                currentPartVehiclesData = getNewArrayWithCorrespondingData(vehiclesData, 0.3);
            }
            if (isSouthWestMap)
            {
                currentPartVehiclesData = getNewArrayWithCorrespondingData(vehiclesData, 0.2);
            }
            if (isNorthWestMap)
            {
                currentPartVehiclesData = getNewArrayWithCorrespondingData(vehiclesData, 0.1);
            }

            Vehicles_JCheckBox.setText("[" + currentPartVehiclesData.length + "] " + vehicles_str);
            vehicleButton = new ButtonExtended[currentPartVehiclesData.length];
            VehicleButtonAction vehicleButAction = new VehicleButtonAction(vehiclesData, mainGui_ClassLoader, this);
            for (int k = 0; k < vehicleButton.length; k++)
            {
                vehicleButton[k] = new ButtonExtended();
                vehicleButton[k].setSize(18, 18);
                vehicleButton[k].setForeground(TRANSPARENCY);
                vehicleButton[k].setBackground(TRANSPARENCY);
                vehicleButton[k].setBorder(null);
                //tmpBut.setText("");
                vehicleButton[k].setIcon(new ImageIcon(mainGui_ClassLoader.getResource("Images/vehicle_3.png")));
                vehicleButton[k].setLocation((int) currentPartVehiclesData[k][0], (int) currentPartVehiclesData[k][1]);
                vehicleButton[k].addMouseListener(vehicleButAction);

                if (vehicleButton[k].getX() == vehiclesData[0][0]
                        && vehicleButton[k].getY() == vehiclesData[0][1])
                {
                    vehicleButton[k].setToolTipText(oceanic_vehicle_str);
                }
                if (vehicleButton[k].getX() == vehiclesData[1][0]
                        && vehicleButton[k].getY() == vehiclesData[1][1])
                {
                    vehicleButton[k].setToolTipText(caddy_vehicle_str);
                }
                if (vehicleButton[k].getX() == vehiclesData[2][0]
                        && vehicleButton[k].getY() == vehiclesData[2][1])
                {
                    vehicleButton[k].setToolTipText(cheetah_vehicle_str);
                }
                if (vehicleButton[k].getX() == vehiclesData[3][0]
                        && vehicleButton[k].getY() == vehiclesData[3][1])
                {
                    vehicleButton[k].setToolTipText(pcj600_vehicle_str);
                }
                if (vehicleButton[k].getX() == vehiclesData[4][0]
                        && vehicleButton[k].getY() == vehiclesData[4][1])
                {
                    vehicleButton[k].setToolTipText(pcj600_vehicle_str);
                }
                if (vehicleButton[k].getX() == vehiclesData[5][0]
                        && vehicleButton[k].getY() == vehiclesData[5][1])
                {
                    vehicleButton[k].setToolTipText(ambulance_vehicle_str);
                }
                if (vehicleButton[k].getX() == vehiclesData[6][0]
                        && vehicleButton[k].getY() == vehiclesData[6][1])
                {
                    vehicleButton[k].setToolTipText(faggio_vehicle_str);
                }
                if (vehicleButton[k].getX() == vehiclesData[7][0]
                        && vehicleButton[k].getY() == vehiclesData[7][1])
                {
                    vehicleButton[k].setToolTipText(police_vehicle_str);
                }
                if (vehicleButton[k].getX() == vehiclesData[8][0]
                        && vehicleButton[k].getY() == vehiclesData[8][1])
                {
                    vehicleButton[k].setToolTipText(pcj600_vehicle_str);
                }
                if (vehicleButton[k].getX() == vehiclesData[9][0]
                        && vehicleButton[k].getY() == vehiclesData[9][1])
                {
                    vehicleButton[k].setToolTipText(coastGuard_vehicle_str);
                }
                if (vehicleButton[k].getX() == vehiclesData[10][0]
                        && vehicleButton[k].getY() == vehiclesData[10][1])
                {
                    vehicleButton[k].setToolTipText(rumpo_vehicle_str);
                }
                if (vehicleButton[k].getX() == vehiclesData[11][0]
                        && vehicleButton[k].getY() == vehiclesData[11][1])
                {
                    vehicleButton[k].setToolTipText(faggio_vehicle_str);
                }
                if (vehicleButton[k].getX() == vehiclesData[12][0]
                        && vehicleButton[k].getY() == vehiclesData[12][1])
                {
                    vehicleButton[k].setToolTipText(police_vehicle_str);
                }
                if (vehicleButton[k].getX() == vehiclesData[13][0]
                        && vehicleButton[k].getY() == vehiclesData[13][1])
                {
                    vehicleButton[k].setToolTipText(stinger_vehicle_str);
                }
                if (vehicleButton[k].getX() == vehiclesData[14][0]
                        && vehicleButton[k].getY() == vehiclesData[14][1])
                {
                    vehicleButton[k].setToolTipText(banshee_vehicle_str);
                }
                if (vehicleButton[k].getX() == vehiclesData[15][0]
                        && vehicleButton[k].getY() == vehiclesData[15][1])
                {
                    vehicleButton[k].setToolTipText(gangBurrito_vehicle_str);
                }
                if (vehicleButton[k].getX() == vehiclesData[16][0]
                        && vehicleButton[k].getY() == vehiclesData[16][1])
                {
                    vehicleButton[k].setToolTipText(freeway_vehicle_str);
                }
                if (vehicleButton[k].getX() == vehiclesData[17][0]
                        && vehicleButton[k].getY() == vehiclesData[17][1])
                {
                    vehicleButton[k].setToolTipText(hermes_vehicle_str);
                }
                if (vehicleButton[k].getX() == vehiclesData[18][0]
                        && vehicleButton[k].getY() == vehiclesData[18][1])
                {
                    vehicleButton[k].setToolTipText(bfInjection);
                }
                if (vehicleButton[k].getX() == vehiclesData[19][0]
                        && vehicleButton[k].getY() == vehiclesData[19][1])
                {
                    vehicleButton[k].setToolTipText(comet_vehicle_str);
                }
                if (vehicleButton[k].getX() == vehiclesData[20][0]
                        && vehicleButton[k].getY() == vehiclesData[20][1])
                {
                    vehicleButton[k].setToolTipText("FBI " + washington_vehicle_str);
                }
                if (vehicleButton[k].getX() == vehiclesData[21][0]
                        && vehicleButton[k].getY() == vehiclesData[21][1])
                {
                    vehicleButton[k].setToolTipText(freeway_vehicle_str);
                }
                if (vehicleButton[k].getX() == vehiclesData[22][0]
                        && vehicleButton[k].getY() == vehiclesData[22][1])
                {
                    vehicleButton[k].setToolTipText(pcj600_vehicle_str);
                }
                if (vehicleButton[k].getX() == vehiclesData[23][0]
                        && vehicleButton[k].getY() == vehiclesData[23][1])
                {
                    vehicleButton[k].setToolTipText(policeMaverick_vehicle_str);
                }
                if (vehicleButton[k].getX() == vehiclesData[24][0]
                        && vehicleButton[k].getY() == vehiclesData[24][1])
                {
                    vehicleButton[k].setToolTipText(trashmaster_str);
                }
                if (vehicleButton[k].getX() == vehiclesData[25][0]
                        && vehicleButton[k].getY() == vehiclesData[25][1])
                {
                    vehicleButton[k].setToolTipText(cheetah_vehicle_str);
                }
                if (vehicleButton[k].getX() == vehiclesData[26][0]
                        && vehicleButton[k].getY() == vehiclesData[26][1])
                {
                    vehicleButton[k].setToolTipText(ambulance_vehicle_str);
                }
                if (vehicleButton[k].getX() == vehiclesData[27][0]
                        && vehicleButton[k].getY() == vehiclesData[27][1])
                {
                    vehicleButton[k].setToolTipText(pizzaBoy_vehicle_str);
                }
                if (vehicleButton[k].getX() == vehiclesData[28][0]
                        && vehicleButton[k].getY() == vehiclesData[28][1])
                {
                    vehicleButton[k].setToolTipText(police_vehicle_str);
                }
                if (vehicleButton[k].getX() == vehiclesData[29][0]
                        && vehicleButton[k].getY() == vehiclesData[29][1])
                {
                    vehicleButton[k].setToolTipText(firetruck_vehicle_str);
                }
                if (vehicleButton[k].getX() == vehiclesData[30][0]
                        && vehicleButton[k].getY() == vehiclesData[30][1])
                {
                    vehicleButton[k].setToolTipText("VCN " + maverick_vehicle_str);
                }
                if (vehicleButton[k].getX() == vehiclesData[31][0]
                        && vehicleButton[k].getY() == vehiclesData[31][1])
                {
                    vehicleButton[k].setToolTipText(ambulance_vehicle_str);
                }
                if (vehicleButton[k].getX() == vehiclesData[32][0]
                        && vehicleButton[k].getY() == vehiclesData[32][1])
                {
                    vehicleButton[k].setToolTipText(sanchez_vehicle_str + " & " + landstalker_vehicle_str);
                }
                if (vehicleButton[k].getX() == vehiclesData[33][0]
                        && vehicleButton[k].getY() == vehiclesData[33][1])
                {
                    vehicleButton[k].setToolTipText(maverick_vehicle_str);
                }
                if (vehicleButton[k].getX() == vehiclesData[34][0]
                        && vehicleButton[k].getY() == vehiclesData[34][1])
                {
                    vehicleButton[k].setToolTipText(pizzaBoy_vehicle_str);
                }
                if (vehicleButton[k].getX() == vehiclesData[35][0]
                        && vehicleButton[k].getY() == vehiclesData[35][1])
                {
                    vehicleButton[k].setToolTipText(patrion_vehicle_str);
                }
                if (vehicleButton[k].getX() == vehiclesData[36][0]
                        && vehicleButton[k].getY() == vehiclesData[36][1])
                {
                    vehicleButton[k].setToolTipText(voodoo_vehicle_str);
                }
                if (vehicleButton[k].getX() == vehiclesData[37][0]
                        && vehicleButton[k].getY() == vehiclesData[37][1])
                {
                    vehicleButton[k].setToolTipText(pizzaBoy_vehicle_str);
                }
                if (vehicleButton[k].getX() == vehiclesData[38][0]
                        && vehicleButton[k].getY() == vehiclesData[38][1])
                {
                    vehicleButton[k].setToolTipText(securicar_vehicle_str);
                }
                if (vehicleButton[k].getX() == vehiclesData[39][0]
                        && vehicleButton[k].getY() == vehiclesData[39][1])
                {
                    vehicleButton[k].setToolTipText(police_vehicle_str);
                }
                if (vehicleButton[k].getX() == vehiclesData[40][0]
                        && vehicleButton[k].getY() == vehiclesData[40][1])
                {
                    vehicleButton[k].setToolTipText("Police " + cheetah_vehicle_str);
                }
                if (vehicleButton[k].getX() == vehiclesData[41][0]
                        && vehicleButton[k].getY() == vehiclesData[41][1])
                {
                    vehicleButton[k].setToolTipText(pony_vehicle_str);
                }
                if (vehicleButton[k].getX() == vehiclesData[42][0]
                        && vehicleButton[k].getY() == vehiclesData[42][1])
                {
                    vehicleButton[k].setToolTipText(linerunner_vehicle_str);
                }
                if (vehicleButton[k].getX() == vehiclesData[43][0]
                        && vehicleButton[k].getY() == vehiclesData[43][1])
                {
                    vehicleButton[k].setToolTipText(baggageHandler_vehicle_str);
                }
                if (vehicleButton[k].getX() == vehiclesData[44][0]
                        && vehicleButton[k].getY() == vehiclesData[44][1])
                {
                    vehicleButton[k].setToolTipText(topFun_vehicle_str);
                }
                if (vehicleButton[k].getX() == vehiclesData[45][0]
                        && vehicleButton[k].getY() == vehiclesData[45][1])
                {
                    vehicleButton[k].setToolTipText(bus_vehicle_str);
                }
                if (vehicleButton[k].getX() == vehiclesData[46][0]
                        && vehicleButton[k].getY() == vehiclesData[46][1])
                {
                    vehicleButton[k].setToolTipText(sentinelXS_vehicle_str);
                }
                if (vehicleButton[k].getX() == vehiclesData[47][0]
                        && vehicleButton[k].getY() == vehiclesData[47][1])
                {
                    vehicleButton[k].setToolTipText(infernus_vehicle_str);
                }
                if (vehicleButton[k].getX() == vehiclesData[48][0]
                        && vehicleButton[k].getY() == vehiclesData[48][1])
                {
                    vehicleButton[k].setToolTipText(comet_vehicle_str);
                }
                if (vehicleButton[k].getX() == vehiclesData[49][0]
                        && vehicleButton[k].getY() == vehiclesData[49][1])
                {
                    vehicleButton[k].setToolTipText(landstalker_vehicle_str);
                }
                if (vehicleButton[k].getX() == vehiclesData[50][0]
                        && vehicleButton[k].getY() == vehiclesData[50][1])
                {
                    vehicleButton[k].setToolTipText(admiral_vehicle_str);
                }
                if (vehicleButton[k].getX() == vehiclesData[51][0]
                        && vehicleButton[k].getY() == vehiclesData[51][1])
                {
                    vehicleButton[k].setToolTipText(phoenix_vehicle_str);
                }
                if (vehicleButton[k].getX() == vehiclesData[52][0]
                        && vehicleButton[k].getY() == vehiclesData[52][1])
                {
                    vehicleButton[k].setToolTipText(infernus_vehicle_str);
                }
                if (vehicleButton[k].getX() == vehiclesData[53][0]
                        && vehicleButton[k].getY() == vehiclesData[53][1])
                {
                    vehicleButton[k].setToolTipText(faggio_vehicle_str);
                }

                if (vehicleButton[k].getX() == vehiclesData[54][0]
                        && vehicleButton[k].getY() == vehiclesData[54][1])
                {
                    vehicleButton[k].setToolTipText("Packer");
                }

                if (vehicleButton[k].getX() == vehiclesData[55][0]
                        && vehicleButton[k].getY() == vehiclesData[55][1])
                {
                    vehicleButton[k].setToolTipText(baggageHandler_vehicle_str);
                }

                if (vehicleButton[k].getX() == vehiclesData[56][0]
                        && vehicleButton[k].getY() == vehiclesData[56][1])
                {
                    vehicleButton[k].setToolTipText(barracksOL_vehicle_str);
                }

                if (vehicleButton[k].getX() == vehiclesData[57][0]
                        && vehicleButton[k].getY() == vehiclesData[57][1])
                {
                    vehicleButton[k].setToolTipText(patrion_vehicle_str);
                }

                if (vehicleButton[k].getX() == vehiclesData[58][0]
                        && vehicleButton[k].getY() == vehiclesData[58][1])
                {
                    vehicleButton[k].setToolTipText(topFun_vehicle_str);
                }

                if (vehicleButton[k].getX() == vehiclesData[59][0]
                        && vehicleButton[k].getY() == vehiclesData[59][1])
                {
                    vehicleButton[k].setToolTipText(topFun_vehicle_str);
                }

                vehicleButton[k].setFontForToolTip(casualContactMF);

                Map.add(vehicleButton[k]);
            }
            Map.repaint();

        } else
        {
            showVehicles_JMenuItem.setSelected(false);
            for (int k = 0; k < vehicleButton.length; k++)
            {
                vehicleButton[k].setVisible(false);
            }
            DebugTools.printDebugMessage("Деактивирован CheckBox Vehicles.");
            Map.repaint();
        }
    }

    private void uniqueJumps_CheckBoxAction()
    {
        if (UniqueJumps_JCheckBox.isSelected())
        {
            showUniqueJumps_JMenuItem.setSelected(true);
            DebugTools.printDebugMessage("Активирован CheckBox UniqueJumps.");

            initUniqueJumpsData();

            double[][] currentPartUniqueJumpsData = null;
            if (isSouthEastMap)
            {
                currentPartUniqueJumpsData = getNewArrayWithCorrespondingData(uniqueJumpsData, 0.4);
            }
            if (isNorthEastMap)
            {
                currentPartUniqueJumpsData = getNewArrayWithCorrespondingData(uniqueJumpsData, 0.3);
            }
            if (isSouthWestMap)
            {
                currentPartUniqueJumpsData = getNewArrayWithCorrespondingData(uniqueJumpsData, 0.2);
            }
            if (isNorthWestMap)
            {
                currentPartUniqueJumpsData = getNewArrayWithCorrespondingData(uniqueJumpsData, 0.1);
            }

            UniqueJumps_JCheckBox.setText("[" + currentPartUniqueJumpsData.length + "] " + uniqueJumps_str);
            uniqueJumpButton = new ButtonExtended[currentPartUniqueJumpsData.length];
            UniqueJumpAction uniqueJumpAction = new UniqueJumpAction(uniqueJumpsData, mainGui_ClassLoader, this);
            for (int k = 0; k < uniqueJumpButton.length; k++)
            {
                uniqueJumpButton[k] = new ButtonExtended();
                uniqueJumpButton[k].setSize(18, 18);
                uniqueJumpButton[k].setForeground(TRANSPARENCY);
                uniqueJumpButton[k].setBackground(TRANSPARENCY);
                uniqueJumpButton[k].setBorder(null);
                //tmpBut.setText("");
                uniqueJumpButton[k].setIcon(new ImageIcon(mainGui_ClassLoader.getResource("Images/uniqueJump-down.png")));
                uniqueJumpButton[k].setLocation((int) currentPartUniqueJumpsData[k][0], (int) currentPartUniqueJumpsData[k][1]);
                uniqueJumpButton[k].addMouseListener(uniqueJumpAction);
                uniqueJumpButton[k].setFontForToolTip(toolTipDescription);


                if (uniqueJumpButton[k].getX() == uniqueJumpsData[0][0] &&
                        uniqueJumpButton[k].getY() == uniqueJumpsData[0][1])
                {
                    uniqueJumpButton[k].setIcon(new ImageIcon(mainGui_ClassLoader.getResource("Images/uniqueJump-left.png")));
                    uniqueJumpButton[k].setTextToExtendedToolTip(uniqueJump_0);
                }

                if (uniqueJumpButton[k].getX() == uniqueJumpsData[1][0] &&
                        uniqueJumpButton[k].getY() == uniqueJumpsData[1][1])
                {
                    uniqueJumpButton[k].setIcon(new ImageIcon(mainGui_ClassLoader.getResource("Images/uniqueJump-right.png")));
                    uniqueJumpButton[k].setTextToExtendedToolTip(uniqueJump_1);
                }

                if (uniqueJumpButton[k].getX() == uniqueJumpsData[2][0] &&
                        uniqueJumpButton[k].getY() == uniqueJumpsData[2][1])
                {
                    uniqueJumpButton[k].setIcon(new ImageIcon(mainGui_ClassLoader.getResource("Images/uniqueJump-left.png")));
                    uniqueJumpButton[k].setTextToExtendedToolTip(uniqueJump_2);
                }


                if (uniqueJumpButton[k].getX() == uniqueJumpsData[8][0] &&
                        uniqueJumpButton[k].getY() == uniqueJumpsData[8][1])
                {
                    uniqueJumpButton[k].setIcon(new ImageIcon(mainGui_ClassLoader.getResource("Images/uniqueJump.png")));
                }


                if (uniqueJumpButton[k].getX() == uniqueJumpsData[3][0] &&
                        uniqueJumpButton[k].getY() == uniqueJumpsData[3][1])
                {
                    uniqueJumpButton[k].setTextToExtendedToolTip(uniqueJump_3);
                }

                if (uniqueJumpButton[k].getX() == uniqueJumpsData[4][0] &&
                        uniqueJumpButton[k].getY() == uniqueJumpsData[4][1])
                {
                    uniqueJumpButton[k].setTextToExtendedToolTip(uniqueJump_4);
                }

                if (uniqueJumpButton[k].getX() == uniqueJumpsData[5][0] &&
                        uniqueJumpButton[k].getY() == uniqueJumpsData[5][1])
                {
                    uniqueJumpButton[k].setTextToExtendedToolTip(uniqueJump_5);
                }

                if (uniqueJumpButton[k].getX() == uniqueJumpsData[6][0] &&
                        uniqueJumpButton[k].getY() == uniqueJumpsData[6][1])
                {
                    uniqueJumpButton[k].setTextToExtendedToolTip(uniqueJump_6);
                    uniqueJumpButton[k].setIcon(new ImageIcon(mainGui_ClassLoader.getResource("Images/uniqueJump-left.png")));
                }

                if (uniqueJumpButton[k].getX() == uniqueJumpsData[7][0] &&
                        uniqueJumpButton[k].getY() == uniqueJumpsData[7][1])
                {
                    uniqueJumpButton[k].setTextToExtendedToolTip(uniqueJump_7);
                }

                if (uniqueJumpButton[k].getX() == uniqueJumpsData[8][0] &&
                        uniqueJumpButton[k].getY() == uniqueJumpsData[8][1])
                {
                    uniqueJumpButton[k].setTextToExtendedToolTip(uniqueJump_8);
                }

                if (uniqueJumpButton[k].getX() == uniqueJumpsData[9][0] &&
                        uniqueJumpButton[k].getY() == uniqueJumpsData[9][1])
                {
                    uniqueJumpButton[k].setTextToExtendedToolTip(uniqueJump_9);
                }

                if (uniqueJumpButton[k].getX() == uniqueJumpsData[10][0] &&
                        uniqueJumpButton[k].getY() == uniqueJumpsData[10][1])
                {
                    uniqueJumpButton[k].setIcon(new ImageIcon(mainGui_ClassLoader.getResource("Images/uniqueJump-right.png")));
                    uniqueJumpButton[k].setTextToExtendedToolTip(uniqueJump_10);
                }

                if (uniqueJumpButton[k].getX() == uniqueJumpsData[11][0] &&
                        uniqueJumpButton[k].getY() == uniqueJumpsData[11][1])
                {
                    uniqueJumpButton[k].setTextToExtendedToolTip(uniqueJump_11);
                    uniqueJumpButton[k].setIcon(new ImageIcon(mainGui_ClassLoader.getResource("Images/uniqueJump-right.png")));
                }

                if (uniqueJumpButton[k].getX() == uniqueJumpsData[12][0] &&
                        uniqueJumpButton[k].getY() == uniqueJumpsData[12][1])
                {
                    uniqueJumpButton[k].setTextToExtendedToolTip(uniqueJump_12);
                }

                if (uniqueJumpButton[k].getX() == uniqueJumpsData[13][0] &&
                        uniqueJumpButton[k].getY() == uniqueJumpsData[13][1])
                {
                    uniqueJumpButton[k].setTextToExtendedToolTip(uniqueJump_13);
                }

                if (uniqueJumpButton[k].getX() == uniqueJumpsData[14][0] &&
                        uniqueJumpButton[k].getY() == uniqueJumpsData[14][1])
                {
                    uniqueJumpButton[k].setTextToExtendedToolTip(uniqueJump_14);
                }

                if (uniqueJumpButton[k].getX() == uniqueJumpsData[15][0] &&
                        uniqueJumpButton[k].getY() == uniqueJumpsData[15][1])
                {
                    uniqueJumpButton[k].setIcon(new ImageIcon(mainGui_ClassLoader.getResource("Images/uniqueJump.png")));
                    uniqueJumpButton[k].setTextToExtendedToolTip(uniqueJump_15);
                }

                if (uniqueJumpButton[k].getX() == uniqueJumpsData[16][0] &&
                        uniqueJumpButton[k].getY() == uniqueJumpsData[16][1])
                {
                    uniqueJumpButton[k].setTextToExtendedToolTip(uniqueJump_16);
                }

                if (uniqueJumpButton[k].getX() == uniqueJumpsData[17][0] &&
                        uniqueJumpButton[k].getY() == uniqueJumpsData[17][1])
                {
                    uniqueJumpButton[k].setTextToExtendedToolTip(uniqueJump_17);
                }

                if (uniqueJumpButton[k].getX() == uniqueJumpsData[18][0] &&
                        uniqueJumpButton[k].getY() == uniqueJumpsData[18][1])
                {
                    uniqueJumpButton[k].setIcon(new ImageIcon(mainGui_ClassLoader.getResource("Images/uniqueJump-left.png")));
                    uniqueJumpButton[k].setTextToExtendedToolTip(uniqueJump_18);
                }

                if (uniqueJumpButton[k].getX() == uniqueJumpsData[19][0] &&
                        uniqueJumpButton[k].getY() == uniqueJumpsData[19][1])
                {
                    uniqueJumpButton[k].setIcon(new ImageIcon(mainGui_ClassLoader.getResource("Images/uniqueJump.png")));
                    uniqueJumpButton[k].setTextToExtendedToolTip(uniqueJump_19);
                }

                if (uniqueJumpButton[k].getX() == uniqueJumpsData[20][0] &&
                        uniqueJumpButton[k].getY() == uniqueJumpsData[20][1])
                {
                    uniqueJumpButton[k].setTextToExtendedToolTip(uniqueJump_20);
                }

                if (uniqueJumpButton[k].getX() == uniqueJumpsData[21][0] &&
                        uniqueJumpButton[k].getY() == uniqueJumpsData[21][1])
                {
                    uniqueJumpButton[k].setIcon(new ImageIcon(mainGui_ClassLoader.getResource("Images/uniqueJump.png")));
                    uniqueJumpButton[k].setTextToExtendedToolTip(uniqueJump_21);
                }

                if (uniqueJumpButton[k].getX() == uniqueJumpsData[22][0] &&
                        uniqueJumpButton[k].getY() == uniqueJumpsData[22][1])
                {
                    uniqueJumpButton[k].setTextToExtendedToolTip(uniqueJump_22);
                }

                if (uniqueJumpButton[k].getX() == uniqueJumpsData[23][0] &&
                        uniqueJumpButton[k].getY() == uniqueJumpsData[23][1])
                {
                    uniqueJumpButton[k].setTextToExtendedToolTip(uniqueJump_23);
                }

                if (uniqueJumpButton[k].getX() == uniqueJumpsData[24][0] &&
                        uniqueJumpButton[k].getY() == uniqueJumpsData[24][1])
                {
                    uniqueJumpButton[k].setIcon(new ImageIcon(mainGui_ClassLoader.getResource("Images/uniqueJump-right.png")));
                    uniqueJumpButton[k].setTextToExtendedToolTip(uniqueJump_24);
                }

                if (uniqueJumpButton[k].getX() == uniqueJumpsData[25][0] &&
                        uniqueJumpButton[k].getY() == uniqueJumpsData[25][1])
                {
                    uniqueJumpButton[k].setTextToExtendedToolTip(uniqueJump_25);
                }

                if (uniqueJumpButton[k].getX() == uniqueJumpsData[26][0] &&
                        uniqueJumpButton[k].getY() == uniqueJumpsData[26][1])
                {
                    uniqueJumpButton[k].setIcon(new ImageIcon(mainGui_ClassLoader.getResource("Images/uniqueJump-right.png")));
                    uniqueJumpButton[k].setTextToExtendedToolTip(uniqueJump_26);
                }

                if (uniqueJumpButton[k].getX() == uniqueJumpsData[27][0] &&
                        uniqueJumpButton[k].getY() == uniqueJumpsData[27][1])
                {
                    uniqueJumpButton[k].setTextToExtendedToolTip(uniqueJump_27);
                }

                if (uniqueJumpButton[k].getX() == uniqueJumpsData[28][0] &&
                        uniqueJumpButton[k].getY() == uniqueJumpsData[28][1])
                {
                    uniqueJumpButton[k].setIcon(new ImageIcon(mainGui_ClassLoader.getResource("Images/uniqueJump-right.png")));
                    uniqueJumpButton[k].setTextToExtendedToolTip(uniqueJump_28);
                }

                if (uniqueJumpButton[k].getX() == uniqueJumpsData[29][0] &&
                        uniqueJumpButton[k].getY() == uniqueJumpsData[29][1])
                {
                    uniqueJumpButton[k].setIcon(new ImageIcon(mainGui_ClassLoader.getResource("Images/uniqueJump-right.png")));
                    uniqueJumpButton[k].setTextToExtendedToolTip(uniqueJump_29);
                }

                if (uniqueJumpButton[k].getX() == uniqueJumpsData[30][0] &&
                        uniqueJumpButton[k].getY() == uniqueJumpsData[30][1])
                {
                    uniqueJumpButton[k].setIcon(new ImageIcon(mainGui_ClassLoader.getResource("Images/uniqueJump-left.png")));
                    uniqueJumpButton[k].setTextToExtendedToolTip(uniqueJump_30);
                }

                if (uniqueJumpButton[k].getX() == uniqueJumpsData[31][0] &&
                        uniqueJumpButton[k].getY() == uniqueJumpsData[31][1])
                {
                    uniqueJumpButton[k].setIcon(new ImageIcon(mainGui_ClassLoader.getResource("Images/uniqueJump-right.png")));
                    uniqueJumpButton[k].setTextToExtendedToolTip(uniqueJump_31);
                }

                if (uniqueJumpButton[k].getX() == uniqueJumpsData[32][0] &&
                        uniqueJumpButton[k].getY() == uniqueJumpsData[32][1])
                {
                    uniqueJumpButton[k].setIcon(new ImageIcon(mainGui_ClassLoader.getResource("Images/uniqueJump-left.png")));
                    uniqueJumpButton[k].setTextToExtendedToolTip(uniqueJump_32);
                }

                if (uniqueJumpButton[k].getX() == uniqueJumpsData[33][0] &&
                        uniqueJumpButton[k].getY() == uniqueJumpsData[33][1])
                {
                    uniqueJumpButton[k].setIcon(new ImageIcon(mainGui_ClassLoader.getResource("Images/uniqueJump-left.png")));
                    uniqueJumpButton[k].setTextToExtendedToolTip(uniqueJump_33);
                }

                if (uniqueJumpButton[k].getX() == uniqueJumpsData[34][0] &&
                        uniqueJumpButton[k].getY() == uniqueJumpsData[34][1])
                {
                    uniqueJumpButton[k].setIcon(new ImageIcon(mainGui_ClassLoader.getResource("Images/uniqueJump-right.png")));
                    uniqueJumpButton[k].setTextToExtendedToolTip(uniqueJump_34);
                }

                if (uniqueJumpButton[k].getX() == uniqueJumpsData[35][0] &&
                        uniqueJumpButton[k].getY() == uniqueJumpsData[35][1])
                {
                    uniqueJumpButton[k].setIcon(new ImageIcon(mainGui_ClassLoader.getResource("Images/uniqueJump.png")));
                    uniqueJumpButton[k].setTextToExtendedToolTip(uniqueJump_35);
                }


                Map.add(uniqueJumpButton[k]);
            }
            Map.repaint();

        } else
        {
            showUniqueJumps_JMenuItem.setSelected(false);
            for (int k = 0; k < uniqueJumpButton.length; k++)
            {
                uniqueJumpButton[k].setVisible(false);
            }
            DebugTools.printDebugMessage("Деактивирован CheckBox UniqueJumps.");
            Map.repaint();
        }
    }

    private void hiddenPackages_CheckBoxAction()
    {
        if (HiddenPackages_JCheckBox.isSelected())
        {
            showHiddenPackages_JMenuItem.setSelected(true);
            DebugTools.printDebugMessage("Активирован CheckBox HiddenPackages.");

            initHiddenPackagesData();

            double[][] currentPartHiddenPackagesData = null;
            if (isSouthEastMap)
            {
                currentPartHiddenPackagesData = getNewArrayWithCorrespondingData(hiddenPackagesData, 0.4);
            }
            if (isNorthEastMap)
            {
                currentPartHiddenPackagesData = getNewArrayWithCorrespondingData(hiddenPackagesData, 0.3);
            }
            if (isSouthWestMap)
            {
                currentPartHiddenPackagesData = getNewArrayWithCorrespondingData(hiddenPackagesData, 0.2);
            }
            if (isNorthWestMap)
            {
                currentPartHiddenPackagesData = getNewArrayWithCorrespondingData(hiddenPackagesData, 0.1);
            }

            HiddenPackages_JCheckBox.setText("[" + currentPartHiddenPackagesData.length + "] " + hiddenPackages_str);
            hiddenPackagesButton = new JButton[currentPartHiddenPackagesData.length];
            HiddenPackageButtonAction hiddenPackagesAction = new HiddenPackageButtonAction(hiddenPackagesData, this);
            for (int k = 0; k < hiddenPackagesButton.length; k++)
            {
                hiddenPackagesButton[k] = new JButton("");
                hiddenPackagesButton[k].setSize(18, 18);
                hiddenPackagesButton[k].setForeground(TRANSPARENCY);
                hiddenPackagesButton[k].setBackground(TRANSPARENCY);
                hiddenPackagesButton[k].setBorder(null);
                //tmpBut.setText("");
                hiddenPackagesButton[k].setIcon(new ImageIcon(mainGui_ClassLoader.getResource("Images/hiddenPackage.png")));
                hiddenPackagesButton[k].setLocation((int) currentPartHiddenPackagesData[k][0], (int) currentPartHiddenPackagesData[k][1]);
                hiddenPackagesButton[k].addMouseListener(hiddenPackagesAction);

                Map.add(hiddenPackagesButton[k]);
            }
            Map.repaint();

        } else
        {
            showHiddenPackages_JMenuItem.setSelected(false);
            for (int k = 0; k < hiddenPackagesButton.length; k++)
            {
                hiddenPackagesButton[k].setVisible(false);
            }
            DebugTools.printDebugMessage("Деактивирован CheckBox HiddenPackages.");
            Map.repaint();
        }
    }

    private void secrets_CheckBoxAction()
    {
        if (Secrets_JCheckBox.isSelected())
        {
            showSecrets_JMenuItem.setSelected(true);
            DebugTools.printDebugMessage("Активирован CheckBox Secrets.");

            initSecretsData();

            double[][] currentPartSecretsData = null;
            if (isSouthEastMap)
            {
                currentPartSecretsData = getNewArrayWithCorrespondingData(secretsData, 0.4);
            }
            if (isNorthEastMap)
            {
                currentPartSecretsData = getNewArrayWithCorrespondingData(secretsData, 0.3);
            }
            if (isSouthWestMap)
            {
                currentPartSecretsData = getNewArrayWithCorrespondingData(secretsData, 0.2);
            }
            if (isNorthWestMap)
            {
                currentPartSecretsData = getNewArrayWithCorrespondingData(secretsData, 0.1);
            }

            Secrets_JCheckBox.setText("[" + currentPartSecretsData.length + "] " + secrets_str);
            secretsButton = new ButtonExtended[currentPartSecretsData.length];
            SecretButtonAction secretButAction = new SecretButtonAction(secretsData, this);
            for (int k = 0; k < secretsButton.length; k++)
            {
                secretsButton[k] = new ButtonExtended();
                secretsButton[k].setSize(18, 18);
                secretsButton[k].setForeground(TRANSPARENCY);
                secretsButton[k].setBackground(TRANSPARENCY);
                secretsButton[k].setBorder(null);
                //tmpBut.setText("");
                secretsButton[k].setIcon(new ImageIcon(mainGui_ClassLoader.getResource("Images/secret.png")));
                secretsButton[k].setLocation((int) currentPartSecretsData[k][0], (int) currentPartSecretsData[k][1]);
                secretsButton[k].addMouseListener(secretButAction);

                if (secretsButton[k].getX() == secretsData[0][0]
                        && secretsButton[k].getY() == secretsData[0][1])
                {
                    secretsButton[k].setToolTipText(secret_0_sunkenReefer);
                }
                if (secretsButton[k].getX() == secretsData[1][0]
                        && secretsButton[k].getY() == secretsData[1][1])
                {
                    secretsButton[k].setToolTipText(secret_1_fatManUnderWater);
                }
                if (secretsButton[k].getX() == secretsData[2][0]
                        && secretsButton[k].getY() == secretsData[2][1])
                {
                    secretsButton[k].setToolTipText(secret_2_vehicleUnderWater);
                }
                if (secretsButton[k].getX() == secretsData[3][0]
                        && secretsButton[k].getY() == secretsData[3][1])
                {
                    secretsButton[k].setToolTipText(secret_3_easterEgg);
                }
                if (secretsButton[k].getX() == secretsData[4][0]
                        && secretsButton[k].getY() == secretsData[4][1])
                {
                    secretsButton[k].setToolTipText(secret_4_humansOrgans);
                }
                if (secretsButton[k].getX() == secretsData[5][0]
                        && secretsButton[k].getY() == secretsData[5][1])
                {
                    secretsButton[k].setToolTipText(secret_5_lifeIsBitch);
                }
                if (secretsButton[k].getX() == secretsData[6][0]
                        && secretsButton[k].getY() == secretsData[6][1])
                {
                    secretsButton[k].setToolTipText(secret_6);
                }
                if (secretsButton[k].getX() == secretsData[7][0]
                        && secretsButton[k].getY() == secretsData[7][1])
                {
                    secretsButton[k].setToolTipText(secret_7_rockstarLogoPool);
                }
                if (secretsButton[k].getX() == secretsData[8][0]
                        && secretsButton[k].getY() == secretsData[8][1])
                {
                    secretsButton[k].setToolTipText(secret_8);
                }
                if (secretsButton[k].getX() == secretsData[9][0]
                        && secretsButton[k].getY() == secretsData[9][1])
                {
                    secretsButton[k].setToolTipText(secret_9);
                }
                if (secretsButton[k].getX() == secretsData[10][0]
                        && secretsButton[k].getY() == secretsData[10][1])
                {
                    secretsButton[k].setToolTipText(secret_10_manUnderWater);
                }
                if (secretsButton[k].getX() == secretsData[11][0]
                        && secretsButton[k].getY() == secretsData[11][1])
                {
                    secretsButton[k].setToolTipText(secret_11_submarine);
                }
                if (secretsButton[k].getX() == secretsData[12][0]
                        && secretsButton[k].getY() == secretsData[12][1])
                {
                    secretsButton[k].setToolTipText(secret_12_sunkenContainerShip);
                }
                if (secretsButton[k].getX() == secretsData[13][0]
                        && secretsButton[k].getY() == secretsData[13][1])
                {
                    secretsButton[k].setToolTipText(secret_13_dick);
                }
                if (secretsButton[k].getX() == secretsData[14][0]
                        && secretsButton[k].getY() == secretsData[14][1])
                {
                    secretsButton[k].setToolTipText(secret_14_apartment3c);
                }
                if (secretsButton[k].getX() == secretsData[15][0]
                        && secretsButton[k].getY() == secretsData[15][1])
                {
                    secretsButton[k].setToolTipText(secret_15_sunkenContainerShip);
                }

                secretsButton[k].setFontForToolTip(toolTipDescription);

                Map.add(secretsButton[k]);
            }
            Map.repaint();

        } else
        {
            showSecrets_JMenuItem.setSelected(false);
            for (int k = 0; k < secretsButton.length; k++)
            {
                secretsButton[k].setVisible(false);
            }
            DebugTools.printDebugMessage("Деактивирован CheckBox Secrets.");
            Map.repaint();
        }
    }

    //заполнены
    private void initMedkitData()
    {
        medkitData = new double[25][5];
        medkitData[0][0] = 72.0;
        medkitData[0][1] = 237.0;
        medkitData[0][4] = 0.4;

        medkitData[1][0] = 46.0;//дорожка, рядом с пристанью
        medkitData[1][1] = 290.0;
        medkitData[1][4] = 0.4;

        medkitData[2][0] = 206.0;
        medkitData[2][1] = 128.0;
        medkitData[2][4] = 0.4;

        medkitData[3][0] = 94.0;//гольфклуб
        medkitData[3][1] = 305.0;
        medkitData[3][4] = 0.3;

        medkitData[4][0] = 167.0;//рядом с Pay N' Spray
        medkitData[4][1] = 287.0;
        medkitData[4][4] = 0.3;

        medkitData[5][0] = 73.0;//рядом с фонтаном
        medkitData[5][1] = 136.0;
        medkitData[5][4] = 0.3;

        medkitData[6][0] = 168.0;//в торговом центре
        medkitData[6][1] = 97.0;
        medkitData[6][4] = 0.3;

        medkitData[7][0] = 190.0;//у госпиталя
        medkitData[7][1] = 231.0;
        medkitData[7][4] = 0.3;

        medkitData[8][0] = 168.0;//в аптеке, рядом с госпиталем
        medkitData[8][1] = 216.0;
        medkitData[8][4] = 0.3;

        medkitData[9][0] = 255.0;//на крыше ammunation
        medkitData[9][1] = 106.0;
        medkitData[9][4] = 0.1;

        medkitData[10][0] = 212.0;//во дворе госпиталя
        medkitData[10][1] = 133.0;
        medkitData[10][4] = 0.1;

        medkitData[11][0] = 215.0;//на крыше госпиталя
        medkitData[11][1] = 131.0;
        medkitData[11][4] = 0.1;

        medkitData[12][0] = 302.0;//в центре гонки по грунтовой дороге
        medkitData[12][1] = 44.0;
        medkitData[12][4] = 0.1;

        medkitData[13][0] = 216.0;//аптека возле rock-city
        medkitData[13][1] = 250.0;
        medkitData[13][4] = 0.1;

        medkitData[14][0] = 83.0;//в одной из машин по утилизации мусора
        medkitData[14][1] = 428.0;
        medkitData[14][4] = 0.1;

        medkitData[15][0] = 126.0;//рядом с лестницей на кришу здания, на территории завода наркотиков
        medkitData[15][1] = 447.0;
        medkitData[15][4] = 0.1;

        medkitData[16][0] = 121.0;//в здании аэропорта
        medkitData[16][1] = 238.0;
        medkitData[16][4] = 0.2;

        medkitData[17][0] = 305.0;//под трапом
        medkitData[17][1] = 344.0;
        medkitData[17][4] = 0.2;

        medkitData[18][0] = 310.0;//на одном из прицалов лодочной станции Верситти
        medkitData[18][1] = 403.0;
        medkitData[18][4] = 0.2;

        medkitData[19][0] = 373.0;//в баре особняка
        medkitData[19][1] = 160.0;
        medkitData[19][4] = 0.2;

        medkitData[20][0] = 300.0;//под аркой моста
        medkitData[20][1] = 143.0;
        medkitData[20][4] = 0.2;

        medkitData[21][0] = 258.0;//в аптеке, рядом с pay 'n' spray
        medkitData[21][1] = 29.0;
        medkitData[21][4] = 0.2;

        medkitData[22][0] = 256.0;//рядом с госпиталем
        medkitData[22][1] = 136.0;
        medkitData[22][4] = 0.2;

        medkitData[23][0] = 239.0;//на крыше одного из зданий, что на территори мороженной фабрика
        medkitData[23][1] = 176.0;
        medkitData[23][4] = 0.2;

        medkitData[24][0] = 401.0;//в подвале особняка
        medkitData[24][1] = 159.0;
        medkitData[24][4] = 0.2;

        for (int k = 0; k < medkitData.length; k++)
        {
            if (isSouthEastMap && medkitData[k][4] == 0.4)
            {
                medkitData[k][2] = medkitData[k][0] / (double) Map.getScaled_image_width();
                medkitData[k][3] = medkitData[k][1] / (double) Map.getScaled_image_height();
                //printDebugMessage(String.valueOf(medkitData[k][3]));
            }
            if (isNorthEastMap && medkitData[k][4] == 0.3)
            {
                medkitData[k][2] = medkitData[k][0] / (double) Map.getScaled_image_width();
                medkitData[k][3] = medkitData[k][1] / (double) Map.getScaled_image_height();
                //printDebugMessage(String.valueOf(medkitData[k][3]));
            }
            if (isSouthWestMap && medkitData[k][4] == 0.2)
            {
                medkitData[k][2] = medkitData[k][0] / (double) Map.getScaled_image_width();
                medkitData[k][3] = medkitData[k][1] / (double) Map.getScaled_image_height();
                //printDebugMessage(String.valueOf(medkitData[k][3]));
            }
            if (isNorthWestMap && medkitData[k][4] == 0.1)
            {
                medkitData[k][2] = medkitData[k][0] / (double) Map.getScaled_image_width();
                medkitData[k][3] = medkitData[k][1] / (double) Map.getScaled_image_height();
                //printDebugMessage(String.valueOf(medkitData[k][3]));
            }
        }
    }

    //заполнены
    private void initArmorData()
    {
        armorData = new double[20][5];
        armorData[0][0] = 100.0;
        armorData[0][1] = 300.0;
        armorData[0][4] = 0.4;

        armorData[1][0] = 144.0;
        armorData[1][1] = 221.0;
        armorData[1][4] = 0.4;

        armorData[2][0] = 226.0;//рядом с клубом малибу
        armorData[2][1] = 65.0;
        armorData[2][4] = 0.4;

        armorData[3][0] = 137.0;//стройка рядом с Ейвери
        armorData[3][1] = 97.0;
        armorData[3][4] = 0.4;

        armorData[4][0] = 83.0;//рядом с киностудией
        armorData[4][1] = 176.0;
        armorData[4][4] = 0.3;

        armorData[5][0] = 210.0;//жилой дом рядом с торговым центром
        armorData[5][1] = 105.0;
        armorData[5][4] = 0.3;

        armorData[6][0] = 163.0;//синий дом, на втором этаже
        armorData[6][1] = 180.0;
        armorData[6][4] = 0.3;

        armorData[7][0] = 178.0;//
        armorData[7][1] = 277.0;
        armorData[7][4] = 0.3;

        armorData[8][0] = 56.0;//пеcчаная яма в гольф-клубе
        armorData[8][1] = 283.0;
        armorData[8][4] = 0.3;

        armorData[9][0] = 178.0;//
        armorData[9][1] = 362.0;
        armorData[9][4] = 0.3;

        armorData[10][0] = 276.0;//в гараже, позади клуба байкеров
        armorData[10][1] = 280.0;
        armorData[10][4] = 0.1;

        armorData[11][0] = 190.0;//за кафе Rock-city
        armorData[11][1] = 243.0;
        armorData[11][4] = 0.1;

        armorData[12][0] = 294.0;//на крыше здания, рядом с гонкой по бездорожью
        armorData[12][1] = 66.0;
        armorData[12][4] = 0.1;

        armorData[13][0] = 36.0;//на сторожевой вышке, что на территории арзмии
        armorData[13][1] = 83.0;
        armorData[13][4] = 0.2;

        armorData[14][0] = 208.0;//на крыше печатного завода
        armorData[14][1] = 78.0;
        armorData[14][4] = 0.2;

        armorData[15][0] = 184.0;//на парковке, что восточнее аэропорта
        armorData[15][1] = 254.0;
        armorData[15][4] = 0.2;

        armorData[16][0] = 294.0;//у входа на КПП, что у порта, рядом с кораблем
        armorData[16][1] = 345.0;
        armorData[16][4] = 0.2;

        armorData[17][0] = 181.0;//
        armorData[17][1] = 132.0;
        armorData[17][4] = 0.2;

        armorData[18][0] = 372.0;//в баре особняка
        armorData[18][1] = 160.0;
        armorData[18][4] = 0.2;

        armorData[19][0] = 407.0;//в подвале особняка
        armorData[19][1] = 161.0;
        armorData[19][4] = 0.2;

        for (int k = 0; k < armorData.length; k++)
        {
            if (isSouthEastMap && armorData[k][4] == 0.4)
            {
                armorData[k][2] = armorData[k][0] / (double) Map.getScaled_image_width();
                armorData[k][3] = armorData[k][1] / (double) Map.getScaled_image_height();
                //printDebugMessage(String.valueOf(medkitData[k][3]));
            }
            if (isNorthEastMap && armorData[k][4] == 0.3)
            {
                armorData[k][2] = armorData[k][0] / (double) Map.getScaled_image_width();
                armorData[k][3] = armorData[k][1] / (double) Map.getScaled_image_height();
                //printDebugMessage(String.valueOf(medkitData[k][3]));
            }
            if (isSouthWestMap && armorData[k][4] == 0.2)
            {
                armorData[k][2] = armorData[k][0] / (double) Map.getScaled_image_width();
                armorData[k][3] = armorData[k][1] / (double) Map.getScaled_image_height();
                //printDebugMessage(String.valueOf(medkitData[k][3]));
            }
            if (isNorthWestMap && armorData[k][4] == 0.1)
            {
                armorData[k][2] = armorData[k][0] / (double) Map.getScaled_image_width();
                armorData[k][3] = armorData[k][1] / (double) Map.getScaled_image_height();
                //armorData(String.valueOf(medkitData[k][3]));
            }
        }
    }

    //заполнены
    private void initBribesData()
    {
        bribesData = new double[13][5];
        bribesData[0][0] = 128.0;//в подземном городке, рядом с отелем Ocean View
        bribesData[0][1] = 323.0;
        bribesData[0][4] = 0.4;

        bribesData[1][0] = 190.0;//в переулке
        bribesData[1][1] = 176.0;
        bribesData[1][4] = 0.4;

        bribesData[2][0] = 190.0;
        bribesData[2][1] = 37.0;
        bribesData[2][4] = 0.4;

        bribesData[3][0] = 96.0;//на дороге, что ведет к киностудии
        bribesData[3][1] = 180.0;
        bribesData[3][4] = 0.3;

        bribesData[4][0] = 174.0;//на дороге, что южнее торгового центра
        bribesData[4][1] = 160.0;
        bribesData[4][4] = 0.3;

        bribesData[5][0] = 191.0;//между пальм, что между пиццерией и клубом "Малибу"
        bribesData[5][1] = 392.0;
        bribesData[5][4] = 0.3;

        bribesData[6][0] = 168.0;//в переулке, рядом с недвижимостью
        bribesData[6][1] = 318.0;
        bribesData[6][4] = 0.3;

        bribesData[7][0] = 218.0;//рядом с апартаментами Hyman Condo
        bribesData[7][1] = 84.0;
        bribesData[7][4] = 0.1;

        bribesData[8][0] = 168.0;//в переуглке, с гайтянскийм поселком
        bribesData[8][1] = 442.0;
        bribesData[8][4] = 0.1;

        bribesData[9][0] = 172.0;//рядом с уникальным прыжком
        bribesData[9][1] = 380.0;
        bribesData[9][4] = 0.1;

        bribesData[10][0] = 258.0;//рядом с прыжком, строго восточнее автосалона
        bribesData[10][1] = 238.0;
        bribesData[10][4] = 0.2;

        bribesData[11][0] = 220;//в длинном переуле, редом с магазином инструментов
        bribesData[11][1] = 170.0;
        bribesData[11][4] = 0.2;

        bribesData[12][0] = 243;//над каналом, в гаитянском районе
        bribesData[12][1] = 42.0;
        bribesData[12][4] = 0.2;

        for (int k = 0; k < bribesData.length; k++)
        {
            if (isSouthEastMap && bribesData[k][4] == 0.4)
            {
                bribesData[k][2] = bribesData[k][0] / (double) Map.getScaled_image_width();
                bribesData[k][3] = bribesData[k][1] / (double) Map.getScaled_image_height();
                //printDebugMessage(String.valueOf(medkitData[k][3]));
            }
            if (isNorthEastMap && bribesData[k][4] == 0.3)
            {
                bribesData[k][2] = bribesData[k][0] / (double) Map.getScaled_image_width();
                bribesData[k][3] = bribesData[k][1] / (double) Map.getScaled_image_height();
                //printDebugMessage(String.valueOf(medkitData[k][3]));
            }
            if (isSouthWestMap && bribesData[k][4] == 0.2)
            {
                bribesData[k][2] = bribesData[k][0] / (double) Map.getScaled_image_width();
                bribesData[k][3] = bribesData[k][1] / (double) Map.getScaled_image_height();
                //printDebugMessage(String.valueOf(medkitData[k][3]));
            }
            if (isNorthWestMap && bribesData[k][4] == 0.1)
            {
                bribesData[k][2] = bribesData[k][0] / (double) Map.getScaled_image_width();
                bribesData[k][3] = bribesData[k][1] / (double) Map.getScaled_image_height();
                //printDebugMessage(String.valueOf(medkitData[k][3]));
            }
        }
    }

    //заполнены
    private void initDrugsData()
    {
        drugsData = new double[15][5];
        drugsData[0][0] = 90.0;//в здании напротив больницы, на втором этаже
        drugsData[0][1] = 237.0;
        drugsData[0][4] = 0.4;

        drugsData[1][0] = 212.0;//
        drugsData[1][1] = 115.0;
        drugsData[1][4] = 0.4;

        drugsData[2][0] = 186.0;//за стоянкой клуба "Малибу"
        drugsData[2][1] = 410.0;
        drugsData[2][4] = 0.3;

        drugsData[3][0] = 180.0;//балкон коттеджа
        drugsData[3][1] = 355.0;
        drugsData[3][4] = 0.3;

        drugsData[4][0] = 168.0;//в аптеке, рядом с госпиталем
        drugsData[4][1] = 218.0;
        drugsData[4][4] = 0.3;

        drugsData[5][0] = 180.0;//у ступенек, южнее торгового центром
        drugsData[5][1] = 171.0;
        drugsData[5][4] = 0.3;

        drugsData[6][0] = 136.0;//на территории завода по производству наркотиков
        drugsData[6][1] = 431.0;
        drugsData[6][4] = 0.1;

        drugsData[7][0] = 200.0;//в переулке под мостом
        drugsData[7][1] = 431.0;
        drugsData[7][4] = 0.1;

        drugsData[8][0] = 336.0;//у моста Prawn Island
        drugsData[8][1] = 156.0;
        drugsData[8][4] = 0.1;

        drugsData[9][0] = 245.0;//на крыше оружейного магазина, у вентиляционного сооружения
        drugsData[9][1] = 107.0;
        drugsData[9][4] = 0.1;

        drugsData[10][0] = 212.0;//аптека, рядом с пиццерией
        drugsData[10][1] = 250.0;
        drugsData[10][4] = 0.1;

        drugsData[11][0] = 385.0;//спереди особняка, у забора
        drugsData[11][1] = 144.0;
        drugsData[11][4] = 0.2;

        drugsData[12][0] = 392.0;//на крыше особняка
        drugsData[12][1] = 162.0;
        drugsData[12][4] = 0.2;

        drugsData[13][0] = 218.0;//
        drugsData[13][1] = 155.0;
        drugsData[13][4] = 0.2;

        drugsData[14][0] = 262.0;//в аптеке, рядом с pay 'n' spray
        drugsData[14][1] = 31.0;
        drugsData[14][4] = 0.2;


        for (int k = 0; k < drugsData.length; k++)
        {
            if (isSouthEastMap && drugsData[k][4] == 0.4)
            {
                drugsData[k][2] = drugsData[k][0] / (double) Map.getScaled_image_width();
                drugsData[k][3] = drugsData[k][1] / (double) Map.getScaled_image_height();
                //printDebugMessage(String.valueOf(medkitData[k][3]));
            }
            if (isNorthEastMap && drugsData[k][4] == 0.3)
            {
                drugsData[k][2] = drugsData[k][0] / (double) Map.getScaled_image_width();
                drugsData[k][3] = drugsData[k][1] / (double) Map.getScaled_image_height();
                //printDebugMessage(String.valueOf(medkitData[k][3]));
            }
            if (isSouthWestMap && drugsData[k][4] == 0.2)
            {
                drugsData[k][2] = drugsData[k][0] / (double) Map.getScaled_image_width();
                drugsData[k][3] = drugsData[k][1] / (double) Map.getScaled_image_height();
                //printDebugMessage(String.valueOf(medkitData[k][3]));
            }
            if (isNorthWestMap && drugsData[k][4] == 0.1)
            {
                drugsData[k][2] = drugsData[k][0] / (double) Map.getScaled_image_width();
                drugsData[k][3] = drugsData[k][1] / (double) Map.getScaled_image_height();
                //printDebugMessage(String.valueOf(medkitData[k][3]));
            }
        }
    }

    //заполнены
    private void initSpraiesData()
    {
        spraiesData = new double[5][5];
        spraiesData[0][0] = 101.0;//
        spraiesData[0][1] = 305.0;
        spraiesData[0][4] = 0.4;

        spraiesData[1][0] = 149.0;//
        spraiesData[1][1] = 293.0;
        spraiesData[1][4] = 0.3;

        spraiesData[2][0] = 232.0;//на территории Sunshine Auto
        spraiesData[2][1] = 237.0;
        spraiesData[2][4] = 0.2;

        spraiesData[3][0] = 252.0;//на территории порта Auto
        spraiesData[3][1] = 340.0;
        spraiesData[3][4] = 0.2;

        spraiesData[4][0] = 259.0;//
        spraiesData[4][1] = 43.0;
        spraiesData[4][4] = 0.2;

        for (int k = 0; k < spraiesData.length; k++)
        {
            if (isSouthEastMap && spraiesData[k][4] == 0.4)
            {
                spraiesData[k][2] = spraiesData[k][0] / (double) Map.getScaled_image_width();
                spraiesData[k][3] = spraiesData[k][1] / (double) Map.getScaled_image_height();
                //printDebugMessage(String.valueOf(medkitData[k][3]));
            }
            if (isNorthEastMap && spraiesData[k][4] == 0.3)
            {
                spraiesData[k][2] = spraiesData[k][0] / (double) Map.getScaled_image_width();
                spraiesData[k][3] = spraiesData[k][1] / (double) Map.getScaled_image_height();
                //printDebugMessage(String.valueOf(medkitData[k][3]));
            }
            if (isSouthWestMap && spraiesData[k][4] == 0.2)
            {
                spraiesData[k][2] = spraiesData[k][0] / (double) Map.getScaled_image_width();
                spraiesData[k][3] = spraiesData[k][1] / (double) Map.getScaled_image_height();
                //printDebugMessage(String.valueOf(medkitData[k][3]));
            }
            if (isNorthWestMap && spraiesData[k][4] == 0.1)
            {
                spraiesData[k][2] = spraiesData[k][0] / (double) Map.getScaled_image_width();
                spraiesData[k][3] = spraiesData[k][1] / (double) Map.getScaled_image_height();
                //printDebugMessage(String.valueOf(medkitData[k][3]));
            }
        }
    }

    //заполнены
    private void initPropertiesData()
    {
        propertiesData = new double[16][5];
        propertiesData[0][0] = 119.0;//1102 Washington Street
        propertiesData[0][1] = 199.0;
        propertiesData[0][4] = 0.4;

        propertiesData[1][0] = 106.0;//Ocean Heights
        propertiesData[1][1] = 365.0;
        propertiesData[1][4] = 0.4;


        propertiesData[2][0] = 146.0;//Links View Apartments
        propertiesData[2][1] = 313.0;
        propertiesData[2][4] = 0.3;

        propertiesData[3][0] = 177.0;//El Swanko Casa
        propertiesData[3][1] = 253.0;
        propertiesData[3][4] = 0.3;

        propertiesData[4][0] = 207.0;//3321 Vice Point
        propertiesData[4][1] = 82.0;
        propertiesData[4][4] = 0.3;

        propertiesData[5][0] = 152.0;//Ocean View
        propertiesData[5][1] = 314.0;
        propertiesData[5][4] = 0.4;

        propertiesData[6][0] = 295.0;//Skumole Shack
        propertiesData[6][1] = 237.0;
        propertiesData[6][4] = 0.1;

        propertiesData[7][0] = 212.0;//Hyman Condo
        propertiesData[7][1] = 77.0;
        propertiesData[7][4] = 0.1;

        propertiesData[8][0] = 118.0;//стрип клуб
        propertiesData[8][1] = 354.0;
        propertiesData[8][4] = 0.4;

        propertiesData[9][0] = 194.0;//клуб "Малибу"
        propertiesData[9][1] = 426.0;
        propertiesData[9][4] = 0.3;

        propertiesData[10][0] = 68.0;//киностудия
        propertiesData[10][1] = 166.0;
        propertiesData[10][4] = 0.3;

        propertiesData[11][0] = 222.0;//Автосалон
        propertiesData[11][1] = 235.0;
        propertiesData[11][4] = 0.2;

        propertiesData[12][0] = 202.0;//типография
        propertiesData[12][1] = 79.0;
        propertiesData[12][4] = 0.2;

        propertiesData[13][0] = 311.0;//лодочная
        propertiesData[13][1] = 397.0;
        propertiesData[13][4] = 0.2;

        propertiesData[14][0] = 259.0;//фабрика мороженного
        propertiesData[14][1] = 165.0;
        propertiesData[14][4] = 0.2;

        propertiesData[15][0] = 163.0;//такси
        propertiesData[15][1] = 404.0;
        propertiesData[15][4] = 0.1;

        for (int k = 0; k < propertiesData.length; k++)
        {
            if (isSouthEastMap && propertiesData[k][4] == 0.4)
            {
                propertiesData[k][2] = propertiesData[k][0] / (double) Map.getScaled_image_width();
                propertiesData[k][3] = propertiesData[k][1] / (double) Map.getScaled_image_height();
                //printDebugMessage(String.valueOf(medkitData[k][3]));
            }
            if (isNorthEastMap && propertiesData[k][4] == 0.3)
            {
                propertiesData[k][2] = propertiesData[k][0] / (double) Map.getScaled_image_width();
                propertiesData[k][3] = propertiesData[k][1] / (double) Map.getScaled_image_height();
                //printDebugMessage(String.valueOf(medkitData[k][3]));
            }
            if (isSouthWestMap && propertiesData[k][4] == 0.2)
            {
                propertiesData[k][2] = propertiesData[k][0] / (double) Map.getScaled_image_width();
                propertiesData[k][3] = propertiesData[k][1] / (double) Map.getScaled_image_height();
                //printDebugMessage(String.valueOf(medkitData[k][3]));
            }
            if (isNorthWestMap && propertiesData[k][4] == 0.1)
            {
                propertiesData[k][2] = propertiesData[k][0] / (double) Map.getScaled_image_width();
                propertiesData[k][3] = propertiesData[k][1] / (double) Map.getScaled_image_height();
                //printDebugMessage(String.valueOf(medkitData[k][3]));
            }
        }
    }

    //заполнены
    private void initRampagesData()
    {
        rampagesData = new double[35][5];
        rampagesData[0][0] = 53.0;//рядом со входом в здание
        rampagesData[0][1] = 270.0;
        rampagesData[0][4] = 0.4;

        rampagesData[1][0] = 15.0;//на острове
        rampagesData[1][1] = 426.0;
        rampagesData[1][4] = 0.4;

        rampagesData[2][0] = 168.0;//рядом с мостом на маяк
        rampagesData[2][1] = 395.0;
        rampagesData[2][4] = 0.4;

        rampagesData[3][0] = 215.0;//на пляже, севернее маяка
        rampagesData[3][1] = 386.0;
        rampagesData[3][4] = 0.4;

        rampagesData[4][0] = 98.0;//катана
        rampagesData[4][1] = 280.0;
        rampagesData[4][4] = 0.4;

        rampagesData[5][0] = 116.0;//цепная пила, рядом с магазино одежды рафаэля
        rampagesData[5][1] = 275.0;
        rampagesData[5][4] = 0.4;

        rampagesData[6][0] = 93.0;//в торговом центре
        rampagesData[6][1] = 235.0;
        rampagesData[6][4] = 0.4;

        rampagesData[7][0] = 120.0;//на крыше здания, по соседкству с парковкой
        rampagesData[7][1] = 315.0;
        rampagesData[7][4] = 0.4;

        rampagesData[8][0] = 155.0;//на обочине дороги с круговым движением
        rampagesData[8][1] = 8.0;
        rampagesData[8][4] = 0.4;

        rampagesData[9][0] = 133.0;//западнее магазина иснтрументов
        rampagesData[9][1] = 145.0;
        rampagesData[9][4] = 0.4;

        rampagesData[10][0] = 240.0;//здание с бассейном
        rampagesData[10][1] = 94.0;
        rampagesData[10][4] = 0.4;

        rampagesData[11][0] = 240.0;//здание с бассейном
        rampagesData[11][1] = 68.0;
        rampagesData[11][4] = 0.4;

        rampagesData[12][0] = 220.0;//за зданием, что рядом с недвижимостью
        rampagesData[12][1] = 106.0;
        rampagesData[12][4] = 0.3;

        rampagesData[13][0] = 193.0;//вблизи восточного входа в торговый центр
        rampagesData[13][1] = 129.0;
        rampagesData[13][4] = 0.3;

        rampagesData[14][0] = 146.0;//На территории здания, что севернее парковки рядом с торговым центром
        rampagesData[14][1] = 78.0;
        rampagesData[14][4] = 0.3;

        rampagesData[15][0] = 169.0;//На территории торгового центра
        rampagesData[15][1] = 125.0;
        rampagesData[15][4] = 0.3;

        rampagesData[16][0] = 133.0;//под мостом, что между гольф-клубом и домом Мерседес
        rampagesData[16][1] = 344.0;
        rampagesData[16][4] = 0.3;

        rampagesData[17][0] = 322.0;//
        rampagesData[17][1] = 96.0;
        rampagesData[17][4] = 0.1;

        rampagesData[18][0] = 308.0;//
        rampagesData[18][1] = 136.0;
        rampagesData[18][4] = 0.1;

        rampagesData[19][0] = 258.0;//напротив оружейного магазино, на лестнице
        rampagesData[19][1] = 131.0;
        rampagesData[19][4] = 0.1;

        rampagesData[20][0] = 132.0;//рядом со стадионом
        rampagesData[20][1] = 41.0;
        rampagesData[20][4] = 0.1;

        rampagesData[21][0] = 227.0;//За зданием-стеной с парковкой
        rampagesData[21][1] = 292.0;
        rampagesData[21][4] = 0.1;

        rampagesData[22][0] = 188.0;//за пиццерией
        rampagesData[22][1] = 248.0;
        rampagesData[22][4] = 0.1;

        rampagesData[23][0] = 164.0;//на краю
        rampagesData[23][1] = 42.0;
        rampagesData[23][4] = 0.2;

        rampagesData[24][0] = 220.0;//
        rampagesData[24][1] = 56.0;
        rampagesData[24][4] = 0.2;

        rampagesData[25][0] = 308.0;//за зданием на острове
        rampagesData[25][1] = 122.0;
        rampagesData[25][4] = 0.2;

        rampagesData[26][0] = 256.0;//на крыше мороженной фабрики
        rampagesData[26][1] = 144.0;
        rampagesData[26][4] = 0.2;

        rampagesData[27][0] = 235.0;//южнее госпиталя
        rampagesData[27][1] = 106.0;
        rampagesData[27][4] = 0.2;

        rampagesData[28][0] = 187.0;//на крыше здания, что рядом с кафе Роберто
        rampagesData[28][1] = 197.0;
        rampagesData[28][4] = 0.2;

        rampagesData[29][0] = 236.0;//позади здания с бассейном
        rampagesData[29][1] = 323.0;
        rampagesData[29][4] = 0.2;

        rampagesData[30][0] = 318.0;//на грузовом корабле
        rampagesData[30][1] = 358.0;
        rampagesData[30][4] = 0.2;

        rampagesData[31][0] = 91.0;//в кустах, восточнее военной базы
        rampagesData[31][1] = 88.0;
        rampagesData[31][4] = 0.2;

        rampagesData[32][0] = 187.0;//в центре баскетбольной площадки
        rampagesData[32][1] = 115.0;
        rampagesData[32][4] = 0.2;

        rampagesData[33][0] = 115.0;//на крыше аэропорта
        rampagesData[33][1] = 232.0;
        rampagesData[33][4] = 0.2;

        rampagesData[34][0] = 102.0;//в здании аэропорта
        rampagesData[34][1] = 245.0;
        rampagesData[34][4] = 0.2;


        for (int k = 0; k < rampagesData.length; k++)
        {
            if (isSouthEastMap && rampagesData[k][4] == 0.4)
            {
                rampagesData[k][2] = rampagesData[k][0] / (double) Map.getScaled_image_width();
                rampagesData[k][3] = rampagesData[k][1] / (double) Map.getScaled_image_height();
            }
            if (isNorthEastMap && rampagesData[k][4] == 0.3)
            {
                rampagesData[k][2] = rampagesData[k][0] / (double) Map.getScaled_image_width();
                rampagesData[k][3] = rampagesData[k][1] / (double) Map.getScaled_image_height();
            }
            if (isSouthWestMap && rampagesData[k][4] == 0.2)
            {
                rampagesData[k][2] = rampagesData[k][0] / (double) Map.getScaled_image_width();
                rampagesData[k][3] = rampagesData[k][1] / (double) Map.getScaled_image_height();
            }
            if (isNorthWestMap && rampagesData[k][4] == 0.1)
            {
                rampagesData[k][2] = rampagesData[k][0] / (double) Map.getScaled_image_width();
                rampagesData[k][3] = rampagesData[k][1] / (double) Map.getScaled_image_height();
            }
        }
    }

    //заполнены
    private void initClothesData()
    {
        clothesData = new double[13][5];
        clothesData[0][0] = 152.0;//уличная в Ocean View
        clothesData[0][1] = 318.0;
        clothesData[0][4] = 0.4;

        clothesData[1][0] = 126.0;//у рафаэля
        clothesData[1][1] = 284.0;
        clothesData[1][4] = 0.4;

        clothesData[2][0] = 138.0;//mr.versetty
        clothesData[2][1] = 312.0;
        clothesData[2][4] = 0.4;

        clothesData[3][0] = 194.0;//Полицейский
        clothesData[3][1] = 132.0;
        clothesData[3][4] = 0.4;

        clothesData[4][0] = 191.0;//в клубе "Малибу"
        clothesData[4][1] = 425.0;
        clothesData[4][4] = 0.3;

        clothesData[5][0] = 101.0;//у гольф клуба
        clothesData[5][1] = 342.0;
        clothesData[5][4] = 0.3;

        clothesData[6][0] = 176.0;//в тороговом центре
        clothesData[6][1] = 152.0;
        clothesData[6][4] = 0.3;

        clothesData[7][0] = 172.0;//в торговом центре
        clothesData[7][1] = 133.0;
        clothesData[7][4] = 0.3;

        clothesData[8][0] = 210.0;//на крыше hyman condo
        clothesData[8][1] = 72.0;
        clothesData[8][4] = 0.1;

        clothesData[9][0] = 188.0;//в торговом центре
        clothesData[9][1] = 208.0;
        clothesData[9][4] = 0.1;

        clothesData[10][0] = 173.0;//спортивная
        clothesData[10][1] = 98.0;
        clothesData[10][4] = 0.2;

        clothesData[11][0] = 220.0;//кубинская
        clothesData[11][1] = 122.0;
        clothesData[11][4] = 0.2;

//        clothesData[12][0] = 385.0;//в особняке
//        clothesData[12][1] = 162.0;
//        clothesData[12][4] = 0.2;

        clothesData[12][0] = 379.0;//в особняке
        clothesData[12][1] = 164.0;
        clothesData[12][4] = 0.2;

        for (int k = 0; k < clothesData.length; k++)
        {
            if (isSouthEastMap && clothesData[k][4] == 0.4)
            {
                clothesData[k][2] = clothesData[k][0] / (double) Map.getScaled_image_width();
                clothesData[k][3] = clothesData[k][1] / (double) Map.getScaled_image_height();
            }
            if (isNorthEastMap && clothesData[k][4] == 0.3)
            {
                clothesData[k][2] = clothesData[k][0] / (double) Map.getScaled_image_width();
                clothesData[k][3] = clothesData[k][1] / (double) Map.getScaled_image_height();
            }
            if (isSouthWestMap && clothesData[k][4] == 0.2)
            {
                clothesData[k][2] = clothesData[k][0] / (double) Map.getScaled_image_width();
                clothesData[k][3] = clothesData[k][1] / (double) Map.getScaled_image_height();
            }
            if (isNorthWestMap && clothesData[k][4] == 0.1)
            {
                clothesData[k][2] = clothesData[k][0] / (double) Map.getScaled_image_width();
                clothesData[k][3] = clothesData[k][1] / (double) Map.getScaled_image_height();
            }
        }
    }

    //заполнены
    private void initWeaponsData()
    {
        weaponsData = new double[41][5];
        weaponsData[0][0] = 147.0;//бейсбольная бита
        weaponsData[0][1] = 312.0;
        weaponsData[0][4] = 0.4;

        weaponsData[1][0] = 156.0;//кастет
        weaponsData[1][1] = 296.0;
        weaponsData[1][4] = 0.4;

        weaponsData[2][0] = 130.0;//нож
        weaponsData[2][1] = 376.0;
        weaponsData[2][4] = 0.4;

        weaponsData[3][0] = 105.0;//chainsaw
        weaponsData[3][1] = 325.0;
        weaponsData[3][4] = 0.4;

        weaponsData[4][0] = 104.0;//uzi
        weaponsData[4][1] = 309.0;
        weaponsData[4][4] = 0.4;

        weaponsData[5][0] = 48.0;//пистолет рядом с причалом
        weaponsData[5][1] = 322.0;
        weaponsData[5][4] = 0.4;

        weaponsData[6][0] = 112.0;//дробовик на парковке
        weaponsData[6][1] = 238.0;
        weaponsData[6][4] = 0.4;

        weaponsData[7][0] = 233.0;//дробовик рядом со скамейко
        weaponsData[7][1] = 122.0;
        weaponsData[7][4] = 0.4;

        weaponsData[8][0] = 195.0;//дубинка в полицейской раздевалке
        weaponsData[8][1] = 133.0;
        weaponsData[8][4] = 0.4;

        weaponsData[9][0] = 184.0;//гранаты на маленькой стоянке полицейского участка
        weaponsData[9][1] = 136.0;
        weaponsData[9][4] = 0.4;

        weaponsData[10][0] = 181.0;//пистолет на стройке Эйвери
        weaponsData[10][1] = 72.0;
        weaponsData[10][4] = 0.4;

        weaponsData[11][0] = 110.0;//У арки моста мачете
        weaponsData[11][1] = 122.0;
        weaponsData[11][4] = 0.4;

        weaponsData[12][0] = 167.0;//TEC9 между двумя домами
        weaponsData[12][1] = 8.0;
        weaponsData[12][4] = 0.4;

        weaponsData[13][0] = 191.0;//нож в клубе "Малибу"
        weaponsData[13][1] = 429.0;
        weaponsData[13][4] = 0.3;

        weaponsData[14][0] = 64.0;//M4 на киностудии
        weaponsData[14][1] = 156.0;
        weaponsData[14][4] = 0.3;

        weaponsData[15][0] = 75.0;//uzi на балконе дома
        weaponsData[15][1] = 122.0;
        weaponsData[15][4] = 0.3;

        weaponsData[16][0] = 190.0;//катана в торговом центре
        weaponsData[16][1] = 155.0;
        weaponsData[16][4] = 0.3;

        weaponsData[17][0] = 178.0;//Ruger на крыше El Swanco
        weaponsData[17][1] = 264.0;
        weaponsData[17][4] = 0.3;

        weaponsData[18][0] = 214.0;//гранаты с дистанционным управлением
        weaponsData[18][1] = 359.0;
        weaponsData[18][4] = 0.3;

        weaponsData[19][0] = 97.0;//клюшка для гольфа позади главного входа
        weaponsData[19][1] = 332.0;
        weaponsData[19][4] = 0.3;

        weaponsData[20][0] = 172.0;//нож мясника позади пиццерии
        weaponsData[20][1] = 380.0;
        weaponsData[20][4] = 0.3;

        weaponsData[21][0] = 295.0;//uzi рядом с недвижисостю
        weaponsData[21][1] = 261.0;
        weaponsData[21][4] = 0.1;

        weaponsData[22][0] = 233.0;//нож
        weaponsData[22][1] = 75.0;
        weaponsData[22][4] = 0.1;

        weaponsData[23][0] = 75.0;//обрез
        weaponsData[23][1] = 410.0;
        weaponsData[23][4] = 0.1;

        weaponsData[24][0] = 170.0;//M4 посреди гаитянской деревни
        weaponsData[24][1] = 429.0;
        weaponsData[24][4] = 0.1;

        weaponsData[25][0] = 108.0;//minugun
        weaponsData[25][1] = 428.0;
        weaponsData[25][4] = 0.1;

        weaponsData[26][0] = 286.0;//снайперская винтовка на опоре моста
        weaponsData[26][1] = 260.0;
        weaponsData[26][4] = 0.2;

        weaponsData[27][0] = 229.0;//РПГ в бассейне
        weaponsData[27][1] = 318.0;
        weaponsData[27][4] = 0.2;

        weaponsData[28][0] = 294.0;//mac рядом с пони
        weaponsData[28][1] = 390.0;
        weaponsData[28][4] = 0.2;

        weaponsData[29][0] = 224.0;//огнемет
        weaponsData[29][1] = 376.0;
        weaponsData[29][4] = 0.2;

        weaponsData[30][0] = 131.0;//обрез за постером
        weaponsData[30][1] = 200.0;
        weaponsData[30][4] = 0.2;

        weaponsData[31][0] = 35.0;//M60 на вышке
        weaponsData[31][1] = 86.0;
        weaponsData[31][4] = 0.2;

        weaponsData[32][0] = 168.0;//M4 за забором
        weaponsData[32][1] = 175.0;
        weaponsData[32][4] = 0.2;

        weaponsData[33][0] = 246.0;//гранаты на баскетбольной площадке
        weaponsData[33][1] = 137.0;
        weaponsData[33][4] = 0.2;

        weaponsData[34][0] = 338.0;//огнемет в бассейне
        weaponsData[34][1] = 116.0;
        weaponsData[34][4] = 0.2;

        weaponsData[35][0] = 355.0;//снайперская винтовка в лабиринте
        weaponsData[35][1] = 161.0;
        weaponsData[35][4] = 0.2;

        weaponsData[36][0] = 336.0;//катана
        weaponsData[36][1] = 158.0;
        weaponsData[36][4] = 0.2;

        weaponsData[37][0] = 176.0;//кольт в тупике
        weaponsData[37][1] = 37.0;
        weaponsData[37][4] = 0.2;

        weaponsData[38][0] = 375.0;//кольт в баре особняка
        weaponsData[38][1] = 160.0;
        weaponsData[38][4] = 0.2;

        weaponsData[39][0] = 398.0;//дробовик в подвале
        weaponsData[39][1] = 161.0;
        weaponsData[39][4] = 0.2;

        weaponsData[40][0] = 194.0;//снайперская винтовка на крыше
        weaponsData[40][1] = 172.0;
        weaponsData[40][4] = 0.2;


        for (int k = 0; k < weaponsData.length; k++)
        {
            if (isSouthEastMap && weaponsData[k][4] == 0.4)
            {
                weaponsData[k][2] = weaponsData[k][0] / (double) Map.getScaled_image_width();
                weaponsData[k][3] = weaponsData[k][1] / (double) Map.getScaled_image_height();
            }
            if (isNorthEastMap && weaponsData[k][4] == 0.3)
            {
                weaponsData[k][2] = weaponsData[k][0] / (double) Map.getScaled_image_width();
                weaponsData[k][3] = weaponsData[k][1] / (double) Map.getScaled_image_height();
            }
            if (isSouthWestMap && weaponsData[k][4] == 0.2)
            {
                weaponsData[k][2] = weaponsData[k][0] / (double) Map.getScaled_image_width();
                weaponsData[k][3] = weaponsData[k][1] / (double) Map.getScaled_image_height();
            }
            if (isNorthWestMap && weaponsData[k][4] == 0.1)
            {
                weaponsData[k][2] = weaponsData[k][0] / (double) Map.getScaled_image_width();
                weaponsData[k][3] = weaponsData[k][1] / (double) Map.getScaled_image_height();
            }
        }
    }

    //заполнены
    private void initStoresData()
    {
        storesData = new double[15][5];
        storesData[0][0] = 146.0;//магазин инструментов
        storesData[0][1] = 130.0;
        storesData[0][4] = 0.4;

        storesData[1][0] = 171.0;//Jeverly
        storesData[1][1] = 364.0;
        storesData[1][4] = 0.3;

        storesData[2][0] = 166.0;//аптека
        storesData[2][1] = 219.0;
        storesData[2][4] = 0.3;

        storesData[3][0] = 186.0;
        storesData[3][1] = 210.0;
        storesData[3][4] = 0.3;

        storesData[4][0] = 182.0;//Jeverly 2
        storesData[4][1] = 104.0;
        storesData[4][4] = 0.3;

        storesData[5][0] = 167.0;
        storesData[5][1] = 124.0;
        storesData[5][4] = 0.3;

        storesData[6][0] = 168.0;
        storesData[6][1] = 130.0;
        storesData[6][4] = 0.3;

        storesData[7][0] = 178.0;//gash
        storesData[7][1] = 146.0;
        storesData[7][4] = 0.3;

        storesData[8][0] = 209.0;//jeverly
        storesData[8][1] = 217.0;
        storesData[8][4] = 0.1;

        storesData[9][0] = 210.0;//pharmacy
        storesData[9][1] = 249.0;
        storesData[9][4] = 0.1;

        storesData[10][0] = 185.0;//cafe robina
        storesData[10][1] = 173.0;
        storesData[10][4] = 0.2;

        storesData[11][0] = 232.0;//screwThis
        storesData[11][1] = 192.0;
        storesData[11][4] = 0.2;

        storesData[12][0] = 264.0;//Doughnut Shop
        storesData[12][1] = 177.0;
        storesData[12][4] = 0.2;

        storesData[13][0] = 174.0;//Laundromat
        storesData[13][1] = 97.0;
        storesData[13][4] = 0.2;

        storesData[14][0] = 264.0;//Ryton Aide Pharmacy
        storesData[14][1] = 33.0;
        storesData[14][4] = 0.2;


        for (int k = 0; k < storesData.length; k++)
        {
            if (isSouthEastMap && storesData[k][4] == 0.4)
            {
                storesData[k][2] = storesData[k][0] / (double) Map.getScaled_image_width();
                storesData[k][3] = storesData[k][1] / (double) Map.getScaled_image_height();
            }
            if (isNorthEastMap && storesData[k][4] == 0.3)
            {
                storesData[k][2] = storesData[k][0] / (double) Map.getScaled_image_width();
                storesData[k][3] = storesData[k][1] / (double) Map.getScaled_image_height();
            }
            if (isSouthWestMap && storesData[k][4] == 0.2)
            {
                storesData[k][2] = storesData[k][0] / (double) Map.getScaled_image_width();
                storesData[k][3] = storesData[k][1] / (double) Map.getScaled_image_height();
            }
            if (isNorthWestMap && storesData[k][4] == 0.1)
            {
                storesData[k][2] = storesData[k][0] / (double) Map.getScaled_image_width();
                storesData[k][3] = storesData[k][1] / (double) Map.getScaled_image_height();
            }
        }
    }

    private void initVehiclesData()
    {
        vehiclesData = new double[60][5];
        vehiclesData[0][0] = 158.0;//Oceanic
        vehiclesData[0][1] = 318.0;
        vehiclesData[0][4] = 0.4;

        vehiclesData[1][0] = 192.0;//гольф-машина
        vehiclesData[1][1] = 416.0;
        vehiclesData[1][4] = 0.4;

        vehiclesData[2][0] = 44.0;//Cheetah
        vehiclesData[2][1] = 340.0;
        vehiclesData[2][4] = 0.4;

        vehiclesData[3][0] = 42.0;//PCJ-600
        vehiclesData[3][1] = 315.0;
        vehiclesData[3][4] = 0.4;

        vehiclesData[4][0] = 96.0;//PCJ-600
        vehiclesData[4][1] = 296.0;
        vehiclesData[4][4] = 0.4;

        vehiclesData[5][0] = 72.0;//Ambulance
        vehiclesData[5][1] = 234.0;
        vehiclesData[5][4] = 0.4;

        vehiclesData[6][0] = 129.0;//faggio
        vehiclesData[6][1] = 208.0;
        vehiclesData[6][4] = 0.4;

        vehiclesData[7][0] = 180.0;//police
        vehiclesData[7][1] = 138.0;
        vehiclesData[7][4] = 0.4;

        vehiclesData[8][0] = 221.0;//PCJ-600
        vehiclesData[8][1] = 94.0;
        vehiclesData[8][4] = 0.4;

        vehiclesData[9][0] = 292.0;//Coast Guard
        vehiclesData[9][1] = 294.0;
        vehiclesData[9][4] = 0.2;

        vehiclesData[10][0] = 191.0;//Rumpo
        vehiclesData[10][1] = 424.0;
        vehiclesData[10][4] = 0.3;

        vehiclesData[11][0] = 166.0;//faggio
        vehiclesData[11][1] = 290.0;
        vehiclesData[11][4] = 0.3;

        vehiclesData[12][0] = 200.0;//police
        vehiclesData[12][1] = 280.0;
        vehiclesData[12][4] = 0.3;

        vehiclesData[13][0] = 355.0;//Stinger
        vehiclesData[13][1] = 125.0;
        vehiclesData[13][4] = 0.2;

        vehiclesData[14][0] = 110.0;//banchee
        vehiclesData[14][1] = 304.0;
        vehiclesData[14][4] = 0.4;

        vehiclesData[15][0] = 75.0;//gang burriton
        vehiclesData[15][1] = 121.0;
        vehiclesData[15][4] = 0.3;

        vehiclesData[16][0] = 168.0;//freeway
        vehiclesData[16][1] = 200.0;
        vehiclesData[16][4] = 0.3;

        vehiclesData[17][0] = 110.0;//helmet
        vehiclesData[17][1] = 320.0;
        vehiclesData[17][4] = 0.4;

        vehiclesData[18][0] = 250.0;//bf Injection
        vehiclesData[18][1] = 67.0;
        vehiclesData[18][4] = 0.4;

        vehiclesData[19][0] = 104.0;//comet
        vehiclesData[19][1] = 346.0;
        vehiclesData[19][4] = 0.3;

        vehiclesData[20][0] = 195.0;//FBI washington
        vehiclesData[20][1] = 260.0;
        vehiclesData[20][4] = 0.1;

        vehiclesData[21][0] = 276.0;//freeway
        vehiclesData[21][1] = 273.0;
        vehiclesData[21][4] = 0.1;

        vehiclesData[22][0] = 276.0;//pcj-600
        vehiclesData[22][1] = 266.0;
        vehiclesData[22][4] = 0.1;

        vehiclesData[23][0] = 269.0;//police maverick
        vehiclesData[23][1] = 229.0;
        vehiclesData[23][4] = 0.1;

        vehiclesData[24][0] = 86.0;//trashmaster
        vehiclesData[24][1] = 431.0;
        vehiclesData[24][4] = 0.1;

        vehiclesData[25][0] = 422.0;//cheetah
        vehiclesData[25][1] = 128.0;
        vehiclesData[25][4] = 0.2;

        vehiclesData[26][0] = 192.0;//abulance
        vehiclesData[26][1] = 230.0;
        vehiclesData[26][4] = 0.3;

        vehiclesData[27][0] = 176.0;//pizza Boy
        vehiclesData[27][1] = 386.0;
        vehiclesData[27][4] = 0.3;

        vehiclesData[28][0] = 262.0;//police
        vehiclesData[28][1] = 238.0;
        vehiclesData[28][4] = 0.1;

        vehiclesData[29][0] = 250.0;//firetruck
        vehiclesData[29][1] = 198.0;
        vehiclesData[29][4] = 0.1;

        vehiclesData[30][0] = 308.0;//vcn Maverick
        vehiclesData[30][1] = 133.0;
        vehiclesData[30][4] = 0.1;

        vehiclesData[31][0] = 220.0;//amulance
        vehiclesData[31][1] = 138.0;
        vehiclesData[31][4] = 0.1;

        vehiclesData[32][0] = 328.0;//Sanchez & Landstalker
        vehiclesData[32][1] = 48.0;
        vehiclesData[32][4] = 0.1;

        vehiclesData[33][0] = 206.0;//Maverick
        vehiclesData[33][1] = 72.0;
        vehiclesData[33][4] = 0.1;

        vehiclesData[34][0] = 190.0;//Pizza Boy
        vehiclesData[34][1] = 232.0;
        vehiclesData[34][4] = 0.1;

        vehiclesData[35][0] = 132.0;//Patriot
        vehiclesData[35][1] = 376.0;
        vehiclesData[35][4] = 0.1;

        vehiclesData[36][0] = 176.0;//Voodoo
        vehiclesData[36][1] = 425.0;
        vehiclesData[36][4] = 0.1;

        vehiclesData[37][0] = 158.0;//Pizza Boy
        vehiclesData[37][1] = 439.0;
        vehiclesData[37][4] = 0.1;

        vehiclesData[38][0] = 260.0;//Securicar
        vehiclesData[38][1] = 104.0;
        vehiclesData[38][4] = 0.2;

        vehiclesData[39][0] = 268.0;//Police
        vehiclesData[39][1] = 189.0;
        vehiclesData[39][4] = 0.2;

        vehiclesData[40][0] = 260.0;//Police cheetah
        vehiclesData[40][1] = 200.0;
        vehiclesData[40][4] = 0.2;

        vehiclesData[41][0] = 292.0;//Pony
        vehiclesData[41][1] = 392.0;
        vehiclesData[41][4] = 0.2;

        vehiclesData[42][0] = 288.0;//Linerunner
        vehiclesData[42][1] = 366.0;
        vehiclesData[42][4] = 0.2;

        vehiclesData[43][0] = 82.0;//Baggage
        vehiclesData[43][1] = 220.0;
        vehiclesData[43][4] = 0.2;

        vehiclesData[44][0] = 164.0;//Top Fun
        vehiclesData[44][1] = 336.0;
        vehiclesData[44][4] = 0.2;

        vehiclesData[45][0] = 184.0;//Bus
        vehiclesData[45][1] = 348.0;
        vehiclesData[45][4] = 0.2;

        vehiclesData[46][0] = 186.0;//Sentinel XS
        vehiclesData[46][1] = 260.0;
        vehiclesData[46][4] = 0.2;

        vehiclesData[47][0] = 390.0;//Infernus
        vehiclesData[47][1] = 153.0;
        vehiclesData[47][4] = 0.2;

        vehiclesData[48][0] = 414.0;//Comet
        vehiclesData[48][1] = 94.0;
        vehiclesData[48][4] = 0.2;

        vehiclesData[49][0] = 332.0;//Landstalker
        vehiclesData[49][1] = 96.0;
        vehiclesData[49][4] = 0.2;

        vehiclesData[50][0] = 308.0;//Admiral
        vehiclesData[50][1] = 123.0;
        vehiclesData[50][4] = 0.2;

        vehiclesData[51][0] = 342.0;//Phoenix
        vehiclesData[51][1] = 130.0;
        vehiclesData[51][4] = 0.2;

        vehiclesData[52][0] = 168.0;//Infernus
        vehiclesData[52][1] = 99.0;
        vehiclesData[52][4] = 0.3;

        vehiclesData[53][0] = 190.0;//Faggio
        vehiclesData[53][1] = 163.0;
        vehiclesData[53][4] = 0.4;

        vehiclesData[54][0] = 130.0;//Packer
        vehiclesData[54][1] = 260.0;
        vehiclesData[54][4] = 0.2;

        vehiclesData[55][0] = 18.0;//Baggage Handler
        vehiclesData[55][1] = 297.0;
        vehiclesData[55][4] = 0.2;

        vehiclesData[56][0] = 43.0;//Barracks OL
        vehiclesData[56][1] = 86.0;
        vehiclesData[56][4] = 0.2;

        vehiclesData[57][0] = 40.0;//Patriot
        vehiclesData[57][1] = 73.0;
        vehiclesData[57][4] = 0.2;

        vehiclesData[58][0] = 148.0;//Top Fun
        vehiclesData[58][1] = 95.0;
        vehiclesData[58][4] = 0.3;

        vehiclesData[59][0] = 270.0;//Top Fun
        vehiclesData[59][1] = 235.0;
        vehiclesData[59][4] = 0.3;

        for (int k = 0; k < vehiclesData.length; k++)
        {
            if (isSouthEastMap && vehiclesData[k][4] == 0.4)
            {
                vehiclesData[k][2] = vehiclesData[k][0] / (double) Map.getScaled_image_width();
                vehiclesData[k][3] = vehiclesData[k][1] / (double) Map.getScaled_image_height();
            }
            if (isNorthEastMap && vehiclesData[k][4] == 0.3)
            {
                vehiclesData[k][2] = vehiclesData[k][0] / (double) Map.getScaled_image_width();
                vehiclesData[k][3] = vehiclesData[k][1] / (double) Map.getScaled_image_height();
            }
            if (isSouthWestMap && vehiclesData[k][4] == 0.2)
            {
                vehiclesData[k][2] = vehiclesData[k][0] / (double) Map.getScaled_image_width();
                vehiclesData[k][3] = vehiclesData[k][1] / (double) Map.getScaled_image_height();
            }
            if (isNorthWestMap && vehiclesData[k][4] == 0.1)
            {
                vehiclesData[k][2] = vehiclesData[k][0] / (double) Map.getScaled_image_width();
                vehiclesData[k][3] = vehiclesData[k][1] / (double) Map.getScaled_image_height();
            }
        }
    }

    //заполнены
    private void initUniqueJumpsData()
    {
        uniqueJumpsData = new double[36][5];
        uniqueJumpsData[0][0] = 164.0;//возле полицейского участка
        uniqueJumpsData[0][1] = 134.0;
        uniqueJumpsData[0][4] = 0.4;

        uniqueJumpsData[1][0] = 157.0;//на противоположной стороне к предыдущему
        uniqueJumpsData[1][1] = 132.0;
        uniqueJumpsData[1][4] = 0.4;

        uniqueJumpsData[2][0] = 203.0;//ряом с клубом "Малибу"
        uniqueJumpsData[2][1] = 47.0;
        uniqueJumpsData[2][4] = 0.4;

        uniqueJumpsData[3][0] = 198.0;//у моста, что напротив полицейского участка
        uniqueJumpsData[3][1] = 89.0;
        uniqueJumpsData[3][4] = 0.4;

        uniqueJumpsData[4][0] = 206.0;//первый, с севера, трамплин в длинном перейлке
        uniqueJumpsData[4][1] = 104.0;
        uniqueJumpsData[4][4] = 0.4;

        uniqueJumpsData[5][0] = 207.0;//второй, с севера, трамплин в длинном перейлке
        uniqueJumpsData[5][1] = 136.0;
        uniqueJumpsData[5][4] = 0.4;

        uniqueJumpsData[6][0] = 213.0;//перпендикулярно направлению переулка, в сторону полиц участка
        uniqueJumpsData[6][1] = 139.0;
        uniqueJumpsData[6][4] = 0.4;

        uniqueJumpsData[7][0] = 184.0;//
        uniqueJumpsData[7][1] = 176.0;
        uniqueJumpsData[7][4] = 0.4;

        uniqueJumpsData[8][0] = 147.0;//маленький поддон, но уже в обратную сторону
        uniqueJumpsData[8][1] = 291.0;
        uniqueJumpsData[8][4] = 0.4;

        uniqueJumpsData[9][0] = 161.0;//
        uniqueJumpsData[9][1] = 236.0;
        uniqueJumpsData[9][4] = 0.4;

        uniqueJumpsData[10][0] = 109.0;//на парковке
        uniqueJumpsData[10][1] = 240.0;
        uniqueJumpsData[10][4] = 0.4;

        uniqueJumpsData[11][0] = 144.0;//северо-восточный угол здания с парковки
        uniqueJumpsData[11][1] = 243.0;
        uniqueJumpsData[11][4] = 0.4;

        uniqueJumpsData[12][0] = 125.0;//на третьем этаже парковки
        uniqueJumpsData[12][1] = 303.0;
        uniqueJumpsData[12][4] = 0.4;

        uniqueJumpsData[13][0] = 108.0;//здание с бронежилетов
        uniqueJumpsData[13][1] = 306.0;
        uniqueJumpsData[13][4] = 0.4;

        uniqueJumpsData[13][0] = 106.0;//здание с бронежилетов
        uniqueJumpsData[13][1] = 304.0;
        uniqueJumpsData[13][4] = 0.4;

        uniqueJumpsData[14][0] = 102.0;//здание южнее pay 'n' sprayетов
        uniqueJumpsData[14][1] = 323.0;
        uniqueJumpsData[14][4] = 0.4;

        uniqueJumpsData[15][0] = 168.0;//стройка Эйвери
        uniqueJumpsData[15][1] = 69.0;
        uniqueJumpsData[15][4] = 0.4;

        uniqueJumpsData[16][0] = 24.0;//первый причал с севера
        uniqueJumpsData[16][1] = 314.0;
        uniqueJumpsData[16][4] = 0.4;

        uniqueJumpsData[17][0] = 24.0;//второй причал с севера
        uniqueJumpsData[17][1] = 340.0;
        uniqueJumpsData[17][4] = 0.4;

        uniqueJumpsData[18][0] = 82.0;//залет на киностудию
        uniqueJumpsData[18][1] = 182.0;
        uniqueJumpsData[18][4] = 0.3;

        uniqueJumpsData[19][0] = 258.0;//лестница на аммуницию
        uniqueJumpsData[19][1] = 132.0;
        uniqueJumpsData[19][4] = 0.1;

        uniqueJumpsData[20][0] = 364.0;//рядом с мотстг
        uniqueJumpsData[20][1] = 138.0;
        uniqueJumpsData[20][4] = 0.1;

        uniqueJumpsData[21][0] = 210.0;//на крыше госпиталя
        uniqueJumpsData[21][1] = 134.0;
        uniqueJumpsData[21][4] = 0.1;

        uniqueJumpsData[22][0] = 190.0;//рядом со звездочкой
        uniqueJumpsData[22][1] = 385.0;
        uniqueJumpsData[22][4] = 0.1;

        uniqueJumpsData[23][0] = 220.0;//
        uniqueJumpsData[23][1] = 20.0;
        uniqueJumpsData[23][4] = 0.2;

        uniqueJumpsData[24][0] = 238.0;//прыжок в сторону pay 'n' spray
        uniqueJumpsData[24][1] = 46.0;
        uniqueJumpsData[24][4] = 0.2;

        uniqueJumpsData[25][0] = 214.0;//с вылетом на магазин инструментов
        uniqueJumpsData[25][1] = 150.0;
        uniqueJumpsData[25][4] = 0.2;

        uniqueJumpsData[26][0] = 395.0;//через лестницу одного из домов на starfish
        uniqueJumpsData[26][1] = 87.0;
        uniqueJumpsData[26][4] = 0.2;

        uniqueJumpsData[27][0] = 136.0;//Рекламный постер у аэропорта
        uniqueJumpsData[27][1] = 208.0;
        uniqueJumpsData[27][4] = 0.2;

        uniqueJumpsData[28][0] = 168.0;//в аэропорту в сторону здания на западе
        uniqueJumpsData[28][1] = 241.0;
        uniqueJumpsData[28][4] = 0.2;

        uniqueJumpsData[29][0] = 163.0;//взлетная полоса на запад
        uniqueJumpsData[29][1] = 283.0;
        uniqueJumpsData[29][4] = 0.2;

        uniqueJumpsData[30][0] = 86.0;//прыжок через радар
        uniqueJumpsData[30][1] = 324.0;
        uniqueJumpsData[30][4] = 0.2;

        uniqueJumpsData[31][0] = 79.0;//через радарр, но с другой стороны
        uniqueJumpsData[31][1] = 342.0;
        uniqueJumpsData[31][4] = 0.2;

        uniqueJumpsData[32][0] = 150.0;//первый по прямой с запада на восток
        uniqueJumpsData[32][1] = 263.0;
        uniqueJumpsData[32][4] = 0.2;

        uniqueJumpsData[33][0] = 95.0;//второй по прямой с запада на восток
        uniqueJumpsData[33][1] = 276.0;
        uniqueJumpsData[33][4] = 0.2;

        uniqueJumpsData[34][0] = 84.0;//На этой же прямой, но в обратную сторону
        uniqueJumpsData[34][1] = 283.0;
        uniqueJumpsData[34][4] = 0.2;

        uniqueJumpsData[35][0] = 294.0;//с одной высотки на другую
        uniqueJumpsData[35][1] = 224.0;
        uniqueJumpsData[35][4] = 0.1;

        for (int k = 0; k < uniqueJumpsData.length; k++)
        {
            if (isSouthEastMap && uniqueJumpsData[k][4] == 0.4)
            {
                uniqueJumpsData[k][2] = uniqueJumpsData[k][0] / (double) Map.getScaled_image_width();
                uniqueJumpsData[k][3] = uniqueJumpsData[k][1] / (double) Map.getScaled_image_height();
            }
            if (isNorthEastMap && uniqueJumpsData[k][4] == 0.3)
            {
                uniqueJumpsData[k][2] = uniqueJumpsData[k][0] / (double) Map.getScaled_image_width();
                uniqueJumpsData[k][3] = uniqueJumpsData[k][1] / (double) Map.getScaled_image_height();
            }
            if (isSouthWestMap && uniqueJumpsData[k][4] == 0.2)
            {
                uniqueJumpsData[k][2] = uniqueJumpsData[k][0] / (double) Map.getScaled_image_width();
                uniqueJumpsData[k][3] = uniqueJumpsData[k][1] / (double) Map.getScaled_image_height();
            }
            if (isNorthWestMap && uniqueJumpsData[k][4] == 0.1)
            {
                uniqueJumpsData[k][2] = uniqueJumpsData[k][0] / (double) Map.getScaled_image_width();
                uniqueJumpsData[k][3] = uniqueJumpsData[k][1] / (double) Map.getScaled_image_height();
            }
        }
    }

    //заполнены
    private void initHiddenPackagesData()
    {
        hiddenPackagesData = new double[100][5];
        hiddenPackagesData[0][0] = 79.0;//у лестници за зданием
        hiddenPackagesData[0][1] = 397.0;
        hiddenPackagesData[0][4] = 0.4;

        hiddenPackagesData[1][0] = 46.0;//под мостом
        hiddenPackagesData[1][1] = 233.0;
        hiddenPackagesData[1][4] = 0.4;

        hiddenPackagesData[2][0] = 45.0;//за Зданием
        hiddenPackagesData[2][1] = 269.0;
        hiddenPackagesData[2][4] = 0.4;

        hiddenPackagesData[3][0] = 15.0;//на разрушенном доме в море
        hiddenPackagesData[3][1] = 424.0;
        hiddenPackagesData[3][4] = 0.4;

        hiddenPackagesData[4][0] = 213.0;//на лестнице у маяка
        hiddenPackagesData[4][1] = 412.0;
        hiddenPackagesData[4][4] = 0.4;

        hiddenPackagesData[5][0] = 61.0;//подземная стоянка у причала
        hiddenPackagesData[5][1] = 322.0;
        hiddenPackagesData[5][4] = 0.4;

        hiddenPackagesData[6][0] = 49.0;//на скалах в море
        hiddenPackagesData[6][1] = 400.0;
        hiddenPackagesData[6][4] = 0.4;

        hiddenPackagesData[7][0] = 122.0;//здание восточнее парковки
        hiddenPackagesData[7][1] = 248.0;
        hiddenPackagesData[7][4] = 0.4;

        hiddenPackagesData[8][0] = 113.0;//за зданием недвижимостью
        hiddenPackagesData[8][1] = 200.0;
        hiddenPackagesData[8][4] = 0.4;

        hiddenPackagesData[9][0] = 146.0;//рядом с бронежилетом
        hiddenPackagesData[9][1] = 222.0;
        hiddenPackagesData[9][4] = 0.4;

        hiddenPackagesData[10][0] = 143.0;//У моста
        hiddenPackagesData[10][1] = 168.0;
        hiddenPackagesData[10][4] = 0.4;

        hiddenPackagesData[11][0] = 182.0;//на высотке
        hiddenPackagesData[11][1] = 149.0;
        hiddenPackagesData[11][4] = 0.4;

        hiddenPackagesData[12][0] = 180.0;//в полицейском участке
        hiddenPackagesData[12][1] = 128.0;
        hiddenPackagesData[12][4] = 0.4;

        hiddenPackagesData[13][0] = 179.0;//на крыше здания что в переулке
        hiddenPackagesData[13][1] = 196.0;
        hiddenPackagesData[13][4] = 0.4;

        hiddenPackagesData[14][0] = 276.0;//у сторожевом домике, что на пляже
        hiddenPackagesData[14][1] = 125.0;
        hiddenPackagesData[14][4] = 0.4;

        hiddenPackagesData[15][0] = 119.0;//в домике со множеством дверей
        hiddenPackagesData[15][1] = 136.0;
        hiddenPackagesData[15][4] = 0.4;

        hiddenPackagesData[16][0] = 108.0;//под мостом
        hiddenPackagesData[16][1] = 120.0;
        hiddenPackagesData[16][4] = 0.4;

        hiddenPackagesData[17][0] = 125.0;//в углу здания
        hiddenPackagesData[17][1] = 148.0;
        hiddenPackagesData[17][4] = 0.4;

        hiddenPackagesData[18][0] = 162.0;//металическая конструкция
        hiddenPackagesData[18][1] = 76.0;
        hiddenPackagesData[18][4] = 0.4;

        hiddenPackagesData[19][0] = 175.0;//у забора
        hiddenPackagesData[19][1] = 77.0;
        hiddenPackagesData[19][4] = 0.4;

        hiddenPackagesData[20][0] = 154.0;//у маленького причала, за массивом домов
        hiddenPackagesData[20][1] = 50.0;
        hiddenPackagesData[20][4] = 0.4;

        hiddenPackagesData[21][0] = 223.0;//на здании юго-восточнее малибу
        hiddenPackagesData[21][1] = 44.0;
        hiddenPackagesData[21][4] = 0.4;

        hiddenPackagesData[22][0] = 193.0;//позади малибу
        hiddenPackagesData[22][1] = 422.0;
        hiddenPackagesData[22][4] = 0.3;

        hiddenPackagesData[23][0] = 185.0;//здание с бассейном севернее малибу
        hiddenPackagesData[23][1] = 407.0;
        hiddenPackagesData[23][4] = 0.3;

        hiddenPackagesData[24][0] = 225.0;//в углу здания
        hiddenPackagesData[24][1] = 405.0;
        hiddenPackagesData[24][4] = 0.3;

        hiddenPackagesData[25][0] = 189.0;//за лестницей у коттедже
        hiddenPackagesData[25][1] = 380.0;
        hiddenPackagesData[25][4] = 0.3;

        hiddenPackagesData[26][0] = 178.0;//в здании пиццерии
        hiddenPackagesData[26][1] = 388.0;
        hiddenPackagesData[26][4] = 0.3;

        hiddenPackagesData[27][0] = 140.0;//в здании мерседес
        hiddenPackagesData[27][1] = 336.0;
        hiddenPackagesData[27][4] = 0.3;

        hiddenPackagesData[28][0] = 165.0;//в ювелирном магазине
        hiddenPackagesData[28][1] = 355.0;
        hiddenPackagesData[28][4] = 0.3;

        hiddenPackagesData[29][0] = 161.0;//на крыше здания
        hiddenPackagesData[29][1] = 339.0;
        hiddenPackagesData[29][4] = 0.3;

        hiddenPackagesData[30][0] = 174.0;//в саду
        hiddenPackagesData[30][1] = 298.0;
        hiddenPackagesData[30][4] = 0.3;

        hiddenPackagesData[31][0] = 126.0;//
        hiddenPackagesData[31][1] = 233.0;
        hiddenPackagesData[31][4] = 0.3;

        hiddenPackagesData[32][0] = 156.0;//на трамплине у бассейна
        hiddenPackagesData[32][1] = 226.0;
        hiddenPackagesData[32][4] = 0.3;

        hiddenPackagesData[33][0] = 212.0;//за зданием с бронежилетом
        hiddenPackagesData[33][1] = 116.0;
        hiddenPackagesData[33][4] = 0.3;

        hiddenPackagesData[34][0] = 291.0;//за рекламным баннеров на гонке
        hiddenPackagesData[34][1] = 190.0;
        hiddenPackagesData[34][4] = 0.3;

        hiddenPackagesData[35][0] = 176.0;//у северной стены торгового центра
        hiddenPackagesData[35][1] = 100.0;
        hiddenPackagesData[35][4] = 0.3;

        hiddenPackagesData[36][0] = 187.0;//у восточного края торгового центра
        hiddenPackagesData[36][1] = 126.0;
        hiddenPackagesData[36][4] = 0.3;

        hiddenPackagesData[37][0] = 174.0;//магазин одежды
        hiddenPackagesData[37][1] = 153.0;
        hiddenPackagesData[37][4] = 0.3;

        hiddenPackagesData[38][0] = 144.0;//у моста
        hiddenPackagesData[38][1] = 177.0;
        hiddenPackagesData[38][4] = 0.3;

        hiddenPackagesData[39][0] = 151.0;//на порковке
        hiddenPackagesData[39][1] = 109.0;
        hiddenPackagesData[39][4] = 0.3;

        hiddenPackagesData[40][0] = 90.0;//гольфклуб
        hiddenPackagesData[40][1] = 260.0;
        hiddenPackagesData[40][4] = 0.3;

        hiddenPackagesData[41][0] = 116.0;//под мостом у гольф клуба
        hiddenPackagesData[41][1] = 346.0;
        hiddenPackagesData[41][4] = 0.3;

        hiddenPackagesData[42][0] = 62.0;//в яме
        hiddenPackagesData[42][1] = 340.0;
        hiddenPackagesData[42][4] = 0.3;

        hiddenPackagesData[43][0] = 82.0;//на маленьком островке
        hiddenPackagesData[43][1] = 412.0;
        hiddenPackagesData[43][4] = 0.3;

        hiddenPackagesData[44][0] = 81.0;//на мостуовке
        hiddenPackagesData[44][1] = 446.0;
        hiddenPackagesData[44][4] = 0.3;

        hiddenPackagesData[45][0] = 92.0;//в тайной комнатке здания
        hiddenPackagesData[45][1] = 130.0;
        hiddenPackagesData[45][4] = 0.3;

        hiddenPackagesData[46][0] = 54.0;//позади здания
        hiddenPackagesData[46][1] = 126.0;
        hiddenPackagesData[46][4] = 0.3;

        hiddenPackagesData[47][0] = 86.0;//в закоулке
        hiddenPackagesData[47][1] = 176.0;
        hiddenPackagesData[47][4] = 0.3;

        hiddenPackagesData[48][0] = 70.0;//в ангаре
        hiddenPackagesData[48][1] = 159.0;
        hiddenPackagesData[48][4] = 0.3;

        hiddenPackagesData[49][0] = 59.0;//на крыше
        hiddenPackagesData[49][1] = 178.0;
        hiddenPackagesData[49][4] = 0.3;

        hiddenPackagesData[50][0] = 321.0;//на здании с вертолетной площадкой
        hiddenPackagesData[50][1] = 136.0;
        hiddenPackagesData[50][4] = 0.1;

        hiddenPackagesData[51][0] = 383.0;//на углу задния
        hiddenPackagesData[51][1] = 116.0;
        hiddenPackagesData[51][4] = 0.1;

        hiddenPackagesData[52][0] = 320.0;//в архитектуном сооружении
        hiddenPackagesData[52][1] = 93.0;
        hiddenPackagesData[52][4] = 0.1;

        hiddenPackagesData[53][0] = 230.0;//в закоулке рядом с hyman condo
        hiddenPackagesData[53][1] = 101.0;
        hiddenPackagesData[53][4] = 0.1;

        hiddenPackagesData[54][0] = 27.0;//за стадионом
        hiddenPackagesData[54][1] = 30.0;
        hiddenPackagesData[54][4] = 0.1;

        hiddenPackagesData[55][0] = 219.0;//в парковме госпиталя
        hiddenPackagesData[55][1] = 138.0;
        hiddenPackagesData[55][4] = 0.1;

        hiddenPackagesData[56][0] = 290.0;//в оффисе
        hiddenPackagesData[56][1] = 236.0;
        hiddenPackagesData[56][4] = 0.1;

        hiddenPackagesData[57][0] = 143.0;//в сарае Фила
        hiddenPackagesData[57][1] = 357.0;
        hiddenPackagesData[57][4] = 0.1;

        hiddenPackagesData[58][0] = 120.0;//на краю
        hiddenPackagesData[58][1] = 340.0;
        hiddenPackagesData[58][4] = 0.1;

        hiddenPackagesData[59][0] = 196.0;//в углу парковки
        hiddenPackagesData[59][1] = 338.0;
        hiddenPackagesData[59][4] = 0.1;

        hiddenPackagesData[60][0] = 170.0;//у черного входа в взадние, переулок
        hiddenPackagesData[60][1] = 383.0;
        hiddenPackagesData[60][4] = 0.1;

        hiddenPackagesData[61][0] = 172.0;//на лестнице поздаи такси
        hiddenPackagesData[61][1] = 402.0;
        hiddenPackagesData[61][4] = 0.1;

        hiddenPackagesData[62][0] = 205.0;//в отсеке для бассейна на крыше здания
        hiddenPackagesData[62][1] = 390.0;
        hiddenPackagesData[62][4] = 0.1;

        hiddenPackagesData[63][0] = 155.0;//в переулке у могилы
        hiddenPackagesData[63][1] = 450.0;
        hiddenPackagesData[63][4] = 0.1;

        hiddenPackagesData[64][0] = 202.0;//в центре вытяжок на крыше здания севернее печатной фабрики
        hiddenPackagesData[64][1] = 44.0;
        hiddenPackagesData[64][4] = 0.2;

        hiddenPackagesData[65][0] = 222.0;//под билборадом
        hiddenPackagesData[65][1] = 78.0;
        hiddenPackagesData[65][4] = 0.2;

        hiddenPackagesData[66][0] = 184.0;//на лестнице у входа в здание
        hiddenPackagesData[66][1] = 101.0;
        hiddenPackagesData[66][4] = 0.2;

        hiddenPackagesData[67][0] = 172.0;//в магазине
        hiddenPackagesData[67][1] = 94.0;
        hiddenPackagesData[67][4] = 0.2;

        hiddenPackagesData[68][0] = 180.0;//п проушле
        hiddenPackagesData[68][1] = 160.0;
        hiddenPackagesData[68][4] = 0.2;

        hiddenPackagesData[69][0] = 200.0;//на постере
        hiddenPackagesData[69][1] = 170.0;
        hiddenPackagesData[69][4] = 0.2;

        hiddenPackagesData[70][0] = 262.0;//в магазине на углу
        hiddenPackagesData[70][1] = 174.0;
        hiddenPackagesData[70][4] = 0.2;

        hiddenPackagesData[71][0] = 350.0;//за бассейном на западе особняка
        hiddenPackagesData[71][1] = 176.0;
        hiddenPackagesData[71][4] = 0.2;

        hiddenPackagesData[72][0] = 418.0;//на пристани особняка
        hiddenPackagesData[72][1] = 168.0;
        hiddenPackagesData[72][4] = 0.2;

        hiddenPackagesData[73][0] = 404.0;//в маленьком бассейне
        hiddenPackagesData[73][1] = 120.0;
        hiddenPackagesData[73][4] = 0.2;

        hiddenPackagesData[74][0] = 416.0;//у входа в здание
        hiddenPackagesData[74][1] = 100.0;
        hiddenPackagesData[74][4] = 0.2;

        hiddenPackagesData[75][0] = 332.0;//маленькое кафе
        hiddenPackagesData[75][1] = 118.0;
        hiddenPackagesData[75][4] = 0.2;

        hiddenPackagesData[76][0] = 175.0;//на крыше здания, рядом с rmpage
        hiddenPackagesData[76][1] = 199.0;
        hiddenPackagesData[76][4] = 0.2;

        hiddenPackagesData[77][0] = 218.0;//в автосалоне
        hiddenPackagesData[77][1] = 238.0;
        hiddenPackagesData[77][4] = 0.2;

        hiddenPackagesData[78][0] = 180.0;//на стоянке, между контейнерами
        hiddenPackagesData[78][1] = 280.0;
        hiddenPackagesData[78][4] = 0.2;

        hiddenPackagesData[79][0] = 206.0;//посреди цистерн
        hiddenPackagesData[79][1] = 260.0;
        hiddenPackagesData[79][4] = 0.2;

        hiddenPackagesData[80][0] = 276.0;//у массива жилих домов
        hiddenPackagesData[80][1] = 321.0;
        hiddenPackagesData[80][4] = 0.2;

        hiddenPackagesData[81][0] = 321.0;//на грузовом судне
        hiddenPackagesData[81][1] = 360.0;
        hiddenPackagesData[81][4] = 0.2;

        hiddenPackagesData[82][0] = 266.0;//на углу
        hiddenPackagesData[82][1] = 386.0;
        hiddenPackagesData[82][4] = 0.2;

        hiddenPackagesData[83][0] = 218.0;//на корме корабля
        hiddenPackagesData[83][1] = 400.0;
        hiddenPackagesData[83][4] = 0.2;

        hiddenPackagesData[84][0] = 202.0;//у входа в здание
        hiddenPackagesData[84][1] = 329.0;
        hiddenPackagesData[84][4] = 0.2;

        hiddenPackagesData[85][0] = 179.0;//в сарае
        hiddenPackagesData[85][1] = 362.0;
        hiddenPackagesData[85][4] = 0.2;

        hiddenPackagesData[86][0] = 154.0;//на крыше здания
        hiddenPackagesData[86][1] = 360.0;
        hiddenPackagesData[86][4] = 0.2;

        hiddenPackagesData[87][0] = 131.0;//на вертолетной плащадке
        hiddenPackagesData[87][1] = 332.0;
        hiddenPackagesData[87][4] = 0.2;

        hiddenPackagesData[88][0] = 156.0;//на ангаре
        hiddenPackagesData[88][1] = 307.0;
        hiddenPackagesData[88][4] = 0.2;

        hiddenPackagesData[89][0] = 136.0;//крыше посадочного строение
        hiddenPackagesData[89][1] = 280.0;
        hiddenPackagesData[89][4] = 0.2;

        hiddenPackagesData[90][0] = 93.0;//под крылом самолета
        hiddenPackagesData[90][1] = 282.0;
        hiddenPackagesData[90][4] = 0.2;

        hiddenPackagesData[91][0] = 88.0;//на крыше этого же самолета
        hiddenPackagesData[91][1] = 276.0;
        hiddenPackagesData[91][4] = 0.2;

        hiddenPackagesData[92][0] = 10.0;//позади зданя рядом с ангарами
        hiddenPackagesData[92][1] = 242.0;
        hiddenPackagesData[92][4] = 0.2;

        hiddenPackagesData[93][0] = 27.0;//под крылом самолета в ангаре
        hiddenPackagesData[93][1] = 289.0;
        hiddenPackagesData[93][4] = 0.2;

        hiddenPackagesData[94][0] = 130.0;//в констуркции для посадки пассажиров
        hiddenPackagesData[94][1] = 248.0;
        hiddenPackagesData[94][4] = 0.2;

        hiddenPackagesData[95][0] = 90.0;//угол аэропорта
        hiddenPackagesData[95][1] = 220.0;
        hiddenPackagesData[95][4] = 0.2;

        hiddenPackagesData[96][0] = 102.0;//на крыше аэропотра аэропорта
        hiddenPackagesData[96][1] = 236.0;
        hiddenPackagesData[96][4] = 0.2;

        hiddenPackagesData[97][0] = 36.0;//под самолетом в конце взлетной полосы
        hiddenPackagesData[97][1] = 114.0;
        hiddenPackagesData[97][4] = 0.2;

        hiddenPackagesData[98][0] = 142.0;//за рекламными постерами
        hiddenPackagesData[98][1] = 150.0;
        hiddenPackagesData[98][4] = 0.2;

        hiddenPackagesData[99][0] = 36.0;//военной базы
        hiddenPackagesData[99][1] = 88.0;
        hiddenPackagesData[99][4] = 0.2;

        for (int k = 0; k < hiddenPackagesData.length; k++)
        {
            if (isSouthEastMap && hiddenPackagesData[k][4] == 0.4)
            {
                hiddenPackagesData[k][2] = hiddenPackagesData[k][0] / (double) Map.getScaled_image_width();
                hiddenPackagesData[k][3] = hiddenPackagesData[k][1] / (double) Map.getScaled_image_height();
            }
            if (isNorthEastMap && hiddenPackagesData[k][4] == 0.3)
            {
                hiddenPackagesData[k][2] = hiddenPackagesData[k][0] / (double) Map.getScaled_image_width();
                hiddenPackagesData[k][3] = hiddenPackagesData[k][1] / (double) Map.getScaled_image_height();
            }
            if (isSouthWestMap && hiddenPackagesData[k][4] == 0.2)
            {
                hiddenPackagesData[k][2] = hiddenPackagesData[k][0] / (double) Map.getScaled_image_width();
                hiddenPackagesData[k][3] = hiddenPackagesData[k][1] / (double) Map.getScaled_image_height();
            }
            if (isNorthWestMap && hiddenPackagesData[k][4] == 0.1)
            {
                hiddenPackagesData[k][2] = hiddenPackagesData[k][0] / (double) Map.getScaled_image_width();
                hiddenPackagesData[k][3] = hiddenPackagesData[k][1] / (double) Map.getScaled_image_height();
            }
        }
    }

    private void initSecretsData()
    {
        secretsData = new double[16][5];
        secretsData[0][0] = 376.0;//затонувший корабля
        secretsData[0][1] = 155.0;
        secretsData[0][4] = 0.1;

        secretsData[1][0] = 352.0;//утопленник
        secretsData[1][1] = 292.0;
        secretsData[1][4] = 0.1;

        secretsData[2][0] = 298.0;//автомобили под мостом
        secretsData[2][1] = 15.0;
        secretsData[2][4] = 0.2;

        secretsData[3][0] = 312.0;//пасхальное яйцо в комнате
        secretsData[3][1] = 135.0;
        secretsData[3][4] = 0.1;

        secretsData[4][0] = 152.0;//человеческие органы
        secretsData[4][1] = 450.0;
        secretsData[4][4] = 0.1;

        secretsData[5][0] = 224.0;//билборд
        secretsData[5][1] = 80.0;
        secretsData[5][4] = 0.2;

        secretsData[6][0] = 178.0;//биборд welcome to hell
        secretsData[6][1] = 32.0;
        secretsData[6][4] = 0.2;

        secretsData[7][0] = 344.0;//бассейн RockStart
        secretsData[7][1] = 120.0;
        secretsData[7][4] = 0.2;

        secretsData[8][0] = 356.0;//бассейн в форме женского тела
        secretsData[8][1] = 84.0;
        secretsData[8][4] = 0.2;

        secretsData[9][0] = 429.0;//мяч в бассейне
        secretsData[9][1] = 112.0;
        secretsData[9][4] = 0.2;

        secretsData[10][0] = 95.0;//утопленник
        secretsData[10][1] = 171.0;
        secretsData[10][4] = 0.4;

        secretsData[11][0] = 179.0;//подводная лодка
        secretsData[11][1] = 31.0;
        secretsData[11][4] = 0.3;

        secretsData[12][0] = 225.0;//разрушенное транспотное судно
        secretsData[12][1] = 32.0;
        secretsData[12][4] = 0.3;

        secretsData[13][0] = 216.0;//Свет на окнах (Член)
        secretsData[13][1] = 410.0;
        secretsData[13][4] = 0.3;

        secretsData[14][0] = 103.0;//апартаменты 1C
        secretsData[14][1] = 328.0;
        secretsData[14][4] = 0.4;

        secretsData[15][0] = 2.0;//еще одно судно
        secretsData[15][1] = 439.0;
        secretsData[15][4] = 0.4;

        for (int k = 0; k < secretsData.length; k++)
        {
            if (isSouthEastMap && secretsData[k][4] == 0.4)
            {
                secretsData[k][2] = secretsData[k][0] / (double) Map.getScaled_image_width();
                secretsData[k][3] = secretsData[k][1] / (double) Map.getScaled_image_height();
            }
            if (isNorthEastMap && secretsData[k][4] == 0.3)
            {
                secretsData[k][2] = secretsData[k][0] / (double) Map.getScaled_image_width();
                secretsData[k][3] = secretsData[k][1] / (double) Map.getScaled_image_height();
            }
            if (isSouthWestMap && secretsData[k][4] == 0.2)
            {
                secretsData[k][2] = secretsData[k][0] / (double) Map.getScaled_image_width();
                secretsData[k][3] = secretsData[k][1] / (double) Map.getScaled_image_height();
            }
            if (isNorthWestMap && secretsData[k][4] == 0.1)
            {
                secretsData[k][2] = secretsData[k][0] / (double) Map.getScaled_image_width();
                secretsData[k][3] = secretsData[k][1] / (double) Map.getScaled_image_height();
            }
        }
    }

    public boolean isMedkitActive()
    {
        return Medkit_jCheckBox.isSelected();
    }

    public boolean isSouthEastMap()
    {
        return isSouthEastMap;
    }

    public boolean isNorthEastMap()
    {
        return isNorthEastMap;
    }

    public boolean isNorthWestMap()
    {
        return isNorthWestMap;
    }

    public boolean isSouthWestMap()
    {
        return isSouthWestMap;
    }

    public boolean isArmorActive()
    {
        return Armor_JCheckBox.isSelected();
    }

    public boolean isSpraiesActive()
    {
        return Spraies_JCheckBox.isSelected();
    }

    public boolean isBribesActive()
    {
        return Bribes_JCheckBox.isSelected();
    }

    public boolean isDrugsActive()
    {
        return Drugs_JCheckBox.isSelected();
    }

    public boolean isPropertiesActive()
    {
        return Properties_JCheckBox.isSelected();
    }

    public boolean isRampagesActive()
    {
        return Rampages_JCheckBox.isSelected();
    }

    public boolean isClothesActive()
    {
        return Clothes_JCheckBox.isSelected();
    }

    public boolean isWeaponsActive()
    {
        return Weapons_JCheckBox.isSelected();
    }

    public boolean isStoreActive()
    {
        return Stores_JCheckBox.isSelected();
    }

    public static main_gui getFrameObject()
    {
        return thisObject;
    }

    public int getScreenshotViewerSizePercent()
    {
        return screenshotViewerSizePercent;
    }

    public Preferences getPref()
    {
        return pref;
    }

    public ButtonExtended[] getVehicleButtons()
    {
        return vehicleButton;
    }

    public JButton[] getArmorButtons()
    {
        return armorButton;
    }

    public JButton[] getBribeButtons()
    {
        return bribeButton;
    }

    public JButton[] getClothesButtons()
    {
        return clothesButton;
    }

    public JButton[] getDrugButton()
    {
        return drugButton;
    }

    public JButton[] getHiddenPackagesButtons()
    {
        return hiddenPackagesButton;
    }

    public JButton[] getMedkitButtons()
    {
        return medkitButton;
    }

    public ButtonExtended[] getRampegeButtons()
    {
        return rampageButton;
    }

    public ButtonExtended[] getUniqueJumpButtons()
    {
        return uniqueJumpButton;
    }

    public JButton[] getStoreButtons()
    {
        return storeButton;
    }

    public ButtonExtended[] getPropertyButtons()
    {
        return propertiesButton;
    }

    public ButtonExtended[] getWeaponButtons()
    {
        return weaponButton;
    }

    public ButtonExtended[] getSecretsButtons()
    {
        return secretsButton;
    }

    public JButton[] getSprayButtons()
    {
        return sprayButton;
    }

    public JCheckBox getVehicle_CheckBox()
    {
        return Vehicles_JCheckBox;
    }

    public JCheckBox getArmor_JCheckBox()
    {
        return Armor_JCheckBox;
    }

    public JCheckBox getBribes_JCheckBox()
    {
        return Bribes_JCheckBox;
    }

    public JCheckBox getHiddenPackages_JCheckBox()
    {
        return HiddenPackages_JCheckBox;
    }

    public JCheckBox getClothes_JCheckBox()
    {
        return Clothes_JCheckBox;
    }

    public JCheckBox getWeapons_JCheckBox()
    {
        return Weapons_JCheckBox;
    }

    public JCheckBox getProperties_JCheckBox()
    {
        return Properties_JCheckBox;
    }

    public JCheckBox getStores_JCheckBox()
    {
        return Stores_JCheckBox;
    }

    public JCheckBox getMedkit_jCheckBox()
    {
        return Medkit_jCheckBox;
    }

    public JCheckBox getDrugs_JCheckBox()
    {
        return Drugs_JCheckBox;
    }

    public JCheckBox getRampages_JCheckBox()
    {
        return Rampages_JCheckBox;
    }

    public JCheckBox getUniqueJumps_JCheckBox()
    {
        return UniqueJumps_JCheckBox;
    }

    public JCheckBox getSpraies_JCheckBox()
    {
        return Spraies_JCheckBox;
    }


    public JCheckBox getSecrets_JCheckBox()
    {
        return Secrets_JCheckBox;
    }

    public JCheckBoxMenuItem getShowVehicles_JMenuItem()
    {
        return showVehicles_JMenuItem;
    }

    public JCheckBoxMenuItem getShowHiddenPackages_JMenuItem()
    {
        return showHiddenPackages_JMenuItem;
    }

    public JCheckBoxMenuItem getShowClothes_JMenuItem()
    {
        return showClothes_JMenuItem;
    }

    public JCheckBoxMenuItem getShowWeapons_JMenuItem()
    {
        return showWeapons_JMenuItem;
    }

    public JCheckBoxMenuItem getShowProperties_JMenuItem()
    {
        return showProperties_JMenuItem;
    }

    public JCheckBoxMenuItem getShowStores_JMenuItem()
    {
        return showStores_JMenuItem;
    }

    public JCheckBoxMenuItem getShowMedkit_JMenuItem()
    {
        return showMedkit_JMenuItem;
    }

    public JCheckBoxMenuItem getShowDrugs_JMenuItem()
    {
        return showDrugs_JMenuItem;
    }

    public JCheckBoxMenuItem getShowArmor_JMenuItem()
    {
        return showArmor_JMenuItem;
    }

    public JCheckBoxMenuItem getShowBribes_JMenuItem()
    {
        return showBribes_JMenuItem;
    }

    public JCheckBoxMenuItem getShowRampages_JMenuItem()
    {
        return showRampages_JMenuItem;
    }

    public JCheckBoxMenuItem getShowUniqueJumps_JMenuItem()
    {
        return showUniqueJumps_JMenuItem;
    }

    public JCheckBoxMenuItem getShowSecrets_JMenuItem()
    {
        return showSecrets_JMenuItem;
    }

    public JCheckBoxMenuItem getShowSpraies_JMenuItem()
    {
        return showSpraies_JMenuItem;
    }

    private double[][] getNewArrayWithCorrespondingData(double[][] input, final double value)
    {
        int countElement = 0;
        for (int k = 0; k < input.length; k++)
        {
            if (input[k][4] == value)
            {
                countElement++;
            }
        }
        double[][] currentPartData = new double[countElement][5];
        for (int k = 0, m = 0; k < input.length; k++)
        {
            if (input[k][4] == value)
            {
                currentPartData[m][0] = input[k][0];
                currentPartData[m][1] = input[k][1];
                currentPartData[m][2] = input[k][2];
                currentPartData[m][3] = input[k][3];
                currentPartData[m][4] = input[k][4];
                m++;
            }
        }
        return currentPartData;
    }

}
