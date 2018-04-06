package com.alvo.runner.postman;

import com.alvo.runner.postman.model.PostmanResponseCode;
import org.apache.commons.codec.Charsets;
import org.apache.http.HttpEntity;
import org.apache.http.util.EntityUtils;

import javax.script.ScriptContext;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.script.SimpleScriptContext;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public final class PostmanTestScriptEvaluator {

  private final ScriptContext ctx;
  private final ScriptEngineManager scriptEngineManager;
  private final ScriptEngine engine;

  public PostmanTestScriptEvaluator() {
    this.ctx = new SimpleScriptContext();
    this.scriptEngineManager = new ScriptEngineManager();
    this.engine = scriptEngineManager.getEngineByName("nashorn");

    this.ctx.setBindings(engine.createBindings(), ScriptContext.ENGINE_SCOPE);
  }

  public PostmanTestScriptEvaluator withResponseBody(HttpEntity response) throws IOException {
    ctx.setAttribute("responseBody", EntityUtils.toString(response, Charsets.UTF_8), ScriptContext.ENGINE_SCOPE);
    return this;
  }

  public PostmanTestScriptEvaluator withResponseCode(PostmanResponseCode responseCode) {
    ctx.setAttribute("responseCode", responseCode, ScriptContext.ENGINE_SCOPE);
    return this;
  }

  public PostmanTestScriptEvaluator withResponseTime(Double responseTime) {
    ctx.setAttribute("responseTime", responseTime, ScriptContext.ENGINE_SCOPE);
    return this;
  }

  public PostmanTestScriptEvaluator withIteration(Integer iteration) {
    ctx.setAttribute("iteration", iteration, ScriptContext.ENGINE_SCOPE);
    return this;
  }

  @SuppressWarnings("unchecked")
  public Map<String, Object> getTestResults() {
    return (Map<String, Object>) this.ctx.getAttribute("tests", ScriptContext.ENGINE_SCOPE);
  }

  public Object evaluate(String script) throws ScriptException {
    ctx.setAttribute("tests", new HashMap<String, String>(), ScriptContext.ENGINE_SCOPE);
    return engine.eval(script, ctx);
  }
}
