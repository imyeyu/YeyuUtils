package net.imyeyu.utils.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.net.ConnectException;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import com.sun.management.OperatingSystemMXBean;

import net.imyeyu.utils.Propertier;
import net.imyeyu.utils.YeyuUtils;

public class Error extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;
	int xOld = 0, yOld = 0;
	private JPanel main, info, title, btn;
	private JButton exit;
	private JTextArea textArea;
	private JButton report;

	public Error() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Error.class.getResource("/net/imyeyu/utils/res/img/error.png")));
		setSize(700, 400);
		setUndecorated(true);
		setLocationRelativeTo(null);

		main = new JPanel();
		main.setBorder(new LineBorder(Color.PINK));
		getContentPane().add(main, BorderLayout.CENTER);
		main.setLayout(new BorderLayout(0, 0));

		title = new JPanel();
		title.setBorder(new EmptyBorder(10, 0, 0, 0));
		main.add(title, BorderLayout.NORTH);

		JLabel label = new JLabel("错误");
		label.setIcon(new ImageIcon(Error.class.getResource("/net/imyeyu/utils/res/img/error.png")));
		GroupLayout gl_title = new GroupLayout(title);
		gl_title.setHorizontalGroup(gl_title.createParallelGroup(Alignment.LEADING).addGroup(gl_title
				.createSequentialGroup().addContainerGap().addComponent(label).addContainerGap(633, Short.MAX_VALUE)));
		gl_title.setVerticalGroup(
				gl_title.createParallelGroup(Alignment.LEADING).addGroup(gl_title.createSequentialGroup()
						.addComponent(label).addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		title.setLayout(gl_title);

		info = new JPanel();
		main.add(info, BorderLayout.CENTER);

		JScrollPane scrollPane = new JScrollPane();
		GroupLayout gl_info = new GroupLayout(info);
		gl_info.setHorizontalGroup(gl_info.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_info.createSequentialGroup().addContainerGap()
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 678, Short.MAX_VALUE).addContainerGap()));
		gl_info.setVerticalGroup(gl_info.createParallelGroup(Alignment.LEADING).addComponent(scrollPane,
				GroupLayout.DEFAULT_SIZE, 317, Short.MAX_VALUE));

		textArea = new JTextArea();
		textArea.setTabSize(4);
		textArea.setEditable(false);
		textArea.setFont(new Font("Consolas", Font.PLAIN, 13));
		textArea.setBorder(new EmptyBorder(5, 5, 5, 5));
		scrollPane.setViewportView(textArea);
		info.setLayout(gl_info);

		btn = new JPanel();
		btn.setBorder(new EmptyBorder(0, 0, 10, 0));
		main.add(btn, BorderLayout.SOUTH);

		exit = new JButton("退出");
		exit.addActionListener(this);

		report = new JButton("反馈错误");
		report.addActionListener(this);
		
		GroupLayout gl_btn = new GroupLayout(btn);
		gl_btn.setHorizontalGroup(gl_btn.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_btn.createSequentialGroup().addContainerGap().addComponent(report)
						.addPreferredGap(ComponentPlacement.RELATED, 528, Short.MAX_VALUE).addComponent(exit)
						.addContainerGap()));
		gl_btn.setVerticalGroup(gl_btn.createParallelGroup(Alignment.TRAILING).addGroup(
				gl_btn.createSequentialGroup().addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addGroup(
						gl_btn.createParallelGroup(Alignment.BASELINE).addComponent(exit).addComponent(report))));
		btn.setLayout(gl_btn);

		addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				xOld = e.getX();
				yOld = e.getY();
			}
		});
		addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseDragged(MouseEvent e) {
				Error.this.setLocation(e.getXOnScreen() - xOld, e.getYOnScreen() - yOld);
			}
		});
	}

	public void error(String errorData) {
		textArea.setText(errorData);
	}

	public void actionPerformed(ActionEvent event) {
		Object object = event.getSource();
		if (object == report) {
			try {
				String param = 
					"app=" + new Propertier().getValue("appName") +
					"&cpu=" + getCpu() +
					"&memory=" + getSystemMemorySize() +
					"&os=" + System.getProperty("os.name") +
					"&jvm=" + System.getProperty("java.version") +
					"&date=" + new SimpleDateFormat("yyyy-MM-dd H:mm:ss").format(new Date()) +
					"&data=" + textArea.getText()
				;
				int i = JOptionPane.showConfirmDialog(null, "即将收集你的以下数据：\n\u3000\u3000处理器型号\n\u3000\u3000最大内存\n\u3000\u3000系统版本\n\u3000\u3000JVM 虚拟机版本\n\u3000\u3000报错代码\n\n收集这些数据仅用于完善本程序", "数据收集说明", JOptionPane.YES_NO_OPTION);
				if (i == JOptionPane.YES_OPTION) {
					YeyuUtils.network().sendPost("https://www.imyeyu.net/java/report", param);
					JOptionPane.showMessageDialog(null, "反馈成功，感谢提交", "", JOptionPane.DEFAULT_OPTION);
					System.exit(0);
				}
			} catch (ConnectException e) {
				JOptionPane.showMessageDialog(null, "反馈失败：无法连接反馈服务器_CONNECT_ECX", "", JOptionPane.DEFAULT_OPTION);
			} catch (UnknownHostException e) {
				JOptionPane.showMessageDialog(null, "反馈失败：无法连接反馈服务器_HOST_ECX", "", JOptionPane.DEFAULT_OPTION);
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, "反馈失败：反馈服务器异常_IO_ECX", "", JOptionPane.DEFAULT_OPTION);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "反馈失败：程序异常_ECX", "", JOptionPane.DEFAULT_OPTION);
			}
		}
		if (object == exit) {
			System.exit(0);
		}
	}
	
	private static String getCpu() {
		try {
			Process process = Runtime.getRuntime().exec("wmic cpu get name");
			process.getOutputStream().close();
			Scanner sc = new Scanner(process.getInputStream());
			String cpuName = "";
			while (sc.hasNext()) {
				cpuName += sc.next() + " ";
			}
			sc.close();
			return cpuName.substring(cpuName.indexOf(" ") + 1, cpuName.length() - 1);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private static int getSystemMemorySize() {
        OperatingSystemMXBean osmb = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
        long size = osmb.getTotalPhysicalMemorySize();
        size = size / 1024 / 1024;
        return Integer.parseInt(size + "");
    }
}
