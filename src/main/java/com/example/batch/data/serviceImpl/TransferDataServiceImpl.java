package com.example.batch.data.serviceImpl;

import com.example.batch.data.constants.Constants;
import com.example.batch.data.util.Utils;
import com.example.batch.data.entity.TestDb;
import com.example.batch.data.repository.TestDbRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 *
 * @author kunanan.t
 */
@Slf4j
@Service
@Transactional
public class TransferDataServiceImpl {

    @Value("${txt.file.out.path}")
    private String PATH_FILE;
    @Value("${txt.err.in.path}")
    private String ERR_FILE;
    private final String quote = "\"";
    private final String lineSeparator = System.getProperty("line.separator");
    @Autowired
    private static TestDbRepository testDbRepository; // example db
    int sum = 0;
    int success = 0;
    int fail = 0;

    public void transfer() {
        try {
            List<TestDb> DBTest = testDbRepository.findTest();
            long startTime = System.currentTimeMillis();
            this.insertTableToCsv(DBTest);
            long endTime = System.currentTimeMillis() - startTime;
            this.reportSummary("Batch Process : transfer", endTime);

        } catch (Exception e) {
            this.fail++;
            log.error(e.getMessage(),e);
        }
    }


    private void insertTableToCsv(List<TestDb> DBTest) {
        OutputStreamWriter bw = null;
        Date dtd = new Date();
        Calendar c = Calendar.getInstance();
        //		c.setTime(dtd);
        //		c.add(Calendar.DATE, -1);
        dtd = c.getTime();
        SimpleDateFormat ft = new SimpleDateFormat(Constants.TXT_FILE.DATE_FORMAT_TEST);
        SimpleDateFormat mt = new SimpleDateFormat(Constants.TXT_FILE.MONTH_FORMAT_TEST);
        String dt = ft.format(dtd);
        String mm = mt.format(dtd);

        String fileName = "-TEST-";
        if (!PATH_FILE.endsWith("/"))
            PATH_FILE += "/";
        File file = new File(PATH_FILE + dt +fileName +mm+ "." + Constants.TXT_FILE.FILE_EXTENSION_CSV);
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            bw = new OutputStreamWriter(new FileOutputStream(file.getAbsoluteFile()), Constants.TXT_FILE.CHARSET_ENCODING);

            //subject
            bw.write(subjectLEG());
            bw.write(lineSeparator);

            if(!DBTest.isEmpty()){
                for(TestDb list:DBTest){
                    bw.write(quote+Utils.getString(list.getOne())+quote);
                    bw.write(",");
                    bw.write(quote+Utils.getInteger(list.getTwo())+quote);
                    bw.write(",");
                    bw.write(quote+Utils.getString(list.getThree())+quote);


                    bw.write(lineSeparator);
                    this.sum++;
                    this.success++;
                }
            }
        } catch (IOException e) {
            this.fail++;
            log.error(e.getMessage(),e);
        } finally {
            try {
                if (null != bw)
                    bw.close();
            } catch (IOException ex) {
                log.error(ex.getMessage(),ex);
            }
        }
    }

    public String subjectLEG(){
        String subject = "\"one\",\"two\",\"three\"";
        return subject;
    }


    private void reportSummary(String function,  long endTime) {
        log.info("**************** Summary of find : {} ****************", function);
        log.info("          Total time in function = {}.{} Minutes ", Utils.getMinutes(endTime), Utils.getSeconds(endTime));
        log.info("          Total find record = {}", this.sum);
        log.info("          Success = {}", this.success);
        log.info("          fail = {}", this.fail);
        log.info("************************************************************");
        clearData();
    }


    private void clearData() {
        this.sum = 0;
        this.success = 0;
        this.fail = 0;
    }
}
