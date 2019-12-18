package net.wanho.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Created by Administrator on 2019/12/17.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Role {
    private Integer roleId;
    private String roleName;
    private List<Permission> permissionList;
}
