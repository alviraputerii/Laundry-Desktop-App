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
public class TransaksiControl {

    SessionFactory session = HibernateUtil.getSessionFactory();

    private Session bukaSession() {
        return session.openSession();
    }

    public List<Transaksi> getAllTransaksi() {
        Session sess = bukaSession();
        List<Transaksi> temp = sess.createQuery("SELECT t FROM Transaksi t").list();
        sess.close();
        return temp;
    }

    public List<Pelanggan> getAllPelanggan() {
        Session sess = bukaSession();
        List<Pelanggan> temp = sess.createQuery("SELECT p FROM Pelanggan p").list();
        sess.close();
        return temp;
    }

    public List<Karyawan> getAllKaryawan() {
        Session sess = bukaSession();
        List<Karyawan> temp = sess.createQuery("SELECT k FROM Karyawan k").list();
        sess.close();
        return temp;
    }

    public List<Jenislayanan> getAllLayanan() {
        Session sess = bukaSession();
        List<Jenislayanan> temp = sess.createQuery("SELECT l FROM Jenislayanan l").list();
        sess.close();
        return temp;
    }

    public boolean simpanAtauUpdateOneTransaksi(Transaksi param) {
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

    public boolean deleteOneTransaksi(Transaksi param) {
        try {
            Session sess = bukaSession();
            Transaction t = sess.beginTransaction();
            param = (Transaksi) sess.load(Transaksi.class, param.getIdTransaksi());
            sess.delete(param);
            t.commit();
            sess.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public String[] getStatusLaundry() {
        String[] cb = new String[3];
        cb[0] = "Belum Selesai";
        cb[1] = "Selesai";
        cb[2] = "Sudah Diambil";
        return cb;
    }
    
    public String[] getStatusPengantaran() {
        String[] cb = new String[2];
        cb[0] = "Belum Diantar";
        cb[1] = "Sudah Diantar";
        return cb;
    }
    
    public String[] getStatusTransaksi() {
        String[] cb = new String[2];
        cb[0] = "Belum Lunas";
        cb[1] = "Lunas";
        return cb;
    }
}
