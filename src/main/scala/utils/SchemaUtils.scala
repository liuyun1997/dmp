package utils

import constant.Constants
import org.apache.spark.sql.types._

object SchemaUtils {
  //定义日志的元数据信息
  val schema = StructType(
    Array(
      StructField("sessionid",StringType,true),
      StructField("advertisersid",IntegerType,true),
      StructField("adorderid",IntegerType,true),
      StructField("adcreativeid",IntegerType,true),
      StructField("adplatformproviderid",IntegerType,true),
      StructField("sdkversion",StringType,true),
      StructField("adplatformkey",StringType,true),
      StructField("putinmodeltype",IntegerType,true),
      StructField("requestmode",IntegerType,true),
      StructField("adprice",DoubleType,true),
      StructField("adppprice",DoubleType,true),
      StructField("requestdate",StringType,true),
      StructField("ip",StringType,true),
      StructField("appid",StringType,true),
      StructField("appname",StringType,true),
      StructField("uuid",StringType,true),
      StructField("device",StringType,true),
      StructField("client",IntegerType,true),
      StructField("osversion",StringType,true),
      StructField("density",StringType,true),
      StructField("pw",IntegerType,true),
      StructField("ph",IntegerType,true),
      StructField("long",StringType,true),
      StructField("lat",StringType,true),
      StructField("provincename",StringType,true),
      StructField("cityname",StringType,true),
      StructField("ispid",IntegerType,true),
      StructField("ispname",StringType,true),
      StructField("networkmannerid",IntegerType,true),
      StructField("networkmannername",StringType,true),
      StructField("iseffective",IntegerType,true),
      StructField("isbilling",IntegerType,true),
      StructField("adspacetype",IntegerType,true),
      StructField("adspacetypename",StringType,true),
      StructField("devicetype",IntegerType,true),
      StructField("processnode",IntegerType,true),
      StructField("apptype",IntegerType,true),
      StructField("district",StringType,true),
      StructField("paymode",IntegerType,true),
      StructField("isbid",IntegerType,true),
      StructField("bidprice",DoubleType,true),
      StructField("winprice",DoubleType,true),
      StructField("iswin",IntegerType,true),
      StructField("cur",StringType,true),
      StructField("rate",DoubleType,true),
      StructField("cnywinprice",DoubleType,true),
      StructField("imei",StringType,true),
      StructField("mac",StringType,true),
      StructField("idfa",StringType,true),
      StructField("openudid",StringType,true),
      StructField("androidid",StringType,true),
      StructField("rtbprovince",StringType,true),
      StructField("rtbcity",StringType,true),
      StructField("rtbdistrict",StringType,true),
      StructField("rtbstreet",StringType,true),
      StructField("storeurl",StringType,true),
      StructField("realip",StringType,true),
      StructField("isqualityapp",IntegerType,true),
      StructField("bidfloor",DoubleType,true),
      StructField("aw",IntegerType,true),
      StructField("ah",IntegerType,true),
      StructField("imeimd5",StringType,true),
      StructField("macmd5",StringType,true),
      StructField("idfamd5",StringType,true),
      StructField("openudidmd5",StringType,true),
      StructField("androididmd5",StringType,true),
      StructField("imeisha1",StringType,true),
      StructField("macsha1",StringType,true),
      StructField("idfasha1",StringType,true),
      StructField("openudidsha1",StringType,true),
      StructField("androididsha1",StringType,true),
      StructField("uuidunknow",StringType,true),
      StructField("userid",StringType,true),
      StructField("iptype",IntegerType,true),
      StructField("initbidprice",DoubleType,true),
      StructField("adpayment",DoubleType,true),
      StructField("agentrate",DoubleType,true),
      StructField("lomarkrate",DoubleType,true),
      StructField("adxrate",DoubleType,true),
      StructField("title",StringType,true),
      StructField("keywords",StringType,true),
      StructField("tagid",StringType,true),
      StructField("callbackdate",StringType,true),
      StructField("channelid",StringType,true),
      StructField("mediatype",IntegerType,true)
    )
  )

  // 统计各省市数据量分布情况的schema信息
  val provinceCitySchema = StructType(
    Array(
      StructField(Constants.PROVINCENAME,StringType,true),
      StructField(Constants.CITYNAME,StringType,true),
      StructField(Constants.ORIGINAL_REQUEST_COUNT,IntegerType,true),
      StructField(Constants.EFFECTIVE_REQUEST_COUNT,IntegerType,true),
      StructField(Constants.AD_REQUEST_COUNT,IntegerType,true),
      StructField(Constants.JOIN_BIDING_COUNT,IntegerType,true),
      StructField(Constants.BIDING_WIN_COUNT,IntegerType,true),
      StructField(Constants.BIDING_WIN_RATE,DoubleType,true),
      StructField(Constants.SHOW_COUNT,IntegerType,true),
      StructField(Constants.CLICK_COUNT,IntegerType,true),
      StructField(Constants.CLICK_RATE,DoubleType,true),
      StructField(Constants.DSPWINPRICE,DoubleType,true),
      StructField(Constants.DSPADPAYMENT,DoubleType,true)
    )
  )

  // 统计各app数据量分布的schema信息
  val appnameSchame = StructType(
    Array(
      StructField(Constants.APPNAME,StringType,true),
      StructField(Constants.ORIGINAL_REQUEST_COUNT,IntegerType,true),
      StructField(Constants.EFFECTIVE_REQUEST_COUNT,IntegerType,true),
      StructField(Constants.AD_REQUEST_COUNT,IntegerType,true),
      StructField(Constants.JOIN_BIDING_COUNT,IntegerType,true),
      StructField(Constants.BIDING_WIN_COUNT,IntegerType,true),
      StructField(Constants.BIDING_WIN_RATE,DoubleType,true),
      StructField(Constants.SHOW_COUNT,IntegerType,true),
      StructField(Constants.CLICK_COUNT,IntegerType,true),
      StructField(Constants.CLICK_RATE,DoubleType,true),
      StructField(Constants.DSPWINPRICE,DoubleType,true),
      StructField(Constants.DSPADPAYMENT,DoubleType,true)
    )
  )
}
