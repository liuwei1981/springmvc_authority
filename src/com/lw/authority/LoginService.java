package com.lw.authority;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

	protected static final Logger logger = Logger.getLogger(LoginService.class);
	private HibernateTemplate hibernateTemplate;

	public boolean login(User user, HttpSession session) {
		boolean ret = false;

		try {
			String hql = " from User where username=? and password=? ";
			Object[] value = { user.getUsername(), user.getPassword() };
			List<User> users = this.hibernateTemplate.find(hql, value);

			if (users != null && !users.isEmpty()) {
				session.setAttribute("loginUser", users.get(0));
				ret = true;
			}

		} catch (Exception e) {
			logger.error("login service error...");
			logger.error(e);
		}

		return ret;
	}

	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	@Resource
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

}
