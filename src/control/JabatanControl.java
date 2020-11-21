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
public class JabatanControl {
     SessionFactory session = HibernateUtil.getSessionFactory();

    private Session bukaSession() {
        return session.openSession();
    }

    public List<Jabatan> getAllJabatan() {
        Session sess = bukaSession();
        List<Jabatan> temp = sess.createQuery("SELECT j FROM Jabatan j").list();
        sess.close();
        return temp;
    }
    
    public List<Jabatan> getSearchJabatan(String text) {
        Session sess = bukaSession();
        List<Jabatan> temp = sess.createQuery("SELECT j FROM Jabatan j WHERE NamaJabatan LIKE '%" + text+ "%'").list();
        sess.close();
        return temp;
    }

    public boolean simpanAtauUpdateOneJabatan(Jabatan param) {
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

    public boolean deleteOneJabatan(Jabatan param) {
        try {
            Session sess = bukaSession();
            Transaction t = sess.beginTransaction();
            param = (Jabatan) sess.load(Jabatan.class, param.getIdJabatan());
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
