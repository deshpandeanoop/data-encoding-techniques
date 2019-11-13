package com.github.data.encoding.avro;

import java.io.IOException;

public interface SchemaBuilder {
	void build(String schemaPath) throws IOException;
}
