package com.lottery.service;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.lottery.model.RawWeeklyDraw;
import com.lottery.model.WeeklyDraw;
import com.lottery.repository.WeeklyDrawJPARepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CsvToRawWeeklyDraw {

    @Autowired
    WeeklyDrawJPARepository wrepo;

    private  <T> List<T> loadObjectList(Class<T> type, String fileName) throws IOException {
        CsvSchema bootstrapSchema = CsvSchema.emptySchema().withColumnSeparator(';').withHeader();
        CsvMapper mapper = new CsvMapper();
        File file = new ClassPathResource(fileName).getFile();
        try (MappingIterator<T> readValues = mapper.readerFor(type)
                                                                .with(bootstrapSchema)
                                                                .readValues(file)){
            return readValues.readAll();
        } catch (Exception e) {
            System.out.println("Error occurred while loading object list from file " + fileName + ": " + e);
            throw new Error("");
        }
    }

    public List populateRawWeeklyDraws(String filename) {

        List<RawWeeklyDraw> objectList = new ArrayList<>();
        try {
            objectList = loadObjectList(RawWeeklyDraw.class, "otos.csv");

        } catch (IOException e) {
            System.out.println("nem beolvasható a fájl" + e);
        }

        return objectList;
    }

    public void persistAllToDB(List<RawWeeklyDraw> rawWeeklyDraws) throws FileNotFoundException {


        WeeklyDrawConverter converter = new WeeklyDrawConverterImpl();
        List<WeeklyDraw> weeklyDraws = converter.convertRawsToWeeklyDraws(rawWeeklyDraws);

        wrepo.saveAll(weeklyDraws);
    }

}
