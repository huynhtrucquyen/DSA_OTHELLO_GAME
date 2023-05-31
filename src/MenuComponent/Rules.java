package MenuComponent;

import GUI.Parameter;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class Rules extends JFrame {

    public Rules() {
        this.setTitle("Othello");
        initComponents();
        //setSize(1000, 850);
    }


    private void initComponents() {

        jPanel1 = new drawRules(this.getWidth(), this.getHeight());
        //jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        titleJlabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setAutoRequestFocus(false);

        jPanel1.setBackground(new java.awt.Color(233, 195, 187));

        titleJlabel.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        titleJlabel.setForeground(new java.awt.Color(55, 26, 22));
        titleJlabel.setText("OTHELLO RULES");

        jButton1.setBackground(new java.awt.Color(233, 141, 158));
        jButton1.setForeground(new java.awt.Color(193, 51, 70));
        jButton1.setText("OK");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });


        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(218, 218, 218)
                                .addComponent(titleJlabel)
                                .addGap(218, 218, 218))
                .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addContainerGap()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel1Layout.createSequentialGroup()
                                                        .addGap(331, 331, 331)
                                                        .addComponent(jButton1)))))
                )
        );

        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(687, 687, 687)
                        .addComponent(titleJlabel)
                        .addGap(0, 0, 0)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGap(680, 680, 680)
                                .addComponent(jButton1)

        ));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
        dispose();
    }
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {
        dispose();
    }


    public static void main(String args[]) {
        new Rules().setVisible(true);
    }


    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel titleJlabel;

}


class drawRules extends JPanel {
    int width, height;
    private BufferedImage image;

    public drawRules(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public void paintComponent(Graphics g) {
        try {
            image = ImageIO.read(new File(Parameter.rule));
        } catch (IOException ex) {
            System.out.println("error");
        }
        g.setColor(new java.awt.Color(233, 195, 187));
        g.fillRect(1, 1, 1500, 1500);
        System.out.println(width);
        g.drawImage(image, 52, 55, 609, 590, null); // see javadoc for more info on the parameters

    }
}