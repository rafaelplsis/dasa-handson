package com.dasa.utils;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;

import java.io.IOException;
import java.io.Reader;
import java.io.UncheckedIOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.dasa.domain.DadoPopulacional;

public class DatasetReaderTest {

    private DatasetReader reader;
	private Integer linesCount;

    @Before
    public void init() {
        final Path path = Paths.get("src/test/resources/datasets", "dados_populacionais.csv");
          
        reader = createDatasetReader(path);
        linesCount = createLinesCount(path);
    }

    @Test
    public void readsDataset() {
        final List<DadoPopulacional> dadosDadoPopulacionalList = reader.readDataset();
        assertThat(dadosDadoPopulacionalList, hasSize(linesCount));
    }

    private DatasetReader createDatasetReader(Path path) {
        try {

            Reader reader = Files.newBufferedReader(path, Charset.forName("UTF-8"));
            return new DatasetReader(reader);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
    
    private int createLinesCount(Path path){
        try {
			return this.linesCount = Files.lines(path).mapToInt(l -> 1).sum();
		} catch (IOException e) {
            throw new UncheckedIOException(e);
		}      	
    }
}