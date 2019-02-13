package app


import org.apache.spark.SparkConf
import org.apache.spark.sql.{DataFrame, SparkSession}
import utils.JDBC

/**
  * 实现报表生成
  */
object GenerateReport {



  def main(args: Array[String]): Unit = {
    // 模拟企业级编程 首先判断目录是否为空
    if(args.length!=2){
      println("目录不正确，退出程序")
      sys.exit()
    }

    // 创建一个集合存储输入输出目录
    val Array(inputPath,outputPath) = args

    val conf = new SparkConf()
      .setAppName(s"${this.getClass.getSimpleName}")
      .set("spark.serializer", "org.apache.spark.serializer.KryoSerializer")
    val spark = SparkSession
      .builder()
      .config(conf)
      .master("local[*]")
      .getOrCreate()

    val df = spark.read.parquet(inputPath)
    // 创建临时视图
    df.createOrReplaceTempView("log")

    // 统计各省市数据量分布情况
//    calculateProvinceCityCount(spark,outputPath)

    // 地域分布
    calculateLocationCount(spark)
    spark.stop()

  }

  /**
    * 地域分布计算
    * @param spark
    */
  def calculateLocationCount(spark: SparkSession) = {
    // 计算地域分布
    val requestsql =  "select provincename,cityname," +
      "sum(case when processnode >= 1 and requestmode = 1 then 1 else 0 end) as original_request_count, " +
      "sum(case when processnode >= 2 and requestmode = 1 then 1 else 0 end) as effective_request_count, " +
      "sum(case when processnode = 3 and requestmode = 1 then 1 else 0 end) as ad_request_count, " +
      "sum(case when iseffective = 1 and isbilling = 1 and isbid = 1 then 1 else 0 end) as join_biding_count, " +
      "sum(case when iseffective = 1 and isbilling = 1 and iswin = 1 and adorderid != 0 then 1 else 0 end) as biding_win_count, " +
      "round(cast(sum(case when iseffective = 1 and isbilling = 1 and iswin = 1 and adorderid != 0 then 1 else 0 end) as double)" +
      "/cast(sum(case when iseffective = 1 and isbilling = 1 and isbid = 1 then 1 else 0 end) as double ),2) as biding_win_rate, " +
      "sum(case when processnode = 2 and iseffective = 1 then 1 else 0 end) as show_count, " +
      "sum(case when processnode = 3 and iseffective = 1 then 1 else 0 end) as click_count, " +
      "round(cast(sum(case when processnode = 3 and iseffective = 1 then 1 else 0 end) as double)" +
      "/cast(sum(case when processnode = 2 and iseffective = 1 then 1 else 0 end) as double),2) as click_rate, " +
      "round(sum(case when iseffective = 1 and isbilling = 1 and iswin = 1 then winprice else 0 end)/1000,2) as DSPwinprice, " +
      "round(sum(case when iseffective = 1 and isbilling = 1 and iswin = 1 then adpayment else 0 end)/1000,2) as DSPadpayment " +
      "from log " +
      "group by provincename,cityname"

    val requestDF = spark.sql(requestsql)
//    requestDF.show(300)

    // 将数据写到mysql中
    // 创建properties存储数据库相关属性
    JDBC.createTable("requestCount",requestDF)
  }

  /**
    * 计算各省市数据量分布情况
    * @param spark
    */
  def calculateProvinceCityCount(spark: SparkSession,outputPath:String): Unit = {
    val sql = "select count(1) as ct," +
      "provincename,cityname " +
      "from log " +
      "group by provincename,cityname"

    val provinceCityCountDF: DataFrame = spark.sql(sql)

    // 将数据写入到mysql中
    JDBC.createTable("provinceCityCount",provinceCityCountDF)
//    provinceCityCountDF.write.json(outputPath)


  }


}
