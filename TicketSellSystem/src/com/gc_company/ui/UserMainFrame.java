package com.gc_company.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

import com.gc_company.Constant.Constant;
import com.gc_company.enity.Ticket;
import com.gc_company.enity.User;
import com.gc_company.exception.InsufficientMoneyException;
import com.gc_company.exception.YouBoughtTheTicketException;
import com.gc_company.factory.ObjectFactory;
import com.gc_company.service.proxy.PurchaseRecordServiceProxy;
import com.gc_company.service.proxy.TicketServiceProxy;
import com.gc_company.service.proxy.UserServiceProxy;
import com.gc_company.util.DateVaileUtil;
import com.gc_company.util.Pager;

public class UserMainFrame {
	JFrame mainFrame;
	JLabel startPlaceLabel;
	JTextField startPlaceField;
	JLabel endPlacrLabel;
	JTextField endPlaceField;
	JLabel startTimeLabel;
	JTextField startTimeField;
	JButton searchButton;
	JButton ownerCenter;
	JTable resultTable;
	JButton firstPageButton;
	JButton lastPageButton;
	JButton nextPageButton;
	JButton endPageButton;
	JButton buyButton;
	TicketServiceProxy ticketServiceProxy;
	JScrollPane jScrollPane;
	JLabel userMoney;
	Pager<Ticket> page;
	String lastStartPlace;
	String lastEndPlace;
	String lastStartTime;
	User user;

	public UserMainFrame(User user) {
		this.user = user;
	}

	public void init() {
		mainFrame = new JFrame("购票系统-用户主页面");
		startPlaceLabel = new JLabel("起点:");
		startPlaceField = new JTextField();
		endPlacrLabel = new JLabel("终点:");
		endPlaceField = new JTextField();
		startTimeLabel = new JLabel("出发时间:");
		startTimeField = new JTextField("请按照以下格式输入,例:2000-05-03");
		searchButton = new JButton("查询");
		ownerCenter = new JButton("个人中心");
		resultTable = new JTable() {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		firstPageButton = new JButton("首页");
		lastPageButton = new JButton("上一页");
		nextPageButton = new JButton("下一页");
		endPageButton = new JButton("末页");
		buyButton = new JButton("购买");
		userMoney = new JLabel("余额:" + user.getMoney());
		ticketServiceProxy = (TicketServiceProxy) ObjectFactory
				.getObject("ticketServiceProxy");
		jScrollPane = new JScrollPane(resultTable);
		lastStartPlace = "";
		lastEndPlace = "";
		lastStartTime = "";
	}

	public void build() {
		mainFrame.setBounds(200, 200, 820, 460);
		mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		mainFrame.setLayout(null);

		startPlaceLabel.setBounds(25, 25, 40, 25);
		mainFrame.add(startPlaceLabel);

		startPlaceField.setBounds(65, 25, 80, 25);
		mainFrame.add(startPlaceField);

		endPlacrLabel.setBounds(170, 25, 40, 25);
		mainFrame.add(endPlacrLabel);

		endPlaceField.setBounds(210, 25, 80, 25);
		mainFrame.add(endPlaceField);

		startTimeLabel.setBounds(315, 25, 60, 25);
		mainFrame.add(startTimeLabel);

		startTimeField.setBounds(385, 25, 200, 25);
		mainFrame.add(startTimeField);

		searchButton.setBounds(605, 25, 60, 25);
		mainFrame.add(searchButton);

		ownerCenter.setBounds(690, 25, 100, 25);
		mainFrame.add(ownerCenter);

		jScrollPane.setBounds(35, 70, 750, 300);
		mainFrame.add(jScrollPane);

		firstPageButton.setBounds(120, 385, 60, 25);
		mainFrame.add(firstPageButton);

		lastPageButton.setBounds(240, 385, 80, 25);
		mainFrame.add(lastPageButton);

		nextPageButton.setBounds(380, 385, 80, 25);
		mainFrame.add(nextPageButton);

		endPageButton.setBounds(520, 385, 60, 25);
		mainFrame.add(endPageButton);

		buyButton.setBounds(640, 385, 60, 25);
		mainFrame.add(buyButton);

		userMoney.setBounds(10, 385, 120, 25);
		mainFrame.add(userMoney);

		mainFrame.setVisible(true);
	}

	public void addAction() {
		startTimeFieldSetFocusListener();
		searchButtonSetActionListener();
		lastPageButtonSetActionListener();
		nextPageButtonSetActionListener();
		firstPageButtonSetActionListener();
		endPageButtonSetActionListener();
		buyButtonSetActionListener();
		ownerCenterSetActionListener();
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
				// 出发时间的框获得焦点，把内容清空，等待用户输入时间
				if ("请按照以下格式输入,例:2000-05-03".equals(startTimeField.getText())) {
					startTimeField.setText("");
				}
			}
		});
	}

	// 首页按钮
	public void firstPageButtonSetActionListener() {
		firstPageButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (page != null) {
					selectAndShowOnePage(1);
				}
			}
		});
	}

	// 上一页按钮
	public void lastPageButtonSetActionListener() {

		lastPageButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (page != null) {
					selectAndShowOnePage(page.getLastPageNum());
				}
			}
		});
	}

	// 下一页按钮
	public void nextPageButtonSetActionListener() {
		nextPageButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (page != null) {
					selectAndShowOnePage(page.getNextPageNum());
				}
			}
		});
	}

	// 末页按钮
	public void endPageButtonSetActionListener() {
		endPageButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (page != null) {
					selectAndShowOnePage(page.getTotalPage());
				}
			}
		});
	}

	// 查询按钮
	public void searchButtonSetActionListener() {
		searchButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				selectAndShowOnePage(1);
			}
		});
	}

	// 个人中心
	public void ownerCenterSetActionListener() {
		ownerCenter.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new OwnerCenterFrame(user).start();
				mainFrame.setVisible(false);
			}
		});
	}

	public void selectAndShowOnePage(Integer pageNum) {
		String startPlace = startPlaceField.getText();
		String endPlace = endPlaceField.getText();
		String startTime = startTimeField.getText();
		StringBuffer errorMessages = new StringBuffer();
		// 表单验证
		if ("请按照以下格式输入,例:2000-05-03".equals(startTime)) {
			startTime = "";
		}
		// 起点终点相同且不为空
		if (startPlace.equals(endPlace) && !"".equals(startPlace)) {
			errorMessages.append("起点和终点不能相同").append("\n");
		}
		// 时间格式不匹配

		try {
			if (!DateVaileUtil.isValidDate(startTime)
					&& (!"".equals(startTime))) {
				errorMessages.append("时间格式不匹配，请重新输入").append("\n");
			}
		} catch (NumberFormatException e1) {
			e1.printStackTrace();
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		if (errorMessages.length() != 0) {
			JOptionPane.showMessageDialog(mainFrame, errorMessages.toString(),
					"查询错误", JOptionPane.ERROR_MESSAGE);
			return;
		}
		try {
			// 如果三个查询的条件的任意个数，与上次查询结果不同
			// 则本次作为一次新查询，显示新条件的第一页
			if ((!startPlace.equals(lastStartPlace))
					|| (!endPlace.equals(lastEndPlace))
					|| (!startTime.equals(lastStartTime))) {
				page = ticketServiceProxy.getTicketByPageAndCond(startPlace,
						endPlace, startTime, 1);
			} else {
				// 三个查询条件与上次相同，则为正常的换页操作
				page = ticketServiceProxy.getTicketByPageAndCond(startPlace,
						endPlace, startTime, pageNum);
			}
			String[] columns = new String[] { "车次", "起点", "终点", "出发时间", "剩余票数",
					"价格", "状态" };
			Object[][] data = new Object[page.getData().size()][columns.length];
			for (int i = 0; i < page.getData().size(); i++) {
				Ticket ticket = page.getData().get(i);
				data[i][0] = ticket.getTrainnum();
				data[i][1] = ticket.getStartPlace();
				data[i][2] = ticket.getEndPlace();
				data[i][3] = ticket.getStartTime();
				data[i][4] = ticket.getCount();
				data[i][5] = ticket.getPrice();
				data[i][6] = ticket.getState();
			}
			DefaultTableModel defaultTableModel = new DefaultTableModel(data,
					columns);
			resultTable.setModel(defaultTableModel);
			// 把本次查询结果，作为"上次查询"的结果
			lastStartPlace = startPlace;
			lastEndPlace = endPlace;
			lastStartTime = startTime;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 购买按钮
	public void buyButtonSetActionListener() {
		buyButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				StringBuffer errorMessages = new StringBuffer();
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
						"yyyy-MM-dd");
				int row = resultTable.getSelectedRow();
				int column = 0;
				// 表单验证
				// 没有选择车次
				if (row == -1) {
					errorMessages.append("您还没有选择车次").append("\n");
				}
				// 选择的车次不可购票
				if (row != -1) {
					String state = String.valueOf(resultTable
							.getValueAt(row, 6));
					if (state.equals("不可购买")) {
						errorMessages.append("很抱歉，此车次已停售").append("\n");
					}
				}
				//当前时间已经过了发车时间
				String startTime = String.valueOf(resultTable
						.getValueAt(row, 3));
				try {
					if (simpleDateFormat.parse(startTime).getTime()
							- new Date().getTime() < 0) {
						errorMessages.append("已过了发车时间，不可购买").append("\n");
					}
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
				//余票不足
				Integer count=Integer.valueOf(String.valueOf(resultTable.getValueAt(row, 4)));
				if(count==0){
					errorMessages.append("当前车次余票不足").append("\n");
				}
				
				if (errorMessages.length() != 0) {
					JOptionPane.showMessageDialog(mainFrame,
							errorMessages.toString(), "购票失败",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
				try {
					String trainNum = String.valueOf(resultTable.getValueAt(
							row, column));

					// 买票，内含逻辑判断：是否已经买了这趟票，查余票是否足够，钱是否足够,没问题则更新车票数
					ticketServiceProxy.buyOneTicket(user, trainNum);
					// 找到票id，传给购买记录表
					// Ticket ticket =
					// ticketServiceProxy.selectOneTicketByTrainnum(trainNum);
					// 更新购买记录表
					// purchaseRecordProxy.addOneBuyRecord(user.getId(),
					// ticket.getId(), simpleDateFormat.format(new
					// java.util.Date()), "已购买");
					// 买票后，自动刷新当前页面
					selectAndShowOnePage(page.getPageNum());
					userMoney.setText("余额:" + user.getMoney());
				} catch (InsufficientMoneyException e) {
					errorMessages.append(e.getMessage()).append("\n");
				} catch (YouBoughtTheTicketException e) {
					errorMessages.append(e.getMessage()).append("\n");
				} catch (Exception e) {
					errorMessages.append(e.getMessage()).append("\n");
				}
				if (errorMessages.length() != 0) {
					JOptionPane.showMessageDialog(mainFrame, errorMessages,
							"购买失败", JOptionPane.ERROR_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(mainFrame, "购票成功", "购买成功",
							JOptionPane.INFORMATION_MESSAGE);
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
