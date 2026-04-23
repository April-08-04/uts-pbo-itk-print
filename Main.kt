class Pelanggan(private var nama: String, private var saldo: Int) {

    fun getNama(): String = nama

    fun getSaldo(): Int = saldo

    fun topUpSaldo(jumlah: Int) {
        saldo += jumlah
    }

    fun kurangiSaldo(jumlah: Int): Boolean {
        return if (saldo >= jumlah) {
            saldo -= jumlah
            true
        } else {
            false
        }
    }
}

class Dokumen(private var namaDokumen: String, private var jumlahHalaman: Int) {

    fun getNamaDokumen(): String = namaDokumen

    fun getJumlahHalaman(): Int = jumlahHalaman
}

class MesinPrint(
    private var namaMesin: String,
    private var tinta: Int,
    private var kertas: Int,
    private var hargaPerLembar: Int
) {

    fun prosesCetak(pelanggan: Pelanggan, dokumen: Dokumen) {
        val totalBiaya = dokumen.getJumlahHalaman() * hargaPerLembar

        if (kertas < dokumen.getJumlahHalaman()) {
            println("Gagal: Kertas tidak cukup")
            return
        }

        if (tinta < dokumen.getJumlahHalaman()) {
            println("Gagal: Tinta tidak cukup")
            return
        }

        if (!pelanggan.kurangiSaldo(totalBiaya)) {
            println("Gagal: Saldo tidak cukup")
            return
        }

        kertas -= dokumen.getJumlahHalaman()
        tinta -= dokumen.getJumlahHalaman()

        println("Cetak berhasil untuk ${pelanggan.getNama()}")
    }
}

fun main() {
    val pelanggan = Pelanggan("Fadilah", 10000)
    val dokumen = Dokumen("Laporan", 10)
    val mesin = MesinPrint("Printer ITK", 50, 50, 500)

    mesin.prosesCetak(pelanggan, dokumen)
}
