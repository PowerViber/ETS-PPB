package com.example.resepkita.data

data class Recipe(
    val id: String,
    val name: String,
    val imageUrl: String,
    val description: String,
    val ingredients: List<String>,
    val steps: List<String>,
    val time: String,
    val difficulty: String,
    val rating: String,
    val region: String,
    val category: String,
    val servings: String,
    val note: String,
    val isFavorite: Boolean = false
)

object RecipeRepository {
    val recipes = listOf(
        Recipe(
            id = "rendang-minang",
            name = "Rendang Minang Asli",
            imageUrl = "file:///android_asset/photos/rendang-minang.png",
            description = "Warisan leluhur Minangkabau dengan daging sapi, santan, dan bumbu rempah yang dimasak perlahan hingga pekat.",
            ingredients = listOf(
                "1 kg daging sapi, potong kotak",
                "1 liter santan kental",
                "5 siung bawang putih",
                "10 butir bawang merah",
                "8 cabai merah keriting",
                "3 batang serai, memarkan",
                "4 lembar daun jeruk",
                "Lengkuas, jahe, garam, dan gula secukupnya"
            ),
            steps = listOf(
                "Haluskan bawang, cabai, jahe, lengkuas, garam, dan gula.",
                "Masak santan bersama bumbu halus, serai, dan daun jeruk sampai harum.",
                "Masukkan daging, lalu kecilkan api.",
                "Aduk sesekali sampai santan menyusut dan bumbu meresap.",
                "Masak hingga rendang berwarna gelap dan berminyak."
            ),
            time = "120 Menit",
            difficulty = "Mahir",
            rating = "4.9",
            region = "Sumatera Barat",
            category = "Tradisional",
            servings = "6 Porsi",
            note = "Disimpan untuk akhir pekan",
            isFavorite = true
        ),
        Recipe(
            id = "gado-gado-siram",
            name = "Gado-Gado Siram",
            imageUrl = "file:///android_asset/photos/gado-gado.jpg",
            description = "Sayuran rebus, tahu, tempe, dan telur dengan siraman saus kacang gurih yang cocok untuk makan siang cepat.",
            ingredients = listOf(
                "100 g kacang tanah goreng",
                "2 siung bawang putih",
                "3 cabai merah",
                "Tahu dan tempe goreng",
                "Kacang panjang, kol, tauge, dan kentang rebus",
                "Telur rebus",
                "Gula merah, garam, air asam, dan kecap manis"
            ),
            steps = listOf(
                "Haluskan kacang tanah, bawang putih, cabai, gula merah, dan garam.",
                "Tambahkan air asam dan sedikit air hangat sampai saus cukup kental.",
                "Tata sayuran, tahu, tempe, kentang, dan telur di piring.",
                "Siram dengan saus kacang.",
                "Tambahkan kecap dan kerupuk sesuai selera."
            ),
            time = "25 Menit",
            difficulty = "Pemula",
            rating = "4.8",
            region = "Betawi",
            category = "Vegetarian",
            servings = "3 Porsi",
            note = "Cepat untuk makan siang",
            isFavorite = true
        ),
        Recipe(
            id = "sate-madura",
            name = "Sate Madura",
            imageUrl = "https://lh3.googleusercontent.com/aida-public/AB6AXuDtPQCbh1VB-bvpoDjBt36tGDo23hU7poaqzaHNX-VmgGYca1sSij6-gp_9_hswYQfrKrbsJVyh8jabMZu_oAVfjVKKpaWCx2UDljGL2hfyEgusytZaIzUgKDDY51r6JrB0Sa_jqLCOGgcZYdmEw_qeaPo87O_DTuQkqfkaQN5yPC5PjdCVpS5r7mtqV2Esyxif6hutnRpxfCr6llV0uqUetiM0ws6fyF_l535pbpu1yVAyh-kKCFxwL1gp9iwkMYmVT8HOs1RWMFVC",
            description = "Potongan ayam berbumbu yang dibakar harum lalu disajikan dengan bumbu kacang, kecap, dan irisan bawang.",
            ingredients = listOf(
                "500 g daging ayam, potong dadu",
                "Tusuk sate secukupnya",
                "150 g kacang tanah goreng",
                "3 siung bawang putih",
                "5 butir bawang merah",
                "Cabai, kecap manis, garam, dan jeruk limau"
            ),
            steps = listOf(
                "Tusuk potongan ayam hingga rapi.",
                "Haluskan kacang, bawang, cabai, dan garam untuk bumbu.",
                "Lumuri sate dengan sedikit bumbu dan kecap.",
                "Bakar sate sambil dibolak-balik sampai matang.",
                "Sajikan dengan bumbu kacang, kecap, dan jeruk limau."
            ),
            time = "45 Menit",
            difficulty = "Menengah",
            rating = "4.7",
            region = "Jawa Timur",
            category = "Populer",
            servings = "4 Porsi",
            note = "Bumbu kacang favorit",
            isFavorite = true
        ),
        Recipe(
            id = "nasi-goreng-kampung",
            name = "Nasi Goreng Kampung",
            imageUrl = "file:///android_asset/photos/nasi-goreng.jpg",
            description = "Nasi goreng rumahan dengan bawang, cabai, telur, dan kecap yang praktis untuk sarapan atau makan malam.",
            ingredients = listOf(
                "2 piring nasi putih dingin",
                "2 butir telur",
                "4 siung bawang merah",
                "2 siung bawang putih",
                "3 cabai rawit",
                "Kecap manis, garam, merica, dan daun bawang"
            ),
            steps = listOf(
                "Haluskan bawang merah, bawang putih, cabai, garam, dan merica.",
                "Tumis bumbu sampai harum.",
                "Masukkan telur lalu orak-arik.",
                "Tambahkan nasi dan kecap manis.",
                "Aduk rata sampai nasi panas dan bumbu meresap."
            ),
            time = "20 Menit",
            difficulty = "Pemula",
            rating = "4.6",
            region = "Rumahan",
            category = "Modern",
            servings = "2 Porsi",
            note = "Andalan saat lapar malam"
        ),
        Recipe(
            id = "ayam-betutu",
            name = "Ayam Betutu",
            imageUrl = "file:///android_asset/photos/ayam-betutu.jpg",
            description = "Ayam khas Bali dengan bumbu genep yang kuat, pedas, dan aromatik.",
            ingredients = listOf(
                "1 ekor ayam",
                "8 bawang merah dan 5 bawang putih",
                "Cabai merah dan cabai rawit",
                "Kunyit, jahe, kencur, lengkuas, dan kemiri",
                "Serai, daun salam, daun jeruk, garam, dan minyak"
            ),
            steps = listOf(
                "Haluskan seluruh bumbu genep.",
                "Tumis bumbu sampai matang dan harum.",
                "Lumuri ayam dengan bumbu hingga merata.",
                "Kukus atau panggang ayam sampai empuk.",
                "Sajikan dengan nasi hangat dan sambal matah."
            ),
            time = "90 Menit",
            difficulty = "Menengah",
            rating = "4.8",
            region = "Bali",
            category = "Tradisional",
            servings = "5 Porsi",
            note = "Pedas berempah"
        )
    )

    fun findById(id: String?): Recipe = recipes.firstOrNull { it.id == id } ?: recipes.first()

    fun search(query: String): List<Recipe> {
        val keyword = query.trim().lowercase()
        if (keyword.isEmpty()) return recipes

        return recipes.filter { recipe ->
            recipe.name.lowercase().contains(keyword) ||
                recipe.region.lowercase().contains(keyword) ||
                recipe.category.lowercase().contains(keyword) ||
                recipe.ingredients.any { it.lowercase().contains(keyword) }
        }
    }
}
