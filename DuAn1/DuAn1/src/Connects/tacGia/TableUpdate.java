package Connects.tacGia;

import javax.swing.*;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TableUpdate extends JFrame {
    private JPanel JPL;
    private JTable tblTacGia;
    private JTextField txtma;
    private JTextField txtten;
    private JTextField txtdiachi;
    DefaultTableModel dtb;

    String low[] = {"mã", "tên", "địa chỉ"};

    public TableUpdate() {
        add(JPL);
        setSize(800, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);

        //  dtb = new DefaultTableModel(low,0);

//        tblTacGia.setModel(dtb);
//        for (int i = 0; i <Data1.getList().size() ; i++) {
//            System.out.println(Data1.getList());
//        }

        setTableMode();
        //
//menu table
        final JMenuItem item1 = new JMenuItem();
        item1.setText("Menu Item 1");
        final JMenuItem item2 = new JMenuItem();
        item2.setText("Menu Item 2");
        final JMenuItem item3 = new JMenuItem();
        item3.setText("Menu Item 3");


        final JPopupMenu popupMenu = new JPopupMenu();
        popupMenu.add(item1);
        popupMenu.addSeparator();
        popupMenu.add(item2);
        popupMenu.add(item3);
        tblTacGia.setComponentPopupMenu(popupMenu);


        popupMenu.addPopupMenuListener(new PopupMenuListener() {
            @Override
            public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        // force selection of row upon right-click (it works)
                        //buộc chọn hàng khi nhấp chuột phải (nó hoạt động)
                        int rowAtPoint = tblTacGia.rowAtPoint(SwingUtilities.convertPoint(popupMenu, new Point(0, 0), tblTacGia));
                        if (rowAtPoint > -1) {
                            tblTacGia.setRowSelectionInterval(rowAtPoint, rowAtPoint);
                        }
                    }
                });
            }

            @Override
            public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        // force row selection upon exiting popup menu
                        // does not work; rowAtPoint always returns -1
                        //buộc chọn hàng khi thoát menu bật lên
                        // không hoạt động; rowAtPoint luôn trả về -1
                        int rowAtPoint = tblTacGia.rowAtPoint(SwingUtilities.convertPoint(null, new Point(0, 0), tblTacGia));
                        //  int rowAtPoint = tblTacGia.rowAtPoint(SwingUtilities.convertPoint(popupMenu, new Point(0, 0), tblTacGia));
                        if (rowAtPoint > -1) {
                            tblTacGia.setRowSelectionInterval(rowAtPoint, rowAtPoint);
                        }
                    }
                });
            }

            @Override
            public void popupMenuCanceled(PopupMenuEvent e) {
                // TODO

            }
        });

        //
        item1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "chon item 1");
            }
        });
        item2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "chon item 2");
            }
        });
        item3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "chon item 3");
            }
        });

        //menu table
        String cot[] = {"anh", "chi", "em"};
        JComboBox combo = new JComboBox<String>(cot);
        combo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, combo.getSelectedItem());
            }
        });
        TableColumn column = tblTacGia.getColumnModel().getColumn(2);
        column.setCellEditor(new DefaultCellEditor(combo));

//

        tblTacGia.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int index = tblTacGia.getSelectedRow();
                if (index == -1) {
                    return;
                }
                txtma.setText(String.valueOf(tblTacGia.getValueAt(index, 0)));
                txtten.setText(String.valueOf(tblTacGia.getValueAt(index, 1)));
                txtdiachi.setText(String.valueOf(tblTacGia.getValueAt(index, 2)));
                int mPosX = MouseInfo.getPointerInfo().getLocation().x;
                int mPosY = MouseInfo.getPointerInfo().getLocation().y;
                popupMenu.show(null, mPosX, mPosY);

            }
        });
        tblTacGia.getModel().addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {

            }
        });
    }

    public static void main(String[] args) {
        new TableUpdate();
    }

    public void setTableMode() {
        UpdateTacGia model = new UpdateTacGia(DataTacGia.getListTG());
        tblTacGia.setModel(model);

    }
}
