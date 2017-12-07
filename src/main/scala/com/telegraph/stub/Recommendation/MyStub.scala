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

    //Recommed Article OK
    wireMockServer.stubFor(post(urlMatching(".*/recommend-articles/by-article"))
      .withRequestBody(matchingJsonPath("$.query-filters.type",equalToJson("[\"Article\"]")))
      .willReturn(
        aResponse()
          .withHeader("Content-Type", "application/json")
          .withHeader("access-control-allow-origin", "*")
          .withBody(Source.fromFile(cannedResponsesPath + "/RecommendByArticleOK.json").mkString
            .replace("&apos;", " ").replace("&quot;", "'"))
          .withStatus(200)))

    //Recommend Article Error - Missing Sort
    wireMockServer.stubFor(post(urlMatching(".*/recommend-articles/by-article"))
      .withRequestBody(equalToJson("{\"sort\": \"\"}", true, true))
      .willReturn(
        aResponse()
          .withHeader("Content-Type", "application/json")
          .withHeader("access-control-allow-origin", "*")
          .withBody(Source.fromFile(cannedResponsesPath + "/RecommendByArticleError.json").mkString
            .replace("&apos;", " ").replace("&quot;", "'"))
          .withStatus(400)))

    //Recommend Images
    wireMockServer.stubFor(post(urlMatching(".*/recommend-articles/by-article"))
      .withRequestBody(matchingJsonPath("$.query-filters.type",equalToJson("[\"Image\"]")))
      .willReturn(
        aResponse()
          .withHeader("Content-Type", "application/json")
          .withHeader("access-control-allow-origin", "*")
          .withBody(Source.fromFile(cannedResponsesPath + "/RecommendByImageOK.json").mkString
            .replace("&apos;", " ").replace("&quot;", "'"))
          .withStatus(200)))

    //Recommend Galleries
    wireMockServer.stubFor(post(urlMatching(".*/recommend-articles/by-article"))
      .withRequestBody(matchingJsonPath("$.query-filters.type",equalToJson("[\"Gallery\"]")))
      .willReturn(
        aResponse()
          .withHeader("Content-Type", "application/json")
          .withHeader("access-control-allow-origin", "*")
          .withBody(Source.fromFile(cannedResponsesPath + "/RecommendByGalleryOK.json").mkString
            .replace("&apos;", " ").replace("&quot;", "'"))
          .withStatus(200)))

    //Recommend Videos
    wireMockServer.stubFor(post(urlMatching(".*/recommend-articles/by-article"))
      .withRequestBody(matchingJsonPath("$.query-filters.type",equalToJson("[\"Video\"]")))
      .willReturn(
        aResponse()
          .withHeader("Content-Type", "application/json")
          .withHeader("access-control-allow-origin", "*")
          .withBody(Source.fromFile(cannedResponsesPath + "/RecommendByVideoOK.json").mkString
            .replace("&apos;", " ").replace("&quot;", "'"))
          .withStatus(200)))

    //Recommend Articles by Source filter = Reuters
    wireMockServer.stubFor(post(urlMatching(".*/recommend-articles/by-article"))
      .withRequestBody(matchingJsonPath("$.query-filters.source",equalToJson("[\"Reuters\"]")))
      .willReturn(
        aResponse()
          .withHeader("Content-Type", "application/json")
          .withHeader("access-control-allow-origin", "*")
          .withBody(Source.fromFile(cannedResponsesPath + "/RecommendBySource.json").mkString
            .replace("&apos;", " ").replace("&quot;", "'"))
          .withStatus(200)))


    //Recommend Articles by Channel filter = Football
    wireMockServer.stubFor(post(urlMatching(".*/recommend-articles/by-article"))
      .withRequestBody(matchingJsonPath("$.query-filters.channel",equalToJson("[\"football\"]")))
      .willReturn(
        aResponse()
          .withHeader("Content-Type", "application/json")
          .withHeader("access-control-allow-origin", "*")
          .withBody(Source.fromFile(cannedResponsesPath + "/RecommendByChannel.json").mkString
            .replace("&apos;", " ").replace("&quot;", "'"))
          .withStatus(200)))
  }

  //driver class
    def main(args : Array[String]) {

      // port, canned file directory, swagger file, state model file, opening state
      MyStub.configureStub(args(0), args(1), args(2), args(3), "any")
      MyStub.start


    }
}