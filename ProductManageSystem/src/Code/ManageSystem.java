package Code;

import java.util.*;
import java.io.*;
import java.sql.*;

public class ManageSystem {
    // URL格式：jdbc:mysql://主机名:端口号/数据库名
    String url = "jdbc:mysql://121.40.20.237:3306/db_pms";
    String user = "locher"; // 数据库用户名
    String password = "locher@123"; // 数据库密码

    List<Product> products = new ArrayList<>();
    // List<User> users = new ArrayList<>();
    HashSet<User> users = new HashSet<>();
    public boolean isAdmin = false;

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

            // create table User(
            //     id int auto_increment primary key,
            //     userName varchar(64),
            //     email varchar(32),
            //     userID int,
            //     hashCode_password long
            // );
            
            sql = "select * from User";
            rs = stmt.executeQuery(sql);


            while (rs.next()) {
                User temp = new User();
                temp.setUserName(rs.getString("userName"));
                temp.setUserID(rs.getInt("userID"));
                temp.setEmail(rs.getString("email"));
                temp.setHashcode_password(rs.getLong("hashCode_password"));
                users.add(temp);
            }

            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            System.out.println("error read database.");
            e.printStackTrace();
        }
    }

    public boolean signIn(User newUser) {
        boolean isLegal = true;
        for (var i : users) {
            if (i.getUserName().equals(newUser.getUserName()))
                isLegal = false;
        }
        if (isLegal) {
            users.add(newUser);
        }
        return isLegal;
    }

    public boolean login(User userMsg) {
        users.forEach((i) -> {
            if (i.getEmail().equals(userMsg.getEmail())
                    && i.getHashcode_password() == userMsg.getHashcode_password()) {
                isAdmin = true;
            }
            if (i.getUserName().equals(userMsg.getUserName())
                    && i.getHashcode_password() == userMsg.getHashcode_password()) {
                isAdmin = true;
            }
        });

        if (isAdmin)
            return true;
        else
            return false;
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

            String truncateCMD = "truncate table Product";
            stmt.executeUpdate(truncateCMD);

            String sql = "insert into Product (name, price, category, description) value (?,?,?,?)";
            PreparedStatement preStatement = conn.prepareStatement(sql);

            products.forEach(p -> {
                try {
                    preStatement.setString(1, p.getName());
                    preStatement.setInt(2, p.getPrice());
                    preStatement.setString(3, p.getCategory());
                    preStatement.setString(4, p.getDescription());
                    // 执行插入操作
                    int affectedRows = preStatement.executeUpdate();
                    if (affectedRows > 0) {
                        System.out.println("Product data inserted successfully.");
                    } else {
                        System.out.println("No rows affected.");
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            });
            // private String userName;
            // private int userID;
            // private String email;
            // private long hashcode_password;

            // create table User(
            // id int auto_increment primary key,
            // userName varchar(64),
            // email varchar(32),
            // userID int,
            // hashCode_password long
            // );

            sql = "insert into User (userName, email, userID, hashCode_password) value(?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql);

            users.forEach(i -> {
                try {
                    ps.setString(1, i.getUserName());
                    ps.setString(2, i.getEmail());
                    ps.setInt(3, i.getUserID());
                    ps.setLong(4, i.getHashcode_password());

                    ps.executeUpdate();
                    System.out.println("user data insert success.");
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            });

        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

    }

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
