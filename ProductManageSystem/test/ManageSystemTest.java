package ProductManageSystem.test;

import Code.ManageSystem;
import org.junit.Test;

public class ManageSystemTest{
    ManageSystem ms = new ManageSystem();
    @Test
    public void ManageSystem(){

        ms.sortByPriceAsc();
        ms.sortByPriceDes();
        ms.getProductsInfo();
        ms.toString();
        ms.saveData();

        ms.searchByName(null);
        ms.searchByName("null");
        ms.searchByName("0");
        ms.searchByName("false");
        ms.searchByName("true");

        ms.deleteProduct(null);
        ms.deleteProduct("null");
        ms.deleteProduct("0");
        ms.deleteProduct("false");
        ms.deleteProduct("true");

    }
}