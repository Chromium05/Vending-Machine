import java.util.Scanner;

public class VendingMachine {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int[][] item = new int[9][2]; // Dimensi 0: stock, Dimensi 1: harga
        int choice, qty;
        int pass = 135642;
        int inPass;
        int money, total;
        money = total = 0;
        String[] drink = {"Coca-Cola", "Sprite", "Fanta", "Aqua", "Le Mineral", "Club", "Frestea", "Fruit Tea", "Teh Pucuk"};

        // Inisialisasi Stock
        for (int i = 0; i < item.length; i++) {
            item[i][0] += 10; 
        }

        // Inisialisasi Harga
        for (int j = 0; j < 3; j++) { // Softdrinks
            item[j][1] += 6000;
        }

        for (int j = 3; j < 6; j++) { // Air Mineral
            item[j][1] += 3500;
        }

        for (int j = 6; j < 9; j++) { // Teh
            item[j][1] += 4000;
        }

        // Home Menu
        while(true) {
            System.out.println(" ");
            System.out.println("=======================");
            System.out.println("    Vending Machine    ");
            System.out.println("=======================");
            System.out.println("Item (Price|Quantity)");
            for (int k = 0; k < item.length; k++) {
                System.out.println("[" + (k+1) + "] " + drink[k] + " (Rp." + item[k][1] + "|" + item[k][0] + ")");
            }
            System.out.println("Tekan <0> untuk memasuki admin mode.");
            System.out.print("Input: ");
            choice = input.nextInt();

            switch(choice) {
                default: // Menu Pembelian
                    choice--;
                    for (int i = choice; i < (choice+1); i++) {
                        if (item[i][0] == 0) {
                            System.out.println("\nItem ini habis! Silahkan pilih item lain.");
                        } else {
                            System.out.println("\n===== Purchase =====");
                            System.out.println("Item: " + drink[i]);
                            System.out.print("Jumlah: ");
                            qty = input.nextInt();
                            item[i][1] *= qty;
                            System.out.println("Harga: Rp." + item[i][1]);
                            System.out.print("Masukkan uang: ");
                            money = input.nextInt();

                            if (money < item[i][1] || money > item[i][1]) {
                                do {
                                    System.out.println("\n===== Notice =====");
                                    System.out.println("Harga: " + item[i][1]);
                                    System.out.println("Uang anda harus pas dengan total harga! Silahkan coba lagi.");
                                    System.out.print("Input: ");
                                    money = input.nextInt();
                                } while (money < item[i][1] || money > item[i][1]);
                            }

                            System.out.println("\n===== Details =====");
                            System.out.println("Item: " + drink[i] + " (" + qty + "x)");
                            System.out.println("Harga: Rp." + item[i][1]);
                            System.out.println("Pembayaran berhasil! Silahkan ambil minuman anda.");
                            try {
                                Thread.sleep(2000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }

                            total += money;
                            item[i][1] /= qty;
                            item[i][0] -= qty;
                        }
                    }
                break;
                case 0: // Menu Admin
                    System.out.println("\nTekan <0> untuk kembali ke menu utama.");
                    System.out.print("Masukkan password: ");
                    inPass = input.nextInt();

                    if (inPass != pass && inPass != 0) {
                        do {
                            System.out.println("\nPassword yang anda masukkan salah! Mohon coba lagi.");
                            System.out.println("Tekan <0> untuk kembali.");
                            System.out.print("Masukkan password: ");
                            inPass = input.nextInt();
                        } while (inPass != pass && inPass != 0);
                    }

                    if (inPass == 0) {
                        continue;
                    }
                    
                    if (inPass == pass) {
                        System.out.println("\n===== Admin Mode =====");
                        System.out.println("[1] Restock" + "\n[2] Pendapatan" + "\nTekan tombol lainnya untuk keluar.");
                        System.out.print("Input: ");
                        choice = input.nextInt();

                        switch(choice) {
                            case 1:
                                System.out.println("\n===== Restock =====");
                                for (int k = 0; k < item.length; k++) {
                                    System.out.println("[" + (k+1) + "] " + drink[k] + " (" + item[k][0] + ")");
                                }
                                System.out.println("Tekan <0> untuk kembali ke menu pembelian.");
                                System.out.print("Input: ");
                                choice = input.nextInt();

                                if (choice == 0) {
                                    continue;
                                }

                                choice--;
                                if (item[choice][0] >=  10) {
                                    System.out.println("Stock item ini penuh! Silahkan pilih item lain.");
                                } else {
                                    for (int i = choice; i < (choice+1); i++) {
                                        System.out.println("\nItem: " + drink[i]);
                                        System.out.print("Masukkan jumlah (Max. 10): ");
                                        qty = input.nextInt();

                                        if (qty > 10) {
                                            do {
                                                System.out.println("\n===== Notice =====");
                                                System.out.println("Jumlah yang dimasukkan tidak boleh melebihi 10! Silahkan coba lagi.");
                                                System.out.print("Input: ");
                                                qty = input.nextInt();
                                            } while (qty > 10);
                                        }

                                        item[i][0] += qty;
                                        System.out.println("\n===== Details =====");
                                        System.out.println("Item: " + drink[i]);
                                        System.out.println("Stock: " + item[i][0]);
                                        System.out.println("Item berhasil di restock!");
                                        try {
                                            Thread.sleep(2000);
                                        } catch (InterruptedException e) {
                                            e.printStackTrace();
                                        }
                                     }
                                }
                            break;
                            case 2:
                                System.out.println("\n===== Revenue =====");
                                System.out.println("Total pendapatan = Rp." + total);
                                System.out.println("[1] Ambil" + "\n[2] Keluar");
                                System.out.print("Input: ");
                                choice = input.nextInt();

                                if (choice == 1) {
                                    System.out.print("\nMasukkan jumlah yang ingin diambil: ");
                                    money = input.nextInt();

                                    if (money > total) {
                                        do {
                                            System.out.println("\n===== Notice =====");
                                            System.out.println("Total pendapatan = Rp." + total);
                                            System.out.println("Jumlah yang anda masukkan melebihi total pendapatan! Silahkan coba lagi.");
                                            System.out.print("Input: ");
                                            money = input.nextInt();
                                        } while (money > total);
                                    }

                                    total -= money;
                                    System.out.println("\n===== Details =====");
                                    System.out.println("Total pendapatan = Rp." + total);
                                    System.out.println("Jumlah yang anda ambil = Rp." + money);
                                    System.out.println("Silahkan ambil uang anda!");
                                    try {
                                        Thread.sleep(2000);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }

                                } else {    
                                    continue;
                                }

                            break;
                        }
                    }
                break;
            }
        }
    }
}
