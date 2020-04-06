package com.gc_company.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import com.gc_company.enity.User;
import com.gc_company.exception.LoginFailException;
import com.gc_company.factory.ObjectFactory;
import com.gc_company.service.impl.UserServiceImpl;
import com.gc_company.service.proxy.UserServiceProxy;

public class LoginFrame {

	JFrame mainFrame = null;
	JLabel welcomeWord = null;
	JLabel usernameLabel = null;
	JTextField usernameField = null;
	JLabel passwordLabel = null;
	JPasswordField passwordField = null;
	JButton loginButton = null;
	JButton registButton = null;
	UserServiceProxy userServiceProxy = null;

	public void init() {
		mainFrame = new JFrame("购票系统登录页面");
		welcomeWord = new JLabel("欢  迎  使  用");
		usernameLabel = new JLabel("用户名:");
		usernameField = new JTextField();
		passwordLabel = new JLabel("密 码:");
		passwordField = new JPasswordField();
		loginButton = new JButton("登录");
		registButton = new JButton("注册");
		userServiceProxy = (UserServiceProxy) ObjectFactory.getObject("userServiceProxy");
	}

	public void build() {
		mainFrame.setSize(300, 300);
		mainFrame.setLocation(500, 100);
		mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		mainFrame.setLayout(null);

		welcomeWord.setBounds(110, 10, 100, 30);
		mainFrame.add(welcomeWord);

		usernameLabel.setBounds(50, 50, 60, 25);
		mainFrame.add(usernameLabel);

		usernameField.setBounds(120, 50, 120, 25);
		mainFrame.add(usernameField);

		passwordLabel.setBounds(50, 100, 60, 25);
		mainFrame.add(passwordLabel);

		passwordField.setBounds(120, 100, 120, 25);
		mainFrame.add(passwordField);

		loginButton.setBounds(80, 150, 60, 25);
		mainFrame.add(loginButton);

		registButton.setBounds(160, 150, 60, 25);
		mainFrame.add(registButton);

		mainFrame.setVisible(true);
	}

	public void loginButtonSetActionListener() {
		loginButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String username = usernameField.getText();
				String password = new String(passwordField.getPassword());
				// 表单验证，帐号密码不能为空
				if ("".equals(username) || "".equals(password)) {
					JOptionPane.showMessageDialog(mainFrame, "用户名或密码不能为空", "登录失败", JOptionPane.ERROR_MESSAGE);
					return;
				}
				try {
					User user = userServiceProxy.login(username, password);
					System.out.println(user.getState());
					if ("¹ÜÀíÔ±".equals(user.getState())) {
						new AdminFrame().start();
						JOptionPane.showMessageDialog(mainFrame, "登录成功", "登录成功", JOptionPane.INFORMATION_MESSAGE);
					} else if ("Õý³£ÓÃ»§".equals(user.getState())) {
						new UserMainFrame(user).start();
						JOptionPane.showMessageDialog(mainFrame, "登录成功", "登录成功", JOptionPane.INFORMATION_MESSAGE);
					} else {
						throw new LoginFailException("很抱歉，您已被禁止登录");
					}
					mainFrame.setVisible(false);
				} catch (LoginFailException e) {
					JOptionPane.showMessageDialog(mainFrame, e.getMessage(), "登录失败", JOptionPane.ERROR_MESSAGE);
				} catch (Exception e) {
					// 捕获意外的异常(事务操作)
					JOptionPane.showMessageDialog(mainFrame, e.getMessage(), "登录失败", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
	}

	public void registButtonSetActionListener() {
		registButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				mainFrame.setVisible(false);
				new RegistFrame().start();
			}
		});
	}

	public void addAction() {
		loginButtonSetActionListener();
		registButtonSetActionListener();
	}

	public void start() {
		init();
		addAction();
		build();
	}
}
