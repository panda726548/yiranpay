package com.yiranpay.web.controller.paychannel;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.yiranpay.common.annotation.Log;
import com.yiranpay.common.enums.BusinessType;
import com.yiranpay.paychannel.domain.TmFundChannel;
import com.yiranpay.paychannel.domain.TmFundChannelTargetInit;
import com.yiranpay.paychannel.domain.TrFcTargetInstRelation;
import com.yiranpay.paychannel.service.ITmFundChannelService;
import com.yiranpay.paychannel.service.ITmFundChannelTargetInitService;
import com.yiranpay.paychannel.service.ITrFcTargetInstRelationService;

import cn.hutool.json.JSONUtil;

import com.yiranpay.common.config.Global;
import com.yiranpay.common.core.controller.BaseController;
import com.yiranpay.common.core.domain.AjaxResult;
import com.yiranpay.common.core.page.TableDataInfo;
import com.yiranpay.common.utils.poi.ExcelUtil;
import com.yiranpay.framework.util.ShiroUtils;
/**
 * 目标机构 信息操作处理
 * 
 * @author yiran
 * @date 2019-04-19
 */
@Controller
@RequestMapping("/paychannel/trFcTargetInstRelation")
public class TrFcTargetInstRelationController extends BaseController
{
	private Logger        logger = LoggerFactory.getLogger(TrFcTargetInstRelationController.class);
    private String prefix = "paychannel/trFcTargetInstRelation";
    @Autowired
    private ITmFundChannelTargetInitService tmFundChannelTargetInitService;
	@Autowired
	private ITrFcTargetInstRelationService trFcTargetInstRelationService;
	
	@Autowired
	private ITmFundChannelService tmFundChannelService;
	
	@RequiresPermissions("paychannel:trFcTargetInstRelation:view")
	@GetMapping()
	public String trFcTargetInstRelation()
	{
	    return prefix + "/trFcTargetInstRelation";
	}
	
	/**
	 * 查询目标机构列表
	 */
	@RequiresPermissions("paychannel:trFcTargetInstRelation:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(TrFcTargetInstRelation trFcTargetInstRelation)
	{
		startPage();
        List<TrFcTargetInstRelation> list = trFcTargetInstRelationService.selectTrFcTargetInstRelationList(trFcTargetInstRelation);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出目标机构列表
	 */
	@RequiresPermissions("paychannel:trFcTargetInstRelation:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(TrFcTargetInstRelation trFcTargetInstRelation)
    {
    	List<TrFcTargetInstRelation> list = trFcTargetInstRelationService.selectTrFcTargetInstRelationList(trFcTargetInstRelation);
        ExcelUtil<TrFcTargetInstRelation> util = new ExcelUtil<TrFcTargetInstRelation>(TrFcTargetInstRelation.class);
        return util.exportExcel(list, "trFcTargetInstRelation");
    }
	
	
	/**
	 * 新增目标机构
	 */
	@GetMapping("/setTargetInst/{id}")
	public String setTargetInst(@PathVariable("id") String id, ModelMap mmap)
	{
		logger.info("设置目标机构->渠道编号：{}",id);
		mmap.put("fundChannelCode", id);
		//根据渠道号获取渠道信息
		TmFundChannel tmFundChannel = tmFundChannelService.selectTmFundChannelById(id);
		String fundChannelName = tmFundChannel.getName();
		logger.info("设置目标机构->渠道编名称：{}",fundChannelName);
		mmap.put("fundChannelName", fundChannelName);
		//获取目标机构
		TmFundChannelTargetInit tmFundChannelTargetInit = new TmFundChannelTargetInit();
		List<TmFundChannelTargetInit> instList = tmFundChannelTargetInitService.selectTmFundChannelTargetInitList(tmFundChannelTargetInit);
		mmap.put("instList", instList);
		TrFcTargetInstRelation trFcTargetInstRelation = new TrFcTargetInstRelation();
		trFcTargetInstRelation.setFundChannelCode(id);
		List<TrFcTargetInstRelation> list = trFcTargetInstRelationService.selectTrFcTargetInstRelationList(trFcTargetInstRelation);
		mmap.put("targetInstRelationList", list);
	    return prefix + "/add";
	}
	/**
	 * 新增保存目标机构
	 */
	@RequiresPermissions("paychannel:trFcTargetInstRelation:add")
	@Log(title = "目标机构", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(TrFcTargetInstRelation trFcTargetInstRelation)
	{		
		
		logger.info("添加目标机构：{}",JSONUtil.toJsonStr(trFcTargetInstRelation));
		//获取
		String targetInstCode = trFcTargetInstRelation.getTargetInstCode();
		List<TrFcTargetInstRelation> list = new ArrayList<TrFcTargetInstRelation>();
		if(!StringUtils.isBlank(targetInstCode)){
			String[] splits = targetInstCode.split(",");
			if(splits.length >0){
				for (int i = 0; i < splits.length; i++) {
					TrFcTargetInstRelation fir =new TrFcTargetInstRelation();
					fir.setFundChannelCode(trFcTargetInstRelation.getFundChannelCode());
					fir.setTargetInstCode(splits[i]);
					list.add(fir);
				}
			}
		}
		return toAjax(trFcTargetInstRelationService.insertBatchTrFcTargetInstRelations(list));
	}

	/**
	 * 修改目标机构
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		TrFcTargetInstRelation trFcTargetInstRelation = trFcTargetInstRelationService.selectTrFcTargetInstRelationById(id);
		mmap.put("trFcTargetInstRelation", trFcTargetInstRelation);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存目标机构
	 */
	@RequiresPermissions("paychannel:trFcTargetInstRelation:edit")
	@Log(title = "目标机构", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(TrFcTargetInstRelation trFcTargetInstRelation)
	{		
		return toAjax(trFcTargetInstRelationService.updateTrFcTargetInstRelation(trFcTargetInstRelation));
	}
	
	/**
	 * 删除目标机构
	 */
	@RequiresPermissions("paychannel:trFcTargetInstRelation:remove")
	@Log(title = "目标机构", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(trFcTargetInstRelationService.deleteTrFcTargetInstRelationByIds(ids));
	}
	
}
