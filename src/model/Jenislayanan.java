/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author vr
 */
@Entity
@Table(name = "jenislayanan")
@NamedQueries({
    @NamedQuery(name = "Jenislayanan.findAll", query = "SELECT j FROM Jenislayanan j")})
public class Jenislayanan implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "IdLayanan")
    private String idLayanan;
    @Basic(optional = false)
    @Column(name = "NamaLayanan")
    private String namaLayanan;
    @Basic(optional = false)
    @Column(name = "Harga")
    private double harga;
    @Basic(optional = false)
    @Column(name = "StockDipakai")
    private int stockDipakai;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idLayanan", fetch = FetchType.LAZY)
    private List<Transaksi> transaksiList;
    @JoinColumn(name = "IdInventory", referencedColumnName = "IdInventory")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Inventory idInventory;

    public Jenislayanan() {
    }

    public Jenislayanan(String idLayanan) {
        this.idLayanan = idLayanan;
    }

    public Jenislayanan(String idLayanan, String namaLayanan, double harga, int stockDipakai) {
        this.idLayanan = idLayanan;
        this.namaLayanan = namaLayanan;
        this.harga = harga;
        this.stockDipakai = stockDipakai;
    }

    public String getIdLayanan() {
        return idLayanan;
    }

    public void setIdLayanan(String idLayanan) {
        this.idLayanan = idLayanan;
    }

    public String getNamaLayanan() {
        return namaLayanan;
    }

    public void setNamaLayanan(String namaLayanan) {
        this.namaLayanan = namaLayanan;
    }

    public double getHarga() {
        return harga;
    }

    public void setHarga(double harga) {
        this.harga = harga;
    }

    public int getStockDipakai() {
        return stockDipakai;
    }

    public void setStockDipakai(int stockDipakai) {
        this.stockDipakai = stockDipakai;
    }

    public List<Transaksi> getTransaksiList() {
        return transaksiList;
    }

    public void setTransaksiList(List<Transaksi> transaksiList) {
        this.transaksiList = transaksiList;
    }

    public Inventory getIdInventory() {
        return idInventory;
    }

    public void setIdInventory(Inventory idInventory) {
        this.idInventory = idInventory;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idLayanan != null ? idLayanan.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Jenislayanan)) {
            return false;
        }
        Jenislayanan other = (Jenislayanan) object;
        if ((this.idLayanan == null && other.idLayanan != null) || (this.idLayanan != null && !this.idLayanan.equals(other.idLayanan))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return idLayanan;
    }
    
}
