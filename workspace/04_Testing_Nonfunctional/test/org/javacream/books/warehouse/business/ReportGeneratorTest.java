package org.javacream.books.warehouse.business;

import org.javacream.books.warehouse.ReportGenerator;
import org.javacream.util.test.BaseDecorator;
import org.javacream.util.test.HeapProfilingDecorator;
import org.junit.Test;

public class ReportGeneratorTest {
	@Test
	public void testReportGenerator() {
		ReportGenerator reportGenerator = new ReportGeneratorImpl();
		reportGenerator = BaseDecorator.decorate(reportGenerator,
				HeapProfilingDecorator.class);
		reportGenerator.createReport(1);
	}
}
