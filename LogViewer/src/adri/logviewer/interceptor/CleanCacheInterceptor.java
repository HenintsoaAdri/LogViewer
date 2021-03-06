package adri.logviewer.interceptor;

import java.io.File;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;


public class CleanCacheInterceptor extends MethodFilterInterceptor{

	@Override
	protected String doIntercept(ActionInvocation invocation) throws Exception {
		Map<String, Object> session = ActionContext.getContext().getSession();
		if(session.containsKey("tempFile")){
			File temp = (File) session.get("tempFile");
			temp.delete();
		}
		return invocation.invoke();
	}	
}