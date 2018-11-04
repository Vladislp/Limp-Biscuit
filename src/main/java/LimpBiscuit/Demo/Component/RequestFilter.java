package LimpBiscuit.Demo.Component;

import LimpBiscuit.Demo.Entity.Request;
import LimpBiscuit.Demo.Entity.RequestBrowser;
import LimpBiscuit.Demo.Entity.RequestOperatingSystem;
import LimpBiscuit.Demo.Repository.RequestBrowserRepository;
import LimpBiscuit.Demo.Repository.RequestOperatingSystemRepository;
import LimpBiscuit.Demo.Repository.RequestRepository;
import eu.bitwalker.useragentutils.Browser;
import eu.bitwalker.useragentutils.OperatingSystem;
import eu.bitwalker.useragentutils.UserAgent;
import eu.bitwalker.useragentutils.Version;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Component
public class RequestFilter implements Filter {
    @Autowired
    RequestBrowserRepository requestBrowserRepository;

    @Autowired
    RequestOperatingSystemRepository requestOperatingSystemRepository;

    @Autowired
    RequestRepository requestRepository;

    @Override
    public void init(FilterConfig filterConfig) {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        chain.doFilter(request, response);

        System.out.println("Hello");

        HttpServletRequest servletRequest = (HttpServletRequest) request;

        String header = servletRequest.getHeader("User-Agent");
        UserAgent userAgent = UserAgent.parseUserAgentString(header);
        Browser browser = userAgent.getBrowser();
        Version version = userAgent.getBrowserVersion();

        String browserName = browser.getName();
        String browserVersion = version == null ? "Unknown" : version.getVersion();

        RequestBrowser requestBrowser = requestBrowserRepository.findByNameAndVersion(browserName, browserVersion);

        if (requestBrowser == null) {
            requestBrowser = new RequestBrowser();
            requestBrowser.setName(browserName);
            requestBrowser.setVersion(browserVersion);

            try {
                requestBrowserRepository.save(requestBrowser);
            } catch (DataIntegrityViolationException exception) {
                requestBrowser = requestBrowserRepository.findByNameAndVersion(browserName, browserVersion);
            }
        }

        OperatingSystem operatingSystem = userAgent.getOperatingSystem();
        RequestOperatingSystem requestOperatingSystem = requestOperatingSystemRepository.findByNameAndAndFamily(operatingSystem.getName(), operatingSystem.getGroup().getName());

        if (requestOperatingSystem == null) {
            requestOperatingSystem = new RequestOperatingSystem();
            requestOperatingSystem.setName(operatingSystem.getName());
            requestOperatingSystem.setFamily(operatingSystem.getGroup().getName());

            try {
                requestOperatingSystemRepository.save(requestOperatingSystem);
            } catch (DataIntegrityViolationException exception) {
                requestOperatingSystem = requestOperatingSystemRepository.findByNameAndAndFamily(operatingSystem.getName(), operatingSystem.getGroup().getName());
            }
        }

        Request dbRequest = new Request();

        dbRequest.setUrl(servletRequest.getRequestURI());
        dbRequest.setType(servletRequest.getMethod());
        dbRequest.setOperatingSystem(requestOperatingSystem);
        dbRequest.setBrowser(requestBrowser);

        requestRepository.save(dbRequest);
    }

    @Override
    public void destroy() {
    }
}
