# Tutorial Advanced Programming

### Rafi Ghani Harditama
### 2206081364
### Advanced Programming A / VRO 

[Link Website](https://eshop-rafghan.koyeb.app/)

## Tutorial 1

<details>
<summary>Reflection 1</summary>
Prinsip Clean Code yang diterapkan di tugas ini:

* Meaningful Names
_Meaningful names_ berarti memberikan nama yang bermakna dan deskriptif kepada komponen dalam kode yang kita buat agar pembaca kode dapat dengan mudah memahaminya.

contoh implementasinya:
``` java
public class Product {
    private String productId;
    private String productName;
    private int productQuantity;
}
```

* Function
_Function_ dalam prinsip _clean code_ menekankan pembuatan fungsi dengan ringkas, fokus, dan hanya melakukan satu tugas yang terdefinisi dengan jelas. Fungsi yang baik harus memiliki nama yang bermakna, menerima parameter yang sesuai, dan mengembalikan nilai yang relevan. 

contoh implementasinya:
``` java
public Product findById(String id){
    for (Product product : productData){
        if (product.getProductId().equals(id)){
            return product;
        }
    }
    return null;
}
```

* Objects and Data Structures
contoh implementasinya:
``` java
public interface ProductService {
    public Product create(Product product);
    public List <Product> findAll();
    public Product findById(String id);
    public Product editProduct(Product editedProduct);
    public Product deleteProduct(String id);
}
```
``` java
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product create(Product product) {
        productRepository.create(product);
        return product;
    }
    ...
}
```

* Error Handling
Kita dapat menulis _clean code_ yang kuat dengan memperlakukan _error handling_ sebagai masalah tersendiri, yang dapat dipisahkan dari logika utama kode kita. Program kita bisa saja salah, dan programmer bertanggung jawab untuk menangani kemungkinan kesalahan tersebut secara efektif.

contoh implementasinya:
``` java
@Test
void pageTitle_isCorrect(ChromeDriver driver) throws Exception {
    driver.get(baseUrl);
    String pageTitle = driver.getTitle();

    assertEquals("ADV Shop", pageTitle);
} 
```


How to Improve My Code
Setelah meninjau kode saya, ada aspek yang dapat ditingkatkan terkait validasi input. Saat ini, pada halaman create product dan edit product, perlu ditambahkan validasi untuk memastikan bahwa tipe data input sesuai dan jumlah quantity yang diinput tidak boleh negatif.

</details>

<details>
<summary>Reflection 2</summary>

* Unit Test & Code Coverage

Setelah menulis _unit test_, saya merasa lebih percaya diri tentang kebenaran kode saya. _Unit test_ membantu memastikan setiap komponen dari kode bekerja seperti yang diharapkan dan membantu mengeliminasi kesalahan dari kode saya. Jumlah _unit test_ yang dibutuhkan tergantung pada banyaknya fungsi yang ada, biasanya minimal setiap fungsi memiliki satu unit test.

Untuk memastikan unit test kita cukup untuk memverifikasi program kita, kita bisa menggunakan alat analisis _code coverage_. _Code coverage_  mengukur persentase kode sumber yang dijalankan oleh tes kita. Meskipun memiliki _code coverage_ 100%, tidak menjamin bahwa kode kita bebas dari _error_. Kita dapat menggabungkan _code coverage_ dengan teknik pengujian lainnya agar pengujiannya lebih menyeluruh.Teknik ini membantu mengidentifikasi masalah potensial dan memastikan kode berfungsi dengan benar. 

* Number of Items in the Product List

Ketika kita membuat rangkaian _functional test_ baru yang mirip dengan yang sudah ada, ada kemungkinan untuk menimbulkan beberapa masalah baru yang terkait dengan duplikasi kode, yang dapat mengakibatkan beban pemeliharaan yang meningkat dan penurunan kebersihan serta kualitas kode.

Solusi yang dapat kita terapkan adalah dengan melakukan refaktor kode untuk membuat fungsi yang dapat digunakan kembali sehingga kita dapat menghindari duplikasi kode di berbagai rangkaian uji. Selain itu, menggunakan inheritance untuk berbagi kode antara berbagai rangkaian uji juga dapat membantu mengurangi duplikasi. Lalu kita juga dapat mengorganisir rangkaian _functional test_ dengan baik dan mempertimbangkan pengujian otomatis untuk menjaga konsistensi dan mempercepat siklus pengujian.
</details>

## Tutorial 2

<details>
<summary>Reflection 1</summary>

* List the code quality issue(s) that you fixed during the exercise and explain your strategy on fixing them.

    + Tabel pada halaman List Produk tidak memiliki deskripsi. Dapat ditambahkan elemen `<caption>` ke dalam tabel untuk memberikan deskripsi singkat tentang konten tabel tersebut.

    + Field Injection seperti `@Autowired` menyisipkan dependensi ke dalam sebuah kelas melalui properti atau field. Fied injection dapat menyebabkan ketidakjelasan ketergantungan serta kesulitan dalam pengujian. Untuk memperbaiki masalah tersebut dapat dilakukan dengan mengubah field injection menjadi constructor injection pada kelas controller dan service

    + Modifier public pada unit test. Untuk memperbaiki masalah tersebut dapat dilakukan dengan menghapus semua modifier public pada semua kelas test. 

    + Menambahkan Assertion pada `EshopApplicationTests.java`

    + Menghapus semua importan yang tidak terpakai penting untuk menjaga kebersihan dan keterbacaan kode. Importan yang tidak digunakan dapat menyebabkan overhead yang tidak perlu dalam kompilasi dan dapat membingungkan pembaca kode. Oleh karena itu, menghapus impor yang tidak digunakan adalah _best practice_ dalam pengembangan perangkat lunak berbasis Java.

* Look at your CI/CD workflows (GitHub)/pipelines (GitLab). Do you think the current implementation has met the definition of Continuous Integration and Continuous Deployment? Explain the reasons (minimum 3 sentences)!

Menurut saya, implementasi yang saya gunakan telah memenuhi konsep CI/CD dengan menggunakan GitHub Workflows. Setiap kali saya melakukan perubahan yang di push atau pull request ke repository GitHub, secara otomatis akan dilakukan test yang sudah ditentukan dalam ci.yml dan dianalis dengan SonarCloud. Jika pengecekan berhasil, baru bisa dilakukan pull request. Hal tersebut sudah memenuhi konsep Continous Integration yang berguna untuk memastikan setiap perubahan kode tidak merubah fungsionalitasnya. Selain itu, saya juga sudah menerapkan Continous Delivery dengan melakukan deploy secara otomatis ke PaaS Koyeb. 
</details>

## Tutorial 3
<details>
<summary>Reflection</summary>

* Explain what principles you apply to your project!
    + **Single Responsibility Principle(SRP)** : Saya mengimplementasikan SRP dengan memisahkan `Product Controller` dan `Car Controller` yang awalnya berada pada 1 file yang sama menjadi terpisah di filenya masing-masing. Hal tersebut bertujuan untuk memastikan bahwa setiap kelas memiliki tanggung jawabnya sendiri.
    + **Interface Segregation Principle(ISP)** : Saya menerapkan prinsip ISP dengan memisahkan interface `Product Controller` dan interface `Car Controller`. Hal tersebut bertujuan untuk memastikan setiap interface hanya memiliki tanggung jawab yang sesuai dengan kebutuhan modul yang mengimplementasikannya. Sehingga kelas yang berhubungan dengan produk dan yang berhubungan dengan mobil dapat fokus pada tugasnya masing-masing tanpa harus terbebani oleh fungsi yang lain
    + **Dependency Inversion Principle(DIP)** : Saya menerapkan prinsip DIP dengan mengubah `private CarServiceImpl carService;` menjadi `private CarService carService;` pada file `CarController.java`. Hal tersebut saya lakukan untuk menghindari adanya masalah ketika saya melakukan perubahan di `CarServiceImpl`

* Explain the advantages of applying SOLID principles to your project with examples.

    Dengan menerapkan SOLID principles, kode saya menjadi lebih rapi dan bersih sehingga mudah dibaca dan dipahami. Contohnya pada tugas saya ini adalah ketika saya memisahkan `Product Controller` dan `Car Controller`. Awalnya kedua kelas tersebut berada pada satu file yang sama sehingga kode tersebut menjadi sulit untuk dibaca dan dipahami karena sangat kompleks dan panjang. Oleh karena itu, dengan memisahkan kedua class tersebut dapat memudahkan kita untuk membaca dan memahami kode tersebut. Selain itu, kode juga lebih aman ketika ada perubahan karena setiap komponen pada kode saya sudah menjalankan tugasnya masing-masing dan tidak akan merusak kode lain ketika ada perubahan. 
    
* Explain the disadvantages of not applying SOLID principles to your project with examples.

    Jika kita tidak menerapkan SOLID principles pada kode kita, maka kode akan lebih sulit untuk dibaca dan dipahami. Selain itu, kode kita akan lebih sulit untuk dirawat juga. Belum lagi ketika kode kita saling bergantung satu sama lain sehingga ketika kita mengubah sesuatu dapat berdampak langsung ke kode lainnya. Contohnya ketika `CarController` masih bergantung pada `CarServiceImpl`, ketika ada perubahan di `CarServiceImpl` maka akan langsung berdampak dan dapat menyebabkan kerusakan di `CarController` 
</details>

## Tutorial 4

<details>
<summary>Reflection</summary>
* Setelah menyelesaikan tutorial di modul ini, saya merasa bahwa pendekatan TDD (Test-Driven Development) sangat bermanfaat karena berkontribusi positif terhadap kualitas kode yang saya hasilkan. Siklus yang terdiri dari tahapan RED, GREEN, dan REFACTOR membuat proses pengembangan menjadi lebih terstruktur dan terarah. Dengan menulis tes sebelum melakukan implementasi, saya terpaksa harus memikirkan dengan lebih rinci tentang perilaku yang diharapkan dari kode saya, serta merencanakan bagaimana kelas akan diimplementasikan sebelumnya. Ini akan sangat membantu dalam membuat implementasi yang lebih baik di kemudian hari. Namun, saya juga menyadari bahwa TDD membutuhkan waktu yang cukup banyak karena cukup sulit dilakukan dan terkadang membingungkan, terutama karena ada keharusan untuk membuat tes terlebih dahulu sebelum implementasi yang pasti.

* Unit test yang telah saya buat dalam tutorial ini sudah menggunakan prinsip F.I.R.S.T dengan baik. Pertama, tes tersebut cepat karena dapat dieksekusi dengan cepat, memberikan umpan balik secara instan tanpa mengganggu alur kerja saya. Selanjutnya, tes tersebut bersifat terisolasi dan independen karena fokus pada satu metode tertentu tanpa memengaruhi tes lainnya, juga mengimplementasikan penggunaan mock. Tes tersebut juga dapat diulang, memastikan hasil yang konsisten, sehingga dapat dianggap sebagai tes yang repeatable. Dengan menggunakan asertion untuk memeriksa output program, masalah dalam kode dapat dengan mudah teridentifikasi berdasarkan hasil asertion, menjadikan tes sebagai self-validating. Terakhir, tes tersebut komprehensif dan tepat waktu karena mencakup semua skenario mungkin termasuk kasus bahagia maupun kasus tidak bahagia.
</details>