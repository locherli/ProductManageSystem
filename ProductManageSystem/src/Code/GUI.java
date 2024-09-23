package Code;

import java.io.*;
//java GUI package
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI extends JFrame {
    ManageSystem manageSys = new ManageSystem();

    public static void startGUI() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    GUI frame = new GUI(); // 创建 JFrame 实例
                    frame.setVisible(true); // 在 JFrame 实例上调用 setVisible
                } catch (Exception e) {
                    // i'm confident about my code
                    // so there's no need to handle Exception.
                    System.out.println("error");
                    System.out.println(e.getMessage());
                }
            }
        });
    }

    private JPanel createMenuPanel() {
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

        // 为每个按钮添加事件监听器
        viewSpecialties.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // if (manageSys.products.isEmpty())
                //     printText("no data for now.");
                // else
                    printText(manageSys.products.toString());
            }
        });

        searchSpecialty.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // System.out.println("查询特产");
                createSearchPanel();
            }
        });

        sortSpecialties.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // System.out.println("将特产按价格排序");
                manageSys.products.sort((a, b) -> {
                    return a.getPrice() - b.getPrice();
                });

                printText("降序排序完成");
            }
        });

        addSpecialty.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("添加特产");
                productForm();
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
                manageSys.saveData();
                System.exit(0); // 退出程序
            }
        });

        return panel;
    }

    private void productForm(){
        
        JFrame frame = new JFrame("添加");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(350, 500);

        frame.setLayout(new FlowLayout()); // 设置布局管理器

        //get information from user.
        frame.add(new JLabel("输入产品名:"));
        JTextField nameField = new JTextField(30);
        nameField.setSize(getPreferredSize());
        frame.add(nameField);

        frame.add(new JLabel("输入产品市价:"));
        JTextField priceField = new JTextField(30);
        priceField.setSize(getPreferredSize());
        frame.add(priceField);

        frame.add(new JLabel("输入产品类别:"));
        JTextField categoryField = new JTextField(30);
        categoryField.setSize(getPreferredSize());
        frame.add(categoryField);

        frame.add(new JLabel("输入产品描述:"));
        JTextField descriptionField = new JTextField(30);
        descriptionField.setSize(getPreferredSize());
        frame.add(descriptionField);

        frame.setLocation(600, 200);
        frame.setVisible(true);
        //frame.pack();

        JButton submit = new JButton("submit");
        // 设置按钮的首选大小
        submit.setPreferredSize(new Dimension(80, 30));
        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Product p=new Product();
                p.setName(nameField.getText());
                p.setPrice(Integer.valueOf(priceField.getText()));
                p.setCategory(categoryField.getText());
                p.setDescription(descriptionField.getText());
                manageSys.products.add(p);
                System.out.println("submit pressed");
                frame.dispose();
            }
        });
        frame.add(submit);

    }

    private void createSearchPanel() {

        JFrame frame = new JFrame("输入产品名");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(400, 100);

        frame.setLayout(new FlowLayout()); // 设置布局管理器

        frame.add(new JLabel("输入产品名:"));
        JTextField field = new JTextField(30);
        field.setSize(getPreferredSize());
        frame.add(field);

        frame.setLocation(600, 200);
        frame.setVisible(true);

        Button submit = new Button("submit");
        // 设置按钮的首选大小
        submit.setPreferredSize(new Dimension(80, 30));
        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String productName = field.getText();

                // invoke the logic code from ManageSystem.
                Product result = manageSys.searchByName(productName);
                printText(result.toString());
                frame.dispose();
            }
        });
        frame.add(submit);
    }

    private void printText(String str) {
        JFrame f = new JFrame("result");
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        f.setSize(500, 300);
        f.add(new JLabel(str));
        f.setVisible(true);

        JScrollPane scrollPane = new JScrollPane(f);
        f.add(scrollPane);
    }

    public GUI() {
        setTitle("特产管理系统");
        setSize(300, 500);
        setLocationRelativeTo(null); // 居中显示
        setDefaultCloseOperation(EXIT_ON_CLOSE); // 设置默认关闭操作
        // 添加菜单面板到窗口
        add(createMenuPanel());
    }

}
