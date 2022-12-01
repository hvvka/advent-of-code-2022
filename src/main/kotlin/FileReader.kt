class FileReader {

    fun readFile(filename: String): String = this::class.java.getResource(filename).readText()
}