# Reflection 1
## Emanuella Abygail - 2306152185

### Clean and Secure Coding Principles Implemented
1. **Penamaan yang Bermakna**: 
   - Menggunakan nama kelas yang deskriptif seperi `ProductController`, `ProductService`, ataupun `ProductRepository`
   - Nama method yang digunakan jelas menyampaikan tujuannya seperti `createProduct` untuk membuat sebuah product
2. **Prinsip Single Responsibility**:
   Setiap kelas memiliki tanggung jawab spesifik:
   - Controller menangani permintaan HTTP dan routing
   - Service mengelola logika bisnis
   - Repository menangani penyimpanan data
3. **Konsistensi dalam Format Penulisan Kode**:

   Indentasi yang rapi, penggunaan spasi yang konsisten, dan pemisahan logika yang jelas meningkatkan keterbacaan kode.
4. **Penggunaan UUID**:

    Untuk mencegah percobaan berkali-kali ataupun tebakan untuk ID product, penggunaan UUID membantu membuat program lebih aman.