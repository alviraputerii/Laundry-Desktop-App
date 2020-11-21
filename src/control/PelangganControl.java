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
public class PelangganControl {
     SessionFactory session = HibernateUtil.getSessionFactory();

    private Session bukaSession() {
        return session.openSession();
    }

    public List<Pelanggan> getAllPelanggan() {
        Session sess = bukaSession();
        List<Pelanggan> temp = sess.createQuery("SELECT p FROM Pelanggan p").list();
        sess.close();
        return temp;
    }
    
    public List<Pelanggan> getSearchPelanggan(String text) {
        Session sess = bukaSession();
        List<Pelanggan> temp = sess.createQuery("SELECT p FROM Pelanggan p WHERE NamaPelanggan LIKE '%" + text+ "%'").list();
        sess.close();
        return temp;
    }

    public boolean simpanAtauUpdateOnePelanggan(Pelanggan param) {
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

    public boolean deleteOnePelanggan(Pelanggan param) {
        try {
            Session sess = bukaSession();
            Transaction t = sess.beginTransaction();
            param = (Pelanggan) sess.load(Pelanggan.class, param.getIdPelanggan());
            sess.delete(param);
            t.commit();
            sess.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public String[] getStatusMem() {
        String[] cb = new String[2];
        cb[0] = "Tidak Aktif";
        cb[1] = "Aktif";
        return cb;
    }
}
