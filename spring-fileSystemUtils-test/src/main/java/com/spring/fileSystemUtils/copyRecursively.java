package com.spring.fileSystemUtils;

import org.springframework.util.FileSystemUtils;

import java.io.File;
import java.io.IOException;

public class copyRecursively {
    public static void main(String[] args) throws IOException {
        File src = new File("a");
        File dest = new File("aa");
        // void copyRecursively(File src, File dest)递归复制src文件到dest（目标路径不存在则自动创建）
        // 重载方法void copyRecursively(Path src, Path dest)
        FileSystemUtils.copyRecursively(src, dest);
    }
}
