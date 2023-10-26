package com.officeToPdf.covert;

import com.aspose.words.Document;
import com.aspose.words.License;
import com.aspose.words.SaveFormat;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class WordsFileConvert implements IFileConvert {

    private boolean getLicense() {
        boolean result = false;
        InputStream is = null;
        try {
            ClassLoader loader = Thread.currentThread().getContextClassLoader();
            is = loader.getResourceAsStream("license.xml");
            License license = new License();
            license.setLicense(is);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

    @Override
    public String fileToPdf(String filePath) {
        //去除水印
        if (!getLicense()) {
            return null;
        }
        String pdfPath = null;
        FileOutputStream os = null;
        try {
            pdfPath = getResultPath(filePath, FILE_PDF_SUFFIX);
            os = new FileOutputStream(pdfPath);
            Document doc = new Document(filePath);
            doc.save(os, SaveFormat.PDF);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return pdfPath;
    }
}