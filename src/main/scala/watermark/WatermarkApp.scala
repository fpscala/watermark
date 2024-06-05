package watermark

import com.sksamuel.scrimage._
import com.sksamuel.scrimage.canvas.drawables.Text
import com.sksamuel.scrimage.color.RGBColor
import com.sksamuel.scrimage.implicits._
import com.sksamuel.scrimage.nio.JpegWriter

object WatermarkApp {
  def main(args: Array[String]): Unit = {
    // Load the product image
//    val imagePath = "/product.jpg"
    val imagePath = "/bobur go'ja.jpeg"
    val borderImagePath = "/sale2.png"
    val image = ImmutableImage.loader.fromResource(imagePath)
    val borderImage = ImmutableImage.loader.fromResource(borderImagePath)

    // Define the watermark text and its properties
    val watermarkText = "Bobur Go'ja"
    val discountText = "50%"
    val salePriceText = "8000"
    val uzsText = "UZS"
    val watermarkFont = FontUtils.createFont("Arial", image.width * 4 / 100)
    val discountFont = FontUtils.bold("Arial", image.width * 14 / 200)
    val salePriceFont = FontUtils.bold("Arial", image.width * 14 / 300)
    val uzsFont = FontUtils.bold("Arial", image.width * 7 / 300)
    val discountColor = new RGBColor(255, 0, 0) // White color with some transparency
    val watermarkColor = new RGBColor(255, 255, 255) // White color with some transparency

    // Calculate the position to place the watermark (bottom right corner)
    val resizedBorderImage = borderImage.scaleTo(image.width, image.height)
    val combinedImage = image.overlay(resizedBorderImage, 0, 0)
    val watermarkedImage = combinedImage.draw(
      new Text(
        watermarkText,
        image.width - (image.width * 7 / 9),
        image.height - (image.height * 1 / 20),
        g => {
          g.setColor(watermarkColor)
          g.setFont(watermarkFont)
        },
      ),
      new Text(
        salePriceText,
        image.width - (image.width * 39 / 40),
        image.height - (image.height * 1 / 10),
        g => {
          g.setColor(watermarkColor)
          g.setFont(salePriceFont)
        },
      ),
      new Text(
        uzsText,
        image.width - (image.width * 69 / 80),
        image.height - (image.height * 1 / 10),
        g => {
          g.setColor(watermarkColor)
          g.setFont(uzsFont)
        },
      ),
      new Text(
        discountText,
        image.width - (image.width * 3 / 16),
        image.height - (image.height * 69 / 80),
        g => {
          g.setColor(discountColor)
          g.setFont(discountFont)
        },
      )
    )

    // Save the watermarked image
    implicit val writer: JpegWriter = new JpegWriter()//.withCompression(100)
    val outputPath = "./watermarked-image.jpg"
    watermarkedImage.output(writer, new java.io.File(outputPath))

    println(s"Watermarked image saved at $outputPath")
  }
}
