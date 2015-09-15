package com.lw.authority;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class LoginAction {

	protected static final Logger logger = Logger.getLogger(LoginAction.class);

	private LoginService loginService;

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView login(User user, HttpSession session) {
		ModelAndView ret = new ModelAndView();

		try {
			boolean flag = loginService.login(user, session);
			if (flag) {
				ret.setViewName("main");
			} else {
				ret.addObject("info","登录失败");
				ret.setViewName("login");

			}

		} catch (Exception e) {
			logger.error("login action error...");
		}

		return ret;
	}

	public LoginService getLoginService() {
		return loginService;
	}

	@Resource
	public void setLoginService(LoginService loginService) {
		this.loginService = loginService;
	}

}
