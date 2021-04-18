package com.someexp.modules.user.controller;

import com.someexp.common.domain.Result;
import com.someexp.common.exception.BusinessException;
import com.someexp.common.utils.MsgUtils;
import com.someexp.common.validator.ValidatorUtils;
import com.someexp.common.validator.group.AddGroup;
import com.someexp.modules.user.domain.dto.VotingDTO;
import com.someexp.modules.user.service.VotingService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author someexp
 * @date 2021/1/5
 */
@RestController
public class VotingController {

    @Resource
    private VotingService votingService;

    @PostMapping("/voting")
    public Result add(@RequestBody VotingDTO votingDTO) {
        ValidatorUtils.validateEntity(votingDTO, AddGroup.class);
        if (votingDTO.getUseful() == 0) {
            throw new BusinessException(MsgUtils.get("parameter.illegal", new String[]{"useful", String.valueOf(votingDTO.getUseful())}));
        }
        return Result.success(votingService.add(votingDTO), MsgUtils.get("voting.add.success"));
    }

}