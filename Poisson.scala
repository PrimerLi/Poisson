import scala.util.control.Breaks._
import java.io.File
import java.io.PrintWriter
import scala.collection.mutable.HashMap
import scala.collection.mutable.ArrayBuffer

class Poisson{

}

object Poisson
{
    def uniform(): Double = 
    {
        return scala.util.Random.nextDouble
    }
    def uniform(lower: Double, upper: Double): Double = 
    {
        return lower + (upper - lower)*uniform()
    }
    def poissonNumber(l: Double): Int = 
    {
        val random = scala.util.Random
        var k: Int = 0
        var p: Double = 1.0
        val L: Double = scala.math.exp(-l)
        val k_max: Int = 1000
        breakable
        {
            while(true)
            {
                k = k + 1
                p = p*uniform()
                if (p < L) break
                if (k > k_max) break
            }
        }
        return k-1
    }

    def printMap(map: HashMap[Int, Int], output: String): Unit = 
    {
        val writer = new PrintWriter(new File(output))
        for (key <- map.keys)
        {
            writer.write(key.toString + " -> " + map(key).toString + "\n")
        }
        writer.close()
    }

    def printFile(x: Array[Int], y: Array[Double], output: String): Unit = 
    {
        val writer = new PrintWriter(new File(output))
        assert(x.length == y.length)
        for (i <- 0 until x.length)
        {
            writer.write(x(i).toString + "  " + y(i).toString + "\n")
        }
        writer.close()
    }


    def testPoisson(l: Double): Unit = 
    {
        val numbers = new scala.collection.mutable.ArrayBuffer[Int]()
        val N = 10000
        for (i <- 0 until N)
        {
            numbers.append(poissonNumber(l))
        }
        val stat = new HashMap[Int, Int]()
        numbers.foreach(n => stat(n) = 1 + stat.getOrElse(n, 0))
        printMap(stat, "Poisson_stat.txt")
        val keys:Array[Int] = stat.keys.toArray
        java.util.Arrays.sort(keys)
        var frequency = new HashMap[Int, Double]()
        for (key <- keys)
        {
            frequency += key -> stat(key).toDouble/N.toDouble
        }
        val outputFileName = "Poisson_distribution_" + l.toString + ".txt"
        val writer = new PrintWriter(new File(outputFileName))
        for (key <- keys)
        {
            writer.write(key.toString + "  " + frequency(key).toString + "\n")
        }
        writer.close()
    }
}
