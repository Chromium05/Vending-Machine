#include <iostream>
#include <iomanip>
#include <string>
#include <thread>
#include <chrono>

using namespace std;

int main() {
    int item[9][2] = {{0}}; 
    int choice, qty;
    int pass = 135642;
    int inPass;
    int money, total;
    money = total = 0;
    string drink[] = {"Coca-Cola", "Sprite", "Fanta", "Aqua", "Le Mineral", "Club", "Frestea", "Fruit Tea", "Teh Pucuk"};

    
    for (int i = 0; i < 9; i++) {
        item[i][0] += 10;
    }

    
    for (int j = 0; j < 3; j++) { 
        item[j][1] += 6000;
    }

    for (int j = 3; j < 6; j++) { 
        item[j][1] += 3500;
    }

    for (int j = 6; j < 9; j++) { 
        item[j][1] += 4000;
    }

    
    while (true) {
        cout << "\n=======================";
        cout << "\n    Vending Machine    ";
        cout << "\n=======================";
        cout << "\nItem (Price|Quantity)";

        for (int k = 0; k < 9; k++) {
            cout << "\n[" << (k + 1) << "] " << drink[k] << " (Rp." << item[k][1] << "|" << item[k][0] << ")";
        }

        cout << "\nTekan <0> untuk memasuki admin mode.";
        cout << "\nInput: ";
        cin >> choice;

        switch (choice) {
            default:
                choice--;
                for (int i = choice; i < (choice + 1); i++) {
                    if (item[i][0] == 0) {
                        cout << "\nItem ini habis! Silahkan pilih item lain.";
                    } else {
                        cout << "\n===== Purchase =====";
                        cout << "\nItem: " << drink[i];
                        cout << "\nJumlah: ";
                        cin >> qty;
                        item[i][1] *= qty;
                        cout << "Harga: Rp." << item[i][1];
                        cout << "\nMasukkan uang: ";
                        cin >> money;

                        if (money < item[i][1] || money > item[i][1]) {
                            do {
                                cout << "\n===== Notice =====";
                                cout << "\nHarga: " << item[i][1];
                                cout << "\nUang anda harus pas dengan total harga! Silahkan coba lagi.";
                                cout << "\nInput: ";
                                cin >> money;
                            } while (money < item[i][1] || money > item[i][1]);
                        }

                        cout << "\n===== Details =====";
                        cout << "\nItem: " << drink[i] << " (" << qty << "x)";
                        cout << "\nHarga: Rp." << item[i][1];
                        cout << "\nPembayaran berhasil! Silahkan ambil minuman anda.";
                        this_thread::sleep_for(chrono::milliseconds(2000));

                        total += money;
                        item[i][1] /= qty;
                        item[i][0] -= qty;
                    }
                }
                break;

            case 0:
                cout << "\nTekan <0> untuk kembali ke menu utama.";
                cout << "\nMasukkan password: ";
                cin >> inPass;

                if (inPass != pass && inPass != 0) {
                    do {
                        cout << "\nPassword yang anda masukkan salah! Mohon coba lagi.";
                        cout << "\nTekan <0> untuk kembali.";
                        cout << "\nMasukkan password: ";
                        cin >> inPass;
                    } while (inPass != pass && inPass != 0);
                }

                if (inPass == 0) {
                    continue;
                }

                if (inPass == pass) {
                    cout << "\n===== Admin Mode =====";
                    cout << "\n[1] Restock" << "\n[2] Pendapatan" << "\nTekan tombol lainnya untuk keluar.";
                    cout << "\nInput: ";
                    cin >> choice;

                    switch (choice) {
                        case 1:
                            cout << "\n===== Restock =====";
                            for (int k = 0; k < 9; k++) {
                                cout << "\n[" << (k + 1) << "] " << drink[k] << " (" << item[k][0] << ")";
                            }
                            cout << "\nTekan <0> untuk kembali ke menu pembelian.";
                            cout << "\nInput: ";
                            cin >> choice;

                            if (choice == 0) {
                                continue;
                            }

                            choice--;
                            if (item[choice][0] >= 10) {
                                cout << "Stock item ini penuh! Silahkan pilih item lain.";
                            } else {
                                for (int i = choice; i < (choice + 1); i++) {
                                    cout << "\nItem: " << drink[i];
                                    cout << "\nMasukkan jumlah (Max. 10): ";
                                    cin >> qty;

                                    if (qty > 10) {
                                        do {
                                            cout << "\n===== Notice =====";
                                            cout << "\nJumlah yang dimasukkan tidak boleh melebihi 10! Silahkan coba lagi.";
                                            cout << "\nInput: ";
                                            cin >> qty;
                                        } while (qty > 10);
                                    }

                                    item[i][0] += qty;
                                    cout << "\n===== Details =====";
                                    cout << "\nItem: " << drink[i];
                                    cout << "\nStock: " << item[i][0];
                                    cout << "\nItem berhasil di restock!";
                                    this_thread::sleep_for(chrono::milliseconds(2000));
                                }
                            }
                            break;

                        case 2:
                            cout << "\n===== Revenue =====";
                            cout << "\nTotal pendapatan = Rp." << total;
                            cout << "\n[1] Ambil" << "\n[2] Keluar";
                            cout << "\nInput: ";
                            cin >> choice;

                            if (choice == 1) {
                                cout << "\nMasukkan jumlah yang ingin diambil: ";
                                cin >> money;

                                if (money > total) {
                                    do {
                                        cout << "\n===== Notice =====";
                                        cout << "\nTotal pendapatan = Rp." << total;
                                        cout << "\nJumlah yang anda masukkan melebihi total pendapatan! Silahkan coba lagi.";
                                        cout << "\nInput: ";
                                        cin >> money;
                                    } while (money > total);
                                }

                                total -= money;
                                cout << "\n===== Details =====";
                                cout << "\nTotal pendapatan = Rp." << total;
                                cout << "\nJumlah yang anda ambil = Rp." << money;
                                cout << "\nSilahkan ambil uang anda!";
                                this_thread::sleep_for(chrono::milliseconds(2000));

                            } else {
                                continue;
                            }

                            break;
                    }
                }
                break;
        }
    }

    return 0;
}
