package com.gc_company.ui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

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

public class AddTicketFrame {
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
	JButton addTicketButton;
	JLabel ticketCountLabel;
	JTextField ticketCountField;
	JLabel ticketPriceLabel;
	JTextField ticketPriceField;
	TicketServiceProxy ticketServiceProxy;

	public void init() {
		mainFrame = new JFrame("购票系统-添加车票界面");
		welcomeWord = new JLabel("添 加 车 票");
		trainNumLabel = new JLabel("车次:");
		trainNumField = new JTextField();
		startPlaceLabel = new JLabel("起点:");
		startPlaceField = new JTextField();
		endPlaceLabel = new JLabel("终点:");
		endPlaceField = new JTextField();
		startTimeLabel = new JLabel("出发时间:");
		startTimeField = new JTextField("请按照以下格式输入,例:2000-05-03");
		backButton = new JButton("返回主界面");
		addTicketButton = new JButton("添加车次");
		ticketCountLabel = new JLabel("票数:");
		ticketCountField = new JTextField();
		ticketPriceLabel = new JLabel("价格:");
		ticketPriceField = new JTextField();
		ticketServiceProxy = (TicketServiceProxy) ObjectFactory
				.getObject("ticketServiceProxy");
	}

	public void build() {
		mainFrame.setBounds(200, 200, 300, 400);
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

		backButton.setBounds(30, 330, 100, 25);
		mainFrame.add(backButton);

		addTicketButton.setBounds(170, 330, 100, 25);
		mainFrame.add(addTicketButton);

		mainFrame.setVisible(true);
	}

	public void addAction() {
		startTimeFieldSetFocusListener();
		backButtonSetActionListener();
		addTicketButtonSetActionListener();
	}

	public void startTimeFieldSetFocusListener() {
		startTimeField.addFocusListener(new FocusListener() {
			@Override
			public void focusLost(FocusEvent e) {
				// 出发时间的框失去焦点，且框内容为空时，把默认显示的字符串显示
				if ("".equals(startTimeField.getText())) {
					startTimeField.setText("请按照以下格式输入,例:2000-05-03");
				}
			}

			@Override
			public void focusGained(FocusEvent e) {
				// 出发时间的框获得焦点，把内容清空，等待管理员输入时间
				if ("请按照以下格式输入,例:2000-05-03".equals(startTimeField.getText())) {
					startTimeField.setText("");
				}
			}
		});
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

	// 添加车次按钮
	public void addTicketButtonSetActionListener() {
		addTicketButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				StringBuffer errorMessages = new StringBuffer();
				String trainNum = trainNumField.getText();
				String startPlace = startPlaceField.getText();
				String endPlace = endPlaceField.getText();
				String startTime = startTimeField.getText();
				String ticketCount = ticketCountField.getText();
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
				System.out.println("!!!!!" + startTime);

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
				if (!ticketCount.matches("[1-9]{1}([0-9])*")) {
					errorMessages.append("票数格式不规范，请重新输入").append("\n");
				}
				// 票价格式不正确
				if (!ticketPrice.matches("[1-9]{1}(\\d*(.)?\\d+)?")) {
					errorMessages.append("票价不规范，请重新输入").append("\n");
				}
				// 显示所有表单验证的错误
				if (errorMessages.length() != 0) {
					JOptionPane.showMessageDialog(mainFrame, errorMessages,
							"添加失败", JOptionPane.ERROR_MESSAGE);
					return;
				}
				try {
					Ticket ticket = new Ticket();
					ticket.setTrainnum(trainNum);
					ticket.setStartPlace(startPlace);
					ticket.setEndPlace(endPlace);
					ticket.setStartTime(startTime);
					ticket.setCount(Integer.valueOf(ticketCount));
					ticket.setPrice(Double.valueOf(ticketPrice));
					System.out.println(ticket);
					ticketServiceProxy.addOneTicket(ticket);
					JOptionPane.showMessageDialog(mainFrame, "添加成功", "添加成功",
							JOptionPane.INFORMATION_MESSAGE);
					trainNumField.setText("");
					startPlaceField.setText("");
					endPlaceField.setText("");
					startTimeField.setText("");
					ticketCountField.setText("");
					ticketPriceField.setText("");
				} catch (Exception e) {
					JOptionPane.showMessageDialog(mainFrame, e.getMessage(),
							"添加失败", JOptionPane.ERROR_MESSAGE);
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
