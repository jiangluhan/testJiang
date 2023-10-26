import com.officeToPdf.covert.IFileConvert;
import com.officeToPdf.enums.FileConvertEnum;

public class testFile {
    public static void main(String[] args) {
        IFileConvert fileConvert = FileConvertEnum.getFileConvert("WORD");
        String pdfUri = fileConvert.fileToPdf("D:\\Users\\admin\\Desktop\\1.doc");
        System.out.println(pdfUri);
    }

}
