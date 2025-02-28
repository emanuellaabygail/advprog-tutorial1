## Emanuella Abygail - 2306152185

Deployment: [Adpro Eshop](https://adpro-emanuellaabygail.koyeb.app/)
<details>
<summary>Module 1</summary>

## Reflection 1
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

## Reflection 2

1. **Unit Test**

    - Setelah menulis unit test, saya merasa bahwa unit test memudahkan saya untuk memastikan setiap komponen dapat berfungsi sesuai dengan yang seharusnya. Dalam penulisan unit test, kita dipaksa untuk memikirkan segala skenario yang dapat terjadi untuk memastikan fungsi berjalan dengan baik.
    - Jumlah unit test yang ideal bergantung pada fungsi yang akan diuji. Unit test yang dibuat harus menguji masing-masing fungsi, mencakup kasus positif dan negatif, menguji *edge cases*, dan memastikan semua alur yang mungkin sudah benar.
    - Code coverage mengukur berapa banyak dari kode yang sudah ter-*cover*, tetapi code coverage 100% tidak berarti program tidak memiliki bug. Code coverage hanya mengukur jalur kode yang dieksekusi, bukan logika bisnis. Code coverage tidak menguji interaksi antarkomponen, edge cases yang mungkin tidak terpikirkan, dan performa serta keamanan kode.

2. **Functional Test**

    - Jika kita membuat functional test baru dengan setup yang sama, terdapat pelanggaran prinsip clean coding yang terjadi, di antaranya:
        - *Code Duplication* karena *setup procedures* dan *instance variables* yang sama diulangi, melanggar prinsip DRY (*Don't Repeat Yourself*), dan mempersulit maintanance
        - *Lack of Inheritance* karena setup yang sama menandakan ada behavior abstrak yang bisa diturunkan sehingga membuat functional test baru menghilangkan kesempatan untuk inheritance dan reusability
    - Permasalahan yang ditimbulkan dari dibuatnya functional test baru adalah sebagai berikut
        - *Mantainability* yang terhambat karena jika ada perubahan, banyak yang harus diubah, meningkatkan risiko *human error*, dan memperlambat proses pengembangan.
        - *Readability*, kode berulang membuat test lebih panjang sehingga sulit untuk membedakan bagian yang unik dari setiap test.
        - *Scalability* karena sulit menambah test baru karena harus menduplikasi (*copy-paste*) setup dan tidak efisien dalam pengembangan jangka panjang
    - Untuk memperbaiki hal ini, kita dapat membuat base test class, meng-*extend* base class, kemudian menambahkan *utility methods* sesuai dengan fungsi yang akan ditest. Dengan begitu, setup code hanya ada di satu tempat, program lebih mudah dimaintain, test bisa fokus pada logika yang spesifik, serta *utility methods* dapat digunakan berulang (*reusable*)

</details>

<details>
<summary>Module 2</summary>

## Reflection
1. **Code Quality Issues and Fixing Strategies**
   - *Removing unnecessary method*. Pada ProductRepositoryTest.java, terdapat dua unit test yang melakukan hal yang sama. Hal tersebut seharusnya tidak boleh terjadi karena unit test seharusnya *meaningful*. Oleh karena itu, saya menghapus salah satu dari unit test tersebut.
   - *Removing unused import*. Pada salah satu file unit test, terdapat import yang tidak digunakan. Hal tersebut akan "mengotori" kode sehingga harus dihapus.
   - *Adding comment in empty method*. Pada unit test, terdapat method `setUp()` yang kosong karena unit test tersebut tidak memerlukan proses set up. Agar tidak terjadi kesalahpahaman di kemudian hari, perlu ditambahkan komentar yang menjelaskan bahwa method tersebut memang dibiarkan kosong.
2. **Continuous Integration and Continuous Deployment**
    
    Ya. Menurut saya, implementasi yang telah saya lakukan saat ini telah sesuai dengan definisi dan ketentuan dari *Continuous Integration and Continuous Deployment*. 
   - *Continuous Integration* berarti setiap perubahan pada kode terintegrasi dan akan diuji oleh *automated build script*. Hal ini telah diimplementasikan dengan cara membuat workflow `ci.yml` yang menjalankan unit test, `scorecard.yml` yang melakukan pengecekan keamanan dengan OSSF, dan `sonarcloud.yml` yang melakukan pemeriksaan kode melalui SonarCloud. Setiap terjadi perubahan pada kode dan perubahan tersebut di-*push*, ketiga workflows tersebut akan berjalan untuk melakukan pengecekan kode.
   - *Continuous Deployment* berarti setiap perubahan pada kode akan di-*deploy* secara langsung. Hal ini sudah diimplementasikan melalui PaaS Koyeb. Dengan menggunakan Koyeb, setiap perubahan yang di-*push* akan langsung dideploy ke web.
</details>

<details>
<summary>Module 3</summary>

## Reflection
1. **Principles applied to the code**
   - *Single Responsibility Principle (SRP)*: Pemisahan antara `ProductController` dan juga `CarController`. Pada before-solid, kedua kelas controller tersebut dijadikan satu, tetapi setelah penerapan SRP, saya memisahkan keduanya agar masing-masing class hanya memiliki satu responsibility.
   - *Open-Closed Principle (OCP)*: Untuk menerapkan OCP, saya membuat class baru yaitu `GenericRepository`. `ProductRepository` dan `CarRepository` memiliki struktur yang mirip. Jika nantinya akan ditambahkan model baru, kita tidak perlu mengedit repository yang sudah ada, hanya perlu mengimplement `GenericRepository`
   - *Liskov Substitution Principle (LSP)*: Awalnya, `CarController` meng-extend `ProductController` padahal terdapat method yang berbeda di antara keduanya. Hal tersebut melanggar LSP. Oleh karena itu, saya mengatasinya dengan menghapus `extend ProductController` pada `CarController`
   - *Interface Segregation Principle (ISP)*: Sudah diimplementasikan pada `CarService` dan `ProductService` karena kedua interface tersebut mendefinisikan methods yang sesuai dengan masing-masing model sehingga sudah sesuai dengan ISP.
   - *Dependency Inversion Principle (DIP)*: Sebelumnya, `CarController` bergantung langsung ke `CarServiceImpl` yang merupakan kelas abstract. Hal ini melanggar DIP. Untuk memperbaikinya, saya mengganti data type dari `carService` menjadi `CarService` yang merupakan sebuah interface.


2. **Advantages of applying SOLID principles to project**
   
   Menggunakan SOLID Principles membuat kode lebih mudah dipahami, dikelola, dan dikembangkan. Dengan menerapkan prinsip Single Responsibility, setiap kelas memiliki satu tugas utama, sehingga perubahan lebih terlokalisasi. Open/Closed Principle memungkinkan penambahan fitur tanpa harus mengubah kode yang sudah ada, mengurangi risiko bug. Liskov Substitution memastikan bahwa subclass bisa menggantikan superclass tanpa mengubah perilaku sistem. Interface Segregation membantu menghindari pembuatan interface yang terlalu besar, sehingga setiap bagian hanya menggunakan metode yang memang diperlukan. Terakhir, Dependency Inversion membuat sistem lebih fleksibel dengan mengandalkan abstraksi daripada implementasi langsung, yang mempermudah pengujian dan pengembangan di masa depan.


3. **Disdvantages of not applying SOLID principles to project**

   Tidak menerapkan prinsip SOLID bisa membuat kode menjadi sulit dipahami, diperbaiki, dan dikembangkan. Tanpa Single Responsibility Principle (SRP), sebuah kelas bisa memiliki terlalu banyak tanggung jawab, sehingga setiap perubahan berisiko memengaruhi banyak bagian lain. Jika Open/Closed Principle (OCP) tidak diterapkan, menambahkan fitur baru bisa menyebabkan perubahan pada kode lama, meningkatkan risiko bug. Mengabaikan Liskov Substitution Principle (LSP) dapat membuat subclass tidak bisa menggantikan superclass dengan benar, menyebabkan error yang sulit dideteksi. Interface Segregation Principle (ISP) yang diabaikan bisa menghasilkan antarmuka yang terlalu besar dan membebani kelas dengan metode yang tidak perlu. Terakhir, tanpa Dependency Inversion Principle (DIP), sistem akan terlalu bergantung pada implementasi konkret, menyulitkan pengujian dan perubahan di masa depan.
   
</details>