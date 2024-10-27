package Code;

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
        JButton viewSpecialties = new JButton("查看所有特产");
        JButton searchSpecialty = new JButton("查询特产");
        JButton sortSpecialties = new JButton("将特产按价格排序");
        JButton addSpecialty = new JButton("添加特产");
        JButton alterSpecialty = new JButton("更改产品信息");
        JButton deleteSpecialty = new JButton("删除特产");
        JButton exit = new JButton("退出");

        // 添加按钮到面板
        panel.add(viewSpecialties);
        panel.add(searchSpecialty);
        panel.add(sortSpecialties);
        panel.add(addSpecialty);
        panel.add(alterSpecialty);
        panel.add(deleteSpecialty);
        panel.add(exit);

        // 为每个按钮添加事件监听器
        viewSpecialties.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (manageSys.products.isEmpty())
                    printText("no data for now.");
                else
                    printText(manageSys.getProductsInfo());
            }
        });

        searchSpecialty.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createSearchPanel();
            }
        });

        sortSpecialties.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createSortPanel();
            }
        });

        addSpecialty.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                productForm();
            }
        });

        alterSpecialty.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createAlterPanel();
            }
        });

        deleteSpecialty.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createDeletePanel();

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

    private void productForm() {

        JFrame frame = new JFrame("添加");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(350, 500);

        frame.setLayout(new FlowLayout()); // 设置布局管理器

        // get information from user.
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
        // frame.pack();

        JButton submit = new JButton("submit");
        // 设置按钮的首选大小
        submit.setPreferredSize(new Dimension(80, 30));
        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Product p = new Product();
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

    private void createAlterPanel() {
        JFrame f = new JFrame("更改信息");
        f.setLayout(new FlowLayout());
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        f.setVisible(true);
        f.setSize(350, 400);

        f.add(new JLabel("<html>输入想要更改的产品的名字<br>(空着即为不更改)<br></html>"));

        JTextField nameField = new JTextField(30);
        f.add(nameField);
  

        f.add(new JLabel("<html><br></html>"));

        f.add(new JLabel("输入新市价:"));
        JTextField priceField = new JTextField(30);
        priceField.setSize(getPreferredSize());
        f.add(priceField);

        f.add(new JLabel("输入新类别:"));
        JTextField categoryField = new JTextField(30);
        categoryField.setSize(getPreferredSize());
        f.add(categoryField);

        f.add(new JLabel("输入新描述:"));
        JTextField descriptionField = new JTextField(30);
        descriptionField.setSize(getPreferredSize());
        f.add(descriptionField);

        JButton submit = new JButton("submit");
        // 设置按钮的首选大小
        submit.setPreferredSize(new Dimension(80, 30));
        submit.addActionListener(e -> {

            Product productToAlter = manageSys.searchByName(nameField.getText());
            Product newProduct = new Product();
            newProduct.setName(productToAlter.getName());
            if (priceField.getText().equals("")) {
                newProduct.setPrice(productToAlter.getPrice());
            } else {
                newProduct.setPrice(Integer.valueOf(priceField.getText()));
            }

            if (categoryField.getText().equals("")) {
                newProduct.setCategory(productToAlter.getCategory());
            } else {
                newProduct.setCategory(categoryField.getText());
            }

            if (descriptionField.getText().equals("")) {
                newProduct.setDescription(productToAlter.getDescription());
            } else {
                newProduct.setDescription(descriptionField.getText());
            }

            manageSys.products.add(newProduct);
            manageSys.deleteProduct(nameField.getText());
        }

        );
        f.add(submit);
    }

    private void createSortPanel() {
        JFrame f = new JFrame("请选择");
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        f.setSize(400, 300);
        f.setLocation(600, 200);
        f.setLayout(new FlowLayout()); // Use FlowLayout to manage button placement

        JButton button_des = new JButton("按价格降序排序");
        button_des.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                manageSys.sortByPriceDes();
                printText("排序完成");
            }
        });

        JButton button_asc = new JButton("按价格升序排序"); // Corrected button text
        button_asc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                manageSys.sortByPriceAsc();
                printText("排序完成");
            }
        });

        // Add buttons to the frame
        f.add(button_des);
        f.add(button_asc);

        f.setVisible(true); // Set visibility after adding components
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
                if (result == null)
                    printText("There's no product named " + productName);
                else
                    printText(result.toString());
                frame.dispose();
            }
        });
        frame.add(submit);
    }

    private void createDeletePanel() {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(400, 200);

        frame.setLayout(new FlowLayout()); // 设置布局管理器

        frame.add(new JLabel("需删除的产品名:"));
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
                boolean bool = manageSys.deleteProduct(field.getText());
                if (bool == true) {
                    printText("成功删除");
                } else {
                    printText("没有名为 " + field.getText() + " 的产品");
                }
                frame.dispose();
            }
        });
        frame.add(submit);
    }

    private void printText(String str) {
        JFrame f = new JFrame("Result");
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        f.setSize(500, 300);
        f.setLayout(new FlowLayout());

        JTextArea textArea = new JTextArea(str);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setEditable(false); // Make it non-editable
        textArea.setPreferredSize(new Dimension(480, 700)); // Set preferred size for the JTextArea

        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        f.add(scrollPane);

        f.setVisible(true);
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
