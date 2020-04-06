package com.gc_company.ui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import com.gc_company.enity.Ticket;
import com.gc_company.factory.ObjectFactory;
import com.gc_company.service.proxy.TicketServiceProxy;
import com.gc_company.util.DateVaileUtil;

public class UpdateTicketFrame {
	JFrame mainFrame;
	JLabel welcomeWord;
	JLabel trainNumLabel;
	JTextField trainNumField;
	JLabel startPlaceLabel;
	JTextField startPlaceField;
	JLabel endPlaceLabel;
	JTextField endPlaceField;
	JLabel startTimeLabel;
	JTextField startTimeField;
	JButton backButton;
	JButton updateTicketButton;
	JLabel ticketCountLabel;
	JTextField ticketCountField;
	JLabel ticketPriceLabel;
	JTextField ticketPriceField;
	JLabel ticketStateLabel;
	JTextField ticketStateField;
	TicketServiceProxy ticketServiceProxy;
	Ticket ticket;

	public UpdateTicketFrame(Ticket ticket) {
		this.ticket = ticket;
	}

	public void init() {
		mainFrame = new JFrame("购票系统-修改车票界面");
		welcomeWord = new JLabel("修 改 车 票");
		trainNumLabel = new JLabel("车次:");
		trainNumField = new JTextField(ticket.getTrainnum());
		trainNumField.setEditable(false);
		startPlaceLabel = new JLabel("起点:");
		startPlaceField = new JTextField(ticket.getStartPlace());
		endPlaceLabel = new JLabel("终点:");
		endPlaceField = new JTextField(ticket.getEndPlace());
		startTimeLabel = new JLabel("出发时间:");
		startTimeField = new JTextField(ticket.getStartTime());
		backButton = new JButton("返回主界面");
		updateTicketButton = new JButton("修改车次");
		ticketCountLabel = new JLabel("票数:");
		ticketCountField = new JTextField(String.valueOf(ticket.getCount()));
		ticketPriceLabel = new JLabel("价格:");
		ticketPriceField = new JTextField(String.valueOf(ticket.getPrice()));
		ticketStateLabel = new JLabel("状态:");
		ticketStateField = new JTextField(ticket.getState());
		ticketServiceProxy = (TicketServiceProxy) ObjectFactory
				.getObject("ticketServiceProxy");
	}

	public void build() {
		mainFrame.setBounds(200, 200, 300, 450);
		mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		mainFrame.setLayout(null);

		welcomeWord.setBounds(100, 10, 100, 30);
		welcomeWord.setFont(new Font("", Font.PLAIN, 20));
		mainFrame.add(welcomeWord);

		trainNumLabel.setBounds(30, 60, 60, 25);
		mainFrame.add(trainNumLabel);

		trainNumField.setBounds(70, 60, 200, 25);
		mainFrame.add(trainNumField);

		startPlaceLabel.setBounds(30, 100, 60, 25);
		mainFrame.add(startPlaceLabel);

		startPlaceField.setBounds(70, 100, 200, 25);
		mainFrame.add(startPlaceField);

		endPlaceLabel.setBounds(30, 140, 60, 25);
		mainFrame.add(endPlaceLabel);

		endPlaceField.setBounds(70, 140, 200, 25);
		mainFrame.add(endPlaceField);

		startTimeLabel.setBounds(5, 180, 60, 25);
		mainFrame.add(startTimeLabel);

		startTimeField.setBounds(70, 180, 200, 25);
		mainFrame.add(startTimeField);

		ticketCountLabel.setBounds(30, 220, 60, 25);
		mainFrame.add(ticketCountLabel);

		ticketCountField.setBounds(70, 220, 200, 25);
		mainFrame.add(ticketCountField);

		ticketPriceLabel.setBounds(30, 260, 60, 25);
		mainFrame.add(ticketPriceLabel);

		ticketPriceField.setBounds(70, 260, 200, 25);
		mainFrame.add(ticketPriceField);

		ticketStateLabel.setBounds(30, 300, 60, 25);
		mainFrame.add(ticketStateLabel);

		ticketStateField.setBounds(70, 300, 200, 25);
		mainFrame.add(ticketStateField);

		backButton.setBounds(30, 360, 100, 25);
		mainFrame.add(backButton);

		updateTicketButton.setBounds(170, 360, 100, 25);
		mainFrame.add(updateTicketButton);

		mainFrame.setVisible(true);
	}

	public void addAction() {
		backButtonSetActionListener();
		updateTicketButtonSetActionListener();
	}

	// 返回管理员主界面按钮
	public void backButtonSetActionListener() {
		backButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				mainFrame.setVisible(false);
				new AdminFrame().start();
			}
		});
	}

	// 修改车次按钮
	public void updateTicketButtonSetActionListener() {
		updateTicketButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				StringBuffer errorMessages = new StringBuffer();
				String trainNum = trainNumField.getText();
				String startPlace = startPlaceField.getText();
				String endPlace = endPlaceField.getText();
				String startTime = startTimeField.getText();
				String ticketCount = ticketCountField.getText();
				String ticketState = ticketStateField.getText();
				String ticketPrice = ticketPriceField.getText();
				// 表单验证
				// 车次不符合命名规则 一个大写字母+(2~4)个数字
				if (!trainNum.matches("[K,D,T,G,Z]{1}\\d{2,4}")) {
					errorMessages.append("车次名称不规范，请重新输入").append("\n");
				}
				// 起点终点相同
				if (startPlace.equals(endPlace)) {
					errorMessages.append("起点终点不能相同，请重新输入").append("\n");
				}
				// 起点终点不能为空
				if ("".equals(startPlace.trim()) || "".equals(endPlace.trim())) {
					errorMessages.append("起点或终点不能为空，请重新输入").append("\n");
				}
				// 时间格式不正确
				try {
					if (!DateVaileUtil.isValidDate(startTime)) {
						errorMessages.append("时间格式不规范，请重新输入").append("\n");
					}
				} catch (NumberFormatException e1) {
					e1.printStackTrace();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				// 票数格式不正确
				if (!ticketCount.matches("[0-9]*")) {
					errorMessages.append("票数格式不规范，请重新输入").append("\n");
				}
				// 票价格式不正确
				if (!ticketPrice.matches("[1-9]{1}\\d*(.)?\\d+")) {
					errorMessages.append("票价不规范，请重新输入").append("\n");
				}
				// 显示所有表单验证的错误
				if (errorMessages.length() != 0) {
					JOptionPane.showMessageDialog(mainFrame, errorMessages,
							"修改失败", JOptionPane.ERROR_MESSAGE);
					return;
				}
				try {
					ticket.setTrainnum(trainNum);
					ticket.setStartPlace(startPlace);
					ticket.setEndPlace(endPlace);
					ticket.setStartTime(startTime);
					ticket.setCount(Integer.valueOf(ticketCount));
					ticket.setState(ticketState);
					ticket.setPrice(Double.valueOf(ticketPrice));
					ticketServiceProxy.updateOneTicket(ticket);
					JOptionPane.showMessageDialog(mainFrame, "修改成功", "修改成功",
							JOptionPane.INFORMATION_MESSAGE);
				} catch (Exception e) {
					e.printStackTrace();
					JOptionPane.showMessageDialog(mainFrame, e.getMessage(),
							"修改失败", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
	}

	public void start() {
		init();
		addAction();
		build();
	}
}
