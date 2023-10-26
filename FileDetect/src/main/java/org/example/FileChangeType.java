package org.example;


public enum FileChangeType {
    FILE_UPDATE(0, "文件更新"),
    FILE_ADD(1, "文件添加"),
    FILE_DELETED(2, "文件删除");

    private int type;
    private String desc;

    public int getType() {
        return type;
    }


    public void setType(int type) {
        this.type = type;
    }


    public String getDesc() {
        return desc;
    }


    public void setDesc(String desc) {
        this.desc = desc;
    }

    FileChangeType(Integer type, String desc) {
        this.type = type;
        this.desc = desc;
    }
}
