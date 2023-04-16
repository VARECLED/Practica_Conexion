package bridge

import java.util.Base64
import javax.crypto.Cipher
import javax.crypto.SecretKeyFactory
import javax.crypto.spec.DESKeySpec
import javax.crypto.spec.SecretKeySpec

class DESEncryptAlgorithm: IEncryptAlgorithm {
    override fun encrypt(message: String, password: String): String {
        val dks = DESKeySpec(message.toByteArray())
        val sfk= SecretKeyFactory.getInstance("DES")
        val desKey = sfk.generateSecret(dks)
        val desCypher = Cipher.getInstance("DES/ECB/PKCS5Padding")
        desCypher.init(Cipher.ENCRYPT_MODE, desKey)

        return Base64.getEncoder().encodeToString(message.toByteArray())

    }
}