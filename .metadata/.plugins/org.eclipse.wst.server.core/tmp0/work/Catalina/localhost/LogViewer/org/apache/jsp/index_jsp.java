/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/8.0.32
 * Generated at: 2017-08-31 13:51:40 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import adri.logviewermain.model.PermissionType;
import java.util.List;
import adri.logviewermain.model.Utilisateur;

public final class index_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.HashMap<java.lang.String,java.lang.Long>(4);
    _jspx_dependants.put("/WEB-INF/lib/struts2-core-2.5.10.1.jar", Long.valueOf(1499148268374L));
    _jspx_dependants.put("jar:file:/A:/Projet/Projet%20Stage/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/LogViewer/WEB-INF/lib/struts2-core-2.5.10.1.jar!/META-INF/struts-tags.tld", Long.valueOf(1488788942000L));
    _jspx_dependants.put("/includes/footer.jsp", Long.valueOf(1500882424065L));
    _jspx_dependants.put("/includes/header.jsp", Long.valueOf(1504171669627L));
  }

  private static final java.util.Set<java.lang.String> _jspx_imports_packages;

  private static final java.util.Set<java.lang.String> _jspx_imports_classes;

  static {
    _jspx_imports_packages = new java.util.HashSet<>();
    _jspx_imports_packages.add("javax.servlet");
    _jspx_imports_packages.add("javax.servlet.http");
    _jspx_imports_packages.add("javax.servlet.jsp");
    _jspx_imports_classes = new java.util.HashSet<>();
    _jspx_imports_classes.add("java.util.List");
    _jspx_imports_classes.add("adri.logviewermain.model.Utilisateur");
    _jspx_imports_classes.add("adri.logviewermain.model.PermissionType");
  }

  private volatile javax.el.ExpressionFactory _el_expressionfactory;
  private volatile org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public java.util.Set<java.lang.String> getPackageImports() {
    return _jspx_imports_packages;
  }

  public java.util.Set<java.lang.String> getClassImports() {
    return _jspx_imports_classes;
  }

  public javax.el.ExpressionFactory _jsp_getExpressionFactory() {
    if (_el_expressionfactory == null) {
      synchronized (this) {
        if (_el_expressionfactory == null) {
          _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
        }
      }
    }
    return _el_expressionfactory;
  }

  public org.apache.tomcat.InstanceManager _jsp_getInstanceManager() {
    if (_jsp_instancemanager == null) {
      synchronized (this) {
        if (_jsp_instancemanager == null) {
          _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
        }
      }
    }
    return _jsp_instancemanager;
  }

  public void _jspInit() {
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
        throws java.io.IOException, javax.servlet.ServletException {

final java.lang.String _jspx_method = request.getMethod();
if (!"GET".equals(_jspx_method) && !"POST".equals(_jspx_method) && !"HEAD".equals(_jspx_method) && !javax.servlet.DispatcherType.ERROR.equals(request.getDispatcherType())) {
response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "JSPs only permit GET POST or HEAD");
return;
}

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html lang=\"en\">\r\n");
      out.write("\r\n");
      out.write("<head>\r\n");
      out.write("    <meta charset=\"utf-8\">\r\n");
      out.write("    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\r\n");
      out.write("    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\r\n");
      out.write("    <meta name=\"description\" content=\"\">\r\n");
      out.write("    <meta name=\"author\" content=\"\">\r\n");
      out.write("\t<link rel=\"apple-touch-icon\" sizes=\"57x57\" href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("/img/icon/apple-icon-57x57.png\">\r\n");
      out.write("\t<link rel=\"apple-touch-icon\" sizes=\"60x60\" href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("/img/icon/apple-icon-60x60.png\">\r\n");
      out.write("\t<link rel=\"apple-touch-icon\" sizes=\"72x72\" href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("/img/icon/apple-icon-72x72.png\">\r\n");
      out.write("\t<link rel=\"apple-touch-icon\" sizes=\"76x76\" href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("/img/icon/apple-icon-76x76.png\">\r\n");
      out.write("\t<link rel=\"apple-touch-icon\" sizes=\"114x114\" href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("/img/icon/apple-icon-114x114.png\">\r\n");
      out.write("\t<link rel=\"apple-touch-icon\" sizes=\"120x120\" href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("/img/icon/apple-icon-120x120.png\">\r\n");
      out.write("\t<link rel=\"apple-touch-icon\" sizes=\"144x144\" href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("/img/icon/apple-icon-144x144.png\">\r\n");
      out.write("\t<link rel=\"apple-touch-icon\" sizes=\"152x152\" href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("/img/icon/apple-icon-152x152.png\">\r\n");
      out.write("\t<link rel=\"apple-touch-icon\" sizes=\"180x180\" href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("/img/icon/apple-icon-180x180.png\">\r\n");
      out.write("\t<link rel=\"icon\" type=\"image/png\" sizes=\"192x192\"  href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("/img/icon/android-icon-192x192.png\">\r\n");
      out.write("\t<link rel=\"icon\" type=\"image/png\" sizes=\"32x32\" href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("/img/icon/favicon-32x32.png\">\r\n");
      out.write("\t<link rel=\"icon\" type=\"image/png\" sizes=\"96x96\" href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("/img/icon/favicon-96x96.png\">\r\n");
      out.write("\t<link rel=\"icon\" type=\"image/png\" sizes=\"16x16\" href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("/img/icon/favicon-16x16.png\">\r\n");
      out.write("\t<link rel=\"manifest\" href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("/img/icon/manifest.json\">\r\n");
      out.write("\t<meta name=\"msapplication-TileColor\" content=\"#ffffff\">\r\n");
      out.write("\t<meta name=\"msapplication-TileImage\" content=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("/img/icon/ms-icon-144x144.png\">\r\n");
      out.write("\t<meta name=\"theme-color\" content=\"#ffffff\">\r\n");
      out.write("    <title>LogViewer</title>\r\n");
      out.write("    <!-- Bootstrap Core CSS -->\r\n");
      out.write("    <link href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("/css/bootstrap.min.css\" rel=\"stylesheet\">\r\n");
      out.write("    <!-- Menu CSS -->\r\n");
      out.write("    <link href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("/css/sidebar-nav.min.css\" rel=\"stylesheet\">\r\n");
      out.write("    <!-- toast CSS -->\r\n");
      out.write("    <link href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("/css/jquery.toast.css\" rel=\"stylesheet\">\r\n");
      out.write("    <!-- animation CSS -->\r\n");
      out.write("    <link href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("/css/animate.css\" rel=\"stylesheet\">\r\n");
      out.write("    <!-- Custom CSS -->\r\n");
      out.write("    <link href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("/css/style.css\" rel=\"stylesheet\">\r\n");
      out.write("    <!-- FontAwesome CSS -->\r\n");
      out.write("    <link href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("/css/font-awesome.min.css\" rel=\"stylesheet\">\r\n");
      out.write("    <!-- color CSS -->\r\n");
      out.write("    <link href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("/css/colors/default.css\" id=\"theme\" rel=\"stylesheet\">\r\n");
      out.write("    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->\r\n");
      out.write("    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->\r\n");
      out.write("    <!--[if lt IE 9]>\r\n");
      out.write("    <script src=\"https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js\"></script>\r\n");
      out.write("    <script src=\"https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js\"></script>\r\n");
      out.write("<![endif]-->\r\n");
      out.write("</head>\r\n");
      out.write("\r\n");
 Utilisateur user = (Utilisateur)request.getAttribute("user"); 
      out.write("\r\n");
      out.write("<body class=\"fix-header\">\r\n");
      out.write("\t<script type=\"text/javascript\">\r\n");
      out.write("\t\tvar url = '");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("';\r\n");
      out.write("\t</script>\r\n");
      out.write("    <!-- ============================================================== -->\r\n");
      out.write("    <!-- Preloader -->\r\n");
      out.write("    <!-- ============================================================== -->\r\n");
      out.write("    <div class=\"preloader\">\r\n");
      out.write("        <svg class=\"circular\" viewBox=\"25 25 50 50\">\r\n");
      out.write("            <circle class=\"path\" cx=\"50\" cy=\"50\" r=\"20\" fill=\"none\" stroke-width=\"2\" stroke-miterlimit=\"10\" />\r\n");
      out.write("        </svg>\r\n");
      out.write("    </div>\r\n");
      out.write("    <!-- ============================================================== -->\r\n");
      out.write("    <!-- Wrapper -->\r\n");
      out.write("    <!-- ============================================================== -->\r\n");
      out.write("    <div id=\"wrapper\">\r\n");
      out.write("        <!-- ============================================================== -->\r\n");
      out.write("        <!-- Topbar header - style you can find in pages.scss -->\r\n");
      out.write("        <!-- ============================================================== -->\r\n");
      out.write("        <nav class=\"navbar navbar-default navbar-static-top m-b-0\">\r\n");
      out.write("            <div class=\"navbar-header\">\r\n");
      out.write("                <div class=\"top-left-part\">\r\n");
      out.write("                    <!-- Logo -->\r\n");
      out.write("                    <a class=\"logo\" href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("/index\">\r\n");
      out.write("                        <!-- Logo icon image, you can use font-icon also --><b>\r\n");
      out.write("                        <span class=\"visible-xs\">\r\n");
      out.write("                        <!--This is dark logo icon--><img src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("/img/Logo-white.png\" alt=\"home\" class=\"dark-logo\" /><!--This is light logo icon--><img src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("/img/Logo-transparent.png\" alt=\"home\" class=\"light-logo\" />\r\n");
      out.write("                        </span>\r\n");
      out.write("                     </b>\r\n");
      out.write("                        <!-- Logo text image you can use text also -->\r\n");
      out.write("                        <span class=\"hidden-xs\">\r\n");
      out.write("                        <!--This is dark logo text--><img src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("/img/Logo-large-white.png\" alt=\"home\" class=\"dark-logo\" /><!--This is light logo text--><img src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("/img/Logo-large-transparent.png\" alt=\"home\" class=\"light-logo\" />\r\n");
      out.write("                     \t</span> \r\n");
      out.write("                     </a>\r\n");
      out.write("                </div>\r\n");
      out.write("                <!-- /Logo -->\r\n");
      out.write("                <ul class=\"nav navbar-top-links navbar-right pull-right\">\r\n");
      out.write("                \t<li>\r\n");
      out.write("                \t\t<a class=\"waves-effect waves-light\" href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("/MesInformations\">");
 out.print(user.getFullName()); 
      out.write("</a>\r\n");
      out.write("                \t</li>\r\n");
      out.write("                    <li>\r\n");
      out.write("                        <a class=\"waves-effect waves-light\" href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("/Deconnexion\"><i class=\"fa fa-sign-out fw\"></i> D&eacute;connexion</a>\r\n");
      out.write("                    </li>\r\n");
      out.write("                </ul>\r\n");
      out.write("            </div>\r\n");
      out.write("            <!-- /.navbar-header -->\r\n");
      out.write("            <!-- /.navbar-top-links -->\r\n");
      out.write("            <!-- /.navbar-static-side -->\r\n");
      out.write("        </nav>\r\n");
      out.write("        <!-- End Top Navigation -->\r\n");
      out.write("        <!-- ============================================================== -->\r\n");
      out.write("        <!-- Left Sidebar - style you can find in sidebar.scss  -->\r\n");
      out.write("        <!-- ============================================================== -->\r\n");
      out.write("        <div class=\"navbar-default sidebar\" role=\"navigation\">\r\n");
      out.write("            <div class=\"sidebar-nav slimscrollsidebar\">\r\n");
      out.write("                <div class=\"sidebar-head\">\r\n");
      out.write("                    <h3><span class=\"fa-fw open-close\"><i class=\"ti-close ti-menu\"></i></span> <span class=\"hide-menu\">Navigation</span></h3>\r\n");
      out.write("                </div>\r\n");
      out.write("                <ul class=\"nav\" id=\"side-menu\">\r\n");
      out.write("                    <li style=\"padding: 70px 0 0;\">\r\n");
      out.write("                        <a href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("/index\" class=\"waves-effect\"><i class=\"fa fa-dashboard fa-fw\" aria-hidden=\"true\"></i>Board</a>\r\n");
      out.write("                    </li>\r\n");
      out.write("                    ");
 if(user.isGenerallyAllowed("Agent")){ 
      out.write("\r\n");
      out.write("                    <li>\r\n");
      out.write("                        <a href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("/Agent\" class=\"waves-effect\"><i class=\"fa fa-gear fa-fw\" aria-hidden=\"true\"></i>Agent</a>\r\n");
      out.write("                    </li>\r\n");
      out.write("                    ");
 } if(user.isGenerallyAllowed("Groupe")){ 
      out.write("\r\n");
      out.write("                    <li>\r\n");
      out.write("                        <a href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("/Groupe\" class=\"waves-effect\"><i class=\"fa fa-gears fa-fw\" aria-hidden=\"true\"></i>Groupe</a>\r\n");
      out.write("                    </li>\r\n");
      out.write("                    ");
 } if(user.isGenerallyAllowed("Utilisateur")){ 
      out.write("\r\n");
      out.write("                    <li>\r\n");
      out.write("                    \t<a href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("/Utilisateur\" class=\"waves-effect\"><i class=\"fa fa-address-card fa-fw\" aria-hidden=\"true\"></i>Utilisateur</a>\r\n");
      out.write("                \t</li>\r\n");
      out.write("                \t");
 } if(user.isGenerallyAllowed("Profil")){ 
      out.write("\r\n");
      out.write("                    <li>\r\n");
      out.write("                        <a href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("/Profil\" class=\"waves-effect\"><i class=\"fa fa-shield fa-fw\" aria-hidden=\"true\"></i>Profil</a>\r\n");
      out.write("                    </li>\r\n");
      out.write("                    ");
 } if(user.isGenerallyAllowed("Fichier")){
      out.write("\r\n");
      out.write("                    <li>\r\n");
      out.write("                        <a href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("/Fichier\" class=\"waves-effect\"><i class=\"fa fa-save fa-fw\" aria-hidden=\"true\"></i>Fichiers enregistr&eacute;s</a>\r\n");
      out.write("                    </li>\r\n");
      out.write("                    ");
 } 
      out.write("\r\n");
      out.write("                    <li>\r\n");
      out.write("                        <a href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("/MesInformations\" class=\"waves-effect\"><i class=\"fa fa-user fa-fw\" aria-hidden=\"true\"></i>Mes Informations</a>\r\n");
      out.write("                    </li>\r\n");
      out.write("\r\n");
      out.write("                </ul>\r\n");
      out.write("            </div>\r\n");
      out.write("            \r\n");
      out.write("        </div>\r\n");
      out.write("        <!-- ============================================================== -->\r\n");
      out.write("        <!-- End Left Sidebar -->\r\n");
      out.write("        <!-- ============================================================== -->\r\n");
      out.write("        <!-- ============================================================== -->\r\n");
      out.write("        <!-- Page Content -->\r\n");
      out.write("        <!-- ============================================================== -->\r\n");
      out.write("        <div id=\"page-wrapper\">");
      out.write("\r\n");
      out.write("            <div class=\"container-fluid\">\r\n");
      out.write("                <div class=\"row bg-title\">\r\n");
      out.write("                    <div class=\"col-lg-3 col-md-4 col-sm-4 col-xs-12\">\r\n");
      out.write("                        <h4 class=\"page-title\">Dashboard</h4> </div>\r\n");
      out.write("                    <div class=\"col-lg-9 col-sm-8 col-md-8 col-xs-12\">\r\n");
      out.write("                        <ol class=\"breadcrumb\">\r\n");
      out.write("                            <li><a href=\"#\">Log viewer</a></li>\r\n");
      out.write("                        </ol>\r\n");
      out.write("                    </div>\r\n");
      out.write("                    <!-- /.col-lg-12 -->\r\n");
      out.write("                </div>\r\n");
      out.write("                <!-- /.row -->\r\n");
      out.write("                <!-- ============================================================== -->\r\n");
      out.write("                <!-- Different data widgets -->\r\n");
      out.write("                <!-- ============================================================== -->\r\n");
      out.write("                <!-- .row --> \r\n");
      out.write("\t\t        ");

		        	if(request.getAttribute("exception") != null){ 
		      		Exception e = (Exception)request.getAttribute("exception");
		        
      out.write("\r\n");
      out.write("\t\t      \t<div class=\"alert alert-danger alert-dismissable\">\r\n");
      out.write("\t                <button type=\"button\" class=\"close\" data-dismiss=\"alert\" aria-hidden=\"true\">&times;</button>\r\n");
      out.write("\t      \t\t\t");
 out.print(e.getMessage()); 
      out.write("\r\n");
      out.write("\t            </div>\r\n");
      out.write("                <!--./row-->\r\n");
      out.write("\t\t\t\t");
 } 
      out.write("\r\n");
      out.write("                <!--row -->\r\n");
      out.write("\r\n");
      out.write("            </div>\r\n");
      out.write("            <!-- /.container-fluid -->\r\n");
      out.write("            <footer class=\"footer text-center\"> 2017 &copy; Log viewer </footer>\r\n");
      out.write("</div>\r\n");
      out.write("        <!-- ============================================================== -->\r\n");
      out.write("        <!-- End Page Content -->\r\n");
      out.write("        <!-- ============================================================== -->\r\n");
      out.write("    </div>\r\n");
      out.write("    <!-- ============================================================== -->\r\n");
      out.write("    <!-- End Wrapper -->\r\n");
      out.write("    <!-- ============================================================== -->\r\n");
      out.write("    <!-- ============================================================== -->\r\n");
      out.write("    <!-- All Jquery -->\r\n");
      out.write("    <!-- ============================================================== -->\r\n");
      out.write("    <script src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("/js/jquery-3.1.1.min.js\"></script>\r\n");
      out.write("    <!-- Bootstrap Core JavaScript -->\r\n");
      out.write("    <script src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("/js/bootstrap.min.js\"></script>\r\n");
      out.write("    <!-- Menu Plugin JavaScript -->\r\n");
      out.write("    <script src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("/js/sidebar-nav.min.js\"></script>\r\n");
      out.write("    <!--slimscroll JavaScript -->\r\n");
      out.write("    <script src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("/js/jquery.slimscroll.js\"></script>\r\n");
      out.write("    <!--Wave Effects -->\r\n");
      out.write("    <script src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("/js/waves.js\"></script>\r\n");
      out.write("    <!--Counter js -->\r\n");
      out.write("    <script src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("/js/jquery.waypoints.min.js\"></script>\r\n");
      out.write("    <script src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("/js/jquery.counterup.min.js\"></script>\r\n");
      out.write("    <!-- Custom Theme JavaScript -->\r\n");
      out.write("    <script src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("/js/custom.min.js\"></script>\r\n");
      out.write("    <script src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("/js/jquery.toast.js\"></script>\r\n");
      out.write("</body>\r\n");
      out.write("\r\n");
      out.write("</html>");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try {
            if (response.isCommitted()) {
              out.flush();
            } else {
              out.clearBuffer();
            }
          } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
