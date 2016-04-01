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

import com.sdi.presentation.BeanViajes;

@WebFilter(
		dispatcherTypes = {DispatcherType.REQUEST},
		urlPatterns= {"/viaje.xhtml"}
		)
public class FilterViaje implements Filter{

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest requ = ((HttpServletRequest)req);
		
		if(requ.getSession() == null
				|| requ.getSession().getAttribute("viajes") == null
				|| ((BeanViajes)requ.getSession().getAttribute("viajes")).getViaje() == null){
			
			String path = requ.getContextPath();
			((HttpServletResponse)res).sendRedirect(path + "/index.xhtml"); 
			
			
		}
		else{
			
			chain.doFilter(req, res);
			
		}

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
