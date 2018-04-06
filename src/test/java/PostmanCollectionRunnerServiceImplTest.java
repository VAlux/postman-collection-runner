import com.alvo.runner.http.HttpRequestExecutor;
import com.alvo.runner.checking.TestExecutionStatus;
import com.alvo.runner.checking.result.MonitoringExecutionResult;
import com.alvo.runner.checking.utils.TestResultProcessor;
import com.alvo.runner.postman.PostmanCollectionRunnerService;
import com.alvo.runner.postman.PostmanCollectionRunnerServiceImpl;
import com.alvo.runner.postman.PostmanResponseHandler;
import com.alvo.runner.postman.PostmanTestScriptEvaluator;
import com.alvo.runner.postman.model.PostmanResponseCode;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.io.InputStream;

public class PostmanCollectionRunnerServiceImplTest {

  @Mock
  private HttpRequestExecutor executor;

  private PostmanCollectionRunnerService collectionRunner;

  @BeforeClass
  public void setUp() throws IOException {
    MockitoAnnotations.initMocks(this);
    Mockito.when(executor.execute(Mockito.any(), Mockito.any(PostmanResponseHandler.class))).thenReturn(createEvaluator());
    this.collectionRunner = new PostmanCollectionRunnerServiceImpl(executor);
  }

  private PostmanTestScriptEvaluator createEvaluator() {
    PostmanResponseCode responseCode = new PostmanResponseCode(200, "name", "detail");
    return new PostmanTestScriptEvaluator().withResponseCode(responseCode);
  }

  @Test
  public void testExecutePostmanCollection() throws IOException {
    InputStream resource = getClass().getClassLoader().getResourceAsStream("standard_api_call_collection_old_style.json");
    MonitoringExecutionResult result = collectionRunner.executePostmanCollectionResource(resource);
    Assert.assertNotNull(result);
    Assert.assertEquals(result.getTests().size(), 1);
    Assert.assertEquals(result.getStatus(), TestExecutionStatus.SUCCESS);
    Assert.assertEquals(TestResultProcessor.extractMonitoringExceptions(result).size(), 0);
    Assert.assertEquals(TestResultProcessor.extractIndividualCheckResults(result).size(), 1);
  }
}