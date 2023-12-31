package com.qiong.handshaker.entity.moduie.sys;

import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.qiong.handshaker.entity.moduie.BaseEntity;
import com.qiong.handshaker.entity.moduie.base.Storehouse;
import com.qiong.handshaker.entity.vo.sys.VoUserOptionForm;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@TableName("sys_user")
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseEntity {

    private String username;

    private String email;

    private String password;

    private String name;

    @TableField(value = "phone_no")
    private String phone_no;

    @TableField(value = "storehouse_id")
    private Long storehouse_id;

    @TableField(value = "is_admin")
    private Integer isAdmin;

    @TableField(exist = false)
    private Storehouse storehouse;

    /**
    * 只获取 脱敏信息
    * @params
    * @return
    */
    public void doProtected() {
        this.setPassword("");
    }

    /**
    *
    * @params
    * @return
    */
    public static User init(String email, String newPass) {
        User u = new User();
        u.setPassword(newPass);
        u.setEmail(email);
        u.setName("用戶_" + email);
        u.setUsername(email);
        u.setIsAdmin(1); // 默認全是 ADMIN
        return autoGenerate(u);
    }

    /**
    * 来自于 VoUserOptionForm
    * @params
    * @return
    */
    public static User init(VoUserOptionForm form) {
        // 率先提取
        boolean is_admin = form.getIsAdmin();
        form.setIsAdmin(null);
        long storehouse_id = form.getStorehouse();
        form.setStorehouse(null);

        User u = JSONUtil.toBean( JSONUtil.toJsonStr(form), User.class);

        u.setUsername(u.getEmail());
        u.setIsAdmin(is_admin ? 1 : 0);
        u.setStorehouse_id(storehouse_id);

        return autoGenerate(u);
    }

}
