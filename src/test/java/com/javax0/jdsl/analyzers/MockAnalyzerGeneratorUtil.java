package com.javax0.jdsl.analyzers;

import org.mockito.Matchers;
import org.mockito.Mockito;
import org.mockito.stubbing.OngoingStubbing;

import com.javax0.jdsl.executors.ListExecutor;

public class MockAnalyzerGeneratorUtil {
	private MockAnalyzerGeneratorUtil() {
		throw new RuntimeException(MockAnalyzerGeneratorUtil.class.toString()
				+ " is utility class, should not be instantiated");
	}

	private final static ListExecutor NO_EXECUTOR = null;
	private final static SourceCode NO_SOURCE_CODE = null;

	static Analyzer successNTimesThenFail(int n) {
		Analyzer analyzer = Mockito.mock(Analyzer.class);
		OngoingStubbing<AnalysisResult> stub = Mockito.when(analyzer
				.analyze(Matchers.any(SourceCode.class)));
		for (int i = 0; i < n; i++) {
			stub = stub.thenReturn(SimpleAnalysisResult.success(Analyzer.class,
					NO_SOURCE_CODE, NO_EXECUTOR));
		}
		stub.thenReturn(SimpleAnalysisResult.failed(Analyzer.class));
		return analyzer;
	}
}
