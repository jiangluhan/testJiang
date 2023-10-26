
public enum ResultCode {
    SUCCESS(0, "success"),
    ERROR(10001, "其他错误"),
    PARAM_NULL(10002, "参数为空"),
    PARAM_ERROR(10003, "参数错误"),
    TOKEN_NULL(10004, "TOKEN为空"),
    TOKEN_ERROR(10005, "TOKEN不存在"),
    UNAUTHORIZED(10006, "权限不足"),
    USER_NAME_INIT(10007, "请先修改初始账号与密码"),
    EQUIPMENT_IS_NOT_BIND(20003, "工号、资产编号未绑定"),
    EQUIPMENT_IS_NOT_UNINSTALL(20004, "客户端不可卸载"),
    EQUIPMENT_IS_NOT_EXIST(20005, "溯源对象ID未绑定资产信息"),
    EQUIPMENT_IS_ALREADY_EXIST(20006, "资产信息已存在"),
    PRINT_LOG_IS_NOT_EXIST(20201, "打印日志记录不存在"),
    MAIL_SERVER_INFO_IS_NOT_EXIST(20301, "获取邮件服务器信息失败"),
    UPGRADE_ZIP_ERROR(20401, "上传的升级包内容不符合规范"),
    STRATEGY_NAME_IS_EXISTS(20501, "策略名称已存在"),
    STRATEGY_NOT_EXISTS(20502, "策略不存在"),
    SQL_ERROR(50001, "SQL错误"),
    USER_NAME_NULL(60001, "用户不存在"),
    PASSWORD_ERROR(60002, "密码错误"),
    USER_LOCK(60003, "密码错误次数大于5"),
    USER_EXPIRE(60004, "超过90天没修改密码"),
    PASS_INVALIDATE(60005, "密码不合法"),
    USERNAME_SAME(60006, "新旧账号相同"),
    USERNAME_EXISTS(60007, "账号已存在"),
    PASS_INIT_VALIDATE(60008, "密码不能为初始密码"),
    DISABLED_ACCOUNT(60009, "账户被禁用"),
    FIRST_LOGIN_UPDATE_NAME_PWD(60010, "首次登陆强制修改账户密码"),
    TRACEABILITY_CODE_GENERATE_ERROR(70001, "溯源标识生成失败"),
    TRACEABILITY_CODE_NULL(70002, "溯源标识不存在"),
    TRACEABILITY_OBJECT_NULL(70003, "溯源对象不存在"),
    TRACEABILITY_OBJECT_ALREADY_GENERATE_CODE(70004, "该溯源对象已生成该水印类型的溯源标识"),
    TRACEABILITY_OBJECT_EXIST(70005, "该溯源对象已存在"),
    TRACEABILITY_CODE_ALLOCATION_FAIL(70006, "溯源标识分配失败"),
    FILE_WATERMARK_SRC_FILE_SIZE_OUT_OF_LIMIT(70100, "文件嵌入水印的文件大小超出限制"),
    FILE_WATERMARK_SRC_FILE_FORMAT_ERROR(70101, "源文件格式错误"),
    FILE_FORMAT_IS_TAMPERED_WITH(70102, "水印嵌入失败，该文件被篡改非有效格式"),
    FILE_CONVERT_FAIL(70200, "文档格式转换失败"),
    FILE_WATERMARK_SRC_FILE_HAS_WATERMARK(71003, "水印已存在，暂不支持更新水印信息"),
    FILE_WATERMARK_QR_FILE_GENERATE_FAIL(71004, "电子文档二维码生成失败"),
    FILE_DOWNLOAD_FAIL(71005, "文件下载失败"),
    FILE_UPLOAD_FAIL(71006, "文件上传失败"),
    FILE_RESULT_NOT_EXIST(71007, "结果文件不存在"),
    M_PROGRAM_HANDING_EXCEPTION(71008, "明水印嵌入失败：程序处理异常"),
    PROGRAM_HANDING_TIMEOUT(71009, "水印嵌入失败：嵌入超时"),
    FILE_PARAM_ERROR(71010, "电子文档水印嵌入失败：输入参数错误！"),
    FILE_PATH_PARAM_ERROR(71011, "电子文档水印嵌入失败：输入路径、输出路径或字体路径不可用！"),
    FILE_ENVIRONMENT_INIT_FAILED(71012, "电子文档水印嵌入失败：系统环境初始化异常！"),
    FILE_MEMORY_ERROR(71013, "电子文档水印嵌入失败：内存异常！"),
    FILE_ALL_PAGES_FAILED(71014, "电子文档水印嵌入失败：所有页嵌入失败！"),
    FILE_HAVE_NO_ENOUGH_EMBEDDABLE_WORDS(71015, "电子文档水印嵌入失败：文档中可嵌入字数不足！"),
    FILE_HAVE_NO_EMBEDDABLE_TYPEFACE(71016, "电子文档水印嵌入失败：文档中无可嵌入字体！"),
    FILE_UNKNOWN_ERROR(71017, "电子文档水印嵌入失败：未知原因嵌入失败！"),
    FILE_OFD_ANALYSE_FAIL(71018, "电子文档水印嵌入失败：OFD文件解析失败！"),
    FILE_OFD_SAVE_FAIL(71019, "电子文档水印嵌入失败：OFD文件保存失败！"),
    FILE_OFD_M_WATERMARK_FAIL(71020, "电子文档水印嵌入失败：明水印嵌入失败！"),
    FILE_OFD_CAN_NOT_FIND_FILE(71021, "电子文档水印嵌入失败：无可嵌入的资源文件！"),
    FILE_HAS_BEEN_ENCRYPTED(71022, "电子文档水印嵌入失败：加密文档不可嵌入！"),
    M_WATERMARK_RECORD_NOT_EXIST(72001, "明水印模板记录不存在"),
    M_WATERMARK_MODULE_NAME_IS_EXISTS(72002, "明水印模板名称已存在"),
    M_WATERMARK_PICTURE_UPLOAD_ERROR(72003, "明水印图片上传失败"),
    VIDEO_WATERMARK_SERIAL_NUM_OUT_OF_LIMIT(73001, "视频嵌入水印的序号超出限制"),
    VIDEO_WATERMARK_EMBED_FAIL(73002, "视频文件水印嵌入失败"),
    PIC_WATERMARK_SERIAL_NUM_OUT_OF_LIMIT(74001, "图片嵌入水印的序号超出限制"),
    PIC_WATERMARK_EMBED_FAIL(74002, "图片文件水印嵌入失败"),
    WEB_PARAMETER_ENCRYPTION_FAILED(75001, "网页水印嵌入失败：水印参数加密失败"),
    WEB_UUID_LENGTH_ERROR(75002, "网页水印嵌入失败：溯源标识长度错误"),
    WEB_GENERATE_WATERMARK_PARAMETERS_FAIL(75003, "网页水印嵌入失败：sdk生成水印参数失败"),
    WEB_TIMESTAMP_ERROR(75004, "网页水印嵌入失败：时间戳错误"),
    WEB_UUID_ERROR(75005, "网页水印嵌入失败：错误的溯源标识"),
    WEB_PARAMETERS_ERROR(75006, "网页水印嵌入失败：传参错误"),
    WEB_JS_READ_ERROR(75007, "js文件读取失败"),
    LICENSE_ERROR(80001, "授权失败"),
    LICENSE_TIME_ERROR(80002, "授权时间到期"),
    LICENSE_NUM_ERROR(80003, "授权数量超限"),
    LICENSE_PRODUCT_NO_AUTHORITY(80004, "产品未授权"),
    LICENSE_KEY_INVALID(80005, "授权密钥失效"),
    LICENSE_INFO_GET_FAIL(80006, "获取授权信息错误"),
    SERVER_SIGN_INFO_GET_FAIL(80007, "获取服务器标识信息错误"),
    LICENSE_KEY_ANALYSIS_FAIL(80008, "授权密钥解析错误"),
    LICENSE_KEY_NULL_FAIL(80009, "授权密钥为空"),
    SERVER_SIGN_NOT_MATCHING(80010, "服务器标识串不匹配"),
    LICENSE_CANNOT_REDUCE_WATERMARK_TYPE(80011, "授权不能减少水印类型"),
    WATERMARK_CODING_SCHEME_NOT_EQUAL(80012, "水印编码方案不一致"),
    WATERMARK_COUNT_OR_SERIAL_NUM_CANNOT_REDUCE(80013, "水印容量或者次数不能减少"),
    WATERMARK_CONTAIN_APPIDS_CONNOT_REDUCE(80014, "水印包含的appIds不能减少"),
    FORENSIC_RECORD_NOT_EXIST(90001, "取证记录不存在"),
    FORENSIC_FILE_UPLOAD_FAIL(90002, "取证文件上传失败"),
    FORENSIC_TRANSFORM_FAIL(90003, "仿射变换失败"),
    FORENSIC_EXTRACT_FAIL(90004, "提取失败"),
    FORENSIC_RESULT_TRACEABILITY_FAIL(90005, "取证结果溯源失败"),
    FORENSIC_STEP_ERROR(90006, "取证步骤错误"),
    FORENSIC_CORRECT_FAIL(90007, "取证矫正失败"),
    FORENSIC_IMAGE_SIZE_TOO_SMALL(90008, "取证图片太小"),
    FORENSIC_FILE_SIZE_OUT_OF_LIMIT(90009, "取证文件超出限制"),
    FORENSIC_CROP_FAIL(90010, "取证裁剪失败"),
    FORENSIC_FILE_VIDEO_GENERATE_IMAGE_FAIL(90011, "取证文件处理失败"),
    FORENSIC_RESULT_ANALYSIS_FAIL(90012, "取证结果解析失败"),
    FORENSIC_TASK_IS_NOT_EXIST(90013, "取证任务不存在"),
    FORENSIC_FAILED_SRC_FILE_MISSING(90101, "取证失败泄密样本导出时，泄密样本源文件缺失"),
    FORENSIC_FAILED_SRC_FILE_COPY_ERROR(90102, "取证失败泄密样本复制出错"),
    FORENSIC_FAILED_SAMPLES_OUT_OF_LIMIT(90103, "取证失败泄密样本超过100条"),
    FORENSIC_FAILED_SAMPLES_CHOOSE_ERROR(90104, "取证成功的泄密样本不可导出"),
    FORENSIC_FAILED_FILES_OUT_OF_LIMIT(90105, "取证成功的泄密样本超过500M"),
    FORENSIC_FAILED_FILES_ZIP_ERROR(90106, "泄密样本压缩失败"),
    FORENSIC_FAILED_ZIP_KEY_ENCRYPT_ERROR(90107, "压缩包密码加密失败"),
    FORENSIC_RESULT_FILES_UNZIP_ERROR(90201, "取证结果压缩文件解压失败"),
    FORENSIC_RESULT_ZIP_ERROR(90202, "文件不符合规范，请重新上传！"),
    FORENSIC_RESULT_ZIP_KEY_DECRYPT_ERROR(90203, "取证结果压缩文件密码解密失败"),
    FORENSIC_RESULT_INFO_FILE_ERROR(90204, "取证结果信息文件格式错误"),
    FORENSIC_RESULT_FILE_COPY_ERROR(90205, "泄密样本复制出错"),
    FORENSIC_RESULT_ZIP_PASSWORD_ERROR(90206, "压缩文件密码错误"),
    FORENSIC_RESULT_NOT_EXIST(90207, "压缩文件密码错误"),
    FORENSIC_RESULT_ORG_CODE_ERROR(90208, "导入失败，泄密样本来源非本系统"),
    MINIO_CREATE_BUCKET_CODE_ERROR(91000, "minio文件系统创建bucket失败"),
    MINIO_CHECK_BUCKET_EXIST_ERROR(91001, "minio文件系统bucket不存在");

    private int code;
    private String msg;

    private ResultCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return this.code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
