/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import com.sun.glass.events.KeyEvent;
import control.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.*;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import javax.swing.JDialog;
import javax.swing.Timer;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author vr
 */
public class MainApps extends JFrame implements ListSelectionListener, ItemListener {

    private InventoryControl inventoryControl;
    private JabatanControl jabatanControl;
    private KaryawanControl karyawanControl;
    private LayananControl layananControl;
    private PelangganControl pelangganControl;
    private TransaksiControl transaksiControl;

    private DefaultTableModel dtmInventory;
    private DefaultTableModel dtmJabatan;
    private DefaultTableModel dtmKaryawan;
    private DefaultTableModel dtmLayanan;
    private DefaultTableModel dtmPelanggan;
    private DefaultTableModel dtmCariPelanggan;
    private DefaultTableModel dtmTransaksi;

    private Inventory inventory;
    private Inventory inventorySelected;
    private Jabatan jabatan;
    private Jabatan jabatanSelected;
    private Karyawan karyawan;
    private Karyawan karyawanSelected;
    private Jenislayanan jenislayanan;
    private Jenislayanan jenislayananSelected;
    private Pelanggan pelanggan;
    private Pelanggan pelangganSelected;
    private Pelanggan cariPelangganSelected;
    private Transaksi transaksi;
    private Transaksi transaksiSelected;

    private DefaultComboBoxModel cbmStatusMember;
    private DefaultComboBoxModel cbmStatusLaundry;
    private DefaultComboBoxModel cbmStatusPengantaran;
    private DefaultComboBoxModel cbmStatusTransaksi;

    int row = -1;
    String member = "";
    String member1 = "";
    String slaundry = "";
    String santar = "";
    String stransaksi = "";
    int aidipelanggan = 0;
    int days = 0;
    String pengantar = "";
    Boolean cek = false;
    Boolean admin = false;
    String log = "";
    String job = "";
    int idJob = 0;

    double berat;
    double hargaLayanan;
    double jumlahLayanan;
    double jumlahBiayaAntar;
    String total;
    JDialog d;
    JDialog lunas;
    JDialog belumLunas;

    DateFormat df;
    Date dateobj;
    Date dateLunas;
    Date dateBelumLunas;

    public MainApps() {

        inventoryControl = new InventoryControl();
        jabatanControl = new JabatanControl();
        karyawanControl = new KaryawanControl();
        layananControl = new LayananControl();
        pelangganControl = new PelangganControl();
        transaksiControl = new TransaksiControl();

        initComponents();
        this.add(login);
        this.add(home);
        this.add(mdPelanggan);
        this.add(mdKaryawan);
        this.add(mdJabatan);
        this.add(mdInventory);
        this.add(mdLayanan);
        this.add(dtTransaksi);
        this.add(about);

        jMenuBar1.setVisible(false);
        login.setVisible(true);
        home.setVisible(false);
        mdPelanggan.setVisible(false);
        mdKaryawan.setVisible(false);
        mdJabatan.setVisible(false);
        mdInventory.setVisible(false);
        mdLayanan.setVisible(false);
        dtTransaksi.setVisible(false);
        about.setVisible(false);

        resetPelanggan();
        resetKaryawan();
        resetJabatan();
        resetInventory();
        resetLayanan();
        resetTransaksi();

        dtmInventory = (DefaultTableModel) tableDataInventory.getModel();
        dtmJabatan = (DefaultTableModel) tableDataJabatan.getModel();
        dtmKaryawan = (DefaultTableModel) tableDataEmp.getModel();
        dtmLayanan = (DefaultTableModel) tableDataLayanan.getModel();
        dtmPelanggan = (DefaultTableModel) tableDataCust.getModel();
        dtmCariPelanggan = (DefaultTableModel) tableDataCariPelanggan.getModel();
        dtmTransaksi = (DefaultTableModel) tableDataTransaksi.getModel();

        df = new SimpleDateFormat("dd/MMM/yyyy");
        dateobj = new Date();

        setDate();

    }

    public void setDate() {
        ActionListener actiondate = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Date mydate = new Date();
                txtcurrdate.setText(df.format(dateobj) + " - " + mydate.getHours() + " : " + mydate.getMinutes() + " : " + mydate.getSeconds());
                txtdate.setText(df.format(dateobj) + " - " + mydate.getHours() + " : " + mydate.getMinutes() + " : " + mydate.getSeconds());
                txtHariIni.setText(df.format(dateobj) + " - " + mydate.getHours() + " : " + mydate.getMinutes() + " : " + mydate.getSeconds());
                if (transaksiSelected == null) {
                    dateTerima.setDate(mydate);
                }
            }
        };
        new Timer(10, actiondate).start();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        login = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtUsername = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtPassword = new javax.swing.JPasswordField();
        btnLogin = new javax.swing.JButton();
        jLabel24 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        home = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        txtjabatan = new javax.swing.JLabel();
        txtHariIni = new javax.swing.JLabel();
        mdPelanggan = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtIdCust = new javax.swing.JLabel();
        txtNoHpCust = new javax.swing.JTextField();
        jScrollPane4 = new javax.swing.JScrollPane();
        txtAlamatCust = new javax.swing.JTextArea();
        txtNamaCust = new javax.swing.JTextField();
        jPanel10 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        cbMembership = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        dateMember = new com.toedter.calendar.JDateChooser();
        txtDurasi = new javax.swing.JLabel();
        btnSimpanCust = new javax.swing.JButton();
        btnResetCust = new javax.swing.JButton();
        btnHapusCust = new javax.swing.JButton();
        txtdate = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        btnCariPelanggan = new javax.swing.JButton();
        txtCariPelanggan = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableDataCust = new javax.swing.JTable();
        btnResetPelanggan = new javax.swing.JButton();
        mdKaryawan = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jPanel17 = new javax.swing.JPanel();
        jPanel18 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        txtNamaEmp = new javax.swing.JTextField();
        txtIdEmp = new javax.swing.JLabel();
        txtNoHpEmp = new javax.swing.JTextField();
        jScrollPane11 = new javax.swing.JScrollPane();
        txtAlamatEmp = new javax.swing.JTextArea();
        jPanel19 = new javax.swing.JPanel();
        jLabel50 = new javax.swing.JLabel();
        jLabel69 = new javax.swing.JLabel();
        txtUnameEmp = new javax.swing.JTextField();
        txtPassEmp = new javax.swing.JTextField();
        jLabel71 = new javax.swing.JLabel();
        txtConfirmPass = new javax.swing.JTextField();
        btnSimpanEmp = new javax.swing.JButton();
        btnResetEmp = new javax.swing.JButton();
        btnHapusEmp = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        cbJabatan = new javax.swing.JComboBox<>();
        jPanel9 = new javax.swing.JPanel();
        jScrollPane12 = new javax.swing.JScrollPane();
        tableDataEmp = new javax.swing.JTable();
        btnCariKaryawan = new javax.swing.JButton();
        txtCariKaryawan = new javax.swing.JTextField();
        btnResetKaryawan = new javax.swing.JButton();
        mdJabatan = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jPanel15 = new javax.swing.JPanel();
        jPanel16 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        jLabel70 = new javax.swing.JLabel();
        jLabel72 = new javax.swing.JLabel();
        txtNamaJabatan = new javax.swing.JTextField();
        txtIdJabatan = new javax.swing.JLabel();
        jScrollPane13 = new javax.swing.JScrollPane();
        txtDeskripsi = new javax.swing.JTextArea();
        btnSimpanJabatan = new javax.swing.JButton();
        btnResetJabatan = new javax.swing.JButton();
        btnHapusJabatan = new javax.swing.JButton();
        jPanel22 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableDataJabatan = new javax.swing.JTable();
        btnCariJabatan = new javax.swing.JButton();
        txtCariJabatan = new javax.swing.JTextField();
        btnResetJob = new javax.swing.JButton();
        mdInventory = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        jLabel74 = new javax.swing.JLabel();
        jLabel75 = new javax.swing.JLabel();
        jLabel76 = new javax.swing.JLabel();
        txtNamaInv = new javax.swing.JTextField();
        txtIdInv = new javax.swing.JLabel();
        spStock = new javax.swing.JSpinner();
        btnSimpanInv = new javax.swing.JButton();
        btnResetInv = new javax.swing.JButton();
        btnHapusInv = new javax.swing.JButton();
        jPanel24 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tableDataInventory = new javax.swing.JTable();
        btnCariInventory = new javax.swing.JButton();
        txtCariInventory = new javax.swing.JTextField();
        btnResetInventory = new javax.swing.JButton();
        mdLayanan = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jPanel20 = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        txtNamaaLayanan = new javax.swing.JTextField();
        txtHargaLayanan = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        txtIdLayanan = new javax.swing.JTextField();
        jPanel21 = new javax.swing.JPanel();
        jLabel25 = new javax.swing.JLabel();
        cbInventory = new javax.swing.JComboBox<>();
        jLabel26 = new javax.swing.JLabel();
        spStockDipakai = new javax.swing.JSpinner();
        jLabel23 = new javax.swing.JLabel();
        btnSimpanLayanan = new javax.swing.JButton();
        btnResetLayanan = new javax.swing.JButton();
        btnHapusLayanan = new javax.swing.JButton();
        jPanel23 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        tableDataLayanan = new javax.swing.JTable();
        txtCariLayanan = new javax.swing.JTextField();
        btnCariLayanan = new javax.swing.JButton();
        btnResetServ = new javax.swing.JButton();
        about = new javax.swing.JPanel();
        jLabel68 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        dtTransaksi = new javax.swing.JPanel();
        txtcurrdate = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jLabel37 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        label2 = new javax.swing.JLabel();
        label1 = new javax.swing.JLabel();
        jScrollPane9 = new javax.swing.JScrollPane();
        tableDataTransaksi = new javax.swing.JTable();
        cbAntar = new javax.swing.JCheckBox();
        cbStatusPengantaran = new javax.swing.JComboBox<>();
        label3 = new javax.swing.JLabel();
        cbPengantar = new javax.swing.JComboBox<>();
        jLabel46 = new javax.swing.JLabel();
        cbStatusLaundry = new javax.swing.JComboBox<>();
        jLabel29 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        txtNotes = new javax.swing.JTextArea();
        dateAntar = new com.toedter.calendar.JDateChooser();
        txtIdTransaksi = new javax.swing.JLabel();
        dateSelesai = new com.toedter.calendar.JDateChooser();
        dateTerima = new com.toedter.calendar.JDateChooser();
        jPanel13 = new javax.swing.JPanel();
        btnSimpanTransaksi = new javax.swing.JButton();
        btnResetTransaksi = new javax.swing.JButton();
        btnHapusTransaksi = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jLabel36 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        txtBerat = new javax.swing.JTextField();
        txtDeskLayanan = new javax.swing.JLabel();
        txtStatusMember = new javax.swing.JLabel();
        cbPelanggan = new javax.swing.JComboBox<>();
        cbKaryawan = new javax.swing.JComboBox<>();
        cbLayanan = new javax.swing.JComboBox<>();
        btnTambah = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        jLabel59 = new javax.swing.JLabel();
        jLabel60 = new javax.swing.JLabel();
        jLabel61 = new javax.swing.JLabel();
        jLabel63 = new javax.swing.JLabel();
        cbStatusTransaksi = new javax.swing.JComboBox<>();
        txtJumlahHarga = new javax.swing.JLabel();
        txtJumlahBiayaAntar = new javax.swing.JLabel();
        jlabel = new javax.swing.JLabel();
        jLabel56 = new javax.swing.JLabel();
        txthargalayanan = new javax.swing.JLabel();
        jLabel62 = new javax.swing.JLabel();
        jLabel77 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        txtTotal = new javax.swing.JLabel();
        btnInvoice = new javax.swing.JButton();
        cariPelanggan = new javax.swing.JPanel();
        txtCariCust = new javax.swing.JTextField();
        btnCari = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jScrollPane7 = new javax.swing.JScrollPane();
        tableDataCariPelanggan = new javax.swing.JTable();
        jButton3 = new javax.swing.JButton();
        buttonGroup1 = new javax.swing.ButtonGroup();
        panelBelumLunas = new javax.swing.JPanel();
        pilihTanggal1 = new javax.swing.JRadioButton();
        pilihBulan1 = new javax.swing.JRadioButton();
        jRadioButton4 = new javax.swing.JRadioButton();
        okBelumLunas = new javax.swing.JButton();
        datePilihTanggal1 = new com.toedter.calendar.JDateChooser();
        txtPilihTanggal1 = new javax.swing.JLabel();
        txtPilihBulan1 = new javax.swing.JLabel();
        datePilihBulan1 = new com.toedter.calendar.JMonthChooser();
        buttonGroup2 = new javax.swing.ButtonGroup();
        panelLunas = new javax.swing.JPanel();
        pilihTanggal = new javax.swing.JRadioButton();
        pilihBulan = new javax.swing.JRadioButton();
        jRadioButton5 = new javax.swing.JRadioButton();
        okBelumLunas1 = new javax.swing.JButton();
        datePilihTanggal = new com.toedter.calendar.JDateChooser();
        txtPilihTanggal = new javax.swing.JLabel();
        txtPilihBulan = new javax.swing.JLabel();
        datePilihBulan = new com.toedter.calendar.JMonthChooser();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        homeMenu = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        logOut = new javax.swing.JMenuItem();
        aboutMenu = new javax.swing.JMenuItem();
        exitMenu = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        custMenu = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        empMenu = new javax.swing.JMenuItem();
        jobMenu = new javax.swing.JMenuItem();
        jSeparator3 = new javax.swing.JPopupMenu.Separator();
        invMenu = new javax.swing.JMenuItem();
        servMenu = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        laundryMenu = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        menuLunas = new javax.swing.JMenuItem();
        menuBelumLunas = new javax.swing.JMenuItem();
        menuInventory = new javax.swing.JMenuItem();
        menuPelanggan = new javax.swing.JMenuItem();

        login.setBackground(new java.awt.Color(233, 235, 242));
        login.setLayout(new java.awt.GridBagLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setText("Username");
        jLabel1.setPreferredSize(new java.awt.Dimension(80, 30));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.ipadx = 2;
        gridBagConstraints.ipady = 8;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(42, 423, 0, 0);
        login.add(jLabel1, gridBagConstraints);

        txtUsername.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtUsername.setPreferredSize(new java.awt.Dimension(150, 30));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.ipadx = 113;
        gridBagConstraints.ipady = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(42, 43, 0, 0);
        login.add(txtUsername, gridBagConstraints);

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setText("Password");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(20, 429, 0, 0);
        login.add(jLabel2, gridBagConstraints);

        txtPassword.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtPassword.setPreferredSize(new java.awt.Dimension(150, 30));
        txtPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPasswordActionPerformed(evt);
            }
        });
        txtPassword.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtPasswordKeyPressed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 113;
        gridBagConstraints.ipady = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(16, 43, 0, 0);
        login.add(txtPassword, gridBagConstraints);

        btnLogin.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnLogin.setText("Login");
        btnLogin.setPreferredSize(new java.awt.Dimension(200, 30));
        btnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.ipadx = 188;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(34, 429, 378, 0);
        login.add(btnLogin, gridBagConstraints);

        jLabel24.setFont(new java.awt.Font("Tahoma", 0, 45)); // NOI18N
        jLabel24.setText("WELCOME BACK");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(251, 450, 0, 0);
        login.add(jLabel24, gridBagConstraints);

        jLabel30.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel30.setText("PLEASE LOG ON TO USE THE APPLICATION");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(9, 376, 0, 390);
        login.add(jLabel30, gridBagConstraints);

        home.setBackground(new java.awt.Color(233, 235, 242));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/logo2.png"))); // NOI18N

        jLabel31.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel31.setText("Log In sebagai :");

        txtjabatan.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        txtjabatan.setText("jLabel32");

        txtHariIni.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        txtHariIni.setText("jLabel32");

        javax.swing.GroupLayout homeLayout = new javax.swing.GroupLayout(home);
        home.setLayout(homeLayout);
        homeLayout.setHorizontalGroup(
            homeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, homeLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel31)
                .addGap(32, 32, 32)
                .addComponent(txtjabatan)
                .addGap(225, 225, 225))
            .addGroup(homeLayout.createSequentialGroup()
                .addGroup(homeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(homeLayout.createSequentialGroup()
                        .addGap(209, 209, 209)
                        .addComponent(jLabel5))
                    .addGroup(homeLayout.createSequentialGroup()
                        .addGap(794, 794, 794)
                        .addComponent(txtHariIni)))
                .addContainerGap(233, Short.MAX_VALUE))
        );
        homeLayout.setVerticalGroup(
            homeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(homeLayout.createSequentialGroup()
                .addGap(58, 58, 58)
                .addGroup(homeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel31)
                    .addComponent(txtjabatan))
                .addGap(38, 38, 38)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtHariIni)
                .addContainerGap(440, Short.MAX_VALUE))
        );

        mdPelanggan.setBackground(new java.awt.Color(233, 235, 242));
        mdPelanggan.setLayout(new java.awt.BorderLayout());

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/customer.png"))); // NOI18N
        jLabel4.setText("Master Data Pelanggan");
        mdPelanggan.add(jLabel4, java.awt.BorderLayout.PAGE_START);

        jPanel1.setBackground(new java.awt.Color(233, 235, 242));
        jPanel1.setMinimumSize(new java.awt.Dimension(300, 100));

        jPanel7.setBackground(new java.awt.Color(233, 235, 242));
        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Data Pelanggan", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 18))); // NOI18N

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel3.setText("ID Pelanggan");
        jLabel3.setPreferredSize(new java.awt.Dimension(110, 30));

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel6.setText("Nama Pelanggan ");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel7.setText("No. Telpon");

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel8.setText("Alamat");

        txtIdCust.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtIdCust.setText("1");

        txtNoHpCust.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        txtAlamatCust.setColumns(20);
        txtAlamatCust.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtAlamatCust.setRows(5);
        jScrollPane4.setViewportView(txtAlamatCust);

        txtNamaCust.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtNamaCust.setPreferredSize(new java.awt.Dimension(200, 30));

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(43, 43, 43)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtNoHpCust, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtNamaCust, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 227, Short.MAX_VALUE)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(txtIdCust, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel7Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel3, jLabel6, jLabel7, jLabel8});

        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtIdCust))
                .addGap(16, 16, 16)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtNamaCust, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtNoHpCust, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(27, Short.MAX_VALUE))
        );

        jPanel7Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jLabel3, jLabel6, jLabel7, jLabel8});

        jPanel7Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {txtIdCust, txtNamaCust, txtNoHpCust});

        jPanel10.setBackground(new java.awt.Color(233, 235, 242));
        jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Data Membership", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 18))); // NOI18N

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel10.setText("Status Membership");
        jLabel10.setPreferredSize(new java.awt.Dimension(150, 30));

        cbMembership.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        cbMembership.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pilih Status Membership", "Aktif", "Tidak Aktif" }));
        cbMembership.setPreferredSize(new java.awt.Dimension(165, 30));

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel11.setText("Tanggal Jadi Member");

        dateMember.setDateFormatString("MMM d, yyyy ");
        dateMember.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        txtDurasi.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtDurasi.setText("jLabel9");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbMembership, 0, 227, Short.MAX_VALUE)
                            .addComponent(dateMember, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(txtDurasi))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel10Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel10, jLabel11});

        jPanel10Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {cbMembership, dateMember});

        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbMembership, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel11)
                    .addComponent(dateMember, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtDurasi)
                .addContainerGap(42, Short.MAX_VALUE))
        );

        jPanel10Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {cbMembership, dateMember, jLabel10, jLabel11});

        btnSimpanCust.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnSimpanCust.setText("Simpan");
        btnSimpanCust.setPreferredSize(new java.awt.Dimension(120, 30));
        btnSimpanCust.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimpanCustActionPerformed(evt);
            }
        });

        btnResetCust.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnResetCust.setText("Reset");
        btnResetCust.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetCustActionPerformed(evt);
            }
        });

        btnHapusCust.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnHapusCust.setText("Hapus");
        btnHapusCust.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHapusCustActionPerformed(evt);
            }
        });

        txtdate.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        txtdate.setText("jLabel24");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                .addComponent(jPanel10, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 0, Short.MAX_VALUE)
                .addComponent(jPanel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(138, 138, 138)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnHapusCust, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnResetCust)
                    .addComponent(btnSimpanCust, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(73, 73, 73)
                .addComponent(txtdate))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnHapusCust, btnResetCust, btnSimpanCust});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtdate)
                .addGap(40, 40, 40)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSimpanCust, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnResetCust)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnHapusCust)
                .addContainerGap(42, Short.MAX_VALUE))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnHapusCust, btnResetCust, btnSimpanCust});

        mdPelanggan.add(jPanel1, java.awt.BorderLayout.LINE_START);

        jPanel5.setBackground(new java.awt.Color(233, 235, 242));

        btnCariPelanggan.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnCariPelanggan.setText("Cari");
        btnCariPelanggan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCariPelangganActionPerformed(evt);
            }
        });

        txtCariPelanggan.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        tableDataCust.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        tableDataCust.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nama Pelanggan", "No. Telp", "Alamat", "Status Membership", "Tgl Jadi Member"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableDataCust.setFillsViewportHeight(true);
        tableDataCust.setName(""); // NOI18N
        tableDataCust.setRowHeight(30);
        tableDataCust.setRowMargin(5);
        tableDataCust.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tableDataCust.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tableDataCust);
        if (tableDataCust.getColumnModel().getColumnCount() > 0) {
            tableDataCust.getColumnModel().getColumn(3).setResizable(false);
            tableDataCust.getColumnModel().getColumn(3).setPreferredWidth(300);
        }

        btnResetPelanggan.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnResetPelanggan.setText("Reset");
        btnResetPelanggan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetPelangganActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 482, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtCariPelanggan, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnCariPelanggan)
                .addGap(3, 3, 3)
                .addComponent(btnResetPelanggan)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCariPelanggan)
                    .addComponent(txtCariPelanggan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnResetPelanggan))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 800, Short.MAX_VALUE)
                .addContainerGap())
        );

        mdPelanggan.add(jPanel5, java.awt.BorderLayout.CENTER);

        mdKaryawan.setBackground(new java.awt.Color(233, 235, 242));
        mdKaryawan.setLayout(new java.awt.BorderLayout());

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/employee.png"))); // NOI18N
        jLabel13.setText("Master Data Karyawan");
        mdKaryawan.add(jLabel13, java.awt.BorderLayout.PAGE_START);

        jPanel17.setBackground(new java.awt.Color(233, 235, 242));
        jPanel17.setMinimumSize(new java.awt.Dimension(300, 100));

        jPanel18.setBackground(new java.awt.Color(233, 235, 242));
        jPanel18.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Data Karyawan", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 18))); // NOI18N

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel14.setText("ID Karyawan");
        jLabel14.setPreferredSize(new java.awt.Dimension(110, 30));

        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel16.setText("Nama ");

        jLabel47.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel47.setText("No. Telpon");

        jLabel48.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel48.setText("Alamat");

        txtNamaEmp.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtNamaEmp.setPreferredSize(new java.awt.Dimension(200, 30));

        txtIdEmp.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtIdEmp.setText("1");

        txtNoHpEmp.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        txtAlamatEmp.setColumns(20);
        txtAlamatEmp.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtAlamatEmp.setRows(5);
        jScrollPane11.setViewportView(txtAlamatEmp);

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel16)
                    .addComponent(jLabel47)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel48))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNoHpEmp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNamaEmp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtIdEmp, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel18Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jScrollPane11, txtIdEmp, txtNamaEmp, txtNoHpEmp});

        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtIdEmp))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNamaEmp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNoHpEmp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel47))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addComponent(jLabel48)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane11, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel18Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {txtIdEmp, txtNamaEmp, txtNoHpEmp});

        jPanel19.setBackground(new java.awt.Color(233, 235, 242));
        jPanel19.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Data Log In", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 18))); // NOI18N

        jLabel50.setBackground(new java.awt.Color(233, 235, 242));
        jLabel50.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel50.setText("Username");
        jLabel50.setPreferredSize(new java.awt.Dimension(150, 30));

        jLabel69.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel69.setText("Password");

        txtUnameEmp.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtUnameEmp.setPreferredSize(new java.awt.Dimension(150, 32));

        txtPassEmp.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtPassEmp.setPreferredSize(new java.awt.Dimension(150, 30));

        jLabel71.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel71.setText("Confirm Password");

        txtConfirmPass.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtConfirmPass.setPreferredSize(new java.awt.Dimension(150, 30));

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel50, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel69)
                    .addComponent(jLabel71))
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(txtUnameEmp, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel19Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtPassEmp, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtConfirmPass, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );

        jPanel19Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel50, jLabel69, jLabel71});

        jPanel19Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {txtConfirmPass, txtPassEmp, txtUnameEmp});

        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel50, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtUnameEmp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel69)
                    .addComponent(txtPassEmp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel71)
                    .addComponent(txtConfirmPass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(26, Short.MAX_VALUE))
        );

        jPanel19Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jLabel50, jLabel69});

        jPanel19Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {txtConfirmPass, txtPassEmp, txtUnameEmp});

        btnSimpanEmp.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnSimpanEmp.setText("Simpan");
        btnSimpanEmp.setPreferredSize(new java.awt.Dimension(120, 30));
        btnSimpanEmp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimpanEmpActionPerformed(evt);
            }
        });

        btnResetEmp.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnResetEmp.setText("Reset");
        btnResetEmp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetEmpActionPerformed(evt);
            }
        });

        btnHapusEmp.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnHapusEmp.setText("Hapus");
        btnHapusEmp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHapusEmpActionPerformed(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(233, 235, 242));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Detail pekerjaan", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 18))); // NOI18N

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel12.setText("Jabatan");

        cbJabatan.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        cbJabatan.setPreferredSize(new java.awt.Dimension(150, 30));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(cbJabatan, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(cbJabatan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(25, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jPanel18, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel19, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addGap(144, 144, 144)
                        .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnHapusEmp)
                            .addComponent(btnResetEmp)
                            .addComponent(btnSimpanEmp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel17Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnHapusEmp, btnResetEmp, btnSimpanEmp});

        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnSimpanEmp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnResetEmp)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnHapusEmp)
                .addContainerGap(72, Short.MAX_VALUE))
        );

        jPanel17Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnHapusEmp, btnResetEmp, btnSimpanEmp});

        mdKaryawan.add(jPanel17, java.awt.BorderLayout.LINE_START);

        jPanel9.setBackground(new java.awt.Color(233, 235, 242));

        tableDataEmp.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        tableDataEmp.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID ", "Nama Karyawan", "No. Telp", "Alamat", "Username", "Password", "Jabatan"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableDataEmp.setFillsViewportHeight(true);
        tableDataEmp.setRowHeight(30);
        tableDataEmp.setRowMargin(5);
        tableDataEmp.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tableDataEmp.getTableHeader().setReorderingAllowed(false);
        jScrollPane12.setViewportView(tableDataEmp);

        btnCariKaryawan.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnCariKaryawan.setText("Cari");
        btnCariKaryawan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCariKaryawanActionPerformed(evt);
            }
        });

        txtCariKaryawan.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        btnResetKaryawan.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnResetKaryawan.setText("Reset");
        btnResetKaryawan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetKaryawanActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane12, javax.swing.GroupLayout.DEFAULT_SIZE, 615, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtCariKaryawan, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnCariKaryawan)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnResetKaryawan)
                .addGap(9, 9, 9))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCariKaryawan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCariKaryawan)
                    .addComponent(btnResetKaryawan))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane12, javax.swing.GroupLayout.DEFAULT_SIZE, 890, Short.MAX_VALUE))
        );

        mdKaryawan.add(jPanel9, java.awt.BorderLayout.CENTER);

        mdJabatan.setBackground(new java.awt.Color(233, 235, 242));
        mdJabatan.setLayout(new java.awt.BorderLayout());

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/jabatan.png"))); // NOI18N
        jLabel17.setText("Master Data Jabatan");
        mdJabatan.add(jLabel17, java.awt.BorderLayout.PAGE_START);

        jPanel15.setBackground(new java.awt.Color(233, 235, 242));
        jPanel15.setMinimumSize(new java.awt.Dimension(300, 100));

        jPanel16.setBackground(new java.awt.Color(233, 235, 242));
        jPanel16.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Data Jabatan", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 18))); // NOI18N

        jLabel18.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel18.setText("ID Jabatan");
        jLabel18.setPreferredSize(new java.awt.Dimension(110, 30));

        jLabel70.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel70.setText("Nama ");

        jLabel72.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel72.setText("Deskripsi");

        txtNamaJabatan.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtNamaJabatan.setPreferredSize(new java.awt.Dimension(200, 30));

        txtIdJabatan.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtIdJabatan.setText("1");

        txtDeskripsi.setColumns(20);
        txtDeskripsi.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtDeskripsi.setRows(5);
        jScrollPane13.setViewportView(txtDeskripsi);

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel70)
                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel72))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(txtNamaJabatan, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 237, Short.MAX_VALUE)
                        .addComponent(txtIdJabatan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jScrollPane13, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel16Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jScrollPane13, txtIdJabatan, txtNamaJabatan});

        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtIdJabatan))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtNamaJabatan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel70))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel72)
                    .addComponent(jScrollPane13, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(34, Short.MAX_VALUE))
        );

        jPanel16Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {txtIdJabatan, txtNamaJabatan});

        btnSimpanJabatan.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnSimpanJabatan.setText("Simpan");
        btnSimpanJabatan.setPreferredSize(new java.awt.Dimension(120, 30));
        btnSimpanJabatan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimpanJabatanActionPerformed(evt);
            }
        });

        btnResetJabatan.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnResetJabatan.setText("Reset");
        btnResetJabatan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetJabatanActionPerformed(evt);
            }
        });

        btnHapusJabatan.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnHapusJabatan.setText("Hapus");
        btnHapusJabatan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHapusJabatanActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGap(150, 150, 150)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnHapusJabatan)
                    .addComponent(btnResetJabatan)
                    .addComponent(btnSimpanJabatan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel15Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnHapusJabatan, btnResetJabatan, btnSimpanJabatan});

        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnSimpanJabatan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnResetJabatan)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnHapusJabatan)
                .addContainerGap(269, Short.MAX_VALUE))
        );

        jPanel15Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnHapusJabatan, btnResetJabatan, btnSimpanJabatan});

        mdJabatan.add(jPanel15, java.awt.BorderLayout.LINE_START);

        jPanel22.setBackground(new java.awt.Color(233, 235, 242));

        tableDataJabatan.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        tableDataJabatan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID Jabatan", "Nama Jabatan", "Deskripsi"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableDataJabatan.setFillsViewportHeight(true);
        tableDataJabatan.setRowHeight(30);
        tableDataJabatan.setRowMargin(5);
        tableDataJabatan.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane2.setViewportView(tableDataJabatan);
        if (tableDataJabatan.getColumnModel().getColumnCount() > 0) {
            tableDataJabatan.getColumnModel().getColumn(1).setResizable(false);
            tableDataJabatan.getColumnModel().getColumn(2).setResizable(false);
            tableDataJabatan.getColumnModel().getColumn(2).setPreferredWidth(500);
        }

        btnCariJabatan.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnCariJabatan.setText("Cari");
        btnCariJabatan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCariJabatanActionPerformed(evt);
            }
        });

        txtCariJabatan.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        btnResetJob.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnResetJob.setText("Reset");
        btnResetJob.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetJobActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
        jPanel22.setLayout(jPanel22Layout);
        jPanel22Layout.setHorizontalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 482, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel22Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtCariJabatan, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCariJabatan)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnResetJob)
                .addContainerGap())
        );
        jPanel22Layout.setVerticalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel22Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnCariJabatan)
                        .addComponent(btnResetJob))
                    .addComponent(txtCariJabatan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 724, Short.MAX_VALUE))
        );

        mdJabatan.add(jPanel22, java.awt.BorderLayout.CENTER);

        mdInventory.setBackground(new java.awt.Color(233, 235, 242));
        mdInventory.setLayout(new java.awt.BorderLayout());

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/inventory.png"))); // NOI18N
        jLabel15.setText("Master Data Inventory");
        mdInventory.add(jLabel15, java.awt.BorderLayout.PAGE_START);

        jPanel3.setBackground(new java.awt.Color(233, 235, 242));
        jPanel3.setMinimumSize(new java.awt.Dimension(300, 100));

        jPanel14.setBackground(new java.awt.Color(233, 235, 242));
        jPanel14.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Data Inventory", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 18))); // NOI18N

        jLabel74.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel74.setText("ID Inventory");
        jLabel74.setToolTipText("");
        jLabel74.setPreferredSize(new java.awt.Dimension(110, 30));

        jLabel75.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel75.setText("Nama ");

        jLabel76.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel76.setText("Stock");

        txtNamaInv.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtNamaInv.setPreferredSize(new java.awt.Dimension(200, 30));

        txtIdInv.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtIdInv.setText("1");

        spStock.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel75)
                    .addComponent(jLabel76)
                    .addComponent(jLabel74, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(spStock)
                    .addComponent(txtNamaInv, javax.swing.GroupLayout.DEFAULT_SIZE, 264, Short.MAX_VALUE)
                    .addComponent(txtIdInv, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel74, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtIdInv))
                .addGap(16, 16, 16)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel75)
                    .addComponent(txtNamaInv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel76)
                    .addComponent(spStock, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(47, Short.MAX_VALUE))
        );

        jPanel14Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {spStock, txtIdInv, txtNamaInv});

        btnSimpanInv.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnSimpanInv.setText("Simpan");
        btnSimpanInv.setPreferredSize(new java.awt.Dimension(120, 30));
        btnSimpanInv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimpanInvActionPerformed(evt);
            }
        });

        btnResetInv.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnResetInv.setText("Reset");
        btnResetInv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetInvActionPerformed(evt);
            }
        });

        btnHapusInv.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnHapusInv.setText("Hapus");
        btnHapusInv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHapusInvActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(107, 107, 107)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnHapusInv)
                    .addComponent(btnResetInv)
                    .addComponent(btnSimpanInv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel3Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnHapusInv, btnResetInv, btnSimpanInv});

        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnSimpanInv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnResetInv)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnHapusInv)
                .addContainerGap(306, Short.MAX_VALUE))
        );

        jPanel3Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnHapusInv, btnResetInv, btnSimpanInv});

        mdInventory.add(jPanel3, java.awt.BorderLayout.LINE_START);

        jPanel24.setBackground(new java.awt.Color(233, 235, 242));

        tableDataInventory.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        tableDataInventory.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID Inventory", "Nama Inventory", "Stock"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableDataInventory.setFillsViewportHeight(true);
        tableDataInventory.setRowHeight(30);
        tableDataInventory.setRowMargin(5);
        tableDataInventory.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane3.setViewportView(tableDataInventory);
        if (tableDataInventory.getColumnModel().getColumnCount() > 0) {
            tableDataInventory.getColumnModel().getColumn(1).setResizable(false);
            tableDataInventory.getColumnModel().getColumn(1).setPreferredWidth(550);
        }

        btnCariInventory.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnCariInventory.setText("Cari");
        btnCariInventory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCariInventoryActionPerformed(evt);
            }
        });

        txtCariInventory.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        btnResetInventory.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnResetInventory.setText("Reset");
        btnResetInventory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetInventoryActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel24Layout = new javax.swing.GroupLayout(jPanel24);
        jPanel24.setLayout(jPanel24Layout);
        jPanel24Layout.setHorizontalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel24Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtCariInventory, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCariInventory)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnResetInventory)
                .addContainerGap())
        );
        jPanel24Layout.setVerticalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCariInventory, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCariInventory)
                    .addComponent(btnResetInventory))
                .addGap(11, 11, 11)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 672, Short.MAX_VALUE))
        );

        mdInventory.add(jPanel24, java.awt.BorderLayout.CENTER);

        mdLayanan.setBackground(new java.awt.Color(233, 235, 242));
        mdLayanan.setLayout(new java.awt.BorderLayout());

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/service.png"))); // NOI18N
        jLabel19.setText("Master Data Layanan");
        mdLayanan.add(jLabel19, java.awt.BorderLayout.PAGE_START);

        jPanel4.setBackground(new java.awt.Color(233, 235, 242));
        jPanel4.setMinimumSize(new java.awt.Dimension(300, 100));

        jPanel20.setBackground(new java.awt.Color(233, 235, 242));
        jPanel20.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Data Layanan", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 18))); // NOI18N

        jLabel20.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel20.setText("ID Layanan");
        jLabel20.setPreferredSize(new java.awt.Dimension(110, 30));

        jLabel21.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel21.setText("Nama ");

        jLabel22.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel22.setText("Harga / kg");

        txtNamaaLayanan.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtNamaaLayanan.setPreferredSize(new java.awt.Dimension(200, 30));

        txtHargaLayanan.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jLabel27.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jLabel28.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel28.setText("Rp");

        txtIdLayanan.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel21)
                    .addComponent(jLabel22)
                    .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel27))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel20Layout.createSequentialGroup()
                            .addComponent(jLabel28)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtHargaLayanan))
                        .addComponent(txtNamaaLayanan, javax.swing.GroupLayout.DEFAULT_SIZE, 223, Short.MAX_VALUE))
                    .addComponent(txtIdLayanan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel20Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {txtIdLayanan, txtNamaaLayanan});

        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtIdLayanan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(txtNamaaLayanan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22)
                    .addComponent(txtHargaLayanan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel28))
                .addGap(18, 18, 18)
                .addComponent(jLabel27)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel20Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {txtHargaLayanan, txtIdLayanan, txtNamaaLayanan});

        jPanel21.setBackground(new java.awt.Color(233, 235, 242));
        jPanel21.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Data Inventory", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 18))); // NOI18N

        jLabel25.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel25.setText("ID Inventory");
        jLabel25.setPreferredSize(new java.awt.Dimension(150, 30));

        cbInventory.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        cbInventory.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1 - Rinso Anti Noda" }));
        cbInventory.setPreferredSize(new java.awt.Dimension(165, 30));

        jLabel26.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel26.setText("Stock Dipakai");

        spStockDipakai.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jLabel23.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel23.setText("mL");
        jLabel23.setToolTipText("");

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel26))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel21Layout.createSequentialGroup()
                        .addComponent(spStockDipakai)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel23))
                    .addComponent(cbInventory, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbInventory, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel26)
                    .addComponent(spStockDipakai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel23))
                .addContainerGap(44, Short.MAX_VALUE))
        );

        btnSimpanLayanan.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnSimpanLayanan.setText("Simpan");
        btnSimpanLayanan.setPreferredSize(new java.awt.Dimension(120, 30));
        btnSimpanLayanan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimpanLayananActionPerformed(evt);
            }
        });

        btnResetLayanan.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnResetLayanan.setText("Reset");
        btnResetLayanan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetLayananActionPerformed(evt);
            }
        });

        btnHapusLayanan.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnHapusLayanan.setText("Hapus");
        btnHapusLayanan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHapusLayananActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(134, 134, 134)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnHapusLayanan)
                    .addComponent(btnResetLayanan)
                    .addComponent(btnSimpanLayanan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnHapusLayanan, btnResetLayanan, btnSimpanLayanan});

        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnSimpanLayanan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnResetLayanan)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnHapusLayanan)
                .addContainerGap(119, Short.MAX_VALUE))
        );

        jPanel4Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnHapusLayanan, btnResetLayanan, btnSimpanLayanan});

        mdLayanan.add(jPanel4, java.awt.BorderLayout.LINE_START);

        jPanel23.setBackground(new java.awt.Color(233, 235, 242));

        tableDataLayanan.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        tableDataLayanan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID Layanan", "Nama Layanan", "Harga /kg", "ID Inventory", "Stock Terpakai"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableDataLayanan.setFillsViewportHeight(true);
        tableDataLayanan.setRowHeight(30);
        tableDataLayanan.setRowMargin(5);
        tableDataLayanan.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tableDataLayanan.getTableHeader().setReorderingAllowed(false);
        jScrollPane6.setViewportView(tableDataLayanan);
        if (tableDataLayanan.getColumnModel().getColumnCount() > 0) {
            tableDataLayanan.getColumnModel().getColumn(0).setResizable(false);
            tableDataLayanan.getColumnModel().getColumn(0).setPreferredWidth(10);
            tableDataLayanan.getColumnModel().getColumn(1).setResizable(false);
            tableDataLayanan.getColumnModel().getColumn(1).setPreferredWidth(200);
            tableDataLayanan.getColumnModel().getColumn(2).setResizable(false);
            tableDataLayanan.getColumnModel().getColumn(2).setPreferredWidth(10);
            tableDataLayanan.getColumnModel().getColumn(3).setResizable(false);
            tableDataLayanan.getColumnModel().getColumn(3).setPreferredWidth(10);
            tableDataLayanan.getColumnModel().getColumn(4).setResizable(false);
            tableDataLayanan.getColumnModel().getColumn(4).setPreferredWidth(10);
        }

        txtCariLayanan.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        btnCariLayanan.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnCariLayanan.setText("Cari");
        btnCariLayanan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCariLayananActionPerformed(evt);
            }
        });

        btnResetServ.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnResetServ.setText("Reset");
        btnResetServ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetServActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
        jPanel23.setLayout(jPanel23Layout);
        jPanel23Layout.setHorizontalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 463, Short.MAX_VALUE)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtCariLayanan, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCariLayanan)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnResetServ)
                .addContainerGap())
        );
        jPanel23Layout.setVerticalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCariLayanan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCariLayanan)
                    .addComponent(btnResetServ))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 687, Short.MAX_VALUE))
        );

        mdLayanan.add(jPanel23, java.awt.BorderLayout.CENTER);

        about.setBackground(new java.awt.Color(233, 235, 242));

        jLabel68.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/logo1.png"))); // NOI18N

        jLabel43.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel43.setText("UAS-PBOL-Laundry");

        jLabel44.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel44.setText("Version 1.0.0.0");

        jLabel45.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel45.setText("1873004 - Alvira Puteri Copyright © 2020");

        javax.swing.GroupLayout aboutLayout = new javax.swing.GroupLayout(about);
        about.setLayout(aboutLayout);
        aboutLayout.setHorizontalGroup(
            aboutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(aboutLayout.createSequentialGroup()
                .addComponent(jLabel68, javax.swing.GroupLayout.PREFERRED_SIZE, 657, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(aboutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel43)
                    .addComponent(jLabel44)
                    .addComponent(jLabel45))
                .addGap(0, 120, Short.MAX_VALUE))
        );
        aboutLayout.setVerticalGroup(
            aboutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(aboutLayout.createSequentialGroup()
                .addGroup(aboutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(aboutLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel68))
                    .addGroup(aboutLayout.createSequentialGroup()
                        .addGap(123, 123, 123)
                        .addComponent(jLabel43)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel44)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel45)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        dtTransaksi.setBackground(new java.awt.Color(233, 235, 242));
        dtTransaksi.setLayout(new java.awt.BorderLayout());

        txtcurrdate.setBackground(new java.awt.Color(255, 255, 255));
        txtcurrdate.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txtcurrdate.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtcurrdate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/calendar.png"))); // NOI18N
        txtcurrdate.setText("Mon, 25 May 2020");
        txtcurrdate.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        dtTransaksi.add(txtcurrdate, java.awt.BorderLayout.PAGE_START);

        jPanel11.setBackground(new java.awt.Color(233, 235, 242));

        jPanel12.setBackground(new java.awt.Color(233, 235, 242));
        jPanel12.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Data Transaksi", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 18))); // NOI18N
        jPanel12.setMinimumSize(new java.awt.Dimension(300, 100));

        jLabel37.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel37.setText("ID Transaksi");
        jLabel37.setPreferredSize(new java.awt.Dimension(100, 20));

        jLabel40.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel40.setText("Tanggal  Terima");

        jLabel41.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel41.setText("Tanggal Selesai");

        label2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        label2.setText("Tanggal Antar");
        label2.setPreferredSize(new java.awt.Dimension(125, 20));

        label1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        label1.setText("Status Pengantaran");

        tableDataTransaksi.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        tableDataTransaksi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Tgl.Terima", "Tgl.Selesai", "Berat", "Pelanggan", "Karyawan", "Layanan", "Status Laundry", "Status Pengantaran", "Pengantar", "Tanggal Antar", "Total", "Status Pembayaran"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableDataTransaksi.setFillsViewportHeight(true);
        tableDataTransaksi.setRowHeight(30);
        tableDataTransaksi.setRowMargin(5);
        tableDataTransaksi.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tableDataTransaksi.getTableHeader().setReorderingAllowed(false);
        jScrollPane9.setViewportView(tableDataTransaksi);
        if (tableDataTransaksi.getColumnModel().getColumnCount() > 0) {
            tableDataTransaksi.getColumnModel().getColumn(0).setPreferredWidth(50);
            tableDataTransaksi.getColumnModel().getColumn(1).setPreferredWidth(110);
            tableDataTransaksi.getColumnModel().getColumn(2).setPreferredWidth(110);
            tableDataTransaksi.getColumnModel().getColumn(4).setPreferredWidth(110);
            tableDataTransaksi.getColumnModel().getColumn(5).setPreferredWidth(110);
            tableDataTransaksi.getColumnModel().getColumn(7).setPreferredWidth(110);
            tableDataTransaksi.getColumnModel().getColumn(8).setPreferredWidth(110);
            tableDataTransaksi.getColumnModel().getColumn(9).setPreferredWidth(110);
            tableDataTransaksi.getColumnModel().getColumn(10).setPreferredWidth(110);
            tableDataTransaksi.getColumnModel().getColumn(12).setPreferredWidth(110);
        }

        cbAntar.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        cbAntar.setSelected(true);
        cbAntar.setText("Laundry di antar");
        cbAntar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbAntarActionPerformed(evt);
            }
        });

        cbStatusPengantaran.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        cbStatusPengantaran.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Belum Diantar" }));
        cbStatusPengantaran.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbStatusPengantaranActionPerformed(evt);
            }
        });

        label3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        label3.setText("Pengantar");

        cbPengantar.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        cbPengantar.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Toyya" }));

        jLabel46.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel46.setText("Status Laundry");

        cbStatusLaundry.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        cbStatusLaundry.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Belum Selesai" }));

        jLabel29.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel29.setText("Notes");

        jScrollPane5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        txtNotes.setColumns(20);
        txtNotes.setRows(5);
        jScrollPane5.setViewportView(txtNotes);

        dateAntar.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        txtIdTransaksi.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtIdTransaksi.setText("1");

        dateSelesai.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        dateTerima.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtIdTransaksi, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(cbAntar)
                        .addGroup(jPanel12Layout.createSequentialGroup()
                            .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(label3)
                                .addComponent(label1)
                                .addComponent(label2, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(cbStatusPengantaran, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(dateAntar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(cbPengantar, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addComponent(jLabel29)
                        .addComponent(jScrollPane5)
                        .addGroup(jPanel12Layout.createSequentialGroup()
                            .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jLabel41, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel40, javax.swing.GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE)
                                .addComponent(jLabel46, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGap(14, 14, 14)
                            .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(dateSelesai, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(cbStatusLaundry, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel12Layout.createSequentialGroup()
                                    .addComponent(dateTerima, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(0, 0, Short.MAX_VALUE))))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane9)
                .addGap(25, 25, 25))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel12Layout.createSequentialGroup()
                                .addGap(43, 43, 43)
                                .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(txtIdTransaksi)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel40)
                            .addComponent(dateTerima, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel41)
                            .addComponent(dateSelesai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbStatusLaundry, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel46))
                        .addGap(23, 23, 23)
                        .addComponent(cbAntar)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbStatusPengantaran, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(label1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(label2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(dateAntar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbPengantar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(label3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel29)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 147, Short.MAX_VALUE))
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane9, javax.swing.GroupLayout.DEFAULT_SIZE, 516, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jPanel12Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {cbPengantar, cbStatusLaundry, cbStatusPengantaran, dateAntar});

        jPanel12Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jLabel41, jLabel46, txtIdTransaksi});

        jPanel12Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jLabel40, label1});

        jPanel13.setBackground(new java.awt.Color(233, 235, 242));
        jPanel13.setMinimumSize(new java.awt.Dimension(300, 100));

        btnSimpanTransaksi.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnSimpanTransaksi.setText("Simpan");
        btnSimpanTransaksi.setPreferredSize(new java.awt.Dimension(95, 31));
        btnSimpanTransaksi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimpanTransaksiActionPerformed(evt);
            }
        });

        btnResetTransaksi.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnResetTransaksi.setText("Reset");
        btnResetTransaksi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetTransaksiActionPerformed(evt);
            }
        });

        btnHapusTransaksi.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnHapusTransaksi.setText("Hapus");
        btnHapusTransaksi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHapusTransaksiActionPerformed(evt);
            }
        });

        jPanel6.setBackground(new java.awt.Color(233, 235, 242));
        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Data Laundry", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 18))); // NOI18N

        jLabel36.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel36.setText("Pelanggan");
        jLabel36.setPreferredSize(new java.awt.Dimension(90, 20));

        jLabel38.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel38.setText("Karyawan");

        jLabel39.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel39.setText("Layanan");

        jLabel42.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel42.setText("Berat /kg");

        txtBerat.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtBerat.setText("4");
        txtBerat.setMinimumSize(new java.awt.Dimension(50, 26));
        txtBerat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBeratActionPerformed(evt);
            }
        });

        txtDeskLayanan.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtDeskLayanan.setText("Deskripsi : Cuci + Kering + Setrika Reguler (3Hari)  ");

        txtStatusMember.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtStatusMember.setText("Status Membership : Aktif");

        cbPelanggan.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        cbPelanggan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Voyya" }));
        cbPelanggan.setPreferredSize(new java.awt.Dimension(200, 30));
        cbPelanggan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbPelangganActionPerformed(evt);
            }
        });

        cbKaryawan.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        cbKaryawan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Noyya" }));

        cbLayanan.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        cbLayanan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2" }));
        cbLayanan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbLayananActionPerformed(evt);
            }
        });

        btnTambah.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnTambah.setText("Tambah Pelanggan");
        btnTambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTambahActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton2.setText("Cari Pelanggan");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel38)
                            .addComponent(jLabel39)
                            .addComponent(jLabel42))
                        .addGap(52, 52, 52)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(cbPelanggan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtStatusMember)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 52, Short.MAX_VALUE)
                                .addComponent(jButton2))
                            .addComponent(txtBerat, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbKaryawan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(cbLayanan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtDeskLayanan))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnTambah)))
                .addContainerGap())
        );

        jPanel6Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel36, jLabel38, jLabel39, jLabel42});

        jPanel6Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {cbKaryawan, cbLayanan, cbPelanggan});

        jPanel6Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnTambah, jButton2});

        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbPelanggan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtStatusMember)
                    .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnTambah)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbKaryawan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel38))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbLayanan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel39)
                    .addComponent(txtDeskLayanan))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtBerat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel42))
                .addContainerGap())
        );

        jPanel6Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnTambah, cbKaryawan, cbLayanan, cbPelanggan, txtBerat});

        jPanel8.setBackground(new java.awt.Color(233, 235, 242));
        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Data Pembayaran", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 18))); // NOI18N

        jLabel59.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel59.setText("Jumlah Harga");

        jLabel60.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel60.setText("Jumlah Biaya Antar");

        jLabel61.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel61.setText("Total");

        jLabel63.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel63.setText("Status Pembayaran");

        cbStatusTransaksi.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        cbStatusTransaksi.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Belum Lunas" }));

        txtJumlahHarga.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtJumlahHarga.setText("Rp");

        txtJumlahBiayaAntar.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtJumlahBiayaAntar.setText("Rp 0,-");

        jlabel.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jlabel.setText("Rp");

        jLabel56.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel56.setText("Harga Layanan");

        txthargalayanan.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txthargalayanan.setText("Rp 6.000,- / kg");

        jLabel62.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel62.setText("Biaya Antar");

        jLabel77.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel77.setText("Rp 2.000,- / kg");

        jButton1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton1.setText("Hitung Total");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel9.setText("--------------------------------------------------------------");

        txtTotal.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtTotal.setText("jLabel24");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel56)
                            .addComponent(jLabel59))
                        .addGap(70, 70, 70)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txthargalayanan)
                            .addComponent(txtJumlahHarga))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel60)
                                    .addComponent(jLabel62))
                                .addGap(69, 69, 69)
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel77)
                                    .addComponent(txtJumlahBiayaAntar)))
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGap(78, 78, 78)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel9))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 69, Short.MAX_VALUE)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel63, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel61))
                        .addGap(46, 46, 46)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(jlabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtTotal))
                            .addComponent(cbStatusTransaksi, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(68, 68, 68))))
        );

        jPanel8Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel59, jLabel60, jLabel61, jLabel63});

        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel56)
                    .addComponent(txthargalayanan))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel59)
                    .addComponent(txtJumlahHarga))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel61)
                            .addComponent(jlabel)
                            .addComponent(txtTotal))
                        .addGap(31, 31, 31)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel63)
                            .addComponent(cbStatusTransaksi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel62)
                            .addComponent(jLabel77))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel60)
                            .addComponent(txtJumlahBiayaAntar))))
                .addGap(23, 23, 23)
                .addComponent(jButton1)
                .addGap(18, 18, 18))
        );

        btnInvoice.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnInvoice.setText("Inovice");
        btnInvoice.setPreferredSize(new java.awt.Dimension(95, 31));
        btnInvoice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInvoiceActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(665, 665, 665)
                .addComponent(btnSimpanTransaksi, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnInvoice, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnResetTransaksi)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnHapusTransaksi)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel13Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnHapusTransaksi, btnResetTransaksi, btnSimpanTransaksi});

        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSimpanTransaksi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnResetTransaksi)
                    .addComponent(btnHapusTransaksi)
                    .addComponent(btnInvoice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel13, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        dtTransaksi.add(jPanel11, java.awt.BorderLayout.CENTER);

        cariPelanggan.setPreferredSize(new java.awt.Dimension(1000, 500));

        txtCariCust.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        btnCari.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnCari.setText("Cari");
        btnCari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCariActionPerformed(evt);
            }
        });

        jButton4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton4.setText("OK");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        tableDataCariPelanggan.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        tableDataCariPelanggan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nama Pelanggan", "No. Telp", "Alamat", "Status Membership", "Tanggal Jadi Member"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableDataCariPelanggan.setFillsViewportHeight(true);
        tableDataCariPelanggan.setRowHeight(30);
        tableDataCariPelanggan.setRowMargin(5);
        tableDataCariPelanggan.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tableDataCariPelanggan.getTableHeader().setReorderingAllowed(false);
        jScrollPane7.setViewportView(tableDataCariPelanggan);
        if (tableDataCariPelanggan.getColumnModel().getColumnCount() > 0) {
            tableDataCariPelanggan.getColumnModel().getColumn(1).setPreferredWidth(200);
            tableDataCariPelanggan.getColumnModel().getColumn(2).setPreferredWidth(150);
            tableDataCariPelanggan.getColumnModel().getColumn(3).setPreferredWidth(200);
            tableDataCariPelanggan.getColumnModel().getColumn(4).setPreferredWidth(150);
            tableDataCariPelanggan.getColumnModel().getColumn(5).setPreferredWidth(150);
        }

        jButton3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton3.setText("Reset");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout cariPelangganLayout = new javax.swing.GroupLayout(cariPelanggan);
        cariPelanggan.setLayout(cariPelangganLayout);
        cariPelangganLayout.setHorizontalGroup(
            cariPelangganLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 1000, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, cariPelangganLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtCariCust, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCari)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton3)
                .addContainerGap())
            .addGroup(cariPelangganLayout.createSequentialGroup()
                .addGap(465, 465, 465)
                .addComponent(jButton4)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        cariPelangganLayout.setVerticalGroup(
            cariPelangganLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cariPelangganLayout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addGroup(cariPelangganLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCariCust, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCari)
                    .addComponent(jButton3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 520, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton4)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelBelumLunas.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Pilih Periode Transaksi", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 18))); // NOI18N

        buttonGroup2.add(pilihTanggal1);
        pilihTanggal1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        pilihTanggal1.setText("Per Hari");
        pilihTanggal1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pilihTanggal1ActionPerformed(evt);
            }
        });

        buttonGroup2.add(pilihBulan1);
        pilihBulan1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        pilihBulan1.setText("Per Bulan");
        pilihBulan1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pilihBulan1ActionPerformed(evt);
            }
        });

        buttonGroup2.add(jRadioButton4);
        jRadioButton4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jRadioButton4.setText("Tampilkan Semua");
        jRadioButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton4ActionPerformed(evt);
            }
        });

        okBelumLunas.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        okBelumLunas.setText("OK");
        okBelumLunas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okBelumLunasActionPerformed(evt);
            }
        });

        datePilihTanggal1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        txtPilihTanggal1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtPilihTanggal1.setText("Pilih Tanggal ");

        txtPilihBulan1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtPilihBulan1.setText("Pilih Bulan ");

        datePilihBulan1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        javax.swing.GroupLayout panelBelumLunasLayout = new javax.swing.GroupLayout(panelBelumLunas);
        panelBelumLunas.setLayout(panelBelumLunasLayout);
        panelBelumLunasLayout.setHorizontalGroup(
            panelBelumLunasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBelumLunasLayout.createSequentialGroup()
                .addGroup(panelBelumLunasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelBelumLunasLayout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addGroup(panelBelumLunasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jRadioButton4)
                            .addComponent(pilihBulan1)
                            .addComponent(pilihTanggal1)
                            .addGroup(panelBelumLunasLayout.createSequentialGroup()
                                .addGap(28, 28, 28)
                                .addGroup(panelBelumLunasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtPilihTanggal1)
                                    .addComponent(txtPilihBulan1))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panelBelumLunasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(datePilihTanggal1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(datePilihBulan1, javax.swing.GroupLayout.DEFAULT_SIZE, 608, Short.MAX_VALUE)))))
                    .addGroup(panelBelumLunasLayout.createSequentialGroup()
                        .addGap(128, 128, 128)
                        .addComponent(okBelumLunas)))
                .addContainerGap())
        );
        panelBelumLunasLayout.setVerticalGroup(
            panelBelumLunasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBelumLunasLayout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(pilihTanggal1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelBelumLunasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(datePilihTanggal1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPilihTanggal1))
                .addGap(18, 18, 18)
                .addComponent(pilihBulan1)
                .addGap(11, 11, 11)
                .addGroup(panelBelumLunasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelBelumLunasLayout.createSequentialGroup()
                        .addComponent(txtPilihBulan1)
                        .addGap(16, 16, 16)
                        .addComponent(jRadioButton4)
                        .addGap(52, 52, 52)
                        .addComponent(okBelumLunas))
                    .addComponent(datePilihBulan1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(45, Short.MAX_VALUE))
        );

        panelBelumLunas.getAccessibleContext().setAccessibleName("Pilih Periode Waktu");

        panelLunas.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Pilih Periode Transaksi", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 18))); // NOI18N

        buttonGroup2.add(pilihTanggal);
        pilihTanggal.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        pilihTanggal.setText("Per Hari");
        pilihTanggal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pilihTanggalActionPerformed(evt);
            }
        });

        buttonGroup2.add(pilihBulan);
        pilihBulan.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        pilihBulan.setText("Per Bulan");
        pilihBulan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pilihBulanActionPerformed(evt);
            }
        });

        buttonGroup2.add(jRadioButton5);
        jRadioButton5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jRadioButton5.setText("Tampilkan Semua");
        jRadioButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton5ActionPerformed(evt);
            }
        });

        okBelumLunas1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        okBelumLunas1.setText("OK");
        okBelumLunas1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okBelumLunas1ActionPerformed(evt);
            }
        });

        datePilihTanggal.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        txtPilihTanggal.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtPilihTanggal.setText("Pilih Tanggal ");

        txtPilihBulan.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtPilihBulan.setText("Pilih Bulan ");

        datePilihBulan.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        javax.swing.GroupLayout panelLunasLayout = new javax.swing.GroupLayout(panelLunas);
        panelLunas.setLayout(panelLunasLayout);
        panelLunasLayout.setHorizontalGroup(
            panelLunasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLunasLayout.createSequentialGroup()
                .addGap(128, 128, 128)
                .addComponent(okBelumLunas1)
                .addContainerGap(403, Short.MAX_VALUE))
            .addGroup(panelLunasLayout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(panelLunasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jRadioButton5)
                    .addComponent(pilihBulan)
                    .addComponent(pilihTanggal)
                    .addGroup(panelLunasLayout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addGroup(panelLunasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtPilihTanggal)
                            .addComponent(txtPilihBulan))
                        .addGap(18, 18, 18)
                        .addGroup(panelLunasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(datePilihTanggal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(datePilihBulan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())))
        );
        panelLunasLayout.setVerticalGroup(
            panelLunasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLunasLayout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(pilihTanggal)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelLunasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(datePilihTanggal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPilihTanggal))
                .addGap(18, 18, 18)
                .addComponent(pilihBulan)
                .addGap(11, 11, 11)
                .addGroup(panelLunasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelLunasLayout.createSequentialGroup()
                        .addComponent(txtPilihBulan)
                        .addGap(16, 16, 16)
                        .addComponent(jRadioButton5)
                        .addGap(52, 52, 52)
                        .addComponent(okBelumLunas1))
                    .addComponent(datePilihBulan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(45, Short.MAX_VALUE))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Aplikasi Laundry");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setMinimumSize(new java.awt.Dimension(800, 600));
        getContentPane().setLayout(new java.awt.CardLayout());

        jMenu1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/file.png"))); // NOI18N
        jMenu1.setMnemonic('F');
        jMenu1.setText("File");
        jMenu1.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jMenu1.setIconTextGap(10);
        jMenu1.setPreferredSize(new java.awt.Dimension(110, 38));

        homeMenu.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_H, java.awt.event.InputEvent.CTRL_MASK));
        homeMenu.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        homeMenu.setText("Home");
        homeMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                homeMenuActionPerformed(evt);
            }
        });
        jMenu1.add(homeMenu);
        jMenu1.add(jSeparator1);

        logOut.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
        logOut.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        logOut.setText("Log out");
        logOut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logOutActionPerformed(evt);
            }
        });
        jMenu1.add(logOut);

        aboutMenu.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        aboutMenu.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        aboutMenu.setText("Tentang");
        aboutMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aboutMenuActionPerformed(evt);
            }
        });
        jMenu1.add(aboutMenu);

        exitMenu.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_X, java.awt.event.InputEvent.CTRL_MASK));
        exitMenu.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        exitMenu.setText("Exit");
        exitMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitMenuActionPerformed(evt);
            }
        });
        jMenu1.add(exitMenu);

        jMenuBar1.add(jMenu1);

        jMenu2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/md.png"))); // NOI18N
        jMenu2.setMnemonic('M');
        jMenu2.setText("Master Data");
        jMenu2.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jMenu2.setIconTextGap(10);
        jMenu2.setPreferredSize(new java.awt.Dimension(180, 38));

        custMenu.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.CTRL_MASK));
        custMenu.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        custMenu.setText("Pelanggan");
        custMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                custMenuActionPerformed(evt);
            }
        });
        jMenu2.add(custMenu);
        jMenu2.add(jSeparator2);

        empMenu.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_K, java.awt.event.InputEvent.CTRL_MASK));
        empMenu.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        empMenu.setText("Karyawan");
        empMenu.setToolTipText("");
        empMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                empMenuActionPerformed(evt);
            }
        });
        jMenu2.add(empMenu);

        jobMenu.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_J, java.awt.event.InputEvent.CTRL_MASK));
        jobMenu.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jobMenu.setText("Jabatan");
        jobMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jobMenuActionPerformed(evt);
            }
        });
        jMenu2.add(jobMenu);
        jMenu2.add(jSeparator3);

        invMenu.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_I, java.awt.event.InputEvent.CTRL_MASK));
        invMenu.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        invMenu.setText("Inventory");
        invMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                invMenuActionPerformed(evt);
            }
        });
        jMenu2.add(invMenu);

        servMenu.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_L, java.awt.event.InputEvent.CTRL_MASK));
        servMenu.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        servMenu.setText("Jenis Layanan");
        servMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                servMenuActionPerformed(evt);
            }
        });
        jMenu2.add(servMenu);

        jMenuBar1.add(jMenu2);

        jMenu3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/transaksi.png"))); // NOI18N
        jMenu3.setText("Transaksi");
        jMenu3.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jMenu3.setIconTextGap(10);
        jMenu3.setPreferredSize(new java.awt.Dimension(149, 30));

        laundryMenu.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_T, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        laundryMenu.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        laundryMenu.setText("Jasa Laundry");
        laundryMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                laundryMenuActionPerformed(evt);
            }
        });
        jMenu3.add(laundryMenu);

        jMenuBar1.add(jMenu3);

        jMenu4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/report.png"))); // NOI18N
        jMenu4.setText("Laporan");
        jMenu4.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N

        menuLunas.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        menuLunas.setText("Laporan Transaksi Lunas");
        menuLunas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuLunasActionPerformed(evt);
            }
        });
        jMenu4.add(menuLunas);

        menuBelumLunas.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        menuBelumLunas.setText("Laporan Transaksi Belum Lunas");
        menuBelumLunas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuBelumLunasActionPerformed(evt);
            }
        });
        jMenu4.add(menuBelumLunas);

        menuInventory.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        menuInventory.setText("Laporan Inventory");
        menuInventory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuInventoryActionPerformed(evt);
            }
        });
        jMenu4.add(menuInventory);

        menuPelanggan.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        menuPelanggan.setText("Laporan List Pelanggan");
        menuPelanggan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuPelangganActionPerformed(evt);
            }
        });
        jMenu4.add(menuPelanggan);

        jMenuBar1.add(jMenu4);

        setJMenuBar(jMenuBar1);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoginActionPerformed
        admin = false;
        for (Karyawan p : karyawanControl.getAllKaryawan()) {
            if (!txtUsername.getText().isEmpty() && txtUsername.getText().equals(p.getUsername())
                    && String.valueOf(txtPassword.getPassword()).equals(p.getPassword())) {
                cek = true;
                log = p.getNamaKaryawan();
                idJob = p.getIdJabatan().getIdJabatan();

                if (p.getIsAdmin() == 1) {
                    admin = true;
                }
                break;
            }
        }

        for (Jabatan p : jabatanControl.getAllJabatan()) {
            if (idJob == p.getIdJabatan()) {
                job = p.getNamaJabatan();
            }
        }

        if (cek == true) {
            txtjabatan.setText(job);
            login.setVisible(false);
            home.setVisible(true);
            jMenuBar1.setVisible(true);
            JOptionPane.showMessageDialog(this, "Welcome " + log + " !");
            cek = false;
            if (admin != true) {
                empMenu.setEnabled(false);
                invMenu.setEnabled(false);
                servMenu.setEnabled(false);
                jobMenu.setEnabled(false);
            } else {
                empMenu.setEnabled(true);
                invMenu.setEnabled(true);
                servMenu.setEnabled(true);
                jobMenu.setEnabled(true);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Invalid Username / Password! ");
            txtUsername.setText("");
            txtPassword.setText("");
            cek = false;
        }
    }//GEN-LAST:event_btnLoginActionPerformed

    private void logOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logOutActionPerformed
        txtUsername.setText("");
        txtPassword.setText("");
        login.setVisible(true);
        home.setVisible(false);
        mdPelanggan.setVisible(false);
        mdKaryawan.setVisible(false);
        mdJabatan.setVisible(false);
        jMenuBar1.setVisible(false);
        mdInventory.setVisible(false);
        mdLayanan.setVisible(false);
        dtTransaksi.setVisible(false);
        about.setVisible(false);
        cek = false;
    }//GEN-LAST:event_logOutActionPerformed

    private void exitMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitMenuActionPerformed
        System.exit(0);
    }//GEN-LAST:event_exitMenuActionPerformed

    private void homeMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_homeMenuActionPerformed
        login.setVisible(false);
        home.setVisible(true);
        mdPelanggan.setVisible(false);
        mdKaryawan.setVisible(false);
        mdJabatan.setVisible(false);
        mdInventory.setVisible(false);
        mdLayanan.setVisible(false);
        dtTransaksi.setVisible(false);
        about.setVisible(false);
        resetAll();
    }//GEN-LAST:event_homeMenuActionPerformed

    private void custMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_custMenuActionPerformed
        login.setVisible(false);
        home.setVisible(false);
        mdPelanggan.setVisible(true);
        mdKaryawan.setVisible(false);
        mdJabatan.setVisible(false);
        mdInventory.setVisible(false);
        mdLayanan.setVisible(false);
        dtTransaksi.setVisible(false);
        about.setVisible(false);
        resetAll();
    }//GEN-LAST:event_custMenuActionPerformed

    private void empMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_empMenuActionPerformed
        login.setVisible(false);
        home.setVisible(false);
        mdPelanggan.setVisible(false);
        mdKaryawan.setVisible(true);
        mdJabatan.setVisible(false);
        mdInventory.setVisible(false);
        mdLayanan.setVisible(false);
        dtTransaksi.setVisible(false);
        about.setVisible(false);
        resetAll();
    }//GEN-LAST:event_empMenuActionPerformed

    private void jobMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jobMenuActionPerformed
        login.setVisible(false);
        home.setVisible(false);
        mdPelanggan.setVisible(false);
        mdKaryawan.setVisible(false);
        mdJabatan.setVisible(true);
        mdInventory.setVisible(false);
        mdLayanan.setVisible(false);
        dtTransaksi.setVisible(false);
        about.setVisible(false);
        resetAll();
    }//GEN-LAST:event_jobMenuActionPerformed

    private void txtPasswordKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPasswordKeyPressed

    }//GEN-LAST:event_txtPasswordKeyPressed

    private void invMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_invMenuActionPerformed
        login.setVisible(false);
        home.setVisible(false);
        mdPelanggan.setVisible(false);
        mdKaryawan.setVisible(false);
        mdJabatan.setVisible(false);
        mdInventory.setVisible(true);
        mdLayanan.setVisible(false);
        dtTransaksi.setVisible(false);
        about.setVisible(false);
        resetAll();
    }//GEN-LAST:event_invMenuActionPerformed

    private void servMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_servMenuActionPerformed
        login.setVisible(false);
        home.setVisible(false);
        mdPelanggan.setVisible(false);
        mdKaryawan.setVisible(false);
        mdJabatan.setVisible(false);
        mdInventory.setVisible(false);
        mdLayanan.setVisible(true);
        dtTransaksi.setVisible(false);
        about.setVisible(false);
        resetAll();
    }//GEN-LAST:event_servMenuActionPerformed

    private void laundryMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_laundryMenuActionPerformed
        login.setVisible(false);
        home.setVisible(false);
        mdPelanggan.setVisible(false);
        mdKaryawan.setVisible(false);
        mdJabatan.setVisible(false);
        mdInventory.setVisible(false);
        mdLayanan.setVisible(false);
        dtTransaksi.setVisible(true);
        about.setVisible(false);
        resetAll();
    }//GEN-LAST:event_laundryMenuActionPerformed

    private void aboutMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aboutMenuActionPerformed
        login.setVisible(false);
        home.setVisible(false);
        mdPelanggan.setVisible(false);
        mdKaryawan.setVisible(false);
        mdJabatan.setVisible(false);
        mdInventory.setVisible(false);
        mdLayanan.setVisible(false);
        dtTransaksi.setVisible(false);
        about.setVisible(true);
        resetAll();
    }//GEN-LAST:event_aboutMenuActionPerformed

    private void btnSimpanTransaksiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanTransaksiActionPerformed
        int inv = 0;
        int stock = 0;
        int dipakai = 0;
        try {
            if (dateTerima.getDate() == null) {
                JOptionPane.showMessageDialog(this, "Tanggal Terima harus di isi!", "Warning Message", JOptionPane.WARNING_MESSAGE);
            } else if (dateSelesai.getDate() == null) {
                JOptionPane.showMessageDialog(this, "Tanggal Selesai harus di isi!", "Warning Message", JOptionPane.WARNING_MESSAGE);
            } else if (getDifferenceDays2(dateTerima.getDate(), dateSelesai.getDate()) <= 0) {
                JOptionPane.showMessageDialog(this, "Tanggal Selesai harus lebih besar dari tanggal terima", "Warning Message", JOptionPane.WARNING_MESSAGE);
            } else if (cbStatusPengantaran.getSelectedItem() == "Sudah Diantar" && dateAntar.getDate() == null) {
                JOptionPane.showMessageDialog(this, "Tanggal Antar harus di isi!", "Warning Message", JOptionPane.WARNING_MESSAGE);
            } else if (txtBerat.getText().equals("0")) {
                JOptionPane.showMessageDialog(this, "Berat Cucian harus di isi!", "Warning Message", JOptionPane.WARNING_MESSAGE);
            } else if (txtTotal.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Total Pembayaran belum dihitung!", "Warning Message", JOptionPane.WARNING_MESSAGE);
            } else {
                if (row == -1) {//INSERT
                    Transaksi t = new Transaksi();
                    Inventory i;
                    t.setTanggalTerima(dateTerima.getDate());
                    t.setTanggalSelesai(dateSelesai.getDate());
                    t.setStatusLaundry(cbStatusLaundry.getSelectedIndex());
                    if (cbAntar.isSelected()) {
                        t.setStatusPengantaran(cbStatusPengantaran.getSelectedIndex() + 1);
                        if (cbStatusPengantaran.getSelectedIndex() == 1) {
                            t.setTanggalAntar(dateAntar.getDate());
                            t.setIdPengantar(karyawanControl.getAllKurir().get(cbPengantar.getSelectedIndex()).getIdKaryawan());
                        } else {
                            t.setTanggalAntar(null);
                            t.setIdPengantar(0);
                        }
                    } else {
                        t.setStatusPengantaran(0);
                        t.setTanggalAntar(null);
                        t.setIdPengantar(0);
                    }
                    t.setNotes(txtNotes.getText());
                    t.setIdPelanggan((Pelanggan) cbPelanggan.getSelectedItem());
                    t.setIdKaryawan((Karyawan) cbKaryawan.getSelectedItem());
                    t.setIdLayanan((Jenislayanan) cbLayanan.getSelectedItem());
                    t.setBerat(Integer.valueOf(txtBerat.getText()));
                    t.setTotal(Double.valueOf(txtTotal.getText()));
                    t.setStatusTransaksi(cbStatusTransaksi.getSelectedIndex());

                    inv = layananControl.getAllLayanan().get(cbLayanan.getSelectedIndex()).getIdInventory().getIdInventory();
                    dipakai = layananControl.getAllLayanan().get(cbLayanan.getSelectedIndex()).getStockDipakai();
                    i = inventoryControl.getSingleData(inv);

                    i.setStock(i.getStock() - (dipakai * Integer.valueOf(txtBerat.getText())));

                    if (JOptionPane.showConfirmDialog(this, "Apakah Anda akan Menambah Data Transaksi baru?", "Confirmation Message", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                        if (transaksiControl.simpanAtauUpdateOneTransaksi(t) && inventoryControl.simpanAtauUpdateOneInventory(i)) {
                            JOptionPane.showMessageDialog(this, "Data Transaksi berhasil di tambah...", "Infromation Message", JOptionPane.INFORMATION_MESSAGE);
                            resetTransaksi();
                            loadAllTable();
                        } else {
                            JOptionPane.showMessageDialog(this, "Data Transaksi gagal di tambah...", "Error Message", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                } else {//UPDATE
                    transaksiSelected.setTanggalTerima(dateTerima.getDate());
                    transaksiSelected.setTanggalSelesai(dateSelesai.getDate());
                    transaksiSelected.setStatusLaundry(cbStatusLaundry.getSelectedIndex());
                    if (cbAntar.isSelected()) {
                        transaksiSelected.setStatusPengantaran(cbStatusPengantaran.getSelectedIndex() + 1);
                        if (cbStatusPengantaran.getSelectedIndex() == 1) {
                            transaksiSelected.setTanggalAntar(dateAntar.getDate());
                            transaksiSelected.setIdPengantar(karyawanControl.getAllKurir().get(cbPengantar.getSelectedIndex()).getIdKaryawan());
                        } else {
                            transaksiSelected.setTanggalAntar(null);
                            transaksiSelected.setIdPengantar(0);
                        }
                    } else {
                        transaksiSelected.setStatusPengantaran(0);
                        transaksiSelected.setTanggalAntar(null);
                        transaksiSelected.setIdPengantar(0);
                    }
                    transaksiSelected.setNotes(txtNotes.getText());
                    transaksiSelected.setIdPelanggan((Pelanggan) cbPelanggan.getSelectedItem());
                    transaksiSelected.setIdKaryawan((Karyawan) cbKaryawan.getSelectedItem());
                    transaksiSelected.setIdLayanan((Jenislayanan) cbLayanan.getSelectedItem());
                    transaksiSelected.setBerat(Integer.valueOf(txtBerat.getText()));
                    transaksiSelected.setTotal(Double.valueOf(txtTotal.getText()));
                    transaksiSelected.setStatusTransaksi(cbStatusTransaksi.getSelectedIndex());
                    if (JOptionPane.showConfirmDialog(this, "Apakah Anda akan Mengubah Data Transaksi?", "Confirmation Message", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                        if (transaksiControl.simpanAtauUpdateOneTransaksi(transaksiSelected)) {
                            JOptionPane.showMessageDialog(this, "Data Transaksi berhasil di ubah...", "Infromation Message", JOptionPane.INFORMATION_MESSAGE);
                            resetTransaksi();
                            loadAllTable();
                        } else {
                            JOptionPane.showMessageDialog(this, "Data Transaksi gagal di ubah...", "Error Message", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
            }
        } catch (NullPointerException npe) {
        }
    }//GEN-LAST:event_btnSimpanTransaksiActionPerformed

    private void cbAntarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbAntarActionPerformed
        if (cbAntar.isSelected()) {
            cbStatusPengantaran.setVisible(true);
            dateAntar.setVisible(false);
            cbPengantar.setVisible(false);
            label1.setVisible(true);
            label2.setVisible(false);
            label3.setVisible(false);
            if (pelangganControl.getAllPelanggan().get(cbPelanggan.getSelectedIndex()).getIsMember() == 0) {
                txtJumlahBiayaAntar.setText("Rp " + String.valueOf(Integer.valueOf(txtBerat.getText()) * 2000) + ",-");
                txtTotal.setText("");
            }
        } else {
            cbStatusPengantaran.setVisible(false);
            dateAntar.setVisible(false);
            cbPengantar.setVisible(false);
            label1.setVisible(false);
            label2.setVisible(false);
            label3.setVisible(false);
            txtJumlahBiayaAntar.setText("Rp 0,-");
            txtTotal.setText("");
        }

    }//GEN-LAST:event_cbAntarActionPerformed

    private void btnInvoiceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInvoiceActionPerformed
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost/laundry";
            String user = "root";
            String pw = "";
            Connection con = DriverManager.getConnection(url, user, pw);
            JasperDesign jdesign = JRXmlLoader.load("D:\\Desktop\\UAS-PBOL-1873004-AlviraPuteri\\src\\iReport\\invoice.jrxml");
            String query = "select * from transaksi t\n"
                    + "join pelanggan p\n"
                    + "on t.idpelanggan = p.idpelanggan\n"
                    + "join karyawan k\n"
                    + "on t.idkaryawan = k.idkaryawan\n"
                    + "join jenislayanan l\n"
                    + "on t.idlayanan = l.idlayanan\n"
                    + "where idtransaksi = " + transaksiSelected.getIdTransaksi();

            JRDesignQuery updateQuery = new JRDesignQuery();
            updateQuery.setText(query);

            jdesign.setQuery(updateQuery);

            JasperReport jreport = JasperCompileManager.compileReport(jdesign);
            JasperPrint jprint = JasperFillManager.fillReport(jreport, null, con);
//            JasperExportManager.exportReportToPdfFile(jprint,"D:\\Desktop\\UAS-PBOL-1873004-AlviraPuteri\\src\\iReport\\lunas.pdf");

            JasperViewer.viewReport(jprint, false);
        } catch (ClassNotFoundException ex) {
            ex.toString();
        } catch (SQLException ex) {
            ex.toString();
        } catch (JRException ex) {
            ex.toString();
        }

    }//GEN-LAST:event_btnInvoiceActionPerformed

    private void btnHapusTransaksiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHapusTransaksiActionPerformed
        if (transaksiSelected.getIdTransaksi() != 0) {
            if (JOptionPane.showConfirmDialog(this, "Apakah Anda akan Menghapus Data Transaksi?", "Confirmation Message", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                if (transaksiControl.deleteOneTransaksi(transaksiSelected)) {
                    JOptionPane.showMessageDialog(this, "Data Transaksi berhasil di hapus !", "Infromation Message", JOptionPane.INFORMATION_MESSAGE);
                    resetTransaksi();
                } else {
                    JOptionPane.showMessageDialog(this, "Data Transaksi gagal di hapus !", "Error Message", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Tidak ada data yang dipilih !", "Error Message", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnHapusTransaksiActionPerformed

    private void btnResetTransaksiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetTransaksiActionPerformed
        resetTransaksi();
    }//GEN-LAST:event_btnResetTransaksiActionPerformed

    private void btnSimpanCustActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanCustActionPerformed
        try {
            if (txtNamaCust.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Nama Pelanggan harus di isi!", "Warning Message", JOptionPane.WARNING_MESSAGE);
            } else if (txtNoHpCust.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "No Hp Pelanggan harus di isi!", "Warning Message", JOptionPane.WARNING_MESSAGE);
            } else if (txtAlamatCust.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Alamat Pelanggan harus di isi!", "Warning Message", JOptionPane.WARNING_MESSAGE);
            } else if (cbMembership.getSelectedIndex() == 1 && dateMember.getDate() == null) {
                JOptionPane.showMessageDialog(this, "Tanggal Jadi Member harus di isi!", "Warning Message", JOptionPane.WARNING_MESSAGE);
            } else {
                if (row == -1) {//INSERT
                    Pelanggan e = new Pelanggan();
                    e.setNamaPelanggan(txtNamaCust.getText());
                    e.setNoTelpon(txtNoHpCust.getText());
                    e.setAlamat(txtAlamatCust.getText());
                    e.setIsMember(cbMembership.getSelectedIndex());
                    if (cbMembership.getSelectedIndex() == 1) {
                        e.setTanggalJadiMember(dateMember.getDate());
                    } else {
                        e.setTanggalJadiMember(null);
                    }
                    if (JOptionPane.showConfirmDialog(this, "Apakah Anda akan Menambah Data Pelanggan baru?", "Confirmation Message", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                        if (pelangganControl.simpanAtauUpdateOnePelanggan(e)) {
                            JOptionPane.showMessageDialog(this, "Data Pelanggan berhasil di tambah...", "Infromation Message", JOptionPane.INFORMATION_MESSAGE);
                            resetPelanggan();
                            loadAllTable();
                            dtmPelanggan.fireTableDataChanged();
                        } else {
                            JOptionPane.showMessageDialog(this, "Data Pelanggan gagal di tambah...", "Error Message", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                } else {//UPDATE
                    pelangganSelected.setNamaPelanggan(txtNamaCust.getText());
                    pelangganSelected.setNoTelpon(txtNoHpCust.getText());
                    pelangganSelected.setAlamat(txtAlamatCust.getText());
                    pelangganSelected.setIsMember(cbMembership.getSelectedIndex());
                    pelangganSelected.setTanggalJadiMember(dateMember.getDate());
                    if (JOptionPane.showConfirmDialog(this, "Apakah Anda akan Mengubah Data Pelanggan?", "Confirmation Message", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                        if (pelangganControl.simpanAtauUpdateOnePelanggan(pelangganSelected)) {
                            JOptionPane.showMessageDialog(this, "Data Pelanggan berhasil di ubah...", "Infromation Message", JOptionPane.INFORMATION_MESSAGE);
                            resetPelanggan();
                        } else {
                            JOptionPane.showMessageDialog(this, "Data Pelanggan gagal di ubah...", "Error Message", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
            }
        } catch (NullPointerException npe) {
        }
    }//GEN-LAST:event_btnSimpanCustActionPerformed

    private void btnResetCustActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetCustActionPerformed
        resetPelanggan();
    }//GEN-LAST:event_btnResetCustActionPerformed

    private void btnHapusCustActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHapusCustActionPerformed
        if (pelangganSelected.getIdPelanggan() != 0) {
            if (JOptionPane.showConfirmDialog(this, "Apakah Anda akan Menghapus Data Pelanggan?", "Confirmation Message", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                if (pelangganControl.deleteOnePelanggan(pelangganSelected)) {
                    JOptionPane.showMessageDialog(this, "Data Pelanggan berhasil di hapus !", "Infromation Message", JOptionPane.INFORMATION_MESSAGE);
                    resetPelanggan();
                } else {
                    JOptionPane.showMessageDialog(this, "Data Pelanggan gagal di hapus !", "Error Message", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Tidak ada data yang dipilih !", "Error Message", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnHapusCustActionPerformed

    private void btnSimpanEmpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanEmpActionPerformed
        boolean usernameIsExist = false;

        for (Karyawan p : karyawanControl.getAllKaryawan()) {
            if (txtUnameEmp.getText().isEmpty()) {
                usernameIsExist = false;
                break;
            } else if (txtUnameEmp.getText().equals(p.getUsername())) {
                
                usernameIsExist = true;
                break;
            }
        }

        if (txtNamaEmp.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nama Karyawan harus di isi!", "Warning Message", JOptionPane.WARNING_MESSAGE);
        } else if (txtNoHpEmp.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "No Hp Karyawan harus di isi!", "Warning Message", JOptionPane.WARNING_MESSAGE);
        } else if (txtAlamatEmp.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Alamat Karyawan harus di isi!", "Warning Message", JOptionPane.WARNING_MESSAGE);
        } else if ((cbJabatan.getSelectedIndex() == 0 || cbJabatan.getSelectedIndex() == 3 )&& txtUnameEmp.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Username Karyawan harus di isi!", "Warning Message", JOptionPane.WARNING_MESSAGE);
        } else if ((cbJabatan.getSelectedIndex() == 0 || cbJabatan.getSelectedIndex() == 3 ) && txtPassEmp.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Password Karyawan harus di isi!", "Warning Message", JOptionPane.WARNING_MESSAGE);
        } else if ((cbJabatan.getSelectedIndex() == 0 || cbJabatan.getSelectedIndex() == 3 ) && txtConfirmPass.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ketikkan ulang password karyawan!", "Warning Message", JOptionPane.WARNING_MESSAGE);
        } else if (!txtConfirmPass.getText().equals(txtPassEmp.getText())) {
            JOptionPane.showMessageDialog(this, "Password yang dimasukkan tidak sama!", "Warning Message", JOptionPane.WARNING_MESSAGE);
        } else if (usernameIsExist == true){
            JOptionPane.showMessageDialog(this, "Username sudah digunakan!", "Warning Message", JOptionPane.WARNING_MESSAGE);
        }else {
            if (row == -1) {//INSERT
                Karyawan e = new Karyawan();
                e.setNamaKaryawan(txtNamaEmp.getText());
                e.setNoTelpon(txtNoHpEmp.getText());
                e.setAlamat(txtAlamatEmp.getText());
                if (cbJabatan.getSelectedIndex() == 0 || cbJabatan.getSelectedIndex() == 3) {
                    e.setUsername(txtUnameEmp.getText());
                    e.setPassword(txtPassEmp.getText());
                } else {
                    e.setUsername("");
                    e.setPassword("");
                }
                e.setIdJabatan((Jabatan) cbJabatan.getSelectedItem());

                if (JOptionPane.showConfirmDialog(this, "Apakah Anda akan Menambah Data Karyawan baru?", "Confirmation Message", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                    if (karyawanControl.simpanAtauUpdateOneKaryawan(e)) {
                        JOptionPane.showMessageDialog(this, "Data Karyawan berhasil di tambah...", "Infromation Message", JOptionPane.INFORMATION_MESSAGE);
                        resetKaryawan();
                    } else {
                        JOptionPane.showMessageDialog(this, "Data Karyawan gagal di tambah...", "Error Message", JOptionPane.ERROR_MESSAGE);
                    }
                }

            } else {//UPDATE
                karyawanSelected.setNamaKaryawan(txtNamaEmp.getText());
                karyawanSelected.setNoTelpon(txtNoHpEmp.getText());
                karyawanSelected.setAlamat(txtAlamatEmp.getText());
               if (cbJabatan.getSelectedIndex() == 0 || cbJabatan.getSelectedIndex() == 3) {
                    karyawanSelected.setUsername(txtUnameEmp.getText());
                    karyawanSelected.setPassword(txtPassEmp.getText());
                } else {
                    karyawanSelected.setUsername("");
                    karyawanSelected.setPassword("");
                }
                karyawanSelected.setIdJabatan((Jabatan) cbJabatan.getSelectedItem());
                if (JOptionPane.showConfirmDialog(this, "Apakah Anda akan Mengubah Data Karyawan?", "Confirmation Message", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                    if (karyawanControl.simpanAtauUpdateOneKaryawan(karyawanSelected)) {
                        JOptionPane.showMessageDialog(this, "Data Karyawan berhasil di ubah...", "Infromation Message", JOptionPane.INFORMATION_MESSAGE);
                        resetKaryawan();
                    } else {
                        JOptionPane.showMessageDialog(this, "Data Karyawan gagal di ubah...", "Error Message", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        }
    }//GEN-LAST:event_btnSimpanEmpActionPerformed

    private void btnResetEmpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetEmpActionPerformed
        resetKaryawan();
    }//GEN-LAST:event_btnResetEmpActionPerformed

    private void btnHapusEmpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHapusEmpActionPerformed
        if (karyawanSelected.getIdKaryawan() != 0) {
            if (JOptionPane.showConfirmDialog(this, "Apakah Anda akan Menghapus Data Karyawan?", "Confirmation Message", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                if (karyawanControl.deleteOneKaryawan(karyawanSelected)) {
                    JOptionPane.showMessageDialog(this, "Data Karyawan berhasil di hapus !", "Infromation Message", JOptionPane.INFORMATION_MESSAGE);
                    resetKaryawan();
                } else {
                    JOptionPane.showMessageDialog(this, "Data Karyawan gagal di hapus !", "Error Message", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Tidak ada data yang dipilih !", "Error Message", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnHapusEmpActionPerformed

    private void btnSimpanJabatanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanJabatanActionPerformed
        try {
            if (txtNamaJabatan.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Nama Jabatan harus di isi!", "Warning Message", JOptionPane.WARNING_MESSAGE);
            } else {
                if (row == -1) {//INSERT
                    Jabatan e = new Jabatan();
                    e.setNamaJabatan(txtNamaJabatan.getText());
                    e.setDeskripsi(txtDeskripsi.getText());
                    if (JOptionPane.showConfirmDialog(this, "Apakah Anda akan Menambah Data Jabatan baru?", "Confirmation Message", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                        if (jabatanControl.simpanAtauUpdateOneJabatan(e)) {
                            JOptionPane.showMessageDialog(this, "Data Jabatan berhasil di tambah...", "Infromation Message", JOptionPane.INFORMATION_MESSAGE);
                            resetJabatan();
                        } else {
                            JOptionPane.showMessageDialog(this, "Data Jabatan gagal di tambah...", "Error Message", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                } else {//UPDATE
                    jabatanSelected.setNamaJabatan(txtNamaJabatan.getText());
                    jabatanSelected.setDeskripsi(txtDeskripsi.getText());
                    if (JOptionPane.showConfirmDialog(this, "Apakah Anda akan Mengubah Data Jabatan?", "Confirmation Message", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                        if (jabatanControl.simpanAtauUpdateOneJabatan(jabatanSelected)) {
                            JOptionPane.showMessageDialog(this, "Data Jabatan berhasil di ubah...", "Infromation Message", JOptionPane.INFORMATION_MESSAGE);
                            resetJabatan();
                        } else {
                            JOptionPane.showMessageDialog(this, "Data Jabatan gagal di ubah...", "Error Message", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
            }
        } catch (NullPointerException npe) {
        }
    }//GEN-LAST:event_btnSimpanJabatanActionPerformed

    private void btnResetJabatanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetJabatanActionPerformed
        resetJabatan();
    }//GEN-LAST:event_btnResetJabatanActionPerformed

    private void btnHapusJabatanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHapusJabatanActionPerformed
        if (jabatanSelected.getIdJabatan() != 0) {
            if (JOptionPane.showConfirmDialog(this, "Apakah Anda akan Menghapus Data Jabatan?", "Confirmation Message", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                if (jabatanControl.deleteOneJabatan(jabatanSelected)) {
                    JOptionPane.showMessageDialog(this, "Data Jabatan berhasil di hapus !", "Infromation Message", JOptionPane.INFORMATION_MESSAGE);
                    resetJabatan();
                } else {
                    JOptionPane.showMessageDialog(this, "Data Jabatan gagal di hapus !", "Error Message", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Tidak ada data yang dipilih !", "Error Message", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnHapusJabatanActionPerformed

    private void btnSimpanInvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanInvActionPerformed
        try {
            if (txtNamaInv.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Nama Inventory harus di isi!", "Warning Message", JOptionPane.WARNING_MESSAGE);
            } else if (spStock.getValue().equals(0)) {
                JOptionPane.showMessageDialog(this, "Stock Inventory harus di isi!", "Warning Message", JOptionPane.WARNING_MESSAGE);
            } else {
                if (row == -1) {//INSERT
                    Inventory e = new Inventory();
                    e.setNamaInventory(txtNamaInv.getText());
                    e.setStock((int) spStock.getValue());
                    if (JOptionPane.showConfirmDialog(this, "Apakah Anda akan Menambah Data Inventory baru?", "Confirmation Message", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                        if (inventoryControl.simpanAtauUpdateOneInventory(e)) {
                            JOptionPane.showMessageDialog(this, "Data Inventory berhasil di tambah...", "Infromation Message", JOptionPane.INFORMATION_MESSAGE);
                            resetInventory();
                        } else {
                            JOptionPane.showMessageDialog(this, "Data Inventory gagal di tambah...", "Error Message", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                } else {//UPDATE
                    inventorySelected.setNamaInventory(txtNamaInv.getText());
                    inventorySelected.setStock((int) spStock.getValue());
                    if (JOptionPane.showConfirmDialog(this, "Apakah Anda akan Mengubah Data Inventory?", "Confirmation Message", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                        if (inventoryControl.simpanAtauUpdateOneInventory(inventorySelected)) {
                            JOptionPane.showMessageDialog(this, "Data Inventory berhasil di ubah...", "Infromation Message", JOptionPane.INFORMATION_MESSAGE);
                            resetInventory();
                        } else {
                            JOptionPane.showMessageDialog(this, "Data Inventory gagal di ubah...", "Error Message", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
            }
        } catch (NullPointerException npe) {
        }
    }//GEN-LAST:event_btnSimpanInvActionPerformed

    private void btnResetInvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetInvActionPerformed
        resetInventory();
    }//GEN-LAST:event_btnResetInvActionPerformed

    private void btnHapusInvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHapusInvActionPerformed
        if (inventorySelected.getIdInventory() != 0) {
            if (JOptionPane.showConfirmDialog(this, "Apakah Anda akan Menghapus Data Inventory?", "Confirmation Message", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                if (inventoryControl.deleteOneInventory(inventorySelected)) {
                    JOptionPane.showMessageDialog(this, "Data Inventory berhasil di hapus !", "Infromation Message", JOptionPane.INFORMATION_MESSAGE);
                    resetInventory();
                } else {
                    JOptionPane.showMessageDialog(this, "Data Inventory gagal di hapus !", "Error Message", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Tidak ada data yang dipilih !", "Error Message", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnHapusInvActionPerformed

    private void btnSimpanLayananActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanLayananActionPerformed
        try {
            if (txtIdLayanan.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "ID Layanan harus di isi!", "Warning Message", JOptionPane.WARNING_MESSAGE);
            } else if (txtIdLayanan.getText().trim().length() > 4) {
                JOptionPane.showMessageDialog(this, "ID Layanan tidak boleh lebih dari 4 huruf!", "Warning Message", JOptionPane.WARNING_MESSAGE);
            } else if (txtNamaaLayanan.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Nama Layanan harus di isi!", "Warning Message", JOptionPane.WARNING_MESSAGE);
            } else if (txtHargaLayanan.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Harga Layanan harus di isi!", "Warning Message", JOptionPane.WARNING_MESSAGE);
            } else if (spStockDipakai.getValue().equals(0)) {
                JOptionPane.showMessageDialog(this, "Stock Dipakai harus di isi!", "Warning Message", JOptionPane.WARNING_MESSAGE);
            } else {
                if (row == -1) {//INSERT
                    Jenislayanan e = new Jenislayanan();
                    e.setIdLayanan(txtIdLayanan.getText());
                    e.setNamaLayanan(txtNamaaLayanan.getText());
                    e.setHarga(Integer.parseInt(txtHargaLayanan.getText()));
                    e.setIdInventory((Inventory) cbInventory.getSelectedItem());
                    e.setStockDipakai(Integer.valueOf(spStockDipakai.getValue().toString()));
                    if (JOptionPane.showConfirmDialog(this, "Apakah Anda akan Menambah Data Layanan baru?", "Confirmation Message", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                        if (layananControl.simpanAtauUpdateOneLayanan(e)) {
                            JOptionPane.showMessageDialog(this, "Data Layanan berhasil di tambah...", "Infromation Message", JOptionPane.INFORMATION_MESSAGE);
                            resetLayanan();
                        } else {
                            JOptionPane.showMessageDialog(this, "Data Layanan gagal di tambah...", "Error Message", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                } else {//UPDATE
                    jenislayananSelected.setIdLayanan(txtIdLayanan.getText());
                    jenislayananSelected.setNamaLayanan(txtNamaaLayanan.getText());
                    jenislayananSelected.setHarga(Integer.parseInt(txtHargaLayanan.getText()));
                    jenislayananSelected.setIdInventory((Inventory) cbInventory.getSelectedItem());
                    jenislayananSelected.setStockDipakai(Integer.valueOf(spStockDipakai.getValue().toString()));
                    if (JOptionPane.showConfirmDialog(this, "Apakah Anda akan Mengubah Data Layanan?", "Confirmation Message", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                        if (layananControl.simpanAtauUpdateOneLayanan(jenislayananSelected)) {
                            JOptionPane.showMessageDialog(this, "Data Layanan berhasil di ubah...", "Infromation Message", JOptionPane.INFORMATION_MESSAGE);
                            resetLayanan();
                        } else {
                            JOptionPane.showMessageDialog(this, "Data Layanan gagal di ubah...", "Error Message", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
            }
        } catch (NullPointerException npe) {
        }
    }//GEN-LAST:event_btnSimpanLayananActionPerformed

    private void btnResetLayananActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetLayananActionPerformed
        resetLayanan();
    }//GEN-LAST:event_btnResetLayananActionPerformed

    private void btnHapusLayananActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHapusLayananActionPerformed
        if (!jenislayananSelected.getIdLayanan().equals(null)) {
            if (JOptionPane.showConfirmDialog(this, "Apakah Anda akan Menghapus Data Layanan?", "Confirmation Message", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                if (layananControl.deleteOneLayanan(jenislayananSelected)) {
                    JOptionPane.showMessageDialog(this, "Data Layanan berhasil di hapus !", "Infromation Message", JOptionPane.INFORMATION_MESSAGE);
                    resetLayanan();
                } else {
                    JOptionPane.showMessageDialog(this, "Data Layanan gagal di hapus !", "Error Message", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Tidak ada data yang dipilih !", "Error Message", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnHapusLayananActionPerformed

    private void cbPelangganActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbPelangganActionPerformed
        aidipelanggan = cbPelanggan.getSelectedIndex() + 1;
        for (Pelanggan p : pelangganControl.getAllPelanggan()) {
            if (aidipelanggan == p.getIdPelanggan()) {
                member1 = String.valueOf(p.getIsMember());
                if (member1.equals("1")) {
                    member1 = "Aktif";
                } else {
                    member1 = "Tidak Aktif";
                }
                txtStatusMember.setText("Status Membership : " + member1);
                break;
            }
        }
        if (cbAntar.isSelected()) {
            if (member1.equals("Aktif")) {
                txtJumlahBiayaAntar.setText("Rp 0,-");
            } else {
                txtJumlahBiayaAntar.setText("Rp " + (2000 * berat) + ",-");
            }
            txtTotal.setText("");
        }
    }//GEN-LAST:event_cbPelangganActionPerformed

    private void btnTambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTambahActionPerformed
        mdPelanggan.setVisible(true);
        dtTransaksi.setVisible(false);
    }//GEN-LAST:event_btnTambahActionPerformed

    private void cbLayananActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbLayananActionPerformed
        int dipakai = 0;
        hargaLayanan = Double.valueOf(layananControl.getAllLayanan().get(cbLayanan.getSelectedIndex()).getHarga());
        berat = Double.valueOf(txtBerat.getText());
        txtDeskLayanan.setText("Deskripsi : " + layananControl.getAllLayanan().get(cbLayanan.getSelectedIndex()).getNamaLayanan());
        txthargalayanan.setText("Rp " + layananControl.getAllLayanan().get(cbLayanan.getSelectedIndex()).getHarga() + ",- /kg");
        txtJumlahHarga.setText("Rp " + (hargaLayanan * berat) + ",-");
        txtTotal.setText("");
    }//GEN-LAST:event_cbLayananActionPerformed

    private void txtBeratActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBeratActionPerformed
        hargaLayanan = Double.valueOf(layananControl.getAllLayanan().get(cbLayanan.getSelectedIndex()).getHarga());
        berat = Double.valueOf(txtBerat.getText());
        txtJumlahHarga.setText("Rp " + (hargaLayanan * berat) + ",-");
        if (member1.equals("Aktif")) {
            txtJumlahBiayaAntar.setText("Rp 0,-");
        } else {
            txtJumlahBiayaAntar.setText("Rp " + (2000 * berat) + ",-");
        }
        txtTotal.setText("");
    }//GEN-LAST:event_txtBeratActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        hargaLayanan = Double.valueOf(layananControl.getAllLayanan().get(cbLayanan.getSelectedIndex()).getHarga());
        berat = Double.valueOf(txtBerat.getText());
        if (cbAntar.isSelected()) {
            if (member1.equals("Aktif")) {
                jumlahBiayaAntar = 0;
            } else {
                jumlahBiayaAntar = 2000 * berat;
            }
        } else {
            jumlahBiayaAntar = 0;
        }
        jumlahLayanan = hargaLayanan * berat;
        total = String.valueOf(jumlahBiayaAntar + jumlahLayanan);
        txtTotal.setText(total);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void menuPelangganActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuPelangganActionPerformed
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost/laundry";
            String user = "root";
            String pw = "";
            Connection con = DriverManager.getConnection(url, user, pw);
            JasperDesign jdesign = JRXmlLoader.load("D:\\Desktop\\UAS-PBOL-1873004-AlviraPuteri\\src\\iReport\\pelanggan.jrxml");
            String query = "select * from pelanggan";

            JRDesignQuery updateQuery = new JRDesignQuery();
            updateQuery.setText(query);

            jdesign.setQuery(updateQuery);

            JasperReport jreport = JasperCompileManager.compileReport(jdesign);
            JasperPrint jprint = JasperFillManager.fillReport(jreport, null, con);
            JasperExportManager.exportReportToPdfFile(jprint, "D:\\Desktop\\UAS-PBOL-1873004-AlviraPuteri\\src\\iReport\\pelanggan.pdf");

            JasperViewer.viewReport(jprint, false);
        } catch (ClassNotFoundException ex) {
            ex.toString();
        } catch (SQLException ex) {
            ex.toString();
        } catch (JRException ex) {
            ex.toString();
        }
    }//GEN-LAST:event_menuPelangganActionPerformed

    private void menuLunasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuLunasActionPerformed
        lunas = new JDialog(this, "Laporan Transaksi Lunas");
        lunas.add(panelLunas);
        txtPilihBulan.setVisible(false);
        txtPilihTanggal.setVisible(false);
        datePilihTanggal.setVisible(false);
        datePilihBulan.setVisible(false);
        lunas.setSize(400, 400);
        lunas.setLocation(300, 100);
        lunas.setVisible(true);
        lunas.setModal(true);
    }//GEN-LAST:event_menuLunasActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        d = new JDialog(this, "Cari Pelanggan");
        d.add(cariPelanggan);
        loadTableCariPelanggan();
        d.setSize(1300, 700);
        d.setLocation(300, 100);
        d.setVisible(true);
        d.setModal(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        d.setVisible(false);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void btnCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCariActionPerformed
        int temp = 0;
        boolean check = false;
        for (int i = 0; i < pelangganControl.getAllPelanggan().size(); i++) {
            if (pelangganControl.getAllPelanggan().get(i).getNamaPelanggan().trim().toLowerCase().contains(txtCariCust.getText().trim().toLowerCase())) {
                temp = 1;
                check = true;
                break;
            } else {
                check = false;
            }

        }
        if (check == true) {
            loadTableCariPelanggan2(txtCariCust.getText());
        } else
            JOptionPane.showMessageDialog(this, "Data tidak ditemukan!", "Warning Message", JOptionPane.WARNING_MESSAGE);
    }//GEN-LAST:event_btnCariActionPerformed

    private void btnCariPelangganActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCariPelangganActionPerformed
        int temp = 0;
        boolean check = false;
        for (int i = 0; i < pelangganControl.getAllPelanggan().size(); i++) {
            if (pelangganControl.getAllPelanggan().get(i).getNamaPelanggan().trim().toLowerCase().contains(txtCariPelanggan.getText().trim().toLowerCase())) {
                temp = 1;
                check = true;
                break;
            } else {
                check = false;
            }

        }
        if (check == true) {
            loadTablePelanggan2(txtCariPelanggan.getText());
        } else
            JOptionPane.showMessageDialog(this, "Data tidak ditemukan!", "Warning Message", JOptionPane.WARNING_MESSAGE);
    }//GEN-LAST:event_btnCariPelangganActionPerformed

    private void btnCariInventoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCariInventoryActionPerformed
        int temp = 0;
        boolean check = false;
        for (int i = 0; i < inventoryControl.getAllInventory().size(); i++) {
            if (inventoryControl.getAllInventory().get(i).getNamaInventory().trim().toLowerCase().contains(txtCariInventory.getText().trim().toLowerCase())) {
                temp = i;
                check = true;
                break;
            } else {
                check = false;
            }

        }
        if (check == true) {
            loadTableInventory2(txtCariInventory.getText());
        } else {
            JOptionPane.showMessageDialog(this, "Data tidak ditemukan!", "Warning Message", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnCariInventoryActionPerformed

    private void btnCariJabatanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCariJabatanActionPerformed
        int temp = 0;
        boolean check = false;
        for (int i = 0; i < jabatanControl.getAllJabatan().size(); i++) {
            if (jabatanControl.getAllJabatan().get(i).getNamaJabatan().trim().toLowerCase().contains(txtCariJabatan.getText().trim().toLowerCase())) {
                temp = i;
                check = true;
                break;
            } else {
                check = false;
            }

        }
        if (check == true) {
            loadTableJabatan2(txtCariJabatan.getText());
        } else {
            JOptionPane.showMessageDialog(this, "Data tidak ditemukan!", "Warning Message", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnCariJabatanActionPerformed

    private void btnCariKaryawanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCariKaryawanActionPerformed
        int temp = 0;
        boolean check = false;
        for (int i = 0; i < karyawanControl.getAllKaryawan().size(); i++) {
            if (karyawanControl.getAllKaryawan().get(i).getNamaKaryawan().trim().toLowerCase().contains(txtCariKaryawan.getText().trim().toLowerCase())) {
                temp = i;
                check = true;
                break;
            } else {
                check = false;
            }

        }
        if (check == true) {
            loadTableKaryawan2(txtCariKaryawan.getText());
        } else {
            JOptionPane.showMessageDialog(this, "Data tidak ditemukan!", "Warning Message", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnCariKaryawanActionPerformed

    private void btnCariLayananActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCariLayananActionPerformed
        int temp = 0;
        boolean check = false;
        for (int i = 0; i < layananControl.getAllLayanan().size(); i++) {
            if (layananControl.getAllLayanan().get(i).getNamaLayanan().trim().toLowerCase().contains(txtCariLayanan.getText().trim().toLowerCase())) {
                temp = i;
                check = true;
                break;
            } else {
                check = false;
            }

        }
        if (check == true) {
            loadTableLayanan2(txtCariLayanan.getText());
        } else {
            JOptionPane.showMessageDialog(this, "Data tidak ditemukan!", "Warning Message", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnCariLayananActionPerformed

    private void btnResetPelangganActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetPelangganActionPerformed
        loadTablePelanggan();
        resetPelanggan();
    }//GEN-LAST:event_btnResetPelangganActionPerformed

    private void btnResetKaryawanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetKaryawanActionPerformed
        loadTableKaryawan();
        resetKaryawan();
    }//GEN-LAST:event_btnResetKaryawanActionPerformed

    private void btnResetJobActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetJobActionPerformed
        loadTableJabatan();
        resetJabatan();
    }//GEN-LAST:event_btnResetJobActionPerformed

    private void btnResetInventoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetInventoryActionPerformed
        loadTableInventory();
        resetInventory();
    }//GEN-LAST:event_btnResetInventoryActionPerformed

    private void btnResetServActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetServActionPerformed
        loadTableLayanan();
        resetLayanan();
    }//GEN-LAST:event_btnResetServActionPerformed

    private void menuBelumLunasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuBelumLunasActionPerformed
        belumLunas = new JDialog(this, "Laporan Transaksi Belum Lunas");
        belumLunas.add(panelBelumLunas);
        txtPilihBulan1.setVisible(false);
        txtPilihTanggal1.setVisible(false);
        datePilihTanggal1.setVisible(false);
        datePilihBulan1.setVisible(false);
        belumLunas.setSize(400, 400);
        belumLunas.setLocation(300, 100);
        belumLunas.setVisible(true);
        belumLunas.setModal(true);
    }//GEN-LAST:event_menuBelumLunasActionPerformed

    private void menuInventoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuInventoryActionPerformed
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost/laundry";
            String user = "root";
            String pw = "";
            Connection con = DriverManager.getConnection(url, user, pw);
            JasperDesign jdesign = JRXmlLoader.load("D:\\Desktop\\UAS-PBOL-1873004-AlviraPuteri\\src\\iReport\\inventory.jrxml");
            String query = "select * from transaksi t\n"
                    + "join jenislayanan l\n"
                    + "on t.idlayanan = l.idlayanan\n"
                    + "join inventory i\n"
                    + "on l.idinventory = i.idinventory order by i.namainventory ";

            JRDesignQuery updateQuery = new JRDesignQuery();
            updateQuery.setText(query);

            jdesign.setQuery(updateQuery);

            JasperReport jreport = JasperCompileManager.compileReport(jdesign);
            JasperPrint jprint = JasperFillManager.fillReport(jreport, null, con);
            JasperExportManager.exportReportToPdfFile(jprint,"D:\\Desktop\\UAS-PBOL-1873004-AlviraPuteri\\src\\iReport\\inventory.pdf");

            JasperViewer.viewReport(jprint, false);
        } catch (ClassNotFoundException ex) {
            ex.toString();
        } catch (SQLException ex) {
            ex.toString();
        } catch (JRException ex) {
            ex.toString();
        }
    }//GEN-LAST:event_menuInventoryActionPerformed

    private void pilihTanggal1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pilihTanggal1ActionPerformed
        if (pilihTanggal1.isSelected()) {
            txtPilihTanggal1.setVisible(true);
            datePilihTanggal1.setVisible(true);
        } else {
            txtPilihTanggal1.setVisible(false);
            datePilihTanggal1.setVisible(false);
        }
        txtPilihBulan1.setVisible(false);
        datePilihBulan1.setVisible(false);
    }//GEN-LAST:event_pilihTanggal1ActionPerformed

    private void pilihBulan1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pilihBulan1ActionPerformed
        if (pilihBulan1.isSelected()) {
            txtPilihBulan1.setVisible(true);
            datePilihBulan1.setVisible(true);
        } else {
            txtPilihBulan1.setVisible(false);
            datePilihBulan1.setVisible(false);
        }
        txtPilihTanggal1.setVisible(false);
        datePilihTanggal1.setVisible(false);
    }//GEN-LAST:event_pilihBulan1ActionPerformed

    private void jRadioButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton4ActionPerformed
        txtPilihBulan1.setVisible(false);
        datePilihBulan1.setVisible(false);
        txtPilihTanggal1.setVisible(false);
        datePilihTanggal1.setVisible(false);
    }//GEN-LAST:event_jRadioButton4ActionPerformed

    private void okBelumLunasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okBelumLunasActionPerformed
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost/laundry";
            String user = "root";
            String pw = "";
            Connection con = DriverManager.getConnection(url, user, pw);
            JasperDesign jdesign = JRXmlLoader.load("D:\\Desktop\\UAS-PBOL-1873004-AlviraPuteri\\src\\iReport\\belum lunas.jrxml");

            String query = "";
            if (pilihTanggal1.isSelected()) {
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
                String firstDay = formatter.format(datePilihTanggal1.getDate());
                System.out.println(firstDay);
                query = "select * from transaksi t\n"
                        + "join pelanggan p\n"
                        + "on t.idpelanggan = p.idpelanggan\n"
                        + "join karyawan k\n"
                        + "on t.idkaryawan = k.idkaryawan\n"
                        + "join jenislayanan l\n"
                        + "on t.idlayanan = l.idlayanan\n"
                        + "where statustransaksi = 0 and t.tanggalterima ='" + firstDay + "' order by idtransaksi";
            } else if (pilihBulan1.isSelected()) {
                String firstDay = "2020/" + (datePilihBulan1.getMonth() + 1) + "/01";
                String lastDay = "2020/" + (datePilihBulan1.getMonth() + 1) + "/30";

                query = "select * from transaksi t\n"
                        + "join pelanggan p\n"
                        + "on t.idpelanggan = p.idpelanggan\n"
                        + "join karyawan k\n"
                        + "on t.idkaryawan = k.idkaryawan\n"
                        + "join jenislayanan l\n"
                        + "on t.idlayanan = l.idlayanan\n"
                        + "where statustransaksi = 0 and t.tanggalterima between '" + firstDay + "' and '" + lastDay + "' order by idtransaksi";
            } else {
                query = "select * from transaksi t\n"
                        + "join pelanggan p\n"
                        + "on t.idpelanggan = p.idpelanggan\n"
                        + "join karyawan k\n"
                        + "on t.idkaryawan = k.idkaryawan\n"
                        + "join jenislayanan l\n"
                        + "on t.idlayanan = l.idlayanan\n"
                        + "where statustransaksi = 0 order by idtransaksi";
            }

            JRDesignQuery updateQuery = new JRDesignQuery();
            updateQuery.setText(query);

            jdesign.setQuery(updateQuery);

            JasperReport jreport = JasperCompileManager.compileReport(jdesign);
            JasperPrint jprint = JasperFillManager.fillReport(jreport, null, con);
            JasperExportManager.exportReportToPdfFile(jprint,"D:\\Desktop\\UAS-PBOL-1873004-AlviraPuteri\\src\\iReport\\belum lunas.pdf");

            JasperViewer.viewReport(jprint, false);
        } catch (ClassNotFoundException ex) {
            ex.toString();
        } catch (SQLException ex) {
            ex.toString();
        } catch (JRException ex) {
            ex.toString();
        }

    }//GEN-LAST:event_okBelumLunasActionPerformed

    private void txtPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPasswordActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPasswordActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        loadTableCariPelanggan();
        resetPelanggan();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void pilihTanggalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pilihTanggalActionPerformed
        if (pilihTanggal.isSelected()) {
            txtPilihTanggal.setVisible(true);
            datePilihTanggal.setVisible(true);
        } else {
            txtPilihTanggal.setVisible(false);
            datePilihTanggal.setVisible(false);
        }
        txtPilihBulan.setVisible(false);
        datePilihBulan.setVisible(false);
    }//GEN-LAST:event_pilihTanggalActionPerformed

    private void pilihBulanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pilihBulanActionPerformed
        if (pilihBulan.isSelected()) {
            txtPilihBulan.setVisible(true);
            datePilihBulan.setVisible(true);
        } else {
            txtPilihBulan.setVisible(false);
            datePilihBulan.setVisible(false);
        }
        txtPilihTanggal.setVisible(false);
        datePilihTanggal.setVisible(false);

    }//GEN-LAST:event_pilihBulanActionPerformed

    private void jRadioButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton5ActionPerformed
        txtPilihBulan.setVisible(false);
        datePilihBulan.setVisible(false);
        txtPilihTanggal.setVisible(false);
        datePilihTanggal.setVisible(false);
    }//GEN-LAST:event_jRadioButton5ActionPerformed

    private void okBelumLunas1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okBelumLunas1ActionPerformed
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost/laundry";
            String user = "root";
            String pw = "";
            Connection con = DriverManager.getConnection(url, user, pw);
            JasperDesign jdesign = JRXmlLoader.load("D:\\Desktop\\UAS-PBOL-1873004-AlviraPuteri\\src\\iReport\\lunas.jrxml");

            String query = "";
            if (pilihTanggal.isSelected()) {
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
                String firstDay = formatter.format(datePilihTanggal.getDate());
                System.out.println(firstDay);
                query = "select * from transaksi t\n"
                        + "join pelanggan p\n"
                        + "on t.idpelanggan = p.idpelanggan\n"
                        + "join karyawan k\n"
                        + "on t.idkaryawan = k.idkaryawan\n"
                        + "join jenislayanan l\n"
                        + "on t.idlayanan = l.idlayanan\n"
                        + "where statustransaksi = 1 and t.tanggalterima ='" + firstDay + "' order by idtransaksi";
            } else if (pilihBulan.isSelected()) {
                String firstDay = "2020/" + (datePilihBulan.getMonth() + 1) + "/01";
                String lastDay = "2020/" + (datePilihBulan.getMonth() + 1) + "/30";

                query = "select * from transaksi t\n"
                        + "join pelanggan p\n"
                        + "on t.idpelanggan = p.idpelanggan\n"
                        + "join karyawan k\n"
                        + "on t.idkaryawan = k.idkaryawan\n"
                        + "join jenislayanan l\n"
                        + "on t.idlayanan = l.idlayanan\n"
                        + "where statustransaksi = 1 and t.tanggalterima between '" + firstDay + "' and '" + lastDay + "' order by idtransaksi";
            } else {
                query = "select * from transaksi t\n"
                        + "join pelanggan p\n"
                        + "on t.idpelanggan = p.idpelanggan\n"
                        + "join karyawan k\n"
                        + "on t.idkaryawan = k.idkaryawan\n"
                        + "join jenislayanan l\n"
                        + "on t.idlayanan = l.idlayanan\n"
                        + "where statustransaksi = 1 order by idtransaksi";
            }

            JRDesignQuery updateQuery = new JRDesignQuery();
            updateQuery.setText(query);

            jdesign.setQuery(updateQuery);

            JasperReport jreport = JasperCompileManager.compileReport(jdesign);
            JasperPrint jprint = JasperFillManager.fillReport(jreport, null, con);
            JasperExportManager.exportReportToPdfFile(jprint,"D:\\Desktop\\UAS-PBOL-1873004-AlviraPuteri\\src\\iReport\\lunas.pdf");

            JasperViewer.viewReport(jprint, false);
        } catch (ClassNotFoundException ex) {
            ex.toString();
        } catch (SQLException ex) {
            ex.toString();
        } catch (JRException ex) {
            ex.toString();
        }
    }//GEN-LAST:event_okBelumLunas1ActionPerformed

    private void cbStatusPengantaranActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbStatusPengantaranActionPerformed
        if (cbStatusPengantaran.getSelectedIndex() == 1) {
            dateAntar.setVisible(true);
            cbPengantar.setVisible(true);
            label2.setVisible(true);
            label3.setVisible(true);
        } else {
            dateAntar.setVisible(false);
            cbPengantar.setVisible(false);
            label2.setVisible(false);
            label3.setVisible(false);
        }
    }//GEN-LAST:event_cbStatusPengantaranActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainApps.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainApps.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainApps.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainApps.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainApps().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel about;
    private javax.swing.JMenuItem aboutMenu;
    private javax.swing.JButton btnCari;
    private javax.swing.JButton btnCariInventory;
    private javax.swing.JButton btnCariJabatan;
    private javax.swing.JButton btnCariKaryawan;
    private javax.swing.JButton btnCariLayanan;
    private javax.swing.JButton btnCariPelanggan;
    private javax.swing.JButton btnHapusCust;
    private javax.swing.JButton btnHapusEmp;
    private javax.swing.JButton btnHapusInv;
    private javax.swing.JButton btnHapusJabatan;
    private javax.swing.JButton btnHapusLayanan;
    private javax.swing.JButton btnHapusTransaksi;
    private javax.swing.JButton btnInvoice;
    private javax.swing.JButton btnLogin;
    private javax.swing.JButton btnResetCust;
    private javax.swing.JButton btnResetEmp;
    private javax.swing.JButton btnResetInv;
    private javax.swing.JButton btnResetInventory;
    private javax.swing.JButton btnResetJabatan;
    private javax.swing.JButton btnResetJob;
    private javax.swing.JButton btnResetKaryawan;
    private javax.swing.JButton btnResetLayanan;
    private javax.swing.JButton btnResetPelanggan;
    private javax.swing.JButton btnResetServ;
    private javax.swing.JButton btnResetTransaksi;
    private javax.swing.JButton btnSimpanCust;
    private javax.swing.JButton btnSimpanEmp;
    private javax.swing.JButton btnSimpanInv;
    private javax.swing.JButton btnSimpanJabatan;
    private javax.swing.JButton btnSimpanLayanan;
    private javax.swing.JButton btnSimpanTransaksi;
    private javax.swing.JButton btnTambah;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JPanel cariPelanggan;
    private javax.swing.JCheckBox cbAntar;
    private javax.swing.JComboBox<String> cbInventory;
    private javax.swing.JComboBox<String> cbJabatan;
    private javax.swing.JComboBox<String> cbKaryawan;
    private javax.swing.JComboBox<String> cbLayanan;
    private javax.swing.JComboBox<String> cbMembership;
    private javax.swing.JComboBox<String> cbPelanggan;
    private javax.swing.JComboBox<String> cbPengantar;
    private javax.swing.JComboBox<String> cbStatusLaundry;
    private javax.swing.JComboBox<String> cbStatusPengantaran;
    private javax.swing.JComboBox<String> cbStatusTransaksi;
    private javax.swing.JMenuItem custMenu;
    private com.toedter.calendar.JDateChooser dateAntar;
    private com.toedter.calendar.JDateChooser dateMember;
    private com.toedter.calendar.JMonthChooser datePilihBulan;
    private com.toedter.calendar.JMonthChooser datePilihBulan1;
    private com.toedter.calendar.JDateChooser datePilihTanggal;
    private com.toedter.calendar.JDateChooser datePilihTanggal1;
    private com.toedter.calendar.JDateChooser dateSelesai;
    private com.toedter.calendar.JDateChooser dateTerima;
    private javax.swing.JPanel dtTransaksi;
    private javax.swing.JMenuItem empMenu;
    private javax.swing.JMenuItem exitMenu;
    private javax.swing.JPanel home;
    private javax.swing.JMenuItem homeMenu;
    private javax.swing.JMenuItem invMenu;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JLabel jLabel76;
    private javax.swing.JLabel jLabel77;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JRadioButton jRadioButton4;
    private javax.swing.JRadioButton jRadioButton5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JPopupMenu.Separator jSeparator3;
    private javax.swing.JLabel jlabel;
    private javax.swing.JMenuItem jobMenu;
    private javax.swing.JLabel label1;
    private javax.swing.JLabel label2;
    private javax.swing.JLabel label3;
    private javax.swing.JMenuItem laundryMenu;
    private javax.swing.JMenuItem logOut;
    private javax.swing.JPanel login;
    private javax.swing.JPanel mdInventory;
    private javax.swing.JPanel mdJabatan;
    private javax.swing.JPanel mdKaryawan;
    private javax.swing.JPanel mdLayanan;
    private javax.swing.JPanel mdPelanggan;
    private javax.swing.JMenuItem menuBelumLunas;
    private javax.swing.JMenuItem menuInventory;
    private javax.swing.JMenuItem menuLunas;
    private javax.swing.JMenuItem menuPelanggan;
    private javax.swing.JButton okBelumLunas;
    private javax.swing.JButton okBelumLunas1;
    private javax.swing.JPanel panelBelumLunas;
    private javax.swing.JPanel panelLunas;
    private javax.swing.JRadioButton pilihBulan;
    private javax.swing.JRadioButton pilihBulan1;
    private javax.swing.JRadioButton pilihTanggal;
    private javax.swing.JRadioButton pilihTanggal1;
    private javax.swing.JMenuItem servMenu;
    private javax.swing.JSpinner spStock;
    private javax.swing.JSpinner spStockDipakai;
    private javax.swing.JTable tableDataCariPelanggan;
    private javax.swing.JTable tableDataCust;
    private javax.swing.JTable tableDataEmp;
    private javax.swing.JTable tableDataInventory;
    private javax.swing.JTable tableDataJabatan;
    private javax.swing.JTable tableDataLayanan;
    private javax.swing.JTable tableDataTransaksi;
    private javax.swing.JTextArea txtAlamatCust;
    private javax.swing.JTextArea txtAlamatEmp;
    private javax.swing.JTextField txtBerat;
    private javax.swing.JTextField txtCariCust;
    private javax.swing.JTextField txtCariInventory;
    private javax.swing.JTextField txtCariJabatan;
    private javax.swing.JTextField txtCariKaryawan;
    private javax.swing.JTextField txtCariLayanan;
    private javax.swing.JTextField txtCariPelanggan;
    private javax.swing.JTextField txtConfirmPass;
    private javax.swing.JLabel txtDeskLayanan;
    private javax.swing.JTextArea txtDeskripsi;
    private javax.swing.JLabel txtDurasi;
    private javax.swing.JTextField txtHargaLayanan;
    private javax.swing.JLabel txtHariIni;
    private javax.swing.JLabel txtIdCust;
    private javax.swing.JLabel txtIdEmp;
    private javax.swing.JLabel txtIdInv;
    private javax.swing.JLabel txtIdJabatan;
    private javax.swing.JTextField txtIdLayanan;
    private javax.swing.JLabel txtIdTransaksi;
    private javax.swing.JLabel txtJumlahBiayaAntar;
    private javax.swing.JLabel txtJumlahHarga;
    private javax.swing.JTextField txtNamaCust;
    private javax.swing.JTextField txtNamaEmp;
    private javax.swing.JTextField txtNamaInv;
    private javax.swing.JTextField txtNamaJabatan;
    private javax.swing.JTextField txtNamaaLayanan;
    private javax.swing.JTextField txtNoHpCust;
    private javax.swing.JTextField txtNoHpEmp;
    private javax.swing.JTextArea txtNotes;
    private javax.swing.JTextField txtPassEmp;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JLabel txtPilihBulan;
    private javax.swing.JLabel txtPilihBulan1;
    private javax.swing.JLabel txtPilihTanggal;
    private javax.swing.JLabel txtPilihTanggal1;
    private javax.swing.JLabel txtStatusMember;
    private javax.swing.JLabel txtTotal;
    private javax.swing.JTextField txtUnameEmp;
    private javax.swing.JTextField txtUsername;
    private javax.swing.JLabel txtcurrdate;
    private javax.swing.JLabel txtdate;
    private javax.swing.JLabel txthargalayanan;
    private javax.swing.JLabel txtjabatan;
    // End of variables declaration//GEN-END:variables

    public void resetPelanggan() {
        pelanggan = new Pelanggan();
        cbmStatusMember = new DefaultComboBoxModel();
        dtmPelanggan = (DefaultTableModel) tableDataCust.getModel();
        loadTablePelanggan();
        loadComboBoxStatusMem();
        tableDataCust.clearSelection();

        txtIdCust.setText("");
        txtNamaCust.setText("");
        txtNoHpCust.setText("");
        txtAlamatCust.setText("");
        cbMembership.setSelectedIndex(0);
        dateMember.setDate(null);
        txtDurasi.setVisible(false);
        txtCariPelanggan.setText("");
        row = -1;
    }

    public void resetKaryawan() {
        karyawan = new Karyawan();
        dtmKaryawan = (DefaultTableModel) tableDataEmp.getModel();
        loadTableKaryawan();
        loadComboBoxJabatan();
        tableDataEmp.clearSelection();

        txtIdEmp.setText("");
        txtNamaEmp.setText("");
        txtNoHpEmp.setText("");
        txtAlamatEmp.setText("");
        txtUnameEmp.setText("");
        txtPassEmp.setText("");
        txtConfirmPass.setText("");
        cbJabatan.setSelectedIndex(0);
        txtCariKaryawan.setText("");
        row = -1;
    }

    public void resetJabatan() {
        jabatan = new Jabatan();
        dtmJabatan = (DefaultTableModel) tableDataJabatan.getModel();
        loadTableJabatan();
        tableDataJabatan.clearSelection();

        txtIdJabatan.setText("");
        txtNamaJabatan.setText("");
        txtDeskripsi.setText("");
        txtCariJabatan.setText("");
        row = -1;
    }

    public void resetInventory() {
        inventory = new Inventory();
        dtmInventory = (DefaultTableModel) tableDataInventory.getModel();
        loadTableInventory();
        tableDataInventory.clearSelection();

        txtIdInv.setText("");
        txtNamaInv.setText("");
        spStock.setValue(0);
        txtCariInventory.setText("");
        row = -1;
    }

    public void resetLayanan() {
        jenislayanan = new Jenislayanan();
        dtmLayanan = (DefaultTableModel) tableDataLayanan.getModel();
        loadTableLayanan();
        loadComboBoxInventory();
        tableDataLayanan.clearSelection();

        txtIdLayanan.setText("");
        txtNamaaLayanan.setText("");
        txtHargaLayanan.setText("");
        cbInventory.setSelectedIndex(0);
        spStockDipakai.setValue(0);
        txtCariLayanan.setText("");
        row = -1;
    }

    public void resetTransaksi() {
        transaksi = new Transaksi();
        cbmStatusLaundry = new DefaultComboBoxModel();
        cbmStatusPengantaran = new DefaultComboBoxModel();
        cbmStatusTransaksi = new DefaultComboBoxModel();
        dtmTransaksi = (DefaultTableModel) tableDataTransaksi.getModel();
        loadTableTransaksi();
        loadComboBoxTransaksi();
        tableDataTransaksi.clearSelection();

        txtIdTransaksi.setText("");
        dateTerima.setDate(null);
        dateSelesai.setDate(null);
        cbStatusLaundry.setSelectedIndex(0);
        cbAntar.setSelected(false);
        cbStatusPengantaran.setVisible(false);
        dateAntar.setVisible(false);
        cbPengantar.setVisible(false);
        label1.setVisible(false);
        label2.setVisible(false);
        label3.setVisible(false);
        txtNotes.setText("");
        cbPelanggan.setSelectedIndex(0);
        cbKaryawan.setSelectedIndex(0);
        cbLayanan.setSelectedIndex(0);
        txtBerat.setText("0");
        txtTotal.setText("0");

        row = -1;
    }

    @Override
    public void itemStateChanged(ItemEvent e) {

    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        try {
            if (mdPelanggan.isVisible()) {
                if (e.getSource() == tableDataCust.getSelectionModel()) {
                    row = tableDataCust.getSelectedRow();
                    if (txtCariPelanggan.getText().trim().isEmpty()) {
                        pelangganSelected = pelangganControl.getAllPelanggan().get(row);
                    } else {
                        pelangganSelected = pelangganControl.getSearchPelanggan(txtCariPelanggan.getText()).get(row);
                    }
                    member = String.valueOf(pelangganSelected.getIsMember());
                    if (member.equals("1")) {
                        member = "Aktif";
                    } else {
                        member = "Tidak Aktif";
                    }

                    Date date = new Date();
                    if (pelangganSelected.getTanggalJadiMember() != null) {
                        txtDurasi.setVisible(true);
                        getDifferenceDays(pelangganSelected.getTanggalJadiMember(), date);
                        days = (int) getDifferenceDays(pelangganSelected.getTanggalJadiMember(), date);
                        txtDurasi.setText("Days left: " + Integer.toString(days));
                        if (days <= 0) {
                            pelangganSelected.setIsMember(0);
                            pelangganControl.simpanAtauUpdateOnePelanggan(pelangganSelected);
                            member = "Tidak Aktif";
                            txtDurasi.setVisible(false);
                        }
                    } else {
                        txtDurasi.setVisible(false);
                    }

                    txtIdCust.setText(String.valueOf(pelangganSelected.getIdPelanggan()));
                    txtNamaCust.setText(pelangganSelected.getNamaPelanggan());
                    txtNoHpCust.setText(pelangganSelected.getNoTelpon());
                    txtAlamatCust.setText(pelangganSelected.getAlamat());
                    cbMembership.setSelectedItem(member);

                    if (member.equals("Aktif")) {
                        dateMember.setDate(pelangganSelected.getTanggalJadiMember());
                    } else {
                        dateMember.setDate(null);
                    }
                    for (int i = 0; i < pelangganControl.getAllPelanggan().size(); i++) {
                        if (pelangganSelected.getIdPelanggan() == pelangganControl.getAllPelanggan().get(i).getIdPelanggan()) {
                            cbPelanggan.setSelectedIndex(i);
                        }
                    }
                }
            } else if (mdKaryawan.isVisible()) {
                if (e.getSource() == tableDataEmp.getSelectionModel()) {
                    row = tableDataEmp.getSelectedRow();
                    if (txtCariKaryawan.getText().trim().isEmpty()) {
                        karyawanSelected = karyawanControl.getAllKaryawan().get(row);
                    } else {
                        karyawanSelected = karyawanControl.getSearchKaryawan(txtCariKaryawan.getText()).get(row);
                    }

                    txtIdEmp.setText(String.valueOf(karyawanSelected.getIdKaryawan()));
                    txtNamaEmp.setText(karyawanSelected.getNamaKaryawan());
                    txtNoHpEmp.setText(karyawanSelected.getNoTelpon());
                    txtAlamatEmp.setText(karyawanSelected.getAlamat());
                    txtUnameEmp.setText(karyawanSelected.getUsername());
                    txtPassEmp.setText(karyawanSelected.getPassword());
                    txtConfirmPass.setText(karyawanSelected.getPassword());
                    cbJabatan.setSelectedItem(karyawanSelected.getIdJabatan());
                }
            } else if (mdJabatan.isVisible()) {
                if (e.getSource() == tableDataJabatan.getSelectionModel()) {
                    row = tableDataJabatan.getSelectedRow();
                    if (txtCariJabatan.getText().trim().isEmpty()) {
                        jabatanSelected = jabatanControl.getAllJabatan().get(row);
                    } else {
                        jabatanSelected = jabatanControl.getSearchJabatan(txtCariJabatan.getText()).get(row);
                    }

                    txtIdJabatan.setText(String.valueOf(jabatanSelected.getIdJabatan()));
                    txtNamaJabatan.setText(jabatanSelected.getNamaJabatan());
                    txtDeskripsi.setText(jabatanSelected.getDeskripsi());
                }
            } else if (mdInventory.isVisible()) {
                if (e.getSource() == tableDataInventory.getSelectionModel()) {
                    row = tableDataInventory.getSelectedRow();
                    if (txtCariInventory.getText().trim().isEmpty()) {
                        inventorySelected = inventoryControl.getAllInventory().get(row);
                    } else {
                        inventorySelected = inventoryControl.getSearchInventory(txtCariInventory.getText()).get(row);
                    }

                    txtIdInv.setText(String.valueOf(inventorySelected.getIdInventory()));
                    txtNamaInv.setText(inventorySelected.getNamaInventory());
                    spStock.setValue(inventorySelected.getStock());
                }
            } else if (mdLayanan.isVisible()) {
                if (e.getSource() == tableDataLayanan.getSelectionModel()) {
                    row = tableDataLayanan.getSelectedRow();
                    if (txtCariLayanan.getText().trim().isEmpty()) {
                        jenislayananSelected = layananControl.getAllLayanan().get(row);
                    } else {
                        jenislayananSelected = layananControl.getSearchLayanan(txtCariLayanan.getText()).get(row);
                    }

                    txtIdLayanan.setText(String.valueOf(jenislayananSelected.getIdLayanan()));
                    txtNamaaLayanan.setText(jenislayananSelected.getNamaLayanan());
                    txtHargaLayanan.setText(Double.toString(jenislayananSelected.getHarga()));
                    cbInventory.setSelectedItem(jenislayananSelected.getIdInventory());
                    spStockDipakai.setValue(jenislayananSelected.getStockDipakai());
                }
            } else if (dtTransaksi.isVisible()) {
                if (e.getSource() == tableDataTransaksi.getSelectionModel()) {

                    row = tableDataTransaksi.getSelectedRow();
                    transaksiSelected = transaksiControl.getAllTransaksi().get(row);

                    slaundry = String.valueOf(transaksiSelected.getStatusLaundry());

                    santar = String.valueOf(transaksiSelected.getStatusPengantaran());
                    if (santar.equals("0")) {
                        santar = "0";
                    } else if (santar.equals("1")) {
                        santar = "Belum Diantar";
                    } else {
                        santar = "Sudah Diantar";
                    }

                    stransaksi = String.valueOf(transaksiSelected.getStatusTransaksi());

                    txtIdTransaksi.setText(String.valueOf(transaksiSelected.getIdTransaksi()));
                    dateTerima.setDate(transaksiSelected.getTanggalTerima());
                    dateSelesai.setDate(transaksiSelected.getTanggalSelesai());

                    if (!santar.equals("0")) {
                        cbAntar.setSelected(true);
                        cbStatusPengantaran.setVisible(true);
                        dateAntar.setVisible(true);
                        cbPengantar.setVisible(true);
                        label1.setVisible(true);
                        label2.setVisible(true);
                        label3.setVisible(true);
                        cbStatusLaundry.setSelectedIndex(Integer.valueOf(slaundry));
                        cbStatusPengantaran.setSelectedItem(santar);
                        for (int i = 0; i < karyawanControl.getAllKurir().size(); i++) {
                            if ((int) karyawanControl.getAllKurir().get(i).getIdKaryawan() == (int) transaksiSelected.getIdPengantar()) {
                                cbPengantar.setSelectedIndex(i);
                            }
                        }

                        dateAntar.setDate(transaksiSelected.getTanggalAntar());
                    } else {
                        cbAntar.setSelected(false);
                        cbStatusPengantaran.setVisible(false);
                        dateAntar.setVisible(false);
                        cbPengantar.setVisible(false);
                        label1.setVisible(false);
                        label2.setVisible(false);
                        label3.setVisible(false);
                    }
                    cbPelanggan.setSelectedItem(transaksiSelected.getIdPelanggan());
                    cbKaryawan.setSelectedItem(transaksiSelected.getIdKaryawan());
                    cbLayanan.setSelectedItem(transaksiSelected.getIdLayanan());

                    txtBerat.setText(String.valueOf(transaksiSelected.getBerat()));
                    cbStatusLaundry.setSelectedIndex(Integer.valueOf(slaundry));
                    cbStatusTransaksi.setSelectedIndex(Integer.valueOf(stransaksi));

                    txtJumlahHarga.setText("Rp " + (String.valueOf(Double.valueOf(transaksiSelected.getIdLayanan().getHarga()) * transaksiSelected.getBerat())) + ",-");
                    if (pelangganControl.getAllPelanggan().get(cbPelanggan.getSelectedIndex()).getIsMember() == 0 && transaksiSelected.getStatusPengantaran() != 0) {
                        txtJumlahBiayaAntar.setText("Rp " + String.valueOf(transaksiSelected.getBerat() * 2000) + ",-");
                    } else {
                        txtJumlahBiayaAntar.setText("Rp 0,-");
                    }
                    txtTotal.setText(String.valueOf(transaksiSelected.getTotal()));
                }
                if (e.getSource() == tableDataCariPelanggan.getSelectionModel()) {
                    row = tableDataCariPelanggan.getSelectedRow();
                    if (txtCariCust.getText().trim().isEmpty()) {
                        pelangganSelected = pelangganControl.getAllPelanggan().get(row);
                    } else {
                        pelangganSelected = pelangganControl.getSearchPelanggan(txtCariCust.getText()).get(row);
                    }

                    for (int i = 0; i < pelangganControl.getAllPelanggan().size(); i++) {
                        if (pelangganSelected.getIdPelanggan() == pelangganControl.getAllPelanggan().get(i).getIdPelanggan()) {
                            cbPelanggan.setSelectedIndex(i);
                        }
                    }
                }
            }
        } catch (Exception x) {
        }
    }

    private void loadTablePelanggan() {

        dtmPelanggan.getDataVector().removeAllElements();
        tableDataCust.getSelectionModel().removeListSelectionListener(this);
        if (pelangganControl.getAllPelanggan().isEmpty()) {
            dtmPelanggan.addRow(new Object[6]);
        }
        for (Pelanggan p : pelangganControl.getAllPelanggan()) {
            member = String.valueOf(p.getIsMember());
            if (member.equals("1")) {
                member = "Aktif";
            } else {
                member = "Tidak Aktif";
            }
            dtmPelanggan.addRow(new Object[]{
                p.getIdPelanggan(),
                p.getNamaPelanggan(),
                p.getNoTelpon(),
                p.getAlamat(),
                member,
                p.getTanggalJadiMember()
            });
        }
        dtmPelanggan.fireTableDataChanged();
        tableDataCust.getSelectionModel().addListSelectionListener(this);
    }

    private void loadTablePelanggan2(String text) {

        dtmPelanggan.getDataVector().removeAllElements();
        tableDataCust.getSelectionModel().removeListSelectionListener(this);
        if (pelangganControl.getSearchPelanggan(text).isEmpty()) {
            dtmPelanggan.addRow(new Object[6]);
        }
        for (Pelanggan p : pelangganControl.getSearchPelanggan(text)) {
            member = String.valueOf(p.getIsMember());
            if (member.equals("1")) {
                member = "Aktif";
            } else {
                member = "Tidak Aktif";
            }
            dtmPelanggan.addRow(new Object[]{
                p.getIdPelanggan(),
                p.getNamaPelanggan(),
                p.getNoTelpon(),
                p.getAlamat(),
                member,
                p.getTanggalJadiMember()
            });
        }
        dtmPelanggan.fireTableDataChanged();
        tableDataCust.getSelectionModel().addListSelectionListener(this);
    }

    private void loadTableCariPelanggan() {

        dtmCariPelanggan.getDataVector().removeAllElements();
        tableDataCariPelanggan.getSelectionModel().removeListSelectionListener(this);
        if (pelangganControl.getAllPelanggan().isEmpty()) {
            dtmCariPelanggan.addRow(new Object[6]);
        }
        for (Pelanggan p : pelangganControl.getAllPelanggan()) {
            member = String.valueOf(p.getIsMember());
            if (member.equals("1")) {
                member = "Aktif";
            } else {
                member = "Tidak Aktif";
            }
            dtmCariPelanggan.addRow(new Object[]{
                p.getIdPelanggan(),
                p.getNamaPelanggan(),
                p.getNoTelpon(),
                p.getAlamat(),
                member,
                p.getTanggalJadiMember()
            });
        }
        dtmCariPelanggan.fireTableDataChanged();
        tableDataCariPelanggan.getSelectionModel().addListSelectionListener(this);
    }

    private void loadTableCariPelanggan2(String text) {
        dtmCariPelanggan.getDataVector().removeAllElements();
        tableDataCariPelanggan.getSelectionModel().removeListSelectionListener(this);
        if (pelangganControl.getAllPelanggan().isEmpty()) {
            dtmCariPelanggan.addRow(new Object[6]);
        }
        for (Pelanggan p : pelangganControl.getSearchPelanggan(text)) {
            member = String.valueOf(p.getIsMember());
            if (member.equals("1")) {
                member = "Aktif";
            } else {
                member = "Tidak Aktif";
            }
            dtmCariPelanggan.addRow(new Object[]{
                p.getIdPelanggan(),
                p.getNamaPelanggan(),
                p.getNoTelpon(),
                p.getAlamat(),
                member,
                p.getTanggalJadiMember()
            });
        }
        dtmCariPelanggan.fireTableDataChanged();
        tableDataCariPelanggan.getSelectionModel().addListSelectionListener(this);
    }

    private void loadTableKaryawan() {
        dtmKaryawan.getDataVector().removeAllElements();
        tableDataEmp.getSelectionModel().removeListSelectionListener(this);
        if (karyawanControl.getAllKaryawan().isEmpty()) {
            dtmKaryawan.addRow(new Object[7]);
        }
        for (Karyawan p : karyawanControl.getAllKaryawan()) {

            dtmKaryawan.addRow(new Object[]{
                p.getIdKaryawan(),
                p.getNamaKaryawan(),
                p.getNoTelpon(),
                p.getAlamat(),
                p.getUsername(),
                p.getPassword(),
                p.getIdJabatan()
            });
        }
        dtmKaryawan.fireTableDataChanged();
        tableDataEmp.getSelectionModel().addListSelectionListener(this);
    }

    private void loadTableKaryawan2(String text) {
        dtmKaryawan.getDataVector().removeAllElements();
        tableDataEmp.getSelectionModel().removeListSelectionListener(this);
        if (karyawanControl.getAllKaryawan().isEmpty()) {
            dtmKaryawan.addRow(new Object[7]);
        }
        for (Karyawan p : karyawanControl.getSearchKaryawan(text)) {

            dtmKaryawan.addRow(new Object[]{
                p.getIdKaryawan(),
                p.getNamaKaryawan(),
                p.getNoTelpon(),
                p.getAlamat(),
                p.getUsername(),
                p.getPassword(),
                p.getIdJabatan()
            });
        }
        dtmKaryawan.fireTableDataChanged();
        tableDataEmp.getSelectionModel().addListSelectionListener(this);
    }

    private void loadTableJabatan() {
        dtmJabatan.getDataVector().removeAllElements();
        tableDataJabatan.getSelectionModel().removeListSelectionListener(this);
        if (jabatanControl.getAllJabatan().isEmpty()) {
            dtmJabatan.addRow(new Object[3]);
        }
        for (Jabatan p : jabatanControl.getAllJabatan()) {

            dtmJabatan.addRow(new Object[]{
                p.getIdJabatan(),
                p.getNamaJabatan(),
                p.getDeskripsi()
            });
        }
        dtmJabatan.fireTableDataChanged();
        tableDataJabatan.getSelectionModel().addListSelectionListener(this);
    }

    private void loadTableJabatan2(String text) {
        dtmJabatan.getDataVector().removeAllElements();
        tableDataJabatan.getSelectionModel().removeListSelectionListener(this);
        if (jabatanControl.getAllJabatan().isEmpty()) {
            dtmJabatan.addRow(new Object[3]);
        }
        for (Jabatan p : jabatanControl.getSearchJabatan(text)) {

            dtmJabatan.addRow(new Object[]{
                p.getIdJabatan(),
                p.getNamaJabatan(),
                p.getDeskripsi()
            });
        }
        dtmJabatan.fireTableDataChanged();
        tableDataJabatan.getSelectionModel().addListSelectionListener(this);
    }

    private void loadTableInventory() {
        dtmInventory.getDataVector().removeAllElements();
        tableDataInventory.getSelectionModel().removeListSelectionListener(this);
        if (inventoryControl.getAllInventory().isEmpty()) {
            dtmInventory.addRow(new Object[3]);
        }
        for (Inventory p : inventoryControl.getAllInventory()) {

            dtmInventory.addRow(new Object[]{
                p.getIdInventory(),
                p.getNamaInventory(),
                p.getStock()
            });
        }
        dtmInventory.fireTableDataChanged();
        tableDataInventory.getSelectionModel().addListSelectionListener(this);
        resetTransaksi();
    }

    private void loadTableInventory2(String text) {
        dtmInventory.getDataVector().removeAllElements();
        tableDataInventory.getSelectionModel().removeListSelectionListener(this);
        if (inventoryControl.getAllInventory().isEmpty()) {
            dtmInventory.addRow(new Object[3]);
        }
        for (Inventory p : inventoryControl.getSearchInventory(text)) {

            dtmInventory.addRow(new Object[]{
                p.getIdInventory(),
                p.getNamaInventory(),
                p.getStock()
            });
        }
        dtmInventory.fireTableDataChanged();
        tableDataInventory.getSelectionModel().addListSelectionListener(this);
    }

    private void loadTableLayanan() {
        dtmLayanan.getDataVector().removeAllElements();
        tableDataLayanan.getSelectionModel().removeListSelectionListener(this);
        if (layananControl.getAllLayanan().isEmpty()) {
            dtmLayanan.addRow(new Object[5]);
        }
        for (Jenislayanan p : layananControl.getAllLayanan()) {

            dtmLayanan.addRow(new Object[]{
                p.getIdLayanan(),
                p.getNamaLayanan(),
                p.getHarga(),
                p.getIdInventory(),
                p.getStockDipakai()
            });
        }
        dtmLayanan.fireTableDataChanged();
        tableDataLayanan.getSelectionModel().addListSelectionListener(this);
    }

    private void loadTableLayanan2(String text) {
        dtmLayanan.getDataVector().removeAllElements();
        tableDataLayanan.getSelectionModel().removeListSelectionListener(this);
        if (layananControl.getAllLayanan().isEmpty()) {
            dtmLayanan.addRow(new Object[5]);
        }
        for (Jenislayanan p : layananControl.getSearchLayanan(text)) {

            dtmLayanan.addRow(new Object[]{
                p.getIdLayanan(),
                p.getNamaLayanan(),
                p.getHarga(),
                p.getIdInventory(),
                p.getStockDipakai()
            });
        }
        dtmLayanan.fireTableDataChanged();
        tableDataLayanan.getSelectionModel().addListSelectionListener(this);
    }

    private void loadTableTransaksi() {
        dtmTransaksi.getDataVector().removeAllElements();
        tableDataTransaksi.getSelectionModel().removeListSelectionListener(this);
        if (transaksiControl.getAllTransaksi().isEmpty()) {
            dtmTransaksi.addRow(new Object[13]);
        }
        for (Transaksi p : transaksiControl.getAllTransaksi()) {
            slaundry = String.valueOf(p.getStatusLaundry());
            if (slaundry.equals("0")) {
                slaundry = "Belum Selesai";
            } else if (slaundry.equals("1")) {
                slaundry = "Selesai";
            } else {
                slaundry = "Sudah Diambil";
            }

            santar = String.valueOf(p.getStatusPengantaran());
            if (santar.equals("0")) {
                santar = "";
            } else if (santar.equals("1")) {
                santar = "Belum Diantar";
            } else {
                santar = "Sudah Diantar";
            }

            stransaksi = String.valueOf(p.getStatusTransaksi());
            if (stransaksi.equals("0")) {
                stransaksi = "Belum Lunas";
            } else {
                stransaksi = "Lunas";
            }

            pengantar = Integer.toString(p.getIdPengantar());
            if (!pengantar.equals("0")) {
                pengantar = karyawanControl.getAllKaryawan().get(Integer.parseInt(pengantar) - 1).getNamaKaryawan();
            } else {
                pengantar = "";
            }

            dtmTransaksi.addRow(new Object[]{
                p.getIdTransaksi(),
                p.getTanggalTerima(),
                p.getTanggalSelesai(),
                p.getBerat(),
                p.getIdPelanggan(),
                p.getIdKaryawan(),
                p.getIdLayanan(),
                slaundry,
                santar,
                pengantar,
                p.getTanggalAntar(),
                p.getTotal(),
                stransaksi
            });
        }
        dtmTransaksi.fireTableDataChanged();
        tableDataTransaksi.getSelectionModel().addListSelectionListener(this);
    }

    private void loadComboBoxJabatan() {
        DefaultComboBoxModel modelJabatan = new DefaultComboBoxModel(jabatanControl.getAllJabatan().toArray());
        cbJabatan.setModel(modelJabatan);
        cbJabatan.setSelectedIndex(0);
    }

    private void loadComboBoxInventory() {
        DefaultComboBoxModel modelInventory = new DefaultComboBoxModel(inventoryControl.getAllInventory().toArray());
        cbInventory.setModel(modelInventory);
        cbInventory.setSelectedIndex(0);
    }

    private void loadComboBoxTransaksi() {
        DefaultComboBoxModel modelPelanggan = new DefaultComboBoxModel(pelangganControl.getAllPelanggan().toArray());
        cbPelanggan.setModel(modelPelanggan);
        cbPelanggan.setSelectedIndex(0);
        DefaultComboBoxModel modelKaryawan = new DefaultComboBoxModel(karyawanControl.getAllStaff().toArray());
        cbKaryawan.setModel(modelKaryawan);
        cbKaryawan.setSelectedIndex(0);
        DefaultComboBoxModel modelLayanan = new DefaultComboBoxModel(layananControl.getAllLayanan().toArray());
        cbLayanan.setModel(modelLayanan);
        cbLayanan.setSelectedIndex(0);
        DefaultComboBoxModel modelPengantar = new DefaultComboBoxModel(karyawanControl.getAllKurir().toArray());
        cbPengantar.setModel(modelPengantar);
        cbPengantar.setSelectedIndex(0);
        cbmStatusLaundry = new DefaultComboBoxModel(transaksiControl.getStatusLaundry());
        cbStatusLaundry.setModel(cbmStatusLaundry);
        cbmStatusPengantaran = new DefaultComboBoxModel(transaksiControl.getStatusPengantaran());
        cbStatusPengantaran.setModel(cbmStatusPengantaran);
        cbmStatusTransaksi = new DefaultComboBoxModel(transaksiControl.getStatusTransaksi());
        cbStatusTransaksi.setModel(cbmStatusTransaksi);

    }

    private void loadComboBoxStatusMem() {
        cbmStatusMember = new DefaultComboBoxModel(pelangganControl.getStatusMem());
        cbMembership.setModel(cbmStatusMember);
    }

    public static long getDifferenceDays(Date d1, Date d2) {
        long diff = d2.getTime() - d1.getTime();
        return (30 - TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS));
    }

    public static long getDifferenceDays2(Date d1, Date d2) {
        long diff = d2.getTime() - d1.getTime();
        return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
    }

    public void loadAllTable() {
        loadTablePelanggan();
        loadTableKaryawan();
        loadTableJabatan();
        loadTableInventory();
        loadTableLayanan();
        loadTableTransaksi();
    }

    public void resetAll() {
        resetInventory();
        resetJabatan();
        resetKaryawan();
        resetLayanan();
        resetPelanggan();
        resetTransaksi();
    }
}
