package com.sabel.beispieljtable;

import javax.swing.*;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.UUID;

/**
 * Created by Steve on 08.05.2017.
 */
public class Fenster extends JFrame {
    private Container c;
    private JTable jTable;
    private JPanel jpSouth;
    private JScrollPane jScrollPane;
    private MeinTableModel model;
    private JButton jbtnNeu, jbtnLoeschen;


    public Fenster() {
        super("Beispiel JTable");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.initComponents();
        this.pack();
        this.initEvents();
        this.setVisible(true);
    }

    private void initEvents() {
        this.jbtnNeu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                neu();
            }
        });

        this.jbtnLoeschen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loeschen();
            }
        });
    }

    private void loeschen() {
        int selectedRow = jTable.getSelectedRow();
        if (selectedRow < 0) {
            return;
        }
        int result = JOptionPane.showConfirmDialog(this, "Wollen Sie die Person wirklich löschen?", "Sicher?", JOptionPane.WARNING_MESSAGE);
        if (result == JOptionPane.YES_OPTION) {
            int modelRow = jTable.convertRowIndexToModel(jTable.getSelectedRow());
            model.loeschePerson((String) model.getValueAt(modelRow, 2));
            model.fireTableDataChanged();
        }
    }

    private void neu() {
        model.neuePerson();
        model.fireTableDataChanged();
    }

    private void initComponents() {
        c = this.getContentPane();
        model = new MeinTableModel(Datenbank.erzeugeTestdatenbank());
        jTable = new JTable(model);
        jTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jTable.setRowSorter(new TableRowSorter<>(model));
        jScrollPane = new JScrollPane(jTable);
        c.add(jScrollPane, BorderLayout.CENTER);

        jpSouth = new JPanel();
        jbtnNeu = new JButton("Neu");
        jbtnLoeschen = new JButton("Löschen");
        jpSouth.add(jbtnNeu);
        jpSouth.add(jbtnLoeschen);
        c.add(jpSouth, BorderLayout.SOUTH);
    }

    public static void main(String[] args) {
        new Fenster();
    }
}
