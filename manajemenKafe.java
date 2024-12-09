import java.util.Scanner;

class Pesanan {
    String namaPelanggan;
    int nomorMeja;
    String namaMenu;
    int jumlahItem;
    int totalHarga;

    public Pesanan(String namaPelanggan, int nomorMeja, String namaMenu, int jumlahItem, int totalHarga) {
        this.namaPelanggan = namaPelanggan;
        this.nomorMeja = nomorMeja;
        this.namaMenu = namaMenu;
        this.jumlahItem = jumlahItem;
        this.totalHarga = totalHarga;
    }

    // Getter untuk Nama Pelanggan
    public String getNamaPelanggan() {
        return namaPelanggan;
    }

    // Getter untuk Nama Pelanggan
    public String setNamaPelanggan() {
        return namaPelanggan;
    }
    

    // Getter untuk Nomor Meja
    public int getNomorMeja() {
        return nomorMeja;
    }

    // Getter untuk Nama Menu
    public String getMenu() {
        return namaMenu;
    }

    // Getter untuk Jumlah Item
    public int getJumlahItem() {
        return jumlahItem;
    }

    // Getter untuk Harga Item
    public int getHargaItem() {
        return totalHarga;
    }


    public void tampilkanPesanan() {
        System.out.println("Nama Pelanggan: " + namaPelanggan);
        System.out.println("Nomor Meja: " + nomorMeja);
        System.out.println("Detail Pesanan:");
        System.out.println("- " + namaMenu + " x " + jumlahItem + " = Rp " + totalHarga);
        System.out.println("-----------------------------");
    }
}

public class manajemenKafe {
    static Pesanan[] daftarPesanan = new Pesanan[100]; // Kapasitas maksimal pesanan
    static int jumlahPesanan = 0; // Menghitung jumlah pesanan yang sudah dimasukkan
    static Scanner scanner = new Scanner(System.in);

    static String[] menuKafe = {"Kopi Hitam", "Latte", "Teh Tarik", "Mie Goreng"};
    static int[] hargaMenu = {15000, 22000, 12000, 18000};

    public static void main(String[] args) {
        while (true) {
            System.out.println("===== MENU UTAMA =====");
            System.out.println("1. Tambahkan Pesanan");
            System.out.println("2. Tampilkan Daftar Pesanan");
            System.out.println("3. Keluar");
            System.out.print("Pilih menu: ");
            int pilihan = scanner.nextInt();
            scanner.nextLine(); // membersihkan buffer

            if (pilihan == 1) {
                tambahkanPesanan();
            } else if (pilihan == 2) {
                tampilkanDaftarPesanan();
            } else if (pilihan == 3) {
                System.out.println("Terima kasih!");
                break;
            } else {
                System.out.println("Pilihan tidak valid. Coba lagi.");
            }
        }
    }

    static void tambahkanPesanan() {
        if (jumlahPesanan >= 100) {
            System.out.println("Kapasitas pesanan penuh! Tidak dapat menambahkan pesanan baru.");
            return;
        }
    
        System.out.print("Masukkan nama pelanggan: ");
        String namaPelanggan = scanner.nextLine();
    
        System.out.print("Masukkan nomor meja: ");
        int nomorMeja = scanner.nextInt();
        scanner.nextLine(); // membersihkan buffer
    
        int totalHargaPesanan = 0;
    
        // Menampilkan menu kafe sekali sebelum memulai memilih item
        System.out.println("===== MENU KAFE =====");
        for (int i = 0; i < menuKafe.length; i++) {
            System.out.println((i + 1) + ". " + menuKafe[i] + " - Rp " + hargaMenu[i]);
        }
        System.out.println("0. Selesai");
    
        while (true) {
            // Meminta input dari pengguna untuk memilih menu
            System.out.print("Pilih menu (masukkan nomor menu, atau 0 untuk selesai): ");
            int pilihanMenu = scanner.nextInt();
    
            if (pilihanMenu == 0) {
                break; // Keluar dari loop jika pilihan 0
            }
    
            if (pilihanMenu < 1 || pilihanMenu > menuKafe.length) {
                System.out.println("Pilihan menu tidak valid. Coba lagi.");
                continue; // Jika input tidak valid, lanjutkan ke iterasi berikutnya
            }
    
            System.out.print("Masukkan jumlah item untuk " + menuKafe[pilihanMenu - 1] + ": ");
            int jumlahItem = scanner.nextInt();
    
            if (jumlahItem <= 0) {
                System.out.println("Jumlah item harus lebih dari 0. Coba lagi.");
                continue; // Jika jumlah item tidak valid, lanjutkan ke iterasi berikutnya
            }
    
            // Menghitung harga item dan menambahkan total harga pesanan
            int hargaItem = hargaMenu[pilihanMenu - 1] * jumlahItem;
            totalHargaPesanan += hargaItem;
    
            // Menyimpan pesanan
            daftarPesanan[jumlahPesanan] = new Pesanan(namaPelanggan, nomorMeja, menuKafe[pilihanMenu - 1], jumlahItem, hargaItem);
            jumlahPesanan++;
        }
    
        // Menampilkan hasil pesanan setelah selesai memilih menu
        if (totalHargaPesanan > 0) {
            System.out.println("Pesanan selesai ditambahkan.");
            System.out.println("Total harga pesanan: Rp " + totalHargaPesanan);
        } else {
            System.out.println("Tidak ada pesanan yang ditambahkan.");
        }
    }
                    
    static void tampilkanDaftarPesanan() {
        if (jumlahPesanan == 0) {
            System.out.println("Belum ada pesanan.");
            return;
        }
    
        System.out.println("===== DAFTAR PESANAN =====");
    
        // Array untuk melacak apakah pelanggan sudah ditampilkan
        boolean[] sudahDitampilkan = new boolean[jumlahPesanan]; 
    
        // Iterasi untuk menampilkan pesanan per pelanggan
        for (int i = 0; i < jumlahPesanan; i++) {
            // Cek apakah pesanan untuk pelanggan ini sudah ditampilkan
            if (!sudahDitampilkan[i]) {
                System.out.println("Nama Pelanggan: " + daftarPesanan[i].getNamaPelanggan());
                System.out.println("Nomor Meja: " + daftarPesanan[i].getNomorMeja());
                System.out.println("Detail Pesanan:");
    
                // Variabel untuk menyimpan total harga pesanan pelanggan
                int totalHargaPelanggan = 0;
    
                // Menampilkan detail pesanan pelanggan
                for (int j = i; j < jumlahPesanan; j++) {
                    if (daftarPesanan[j].getNamaPelanggan().equals(daftarPesanan[i].getNamaPelanggan())) {
                        System.out.println("- " + daftarPesanan[j].getMenu() + " x " + daftarPesanan[j].getJumlahItem() + " = Rp " + daftarPesanan[j].getHargaItem());
                        totalHargaPelanggan += daftarPesanan[j].getHargaItem(); // Menambah total harga pesanan pelanggan
                        sudahDitampilkan[j] = true; // Menandai bahwa pesanan ini sudah ditampilkan
                    }
                }
    
                // Menampilkan total harga pesanan
                System.out.println("Total harga pesanan: Rp " + totalHargaPelanggan);
                System.out.println(); // Menambahkan baris kosong setelah setiap pelanggan
            }
        }
    }
}