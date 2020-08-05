package com.github.xuchengen;

import cn.hutool.core.swing.RobotUtil;
import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.qrcode.QrCodeUtil;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

class MainWindow extends JFrame {

    private JTextField qrcodeData;

    private ScannerWindow scannerWindow;

    public JTextField getQrcodeData() {
        return qrcodeData;
    }

    MainWindow() throws HeadlessException {
        super();

        JPanel jPanel = new JPanel();
        jPanel.setPreferredSize(new Dimension(400, 255));

        JLabel apiAddressLabel = new JLabel("接口地址：");
        apiAddressLabel.setPreferredSize(new Dimension(80, 35));

        JTextField apiAddress = new JTextField();
        apiAddress.setPreferredSize(new Dimension(300, 35));

        JLabel orderTitleLabel = new JLabel("订单名称：");
        orderTitleLabel.setPreferredSize(new Dimension(80, 35));

        JTextField orderTitle = new JTextField("条码支付测试");
        orderTitle.setEditable(false);
        orderTitle.setPreferredSize(new Dimension(300, 35));

        JLabel orderAmountLabel = new JLabel("订单金额：");
        orderAmountLabel.setPreferredSize(new Dimension(80, 35));

        JTextField orderAmount = new JTextField("0.01");
        orderAmount.setEditable(false);
        orderAmount.setPreferredSize(new Dimension(300, 35));

        JLabel qrcodeDataLabel = new JLabel("收款码数据：");
        qrcodeDataLabel.setPreferredSize(new Dimension(80, 35));

        qrcodeData = new JTextField();
        qrcodeData.setEditable(false);
        qrcodeData.setPreferredSize(new Dimension(300, 35));

        JButton qrcodeBtn = new JButton("扫描收款码");
        qrcodeBtn.setPreferredSize(new Dimension(380, 40));

        JButton submitBtn = new JButton("提交订单");
        submitBtn.setPreferredSize(new Dimension(380, 40));

        qrcodeBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    qrcodeBtn.setEnabled(false);
                    scannerWindow.setVisible(false);
                    ThreadUtil.safeSleep(200);
                    BufferedImage bufferedImage = RobotUtil.captureScreen(scannerWindow.getBounds());
                    String decode = QrCodeUtil.decode(bufferedImage);
                    qrcodeData.setText(decode);
                } catch (Exception ex) {
                    qrcodeData.setText("请对准收款码再试一次！");
                } finally {
                    qrcodeBtn.setEnabled(true);
                    scannerWindow.setVisible(true);
                }
            }
        });

        submitBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                EventQueue.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            String apiUrl = apiAddress.getText();
                            String subject = orderTitle.getText();
                            String amount = orderAmount.getText();
                            String barcodeValue = qrcodeData.getText();

                            if (StrUtil.isBlank(apiUrl)) {
                                JOptionPane.showMessageDialog(null, "接口地址为空", "系统提示", JOptionPane.ERROR_MESSAGE);
                                return;
                            }

                            if (StrUtil.isBlank(barcodeValue) || barcodeValue.equals("请对准收款码再试一次！")) {
                                JOptionPane.showMessageDialog(null, "收款码数据为空", "系统提示", JOptionPane.ERROR_MESSAGE);
                                return;
                            }

                            HttpResponse response = HttpUtil.createPost(apiUrl)
                                    .form("subject", subject)
                                    .form("amount", amount)
                                    .form("barcodeValue", barcodeValue)
                                    .execute();

                            String body = response.body();

                            JSONObject jsonObject = JSONUtil.parseObj(body);

                            if (jsonObject.getInt("code") == 200) {
                                JOptionPane.showMessageDialog(null, "收款成功", "系统提示", JOptionPane.PLAIN_MESSAGE);
                            } else {
                                JOptionPane.showMessageDialog(null, jsonObject.getStr("msg"), "系统提示", JOptionPane.ERROR_MESSAGE);
                            }
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(null, "系统异常：" + ex.getMessage(), "系统提示", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                });
            }
        });

        jPanel.add(apiAddressLabel);
        jPanel.add(apiAddress);
        jPanel.add(orderTitleLabel);
        jPanel.add(orderTitle);
        jPanel.add(orderAmountLabel);
        jPanel.add(orderAmount);
        jPanel.add(qrcodeDataLabel);
        jPanel.add(qrcodeData);
        jPanel.add(qrcodeBtn);
        jPanel.add(submitBtn);

        add(jPanel);

        pack();
        setTitle("收款码扫描枪模拟器V0.01——By徐叔科技");
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        scannerWindow = new ScannerWindow(this);
    }

}
