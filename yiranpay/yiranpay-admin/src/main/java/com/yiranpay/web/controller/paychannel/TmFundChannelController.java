package com.yiranpay.web.controller.paychannel;

import java.util.List;
import org.apache.shiro.authz.annotation.RequiresPermissions;
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
import com.yiranpay.paychannel.domain.TmFundChannelInst;
import com.yiranpay.paychannel.service.ITmFundChannelInstService;
import com.yiranpay.paychannel.service.ITmFundChannelService;
import com.yiranpay.framework.util.ShiroUtils;
import com.yiranpay.common.config.Global;
import com.yiranpay.common.core.controller.BaseController;
import com.yiranpay.common.core.domain.AjaxResult;
import com.yiranpay.common.core.page.TableDataInfo;
import com.yiranpay.common.utils.poi.ExcelUtil;

/**
 * 资金渠道 信息操作处理
 * 
 * @author yiran
 * @date 2019-04-19
 */
@Controller
@RequestMapping("/paychannel/tmFundChannel")
public class TmFundChannelController extends BaseController
{
    private String prefix = "paychannel/tmFundChannel";
	
	@Autowired
	private ITmFundChannelService tmFundChannelService;
	
	@Autowired
	private ITmFundChannelInstService tmFundChannelInstService;
	
	@RequiresPermissions("paychannel:tmFundChannel:view")
	@GetMapping()
	public String tmFundChannel()
	{
	    return prefix + "/tmFundChannel";
	}
	
	/**
	 * 查询资金渠道列表
	 */
	@RequiresPermissions("paychannel:tmFundChannel:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(TmFundChannel tmFundChannel)
	{
		startPage();
        List<TmFundChannel> list = tmFundChannelService.selectTmFundChannelList(tmFundChannel);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出资金渠道列表
	 */
	@RequiresPermissions("paychannel:tmFundChannel:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(TmFundChannel tmFundChannel)
    {
    	List<TmFundChannel> list = tmFundChannelService.selectTmFundChannelList(tmFundChannel);
        ExcelUtil<TmFundChannel> util = new ExcelUtil<TmFundChannel>(TmFundChannel.class);
        return util.exportExcel(list, "tmFundChannel");
    }
	
	/**
	 * 新增资金渠道
	 */
	@GetMapping("/add")
	public String add(ModelMap mmap)
	{
		List<TmFundChannelInst> fundChannelInstList = tmFundChannelInstService.selectTmFundChannelInstList(new TmFundChannelInst());
		mmap.put("fundChannelInstList", fundChannelInstList);
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存资金渠道
	 */
	@RequiresPermissions("paychannel:tmFundChannel:add")
	@Log(title = "资金渠道", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(TmFundChannel tmFundChannel)
	{		
		return toAjax(tmFundChannelService.insertTmFundChannel(tmFundChannel));
	}

	/**
	 * 修改资金渠道
	 */
	@GetMapping("/edit/{fundChannelCode}")
	public String edit(@PathVariable("fundChannelCode") String fundChannelCode, ModelMap mmap)
	{
		TmFundChannel tmFundChannel = tmFundChannelService.selectTmFundChannelById(fundChannelCode);
		mmap.put("tmFundChannel", tmFundChannel);
		List<TmFundChannelInst> fundChannelInstList = tmFundChannelInstService.selectTmFundChannelInstList(new TmFundChannelInst());
		mmap.put("fundChannelInstList", fundChannelInstList);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存资金渠道
	 */
	@RequiresPermissions("paychannel:tmFundChannel:edit")
	@Log(title = "资金渠道", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(TmFundChannel tmFundChannel)
	{		
		return toAjax(tmFundChannelService.updateTmFundChannel(tmFundChannel));
	}
	
	/**
	 * 删除资金渠道
	 */
	@RequiresPermissions("paychannel:tmFundChannel:remove")
	@Log(title = "资金渠道", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(tmFundChannelService.deleteTmFundChannelByIds(ids));
	}
	
}
