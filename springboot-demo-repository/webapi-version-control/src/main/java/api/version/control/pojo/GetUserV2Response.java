package api.version.control.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by mr.yang on 2018/4/30.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetUserV2Response {
    private Integer userId;
    private String userName;
    private String notes;
}
