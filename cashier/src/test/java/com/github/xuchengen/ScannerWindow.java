package com.github.xuchengen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * 扫描枪窗口
 */
class ScannerWindow extends JFrame {

    private static Point WINDOW_POINT = new Point();

    ScannerWindow(JFrame jFrame) throws HeadlessException {
        super();
        setSize(400, 400);
        setUndecorated(true);
        setVisible(true);

        JPanel jPanel = new JPanel();

        JLabel jLabel = new JLabel("★拖动我对准收款码★", JLabel.CENTER);
        Font font = new Font(null, Font.BOLD, 30);
        jLabel.setForeground(Color.RED);
        jLabel.setFont(font);

        jPanel.add(jLabel);
        add(jPanel);

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                if (System.getProperty("os.name").startsWith("Mac OS X")) {
                    setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                } else {
                    setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                WINDOW_POINT.x = e.getX();
                WINDOW_POINT.y = e.getY();
            }
        });

        this.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                super.mouseDragged(e);
                Point p = getLocation();
                setLocation(p.x + e.getX() - WINDOW_POINT.x, p.y + e.getY() - WINDOW_POINT.y);
            }
        });

        if (com.sun.awt.AWTUtilities.isWindowOpaque(this)) {
            com.sun.awt.AWTUtilities.setWindowOpacity(this, 0.3F);
        } else {
            JOptionPane.showMessageDialog(this, "系统不支持，当前JDK版本过低！");
        }
    }

}
