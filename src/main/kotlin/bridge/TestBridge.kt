package bridge


fun main(){
    val aesImpl:IMessageEncrypt = DefaultMessageEncryptImpl(
         AESEncryptAlgorithm())
    val desImpl:IMessageEncrypt = DefaultMessageEncryptImpl(
         DESEncryptAlgorithm())
    val noImpl: IMessageEncrypt = DefaultMessageEncryptImpl(
        NoEncryptAlgorithm())
    try {
        val message = "{\"fullname\":\"Clemente Eduardo Vasquez Reyes\",\"age\":21}"
        val messageAES = aesImpl.encryptMessage(message, "HG58YZ3CR9123456")
        println("messageAES > $messageAES\n");
        val  messageDES = desImpl.encryptMessage(message, "XMzDdG4D03CKm2Ix")
        println("messageDES > $messageDES\n");
        val messageNO = noImpl.encryptMessage(message, "")
        println("messageNO > $messageNO\n");
    } catch (e: Exception) {
        e.printStackTrace()
    }
}
