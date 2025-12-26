# Asset Management App (Java Swing)

## Deskripsi Aplikasi

Asset Management App adalah aplikasi desktop berbasis **Java Swing** yang digunakan untuk mengelola data aset. Aplikasi ini dibuat untuk memenuhi ketentuan tugas yang mengimplementasikan materi **Modul 1–6**, meliputi GUI, OOP, CRUD, File Handling, dan Version Control.

Data aset disimpan secara permanen menggunakan **file CSV**, sehingga data tidak hilang ketika aplikasi ditutup dan dijalankan kembali.

---

## Fitur Utama

### 1. Dashboard

* Halaman utama aplikasi
* Menyediakan menu navigasi:

  * Tambah Aset
  * Data Aset
  * Laporan
* Desain GUI dibuat user-friendly dengan pengaturan warna, font, dan layout.

### 2. Data Aset (List Data)

* Menampilkan data aset dalam bentuk **JTable**
* Fitur:

  * Searching data
  * Sorting data
  * Edit data aset
  * Hapus data aset
* Data diambil langsung dari file CSV

### 3. Form Aset (Input Data)

* Digunakan untuk:

  * Menambah data aset
  * Mengedit data aset
* Dilengkapi validasi input sebelum data disimpan

### 4. Laporan Aset

* Menampilkan ringkasan data:

  * Total aset
  * Jumlah kategori
  * Aset terbaru
* Data diperbarui secara otomatis

---

## Struktur Folder

```
src/
├── app/
│   └── Main.java
├── model/
│   └── Asset.java
├── service/
│   └── AssetService.java
├── util/
│   ├── CSVUtil.java
│   └── SimpleDocumentListener.java
└── ui/
    ├── MainFrame.java
    ├── DashboardPanel.java
    ├── AssetTablePanel.java
    ├── AssetFormPanel.java
    └── ReportPanel.java
```

---

## Penjelasan Teknis

### GUI (Java Swing)

* Menggunakan komponen Swing:

  * JFrame, JPanel, JLabel, JButton, JTable
* Layout yang digunakan:

  * BorderLayout
  * BoxLayout
* Tampilan dibuat konsisten dan mudah digunakan

### CRUD & File Handling

* **Create**: Menambah data aset
* **Read**: Menampilkan data dari file CSV
* **Update**: Mengedit data aset berdasarkan ID
* **Delete**: Menghapus data aset

File penyimpanan:

```
data/assets.csv
```

API Java yang digunakan:

* ArrayList
* LocalDate
* Comparator
* BufferedReader & PrintWriter

### Exception Handling

* Menggunakan try-catch untuk menangani:

  * Error baca/tulis file
  * Validasi input
  * File tidak ditemukan

---

## Cara Menjalankan Program

### Persyaratan

* JDK minimal **Java 17**
* IDE (IntelliJ IDEA / NetBeans / VS Code)

### Langkah Menjalankan

1. Buka project di IDE
2. Pastikan struktur folder sesuai
3. Jalankan file:

```
src/app/Main.java
```

4. Aplikasi akan tampil dalam bentuk GUI

---

## Version Control (Git)

### Workflow

* Push dilakukan secara berkala
* Menggunakan branching untuk setiap fitur

Contoh branch:

```
main
feature-crud
feature-ui
feature-report
```

Contoh perintah Git:

```bash
git checkout -b feature-crud
git add .
git commit -m "Menambahkan fitur CRUD aset"
git push origin feature-crud

git checkout main
git merge feature-crud
```

---

## Testing (Manual Testing)

Contoh skenario pengujian:

1. Input data aset melalui form
2. Klik simpan
3. Periksa data muncul di tabel
4. Periksa data tersimpan di file CSV
5. Tutup aplikasi
6. Jalankan kembali aplikasi dan pastikan data masih ada

---

## Code Review

### Temuan

* Duplikasi kode utilitas file → dipusatkan di CSVUtil
* Penamaan variabel diperjelas
* Pemisahan logika UI dan data menggunakan Service

### Perbaikan

* Refactor kode duplikat
* Struktur package disesuaikan dengan tanggung jawab kelas

---

## Kesimpulan

Aplikasi ini telah memenuhi ketentuan:

* GUI Java Swing
* Minimal 4 halaman
* CRUD & File Handling
* Exception Handling
* Version Control (Git)
* Testing dan Code Review


