package adri.logviewer.interceptor;

import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

import adri.logviewer.action.BaseAction;
import adri.logviewer.model.Utilisateur;

public class PermissionInterceptor extends AbstractInterceptor{

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		Map<String, Object> session = ActionContext.getContext().getSession();
		Utilisateur user = (Utilisateur)session.get("user");
		BaseAction action = (BaseAction)invocation.getAction();
		boolean authorization = false;
		if(action.getItem()!= null){
			authorization = user.isGenerallyAllowed(action.getItem().instance());
		}
		if(authorization || user.equals(action.getItem())){
			return invocation.invoke();
		}
		return "permission";
	}
	
}
