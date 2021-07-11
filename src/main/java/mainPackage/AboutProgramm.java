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
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * @author Y@L
 */
public class AboutProgramm extends javax.swing.JFrame
{
    Font montanaFont;
    Font descriptionFont;

    public AboutProgramm()
    {
        initComponents();
    }

    private void initComponents()
    {

        aboutProgram_jTabbedPane = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        authorCopyright_jTextField = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        description_jTextArea = new javax.swing.JTextArea();
        title_jLabel = new javax.swing.JLabel();
        support_jLabel = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextPane1 = new javax.swing.JTextPane();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        license_jTextPane = new javax.swing.JTextPane();

        Dimension minimumAndPreferredSize = new Dimension(640, 320);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setSize(640, 320);
        setPreferredSize(minimumAndPreferredSize);
        setMinimumSize(minimumAndPreferredSize);

        authorCopyright_jTextField.setEditable(false);
        authorCopyright_jTextField.setFont(new java.awt.Font("Ubuntu", 1, 13)); // NOI18N
        authorCopyright_jTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        authorCopyright_jTextField.setText("© 2021 Ярослав Литвинов (aka. Y@Ldysse)");
        authorCopyright_jTextField.setBorder(null);

        description_jTextArea.setEditable(false);
        description_jTextArea.setColumns(20);
        description_jTextArea.setLineWrap(true);
        description_jTextArea.setRows(4);
        description_jTextArea.setTabSize(33);
        description_jTextArea.setText("Данная программа поможет вам пройти Grand Theft Auto: Vice City на 100%. А именно: отыскать 100 скрытых пакетов, утолить 35 вспышек ярости, выполнить 35 уникальных прыжков, ограбить 15 магазинов, угнать кучу автомобилей, приобрести уйму недвижимости и овладеть тонной огнестрельного оружия.");
        description_jTextArea.setBackground(new Color(238, 238, 238));
        description_jTextArea.setBorder(null);
        jScrollPane2.setViewportView(description_jTextArea);

        //
        try
        {
            URL montanaFont_url = this.getClass().getClassLoader().getResource("Fonts/[Roman]MontanaTypeface.ttf");

            InputStream montanaFont_is = montanaFont_url.openStream();
            montanaFont = Font.createFont(Font.TRUETYPE_FONT, montanaFont_is);
            montanaFont = montanaFont.deriveFont(Font.PLAIN, 26.0f);

            URL descriptionFont_url = this.getClass().getClassLoader().getResource("Fonts/20648.ttf");
            InputStream descriptionFont_is = descriptionFont_url.openStream();
            descriptionFont = Font.createFont(Font.PLAIN, descriptionFont_is);

        }
        catch (IOException ioExc)
        {

        }
        catch (FontFormatException fontFormatExc)
        {
            DebugTools.printDebugMessage("Ошибка формата шрифта в названии программы.");
            DebugTools.createLogFile(fontFormatExc);
        }
        //
        description_jTextArea.setFont(descriptionFont.deriveFont(Font.ITALIC, 14.f));

        title_jLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        title_jLabel.setText("Vice City Helper");
        title_jLabel.setFont(montanaFont);
        title_jLabel.setForeground(new Color(251, 80, 226));
        title_jLabel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        support_jLabel.setText("Нашли ошибку ? Есть предложение ? Пишите сюда!");

        jTextField1.setEditable(false);
        jTextField1.setText("Yaroslav_A_Litvinov@yahoo.com");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(authorCopyright_jTextField)
                                        .addComponent(jScrollPane2)
                                        .addComponent(title_jLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(support_jLabel)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(0, 0, Short.MAX_VALUE)))
                                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGap(24, 24, 24)
                                .addComponent(title_jLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 139, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(support_jLabel)
                                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(27, 27, 27)
                                .addComponent(authorCopyright_jTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
        );

        aboutProgram_jTabbedPane.addTab("О программе", jPanel1);

        jTextPane1.setEditable(false);
        jTextPane1.setText("Выражаю благодарность:\n\t* Веб-сайту [ http://en.wikigta.org/ ] за предоставленные материалы\n\t* Веб-сайту [ http://selmiak.bplaced.net/ ] за предоставленные материалы\n\t* Веб-сайту [ https://gta.com.ua/ ] за предоставленные материалы\n\t* Веб-сайту [ https://www.flaticon.com/ ] за иконки\n\t* Веб-сайту [ https://gta.ag.ru/ ] за информацию об видеоигре" +
                "\n\t* @joyo2 за карту Вайс-сити\n\t* @Huggito Baggio за подробную карту Вайс-сити\n\t* Веб-сайту [ https://rockstargame.su/ ] за информацию об игре\n\t* Веб-сайту [ https://games.mail.ru/ ] за материалы об игре\n\t * Веб-сайту [ https://gamesisart.ru/ ] за статью о прохождении миссий"
                + "\n\t* Артёму Литвинову за помощь в организации базы данных"
                + "\n\nВнимание: Наработки, которые использовались в проекте пренадлежат авторам этих наработок (см. выше)!");
        jTextPane1.setFont(descriptionFont.deriveFont(Font.PLAIN, 15.0f));

        //Стиль для раздела благодарность
        SimpleAttributeSet dxd = new SimpleAttributeSet();
        StyleConstants.setFontSize(dxd, jTextPane1.getFont().getSize() + 4);
        StyleConstants.setForeground(dxd, new Color(239, 73, 73));
        StyleConstants.setBold(dxd, true);
        int startIndex = jTextPane1.getText().indexOf("Внимание:");
        int length = jTextPane1.getText().length() - startIndex;
        jTextPane1.getStyledDocument().setCharacterAttributes(startIndex, length, dxd, true);
        //===============================

        license_jTextPane.setText("Данное программное обеспечение распостраняется по свободной лицензии GNU GPL v3. Изменение и/или распостранение исходного кода согласно условиям лицензии.");
        license_jTextPane.setFont(descriptionFont.deriveFont(Font.ITALIC, 16.0f));
        license_jTextPane.setForeground(new Color(177, 122, 34, 255));
        license_jTextPane.setBackground(new Color(238, 238, 238));

        jTextPane1.setBackground(new Color(238, 238, 238));
        jScrollPane1.setViewportView(jTextPane1);

        aboutProgram_jTabbedPane.addTab("Благодарности", jScrollPane1);

        license_jTextPane.setEditable(false);
        jScrollPane3.setViewportView(license_jTextPane);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 544, Short.MAX_VALUE)
                                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 316, Short.MAX_VALUE)
                                .addContainerGap())
        );

        aboutProgram_jTabbedPane.addTab("Лицензия", jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(aboutProgram_jTabbedPane)
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(aboutProgram_jTabbedPane)
                                .addContainerGap())
        );

        pack();
    }


    private javax.swing.JTabbedPane aboutProgram_jTabbedPane;
    private javax.swing.JTextField authorCopyright_jTextField;
    private javax.swing.JTextArea description_jTextArea;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextPane jTextPane1;
    private javax.swing.JTextPane license_jTextPane;
    private javax.swing.JLabel support_jLabel;
    private javax.swing.JLabel title_jLabel;
}
