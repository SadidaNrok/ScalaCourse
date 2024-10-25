package lectures.week1basics

import java.text.SimpleDateFormat
import java.time.LocalDateTime

import com.fasterxml.jackson.databind.json.JsonMapper
import com.fasterxml.jackson.module.scala.DefaultScalaModule
import java.util.TimeZone

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule

object Expressions extends App {
  val someVal: Unit = print("I just want to print")

  var date = LocalDateTime.now()
  println(date)
  var json = """{"members":[
   {"memid":0,"surname":"GUEST","firstname":"GUEST","address":"GUEST","zipcode":0,"telephone":"(000) 000-0000","recommendedby":null,"joindate":"2012-07-01T00:00:00Z"}
]}"""
  /*var json = """{"members":[
   {"memid":0,"surname":"GUEST","firstname":"GUEST","address":"GUEST","zipcode":0,"telephone":"(000) 000-0000","recommendedby":null,"joindate":"01.07.2012 00:00:00"},
   {"memid":1,"surname":"Smith","firstname":"Darren","address":"8 Bloomsbury Close, Boston","zipcode":4321,"telephone":"555-555-5555","recommendedby":null,"joindate":"02.07.2012 12:02:05"},
   {"memid":2,"surname":"Smith","firstname":"Tracy","address":"8 Bloomsbury Close, New York","zipcode":4321,"telephone":"555-555-5555","recommendedby":null,"joindate":"02.07.2012 12:08:23"},
   {"memid":3,"surname":"Rownam","firstname":"Tim","address":"23 Highway Way, Boston","zipcode":23423,"telephone":"(844) 693-0723","recommendedby":null,"joindate":"03.07.2012 09:32:15"},
   {"memid":4,"surname":"Joplette","firstname":"Janice","address":"20 Crossing Road, New York","zipcode":234,"telephone":"(833) 942-4710","recommendedby":1,"joindate":"03.07.2012 10:25:05"}
]}"""*/

  val mapper = {
    val df = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss")
    //df.setTimeZone(TimeZone.getTimeZone("UTC"))
    val m = JsonMapper.builder()
      .addModule(DefaultScalaModule)
    //m.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
      .addModule(new JavaTimeModule())
    //m.defaultDateFormat(df)
    m.build()
  }

  //var members : Members
  var members = mapper.readValue(json, classOf[Members])
}

class Member {
  var memid : Number = 0
  var surname : String = "GUEST"
  var firstname : String = "GUEST"
  var address : String = "GUEST"
  var zipcode : Number = 0
  var telephone : String = "(000) 000-0000"
  var recommendedby : Option[Number] = _

  //var joindate : String = "01.07.2012 00:00:00";// LocalDateTime = LocalDateTime.now()//.parse("01.07.2012 00:00:00")
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy HH:mm:ss")
  var joindate : java.time.LocalDateTime = _//LocalDateTime.parse("01.07.2012 00:00:00")
}

class Members {
  val members : Array[Member] = Array.empty
}