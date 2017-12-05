package com.telegraph.stub.Recommendation

import com.github.tomakehurst.wiremock.client.WireMock._
import org.scalatest.{Matchers, WordSpecLike}
import uk.co.telegraph.qe.SmartStub

import scala.io.Source

import com.telegraph.stub.Recommendation.utils._

/**
  * Created by J James on 01/12/2017.
  * Draft Recomendations stub encapsulating contract
  */
object MyStub extends SmartStub {


  override def setUpMocks(cannedResponsesPath: String): Unit = {

    //Recomed Article OK
    wireMockServer.stubFor(post(urlMatching(".*/recommend-articles/by-article"))
      .willReturn(
        aResponse()
          .withHeader("Content-Type", "application/json")
          .withHeader("access-control-allow-origin", "*")
          .withBody(Source.fromFile(cannedResponsesPath + "/RecommendByArticleOK.json").mkString
            .replace("&apos;", " ").replace("&quot;", "'"))
          .withStatus(200)))


    //Recomend Article Error
    wireMockServer.stubFor(post(urlMatching(".*/recommend-articles/by-article"))
      .withRequestBody(equalToJson("{\"sort\": \"XXXXX\"}", true, true))
      .willReturn(
        aResponse()
          .withHeader("Content-Type", "application/json")
          .withHeader("access-control-allow-origin", "*")
          .withBody(Source.fromFile(cannedResponsesPath + "/RecommendByArticleError.json").mkString
            .replace("&apos;", " ").replace("&quot;", "'"))
          .withStatus(400)))


  }

  //driver class
    def main(args : Array[String]) {

      // port, canned file directory, swagger file, state model file, opening state
      MyStub.configureStub(args(0), args(1), args(2), args(3), "any")
      MyStub.start
    }
}