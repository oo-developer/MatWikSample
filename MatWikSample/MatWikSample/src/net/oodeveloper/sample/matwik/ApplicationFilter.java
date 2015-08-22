package net.oodeveloper.sample.matwik;

import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;

import org.apache.wicket.protocol.http.WicketFilter;

@WebFilter(value = "/*", 
initParams = { 
	@WebInitParam(name = "applicationClassName", value = "net.oodeveloper.sample.matwik.Application"), 
	@WebInitParam(name="filterMappingUrlPattern", value="/*"),
	@WebInitParam(name="configuration",  value="development") })

public class ApplicationFilter extends WicketFilter  {

}
