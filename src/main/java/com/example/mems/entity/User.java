package com.example.mems.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author cold fish
 * @since 2022-06-19
 */
@Getter
@Setter
  public class User implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "id", type = IdType.AUTO)
      private Integer id;

    private String name;

    private Integer health;

    private Float balance;

    private String idCard;

    private Integer role;

    private String account;

    private byte[] password;

    private String phone;


}
