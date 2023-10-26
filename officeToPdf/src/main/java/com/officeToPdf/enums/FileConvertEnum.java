package com.officeToPdf.enums;

import com.officeToPdf.covert.CellsFileConvert;
import com.officeToPdf.covert.IFileConvert;
import com.officeToPdf.covert.SlidesFileConvert;
import com.officeToPdf.covert.WordsFileConvert;

public enum FileConvertEnum {

    PPT("PPT", new SlidesFileConvert()),
    WORD("WORD", new WordsFileConvert()),
    EXCEL("EXCEL", new CellsFileConvert()),
    ;

    private String fileFormat;
    private IFileConvert fileConvert;

    FileConvertEnum(String fileFormat, IFileConvert fileConvert) {
        this.fileFormat = fileFormat;
        this.fileConvert = fileConvert;
    }

    public String getFileFormat() {
        return fileFormat;
    }

    public IFileConvert getFileConvert() {
        return fileConvert;
    }

    public static IFileConvert getFileConvert(String fileFormat) {
        for (FileConvertEnum fileConvertEnum : values()) {
            if (fileConvertEnum.fileFormat.equals(fileFormat)){
                return fileConvertEnum.fileConvert;
            }
        }
        return null;
    }
}