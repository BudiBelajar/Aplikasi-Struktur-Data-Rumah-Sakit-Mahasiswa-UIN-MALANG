/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataEngine;

/**
 *
 * @author Ilham
 */
public class Mahasiswa {
    private String NIM;
    private String Nama;
    private char JK;
    private String Jurusan;
    private String Penyakit;
    private String Kamar;
    
    public Mahasiswa next;
// deklarasi
    public Mahasiswa(String NIM, String Nama, char JK, String Jurusan, String Penyakit, String Kamar) {
        this.NIM = NIM;
        this.Nama = Nama;
        this.JK = JK;
        this.Jurusan = Jurusan;
        this.Penyakit = Penyakit;
        this.Kamar = Kamar;
    }
//inisialisasi 
    public void setKamar(String Kamar) {
        this.Kamar = Kamar;
    }  
    public void setNIM(String NIM) {
        this.NIM = NIM;
    }

    public void setNama(String Nama) {
        this.Nama = Nama;
    }

    public void setJK(char JK) {
        this.JK = JK;
    }

    public void setJurusan(String Jurusan) {
        this.Jurusan = Jurusan;
    }

    public void setHP(String Penyakit) {
        this.Penyakit = Penyakit;
    }
    
//    untuk megambil data ke array
    public String getKamar() {
        return Kamar;
    }
    public String getNIM() {
        return NIM;
    }

    public String getNama() {
        return Nama;
    }

    public char getJK() {
        return JK;
    }

    public String getJurusan() {
        return Jurusan;
    }

    public String getPenyakit() {
        return Penyakit;
    }

    
}
