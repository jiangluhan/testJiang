package com.officeToPdf.covert;

import com.aspose.cells.License;
import com.aspose.cells.SaveFormat;
import com.aspose.cells.Workbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class CellsFileConvert implements IFileConvert {

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
            Workbook workbook = new Workbook(filePath);
            workbook.save(os, SaveFormat.PDF);
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