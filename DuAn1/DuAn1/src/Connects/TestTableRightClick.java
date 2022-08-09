package Connects;


import java.awt.BorderLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;

public class TestTableRightClick extends JFrame {
    JPopupMenu popupMenu;

    protected void initUI() {
        final JFrame frame = new JFrame(TestTableRightClick.class.getSimpleName());
        Vector<String> columns = new Vector<String>(Arrays.asList("Name", "Age"));
        Vector<Vector<String>> data = new Vector<Vector<String>>();
        for (int i = 0; i < 50; i++) {
            Vector<String> row = new Vector<String>();
            for (int j = 0; j < columns.size(); j++) {
                row.add("Cell " + (i + 1) + "," + (j + 1));
            }
            data.add(row);
        }
        JTable table = new JTable(data, columns);
        popupMenu = new JPopupMenu();

        popupMenu.addPopupMenuListener(new PopupMenuListener() {

            @Override
            public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
                //int rowAtPoint = table.rowAtPoint(SwingUtilities.convertPoint(popupMenu, new Point(0, 0), table));
                //generateTablePopupMenu(rowAtPoint);

                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        int rowAtPoint = table.rowAtPoint(SwingUtilities.convertPoint(popupMenu, new Point(0, 0), table));
                        generateTablePopupMenu(rowAtPoint);
                        if (rowAtPoint > -1) {
                            table.setRowSelectionInterval(rowAtPoint, rowAtPoint);
                        }
                    }
                });
            }

            private void generateTablePopupMenu(int rowAtPoint) {
                System.out.println(rowAtPoint);
                popupMenu.removeAll();
                if ((rowAtPoint & 1) == 0) {
                    System.out.println("even");
                    JMenuItem item = new JMenuItem("Even Row");
                    popupMenu.add(item);
                } else {
                    System.out.println("odd");
                    //popupMenu = new JPopupMenu();
                    JMenuItem item = new JMenuItem("Odd Row");
                    popupMenu.add(item);
                }

            }

            @Override
            public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void popupMenuCanceled(PopupMenuEvent e) {
                // TODO Auto-generated method stub

            }


        });


        table.setComponentPopupMenu(popupMenu);
        frame.add(new JScrollPane(table), BorderLayout.CENTER);
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                new TestTableRightClick().initUI();
            }
        });
    }
}
