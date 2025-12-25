# UAP_PemrogramanLanjut
📦 Asset Management Application

UAP Pemrograman Lanjut 2025

📌 Deskripsi Program

Asset Management Application adalah aplikasi berbasis Java Swing yang digunakan untuk mengelola data aset. Aplikasi ini dikembangkan untuk memenuhi kebutuhan Ujian Akhir Praktikum (UAP) Pemrograman Lanjut, dengan menerapkan konsep GUI, CRUD, File Handling, dan Exception Handling sesuai dengan materi Modul 1–6.

Aplikasi ini memungkinkan pengguna untuk:

Menambahkan data aset

Menampilkan daftar aset dalam bentuk tabel

Menghapus data aset

Melihat laporan total nilai aset

Menyimpan data secara permanen menggunakan file CSV

🛠️ Teknologi yang Digunakan

Bahasa Pemrograman: Java

GUI: Java Swing

Penyimpanan Data: File CSV

IDE: IntelliJ IDEA / NetBeans / VS Code

Version Control: Git & GitHub

📂 Struktur Project
AssetManagementApp/
├── src/
│   ├── app/
│   │   └── Main.java
│   ├── model/
│   │   └── Asset.java
│   ├── service/
│   │   └── AssetService.java
│   ├── ui/
│   │   ├── MainFrame.java
│   │   ├── AssetFormPanel.java
│   │   ├── AssetTablePanel.java
│   │   └── ReportPanel.java
│   └── util/
│       ├── CSVUtil.java
│       └── SimpleDocumentListener.java
├── data/
│   └── assets.csv
└── README.md

🖥️ Fitur Aplikasi
1️⃣ List Data Aset

Menampilkan data aset dalam bentuk tabel

Data diambil dari file CSV

2️⃣ Input Data Aset

Menambahkan data aset baru

Validasi input menggunakan Exception Handling

3️⃣ Hapus Data Aset

Menghapus data aset berdasarkan baris tabel yang dipilih

4️⃣ Laporan

Menampilkan total nilai aset

🔄 CRUD Implementation
Operasi	Keterangan
Create	Menambah data aset
Read	Menampilkan data aset
Delete	Menghapus data aset
Update	Tidak diimplementasikan
💾 File Handling

Data disimpan dalam file assets.csv

Data tetap tersimpan meskipun aplikasi ditutup

File akan dibuat otomatis jika belum tersedia

⚠️ Exception Handling

Validasi input angka pada form

Penanganan error saat membaca/menulis file

Mencegah aplikasi crash akibat input tidak valid

▶️ Cara Menjalankan Program

Pastikan Java Development Kit (JDK) sudah terinstall

Clone repository:

git clone https://github.com/username/AssetManagementApp.git


Buka project menggunakan IDE

Jalankan file:

src/app/Main.java

👥 Anggota Kelompok

Nama Mahasiswa 1

Nama Mahasiswa 2

📚 Catatan Tambahan

Aplikasi ini dibuat sebagai bagian dari UAP Pemrograman Lanjut

Code review dan testing dilakukan secara manual

Pengembangan selanjutnya dapat mencakup fitur edit data dan pencarian
