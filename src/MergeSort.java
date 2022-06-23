// Deklarasi variabel kiri ke 0 dan variabel kanan ke n-1
// Temukan tengahnya dengan formula = (left+right)/2
// Panggil merge sort dalam (left,mid)
// Panggil merge sort dalam (mid+1,rear)
// Lanjutkan sampail left kurang dari right
// Lalu panggil fungsi merge

// step 1: start
// step 2: declare array and left, right, mid variable
// step 3: perform merge function.
//          mergesort(array,left,right)
//          mergesort (array, left, right)
//          if left > right
//          return
//          mid= (left+right)/2
//          mergesort(array, left, mid)
//          mergesort(array, mid+1, right)
//          merge(array, left, mid, right)
// step 4: Stop

//
// Time Complexity: O(n log n)
// Worst Case :

// Kelebihan komplekstas lebih rendah, dan sangat efisien
// Kekurangan ada overhead, terlalu banyak menggunakan ruang pada memori dibandingkan sorting lainnya
class MergeSort {
//    Menggabungkan dua subarray dari arr[].
//    Subarray pertama adalah arr[l..m]
//    Subarray kedua adalah arr[m+1..r]
    void merge(int arr[], int l, int m, int r) {
//        Temukan ukuran dua subarray yang akan digabungkan
        int n1 = m - l + 1;
        int n2 = r - m;

//        Temp array
        int L[] = new int[n1];
        int R[] = new int[n2];

//        Copy data
        for (int i = 0; i < n1; ++i)
            L[i] = arr[l + i];
        for (int j = 0; j < n2; ++j)
            R[j] = arr[m + 1 + j];

//        Merge Temp array

        int i = 0, j = 0;
//        Initial index of merged subarray array
        int k = l;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                arr[k] = L[i];
                i++;
            }
            else {
                arr[k] = R[j];
                j++;
            }
            k++;
        }
//        Salin elemen L[] yang tersisa jika ada
        while (i < n1) {
            arr[k] = L[i];
            i++;
            k++;
        }
//        Salin elemen R[] yang tersisa jika ada
        while (j < n2) {
            arr[k] = R[j];
            j++;
            k++;
        }

    }

    // Fungsi utama yang mengurutkan arr[l..r]
    // merge()
    void sort(int arr[], int l, int r) {
        if (l < r) {
//            Memnemukan point di tengah
            int m =l+ (r-l)/2;
//            Sort
            sort(arr, l, m);
            sort(arr, m + 1, r);
//            Merge
            merge(arr, l, m, r);
        }
    }

    static void printArray(int arr[]) {
        int n = arr.length;
        for (int i = 0; i < n; ++i)
            System.out.print(arr[i] + " ");
        System.out.println();
    }

    // Driver code
    public static void main(String args[]) {
        int arr[] = { 12, 11, 13, 5, 6, 7 };

        System.out.println("Given Array");
        printArray(arr);

        MergeSort test = new MergeSort();
        test.sort(arr, 0, arr.length - 1);

        System.out.println("\nSorted array");
        printArray(arr);
    }
}
