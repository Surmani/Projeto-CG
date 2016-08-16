/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalhocg;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Arrays;
import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 *
 * @author lucas
 */
public class viewTrabalho extends javax.swing.JFrame {

    escolhaDePoligono escolha;
    
    
    
    /**
     * Creates new form viewTrabalho
     */
    public viewTrabalho() {
        initComponents();
        painel.setBackground(Color.white);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        painel = new javax.swing.JPanel();
        ImageIcon imgReta = new ImageIcon(getClass().getResource("/imagens/reta.png"));
        drawLine = new javax.swing.JButton("",imgReta);
        ImageIcon imgRetangulo = new ImageIcon(getClass().getResource("/imagens/retangulo.png"));
        drawRect = new javax.swing.JButton("",imgRetangulo);
        ImageIcon imgLimpar = new ImageIcon(getClass().getResource("/imagens/limpar.png"));
        limpar = new javax.swing.JButton("",imgLimpar);
        ImageIcon imgTriangulo = new ImageIcon(getClass().getResource("/imagens/triangulo.png"));
        drawTri = new javax.swing.JButton("",imgTriangulo);
        ImageIcon imgRotacao = new ImageIcon(getClass().getResource("/imagens/rotacao.png"));
        rotacao = new javax.swing.JButton("",imgRotacao);
        ImageIcon imgTranslacao = new ImageIcon(getClass().getResource("/imagens/translacao.png"));
        translacao = new javax.swing.JButton("",imgTranslacao);
        ImageIcon imgEscala = new ImageIcon(getClass().getResource("/imagens/escala.png"));
        escala = new javax.swing.JButton("",imgEscala);
        ImageIcon imgZoomExtend = new ImageIcon(getClass().getResource("/imagens/zoomextend.png"));
        zoomExtend = new javax.swing.JButton("",imgZoomExtend);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        painel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        painel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                painelMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout painelLayout = new javax.swing.GroupLayout(painel);
        painel.setLayout(painelLayout);
        painelLayout.setHorizontalGroup(
            painelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 532, Short.MAX_VALUE)
        );
        painelLayout.setVerticalGroup(
            painelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 340, Short.MAX_VALUE)
        );

        drawLine.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                drawLineActionPerformed(evt);
            }
        });

        drawRect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                drawRectActionPerformed(evt);
            }
        });

        limpar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                limparActionPerformed(evt);
            }
        });

        drawTri.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                drawTriActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(drawRect, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(drawTri, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(drawLine, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(limpar, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(rotacao, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(escala, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(translacao, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(zoomExtend, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(painel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(49, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(escala, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(limpar, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(rotacao, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(translacao, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(zoomExtend, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(drawLine, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(drawRect, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(drawTri, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(painel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(56, 56, 56))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void drawRectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_drawRectActionPerformed
        //escolha = new escolhaDePoligono(4,painel);
        //escolha.setVisible(true);
        
        Poligono p = new Poligono(
                new Ponto2D[] {
                    new Ponto2D(0,0),
                    new Ponto2D(0, 200),
                    new Ponto2D(200,0)
                }
        );
        
        
        
        Graphics g1 = painel.getGraphics();
        g1.setColor(Color.white);
        g1.fillRect(0,0, painel.getWidth(), painel.getHeight());
        
        
        g1.setColor(Color.black);

        p.scale(new Ponto2D(1,1), 0.5, 3);
        
        double[][] coords = p.transJanelaViewport(painel.getHeight()-1);
        Poligono.printMatrix(coords);
        int[] xs;
        xs = new int[coords[0].length];
        int[] ys;
        ys = new int[coords[0].length];
        for(int i = 0; i < coords[0].length; i++) {
            xs[i] = (int) Math.round(coords[0][i]);
            ys[i] = (int) Math.round(coords[1][i]);
        }
        
        
        
        
        g1.drawPolygon(xs, ys, coords[0].length);
        
    }//GEN-LAST:event_drawRectActionPerformed

    private void limparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_limparActionPerformed
        painel.repaint();
        painel.setBackground(Color.white);
    }//GEN-LAST:event_limparActionPerformed

    private void drawTriActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_drawTriActionPerformed
        escolha = new escolhaDePoligono(3,painel);
        escolha.setVisible(true);
    }//GEN-LAST:event_drawTriActionPerformed

    private void painelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_painelMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_painelMouseClicked

    private void drawLineActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_drawLineActionPerformed
        escolha = new escolhaDePoligono(2,painel);
        escolha.setVisible(true);
    }//GEN-LAST:event_drawLineActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(viewTrabalho.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(viewTrabalho.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(viewTrabalho.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(viewTrabalho.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new viewTrabalho().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton drawLine;
    private javax.swing.JButton drawRect;
    private javax.swing.JButton drawTri;
    private javax.swing.JButton escala;
    private javax.swing.JButton limpar;
    public javax.swing.JPanel painel;
    private javax.swing.JButton rotacao;
    private javax.swing.JButton translacao;
    private javax.swing.JButton zoomExtend;
    // End of variables declaration//GEN-END:variables
}
