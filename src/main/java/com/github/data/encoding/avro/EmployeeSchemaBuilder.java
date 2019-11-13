package com.github.data.encoding.avro;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.avro.Schema;
import org.apache.avro.SchemaBuilder;

public class EmployeeSchemaBuilder implements com.github.data.encoding.avro.SchemaBuilder{
	
	public void build(String schemaPath) throws IOException{
		Schema schema = SchemaBuilder.record("Employee")
				.namespace("com.github.data.encoding.avro")
				.fields()
				.requiredInt("id")
				.requiredString("firstName")
				.requiredString("lastName")
				.endRecord();
		BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new File(schemaPath)));
		bufferedWriter.write(schema.toString());
		bufferedWriter.flush();
		bufferedWriter.close();
	}
}
