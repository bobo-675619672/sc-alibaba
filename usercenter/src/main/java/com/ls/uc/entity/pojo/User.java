package com.ls.uc.entity.pojo;


import com.baomidou.mybatisplus.annotation.TableName;
import com.gitee.sunchenbin.mybatis.actable.annotation.Column;
import com.gitee.sunchenbin.mybatis.actable.annotation.Table;
import com.gitee.sunchenbin.mybatis.actable.constants.MySqlTypeConstant;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
@TableName("user")
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Table("user")
public class User implements Serializable {

    @Column(name = "uset_id",type = MySqlTypeConstant.INT,isKey = true,isAutoIncrement = true)
    private Integer id;

    @Column(name = "name",type = MySqlTypeConstant.VARCHAR,isKey = true)
    @NotBlank(message = "不能为空")
    private String name;

    @Column(name = "password",type = MySqlTypeConstant.VARCHAR,isKey = true)
    @NotBlank(message = "不能为空")
    private String pwd;

}
