
import java.security.MessageDigest
import java.util.*
import javax.crypto.Cipher
import javax.crypto.SecretKeyFactory
import javax.crypto.spec.PBEKeySpec
import javax.crypto.spec.SecretKeySpec

object AES {

    val cipher = Cipher.getInstance("AES/ECB/PKCS5Padding")

    fun setSecretKey(value: String): SecretKeySpec {

        val factory = SecretKeyFactory.getInstance("PBKDF2withHmacSHA1")

        val spec = PBEKeySpec(value.toCharArray(), "asfasasdadvarr".toByteArray(), 65536, 256)

        val key = factory.generateSecret(spec)

        return SecretKeySpec(key.encoded, "AES")
    }

    fun encrypt(value: String, key: String) : String{

        val secretKeySpec = setSecretKey(key)

        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec)

        return String(Base64.getEncoder().encode(cipher.doFinal(value.toByteArray(Charsets.UTF_8))))
    }

    fun decrypt(value: String, key: String): String {
        val secretKeySpec = setSecretKey(key)

        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec)

        return String(cipher.doFinal(Base64.getDecoder().decode(value)))
    }
}