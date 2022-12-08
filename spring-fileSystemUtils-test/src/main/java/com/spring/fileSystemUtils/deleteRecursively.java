package com.spring.fileSystemUtils;

import org.springframework.util.FileSystemUtils;

import java.io.File;

public class deleteRecursively {
    public static void main(String[] args) {
        // 实用File的delete目录尝试删除a目录
//        File file = new File("a");
//        Print.print(file.delete()); // false--》因为a目录包含子目录（文件），所以应该使用递归删除

//         这个目录必须放到最外层项目的下面，即testJiang的目录下，不能放在本项目的目录下
//        File file = new File("a");
//        Print.print(FileSystemUtils.deleteRecursively(file)); // true

        // 那如果我想删除的是本项目的目录文件怎么办呢？那就是路径带上该项目的名称
        File file = new File("spring-fileSystemUtils-test/a1");
        Print.print(FileSystemUtils.deleteRecursively(file)); // true

        /*
         * 上述使用的方法是：boolean deleteRecursively(@Nullable File root)递归删除指定文件或目录，删除成功返回true，失败返回false，不会抛出异常。
         * 重载方法boolean deleteRecursively(@Nullable Path root)和该方法功能相似，但该方法可能会抛出IO异常
         * 区别：一个入参是file，一个入参是path，建议使用入参为file的方法，这样不会抛出IO异常
         */
    }
}
