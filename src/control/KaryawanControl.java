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
public class KaryawanControl {
     SessionFactory session = HibernateUtil.getSessionFactory();

    private Session bukaSession() {
        return session.openSession();
    }

    public List<Karyawan> getAllKaryawan() {
        Session sess = bukaSession();
        List<Karyawan> temp = sess.createQuery("SELECT k FROM Karyawan k").list();
        sess.close();
        return temp;
    }
    
    public List<Karyawan> getSearchKaryawan(String text) {
        Session sess = bukaSession();
        List<Karyawan> temp = sess.createQuery("SELECT k FROM Karyawan k WHERE NamaKaryawan LIKE '%" + text+ "%'").list();
        sess.close();
        return temp;
    }
    
    public List<Karyawan> getAllKurir() {
        Session sess = bukaSession();
        List<Karyawan> temp = sess.createQuery("SELECT k FROM Karyawan k WHERE IdJabatan = 3").list();
        sess.close();
        return temp;
    }
    
    public List<Karyawan> getAllStaff() {
        Session sess = bukaSession();
        List<Karyawan> temp = sess.createQuery("SELECT k FROM Karyawan k WHERE IdJabatan = 2").list();
        sess.close();
        return temp;
    }
    
    public List<Jabatan> getAllJabatan() {
        Session sess = bukaSession();
        List<Jabatan> temp = sess.createQuery("SELECT j FROM Jabatan j").list();
        sess.close();
        return temp;
    }

    public boolean simpanAtauUpdateOneKaryawan(Karyawan param) {
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

    public boolean deleteOneKaryawan(Karyawan param) {
        try {
            Session sess = bukaSession();
            Transaction t = sess.beginTransaction();
            param = (Karyawan) sess.load(Karyawan.class, param.getIdKaryawan());
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
