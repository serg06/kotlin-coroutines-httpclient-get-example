import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse.BodyHandlers
import kotlinx.coroutines.*

suspend fun downloadFile(path: String): String {
    return withContext(Dispatchers.IO) {
        val client = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_2)
            .build()
    
        val request = HttpRequest.newBuilder()
            .uri(URI.create(path))
            .build()
    
        val response = client.send(request, BodyHandlers.ofString())
    
        response.body()
    }
}

suspend fun main(args: Array<String>) {
    println("Downloading file...")
    val file = downloadFile("https://filesamples.com/samples/document/txt/sample1.txt")
    println(file)
    println("Done!")
}
