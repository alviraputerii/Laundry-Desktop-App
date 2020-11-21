package control;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import model.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author vr
 */
public class InventoryControl {

    SessionFactory session = HibernateUtil.getSessionFactory();

    private Session bukaSession() {
        return session.openSession();
    }

    public List<Inventory> getAllInventory() {
        Session sess = bukaSession();
        List<Inventory> temp = sess.createQuery("SELECT i FROM Inventory i").list();
        sess.close();
        return temp;
    }

    public List<Inventory> getSearchInventory(String text) {
        Session sess = bukaSession();
        List<Inventory> temp = sess.createQuery("SELECT i FROM Inventory i WHERE NamaInventory LIKE '%" + text + "%'").list();
        sess.close();
        return temp;
    }

    public Inventory getSingleData(Integer ID) {
        for (Inventory inv : getAllInventory()) {
            if (Objects.equals(ID, inv.getIdInventory())) {
                return inv;
            }
        }
        return null;
    }

    public boolean simpanAtauUpdateOneInventory(Inventory param) {
        try {
            Session sess = bukaSession();
            Transaction t = sess.beginTransaction();
            sess.merge(param);
            t.commit();
            sess.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteOneInventory(Inventory param) {
        try {
            Session sess = bukaSession();
            Transaction t = sess.beginTransaction();
            param = (Inventory) sess.load(Inventory.class, param.getIdInventory());
            sess.delete(param);
            t.commit();
            sess.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
