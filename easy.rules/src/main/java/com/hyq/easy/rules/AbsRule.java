package com.hyq.easy.rules;

import org.jeasy.rules.annotation.Fact;
import org.jeasy.rules.api.Facts;

/**
 * @author：huyuanqiang
 * @time: 2020-07-12 10:36
 * @description: \
 **/
public abstract class AbsRule {
    /**
     * 是否是当前规则
     * @param condition 条件对象
     * @return 是否匹配当前规则
     */
    public abstract boolean isThisCondition(@Fact("condition") Object condition);

    /**
     * 规则执行的动作
     * @param facts 参数
     */
    public abstract void action(Facts facts);
}
