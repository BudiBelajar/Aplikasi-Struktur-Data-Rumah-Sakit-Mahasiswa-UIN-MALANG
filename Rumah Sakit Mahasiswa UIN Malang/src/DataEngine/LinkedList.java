/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataEngine;

/**
 *
 * @author ilham
 */
//linkedlist untuk menyimpan sekumpulan elemen atau data dari mahassiwa
public class LinkedList {
    private Mahasiswa first;
    private Mahasiswa last;
    private int count;
    String[][] result;
    private Array sortHandler;

    public LinkedList() {
        first = null;
        last = null;
        sortHandler = new Array();
    }
//    untuk menentukan apakah linkedlist masih kososng
    public boolean isEmpty(){
        return first==null;
    }
//untuk manambah elemen baru pada aray pada akhir list
    public void insertLast(Mahasiswa newLink) {
        if (isEmpty()) {
            first = newLink;
        } else {
            last.next = newLink;
        }
        last = newLink;
        count++;
    }
//    untuk menghapus data yang akan dihapus dengan menggunakan nim
    public boolean deleteKey(String key){
        Mahasiswa current = first;
        Mahasiswa previous = first;
        while (!current.getNIM().equals(key)) {
            if (current.next == null) {
                return false;
            }else{
                previous = current;
                current = current.next;
            }
        }
        if (current == first) {
            first = first.next;
        }else{
            previous.next = current.next;
        }
        count--;
        return true;
    }
//    untuk penempatan nama index 
    public String[][] LoadData() {
        while (isEmpty()) {
            return null;
        }
        result = new String[count][6];//get data mahasiswa
        Mahasiswa current = first;
        int i = 0, j = 0;

        while (current != null) {
            result[i][0] = current.getNIM();
            result[i][1] = current.getNama();
            result[i][2] = String.valueOf(current.getJurusan());
            result[i][3] = String.valueOf(current.getJK());
            result[i][4] = current.getPenyakit();
            result[i][5] = current.getKamar();

            i++;
            current = current.next;
        }
        
        return result;
    }
    

    
    
}
