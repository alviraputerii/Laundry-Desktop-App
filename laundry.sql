/*
SQLyog Ultimate v12.4.1 (64 bit)
MySQL - 10.4.11-MariaDB : Database - laundry
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`laundry` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;

USE `laundry`;

/*Table structure for table `inventory` */

DROP TABLE IF EXISTS `inventory`;

CREATE TABLE `inventory` (
  `IdInventory` int(2) NOT NULL AUTO_INCREMENT,
  `NamaInventory` varchar(60) NOT NULL,
  `Stock` int(10) NOT NULL,
  PRIMARY KEY (`IdInventory`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

/*Data for the table `inventory` */

insert  into `inventory`(`IdInventory`,`NamaInventory`,`Stock`) values 
(1,'Acer Attack Hygiene Plus',800),
(2,'Acer Attack Protection Liquid ',900),
(3,'Detergent Cair Sun Klin Anti Bakterial',900);

/*Table structure for table `jabatan` */

DROP TABLE IF EXISTS `jabatan`;

CREATE TABLE `jabatan` (
  `IdJabatan` int(1) NOT NULL AUTO_INCREMENT,
  `NamaJabatan` varchar(40) NOT NULL,
  `Deskripsi` text DEFAULT NULL,
  PRIMARY KEY (`IdJabatan`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

/*Data for the table `jabatan` */

insert  into `jabatan`(`IdJabatan`,`NamaJabatan`,`Deskripsi`) values 
(1,'Admin',''),
(2,'Staff Laundry','Bertanggung jawab mulai dari proses pencucian hingga siap dikembalikan kepada pelanggan'),
(3,'Kurir','Mengantarkan laundryan kepada pelanggan'),
(4,'Kasir','Menangani transaksi');

/*Table structure for table `jenislayanan` */

DROP TABLE IF EXISTS `jenislayanan`;

CREATE TABLE `jenislayanan` (
  `IdLayanan` varchar(4) NOT NULL,
  `NamaLayanan` varchar(60) NOT NULL,
  `Harga` double NOT NULL,
  `StockDipakai` int(4) NOT NULL,
  `IdInventory` int(2) NOT NULL,
  PRIMARY KEY (`IdLayanan`),
  KEY `layanan_inv_fk` (`IdInventory`),
  CONSTRAINT `layanan_inv_fk` FOREIGN KEY (`IdInventory`) REFERENCES `inventory` (`IdInventory`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `jenislayanan` */

insert  into `jenislayanan`(`IdLayanan`,`NamaLayanan`,`Harga`,`StockDipakai`,`IdInventory`) values 
('CKR','Cuci + Kering Reguler (2hari)',6000,4,2),
('CKSE','Cuci + Kering + Setrika Express (1hari)',10000,4,3),
('CKSR','Cuci + Kering + Setrika Reguler (3hari)',7000,4,1);

/*Table structure for table `karyawan` */

DROP TABLE IF EXISTS `karyawan`;

CREATE TABLE `karyawan` (
  `IdKaryawan` int(2) NOT NULL AUTO_INCREMENT,
  `NamaKaryawan` varchar(40) NOT NULL,
  `NoTelpon` varchar(15) NOT NULL,
  `Alamat` varchar(150) NOT NULL,
  `Username` varchar(20) DEFAULT NULL,
  `Password` varchar(20) DEFAULT NULL,
  `isAdmin` int(1) NOT NULL,
  `IdJabatan` int(1) NOT NULL,
  PRIMARY KEY (`IdKaryawan`),
  KEY `karyawan_jabatan_fk` (`IdJabatan`),
  CONSTRAINT `karyawan_jabatan_fk` FOREIGN KEY (`IdJabatan`) REFERENCES `jabatan` (`IdJabatan`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

/*Data for the table `karyawan` */

insert  into `karyawan`(`IdKaryawan`,`NamaKaryawan`,`NoTelpon`,`Alamat`,`Username`,`Password`,`isAdmin`,`IdJabatan`) values 
(1,'Ae Ri','081267574565','Jalan Stockholm','aeriy','satudua',1,1),
(2,'Myung Hee','086445342422','Jalan La Sagrada','','',0,2),
(3,'Yoona','089783234742','Jalan Keukenhof','','',0,3),
(4,'Voyya','082337642746','Jalan Grossmunster','','',0,3),
(5,'Soo Min','087864726923','Jalan Vittorio','','',0,2),
(6,'Kyung Ri','089848365347','Jalan Kenangan','kyungie','duatiga',0,4);

/*Table structure for table `pelanggan` */

DROP TABLE IF EXISTS `pelanggan`;

CREATE TABLE `pelanggan` (
  `IdPelanggan` int(10) NOT NULL AUTO_INCREMENT,
  `NamaPelanggan` varchar(40) NOT NULL,
  `NoTelpon` varchar(15) NOT NULL,
  `Alamat` varchar(150) NOT NULL,
  `isMember` int(1) NOT NULL,
  `TanggalJadiMember` date DEFAULT NULL,
  PRIMARY KEY (`IdPelanggan`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

/*Data for the table `pelanggan` */

insert  into `pelanggan`(`IdPelanggan`,`NamaPelanggan`,`NoTelpon`,`Alamat`,`isMember`,`TanggalJadiMember`) values 
(1,'Hwa Young','081278654385','Jalan Mangnidan',1,'2020-06-01'),
(2,'Jin Ae','089754348249','Jalan Songnidan',0,NULL),
(3,'Chun Cha','087325473638','Jalan Haengnidan',1,'2020-05-20'),
(4,'Jae-Hwa','082587434754','Jalan Hwangnidan',0,NULL),
(5,'Min Jung','085482749236','Jalan Haeridan',0,NULL);

/*Table structure for table `transaksi` */

DROP TABLE IF EXISTS `transaksi`;

CREATE TABLE `transaksi` (
  `IdTransaksi` int(10) NOT NULL AUTO_INCREMENT,
  `TanggalTerima` date NOT NULL,
  `TanggalSelesai` date NOT NULL,
  `Berat` int(2) NOT NULL,
  `IdPelanggan` int(10) NOT NULL,
  `IdKaryawan` int(2) NOT NULL,
  `IdLayanan` varchar(4) NOT NULL,
  `Notes` text DEFAULT NULL,
  `Total` double NOT NULL,
  `StatusLaundry` int(1) NOT NULL,
  `StatusPengantaran` int(1) DEFAULT NULL,
  `IdPengantar` int(2) DEFAULT NULL,
  `StatusTransaksi` int(1) NOT NULL,
  `TanggalAntar` date DEFAULT NULL,
  PRIMARY KEY (`IdTransaksi`),
  KEY `transaksi_cust_fk` (`IdPelanggan`),
  KEY `transaksi_emp_fk` (`IdKaryawan`),
  KEY `transaksi_serv_fk` (`IdLayanan`),
  CONSTRAINT `transaksi_cust_fk` FOREIGN KEY (`IdPelanggan`) REFERENCES `pelanggan` (`IdPelanggan`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `transaksi_emp_fk` FOREIGN KEY (`IdKaryawan`) REFERENCES `karyawan` (`IdKaryawan`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `transaksi_serv_fk` FOREIGN KEY (`IdLayanan`) REFERENCES `jenislayanan` (`IdLayanan`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

/*Data for the table `transaksi` */

insert  into `transaksi`(`IdTransaksi`,`TanggalTerima`,`TanggalSelesai`,`Berat`,`IdPelanggan`,`IdKaryawan`,`IdLayanan`,`Notes`,`Total`,`StatusLaundry`,`StatusPengantaran`,`IdPengantar`,`StatusTransaksi`,`TanggalAntar`) values 
(1,'2020-06-03','2020-06-06',4,3,2,'CKSR','',28000,2,0,0,1,NULL),
(2,'2020-06-05','2020-06-06',2,1,2,'CKSE','',20000,1,2,3,1,'2020-05-05'),
(3,'2020-06-08','2020-06-10',5,5,5,'CKR','',30000,2,0,0,0,NULL),
(4,'2020-06-11','2020-06-15',2,2,2,'CKSR','',18000,1,1,0,1,NULL),
(5,'2020-06-13','2020-06-16',3,4,5,'CKR','',18000,0,0,0,0,NULL),
(6,'2020-06-15','2020-06-16',3,2,5,'CKSE','',36000,0,1,0,0,NULL);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
