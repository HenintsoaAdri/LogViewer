package adri.logviewer.interceptor;

import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

import adri.logviewer.model.Utilisateur;

public class FilePermissionInterceptor extends AbstractInterceptor{

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		Map<String, Object> session = ActionContext.getContext().getSession();
		Utilisateur user = (Utilisateur)session.get("user");
		if(user.isGenerallyAllowed("Fichier")){
			return invocation.invoke();
		}
		return "permission";
	}
	
}
