package com.sdi.presentation.filter;

import java.io.IOException;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sdi.presentation.BeanSettings;

@WebFilter(
		dispatcherTypes = {DispatcherType.REQUEST},
		urlPatterns= {"/iniciarSesion.xhtml", "/registrarse.xhtml"}
		)
public class FilterNoLogin implements Filter{

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest requ = ((HttpServletRequest)req);
		
		if((requ.getSession() != null
				&& requ.getSession().getAttribute("settings") != null
				&& ((BeanSettings)requ.getSession().getAttribute("settings")).getUsuario() == null)
				|| requ.getSession() == null
				|| requ.getSession().getAttribute("settings") == null){
			
			chain.doFilter(req, res);
			
		}
		else{
			
			String path = requ.getContextPath();
			((HttpServletResponse)res).sendRedirect(path + "/index.xhtml"); 
			
		}

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
