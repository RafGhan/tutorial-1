# Tutorial 1

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