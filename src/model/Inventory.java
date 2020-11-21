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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author vr
 */
@Entity
@Table(name = "inventory")
@NamedQueries({
    @NamedQuery(name = "Inventory.findAll", query = "SELECT i FROM Inventory i")})
public class Inventory implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IdInventory")
    private Integer idInventory;
    @Basic(optional = false)
    @Column(name = "NamaInventory")
    private String namaInventory;
    @Basic(optional = false)
    @Column(name = "Stock")
    private int stock;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idInventory", fetch = FetchType.LAZY)
    private List<Jenislayanan> jenislayananList;

    public Inventory() {
    }

    public Inventory(Integer idInventory) {
        this.idInventory = idInventory;
    }

    public Inventory(Integer idInventory, String namaInventory, int stock) {
        this.idInventory = idInventory;
        this.namaInventory = namaInventory;
        this.stock = stock;
    }

    public Integer getIdInventory() {
        return idInventory;
    }

    public void setIdInventory(Integer idInventory) {
        this.idInventory = idInventory;
    }

    public String getNamaInventory() {
        return namaInventory;
    }

    public void setNamaInventory(String namaInventory) {
        this.namaInventory = namaInventory;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public List<Jenislayanan> getJenislayananList() {
        return jenislayananList;
    }

    public void setJenislayananList(List<Jenislayanan> jenislayananList) {
        this.jenislayananList = jenislayananList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idInventory != null ? idInventory.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Inventory)) {
            return false;
        }
        Inventory other = (Inventory) object;
        if ((this.idInventory == null && other.idInventory != null) || (this.idInventory != null && !this.idInventory.equals(other.idInventory))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return namaInventory;
    }
    
}
