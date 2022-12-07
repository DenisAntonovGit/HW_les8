import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.opencsv.CSVReader;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import static org.assertj.core.api.Assertions.assertThat;

public class ZipParseTest {

    ClassLoader cl = ZipParseTest.class.getClassLoader();

    @Test
    void zipFileParse() throws Exception {
        try(InputStream zipArch = cl.getResourceAsStream("TableOfCalory.xls.zip");
            ZipInputStream zipstream = new ZipInputStream(zipArch)
        ) {
            ZipEntry entry;
            while((entry = zipstream.getNextEntry()) != null) {
                String entryName = entry.getName();
                if (entryName.contains(".pdf")) {
                    PDF content = new PDF(zipstream);
                    assertThat(content.text).contains("REMEMBER THESE SHORTCUTS");
                } else if (entryName.contains(".xls")) {
                    XLS content = new XLS(zipstream);
                    assertThat(content.excel.getSheetAt(0).getRow(5).getCell(0).getStringCellValue()).contains("Абрикосы");
                } else if (entryName.contains(".csv")) {
                    CSVReader reader = new CSVReader(new InputStreamReader(zipstream));
                    List<String[]> content = reader.readAll();
                    assertThat(content.get(1)[1]).contains("Java");
                }
            }
        }
    }
}
