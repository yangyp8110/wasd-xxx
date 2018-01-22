package api.doc.demo.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created by mr.yang on 2018/1/22.
 */
@Data
@ApiModel(value = "user", description = "user对象")
public class UserModel {
    @ApiModelProperty(value = "ID", dataType = "Integer", required = true)
    private Integer userId;
    @ApiModelProperty(value = "用戶名", dataType = "String")
    private String userName;
    @ApiModelProperty(value = "性別", dataType = "Integer", allowableValues = "0,1,2")
    private Integer sex;
}
