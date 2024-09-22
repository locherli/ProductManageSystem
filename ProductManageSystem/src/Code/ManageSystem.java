package Code;

import java.util.*;
import java.util.List;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

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
    public ManageSystem() throws Exception {
        try {
            ObjectInputStream ois = new ObjectInputStream(
                    new FileInputStream("src/Info.ser"));
            ObjectInputStream ois_keys = new ObjectInputStream(
                    new FileInputStream("src/Info.ser"));
            products = (ArrayList<Product>) ois.readObject();
            keys = (ArrayList<Integer>) ois_keys.readObject();
        } catch (Exception e) {
            new File("src/Info.ser").createNewFile();
            System.out.println("a file was created.");
            new Main().main();
        } finally {

        }
    }

    public void saveData() {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(
                    new FileOutputStream("src/Info.ser",true));
            ObjectOutputStream oos_keys = new ObjectOutputStream(
                        new FileOutputStream("src/Info.ser",true));
            oos.writeObject(products);
            oos_keys.writeObject(oos_keys);
        } catch (Exception e) {
            System.out.println(e.getStackTrace());
        }
    }

    public boolean logIn(int key) throws IOException {
        File fileAccounts = new File("ProductManageSystem/src/data/userInfo.csv");
        fileAccounts.createNewFile(); // make sure the file exist.

        System.out.println("-------LOG IN-------");
        System.out.println("username:");
        System.out.println("password:");
        int hashCode_key = (sc.next() + sc.next()).hashCode();

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

}
