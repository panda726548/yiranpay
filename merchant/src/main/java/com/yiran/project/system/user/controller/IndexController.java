package com.yiran.project.system.user.controller;

import java.util.List;

import com.yiran.common.utils.security.ShiroUtils;
import com.yiran.project.merchant.account.domain.Account;
import com.yiran.project.merchant.account.domain.MemberTrCompanyMember;
import com.yiran.project.merchant.account.domain.MemberTrMemberAccount;
import com.yiran.project.merchant.account.domain.MemberTrMemberAccountDetail;
import com.yiran.project.merchant.account.service.IMemberTrCompanyMemberService;
import com.yiran.project.merchant.account.service.IMemberTrMemberAccountDetailService;
import com.yiran.project.merchant.account.service.IMemberTrMemberAccountService;
import com.yiran.project.merchant.order.domain.TradeOrder;
import com.yiran.project.merchant.order.service.ITradeOrderService;
import com.yiran.project.system.notice.domain.Notice;
import com.yiran.project.system.notice.domain.NoticeRead;
import com.yiran.project.system.notice.service.INoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import com.yiran.framework.config.YiRanConfig;
import com.yiran.framework.web.controller.BaseController;
import com.yiran.project.system.user.domain.MemberTmMemberIdentity;
import com.yiran.project.system.user.domain.SysLogininfor;
import com.yiran.project.system.user.domain.User;
import com.yiran.project.system.user.service.IMemberTmMemberIdentityService;
import com.yiran.project.system.user.service.ISysLogininforService;

/**
 * 首页 业务处理
 * 
 * @author yiran
 */
@Controller
public class IndexController extends BaseController
{

    @Autowired
    private YiRanConfig yiranConfig;
    @Autowired
    private INoticeService noticeService;
    @Autowired
	private ITradeOrderService tradeOrderService;
    @Autowired
    private IMemberTmMemberIdentityService memberTmMemberIdentityService;
    @Autowired
    private ISysLogininforService sysLogininforService;
    @Autowired
    private IMemberTrMemberAccountService memberTrMemberAccountService;
    @Autowired
    private IMemberTrMemberAccountDetailService memberTrMemberAccountDetailService;
    @Autowired
    private IMemberTrCompanyMemberService memberTrCompanyMemberService;
    // 系统首页
    @GetMapping("/index")
    public String index(ModelMap mmap)
    {
        // 取身份信息
        User user = getSysUser();
        mmap.put("user", user);
        mmap.put("copyrightYear", yiranConfig.getCopyrightYear());
        mmap.put("demoEnabled", yiranConfig.isDemoEnabled());
        return "index";
    }

    // 切换主题
    @GetMapping("/system/switchSkin")
    public String switchSkin(ModelMap mmap)
    {
        return "skin";
    }

    // 系统介绍
    @GetMapping("/system/main")
    public String main(ModelMap model)
    {
    	//获取操作员信息
    	
    	//获取当前用户会员企业信息
    	MemberTrCompanyMember companyMember = memberTrCompanyMemberService.selectMemberTrCompanyMemberById(ShiroUtils.getUserId());
    	//获取最后一条登录记录
    	SysLogininfor sysLogin = getLastSysLogin();
    	//获取6条通知数据
    	Notice notice = new Notice();
    	NoticeRead noticeRead = new NoticeRead();
    	noticeRead.setLoginName(ShiroUtils.getLoginName());
    	notice.setNoticeRead(noticeRead);
    	List<Notice> noticeList = noticeService.selectNoticeList6(notice);
    	//获取当前用户账户信息
    	Account account = getAccountInfo();
    	//获取当前用户今日交易总额
    	double todayTradeMoney = tradeOrderService.geTodayTradeMoney(ShiroUtils.getUserId());
    	//今日交易笔数
    	int todayTradeCount = tradeOrderService.geTodayTradeCount(ShiroUtils.getUserId());
    	//获取当前用户昨日交易总额
    	double yesterdayTradeMoney = tradeOrderService.getYesterdayTradeMoney(ShiroUtils.getUserId());
    	//昨日交易笔数
    	int yesterdayTradeCount = tradeOrderService.getYesterdayTradeCount(ShiroUtils.getUserId());
    	//查询最新10条订单信息
    	TradeOrder order = new TradeOrder();
    	order.setMemberId(ShiroUtils.getUserId());
    	List<TradeOrder> orderList = tradeOrderService.selectTradeOrderList10s(order);
    	model.put("orderList", orderList);
    	model.put("noticeList", noticeList);
    	model.put("sysLogin", sysLogin);
    	model.put("account", account);
    	model.put("todayTradeMoney", todayTradeMoney);
    	model.put("yesterdayTradeMoney", yesterdayTradeMoney);
    	model.put("todayTradeCount", todayTradeCount);
    	model.put("yesterdayTradeCount", yesterdayTradeCount);
    	model.put("companyMember", companyMember);
        return "main_v1";
    }
    
    private Account getAccountInfo() {
    	//根据商户号查询账户
    	MemberTrMemberAccount memberAccount = memberTrMemberAccountService.selectMemberTrMemberAccountByMemberId(ShiroUtils.getUserId());
    	//根据商户id获取账户明细
    	MemberTrMemberAccountDetail accountDetail = memberTrMemberAccountDetailService.selectMemberTrMemberAccountDetailByAccountId(memberAccount.getId());
    	Account account = new Account();
    	account.setAccountId(memberAccount.getAccountId());
    	account.setAlias(memberAccount.getAlias());
    	account.setMemberId(memberAccount.getMemberId());
    	account.setBalance(accountDetail.getBalance());
    	account.setAvailableBalance(accountDetail.getAvailableBalance());
    	account.setFrozenBalance(accountDetail.getFrozenBalance());
    	account.setWithdrawBalance(accountDetail.getWithdrawBalance());
    	account.setCurrencyCode("人民币");
		return account;
	}

	public SysLogininfor getLastSysLogin(){
    	//获取当前商户所有的登录名称
    	MemberTmMemberIdentity memberTmMemberIdentity = new MemberTmMemberIdentity();
    	memberTmMemberIdentity.setMemberId(ShiroUtils.getUserId());
    	List<MemberTmMemberIdentity> list = memberTmMemberIdentityService.selectMemberTmMemberIdentityList(memberTmMemberIdentity);
    	String [] loginNames = new String[list.size()];
    	for (int i=0;i< list.size();i++) {
    		loginNames[i] = list.get(i).getIdentity();
		}
    	SysLogininfor logininfor = sysLogininforService.selectLastLogininfor(loginNames);
		return logininfor;
    }
}
