package com.alvo.runner.postman.translator;

import com.alvo.runner.postman.model.PostmanRequest;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Optional;

public final class HttpRequestBaseTranslator {

  private HttpRequestBaseTranslator() {
  }

  public static Optional<HttpRequestBase> from(PostmanRequest request)
      throws URISyntaxException, UnsupportedEncodingException, MalformedURLException {

    URI uri = parseURI(request);
    switch (request.getMethod()) {
      case "GET":
        return Optional.of(new HttpGet(uri));
      case "POST":
        return Optional.of(new HttpPost(uri));
      case "PUT":
        return Optional.of(new HttpPut(uri));
      case "PATCH":
        return Optional.of(new HttpPatch(uri));
      case "DELETE":
        return Optional.of(new HttpDelete(uri));
      default:
        return Optional.empty();
    }
  }

  private static URI parseURI(PostmanRequest request)
      throws UnsupportedEncodingException, MalformedURLException, URISyntaxException {

    final String decodedUrl = URLDecoder.decode(request.getUrl().getRaw(), "UTF-8");
    URL url = new URL(decodedUrl);
    return new URI(
        url.getProtocol(),
        url.getUserInfo(),
        url.getHost(),
        url.getPort(),
        url.getPath(),
        url.getQuery(),
        url.getRef()
    );
  }
}
