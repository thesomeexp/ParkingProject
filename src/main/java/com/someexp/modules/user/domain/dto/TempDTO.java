package com.someexp.modules.user.domain.dto;

import com.someexp.common.validator.group.AddGroup;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * @author someexp
 * @date 2020/12/31
 */
@Data
public class TempDTO {

    /**
     * 停车场id
     */
    @NotNull(message = "temp.pid.cant.be.null", groups = AddGroup.class)
    private Long pid;

    /**
     * 空闲停车位数
     */
    @NotNull(message = "temp.state.cant.be.null", groups = AddGroup.class)
    @Min(value = 1, message = "temp.state.out.of.min", groups = AddGroup.class)
    private Integer state;

    @Length(max = 100, message = "temp.content.out.of.length", groups = AddGroup.class)
    private String content;

}
