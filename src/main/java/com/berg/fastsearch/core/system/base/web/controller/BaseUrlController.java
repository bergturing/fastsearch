package com.berg.fastsearch.core.system.base.web.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * <p></p>
 *
 * @author bergturing@qq.com
 * @version v1.0
 * @apiNote Created on 18-5-7
 */
public abstract class BaseUrlController<ID> {

    /**
     * 列表界面
     * @return      列表界面的路径
     */
    @GetMapping("/list")
    public final String list(Model model){
        //处理数据
        listData(model);

        return getPrefix() + "/list";
    }

    /**
     * 新增车辆的界面
     * @return      新增车辆界面路径
     */
    @GetMapping("/add")
    public final String add(Model model){
        //处理数据
        addData(model);

        return getPrefix() + "/add";
    }

    /**
     * 编辑车辆的界面
     * @return      编辑车辆的路径
     */
    @GetMapping("/edit")
    public final String edit(ID id, Model model){
        //设置主键
        model.addAttribute("id", id);
        //处理数据
        editData(id, model);

        return getPrefix() + "/edit";
    }

    /**
     * 获取路径前缀
     * @return      返回路径前缀
     */
    protected abstract String getPrefix();

    /**
     * 为list页面添加数据
     * @param model     model对象
     */
    protected void listData(final Model model){}

    /**
     * 为add页面添加数据
     * @param model     model对象
     */
    protected void addData(final Model model){}

    /**
     * 为eidt页面添加数据
     * @param id        修改对象的主键
     * @param model     model对象
     */
    protected void editData(final ID id, final Model model){}
}
