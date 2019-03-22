fun main(args: Array<String>) {
    println("Encrypt . . .")
    val encrypted = AES.encrypt("Hello World", "dojgofibjofbj")
    println(encrypted)
    println("Decrypt . . .")
    println(AES.decrypt(encrypted, "dojgofibjofbj"))
}