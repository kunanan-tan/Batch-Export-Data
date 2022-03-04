package com.example.batch.data.constants;

import org.springframework.stereotype.Component;
/**
 *
 * @author kunanan.t
 */
@Component
public class Constants {

    public interface TXT_FILE {
        String STR_SPLITER = "\\;";
        String FILE_EXTENSION = "txt";
        String ERR_FILE_EXTENSION = "err";
        String DATE_FORMAT = "yyyyMMdd";
        String DATE_DB_FORMAT = "dd/MM/yyyy";
        String CHARSET_ENCODING = "TIS620";
        String FILE_EXTENSION_CSV = "csv";
        String STR_SPLITER_PIPE = "\\|";
        String DATE_FORMAT_TEST = "yyyyMMdd-HHmmss";
        String MONTH_FORMAT_TEST = "MM";

    }
}