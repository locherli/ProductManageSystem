package Code;

import java.util.*;
import java.io.*;
import java.sql.*;

public class ManageSystem {
    // URL格式：jdbc:mysql://主机名:端口号/数据库名
    String url = "jdbc:mysql://localhost:3306/db_pms";
    String user = "root"; // 数据库用户名
    String password = "locher"; // 数据库密码

    List<Product> products = new ArrayList<>();
    List<User> users = new ArrayList<>();
    public int index;

    // Use constructor to read the files.
    public ManageSystem() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("error load Driver");
        }
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, password);
            Statement stmt = conn.createStatement();

            String sql = "SELECT * FROM Product";
            ResultSet rs = stmt.executeQuery(sql);

            // read values line by line.
            while (rs.next()) {
                Product temp = new Product();
                temp.setName(rs.getString("name"));
                temp.setPrice(Integer.valueOf(rs.getString("price")));
                temp.setCategory(rs.getString("category"));
                temp.setDescription(rs.getString("description"));
                products.add(temp);
            }
            sql = "select * from User;";
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                User temp = new User();
                temp.setUserName(rs.getString("UserName"));
                temp.setEmail(rs.getString("email"));
                temp.setUserID(Integer.valueOf(rs.getString("UserID")));
                temp.setHashcode_password(Long.valueOf(rs.getString("hashCode_password")));
            }

            index=products.size();
            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            System.out.println("error read database.");
            e.printStackTrace();
        }
    }

    // descending
    public void sortByPriceDes() {
        products.sort((a, b) -> {
            return b.getPrice() - a.getPrice();
        });
    }

    // ascending
    public void sortByPriceAsc() {
        products.sort((a, b) -> {
            return a.getPrice() - b.getPrice();
        });
    }

    public String getProductsInfo() {
        StringBuilder sBuilder = new StringBuilder();
        for (var i : products) {
            sBuilder.append(" 产品名: " + i.getName())
                    .append(" 市价: " + i.getPrice())
                    .append(" 种类: " + i.getCategory())
                    .append(" 描述: " + i.getDescription() + "\n");
        }
        return sBuilder.toString();
    }

    public void saveData() {
        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            Statement stmt = conn.createStatement();
            String sql = "insert into Product (name, price, category, description) value (?,?,?,?)";
            PreparedStatement preStatement = conn.prepareStatement(sql);

            for (int i=index;i<products.size();i++){
                try {
                    preStatement.setString(1, products.get(index).getName());
                    preStatement.setInt(2, products.get(index).getPrice());
                    preStatement.setString(3, products.get(index).getCategory());
                    preStatement.setString(4, products.get(index).getDescription());
                    // 执行插入操作
                    int affectedRows = preStatement.executeUpdate();
                    if (affectedRows > 0) {
                        System.out.println("Data inserted successfully.");
                    } else {
                        System.out.println("No rows affected.");
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }


//            products.forEach(p -> {
//                try {
//                    preStatement.setString(1, p.getName());
//                    preStatement.setInt(2, p.getPrice());
//                    preStatement.setString(3, p.getCategory());
//                    preStatement.setString(4, p.getDescription());
//                    // 执行插入操作
//                    int affectedRows = preStatement.executeUpdate();
//                    if (affectedRows > 0) {
//                        System.out.println("Data inserted successfully.");
//                    } else {
//                        System.out.println("No rows affected.");
//                    }
//                } catch (SQLException e) {
//                    throw new RuntimeException(e);
//                }
//
//            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // public void saveData() {
    // try (
    // BufferedWriter writer = new BufferedWriter(
    // new FileWriter("Info.txt", false));) {
    // for (var i : products) {
    // writer.write(i.getName());
    // writer.newLine();
    // writer.write(String.valueOf(i.getPrice()));
    // writer.newLine();
    // writer.write(i.getCategory());
    // writer.newLine();
    // writer.write(i.getDescription());
    // writer.newLine();
    // }
    // } catch (Exception e) {
    // System.out.println(e.getMessage());
    // }

    // }

    public boolean deleteProduct(String name) {
        for (var i : products) {
            if (i.getName().equals(name)) {
                products.remove(i);
                return true;
            }
        }
        return false;
    }

    public Product searchByName(String name) {
        for (var i : products) {
            if (i.getName().equals(name))
                return i;
        }
        return null;
    }

}
