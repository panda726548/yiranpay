package com.yiranpay.web.controller.system;


import com.yiranpay.common.core.controller.BaseController;
import com.yiranpay.common.core.domain.AjaxResult;
import com.yiranpay.common.core.page.TableDataInfo;
import com.yiranpay.common.core.text.Convert;
import com.yiranpay.common.utils.StrUtils;
import com.yiranpay.framework.util.ShiroUtils;
import com.yiranpay.system.domain.SysContentTopic;
import com.yiranpay.system.domain.SysUser;
import com.yiranpay.system.service.ISysContentColumnService;
import com.yiranpay.system.service.ISysContentTopicService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 文章管理 信息操作处理
 * 
 * @author yiran
 * @date 2018-07-26
 */
@Controller
@RequestMapping("/system/content/topic")
public class SysContentTopicController extends BaseController
{
	private static final Logger logger = LoggerFactory.getLogger(SysContentTopicController.class);
    private String prefix = "system/content/topic";
    @Autowired
	private ISysContentColumnService channelService;
	@Autowired
	private ISysContentTopicService topicService;
	
	@RequiresPermissions("system:content:topic:view")
	@GetMapping()
	public String topic()
	{
	    return prefix + "/topic";
	}
	
	/**
	 * 查询文章管理列表
	 */
	@RequiresPermissions("system:content:topic:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(SysContentTopic topic)
	{
		startPage();
        List<SysContentTopic> list = topicService.selectTopicList(topic);
		return getDataTable(list);
	}
	
	/**
	 * 新增文章管理
	 */
	@RequiresPermissions("system:content:topic:add")

	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}

	/**
	 * 修改文章管理
	 */
	@RequiresPermissions("system:content:topic:edit")

	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, Model model)
	{
		SysContentTopic topic = topicService.selectTopicById(id);
		//logger.debug("修改文章："+JSON.toJSONString(topic));
		model.addAttribute("topic", topic);
	    return prefix + "/edit";
	}
	
	/**
	 * 保存文章管理
	 */
	@RequiresPermissions("system:content:topic:save")

	@PostMapping("/save")
	@ResponseBody
	public AjaxResult save(SysContentTopic topic)
	{
		Integer id = topic.getId();
		SysUser user = ShiroUtils.getSysUser();
		if (StrUtils.isNotNull(id)){
			topic.setUpdateBy(user.getLoginName());
		}else
		{
			topic.setCreateBy(user.getLoginName());
			topic.setAuthor(user.getUserName());
			topic.setUid(Integer.parseInt(String.valueOf(user.getUserId())));
		}

		//logger.info("保存文章数据："+JSON.toJSONString(topic));
		if (topicService.saveTopic(topic) > 0)
		{
			return success();
		}
		return error();
	}
	
	/**
	 * 发布文章
	 */
	@RequiresPermissions("system:content:topic:publishArticles")

	@PostMapping("/publishArticles")
	@ResponseBody
	public AjaxResult publishArticles( Integer id)
	{
		SysContentTopic topic = topicService.selectTopicById(id);
		topic.setStatus(1);
		if (topicService.saveTopic(topic) > 0)
		{
			return success();
		}
		return error();
	}
	
	/**
	 * 预览文章
	 */
	@RequiresPermissions("system:content:topic:showArticles")
	@GetMapping("/showArticles/{id}")
	public String showArticles(@PathVariable("id") Integer id, Model model)
	{
		SysContentTopic topic = topicService.selectTopicById(id);
		model.addAttribute("topic", topic);
	    return prefix + "/article";
	}
	
	/**
	 * 取消发布文章
	 */
	@RequiresPermissions("system:content:topic:cancelRelease")

	@PostMapping("/cancelRelease")
	@ResponseBody
	public AjaxResult cancelRelease(Integer id)
	{
		SysContentTopic topic = topicService.selectTopicById(id);
		topic.setStatus(0);
		if (topicService.saveTopic(topic) > 0)
		{
			return success();
		}
		return error();
	}
	
	/**
	 * 删除文章管理
	 */
	@RequiresPermissions("system:content:topic:remove")

	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{
		Long[] topicIds = Convert.toLongArray(ids);
		for (Long topicId : topicIds)
		{
			SysContentTopic topic = topicService.selectTopicById(topicId.intValue());
			if(topic.getStatus() == 1){
				return error("该文章已经发布，不能删除，如果需要删除，请先取消发布！");
			}

		}
		int rows = topicService.deleteTopicByIds(ids);
		if (rows > 0)
		{
			return success();
		}
		return error();
	}
	@RequiresPermissions("system:content:topic:remove")

	@PostMapping("/remove/{id}")
    @ResponseBody
    public AjaxResult remove(@PathVariable("id") Integer id)
    {
		SysContentTopic topic = topicService.selectTopicById(id);
		if(topic.getStatus() == 1){
			 return error("该文章已经发布，不能删除，如果需要删除，请先取消发布！");
		}
        if (topicService.deleteTopicById(id) > 0)
        {
            return success();
        }
        return error();
    }
	/**
     * 选择栏目树
     */
    @GetMapping("/selectChannelTree/{id}")
    public String selectChannelTree(@PathVariable("id") Integer id, Model model)
    {
        model.addAttribute("column", channelService.selectChannelById(id));
        return prefix + "/tree";
    }
	
    
  //只需要加上下面这段即可，注意不能忘记注解
  	@InitBinder
  	public void initBinder(WebDataBinder binder, WebRequest request) {
  		
  		//转换日期
  		DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
  		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));// CustomDateEditor为自定义日期编辑器
  	}

}
