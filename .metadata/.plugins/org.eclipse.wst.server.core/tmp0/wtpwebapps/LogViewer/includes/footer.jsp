            <footer class="footer text-center"> 2017 &copy; Log viewer </footer>
</div>
        <!-- ============================================================== -->
        <!-- End Page Content -->
        <!-- ============================================================== -->
    </div>
    <!-- ============================================================== -->
    <!-- End Wrapper -->
    <!-- ============================================================== -->
    <!-- ============================================================== -->
    <!-- All Jquery -->
    <!-- ============================================================== -->
    <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
    <!-- Bootstrap Core JavaScript -->
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    <!-- Menu Plugin JavaScript -->
    <script src="${pageContext.request.contextPath}/js/sidebar-nav.min.js"></script>
    <!--slimscroll JavaScript -->
    <script src="${pageContext.request.contextPath}/js/jquery.slimscroll.js"></script>
    <!--Wave Effects -->
    <script src="${pageContext.request.contextPath}/js/waves.js"></script>
    <!-- Custom Theme JavaScript -->
    <script src="${pageContext.request.contextPath}/js/custom.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/jquery.canvasjs.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/jquery.toast.js"></script>
    <script src="${pageContext.request.contextPath}/js/jquery.tablesorter.min.js"></script>
    <script type="text/javascript">
		$(document).ready(function(){
		    $('.tablesorter').tablesorter();
		    $('th.header').append(' <i class="fa fa-sort fa-fw"><i>');
		});
	</script>
	<style>
		th.header { 
		    cursor: pointer; 
		}
	</style>