package net.wanho.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by Administrator on 2019/12/17.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Permission {
    private Integer permissionId;
    private String permissionName;
    private String url;
    private String permission;
    private Integer parentId;
}
