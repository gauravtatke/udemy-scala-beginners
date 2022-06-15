package lectures.part1basics

object DefaultArgs extends App {

    def tailRecFactorial(n: Int, acc: Int = 1): Int = {
        if (n <= 1) acc
        else tailRecFactorial(n-1, n*acc)
    }

    val fact10 = tailRecFactorial(10)

    def savePicture(format: String = "jpg", width: Int, height: Int): Unit = println("saving picture")
    // savePicture(800, 600) // cannot determine first param correctly. errors out at compiler
    // we have to pass leading argument always in above case

    def savePicture2(format: String = "jpg", width: Int = 800, height: Int = 600): Unit = println("saving picture")
    savePicture2(width = 1920, format = "bmp", height = 1080) // named argument can be in any order

}
