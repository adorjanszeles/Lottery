package com.lottery.service;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.lottery.model.RawWeeklyDraw;
import com.lottery.model.WeeklyDraw;
import com.lottery.repository.WeeklyDrawJPARepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * A csv beolvasását végző osztály.
 */
public class PersistFromCsv {

    private WeeklyDrawJPARepository wrepo;

    private static final Logger LOGGER = LoggerFactory.getLogger(PersistFromCsv.class);

    @Autowired
    public PersistFromCsv(WeeklyDrawJPARepository weeklyDrawJPARepository) {
        this.wrepo = weeklyDrawJPARepository;
    }

    /**
     * A csv-ből beolvasott sorokból RawWeeklyDraw instance-okat gyártó függvény.
     *
     * @param filename a beolvasandó fájl neve
     * @return List of RawWeeklyDraws
     */
    public List populateRawWeeklyDraws(String filename) {

        List<RawWeeklyDraw> objectList = new ArrayList<>();
        try {
            objectList = loadObjectList(RawWeeklyDraw.class, filename);

        } catch (FileNotFoundException e) {
            LOGGER.debug("nem beolvasható a fájl");
            e.printStackTrace();
        } catch (IOException e) {
            LOGGER.debug("probléma beolvasáskor vagy nem létezik a fájl");
            e.printStackTrace();
        }

        return objectList;
    }

    /**
     * A RawWeeklyDraw instanc-okból az adatbázisba mentődő WeeklyDraw instance-okat gyártó függvény.
     * @param rawWeeklyDraws lista
     */
    public void persistAllToDB(List<RawWeeklyDraw> rawWeeklyDraws) {

        WeeklyDrawConverter converter = new WeeklyDrawConverterImpl();
        try {
            List<WeeklyDraw> weeklyDraws = converter.convertRawsToWeeklyDraws(rawWeeklyDraws);
            this.wrepo.save(weeklyDraws);
        } catch (FileNotFoundException e) {
            LOGGER.debug("nem beolvasható a fájl" + e);
        }
    }

    /**
     * Meghívja a persistAllToDB függvényt és paraméterként átadja neki a populateRawWeeklyDraws függvényt.
     */
    public void readAndPersist() {
        persistAllToDB(this.populateRawWeeklyDraws("otoswithheader.csv"));
    }

    /**
     * A Csv beolvasását végző függvény.
     * @param type RawWeeklyDraw osztály adattagjai alapján kell olvasni a csv-t
     * @param fileName a beolvasandó fájl neve
     * @param <T> RawWeeklyDraw
     * @return Object List (RawWeeklyDraw lista)
     * @throws IOException ha a fájl beolvasása nem sikerül
     */
    private <T> List<T> loadObjectList(Class<T> type, String fileName) throws IOException {
        CsvSchema bootstrapSchema = CsvSchema.emptySchema().withColumnSeparator(';').withHeader();
        CsvMapper mapper = new CsvMapper();
        File file = new ClassPathResource(fileName).getFile();
        try (MappingIterator<T> readValues = mapper.readerFor(type).with(bootstrapSchema).readValues(file)) {
            return readValues.readAll();
        } catch (Exception e) {
            LOGGER.debug("Error occurred while loading object list from file " + fileName + ": " + e);
            throw new Error("");
        }
    }

}
