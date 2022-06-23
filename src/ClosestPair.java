import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Comparator;
class Point {
    public int x;
    public int y;
    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
//    Fungsi utilitas untuk mencari jarak antara dua titik
    public static float dist(Point p1, Point p2) {
        return (float) Math.sqrt((p1.x - p2.x) * (p1.x - p2.x) +
                (p1.y - p2.y) * (p1.y - p2.y)
        );
    }

//    Metode Brute Force untuk mengembalikan jarak terkecil antara dua titik di P[] berukuran n
    public static float bruteForce(Point[] P, int n) {
        float min = Float.MAX_VALUE;
        float currMin = 0;

        for (int i = 0; i < n; ++i) {
            for (int j = i + 1; j < n; ++j) {
                currMin = dist(P[i], P[j]);
                if (currMin < min) {
                    min = currMin;
                }
            }
        }
        return min;
    }

//    Fungsi utilitas untuk menemukan jarak antara titik terdekat
//    dari strip dengan ukuran tertentu. Semua titik di strip[] diurutkan menurut koordinat y.
//    Semua memiliki batas atas pada jarak minimum sebagai d.
//    Metode ini tampaknya merupakan metode O(n^2),
//    tetapi ini adalah metode O(n) karena loop dalam berjalan paling banyak 6 kali
    public static float stripClosest(Point[] strip, int size, float d) {
        float min = d; // Initialize the minimum distance as d

        Arrays.sort(strip, 0, size, new PointYComparator());

//        Pilih semua titik satu per satu dan coba titik berikutnya
//        sampai perbedaan antara koordinat y lebih kecil dari d.
//        Ini adalah fakta yang terbukti bahwa loop ini berjalan paling banyak 6 kali
        for (int i = 0; i < size; ++i) {
            for (int j = i + 1; j < size && (strip[j].y - strip[i].y) < min; ++j) {
                if (dist(strip[i], strip[j]) < min) {
                    min = dist(strip[i], strip[j]);
                }
            }
        }

        return min;
    }

//    Fungsi rekursif untuk mencari jarak terkecil.
//    Array P berisi semua titik yang diurutkan menurut koordinat x
    public static float closestUtil(Point[] P,
                                    int startIndex,
                                    int endIndex)
    {

//        Jika ada 2 atau 3 poin, maka gunakan brute force
        if ((endIndex - startIndex) <= 3) {
            return bruteForce(P, endIndex);
        }

//        Temukan titik tengahnya
        int mid = startIndex + (endIndex - startIndex) / 2;
        Point midPoint = P[mid];

//        Perhatikan garis vertikal yang melalui titik tengah
//        hitung jarak terkecil dl di sebelah kiri titik tengah
//        dan dr di sebelah kanan
        float dl = closestUtil(P, startIndex, mid);
        float dr = closestUtil(P, mid, endIndex);

//        Tentukan jarak terkecil dari dua jarak
        float d = Math.min(dl, dr);

//        Bangun strip array[] yang berisi titik-titik
//        yang dekat (lebih dekat dari d) ke
//        garis yang melewati titik tengah
        Point[] strip = new Point[endIndex];
        int j = 0;
        for (int i = 0; i < endIndex; i++) {
            if (Math.abs(P[i].x - midPoint.x) < d) {
                strip[j] = P[i];
                j++;
            }
        }

//        Temukan titik terdekat di strip.
//        Kembalikan minimum d dan jarak terdekat adalah strip[]
        return Math.min(d, stripClosest(strip, j, d));
    }

//    Fungsi utama yang menemukan jarak terkecil
//    Metode ini terutama menggunakan nearUtil()
    public static float closest(Point[] P, int n) {
        Arrays.sort(P, 0, n, new PointXComparator());

//        Gunakan fungsi rekursif terdekatUtil() untuk menemukan jarak terkecil
        return closestUtil(P, 0, n);
    }

}

//Struktur untuk mewakili Titik dalam bidang 2D
class PointXComparator implements Comparator<Point> {

//    Diperlukan untuk mengurutkan susunan titik menurut koordinat X
    @Override
    public int compare(Point pointA, Point pointB) {
        return Integer.compare(pointA.x, pointB.x);
    }

}

class PointYComparator implements Comparator<Point> {

//    Diperlukan untuk mengurutkan susunan titik menurut koordinat Y
    @Override
    public int compare(Point pointA, Point pointB) {
        return Integer.compare(pointA.y, pointB.y);
    }

}

public class ClosestPair {
    public static void main(String[] args) {
        Point[] P = new Point[]{
                new Point(2, 3),
                new Point(12, 30),
                new Point(40, 50),
                new Point(5, 1),
                new Point(12, 10),
                new Point(3, 4)
        };

        DecimalFormat df = new DecimalFormat("#.######");
        System.out.println("The smallest distance is " +
                df.format(Point.closest(P, P.length)));

//        Time Complexity
//        T(n) = 2T(n/2) + O(n) + O(nLogn) + O(n)
//        T(n) = 2T(n/2) + O(nLogn)
//        T(n) = T(n x Logn x Logn)
    }

}