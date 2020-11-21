package control;

import java.util.ArrayList;
import java.util.List;
import model.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author vr
 */
public class LayananControl {
    SessionFactory session = HibernateUtil.getSessionFactory();

    private Session bukaSession() {
        return session.openSession();
    }

    public List<Jenislayanan> getAllLayanan() {
        Session sess = bukaSession();
        List<Jenislayanan> temp = sess.createQuery("SELECT l FROM Jenislayanan l").list();
        sess.close();
        return temp;
    }
    
    public List<Jenislayanan> getSearchLayanan(String text) {
        Session sess = bukaSession();
        List<Jenislayanan> temp = sess.createQuery("SELECT l FROM Jenislayanan l WHERE NamaLayanan LIKE '%" + text+ "%'").list();
        sess.close();
        return temp;
    }
    
     public List<Inventory> getAllInventory() {
        Session sess = bukaSession();
        List<Inventory> temp = sess.createQuery("SELECT i FROM Inventory i").list();
        sess.close();
        return temp;
    }

    public boolean simpanAtauUpdateOneLayanan(Jenislayanan param) {
        try {
            Session sess = bukaSession();
            Transaction t = sess.beginTransaction();
            sess.saveOrUpdate(param);
            t.commit();
            sess.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteOneLayanan(Jenislayanan param) {
        try {
            Session sess = bukaSession();
            Transaction t = sess.beginTransaction();
            param = (Jenislayanan) sess.load(Jenislayanan.class, param.getIdLayanan());
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
