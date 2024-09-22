package Code;


//java GUI package
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI extends JFrame {

    public static void startGUI() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new GUI().setVisible(true);
            }
        });
    }


    public GUI() {
        setTitle("特产管理系统");
        setSize(300, 500);
        setLocationRelativeTo(null); // 居中显示
        setDefaultCloseOperation(EXIT_ON_CLOSE); // 设置默认关闭操作

        // 创建面板并设置为布局管理器
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(7, 1)); // 7行1列的网格布局

        // 创建按钮并添加到面板
        JButton viewSpecialties = new JButton("1.查看所有特产");
        JButton searchSpecialty = new JButton("2.查询特产");
        JButton sortSpecialties = new JButton("3.将特产按价格排序");
        JButton addSpecialty = new JButton("4.添加特产");
        JButton deleteSpecialty = new JButton("5.删除特产");
        JButton exit = new JButton("0.退出");

        // 添加按钮到面板
        panel.add(viewSpecialties);
        panel.add(searchSpecialty);
        panel.add(sortSpecialties);
        panel.add(addSpecialty);
        panel.add(deleteSpecialty);
        panel.add(exit);

        // 添加面板到窗口
        add(panel);

        // 为每个按钮添加事件监听器
        viewSpecialties.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("查看所有特产");
            }
        });

        searchSpecialty.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("查询特产");
                // 在这里添加查询特产的逻辑
            }
        });

        sortSpecialties.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("将特产按价格排序");
                // 在这里添加按价格排序特产的逻辑
            }
        });

        addSpecialty.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("添加特产");
                // 在这里添加添加特产的逻辑
            }
        });

        deleteSpecialty.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("删除特产");
                // 在这里添加删除特产的逻辑
            }
        });

        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("退出");
                System.exit(0); // 退出程序
            }
        });
    }


}
