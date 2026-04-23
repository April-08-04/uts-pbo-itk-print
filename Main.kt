class Pelanggan(
    private val nama: String,
    saldoAwal: Int
) {
    private var saldo: Int = saldoAwal

    fun getNama(): String {
        return nama
    }

    fun getSaldo(): Int {
        return saldo
    }

    fun topUpSaldo(jumlah: Int) {
        if (jumlah > 0) {
            saldo += jumlah
            println("Top up berhasil. Saldo ${nama} sekarang: Rp$saldo")
        } else {
            println("Top up gagal. Jumlah top up harus lebih dari 0.")
        }
    }

    fun kurangiSaldo(jumlah: Int): Boolean {
        return if (jumlah > 0 && saldo >= jumlah) {
            saldo -= jumlah
            true
        } else {
            false
        }
    }
}

class Dokumen(
    private val namaDokumen: String,
    private val jumlahHalaman: Int
) {
    fun getNamaDokumen(): String {
        return namaDokumen
    }

    fun getJumlahHalaman(): Int {
        return jumlahHalaman
    }
}

class MesinPrint(
    private val namaMesin: String,
    tintaAwal: Int,
    kertasAwal: Int,
    private val hargaPerLembar: Int
) {
    private var tinta: Int = tintaAwal
    private var kertas: Int = kertasAwal

    fun getInfoMesin(): String {
        return "Mesin: $namaMesin | Tinta: $tinta | Kertas: $kertas | Harga/Lembar: Rp$hargaPerLembar"
    }

    fun isiTinta(jumlah: Int) {
        if (jumlah > 0) {
            tinta += jumlah
            println("Tinta berhasil ditambah $jumlah. Total tinta: $tinta")
        } else {
            println("Isi tinta gagal. Jumlah harus lebih dari 0.")
        }
    }

    fun isiKertas(jumlah: Int) {
        if (jumlah > 0) {
            kertas += jumlah
            println("Kertas berhasil ditambah $jumlah. Total kertas: $kertas")
        } else {
            println("Isi kertas gagal. Jumlah harus lebih dari 0.")
        }
    }

    fun prosesCetak(pelanggan: Pelanggan, dokumen: Dokumen) {
        val halaman = dokumen.getJumlahHalaman()
        val totalBiaya = halaman * hargaPerLembar

        println("\n=== PROSES CETAK ===")
        println("Mesin      : $namaMesin")
        println("Pelanggan  : ${pelanggan.getNama()}")
        println("Dokumen    : ${dokumen.getNamaDokumen()}")
        println("Halaman    : $halaman")
        println("Biaya      : Rp$totalBiaya")

        if (halaman <= 0) {
            println("Cetak gagal: jumlah halaman dokumen tidak valid.")
            return
        }

        if (tinta <= 0) {
            println("Cetak gagal: tinta mesin habis.")
            return
        }

        if (kertas <= 0) {
            println("Cetak gagal: kertas mesin habis.")
            return
        }

        if (kertas < halaman) {
            println("Cetak gagal: kertas tidak mencukupi untuk mencetak $halaman halaman.")
            return
        }

        if (tinta < halaman) {
            println("Cetak gagal: tinta tidak mencukupi untuk mencetak $halaman halaman.")
            return
        }

        if (!pelanggan.kurangiSaldo(totalBiaya)) {
            println("Cetak gagal: saldo pelanggan tidak mencukupi.")
            return
        }

        kertas -= halaman
        tinta -= halaman

        println("Cetak berhasil.")
        println("Saldo pelanggan tersisa: Rp${pelanggan.getSaldo()}")
        println("Sisa kertas mesin: $kertas")
        println("Sisa tinta mesin : $tinta")
    }
}

fun main() {
    println("=== SISTEM ITK PRINT BERJALAN ===")

    // Simulasi gagal: saldo tidak cukup
    val pelangganGagal = Pelanggan("Fadilah", 1000)
    val dokumenGagal = Dokumen("Laporan_Gagal.pdf", 10)
    val mesinGagal = MesinPrint("Printer ITK-01", tintaAwal = 50, kertasAwal = 50, hargaPerLembar = 500)

    println("\n=== SIMULASI GAGAL: SALDO TIDAK CUKUP ===")
    mesinGagal.prosesCetak(pelangganGagal, dokumenGagal)

    // Simulasi sukses
    val pelangganSukses = Pelanggan("Fadilah", 10000)
    val dokumenSukses = Dokumen("Laporan_Sukses.pdf", 10)
    val mesinSukses = MesinPrint("Printer ITK-01", tintaAwal = 50, kertasAwal = 50, hargaPerLembar = 500)

    println("\n=== SIMULASI SUKSES ===")
    mesinSukses.prosesCetak(pelangganSukses, dokumenSukses)

    println("\n=== INFO AKHIR MESIN ===")
    println(mesinSukses.getInfoMesin())
}
