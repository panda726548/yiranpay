package com.yiranpay.web.controller.system;

import com.alibaba.fastjson.JSON;
import com.yiranpay.common.annotation.Log;
import com.yiranpay.common.core.controller.BaseController;
import com.yiranpay.common.core.domain.AjaxResult;
import com.yiranpay.common.enums.BusinessType;
import com.yiranpay.common.utils.StrUtils;
import com.yiranpay.framework.util.ShiroUtils;
import com.yiranpay.system.domain.SysContentColumn;
import com.yiranpay.system.service.ISysContentColumnService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 网站系统栏目 信息操作处理
 * 
 * @author yiran
 * @date 2018-07-26
 */
@Controller
@RequestMapping("/system/content/column")
public class SysContentColumnController extends BaseController
{
	private static final Logger logger = LoggerFactory.getLogger(SysContentColumnController.class);
    private String prefix = "system/content/column";
	
	@Autowired
	private ISysContentColumnService columnService;
	
	@RequiresPermissions("system:content:column:view")
	@GetMapping()
	public String channel()
	{
	    return prefix + "/column";
	}
	
	/**
	 * 查询网站系统栏目列表
	 */
	@RequiresPermissions("system:content:column:list")
	@PostMapping("/list")
	@ResponseBody
	public List<SysContentColumn> list(SysContentColumn column)
	{
        column.setCreateBy(ShiroUtils.getLoginName());
        List<SysContentColumn> list = columnService.selectChannelList(column);
		return list;
	}
	
	/**
     * 加载网站栏目列表树
     */
    @GetMapping("/treeData")
    @ResponseBody
    public List<Map<String, Object>> treeData()
    {
        List<Map<String, Object>> tree = columnService.selectChannelTree();
        return tree;
    }
    
    /**
     * 新增
     */

    @RequiresPermissions("system:content:column:add")
    @GetMapping("/add/{parentId}")
    public String add(@PathVariable("parentId") Integer parentId, Model model)
    {
    	logger.debug("网站栏目管理-/add/parentId:{}"+parentId);
        SysContentColumn column = columnService.selectChannelById(parentId);
        model.addAttribute("column", column);
        return prefix + "/add";
    }
    
    
    /**
     * 保存
     */

    @RequiresPermissions("system:content:column:save")
    @PostMapping("/save")
    @ResponseBody
    public AjaxResult save(SysContentColumn column)
    {
    	logger.debug("网站栏目管理->保存栏目信息："+ JSON.toJSONString(column));
        Integer id = column.getId();
        if (StrUtils.isNotNull(id))
        {
            column.setUpdateBy(ShiroUtils.getLoginName());

        }
        else
        {
            column.setCreateBy(ShiroUtils.getLoginName());

        }
        if (columnService.saveChannel(column) > 0)
        {
            return success();
        }
        return error();
    }

    /**
     * 校验栏目名称
     */
    @PostMapping("/checkChannelNameUnique")
    @ResponseBody
    public String checkChannelNameUnique(SysContentColumn column)
    {
        String uniqueFlag = "0";
        if (column != null)
        {
            uniqueFlag = columnService.checkDeptNameUnique(column);
        }
        return uniqueFlag;
    }
    
    /**
     * 选择栏目树
     */
    @GetMapping("/selectChannelTree/{id}")
    public String selectChannelTree(@PathVariable("id") Integer id, Model model)
    {
        model.addAttribute("treeName", columnService.selectChannelById(id).getName());
        return prefix + "/tree";
    }

    /**
     * 修改
     */

    @RequiresPermissions("system:content:column:edit")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, Model model)
    {
        SysContentColumn column = columnService.selectChannelById(id);
    	logger.debug("网站栏目管理->修改栏目信息："+column);
        model.addAttribute("column", column);
        return prefix + "/edit";
    }
    
    /**
     * 删除
     */
    @RequiresPermissions("system:content:column:remove")
    @PostMapping("/remove/{id}")
    @ResponseBody
    public AjaxResult remove(@PathVariable("id") Integer id)
    {
        if (columnService.selectChannelCount(id) > 0)
        {
            return error(AjaxResult.Type.ERROR, "存在下级栏目,不允许删除");
        }
        //TODO :判断该栏目下是否有文章
        /*if ()
        {
            return error(1, "栏目存在文章,不允许删除");
        }*/
        if (columnService.deleteChannelById(id) > 0)
        {
            return success();
        }
        return error();
    }

}
