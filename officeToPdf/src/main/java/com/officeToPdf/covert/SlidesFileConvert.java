package com.officeToPdf.covert;

import com.aspose.slides.License;
import com.aspose.slides.Presentation;
import com.aspose.slides.SaveFormat;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class SlidesFileConvert implements IFileConvert {

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
        if (!getLicense()) {
            return null;
        }
        String pdfPath = null;
        FileOutputStream os = null;
        try {
            pdfPath = getResultPath(filePath, FILE_PDF_SUFFIX);
            os = new FileOutputStream(pdfPath);
            Presentation pres = new Presentation(filePath);
            pres.save(os, SaveFormat.Pdf);
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