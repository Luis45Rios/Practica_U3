/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ejercicio_2;

import java.util.Set;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author luisr
 */
public class frm_biblioteca extends javax.swing.JFrame {

    /**
     * Creates new form frm_biblioteca
     */
    public frm_biblioteca() {
        initComponents();
        iniciarLibros();
        cargarLibros();
    }

    int numLibros = 10;

    // Arreglos para alamacenar la informacion
    String[] id = new String[numLibros];
    String[] titulo = new String[numLibros];
    String[] autor = new String[numLibros];
    String[] isbn = new String[numLibros];
    int[] pags = new int[numLibros];
    int[] duplicado = new int[numLibros];
    String[] nroInventario = new String[numLibros];
    double[] precio = new double[numLibros];
    int[] totalDuplicados = new int[numLibros];

    String[] prestamos = new String[100];

    // Ingresamos los libros manualmente
    public void iniciarLibros() {
        id[0] = "B001";
        titulo[0] = "Harry Potter y la piedra filosofal";
        autor[0] = "J.K. Rowling";
        isbn[0] = "1111";
        pags[0] = 223;
        duplicado[0] = 3;
        nroInventario[0] = "001";
        precio[0] = 15.99;
        totalDuplicados[0] = 3;

        id[1] = "B002";
        titulo[1] = "El señor de los anillos";
        autor[1] = "J.R.R. Tolkien";
        isbn[1] = "2222";
        pags[1] = 1216;
        duplicado[1] = 2;
        nroInventario[1] = "002";
        precio[1] = 25.50;
        totalDuplicados[1] = 2;

        id[2] = "B003";
        titulo[2] = "Cien años de soledad";
        autor[2] = "Gabriel García Márquez";
        isbn[2] = "3333";
        pags[2] = 471;
        duplicado[2] = 4;
        nroInventario[2] = "003";
        precio[2] = 18.99;
        totalDuplicados[2] = 4;

        id[3] = "B004";
        titulo[3] = "1984";
        autor[3] = "George Orwell";
        isbn[3] = "4444";
        pags[3] = 328;
        duplicado[3] = 3;
        nroInventario[3] = "004";
        precio[3] = 12.99;
        totalDuplicados[3] = 3;

        id[4] = "B005";
        titulo[4] = "Don Quijote de la Mancha";
        autor[4] = "Miguel de Cervantes";
        isbn[4] = "5555";
        pags[4] = 863;
        duplicado[4] = 2;
        nroInventario[4] = "005";
        precio[4] = 22.50;
        totalDuplicados[4] = 2;

        id[5] = "B006";
        titulo[5] = "Orgullo y prejuicio";
        autor[5] = "Jane Austen";
        isbn[5] = "6666";
        pags[5] = 279;
        duplicado[5] = 5;
        nroInventario[5] = "006";
        precio[5] = 10.99;
        totalDuplicados[5] = 5;

        id[6] = "B007";
        titulo[6] = "Moby-Dick";
        autor[6] = "Herman Melville";
        isbn[6] = "7777";
        pags[6] = 635;
        duplicado[6] = 2;
        nroInventario[6] = "007";
        precio[6] = 17.99;
        totalDuplicados[6] = 2;

        id[7] = "B008";
        titulo[7] = "Crimen y castigo";
        autor[7] = "Fiódor Dostoievski";
        isbn[7] = "8888";
        pags[7] = 671;
        duplicado[7] = 3;
        nroInventario[7] = "008";
        precio[7] = 19.99;
        totalDuplicados[7] = 3;

        id[8] = "B009";
        titulo[8] = "El principito";
        autor[8] = "Antoine de Saint-Exupéry	";
        isbn[8] = "9999";
        pags[8] = 96;
        duplicado[8] = 6;
        nroInventario[8] = "009";
        precio[8] = 8.99;
        totalDuplicados[8] = 6;

        id[9] = "B010";
        titulo[9] = "Rayuela";
        autor[9] = "Julio Cortázar";
        isbn[9] = "101010";
        pags[9] = 736;
        duplicado[9] = 3;
        nroInventario[9] = "09";
        precio[9] = 20.00;
        totalDuplicados[9] = 3;
    }

    // Metodo para cargar los libros en la tabla
    public void cargarLibros() {
        DefaultTableModel modeloTabla = (DefaultTableModel) tbl_libros.getModel();
        modeloTabla.setRowCount(0);

        for (int i = 0; i < id.length; i++) {
            modeloTabla.addRow(new Object[]{id[i], titulo[i], autor[i], isbn[i], pags[i], duplicado[i], nroInventario[i], precio[i], totalDuplicados[i]});
        }

    }

    // Metodo para buscar el libro mediante ISBN o ID
    public void buscarLibro() {
        String criterio = JOptionPane.showInputDialog("Ingrese ID, título o ISBN del libro a buscar:");

        if (criterio == null || criterio.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, ingrese un criterio de búsqueda.");
            return;
        }

        DefaultTableModel modeloTabla = (DefaultTableModel) tbl_libros.getModel();
        modeloTabla.setRowCount(0); // Limpiar tabla antes de mostrar resultados

        boolean encontrado = false;

        for (int i = 0; i < numLibros; i++) {
            if (id[i] != null && (id[i].equalsIgnoreCase(criterio)
                    || titulo[i].toLowerCase().contains(criterio.toLowerCase())
                    || isbn[i].equals(criterio))) {
                modeloTabla.addRow(new Object[]{id[i], titulo[i], autor[i], isbn[i], pags[i], duplicado[i], nroInventario[i], precio[i], totalDuplicados[i]});
                encontrado = true;
            }
        }

        if (!encontrado) {
            JOptionPane.showMessageDialog(this, "No se encontraron libros con ese criterio.");
            cargarLibros(); // Volver a mostrar todos los libros si no hay coincidencias
        }
    }

    // Metodo para prestar Libro
    public void prestarLibro() {
        int filaSeleccionada = tbl_libros.getSelectedRow();

        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione un libro para prestar.");
            return;
        }

        int indiceLibro = filaSeleccionada;

        if (totalDuplicados[indiceLibro] <= 0) {
            JOptionPane.showMessageDialog(this, "No hay copias disponibles para préstamo.");
            return;
        }

        // Registrar el préstamo en el primer espacio vacío del arreglo
        for (int i = 0; i < prestamos.length; i++) {
            if (prestamos[i] == null) {
                prestamos[i] = id[indiceLibro] + " - " + titulo[indiceLibro];
                totalDuplicados[indiceLibro]--; // Reducir el número de copias disponibles
                JOptionPane.showMessageDialog(this, "Libro prestado con éxito.");
                cargarLibros(); // Actualizar la tabla
                return;
            }
        }

        JOptionPane.showMessageDialog(this, "No hay espacio para más préstamos.");
    }

    // MEtodo para devol;ver libro
    public void devolverLibro() {
        String idLibro = JOptionPane.showInputDialog(this, "Ingrese el ID o ISBN del libro a devolver:");

        if (idLibro == null || idLibro.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debe ingresar un ID o ISBN válido.");
            return;
        }

        // Busca el libro en el aregglo de prestamos préstamos
        for (int i = 0; i < prestamos.length; i++) {
            if (prestamos[i] != null && (prestamos[i].startsWith(idLibro) || prestamos[i].contains(" - " + idLibro))) {
                // Encontrado: eliminar del repositorio de préstamos
                prestamos[i] = null;

                // Incrementa el total de copias disponibles
                for (int j = 0; j < numLibros; j++) {
                    if (id[j].equals(idLibro) || isbn[j].equals(idLibro)) {
                        totalDuplicados[j]++;
                        JOptionPane.showMessageDialog(this, "Libro devuelto con éxito.");
                        cargarLibros(); // Actualizar la tabla
                        return;
                    }
                }
            }
        }

        JOptionPane.showMessageDialog(this, "Libro no encontrado en el repositorio de préstamos.");
    }

    // Metodo para mostrar el catalogo
    public void catalogo() {
        StringBuilder catalogo = new StringBuilder("📚 Catálogo de Libros 📚\n\n");

        for (int i = 0; i < numLibros; i++) {
            if (id[i] != null) {
                catalogo.append("📖 ID: ").append(id[i]).append("\n")
                        .append("📌 Título: ").append(titulo[i]).append("\n")
                        .append("✍ Autor: ").append(autor[i]).append("\n")
                        .append("📖 ISBN: ").append(isbn[i]).append("\n")
                        .append("📦 Copias disponibles: ").append(totalDuplicados[i]).append("\n")
                        .append("---------------------------\n");
            }
        }

        // Crear un JTextArea con el texto del catálogo
        JTextArea textArea = new JTextArea(15, 40); // Tamaño del área de texto
        textArea.setText(catalogo.toString());
        textArea.setEditable(false); // Para que el usuario no pueda editar el texto

        // Agregar el JTextArea a un JScrollPane
        JScrollPane scrollPane = new JScrollPane(textArea);

        // Mostrarlo en un JOptionPane
        JOptionPane.showMessageDialog(this, scrollPane, "Catálogo OPAC", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_libros = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        btn_catalogo = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("SISTEMA BIBLIOTECARIO");

        tbl_libros.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nombre", "Autor", "ISBN", "Paginas", "Duplicado", "Nro. Inventario", "Precio", "Total Duplicados"
            }
        ));
        jScrollPane1.setViewportView(tbl_libros);

        jButton1.setText("Prestamo");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Devolucion");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Buscar Libro");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        btn_catalogo.setText("Catalogo");
        btn_catalogo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_catalogoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 931, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(197, 197, 197)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(147, 147, 147))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(166, 166, 166)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_catalogo, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(164, 164, 164))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
                    .addComponent(btn_catalogo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(42, 42, 42))
        );

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
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        prestarLibro();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        buscarLibro();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        devolverLibro();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void btn_catalogoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_catalogoActionPerformed
        // TODO add your handling code here:
        catalogo();
    }//GEN-LAST:event_btn_catalogoActionPerformed

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
            java.util.logging.Logger.getLogger(frm_biblioteca.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frm_biblioteca.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frm_biblioteca.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frm_biblioteca.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frm_biblioteca().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_catalogo;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbl_libros;
    // End of variables declaration//GEN-END:variables
}
