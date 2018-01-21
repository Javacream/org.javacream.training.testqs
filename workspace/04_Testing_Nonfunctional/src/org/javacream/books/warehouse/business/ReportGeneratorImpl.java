package org.javacream.books.warehouse.business;

import org.javacream.books.warehouse.ReportGenerator;
import org.javacream.util.memory.MegaByte;

public class ReportGeneratorImpl implements ReportGenerator {

	@Override
	public MegaByte createReport(int size) {
		return new MegaByte(size);
	}

}
