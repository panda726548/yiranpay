package com.yiranpay.web.controller.amqp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.Binding.DestinationType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.amqp.RabbitProperties;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.alibaba.fastjson.JSON;
import com.yiranpay.amqp.domain.MqExchanges;
import com.yiranpay.amqp.domain.MqExchangesQueue;
import com.yiranpay.amqp.domain.MqQueue;
import com.yiranpay.amqp.service.IMqExchangesQueueService;
import com.yiranpay.amqp.service.IMqExchangesService;
import com.yiranpay.amqp.service.IMqQueueService;
import com.yiranpay.common.annotation.Log;
import com.yiranpay.common.core.controller.BaseController;
import com.yiranpay.common.core.domain.AjaxResult;
import com.yiranpay.common.core.page.TableDataInfo;
import com.yiranpay.common.enums.BusinessType;
import com.yiranpay.common.utils.poi.ExcelUtil;
import com.yiranpay.framework.util.ShiroUtils;
import cn.hutool.json.JSONUtil;

/**
 * RabbitMQ交换器 信息操作处理
 * 
 * @author yiran
 * @date 2019-04-28
 */
@Controller
@RequestMapping("/amqp/mqExchanges")
public class MqExchangesController extends BaseController
{
	private Logger        logger = LoggerFactory.getLogger(MqExchangesController.class);
    private String prefix = "amqp/mqExchanges";
    @Autowired
	private AmqpAdmin amqpAdmin;
	@Autowired
	private IMqExchangesService mqExchangesService;
	@Autowired
	private IMqQueueService mqQueueService;
	@Autowired
	private RabbitProperties rabbitProperties;
	@Autowired
	private IMqExchangesQueueService mqExchangesQueueService;
	
	@RequiresPermissions("amqp:mqExchanges:view")
	@GetMapping()
	public String mqExchanges()
	{
	    return prefix + "/mqExchanges";
	}
	
	/**
	 * 查询RabbitMQ交换器列表
	 */
	@RequiresPermissions("amqp:mqExchanges:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(MqExchanges mqExchanges)
	{
		startPage();
        List<MqExchanges> list = mqExchangesService.selectMqExchangesList(mqExchanges);
        logger.info("查询RabbitMQ交换器列表:{}",JSONUtil.toJsonStr(list));
		return getDataTable(list);
	}
	
	
	/**
	 * 导出RabbitMQ交换器列表
	 */
	@RequiresPermissions("amqp:mqExchanges:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(MqExchanges mqExchanges)
    {
    	List<MqExchanges> list = mqExchangesService.selectMqExchangesList(mqExchanges);
        ExcelUtil<MqExchanges> util = new ExcelUtil<MqExchanges>(MqExchanges.class);
        return util.exportExcel(list, "mqExchanges");
    }
	
	/**
	 * 新增RabbitMQ交换器
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	@GetMapping("/admin")
	public String admin()
	{
		StringBuffer bf = new StringBuffer();
		bf.append("http://");
		bf.append(rabbitProperties.getHost());
		bf.append(":15672");
		String url = bf.toString();
	    return "redirect:"+url;
	}
	
	/**
	 * 新增保存RabbitMQ交换器
	 */
	@RequiresPermissions("amqp:mqExchanges:add")
	@Log(title = "RabbitMQ交换器", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(MqExchanges mqExchanges)
	{		
		return toAjax(mqExchangesService.insertMqExchanges(mqExchanges));
	}

	/**
	 * 修改RabbitMQ交换器
	 */
	@GetMapping("/edit/{exchangesId}")
	public String edit(@PathVariable("exchangesId") Integer exchangesId, ModelMap mmap)
	{
		MqExchanges mqExchanges = mqExchangesService.selectMqExchangesById(exchangesId);
		mmap.put("mqExchanges", mqExchanges);
		
	    return prefix + "/edit";
	}
	
	@GetMapping("/showQueues/{exchangesId}")
	public String showQueues(@PathVariable("exchangesId") Integer exchangesId, ModelMap mmap)
	{
		MqExchanges mqExchanges = mqExchangesService.selectMqExchangesById(exchangesId);
		List<MqQueue> list = new ArrayList<MqQueue>();
		if(mqExchanges != null){
			list = mqExchanges.getQueues();
		}
		mmap.put("queues", list);
		mmap.put("exchangesId", exchangesId);
	    return prefix + "/exchangeQueues";
	}
	
	@GetMapping("/untyingQueue/{exchangesId}/{queueId}")
	public String untyingQueue(@PathVariable("exchangesId") Integer exchangesId,@PathVariable("queueId") Integer queueId, ModelMap mmap)
	{
		logger.info("解绑路由器ID:【{}】,队列ID:【{}】",exchangesId,queueId);
		//解绑
		MqQueue mqQueue = mqQueueService.selectMqQueueById(queueId);
		MqExchanges mqExchanges = mqExchangesService.selectMqExchangesById(exchangesId);
		Map<String, Object> arguments = new HashMap<String,Object>();
		if(!StringUtils.isBlank(mqQueue.getArguments())){
			//json转map
			arguments = JSON.parseObject(mqQueue.getArguments(), Map.class);
		}
		amqpAdmin.removeBinding(new Binding(mqQueue.getQueueName(), DestinationType.QUEUE, 
				mqExchanges.getName(), mqQueue.getRoutingKey(), arguments));
		//删除关联关系
		MqExchangesQueue exchangesQueue = new MqExchangesQueue();
		exchangesQueue.setExchangesId(exchangesId);
		exchangesQueue.setQueueId(queueId);
		mqExchangesQueueService.deleteMqExchangesQueue(exchangesQueue);
		logger.info("队列【{}】从交换器【{}】中解绑完毕.",mqQueue.getQueueName(),mqExchanges.getName());
	    return prefix + "/exchangeQueues";
	}
	
	
	/**
	 * 修改保存RabbitMQ交换器
	 */
	@RequiresPermissions("amqp:mqExchanges:edit")
	@Log(title = "RabbitMQ交换器", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(MqExchanges mqExchanges)
	{		
		return toAjax(mqExchangesService.updateMqExchanges(mqExchanges));
	}
	
	/**
	 * 删除RabbitMQ交换器
	 */
	@RequiresPermissions("amqp:mqExchanges:remove")
	@Log(title = "RabbitMQ交换器", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(mqExchangesService.deleteMqExchangesByIds(ids));
	}
	
}
