package com.egeo.components.promotion.service.read;


import com.egeo.components.promotion.dto.PraisePointDTO;

public interface PraisePointReadService {

	PraisePointDTO queryPraisePointByUserId(Long userId);

}
	