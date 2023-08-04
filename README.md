# buku-service
## pre-config
1. Disini saya memakai database postgresql, maka install master postgresql terlebih dahulu
2. Setelah master terinstall, lakukan setting admin db dengan memasukan username dan passwordnya. username yg saya pakai adalah postgres dan passwordnya adalah admin
3. Saya mengoneksikan db dengan menggunakan tools dbeaver.
4. Jika koneksi sudah sukses, maka akan masuk ke database dan schema.
5. Lalu saya membuat schema db test yang akan digunakan
6. Setelah schema terbuat, maka kita create table yang sesuai dengan kebutuhan.
7. Kebutuhan disini kita perlu membuat table buku dan kolom yang dibutuhkan
8. Setelah table terbuat, maka kita mappingkan ke java spring framework
9. Generate java spring framework dari situs https://start.spring.io/ 
10. Sebelum generate, kita bisa memilih versi project, languange, versi spring boot, project metadata, dan dependency
11. Setelah terisi sesuai dengan kebutuhan kita, maka kita tinggal klik generate dan tunggu sampai proses downloadnya selesai.
12. Setelah selesai, maka kita export project tersebut menjadi sebuah folder
13. Setelah menjadi folder, kita bisa membukanya dengan tools intellij misalnya untuk membuat api

## cara running
1. Setting application.properties
2. Setting server-port terlebih dahulu. Disini saya menggunakan server-port 8081 agar tidak tabrakan dengan port yang lainnya
3. Setting database yg meliputi host, port, name, url, username, dan password dengan value yang sama dengan saat kita setting database di preconfig. Value disini bisa dilihat di tools database. Misalnya saya memakai dbeaver. Di connection properties bisa dilihat valuenya untuk dimasukkan ke dalam application.properties
4. Kemudian setting lagi dialect properties hibernate dari JPA
5. Berikutnya kita setting file pom nya. File pom ini berisi library yang kita butuhkan untuk membuat api. Disini kita bisa menambahkan atau menghapus dependency
6. Lalu setting path api, nama service, dan swaggernya
7. Setelah application.properties sudah tersetting, maka kita mappingkan table dari database ke model java class
8. Karena table yg kita buat buku, maka kita namakan classnya buku biar tidak bingung. Jadi nama table di db harus sama dengan nama model class
9. Di class buku tersebut, kita representasikan yg ada di table dengan menambahkan annotation entity dan table. Di dalam annotation table, kita isi nama table dan schema yang sama dengan yang di db
10. Dengan menambahkan annotation entity, maka tergeneratelah field id di class buku. Field id ini tipe data dan definisi kolomnya harus sama dengan yang di database. Karena kalau tidak sama bisa menyebabkan error
11. Lalu kita buat field-field di class buku yang mana nama field-field ini diambil dari kolom table buku
12. Setelah itu, kita tambahkan annotation column di setiap nama field yang ada di class buku untuk merepresentasikan kolom yg ada di table buku
13. Kemudian kita tambahkan setting constractur di class buku
14. Selanjutnya, kita buat package config yang isinya class-class untuk konfigurasi swagger dan server portnya agar bisa kita ubah-ubah server portnya ketika terjadi tabrakan dengan port lain dan swaggernya bisa aktifkan atau dinonaktifkan
15. Berikutnya, kita buat package constant untuk menaruh value-value yang sifatnya tidak berubah-ubah dan bisa dipakai di banyak class sehingga tinggal ambil saja dari class constant tersebut jika dibutuhkan.
16. Kemudian kita buat package exception untuk memappingkan error message ketika terjadi error dari request atau responsenya.
17. Berikutnya, kita buat package utils yang berisi class java untuk membuat method yang sifatnya reusable misalnya getDateFormat() agar ketika eksekusi di service, kita tinggal panggil saja method tersebut sehingga tidak banyak code yang duplikat.
18. Lalu kita buat package repository yang isinya interface untuk melakukan query ke db. Query bisa menggunakan query bawaan jpa atau bisa juga menggunakan native query
19. Kemudian, kita buat package service yang isinya class service. Di sini kita mappingkan request dan response untuk membuat apinya
20. Setelah service jadi, kita buat package controller yang berisi class controller 
21. Di class controller ini kita definisikan nama apinya untuk memanggil method di class service yang kita sudah kita mappingkan
22. Lalu kita running java spring bootnya dengan cara klik kanan pada class PendaftaranbukuApplication dan klik Run PendaftaranbukuApplication
23. Maka akan muncul proses lognya. Disini kita tunggu sampai proses lognya Completed initialization in 2 ms yang menandakan bahwa service sudah ready untuk di hit
24. Kemudian kita coba hit api yang kita buat tadi menggunakan postman atau swagger apakah sudah berjalan dengan benar atau belum
25. Jika response status code di postman atau swagger bernilai 200 OK berarti api tidak ada error dan kita tinggal cek isi responsenya. Jika isi responsenya sudah sesuai dengan kebutuhan kita, maka logic yang kita mappingkan sudah benar. Namun jika responsenya tidak 200 OK, berarti ada beberapa kasalahan. Misalnya salah kirim request  atau requestnya tidak valid atau data yang dicari tidak ditemukan