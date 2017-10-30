object test
{
    def main(args: Array[String]): Unit = 
    {
        if (args.length != 1)
        {
            println("l = args(0). ")
            System.exit(1)
        }
        val l = args(0).toDouble
        var poissonParameter = 1.0
        while(poissonParameter <= l)
        {
            println(poissonParameter)
            Poisson.testPoisson(poissonParameter)
            poissonParameter += 1.0
        }
    }
}
