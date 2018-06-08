package com.wdc.learning.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author wdc
 * @date 2018/6/8
 */
@Service
@Transactional
public class TxHelperService {
    public void txTestRepoTx(HomeService target) {
        target.internalTestRepoTx();
    }
}
