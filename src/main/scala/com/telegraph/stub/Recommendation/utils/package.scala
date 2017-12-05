package com.telegraph.stub.Recommendation

import org.apache.http.client.methods.{CloseableHttpResponse, HttpGet, HttpPost}
import org.apache.http.entity.StringEntity
import org.apache.http.impl.client.DefaultHttpClient

package object utils {
  def doPost(url:String, body:String): CloseableHttpResponse = {
    val post = new HttpPost(url)
    post.setHeader("Content-type", "application/json")
    post.setEntity(new StringEntity(body))
    (new DefaultHttpClient).execute(post)
  }

  def doGet(url:String): CloseableHttpResponse = {
    val get = new HttpGet(url)
    get.setHeader("Content-type", "application/json")
    (new DefaultHttpClient).execute(get)
  }
}
