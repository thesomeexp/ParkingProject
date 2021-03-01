package com.someexp.modules.user.domain.dto;

import com.someexp.common.validator.group.AddGroup;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;

/**
 * @author someexp
 * @date 2020/12/3
 */
@Data
public class ParkingDTO {

    @NotBlank(message = "parking.name.cant.be.null", groups = AddGroup.class)
    @Length(min = 1, max = 20, message = "parking.name.out.of.length", groups = AddGroup.class)
    private String name;

    @NotBlank(message = "parking.location.cant.be.null", groups = AddGroup.class)
    private String location;

    private MultipartFile image;

    @NotBlank(message = "parking.content.cant.be.null", groups = AddGroup.class)
    @Length(min = 1, max = 100, message = "parking.content.out.of.length", groups = AddGroup.class)
    private String content;

}
