package student.model.formatters;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import student.model.ItemModel;
import student.model.ItemModel.GamesRecord;

import javax.annotation.Nonnull;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Collection;

public class Display {

    /**
     * Private constructor to prevent instantiation.
     */
    private Display() {
        // empty
    }

    /**
     * Pretty print the data in a human-readable format.
     * @param records the records to print
     * @param out the output stream to write to
     */
    private static void prettyPrint(Collection<GamesRecord> records, OutputStream out) {
        PrintStream pout = new PrintStream(out);
        for (GamesRecord record : records) {
            prettySingle(record, pout);
            pout.println();
        }
    }

    /**
     * Pretty print a single record.
     * Let this as an example, so you didn't have to worry about spacing.
     * @param record the record to print
     * @param out the output stream to write to
     */
    private static void prettySingle(@Nonnull GamesRecord record, @Nonnull PrintStream out) {
        out.println(record.title());
        out.println("             ID: " + record.id());
        out.println("             Short Description: " + record.shortDescription());
        out.println("             Genre: " + record.genre());
        out.println("             Platform: " + record.platform());
        out.println("             Publisher: " + record.publisher());
        out.println("             Developer: " + record.developer());
        out.println("             Release Date: " + record.releaseDate());
        out.println("             Thumbnail URL: " + record.thumbnail());
        out.println("             Game URL: " + record.gameUrl());
        out.println("             freetogame ProfileURL: " + record.freetogameProfileUrl());

    }

    /**
     * Write the data as XML.
     * @param records the records to write
     * @param out the output stream to write to
     */
    private static void writeXmlData(Collection<GamesRecord> records, OutputStream out) {
        XmlMapper mapper = new XmlMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        XmlWrapper wrapper = new XmlWrapper(records);
        try {
            mapper.writeValue(out, wrapper);
        } catch (IOException e) {
            System.out.println("Error writing xml");
        }
    }


    /**
     * Write the data as JSON.
     * @param records the records to write
     * @param out the output stream to write to
     */
    private static void writeJsonData(Collection<GamesRecord> records, OutputStream out) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        try {
            mapper.writeValue(out, records);
        } catch (IOException e) {
            System.out.println("Error writing json");
        }
    }

    /**
     * Write the data as CSV.
     * @param records the records to write
     * @param out the output stream to write to
     */
    private static void writeCSVData(Collection<GamesRecord> records, OutputStream out) {
        CsvMapper mapper = new CsvMapper();
        CsvSchema schema = mapper.schemaFor(ItemModel.GamesRecord.class).withHeader();
        try {
            mapper.writer(schema).writeValue(out, records);
        } catch (IOException e) {
            System.out.println("Error writing csv");
        }
    }

    /**
     * Write the data in the specified format.
     * @param records the records to write
     * @param format the format to write the records in
     * @param out the output stream to write to
     */
    public static void write(@Nonnull Collection<GamesRecord> records, @Nonnull Formats format,
                             @Nonnull OutputStream out) {

        switch (format) {
            case XML:
                writeXmlData(records, out);
                break;
            case JSON:
                writeJsonData(records, out);
                break;
            case CSV:
                writeCSVData(records, out);
                break;
            default:
                prettyPrint(records, out);
        }
    }
}
