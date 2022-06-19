package com.example.mems.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.time.LocalDateTime;
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
  public class Ticket implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "id", type = IdType.AUTO)
      private Integer id;

    private Float price;

    private LocalDateTime timestamp;

    private Integer userID;

    private Integer fromID;

    private Integer toID;


}
