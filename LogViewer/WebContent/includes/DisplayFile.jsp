<%@page import="java.io.InputStreamReader"%>
<%@page import="java.io.LineNumberReader"%>
<%@page import="java.io.FileInputStream"%>
									<pre class="frame"><%
										int currentPage = (int)request.getAttribute("page");
										FileInputStream in = null;
										LineNumberReader reader = null;
										int total = 0;
										int startLine = (currentPage)*100;
										try{
											in = (FileInputStream)request.getAttribute("fileInputStream");
											reader = new LineNumberReader(new InputStreamReader(in));
											String line;
											int i = 0;
											while((line = reader.readLine())!= null){
												line = line.replace("&", "&amp;");
												line = line.replace("<", "&lt;");
												line = line.replace(">", "&gt;");
												if(reader.getLineNumber() >= startLine && i < 100){
													out.println(line);
													i++;
												}
												total ++;
											}
										}finally{
											if(reader != null){
												reader.close();
											}
											if(in != null){
												in.close();
											}
										}										
									%></pre>