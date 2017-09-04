package adri.logviewermain.interceptor;

import java.util.Map;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class LoginInterceptor extends AbstractInterceptor {

	@Override
	public String intercept(ActionInvocation arg0) throws Exception {
		Map<String, Object> session = ActionContext.getContext().getSession();
		if(!session.containsKey("user")){
			return Action.LOGIN;
		}
		return arg0.invoke();
	}

}
