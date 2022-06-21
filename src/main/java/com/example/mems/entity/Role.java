package com.example.mems.entity;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author cold fish
 * @since 2022-06-20
 */
@Getter
@Setter
  public class Role implements Serializable {

    private static final long serialVersionUID = 1L;

      private Integer id;

    private String role;


}
