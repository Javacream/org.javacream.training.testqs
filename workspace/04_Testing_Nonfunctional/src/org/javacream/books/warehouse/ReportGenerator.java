package org.javacream.books.warehouse;

import org.javacream.util.memory.MegaByte;

public interface ReportGenerator {

	public MegaByte createReport(int size);
}
