
package DataEngine;

public class Heap {
    //heap adalag sebuha struktur data dalam kategori arraylist yang menyerupai binary tree tetapi yang mencolok adalah pada insert delete dan search 
    //adalah pada insert delete dan search lebih cepat dibanding binary tree
//heapsort memasukkan data kedalam struktur data heap
    //heapsort sebuah array yang menyerupai binary tree yang complete  dimana nilai masing-masing 
//    index array akan diurutkan
//    dimana node tiap levelnya diisi mulai dari kiri hingga kekanan 
    // private berguna untuk identitas pada method untuk diisi code code masing masing
    //yang berbeda dengan method lain atau pendeklarasian
    private String[][] heapArray;
    private int maxSize;
    private int currentSize;
    private int by;
//deklarasi untuk panjang array  
    public Heap(String[][] array) {
        this.maxSize = array.length;
        currentSize = array.length;
        heapArray = array;
    }
// untuk mengecek apa data masih kosong
    public boolean isEmpty() {
        //sebuah method apakah salat atau benar yang dikategorikan untuk isEmpty
        return currentSize == 0;
    }
//
    public void sizeIncrement(){
        //berguna untuk mengambil pada method dan main utama dijalankan
        currentSize++;
    }
    
    public void insertAt(int index, String[] data){
        //berguna untuk memasukkan angka pada index serta pada kata pada data
        if (index>=maxSize||index<0) {
            return ;
        }
        //urutan index untuk data 
        heapArray[index]=data;
        
    }
    
    public String[][] heapSortASC(int by){
        this.by = by;
        int sizeTemp=currentSize;
        for (int i = currentSize/2-1; i >= 0; i--) {//trikle down n/2-1 
            tricleDown(i);
        }
        for (int i = currentSize-1; i >=0; i--) {
            String[] temp = remove();
            insertAt(i, temp);
        }
        currentSize=sizeTemp;
        System.out.println("urutkan dengan "+by+" ASC");
        return heapArray;
    }
    
    public String[][] heapSortDESC(int by){
        this.by = by;//index sorting
        
        String[][] result = new String[heapArray.length][heapArray[0].length];//srrsy bust nyimpsn
        int sizeTemp=currentSize;//nyimpsn size sementara
        
        for (int i = currentSize/2-1; i >= 0; i--) {//trikle down n/2-1 
            tricleDown(i);
        }
        
        for (int i = 0; i <maxSize; i++) {//
            result[i]=remove();
        }
        
        heapArray=result;
        currentSize=sizeTemp;
        System.out.println("urutkan dengan "+by+" DESC");
        return heapArray;
    }
    
    public int compare(String a, String b) {
        return a.compareTo(b);
    }
//    untuk menempatkan node baru yang mempunyai nilai index yang besar keatas dengan cara
//    membandingkan tiap node
    public void tricleUp(int index) {
        int parent = (index - 1) / 2;
        String[] bottom = heapArray[index];
        while (index > 0 && compare(heapArray[parent][by], bottom[by])<0) {
            heapArray[index] = heapArray[parent];
            index = parent;
            parent = (parent - 1) / 2;
        }
        heapArray[index] = bottom;
    }
    
    public String[] remove() {
        String[] root = heapArray[0];
        heapArray[0] = heapArray[--currentSize];
        tricleDown(0);
        return root;
    }
       
    public void tricleDown(int index) {
        int largerChild;
        String[] top = heapArray[index];
        while (index < currentSize / 2) {
            int leftChild = 2 * index + 1;
            int rightChild = leftChild + 1;

            if (rightChild < currentSize
                    && compare(heapArray[leftChild][by]
                    , heapArray[rightChild][by])<0 ) {
                largerChild = rightChild;
            }else{
                largerChild = leftChild;
            }
            if (compare(top[by],heapArray[largerChild][by])>=0) {
                break;
            }
            heapArray[index] = heapArray[largerChild];
            index = largerChild;
        }
        heapArray[index]=top;
        
    }
    
    public void tricleDownDESC(int index) {
        int largerChild;
        String[] top = heapArray[index];
        while (index < currentSize / 2) {
            int leftChild = 2 * index + 1;
            int rightChild = leftChild + 1;

            if (rightChild < currentSize
                    && compare(heapArray[leftChild][by]
                    , heapArray[rightChild][by])>0 ) {
                largerChild = rightChild;
            }else{
                largerChild = leftChild;
            }
            if (compare(top[by],heapArray[largerChild][by])<=0) {
                break;
            }
            
            heapArray[index] = heapArray[largerChild];
            index = largerChild;
        }
        heapArray[index]=top;
    }
    

}
