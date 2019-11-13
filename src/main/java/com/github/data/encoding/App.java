package com.github.data.encoding;

import java.io.IOException;

import com.github.data.encoding.avro.EmployeeSchemaBuilder;
import com.github.data.encoding.avro.SchemaBuilder;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws IOException
    {
        SchemaBuilder schemaBuilder = new EmployeeSchemaBuilder();
        schemaBuilder.build("E:\\worskspaces\\git-workspace\\employee.avsc");
    }
  
}
