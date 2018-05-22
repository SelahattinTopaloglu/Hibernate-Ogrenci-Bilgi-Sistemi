/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.List;
import java.util.Vector;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import model.Ogrenci;
import org.hibernate.Session;

/**
 *
 * @author USER
 */
public class ObsYonetici {
    private JTable ogrenciTablo;
    private final static String SORGU_KALIP = "from Ogrenci m";
    private Session session;
    private Vector<String> sutunlar = new Vector<>();
    private Vector<Object> satir;
    private DefaultTableModel model;
    public ObsYonetici(JTable ogrenciTablo) {
        this.ogrenciTablo = ogrenciTablo;
        sutunlar.add("Ogrenci ID");
        sutunlar.add("Ogrenci NO");
        sutunlar.add("adsoyad");
        sutunlar.add("Sehir");
        sutunlar.add("TelNo");
        model=(DefaultTableModel)ogrenciTablo.getModel();
        model.setColumnIdentifiers(sutunlar);
    }

       public void OgrenciGetir(String aranan, String filtre) {
        String sorguMetin = "";
        if (filtre.equalsIgnoreCase("adsoyad")) {
            sorguMetin = SORGU_KALIP + " where o.adsoyad like '%" + aranan + "%'";
        } else if (filtre.equalsIgnoreCase("ogrenci no")) {
            sorguMetin = SORGU_KALIP + " where o.ogrenci no like '%" + aranan + "%'";
        }
        session.beginTransaction();
        List ogrenciList = session.createQuery(sorguMetin).list();
        session.getTransaction().commit();
        musteriGoster(ogrenciList);

    }
 
       public void ac() {
        session = HibernateUtil.getSessionFactory().openSession();
    }

    public void kapat() {
        session.close();
    }

    private void musteriGoster(List<Musteri> musterilerList) {
        model.getDataVector().removeAllElements();
        for (Ogrenci gelenOgrenci : ogrenciList) {
            satir=new Vector();
            satir.add(gelenOgrenci.getogrenciid());
            satir.add(gelenOgrenci.getogrencino());
            satir.add(gelenOgrenci.getadsoyad());
            satir.add(gelenOgrenci.getsehir());
            satir.add(gelenOgrenci.gettelno());
            model.addRow(satir);
        }
    }
}