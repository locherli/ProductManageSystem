package Code;

import java.util.*;
//Target:
//1.read information from file.
//2.can store information after project close.
//3.can sort the product through their price(ascending and descending).
//4.can show the description of a product.
//5.can show all the products at once.
import java.io.*;

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
            File f=new File("ProductManageSystem\\src\\Info.txt");
            try {
                f.createNewFile();
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.getMessage();
                System.out.println("error");
            }
            System.out.println(e.getMessage());
        }

        // try (
        // ObjectInputStream ois = new ObjectInputStream(new
        // FileInputStream("ProductManageSystem/src/Info.ser"));
        // ObjectInputStream ois_keys = new ObjectInputStream(
        // new FileInputStream("ProductManageSystem/src/keys.ser"))) {
        // if (ois.readObject() == null || ois_keys.readObject() == null) {
        // return;
        // }
        // products = (ArrayList<Product>) ois.readObject();
        // keys = (ArrayList<Integer>) ois_keys.readObject();
        // } catch (FileNotFoundException e) {
        // File file_info = new File("ProductManageSystem/src/Info.ser");
        // File file_keys = new File("ProductManageSystem/src/keys.ser");
        // try {
        // file_info.createNewFile();
        // file_keys.createNewFile();
        // System.out.println("Files was created.");
        // Main.main();
        // } catch (IOException ioException) {
        // System.err.println("Error creating file 'Info.ser': " +
        // ioException.getMessage());
        // }
        // } catch (IOException e) {
        // System.err.println("Error reading from file 'Info.ser': " + e.getMessage());
        // } catch (ClassNotFoundException e) {
        // }
    }

    public void saveData() {
        try (
            BufferedWriter writer=new BufferedWriter(
                new FileWriter("ProductManageSystem/src/Info.txt"));
        ){
           for(var i : products){
            writer.write(i.getName()); writer.newLine();
            writer.write(i.getPrice()); writer.newLine();
            writer.write(i.getCategory()); writer.newLine();
            writer.write(i.getDescription()); writer.newLine();
           }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        // try {
        // ObjectOutputStream oos = new ObjectOutputStream(
        // new FileOutputStream("ProductManageSystem/src/Info.ser", true));
        // ObjectOutputStream oos_keys = new ObjectOutputStream(
        // new FileOutputStream("ProductManageSystem/src/keys.ser", true));
        // oos.writeObject(products);
        // oos_keys.writeObject(oos_keys);
        // } catch (Exception e) {
        // System.out.println(e.getStackTrace());
        // }
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
