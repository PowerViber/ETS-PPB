# ResepKita - Alur Pengerjaan

Konten ini dibuat untuk mengganti bagian **ALUR PENGERJAAN** pada page 7 dan 8 dari contoh presentasi TaskManager, tetapi disesuaikan dengan struktur aplikasi **ResepKita** di folder `App`.

## Page 7 - ALUR PENGERJAAN

### 1. SETUP PROJECT
Membuat project Android dengan:

- Kotlin
- Jetpack Compose
- Material 3
- Navigation Compose
- Coil untuk menampilkan gambar resep
- Activity Result API untuk membuka kamera
- Local assets untuk gambar makanan

### 2. MEMBUAT STRUKTUR APLIKASI
Menyusun struktur aplikasi dengan:

- **Entry point & state:** `MainActivity.kt`
- **Data layer:** `RecipeRepository.kt`, `Recipe`, `CookingPhoto.kt`
- **Reusable component:** `ResepKitaTopBar.kt`
- **UI layer:** `HomeScreen.kt`, `SearchScreen.kt`, `DetailScreen.kt`, `FavoritesScreen.kt`, `ProfileScreen.kt`
- **Theme:** `Color.kt`, `Theme.kt`, `Type.kt`

### 3. MERANCANG MODEL DATA
Membuat data class untuk resep yang berisi:

- `id`
- `name`
- `imageUrl`
- `description`
- `ingredients`
- `steps`
- `time`
- `difficulty`
- `rating`
- `region`
- `category`
- `servings`
- `note`
- `isFavorite`

Membuat model foto masakan:

- `CookingPhoto`
- Menyimpan `id`, `recipeId`, dan `Bitmap` hasil kamera

### 4. MEMBUAT KOMPONEN UI
Membuat komponen UI sesuai kebutuhan aplikasi resep:

- `ResepKitaTopBar`: top bar reusable untuk semua halaman
- `GlassBottomNavigation`: navigasi bawah Home, Search, Favorites, Profile
- `FeaturedRecipeCard`: kartu resep utama di Home
- `RecipeListCard`: daftar resep di Home
- `BriefRecipeDetail`: ringkasan resep sebelum masuk detail
- `SearchResultItem`: hasil pencarian resep
- `IngredientItem`: checklist bahan di halaman detail
- `InstructionStep`: langkah memasak
- `FavoriteRecipeCard`: kartu resep favorit
- `CookingStats` dan `TasteProfile`: ringkasan aktivitas memasak di Profile

## Page 8 - ALUR PENGERJAAN

### 5. IMPLEMENTASI DATA & STATE
Mengimplementasikan data dan state aplikasi dengan:

- `RecipeRepository` sebagai sumber data resep lokal
- List resep statis untuk kebutuhan front-end assignment
- Gambar resep dari `assets/photos`
- Fungsi `findById()` untuk mengambil resep berdasarkan id
- Fungsi `search()` untuk mencari resep berdasarkan nama, bahan, daerah, dan kategori
- `favoriteRecipeIds` untuk menyimpan resep favorit
- `plannedRecipeIds` untuk menyimpan rencana masak
- `cookingPhotos` untuk menyimpan foto hasil masakan
- `remember` dan `mutableStateListOf` agar UI otomatis berubah ketika state berubah

### 6. FITUR UTAMA
Mengimplementasikan fitur utama ResepKita:

- Menampilkan daftar resep dengan `LazyColumn` dan `LazyRow`
- Menampilkan detail resep berdasarkan `recipeId`
- Menampilkan gambar, deskripsi, bahan, dan langkah memasak
- Pencarian resep berdasarkan nama, bahan, daerah, dan kategori
- Toggle favorit menggunakan ikon hati
- Halaman Favorites otomatis menampilkan resep yang difavoritkan
- Kategori Favorites dibuat otomatis dari kategori resep favorit
- Rencana masak melalui tombol `MULAI MEMASAK`
- Checklist bahan pada halaman detail
- Tombol `SELESAI MEMASAK` membuka kamera
- Foto hasil masak masuk ke profile dan tidak boleh duplikat
- Setelah upload foto, rencana masak otomatis dihapus
- Profile menampilkan jumlah rencana, favorit, dan masakan yang sudah difoto
