/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author vr
 */
@Entity
@Table(name = "transaksi")
@NamedQueries({
    @NamedQuery(name = "Transaksi.findAll", query = "SELECT t FROM Transaksi t")})
public class Transaksi implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IdTransaksi")
    private Integer idTransaksi;
    @Basic(optional = false)
    @Column(name = "TanggalTerima")
    @Temporal(TemporalType.DATE)
    private Date tanggalTerima;
    @Basic(optional = false)
    @Column(name = "TanggalSelesai")
    @Temporal(TemporalType.DATE)
    private Date tanggalSelesai;
    @Basic(optional = false)
    @Column(name = "Berat")
    private int berat;
    @Lob
    @Column(name = "Notes")
    private String notes;
    @Basic(optional = false)
    @Column(name = "Total")
    private double total;
    @Basic(optional = false)
    @Column(name = "StatusLaundry")
    private int statusLaundry;
    @Column(name = "StatusPengantaran")
    private Integer statusPengantaran;
    @Column(name = "IdPengantar")
    private Integer idPengantar;
    @Basic(optional = false)
    @Column(name = "StatusTransaksi")
    private int statusTransaksi;
    @Column(name = "TanggalAntar")
    @Temporal(TemporalType.DATE)
    private Date tanggalAntar;
    @JoinColumn(name = "IdPelanggan", referencedColumnName = "IdPelanggan")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Pelanggan idPelanggan;
    @JoinColumn(name = "IdKaryawan", referencedColumnName = "IdKaryawan")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Karyawan idKaryawan;
    @JoinColumn(name = "IdLayanan", referencedColumnName = "IdLayanan")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Jenislayanan idLayanan;

    public Transaksi() {
    }

    public Transaksi(Integer idTransaksi) {
        this.idTransaksi = idTransaksi;
    }

    public Transaksi(Integer idTransaksi, Date tanggalTerima, Date tanggalSelesai, int berat, double total, int statusLaundry, int statusTransaksi) {
        this.idTransaksi = idTransaksi;
        this.tanggalTerima = tanggalTerima;
        this.tanggalSelesai = tanggalSelesai;
        this.berat = berat;
        this.total = total;
        this.statusLaundry = statusLaundry;
        this.statusTransaksi = statusTransaksi;
    }

    public Integer getIdTransaksi() {
        return idTransaksi;
    }

    public void setIdTransaksi(Integer idTransaksi) {
        this.idTransaksi = idTransaksi;
    }

    public Date getTanggalTerima() {
        return tanggalTerima;
    }

    public void setTanggalTerima(Date tanggalTerima) {
        this.tanggalTerima = tanggalTerima;
    }

    public Date getTanggalSelesai() {
        return tanggalSelesai;
    }

    public void setTanggalSelesai(Date tanggalSelesai) {
        this.tanggalSelesai = tanggalSelesai;
    }

    public int getBerat() {
        return berat;
    }

    public void setBerat(int berat) {
        this.berat = berat;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public int getStatusLaundry() {
        return statusLaundry;
    }

    public void setStatusLaundry(int statusLaundry) {
        this.statusLaundry = statusLaundry;
    }

    public Integer getStatusPengantaran() {
        return statusPengantaran;
    }

    public void setStatusPengantaran(Integer statusPengantaran) {
        this.statusPengantaran = statusPengantaran;
    }

    public Integer getIdPengantar() {
        return idPengantar;
    }

    public void setIdPengantar(Integer idPengantar) {
        this.idPengantar = idPengantar;
    }

    public int getStatusTransaksi() {
        return statusTransaksi;
    }

    public void setStatusTransaksi(int statusTransaksi) {
        this.statusTransaksi = statusTransaksi;
    }

    public Date getTanggalAntar() {
        return tanggalAntar;
    }

    public void setTanggalAntar(Date tanggalAntar) {
        this.tanggalAntar = tanggalAntar;
    }

    public Pelanggan getIdPelanggan() {
        return idPelanggan;
    }

    public void setIdPelanggan(Pelanggan idPelanggan) {
        this.idPelanggan = idPelanggan;
    }

    public Karyawan getIdKaryawan() {
        return idKaryawan;
    }

    public void setIdKaryawan(Karyawan idKaryawan) {
        this.idKaryawan = idKaryawan;
    }

    public Jenislayanan getIdLayanan() {
        return idLayanan;
    }

    public void setIdLayanan(Jenislayanan idLayanan) {
        this.idLayanan = idLayanan;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTransaksi != null ? idTransaksi.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Transaksi)) {
            return false;
        }
        Transaksi other = (Transaksi) object;
        if ((this.idTransaksi == null && other.idTransaksi != null) || (this.idTransaksi != null && !this.idTransaksi.equals(other.idTransaksi))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Transaksi[ idTransaksi=" + idTransaksi + " ]";
    }
    
}
