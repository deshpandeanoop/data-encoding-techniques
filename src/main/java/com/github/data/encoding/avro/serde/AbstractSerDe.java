package com.github.data.encoding.avro.serde;

import org.apache.avro.Schema;
import org.apache.avro.file.DataFileReader;
import org.apache.avro.file.DataFileWriter;
import org.apache.avro.io.DatumReader;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.specific.SpecificDatumReader;
import org.apache.avro.specific.SpecificDatumWriter;

import java.io.File;
import java.io.IOException;
import java.util.List;

public abstract class AbstractSerDe<T> {

    protected Schema schema;

    protected DatumWriter<T> constructDatumWriter(Class<T> cls){
        return new SpecificDatumWriter<>(cls);
    }

    protected DataFileWriter<T> constructDataFileWriter(DatumWriter<T> datumWriter, String destination) throws IOException {
        if(null == schema){
            throw new IllegalArgumentException("Avro schema cannot be null.");
        }
        DataFileWriter dataFileWriter = new DataFileWriter(datumWriter);
        dataFileWriter.create(schema, new File(destination));
        return dataFileWriter;
    }

    protected DatumReader<T> constructDatumReader(Class<T> cls){
        return new SpecificDatumReader<>(cls);
    }

    protected DataFileReader<T> constructDataFileReader(DatumReader<T> datumReader, String source) throws IOException{
        return new DataFileReader(new File(source), datumReader);
    }

    public abstract void serializeToFile(List<T> beans,String destination) throws Exception;

    public abstract List<T> deserializeFromFile(String source) throws Exception;
}
