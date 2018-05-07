package com.berg.fastsearch.core.system.base.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * <p></p>
 *
 * @author bo.he02@hand-china.com
 * @version v1.0
 * @apiNote Created on 18-5-7
 */
public abstract class BaseUrlController<ID> {

    /**
     * 列表界面
     * @return      列表界面的路径
     */
    @GetMapping("/list")
    public String list(){
        return getPrefix() + "/list";
    }

    /**
     * 新增车辆的界面
     * @return      新增车辆界面路径
     */
    @GetMapping("/add")
    public String add(){
        return getPrefix() + "/add";
    }

    /**
     * 编辑车辆的界面
     * @return      编辑车辆的路径
     */
    @GetMapping("/edit")
    public String edit(ID id, ModelAndView modelAndView){
        //设置主键
        modelAndView.addObject("id", id);

        return getPrefix() + "/edit";
    }

    /**
     * 获取路径前缀
     * @return      返回路径前缀
     */
    protected abstract String getPrefix();
}
