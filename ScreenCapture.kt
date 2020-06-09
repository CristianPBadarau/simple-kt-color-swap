import java.awt.*
import java.awt.image.BufferedImage
import java.io.File
import java.io.IOException
import javax.imageio.ImageIO
import kotlin.random.Random

fun main(args : Array<String>){
    ScreenCapture().takeScreenShot()
}

class ScreenCapture {
    var robot = Robot()

    var redColorSearch: Int = 50
    var greenColorSearch: Int = 168
    var blueColorSearch: Int = 82

    var redColorSwitch: Int = 77
    var greenColorSwitch: Int = 0
    var blueColorSwitch: Int = 200


    fun takeScreenShot(){
        try {
            robot = Robot()
            val rectangle = Rectangle(Toolkit.getDefaultToolkit().getScreenSize())
            val bufferedImage = robot.createScreenCapture(rectangle)

            var i: Int = 0;
            var j: Int = 0;
            var newScreenShotBI = BufferedImage(bufferedImage.width,bufferedImage.height,bufferedImage.type)

            i.until(bufferedImage.width).forEach { i: Int ->
                j.until(bufferedImage.height).forEach{ j: Int ->
                    val pruebaRGB = Color(bufferedImage.getRGB(i,j))
                    val arrColores = IntArray(3) { pruebaRGB.red;pruebaRGB.green;pruebaRGB.blue }
                    if(pruebaRGB.red == redColorSearch && pruebaRGB.green == greenColorSearch && pruebaRGB.blue == blueColorSearch) {
//                        newScreenShotBI.setRGB(i,j,Color(redColorSwitch,greenColorSwitch,blueColorSwitch).rgb)
                        newScreenShotBI.setRGB(i,j,Color(Random.nextInt(150,200),Random.nextInt(0,100),Random.nextInt(200,230)).rgb)
                    }else{
//                        newScreenShotBI.setRGB(i,j,bufferedImage.getRGB(i,j))
                        newScreenShotBI.setRGB(i,j,bufferedImage.getRGB(i,j))
                    }
                }
            }

            // Original screenshot
            val file = File("screen-capture.png")
            val status = ImageIO.write(bufferedImage, "png", file)
            println(""+status + " File Path:- " + file.absolutePath)
            // Modified screenshot
            val newScreenshotFile = File("new-screen-capture.png")
            val status2 = ImageIO.write(newScreenShotBI, "png", newScreenshotFile)
            println("" + status2 + " File Path:- " + newScreenshotFile.absolutePath)
            
        } catch (ex: AWTException) {
            System.err.println(ex)
        } catch (ex: IOException) {
            System.err.println(ex)
        }

    }

}
