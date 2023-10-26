import com.apifan.common.random.entity.DataField;
import com.apifan.common.random.source.PersonInfoSource;
import com.apifan.common.random.util.DataUtils;
import com.google.common.collect.Lists;
import com.opencsv.CSVWriter;

import java.io.FileWriter;
import java.util.List;

public class generationData {

    public static void main(String[] args) {

        //准备字段定义
        //字段name：随机姓名
        DataField df1 = new DataField("objectId", () -> PersonInfoSource.getInstance().randomChineseName());
//    //字段birthDate：随机日期
//    DataField df2 = new DataField("birthDate", () -> DateTimeSource.getInstance().randomPastDate("yyyy-MM-dd"));
//    //字段salary：随机数字
//    DataField df3 = new DataField("salary", () -> NumberSource.getInstance().randomInt(5000, 18000));
        List<DataField> fieldList = Lists.newArrayList(df1);

        //设置数量
        int total = 4000000;

        //生成JSON
//    String json = DataUtils.generateJson(fieldList, total);

        //生成CSV
        String csv = DataUtils.generateCsv(fieldList, total);
        String[] s = new String[] {
            csv
        };

        // 定义csv文件路径
        String csvFilePath = "test.csv";

        try {
            // 创建CSVWriter对象
            FileWriter fileWriter = new FileWriter(csvFilePath);
            CSVWriter csvWriter = new CSVWriter(fileWriter);
            csvWriter.writeNext(s);
            csvWriter.close();

        }catch (Exception ex){
            ex.printStackTrace();
        }
//    //生成SQL之前先要指定表名
//    String tableName = "user";
//    //生成SQL插入语句
//    String sql = DataUtils.generateJson(fieldList, tableName, total);

    }

}
