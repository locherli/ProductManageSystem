package Code;

import java.util.*;
import java.io.*;
//Target:
//1.read information from file.
//2.can store information after project close.
//3.can sort the product through their price(ascending and descending).
//4.can show the description of a product.
//5.can show all the products at once.

public class ManageSystem {
    static Scanner sc = new Scanner(System.in);
    List<Product> products = new ArrayList<>();
    List<Integer> keys = new ArrayList<>();

    // Use constructor to read the files.
    public ManageSystem() {

        try (
                BufferedReader reader = new BufferedReader(new FileReader("ProductManageSystem/src/Info.txt"));) {
            String line;
            while ((line = reader.readLine()) != null) {
                Product temp = new Product();
                temp.setName(line);
                temp.setPrice(Integer.valueOf(reader.readLine()));
                temp.setCategory(reader.readLine());
                temp.setDescription(reader.readLine());
                products.add(temp);
            }
        } catch (Exception e) {
            File f = new File("ProductManageSystem\\src\\Info.txt");
            try {
                f.createNewFile();
            } catch (IOException e1) {
                e1.getMessage();
                System.out.println("error");
            }
            System.out.println(e.getMessage());
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
        try (
                BufferedWriter writer = new BufferedWriter(
                        new FileWriter("ProductManageSystem/src/Info.txt", false));) {
            for (var i : products) {
                writer.write(i.getName());
                writer.newLine();
                writer.write(String.valueOf(i.getPrice()));
                writer.newLine();
                writer.write(i.getCategory());
                writer.newLine();
                writer.write(i.getDescription());
                writer.newLine();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public boolean logIn(int key) {
        for (var i : keys) {
            if (i == key)
                return true;
        }
        return false;
    }

    public void adminMode() {
        System.out.println("-----------WELCOME------------");
        System.out.println("1.查看所有特产");
        System.out.println("2.查询特产");
        System.out.println("3.将特产按价格排序");
        System.out.println("4.添加特产");
        System.out.println("5.删除特产");
        System.out.println("0.退出");
        int choice = sc.nextInt();
    }

    public Product searchByName(String name) {
        for (var i : products) {
            if (i.getName().equals(name))
                return i;
        }
        return null;
    }

}
