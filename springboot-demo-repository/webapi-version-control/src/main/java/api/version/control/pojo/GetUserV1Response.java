package api.version.control.pojo;

import lombok.*;

/**
 * Created by mr.yang on 2018/4/30.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetUserV1Response {
    private Integer userId;
    private String userName;
}
