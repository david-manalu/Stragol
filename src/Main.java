import java.util.Arrays;

// Nama : David Kharis Elio M
// NIM : 1121028

class Main {
//    Nomor 1
//    Kompleksitas -> O(n/2)
    public void swap(int[] gelas, int a, int b) {
        gelas[a] += gelas[b];
        gelas[b] = gelas[a] - gelas[b];
        gelas[a] -= gelas[b];
    }

    public void gelasProses(int[] glasses) {
        int n = glasses.length / 2;
        int y = n % 2 - 2;
        for (int i = 2; i - 1 < n; i += 2) {
            swap(glasses, i - 1, n + i + y);
        }
    }

//    Nomor 2
//    Kompleksitas o(n)
public void gelasProsesAcak(int[] gelas) {
    var test = Arrays.copyOf(gelas, gelas.length);
    int a = 0, b = 0;
    for (int l : test) {
        if (l == 0) {
            gelas[2 * a + 1] = l;
            a++;
        }
        else {
            gelas[2 * b] = l;
            b++;
        }
    }
}

//    Nomor 3
//    Kompleksitas -> O(log2 n)
    public int tongkatPotongan(int panjang) {
        for (int i = 0; panjang > 1; i++) {
            panjang = (int) Math.ceil((double) panjang / 2);
            if (panjang == 1) {
                return i + 1;
            }
        }
        return 0;
    }

    Main() {
        int[] gelas = new int[]{1, 1, 1, 1, 0, 0, 0, 0};
        int[] gelasAcak = new int[]{1, 1, 0, 1, 1, 0, 0, 0};

        gelasProses(gelas);
        gelasProsesAcak(gelasAcak);

        System.out.println(Arrays.toString(gelas));
        System.out.println(Arrays.toString(gelasAcak));

        System.out.println(tongkatPotongan(10));
    }

    public static void main(String[] args) {
        new Main();
    }
}