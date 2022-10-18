import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class GwForensicReqBo {

    /**
     * 溯源标识
     */
    @NotBlank(message = "溯源标识不可为空")
    private String uuid;

    /**
     * 溯源时间
     */
    private String screenTime;

}