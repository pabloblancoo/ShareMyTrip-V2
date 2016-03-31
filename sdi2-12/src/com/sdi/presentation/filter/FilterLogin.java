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
import com.sdi.presentation.BeanUsuario;

@WebFilter(
		dispatcherTypes = {DispatcherType.REQUEST},
		urlPatterns= {"/a/*"}
		)
public class FilterLogin implements Filter{

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {

		BeanUsuario usuario = ((BeanSettings) ((HttpServletRequest)req)
				.getSession().getAttribute(new String("settings"))).getUsuario();

		if(usuario == null){
			String path = ((HttpServletRequest)req).getContextPath();
			((HttpServletResponse)res).sendRedirect(path + "/index.xhtml"); 
		}

		chain.doFilter(req, res);

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
